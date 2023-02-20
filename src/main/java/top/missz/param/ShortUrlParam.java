package top.missz.param;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


/**
 * @author: wxy
 * @create: 2022-02-23 17:29
 * @email: admin@mail.wxy97.com
 * @description:
 **/
@Data
public class ShortUrlParam {
    @NotNull(message = "参数错误")
    @Pattern(regexp = "http(s*)://[^\\s]*",message = "不是合法的url")
    private String longUrl;
}
