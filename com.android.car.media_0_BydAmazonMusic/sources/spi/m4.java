package spi;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* JADX INFO: compiled from: HandlerPoster.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m4 extends Handler {
    public final h8 a;
    public final int b;
    public final o3 c;
    public boolean d;

    public m4(o3 o3Var, Looper looper, int i) {
        super(looper);
        this.c = o3Var;
        this.b = i;
        this.a = new h8();
    }

    public final void a(pa paVar, Object obj) {
        g8 g8VarA = g8.a(paVar, obj);
        synchronized (this) {
            this.a.a(g8VarA);
            if (!this.d) {
                this.d = true;
                if (!sendMessage(obtainMessage())) {
                    throw new q3("Could not send handler message");
                }
            }
        }
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        try {
            long jUptimeMillis = SystemClock.uptimeMillis();
            do {
                g8 g8VarB = this.a.b();
                if (g8VarB == null) {
                    synchronized (this) {
                        g8VarB = this.a.b();
                        if (g8VarB == null) {
                            return;
                        }
                    }
                }
                this.c.c(g8VarB);
            } while (SystemClock.uptimeMillis() - jUptimeMillis < this.b);
            if (!sendMessage(obtainMessage())) {
                throw new q3("Could not send handler message");
            }
            this.d = true;
        } finally {
            this.d = false;
        }
    }
}
