package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.spotify.esperanto.esperanto.MethodDescriptor;
import com.spotify.esperanto.esperanto.ServiceDescriptor;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class EsLogin5EsperantoDescriptor {
    public static final Companion Companion;

    public static final class Companion extends ServiceDescriptor {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // com.spotify.esperanto.esperanto.ServiceDescriptor
        public String getServiceID() {
            return "spotify.authentication.login5.impl.proto.Login5";
        }

        @Override // com.spotify.esperanto.esperanto.ServiceDescriptor
        public String getServiceName() {
            return "Login5";
        }

        private Companion() {
        }
    }

    static {
        Companion companion = new Companion(null);
        Companion = companion;
        companion.addMethod("authenticate", new MethodDescriptor("com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest$AuthenticateRequest", "com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult$AuthenticateResult", "authenticate"));
        companion.addMethod("codeRequiredResend", new MethodDescriptor("com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired$CodeRequiredResendRequest", "com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired$CodeRequiredResendResult", "codeRequiredResend"));
        companion.addMethod("codeRequiredProceed", new MethodDescriptor("com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired$CodeRequiredProceedRequest", "com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired$CodeRequiredProceedResult", "codeRequiredProceed"));
        companion.addMethod("interactionRequiredProceed", new MethodDescriptor("com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired$InteractionRequiredProceedRequest", "com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired$InteractionRequiredProceedResult", "interactionRequiredProceed"));
    }

    public static String getServiceID() {
        return Companion.getServiceID();
    }

    public static String getServiceName() {
        return Companion.getServiceName();
    }
}
