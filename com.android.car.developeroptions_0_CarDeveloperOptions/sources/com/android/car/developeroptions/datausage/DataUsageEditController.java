package com.android.car.developeroptions.datausage;

import android.net.NetworkTemplate;
import com.android.settingslib.NetworkPolicyEditor;

/* JADX INFO: loaded from: classes.dex */
public interface DataUsageEditController {
    NetworkPolicyEditor getNetworkPolicyEditor();

    NetworkTemplate getNetworkTemplate();

    void updateDataUsage();
}
