package top.missz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmnMd5 {
    /**
     * ID
     */
    private Long id;

    private String md5Key;

    private String value;

    private Integer keyLength;

}