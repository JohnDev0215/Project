package org.apache.shiro.authc.credential;

import org.apache.shiro.crypto.hash.Sha512Hash;

/* JADX INFO: loaded from: classes.dex */
public class Sha512CredentialsMatcher extends HashedCredentialsMatcher {
    public Sha512CredentialsMatcher() {
        setHashAlgorithmName(Sha512Hash.ALGORITHM_NAME);
    }
}
