package K;

import z7.InterfaceC2772a;

/* JADX INFO: renamed from: K.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0345i0 extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3288m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0355n0 f3289n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0345i0(InterfaceC0355n0 interfaceC0355n0, int i7) {
        super(0);
        this.f3288m = i7;
        this.f3289n = interfaceC0355n0;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f3288m) {
            case 0:
                this.f3289n.a();
                break;
            default:
                this.f3289n.onCancel();
                break;
        }
        return m7.q.f19267a;
    }
}
