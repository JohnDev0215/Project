package N1;

import h8.AbstractC1386c;
import java.io.Serializable;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class G extends s7.i implements InterfaceC2782k {
    public Serializable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4466m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A7.z f4467n;
    public final /* synthetic */ Q o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ A7.x f4468p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public G(A7.z zVar, Q q4, A7.x xVar, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f4467n = zVar;
        this.o = q4;
        this.f4468p = xVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((G) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new G(this.f4467n, this.o, this.f4468p, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        A7.z zVar;
        A7.x xVar;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f4466m;
        A7.x xVar2 = this.f4468p;
        A7.z zVar2 = this.f4467n;
        Q q4 = this.o;
        try {
        } catch (C0422c unused) {
            Object obj2 = zVar2.l;
            this.l = xVar2;
            this.f4466m = 3;
            obj = q4.i(obj2, true, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        }
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            this.l = zVar2;
            this.f4466m = 1;
            obj = q4.h(this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
            zVar = zVar2;
        } else {
            if (i7 != 1) {
                if (i7 == 2) {
                    xVar = (A7.x) this.l;
                    AbstractC1386c.u(obj);
                    xVar.l = ((Number) obj).intValue();
                    return m7.q.f19267a;
                }
                if (i7 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                xVar2 = (A7.x) this.l;
                AbstractC1386c.u(obj);
                xVar2.l = ((Number) obj).intValue();
                return m7.q.f19267a;
            }
            zVar = (A7.z) this.l;
            AbstractC1386c.u(obj);
        }
        zVar.l = obj;
        b0 b0VarF = q4.f();
        this.l = xVar2;
        this.f4466m = 2;
        obj = b0VarF.a();
        if (obj == enumC2303a) {
            return enumC2303a;
        }
        xVar = xVar2;
        xVar.l = ((Number) obj).intValue();
        return m7.q.f19267a;
    }
}
