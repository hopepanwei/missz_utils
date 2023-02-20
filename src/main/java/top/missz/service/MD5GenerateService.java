package top.missz.service;

import org.springframework.scheduling.annotation.Async;

public interface MD5GenerateService {

    @Async
    void generateMd5Key(int length);

    @Async
    void generateMd5();
}
