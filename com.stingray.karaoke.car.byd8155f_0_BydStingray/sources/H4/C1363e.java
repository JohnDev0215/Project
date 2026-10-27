package h4;

/* JADX INFO: renamed from: h4.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1363e extends A9.b {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f15515n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1363e(int i7) {
        super(6);
        this.f15515n = i7;
    }

    public final InterfaceC1365g a1() {
        switch (this.f15515n) {
            case 0:
                return new C1362d(this);
            default:
                return new C1367i(this);
        }
    }
}
