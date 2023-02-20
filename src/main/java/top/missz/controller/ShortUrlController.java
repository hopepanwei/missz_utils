package top.missz.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.missz.entity.CmnShortUrl;
import top.missz.param.ShortUrlParam;
import top.missz.service.ShortUrlService;
import top.missz.util.URLUtil;

import java.io.IOException;
import java.util.Objects;

/**
 * @author wxy
 */
@Controller
public class ShortUrlController {

    private ShortUrlService shortUrlService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 生成短链接
     *
     * @param param   需要转换的链接
     * @param request
     * @return
     */
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity shortUrl(@Validated @RequestBody ShortUrlParam param, HttpServletRequest request) {
        return new ResponseEntity(shortUrlService.genShortUrl(param.getLongUrl(), URLUtil.getUrlStart(request)), HttpStatus.OK);
    }

    /**
     * 访问短链接
     *
     * @param response
     * @param shorts
     * @return
     */
    @GetMapping("/s/{shorts}")
    @ResponseBody
    public ResponseEntity redirectUrl(HttpServletResponse response, @PathVariable String shorts) {
        CmnShortUrl shortUrl = shortUrlService.genLongUrl(shorts);
        if (Objects.nonNull(shortUrl)) {
            try {
                response.sendRedirect(shortUrl.getLongUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("不存在的短链接");
        }
    }


    public ShortUrlService getShortUrlService() {
        return shortUrlService;
    }

    @Autowired
    public void setShortUrlService(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }
}
