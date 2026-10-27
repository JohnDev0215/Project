package Q5;

import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import l0.InterfaceC1716g;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.u1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0626u1 implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7082m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7083n;
    public final /* synthetic */ Object o;

    public /* synthetic */ C0626u1(Object obj, Object obj2, Object obj3, int i7) {
        this.l = i7;
        this.f7082m = obj;
        this.f7083n = obj2;
        this.o = obj3;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                InterfaceC1716g.a((InterfaceC1716g) this.f7082m);
                ((InterfaceC2785n) this.f7083n).invoke((String) this.o, 0L);
                break;
            default:
                ((InterfaceC2782k) this.f7082m).b((StingrayKaraokeTabScreen) this.f7083n);
                T.X x8 = (T.X) this.o;
                if (((Boolean) x8.getValue()).booleanValue()) {
                    x8.setValue(Boolean.FALSE);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
