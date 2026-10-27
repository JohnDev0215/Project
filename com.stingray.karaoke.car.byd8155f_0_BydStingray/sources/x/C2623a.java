package x;

import A.C0032p;
import h8.AbstractC1386c;
import java.util.concurrent.CancellationException;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: x.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2623a extends s7.i implements InterfaceC2782k {
    public C2648n l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public A7.v f23473m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f23474n;
    public final /* synthetic */ C2629d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f23475p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ p0 f23476q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ long f23477r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f23478s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2623a(C2629d c2629d, Object obj, p0 p0Var, long j3, InterfaceC2782k interfaceC2782k, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.o = c2629d;
        this.f23475p = obj;
        this.f23476q = p0Var;
        this.f23477r = j3;
        this.f23478s = interfaceC2782k;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((C2623a) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C2623a(this.o, this.f23475p, this.f23476q, this.f23477r, this.f23478s, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        A7.v vVar;
        C2648n c2648n;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f23474n;
        int i10 = 1;
        C2629d c2629d = this.o;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                c2629d.c.f23570n = (AbstractC2652s) c2629d.f23491a.f23394a.b(this.f23475p);
                p0 p0Var = this.f23476q;
                c2629d.f23493e.setValue(p0Var.c);
                c2629d.d.setValue(Boolean.TRUE);
                C2648n c2648n2 = c2629d.c;
                C2648n c2648n3 = new C2648n(c2648n2.l, c2648n2.f23569m.getValue(), AbstractC2631e.i(c2648n2.f23570n), c2648n2.o, Long.MIN_VALUE, c2648n2.f23572q);
                A7.v vVar2 = new A7.v();
                long j3 = this.f23477r;
                C0032p c0032p = new C0032p(c2629d, c2648n3, this.f23478s, vVar2, 5);
                this.l = c2648n3;
                this.f23473m = vVar2;
                this.f23474n = 1;
                if (AbstractC2631e.d(c2648n3, p0Var, j3, c0032p, this) == enumC2303a) {
                    return enumC2303a;
                }
                vVar = vVar2;
                c2648n = c2648n3;
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                vVar = this.f23473m;
                c2648n = this.l;
                AbstractC1386c.u(obj);
            }
            if (!vVar.l) {
                i10 = 2;
            }
            C2629d.b(c2629d);
            return new C2643k(i10, c2648n);
        } catch (CancellationException e10) {
            C2629d.b(c2629d);
            throw e10;
        }
    }
}
