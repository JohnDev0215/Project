package o;

import java.util.Arrays;
import o.b;

/* JADX INFO: compiled from: SolverVariableValues.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements b.a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static float f8626n = 0.001f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f8627a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f8628b = 16;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f8629c = 16;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int[] f8630d = new int[16];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int[] f8631e = new int[16];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int[] f8632f = new int[16];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    float[] f8633g = new float[16];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int[] f8634h = new int[16];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int[] f8635i = new int[16];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    int f8636j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    int f8637k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final b f8638l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    protected final c f8639m;

    j(b bVar, c cVar) {
        this.f8638l = bVar;
        this.f8639m = cVar;
        clear();
    }

    private void l(i iVar, int i7) {
        int[] iArr;
        int i8 = iVar.f8610h % this.f8629c;
        int[] iArr2 = this.f8630d;
        int i9 = iArr2[i8];
        if (i9 == -1) {
            iArr2[i8] = i7;
        } else {
            while (true) {
                iArr = this.f8631e;
                if (iArr[i9] == -1) {
                    break;
                } else {
                    i9 = iArr[i9];
                }
            }
            iArr[i9] = i7;
        }
        this.f8631e[i7] = -1;
    }

    private void m(int i7, i iVar, float f7) {
        this.f8632f[i7] = iVar.f8610h;
        this.f8633g[i7] = f7;
        this.f8634h[i7] = -1;
        this.f8635i[i7] = -1;
        iVar.a(this.f8638l);
        iVar.f8620r++;
        this.f8636j++;
    }

    private int n() {
        for (int i7 = 0; i7 < this.f8628b; i7++) {
            if (this.f8632f[i7] == -1) {
                return i7;
            }
        }
        return -1;
    }

    private void o() {
        int i7 = this.f8628b * 2;
        this.f8632f = Arrays.copyOf(this.f8632f, i7);
        this.f8633g = Arrays.copyOf(this.f8633g, i7);
        this.f8634h = Arrays.copyOf(this.f8634h, i7);
        this.f8635i = Arrays.copyOf(this.f8635i, i7);
        this.f8631e = Arrays.copyOf(this.f8631e, i7);
        for (int i8 = this.f8628b; i8 < i7; i8++) {
            this.f8632f[i8] = -1;
            this.f8631e[i8] = -1;
        }
        this.f8628b = i7;
    }

    private void q(int i7, i iVar, float f7) {
        int iN = n();
        m(iN, iVar, f7);
        if (i7 != -1) {
            this.f8634h[iN] = i7;
            int[] iArr = this.f8635i;
            iArr[iN] = iArr[i7];
            iArr[i7] = iN;
        } else {
            this.f8634h[iN] = -1;
            if (this.f8636j > 0) {
                this.f8635i[iN] = this.f8637k;
                this.f8637k = iN;
            } else {
                this.f8635i[iN] = -1;
            }
        }
        int[] iArr2 = this.f8635i;
        if (iArr2[iN] != -1) {
            this.f8634h[iArr2[iN]] = iN;
        }
        l(iVar, iN);
    }

    private void r(i iVar) {
        int[] iArr;
        int i7 = iVar.f8610h;
        int i8 = i7 % this.f8629c;
        int[] iArr2 = this.f8630d;
        int i9 = iArr2[i8];
        if (i9 == -1) {
            return;
        }
        if (this.f8632f[i9] == i7) {
            int[] iArr3 = this.f8631e;
            iArr2[i8] = iArr3[i9];
            iArr3[i9] = -1;
            return;
        }
        while (true) {
            iArr = this.f8631e;
            if (iArr[i9] == -1 || this.f8632f[iArr[i9]] == i7) {
                break;
            } else {
                i9 = iArr[i9];
            }
        }
        int i10 = iArr[i9];
        if (i10 == -1 || this.f8632f[i10] != i7) {
            return;
        }
        iArr[i9] = iArr[i10];
        iArr[i10] = -1;
    }

    @Override // o.b.a
    public float a(int i7) {
        int i8 = this.f8636j;
        int i9 = this.f8637k;
        for (int i10 = 0; i10 < i8; i10++) {
            if (i10 == i7) {
                return this.f8633g[i9];
            }
            i9 = this.f8635i[i9];
            if (i9 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // o.b.a
    public void b(i iVar, float f7, boolean z6) {
        float f8 = f8626n;
        if (f7 <= (-f8) || f7 >= f8) {
            int iP = p(iVar);
            if (iP == -1) {
                e(iVar, f7);
                return;
            }
            float[] fArr = this.f8633g;
            fArr[iP] = fArr[iP] + f7;
            float f9 = fArr[iP];
            float f10 = f8626n;
            if (f9 <= (-f10) || fArr[iP] >= f10) {
                return;
            }
            fArr[iP] = 0.0f;
            i(iVar, z6);
        }
    }

    @Override // o.b.a
    public boolean c(i iVar) {
        return p(iVar) != -1;
    }

    @Override // o.b.a
    public void clear() {
        int i7 = this.f8636j;
        for (int i8 = 0; i8 < i7; i8++) {
            i iVarG = g(i8);
            if (iVarG != null) {
                iVarG.d(this.f8638l);
            }
        }
        for (int i9 = 0; i9 < this.f8628b; i9++) {
            this.f8632f[i9] = -1;
            this.f8631e[i9] = -1;
        }
        for (int i10 = 0; i10 < this.f8629c; i10++) {
            this.f8630d[i10] = -1;
        }
        this.f8636j = 0;
        this.f8637k = -1;
    }

    @Override // o.b.a
    public float d(b bVar, boolean z6) {
        float f7 = f(bVar.f8559a);
        i(bVar.f8559a, z6);
        j jVar = (j) bVar.f8563e;
        int iK = jVar.k();
        int i7 = 0;
        int i8 = 0;
        while (i7 < iK) {
            int[] iArr = jVar.f8632f;
            if (iArr[i8] != -1) {
                b(this.f8639m.f8568d[iArr[i8]], jVar.f8633g[i8] * f7, z6);
                i7++;
            }
            i8++;
        }
        return f7;
    }

    @Override // o.b.a
    public void e(i iVar, float f7) {
        float f8 = f8626n;
        if (f7 > (-f8) && f7 < f8) {
            i(iVar, true);
            return;
        }
        if (this.f8636j == 0) {
            m(0, iVar, f7);
            l(iVar, 0);
            this.f8637k = 0;
            return;
        }
        int iP = p(iVar);
        if (iP != -1) {
            this.f8633g[iP] = f7;
            return;
        }
        if (this.f8636j + 1 >= this.f8628b) {
            o();
        }
        int i7 = this.f8636j;
        int i8 = this.f8637k;
        int i9 = -1;
        for (int i10 = 0; i10 < i7; i10++) {
            int[] iArr = this.f8632f;
            int i11 = iArr[i8];
            int i12 = iVar.f8610h;
            if (i11 == i12) {
                this.f8633g[i8] = f7;
                return;
            }
            if (iArr[i8] < i12) {
                i9 = i8;
            }
            i8 = this.f8635i[i8];
            if (i8 == -1) {
                break;
            }
        }
        q(i9, iVar, f7);
    }

    @Override // o.b.a
    public float f(i iVar) {
        int iP = p(iVar);
        if (iP != -1) {
            return this.f8633g[iP];
        }
        return 0.0f;
    }

    @Override // o.b.a
    public i g(int i7) {
        int i8 = this.f8636j;
        if (i8 == 0) {
            return null;
        }
        int i9 = this.f8637k;
        for (int i10 = 0; i10 < i8; i10++) {
            if (i10 == i7 && i9 != -1) {
                return this.f8639m.f8568d[this.f8632f[i9]];
            }
            i9 = this.f8635i[i9];
            if (i9 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // o.b.a
    public void h(float f7) {
        int i7 = this.f8636j;
        int i8 = this.f8637k;
        for (int i9 = 0; i9 < i7; i9++) {
            float[] fArr = this.f8633g;
            fArr[i8] = fArr[i8] / f7;
            i8 = this.f8635i[i8];
            if (i8 == -1) {
                return;
            }
        }
    }

    @Override // o.b.a
    public float i(i iVar, boolean z6) {
        int iP = p(iVar);
        if (iP == -1) {
            return 0.0f;
        }
        r(iVar);
        float f7 = this.f8633g[iP];
        if (this.f8637k == iP) {
            this.f8637k = this.f8635i[iP];
        }
        this.f8632f[iP] = -1;
        int[] iArr = this.f8634h;
        if (iArr[iP] != -1) {
            int[] iArr2 = this.f8635i;
            iArr2[iArr[iP]] = iArr2[iP];
        }
        int[] iArr3 = this.f8635i;
        if (iArr3[iP] != -1) {
            iArr[iArr3[iP]] = iArr[iP];
        }
        this.f8636j--;
        iVar.f8620r--;
        if (z6) {
            iVar.d(this.f8638l);
        }
        return f7;
    }

    @Override // o.b.a
    public void j() {
        int i7 = this.f8636j;
        int i8 = this.f8637k;
        for (int i9 = 0; i9 < i7; i9++) {
            float[] fArr = this.f8633g;
            fArr[i8] = fArr[i8] * (-1.0f);
            i8 = this.f8635i[i8];
            if (i8 == -1) {
                return;
            }
        }
    }

    @Override // o.b.a
    public int k() {
        return this.f8636j;
    }

    public int p(i iVar) {
        int[] iArr;
        if (this.f8636j != 0 && iVar != null) {
            int i7 = iVar.f8610h;
            int i8 = this.f8630d[i7 % this.f8629c];
            if (i8 == -1) {
                return -1;
            }
            if (this.f8632f[i8] == i7) {
                return i8;
            }
            while (true) {
                iArr = this.f8631e;
                if (iArr[i8] == -1 || this.f8632f[iArr[i8]] == i7) {
                    break;
                }
                i8 = iArr[i8];
            }
            if (iArr[i8] != -1 && this.f8632f[iArr[i8]] == i7) {
                return iArr[i8];
            }
        }
        return -1;
    }

    public String toString() {
        String str = hashCode() + " { ";
        int i7 = this.f8636j;
        for (int i8 = 0; i8 < i7; i8++) {
            i iVarG = g(i8);
            if (iVarG != null) {
                String str2 = str + iVarG + " = " + a(i8) + " ";
                int iP = p(iVarG);
                String str3 = str2 + "[p: ";
                String str4 = (this.f8634h[iP] != -1 ? str3 + this.f8639m.f8568d[this.f8632f[this.f8634h[iP]]] : str3 + "none") + ", n: ";
                str = (this.f8635i[iP] != -1 ? str4 + this.f8639m.f8568d[this.f8632f[this.f8635i[iP]]] : str4 + "none") + "]";
            }
        }
        return str + " }";
    }
}
