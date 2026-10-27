package r7;

import A7.E;
import A7.m;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import s7.AbstractC2410c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: r7.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2305c extends AbstractC2410c {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f21876m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2189c f21877n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2305c(InterfaceC2189c interfaceC2189c, InterfaceC2194h interfaceC2194h, InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c2) {
        super(interfaceC2189c, interfaceC2194h);
        this.f21876m = interfaceC2785n;
        this.f21877n = interfaceC2189c2;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i7 = this.l;
        if (i7 != 0) {
            if (i7 != 1) {
                throw new IllegalStateException("This coroutine had already completed");
            }
            this.l = 2;
            AbstractC1386c.u(obj);
            return obj;
        }
        this.l = 1;
        AbstractC1386c.u(obj);
        InterfaceC2785n interfaceC2785n = this.f21876m;
        m.d(interfaceC2785n, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted>, kotlin.Any?>");
        E.e(2, interfaceC2785n);
        return interfaceC2785n.invoke(this.f21877n, this);
    }
}
