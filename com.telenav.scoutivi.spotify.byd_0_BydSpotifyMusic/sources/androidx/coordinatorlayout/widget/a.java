package androidx.coordinatorlayout.widget;

import h0.e;
import h0.f;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import n.g;

/* JADX INFO: compiled from: DirectedAcyclicGraph.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e<ArrayList<T>> f1943a = new f(10);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g<T, ArrayList<T>> f1944b = new g<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ArrayList<T> f1945c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final HashSet<T> f1946d = new HashSet<>();

    private void e(T t6, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (arrayList.contains(t6)) {
            return;
        }
        if (hashSet.contains(t6)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(t6);
        ArrayList<T> arrayList2 = this.f1944b.get(t6);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i7 = 0; i7 < size; i7++) {
                e(arrayList2.get(i7), arrayList, hashSet);
            }
        }
        hashSet.remove(t6);
        arrayList.add(t6);
    }

    private ArrayList<T> f() {
        ArrayList<T> arrayListB = this.f1943a.b();
        return arrayListB == null ? new ArrayList<>() : arrayListB;
    }

    private void k(ArrayList<T> arrayList) {
        arrayList.clear();
        this.f1943a.a(arrayList);
    }

    public void a(T t6, T t7) {
        if (!this.f1944b.containsKey(t6) || !this.f1944b.containsKey(t7)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList<T> arrayListF = this.f1944b.get(t6);
        if (arrayListF == null) {
            arrayListF = f();
            this.f1944b.put(t6, arrayListF);
        }
        arrayListF.add(t7);
    }

    public void b(T t6) {
        if (this.f1944b.containsKey(t6)) {
            return;
        }
        this.f1944b.put(t6, null);
    }

    public void c() {
        int size = this.f1944b.size();
        for (int i7 = 0; i7 < size; i7++) {
            ArrayList<T> arrayListO = this.f1944b.o(i7);
            if (arrayListO != null) {
                k(arrayListO);
            }
        }
        this.f1944b.clear();
    }

    public boolean d(T t6) {
        return this.f1944b.containsKey(t6);
    }

    public List g(T t6) {
        return this.f1944b.get(t6);
    }

    public List<T> h(T t6) {
        int size = this.f1944b.size();
        ArrayList arrayList = null;
        for (int i7 = 0; i7 < size; i7++) {
            ArrayList<T> arrayListO = this.f1944b.o(i7);
            if (arrayListO != null && arrayListO.contains(t6)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.f1944b.k(i7));
            }
        }
        return arrayList;
    }

    public ArrayList<T> i() {
        this.f1945c.clear();
        this.f1946d.clear();
        int size = this.f1944b.size();
        for (int i7 = 0; i7 < size; i7++) {
            e(this.f1944b.k(i7), this.f1945c, this.f1946d);
        }
        return this.f1945c;
    }

    public boolean j(T t6) {
        int size = this.f1944b.size();
        for (int i7 = 0; i7 < size; i7++) {
            ArrayList<T> arrayListO = this.f1944b.o(i7);
            if (arrayListO != null && arrayListO.contains(t6)) {
                return true;
            }
        }
        return false;
    }
}
