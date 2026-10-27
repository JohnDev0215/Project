package com.amazon.music.media.playback.config;

import androidx.annotation.NonNull;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class PlayerAuthentication {
    private final String adpToken;
    private final String signingKey;

    public PlayerAuthentication(@NonNull String str, @NonNull String str2) {
        this.adpToken = (String) Validate.notNull(str);
        this.signingKey = (String) Validate.notNull(str2);
    }

    public String getAdpToken() {
        return this.adpToken;
    }

    public String getSigningKey() {
        return this.signingKey;
    }
}
