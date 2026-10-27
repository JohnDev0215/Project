package w0;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f5668a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f5669b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5670a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5671b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5672c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5673d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5674e;

        public void a(int i3) {
            this.f5670a = i3 | this.f5670a;
        }

        public boolean b() {
            int i3 = this.f5670a;
            if ((i3 & 7) != 0 && (i3 & (c(this.f5673d, this.f5671b) << 0)) == 0) {
                return false;
            }
            int i4 = this.f5670a;
            if ((i4 & 112) != 0 && (i4 & (c(this.f5673d, this.f5672c) << 4)) == 0) {
                return false;
            }
            int i5 = this.f5670a;
            if ((i5 & 1792) != 0 && (i5 & (c(this.f5674e, this.f5671b) << 8)) == 0) {
                return false;
            }
            int i6 = this.f5670a;
            return (i6 & 28672) == 0 || (i6 & (c(this.f5674e, this.f5672c) << 12)) != 0;
        }

        public int c(int i3, int i4) {
            if (i3 > i4) {
                return 1;
            }
            return i3 == i4 ? 2 : 4;
        }

        public void d() {
            this.f5670a = 0;
        }

        public void e(int i3, int i4, int i5, int i6) {
            this.f5671b = i3;
            this.f5672c = i4;
            this.f5673d = i5;
            this.f5674e = i6;
        }
    }

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    public interface b {
        View a(int i3);

        int b();

        int c();

        int d(View view);

        int e(View view);
    }

    public p(b bVar) {
        this.f5668a = bVar;
    }

    public View a(int i3, int i4, int i5, int i6) {
        int iC = this.f5668a.c();
        int iB = this.f5668a.b();
        int i7 = i4 > i3 ? 1 : -1;
        View view = null;
        while (i3 != i4) {
            View viewA = this.f5668a.a(i3);
            this.f5669b.e(iC, iB, this.f5668a.e(viewA), this.f5668a.d(viewA));
            if (i5 != 0) {
                this.f5669b.d();
                this.f5669b.a(i5);
                if (this.f5669b.b()) {
                    return viewA;
                }
            }
            if (i6 != 0) {
                this.f5669b.d();
                this.f5669b.a(i6);
                if (this.f5669b.b()) {
                    view = viewA;
                }
            }
            i3 += i7;
        }
        return view;
    }

    public boolean b(View view, int i3) {
        this.f5669b.e(this.f5668a.c(), this.f5668a.b(), this.f5668a.e(view), this.f5668a.d(view));
        if (i3 == 0) {
            return false;
        }
        this.f5669b.d();
        this.f5669b.a(i3);
        return this.f5669b.b();
    }
}
