package com.byd.systemsettings.wifi2.callback;

import com.byd.bt.model.EnableState;

/* JADX INFO: loaded from: classes.dex */
public interface IEnableStateCallback extends WifiCallback {
    void onEnableStateChanged(EnableState enableState);
}
