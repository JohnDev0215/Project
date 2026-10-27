package b.e.b;

import b.e.b.b;
import java.util.Arrays;

/* JADX INFO: compiled from: ArrayLinkedVariables.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static float f2351a = 0.001f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b f2353c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected final c f2354d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f2352b = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2355e = 8;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private i f2356f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int[] f2357g = new int[8];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int[] f2358h = new int[8];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private float[] f2359i = new float[8];
    private int j = -1;
    private int k = -1;
    private boolean l = false;

    a(b bVar, c cVar) {
        this.f2353c = bVar;
        this.f2354d = cVar;
    }

    @Override // b.e.b.b.a
    public float a(int i2) {
        int i3 = this.j;
        for (int i4 = 0; i3 != -1 && i4 < this.f2352b; i4++) {
            if (i4 == i2) {
                return this.f2359i[i3];
            }
            i3 = this.f2358h[i3];
        }
        return 0.0f;
    }

    @Override // b.e.b.b.a
    public float b(b bVar, boolean z) {
        float fG = g(bVar.f2360a);
        d(bVar.f2360a, z);
        b.a aVar = bVar.f2364e;
        int iK = aVar.k();
        for (int i2 = 0; i2 < iK; i2++) {
            i iVarF = aVar.f(i2);
            e(iVarF, aVar.g(iVarF) * fG, z);
        }
        return fG;
    }

    @Override // b.e.b.b.a
    public final void c(i iVar, float f2) {
        if (f2 == 0.0f) {
            d(iVar, true);
            return;
        }
        int i2 = this.j;
        if (i2 == -1) {
            this.j = 0;
            this.f2359i[0] = f2;
            this.f2357g[0] = iVar.f2392d;
            this.f2358h[0] = -1;
            iVar.n++;
            iVar.a(this.f2353c);
            this.f2352b++;
            if (this.l) {
                return;
            }
            int i3 = this.k + 1;
            this.k = i3;
            int[] iArr = this.f2357g;
            if (i3 >= iArr.length) {
                this.l = true;
                this.k = iArr.length - 1;
                return;
            }
            return;
        }
        int i4 = -1;
        for (int i5 = 0; i2 != -1 && i5 < this.f2352b; i5++) {
            int[] iArr2 = this.f2357g;
            int i6 = iArr2[i2];
            int i7 = iVar.f2392d;
            if (i6 == i7) {
                this.f2359i[i2] = f2;
                return;
            }
            if (iArr2[i2] < i7) {
                i4 = i2;
            }
            i2 = this.f2358h[i2];
        }
        int length = this.k;
        int i8 = length + 1;
        if (this.l) {
            int[] iArr3 = this.f2357g;
            if (iArr3[length] != -1) {
                length = iArr3.length;
            }
        } else {
            length = i8;
        }
        int[] iArr4 = this.f2357g;
        if (length >= iArr4.length && this.f2352b < iArr4.length) {
            int i9 = 0;
            while (true) {
                int[] iArr5 = this.f2357g;
                if (i9 >= iArr5.length) {
                    break;
                }
                if (iArr5[i9] == -1) {
                    length = i9;
                    break;
                }
                i9++;
            }
        }
        int[] iArr6 = this.f2357g;
        if (length >= iArr6.length) {
            length = iArr6.length;
            int i10 = this.f2355e * 2;
            this.f2355e = i10;
            this.l = false;
            this.k = length - 1;
            this.f2359i = Arrays.copyOf(this.f2359i, i10);
            this.f2357g = Arrays.copyOf(this.f2357g, this.f2355e);
            this.f2358h = Arrays.copyOf(this.f2358h, this.f2355e);
        }
        this.f2357g[length] = iVar.f2392d;
        this.f2359i[length] = f2;
        if (i4 != -1) {
            int[] iArr7 = this.f2358h;
            iArr7[length] = iArr7[i4];
            iArr7[i4] = length;
        } else {
            this.f2358h[length] = this.j;
            this.j = length;
        }
        iVar.n++;
        iVar.a(this.f2353c);
        int i11 = this.f2352b + 1;
        this.f2352b = i11;
        if (!this.l) {
            this.k++;
        }
        int[] iArr8 = this.f2357g;
        if (i11 >= iArr8.length) {
            this.l = true;
        }
        if (this.k >= iArr8.length) {
            this.l = true;
            this.k = iArr8.length - 1;
        }
    }

    @Override // b.e.b.b.a
    public final void clear() {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f2352b; i3++) {
            i iVar = this.f2354d.f2369d[this.f2357g[i2]];
            if (iVar != null) {
                iVar.c(this.f2353c);
            }
            i2 = this.f2358h[i2];
        }
        this.j = -1;
        this.k = -1;
        this.l = false;
        this.f2352b = 0;
    }

    @Override // b.e.b.b.a
    public final float d(i iVar, boolean z) {
        if (this.f2356f == iVar) {
            this.f2356f = null;
        }
        int i2 = this.j;
        if (i2 == -1) {
            return 0.0f;
        }
        int i3 = 0;
        int i4 = -1;
        while (i2 != -1 && i3 < this.f2352b) {
            if (this.f2357g[i2] == iVar.f2392d) {
                if (i2 == this.j) {
                    this.j = this.f2358h[i2];
                } else {
                    int[] iArr = this.f2358h;
                    iArr[i4] = iArr[i2];
                }
                if (z) {
                    iVar.c(this.f2353c);
                }
                iVar.n--;
                this.f2352b--;
                this.f2357g[i2] = -1;
                if (this.l) {
                    this.k = i2;
                }
                return this.f2359i[i2];
            }
            i3++;
            i4 = i2;
            i2 = this.f2358h[i2];
        }
        return 0.0f;
    }

    @Override // b.e.b.b.a
    public void e(i iVar, float f2, boolean z) {
        float f3 = f2351a;
        if (f2 <= (-f3) || f2 >= f3) {
            int i2 = this.j;
            if (i2 == -1) {
                this.j = 0;
                this.f2359i[0] = f2;
                this.f2357g[0] = iVar.f2392d;
                this.f2358h[0] = -1;
                iVar.n++;
                iVar.a(this.f2353c);
                this.f2352b++;
                if (this.l) {
                    return;
                }
                int i3 = this.k + 1;
                this.k = i3;
                int[] iArr = this.f2357g;
                if (i3 >= iArr.length) {
                    this.l = true;
                    this.k = iArr.length - 1;
                    return;
                }
                return;
            }
            int i4 = -1;
            for (int i5 = 0; i2 != -1 && i5 < this.f2352b; i5++) {
                int[] iArr2 = this.f2357g;
                int i6 = iArr2[i2];
                int i7 = iVar.f2392d;
                if (i6 == i7) {
                    float[] fArr = this.f2359i;
                    float f4 = fArr[i2] + f2;
                    float f5 = f2351a;
                    if (f4 > (-f5) && f4 < f5) {
                        f4 = 0.0f;
                    }
                    fArr[i2] = f4;
                    if (f4 == 0.0f) {
                        if (i2 == this.j) {
                            this.j = this.f2358h[i2];
                        } else {
                            int[] iArr3 = this.f2358h;
                            iArr3[i4] = iArr3[i2];
                        }
                        if (z) {
                            iVar.c(this.f2353c);
                        }
                        if (this.l) {
                            this.k = i2;
                        }
                        iVar.n--;
                        this.f2352b--;
                        return;
                    }
                    return;
                }
                if (iArr2[i2] < i7) {
                    i4 = i2;
                }
                i2 = this.f2358h[i2];
            }
            int length = this.k;
            int i8 = length + 1;
            if (this.l) {
                int[] iArr4 = this.f2357g;
                if (iArr4[length] != -1) {
                    length = iArr4.length;
                }
            } else {
                length = i8;
            }
            int[] iArr5 = this.f2357g;
            if (length >= iArr5.length && this.f2352b < iArr5.length) {
                int i9 = 0;
                while (true) {
                    int[] iArr6 = this.f2357g;
                    if (i9 >= iArr6.length) {
                        break;
                    }
                    if (iArr6[i9] == -1) {
                        length = i9;
                        break;
                    }
                    i9++;
                }
            }
            int[] iArr7 = this.f2357g;
            if (length >= iArr7.length) {
                length = iArr7.length;
                int i10 = this.f2355e * 2;
                this.f2355e = i10;
                this.l = false;
                this.k = length - 1;
                this.f2359i = Arrays.copyOf(this.f2359i, i10);
                this.f2357g = Arrays.copyOf(this.f2357g, this.f2355e);
                this.f2358h = Arrays.copyOf(this.f2358h, this.f2355e);
            }
            this.f2357g[length] = iVar.f2392d;
            this.f2359i[length] = f2;
            if (i4 != -1) {
                int[] iArr8 = this.f2358h;
                iArr8[length] = iArr8[i4];
                iArr8[i4] = length;
            } else {
                this.f2358h[length] = this.j;
                this.j = length;
            }
            iVar.n++;
            iVar.a(this.f2353c);
            this.f2352b++;
            if (!this.l) {
                this.k++;
            }
            int i11 = this.k;
            int[] iArr9 = this.f2357g;
            if (i11 >= iArr9.length) {
                this.l = true;
                this.k = iArr9.length - 1;
            }
        }
    }

    @Override // b.e.b.b.a
    public i f(int i2) {
        int i3 = this.j;
        for (int i4 = 0; i3 != -1 && i4 < this.f2352b; i4++) {
            if (i4 == i2) {
                return this.f2354d.f2369d[this.f2357g[i3]];
            }
            i3 = this.f2358h[i3];
        }
        return null;
    }

    @Override // b.e.b.b.a
    public final float g(i iVar) {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f2352b; i3++) {
            if (this.f2357g[i2] == iVar.f2392d) {
                return this.f2359i[i2];
            }
            i2 = this.f2358h[i2];
        }
        return 0.0f;
    }

    @Override // b.e.b.b.a
    public boolean h(i iVar) {
        int i2 = this.j;
        if (i2 == -1) {
            return false;
        }
        for (int i3 = 0; i2 != -1 && i3 < this.f2352b; i3++) {
            if (this.f2357g[i2] == iVar.f2392d) {
                return true;
            }
            i2 = this.f2358h[i2];
        }
        return false;
    }

    @Override // b.e.b.b.a
    public void i(float f2) {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f2352b; i3++) {
            float[] fArr = this.f2359i;
            fArr[i2] = fArr[i2] / f2;
            i2 = this.f2358h[i2];
        }
    }

    @Override // b.e.b.b.a
    public void j() {
        int i2 = this.j;
        for (int i3 = 0; i2 != -1 && i3 < this.f2352b; i3++) {
            float[] fArr = this.f2359i;
            fArr[i2] = fArr[i2] * (-1.0f);
            i2 = this.f2358h[i2];
        }
    }

    @Override // b.e.b.b.a
    public int k() {
        return this.f2352b;
    }

    public String toString() {
        int i2 = this.j;
        String str = "";
        for (int i3 = 0; i2 != -1 && i3 < this.f2352b; i3++) {
            str = ((str + " -> ") + this.f2359i[i2] + " : ") + this.f2354d.f2369d[this.f2357g[i2]];
            i2 = this.f2358h[i2];
        }
        return str;
    }
}
