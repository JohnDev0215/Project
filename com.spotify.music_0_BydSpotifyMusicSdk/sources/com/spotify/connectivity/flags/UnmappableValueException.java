package com.spotify.connectivity.flags;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class UnmappableValueException extends Exception {
    public static final Companion Companion = new Companion(null);
    private static final long serialVersionUID = 4006225106797912781L;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnmappableValueException(Flag<?> flag, String str, Throwable th) {
        super(flag.getIdentifier() + " of type " + flag.getType().getName() + " cannot map value \"" + str + '\"', th);
        v03.j(flag, "flag");
        v03.j(str, "value");
    }
}
