package o;

import java.util.Arrays;
import o.b;

/* JADX INFO: compiled from: ArrayLinkedVariables.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static float f8547l = 0.001f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f8549b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final c f8550c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f8548a = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f8551d = 8;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private i f8552e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int[] f8553f = new int[8];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int[] f8554g = new int[8];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float[] f8555h = new float[8];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f8556i = -1;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f8557j = -1;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f8558k = false;

    a(b bVar, c cVar) {
        this.f8549b = bVar;
        this.f8550c = cVar;
    }

    @Override // o.b.a
    public float a(int i7) {
        int i8 = this.f8556i;
        for (int i9 = 0; i8 != -1 && i9 < this.f8548a; i9++) {
            if (i9 == i7) {
                return this.f8555h[i8];
            }
            i8 = this.f8554g[i8];
        }
        return 0.0f;
    }

    @Override // o.b.a
    public void b(i iVar, float f7, boolean z6) {
        float f8 = f8547l;
        if (f7 <= (-f8) || f7 >= f8) {
            int i7 = this.f8556i;
            if (i7 == -1) {
                this.f8556i = 0;
                this.f8555h[0] = f7;
                this.f8553f[0] = iVar.f8610h;
                this.f8554g[0] = -1;
                iVar.f8620r++;
                iVar.a(this.f8549b);
                this.f8548a++;
                if (this.f8558k) {
                    return;
                }
                int i8 = this.f8557j + 1;
                this.f8557j = i8;
                int[] iArr = this.f8553f;
                if (i8 >= iArr.length) {
                    this.f8558k = true;
                    this.f8557j = iArr.length - 1;
                    return;
                }
                return;
            }
            int i9 = -1;
            for (int i10 = 0; i7 != -1 && i10 < this.f8548a; i10++) {
                int[] iArr2 = this.f8553f;
                int i11 = iArr2[i7];
                int i12 = iVar.f8610h;
                if (i11 == i12) {
                    float[] fArr = this.f8555h;
                    float f9 = fArr[i7] + f7;
                    float f10 = f8547l;
                    if (f9 > (-f10) && f9 < f10) {
                        f9 = 0.0f;
                    }
                    fArr[i7] = f9;
                    if (f9 == 0.0f) {
                        if (i7 == this.f8556i) {
                            this.f8556i = this.f8554g[i7];
                        } else {
                            int[] iArr3 = this.f8554g;
                            iArr3[i9] = iArr3[i7];
                        }
                        if (z6) {
                            iVar.d(this.f8549b);
                        }
                        if (this.f8558k) {
                            this.f8557j = i7;
                        }
                        iVar.f8620r--;
                        this.f8548a--;
                        return;
                    }
                    return;
                }
                if (iArr2[i7] < i12) {
                    i9 = i7;
                }
                i7 = this.f8554g[i7];
            }
            int length = this.f8557j;
            int i13 = length + 1;
            if (this.f8558k) {
                int[] iArr4 = this.f8553f;
                if (iArr4[length] != -1) {
                    length = iArr4.length;
                }
            } else {
                length = i13;
            }
            int[] iArr5 = this.f8553f;
            if (length >= iArr5.length && this.f8548a < iArr5.length) {
                int i14 = 0;
                while (true) {
                    int[] iArr6 = this.f8553f;
                    if (i14 >= iArr6.length) {
                        break;
                    }
                    if (iArr6[i14] == -1) {
                        length = i14;
                        break;
                    }
                    i14++;
                }
            }
            int[] iArr7 = this.f8553f;
            if (length >= iArr7.length) {
                length = iArr7.length;
                int i15 = this.f8551d * 2;
                this.f8551d = i15;
                this.f8558k = false;
                this.f8557j = length - 1;
                this.f8555h = Arrays.copyOf(this.f8555h, i15);
                this.f8553f = Arrays.copyOf(this.f8553f, this.f8551d);
                this.f8554g = Arrays.copyOf(this.f8554g, this.f8551d);
            }
            this.f8553f[length] = iVar.f8610h;
            this.f8555h[length] = f7;
            if (i9 != -1) {
                int[] iArr8 = this.f8554g;
                iArr8[length] = iArr8[i9];
                iArr8[i9] = length;
            } else {
                this.f8554g[length] = this.f8556i;
                this.f8556i = length;
            }
            iVar.f8620r++;
            iVar.a(this.f8549b);
            this.f8548a++;
            if (!this.f8558k) {
                this.f8557j++;
            }
            int i16 = this.f8557j;
            int[] iArr9 = this.f8553f;
            if (i16 >= iArr9.length) {
                this.f8558k = true;
                this.f8557j = iArr9.length - 1;
            }
        }
    }

    @Override // o.b.a
    public boolean c(i iVar) {
        int i7 = this.f8556i;
        if (i7 == -1) {
            return false;
        }
        for (int i8 = 0; i7 != -1 && i8 < this.f8548a; i8++) {
            if (this.f8553f[i7] == iVar.f8610h) {
                return true;
            }
            i7 = this.f8554g[i7];
        }
        return false;
    }

    @Override // o.b.a
    public final void clear() {
        int i7 = this.f8556i;
        for (int i8 = 0; i7 != -1 && i8 < this.f8548a; i8++) {
            i iVar = this.f8550c.f8568d[this.f8553f[i7]];
            if (iVar != null) {
                iVar.d(this.f8549b);
            }
            i7 = this.f8554g[i7];
        }
        this.f8556i = -1;
        this.f8557j = -1;
        this.f8558k = false;
        this.f8548a = 0;
    }

    @Override // o.b.a
    public float d(b bVar, boolean z6) {
        float f7 = f(bVar.f8559a);
        i(bVar.f8559a, z6);
        b.a aVar = bVar.f8563e;
        int iK = aVar.k();
        for (int i7 = 0; i7 < iK; i7++) {
            i iVarG = aVar.g(i7);
            b(iVarG, aVar.f(iVarG) * f7, z6);
        }
        return f7;
    }

    @Override // o.b.a
    public final void e(i iVar, float f7) {
        if (f7 == 0.0f) {
            i(iVar, true);
            return;
        }
        int i7 = this.f8556i;
        if (i7 == -1) {
            this.f8556i = 0;
            this.f8555h[0] = f7;
            this.f8553f[0] = iVar.f8610h;
            this.f8554g[0] = -1;
            iVar.f8620r++;
            iVar.a(this.f8549b);
            this.f8548a++;
            if (this.f8558k) {
                return;
            }
            int i8 = this.f8557j + 1;
            this.f8557j = i8;
            int[] iArr = this.f8553f;
            if (i8 >= iArr.length) {
                this.f8558k = true;
                this.f8557j = iArr.length - 1;
                return;
            }
            return;
        }
        int i9 = -1;
        for (int i10 = 0; i7 != -1 && i10 < this.f8548a; i10++) {
            int[] iArr2 = this.f8553f;
            int i11 = iArr2[i7];
            int i12 = iVar.f8610h;
            if (i11 == i12) {
                this.f8555h[i7] = f7;
                return;
            }
            if (iArr2[i7] < i12) {
                i9 = i7;
            }
            i7 = this.f8554g[i7];
        }
        int length = this.f8557j;
        int i13 = length + 1;
        if (this.f8558k) {
            int[] iArr3 = this.f8553f;
            if (iArr3[length] != -1) {
                length = iArr3.length;
            }
        } else {
            length = i13;
        }
        int[] iArr4 = this.f8553f;
        if (length >= iArr4.length && this.f8548a < iArr4.length) {
            int i14 = 0;
            while (true) {
                int[] iArr5 = this.f8553f;
                if (i14 >= iArr5.length) {
                    break;
                }
                if (iArr5[i14] == -1) {
                    length = i14;
                    break;
                }
                i14++;
            }
        }
        int[] iArr6 = this.f8553f;
        if (length >= iArr6.length) {
            length = iArr6.length;
            int i15 = this.f8551d * 2;
            this.f8551d = i15;
            this.f8558k = false;
            this.f8557j = length - 1;
            this.f8555h = Arrays.copyOf(this.f8555h, i15);
            this.f8553f = Arrays.copyOf(this.f8553f, this.f8551d);
            this.f8554g = Arrays.copyOf(this.f8554g, this.f8551d);
        }
        this.f8553f[length] = iVar.f8610h;
        this.f8555h[length] = f7;
        if (i9 != -1) {
            int[] iArr7 = this.f8554g;
            iArr7[length] = iArr7[i9];
            iArr7[i9] = length;
        } else {
            this.f8554g[length] = this.f8556i;
            this.f8556i = length;
        }
        iVar.f8620r++;
        iVar.a(this.f8549b);
        int i16 = this.f8548a + 1;
        this.f8548a = i16;
        if (!this.f8558k) {
            this.f8557j++;
        }
        int[] iArr8 = this.f8553f;
        if (i16 >= iArr8.length) {
            this.f8558k = true;
        }
        if (this.f8557j >= iArr8.length) {
            this.f8558k = true;
            this.f8557j = iArr8.length - 1;
        }
    }

    @Override // o.b.a
    public final float f(i iVar) {
        int i7 = this.f8556i;
        for (int i8 = 0; i7 != -1 && i8 < this.f8548a; i8++) {
            if (this.f8553f[i7] == iVar.f8610h) {
                return this.f8555h[i7];
            }
            i7 = this.f8554g[i7];
        }
        return 0.0f;
    }

    @Override // o.b.a
    public i g(int i7) {
        int i8 = this.f8556i;
        for (int i9 = 0; i8 != -1 && i9 < this.f8548a; i9++) {
            if (i9 == i7) {
                return this.f8550c.f8568d[this.f8553f[i8]];
            }
            i8 = this.f8554g[i8];
        }
        return null;
    }

    @Override // o.b.a
    public void h(float f7) {
        int i7 = this.f8556i;
        for (int i8 = 0; i7 != -1 && i8 < this.f8548a; i8++) {
            float[] fArr = this.f8555h;
            fArr[i7] = fArr[i7] / f7;
            i7 = this.f8554g[i7];
        }
    }

    @Override // o.b.a
    public final float i(i iVar, boolean z6) {
        if (this.f8552e == iVar) {
            this.f8552e = null;
        }
        int i7 = this.f8556i;
        if (i7 == -1) {
            return 0.0f;
        }
        int i8 = 0;
        int i9 = -1;
        while (i7 != -1 && i8 < this.f8548a) {
            if (this.f8553f[i7] == iVar.f8610h) {
                if (i7 == this.f8556i) {
                    this.f8556i = this.f8554g[i7];
                } else {
                    int[] iArr = this.f8554g;
                    iArr[i9] = iArr[i7];
                }
                if (z6) {
                    iVar.d(this.f8549b);
                }
                iVar.f8620r--;
                this.f8548a--;
                this.f8553f[i7] = -1;
                if (this.f8558k) {
                    this.f8557j = i7;
                }
                return this.f8555h[i7];
            }
            i8++;
            i9 = i7;
            i7 = this.f8554g[i7];
        }
        return 0.0f;
    }

    @Override // o.b.a
    public void j() {
        int i7 = this.f8556i;
        for (int i8 = 0; i7 != -1 && i8 < this.f8548a; i8++) {
            float[] fArr = this.f8555h;
            fArr[i7] = fArr[i7] * (-1.0f);
            i7 = this.f8554g[i7];
        }
    }

    @Override // o.b.a
    public int k() {
        return this.f8548a;
    }

    public String toString() {
        int i7 = this.f8556i;
        String str = "";
        for (int i8 = 0; i7 != -1 && i8 < this.f8548a; i8++) {
            str = ((str + " -> ") + this.f8555h[i7] + " : ") + this.f8550c.f8568d[this.f8553f[i7]];
            i7 = this.f8554g[i7];
        }
        return str;
    }
}
