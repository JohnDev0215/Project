package com.spotify.connectivity.product_state.esperanto.proto;

import java.util.Map;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface PutValuesRequestOrBuilder extends yj1 {
    boolean containsPairs(String str);

    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    @Deprecated
    Map<String, String> getPairs();

    int getPairsCount();

    Map<String, String> getPairsMap();

    String getPairsOrDefault(String str, String str2);

    String getPairsOrThrow(String str);

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
