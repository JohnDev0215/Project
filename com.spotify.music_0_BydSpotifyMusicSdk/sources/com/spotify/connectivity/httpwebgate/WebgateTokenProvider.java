package com.spotify.connectivity.httpwebgate;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public interface WebgateTokenProvider {

    public static final class WebgateTokenException extends Exception {
        public static final Companion Companion = new Companion(null);
        private static final long serialVersionUID = 593539459345L;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public WebgateTokenException(String str) {
            super(str);
        }

        public WebgateTokenException() {
        }
    }

    void onSessionStart();

    void onStart();

    void onStop();

    String requestAccessToken(int i);

    String requestAccessToken(int i, boolean z);

    void reset();

    void resetAndStopServingTokens();
}
