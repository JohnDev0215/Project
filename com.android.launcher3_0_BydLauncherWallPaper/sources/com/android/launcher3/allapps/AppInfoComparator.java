package com.android.launcher3.allapps;

import android.content.Context;
import android.os.Process;
import android.os.UserHandle;
import com.android.launcher3.AppInfo;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.util.LabelComparator;
import java.util.Comparator;

/* JADX INFO: loaded from: classes4.dex */
public class AppInfoComparator implements Comparator<AppInfo> {
    private final UserManagerCompat mUserManager;
    private final UserHandle mMyUser = Process.myUserHandle();
    private final LabelComparator mLabelComparator = new LabelComparator();

    public AppInfoComparator(Context context) {
        this.mUserManager = UserManagerCompat.getInstance(context);
    }

    @Override // java.util.Comparator
    public int compare(AppInfo a, AppInfo b) {
        int iCompare = this.mLabelComparator.compare(a.title.toString(), b.title.toString());
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompareTo = a.componentName.compareTo(b.componentName);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        if (this.mMyUser.equals(a.user)) {
            return -1;
        }
        return Long.valueOf(this.mUserManager.getSerialNumberForUser(a.user)).compareTo(Long.valueOf(this.mUserManager.getSerialNumberForUser(b.user)));
    }
}
