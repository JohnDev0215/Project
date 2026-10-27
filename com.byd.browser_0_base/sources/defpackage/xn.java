package defpackage;

import android.os.Handler;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: compiled from: ChildProcessRanking.java */
/* JADX INFO: loaded from: classes3.dex */
public final class xn implements Iterable<qn> {
    public boolean i;
    public boolean j;
    public final Handler e = new Handler();
    public final ArrayList g = new ArrayList();
    public final Runnable h = new an2(10, this);
    public final int f = -1;

    /* JADX INFO: compiled from: ChildProcessRanking.java */
    public static class a {
        public final qn a;
        public boolean b = false;
        public long c = 1;
        public boolean d = false;
        public int e = 1;

        public a(qn qnVar) {
            this.a = qnVar;
        }

        public final boolean a() {
            return this.e == 0 && !(this.b && ((this.c > 0L ? 1 : (this.c == 0L ? 0 : -1)) == 0 || this.d));
        }
    }

    /* JADX INFO: compiled from: ChildProcessRanking.java */
    public static class b implements Comparator<a> {
        public static int a(a aVar, a aVar2) {
            boolean z = aVar.b;
            boolean z2 = (z && aVar.c == 0) || aVar.e == 2;
            boolean z3 = aVar2.b;
            boolean z4 = (z3 && aVar2.c == 0) || aVar2.e == 2;
            if (z2 && z4) {
                return b(aVar, aVar2);
            }
            if (z2 && !z4) {
                return -1;
            }
            if (!z2 && z4) {
                return 1;
            }
            boolean z5 = (z && aVar.c > 0 && aVar.d) || aVar.e == 1;
            boolean z6 = (z3 && aVar2.c > 0 && aVar2.d) || aVar2.e == 1;
            if (z5 && z6) {
                return b(aVar, aVar2);
            }
            if (z5 && !z6) {
                return -1;
            }
            if (!z5 && z6) {
                return 1;
            }
            if (z && z3) {
                return b(aVar, aVar2);
            }
            if (!z || z3) {
                return (z || !z3) ? 0 : 1;
            }
            return -1;
        }

        public static int b(a aVar, a aVar2) {
            boolean z = aVar.d;
            if (z && !aVar2.d) {
                return -1;
            }
            if (z || !aVar2.d) {
                return Long.signum(aVar.c - aVar2.c);
            }
            return 1;
        }
    }

    /* JADX INFO: compiled from: ChildProcessRanking.java */
    public class c implements Iterator<qn> {
        public int e;

        public c() {
            this.e = xn.this.g.size() - 1;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.e >= 0;
        }

        @Override // java.util.Iterator
        public final qn next() {
            ArrayList arrayList = xn.this.g;
            int i = this.e;
            this.e = i - 1;
            return ((a) arrayList.get(i)).a;
        }
    }

    public static void a(xn xnVar) {
        xnVar.j = false;
        ArrayList arrayList = xnVar.g;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            a aVar = (a) arrayList.get(size);
            if (!aVar.a()) {
                qn qnVar = aVar.a;
                if (qnVar.g()) {
                    qnVar.x.a();
                }
            }
        }
    }

    public final void b(int i) {
        ArrayList arrayList = this.g;
        a aVar = (a) arrayList.remove(i);
        int i2 = 0;
        while (i2 < arrayList.size() && b.a((a) arrayList.get(i2), aVar) < 0) {
            i2++;
        }
        arrayList.add(i2, aVar);
        if (this.i) {
            boolean zA = aVar.a();
            qn qnVar = aVar.a;
            if (!zA) {
                if (qnVar.B != 0) {
                    qnVar.n(0, 0);
                    return;
                }
                return;
            }
            boolean z = i2 == 0;
            boolean z2 = i2 == arrayList.size() - 1;
            int i3 = z ? 0 : ((a) arrayList.get(i2 - 1)).a.C;
            int i4 = z2 ? Integer.MAX_VALUE : ((a) arrayList.get(i2 + 1)).a.C;
            int i5 = qnVar.C;
            if (i5 <= i3 || i5 >= i4) {
                int i6 = i4 - i3;
                if (i6 > 65536) {
                    qnVar.n(1, i4 - 32768);
                } else if (i6 > 2) {
                    qnVar.n(1, (i6 / 2) + i3);
                } else {
                    c();
                }
                if (this.j) {
                    return;
                }
                this.e.postDelayed(this.h, 1000L);
                this.j = true;
            }
        }
    }

    public final void c() {
        ArrayList arrayList = this.g;
        int i = 2147450879;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            a aVar = (a) arrayList.get(size);
            if (!aVar.a()) {
                return;
            }
            aVar.a.n(1, i);
            i -= 32768;
        }
    }

    @Override // java.lang.Iterable
    public final Iterator<qn> iterator() {
        return new c();
    }
}
