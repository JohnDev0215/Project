package Q5;

import T.C0738e0;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import i6.C1415d;
import n3.C1930E;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: Q5.n1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0599n1 implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1415d f6981m;

    public /* synthetic */ C0599n1(C1415d c1415d, int i7) {
        this.l = i7;
        this.f6981m = c1415d;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                C1415d c1415d = this.f6981m;
                C0738e0 c0738e0 = c1415d.f15769Q;
                Integer num = (Integer) c0738e0.getValue();
                A7.m.c(num);
                int iIntValue = num.intValue();
                Integer num2 = (Integer) c1415d.f15770R.getValue();
                A7.m.c(num2);
                int iIntValue2 = num2.intValue();
                Integer num3 = (Integer) c0738e0.getValue();
                A7.m.c(num3);
                n7.z.b(null, false, new b0.b(-1468995894, new C0579i1(iIntValue, iIntValue2, num3.intValue(), c1415d), true), 7);
                break;
            default:
                C1415d c1415d2 = this.f6981m;
                StingrayKaraokeTabScreen stingrayKaraokeTabScreen = StingrayKaraokeTabScreen.Homepage;
                W5.e eVar = c1415d2.f15759G;
                eVar.getClass();
                A7.m.f(stingrayKaraokeTabScreen, "tab");
                C1930E c1930e = (C1930E) eVar.d.get(NavigationType.MAIN);
                if (c1930e != null) {
                    c1930e.b(new W5.d(eVar, stingrayKaraokeTabScreen, false));
                }
                break;
        }
        return m7.q.f19267a;
    }
}
