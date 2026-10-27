package com.spotify.cosmos.rxrouter;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosException extends Exception {
    public static final Companion Companion = new Companion(null);
    private static final long serialVersionUID = -9094328986537932508L;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CosmosException(String str) {
        super(str);
    }
}
