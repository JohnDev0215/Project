package org.chromium.chrome.browser.signin.services;

import defpackage.a8;
import defpackage.oj2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: loaded from: classes2.dex */
public class FREMobileIdentityConsistencyFieldTrial {
    public static final Object a = new Object();

    public static String a(int i, int i2) {
        int I = a8.I(i);
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += a8.I(i4) + Integer.MIN_VALUE < Integer.MIN_VALUE + I ? 1 : 0;
        }
        double d = (((double) i3) / ((double) i2)) * 100.0d;
        double d2 = 100;
        if (d < d2) {
            return "Enabled10";
        }
        double d3 = d - d2;
        double d4 = 0;
        return d3 < d4 ? "Disabled10" : d3 - d4 < d4 ? "OldFreWithUmaDialog10" : "Default";
    }

    @CalledByNative
    public static String getFirstRunTrialGroup() {
        String strG;
        synchronized (a) {
            strG = SharedPreferencesManager.getInstance().g("Chrome.FirstRun.FieldTrialEnabled", "Default");
        }
        return strG;
    }

    @CalledByNative
    public static int getFirstRunTrialVariationId(int i, int i2) {
        boolean zEquals = getFirstRunTrialGroup().equals(a(i, i2));
        oj2.O("Signin.AndroidIsFREStudyGroupConsistent", zEquals);
        if (!zEquals) {
            return 0;
        }
        String firstRunTrialGroup = getFirstRunTrialGroup();
        firstRunTrialGroup.getClass();
        switch (firstRunTrialGroup) {
        }
        return 0;
    }
}
