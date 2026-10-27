package defpackage;

import android.os.BatteryManager;

/* JADX INFO: renamed from: oE, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1262oE {
    public final BatteryManager a;

    public C1262oE(BatteryManager batteryManager) {
        this.a = batteryManager;
    }

    public int a(int i) {
        return this.a.getIntProperty(i);
    }
}
