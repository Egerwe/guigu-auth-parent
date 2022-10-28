package com.atguigu.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author Yjw
 * 2022/10/28 23:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuiguException extends RuntimeException{

    private Integer code;
    private String msg;

}
