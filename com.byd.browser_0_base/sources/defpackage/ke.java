package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;
import defpackage.ge;

/* JADX INFO: compiled from: BatteryStatusManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ke {
    public final c a;
    public final IntentFilter b;
    public final a c;
    public final b d;
    public boolean e;

    /* JADX INFO: compiled from: BatteryStatusManager.java */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            ke keVar = ke.this;
            keVar.getClass();
            if (!intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
                Log.e("cr_BatteryStatusManager", "Unexpected intent.");
                return;
            }
            boolean booleanExtra = intent.getBooleanExtra("present", false);
            int intExtra = intent.getIntExtra("plugged", -1);
            c cVar = keVar.a;
            if (!booleanExtra || intExtra == -1) {
                ((ge.a) cVar).a(new je());
                return;
            }
            double intExtra2 = ((double) intent.getIntExtra("level", -1)) / ((double) intent.getIntExtra("scale", -1));
            if (intExtra2 < 0.0d || intExtra2 > 1.0d) {
                intExtra2 = 1.0d;
            }
            boolean z = intExtra != 0;
            double d = (z && (intent.getIntExtra("status", -1) == 5)) ? 0.0d : Double.POSITIVE_INFINITY;
            je jeVar = new je();
            jeVar.b = z;
            jeVar.c = d;
            jeVar.d = Double.POSITIVE_INFINITY;
            jeVar.e = intExtra2;
            if (keVar.d != null) {
                new le(keVar, jeVar).c(i9.e);
            } else {
                ((ge.a) cVar).a(jeVar);
            }
        }
    }

    /* JADX INFO: compiled from: BatteryStatusManager.java */
    public static class b {
        public final BatteryManager a;

        public b(BatteryManager batteryManager) {
            this.a = batteryManager;
        }
    }

    /* JADX INFO: compiled from: BatteryStatusManager.java */
    public interface c {
    }

    public ke(ge.a aVar) {
        b bVar = new b((BatteryManager) xz.a.getSystemService("batterymanager"));
        this.b = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        this.c = new a();
        this.a = aVar;
        this.d = bVar;
    }
}
