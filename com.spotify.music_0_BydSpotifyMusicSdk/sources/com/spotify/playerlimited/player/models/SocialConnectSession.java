package com.spotify.playerlimited.player.models;

import p.c71;
import p.hf2;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class SocialConnectSession {
    public boolean a;
    public String b;
    public String c;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        new Companion(0);
        new SocialConnectSession();
    }

    @z61(name = "join_session_uri")
    public static /* synthetic */ void getJoinSessionUri$annotations() {
    }

    @z61(name = "session_id")
    public static /* synthetic */ void getSessionId$annotations() {
    }

    @z61(name = "is_session_owner")
    public static /* synthetic */ void isHost$annotations() {
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{isHost=");
        sb.append(this.a);
        sb.append(", joinSessionUri=");
        sb.append(this.b);
        sb.append(", sessionId=");
        return hf2.d(sb, this.c, '}');
    }
}
