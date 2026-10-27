package com.android.launcher3;

import android.content.ComponentName;

/* JADX INFO: loaded from: classes4.dex */
public class PendingAddItemInfo extends ItemInfo {
    public ComponentName componentName;

    @Override // com.android.launcher3.ItemInfo
    protected String dumpProperties() {
        return super.dumpProperties() + " componentName=" + this.componentName;
    }
}
