package org.apache.shiro.authc.credential;

import org.apache.shiro.crypto.hash.Sha384Hash;

/* JADX INFO: loaded from: classes.dex */
public class Sha384CredentialsMatcher extends HashedCredentialsMatcher {
    public Sha384CredentialsMatcher() {
        setHashAlgorithmName(Sha384Hash.ALGORITHM_NAME);
    }
}
