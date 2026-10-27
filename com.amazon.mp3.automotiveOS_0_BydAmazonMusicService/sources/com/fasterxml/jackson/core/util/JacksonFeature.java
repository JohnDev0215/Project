package com.fasterxml.jackson.core.util;

/* JADX INFO: loaded from: classes6.dex */
public interface JacksonFeature {
    boolean enabledByDefault();

    boolean enabledIn(int i);

    int getMask();
}
