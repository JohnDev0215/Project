package b.r.d;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f3130a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f3131b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3132a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3133b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3134c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3135d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3136e;

        public void a(int i2) {
            this.f3132a = i2 | this.f3132a;
        }

        public boolean b() {
            int i2 = this.f3132a;
            if ((i2 & 7) != 0 && (i2 & (c(this.f3135d, this.f3133b) << 0)) == 0) {
                return false;
            }
            int i3 = this.f3132a;
            if ((i3 & 112) != 0 && (i3 & (c(this.f3135d, this.f3134c) << 4)) == 0) {
                return false;
            }
            int i4 = this.f3132a;
            if ((i4 & 1792) != 0 && (i4 & (c(this.f3136e, this.f3133b) << 8)) == 0) {
                return false;
            }
            int i5 = this.f3132a;
            return (i5 & 28672) == 0 || (i5 & (c(this.f3136e, this.f3134c) << 12)) != 0;
        }

        public int c(int i2, int i3) {
            if (i2 > i3) {
                return 1;
            }
            return i2 == i3 ? 2 : 4;
        }

        public void d() {
            this.f3132a = 0;
        }

        public void e(int i2, int i3, int i4, int i5) {
            this.f3133b = i2;
            this.f3134c = i3;
            this.f3135d = i4;
            this.f3136e = i5;
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

    public p(b bVar) {
        this.f3130a = bVar;
    }

    public View a(int i2, int i3, int i4, int i5) {
        int iC = this.f3130a.c();
        int iB = this.f3130a.b();
        int i6 = i3 > i2 ? 1 : -1;
        View view = null;
        while (i2 != i3) {
            View viewA = this.f3130a.a(i2);
            this.f3131b.e(iC, iB, this.f3130a.e(viewA), this.f3130a.d(viewA));
            if (i4 != 0) {
                this.f3131b.d();
                this.f3131b.a(i4);
                if (this.f3131b.b()) {
                    return viewA;
                }
            }
            if (i5 != 0) {
                this.f3131b.d();
                this.f3131b.a(i5);
                if (this.f3131b.b()) {
                    view = viewA;
                }
            }
            i2 += i6;
        }
        return view;
    }

    public boolean b(View view, int i2) {
        this.f3131b.e(this.f3130a.c(), this.f3130a.b(), this.f3130a.e(view), this.f3130a.d(view));
        if (i2 == 0) {
            return false;
        }
        this.f3131b.d();
        this.f3131b.a(i2);
        return this.f3131b.b();
    }
}
