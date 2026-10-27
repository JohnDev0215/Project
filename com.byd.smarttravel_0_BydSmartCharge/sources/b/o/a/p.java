package b.o.a;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f1414a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f1415b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1416a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1417b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1418c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1419d;
        public int e;

        public void a(int i) {
            this.f1416a = i | this.f1416a;
        }

        public boolean b() {
            int i = this.f1416a;
            if ((i & 7) != 0 && (i & (c(this.f1419d, this.f1417b) << 0)) == 0) {
                return false;
            }
            int i2 = this.f1416a;
            if ((i2 & 112) != 0 && (i2 & (c(this.f1419d, this.f1418c) << 4)) == 0) {
                return false;
            }
            int i3 = this.f1416a;
            if ((i3 & 1792) != 0 && (i3 & (c(this.e, this.f1417b) << 8)) == 0) {
                return false;
            }
            int i4 = this.f1416a;
            return (i4 & 28672) == 0 || (i4 & (c(this.e, this.f1418c) << 12)) != 0;
        }

        public int c(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 2 : 4;
        }

        public void d() {
            this.f1416a = 0;
        }

        public void e(int i, int i2, int i3, int i4) {
            this.f1417b = i;
            this.f1418c = i2;
            this.f1419d = i3;
            this.e = i4;
        }
    }

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    public interface b {
        View a(int i);

        int b();

        int c();

        int d(View view);

        int e(View view);
    }

    public p(b bVar) {
        this.f1414a = bVar;
    }

    public View a(int i, int i2, int i3, int i4) {
        int iC = this.f1414a.c();
        int iB = this.f1414a.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewA = this.f1414a.a(i);
            this.f1415b.e(iC, iB, this.f1414a.e(viewA), this.f1414a.d(viewA));
            if (i3 != 0) {
                this.f1415b.d();
                this.f1415b.a(i3);
                if (this.f1415b.b()) {
                    return viewA;
                }
            }
            if (i4 != 0) {
                this.f1415b.d();
                this.f1415b.a(i4);
                if (this.f1415b.b()) {
                    view = viewA;
                }
            }
            i += i5;
        }
        return view;
    }

    public boolean b(View view, int i) {
        this.f1415b.e(this.f1414a.c(), this.f1414a.b(), this.f1414a.e(view), this.f1414a.d(view));
        if (i == 0) {
            return false;
        }
        this.f1415b.d();
        this.f1415b.a(i);
        return this.f1415b.b();
    }
}
