package com.spotify.connectivity.productstate;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConvertProductStateValueException extends Exception {
    public static final Companion Companion = new Companion(null);
    private static final long serialVersionUID = -4803573546423612268L;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConvertProductStateValueException(Class<?> cls, String str) {
        super("Value " + str + " couldn't be converted to target type " + cls.getCanonicalName());
        v03.j(cls, "targetType");
        v03.j(str, "value");
    }
}
