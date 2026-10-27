package p;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class zh2 implements uk0 {
    public static final String s = vc1.d("SystemAlarmDispatcher");
    public final Context c;
    public final yx2 e;
    public final sy2 k;
    public final qx1 l;
    public final wx2 m;
    public final tu n;
    public final ArrayList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Intent f217p;
    public yh2 q;
    public final tx2 r;

    public zh2(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.c = applicationContext;
        g90 g90Var = new g90(3);
        wx2 wx2VarE = wx2.E(context);
        this.m = wx2VarE;
        sw swVar = wx2VarE.h;
        this.n = new tu(applicationContext, swVar.c, g90Var);
        this.k = new sy2(swVar.f);
        qx1 qx1Var = wx2VarE.l;
        this.l = qx1Var;
        yx2 yx2Var = wx2VarE.j;
        this.e = yx2Var;
        this.r = new tx2(qx1Var, yx2Var);
        qx1Var.a(this);
        this.o = new ArrayList();
        this.f217p = null;
    }

    public static void c() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Needs to be invoked on the main thread.");
        }
    }

    public final void a(Intent intent, int i) {
        vc1 vc1VarC = vc1.c();
        Objects.toString(intent);
        vc1VarC.getClass();
        c();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            vc1.c().getClass();
            return;
        }
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action)) {
            c();
            synchronized (this.o) {
                try {
                    Iterator it = this.o.iterator();
                    while (it.hasNext()) {
                        if ("ACTION_CONSTRAINTS_CHANGED".equals(((Intent) it.next()).getAction())) {
                            return;
                        }
                    }
                } finally {
                }
            }
        }
        intent.putExtra("KEY_START_ID", i);
        synchronized (this.o) {
            try {
                boolean z = !this.o.isEmpty();
                this.o.add(intent);
                if (!z) {
                    d();
                }
            } finally {
            }
        }
    }

    @Override // p.uk0
    public final void b(rx2 rx2Var, boolean z) {
        xx2 xx2Var = this.e.d;
        String str = tu.n;
        Intent intent = new Intent(this.c, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_EXECUTION_COMPLETED");
        intent.putExtra("KEY_NEEDS_RESCHEDULE", z);
        tu.d(intent, rx2Var);
        xx2Var.execute(new a52(0, intent, this));
    }

    public final void d() {
        c();
        PowerManager.WakeLock wakeLockA = sv2.a(this.c, "ProcessCommand");
        try {
            wakeLockA.acquire();
            this.m.j.a(new xh2(this, 0));
        } finally {
            wakeLockA.release();
        }
    }
}
