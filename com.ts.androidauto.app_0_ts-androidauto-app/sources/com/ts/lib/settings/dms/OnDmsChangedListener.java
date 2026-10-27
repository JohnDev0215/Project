package com.ts.lib.settings.dms;

/* JADX INFO: loaded from: classes2.dex */
public interface OnDmsChangedListener {
    void onAttentionDetectChanged(int i);

    void onAttentionSwitchChanged(int i);

    void onBlockStatusChanged(int i);

    void onDmsModeChanged(int i);

    void onDmsSwitchChanged(int i);

    void onDriverNotResponse();

    void onFatigueDetectChanged(int i);

    void onFatigueSwitchChanged(int i);

    void onNotifyDmsServerState(int i);

    void onRestoreFatigueDetect();
}
