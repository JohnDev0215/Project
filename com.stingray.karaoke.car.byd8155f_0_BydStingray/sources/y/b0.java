package y;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class b0 extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final b0 f23886n = new b0(0, 0);
    public static final b0 o = new b0(0, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23887m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b0(int i7, int i10) {
        super(i7);
        this.f23887m = i10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f23887m) {
            case 0:
                return C2685F.f23835a;
            default:
                return new p0();
        }
    }
}
