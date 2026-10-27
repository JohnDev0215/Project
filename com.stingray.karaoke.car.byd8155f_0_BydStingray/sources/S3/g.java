package S3;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class g extends s7.i implements InterfaceC2782k {
    public final /* synthetic */ h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ O3.a f7900m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f7901n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f7902p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(h hVar, O3.a aVar, float f10, int i7, boolean z6, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.l = hVar;
        this.f7900m = aVar;
        this.f7901n = f10;
        this.o = i7;
        this.f7902p = z6;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) throws Throwable {
        g gVar = (g) create((InterfaceC2189c) obj);
        m7.q qVar = m7.q.f19267a;
        gVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new g(this.l, this.f7900m, this.f7901n, this.o, this.f7902p, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        h hVar = this.l;
        hVar.f7909t.setValue(this.f7900m);
        hVar.h(this.f7901n);
        hVar.g(this.o);
        hVar.l.setValue(Boolean.FALSE);
        if (this.f7902p) {
            hVar.f7912w.setValue(Long.MIN_VALUE);
        }
        return m7.q.f19267a;
    }
}
