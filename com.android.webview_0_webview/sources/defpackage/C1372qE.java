package defpackage;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;

/* JADX INFO: renamed from: qE, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1372qE {
    public final InterfaceC1317pE a;
    public final IntentFilter b;
    public final BroadcastReceiver c;
    public final boolean d;
    public C1262oE e;
    public boolean f;

    public C1372qE(InterfaceC1317pE interfaceC1317pE) {
        boolean zEquals = Build.MODEL.equals("Galaxy Nexus");
        C1262oE c1262oE = new C1262oE((BatteryManager) Is.a.getSystemService("batterymanager"));
        this.b = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        this.c = new C1207nE(this);
        this.a = interfaceC1317pE;
        this.d = zEquals;
        this.e = c1262oE;
    }
}
