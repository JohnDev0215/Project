package com.spotify.playerlimited.player.models;

import p.c71;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class SocialConnectSessionUpdate {
    public String a;
    public SocialConnectSession b;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        new Companion(0);
    }

    @z61(name = "reason")
    public static /* synthetic */ void getReason$annotations() {
    }

    @z61(name = "session")
    public static /* synthetic */ void getSession$annotations() {
    }

    public final String toString() {
        return "{reason=" + this.a + ", session=" + this.b + '}';
    }
}
