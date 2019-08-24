package com.vijay.utils;

import com.vijay.services.SearchServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;

public class CryptoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceTest.class);

    @Test
    public void testHash()  throws NoSuchAlgorithmException {
        String password = "vijay";

        LOGGER.info(CryptoUtils.hashSha256(password));
        String hashed = "77+9aBYw77+9FzJ0RRnvv73vv70e77+977+9TFfvv70JKDnHtUwI77+91Znvv73vv70s77+9";

        LOGGER.info("Same => {}",CryptoUtils.hashSha256(password).equals(hashed));
        Assert.assertEquals(true, CryptoUtils.hashSha256(password).equals(hashed));
    }
}
