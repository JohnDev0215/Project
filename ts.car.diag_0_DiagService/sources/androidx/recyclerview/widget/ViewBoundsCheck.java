package androidx.recyclerview.widget;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
class ViewBoundsCheck {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b f1145a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f1146b = new a();

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewBounds {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1147a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1148b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1149c;
        int d;
        int e;

        a() {
        }

        void a(int i) {
            this.f1147a = i | this.f1147a;
        }

        boolean b() {
            int i = this.f1147a;
            if ((i & 7) != 0 && (i & (c(this.d, this.f1148b) << 0)) == 0) {
                return false;
            }
            int i2 = this.f1147a;
            if ((i2 & 112) != 0 && (i2 & (c(this.d, this.f1149c) << 4)) == 0) {
                return false;
            }
            int i3 = this.f1147a;
            if ((i3 & 1792) != 0 && (i3 & (c(this.e, this.f1148b) << 8)) == 0) {
                return false;
            }
            int i4 = this.f1147a;
            return (i4 & 28672) == 0 || ((c(this.e, this.f1149c) << 12) & i4) != 0;
        }

        int c(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 2 : 4;
        }

        void d() {
            this.f1147a = 0;
        }

        void e(int i, int i2, int i3, int i4) {
            this.f1148b = i;
            this.f1149c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface b {
        View a(int i);

        int b();

        int c();

        int d(View view);

        int e(View view);
    }

    ViewBoundsCheck(b bVar) {
        this.f1145a = bVar;
    }

    View a(int i, int i2, int i3, int i4) {
        int iC = this.f1145a.c();
        int iB = this.f1145a.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewA = this.f1145a.a(i);
            this.f1146b.e(iC, iB, this.f1145a.e(viewA), this.f1145a.d(viewA));
            if (i3 != 0) {
                this.f1146b.d();
                this.f1146b.a(i3);
                if (this.f1146b.b()) {
                    return viewA;
                }
            }
            if (i4 != 0) {
                this.f1146b.d();
                this.f1146b.a(i4);
                if (this.f1146b.b()) {
                    view = viewA;
                }
            }
            i += i5;
        }
        return view;
    }

    boolean b(View view, int i) {
        this.f1146b.e(this.f1145a.c(), this.f1145a.b(), this.f1145a.e(view), this.f1145a.d(view));
        if (i == 0) {
            return false;
        }
        this.f1146b.d();
        this.f1146b.a(i);
        return this.f1146b.b();
    }
}
