package com.spotify.connectivity.product_state.esperanto.proto;

import com.spotify.esperanto.esperanto.MethodDescriptor;
import com.spotify.esperanto.esperanto.ServiceDescriptor;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateEsperantoDescriptor {
    public static final Companion Companion;

    public static final class Companion extends ServiceDescriptor {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // com.spotify.esperanto.esperanto.ServiceDescriptor
        public String getServiceID() {
            return "spotify.product_state.esperanto.proto.ProductState";
        }

        @Override // com.spotify.esperanto.esperanto.ServiceDescriptor
        public String getServiceName() {
            return "ProductState";
        }

        private Companion() {
        }
    }

    static {
        Companion companion = new Companion(null);
        Companion = companion;
        companion.addMethod("SubValues", new MethodDescriptor("com.spotify.connectivity.product_state.esperanto.proto.SubValuesRequest", "com.spotify.connectivity.product_state.esperanto.proto.GetValuesResponse", "SubValues"));
        companion.addMethod("GetValues", new MethodDescriptor("com.spotify.connectivity.product_state.esperanto.proto.GetValuesRequest", "com.spotify.connectivity.product_state.esperanto.proto.GetValuesResponse", "GetValues"));
        companion.addMethod("PutValues", new MethodDescriptor("com.spotify.connectivity.product_state.esperanto.proto.PutValuesRequest", "com.spotify.connectivity.product_state.esperanto.proto.PutValuesResponse", "PutValues"));
        companion.addMethod("PutOverridesValues", new MethodDescriptor("com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequest", "com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesResponse", "PutOverridesValues"));
        companion.addMethod("DelOverridesValues", new MethodDescriptor("com.spotify.connectivity.product_state.esperanto.proto.DelOverridesValuesRequest", "com.spotify.connectivity.product_state.esperanto.proto.DelOverridesValuesResponse", "DelOverridesValues"));
    }

    public static String getServiceID() {
        return Companion.getServiceID();
    }

    public static String getServiceName() {
        return Companion.getServiceName();
    }
}
