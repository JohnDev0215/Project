package l0;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: l0.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1717h extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1717h f18333n = new C1717h(1, 0);
    public static final C1717h o = new C1717h(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C1717h f18334p = new C1717h(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C1717h f18335q = new C1717h(1, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C1717h f18336r = new C1717h(1, 4);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f18337m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1717h(int i7, int i10) {
        super(i7);
        this.f18337m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f18337m) {
            case 0:
                ((InterfaceC1718i) obj).b(false);
                break;
            case 1:
                int i7 = ((C1711b) obj).f18325a;
                break;
            case 2:
                int i10 = ((C1711b) obj).f18325a;
                break;
        }
        return Boolean.valueOf(AbstractC1713d.A((C1727r) obj));
    }
}
