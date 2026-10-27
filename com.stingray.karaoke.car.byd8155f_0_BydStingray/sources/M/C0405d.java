package M;

import F0.C0196c0;
import K.p0;
import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import android.view.View;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: M.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0405d extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f4130m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0196c0 f4131n;
    public final /* synthetic */ p0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ f f4132p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ v f4133q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0405d(C0196c0 c0196c0, p0 p0Var, f fVar, v vVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4131n = c0196c0;
        this.o = p0Var;
        this.f4132p = fVar;
        this.f4133q = vVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0405d c0405d = new C0405d(this.f4131n, this.o, this.f4132p, this.f4133q, interfaceC2189c);
        c0405d.f4130m = obj;
        return c0405d;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((C0405d) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        f fVar = this.f4132p;
        try {
            if (i7 != 0) {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
                throw new A9.k();
            }
            AbstractC1386c.u(obj);
            InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f4130m;
            x xVar = y.f4171a;
            C0196c0 c0196c0 = this.f4131n;
            View view = c0196c0.l;
            xVar.getClass();
            t tVar = new t(view);
            z zVar = new z(c0196c0.l, new C0404c(this.f4133q), tVar);
            if (L.e.f3874a) {
                AbstractC0727y.w(interfaceC0725w, null, null, new C0403b(fVar, tVar, null), 3);
            }
            p0 p0Var = this.o;
            if (p0Var != null) {
                p0Var.b(zVar);
            }
            fVar.c = zVar;
            this.l = 1;
            c0196c0.a(zVar, this);
            return enumC2303a;
        } catch (Throwable th) {
            fVar.c = null;
            throw th;
        }
    }
}
