package V8;

/* JADX INFO: loaded from: classes.dex */
public final class X implements W {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9599a;

    @Override // V8.W
    public final InterfaceC0817f a(W8.E e10) {
        switch (this.f9599a) {
            case 0:
                return new C0819h(U.l, 0);
            default:
                return new C0819h(new Z(e10, null));
        }
    }

    public final String toString() {
        switch (this.f9599a) {
            case 0:
                return "SharingStarted.Eagerly";
            default:
                return "SharingStarted.Lazily";
        }
    }
}
