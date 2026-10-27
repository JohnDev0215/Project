package F0;

import l0.AbstractC1713d;
import l0.C1711b;
import l0.C1727r;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: F0.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0228t extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2273m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1711b f2274n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0228t(C1711b c1711b, int i7) {
        super(1);
        this.f2273m = i7;
        this.f2274n = c1711b;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f2273m) {
            case 0:
                Boolean boolB = AbstractC1713d.B((C1727r) obj, this.f2274n.f18325a);
                return Boolean.valueOf(boolB != null ? boolB.booleanValue() : true);
            default:
                Boolean boolB2 = AbstractC1713d.B((C1727r) obj, this.f2274n.f18325a);
                return Boolean.valueOf(boolB2 != null ? boolB2.booleanValue() : true);
        }
    }
}
