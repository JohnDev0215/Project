package D;

/* JADX INFO: renamed from: D.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0119a implements InterfaceC0123e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1064a;

    @Override // D.InterfaceC0123e
    public final void b(a1.b bVar, int i7, int[] iArr, a1.k kVar, int[] iArr2) {
        switch (this.f1064a) {
            case 0:
                AbstractC0127i.b(iArr, iArr2, false);
                break;
            case 1:
                AbstractC0127i.c(i7, iArr, iArr2, false);
                break;
            case 2:
                if (kVar != a1.k.l) {
                    AbstractC0127i.b(iArr, iArr2, true);
                } else {
                    AbstractC0127i.c(i7, iArr, iArr2, false);
                }
                break;
            default:
                if (kVar != a1.k.l) {
                    AbstractC0127i.c(i7, iArr, iArr2, true);
                } else {
                    AbstractC0127i.b(iArr, iArr2, false);
                }
                break;
        }
    }

    public final String toString() {
        switch (this.f1064a) {
            case 0:
                return "AbsoluteArrangement#Left";
            case 1:
                return "AbsoluteArrangement#Right";
            case 2:
                return "Arrangement#End";
            default:
                return "Arrangement#Start";
        }
    }
}
