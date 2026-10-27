package com.spotify.connectivity.product_state.esperanto.proto;

import com.google.protobuf.ByteString;
import java.util.List;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface DelOverridesValuesRequestOrBuilder extends yj1 {
    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    String getKeys(int i);

    ByteString getKeysBytes(int i);

    int getKeysCount();

    List<String> getKeysList();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
