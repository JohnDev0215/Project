package U5;

import D.L;
import T.C0735d;
import T.C0761q;
import c6.C1034a;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import java.util.List;
import n3.C1930E;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class v implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f9203m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9204n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f9205p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f9206q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f9207r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f9208s;

    public /* synthetic */ v(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, int i7, int i10) {
        this.l = i10;
        this.f9204n = obj;
        this.o = obj2;
        this.f9205p = obj3;
        this.f9206q = obj4;
        this.f9207r = obj5;
        this.f9208s = obj6;
        this.f9203m = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f9203m | 1);
                List list = (List) this.f9205p;
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.f9207r;
                L l = (L) this.f9208s;
                l9.d.q((g0.o) this.f9204n, (InterfaceC2782k) this.o, list, (InterfaceC2772a) this.f9206q, interfaceC2772a, l, (C0761q) obj, iZ);
                break;
            default:
                ((Integer) obj2).intValue();
                P8.n.h((C1930E) this.f9204n, (StingrayKaraokeTabScreen) this.o, (f6.g) this.f9205p, (e6.h) this.f9206q, (C1034a) this.f9207r, (f6.o) this.f9208s, (C0761q) obj, C0735d.Z(this.f9203m | 1));
                break;
        }
        return m7.q.f19267a;
    }
}
