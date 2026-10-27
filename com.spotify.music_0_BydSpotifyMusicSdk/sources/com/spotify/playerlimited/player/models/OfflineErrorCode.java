package com.spotify.playerlimited.player.models;

import com.spotify.playerlimited.player.moshi.MoshiRootName;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import p.c71;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@MoshiRootName("error")
@c71(generateAdapter = true)
public final class OfflineErrorCode {
    public int a;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface ErrorCode {
    }

    static {
        new Companion(0);
    }

    @ErrorCode
    @z61(name = "code")
    public static /* synthetic */ void getCode$annotations() {
    }
}
