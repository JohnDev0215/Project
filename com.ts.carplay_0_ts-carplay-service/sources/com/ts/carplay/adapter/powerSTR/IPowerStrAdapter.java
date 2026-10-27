package com.ts.carplay.adapter.powerSTR;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface IPowerStrAdapter {
    public static final int POWER_STR_SHUTDOWN_PREPARE = 19;
    public static final int POWER_STR_SUSPEND_EXIT = 16;

    void init(Context context);

    void registerListener(IPowerStrAdapterListener iPowerStrAdapterListener);

    void unregisterListener(IPowerStrAdapterListener iPowerStrAdapterListener);

    public interface IPowerStrAdapterListener {
        default void onPowerStrChanged(int status) {
        }
    }
}
