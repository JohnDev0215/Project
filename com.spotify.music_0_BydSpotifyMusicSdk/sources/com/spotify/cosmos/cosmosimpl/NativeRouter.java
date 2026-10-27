package com.spotify.cosmos.cosmosimpl;

import com.spotify.cosmos.cosmos.Lifetime;
import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.ResolveCallback;
import com.spotify.cosmos.cosmos.Router;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class NativeRouter implements Router {
    public static final Companion Companion = new Companion(null);
    private long nThis;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void initializeRouter(NativeRouter nativeRouter) {
            NativeRouter.initializeRouter(nativeRouter);
        }

        public final NativeRouter create() {
            return NativeRouter.create();
        }
    }

    public NativeRouter() {
        Companion.initializeRouter(this);
    }

    public static final native NativeRouter create();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initializeRouter(NativeRouter nativeRouter);

    public final native void deinitializeScheduling();

    @Override // com.spotify.cosmos.cosmos.Router
    public native void destroy();

    public final native void initializeScheduling(Scheduler scheduler);

    @Override // com.spotify.cosmos.cosmos.Router
    public native Lifetime resolve(Request request, ResolveCallback resolveCallback);
}
