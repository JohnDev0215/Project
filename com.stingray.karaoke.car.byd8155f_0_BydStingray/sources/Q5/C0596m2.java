package Q5;

import E.C0147d;
import T.C0761q;
import com.stingray.karaoke.car.player.PlayerStatus;
import java.util.List;
import z7.InterfaceC2782k;
import z7.InterfaceC2787p;

/* JADX INFO: renamed from: Q5.m2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0596m2 extends A7.o implements InterfaceC2787p {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ List f6960m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ P5.p f6961n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6962p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6963q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ PlayerStatus f6964r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0596m2(List list, P5.p pVar, int i7, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, PlayerStatus playerStatus) {
        super(4);
        this.f6960m = list;
        this.f6961n = pVar;
        this.o = i7;
        this.f6962p = interfaceC2782k;
        this.f6963q = interfaceC2782k2;
        this.f6964r = playerStatus;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        int i7;
        C0147d c0147d = (C0147d) obj;
        int iIntValue = ((Number) obj2).intValue();
        C0761q c0761q = (C0761q) obj3;
        int iIntValue2 = ((Number) obj4).intValue();
        if ((iIntValue2 & 6) == 0) {
            i7 = (c0761q.f(c0147d) ? 4 : 2) | iIntValue2;
        } else {
            i7 = iIntValue2;
        }
        if ((iIntValue2 & 48) == 0) {
            i7 |= c0761q.d(iIntValue) ? 32 : 16;
        }
        if ((i7 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            N5.f0 f0Var = (N5.f0) this.f6960m.get(iIntValue);
            c0761q.Q(905817556);
            String str = f0Var.d;
            P5.p pVar = this.f6961n;
            H7.H.g(c0147d, pVar, str, null, null, false, b0.g.b(1634569606, new C0592l2(f0Var, this.o, iIntValue, this.f6962p, this.f6963q, pVar, this.f6964r), c0761q), c0761q, (i7 & 14) | 1572864);
            c0761q.p(false);
        }
        return m7.q.f19267a;
    }
}
