package V8;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class I extends s7.i implements InterfaceC2786o {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f9573m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ InterfaceC0818g f9574n;
    public /* synthetic */ Object[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ s7.i f9575p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public I(InterfaceC2189c interfaceC2189c, InterfaceC2787p interfaceC2787p) {
        super(3, interfaceC2189c);
        this.f9575p = (s7.i) interfaceC2787p;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [s7.i, z7.p] */
    /* JADX WARN: Type inference failed for: r1v4, types: [s7.i, z7.o] */
    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        InterfaceC0818g interfaceC0818g = (InterfaceC0818g) obj;
        Object[] objArr = (Object[]) obj2;
        InterfaceC2189c interfaceC2189c = (InterfaceC2189c) obj3;
        switch (this.l) {
            case 0:
                I i7 = new I(interfaceC2189c, (InterfaceC2787p) this.f9575p);
                i7.f9574n = interfaceC0818g;
                i7.o = objArr;
                return i7.invokeSuspend(m7.q.f19267a);
            default:
                I i10 = new I((InterfaceC2786o) this.f9575p, interfaceC2189c);
                i10.f9574n = interfaceC0818g;
                i10.o = objArr;
                return i10.invokeSuspend(m7.q.f19267a);
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [s7.i, z7.p] */
    /* JADX WARN: Type inference failed for: r3v4, types: [s7.i, z7.o] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        InterfaceC0818g interfaceC0818g;
        InterfaceC0818g interfaceC0818g2;
        switch (this.l) {
            case 0:
                EnumC2303a enumC2303a = EnumC2303a.l;
                int i7 = this.f9573m;
                if (i7 == 0) {
                    AbstractC1386c.u(obj);
                    interfaceC0818g = this.f9574n;
                    Object[] objArr = this.o;
                    Object obj2 = objArr[0];
                    Object obj3 = objArr[1];
                    Object obj4 = objArr[2];
                    this.f9574n = interfaceC0818g;
                    this.f9573m = 1;
                    obj = this.f9575p.o(obj2, obj3, obj4, this);
                    if (obj == enumC2303a) {
                        return enumC2303a;
                    }
                } else {
                    if (i7 != 1) {
                        if (i7 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        AbstractC1386c.u(obj);
                        return m7.q.f19267a;
                    }
                    interfaceC0818g = this.f9574n;
                    AbstractC1386c.u(obj);
                }
                this.f9574n = null;
                this.f9573m = 2;
                if (interfaceC0818g.f(obj, this) == enumC2303a) {
                    return enumC2303a;
                }
                return m7.q.f19267a;
            default:
                EnumC2303a enumC2303a2 = EnumC2303a.l;
                int i10 = this.f9573m;
                if (i10 == 0) {
                    AbstractC1386c.u(obj);
                    interfaceC0818g2 = this.f9574n;
                    Object[] objArr2 = this.o;
                    Object obj5 = objArr2[0];
                    Object obj6 = objArr2[1];
                    this.f9574n = interfaceC0818g2;
                    this.f9573m = 1;
                    obj = this.f9575p.g(obj5, obj6, this);
                    if (obj == enumC2303a2) {
                        return enumC2303a2;
                    }
                } else {
                    if (i10 != 1) {
                        if (i10 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        AbstractC1386c.u(obj);
                        return m7.q.f19267a;
                    }
                    interfaceC0818g2 = this.f9574n;
                    AbstractC1386c.u(obj);
                }
                this.f9574n = null;
                this.f9573m = 2;
                if (interfaceC0818g2.f(obj, this) == enumC2303a2) {
                    return enumC2303a2;
                }
                return m7.q.f19267a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public I(InterfaceC2786o interfaceC2786o, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.f9575p = (s7.i) interfaceC2786o;
    }
}
