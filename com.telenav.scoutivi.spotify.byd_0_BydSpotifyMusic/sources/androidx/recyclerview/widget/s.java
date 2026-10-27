package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b f3180a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f3181b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f3182a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3183b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f3184c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f3185d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f3186e;

        a() {
        }

        void a(int i7) {
            this.f3182a = i7 | this.f3182a;
        }

        boolean b() {
            int i7 = this.f3182a;
            if ((i7 & 7) != 0 && (i7 & (c(this.f3185d, this.f3183b) << 0)) == 0) {
                return false;
            }
            int i8 = this.f3182a;
            if ((i8 & 112) != 0 && (i8 & (c(this.f3185d, this.f3184c) << 4)) == 0) {
                return false;
            }
            int i9 = this.f3182a;
            if ((i9 & 1792) != 0 && (i9 & (c(this.f3186e, this.f3183b) << 8)) == 0) {
                return false;
            }
            int i10 = this.f3182a;
            return (i10 & 28672) == 0 || (i10 & (c(this.f3186e, this.f3184c) << 12)) != 0;
        }

        int c(int i7, int i8) {
            if (i7 > i8) {
                return 1;
            }
            return i7 == i8 ? 2 : 4;
        }

        void d() {
            this.f3182a = 0;
        }

        void e(int i7, int i8, int i9, int i10) {
            this.f3183b = i7;
            this.f3184c = i8;
            this.f3185d = i9;
            this.f3186e = i10;
        }
    }

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    interface b {
        View a(int i7);

        int b();

        int c();

        int d(View view);

        int e(View view);
    }

    s(b bVar) {
        this.f3180a = bVar;
    }

    View a(int i7, int i8, int i9, int i10) {
        int iC = this.f3180a.c();
        int iB = this.f3180a.b();
        int i11 = i8 > i7 ? 1 : -1;
        View view = null;
        while (i7 != i8) {
            View viewA = this.f3180a.a(i7);
            this.f3181b.e(iC, iB, this.f3180a.e(viewA), this.f3180a.d(viewA));
            if (i9 != 0) {
                this.f3181b.d();
                this.f3181b.a(i9);
                if (this.f3181b.b()) {
                    return viewA;
                }
            }
            if (i10 != 0) {
                this.f3181b.d();
                this.f3181b.a(i10);
                if (this.f3181b.b()) {
                    view = viewA;
                }
            }
            i7 += i11;
        }
        return view;
    }

    boolean b(View view, int i7) {
        this.f3181b.e(this.f3180a.c(), this.f3180a.b(), this.f3180a.e(view), this.f3180a.d(view));
        if (i7 == 0) {
            return false;
        }
        this.f3181b.d();
        this.f3181b.a(i7);
        return this.f3181b.b();
    }
}
