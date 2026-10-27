package z;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import k0.e;
import k0.f;
import s.g;

/* JADX INFO: compiled from: DirectedAcyclicGraph.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e<ArrayList<T>> f6005a = new f(10);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g<T, ArrayList<T>> f6006b = new g<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<T> f6007c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashSet<T> f6008d = new HashSet<>();

    public void a(T t3, T t4) {
        if (!this.f6006b.containsKey(t3) || !this.f6006b.containsKey(t4)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList<T> arrayListF = this.f6006b.get(t3);
        if (arrayListF == null) {
            arrayListF = f();
            this.f6006b.put(t3, arrayListF);
        }
        arrayListF.add(t4);
    }

    public void b(T t3) {
        if (this.f6006b.containsKey(t3)) {
            return;
        }
        this.f6006b.put(t3, null);
    }

    public void c() {
        int size = this.f6006b.size();
        for (int i3 = 0; i3 < size; i3++) {
            ArrayList<T> arrayListM = this.f6006b.m(i3);
            if (arrayListM != null) {
                k(arrayListM);
            }
        }
        this.f6006b.clear();
    }

    public boolean d(T t3) {
        return this.f6006b.containsKey(t3);
    }

    public final void e(T t3, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (arrayList.contains(t3)) {
            return;
        }
        if (hashSet.contains(t3)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(t3);
        ArrayList<T> arrayList2 = this.f6006b.get(t3);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i3 = 0; i3 < size; i3++) {
                e(arrayList2.get(i3), arrayList, hashSet);
            }
        }
        hashSet.remove(t3);
        arrayList.add(t3);
    }

    public final ArrayList<T> f() {
        ArrayList<T> arrayListB = this.f6005a.b();
        return arrayListB == null ? new ArrayList<>() : arrayListB;
    }

    public List g(T t3) {
        return this.f6006b.get(t3);
    }

    public List<T> h(T t3) {
        int size = this.f6006b.size();
        ArrayList arrayList = null;
        for (int i3 = 0; i3 < size; i3++) {
            ArrayList<T> arrayListM = this.f6006b.m(i3);
            if (arrayListM != null && arrayListM.contains(t3)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.f6006b.i(i3));
            }
        }
        return arrayList;
    }

    public ArrayList<T> i() {
        this.f6007c.clear();
        this.f6008d.clear();
        int size = this.f6006b.size();
        for (int i3 = 0; i3 < size; i3++) {
            e(this.f6006b.i(i3), this.f6007c, this.f6008d);
        }
        return this.f6007c;
    }

    public boolean j(T t3) {
        int size = this.f6006b.size();
        for (int i3 = 0; i3 < size; i3++) {
            ArrayList<T> arrayListM = this.f6006b.m(i3);
            if (arrayListM != null && arrayListM.contains(t3)) {
                return true;
            }
        }
        return false;
    }

    public final void k(ArrayList<T> arrayList) {
        arrayList.clear();
        this.f6005a.a(arrayList);
    }
}
