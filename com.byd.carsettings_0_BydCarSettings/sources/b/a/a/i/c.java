package b.a.a.i;

/* JADX INFO: compiled from: ClassWriter.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f192a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f193b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f194c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e[] f195d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f196e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final e f197f;
    public final e g;
    public final e h;
    public int i;
    public int j;
    public String k;
    public int l;
    public int m;
    public int[] n;
    public d o;
    public d p;
    public i q;
    public i r;

    public c() {
        this(0);
    }

    public final e a(e eVar) {
        e[] eVarArr = this.f195d;
        e eVar2 = eVarArr[eVar.h % eVarArr.length];
        while (eVar2 != null && (eVar2.f203b != eVar.f203b || !eVar.a(eVar2))) {
            eVar2 = eVar2.i;
        }
        return eVar2;
    }

    public e b(String str) {
        this.g.c(7, str, null, null);
        e eVarA = a(this.g);
        if (eVarA != null) {
            return eVarA;
        }
        this.f194c.c(7, h(str));
        int i = this.f193b;
        this.f193b = i + 1;
        e eVar = new e(i, this.g);
        i(eVar);
        return eVar;
    }

    public e c(Object obj) {
        if (!(obj instanceof Integer)) {
            if (obj instanceof String) {
                return g((String) obj);
            }
            if (obj instanceof j) {
                j jVar = (j) obj;
                return b(jVar.j == 10 ? jVar.f() : jVar.d());
            }
            throw new IllegalArgumentException("value " + obj);
        }
        int iIntValue = ((Integer) obj).intValue();
        this.f197f.b(iIntValue);
        e eVarA = a(this.f197f);
        if (eVarA != null) {
            return eVarA;
        }
        this.f194c.d(3).f(iIntValue);
        int i = this.f193b;
        this.f193b = i + 1;
        e eVar = new e(i, this.f197f);
        i(eVar);
        return eVar;
    }

    public e d(String str, String str2, String str3) {
        this.h.c(9, str, str2, str3);
        e eVarA = a(this.h);
        if (eVarA != null) {
            return eVarA;
        }
        int i = b(str).f202a;
        this.f194c.c(9, i).g(f(str2, str3).f202a);
        int i2 = this.f193b;
        this.f193b = i2 + 1;
        e eVar = new e(i2, this.h);
        i(eVar);
        return eVar;
    }

    public e e(String str, String str2, String str3, boolean z) {
        int i = z ? 11 : 10;
        this.h.c(i, str, str2, str3);
        e eVarA = a(this.h);
        if (eVarA != null) {
            return eVarA;
        }
        this.f194c.c(i, b(str).f202a).g(f(str2, str3).f202a);
        int i2 = this.f193b;
        this.f193b = i2 + 1;
        e eVar = new e(i2, this.h);
        i(eVar);
        return eVar;
    }

    public e f(String str, String str2) {
        this.g.c(12, str, str2, null);
        e eVarA = a(this.g);
        if (eVarA != null) {
            return eVarA;
        }
        int iH = h(str);
        this.f194c.c(12, iH).g(h(str2));
        int i = this.f193b;
        this.f193b = i + 1;
        e eVar = new e(i, this.g);
        i(eVar);
        return eVar;
    }

    public final e g(String str) {
        this.g.c(8, str, null, null);
        e eVarA = a(this.g);
        if (eVarA != null) {
            return eVarA;
        }
        this.f194c.c(8, h(str));
        int i = this.f193b;
        this.f193b = i + 1;
        e eVar = new e(i, this.g);
        i(eVar);
        return eVar;
    }

    public int h(String str) {
        this.f197f.c(1, str, null, null);
        e eVarA = a(this.f197f);
        if (eVarA == null) {
            this.f194c.d(1).h(str);
            int i = this.f193b;
            this.f193b = i + 1;
            eVarA = new e(i, this.f197f);
            i(eVarA);
        }
        return eVarA.f202a;
    }

    public final void i(e eVar) {
        if (this.f193b > this.f196e) {
            int length = this.f195d.length;
            int i = (length * 2) + 1;
            e[] eVarArr = new e[i];
            for (int i2 = length - 1; i2 >= 0; i2--) {
                e eVar2 = this.f195d[i2];
                while (eVar2 != null) {
                    int i3 = eVar2.h % i;
                    e eVar3 = eVar2.i;
                    eVar2.i = eVarArr[i3];
                    eVarArr[i3] = eVar2;
                    eVar2 = eVar3;
                }
            }
            this.f195d = eVarArr;
            this.f196e = (int) (((double) i) * 0.75d);
        }
        int i4 = eVar.h;
        e[] eVarArr2 = this.f195d;
        int length2 = i4 % eVarArr2.length;
        eVar.i = eVarArr2[length2];
        eVarArr2[length2] = eVar;
    }

    public byte[] j() {
        int iM = (this.m * 2) + 24;
        int i = 0;
        for (d dVar = this.o; dVar != null; dVar = dVar.f198a) {
            i++;
            iM += dVar.a();
        }
        int i2 = 0;
        for (i iVar = this.q; iVar != null; iVar = iVar.f217a) {
            i2++;
            iM += iVar.m();
        }
        a aVar = new a(iM + this.f194c.f185b);
        aVar.f(-889275714).f(this.f192a);
        a aVarG = aVar.g(this.f193b);
        a aVar2 = this.f194c;
        aVarG.e(aVar2.f184a, 0, aVar2.f185b);
        aVar.g(this.i & (-393217)).g(this.j).g(this.l);
        aVar.g(this.m);
        for (int i3 = 0; i3 < this.m; i3++) {
            aVar.g(this.n[i3]);
        }
        aVar.g(i);
        for (d dVar2 = this.o; dVar2 != null; dVar2 = dVar2.f198a) {
            dVar2.b(aVar);
        }
        aVar.g(i2);
        for (i iVar2 = this.q; iVar2 != null; iVar2 = iVar2.f217a) {
            iVar2.n(aVar);
        }
        aVar.g(0);
        return aVar.f184a;
    }

    public void k(int i, int i2, String str, String str2, String[] strArr) {
        this.f192a = i;
        this.i = i2;
        this.j = b(str).f202a;
        this.k = str;
        this.l = str2 == null ? 0 : b(str2).f202a;
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        int length = strArr.length;
        this.m = length;
        this.n = new int[length];
        for (int i3 = 0; i3 < this.m; i3++) {
            this.n[i3] = b(strArr[i3]).f202a;
        }
    }

    public c(int i) {
        this.f193b = 1;
        this.f194c = new a();
        e[] eVarArr = new e[256];
        this.f195d = eVarArr;
        this.f196e = (int) (((double) eVarArr.length) * 0.75d);
        this.f197f = new e();
        this.g = new e();
        this.h = new e();
    }
}
