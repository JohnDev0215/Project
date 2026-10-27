package A3;

import C0.C0102i;
import C0.InterfaceC0103j;
import S8.AbstractC0727y;
import S8.E;
import android.graphics.drawable.Drawable;
import coil.compose.AsyncImagePainter;
import coil.compose.AsyncImagePainter$State$Error;
import coil.compose.AsyncImagePainter$State$Success;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class h extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f388m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ AsyncImagePainter f389n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(AsyncImagePainter asyncImagePainter, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f389n = asyncImagePainter;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        h hVar = new h(this.f389n, interfaceC2189c);
        hVar.f388m = obj;
        return hVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((h) create((K3.i) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        AsyncImagePainter asyncImagePainter;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            K3.i iVar = (K3.i) this.f388m;
            AsyncImagePainter asyncImagePainter2 = this.f389n;
            z3.i iVar2 = (z3.i) asyncImagePainter2.f12633E.getValue();
            K3.h hVarA = K3.i.a(iVar);
            hVarA.d = new k(asyncImagePainter2);
            hVarA.o = null;
            hVarA.f3540p = null;
            hVarA.f3541q = null;
            K3.d dVar = iVar.f3562y;
            if (dVar.f3524a == null) {
                hVarA.f3538m = new k(asyncImagePainter2);
                hVarA.o = null;
                hVarA.f3540p = null;
                hVarA.f3541q = null;
            }
            if (dVar.f3525b == null) {
                InterfaceC0103j interfaceC0103j = asyncImagePainter2.f12643z;
                L3.e eVar = z.f409b;
                hVarA.f3539n = (A7.m.a(interfaceC0103j, C0102i.f903b) || A7.m.a(interfaceC0103j, C0102i.d)) ? L3.f.f4003m : L3.f.l;
            }
            if (dVar.d != L3.d.l) {
                hVarA.f3531e = L3.d.f4001m;
            }
            K3.i iVarA = hVarA.a();
            this.f388m = asyncImagePainter2;
            this.l = 1;
            iVar2.getClass();
            Z8.e eVar2 = E.f8084a;
            obj = AbstractC0727y.F(X8.m.f10573a.f8932q, new z3.e(iVar2, iVarA, null), this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
            asyncImagePainter = asyncImagePainter2;
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            asyncImagePainter = (AsyncImagePainter) this.f388m;
            AbstractC1386c.u(obj);
        }
        K3.j jVar = (K3.j) obj;
        asyncImagePainter.getClass();
        if (jVar instanceof K3.p) {
            K3.p pVar = (K3.p) jVar;
            return new AsyncImagePainter$State$Success(asyncImagePainter.j(pVar.f3578a), pVar);
        }
        if (!(jVar instanceof K3.e)) {
            throw new A9.k();
        }
        K3.e eVar3 = (K3.e) jVar;
        Drawable drawable = eVar3.f3526a;
        return new AsyncImagePainter$State$Error(drawable != null ? asyncImagePainter.j(drawable) : null, eVar3);
    }
}
