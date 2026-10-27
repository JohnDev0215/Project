package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import java.util.concurrent.Executor;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: ChildServiceConnectionImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class eo implements ServiceConnection {
    public final Context e;
    public final Intent f;
    public final int g;
    public final Handler h;
    public final Executor i;
    public co j;
    public final String k;
    public boolean l;

    public eo(Context context, Intent intent, int i, Handler handler, Executor executor, co coVar, String str) {
        this.e = context;
        this.f = intent;
        this.g = i;
        this.h = handler;
        this.i = executor;
        this.j = coVar;
        this.k = str;
    }

    public final boolean a() {
        try {
            TraceEvent.q("ChildServiceConnectionImpl.bindServiceConnection", null);
            this.l = qe.b(this.e, this.f, this, this.g, this.h, this.i, this.k);
            TraceEvent.C("ChildServiceConnectionImpl.bindServiceConnection");
            return this.l;
        } catch (Throwable th) {
            TraceEvent.C("ChildServiceConnectionImpl.bindServiceConnection");
            throw th;
        }
    }

    public final void b() {
        if (this.l) {
            this.e.unbindService(this);
            this.l = false;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        co coVar = this.j;
        if (coVar == null) {
            Log.w("cr_ChildServiceConn", "onServiceConnected after timeout " + componentName);
        } else {
            nn nnVar = (nn) coVar;
            qn qnVar = nnVar.a;
            if (qnVar.b.getLooper() == Looper.myLooper()) {
                qnVar.h(iBinder);
            } else {
                qnVar.b.post(new y70(nnVar, 3, iBinder));
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        co coVar = this.j;
        if (coVar != null) {
            nn nnVar = (nn) coVar;
            qn qnVar = nnVar.a;
            if (qnVar.b.getLooper() == Looper.myLooper()) {
                qnVar.i();
            } else {
                qnVar.b.post(new wv(10, nnVar));
            }
        }
    }
}
