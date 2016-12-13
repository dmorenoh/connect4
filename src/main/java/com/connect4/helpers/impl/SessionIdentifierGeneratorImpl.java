package com.connect4.helpers.impl;

import com.connect4.helpers.SessionIdentifierGenerator;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by dmorenoh on 13/11/16.
 */
@Component
public class SessionIdentifierGeneratorImpl implements SessionIdentifierGenerator {
    private static final SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}
