package defpackage;

import android.os.Looper;
import com.google.android.gms.location.LocationRequest;

/* JADX INFO: renamed from: Ri, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0313Ri extends AbstractC1778xi {
    public final /* synthetic */ LocationRequest r;
    public final /* synthetic */ InterfaceC1558ti s;
    public final /* synthetic */ Looper t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0313Ri(AbstractC0146Ic abstractC0146Ic, LocationRequest locationRequest, InterfaceC1558ti interfaceC1558ti, Looper looper) {
        super(abstractC0146Ic);
        this.r = locationRequest;
        this.s = interfaceC1558ti;
        this.t = looper;
    }

    @Override // defpackage.AbstractC0343Tc
    public final void i(InterfaceC1607uc interfaceC1607uc) {
        C0188Ki c0188Ki = (C0188Ki) interfaceC1607uc;
        BinderC1833yi binderC1833yi = new BinderC1833yi(this);
        LocationRequest locationRequest = this.r;
        InterfaceC1558ti interfaceC1558ti = this.s;
        Looper looperMyLooper = this.t;
        if (looperMyLooper == null) {
            Cif.i(Looper.myLooper() != null, "Can't create handler inside thread that has not called Looper.prepare()");
            looperMyLooper = Looper.myLooper();
        }
        String simpleName = InterfaceC1558ti.class.getSimpleName();
        Cif.g(interfaceC1558ti, "Listener must not be null");
        Cif.g(looperMyLooper, "Looper must not be null");
        Cif.g(simpleName, "Listener type must not be null");
        C0835gd c0835gd = new C0835gd(looperMyLooper, interfaceC1558ti, simpleName);
        synchronized (c0188Ki.E) {
            c0188Ki.E.b(locationRequest, c0835gd, binderC1833yi);
        }
    }
}
