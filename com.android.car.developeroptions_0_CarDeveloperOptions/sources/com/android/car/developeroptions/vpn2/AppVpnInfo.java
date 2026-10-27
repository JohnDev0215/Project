package com.android.car.developeroptions.vpn2;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
class AppVpnInfo implements Comparable {
    public final String packageName;
    public final int userId;

    public AppVpnInfo(int i, String str) {
        this.userId = i;
        Objects.requireNonNull(str);
        this.packageName = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        AppVpnInfo appVpnInfo = (AppVpnInfo) obj;
        int iCompareTo = this.packageName.compareTo(appVpnInfo.packageName);
        return iCompareTo == 0 ? appVpnInfo.userId - this.userId : iCompareTo;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AppVpnInfo)) {
            return false;
        }
        AppVpnInfo appVpnInfo = (AppVpnInfo) obj;
        return this.userId == appVpnInfo.userId && Objects.equals(this.packageName, appVpnInfo.packageName);
    }

    public int hashCode() {
        return Objects.hash(this.packageName, Integer.valueOf(this.userId));
    }
}
