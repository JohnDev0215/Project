package com.spotify.connectivity;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class ReconnectPolicy {
    public static final int NOT_AUTOMATIC = 0;
    public static final Companion Companion = new Companion(null);
    public static final int AUTOMATIC_ON_CHANGE = 1;
    public static final int AUTOMATIC_ON_IMPROVEMENT = 2;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
