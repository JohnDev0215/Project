package org.greenrobot.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* JADX INFO: compiled from: HandlerPoster.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends Handler implements k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final j f4692b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4693c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c f4694d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f4695e;

    protected e(c cVar, Looper looper, int i) {
        super(looper);
        this.f4694d = cVar;
        this.f4693c = i;
        this.f4692b = new j();
    }

    @Override // org.greenrobot.eventbus.k
    public void a(p pVar, Object obj) {
        i iVarA = i.a(pVar, obj);
        synchronized (this) {
            this.f4692b.a(iVarA);
            if (!this.f4695e) {
                this.f4695e = true;
                if (!sendMessage(obtainMessage())) {
                    throw new EventBusException("Could not send handler message");
                }
            }
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            long jUptimeMillis = SystemClock.uptimeMillis();
            do {
                i iVarB = this.f4692b.b();
                if (iVarB == null) {
                    synchronized (this) {
                        iVarB = this.f4692b.b();
                        if (iVarB == null) {
                            this.f4695e = false;
                            return;
                        }
                    }
                }
                this.f4694d.g(iVarB);
            } while (SystemClock.uptimeMillis() - jUptimeMillis < this.f4693c);
            if (!sendMessage(obtainMessage())) {
                throw new EventBusException("Could not send handler message");
            }
            this.f4695e = true;
        } finally {
            this.f4695e = false;
        }
    }
}
