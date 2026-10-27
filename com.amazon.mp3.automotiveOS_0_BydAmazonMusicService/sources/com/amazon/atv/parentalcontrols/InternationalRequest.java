package com.amazon.atv.parentalcontrols;

import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class InternationalRequest {

    @NotThreadSafe
    public static abstract class Builder {
    }

    protected InternationalRequest(Builder builder) {
    }

    public boolean equals(Object obj) {
        return obj == this || (obj instanceof InternationalRequest);
    }

    public int hashCode() {
        return getClass().hashCode();
    }
}
