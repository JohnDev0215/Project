package e2;

/* JADX INFO: compiled from: ClassWriter.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4523a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4524b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f4525c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e[] f4526d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4527e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final e f4528f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final e f4529g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e f4530h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4531i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4532j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f4533k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4534l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4535m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f4536n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public d f4537o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public d f4538p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public i f4539q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public i f4540r;

    public c() {
        this(0);
    }

    public final e a(e eVar) {
        e[] eVarArr = this.f4526d;
        e eVar2 = eVarArr[eVar.f4552h % eVarArr.length];
        while (eVar2 != null && (eVar2.f4546b != eVar.f4546b || !eVar.a(eVar2))) {
            eVar2 = eVar2.f4553i;
        }
        return eVar2;
    }

    public e b(String str) {
        this.f4529g.c(7, str, null, null);
        e eVarA = a(this.f4529g);
        if (eVarA != null) {
            return eVarA;
        }
        this.f4525c.c(7, h(str));
        int i8 = this.f4524b;
        this.f4524b = i8 + 1;
        e eVar = new e(i8, this.f4529g);
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
                return b(jVar.f4582j == 10 ? jVar.f() : jVar.d());
            }
            throw new IllegalArgumentException("value " + obj);
        }
        int iIntValue = ((Integer) obj).intValue();
        this.f4528f.b(iIntValue);
        e eVarA = a(this.f4528f);
        if (eVarA != null) {
            return eVarA;
        }
        this.f4525c.d(3).f(iIntValue);
        int i8 = this.f4524b;
        this.f4524b = i8 + 1;
        e eVar = new e(i8, this.f4528f);
        i(eVar);
        return eVar;
    }

    public e d(String str, String str2, String str3) {
        this.f4530h.c(9, str, str2, str3);
        e eVarA = a(this.f4530h);
        if (eVarA != null) {
            return eVarA;
        }
        int i8 = b(str).f4545a;
        this.f4525c.c(9, i8).g(f(str2, str3).f4545a);
        int i9 = this.f4524b;
        this.f4524b = i9 + 1;
        e eVar = new e(i9, this.f4530h);
        i(eVar);
        return eVar;
    }

    public e e(String str, String str2, String str3, boolean z7) {
        int i8 = z7 ? 11 : 10;
        this.f4530h.c(i8, str, str2, str3);
        e eVarA = a(this.f4530h);
        if (eVarA != null) {
            return eVarA;
        }
        this.f4525c.c(i8, b(str).f4545a).g(f(str2, str3).f4545a);
        int i9 = this.f4524b;
        this.f4524b = i9 + 1;
        e eVar = new e(i9, this.f4530h);
        i(eVar);
        return eVar;
    }

    public e f(String str, String str2) {
        this.f4529g.c(12, str, str2, null);
        e eVarA = a(this.f4529g);
        if (eVarA != null) {
            return eVarA;
        }
        int iH = h(str);
        this.f4525c.c(12, iH).g(h(str2));
        int i8 = this.f4524b;
        this.f4524b = i8 + 1;
        e eVar = new e(i8, this.f4529g);
        i(eVar);
        return eVar;
    }

    public final e g(String str) {
        this.f4529g.c(8, str, null, null);
        e eVarA = a(this.f4529g);
        if (eVarA != null) {
            return eVarA;
        }
        this.f4525c.c(8, h(str));
        int i8 = this.f4524b;
        this.f4524b = i8 + 1;
        e eVar = new e(i8, this.f4529g);
        i(eVar);
        return eVar;
    }

    public int h(String str) {
        this.f4528f.c(1, str, null, null);
        e eVarA = a(this.f4528f);
        if (eVarA == null) {
            this.f4525c.d(1).h(str);
            int i8 = this.f4524b;
            this.f4524b = i8 + 1;
            eVarA = new e(i8, this.f4528f);
            i(eVarA);
        }
        return eVarA.f4545a;
    }

    public final void i(e eVar) {
        if (this.f4524b > this.f4527e) {
            int length = this.f4526d.length;
            int i8 = (length * 2) + 1;
            e[] eVarArr = new e[i8];
            for (int i9 = length - 1; i9 >= 0; i9--) {
                e eVar2 = this.f4526d[i9];
                while (eVar2 != null) {
                    int i10 = eVar2.f4552h % i8;
                    e eVar3 = eVar2.f4553i;
                    eVar2.f4553i = eVarArr[i10];
                    eVarArr[i10] = eVar2;
                    eVar2 = eVar3;
                }
            }
            this.f4526d = eVarArr;
            this.f4527e = (int) (((double) i8) * 0.75d);
        }
        int i11 = eVar.f4552h;
        e[] eVarArr2 = this.f4526d;
        int length2 = i11 % eVarArr2.length;
        eVar.f4553i = eVarArr2[length2];
        eVarArr2[length2] = eVar;
    }

    public byte[] j() {
        int iM = (this.f4535m * 2) + 24;
        int i8 = 0;
        for (d dVar = this.f4537o; dVar != null; dVar = dVar.f4541a) {
            i8++;
            iM += dVar.a();
        }
        int i9 = 0;
        for (i iVar = this.f4539q; iVar != null; iVar = iVar.f4563a) {
            i9++;
            iM += iVar.m();
        }
        a aVar = new a(iM + this.f4525c.f4516b);
        aVar.f(-889275714).f(this.f4523a);
        a aVarG = aVar.g(this.f4524b);
        a aVar2 = this.f4525c;
        aVarG.e(aVar2.f4515a, 0, aVar2.f4516b);
        aVar.g(this.f4531i & (-393217)).g(this.f4532j).g(this.f4534l);
        aVar.g(this.f4535m);
        for (int i10 = 0; i10 < this.f4535m; i10++) {
            aVar.g(this.f4536n[i10]);
        }
        aVar.g(i8);
        for (d dVar2 = this.f4537o; dVar2 != null; dVar2 = dVar2.f4541a) {
            dVar2.b(aVar);
        }
        aVar.g(i9);
        for (i iVar2 = this.f4539q; iVar2 != null; iVar2 = iVar2.f4563a) {
            iVar2.n(aVar);
        }
        aVar.g(0);
        return aVar.f4515a;
    }

    public void k(int i8, int i9, String str, String str2, String[] strArr) {
        this.f4523a = i8;
        this.f4531i = i9;
        this.f4532j = b(str).f4545a;
        this.f4533k = str;
        this.f4534l = str2 == null ? 0 : b(str2).f4545a;
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        int length = strArr.length;
        this.f4535m = length;
        this.f4536n = new int[length];
        for (int i10 = 0; i10 < this.f4535m; i10++) {
            this.f4536n[i10] = b(strArr[i10]).f4545a;
        }
    }

    public c(int i8) {
        this.f4524b = 1;
        this.f4525c = new a();
        e[] eVarArr = new e[256];
        this.f4526d = eVarArr;
        this.f4527e = (int) (((double) eVarArr.length) * 0.75d);
        this.f4528f = new e();
        this.f4529g = new e();
        this.f4530h = new e();
    }
}
