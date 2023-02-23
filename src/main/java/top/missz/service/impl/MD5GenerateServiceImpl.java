package top.missz.service.impl;

import com.twmacinta.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.missz.entity.CmnMd5;
import top.missz.repository.CmnMd5Mapper;
import top.missz.service.MD5GenerateService;
import top.missz.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangpan
 * @Date 2023/2/16
 */
@Service
public class MD5GenerateServiceImpl implements MD5GenerateService {
    private static final Logger logger = LoggerFactory.getLogger(MD5GenerateServiceImpl.class);

    private CmnMd5Mapper cmnMd5Mapper;

    public static boolean generateMd5 = true;


    @Override
    public void generateMd5Key(int length) {
        String key = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        List<String> list = permutationNoRepeat(Arrays.asList(key.split("")), length);
//        List<List<String>> lists = StringUtils.splitList(list, 10000);
//        for (List<String> strings : lists) {
//            List<CmnMd5> keys = new ArrayList<>();
//            for (String string : strings) {
//                keys.add(CmnMd5.builder().md5Key(string)
//                        .keyLength(length).build());
//            }
//            cmnMd5Mapper.batchInsert(keys);
//        }
        Long lastId = 0L;
        String[] list = key.split("");
        while (true){
            List<CmnMd5> listMd5 = cmnMd5Mapper.getListByLength(lastId, length - 1);
            if (listMd5.size() == 0) {
                break;
            }
            CmnMd5 lastBean = listMd5.get(listMd5.size() - 1);
            lastId = lastBean.getId();
            List<CmnMd5> newList = new ArrayList<>();
            listMd5.parallelStream().forEach(bean -> {
                for (String s : list) {
                    newList.add(CmnMd5.builder().md5Key(bean.getMd5Key() + s).keyLength(length).build());
                }
            });
            cmnMd5Mapper.batchInsert(newList);
        }
    }

    @Override
    public void generateMd5() {
        generateMd5 = true;
        Long lastId = 0L;
        while (generateMd5) {
            List<CmnMd5> list = cmnMd5Mapper.selectNotGenerate(lastId, 10000);
            if (list.size() == 0) {
                break;
            }
            CmnMd5 lastBean = list.get(list.size() - 1);
            lastId = lastBean.getId();
            long startTime = System.currentTimeMillis();
            list.parallelStream().forEach(bean -> {
                try {
                    MD5 md5 = new MD5();
                    md5.Update(bean.getMd5Key(), null);
                    String hash = md5.asHex();
                    md5.Init();
                    bean.setValue(hash);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            });
            long endTime = System.currentTimeMillis();
            logger.info("耗时：{}", (endTime - startTime));
            cmnMd5Mapper.updateBatch(list);
        }
    }


    public static List<String> permutationNoRepeat(List<String> list, int length) {
        Stream<String> stream = list.stream();
        for (int n = 1; n < length; n++) {
            stream = stream.flatMap(str -> list.stream()
                    .filter(temp -> !str.contains(temp))
                    .map(str::concat));
        }
        return stream.collect(Collectors.toList());
    }

    //通过java.math包的BigInteger类实现十六进制的转换
    public static String MD5Operation(String s){
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(strTemp);
            byte[] b = md.digest();

            BigInteger bigInt = new BigInteger(1, b);
            return bigInt.toString(16);

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }


    @Autowired
    public void setCmnMd5Mapper(CmnMd5Mapper cmnMd5Mapper) {
        this.cmnMd5Mapper = cmnMd5Mapper;
    }
}
