package i;

import A7.v;
import N1.C0435p;
import S8.InterfaceC0725w;
import U8.l;
import V8.C0813b;
import V8.C0830t;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class f extends i implements InterfaceC2785n {
    public v l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f15642m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ g f15643n;
    public final /* synthetic */ InterfaceC2785n o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ t4.e f15644p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(g gVar, InterfaceC2785n interfaceC2785n, t4.e eVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f15643n = gVar;
        this.o = interfaceC2785n;
        this.f15644p = eVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new f(this.f15643n, this.o, this.f15644p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((f) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        v vVar;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f15642m;
        boolean z6 = true;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            if (this.f15643n.f15131a) {
                v vVar2 = new v();
                C0830t c0830t = new C0830t(new C0813b((l) this.f15644p.f22463n, z6), new C0435p(2, vVar2, null));
                this.l = vVar2;
                this.f15642m = 1;
                if (this.o.invoke(c0830t, this) == enumC2303a) {
                    return enumC2303a;
                }
                vVar = vVar2;
            }
            return q.f19267a;
        }
        if (i7 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        vVar = this.l;
        AbstractC1386c.u(obj);
        if (!vVar.l) {
            throw new IllegalStateException("You must collect the progress flow");
        }
        return q.f19267a;
    }
}
