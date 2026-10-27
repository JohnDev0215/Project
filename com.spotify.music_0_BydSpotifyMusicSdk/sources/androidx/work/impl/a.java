package androidx.work.impl;

import android.content.Context;
import androidx.work.impl.background.systemjob.SystemJobService;
import com.spotify.music.R;
import p.f60;
import p.h52;
import p.h72;
import p.ir1;
import p.jk1;
import p.kk1;
import p.li2;
import p.lk1;
import p.mk1;
import p.nk1;
import p.nn2;
import p.ob2;
import p.ok1;
import p.ph2;
import p.pk1;
import p.qk1;
import p.qx1;
import p.rk1;
import p.sk1;
import p.sw;
import p.tx2;
import p.u32;
import p.v03;
import p.vc1;
import p.wx2;
import p.yx2;
import p.z01;
import p.zb0;
import p.zs;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final wx2 a(Context context, sw swVar) {
        h52 h52VarN;
        v03.j(context, "context");
        yx2 yx2Var = new yx2(swVar.b);
        final Context applicationContext = context.getApplicationContext();
        v03.i(applicationContext, "context.applicationContext");
        ob2 ob2Var = yx2Var.a;
        v03.i(ob2Var, "workTaskExecutor.serialTaskExecutor");
        boolean z = context.getResources().getBoolean(R.bool.workmanager_test_configuration);
        zb0 zb0Var = swVar.c;
        v03.j(zb0Var, "clock");
        if (z) {
            h52VarN = new h52(applicationContext, WorkDatabase.class, null);
            h52VarN.j = true;
        } else {
            h52VarN = f60.n(applicationContext, WorkDatabase.class, "androidx.work.workdb");
            h52VarN.i = new ph2() { // from class: p.ix2
                @Override // p.ph2
                public final qh2 a(oh2 oh2Var) {
                    Context context2 = applicationContext;
                    v03.j(context2, "$context");
                    String str = oh2Var.b;
                    j52 j52Var = oh2Var.c;
                    v03.j(j52Var, "callback");
                    if (str == null || str.length() == 0) {
                        throw new IllegalArgumentException("Must set a non-null database name to a configuration that uses the no backup directory.".toString());
                    }
                    oh2 oh2Var2 = new oh2(context2, str, j52Var, true, true);
                    return new androidx.sqlite.db.framework.d(oh2Var2.a, oh2Var2.b, oh2Var2.c, oh2Var2.d, oh2Var2.e);
                }
            };
        }
        h52VarN.g = ob2Var;
        h52VarN.d.add(new zs(zb0Var));
        h52VarN.a(nk1.c);
        h52VarN.a(new u32(applicationContext, 2, 3));
        h52VarN.a(ok1.c);
        h52VarN.a(pk1.c);
        h52VarN.a(new u32(applicationContext, 5, 6));
        h52VarN.a(qk1.c);
        h52VarN.a(rk1.c);
        h52VarN.a(sk1.c);
        h52VarN.a(new u32(applicationContext));
        h52VarN.a(new u32(applicationContext, 10, 11));
        h52VarN.a(jk1.c);
        h52VarN.a(kk1.c);
        h52VarN.a(lk1.c);
        h52VarN.a(mk1.c);
        h52VarN.l = false;
        h52VarN.m = true;
        WorkDatabase workDatabase = (WorkDatabase) h52VarN.b();
        Context applicationContext2 = context.getApplicationContext();
        v03.i(applicationContext2, "context.applicationContext");
        nn2 nn2Var = new nn2(applicationContext2, yx2Var);
        qx1 qx1Var = new qx1(context.getApplicationContext(), swVar, yx2Var, workDatabase);
        v03.j(WorkManagerImplExtKt$WorkManagerImpl$1.k, "schedulersCreator");
        int i = h72.a;
        li2 li2Var = new li2(context, workDatabase, swVar);
        ir1.a(context, SystemJobService.class, true);
        vc1.c().getClass();
        return new wx2(context.getApplicationContext(), swVar, yx2Var, workDatabase, v03.C(li2Var, new z01(context, swVar, nn2Var, qx1Var, new tx2(qx1Var, yx2Var), yx2Var)), qx1Var, nn2Var);
    }
}
