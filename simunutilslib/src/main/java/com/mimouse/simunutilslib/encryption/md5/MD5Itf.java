package com.mimouse.simunutilslib.encryption.md5;

import java.io.File;

/**
 * Description:MD5加密类
 * Created by wk on 2018/8/7.
 */

public interface MD5Itf {
    String md5(String string);
    String md5(File file);
    String md5Nio(File file);
    String md5(String string, int times);
    String md5(String string, String slat);
}
