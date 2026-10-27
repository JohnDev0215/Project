package C0;

import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class X extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f876m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ V f877n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ X(V v10, int i7) {
        super(1);
        this.f876m = i7;
        this.f877n = v10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f876m) {
            case 0:
                U.g((U) obj, this.f877n, 0, 0);
                break;
            case 1:
                U.f((U) obj, this.f877n, 0, 0);
                break;
            case 2:
                U.f((U) obj, this.f877n, 0, 0);
                break;
            case 3:
                U u4 = (U) obj;
                a1.k kVarB = u4.b();
                a1.k kVar = a1.k.l;
                V v10 = this.f877n;
                if (kVarB == kVar || u4.c() == 0) {
                    U.a(u4, v10);
                    v10.l0(a1.h.d(0L, v10.f873p), 0.0f, null);
                } else {
                    int i7 = (int) 0;
                    long jD = AbstractC2673a.d((u4.c() - v10.l) - i7, i7);
                    U.a(u4, v10);
                    v10.l0(a1.h.d(jD, v10.f873p), 0.0f, null);
                }
                break;
            case 4:
                U.f((U) obj, this.f877n, 0, 0);
                break;
            case 5:
                U.f((U) obj, this.f877n, 0, 0);
                break;
            case 6:
                U.f((U) obj, this.f877n, 0, 0);
                break;
            case 7:
                U.d((U) obj, this.f877n, 0, 0);
                break;
            case 8:
                U.d((U) obj, this.f877n, 0, 0);
                break;
            case 9:
                U u10 = (U) obj;
                A7.m.f(u10, "$this$layout");
                U.f(u10, this.f877n, 0, 0);
                break;
            case 10:
                U.f((U) obj, this.f877n, 0, 0);
                break;
            case 11:
                U.f((U) obj, this.f877n, 0, 0);
                break;
            case 12:
                U.d((U) obj, this.f877n, 0, 0);
                break;
            case 13:
                U.d((U) obj, this.f877n, 0, 0);
                break;
            default:
                U.d((U) obj, this.f877n, 0, 0);
                break;
        }
        return m7.q.f19267a;
    }
}
