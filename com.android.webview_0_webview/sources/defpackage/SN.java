package defpackage;

import android.content.pm.FeatureInfo;
import android.os.Build;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class SN {
    public static Boolean a;

    public static int a(int i) {
        if (a == null) {
            Boolean bool = Boolean.FALSE;
            if (a == null) {
                if ("SAMSUNG".equalsIgnoreCase(Build.MANUFACTURER)) {
                    FeatureInfo[] systemAvailableFeatures = Is.a.getPackageManager().getSystemAvailableFeatures();
                    int length = systemAvailableFeatures.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            a = bool;
                            break;
                        }
                        if ("com.sec.feature.spen_usp".equalsIgnoreCase(systemAvailableFeatures[i2].name)) {
                            a = Boolean.TRUE;
                            break;
                        }
                        i2++;
                    }
                } else {
                    a = bool;
                }
            }
        }
        if (!a.booleanValue()) {
            return i;
        }
        switch (i) {
        }
        return i;
    }
}
