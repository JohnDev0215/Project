package s3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import t3.a;
import t4.b;

/* JADX INFO: compiled from: WeakCompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b, w4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private WeakHashMap<b, C0147a> f9507e = new WeakHashMap<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private volatile boolean f9508g;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: s3.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: WeakCompositeDisposable.java */
    public static class C0147a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final C0147a f9509a = new C0147a();

        private C0147a() {
        }
    }

    private void d() {
        WeakHashMap<b, C0147a> weakHashMap = this.f9507e;
        if (weakHashMap == null) {
            return;
        }
        Iterator<b> it = weakHashMap.keySet().iterator();
        while (it.hasNext()) {
            if (it.next().isDisposed()) {
                it.remove();
            }
        }
    }

    private void e(WeakHashMap<b, C0147a> weakHashMap) {
        if (weakHashMap == null) {
            return;
        }
        ArrayList arrayList = null;
        for (b bVar : weakHashMap.keySet()) {
            try {
                bVar.dispose();
                if (bVar instanceof a.b) {
                    ((a.b) bVar).d();
                }
            } catch (Throwable th) {
                u4.b.b(th);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
            }
        }
        if (arrayList != null) {
            if (arrayList.size() != 1) {
                throw new u4.a(arrayList);
            }
            throw b5.b.a((Throwable) arrayList.get(0));
        }
    }

    @Override // w4.a
    public boolean a(b bVar) {
        synchronized (this) {
            if (this.f9508g) {
                bVar.dispose();
                return false;
            }
            WeakHashMap<b, C0147a> weakHashMap = this.f9507e;
            if (weakHashMap == null) {
                weakHashMap = new WeakHashMap<>();
                this.f9507e = weakHashMap;
            }
            d();
            weakHashMap.put(bVar, C0147a.f9509a);
            return true;
        }
    }

    @Override // w4.a
    public boolean b(b bVar) {
        if (this.f9508g) {
            return false;
        }
        synchronized (this) {
            if (this.f9508g) {
                return false;
            }
            WeakHashMap<b, C0147a> weakHashMap = this.f9507e;
            return weakHashMap == null || weakHashMap.remove(bVar) != null;
        }
    }

    @Override // w4.a
    public boolean c(b bVar) {
        if (!b(bVar)) {
            return false;
        }
        bVar.dispose();
        return true;
    }

    @Override // t4.b
    public void dispose() {
        if (this.f9508g) {
            return;
        }
        synchronized (this) {
            if (this.f9508g) {
                return;
            }
            this.f9508g = true;
            WeakHashMap<b, C0147a> weakHashMap = this.f9507e;
            this.f9507e = null;
            e(weakHashMap);
        }
    }

    public WeakHashMap<b, C0147a> f() {
        return this.f9507e;
    }

    @Override // t4.b
    public boolean isDisposed() {
        return this.f9508g;
    }
}
