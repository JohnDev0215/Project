package j9;

/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f17598a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f17599b = new int[10];

    public final int a() {
        if ((this.f17598a & 128) != 0) {
            return this.f17599b[7];
        }
        return 65535;
    }

    public final void b(y yVar) {
        A7.m.f(yVar, "other");
        for (int i7 = 0; i7 < 10; i7++) {
            if (((1 << i7) & yVar.f17598a) != 0) {
                c(i7, yVar.f17599b[i7]);
            }
        }
    }

    public final void c(int i7, int i10) {
        if (i7 >= 0) {
            int[] iArr = this.f17599b;
            if (i7 >= iArr.length) {
                return;
            }
            this.f17598a = (1 << i7) | this.f17598a;
            iArr[i7] = i10;
        }
    }
}
