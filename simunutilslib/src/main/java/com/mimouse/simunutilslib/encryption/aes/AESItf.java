package com.mimouse.simunutilslib.encryption.aes;

/**
 * Description:AES加密接口
 * Created by wk on 2018/8/7.
 */

public interface AESItf {
    String doEncrypt(String content,String password);
    String doDecrypt(String content,String password);
}
