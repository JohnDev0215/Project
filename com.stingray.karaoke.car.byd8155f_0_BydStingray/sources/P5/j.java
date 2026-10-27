package P5;

import A.J;
import A7.F;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.AbstractC2737o;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class j extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5367m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f5368n;
    public final /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ k f5369p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ k f5370q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ l f5371r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(long j3, k kVar, k kVar2, l lVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = j3;
        this.f5369p = kVar;
        this.f5370q = kVar2;
        this.f5371r = lVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        k kVar = this.f5370q;
        l lVar = this.f5371r;
        j jVar = new j(this.o, this.f5369p, kVar, lVar, interfaceC2189c);
        jVar.f5368n = obj;
        return jVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((j) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r6v9, types: [java.lang.Iterable, java.lang.Object] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        y0.z zVar;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f5367m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            y0.z zVar2 = (y0.z) this.f5368n;
            F f10 = new F(this.f5371r, 3);
            this.f5368n = zVar2;
            this.f5367m = 1;
            Object objC = J.c(zVar2, this.o, f10, this);
            if (objC == enumC2303a) {
                return enumC2303a;
            }
            zVar = zVar2;
            obj = objC;
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            zVar = (y0.z) this.f5368n;
            AbstractC1386c.u(obj);
        }
        if (((Boolean) obj).booleanValue()) {
            for (y0.q qVar : zVar.f24110p.f24042D.f24065a) {
                if (AbstractC2737o.b(qVar)) {
                    qVar.a();
                }
            }
            this.f5369p.a();
        } else {
            this.f5370q.a();
        }
        return m7.q.f19267a;
    }
}
