package D;

/* JADX INFO: renamed from: D.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0122d implements InterfaceC0123e, InterfaceC0125g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1071a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1072b;

    public C0122d(int i7) {
        this.f1071a = i7;
        switch (i7) {
            case 1:
                this.f1072b = 0;
                break;
            case 2:
                this.f1072b = 0;
                break;
            case 3:
                this.f1072b = 0;
                break;
            default:
                this.f1072b = 0;
                break;
        }
    }

    @Override // D.InterfaceC0123e, D.InterfaceC0125g
    public final float a() {
        switch (this.f1071a) {
        }
        return this.f1072b;
    }

    @Override // D.InterfaceC0123e
    public final void b(a1.b bVar, int i7, int[] iArr, a1.k kVar, int[] iArr2) {
        switch (this.f1071a) {
            case 0:
                if (kVar != a1.k.l) {
                    AbstractC0127i.a(i7, iArr, iArr2, true);
                } else {
                    AbstractC0127i.a(i7, iArr, iArr2, false);
                }
                break;
            case 1:
                if (kVar != a1.k.l) {
                    AbstractC0127i.d(i7, iArr, iArr2, true);
                } else {
                    AbstractC0127i.d(i7, iArr, iArr2, false);
                }
                break;
            case 2:
                if (kVar != a1.k.l) {
                    AbstractC0127i.e(i7, iArr, iArr2, true);
                } else {
                    AbstractC0127i.e(i7, iArr, iArr2, false);
                }
                break;
            default:
                if (kVar != a1.k.l) {
                    AbstractC0127i.f(i7, iArr, iArr2, true);
                } else {
                    AbstractC0127i.f(i7, iArr, iArr2, false);
                }
                break;
        }
    }

    @Override // D.InterfaceC0125g
    public final void c(a1.b bVar, int i7, int[] iArr, int[] iArr2) {
        switch (this.f1071a) {
            case 0:
                AbstractC0127i.a(i7, iArr, iArr2, false);
                break;
            case 1:
                AbstractC0127i.d(i7, iArr, iArr2, false);
                break;
            case 2:
                AbstractC0127i.e(i7, iArr, iArr2, false);
                break;
            default:
                AbstractC0127i.f(i7, iArr, iArr2, false);
                break;
        }
    }

    public final String toString() {
        switch (this.f1071a) {
            case 0:
                return "Arrangement#Center";
            case 1:
                return "Arrangement#SpaceAround";
            case 2:
                return "Arrangement#SpaceBetween";
            default:
                return "Arrangement#SpaceEvenly";
        }
    }
}
