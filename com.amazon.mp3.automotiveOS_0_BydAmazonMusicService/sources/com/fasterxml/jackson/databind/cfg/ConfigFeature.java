package com.fasterxml.jackson.databind.cfg;

/* JADX INFO: loaded from: classes6.dex */
public interface ConfigFeature {
    boolean enabledByDefault();

    boolean enabledIn(int i);

    int getMask();
}
