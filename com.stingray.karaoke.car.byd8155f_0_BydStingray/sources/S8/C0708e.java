package S8;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: S8.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0708e implements InterfaceC0709f {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f8112m;

    public /* synthetic */ C0708e(Object obj, int i7) {
        this.l = i7;
        this.f8112m = obj;
    }

    @Override // S8.InterfaceC0709f
    public final void a(Throwable th) {
        switch (this.l) {
            case 0:
                ((InterfaceC2782k) this.f8112m).b(th);
                break;
            default:
                ((F) this.f8112m).a();
                break;
        }
    }

    public final String toString() {
        switch (this.l) {
            case 0:
                return "CancelHandler.UserSupplied[" + ((InterfaceC2782k) this.f8112m).getClass().getSimpleName() + '@' + AbstractC0727y.n(this) + ']';
            default:
                return "DisposeOnCancel[" + ((F) this.f8112m) + ']';
        }
    }
}
