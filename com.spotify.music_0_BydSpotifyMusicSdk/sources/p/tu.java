package p;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.work.NetworkType;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemalarm.ConstraintProxyUpdateReceiver;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class tu implements uk0 {
    public static final String n = vc1.d("CommandHandler");
    public final Context c;
    public final HashMap e = new HashMap();
    public final Object k = new Object();
    public final zb0 l;
    public final g90 m;

    public tu(Context context, zb0 zb0Var, g90 g90Var) {
        this.c = context;
        this.l = zb0Var;
        this.m = g90Var;
    }

    public static rx2 c(Intent intent) {
        return new rx2(intent.getStringExtra("KEY_WORKSPEC_ID"), intent.getIntExtra("KEY_WORKSPEC_GENERATION", 0));
    }

    public static void d(Intent intent, rx2 rx2Var) {
        intent.putExtra("KEY_WORKSPEC_ID", rx2Var.a);
        intent.putExtra("KEY_WORKSPEC_GENERATION", rx2Var.b);
    }

    public final void a(int i, Intent intent, zh2 zh2Var) {
        List<xf2> listK;
        String action = intent.getAction();
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action)) {
            vc1 vc1VarC = vc1.c();
            Objects.toString(intent);
            vc1VarC.getClass();
            yy yyVar = new yy(this.c, this.l, i, zh2Var);
            ArrayList<ky2> arrayListE = zh2Var.m.i.v().e();
            int i2 = iy.a;
            Iterator it = arrayListE.iterator();
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            while (it.hasNext()) {
                wy wyVar = ((ky2) it.next()).j;
                z |= wyVar.d;
                z2 |= wyVar.b;
                z3 |= wyVar.e;
                z4 |= wyVar.a != NetworkType.c;
                if (z && z2 && z3 && z4) {
                    break;
                }
            }
            int i3 = ConstraintProxyUpdateReceiver.a;
            Intent intent2 = new Intent("androidx.work.impl.background.systemalarm.UpdateProxies");
            Context context = yyVar.a;
            intent2.setComponent(new ComponentName(context, (Class<?>) ConstraintProxyUpdateReceiver.class));
            intent2.putExtra("KEY_BATTERY_NOT_LOW_PROXY_ENABLED", z).putExtra("KEY_BATTERY_CHARGING_PROXY_ENABLED", z2).putExtra("KEY_STORAGE_NOT_LOW_PROXY_ENABLED", z3).putExtra("KEY_NETWORK_STATE_PROXY_ENABLED", z4);
            context.sendBroadcast(intent2);
            ArrayList<ky2> arrayList = new ArrayList(arrayListE.size());
            yyVar.b.getClass();
            long jCurrentTimeMillis = System.currentTimeMillis();
            for (ky2 ky2Var : arrayListE) {
                if (jCurrentTimeMillis >= ky2Var.a() && (!ky2Var.b() || yyVar.d.a(ky2Var))) {
                    arrayList.add(ky2Var);
                }
            }
            for (ky2 ky2Var2 : arrayList) {
                String str = ky2Var2.a;
                rx2 rx2VarU = ci1.u(ky2Var2);
                Intent intent3 = new Intent(context, (Class<?>) SystemAlarmService.class);
                intent3.setAction("ACTION_DELAY_MET");
                d(intent3, rx2VarU);
                vc1.c().getClass();
                zh2Var.e.d.execute(new a52(yyVar.c, intent3, zh2Var));
            }
            return;
        }
        if ("ACTION_RESCHEDULE".equals(action)) {
            vc1 vc1VarC2 = vc1.c();
            Objects.toString(intent);
            vc1VarC2.getClass();
            zh2Var.m.G();
            return;
        }
        Bundle extras = intent.getExtras();
        String[] strArr = {"KEY_WORKSPEC_ID"};
        if (extras == null || extras.isEmpty() || extras.get(strArr[0]) == null) {
            vc1.c().a(n, "Invalid request for " + action + " , requires KEY_WORKSPEC_ID .");
            return;
        }
        if ("ACTION_SCHEDULE_WORK".equals(action)) {
            rx2 rx2VarC = c(intent);
            vc1 vc1VarC3 = vc1.c();
            rx2VarC.toString();
            vc1VarC3.getClass();
            WorkDatabase workDatabase = zh2Var.m.i;
            workDatabase.c();
            try {
                ky2 ky2VarH = workDatabase.v().h(rx2VarC.a);
                if (ky2VarH == null) {
                    vc1 vc1VarC4 = vc1.c();
                    rx2VarC.toString();
                    vc1VarC4.getClass();
                } else if (ky2VarH.b.a()) {
                    vc1 vc1VarC5 = vc1.c();
                    rx2VarC.toString();
                    vc1VarC5.getClass();
                } else {
                    long jA = ky2VarH.a();
                    boolean zB = ky2VarH.b();
                    Context context2 = this.c;
                    if (zB) {
                        vc1 vc1VarC6 = vc1.c();
                        rx2VarC.toString();
                        vc1VarC6.getClass();
                        g5.b(context2, workDatabase, rx2VarC, jA);
                        Intent intent4 = new Intent(context2, (Class<?>) SystemAlarmService.class);
                        intent4.setAction("ACTION_CONSTRAINTS_CHANGED");
                        zh2Var.e.d.execute(new a52(i, intent4, zh2Var));
                    } else {
                        vc1 vc1VarC7 = vc1.c();
                        rx2VarC.toString();
                        vc1VarC7.getClass();
                        g5.b(context2, workDatabase, rx2VarC, jA);
                    }
                    workDatabase.o();
                }
                return;
            } finally {
                workDatabase.k();
            }
        }
        if ("ACTION_DELAY_MET".equals(action)) {
            synchronized (this.k) {
                try {
                    rx2 rx2VarC2 = c(intent);
                    vc1 vc1VarC8 = vc1.c();
                    rx2VarC2.toString();
                    vc1VarC8.getClass();
                    if (this.e.containsKey(rx2VarC2)) {
                        vc1 vc1VarC9 = vc1.c();
                        rx2VarC2.toString();
                        vc1VarC9.getClass();
                    } else {
                        q80 q80Var = new q80(this.c, i, zh2Var, this.m.m(rx2VarC2));
                        this.e.put(rx2VarC2, q80Var);
                        q80Var.e();
                    }
                } finally {
                }
            }
            return;
        }
        if (!"ACTION_STOP_WORK".equals(action)) {
            if (!"ACTION_EXECUTION_COMPLETED".equals(action)) {
                vc1 vc1VarC10 = vc1.c();
                intent.toString();
                vc1VarC10.getClass();
                return;
            } else {
                rx2 rx2VarC3 = c(intent);
                boolean z5 = intent.getExtras().getBoolean("KEY_NEEDS_RESCHEDULE");
                vc1 vc1VarC11 = vc1.c();
                intent.toString();
                vc1VarC11.getClass();
                b(rx2VarC3, z5);
                return;
            }
        }
        Bundle extras2 = intent.getExtras();
        String string = extras2.getString("KEY_WORKSPEC_ID");
        boolean zContainsKey = extras2.containsKey("KEY_WORKSPEC_GENERATION");
        g90 g90Var = this.m;
        if (zContainsKey) {
            int i4 = extras2.getInt("KEY_WORKSPEC_GENERATION");
            ArrayList arrayList2 = new ArrayList(1);
            xf2 xf2VarL = g90Var.l(new rx2(string, i4));
            listK = arrayList2;
            if (xf2VarL != null) {
                arrayList2.add(xf2VarL);
                listK = arrayList2;
            }
        } else {
            listK = g90Var.k(string);
        }
        for (xf2 xf2Var : listK) {
            vc1.c().getClass();
            tx2 tx2Var = zh2Var.r;
            tx2Var.getClass();
            v03.j(xf2Var, "workSpecId");
            tx2Var.a(xf2Var, -512);
            WorkDatabase workDatabase2 = zh2Var.m.i;
            int i5 = g5.a;
            cy2 cy2Var = (cy2) workDatabase2.s();
            rx2 rx2Var = xf2Var.a;
            hi2 hi2VarA = cy2Var.a(rx2Var);
            if (hi2VarA != null) {
                g5.a(this.c, rx2Var, hi2VarA.c);
                vc1 vc1VarC12 = vc1.c();
                rx2Var.toString();
                vc1VarC12.getClass();
                i52 i52Var = cy2Var.a;
                i52Var.b();
                androidx.room.a aVar = cy2Var.c;
                th2 th2VarA = aVar.a();
                String str2 = rx2Var.a;
                if (str2 == null) {
                    th2VarA.K(1);
                } else {
                    th2VarA.x(1, str2);
                }
                th2VarA.h0(2, rx2Var.b);
                i52Var.c();
                try {
                    th2VarA.F();
                    i52Var.o();
                } finally {
                    i52Var.k();
                    aVar.c(th2VarA);
                }
            }
            zh2Var.b(rx2Var, false);
        }
    }

    @Override // p.uk0
    public final void b(rx2 rx2Var, boolean z) {
        synchronized (this.k) {
            try {
                q80 q80Var = (q80) this.e.remove(rx2Var);
                this.m.l(rx2Var);
                if (q80Var != null) {
                    q80Var.f(z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
