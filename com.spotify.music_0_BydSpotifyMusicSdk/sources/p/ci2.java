package p;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.work.impl.foreground.SystemForegroundService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ci2 implements rp1, uk0 {
    public static final String r = vc1.d("SystemFgDispatcher");
    public final wx2 c;
    public final yx2 e;
    public final Object k = new Object();
    public rx2 l;
    public final LinkedHashMap m;
    public final HashMap n;
    public final HashMap o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final androidx.work.impl.constraints.a f112p;
    public bi2 q;

    public ci2(Context context) {
        wx2 wx2VarE = wx2.E(context);
        this.c = wx2VarE;
        this.e = wx2VarE.j;
        this.l = null;
        this.m = new LinkedHashMap();
        this.o = new HashMap();
        this.n = new HashMap();
        this.f112p = new androidx.work.impl.constraints.a(wx2VarE.f206p);
        wx2VarE.l.a(this);
    }

    public static Intent a(Context context, rx2 rx2Var, ew0 ew0Var) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction("ACTION_NOTIFY");
        intent.putExtra("KEY_NOTIFICATION_ID", ew0Var.a);
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", ew0Var.b);
        intent.putExtra("KEY_NOTIFICATION", ew0Var.c);
        intent.putExtra("KEY_WORKSPEC_ID", rx2Var.a);
        intent.putExtra("KEY_GENERATION", rx2Var.b);
        return intent;
    }

    public static Intent d(Context context, rx2 rx2Var, ew0 ew0Var) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction("ACTION_START_FOREGROUND");
        intent.putExtra("KEY_WORKSPEC_ID", rx2Var.a);
        intent.putExtra("KEY_GENERATION", rx2Var.b);
        intent.putExtra("KEY_NOTIFICATION_ID", ew0Var.a);
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", ew0Var.b);
        intent.putExtra("KEY_NOTIFICATION", ew0Var.c);
        return intent;
    }

    @Override // p.uk0
    public final void b(rx2 rx2Var, boolean z) {
        Map.Entry entry;
        synchronized (this.k) {
            try {
                p61 p61Var = ((ky2) this.n.remove(rx2Var)) != null ? (p61) this.o.remove(rx2Var) : null;
                if (p61Var != null) {
                    p61Var.i(null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        ew0 ew0Var = (ew0) this.m.remove(rx2Var);
        int i = 0;
        if (rx2Var.equals(this.l)) {
            if (this.m.size() > 0) {
                Iterator it = this.m.entrySet().iterator();
                Object next = it.next();
                while (true) {
                    entry = (Map.Entry) next;
                    if (!it.hasNext()) {
                        break;
                    } else {
                        next = it.next();
                    }
                }
                this.l = (rx2) entry.getKey();
                if (this.q != null) {
                    ew0 ew0Var2 = (ew0) entry.getValue();
                    bi2 bi2Var = this.q;
                    SystemForegroundService systemForegroundService = (SystemForegroundService) bi2Var;
                    systemForegroundService.e.post(new di2(systemForegroundService, ew0Var2.a, ew0Var2.c, ew0Var2.b));
                    SystemForegroundService systemForegroundService2 = (SystemForegroundService) this.q;
                    systemForegroundService2.e.post(new ei2(ew0Var2.a, i, systemForegroundService2));
                }
            } else {
                this.l = null;
            }
        }
        bi2 bi2Var2 = this.q;
        if (ew0Var == null || bi2Var2 == null) {
            return;
        }
        vc1 vc1VarC = vc1.c();
        rx2Var.toString();
        vc1VarC.getClass();
        SystemForegroundService systemForegroundService3 = (SystemForegroundService) bi2Var2;
        systemForegroundService3.e.post(new ei2(ew0Var.a, i, systemForegroundService3));
    }

    @Override // p.rp1
    public final void c(ky2 ky2Var, bz bzVar) {
        if (bzVar instanceof az) {
            String str = ky2Var.a;
            vc1.c().getClass();
            rx2 rx2VarU = ci1.u(ky2Var);
            wx2 wx2Var = this.c;
            wx2Var.getClass();
            wx2Var.j.a(new bg2(wx2Var.l, new xf2(rx2VarU)));
        }
    }

    public final void e(Intent intent) {
        int i = 0;
        int intExtra = intent.getIntExtra("KEY_NOTIFICATION_ID", 0);
        int intExtra2 = intent.getIntExtra("KEY_FOREGROUND_SERVICE_TYPE", 0);
        rx2 rx2Var = new rx2(intent.getStringExtra("KEY_WORKSPEC_ID"), intent.getIntExtra("KEY_GENERATION", 0));
        Notification notification = (Notification) intent.getParcelableExtra("KEY_NOTIFICATION");
        vc1.c().getClass();
        if (notification == null || this.q == null) {
            return;
        }
        ew0 ew0Var = new ew0(intExtra, intExtra2, notification);
        LinkedHashMap linkedHashMap = this.m;
        linkedHashMap.put(rx2Var, ew0Var);
        if (this.l == null) {
            this.l = rx2Var;
            SystemForegroundService systemForegroundService = (SystemForegroundService) this.q;
            systemForegroundService.e.post(new di2(systemForegroundService, intExtra, notification, intExtra2));
            return;
        }
        SystemForegroundService systemForegroundService2 = (SystemForegroundService) this.q;
        systemForegroundService2.e.post(new a52(systemForegroundService2, intExtra, notification));
        if (intExtra2 == 0 || Build.VERSION.SDK_INT < 29) {
            return;
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            i |= ((ew0) ((Map.Entry) it.next()).getValue()).b;
        }
        ew0 ew0Var2 = (ew0) linkedHashMap.get(this.l);
        if (ew0Var2 != null) {
            SystemForegroundService systemForegroundService3 = (SystemForegroundService) this.q;
            systemForegroundService3.e.post(new di2(systemForegroundService3, ew0Var2.a, ew0Var2.c, i));
        }
    }

    public final void f() {
        this.q = null;
        synchronized (this.k) {
            try {
                Iterator it = this.o.values().iterator();
                while (it.hasNext()) {
                    ((p61) it.next()).i(null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.c.l.e(this);
    }
}
