package A;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.EnumC2731i;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class n1 extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f216m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f217n;
    public final /* synthetic */ InterfaceC0725w o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f218p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f219q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ A7.z f220r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ C0023k0 f221s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n1(InterfaceC0725w interfaceC0725w, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, A7.z zVar, C0023k0 c0023k0, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = interfaceC0725w;
        this.f218p = interfaceC2782k;
        this.f219q = interfaceC2782k2;
        this.f220r = zVar;
        this.f221s = c0023k0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        n1 n1Var = new n1(this.o, this.f218p, this.f219q, this.f220r, this.f221s, interfaceC2189c);
        n1Var.f217n = obj;
        return n1Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((n1) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f216m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            y0.z zVar = (y0.z) this.f217n;
            this.f216m = 1;
            obj = r1.e(zVar, EnumC2731i.f24067m, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        y0.q qVar = (y0.q) obj;
        m7.q qVar2 = m7.q.f19267a;
        C0023k0 c0023k0 = this.f221s;
        InterfaceC0725w interfaceC0725w = this.o;
        if (qVar != null) {
            qVar.a();
            AbstractC0727y.w(interfaceC0725w, null, null, new l1(c0023k0, null), 3);
            this.f218p.b(new m0.c(qVar.c));
            return qVar2;
        }
        AbstractC0727y.w(interfaceC0725w, null, null, new m1(c0023k0, null), 3);
        InterfaceC2782k interfaceC2782k = this.f219q;
        if (interfaceC2782k == null) {
            return null;
        }
        interfaceC2782k.b(new m0.c(((y0.q) this.f220r.l).c));
        return qVar2;
    }
}
