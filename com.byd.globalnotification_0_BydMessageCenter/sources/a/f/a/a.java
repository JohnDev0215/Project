package a.f.a;

import a.f.a.b;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class a implements b.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f497b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f498c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f496a = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f499d = 8;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g f500e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f501f = new int[8];
    public int[] g = new int[8];
    public float[] h = new float[8];
    public int i = -1;
    public int j = -1;
    public boolean k = false;

    public a(b bVar, c cVar) {
        this.f497b = bVar;
        this.f498c = cVar;
    }

    @Override // a.f.a.b.a
    public float a(int i) {
        int i2 = this.i;
        for (int i3 = 0; i2 != -1 && i3 < this.f496a; i3++) {
            if (i3 == i) {
                return this.h[i2];
            }
            i2 = this.g[i2];
        }
        return 0.0f;
    }

    @Override // a.f.a.b.a
    public final float b(g gVar) {
        int i = this.i;
        for (int i2 = 0; i != -1 && i2 < this.f496a; i2++) {
            if (this.f501f[i] == gVar.f524c) {
                return this.h[i];
            }
            i = this.g[i];
        }
        return 0.0f;
    }

    @Override // a.f.a.b.a
    public final float c(g gVar, boolean z) {
        if (this.f500e == gVar) {
            this.f500e = null;
        }
        int i = this.i;
        if (i == -1) {
            return 0.0f;
        }
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.f496a) {
            if (this.f501f[i] == gVar.f524c) {
                if (i == this.i) {
                    this.i = this.g[i];
                } else {
                    int[] iArr = this.g;
                    iArr[i3] = iArr[i];
                }
                if (z) {
                    gVar.b(this.f497b);
                }
                gVar.m--;
                this.f496a--;
                this.f501f[i] = -1;
                if (this.k) {
                    this.j = i;
                }
                return this.h[i];
            }
            i2++;
            i3 = i;
            i = this.g[i];
        }
        return 0.0f;
    }

    @Override // a.f.a.b.a
    public final void clear() {
        int i = this.i;
        for (int i2 = 0; i != -1 && i2 < this.f496a; i2++) {
            g gVar = this.f498c.f511d[this.f501f[i]];
            if (gVar != null) {
                gVar.b(this.f497b);
            }
            i = this.g[i];
        }
        this.i = -1;
        this.j = -1;
        this.k = false;
        this.f496a = 0;
    }

    @Override // a.f.a.b.a
    public g d(int i) {
        int i2 = this.i;
        for (int i3 = 0; i2 != -1 && i3 < this.f496a; i3++) {
            if (i3 == i) {
                return this.f498c.f511d[this.f501f[i2]];
            }
            i2 = this.g[i2];
        }
        return null;
    }

    @Override // a.f.a.b.a
    public void e(g gVar, float f2, boolean z) {
        if (f2 <= -0.001f || f2 >= 0.001f) {
            int i = this.i;
            if (i == -1) {
                this.i = 0;
                this.h[0] = f2;
                this.f501f[0] = gVar.f524c;
                this.g[0] = -1;
                gVar.m++;
                gVar.a(this.f497b);
                this.f496a++;
                if (this.k) {
                    return;
                }
                int i2 = this.j + 1;
                this.j = i2;
                int[] iArr = this.f501f;
                if (i2 >= iArr.length) {
                    this.k = true;
                    this.j = iArr.length - 1;
                    return;
                }
                return;
            }
            int i3 = -1;
            for (int i4 = 0; i != -1 && i4 < this.f496a; i4++) {
                int[] iArr2 = this.f501f;
                int i5 = iArr2[i];
                int i6 = gVar.f524c;
                if (i5 == i6) {
                    float f3 = this.h[i] + f2;
                    if (f3 > -0.001f && f3 < 0.001f) {
                        f3 = 0.0f;
                    }
                    this.h[i] = f3;
                    if (f3 == 0.0f) {
                        if (i == this.i) {
                            this.i = this.g[i];
                        } else {
                            int[] iArr3 = this.g;
                            iArr3[i3] = iArr3[i];
                        }
                        if (z) {
                            gVar.b(this.f497b);
                        }
                        if (this.k) {
                            this.j = i;
                        }
                        gVar.m--;
                        this.f496a--;
                        return;
                    }
                    return;
                }
                if (iArr2[i] < i6) {
                    i3 = i;
                }
                i = this.g[i];
            }
            int length = this.j;
            int i7 = length + 1;
            if (this.k) {
                int[] iArr4 = this.f501f;
                if (iArr4[length] != -1) {
                    length = iArr4.length;
                }
            } else {
                length = i7;
            }
            int[] iArr5 = this.f501f;
            if (length >= iArr5.length && this.f496a < iArr5.length) {
                int i8 = 0;
                while (true) {
                    int[] iArr6 = this.f501f;
                    if (i8 >= iArr6.length) {
                        break;
                    }
                    if (iArr6[i8] == -1) {
                        length = i8;
                        break;
                    }
                    i8++;
                }
            }
            int[] iArr7 = this.f501f;
            if (length >= iArr7.length) {
                length = iArr7.length;
                int i9 = this.f499d * 2;
                this.f499d = i9;
                this.k = false;
                this.j = length - 1;
                this.h = Arrays.copyOf(this.h, i9);
                this.f501f = Arrays.copyOf(this.f501f, this.f499d);
                this.g = Arrays.copyOf(this.g, this.f499d);
            }
            this.f501f[length] = gVar.f524c;
            this.h[length] = f2;
            int[] iArr8 = this.g;
            if (i3 != -1) {
                iArr8[length] = iArr8[i3];
                iArr8[i3] = length;
            } else {
                iArr8[length] = this.i;
                this.i = length;
            }
            gVar.m++;
            gVar.a(this.f497b);
            this.f496a++;
            if (!this.k) {
                this.j++;
            }
            int i10 = this.j;
            int[] iArr9 = this.f501f;
            if (i10 >= iArr9.length) {
                this.k = true;
                this.j = iArr9.length - 1;
            }
        }
    }

    @Override // a.f.a.b.a
    public boolean f(g gVar) {
        int i = this.i;
        if (i == -1) {
            return false;
        }
        for (int i2 = 0; i != -1 && i2 < this.f496a; i2++) {
            if (this.f501f[i] == gVar.f524c) {
                return true;
            }
            i = this.g[i];
        }
        return false;
    }

    @Override // a.f.a.b.a
    public void g(float f2) {
        int i = this.i;
        for (int i2 = 0; i != -1 && i2 < this.f496a; i2++) {
            float[] fArr = this.h;
            fArr[i] = fArr[i] / f2;
            i = this.g[i];
        }
    }

    @Override // a.f.a.b.a
    public float h(b bVar, boolean z) {
        float fB = b(bVar.f502a);
        c(bVar.f502a, z);
        b.a aVar = bVar.f506e;
        int iK = aVar.k();
        for (int i = 0; i < iK; i++) {
            g gVarD = aVar.d(i);
            e(gVarD, aVar.b(gVarD) * fB, z);
        }
        return fB;
    }

    @Override // a.f.a.b.a
    public void i() {
        int i = this.i;
        for (int i2 = 0; i != -1 && i2 < this.f496a; i2++) {
            float[] fArr = this.h;
            fArr[i] = fArr[i] * (-1.0f);
            i = this.g[i];
        }
    }

    @Override // a.f.a.b.a
    public final void j(g gVar, float f2) {
        if (f2 == 0.0f) {
            c(gVar, true);
            return;
        }
        int i = this.i;
        if (i == -1) {
            this.i = 0;
            this.h[0] = f2;
            this.f501f[0] = gVar.f524c;
            this.g[0] = -1;
            gVar.m++;
            gVar.a(this.f497b);
            this.f496a++;
            if (this.k) {
                return;
            }
            int i2 = this.j + 1;
            this.j = i2;
            int[] iArr = this.f501f;
            if (i2 >= iArr.length) {
                this.k = true;
                this.j = iArr.length - 1;
                return;
            }
            return;
        }
        int i3 = -1;
        for (int i4 = 0; i != -1 && i4 < this.f496a; i4++) {
            int[] iArr2 = this.f501f;
            int i5 = iArr2[i];
            int i6 = gVar.f524c;
            if (i5 == i6) {
                this.h[i] = f2;
                return;
            }
            if (iArr2[i] < i6) {
                i3 = i;
            }
            i = this.g[i];
        }
        int length = this.j;
        int i7 = length + 1;
        if (this.k) {
            int[] iArr3 = this.f501f;
            if (iArr3[length] != -1) {
                length = iArr3.length;
            }
        } else {
            length = i7;
        }
        int[] iArr4 = this.f501f;
        if (length >= iArr4.length && this.f496a < iArr4.length) {
            int i8 = 0;
            while (true) {
                int[] iArr5 = this.f501f;
                if (i8 >= iArr5.length) {
                    break;
                }
                if (iArr5[i8] == -1) {
                    length = i8;
                    break;
                }
                i8++;
            }
        }
        int[] iArr6 = this.f501f;
        if (length >= iArr6.length) {
            length = iArr6.length;
            int i9 = this.f499d * 2;
            this.f499d = i9;
            this.k = false;
            this.j = length - 1;
            this.h = Arrays.copyOf(this.h, i9);
            this.f501f = Arrays.copyOf(this.f501f, this.f499d);
            this.g = Arrays.copyOf(this.g, this.f499d);
        }
        this.f501f[length] = gVar.f524c;
        this.h[length] = f2;
        int[] iArr7 = this.g;
        if (i3 != -1) {
            iArr7[length] = iArr7[i3];
            iArr7[i3] = length;
        } else {
            iArr7[length] = this.i;
            this.i = length;
        }
        gVar.m++;
        gVar.a(this.f497b);
        this.f496a++;
        if (!this.k) {
            this.j++;
        }
        if (this.f496a >= this.f501f.length) {
            this.k = true;
        }
        int i10 = this.j;
        int[] iArr8 = this.f501f;
        if (i10 >= iArr8.length) {
            this.k = true;
            this.j = iArr8.length - 1;
        }
    }

    @Override // a.f.a.b.a
    public int k() {
        return this.f496a;
    }

    public String toString() {
        int i = this.i;
        String string = "";
        for (int i2 = 0; i != -1 && i2 < this.f496a; i2++) {
            StringBuilder sbD = b.a.a.a.a.d(b.a.a.a.a.c(string, " -> "));
            sbD.append(this.h[i]);
            sbD.append(" : ");
            StringBuilder sbD2 = b.a.a.a.a.d(sbD.toString());
            sbD2.append(this.f498c.f511d[this.f501f[i]]);
            string = sbD2.toString();
            i = this.g[i];
        }
        return string;
    }
}
