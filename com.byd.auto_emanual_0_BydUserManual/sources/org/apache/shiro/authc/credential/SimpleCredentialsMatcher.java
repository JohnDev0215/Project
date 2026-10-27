package org.apache.shiro.authc.credential;

import java.security.MessageDigest;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.config.Ini;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class SimpleCredentialsMatcher extends CodecSupport implements CredentialsMatcher {
    private static final Logger log = LoggerFactory.getLogger(SimpleCredentialsMatcher.class);

    protected Object getCredentials(AuthenticationToken authenticationToken) {
        return authenticationToken.getCredentials();
    }

    protected Object getCredentials(AuthenticationInfo authenticationInfo) {
        return authenticationInfo.getCredentials();
    }

    protected boolean equals(Object obj, Object obj2) {
        Logger logger = log;
        if (logger.isDebugEnabled()) {
            logger.debug("Performing credentials equality check for tokenCredentials of type [" + obj.getClass().getName() + " and accountCredentials of type [" + obj2.getClass().getName() + Ini.SECTION_SUFFIX);
        }
        if (isByteSource(obj) && isByteSource(obj2)) {
            if (logger.isDebugEnabled()) {
                logger.debug("Both credentials arguments can be easily converted to byte arrays.  Performing array equals comparison");
            }
            return MessageDigest.isEqual(toBytes(obj), toBytes(obj2));
        }
        return obj2.equals(obj);
    }

    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        return equals(getCredentials(authenticationToken), getCredentials(authenticationInfo));
    }
}
