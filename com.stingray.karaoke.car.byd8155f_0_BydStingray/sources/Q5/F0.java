package Q5;

import A.C0050y0;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import c6.C1034a;
import com.stingray.karaoke.car.ui.navigation.NavigationRoutes;
import java.util.ArrayList;
import java.util.List;
import n3.C1928C;
import n3.C1930E;
import n3.C1940e;
import n3.C1941f;
import n7.AbstractC1999n;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class F0 implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f6341m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6342n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f6343p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f6344q;

    public /* synthetic */ F0(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i7) {
        this.l = i7;
        this.f6342n = obj;
        this.f6343p = obj2;
        this.f6344q = obj3;
        this.o = obj4;
        this.f6341m = obj5;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                E.k kVar = (E.k) obj;
                A7.m.f(kVar, "$this$LazyColumn");
                E.k.m(kVar, new b0.b(177335589, new G6.e((ArrayList) this.f6343p, 2, (InterfaceC2772a) this.f6344q), true));
                List list = (List) this.f6342n;
                kVar.n(list.size(), null, new C0558d0(2, list), new b0.b(-632812321, new I0(list, (InterfaceC2782k) this.o, (InterfaceC2785n) this.f6341m, 0), true));
                break;
            case 1:
                E.k kVar2 = (E.k) obj;
                A7.m.f(kVar2, "$this$LazyColumn");
                InterfaceC2785n interfaceC2785n = (InterfaceC2785n) this.f6341m;
                if (interfaceC2785n != null) {
                    E.k.m(kVar2, new b0.b(1181480113, new I5.y(interfaceC2785n, 15), true));
                }
                List list2 = (List) this.f6342n;
                kVar2.n(list2.size(), null, new C0558d0(4, list2), new b0.b(-632812321, new D2(list2, (String) this.f6343p, (InterfaceC2785n) this.f6344q, (InterfaceC2782k) this.o), true));
                break;
            case 2:
                C1928C c1928c = (C1928C) obj;
                A7.m.f(c1928c, "$this$NavHost");
                String value = NavigationRoutes.TABS.getValue();
                C1034a c1034a = (C1034a) this.o;
                f6.o oVar = (f6.o) this.f6341m;
                f6.h hVar = (f6.h) this.f6342n;
                h6.m.i(c1928c, value, null, new b0.b(1006862437, new W5.l(hVar, (f6.g) this.f6343p, (e6.h) this.f6344q, c1034a, oVar), true), 254);
                String strH = android.hardware.bydauto.ac.tsimpl.a.h(NavigationRoutes.SONG_DETAILS.getValue(), "/{id}?source={source}&source_type={source_type}");
                C0050y0 c0050y0 = (C0050y0) M5.b.m().f18608m;
                n3.K k10 = (n3.K) c0050y0.f301n;
                n3.K k11 = n3.M.f19839e;
                if (k10 == null) {
                    k10 = k11;
                }
                C1940e c1940e = new C1940e("id", new C1941f(k10, c0050y0.f300m));
                C0050y0 c0050y02 = (C0050y0) M5.b.m().f18608m;
                c0050y02.f300m = true;
                n3.K k12 = (n3.K) c0050y02.f301n;
                if (k12 == null) {
                    k12 = k11;
                }
                C1940e c1940e2 = new C1940e("source_type", new C1941f(k12, c0050y02.f300m));
                C0050y0 c0050y03 = (C0050y0) M5.b.m().f18608m;
                c0050y03.f300m = true;
                n3.K k13 = (n3.K) c0050y03.f301n;
                if (k13 == null) {
                    k13 = k11;
                }
                h6.m.i(c1928c, strH, AbstractC1999n.D(c1940e, c1940e2, new C1940e(BYDAutoMultimediaDevice.PARAM_SOURCE, new C1941f(k13, c0050y03.f300m))), new b0.b(-379114916, new U5.m(hVar, 1), true), 252);
                String strH2 = android.hardware.bydauto.ac.tsimpl.a.h(NavigationRoutes.SONGS.getValue(), "/{id}?source={source}&source_type={source_type}");
                C0050y0 c0050y04 = (C0050y0) M5.b.m().f18608m;
                n3.K k14 = (n3.K) c0050y04.f301n;
                if (k14 == null) {
                    k14 = k11;
                }
                C1940e c1940e3 = new C1940e("id", new C1941f(k14, c0050y04.f300m));
                C0050y0 c0050y05 = (C0050y0) M5.b.m().f18608m;
                c0050y05.f300m = true;
                n3.K k15 = (n3.K) c0050y05.f301n;
                if (k15 == null) {
                    k15 = k11;
                }
                C1940e c1940e4 = new C1940e(BYDAutoMultimediaDevice.PARAM_SOURCE, new C1941f(k15, c0050y05.f300m));
                C0050y0 c0050y06 = (C0050y0) M5.b.m().f18608m;
                c0050y06.f300m = true;
                n3.K k16 = (n3.K) c0050y06.f301n;
                if (k16 == null) {
                    k16 = k11;
                }
                h6.m.i(c1928c, strH2, AbstractC1999n.D(c1940e3, c1940e4, new C1940e("source_type", new C1941f(k16, c0050y06.f300m))), W5.i.f9828a, 252);
                String strH3 = android.hardware.bydauto.ac.tsimpl.a.h(NavigationRoutes.ARTISTS.getValue(), "/{id}?source={source}&source_type={source_type}");
                C0050y0 c0050y07 = (C0050y0) M5.b.m().f18608m;
                n3.K k17 = (n3.K) c0050y07.f301n;
                if (k17 == null) {
                    k17 = k11;
                }
                C1940e c1940e5 = new C1940e("id", new C1941f(k17, c0050y07.f300m));
                C0050y0 c0050y08 = (C0050y0) M5.b.m().f18608m;
                c0050y08.f300m = true;
                n3.K k18 = (n3.K) c0050y08.f301n;
                if (k18 == null) {
                    k18 = k11;
                }
                C1940e c1940e6 = new C1940e(BYDAutoMultimediaDevice.PARAM_SOURCE, new C1941f(k18, c0050y08.f300m));
                C0050y0 c0050y09 = (C0050y0) M5.b.m().f18608m;
                c0050y09.f300m = true;
                n3.K k19 = (n3.K) c0050y09.f301n;
                if (k19 != null) {
                    k11 = k19;
                }
                h6.m.i(c1928c, strH3, AbstractC1999n.D(c1940e5, c1940e6, new C1940e("source_type", new C1941f(k11, c0050y09.f300m))), W5.i.f9829b, 252);
                h6.m.i(c1928c, NavigationRoutes.SETTINGS.getValue(), null, W5.i.c, 254);
                h6.m.i(c1928c, NavigationRoutes.ABOUT.getValue(), null, W5.i.d, 254);
                h6.m.i(c1928c, NavigationRoutes.FAQ.getValue(), null, W5.i.f9830e, 254);
                h6.m.i(c1928c, NavigationRoutes.CONTACT_US.getValue(), null, W5.i.f9831f, 254);
                h6.m.i(c1928c, NavigationRoutes.DEV_SETTINGS.getValue(), null, W5.i.f9832g, 254);
                break;
            default:
                C1928C c1928c2 = (C1928C) obj;
                A7.m.f(c1928c2, "$this$NavHost");
                h6.m.i(c1928c2, "Homepage", null, new b0.b(276184715, new U5.m((f6.g) this.f6342n, 2), true), 254);
                h6.m.i(c1928c2, "ExplorePage", null, new b0.b(1958679092, new S5.n((e6.h) this.f6343p, 2, (C1930E) this.f6344q), true), 254);
                h6.m.i(c1928c2, "CompanionPage", null, new b0.b(1391767059, new U5.m((C1034a) this.o, 3), true), 254);
                h6.m.i(c1928c2, "MyKaraokePage", null, new b0.b(824855026, new U5.m((f6.o) this.f6341m, 4), true), 254);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ F0(InterfaceC2785n interfaceC2785n, List list, String str, InterfaceC2785n interfaceC2785n2, InterfaceC2782k interfaceC2782k) {
        this.l = 1;
        this.f6341m = interfaceC2785n;
        this.f6342n = list;
        this.f6343p = str;
        this.f6344q = interfaceC2785n2;
        this.o = interfaceC2782k;
    }
}
