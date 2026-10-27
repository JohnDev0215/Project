package com.spotify.jvm.jni;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class NativeCrashDiagnostics {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void crash() {
            NativeCrashDiagnostics.crash();
        }
    }

    public static final native void crash();
}
