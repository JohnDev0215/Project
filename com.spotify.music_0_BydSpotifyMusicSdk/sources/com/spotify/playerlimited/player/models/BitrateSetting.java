package com.spotify.playerlimited.player.models;

import kotlin.collections.b;

/* JADX INFO: loaded from: classes.dex */
public enum BitrateSetting {
    AUTO(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    VERY_HIGH(4);

    private final int nativeCode;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        new Companion(0);
        b.w0(values());
    }

    BitrateSetting(int i) {
        this.nativeCode = i;
    }

    public final int a() {
        return this.nativeCode;
    }
}
