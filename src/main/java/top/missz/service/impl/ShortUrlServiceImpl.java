package top.missz.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.missz.entity.CmnShortUrl;
import top.missz.filter.ShortUrlBloomFilter;
import top.missz.repository.CmnShortUrlMapper;
import top.missz.service.ShortUrlService;
import top.missz.util.URLUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private CmnShortUrlMapper shortUrlMapper;

    /**
     * 生成短链接
     *
     * @param longUrl
     * @param baseUrl
     * @return
     */
    @Override
    public CmnShortUrl genShortUrl(String longUrl, String baseUrl) {
        String shortURL = URLUtil.getShortURL();
        CmnShortUrl build = CmnShortUrl.builder()
                .shorts(shortURL)
                .longUrl(longUrl)
                .shortUrl(baseUrl + "/s/" + shortURL).build();
        shortUrlMapper.insertSelective(build);
        ShortUrlBloomFilter.put(build.getShorts());
        return build;
    }

    /**
     * 转换为原链接
     *
     * @param shorts
     * @return
     */
    @Override
    public CmnShortUrl genLongUrl(String shorts) {
        if (ShortUrlBloomFilter.mightContain(shorts)) {
            CmnShortUrl build = CmnShortUrl.builder().shorts(shorts).build();
            build = shortUrlMapper.selectByShorts(build);
            return build;
        }
        return null;
    }

    /**
     * 获取全部数据
     *
     * @return
     */
    @Override
    public List<String> getAllShort() {
        return shortUrlMapper.findAll().stream().map(CmnShortUrl::getShorts).collect(Collectors.toList());
    }

    public CmnShortUrlMapper getShortUrlMapper() {
        return shortUrlMapper;
    }

    @Autowired
    public void setShortUrlMapper(CmnShortUrlMapper shortUrlMapper) {
        this.shortUrlMapper = shortUrlMapper;
    }
}
