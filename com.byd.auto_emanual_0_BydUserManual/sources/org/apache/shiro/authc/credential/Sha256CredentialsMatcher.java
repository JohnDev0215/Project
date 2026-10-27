package org.apache.shiro.authc.credential;

/* JADX INFO: loaded from: classes.dex */
public class Sha256CredentialsMatcher extends HashedCredentialsMatcher {
    public Sha256CredentialsMatcher() {
        setHashAlgorithmName("SHA-256");
    }
}
