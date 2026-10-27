package com.amazon.atv.featureconfig;

import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Config {

    @NotThreadSafe
    public static class Builder {
    }

    protected Config(Builder builder) {
    }

    public boolean equals(Object obj) {
        return obj == this || (obj instanceof Config);
    }

    public int hashCode() {
        return getClass().hashCode();
    }
}
