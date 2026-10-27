package o3;

import R.C0684l;
import T.C0730a0;
import T.X;
import V8.InterfaceC0817f;
import h8.AbstractC1386c;
import java.util.List;
import java.util.concurrent.CancellationException;
import n3.C1945j;
import n7.AbstractC1998m;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class r extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f20493m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2036i f20494n;
    public final /* synthetic */ X o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0730a0 f20495p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ X f20496q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(C2036i c2036i, X x8, C0730a0 c0730a0, X x9, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f20494n = c2036i;
        this.o = x8;
        this.f20495p = c0730a0;
        this.f20496q = x9;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        r rVar = new r(this.f20494n, this.o, this.f20495p, this.f20496q, interfaceC2189c);
        rVar.f20493m = obj;
        return rVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((r) create((InterfaceC0817f) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        C1945j c1945j;
        C1945j c1945j2;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        C2036i c2036i = this.f20494n;
        X x8 = this.o;
        X x9 = this.f20496q;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                InterfaceC0817f interfaceC0817f = (InterfaceC0817f) this.f20493m;
                int size = ((List) x8.getValue()).size();
                C0730a0 c0730a0 = this.f20495p;
                if (size > 1) {
                    c0730a0.j(0.0f);
                    c1945j = (C1945j) AbstractC1998m.i0((List) x8.getValue());
                    A7.m.c(c1945j);
                    c2036i.g(c1945j);
                    c2036i.g((C1945j) ((List) x8.getValue()).get(((List) x8.getValue()).size() - 2));
                } else {
                    c1945j = null;
                }
                C0684l c0684l = new C0684l(x8, x9, c0730a0, 4);
                this.f20493m = c1945j;
                this.l = 1;
                if (interfaceC0817f.b(c0684l, this) == enumC2303a) {
                    return enumC2303a;
                }
                c1945j2 = c1945j;
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c1945j2 = (C1945j) this.f20493m;
                AbstractC1386c.u(obj);
            }
            if (((List) x8.getValue()).size() > 1) {
                x9.setValue(Boolean.FALSE);
                A7.m.c(c1945j2);
                c2036i.e(c1945j2, false);
            }
        } catch (CancellationException unused) {
            if (((List) x8.getValue()).size() > 1) {
                x9.setValue(Boolean.FALSE);
            }
        }
        return m7.q.f19267a;
    }
}
