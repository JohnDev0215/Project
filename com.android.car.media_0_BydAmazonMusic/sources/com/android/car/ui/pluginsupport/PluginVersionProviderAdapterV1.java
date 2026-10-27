package com.android.car.ui.pluginsupport;

import android.content.Context;
import spi.c9;
import spi.d9;

/* JADX INFO: loaded from: classes2.dex */
final class PluginVersionProviderAdapterV1 implements c9 {
    private d9 mOemProvider;

    public PluginVersionProviderAdapterV1(d9 d9Var) {
        this.mOemProvider = d9Var;
    }

    @Override // spi.c9
    public Object getPluginFactory(int i, Context context, String str) {
        return this.mOemProvider.a();
    }
}
