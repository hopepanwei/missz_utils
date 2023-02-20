package top.missz.controller;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.missz.service.MD5GenerateService;
import top.missz.service.impl.MD5GenerateServiceImpl;

/**
 * @author zhangpan
 * @date 2023/2/20
 */
@Controller("/common")
public class CommonController {


    private MD5GenerateService md5GenerateService;

    @PostMapping("/stopGenerateMd5")
    @ResponseBody
    public ResponseEntity stopGenerateMd5(){
        MD5GenerateServiceImpl.generateMd5 = false;
        return ResponseEntity.ok().build();
    }


    @GetMapping("/startGenerateMd5Key")
    @ResponseBody
    public ResponseEntity startGenerateMd5Key(@RequestParam int length){
        md5GenerateService.generateMd5Key(length);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/generateMd5")
    @ResponseBody
    public ResponseEntity generateMd5(){
        md5GenerateService.generateMd5();
        return ResponseEntity.ok().build();
    }







    public MD5GenerateService getMd5GenerateService() {
        return md5GenerateService;
    }

    @Autowired
    public void setMd5GenerateService(MD5GenerateService md5GenerateService) {
        this.md5GenerateService = md5GenerateService;
    }
}
