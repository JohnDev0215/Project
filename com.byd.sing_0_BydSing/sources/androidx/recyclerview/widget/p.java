package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b f1834a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f1835b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1836a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1837b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1838c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1839d;
        int e;

        a() {
        }

        void a(int i) {
            this.f1836a = i | this.f1836a;
        }

        boolean b() {
            int i = this.f1836a;
            if ((i & 7) != 0 && (i & (c(this.f1839d, this.f1837b) << 0)) == 0) {
                return false;
            }
            int i2 = this.f1836a;
            if ((i2 & 112) != 0 && (i2 & (c(this.f1839d, this.f1838c) << 4)) == 0) {
                return false;
            }
            int i3 = this.f1836a;
            if ((i3 & 1792) != 0 && (i3 & (c(this.e, this.f1837b) << 8)) == 0) {
                return false;
            }
            int i4 = this.f1836a;
            return (i4 & 28672) == 0 || (i4 & (c(this.e, this.f1838c) << 12)) != 0;
        }

        int c(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 2 : 4;
        }

        void d() {
            this.f1836a = 0;
        }

        void e(int i, int i2, int i3, int i4) {
            this.f1837b = i;
            this.f1838c = i2;
            this.f1839d = i3;
            this.e = i4;
        }
    }

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    interface b {
        View a(int i);

        int b();

        int c(View view);

        int d();

        int e(View view);
    }

    p(b bVar) {
        this.f1834a = bVar;
    }

    View a(int i, int i2, int i3, int i4) {
        int iD = this.f1834a.d();
        int iB = this.f1834a.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewA = this.f1834a.a(i);
            this.f1835b.e(iD, iB, this.f1834a.c(viewA), this.f1834a.e(viewA));
            if (i3 != 0) {
                this.f1835b.d();
                this.f1835b.a(i3);
                if (this.f1835b.b()) {
                    return viewA;
                }
            }
            if (i4 != 0) {
                this.f1835b.d();
                this.f1835b.a(i4);
                if (this.f1835b.b()) {
                    view = viewA;
                }
            }
            i += i5;
        }
        return view;
    }

    boolean b(View view, int i) {
        this.f1835b.e(this.f1834a.d(), this.f1834a.b(), this.f1834a.c(view), this.f1834a.e(view));
        if (i == 0) {
            return false;
        }
        this.f1835b.d();
        this.f1835b.a(i);
        return this.f1835b.b();
    }
}
