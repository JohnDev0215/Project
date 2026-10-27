package c.a.d.f;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* JADX INFO: compiled from: KeyEventObserver.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f2442a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public IntentFilter f2443b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f2444c = new c();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f2445d;

    /* JADX INFO: compiled from: KeyEventObserver.java */
    public interface b {
        void a();

        void b();

        void c();

        void d();

        void e();
    }

    /* JADX INFO: compiled from: KeyEventObserver.java */
    public class c extends BroadcastReceiver {
        public c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            action.hashCode();
            switch (action) {
                case "android.intent.action.SCREEN_OFF":
                    if (g.this.f2445d != null) {
                        g.this.f2445d.e();
                        break;
                    }
                    break;
                case "android.intent.action.SCREEN_ON":
                    if (g.this.f2445d != null) {
                        g.this.f2445d.c();
                        break;
                    }
                    break;
                case "android.intent.action.CLOSE_SYSTEM_DIALOGS":
                    String stringExtra = intent.getStringExtra("reason");
                    if ("homekey".equals(stringExtra)) {
                        if (g.this.f2445d != null) {
                            g.this.f2445d.b();
                        }
                        break;
                    } else if ("recentapps".equals(stringExtra) && g.this.f2445d != null) {
                        g.this.f2445d.d();
                        break;
                    }
                    break;
                case "android.intent.action.USER_PRESENT":
                    if (g.this.f2445d != null) {
                        g.this.f2445d.a();
                        break;
                    }
                    break;
            }
        }
    }

    public g(Context context) {
        this.f2442a = context;
        IntentFilter intentFilter = new IntentFilter();
        this.f2443b = intentFilter;
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        this.f2443b.addAction("android.intent.action.SCREEN_ON");
        this.f2443b.addAction("android.intent.action.SCREEN_OFF");
        this.f2443b.addAction("android.intent.action.USER_PRESENT");
    }

    public void b(b bVar) {
        this.f2445d = bVar;
    }

    public void c() {
        c cVar;
        IntentFilter intentFilter;
        Context context = this.f2442a;
        if (context == null || (cVar = this.f2444c) == null || (intentFilter = this.f2443b) == null) {
            return;
        }
        context.registerReceiver(cVar, intentFilter);
    }

    public void d() {
        c cVar;
        Context context = this.f2442a;
        if (context == null || (cVar = this.f2444c) == null) {
            return;
        }
        context.unregisterReceiver(cVar);
        this.f2444c = null;
        this.f2443b = null;
    }
}
