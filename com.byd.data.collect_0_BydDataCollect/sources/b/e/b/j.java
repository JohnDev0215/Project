package b.e.b;

import b.e.b.b;
import java.util.Arrays;

/* JADX INFO: compiled from: SolverVariableValues.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static float f2404a = 0.001f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f2405b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2406c = 16;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2407d = 16;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int[] f2408e = new int[16];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int[] f2409f = new int[16];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int[] f2410g = new int[16];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    float[] f2411h = new float[16];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int[] f2412i = new int[16];
    int[] j = new int[16];
    int k = 0;
    int l = -1;
    private final b m;
    protected final c n;

    j(b bVar, c cVar) {
        this.m = bVar;
        this.n = cVar;
        clear();
    }

    private void l(i iVar, int i2) {
        int[] iArr;
        int i3 = iVar.f2392d % this.f2407d;
        int[] iArr2 = this.f2408e;
        int i4 = iArr2[i3];
        if (i4 == -1) {
            iArr2[i3] = i2;
        } else {
            while (true) {
                iArr = this.f2409f;
                if (iArr[i4] == -1) {
                    break;
                } else {
                    i4 = iArr[i4];
                }
            }
            iArr[i4] = i2;
        }
        this.f2409f[i2] = -1;
    }

    private void m(int i2, i iVar, float f2) {
        this.f2410g[i2] = iVar.f2392d;
        this.f2411h[i2] = f2;
        this.f2412i[i2] = -1;
        this.j[i2] = -1;
        iVar.a(this.m);
        iVar.n++;
        this.k++;
    }

    private int n() {
        for (int i2 = 0; i2 < this.f2406c; i2++) {
            if (this.f2410g[i2] == -1) {
                return i2;
            }
        }
        return -1;
    }

    private void o() {
        int i2 = this.f2406c * 2;
        this.f2410g = Arrays.copyOf(this.f2410g, i2);
        this.f2411h = Arrays.copyOf(this.f2411h, i2);
        this.f2412i = Arrays.copyOf(this.f2412i, i2);
        this.j = Arrays.copyOf(this.j, i2);
        this.f2409f = Arrays.copyOf(this.f2409f, i2);
        for (int i3 = this.f2406c; i3 < i2; i3++) {
            this.f2410g[i3] = -1;
            this.f2409f[i3] = -1;
        }
        this.f2406c = i2;
    }

    private void q(int i2, i iVar, float f2) {
        int iN = n();
        m(iN, iVar, f2);
        if (i2 != -1) {
            this.f2412i[iN] = i2;
            int[] iArr = this.j;
            iArr[iN] = iArr[i2];
            iArr[i2] = iN;
        } else {
            this.f2412i[iN] = -1;
            if (this.k > 0) {
                this.j[iN] = this.l;
                this.l = iN;
            } else {
                this.j[iN] = -1;
            }
        }
        int[] iArr2 = this.j;
        if (iArr2[iN] != -1) {
            this.f2412i[iArr2[iN]] = iN;
        }
        l(iVar, iN);
    }

    private void r(i iVar) {
        int[] iArr;
        int i2 = iVar.f2392d;
        int i3 = i2 % this.f2407d;
        int[] iArr2 = this.f2408e;
        int i4 = iArr2[i3];
        if (i4 == -1) {
            return;
        }
        if (this.f2410g[i4] == i2) {
            int[] iArr3 = this.f2409f;
            iArr2[i3] = iArr3[i4];
            iArr3[i4] = -1;
            return;
        }
        while (true) {
            iArr = this.f2409f;
            if (iArr[i4] == -1 || this.f2410g[iArr[i4]] == i2) {
                break;
            } else {
                i4 = iArr[i4];
            }
        }
        int i5 = iArr[i4];
        if (i5 == -1 || this.f2410g[i5] != i2) {
            return;
        }
        iArr[i4] = iArr[i5];
        iArr[i5] = -1;
    }

    @Override // b.e.b.b.a
    public float a(int i2) {
        int i3 = this.k;
        int i4 = this.l;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i5 == i2) {
                return this.f2411h[i4];
            }
            i4 = this.j[i4];
            if (i4 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // b.e.b.b.a
    public float b(b bVar, boolean z) {
        float fG = g(bVar.f2360a);
        d(bVar.f2360a, z);
        j jVar = (j) bVar.f2364e;
        int iK = jVar.k();
        int i2 = 0;
        int i3 = 0;
        while (i2 < iK) {
            int[] iArr = jVar.f2410g;
            if (iArr[i3] != -1) {
                e(this.n.f2369d[iArr[i3]], jVar.f2411h[i3] * fG, z);
                i2++;
            }
            i3++;
        }
        return fG;
    }

    @Override // b.e.b.b.a
    public void c(i iVar, float f2) {
        float f3 = f2404a;
        if (f2 > (-f3) && f2 < f3) {
            d(iVar, true);
            return;
        }
        if (this.k == 0) {
            m(0, iVar, f2);
            l(iVar, 0);
            this.l = 0;
            return;
        }
        int iP = p(iVar);
        if (iP != -1) {
            this.f2411h[iP] = f2;
            return;
        }
        if (this.k + 1 >= this.f2406c) {
            o();
        }
        int i2 = this.k;
        int i3 = this.l;
        int i4 = -1;
        for (int i5 = 0; i5 < i2; i5++) {
            int[] iArr = this.f2410g;
            int i6 = iArr[i3];
            int i7 = iVar.f2392d;
            if (i6 == i7) {
                this.f2411h[i3] = f2;
                return;
            }
            if (iArr[i3] < i7) {
                i4 = i3;
            }
            i3 = this.j[i3];
            if (i3 == -1) {
                break;
            }
        }
        q(i4, iVar, f2);
    }

    @Override // b.e.b.b.a
    public void clear() {
        int i2 = this.k;
        for (int i3 = 0; i3 < i2; i3++) {
            i iVarF = f(i3);
            if (iVarF != null) {
                iVarF.c(this.m);
            }
        }
        for (int i4 = 0; i4 < this.f2406c; i4++) {
            this.f2410g[i4] = -1;
            this.f2409f[i4] = -1;
        }
        for (int i5 = 0; i5 < this.f2407d; i5++) {
            this.f2408e[i5] = -1;
        }
        this.k = 0;
        this.l = -1;
    }

    @Override // b.e.b.b.a
    public float d(i iVar, boolean z) {
        int iP = p(iVar);
        if (iP == -1) {
            return 0.0f;
        }
        r(iVar);
        float f2 = this.f2411h[iP];
        if (this.l == iP) {
            this.l = this.j[iP];
        }
        this.f2410g[iP] = -1;
        int[] iArr = this.f2412i;
        if (iArr[iP] != -1) {
            int[] iArr2 = this.j;
            iArr2[iArr[iP]] = iArr2[iP];
        }
        int[] iArr3 = this.j;
        if (iArr3[iP] != -1) {
            iArr[iArr3[iP]] = iArr[iP];
        }
        this.k--;
        iVar.n--;
        if (z) {
            iVar.c(this.m);
        }
        return f2;
    }

    @Override // b.e.b.b.a
    public void e(i iVar, float f2, boolean z) {
        float f3 = f2404a;
        if (f2 <= (-f3) || f2 >= f3) {
            int iP = p(iVar);
            if (iP == -1) {
                c(iVar, f2);
                return;
            }
            float[] fArr = this.f2411h;
            fArr[iP] = fArr[iP] + f2;
            float f4 = fArr[iP];
            float f5 = f2404a;
            if (f4 <= (-f5) || fArr[iP] >= f5) {
                return;
            }
            fArr[iP] = 0.0f;
            d(iVar, z);
        }
    }

    @Override // b.e.b.b.a
    public i f(int i2) {
        int i3 = this.k;
        if (i3 == 0) {
            return null;
        }
        int i4 = this.l;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i5 == i2 && i4 != -1) {
                return this.n.f2369d[this.f2410g[i4]];
            }
            i4 = this.j[i4];
            if (i4 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // b.e.b.b.a
    public float g(i iVar) {
        int iP = p(iVar);
        if (iP != -1) {
            return this.f2411h[iP];
        }
        return 0.0f;
    }

    @Override // b.e.b.b.a
    public boolean h(i iVar) {
        return p(iVar) != -1;
    }

    @Override // b.e.b.b.a
    public void i(float f2) {
        int i2 = this.k;
        int i3 = this.l;
        for (int i4 = 0; i4 < i2; i4++) {
            float[] fArr = this.f2411h;
            fArr[i3] = fArr[i3] / f2;
            i3 = this.j[i3];
            if (i3 == -1) {
                return;
            }
        }
    }

    @Override // b.e.b.b.a
    public void j() {
        int i2 = this.k;
        int i3 = this.l;
        for (int i4 = 0; i4 < i2; i4++) {
            float[] fArr = this.f2411h;
            fArr[i3] = fArr[i3] * (-1.0f);
            i3 = this.j[i3];
            if (i3 == -1) {
                return;
            }
        }
    }

    @Override // b.e.b.b.a
    public int k() {
        return this.k;
    }

    public int p(i iVar) {
        int[] iArr;
        if (this.k == 0) {
            return -1;
        }
        int i2 = iVar.f2392d;
        int i3 = this.f2408e[i2 % this.f2407d];
        if (i3 == -1) {
            return -1;
        }
        if (this.f2410g[i3] == i2) {
            return i3;
        }
        while (true) {
            iArr = this.f2409f;
            if (iArr[i3] == -1 || this.f2410g[iArr[i3]] == i2) {
                break;
            }
            i3 = iArr[i3];
        }
        if (iArr[i3] != -1 && this.f2410g[iArr[i3]] == i2) {
            return iArr[i3];
        }
        return -1;
    }

    public String toString() {
        String str = hashCode() + " { ";
        int i2 = this.k;
        for (int i3 = 0; i3 < i2; i3++) {
            i iVarF = f(i3);
            if (iVarF != null) {
                String str2 = str + iVarF + " = " + a(i3) + " ";
                int iP = p(iVarF);
                String str3 = str2 + "[p: ";
                String str4 = (this.f2412i[iP] != -1 ? str3 + this.n.f2369d[this.f2410g[this.f2412i[iP]]] : str3 + "none") + ", n: ";
                str = (this.j[iP] != -1 ? str4 + this.n.f2369d[this.f2410g[this.j[iP]]] : str4 + "none") + "]";
            }
        }
        return str + " }";
    }
}
