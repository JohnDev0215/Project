package org.apache.shiro.authc.credential;

import org.apache.shiro.crypto.hash.Md5Hash;

/* JADX INFO: loaded from: classes.dex */
public class Md5CredentialsMatcher extends HashedCredentialsMatcher {
    public Md5CredentialsMatcher() {
        setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
    }
}
