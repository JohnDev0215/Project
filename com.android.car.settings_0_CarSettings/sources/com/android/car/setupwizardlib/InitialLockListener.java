package com.android.car.setupwizardlib;

import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public interface InitialLockListener {
    void onConnectionAttemptFinished(boolean z);

    void onDisconnect();

    void onGetLockConfigs(Map<Integer, LockConfig> map);

    void onLockValidated(int i);

    void onSetLockFinished(int i);
}
