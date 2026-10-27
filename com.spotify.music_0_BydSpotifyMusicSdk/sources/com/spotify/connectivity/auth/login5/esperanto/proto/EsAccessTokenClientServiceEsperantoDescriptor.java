package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.spotify.esperanto.esperanto.MethodDescriptor;
import com.spotify.esperanto.esperanto.ServiceDescriptor;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class EsAccessTokenClientServiceEsperantoDescriptor {
    public static final Companion Companion;

    public static final class Companion extends ServiceDescriptor {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // com.spotify.esperanto.esperanto.ServiceDescriptor
        public String getServiceID() {
            return "spotify.authentication.login5.impl.proto.AccessTokenClient";
        }

        @Override // com.spotify.esperanto.esperanto.ServiceDescriptor
        public String getServiceName() {
            return "AccessTokenClient";
        }

        private Companion() {
        }
    }

    static {
        Companion companion = new Companion(null);
        Companion = companion;
        companion.addMethod("getToken", new MethodDescriptor("com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient$GetTokenRequest", "com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient$AccessTokenResponse", "getToken"));
    }

    public static String getServiceID() {
        return Companion.getServiceID();
    }

    public static String getServiceName() {
        return Companion.getServiceName();
    }
}
