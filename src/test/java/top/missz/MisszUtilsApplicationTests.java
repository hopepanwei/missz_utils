package top.missz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.missz.service.MD5GenerateService;

@SpringBootTest
class MisszUtilsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    MD5GenerateService md5GenerateService;

    @Test
    public void generateMd5Key(){
        md5GenerateService.generateMd5Key(5);
    }

    @Test
    public void generateMd5(){
        md5GenerateService.generateMd5();
    }

}
