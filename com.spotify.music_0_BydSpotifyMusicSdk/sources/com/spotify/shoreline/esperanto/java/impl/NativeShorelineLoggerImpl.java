package com.spotify.shoreline.esperanto.java.impl;

import com.spotify.esperanto.esperanto.Transport;
import com.spotify.shoreline.esperanto.java.api.NativeShorelineLogger;
import com.spotify.shoreline.esperanto.java.api.ShorelineLoggerApi;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class NativeShorelineLoggerImpl implements NativeShorelineLogger, ShorelineLoggerApi {
    public static final Companion Companion = new Companion(null);
    private long nThis;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final NativeShorelineLoggerImpl createInternal(Transport transport) {
            return NativeShorelineLoggerImpl.createInternal(transport);
        }

        public final NativeShorelineLoggerImpl create(Transport transport) {
            v03.j(transport, "shorelineLoggerTransport");
            return createInternal(transport);
        }
    }

    private NativeShorelineLoggerImpl() {
    }

    public static final NativeShorelineLoggerImpl create(Transport transport) {
        return Companion.create(transport);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final native NativeShorelineLoggerImpl createInternal(Transport transport);

    @Override // com.spotify.shoreline.esperanto.java.api.NativeShorelineLogger
    public native void destroy();

    @Override // com.spotify.shoreline.esperanto.java.api.NativeShorelineLogger
    public long getNThis() {
        return this.nThis;
    }
}
