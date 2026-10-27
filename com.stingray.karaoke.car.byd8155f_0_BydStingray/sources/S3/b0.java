package s3;

import android.view.View;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import h2.AbstractC1347d;
import java.util.ArrayList;
import v.AbstractC2530a;

/* JADX INFO: loaded from: classes.dex */
public class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f22256a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22257b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f22258e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f22259f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f22260g;

    public b0(int i7) {
        this.f22256a = 1;
        this.f22257b = i7;
        if (i7 <= 0) {
            AbstractC2530a.c("maxSize <= 0");
            throw null;
        }
        this.f22259f = new K3.n(2);
        this.f22260g = new m6.b();
    }

    public void a() {
        View view = (View) AbstractC1347d.e(1, (ArrayList) this.f22259f);
        Y y10 = (Y) view.getLayoutParams();
        this.c = ((StaggeredGridLayoutManager) this.f22260g).f12175r.b(view);
        y10.getClass();
    }

    public void b() {
        ((ArrayList) this.f22259f).clear();
        this.f22257b = Integer.MIN_VALUE;
        this.c = Integer.MIN_VALUE;
        this.d = 0;
    }

    public void c(Object obj, Object obj2, Object obj3) {
        A7.m.f(obj, "key");
        A7.m.f(obj2, "oldValue");
    }

    public int d() {
        return ((StaggeredGridLayoutManager) this.f22260g).f12180w ? f(r1.size() - 1, -1) : f(0, ((ArrayList) this.f22259f).size());
    }

    public int e() {
        return ((StaggeredGridLayoutManager) this.f22260g).f12180w ? f(0, ((ArrayList) this.f22259f).size()) : f(r1.size() - 1, -1);
    }

    public int f(int i7, int i10) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) this.f22260g;
        int iK = staggeredGridLayoutManager.f12175r.k();
        int iG = staggeredGridLayoutManager.f12175r.g();
        int i11 = i10 > i7 ? 1 : -1;
        while (i7 != i10) {
            View view = (View) ((ArrayList) this.f22259f).get(i7);
            int iE = staggeredGridLayoutManager.f12175r.e(view);
            int iB = staggeredGridLayoutManager.f12175r.b(view);
            boolean z6 = iE <= iG;
            boolean z10 = iB >= iK;
            if (z6 && z10 && (iE < iK || iB > iG)) {
                return AbstractC2367F.D(view);
            }
            i7 += i11;
        }
        return -1;
    }

    public Object g(Object obj) {
        A7.m.f(obj, "key");
        synchronized (((m6.b) this.f22260g)) {
            K3.n nVar = (K3.n) this.f22259f;
            nVar.getClass();
            Object obj2 = nVar.f3576a.get(obj);
            if (obj2 != null) {
                this.d++;
                return obj2;
            }
            this.f22258e++;
            return null;
        }
    }

    public int h(int i7) {
        int i10 = this.c;
        if (i10 != Integer.MIN_VALUE) {
            return i10;
        }
        if (((ArrayList) this.f22259f).size() == 0) {
            return i7;
        }
        a();
        return this.c;
    }

    public View i(int i7, int i10) {
        ArrayList arrayList = (ArrayList) this.f22259f;
        StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) this.f22260g;
        View view = null;
        if (i10 != -1) {
            int size = arrayList.size() - 1;
            while (size >= 0) {
                View view2 = (View) arrayList.get(size);
                if ((staggeredGridLayoutManager.f12180w && AbstractC2367F.D(view2) >= i7) || ((!staggeredGridLayoutManager.f12180w && AbstractC2367F.D(view2) <= i7) || !view2.hasFocusable())) {
                    break;
                }
                size--;
                view = view2;
            }
        } else {
            int size2 = arrayList.size();
            int i11 = 0;
            while (i11 < size2) {
                View view3 = (View) arrayList.get(i11);
                if ((staggeredGridLayoutManager.f12180w && AbstractC2367F.D(view3) <= i7) || ((!staggeredGridLayoutManager.f12180w && AbstractC2367F.D(view3) >= i7) || !view3.hasFocusable())) {
                    break;
                }
                i11++;
                view = view3;
            }
        }
        return view;
    }

    public int j(int i7) {
        int i10 = this.f22257b;
        if (i10 != Integer.MIN_VALUE) {
            return i10;
        }
        if (((ArrayList) this.f22259f).size() == 0) {
            return i7;
        }
        View view = (View) ((ArrayList) this.f22259f).get(0);
        Y y10 = (Y) view.getLayoutParams();
        this.f22257b = ((StaggeredGridLayoutManager) this.f22260g).f12175r.e(view);
        y10.getClass();
        return this.f22257b;
    }

    public Object k(Object obj, Object obj2) {
        Object objPut;
        A7.m.f(obj, "key");
        A7.m.f(obj2, "value");
        synchronized (((m6.b) this.f22260g)) {
            this.c += l(obj, obj2);
            K3.n nVar = (K3.n) this.f22259f;
            nVar.getClass();
            objPut = nVar.f3576a.put(obj, obj2);
            if (objPut != null) {
                this.c -= l(obj, objPut);
            }
        }
        if (objPut != null) {
            c(obj, objPut, obj2);
        }
        n(this.f22257b);
        return objPut;
    }

    public int l(Object obj, Object obj2) {
        int iM = m(obj, obj2);
        if (iM >= 0) {
            return iM;
        }
        String str = "Negative size: " + obj + '=' + obj2;
        A7.m.f(str, "message");
        throw new IllegalStateException(str);
    }

    public int m(Object obj, Object obj2) {
        A7.m.f(obj, "key");
        A7.m.f(obj2, "value");
        return 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0076, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void n(int r6) {
        /*
            r5 = this;
        L0:
            java.lang.Object r0 = r5.f22260g
            m6.b r0 = (m6.b) r0
            monitor-enter(r0)
            int r1 = r5.c     // Catch: java.lang.Throwable -> L1a
            if (r1 < 0) goto L1e
            java.lang.Object r1 = r5.f22259f     // Catch: java.lang.Throwable -> L1a
            K3.n r1 = (K3.n) r1     // Catch: java.lang.Throwable -> L1a
            java.util.LinkedHashMap r1 = r1.f3576a     // Catch: java.lang.Throwable -> L1a
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L1a
            if (r1 == 0) goto L1c
            int r1 = r5.c     // Catch: java.lang.Throwable -> L1a
            if (r1 != 0) goto L1e
            goto L1c
        L1a:
            r5 = move-exception
            goto L7f
        L1c:
            r1 = 1
            goto L1f
        L1e:
            r1 = 0
        L1f:
            if (r1 == 0) goto L77
            int r1 = r5.c     // Catch: java.lang.Throwable -> L1a
            if (r1 <= r6) goto L75
            java.lang.Object r1 = r5.f22259f     // Catch: java.lang.Throwable -> L1a
            K3.n r1 = (K3.n) r1     // Catch: java.lang.Throwable -> L1a
            java.util.LinkedHashMap r1 = r1.f3576a     // Catch: java.lang.Throwable -> L1a
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L1a
            if (r1 == 0) goto L32
            goto L75
        L32:
            java.lang.Object r1 = r5.f22259f     // Catch: java.lang.Throwable -> L1a
            K3.n r1 = (K3.n) r1     // Catch: java.lang.Throwable -> L1a
            java.util.LinkedHashMap r1 = r1.f3576a     // Catch: java.lang.Throwable -> L1a
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> L1a
            java.lang.String r2 = "map.entries"
            A7.m.e(r1, r2)     // Catch: java.lang.Throwable -> L1a
            java.lang.Iterable r1 = (java.lang.Iterable) r1     // Catch: java.lang.Throwable -> L1a
            java.lang.Object r1 = n7.AbstractC1998m.Z(r1)     // Catch: java.lang.Throwable -> L1a
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch: java.lang.Throwable -> L1a
            if (r1 != 0) goto L4d
            monitor-exit(r0)
            return
        L4d:
            java.lang.Object r2 = r1.getKey()     // Catch: java.lang.Throwable -> L1a
            java.lang.Object r1 = r1.getValue()     // Catch: java.lang.Throwable -> L1a
            java.lang.Object r3 = r5.f22259f     // Catch: java.lang.Throwable -> L1a
            K3.n r3 = (K3.n) r3     // Catch: java.lang.Throwable -> L1a
            r3.getClass()     // Catch: java.lang.Throwable -> L1a
            java.lang.String r4 = "key"
            A7.m.f(r2, r4)     // Catch: java.lang.Throwable -> L1a
            java.util.LinkedHashMap r3 = r3.f3576a     // Catch: java.lang.Throwable -> L1a
            r3.remove(r2)     // Catch: java.lang.Throwable -> L1a
            int r3 = r5.c     // Catch: java.lang.Throwable -> L1a
            int r4 = r5.l(r2, r1)     // Catch: java.lang.Throwable -> L1a
            int r3 = r3 - r4
            r5.c = r3     // Catch: java.lang.Throwable -> L1a
            monitor-exit(r0)
            r0 = 0
            r5.c(r2, r1, r0)
            goto L0
        L75:
            monitor-exit(r0)
            return
        L77:
            java.lang.String r5 = "LruCache.sizeOf() is reporting inconsistent results!"
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L1a
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L1a
            throw r6     // Catch: java.lang.Throwable -> L1a
        L7f:
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: s3.b0.n(int):void");
    }

    public String toString() {
        String str;
        switch (this.f22256a) {
            case 1:
                synchronized (((m6.b) this.f22260g)) {
                    try {
                        int i7 = this.d;
                        int i10 = this.f22258e + i7;
                        str = "LruCache[maxSize=" + this.f22257b + ",hits=" + this.d + ",misses=" + this.f22258e + ",hitRate=" + (i10 != 0 ? (i7 * 100) / i10 : 0) + "%]";
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return str;
            default:
                return super.toString();
        }
    }

    public b0(StaggeredGridLayoutManager staggeredGridLayoutManager, int i7) {
        this.f22256a = 0;
        this.f22260g = staggeredGridLayoutManager;
        this.f22259f = new ArrayList();
        this.f22257b = Integer.MIN_VALUE;
        this.c = Integer.MIN_VALUE;
        this.d = 0;
        this.f22258e = i7;
    }
}
