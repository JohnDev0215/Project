package F0;

import l0.AbstractC1713d;
import l0.C1727r;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: F0.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0234w extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2289m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f2290n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0234w(int i7, int i10) {
        super(1);
        this.f2289m = i10;
        this.f2290n = i7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f2289m) {
            case 0:
                Boolean boolB = AbstractC1713d.B((C1727r) obj, this.f2290n);
                return Boolean.valueOf(boolB != null ? boolB.booleanValue() : false);
            default:
                Boolean boolB2 = AbstractC1713d.B((C1727r) obj, this.f2290n);
                return Boolean.valueOf(boolB2 != null ? boolB2.booleanValue() : false);
        }
    }
}
