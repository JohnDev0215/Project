package defpackage;

import J.N;
import android.content.Context;
import android.os.PersistableBundle;
import defpackage.fc;
import org.chromium.chrome.browser.offlinepages.TriggerConditions;

/* JADX INFO: compiled from: OfflineBackgroundTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fq1 extends fk1 {
    /* JADX WARN: Removed duplicated region for block: B:19:0x003b  */
    @Override // defpackage.fk1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(android.content.Context r2, defpackage.a73 r3, defpackage.du r4) {
        /*
            r1 = this;
            java.lang.String r1 = "PowerConnected"
            android.os.PersistableBundle r3 = r3.b
            r4 = 1
            r3.getBoolean(r1, r4)
            java.lang.String r1 = "BatteryPercentage"
            r0 = 100
            int r1 = r3.getInt(r1, r0)
            java.lang.String r0 = "UnmeteredNetwork"
            r3.getBoolean(r0, r4)
            h90 r2 = defpackage.h90.a(r2)
            boolean r3 = r2.a
            r0 = 0
            if (r3 != 0) goto L25
            int r2 = r2.b
            if (r2 < r1) goto L23
            goto L25
        L23:
            r1 = r0
            goto L26
        L25:
            r1 = r4
        L26:
            if (r1 != 0) goto L29
            goto L3b
        L29:
            boolean r1 = org.chromium.base.SysUtils.isLowEndDevice()
            if (r1 == 0) goto L38
            boolean r1 = org.chromium.base.ApplicationStatus.hasVisibleActivities()
            if (r1 != 0) goto L36
            goto L38
        L36:
            r1 = r0
            goto L39
        L38:
            r1 = r4
        L39:
            if (r1 != 0) goto L3d
        L3b:
            r1 = r0
            goto L3e
        L3d:
            r1 = r4
        L3e:
            if (r1 != 0) goto L41
            return r4
        L41:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fq1.c(android.content.Context, a73, du):int");
    }

    @Override // defpackage.fk1
    public final void d(Context context, a73 a73Var, fc.a aVar) {
        if (n3.v == null) {
            n3.v = new n3();
        }
        n3 n3Var = n3.v;
        PersistableBundle persistableBundle = a73Var.b;
        eq1 eq1Var = new eq1(aVar);
        h90 h90VarA = h90.a(context);
        n3Var.getClass();
        if (!N.MrvK6$zt(h90VarA.a, h90VarA.b, h90VarA.d, eq1Var)) {
            aVar.b(true);
            return;
        }
        PersistableBundle persistableBundle2 = a73Var.b;
        bc.a(new TriggerConditions(persistableBundle2.getInt("BatteryPercentage", 100), persistableBundle2.getBoolean("PowerConnected", true), persistableBundle2.getBoolean("UnmeteredNetwork", true)), 300000L, false);
    }

    @Override // defpackage.fk1
    public final boolean e() {
        return true;
    }

    @Override // defpackage.fk1
    public final boolean f(a73 a73Var) {
        if (n3.v == null) {
            n3.v = new n3();
        }
        n3.v.getClass();
        N.MMBt6JJr();
        return true;
    }
}
