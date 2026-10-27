package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;
import java.util.Objects;

/* JADX INFO: renamed from: Bf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0023Bf extends AbstractC0435Ye implements Handler.Callback {
    public final HashMap c = new HashMap();
    public final Context d;
    public final Handler e;
    public final C0149If f;
    public final long g;
    public final long h;

    public C0023Bf(Context context) {
        this.d = context.getApplicationContext();
        this.e = new HandlerC0347Tg(context.getMainLooper(), this);
        if (C0149If.b == null) {
            synchronized (C0149If.a) {
                if (C0149If.b == null) {
                    C0149If.b = new C0149If();
                }
            }
        }
        this.f = C0149If.b;
        this.g = 5000L;
        this.h = 300000L;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            synchronized (this.c) {
                C0417Xe c0417Xe = (C0417Xe) message.obj;
                ServiceConnectionC0005Af serviceConnectionC0005Af = (ServiceConnectionC0005Af) this.c.get(c0417Xe);
                if (serviceConnectionC0005Af != null && serviceConnectionC0005Af.a.isEmpty()) {
                    if (serviceConnectionC0005Af.c) {
                        serviceConnectionC0005Af.g.e.removeMessages(1, serviceConnectionC0005Af.e);
                        C0023Bf c0023Bf = serviceConnectionC0005Af.g;
                        C0149If c0149If = c0023Bf.f;
                        Context context = c0023Bf.d;
                        Objects.requireNonNull(c0149If);
                        context.unbindService(serviceConnectionC0005Af);
                        serviceConnectionC0005Af.c = false;
                        serviceConnectionC0005Af.b = 2;
                    }
                    this.c.remove(c0417Xe);
                }
            }
            return true;
        }
        if (i != 1) {
            return false;
        }
        synchronized (this.c) {
            C0417Xe c0417Xe2 = (C0417Xe) message.obj;
            ServiceConnectionC0005Af serviceConnectionC0005Af2 = (ServiceConnectionC0005Af) this.c.get(c0417Xe2);
            if (serviceConnectionC0005Af2 != null && serviceConnectionC0005Af2.b == 3) {
                String strValueOf = String.valueOf(c0417Xe2);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 47);
                sb.append("Timeout waiting for ServiceConnection callback ");
                sb.append(strValueOf);
                Log.e("GmsClientSupervisor", sb.toString(), new Exception());
                ComponentName componentName = serviceConnectionC0005Af2.f;
                if (componentName == null) {
                    Objects.requireNonNull(c0417Xe2);
                    componentName = null;
                }
                if (componentName == null) {
                    componentName = new ComponentName(c0417Xe2.b, "unknown");
                }
                serviceConnectionC0005Af2.onServiceDisconnected(componentName);
            }
        }
        return true;
    }
}
