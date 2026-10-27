package i1;

import android.os.SystemProperties;

/* JADX INFO: compiled from: UiUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class g {
    public static int a() {
        return Integer.valueOf(SystemProperties.get("persist.vendor.byd.cfg.huc.steering.wheel.position", "0")).intValue();
    }
}
