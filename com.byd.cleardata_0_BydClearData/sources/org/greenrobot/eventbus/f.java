package org.greenrobot.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public class f extends Handler implements l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final k f3304b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f3305c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c f3306d;
    private boolean e;

    protected f(c cVar, Looper looper, int i) {
        super(looper);
        this.f3306d = cVar;
        this.f3305c = i;
        this.f3304b = new k();
    }

    @Override // org.greenrobot.eventbus.l
    public void a(q qVar, Object obj) {
        j jVarA = j.a(qVar, obj);
        synchronized (this) {
            this.f3304b.a(jVarA);
            if (!this.e) {
                this.e = true;
                if (!sendMessage(obtainMessage())) {
                    throw new e("Could not send handler message");
                }
            }
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            long jUptimeMillis = SystemClock.uptimeMillis();
            do {
                j jVarB = this.f3304b.b();
                if (jVarB == null) {
                    synchronized (this) {
                        jVarB = this.f3304b.b();
                        if (jVarB == null) {
                            this.e = false;
                            return;
                        }
                    }
                }
                this.f3306d.g(jVarB);
            } while (SystemClock.uptimeMillis() - jUptimeMillis < this.f3305c);
            if (!sendMessage(obtainMessage())) {
                throw new e("Could not send handler message");
            }
            this.e = true;
        } finally {
            this.e = false;
        }
    }
}
