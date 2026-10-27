package com.spotify.connectivity.auth.esperanto.proto;

import com.spotify.esperanto.esperanto.MethodDescriptor;
import com.spotify.esperanto.esperanto.ServiceDescriptor;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class EsSessionEsperantoDescriptor {
    public static final Companion Companion;

    public static final class Companion extends ServiceDescriptor {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // com.spotify.esperanto.esperanto.ServiceDescriptor
        public String getServiceID() {
            return "spotify.connectivity.auth.esperanto.proto.Session";
        }

        @Override // com.spotify.esperanto.esperanto.ServiceDescriptor
        public String getServiceName() {
            return "Session";
        }

        private Companion() {
        }
    }

    static {
        Companion companion = new Companion(null);
        Companion = companion;
        companion.addMethod("willLogoutAndForgetCurrentUser", new MethodDescriptor("com.google.protobuf.Empty", "com.google.protobuf.Empty", "willLogoutAndForgetCurrentUser"));
        companion.addMethod("apPermanentError", new MethodDescriptor("com.google.protobuf.Empty", "com.spotify.connectivity.auth.esperanto.proto.EsSession$APPermanentErrorResult", "apPermanentError"));
        companion.addMethod("sendEndSongs", new MethodDescriptor("com.google.protobuf.Empty", "com.spotify.connectivity.auth.esperanto.proto.EsSession$SendEndSongsResult", "sendEndSongs"));
        companion.addMethod("writeProductStateToLegacyStorage", new MethodDescriptor("com.spotify.connectivity.auth.esperanto.proto.EsSession$ProductStateMap", "com.google.protobuf.Empty", "writeProductStateToLegacyStorage"));
    }

    public static String getServiceID() {
        return Companion.getServiceID();
    }

    public static String getServiceName() {
        return Companion.getServiceName();
    }
}
