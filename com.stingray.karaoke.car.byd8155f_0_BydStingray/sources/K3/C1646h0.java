package k3;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import h2.AbstractC1346c;
import java.util.List;
import l3.BinderC1769z;
import l3.C1737B;
import l3.C1741F;
import l3.C1767x;
import l3.HandlerC1768y;

/* JADX INFO: renamed from: k3.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1646h0 implements IBinder.DeathRecipient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1767x f17963a = new C1767x(this);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public HandlerC1768y f17964b;
    public BinderC1769z c;
    public final Handler d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ C1650j0 f17965e;

    public C1646h0(C1650j0 c1650j0, Looper looper) {
        this.f17965e = c1650j0;
        this.d = new Handler(looper, new h2.k(this, 2));
    }

    public final void a(C1737B c1737b) {
        C1650j0 c1650j0 = this.f17965e;
        C1648i0 c1648i0 = c1650j0.f18008m;
        int i7 = c1648i0.c;
        c1650j0.f18008m = new C1648i0(c1737b, (l3.X) c1648i0.f17994e, (C1741F) c1648i0.f17995f, c1648i0.f17992a, (CharSequence) c1648i0.f17996g, c1648i0.f17993b, i7, (Bundle) c1648i0.f17997h);
        k();
    }

    public final void b(boolean z6) {
        C1672v c1672v = this.f17965e.f18000b;
        c1672v.getClass();
        AbstractC1346c.j(Looper.myLooper() == c1672v.f18137e.getLooper());
        new Bundle().putBoolean("androidx.media3.session.ARGUMENT_CAPTIONING_ENABLED", z6);
        new y1("androidx.media3.session.SESSION_COMMAND_ON_CAPTIONING_ENABLED_CHANGED", Bundle.EMPTY);
        c1672v.d.getClass();
        InterfaceC1668t.c();
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        i(8, null, null);
    }

    public final void c(Bundle bundle) {
        C1650j0 c1650j0 = this.f17965e;
        H0.b bVar = c1650j0.f18009n;
        c1650j0.f18009n = new H0.b((q1) bVar.f2765m, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, bundle, (A1) null);
        C1672v c1672v = c1650j0.f18000b;
        c1672v.getClass();
        AbstractC1346c.j(Looper.myLooper() == c1672v.f18137e.getLooper());
        C1672v c1672v2 = c1650j0.f18000b;
        c1672v.d.getClass();
    }

    public final void d(C1741F c1741f) {
        C1650j0 c1650j0 = this.f17965e;
        C1648i0 c1648i0 = c1650j0.f18008m;
        int i7 = c1648i0.c;
        c1650j0.f18008m = new C1648i0((C1737B) c1648i0.d, (l3.X) c1648i0.f17994e, c1741f, c1648i0.f17992a, (CharSequence) c1648i0.f17996g, c1648i0.f17993b, i7, (Bundle) c1648i0.f17997h);
        k();
    }

    public final void e(l3.X x8) {
        C1650j0 c1650j0 = this.f17965e;
        C1648i0 c1648i0 = c1650j0.f18008m;
        l3.X xW0 = C1650j0.W0(x8);
        int i7 = c1648i0.c;
        c1650j0.f18008m = new C1648i0((C1737B) c1648i0.d, xW0, (C1741F) c1648i0.f17995f, c1648i0.f17992a, (CharSequence) c1648i0.f17996g, c1648i0.f17993b, i7, (Bundle) c1648i0.f17997h);
        k();
    }

    public final void f(List list) {
        C1650j0 c1650j0 = this.f17965e;
        C1648i0 c1648i0 = c1650j0.f18008m;
        List listV0 = C1650j0.V0(list);
        int i7 = c1648i0.c;
        c1650j0.f18008m = new C1648i0((C1737B) c1648i0.d, (l3.X) c1648i0.f17994e, (C1741F) c1648i0.f17995f, listV0, (CharSequence) c1648i0.f17996g, c1648i0.f17993b, i7, (Bundle) c1648i0.f17997h);
        k();
    }

    public final void g(CharSequence charSequence) {
        C1650j0 c1650j0 = this.f17965e;
        C1648i0 c1648i0 = c1650j0.f18008m;
        int i7 = c1648i0.c;
        c1650j0.f18008m = new C1648i0((C1737B) c1648i0.d, (l3.X) c1648i0.f17994e, (C1741F) c1648i0.f17995f, c1648i0.f17992a, charSequence, c1648i0.f17993b, i7, (Bundle) c1648i0.f17997h);
        k();
    }

    public final void h(String str, Bundle bundle) {
        if (str == null) {
            return;
        }
        C1672v c1672v = this.f17965e.f18000b;
        c1672v.getClass();
        AbstractC1346c.j(Looper.myLooper() == c1672v.f18137e.getLooper());
        new y1(str, Bundle.EMPTY);
        c1672v.d.getClass();
        InterfaceC1668t.c();
    }

    public final void i(int i7, Object obj, Bundle bundle) {
        HandlerC1768y handlerC1768y = this.f17964b;
        if (handlerC1768y != null) {
            Message messageObtainMessage = handlerC1768y.obtainMessage(i7, obj);
            if (bundle != null) {
                messageObtainMessage.setData(bundle);
            }
            messageObtainMessage.sendToTarget();
        }
    }

    public final void j(Handler handler) {
        if (handler != null) {
            HandlerC1768y handlerC1768y = new HandlerC1768y(this, handler.getLooper());
            this.f17964b = handlerC1768y;
            handlerC1768y.f18619b = true;
        } else {
            HandlerC1768y handlerC1768y2 = this.f17964b;
            if (handlerC1768y2 != null) {
                handlerC1768y2.f18619b = false;
                handlerC1768y2.removeCallbacksAndMessages(null);
                this.f17964b = null;
            }
        }
    }

    public final void k() {
        Handler handler = this.d;
        if (handler.hasMessages(1)) {
            return;
        }
        handler.sendEmptyMessageDelayed(1, 500L);
    }
}
