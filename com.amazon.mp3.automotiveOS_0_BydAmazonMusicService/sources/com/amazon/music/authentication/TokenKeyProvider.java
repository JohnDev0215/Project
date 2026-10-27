package com.amazon.music.authentication;

import com.amazon.identity.auth.device.api.TokenKeys;

/* JADX INFO: loaded from: classes4.dex */
public class TokenKeyProvider {
    public String getAdpTokenKeyForPackage(String str) {
        return TokenKeys.getAdpTokenKeyForPackage(str);
    }

    public String getPrivateKeyKeyForPackage(String str) {
        return TokenKeys.getPrivateKeyKeyForPackage(str);
    }

    public String getAccessTokenKeyForPackage(String str) {
        return TokenKeys.getAccessTokenKeyForPackage(str);
    }

    public String getActorAccessTokenKeyForPackage(String str) {
        return TokenKeys.getActorAccessTokenKeyForPackage(str);
    }
}
