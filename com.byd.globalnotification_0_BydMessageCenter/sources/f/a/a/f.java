package f.a.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public class f extends Handler implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f2556a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2557b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f2558c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2559d;

    public f(c cVar, Looper looper, int i) {
        super(looper);
        this.f2558c = cVar;
        this.f2557b = i;
        this.f2556a = new k();
    }

    @Override // f.a.a.l
    public void a(q qVar, Object obj) {
        j jVarA = j.a(qVar, obj);
        synchronized (this) {
            this.f2556a.a(jVarA);
            if (!this.f2559d) {
                this.f2559d = true;
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
                j jVarB = this.f2556a.b();
                if (jVarB == null) {
                    synchronized (this) {
                        jVarB = this.f2556a.b();
                        if (jVarB == null) {
                            this.f2559d = false;
                            return;
                        }
                    }
                }
                this.f2558c.c(jVarB);
            } while (SystemClock.uptimeMillis() - jUptimeMillis < this.f2557b);
            if (!sendMessage(obtainMessage())) {
                throw new e("Could not send handler message");
            }
            this.f2559d = true;
        } finally {
            this.f2559d = false;
        }
    }
}
