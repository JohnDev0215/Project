package com.android.launcher3.widget;

import android.provider.SettingsStringUtil;
import com.android.launcher3.model.PackageItemInfo;
import com.android.launcher3.model.WidgetItem;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetListRowEntry {
    public final PackageItemInfo pkgItem;
    public String titleSectionName;
    public final ArrayList<WidgetItem> widgets;

    public WidgetListRowEntry(PackageItemInfo pkgItem, ArrayList<WidgetItem> items) {
        this.pkgItem = pkgItem;
        this.widgets = items;
    }

    public String toString() {
        return this.pkgItem.packageName + SettingsStringUtil.DELIMITER + this.widgets.size();
    }
}
