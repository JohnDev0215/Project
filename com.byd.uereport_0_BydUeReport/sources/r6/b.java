package r6;

import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.Arrays;

/* JADX INFO: compiled from: BitMatrix.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f7847d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7848e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f7849f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f7850g;

    public b(int i8, int i9) {
        if (i8 < 1 || i9 < 1) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.f7847d = i8;
        this.f7848e = i9;
        int i10 = (i8 + 31) / 32;
        this.f7849f = i10;
        this.f7850g = new int[i10 * i9];
    }

    public final String a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(this.f7848e * (this.f7847d + 1));
        for (int i8 = 0; i8 < this.f7848e; i8++) {
            for (int i9 = 0; i9 < this.f7847d; i9++) {
                sb.append(c(i9, i8) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public b clone() {
        return new b(this.f7847d, this.f7848e, this.f7849f, (int[]) this.f7850g.clone());
    }

    public boolean c(int i8, int i9) {
        return ((this.f7850g[(i9 * this.f7849f) + (i8 / 32)] >>> (i8 & 31)) & 1) != 0;
    }

    public void d(int i8, int i9, int i10, int i11) {
        if (i9 < 0 || i8 < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        }
        if (i11 < 1 || i10 < 1) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        }
        int i12 = i10 + i8;
        int i13 = i11 + i9;
        if (i13 > this.f7848e || i12 > this.f7847d) {
            throw new IllegalArgumentException("The region must fit inside the matrix");
        }
        while (i9 < i13) {
            int i14 = this.f7849f * i9;
            for (int i15 = i8; i15 < i12; i15++) {
                int[] iArr = this.f7850g;
                int i16 = (i15 / 32) + i14;
                iArr[i16] = iArr[i16] | (1 << (i15 & 31));
            }
            i9++;
        }
    }

    public String e(String str, String str2) {
        return a(str, str2, BydAdbHelper.COMMAND_LINE_END);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f7847d == bVar.f7847d && this.f7848e == bVar.f7848e && this.f7849f == bVar.f7849f && Arrays.equals(this.f7850g, bVar.f7850g);
    }

    public int hashCode() {
        int i8 = this.f7847d;
        return (((((((i8 * 31) + i8) * 31) + this.f7848e) * 31) + this.f7849f) * 31) + Arrays.hashCode(this.f7850g);
    }

    public String toString() {
        return e("X ", "  ");
    }

    public b(int i8, int i9, int i10, int[] iArr) {
        this.f7847d = i8;
        this.f7848e = i9;
        this.f7849f = i10;
        this.f7850g = iArr;
    }
}
