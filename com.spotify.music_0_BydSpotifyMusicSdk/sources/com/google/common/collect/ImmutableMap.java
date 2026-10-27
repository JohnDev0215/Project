package com.google.common.collect;

import com.google.common.collect.RegularImmutableMap;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import p.he1;
import p.oq2;

/* JADX INFO: loaded from: classes.dex */
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable {
    public transient ImmutableSet c;
    public transient ImmutableSet e;
    public transient ImmutableCollection k;

    public static class SerializedForm<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Object keys;
        private final Object values;

        public SerializedForm(ImmutableMap immutableMap) {
            Object[] objArr = new Object[immutableMap.size()];
            Object[] objArr2 = new Object[immutableMap.size()];
            oq2 oq2VarI = immutableMap.entrySet().iterator();
            int i = 0;
            while (oq2VarI.hasNext()) {
                Map.Entry entry = (Map.Entry) oq2VarI.next();
                objArr[i] = entry.getKey();
                objArr2[i] = entry.getValue();
                i++;
            }
            this.keys = objArr;
            this.values = objArr2;
        }

        public final Object readResolve() {
            Object obj = this.keys;
            if (obj instanceof ImmutableSet) {
                ImmutableSet immutableSet = (ImmutableSet) obj;
                ImmutableCollection immutableCollection = (ImmutableCollection) this.values;
                b bVar = new b(immutableSet.size());
                oq2 oq2VarI = immutableSet.iterator();
                oq2 oq2VarI2 = immutableCollection.iterator();
                while (oq2VarI.hasNext()) {
                    bVar.b(oq2VarI.next(), oq2VarI2.next());
                }
                return bVar.a();
            }
            Object[] objArr = (Object[]) obj;
            Object[] objArr2 = (Object[]) this.values;
            b bVar2 = new b(objArr.length);
            for (int i = 0; i < objArr.length; i++) {
                bVar2.b(objArr[i], objArr2[i]);
            }
            return bVar2.a();
        }
    }

    public static b a() {
        return new b(4);
    }

    public static ImmutableMap b(Map map) {
        if ((map instanceof ImmutableMap) && !(map instanceof SortedMap)) {
            ImmutableMap immutableMap = (ImmutableMap) map;
            immutableMap.getClass();
            return immutableMap;
        }
        Set<Map.Entry<K, V>> setEntrySet = map.entrySet();
        b bVar = new b(setEntrySet instanceof Collection ? setEntrySet.size() : 4);
        bVar.c(setEntrySet);
        return bVar.a();
    }

    public static ImmutableMap d() {
        return RegularImmutableMap.o;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final ImmutableSet entrySet() {
        ImmutableSet immutableSet = this.c;
        if (immutableSet != null) {
            return immutableSet;
        }
        RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
        RegularImmutableMap.EntrySet entrySet = new RegularImmutableMap.EntrySet(regularImmutableMap, regularImmutableMap.m, regularImmutableMap.n);
        this.c = entrySet;
        return entrySet;
    }

    @Override // java.util.Map
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        ImmutableCollection immutableCollection = this.k;
        if (immutableCollection == null) {
            RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
            RegularImmutableMap.KeysOrValuesAsList keysOrValuesAsList = new RegularImmutableMap.KeysOrValuesAsList(1, regularImmutableMap.n, regularImmutableMap.m);
            this.k = keysOrValuesAsList;
            immutableCollection = keysOrValuesAsList;
        }
        return immutableCollection.contains(obj);
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    public abstract Object get(Object obj);

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        Iterator<E> it = entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i = ~(~(i + (next != null ? next.hashCode() : 0)));
        }
        return i;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        ImmutableSet immutableSet = this.e;
        if (immutableSet != null) {
            return immutableSet;
        }
        RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
        RegularImmutableMap.KeySet keySet = new RegularImmutableMap.KeySet(regularImmutableMap, new RegularImmutableMap.KeysOrValuesAsList(0, regularImmutableMap.n, regularImmutableMap.m));
        this.e = keySet;
        return keySet;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        he1.e(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824L));
        sb.append('{');
        boolean z = true;
        for (Map.Entry<K, V> entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z = false;
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.Map
    public final Collection values() {
        ImmutableCollection immutableCollection = this.k;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
        RegularImmutableMap.KeysOrValuesAsList keysOrValuesAsList = new RegularImmutableMap.KeysOrValuesAsList(1, regularImmutableMap.n, regularImmutableMap.m);
        this.k = keysOrValuesAsList;
        return keysOrValuesAsList;
    }

    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
