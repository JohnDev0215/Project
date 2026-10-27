package org.chromium.media;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PhotoCapabilities {
    public boolean[] a;
    public double[] b;
    public int[] c;
    public int[] d;
    public int[] e;
    public int[][] f;

    public PhotoCapabilities(boolean[] zArr, double[] dArr, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) {
        if (zArr.length != 3 || dArr.length != 16 || iArr.length != 16 || iArr3.length != 3 || iArr4.length != 3) {
            throw new IllegalArgumentException();
        }
        if (iArr2 != null) {
            for (int i = 0; i < iArr2.length; i++) {
                if (iArr2[i] < 0 || iArr2[i] >= 4) {
                    throw new IllegalArgumentException();
                }
            }
        }
        for (int i2 = 0; i2 < iArr3.length; i2++) {
            if (iArr3[i2] < 0 || iArr3[i2] >= 5) {
                throw new IllegalArgumentException();
            }
        }
        for (int i3 = 0; i3 < iArr4.length; i3++) {
            if (iArr4[i3] != null) {
                for (int i4 = 0; i4 < iArr4[i3].length; i4++) {
                    if (iArr4[i3][i4] < 0 || iArr4[i3][i4] >= 5) {
                        throw new IllegalArgumentException();
                    }
                }
            }
        }
        this.a = (boolean[]) zArr.clone();
        this.b = (double[]) dArr.clone();
        this.c = (int[]) iArr.clone();
        this.d = iArr2 == null ? null : (int[]) iArr2.clone();
        this.e = (int[]) iArr3.clone();
        this.f = new int[3][];
        for (int i5 = 0; i5 < iArr4.length; i5++) {
            this.f[i5] = iArr4[i5] == null ? null : (int[]) iArr4[i5].clone();
        }
    }

    public boolean getBool(int i) {
        if (i < 0 || i >= 3) {
            throw new IllegalArgumentException();
        }
        return this.a[i];
    }

    public double getDouble(int i) {
        if (i < 0 || i >= 16) {
            throw new IllegalArgumentException();
        }
        return this.b[i];
    }

    public int[] getFillLightModeArray() {
        int[] iArr = this.d;
        return iArr != null ? (int[]) iArr.clone() : new int[0];
    }

    public int getInt(int i) {
        if (i < 0 || i >= 16) {
            throw new IllegalArgumentException();
        }
        return this.c[i];
    }

    public int getMeteringMode(int i) {
        if (i < 0 || i >= 3) {
            throw new IllegalArgumentException();
        }
        return this.e[i];
    }

    public int[] getMeteringModeArray(int i) {
        if (i < 0 || i >= 3) {
            throw new IllegalArgumentException();
        }
        int[][] iArr = this.f;
        return iArr[i] != null ? (int[]) iArr[i].clone() : new int[0];
    }
}
