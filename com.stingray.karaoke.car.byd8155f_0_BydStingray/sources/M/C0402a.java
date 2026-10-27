package M;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: M.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0402a extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0402a f4124n = new C0402a(1, 0);
    public static final C0402a o = new C0402a(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0402a f4125p = new C0402a(1, 2);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4126m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0402a(int i7, int i10) {
        super(i7);
        this.f4126m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final /* synthetic */ Object b(Object obj) {
        switch (this.f4126m) {
            case 0:
                ((Number) obj).longValue();
                break;
            case 1:
                break;
            default:
                int i7 = ((T0.l) obj).f8458a;
                break;
        }
        return m7.q.f19267a;
    }
}
