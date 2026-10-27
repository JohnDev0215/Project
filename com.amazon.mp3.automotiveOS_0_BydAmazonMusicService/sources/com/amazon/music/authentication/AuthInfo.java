package com.amazon.music.authentication;

/* JADX INFO: loaded from: classes4.dex */
public interface AuthInfo {
    String find(AuthDataType authDataType) throws AuthenticationException;

    void resetSessionId();
}
