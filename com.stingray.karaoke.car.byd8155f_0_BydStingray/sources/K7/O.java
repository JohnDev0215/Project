package K7;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class O extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3740m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ U f3741n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ O(U u4, int i7) {
        super(0);
        this.f3740m = i7;
        this.f3741n = u4;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f3740m) {
            case 0:
                return l9.l.I(this.f3741n.f3750m);
            default:
                return new S(this.f3741n);
        }
    }
}
