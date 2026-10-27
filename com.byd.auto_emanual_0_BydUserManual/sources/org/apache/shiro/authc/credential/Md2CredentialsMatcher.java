package org.apache.shiro.authc.credential;

import org.apache.shiro.crypto.hash.Md2Hash;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class Md2CredentialsMatcher extends HashedCredentialsMatcher {
    public Md2CredentialsMatcher() {
        setHashAlgorithmName(Md2Hash.ALGORITHM_NAME);
    }
}
