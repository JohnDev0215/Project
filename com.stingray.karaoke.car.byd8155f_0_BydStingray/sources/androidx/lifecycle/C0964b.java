package androidx.lifecycle;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: androidx.lifecycle.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0964b extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0966d f11966m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0964b(C0966d c0966d, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f11966m = c0966d;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0964b(this.f11966m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0964b) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        C0966d c0966d = this.f11966m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            long j3 = c0966d.c;
            this.l = 1;
            if (AbstractC0727y.i(j3, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        if (c0966d.f11972a.c <= 0) {
            S8.W w10 = c0966d.f11975f;
            if (w10 != null) {
                w10.g(null);
            }
            c0966d.f11975f = null;
        }
        return m7.q.f19267a;
    }
}
