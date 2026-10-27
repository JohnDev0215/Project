package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b f1940a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f1941b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1942a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1943b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1944c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1945d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1946e;

        a() {
        }

        void a(int i) {
            this.f1942a = i | this.f1942a;
        }

        boolean b() {
            int i = this.f1942a;
            if ((i & 7) != 0 && (i & (c(this.f1945d, this.f1943b) << 0)) == 0) {
                return false;
            }
            int i2 = this.f1942a;
            if ((i2 & 112) != 0 && (i2 & (c(this.f1945d, this.f1944c) << 4)) == 0) {
                return false;
            }
            int i3 = this.f1942a;
            if ((i3 & 1792) != 0 && (i3 & (c(this.f1946e, this.f1943b) << 8)) == 0) {
                return false;
            }
            int i4 = this.f1942a;
            return (i4 & 28672) == 0 || (i4 & (c(this.f1946e, this.f1944c) << 12)) != 0;
        }

        int c(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 2 : 4;
        }

        void d() {
            this.f1942a = 0;
        }

        void e(int i, int i2, int i3, int i4) {
            this.f1943b = i;
            this.f1944c = i2;
            this.f1945d = i3;
            this.f1946e = i4;
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
        this.f1940a = bVar;
    }

    View a(int i, int i2, int i3, int i4) {
        int iC = this.f1940a.c();
        int iB = this.f1940a.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewA = this.f1940a.a(i);
            this.f1941b.e(iC, iB, this.f1940a.e(viewA), this.f1940a.d(viewA));
            if (i3 != 0) {
                this.f1941b.d();
                this.f1941b.a(i3);
                if (this.f1941b.b()) {
                    return viewA;
                }
            }
            if (i4 != 0) {
                this.f1941b.d();
                this.f1941b.a(i4);
                if (this.f1941b.b()) {
                    view = viewA;
                }
            }
            i += i5;
        }
        return view;
    }

    boolean b(View view, int i) {
        this.f1941b.e(this.f1940a.c(), this.f1940a.b(), this.f1940a.e(view), this.f1940a.d(view));
        if (i == 0) {
            return false;
        }
        this.f1941b.d();
        this.f1941b.a(i);
        return this.f1941b.b();
    }
}
