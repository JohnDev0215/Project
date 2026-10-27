package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class o4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ib f472a;
    public Handler b;
    public final Context c;
    public final y5 d;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            o4.this.f472a.a();
        }
    }

    public o4(Context context, ld ldVar, yb ybVar, tc tcVar, y5 y5Var) {
        this.c = context;
        this.d = y5Var;
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper != null) {
            this.b = new Handler(mainLooper);
        } else {
            this.b = new Handler();
        }
        this.f472a = new ib(context, ldVar, a(ybVar), tcVar, y5Var);
    }

    public final yb a(yb ybVar) {
        if (ybVar == null) {
            return null;
        }
        return new n4(this, ybVar);
    }

    public void a() {
        va.f637a.execute(new a());
    }
}
