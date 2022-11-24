package com.pilot.hash;

import org.junit.Assert;
import org.junit.Test;

public class Sha256UtilTest {

    @Test
    public void test() throws Exception {
        String account = "user1";
        String password = "apple";

        String encrypted = Sha256Util.sign(password, account);
        System.out.println(encrypted);

        Assert.assertTrue(Sha256Util.verify(encrypted, password, account));
        Assert.assertFalse(Sha256Util.verify(encrypted, password, "user2"));
        Assert.assertFalse(Sha256Util.verify(encrypted, "applee", account));
    }
}