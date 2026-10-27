package F8;

import h8.AbstractC1387d;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: F8.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0271f extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0271f f2464n = new C0271f(1, 0);
    public static final C0271f o = new C0271f(1, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2465m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0271f(int i7, int i10) {
        super(i7);
        this.f2465m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f2465m) {
            case 0:
                ((Boolean) obj).getClass();
                return new C0270e(AbstractC1387d.j(H8.i.d));
            default:
                AbstractC0287w abstractC0287w = (AbstractC0287w) obj;
                A7.m.f(abstractC0287w, "it");
                return abstractC0287w.toString();
        }
    }
}
