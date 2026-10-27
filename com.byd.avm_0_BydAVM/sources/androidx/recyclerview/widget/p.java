package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b f1308a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f1309b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1310a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1311b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1312c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1313d;
        int e;

        a() {
        }

        void a(int i) {
            this.f1310a = i | this.f1310a;
        }

        boolean b() {
            int i = this.f1310a;
            if ((i & 7) != 0 && (i & (c(this.f1313d, this.f1311b) << 0)) == 0) {
                return false;
            }
            int i2 = this.f1310a;
            if ((i2 & 112) != 0 && (i2 & (c(this.f1313d, this.f1312c) << 4)) == 0) {
                return false;
            }
            int i3 = this.f1310a;
            if ((i3 & 1792) != 0 && (i3 & (c(this.e, this.f1311b) << 8)) == 0) {
                return false;
            }
            int i4 = this.f1310a;
            return (i4 & 28672) == 0 || ((c(this.e, this.f1312c) << 12) & i4) != 0;
        }

        int c(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 2 : 4;
        }

        void d() {
            this.f1310a = 0;
        }

        void e(int i, int i2, int i3, int i4) {
            this.f1311b = i;
            this.f1312c = i2;
            this.f1313d = i3;
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
        this.f1308a = bVar;
    }

    View a(int i, int i2, int i3, int i4) {
        int iC = this.f1308a.c();
        int iB = this.f1308a.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewA = this.f1308a.a(i);
            this.f1309b.e(iC, iB, this.f1308a.e(viewA), this.f1308a.d(viewA));
            if (i3 != 0) {
                this.f1309b.d();
                this.f1309b.a(i3);
                if (this.f1309b.b()) {
                    return viewA;
                }
            }
            if (i4 != 0) {
                this.f1309b.d();
                this.f1309b.a(i4);
                if (this.f1309b.b()) {
                    view = viewA;
                }
            }
            i += i5;
        }
        return view;
    }

    boolean b(View view, int i) {
        this.f1309b.e(this.f1308a.c(), this.f1308a.b(), this.f1308a.e(view), this.f1308a.d(view));
        if (i == 0) {
            return false;
        }
        this.f1309b.d();
        this.f1309b.a(i);
        return this.f1309b.b();
    }
}
