package h1;

import java.util.Arrays;
import l3.P;

/* JADX INFO: renamed from: h1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1341a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1342b f15158b;
    public final P c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f15157a = 0;
    public int d = 8;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f15159e = new int[8];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f15160f = new int[8];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float[] f15161g = new float[8];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f15162h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15163i = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f15164j = false;

    public C1341a(C1342b c1342b, P p10) {
        this.f15158b = c1342b;
        this.c = p10;
    }

    public final void a(e eVar, float f10, boolean z6) {
        if (f10 <= -0.001f || f10 >= 0.001f) {
            int i7 = this.f15162h;
            C1342b c1342b = this.f15158b;
            if (i7 == -1) {
                this.f15162h = 0;
                this.f15161g[0] = f10;
                this.f15159e[0] = eVar.f15185m;
                this.f15160f[0] = -1;
                eVar.f15193v++;
                eVar.a(c1342b);
                this.f15157a++;
                if (this.f15164j) {
                    return;
                }
                int i10 = this.f15163i + 1;
                this.f15163i = i10;
                int[] iArr = this.f15159e;
                if (i10 >= iArr.length) {
                    this.f15164j = true;
                    this.f15163i = iArr.length - 1;
                    return;
                }
                return;
            }
            int i11 = -1;
            for (int i12 = 0; i7 != -1 && i12 < this.f15157a; i12++) {
                int i13 = this.f15159e[i7];
                int i14 = eVar.f15185m;
                if (i13 == i14) {
                    float[] fArr = this.f15161g;
                    float f11 = fArr[i7] + f10;
                    if (f11 > -0.001f && f11 < 0.001f) {
                        f11 = 0.0f;
                    }
                    fArr[i7] = f11;
                    if (f11 == 0.0f) {
                        if (i7 == this.f15162h) {
                            this.f15162h = this.f15160f[i7];
                        } else {
                            int[] iArr2 = this.f15160f;
                            iArr2[i11] = iArr2[i7];
                        }
                        if (z6) {
                            eVar.b(c1342b);
                        }
                        if (this.f15164j) {
                            this.f15163i = i7;
                        }
                        eVar.f15193v--;
                        this.f15157a--;
                        return;
                    }
                    return;
                }
                if (i13 < i14) {
                    i11 = i7;
                }
                i7 = this.f15160f[i7];
            }
            int length = this.f15163i;
            int i15 = length + 1;
            if (this.f15164j) {
                int[] iArr3 = this.f15159e;
                if (iArr3[length] != -1) {
                    length = iArr3.length;
                }
            } else {
                length = i15;
            }
            int[] iArr4 = this.f15159e;
            if (length >= iArr4.length && this.f15157a < iArr4.length) {
                int i16 = 0;
                while (true) {
                    int[] iArr5 = this.f15159e;
                    if (i16 >= iArr5.length) {
                        break;
                    }
                    if (iArr5[i16] == -1) {
                        length = i16;
                        break;
                    }
                    i16++;
                }
            }
            int[] iArr6 = this.f15159e;
            if (length >= iArr6.length) {
                length = iArr6.length;
                int i17 = this.d * 2;
                this.d = i17;
                this.f15164j = false;
                this.f15163i = length - 1;
                this.f15161g = Arrays.copyOf(this.f15161g, i17);
                this.f15159e = Arrays.copyOf(this.f15159e, this.d);
                this.f15160f = Arrays.copyOf(this.f15160f, this.d);
            }
            this.f15159e[length] = eVar.f15185m;
            this.f15161g[length] = f10;
            if (i11 != -1) {
                int[] iArr7 = this.f15160f;
                iArr7[length] = iArr7[i11];
                iArr7[i11] = length;
            } else {
                this.f15160f[length] = this.f15162h;
                this.f15162h = length;
            }
            eVar.f15193v++;
            eVar.a(c1342b);
            this.f15157a++;
            if (!this.f15164j) {
                this.f15163i++;
            }
            int i18 = this.f15163i;
            int[] iArr8 = this.f15159e;
            if (i18 >= iArr8.length) {
                this.f15164j = true;
                this.f15163i = iArr8.length - 1;
            }
        }
    }

    public final void b() {
        int i7 = this.f15162h;
        for (int i10 = 0; i7 != -1 && i10 < this.f15157a; i10++) {
            e eVar = ((e[]) this.c.o)[this.f15159e[i7]];
            if (eVar != null) {
                eVar.b(this.f15158b);
            }
            i7 = this.f15160f[i7];
        }
        this.f15162h = -1;
        this.f15163i = -1;
        this.f15164j = false;
        this.f15157a = 0;
    }

    public final float c(e eVar) {
        int i7 = this.f15162h;
        for (int i10 = 0; i7 != -1 && i10 < this.f15157a; i10++) {
            if (this.f15159e[i7] == eVar.f15185m) {
                return this.f15161g[i7];
            }
            i7 = this.f15160f[i7];
        }
        return 0.0f;
    }

    public final int d() {
        return this.f15157a;
    }

    public final e e(int i7) {
        int i10 = this.f15162h;
        for (int i11 = 0; i10 != -1 && i11 < this.f15157a; i11++) {
            if (i11 == i7) {
                return ((e[]) this.c.o)[this.f15159e[i10]];
            }
            i10 = this.f15160f[i10];
        }
        return null;
    }

    public final float f(int i7) {
        int i10 = this.f15162h;
        for (int i11 = 0; i10 != -1 && i11 < this.f15157a; i11++) {
            if (i11 == i7) {
                return this.f15161g[i10];
            }
            i10 = this.f15160f[i10];
        }
        return 0.0f;
    }

    public final void g(e eVar, float f10) {
        if (f10 == 0.0f) {
            h(eVar, true);
            return;
        }
        int i7 = this.f15162h;
        C1342b c1342b = this.f15158b;
        if (i7 == -1) {
            this.f15162h = 0;
            this.f15161g[0] = f10;
            this.f15159e[0] = eVar.f15185m;
            this.f15160f[0] = -1;
            eVar.f15193v++;
            eVar.a(c1342b);
            this.f15157a++;
            if (this.f15164j) {
                return;
            }
            int i10 = this.f15163i + 1;
            this.f15163i = i10;
            int[] iArr = this.f15159e;
            if (i10 >= iArr.length) {
                this.f15164j = true;
                this.f15163i = iArr.length - 1;
                return;
            }
            return;
        }
        int i11 = -1;
        for (int i12 = 0; i7 != -1 && i12 < this.f15157a; i12++) {
            int i13 = this.f15159e[i7];
            int i14 = eVar.f15185m;
            if (i13 == i14) {
                this.f15161g[i7] = f10;
                return;
            }
            if (i13 < i14) {
                i11 = i7;
            }
            i7 = this.f15160f[i7];
        }
        int length = this.f15163i;
        int i15 = length + 1;
        if (this.f15164j) {
            int[] iArr2 = this.f15159e;
            if (iArr2[length] != -1) {
                length = iArr2.length;
            }
        } else {
            length = i15;
        }
        int[] iArr3 = this.f15159e;
        if (length >= iArr3.length && this.f15157a < iArr3.length) {
            int i16 = 0;
            while (true) {
                int[] iArr4 = this.f15159e;
                if (i16 >= iArr4.length) {
                    break;
                }
                if (iArr4[i16] == -1) {
                    length = i16;
                    break;
                }
                i16++;
            }
        }
        int[] iArr5 = this.f15159e;
        if (length >= iArr5.length) {
            length = iArr5.length;
            int i17 = this.d * 2;
            this.d = i17;
            this.f15164j = false;
            this.f15163i = length - 1;
            this.f15161g = Arrays.copyOf(this.f15161g, i17);
            this.f15159e = Arrays.copyOf(this.f15159e, this.d);
            this.f15160f = Arrays.copyOf(this.f15160f, this.d);
        }
        this.f15159e[length] = eVar.f15185m;
        this.f15161g[length] = f10;
        if (i11 != -1) {
            int[] iArr6 = this.f15160f;
            iArr6[length] = iArr6[i11];
            iArr6[i11] = length;
        } else {
            this.f15160f[length] = this.f15162h;
            this.f15162h = length;
        }
        eVar.f15193v++;
        eVar.a(c1342b);
        int i18 = this.f15157a + 1;
        this.f15157a = i18;
        if (!this.f15164j) {
            this.f15163i++;
        }
        int[] iArr7 = this.f15159e;
        if (i18 >= iArr7.length) {
            this.f15164j = true;
        }
        if (this.f15163i >= iArr7.length) {
            this.f15164j = true;
            this.f15163i = iArr7.length - 1;
        }
    }

    public final float h(e eVar, boolean z6) {
        int i7 = this.f15162h;
        if (i7 == -1) {
            return 0.0f;
        }
        int i10 = 0;
        int i11 = -1;
        while (i7 != -1 && i10 < this.f15157a) {
            if (this.f15159e[i7] == eVar.f15185m) {
                if (i7 == this.f15162h) {
                    this.f15162h = this.f15160f[i7];
                } else {
                    int[] iArr = this.f15160f;
                    iArr[i11] = iArr[i7];
                }
                if (z6) {
                    eVar.b(this.f15158b);
                }
                eVar.f15193v--;
                this.f15157a--;
                this.f15159e[i7] = -1;
                if (this.f15164j) {
                    this.f15163i = i7;
                }
                return this.f15161g[i7];
            }
            i10++;
            i11 = i7;
            i7 = this.f15160f[i7];
        }
        return 0.0f;
    }

    public final String toString() {
        int i7 = this.f15162h;
        String string = "";
        for (int i10 = 0; i7 != -1 && i10 < this.f15157a; i10++) {
            StringBuilder sbI = M5.b.i(android.hardware.bydauto.ac.tsimpl.a.h(string, " -> "));
            sbI.append(this.f15161g[i7]);
            sbI.append(" : ");
            StringBuilder sbI2 = M5.b.i(sbI.toString());
            sbI2.append(((e[]) this.c.o)[this.f15159e[i7]]);
            string = sbI2.toString();
            i7 = this.f15160f[i7];
        }
        return string;
    }
}
