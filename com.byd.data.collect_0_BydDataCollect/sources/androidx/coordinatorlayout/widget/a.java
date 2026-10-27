package androidx.coordinatorlayout.widget;

import b.d.g;
import b.g.j.e;
import b.g.j.f;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: compiled from: DirectedAcyclicGraph.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e<ArrayList<T>> f1143a = new f(10);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g<T, ArrayList<T>> f1144b = new g<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ArrayList<T> f1145c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final HashSet<T> f1146d = new HashSet<>();

    private void e(T t, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (arrayList.contains(t)) {
            return;
        }
        if (hashSet.contains(t)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(t);
        ArrayList<T> arrayList2 = this.f1144b.get(t);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i2 = 0; i2 < size; i2++) {
                e(arrayList2.get(i2), arrayList, hashSet);
            }
        }
        hashSet.remove(t);
        arrayList.add(t);
    }

    private ArrayList<T> f() {
        ArrayList<T> arrayListB = this.f1143a.b();
        return arrayListB == null ? new ArrayList<>() : arrayListB;
    }

    private void k(ArrayList<T> arrayList) {
        arrayList.clear();
        this.f1143a.a(arrayList);
    }

    public void a(T t, T t2) {
        if (!this.f1144b.containsKey(t) || !this.f1144b.containsKey(t2)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList<T> arrayListF = this.f1144b.get(t);
        if (arrayListF == null) {
            arrayListF = f();
            this.f1144b.put(t, arrayListF);
        }
        arrayListF.add(t2);
    }

    public void b(T t) {
        if (this.f1144b.containsKey(t)) {
            return;
        }
        this.f1144b.put(t, null);
    }

    public void c() {
        int size = this.f1144b.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<T> arrayListM = this.f1144b.m(i2);
            if (arrayListM != null) {
                k(arrayListM);
            }
        }
        this.f1144b.clear();
    }

    public boolean d(T t) {
        return this.f1144b.containsKey(t);
    }

    public List g(T t) {
        return this.f1144b.get(t);
    }

    public List<T> h(T t) {
        int size = this.f1144b.size();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<T> arrayListM = this.f1144b.m(i2);
            if (arrayListM != null && arrayListM.contains(t)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.f1144b.i(i2));
            }
        }
        return arrayList;
    }

    public ArrayList<T> i() {
        this.f1145c.clear();
        this.f1146d.clear();
        int size = this.f1144b.size();
        for (int i2 = 0; i2 < size; i2++) {
            e(this.f1144b.i(i2), this.f1145c, this.f1146d);
        }
        return this.f1145c;
    }

    public boolean j(T t) {
        int size = this.f1144b.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<T> arrayListM = this.f1144b.m(i2);
            if (arrayListM != null && arrayListM.contains(t)) {
                return true;
            }
        }
        return false;
    }
}
