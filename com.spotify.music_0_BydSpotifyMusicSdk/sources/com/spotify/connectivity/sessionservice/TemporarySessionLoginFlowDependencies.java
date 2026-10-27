package com.spotify.connectivity.sessionservice;

import com.spotify.connectivity.sessionapi.SharedNativeSession;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class TemporarySessionLoginFlowDependencies {

    public static final class LegacyFlow extends TemporarySessionLoginFlowDependencies {
        private final SharedNativeSession sharedNativeSession;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LegacyFlow(SharedNativeSession sharedNativeSession) {
            super(null);
            v03.j(sharedNativeSession, "sharedNativeSession");
            this.sharedNativeSession = sharedNativeSession;
        }

        public static /* synthetic */ LegacyFlow copy$default(LegacyFlow legacyFlow, SharedNativeSession sharedNativeSession, int i, Object obj) {
            if ((i & 1) != 0) {
                sharedNativeSession = legacyFlow.sharedNativeSession;
            }
            return legacyFlow.copy(sharedNativeSession);
        }

        public final SharedNativeSession component1() {
            return this.sharedNativeSession;
        }

        public final LegacyFlow copy(SharedNativeSession sharedNativeSession) {
            v03.j(sharedNativeSession, "sharedNativeSession");
            return new LegacyFlow(sharedNativeSession);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof LegacyFlow) && v03.b(this.sharedNativeSession, ((LegacyFlow) obj).sharedNativeSession);
        }

        public final SharedNativeSession getSharedNativeSession() {
            return this.sharedNativeSession;
        }

        public int hashCode() {
            return this.sharedNativeSession.hashCode();
        }

        public String toString() {
            return "LegacyFlow(sharedNativeSession=" + this.sharedNativeSession + ')';
        }
    }

    public static final class NewFlow extends TemporarySessionLoginFlowDependencies {
        private final Map<String, String> productState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NewFlow(Map<String, String> map) {
            super(null);
            v03.j(map, "productState");
            this.productState = map;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ NewFlow copy$default(NewFlow newFlow, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                map = newFlow.productState;
            }
            return newFlow.copy(map);
        }

        public final Map<String, String> component1() {
            return this.productState;
        }

        public final NewFlow copy(Map<String, String> map) {
            v03.j(map, "productState");
            return new NewFlow(map);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof NewFlow) && v03.b(this.productState, ((NewFlow) obj).productState);
        }

        public final Map<String, String> getProductState() {
            return this.productState;
        }

        public int hashCode() {
            return this.productState.hashCode();
        }

        public String toString() {
            return "NewFlow(productState=" + this.productState + ')';
        }
    }

    private TemporarySessionLoginFlowDependencies() {
    }

    public /* synthetic */ TemporarySessionLoginFlowDependencies(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
