package com.spotify.logging.logging;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class Logging {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void deinitLogging() {
            Logging.deinitLogging();
        }

        public final void initLogging(boolean z) {
            Logging.initLogging(z);
        }
    }

    public static final native void deinitLogging();

    public static final native void initLogging(boolean z);
}
