package com.android.launcher3.model;

import com.android.launcher3.ItemInfoWithIcon;

/* JADX INFO: loaded from: classes4.dex */
public class PackageItemInfo extends ItemInfoWithIcon {
    public String packageName;

    public PackageItemInfo(String packageName) {
        this.packageName = packageName;
    }

    @Override // com.android.launcher3.ItemInfo
    protected String dumpProperties() {
        return super.dumpProperties() + " packageName=" + this.packageName;
    }
}
