package com.mimouse.simunutilslib.encryption;

import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Description: （Password Based Encryption）算法
 * Created by wk on 2018/8/3.
 */

public class PBE {
    private static PBE pbe = new PBE();

    public static PBE getInstance()
    {
        return pbe;
    }

    private PBE() {}

    /**
     * PBE算法
     *
     */
    public static enum HQPBEAlgorithm
    {
        PBEWithMD5AndDES("PBEWithMD5AndDES"), PBEWithSHA1AndDESede("PBEWithSHA1AndDESede"), PBEWithSHA1AndRC2_40(
            "PBEWithSHA1AndRC2_40");
        private String name;

        private HQPBEAlgorithm(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }

    /**
     * 初始化盐
     *
     * @return
     */
    public byte[] initSalt()
    {
        byte[] salt = new byte[8];
        Random random = new Random();
        random.nextBytes(salt);
        return salt;
    }

    private static Key toKey(String peb, char[] password) throws Exception
    {
        PBEKeySpec keySpec = new PBEKeySpec(password);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(peb);
        SecretKey secretKey = keyFactory.generateSecret(keySpec);
        return secretKey;
    }

    public byte[] encrypt(HQPBEAlgorithm algorithm, byte[] data, char[] password, byte[] salt) throws Exception
    {
        return encrypt(algorithm.getName(), data, password, salt);
    }

    public byte[] encrypt(String algorithm, byte[] data, char[] password, byte[] salt) throws Exception
    {
        return operate(Cipher.ENCRYPT_MODE, algorithm, data, password, salt);
    }

    public byte[] decrypt(HQPBEAlgorithm algorithm, byte[] data, char[] password, byte[] salt) throws Exception
    {
        return decrypt(algorithm.getName(), data, password, salt);
    }

    public byte[] decrypt(String algorithm, byte[] data, char[] password, byte[] salt) throws Exception
    {
        return operate(Cipher.DECRYPT_MODE, algorithm, data, password, salt);
    }

    private byte[] operate(int mode, String algorithm, byte[] data, char[] password, byte[] salt) throws Exception
    {
        Key key = toKey(algorithm, password);
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance(algorithm.toString());
        cipher.init(mode, key, paramSpec);
        return cipher.doFinal(data);
    }
}
