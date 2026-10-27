package org.chromium.base;

import android.util.Pair;
import com.google.protobuf.m0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class CollectionUtil {
    private CollectionUtil() {
    }

    public static <T> void a(Collection<? extends T> collection, Callback<T> callback) {
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            callback.onResult(it.next());
        }
    }

    public static <K, V> void b(Map<? extends K, ? extends V> map, Callback<Map.Entry<K, V>> callback) {
        Iterator<Map.Entry<? extends K, ? extends V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            callback.onResult(it.next());
        }
    }

    public static int[] c(ArrayList arrayList) {
        int[] iArr = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        return iArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SafeVarargs
    public static <K, V> HashMap<K, V> d(Pair<? extends K, ? extends V>... pairArr) {
        m0 m0Var = (HashMap<K, V>) new HashMap();
        for (Pair<? extends K, ? extends V> pair : pairArr) {
            m0Var.put(pair.first, pair.second);
        }
        return m0Var;
    }

    @SafeVarargs
    public static <E> HashSet<E> e(E... eArr) {
        HashSet<E> hashSet = new HashSet<>(eArr.length);
        Collections.addAll(hashSet, eArr);
        return hashSet;
    }

    public static ArrayList f(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Object obj = ((WeakReference) it.next()).get();
            if (obj == null) {
                it.remove();
            } else {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
