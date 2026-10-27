package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b f1607a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f1608b = new a();

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1609a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1610b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1611c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1612d;
        int e;

        a() {
        }

        void a(int i) {
            this.f1609a = i | this.f1609a;
        }

        boolean b() {
            int i = this.f1609a;
            if ((i & 7) != 0 && (i & (c(this.f1612d, this.f1610b) << 0)) == 0) {
                return false;
            }
            int i2 = this.f1609a;
            if ((i2 & 112) != 0 && (i2 & (c(this.f1612d, this.f1611c) << 4)) == 0) {
                return false;
            }
            int i3 = this.f1609a;
            if ((i3 & 1792) != 0 && (i3 & (c(this.e, this.f1610b) << 8)) == 0) {
                return false;
            }
            int i4 = this.f1609a;
            return (i4 & 28672) == 0 || (i4 & (c(this.e, this.f1611c) << 12)) != 0;
        }

        int c(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 2 : 4;
        }

        void d() {
            this.f1609a = 0;
        }

        void e(int i, int i2, int i3, int i4) {
            this.f1610b = i;
            this.f1611c = i2;
            this.f1612d = i3;
            this.e = i4;
        }
    }

    interface b {
        View a(int i);

        int b();

        int c();

        int d(View view);

        int e(View view);
    }

    p(b bVar) {
        this.f1607a = bVar;
    }

    View a(int i, int i2, int i3, int i4) {
        int iC = this.f1607a.c();
        int iB = this.f1607a.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewA = this.f1607a.a(i);
            this.f1608b.e(iC, iB, this.f1607a.e(viewA), this.f1607a.d(viewA));
            if (i3 != 0) {
                this.f1608b.d();
                this.f1608b.a(i3);
                if (this.f1608b.b()) {
                    return viewA;
                }
            }
            if (i4 != 0) {
                this.f1608b.d();
                this.f1608b.a(i4);
                if (this.f1608b.b()) {
                    view = viewA;
                }
            }
            i += i5;
        }
        return view;
    }

    boolean b(View view, int i) {
        this.f1608b.e(this.f1607a.c(), this.f1607a.b(), this.f1607a.e(view), this.f1607a.d(view));
        if (i == 0) {
            return false;
        }
        this.f1608b.d();
        this.f1608b.a(i);
        return this.f1608b.b();
    }
}
