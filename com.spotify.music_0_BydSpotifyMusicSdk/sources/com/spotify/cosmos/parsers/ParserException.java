package com.spotify.cosmos.parsers;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class ParserException extends Exception {
    public static final Companion Companion = new Companion(null);
    private static final long serialVersionUID = -6133574440765179179L;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ParserException(String str) {
        super(str);
    }

    public ParserException(Throwable th) {
        super(th);
    }

    public ParserException(String str, Throwable th) {
        super(str, th);
    }
}
