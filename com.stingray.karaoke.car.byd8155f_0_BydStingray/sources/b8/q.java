package B8;

import B5.K;
import F8.AbstractC0287w;
import Q7.InterfaceC0657j;
import j8.G;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class q extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f797m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ s f798n;
    public final /* synthetic */ G o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ D8.t f799p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(s sVar, G g7, D8.t tVar, int i7) {
        super(0);
        this.f797m = i7;
        this.f798n = sVar;
        this.o = g7;
        this.f799p = tVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f797m) {
            case 0:
                s sVar = this.f798n;
                K kA = sVar.a((InterfaceC0657j) sVar.f805a.c);
                A7.m.c(kA);
                InterfaceC0089a interfaceC0089a = ((i) sVar.f805a.f779a).f764e;
                AbstractC0287w abstractC0287wP = this.f799p.p();
                A7.m.e(abstractC0287wP, "property.returnType");
                return (t8.g) interfaceC0089a.e(kA, this.o, abstractC0287wP);
            case 1:
                s sVar2 = this.f798n;
                E8.o oVar = ((i) sVar2.f805a.f779a).f762a;
                q qVar = new q(sVar2, this.o, this.f799p, 0);
                E8.l lVar = (E8.l) oVar;
                lVar.getClass();
                return new E8.h(lVar, qVar);
            case 2:
                s sVar3 = this.f798n;
                K kA2 = sVar3.a((InterfaceC0657j) sVar3.f805a.c);
                A7.m.c(kA2);
                InterfaceC0089a interfaceC0089a2 = ((i) sVar3.f805a.f779a).f764e;
                AbstractC0287w abstractC0287wP2 = this.f799p.p();
                A7.m.e(abstractC0287wP2, "property.returnType");
                return (t8.g) interfaceC0089a2.i(kA2, this.o, abstractC0287wP2);
            default:
                s sVar4 = this.f798n;
                E8.o oVar2 = ((i) sVar4.f805a.f779a).f762a;
                q qVar2 = new q(sVar4, this.o, this.f799p, 2);
                E8.l lVar2 = (E8.l) oVar2;
                lVar2.getClass();
                return new E8.h(lVar2, qVar2);
        }
    }
}
