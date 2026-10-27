package P5;

import T.C0735d;
import V8.C0819h;
import V8.InterfaceC0818g;
import V8.h0;
import X5.C0870l0;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class v extends s7.i implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5402m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ InterfaceC0818g f5403n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f5404p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v(int i7, Object obj, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.l = i7;
        this.f5404p = obj;
    }

    /* JADX WARN: Type inference failed for: r2v6, types: [s7.i, z7.n] */
    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        InterfaceC0818g interfaceC0818g = (InterfaceC0818g) obj;
        switch (this.l) {
            case 0:
                int i7 = 0;
                v vVar = new v(i7, (p) this.f5404p, (InterfaceC2189c) obj3);
                vVar.f5403n = interfaceC0818g;
                vVar.o = obj2;
                return vVar.invokeSuspend(m7.q.f19267a);
            case 1:
                v vVar2 = new v((s7.i) this.f5404p, (InterfaceC2189c) obj3);
                vVar2.f5403n = interfaceC0818g;
                vVar2.o = obj2;
                return vVar2.invokeSuspend(m7.q.f19267a);
            default:
                int i10 = 2;
                v vVar3 = new v(i10, (C0870l0) this.f5404p, (InterfaceC2189c) obj3);
                vVar3.f5403n = interfaceC0818g;
                vVar3.o = (Object[]) obj2;
                return vVar3.invokeSuspend(m7.q.f19267a);
        }
    }

    /* JADX WARN: Type inference failed for: r3v4, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        InterfaceC0818g interfaceC0818g;
        InterfaceC0818g interfaceC0818g2;
        switch (this.l) {
            case 0:
                EnumC2303a enumC2303a = EnumC2303a.l;
                int i7 = this.f5402m;
                m7.q qVar = m7.q.f19267a;
                if (i7 == 0) {
                    AbstractC1386c.u(obj);
                    InterfaceC0818g interfaceC0818g3 = this.f5403n;
                    C0819h c0819hW = ((Boolean) this.o).booleanValue() ? C0735d.W(new w((p) this.f5404p, 0)) : new C0819h(null, 0);
                    this.f5402m = 1;
                    if (interfaceC0818g3 instanceof h0) {
                        throw ((h0) interfaceC0818g3).l;
                    }
                    Object objB = c0819hW.b(interfaceC0818g3, this);
                    if (objB != enumC2303a) {
                        objB = qVar;
                    }
                    if (objB == enumC2303a) {
                        return enumC2303a;
                    }
                } else {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    AbstractC1386c.u(obj);
                }
                return qVar;
            case 1:
                EnumC2303a enumC2303a2 = EnumC2303a.l;
                int i10 = this.f5402m;
                if (i10 == 0) {
                    AbstractC1386c.u(obj);
                    interfaceC0818g = this.f5403n;
                    Object obj2 = this.o;
                    this.f5403n = interfaceC0818g;
                    this.f5402m = 1;
                    obj = ((s7.i) this.f5404p).invoke(obj2, this);
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
                    interfaceC0818g = this.f5403n;
                    AbstractC1386c.u(obj);
                }
                this.f5403n = null;
                this.f5402m = 2;
                if (interfaceC0818g.f(obj, this) == enumC2303a2) {
                    return enumC2303a2;
                }
                return m7.q.f19267a;
            default:
                EnumC2303a enumC2303a3 = EnumC2303a.l;
                int i11 = this.f5402m;
                if (i11 == 0) {
                    AbstractC1386c.u(obj);
                    interfaceC0818g2 = this.f5403n;
                    Object[] objArr = (Object[]) this.o;
                    Object obj3 = objArr[0];
                    Object obj4 = objArr[1];
                    Object obj5 = objArr[2];
                    Object obj6 = objArr[3];
                    this.f5403n = interfaceC0818g2;
                    this.f5402m = 1;
                    obj = ((C0870l0) this.f5404p).e(obj3, obj4, obj5, obj6, this);
                    if (obj == enumC2303a3) {
                        return enumC2303a3;
                    }
                } else {
                    if (i11 != 1) {
                        if (i11 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        AbstractC1386c.u(obj);
                        return m7.q.f19267a;
                    }
                    interfaceC0818g2 = this.f5403n;
                    AbstractC1386c.u(obj);
                }
                this.f5403n = null;
                this.f5402m = 2;
                if (interfaceC0818g2.f(obj, this) == enumC2303a3) {
                    return enumC2303a3;
                }
                return m7.q.f19267a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public v(InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.l = 1;
        this.f5404p = (s7.i) interfaceC2785n;
    }
}
