package a2;

import S8.AbstractC0727y;
import T.C0748j0;
import V8.d0;
import androidx.lifecycle.EnumC0981t;
import androidx.lifecycle.T;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: a2.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0909c extends i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f11049m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A9.b f11050n;
    public final /* synthetic */ EnumC0981t o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2194h f11051p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ d0 f11052q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0909c(A9.b bVar, EnumC0981t enumC0981t, InterfaceC2194h interfaceC2194h, d0 d0Var, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f11050n = bVar;
        this.o = enumC0981t;
        this.f11051p = interfaceC2194h;
        this.f11052q = d0Var;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0909c c0909c = new C0909c(this.f11050n, this.o, this.f11051p, this.f11052q, interfaceC2189c);
        c0909c.f11049m = obj;
        return c0909c;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0909c) create((C0748j0) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objH;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        q qVar = q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0908b c0908b = new C0908b(this.f11051p, this.f11052q, (C0748j0) this.f11049m, null);
            this.l = 1;
            EnumC0981t enumC0981t = EnumC0981t.f12005m;
            EnumC0981t enumC0981t2 = this.o;
            if (enumC0981t2 == enumC0981t) {
                throw new IllegalArgumentException("repeatOnLifecycle cannot start work with the INITIALIZED lifecycle state.");
            }
            A9.b bVar = this.f11050n;
            if (bVar.O0() == EnumC0981t.l || (objH = AbstractC0727y.h(new T(bVar, enumC0981t2, c0908b, null), this)) != enumC2303a) {
                objH = qVar;
            }
            if (objH == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return qVar;
    }
}
