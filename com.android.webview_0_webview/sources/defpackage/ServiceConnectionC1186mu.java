package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import java.util.concurrent.Executor;
import org.chromium.base.TraceEvent;

/* JADX INFO: renamed from: mu, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ServiceConnectionC1186mu implements InterfaceC1075ku, ServiceConnection {
    public final Context a;
    public final Intent b;
    public final int c;
    public final Handler d;
    public final Executor e;
    public final InterfaceC1131lu f;
    public final String g;
    public boolean h;

    public ServiceConnectionC1186mu(Context context, Intent intent, int i, Handler handler, Executor executor, InterfaceC1131lu interfaceC1131lu, String str, AbstractC0684du abstractC0684du) {
        this.a = context;
        this.b = intent;
        this.c = i;
        this.d = handler;
        this.e = executor;
        this.f = interfaceC1131lu;
        this.g = str;
    }

    public boolean a() {
        try {
            TraceEvent.x("ChildProcessConnection.ChildServiceConnectionImpl.bind", null);
            this.h = Mt.b(this.a, this.b, this, this.c, this.d, this.e, this.g);
            TraceEvent.A("ChildProcessConnection.ChildServiceConnectionImpl.bind");
            return this.h;
        } catch (Throwable th) {
            TraceEvent.A("ChildProcessConnection.ChildServiceConnectionImpl.bind");
            throw th;
        }
    }

    public void b() {
        if (this.h) {
            this.a.unbindService(this);
            this.h = false;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        final C0852gu c0852gu = (C0852gu) this.f;
        if (c0852gu.a.a.getLooper() == Looper.myLooper()) {
            c0852gu.a.g(iBinder);
        } else {
            c0852gu.a.a.post(new Runnable(c0852gu, iBinder) { // from class: eu
                public final C0852gu s;
                public final IBinder t;

                {
                    this.s = c0852gu;
                    this.t = iBinder;
                }

                @Override // java.lang.Runnable
                public void run() {
                    C0852gu c0852gu2 = this.s;
                    c0852gu2.a.g(this.t);
                }
            });
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        final C0852gu c0852gu = (C0852gu) this.f;
        if (c0852gu.a.a.getLooper() == Looper.myLooper()) {
            c0852gu.a.h();
        } else {
            c0852gu.a.a.post(new Runnable(c0852gu) { // from class: fu
                public final C0852gu s;

                {
                    this.s = c0852gu;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.s.a.h();
                }
            });
        }
    }
}
