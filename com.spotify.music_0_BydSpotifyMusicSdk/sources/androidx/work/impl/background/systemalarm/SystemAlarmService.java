package androidx.work.impl.background.systemalarm;

import android.content.Intent;
import android.os.PowerManager;
import java.util.LinkedHashMap;
import java.util.Map;
import p.sv2;
import p.tv2;
import p.u91;
import p.vc1;
import p.yh2;
import p.zh2;

/* JADX INFO: loaded from: classes.dex */
public class SystemAlarmService extends u91 implements yh2 {
    public zh2 e;
    public boolean k;

    static {
        vc1.d("SystemAlarmService");
    }

    public final void b() {
        this.k = true;
        vc1.c().getClass();
        int i = sv2.a;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        synchronized (tv2.a) {
            linkedHashMap.putAll(tv2.b);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            PowerManager.WakeLock wakeLock = (PowerManager.WakeLock) entry.getKey();
            if (wakeLock != null && wakeLock.isHeld()) {
                vc1.c().getClass();
            }
        }
        stopSelf();
    }

    @Override // p.u91, android.app.Service
    public final void onCreate() {
        super.onCreate();
        zh2 zh2Var = new zh2(this);
        this.e = zh2Var;
        if (zh2Var.q != null) {
            vc1.c().a(zh2.s, "A completion listener for SystemAlarmDispatcher already exists.");
        } else {
            zh2Var.q = this;
        }
        this.k = false;
    }

    @Override // p.u91, android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        this.k = true;
        zh2 zh2Var = this.e;
        zh2Var.getClass();
        vc1.c().getClass();
        zh2Var.l.e(zh2Var);
        zh2Var.q = null;
    }

    @Override // p.u91, android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (this.k) {
            vc1.c().getClass();
            zh2 zh2Var = this.e;
            zh2Var.getClass();
            vc1.c().getClass();
            zh2Var.l.e(zh2Var);
            zh2Var.q = null;
            zh2 zh2Var2 = new zh2(this);
            this.e = zh2Var2;
            if (zh2Var2.q != null) {
                vc1.c().a(zh2.s, "A completion listener for SystemAlarmDispatcher already exists.");
            } else {
                zh2Var2.q = this;
            }
            this.k = false;
        }
        if (intent == null) {
            return 3;
        }
        this.e.a(intent, i2);
        return 3;
    }
}
