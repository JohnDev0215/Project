package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b f1816a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f1817b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1818a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1819b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1820c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1821d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1822e;

        a() {
        }

        void a(int i) {
            this.f1818a = i | this.f1818a;
        }

        boolean b() {
            int i = this.f1818a;
            if ((i & 7) != 0 && (i & (c(this.f1821d, this.f1819b) << 0)) == 0) {
                return false;
            }
            int i2 = this.f1818a;
            if ((i2 & 112) != 0 && (i2 & (c(this.f1821d, this.f1820c) << 4)) == 0) {
                return false;
            }
            int i3 = this.f1818a;
            if ((i3 & 1792) != 0 && (i3 & (c(this.f1822e, this.f1819b) << 8)) == 0) {
                return false;
            }
            int i4 = this.f1818a;
            return (i4 & 28672) == 0 || (i4 & (c(this.f1822e, this.f1820c) << 12)) != 0;
        }

        int c(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 2 : 4;
        }

        void d() {
            this.f1818a = 0;
        }

        void e(int i, int i2, int i3, int i4) {
            this.f1819b = i;
            this.f1820c = i2;
            this.f1821d = i3;
            this.f1822e = i4;
        }
    }

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    interface b {
        View a(int i);

        int b();

        int c();

        int d(View view);

        int e(View view);
    }

    p(b bVar) {
        this.f1816a = bVar;
    }

    View a(int i, int i2, int i3, int i4) {
        int iC = this.f1816a.c();
        int iB = this.f1816a.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewA = this.f1816a.a(i);
            this.f1817b.e(iC, iB, this.f1816a.e(viewA), this.f1816a.d(viewA));
            if (i3 != 0) {
                this.f1817b.d();
                this.f1817b.a(i3);
                if (this.f1817b.b()) {
                    return viewA;
                }
            }
            if (i4 != 0) {
                this.f1817b.d();
                this.f1817b.a(i4);
                if (this.f1817b.b()) {
                    view = viewA;
                }
            }
            i += i5;
        }
        return view;
    }

    boolean b(View view, int i) {
        this.f1817b.e(this.f1816a.c(), this.f1816a.b(), this.f1816a.e(view), this.f1816a.d(view));
        if (i == 0) {
            return false;
        }
        this.f1817b.d();
        this.f1817b.a(i);
        return this.f1817b.b();
    }
}
