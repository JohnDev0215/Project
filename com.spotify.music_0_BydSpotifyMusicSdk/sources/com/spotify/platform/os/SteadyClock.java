package com.spotify.platform.os;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class SteadyClock {
    public static final Companion Companion = new Companion(null);
    private static final long timeSinceEpochMs = 0;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getTimeSinceEpochMs$annotations() {
        }

        public final long getTimeSinceEpochMs() {
            return SteadyClock.getTimeSinceEpochMs();
        }
    }

    public static final native long getTimeSinceEpochMs();
}
