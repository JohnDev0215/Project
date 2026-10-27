package p;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryChargingProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryNotLowProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy$NetworkStateProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxy$StorageNotLowProxy;
import androidx.work.impl.background.systemalarm.ConstraintProxyUpdateReceiver;

/* JADX INFO: loaded from: classes.dex */
public final class jq implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Object l;
    public final /* synthetic */ Object m;

    public /* synthetic */ jq(Object obj, Object obj2, Object obj3, Object obj4, int i) {
        this.c = i;
        this.m = obj;
        this.e = obj2;
        this.k = obj3;
        this.l = obj4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        Object obj = this.e;
        Object obj2 = this.k;
        Object obj3 = this.l;
        switch (i) {
            case 0:
                kq kqVar = (kq) obj;
                if (kqVar != null) {
                    hk0 hk0Var = (hk0) this.m;
                    ((lq) hk0Var.e).I = true;
                    kqVar.b.c(false);
                    ((lq) hk0Var.e).I = false;
                }
                MenuItem menuItem = (MenuItem) obj2;
                if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
                    ((si1) obj3).q(menuItem, null, 4);
                    return;
                }
                return;
            default:
                try {
                    boolean booleanExtra = ((Intent) obj).getBooleanExtra("KEY_BATTERY_NOT_LOW_PROXY_ENABLED", false);
                    boolean booleanExtra2 = ((Intent) obj).getBooleanExtra("KEY_BATTERY_CHARGING_PROXY_ENABLED", false);
                    boolean booleanExtra3 = ((Intent) obj).getBooleanExtra("KEY_STORAGE_NOT_LOW_PROXY_ENABLED", false);
                    boolean booleanExtra4 = ((Intent) obj).getBooleanExtra("KEY_NETWORK_STATE_PROXY_ENABLED", false);
                    vc1 vc1VarC = vc1.c();
                    int i2 = ConstraintProxyUpdateReceiver.a;
                    vc1VarC.getClass();
                    ir1.a((Context) obj2, ConstraintProxy$BatteryNotLowProxy.class, booleanExtra);
                    ir1.a((Context) obj2, ConstraintProxy$BatteryChargingProxy.class, booleanExtra2);
                    ir1.a((Context) obj2, ConstraintProxy$StorageNotLowProxy.class, booleanExtra3);
                    ir1.a((Context) obj2, ConstraintProxy$NetworkStateProxy.class, booleanExtra4);
                    return;
                } finally {
                    ((BroadcastReceiver.PendingResult) obj3).finish();
                }
        }
    }
}
