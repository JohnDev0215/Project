package com.android.launcher3.dot;

import android.content.Context;
import android.view.ViewDebug;
import com.android.launcher3.Utilities;

/* JADX INFO: loaded from: classes4.dex */
public class FolderDotInfo extends DotInfo {
    private static final int MIN_COUNT = 0;
    private int mNumNotifications;

    public void addDotInfo(DotInfo dotToAdd) {
        if (dotToAdd == null) {
            return;
        }
        int size = this.mNumNotifications + dotToAdd.getNotificationKeys().size();
        this.mNumNotifications = size;
        this.mNumNotifications = Utilities.boundToRange(size, 0, 999);
    }

    public void subtractDotInfo(DotInfo dotToSubtract) {
        if (dotToSubtract == null) {
            return;
        }
        int size = this.mNumNotifications - dotToSubtract.getNotificationKeys().size();
        this.mNumNotifications = size;
        this.mNumNotifications = Utilities.boundToRange(size, 0, 999);
    }

    @Override // com.android.launcher3.dot.DotInfo
    public int getNotificationCount() {
        return this.mNumNotifications;
    }

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    public boolean hasDot() {
        return this.mNumNotifications > 0;
    }
}
