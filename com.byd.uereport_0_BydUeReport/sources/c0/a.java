package c0;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import n0.e;
import n0.f;
import v.g;

/* JADX INFO: compiled from: DirectedAcyclicGraph.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e<ArrayList<T>> f2139a = new f(10);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g<T, ArrayList<T>> f2140b = new g<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<T> f2141c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashSet<T> f2142d = new HashSet<>();

    public void a(T t7, T t8) {
        if (!this.f2140b.containsKey(t7) || !this.f2140b.containsKey(t8)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList<T> arrayListF = this.f2140b.get(t7);
        if (arrayListF == null) {
            arrayListF = f();
            this.f2140b.put(t7, arrayListF);
        }
        arrayListF.add(t8);
    }

    public void b(T t7) {
        if (this.f2140b.containsKey(t7)) {
            return;
        }
        this.f2140b.put(t7, null);
    }

    public void c() {
        int size = this.f2140b.size();
        for (int i8 = 0; i8 < size; i8++) {
            ArrayList<T> arrayListM = this.f2140b.m(i8);
            if (arrayListM != null) {
                k(arrayListM);
            }
        }
        this.f2140b.clear();
    }

    public boolean d(T t7) {
        return this.f2140b.containsKey(t7);
    }

    public final void e(T t7, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (arrayList.contains(t7)) {
            return;
        }
        if (hashSet.contains(t7)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(t7);
        ArrayList<T> arrayList2 = this.f2140b.get(t7);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i8 = 0; i8 < size; i8++) {
                e(arrayList2.get(i8), arrayList, hashSet);
            }
        }
        hashSet.remove(t7);
        arrayList.add(t7);
    }

    public final ArrayList<T> f() {
        ArrayList<T> arrayListB = this.f2139a.b();
        return arrayListB == null ? new ArrayList<>() : arrayListB;
    }

    public List g(T t7) {
        return this.f2140b.get(t7);
    }

    public List<T> h(T t7) {
        int size = this.f2140b.size();
        ArrayList arrayList = null;
        for (int i8 = 0; i8 < size; i8++) {
            ArrayList<T> arrayListM = this.f2140b.m(i8);
            if (arrayListM != null && arrayListM.contains(t7)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.f2140b.i(i8));
            }
        }
        return arrayList;
    }

    public ArrayList<T> i() {
        this.f2141c.clear();
        this.f2142d.clear();
        int size = this.f2140b.size();
        for (int i8 = 0; i8 < size; i8++) {
            e(this.f2140b.i(i8), this.f2141c, this.f2142d);
        }
        return this.f2141c;
    }

    public boolean j(T t7) {
        int size = this.f2140b.size();
        for (int i8 = 0; i8 < size; i8++) {
            ArrayList<T> arrayListM = this.f2140b.m(i8);
            if (arrayListM != null && arrayListM.contains(t7)) {
                return true;
            }
        }
        return false;
    }

    public final void k(ArrayList<T> arrayList) {
        arrayList.clear();
        this.f2139a.a(arrayList);
    }
}
