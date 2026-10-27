package com.android.car.developeroptions.wifi.savedaccesspoints;

import android.icu.text.Collator;
import com.android.settingslib.wifi.AccessPoint;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class SavedNetworkComparator {
    public static final Comparator<AccessPoint> INSTANCE = new Comparator<AccessPoint>() { // from class: com.android.car.developeroptions.wifi.savedaccesspoints.SavedNetworkComparator.1
        final Collator mCollator = Collator.getInstance();

        private String nullToEmpty(String str) {
            return str == null ? "" : str;
        }

        @Override // java.util.Comparator
        public int compare(AccessPoint accessPoint, AccessPoint accessPoint2) {
            return this.mCollator.compare(nullToEmpty(accessPoint.getConfigName()), nullToEmpty(accessPoint2.getConfigName()));
        }
    };
}
