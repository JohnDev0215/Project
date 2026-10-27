package com.ts.carplay.adapter.power;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface IPowerAdapter {
    public static final int POWER_ACCOFF = 4;
    public static final int POWER_ACCON = 10;

    void init(Context context);

    void registerListener(IPowerAdapterListener iPowerAdapterListener);

    void unregisterListener(IPowerAdapterListener iPowerAdapterListener);

    public interface IPowerAdapterListener {
        default void onAccStatusChanged(int status) {
        }
    }
}
