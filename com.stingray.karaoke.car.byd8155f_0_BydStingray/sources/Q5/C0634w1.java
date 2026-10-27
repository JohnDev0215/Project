package Q5;

import T.C0735d;
import T.C0761q;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.w1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0634w1 implements InterfaceC2785n {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f7108m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f7109n;
    public final /* synthetic */ b6.g o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ D.L f7110p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f7111q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f7112r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f7113s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Serializable f7114t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ m7.c f7115u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ Object f7116v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ Object f7117w;

    public /* synthetic */ C0634w1(g0.o oVar, Integer num, ArrayList arrayList, boolean z6, InterfaceC2772a interfaceC2772a, InterfaceC2785n interfaceC2785n, b6.g gVar, Integer num2, C0612q2 c0612q2, D.L l, int i7) {
        this.f7112r = oVar;
        this.f7113s = num;
        this.f7114t = arrayList;
        this.f7108m = z6;
        this.f7115u = interfaceC2772a;
        this.f7109n = interfaceC2785n;
        this.o = gVar;
        this.f7116v = num2;
        this.f7117w = c0612q2;
        this.f7110p = l;
        this.f7111q = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).intValue();
                com.bumptech.glide.c.n((String) this.f7112r, (List) this.f7113s, (LoadingInterface$LoadingState) this.f7114t, this.f7109n, (InterfaceC2785n) this.f7115u, (InterfaceC2782k) this.f7117w, (InterfaceC2785n) this.f7116v, this.o, this.f7110p, this.f7108m, (C0761q) obj, C0735d.Z(this.f7111q | 1));
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f7111q | 1);
                ArrayList arrayList = (ArrayList) this.f7114t;
                C0612q2 c0612q2 = (C0612q2) this.f7117w;
                D.L l = this.f7110p;
                w0.c.A((g0.o) this.f7112r, (Integer) this.f7113s, arrayList, this.f7108m, (InterfaceC2772a) this.f7115u, this.f7109n, this.o, (Integer) this.f7116v, c0612q2, l, (C0761q) obj, iZ);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ C0634w1(String str, List list, LoadingInterface$LoadingState loadingInterface$LoadingState, InterfaceC2785n interfaceC2785n, InterfaceC2785n interfaceC2785n2, InterfaceC2782k interfaceC2782k, InterfaceC2785n interfaceC2785n3, b6.g gVar, D.L l, boolean z6, int i7) {
        this.f7112r = str;
        this.f7113s = list;
        this.f7114t = loadingInterface$LoadingState;
        this.f7109n = interfaceC2785n;
        this.f7115u = interfaceC2785n2;
        this.f7117w = interfaceC2782k;
        this.f7116v = interfaceC2785n3;
        this.o = gVar;
        this.f7110p = l;
        this.f7108m = z6;
        this.f7111q = i7;
    }
}
