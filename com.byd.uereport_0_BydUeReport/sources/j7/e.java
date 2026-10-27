package j7;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import org.greenrobot.eventbus.EventBusException;

/* JADX INFO: compiled from: HandlerPoster.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends Handler implements k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final j f5649d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5650e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c f5651f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5652g;

    public e(c cVar, Looper looper, int i8) {
        super(looper);
        this.f5651f = cVar;
        this.f5650e = i8;
        this.f5649d = new j();
    }

    @Override // j7.k
    public void a(p pVar, Object obj) {
        i iVarA = i.a(pVar, obj);
        synchronized (this) {
            this.f5649d.a(iVarA);
            if (!this.f5652g) {
                this.f5652g = true;
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
                i iVarB = this.f5649d.b();
                if (iVarB == null) {
                    synchronized (this) {
                        iVarB = this.f5649d.b();
                        if (iVarB == null) {
                            this.f5652g = false;
                            return;
                        }
                    }
                }
                this.f5651f.g(iVarB);
            } while (SystemClock.uptimeMillis() - jUptimeMillis < this.f5650e);
            if (!sendMessage(obtainMessage())) {
                throw new EventBusException("Could not send handler message");
            }
            this.f5652g = true;
        } finally {
            this.f5652g = false;
        }
    }
}
