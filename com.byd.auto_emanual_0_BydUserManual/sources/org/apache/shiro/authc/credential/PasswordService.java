package org.apache.shiro.authc.credential;

/* JADX INFO: loaded from: classes.dex */
public interface PasswordService {
    String encryptPassword(Object obj) throws IllegalArgumentException;

    boolean passwordsMatch(Object obj, String str);
}
