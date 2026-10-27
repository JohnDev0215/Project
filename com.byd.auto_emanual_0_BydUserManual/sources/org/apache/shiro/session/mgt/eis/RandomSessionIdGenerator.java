package org.apache.shiro.session.mgt.eis;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class RandomSessionIdGenerator implements SessionIdGenerator {
    private static final String RANDOM_NUM_GENERATOR_ALGORITHM_NAME = "SHA1PRNG";
    private static final Logger log = LoggerFactory.getLogger(RandomSessionIdGenerator.class);
    private Random random;

    public RandomSessionIdGenerator() {
        try {
            this.random = SecureRandom.getInstance(RANDOM_NUM_GENERATOR_ALGORITHM_NAME);
        } catch (NoSuchAlgorithmException e) {
            log.debug("The SecureRandom SHA1PRNG algorithm is not available on the current platform.  Using the platform's default SecureRandom algorithm.", e);
            this.random = new SecureRandom();
        }
    }

    public Random getRandom() {
        return this.random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    @Override // org.apache.shiro.session.mgt.eis.SessionIdGenerator
    public Serializable generateId(Session session) {
        return Long.toString(getRandom().nextLong());
    }
}
