package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b f1290a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f1291b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1292a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1293b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1294c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1295d;
        int e;

        a() {
        }

        void a(int i) {
            this.f1292a = i | this.f1292a;
        }

        boolean b() {
            int i = this.f1292a;
            if ((i & 7) != 0 && (i & (c(this.f1295d, this.f1293b) << 0)) == 0) {
                return false;
            }
            int i2 = this.f1292a;
            if ((i2 & 112) != 0 && (i2 & (c(this.f1295d, this.f1294c) << 4)) == 0) {
                return false;
            }
            int i3 = this.f1292a;
            if ((i3 & 1792) != 0 && (i3 & (c(this.e, this.f1293b) << 8)) == 0) {
                return false;
            }
            int i4 = this.f1292a;
            return (i4 & 28672) == 0 || (i4 & (c(this.e, this.f1294c) << 12)) != 0;
        }

        int c(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 2 : 4;
        }

        void d() {
            this.f1292a = 0;
        }

        void e(int i, int i2, int i3, int i4) {
            this.f1293b = i;
            this.f1294c = i2;
            this.f1295d = i3;
            this.e = i4;
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
        this.f1290a = bVar;
    }

    View a(int i, int i2, int i3, int i4) {
        int iC = this.f1290a.c();
        int iB = this.f1290a.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewA = this.f1290a.a(i);
            this.f1291b.e(iC, iB, this.f1290a.e(viewA), this.f1290a.d(viewA));
            if (i3 != 0) {
                this.f1291b.d();
                this.f1291b.a(i3);
                if (this.f1291b.b()) {
                    return viewA;
                }
            }
            if (i4 != 0) {
                this.f1291b.d();
                this.f1291b.a(i4);
                if (this.f1291b.b()) {
                    view = viewA;
                }
            }
            i += i5;
        }
        return view;
    }

    boolean b(View view, int i) {
        this.f1291b.e(this.f1290a.c(), this.f1290a.b(), this.f1290a.e(view), this.f1290a.d(view));
        if (i == 0) {
            return false;
        }
        this.f1291b.d();
        this.f1291b.a(i);
        return this.f1291b.b();
    }
}
