package androidx.work.impl.foreground;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.Objects;
import java.util.UUID;
import p.bi2;
import p.ci2;
import p.tp;
import p.u91;
import p.vc1;
import p.wx2;
import p.yf1;

/* JADX INFO: loaded from: classes.dex */
public class SystemForegroundService extends u91 implements bi2 {
    public static final /* synthetic */ int n = 0;
    public Handler e;
    public boolean k;
    public ci2 l;
    public NotificationManager m;

    static {
        vc1.d("SystemFgService");
    }

    public final void b() {
        this.e = new Handler(Looper.getMainLooper());
        this.m = (NotificationManager) getApplicationContext().getSystemService("notification");
        ci2 ci2Var = new ci2(getApplicationContext());
        this.l = ci2Var;
        if (ci2Var.q != null) {
            vc1.c().a(ci2.r, "A callback already exists.");
        } else {
            ci2Var.q = this;
        }
    }

    @Override // p.u91, android.app.Service
    public final void onCreate() {
        super.onCreate();
        b();
    }

    @Override // p.u91, android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        this.l.f();
    }

    @Override // p.u91, android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (this.k) {
            vc1.c().getClass();
            this.l.f();
            b();
            this.k = false;
        }
        if (intent == null) {
            return 3;
        }
        ci2 ci2Var = this.l;
        ci2Var.getClass();
        String action = intent.getAction();
        if ("ACTION_START_FOREGROUND".equals(action)) {
            vc1 vc1VarC = vc1.c();
            Objects.toString(intent);
            vc1VarC.getClass();
            ci2Var.e.a(new yf1(ci2Var, 10, intent.getStringExtra("KEY_WORKSPEC_ID")));
            ci2Var.e(intent);
            return 3;
        }
        if ("ACTION_NOTIFY".equals(action)) {
            ci2Var.e(intent);
            return 3;
        }
        if (!"ACTION_CANCEL_WORK".equals(action)) {
            if (!"ACTION_STOP_FOREGROUND".equals(action)) {
                return 3;
            }
            vc1.c().getClass();
            bi2 bi2Var = ci2Var.q;
            if (bi2Var == null) {
                return 3;
            }
            SystemForegroundService systemForegroundService = (SystemForegroundService) bi2Var;
            systemForegroundService.k = true;
            vc1.c().getClass();
            systemForegroundService.stopForeground(true);
            systemForegroundService.stopSelf();
            return 3;
        }
        vc1 vc1VarC2 = vc1.c();
        Objects.toString(intent);
        vc1VarC2.getClass();
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        if (stringExtra == null || TextUtils.isEmpty(stringExtra)) {
            return 3;
        }
        UUID uuidFromString = UUID.fromString(stringExtra);
        wx2 wx2Var = ci2Var.c;
        wx2Var.getClass();
        wx2Var.j.a(new tp(wx2Var, uuidFromString));
        return 3;
    }
}
