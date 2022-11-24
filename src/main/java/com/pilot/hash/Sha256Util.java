package com.pilot.hash;

import java.security.MessageDigest;
import java.util.Base64;

public class Sha256Util {
    private static final String SALT = "ANYMON4:";
    private static final String CHARSET = "UTF-8";
    private static final String SHA256_PREFFIX = "SHA256:";
    private static final String SHA256X_PREFIX = "SHA256X:";

    public static String sign(String text) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update((SALT + text).getBytes(CHARSET));
        byte[] digest = md.digest();
        return SHA256_PREFFIX + Base64.getEncoder().encodeToString(digest);
    }

    public static String sign(String text, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update((salt + ":" + text).getBytes(CHARSET));
        byte[] digest = md.digest();
        return SHA256X_PREFIX + Base64.getEncoder().encodeToString(digest);
    }

    public static boolean verify(String hashText, String plainText, String salt) throws Exception {
        if (hashText == null || plainText == null)
            throw new Exception("two parameter are required");
        if (hashText.startsWith(SHA256_PREFFIX)) {
            return hashText.equals(sign(plainText));
        } else if (hashText.startsWith(SHA256X_PREFIX)) {
            return hashText.equals(sign(plainText, salt));
        } else {
            return hashText.equals(plainText);
        }
    }

    public static String addSHA256Prefix(String hash) {
        return String.format("%s%s", SHA256_PREFFIX, hash);
    }

    public static String removeSHA256Prefix(String hash) {
        if (hash.startsWith(SHA256_PREFFIX))
            return hash.substring(SHA256_PREFFIX.length());
        return hash;
    }

}
