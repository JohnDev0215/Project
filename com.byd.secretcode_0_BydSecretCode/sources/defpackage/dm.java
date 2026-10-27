package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import org.greenrobot.eventbus.EventBusException;

/* JADX INFO: compiled from: HandlerPoster.java */
/* JADX INFO: loaded from: classes.dex */
public class dm extends Handler implements jm {
    public final im b;
    public final int c;
    public final bm d;
    public boolean e;

    public dm(bm bmVar, Looper looper, int i) {
        super(looper);
        this.d = bmVar;
        this.c = i;
        this.b = new im();
    }

    @Override // defpackage.jm
    public void a(om omVar, Object obj) {
        hm hmVarA = hm.a(omVar, obj);
        synchronized (this) {
            this.b.a(hmVarA);
            if (!this.e) {
                this.e = true;
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
                hm hmVarB = this.b.b();
                if (hmVarB == null) {
                    synchronized (this) {
                        hmVarB = this.b.b();
                        if (hmVarB == null) {
                            this.e = false;
                            return;
                        }
                    }
                }
                this.d.g(hmVarB);
            } while (SystemClock.uptimeMillis() - jUptimeMillis < this.c);
            if (!sendMessage(obtainMessage())) {
                throw new EventBusException("Could not send handler message");
            }
            this.e = true;
        } finally {
            this.e = false;
        }
    }
}
