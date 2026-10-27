package defpackage;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.location.internal.zzbj;

/* JADX INFO: renamed from: wi, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1723wi extends AbstractC1778xi {
    public final /* synthetic */ InterfaceC1558ti r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1723wi(AbstractC0146Ic abstractC0146Ic, InterfaceC1558ti interfaceC1558ti) {
        super(abstractC0146Ic);
        this.r = interfaceC1558ti;
    }

    @Override // defpackage.AbstractC0343Tc
    public final void i(InterfaceC1607uc interfaceC1607uc) {
        C0188Ki c0188Ki = (C0188Ki) interfaceC1607uc;
        InterfaceC1558ti interfaceC1558ti = this.r;
        String simpleName = InterfaceC1558ti.class.getSimpleName();
        Cif.g(interfaceC1558ti, "Listener must not be null");
        Cif.g(simpleName, "Listener type must not be null");
        if (TextUtils.isEmpty(simpleName)) {
            throw new IllegalArgumentException("Listener type must not be empty");
        }
        C0668dd c0668dd = new C0668dd(interfaceC1558ti, simpleName);
        BinderC1833yi binderC1833yi = new BinderC1833yi(this);
        C0116Gi c0116Gi = c0188Ki.E;
        ((C0277Pi) c0116Gi.a).a();
        Cif.g(c0668dd, "Invalid null listener key");
        synchronized (c0116Gi.c) {
            BinderC0170Ji binderC0170Ji = (BinderC0170Ji) c0116Gi.c.remove(c0668dd);
            if (binderC0170Ji != null) {
                synchronized (binderC0170Ji) {
                    C0835gd c0835gd = binderC0170Ji.a;
                    c0835gd.b = null;
                    c0835gd.c = null;
                }
                InterfaceC0080Ei interfaceC0080Ei = (InterfaceC0080Ei) ((C0277Pi) c0116Gi.a).b();
                zzbj zzbjVarB = zzbj.b(binderC0170Ji, binderC1833yi);
                C0062Di c0062Di = (C0062Di) interfaceC0080Ei;
                Parcel parcelF = c0062Di.f();
                AbstractC0383Vg.d(parcelF, zzbjVarB);
                c0062Di.d(59, parcelF);
            }
        }
    }
}
