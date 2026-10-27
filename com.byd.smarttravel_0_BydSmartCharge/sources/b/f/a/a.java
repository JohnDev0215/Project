package b.f.a;

import b.e.g;
import b.g.i.e;
import b.g.i.f;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: compiled from: DirectedAcyclicGraph.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e<ArrayList<T>> f911a = new f(10);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g<T, ArrayList<T>> f912b = new g<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<T> f913c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashSet<T> f914d = new HashSet<>();

    public void a(T t, T t2) {
        if (!this.f912b.containsKey(t) || !this.f912b.containsKey(t2)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList<T> arrayListF = this.f912b.get(t);
        if (arrayListF == null) {
            arrayListF = f();
            this.f912b.put(t, arrayListF);
        }
        arrayListF.add(t2);
    }

    public void b(T t) {
        if (this.f912b.containsKey(t)) {
            return;
        }
        this.f912b.put(t, null);
    }

    public void c() {
        int size = this.f912b.size();
        for (int i = 0; i < size; i++) {
            ArrayList<T> arrayListM = this.f912b.m(i);
            if (arrayListM != null) {
                k(arrayListM);
            }
        }
        this.f912b.clear();
    }

    public boolean d(T t) {
        return this.f912b.containsKey(t);
    }

    public final void e(T t, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (arrayList.contains(t)) {
            return;
        }
        if (hashSet.contains(t)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(t);
        ArrayList<T> arrayList2 = this.f912b.get(t);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                e(arrayList2.get(i), arrayList, hashSet);
            }
        }
        hashSet.remove(t);
        arrayList.add(t);
    }

    public final ArrayList<T> f() {
        ArrayList<T> arrayListB = this.f911a.b();
        return arrayListB == null ? new ArrayList<>() : arrayListB;
    }

    public List g(T t) {
        return this.f912b.get(t);
    }

    public List<T> h(T t) {
        int size = this.f912b.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            ArrayList<T> arrayListM = this.f912b.m(i);
            if (arrayListM != null && arrayListM.contains(t)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.f912b.i(i));
            }
        }
        return arrayList;
    }

    public ArrayList<T> i() {
        this.f913c.clear();
        this.f914d.clear();
        int size = this.f912b.size();
        for (int i = 0; i < size; i++) {
            e(this.f912b.i(i), this.f913c, this.f914d);
        }
        return this.f913c;
    }

    public boolean j(T t) {
        int size = this.f912b.size();
        for (int i = 0; i < size; i++) {
            ArrayList<T> arrayListM = this.f912b.m(i);
            if (arrayListM != null && arrayListM.contains(t)) {
                return true;
            }
        }
        return false;
    }

    public final void k(ArrayList<T> arrayList) {
        arrayList.clear();
        this.f911a.a(arrayList);
    }
}
