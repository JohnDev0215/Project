package com.spotify.automotive.socialconnect.endpoint;

import android.text.TextUtils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class SocialConnectSession {
    public static final Companion Companion = new Companion(null);
    public static final SocialConnectSession EMPTY = new SocialConnectSession();
    public boolean isSessionOwner;
    public String joinSessionUri;
    public String sessionId;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @z61(name = "join_session_uri")
    public static /* synthetic */ void getJoinSessionUri$annotations() {
    }

    @z61(name = "session_id")
    public static /* synthetic */ void getSessionId$annotations() {
    }

    @z61(name = "is_session_owner")
    public static /* synthetic */ void isSessionOwner$annotations() {
    }

    public final boolean isValid() {
        return (TextUtils.isEmpty(this.sessionId) || TextUtils.isEmpty(this.joinSessionUri)) ? false : true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{isSessionOwner=");
        sb.append(this.isSessionOwner);
        sb.append(", joinSessionUri=");
        sb.append(this.joinSessionUri);
        sb.append(", sessionId=");
        return hf2.d(sb, this.sessionId, '}');
    }
}
