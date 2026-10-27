package com.amazon.coral.retry.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ClassList<T> {
    private final List<Class<? extends T>> types;

    public ClassList(Collection<Class<? extends T>> collection) {
        LinkedList linkedList = new LinkedList();
        this.types = linkedList;
        linkedList.addAll(collection);
    }

    public boolean isInstanceOfAny(T t) {
        Iterator<Class<? extends T>> it = this.types.iterator();
        while (it.hasNext()) {
            if (it.next().isInstance(t)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInstanceOfAny(Collection<T> collection) {
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (isInstanceOfAny(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static List<Throwable> getCausalChain(Throwable th) {
        ArrayList arrayList = new ArrayList();
        while (th != null) {
            arrayList.add(th);
            th = th.getCause();
        }
        return Collections.unmodifiableList(arrayList);
    }
}
