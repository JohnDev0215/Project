package com.ts.dm.lib.devicemanager.counter;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface ILastProfileTimeCounter {

    public interface OnLastProfileTimeListenr {
        void onLastProfileTimeMax();
    }

    void addOnLastProfileTimeListener(OnLastProfileTimeListenr onLastProfileTimeListenr);

    int generateLastProfileTime();

    void removeOnLastProfileTimeListener(OnLastProfileTimeListenr onLastProfileTimeListenr);

    void updateLastProfileMax(int i);

    static ILastProfileTimeCounter newInstance(Context context) {
        return new LastProfileTimeCounterImpl(context);
    }
}
