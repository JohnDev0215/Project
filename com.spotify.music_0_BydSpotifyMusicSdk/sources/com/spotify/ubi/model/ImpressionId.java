package com.spotify.ubi.model;

import java.io.Serializable;
import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ImpressionId implements Serializable {
    private static final long serialVersionUID = -8720805093282251390L;
    private final String value;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        new Companion(0);
    }

    public ImpressionId(String str) {
        v03.j(str, "value");
        this.value = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImpressionId) && v03.b(this.value, ((ImpressionId) obj).value);
    }

    public final int hashCode() {
        return this.value.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("ImpressionId(value="), this.value, ')');
    }
}
