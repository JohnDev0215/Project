package defpackage;

import android.content.IntentFilter;
import android.os.PowerManager;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.b;

/* JADX INFO: compiled from: PowerSavingModeMonitor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i32 {
    public static i32 e;
    public final b<Runnable> a = new b<>();
    public final PowerManager b = (PowerManager) xz.a.getSystemService("power");
    public h32 c;
    public boolean d;

    public i32() {
        b();
        a();
        ApplicationStatus.e(new ApplicationStatus.d() { // from class: g32
            @Override // org.chromium.base.ApplicationStatus.d
            public final void v(int i) {
                this.e.a();
            }
        });
    }

    public final void a() {
        int stateForApplication = ApplicationStatus.getStateForApplication();
        if (stateForApplication == 1 || stateForApplication == 2) {
            if (this.c == null) {
                h32 h32Var = new h32(this);
                this.c = h32Var;
                xz.g(xz.a, h32Var, new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED"));
            }
            b();
            return;
        }
        h32 h32Var2 = this.c;
        if (h32Var2 != null) {
            xz.a.unregisterReceiver(h32Var2);
            this.c = null;
        }
    }

    public final void b() {
        PowerManager powerManager = this.b;
        boolean z = powerManager != null && powerManager.isPowerSaveMode();
        if (z == this.d) {
            return;
        }
        this.d = z;
        Iterator<Runnable> it = this.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((Runnable) aVar.next()).run();
            }
        }
    }
}
