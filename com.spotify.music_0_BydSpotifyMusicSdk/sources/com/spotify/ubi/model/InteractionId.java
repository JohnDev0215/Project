package com.spotify.ubi.model;

import java.io.Serializable;
import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionId implements Serializable {
    private static final long serialVersionUID = -6412660095918414747L;
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

    public InteractionId(String str) {
        v03.j(str, "value");
        this.value = str;
    }

    public final String a() {
        return this.value;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof InteractionId) && v03.b(this.value, ((InteractionId) obj).value);
    }

    public final int hashCode() {
        return this.value.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("InteractionId(value="), this.value, ')');
    }
}
