package com.spotify.ubi.model;

import java.io.Serializable;
import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class PageInstanceId implements Serializable {
    private static final long serialVersionUID = -1694494212112626392L;
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

    public final String a() {
        return this.value;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PageInstanceId) && v03.b(this.value, ((PageInstanceId) obj).value);
    }

    public final int hashCode() {
        return this.value.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("PageInstanceId(value="), this.value, ')');
    }
}
