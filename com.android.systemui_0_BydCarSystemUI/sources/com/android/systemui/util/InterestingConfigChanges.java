package com.android.systemui.util;

import android.content.res.Configuration;
import android.content.res.Resources;

/* JADX INFO: loaded from: classes.dex */
public class InterestingConfigChanges {
    private final int mFlags;
    private final Configuration mLastConfiguration;
    private int mLastDensity;

    public InterestingConfigChanges() {
        this(-2147482876);
    }

    public InterestingConfigChanges(int flags) {
        this.mLastConfiguration = new Configuration();
        this.mFlags = flags;
    }

    public boolean applyNewConfig(Resources res) {
        Configuration configuration = this.mLastConfiguration;
        int iUpdateFrom = configuration.updateFrom(Configuration.generateDelta(configuration, res.getConfiguration()));
        if (!(this.mLastDensity != res.getDisplayMetrics().densityDpi) && (iUpdateFrom & this.mFlags) == 0) {
            return false;
        }
        this.mLastDensity = res.getDisplayMetrics().densityDpi;
        return true;
    }
}
