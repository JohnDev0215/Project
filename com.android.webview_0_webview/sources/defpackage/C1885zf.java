package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: zf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1885zf implements Handler.Callback {
    public final InterfaceC1830yf a;
    public final Handler h;
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public final ArrayList d = new ArrayList();
    public volatile boolean e = false;
    public final AtomicInteger f = new AtomicInteger(0);
    public boolean g = false;
    public final Object i = new Object();

    public C1885zf(Looper looper, InterfaceC1830yf interfaceC1830yf) {
        this.a = interfaceC1830yf;
        this.h = new HandlerC0347Tg(looper, this);
    }

    public final void a(InterfaceC0128Hc interfaceC0128Hc) {
        Cif.f(interfaceC0128Hc);
        synchronized (this.i) {
            if (this.d.contains(interfaceC0128Hc)) {
                String strValueOf = String.valueOf(interfaceC0128Hc);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 67);
                sb.append("registerConnectionFailedListener(): listener ");
                sb.append(strValueOf);
                sb.append(" is already registered");
                Log.w("GmsClientEvents", sb.toString());
            } else {
                this.d.add(interfaceC0128Hc);
            }
        }
    }

    public final void b() {
        this.e = false;
        this.f.incrementAndGet();
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i != 1) {
            StringBuilder sb = new StringBuilder(45);
            sb.append("Don't know how to handle message: ");
            sb.append(i);
            Log.wtf("GmsClientEvents", sb.toString(), new Exception());
            return false;
        }
        InterfaceC0110Gc interfaceC0110Gc = (InterfaceC0110Gc) message.obj;
        synchronized (this.i) {
            if (this.e && this.a.a() && this.b.contains(interfaceC0110Gc)) {
                interfaceC0110Gc.f(this.a.b());
            }
        }
        return true;
    }
}
