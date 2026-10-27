package b.n.d;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f2109a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f2110b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2111a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2112b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2113c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2114d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2115e;

        public void a(int i2) {
            this.f2111a = i2 | this.f2111a;
        }

        public boolean b() {
            int i2 = this.f2111a;
            if ((i2 & 7) != 0 && (i2 & (c(this.f2114d, this.f2112b) << 0)) == 0) {
                return false;
            }
            int i3 = this.f2111a;
            if ((i3 & 112) != 0 && (i3 & (c(this.f2114d, this.f2113c) << 4)) == 0) {
                return false;
            }
            int i4 = this.f2111a;
            if ((i4 & 1792) != 0 && (i4 & (c(this.f2115e, this.f2112b) << 8)) == 0) {
                return false;
            }
            int i5 = this.f2111a;
            return (i5 & 28672) == 0 || (i5 & (c(this.f2115e, this.f2113c) << 12)) != 0;
        }

        public int c(int i2, int i3) {
            if (i2 > i3) {
                return 1;
            }
            return i2 == i3 ? 2 : 4;
        }

        public void d() {
            this.f2111a = 0;
        }

        public void e(int i2, int i3, int i4, int i5) {
            this.f2112b = i2;
            this.f2113c = i3;
            this.f2114d = i4;
            this.f2115e = i5;
        }
    }

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    public interface b {
        View a(int i2);

        int b();

        int c();

        int d(View view);

        int e(View view);
    }

    public l(b bVar) {
        this.f2109a = bVar;
    }

    public View a(int i2, int i3, int i4, int i5) {
        int iC = this.f2109a.c();
        int iB = this.f2109a.b();
        int i6 = i3 > i2 ? 1 : -1;
        View view = null;
        while (i2 != i3) {
            View viewA = this.f2109a.a(i2);
            this.f2110b.e(iC, iB, this.f2109a.e(viewA), this.f2109a.d(viewA));
            if (i4 != 0) {
                this.f2110b.d();
                this.f2110b.a(i4);
                if (this.f2110b.b()) {
                    return viewA;
                }
            }
            if (i5 != 0) {
                this.f2110b.d();
                this.f2110b.a(i5);
                if (this.f2110b.b()) {
                    view = viewA;
                }
            }
            i2 += i6;
        }
        return view;
    }

    public boolean b(View view, int i2) {
        this.f2110b.e(this.f2109a.c(), this.f2109a.b(), this.f2109a.e(view), this.f2109a.d(view));
        if (i2 == 0) {
            return false;
        }
        this.f2110b.d();
        this.f2110b.a(i2);
        return this.f2110b.b();
    }
}
