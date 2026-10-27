package l0;

import z7.InterfaceC2772a;

/* JADX INFO: renamed from: l0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1715f extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1715f f18331n = new C1715f(0, 0);
    public static final C1715f o = new C1715f(0, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f18332m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1715f(int i7, int i10) {
        super(i7);
        this.f18332m = i10;
    }

    @Override // z7.InterfaceC2772a
    public final /* bridge */ /* synthetic */ Object a() {
        switch (this.f18332m) {
            case 0:
                return Boolean.FALSE;
            default:
                return m7.q.f19267a;
        }
    }
}
