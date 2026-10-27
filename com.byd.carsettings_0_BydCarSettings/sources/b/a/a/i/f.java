package b.a.a.i;

import net.sqlcipher.database.SQLiteDatabase;

/* JADX INFO: compiled from: Label.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f208a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f209b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f210c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f211d;

    public final void a(int i, int i2, int i3) {
        if (this.f211d == null) {
            this.f211d = new int[6];
        }
        int i4 = this.f210c;
        int[] iArr = this.f211d;
        if (i4 >= iArr.length) {
            int[] iArr2 = new int[iArr.length + 6];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.f211d = iArr2;
        }
        int[] iArr3 = this.f211d;
        int i5 = this.f210c;
        int i6 = i5 + 1;
        this.f210c = i6;
        iArr3[i5] = i;
        this.f210c = i6 + 1;
        iArr3[i6] = i2 | i3;
    }

    public void b(i iVar, a aVar, int i, boolean z) {
        if ((this.f208a & 2) != 0) {
            if (z) {
                aVar.f(this.f209b - i);
                return;
            } else {
                aVar.g(this.f209b - i);
                return;
            }
        }
        if (z) {
            a(i, aVar.f185b, 536870912);
            aVar.f(-1);
        } else {
            a(i, aVar.f185b, SQLiteDatabase.CREATE_IF_NECESSARY);
            aVar.g(-1);
        }
    }

    public void c(i iVar, int i, byte[] bArr) {
        this.f208a |= 2;
        this.f209b = i;
        int i2 = 0;
        while (i2 < this.f210c) {
            int[] iArr = this.f211d;
            int i3 = i2 + 1;
            int i4 = iArr[i2];
            int i5 = i3 + 1;
            int i6 = iArr[i3];
            int i7 = 268435455 & i6;
            int i8 = i - i4;
            if ((i6 & (-268435456)) == 268435456) {
                bArr[i7] = (byte) (i8 >>> 8);
                bArr[i7 + 1] = (byte) i8;
            } else {
                int i9 = i7 + 1;
                bArr[i7] = (byte) (i8 >>> 24);
                int i10 = i9 + 1;
                bArr[i9] = (byte) (i8 >>> 16);
                bArr[i10] = (byte) (i8 >>> 8);
                bArr[i10 + 1] = (byte) i8;
            }
            i2 = i5;
        }
    }
}
