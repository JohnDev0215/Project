package F0;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: F0.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0219o extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0219o f2235n = new C0219o(1, 0);
    public static final C0219o o = new C0219o(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0219o f2236p = new C0219o(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C0219o f2237q = new C0219o(1, 3);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2238m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0219o(int i7, int i10) {
        super(i7);
        this.f2238m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f2238m) {
            case 0:
                return m7.q.f19267a;
            case 1:
                return Boolean.TRUE;
            case 2:
                return Boolean.FALSE;
            default:
                return Boolean.valueOf(T.m(obj));
        }
    }
}
