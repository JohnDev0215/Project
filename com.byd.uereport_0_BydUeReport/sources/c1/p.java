package c1;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f2283a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f2284b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2285a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2286b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2287c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2288d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2289e;

        public void a(int i8) {
            this.f2285a = i8 | this.f2285a;
        }

        public boolean b() {
            int i8 = this.f2285a;
            if ((i8 & 7) != 0 && (i8 & (c(this.f2288d, this.f2286b) << 0)) == 0) {
                return false;
            }
            int i9 = this.f2285a;
            if ((i9 & 112) != 0 && (i9 & (c(this.f2288d, this.f2287c) << 4)) == 0) {
                return false;
            }
            int i10 = this.f2285a;
            if ((i10 & 1792) != 0 && (i10 & (c(this.f2289e, this.f2286b) << 8)) == 0) {
                return false;
            }
            int i11 = this.f2285a;
            return (i11 & 28672) == 0 || (i11 & (c(this.f2289e, this.f2287c) << 12)) != 0;
        }

        public int c(int i8, int i9) {
            if (i8 > i9) {
                return 1;
            }
            return i8 == i9 ? 2 : 4;
        }

        public void d() {
            this.f2285a = 0;
        }

        public void e(int i8, int i9, int i10, int i11) {
            this.f2286b = i8;
            this.f2287c = i9;
            this.f2288d = i10;
            this.f2289e = i11;
        }
    }

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    public interface b {
        View a(int i8);

        int b();

        int c(View view);

        int d();

        int e(View view);
    }

    public p(b bVar) {
        this.f2283a = bVar;
    }

    public View a(int i8, int i9, int i10, int i11) {
        int iD = this.f2283a.d();
        int iB = this.f2283a.b();
        int i12 = i9 > i8 ? 1 : -1;
        View view = null;
        while (i8 != i9) {
            View viewA = this.f2283a.a(i8);
            this.f2284b.e(iD, iB, this.f2283a.c(viewA), this.f2283a.e(viewA));
            if (i10 != 0) {
                this.f2284b.d();
                this.f2284b.a(i10);
                if (this.f2284b.b()) {
                    return viewA;
                }
            }
            if (i11 != 0) {
                this.f2284b.d();
                this.f2284b.a(i11);
                if (this.f2284b.b()) {
                    view = viewA;
                }
            }
            i8 += i12;
        }
        return view;
    }

    public boolean b(View view, int i8) {
        this.f2284b.e(this.f2283a.d(), this.f2283a.b(), this.f2283a.c(view), this.f2283a.e(view));
        if (i8 == 0) {
            return false;
        }
        this.f2284b.d();
        this.f2284b.a(i8);
        return this.f2284b.b();
    }
}
