package com.amazon.atv.playbackauthority.service.model;

import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DrmPolicy {

    @NotThreadSafe
    public static abstract class Builder {
    }

    protected DrmPolicy(Builder builder) {
    }

    public boolean equals(Object obj) {
        return obj == this || (obj instanceof DrmPolicy);
    }

    public int hashCode() {
        return getClass().hashCode();
    }
}
