package S5;

import D.L;
import T.C0761q;
import z7.InterfaceC2772a;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class d implements InterfaceC2787p {
    public final /* synthetic */ String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f7994m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f7995n;
    public final /* synthetic */ String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ String f7996p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f7997q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f7998r;

    public d(String str, String str2, String str3, String str4, String str5, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2) {
        this.l = str;
        this.f7994m = str2;
        this.f7995n = str3;
        this.o = str4;
        this.f7996p = str5;
        this.f7997q = interfaceC2772a;
        this.f7998r = interfaceC2772a2;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        int i7;
        g0.o oVar = (g0.o) obj;
        L l = (L) obj2;
        C0761q c0761q = (C0761q) obj3;
        int iIntValue = ((Number) obj4).intValue();
        A7.m.f(oVar, "modifier");
        A7.m.f(l, "contentPadding");
        if ((iIntValue & 6) == 0) {
            i7 = (c0761q.f(oVar) ? 4 : 2) | iIntValue;
        } else {
            i7 = iIntValue;
        }
        if ((iIntValue & 48) == 0) {
            i7 |= c0761q.f(l) ? 32 : 16;
        }
        if ((i7 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            com.bumptech.glide.d.e(oVar, this.l, this.f7994m, this.f7995n, this.o, this.f7996p, this.f7997q, this.f7998r, l, c0761q, (i7 & 14) | ((i7 << 21) & 234881024), 0);
        }
        return m7.q.f19267a;
    }
}
