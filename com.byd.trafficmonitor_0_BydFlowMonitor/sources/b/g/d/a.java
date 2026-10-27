package b.g.d;

import b.e.g;
import b.h.l.d;
import b.h.l.e;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: compiled from: DirectedAcyclicGraph.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d<ArrayList<T>> f2340a = new e(10);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g<T, ArrayList<T>> f2341b = new g<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<T> f2342c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashSet<T> f2343d = new HashSet<>();

    public void a(T t, T t2) {
        if (!this.f2341b.containsKey(t) || !this.f2341b.containsKey(t2)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList<T> arrayListF = this.f2341b.get(t);
        if (arrayListF == null) {
            arrayListF = f();
            this.f2341b.put(t, arrayListF);
        }
        arrayListF.add(t2);
    }

    public void b(T t) {
        if (this.f2341b.containsKey(t)) {
            return;
        }
        this.f2341b.put(t, null);
    }

    public void c() {
        int size = this.f2341b.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<T> arrayListM = this.f2341b.m(i2);
            if (arrayListM != null) {
                k(arrayListM);
            }
        }
        this.f2341b.clear();
    }

    public boolean d(T t) {
        return this.f2341b.containsKey(t);
    }

    public final void e(T t, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (arrayList.contains(t)) {
            return;
        }
        if (hashSet.contains(t)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(t);
        ArrayList<T> arrayList2 = this.f2341b.get(t);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i2 = 0; i2 < size; i2++) {
                e(arrayList2.get(i2), arrayList, hashSet);
            }
        }
        hashSet.remove(t);
        arrayList.add(t);
    }

    public final ArrayList<T> f() {
        ArrayList<T> arrayListB = this.f2340a.b();
        return arrayListB == null ? new ArrayList<>() : arrayListB;
    }

    public List g(T t) {
        return this.f2341b.get(t);
    }

    public List<T> h(T t) {
        int size = this.f2341b.size();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<T> arrayListM = this.f2341b.m(i2);
            if (arrayListM != null && arrayListM.contains(t)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.f2341b.i(i2));
            }
        }
        return arrayList;
    }

    public ArrayList<T> i() {
        this.f2342c.clear();
        this.f2343d.clear();
        int size = this.f2341b.size();
        for (int i2 = 0; i2 < size; i2++) {
            e(this.f2341b.i(i2), this.f2342c, this.f2343d);
        }
        return this.f2342c;
    }

    public boolean j(T t) {
        int size = this.f2341b.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<T> arrayListM = this.f2341b.m(i2);
            if (arrayListM != null && arrayListM.contains(t)) {
                return true;
            }
        }
        return false;
    }

    public final void k(ArrayList<T> arrayList) {
        arrayList.clear();
        this.f2340a.a(arrayList);
    }
}
