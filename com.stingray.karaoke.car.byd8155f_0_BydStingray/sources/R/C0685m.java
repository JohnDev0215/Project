package R;

import S8.InterfaceC0725w;
import T.C0735d;
import V8.C0819h;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: R.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0685m extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f7307m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A7.o f7308n;
    public final /* synthetic */ s7.i o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public C0685m(InterfaceC2772a interfaceC2772a, InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f7308n = (A7.o) interfaceC2772a;
        this.o = (s7.i) interfaceC2785n;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r2v1, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0685m c0685m = new C0685m(this.f7308n, this.o, interfaceC2189c);
        c0685m.f7307m = obj;
        return c0685m;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0685m) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r5v0, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f7307m;
            A7.z zVar = new A7.z();
            C0819h c0819hW = C0735d.W(this.f7308n);
            C0684l c0684l = new C0684l(zVar, interfaceC0725w, (InterfaceC2785n) this.o);
            this.l = 1;
            if (c0819hW.b(c0684l, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }
}
