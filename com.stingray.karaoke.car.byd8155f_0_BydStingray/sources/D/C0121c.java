package D;

/* JADX INFO: renamed from: D.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0121c implements InterfaceC0125g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1070a = 1;

    @Override // D.InterfaceC0125g
    public final void c(a1.b bVar, int i7, int[] iArr, int[] iArr2) {
        switch (this.f1070a) {
            case 0:
                AbstractC0127i.c(i7, iArr, iArr2, false);
                break;
            default:
                AbstractC0127i.b(iArr, iArr2, false);
                break;
        }
    }

    public final String toString() {
        switch (this.f1070a) {
            case 0:
                return "Arrangement#Bottom";
            default:
                return "Arrangement#Top";
        }
    }
}
