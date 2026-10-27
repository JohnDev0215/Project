package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b f1481a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f1482b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1483a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1484b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1485c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1486d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1487e;

        a() {
        }

        void a(int i) {
            this.f1483a = i | this.f1483a;
        }

        boolean b() {
            int i = this.f1483a;
            if ((i & 7) != 0 && (i & (c(this.f1486d, this.f1484b) << 0)) == 0) {
                return false;
            }
            int i2 = this.f1483a;
            if ((i2 & 112) != 0 && (i2 & (c(this.f1486d, this.f1485c) << 4)) == 0) {
                return false;
            }
            int i3 = this.f1483a;
            if ((i3 & 1792) != 0 && (i3 & (c(this.f1487e, this.f1484b) << 8)) == 0) {
                return false;
            }
            int i4 = this.f1483a;
            return (i4 & 28672) == 0 || (i4 & (c(this.f1487e, this.f1485c) << 12)) != 0;
        }

        int c(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 2 : 4;
        }

        void d() {
            this.f1483a = 0;
        }

        void e(int i, int i2, int i3, int i4) {
            this.f1484b = i;
            this.f1485c = i2;
            this.f1486d = i3;
            this.f1487e = i4;
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

    q(b bVar) {
        this.f1481a = bVar;
    }

    View a(int i, int i2, int i3, int i4) {
        int iC = this.f1481a.c();
        int iB = this.f1481a.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewA = this.f1481a.a(i);
            this.f1482b.e(iC, iB, this.f1481a.e(viewA), this.f1481a.d(viewA));
            if (i3 != 0) {
                this.f1482b.d();
                this.f1482b.a(i3);
                if (this.f1482b.b()) {
                    return viewA;
                }
            }
            if (i4 != 0) {
                this.f1482b.d();
                this.f1482b.a(i4);
                if (this.f1482b.b()) {
                    view = viewA;
                }
            }
            i += i5;
        }
        return view;
    }

    boolean b(View view, int i) {
        this.f1482b.e(this.f1481a.c(), this.f1481a.b(), this.f1481a.e(view), this.f1481a.d(view));
        if (i == 0) {
            return false;
        }
        this.f1482b.d();
        this.f1482b.a(i);
        return this.f1482b.b();
    }
}
