package com.spotify.connectivity.esperanto.proto;

import com.spotify.esperanto.esperanto.MethodDescriptor;
import com.spotify.esperanto.esperanto.ServiceDescriptor;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class EsConnectivityPolicyEsperantoDescriptor {
    public static final Companion Companion;

    public static final class Companion extends ServiceDescriptor {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // com.spotify.esperanto.esperanto.ServiceDescriptor
        public String getServiceID() {
            return "spotify.connectivity.esperanto.proto.ConnectivityPolicy";
        }

        @Override // com.spotify.esperanto.esperanto.ServiceDescriptor
        public String getServiceName() {
            return "ConnectivityPolicy";
        }

        private Companion() {
        }
    }

    static {
        Companion companion = new Companion(null);
        Companion = companion;
        companion.addMethod("subState", new MethodDescriptor("com.spotify.connectivity.esperanto.proto.SubStateRequest", "com.spotify.connectivity.esperanto.proto.GetStateResponse", "subState"));
        companion.addMethod("setRules", new MethodDescriptor("com.spotify.connectivity.esperanto.proto.PutRulesRequest", "com.spotify.connectivity.esperanto.proto.PutRulesResponse", "setRules"));
        companion.addMethod("setForceOffline", new MethodDescriptor("com.spotify.connectivity.esperanto.proto.ForceOfflineRequest", "com.spotify.connectivity.esperanto.proto.ForceOfflineResponse", "setForceOffline"));
    }

    public static String getServiceID() {
        return Companion.getServiceID();
    }

    public static String getServiceName() {
        return Companion.getServiceName();
    }
}
