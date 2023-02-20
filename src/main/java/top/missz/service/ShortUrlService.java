package top.missz.service;

import top.missz.entity.CmnShortUrl;

import java.util.List;

public interface ShortUrlService {


    CmnShortUrl genShortUrl(String longUrl, String baseUrl);

    CmnShortUrl genLongUrl(String shorts);

    List<String> getAllShort();
}
