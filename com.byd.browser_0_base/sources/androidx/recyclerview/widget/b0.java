package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: compiled from: ViewBoundsCheck.java */
/* JADX INFO: loaded from: classes.dex */
public final class b0 {
    public final b a;
    public final a b = new a();

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    public static class a {
        public int a = 0;
        public int b;
        public int c;
        public int d;
        public int e;

        public final boolean a() {
            int i = this.a;
            int i2 = 2;
            if ((i & 7) != 0) {
                int i3 = this.d;
                int i4 = this.b;
                if ((((i3 > i4 ? 1 : i3 == i4 ? 2 : 4) << 0) & i) == 0) {
                    return false;
                }
            }
            if ((i & 112) != 0) {
                int i5 = this.d;
                int i6 = this.c;
                if ((((i5 > i6 ? 1 : i5 == i6 ? 2 : 4) << 4) & i) == 0) {
                    return false;
                }
            }
            if ((i & 1792) != 0) {
                int i7 = this.e;
                int i8 = this.b;
                if ((((i7 > i8 ? 1 : i7 == i8 ? 2 : 4) << 8) & i) == 0) {
                    return false;
                }
            }
            if ((i & 28672) != 0) {
                int i9 = this.e;
                int i10 = this.c;
                if (i9 > i10) {
                    i2 = 1;
                } else if (i9 != i10) {
                    i2 = 4;
                }
                if (((i2 << 12) & i) == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: compiled from: ViewBoundsCheck.java */
    public interface b {
        int a(View view);

        int b();

        int c();

        View d(int i);

        int e(View view);
    }

    public b0(b bVar) {
        this.a = bVar;
    }

    public final View a(int i, int i2, int i3, int i4) {
        b bVar = this.a;
        int iB = bVar.b();
        int iC = bVar.c();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewD = bVar.d(i);
            int iA = bVar.a(viewD);
            int iE = bVar.e(viewD);
            a aVar = this.b;
            aVar.b = iB;
            aVar.c = iC;
            aVar.d = iA;
            aVar.e = iE;
            if (i3 != 0) {
                aVar.a = i3 | 0;
                if (aVar.a()) {
                    return viewD;
                }
            }
            if (i4 != 0) {
                aVar.a = i4 | 0;
                if (aVar.a()) {
                    view = viewD;
                }
            }
            i += i5;
        }
        return view;
    }

    public final boolean b(View view) {
        b bVar = this.a;
        int iB = bVar.b();
        int iC = bVar.c();
        int iA = bVar.a(view);
        int iE = bVar.e(view);
        a aVar = this.b;
        aVar.b = iB;
        aVar.c = iC;
        aVar.d = iA;
        aVar.e = iE;
        aVar.a = 24579 | 0;
        return aVar.a();
    }
}
