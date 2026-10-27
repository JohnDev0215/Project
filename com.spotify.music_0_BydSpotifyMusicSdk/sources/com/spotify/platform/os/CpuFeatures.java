package com.spotify.platform.os;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class CpuFeatures {
    public static final Companion Companion = new Companion(null);
    private static final int cpuFamily = 0;
    private static final long cpuFeatures = 0;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getCpuFamily$annotations() {
        }

        public static /* synthetic */ void getCpuFeatures$annotations() {
        }

        public final int getCpuFamily() {
            return CpuFeatures.getCpuFamily();
        }

        public final long getCpuFeatures() {
            return CpuFeatures.getCpuFeatures();
        }
    }

    public static final native int getCpuFamily();

    public static final native long getCpuFeatures();
}
