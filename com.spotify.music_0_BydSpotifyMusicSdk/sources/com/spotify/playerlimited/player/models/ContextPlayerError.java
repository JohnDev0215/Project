package com.spotify.playerlimited.player.models;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public final class ContextPlayerError {

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
}
