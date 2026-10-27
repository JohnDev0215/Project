package N5;

import S8.InterfaceC0725w;
import com.stingray.karaoke.car.player.PlayerService;
import h8.AbstractC1386c;
import n2.C1923x;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0446b extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4680m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0449e f4681n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0446b(int i7, int i10, C0449e c0449e, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = i7;
        this.f4680m = i10;
        this.f4681n = c0449e;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0446b(this.l, this.f4680m, this.f4681n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C0446b c0446b = (C0446b) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0446b.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        long j3 = this.l - this.f4680m;
        A7.m.f("Adding " + j3 + " ms to the current media item", "text");
        f0 f0Var = (f0) this.f4681n.f4687b.f4637P.getValue();
        if (f0Var != null) {
            C1923x c1923x = PlayerService.f13008u;
            C1923x c1923x2 = PlayerService.f13008u;
            if (c1923x2 == null) {
                A7.m.j("player");
                throw null;
            }
            int iB0 = c1923x2.b0();
            C1923x c1923x3 = PlayerService.f13008u;
            if (c1923x3 == null) {
                A7.m.j("player");
                throw null;
            }
            long jN0 = c1923x3.N0();
            C8.d.q(iB0, C8.d.n(f0Var, j3));
            C1923x c1923x4 = PlayerService.f13008u;
            if (c1923x4 == null) {
                A7.m.j("player");
                throw null;
            }
            c1923x4.W0(c1923x4.b0(), jN0, false);
        }
        return m7.q.f19267a;
    }
}
