package com.android.launcher3.model;

import android.content.pm.PackageManager;
import android.os.Process;
import android.os.UserHandle;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.LauncherAppWidgetProviderInfo;
import com.android.launcher3.Utilities;
import com.android.launcher3.compat.ShortcutConfigActivityInfo;
import com.android.launcher3.icons.IconCache;
import com.android.launcher3.util.ComponentKey;
import java.text.Collator;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetItem extends ComponentKey implements Comparable<WidgetItem> {
    private static Collator sCollator;
    private static UserHandle sMyUserHandle;
    public final ShortcutConfigActivityInfo activityInfo;
    public final String label;
    public final int spanX;
    public final int spanY;
    public final LauncherAppWidgetProviderInfo widgetInfo;

    public WidgetItem(LauncherAppWidgetProviderInfo info, InvariantDeviceProfile idp, IconCache iconCache) {
        super(info.provider, info.getProfile());
        this.label = iconCache.getTitleNoCache(info);
        this.widgetInfo = info;
        this.activityInfo = null;
        this.spanX = Math.min(info.spanX, idp.numColumns);
        this.spanY = Math.min(info.spanY, idp.numRows);
    }

    public WidgetItem(ShortcutConfigActivityInfo info, IconCache iconCache, PackageManager pm) {
        super(info.getComponent(), info.getUser());
        this.label = info.isPersistable() ? iconCache.getTitleNoCache(info) : Utilities.trim(info.getLabel(pm));
        this.widgetInfo = null;
        this.activityInfo = info;
        this.spanY = 1;
        this.spanX = 1;
    }

    @Override // java.lang.Comparable
    public int compareTo(WidgetItem another) {
        if (sMyUserHandle == null) {
            sMyUserHandle = Process.myUserHandle();
            sCollator = Collator.getInstance();
        }
        boolean z = !sMyUserHandle.equals(this.user);
        if ((!sMyUserHandle.equals(another.user)) ^ z) {
            return z ? 1 : -1;
        }
        int iCompare = sCollator.compare(this.label, another.label);
        if (iCompare != 0) {
            return iCompare;
        }
        int i = this.spanX;
        int i2 = this.spanY;
        int i3 = i * i2;
        int i4 = another.spanX;
        int i5 = another.spanY;
        int i6 = i4 * i5;
        if (i3 == i6) {
            return Integer.compare(i2, i5);
        }
        return Integer.compare(i3, i6);
    }
}
