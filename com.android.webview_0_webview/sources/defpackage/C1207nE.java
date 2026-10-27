package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Objects;

/* JADX INFO: renamed from: nE, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1207nE extends BroadcastReceiver {
    public final /* synthetic */ C1372qE a;

    public C1207nE(C1372qE c1372qE) {
        this.a = c1372qE;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        C1372qE c1372qE = this.a;
        Objects.requireNonNull(c1372qE);
        if (!intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
            Ts.a("BatteryStatusManager", "Unexpected intent.", new Object[0]);
            return;
        }
        boolean booleanExtra = c1372qE.d ? true : intent.getBooleanExtra("present", false);
        int intExtra = intent.getIntExtra("plugged", -1);
        if (!booleanExtra || intExtra == -1) {
            ((C1040kE) c1372qE.a).a(new C1153mF(0));
            return;
        }
        double intExtra2 = ((double) intent.getIntExtra("level", -1)) / ((double) intent.getIntExtra("scale", -1));
        if (intExtra2 < 0.0d || intExtra2 > 1.0d) {
            intExtra2 = 1.0d;
        }
        boolean z = intExtra != 0;
        double d = (z && (intent.getIntExtra("status", -1) == 5)) ? 0.0d : Double.POSITIVE_INFINITY;
        C1153mF c1153mF = new C1153mF(0);
        c1153mF.b = z;
        c1153mF.c = d;
        c1153mF.d = Double.POSITIVE_INFINITY;
        c1153mF.e = intExtra2;
        C1262oE c1262oE = c1372qE.e;
        if (c1262oE != null) {
            double dA = ((double) c1262oE.a(4)) / 100.0d;
            double dA2 = c1372qE.e.a(1);
            double dA3 = c1372qE.e.a(3);
            if (c1153mF.b) {
                if (c1153mF.c == Double.POSITIVE_INFINITY && dA3 > 0.0d) {
                    c1153mF.c = Math.ceil((1.0d - dA) * (dA2 / dA3) * 3600.0d);
                }
            } else if (dA3 < 0.0d) {
                c1153mF.d = Math.floor((dA2 / (-dA3)) * dA * 3600.0d);
            }
        }
        ((C1040kE) c1372qE.a).a(c1153mF);
    }
}
