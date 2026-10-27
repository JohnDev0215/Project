package io.objectbox.relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ToMany<TARGET> implements List<TARGET>, Serializable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Integer f2602e = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<TARGET> f2603a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Map<TARGET, Integer> f2604b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile Map<TARGET, Boolean> f2605c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Map<TARGET, Boolean> f2606d;

    @Override // java.util.List
    public synchronized void add(int i, TARGET target) {
        e(target);
        this.f2603a.add(i, target);
    }

    @Override // java.util.List, java.util.Collection
    public synchronized boolean add(TARGET target) {
        e(target);
        return this.f2603a.add(target);
    }

    @Override // java.util.List
    public synchronized boolean addAll(int i, Collection<? extends TARGET> collection) {
        f(collection);
        return this.f2603a.addAll(i, collection);
    }

    @Override // java.util.List, java.util.Collection
    public synchronized boolean addAll(Collection<? extends TARGET> collection) {
        f(collection);
        return this.f2603a.addAll(collection);
    }

    public final void c() {
        if (this.f2603a == null) {
            throw null;
        }
    }

    @Override // java.util.List, java.util.Collection
    public synchronized void clear() {
        d();
        List<TARGET> list = this.f2603a;
        if (list != null) {
            Iterator<TARGET> it = list.iterator();
            while (it.hasNext()) {
                this.f2606d.put(it.next(), Boolean.TRUE);
            }
            list.clear();
        }
        Map<TARGET, Boolean> map = this.f2605c;
        if (map != null) {
            map.clear();
        }
        Map<TARGET, Integer> map2 = this.f2604b;
        if (map2 != null) {
            map2.clear();
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        c();
        return this.f2603a.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        c();
        return this.f2603a.containsAll(collection);
    }

    public final void d() {
        c();
        if (this.f2605c == null) {
            synchronized (this) {
                if (this.f2605c == null) {
                    this.f2605c = new LinkedHashMap();
                    this.f2606d = new LinkedHashMap();
                    this.f2604b = new HashMap();
                    for (TARGET target : this.f2603a) {
                        Integer numPut = this.f2604b.put(target, f2602e);
                        if (numPut != null) {
                            this.f2604b.put(target, Integer.valueOf(numPut.intValue() + 1));
                        }
                    }
                }
            }
        }
    }

    public final void e(TARGET target) {
        d();
        Integer numPut = this.f2604b.put(target, f2602e);
        if (numPut != null) {
            this.f2604b.put(target, Integer.valueOf(numPut.intValue() + 1));
        }
        this.f2605c.put(target, Boolean.TRUE);
        this.f2606d.remove(target);
    }

    public final void f(Collection<? extends TARGET> collection) {
        d();
        Iterator<? extends TARGET> it = collection.iterator();
        while (it.hasNext()) {
            e(it.next());
        }
    }

    public final void g(TARGET target) {
        Map map;
        Object objValueOf;
        d();
        Integer numRemove = this.f2604b.remove(target);
        if (numRemove != null) {
            if (numRemove.intValue() == 1) {
                this.f2604b.remove(target);
                this.f2605c.remove(target);
                map = this.f2606d;
                objValueOf = Boolean.TRUE;
            } else {
                if (numRemove.intValue() <= 1) {
                    throw new IllegalStateException("Illegal count: " + numRemove);
                }
                map = this.f2604b;
                objValueOf = Integer.valueOf(numRemove.intValue() - 1);
            }
            map.put(target, objValueOf);
        }
    }

    @Override // java.util.List
    public TARGET get(int i) {
        c();
        return this.f2603a.get(i);
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        c();
        return this.f2603a.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        c();
        return this.f2603a.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<TARGET> iterator() {
        c();
        return this.f2603a.iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        c();
        return this.f2603a.lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<TARGET> listIterator() {
        c();
        return this.f2603a.listIterator();
    }

    @Override // java.util.List
    public ListIterator<TARGET> listIterator(int i) {
        c();
        return this.f2603a.listIterator(i);
    }

    @Override // java.util.List
    public synchronized TARGET remove(int i) {
        TARGET targetRemove;
        d();
        targetRemove = this.f2603a.remove(i);
        g(targetRemove);
        return targetRemove;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List, java.util.Collection
    public synchronized boolean remove(Object obj) {
        boolean zRemove;
        d();
        zRemove = this.f2603a.remove(obj);
        if (zRemove) {
            g(obj);
        }
        return zRemove;
    }

    @Override // java.util.List, java.util.Collection
    public synchronized boolean removeAll(Collection<?> collection) {
        boolean zRemove;
        zRemove = false;
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.List, java.util.Collection
    public synchronized boolean retainAll(Collection<?> collection) {
        boolean z;
        d();
        z = false;
        ArrayList arrayList = null;
        for (TARGET target : this.f2603a) {
            if (!collection.contains(target)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(target);
                z = true;
            }
        }
        if (arrayList != null) {
            removeAll(arrayList);
        }
        return z;
    }

    @Override // java.util.List
    public synchronized TARGET set(int i, TARGET target) {
        TARGET target2;
        d();
        target2 = this.f2603a.set(i, target);
        g(target2);
        e(target);
        return target2;
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        c();
        return this.f2603a.size();
    }

    @Override // java.util.List
    public List<TARGET> subList(int i, int i2) {
        c();
        return this.f2603a.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        c();
        return this.f2603a.toArray();
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        c();
        return (T[]) this.f2603a.toArray(tArr);
    }
}
