package com.byd.systemsettings.system.model;

import android.content.Intent;
import com.byd.mvp.model.BydData;

/* JADX INFO: loaded from: classes2.dex */
public class BtPairCodeParams extends BydData {
    public Intent intent;
    public String title;

    public BtPairCodeParams(String str, Intent intent) {
        this.title = str;
        this.intent = intent;
    }
}
