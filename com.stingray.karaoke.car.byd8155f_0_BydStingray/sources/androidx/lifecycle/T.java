package androidx.lifecycle;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import a2.C0908b;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class T extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f11955m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A9.b f11956n;
    public final /* synthetic */ EnumC0981t o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0908b f11957p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public T(A9.b bVar, EnumC0981t enumC0981t, C0908b c0908b, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f11956n = bVar;
        this.o = enumC0981t;
        this.f11957p = c0908b;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        T t10 = new T(this.f11956n, this.o, this.f11957p, interfaceC2189c);
        t10.f11955m = obj;
        return t10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((T) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f11955m;
            Z8.e eVar = S8.E.f8084a;
            T8.d dVar = X8.m.f10573a.f8932q;
            S s6 = new S(this.f11956n, this.o, interfaceC0725w, this.f11957p, null);
            this.l = 1;
            if (AbstractC0727y.F(dVar, s6, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }
}
