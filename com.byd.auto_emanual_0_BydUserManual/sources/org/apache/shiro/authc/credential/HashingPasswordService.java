package org.apache.shiro.authc.credential;

import org.apache.shiro.crypto.hash.Hash;

/* JADX INFO: loaded from: classes.dex */
public interface HashingPasswordService extends PasswordService {
    Hash hashPassword(Object obj) throws IllegalArgumentException;

    boolean passwordsMatch(Object obj, Hash hash);
}
