package org.apache.shiro.authc.credential;

import org.apache.shiro.crypto.hash.Sha1Hash;

/* JADX INFO: loaded from: classes.dex */
public class Sha1CredentialsMatcher extends HashedCredentialsMatcher {
    public Sha1CredentialsMatcher() {
        setHashAlgorithmName(Sha1Hash.ALGORITHM_NAME);
    }
}
