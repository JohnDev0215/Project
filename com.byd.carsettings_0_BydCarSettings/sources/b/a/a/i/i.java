package b.a.a.i;

/* JADX INFO: compiled from: MethodWriter.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i f217a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f218b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f219c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f220d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f221e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f222f;
    public int[] g;
    public a h = new a();
    public int i;
    public int j;

    public i(c cVar, int i, String str, String str2, String str3, String[] strArr) {
        if (cVar.q == null) {
            cVar.q = this;
        } else {
            cVar.r.f217a = this;
        }
        cVar.r = this;
        this.f218b = cVar;
        this.f219c = i;
        this.f220d = cVar.h(str);
        this.f221e = cVar.h(str2);
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        int length = strArr.length;
        this.f222f = length;
        this.g = new int[length];
        for (int i2 = 0; i2 < this.f222f; i2++) {
            this.g[i2] = cVar.b(strArr[i2]).f202a;
        }
    }

    @Override // b.a.a.i.h
    public void a(int i, int i2) {
        this.h.d(132).b(i, i2);
    }

    @Override // b.a.a.i.h
    public void b(int i, f fVar) {
        if ((fVar.f208a & 2) != 0 && fVar.f209b - this.h.f185b < -32768) {
            throw new UnsupportedOperationException();
        }
        this.h.d(i);
        a aVar = this.h;
        fVar.b(this, aVar, aVar.f185b - 1, i == 200);
    }

    @Override // b.a.a.i.h
    public void c(int i, String str) {
        this.h.c(i, this.f218b.b(str).f202a);
    }

    @Override // b.a.a.i.h
    public void d(int i, String str, String str2, String str3) {
        boolean z = i == 185;
        e eVarE = this.f218b.e(str, str2, str3, z);
        int iB = eVarE.f204c;
        if (!z) {
            this.h.c(i, eVarE.f202a);
            return;
        }
        if (iB == 0) {
            iB = j.b(str3);
            eVarE.f204c = iB;
        }
        this.h.c(185, eVarE.f202a).b(iB >> 2, 0);
    }

    @Override // b.a.a.i.h
    public void e() {
    }

    @Override // b.a.a.i.h
    public void f(int i, String str, String str2, String str3) {
        this.h.c(i, this.f218b.d(str, str2, str3).f202a);
    }

    @Override // b.a.a.i.h
    public void g(int i, int i2) {
        this.h.b(i, i2);
    }

    @Override // b.a.a.i.h
    public void h(int i) {
        this.h.d(i);
    }

    @Override // b.a.a.i.h
    public void i(Object obj) {
        e eVarC = this.f218b.c(obj);
        int i = eVarC.f202a;
        int i2 = eVarC.f203b;
        if (i2 == 5 || i2 == 6) {
            this.h.c(20, i);
        } else if (i >= 256) {
            this.h.c(19, i);
        } else {
            this.h.b(18, i);
        }
    }

    @Override // b.a.a.i.h
    public void j(int i, int i2) {
        this.i = i;
        this.j = i2;
    }

    @Override // b.a.a.i.h
    public void k(int i, int i2) {
        if (i2 < 4 && i != 169) {
            this.h.d((i < 54 ? ((i - 21) << 2) + 26 : ((i - 54) << 2) + 59) + i2);
        } else if (i2 >= 256) {
            this.h.d(196).c(i, i2);
        } else {
            this.h.b(i, i2);
        }
    }

    @Override // b.a.a.i.h
    public void l(f fVar) {
        a aVar = this.h;
        fVar.c(this, aVar.f185b, aVar.f184a);
    }

    public final int m() {
        int i;
        if (this.h.f185b > 0) {
            this.f218b.h("Code");
            i = this.h.f185b + 18 + 0 + 8;
        } else {
            i = 8;
        }
        if (this.f222f <= 0) {
            return i;
        }
        this.f218b.h("Exceptions");
        return i + (this.f222f * 2) + 8;
    }

    public final void n(a aVar) {
        aVar.g(this.f219c & (-393217)).g(this.f220d).g(this.f221e);
        int i = this.h.f185b > 0 ? 1 : 0;
        if (this.f222f > 0) {
            i++;
        }
        aVar.g(i);
        int i2 = this.h.f185b;
        if (i2 > 0) {
            aVar.g(this.f218b.h("Code")).f(i2 + 12 + 0);
            aVar.g(this.i).g(this.j);
            a aVarF = aVar.f(this.h.f185b);
            a aVar2 = this.h;
            aVarF.e(aVar2.f184a, 0, aVar2.f185b);
            aVar.g(0);
            aVar.g(0);
        }
        if (this.f222f > 0) {
            aVar.g(this.f218b.h("Exceptions")).f((this.f222f * 2) + 2);
            aVar.g(this.f222f);
            for (int i3 = 0; i3 < this.f222f; i3++) {
                aVar.g(this.g[i3]);
            }
        }
    }
}
