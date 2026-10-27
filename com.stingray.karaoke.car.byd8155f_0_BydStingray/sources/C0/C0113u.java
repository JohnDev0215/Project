package C0;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: C0.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0113u extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0113u f913n = new C0113u(1, 0);
    public static final C0113u o = new C0113u(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0113u f914p = new C0113u(1, 2);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f915m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0113u(int i7, int i10) {
        super(i7);
        this.f915m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f915m) {
            case 0:
                ((E0.F) obj).f1468G = true;
                break;
            case 1:
                break;
            default:
                break;
        }
        return m7.q.f19267a;
    }
}
