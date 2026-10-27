package com.spotify.cosmos.routercallback;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class ResolverException extends Exception {
    public static final Companion Companion = new Companion(null);
    private static final long serialVersionUID = -7958249303774126846L;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ResolverException(String str) {
        super(str);
    }

    public ResolverException(Throwable th) {
        super(th);
    }
}
