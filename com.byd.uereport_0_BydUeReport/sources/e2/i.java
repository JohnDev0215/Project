package e2;

/* JADX INFO: compiled from: MethodWriter.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i f4563a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f4564b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4565c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4566d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4567e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4568f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f4569g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f4570h = new a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4571i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4572j;

    public i(c cVar, int i8, String str, String str2, String str3, String[] strArr) {
        if (cVar.f4539q == null) {
            cVar.f4539q = this;
        } else {
            cVar.f4540r.f4563a = this;
        }
        cVar.f4540r = this;
        this.f4564b = cVar;
        this.f4565c = i8;
        this.f4566d = cVar.h(str);
        this.f4567e = cVar.h(str2);
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        int length = strArr.length;
        this.f4568f = length;
        this.f4569g = new int[length];
        for (int i9 = 0; i9 < this.f4568f; i9++) {
            this.f4569g[i9] = cVar.b(strArr[i9]).f4545a;
        }
    }

    @Override // e2.h
    public void a(int i8, int i9) {
        this.f4570h.d(132).b(i8, i9);
    }

    @Override // e2.h
    public void b(int i8, f fVar) {
        if ((fVar.f4554a & 2) != 0 && fVar.f4555b - this.f4570h.f4516b < -32768) {
            throw new UnsupportedOperationException();
        }
        this.f4570h.d(i8);
        a aVar = this.f4570h;
        fVar.b(this, aVar, aVar.f4516b - 1, i8 == 200);
    }

    @Override // e2.h
    public void c(int i8, String str) {
        this.f4570h.c(i8, this.f4564b.b(str).f4545a);
    }

    @Override // e2.h
    public void d(int i8, String str, String str2, String str3) {
        boolean z7 = i8 == 185;
        e eVarE = this.f4564b.e(str, str2, str3, z7);
        int iB = eVarE.f4547c;
        if (!z7) {
            this.f4570h.c(i8, eVarE.f4545a);
            return;
        }
        if (iB == 0) {
            iB = j.b(str3);
            eVarE.f4547c = iB;
        }
        this.f4570h.c(185, eVarE.f4545a).b(iB >> 2, 0);
    }

    @Override // e2.h
    public void e() {
    }

    @Override // e2.h
    public void f(int i8, String str, String str2, String str3) {
        this.f4570h.c(i8, this.f4564b.d(str, str2, str3).f4545a);
    }

    @Override // e2.h
    public void g(int i8, int i9) {
        this.f4570h.b(i8, i9);
    }

    @Override // e2.h
    public void h(int i8) {
        this.f4570h.d(i8);
    }

    @Override // e2.h
    public void i(Object obj) {
        e eVarC = this.f4564b.c(obj);
        int i8 = eVarC.f4545a;
        int i9 = eVarC.f4546b;
        if (i9 == 5 || i9 == 6) {
            this.f4570h.c(20, i8);
        } else if (i8 >= 256) {
            this.f4570h.c(19, i8);
        } else {
            this.f4570h.b(18, i8);
        }
    }

    @Override // e2.h
    public void j(int i8, int i9) {
        this.f4571i = i8;
        this.f4572j = i9;
    }

    @Override // e2.h
    public void k(int i8, int i9) {
        if (i9 < 4 && i8 != 169) {
            this.f4570h.d((i8 < 54 ? ((i8 - 21) << 2) + 26 : ((i8 - 54) << 2) + 59) + i9);
        } else if (i9 >= 256) {
            this.f4570h.d(196).c(i8, i9);
        } else {
            this.f4570h.b(i8, i9);
        }
    }

    @Override // e2.h
    public void l(f fVar) {
        a aVar = this.f4570h;
        fVar.c(this, aVar.f4516b, aVar.f4515a);
    }

    public final int m() {
        int i8;
        if (this.f4570h.f4516b > 0) {
            this.f4564b.h("Code");
            i8 = this.f4570h.f4516b + 18 + 0 + 8;
        } else {
            i8 = 8;
        }
        if (this.f4568f <= 0) {
            return i8;
        }
        this.f4564b.h("Exceptions");
        return i8 + (this.f4568f * 2) + 8;
    }

    public final void n(a aVar) {
        aVar.g(this.f4565c & (-393217)).g(this.f4566d).g(this.f4567e);
        int i8 = this.f4570h.f4516b > 0 ? 1 : 0;
        if (this.f4568f > 0) {
            i8++;
        }
        aVar.g(i8);
        int i9 = this.f4570h.f4516b;
        if (i9 > 0) {
            aVar.g(this.f4564b.h("Code")).f(i9 + 12 + 0);
            aVar.g(this.f4571i).g(this.f4572j);
            a aVarF = aVar.f(this.f4570h.f4516b);
            a aVar2 = this.f4570h;
            aVarF.e(aVar2.f4515a, 0, aVar2.f4516b);
            aVar.g(0);
            aVar.g(0);
        }
        if (this.f4568f > 0) {
            aVar.g(this.f4564b.h("Exceptions")).f((this.f4568f * 2) + 2);
            aVar.g(this.f4568f);
            for (int i10 = 0; i10 < this.f4568f; i10++) {
                aVar.g(this.f4569g[i10]);
            }
        }
    }
}
