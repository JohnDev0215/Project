package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b f2018a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f2019b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2020a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2021b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2022c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2023d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2024e;

        a() {
        }

        void a(int i2) {
            this.f2020a = i2 | this.f2020a;
        }

        boolean b() {
            int i2 = this.f2020a;
            if ((i2 & 7) != 0 && (i2 & (c(this.f2023d, this.f2021b) << 0)) == 0) {
                return false;
            }
            int i3 = this.f2020a;
            if ((i3 & 112) != 0 && (i3 & (c(this.f2023d, this.f2022c) << 4)) == 0) {
                return false;
            }
            int i4 = this.f2020a;
            if ((i4 & 1792) != 0 && (i4 & (c(this.f2024e, this.f2021b) << 8)) == 0) {
                return false;
            }
            int i5 = this.f2020a;
            return (i5 & 28672) == 0 || (i5 & (c(this.f2024e, this.f2022c) << 12)) != 0;
        }

        int c(int i2, int i3) {
            if (i2 > i3) {
                return 1;
            }
            return i2 == i3 ? 2 : 4;
        }

        void d() {
            this.f2020a = 0;
        }

        void e(int i2, int i3, int i4, int i5) {
            this.f2021b = i2;
            this.f2022c = i3;
            this.f2023d = i4;
            this.f2024e = i5;
        }
    }

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    interface b {
        View a(int i2);

        int b();

        int c();

        int d(View view);

        int e(View view);
    }

    p(b bVar) {
        this.f2018a = bVar;
    }

    View a(int i2, int i3, int i4, int i5) {
        int iC = this.f2018a.c();
        int iB = this.f2018a.b();
        int i6 = i3 > i2 ? 1 : -1;
        View view = null;
        while (i2 != i3) {
            View viewA = this.f2018a.a(i2);
            this.f2019b.e(iC, iB, this.f2018a.e(viewA), this.f2018a.d(viewA));
            if (i4 != 0) {
                this.f2019b.d();
                this.f2019b.a(i4);
                if (this.f2019b.b()) {
                    return viewA;
                }
            }
            if (i5 != 0) {
                this.f2019b.d();
                this.f2019b.a(i5);
                if (this.f2019b.b()) {
                    view = viewA;
                }
            }
            i2 += i6;
        }
        return view;
    }

    boolean b(View view, int i2) {
        this.f2019b.e(this.f2018a.c(), this.f2018a.b(), this.f2018a.e(view), this.f2018a.d(view));
        if (i2 == 0) {
            return false;
        }
        this.f2019b.d();
        this.f2019b.a(i2);
        return this.f2019b.b();
    }
}
