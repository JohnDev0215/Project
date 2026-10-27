package com.sun.xml.bind.v2.util;

import com.sun.xml.bind.v2.runtime.Name;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.xml.namespace.QName;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public final class QNameMap<V> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    transient int size;
    private Set<Entry<V>> entrySet = null;
    private int threshold = 12;
    transient Entry<V>[] table = new Entry[16];

    private static int indexFor(int i, int i2) {
        return i & (i2 - 1);
    }

    public void put(String str, String str2, V v) {
        int iHash = hash(str2);
        int iIndexFor = indexFor(iHash, this.table.length);
        for (Entry<V> entry = this.table[iIndexFor]; entry != null; entry = entry.next) {
            if (entry.hash == iHash && str2 == entry.localName && str == entry.nsUri) {
                entry.value = v;
                return;
            }
        }
        addEntry(iHash, str, str2, v, iIndexFor);
    }

    public void put(QName qName, V v) {
        put(qName.getNamespaceURI(), qName.getLocalPart(), v);
    }

    public void put(Name name, V v) {
        put(name.nsUri, name.localName, v);
    }

    public V get(String str, String str2) {
        Entry<V> entry = getEntry(str, str2);
        if (entry == null) {
            return null;
        }
        return entry.value;
    }

    public V get(QName qName) {
        return get(qName.getNamespaceURI(), qName.getLocalPart());
    }

    public int size() {
        return this.size;
    }

    public QNameMap<V> putAll(QNameMap<? extends V> qNameMap) {
        int size = qNameMap.size();
        if (size == 0) {
            return this;
        }
        if (size > this.threshold) {
            if (size > 1073741824) {
                size = 1073741824;
            }
            int length = this.table.length;
            while (length < size) {
                length <<= 1;
            }
            if (length > this.table.length) {
                resize(length);
            }
        }
        for (Entry<? extends V> entry : qNameMap.entrySet()) {
            put(entry.nsUri, entry.localName, entry.getValue());
        }
        return this;
    }

    private static int hash(String str) {
        int iHashCode = str.hashCode();
        int i = iHashCode + (~(iHashCode << 9));
        int i2 = i ^ (i >>> 14);
        int i3 = i2 + (i2 << 4);
        return i3 ^ (i3 >>> 10);
    }

    private void addEntry(int i, String str, String str2, V v, int i2) {
        Entry<V>[] entryArr = this.table;
        entryArr[i2] = new Entry<>(i, str, str2, v, entryArr[i2]);
        int i3 = this.size;
        this.size = i3 + 1;
        if (i3 >= this.threshold) {
            resize(this.table.length * 2);
        }
    }

    private void resize(int i) {
        if (this.table.length == 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        Entry<V>[] entryArr = new Entry[i];
        transfer(entryArr);
        this.table = entryArr;
        this.threshold = i;
    }

    private void transfer(Entry<V>[] entryArr) {
        Entry<V>[] entryArr2 = this.table;
        int length = entryArr.length;
        for (int i = 0; i < entryArr2.length; i++) {
            Entry<V> entry = entryArr2[i];
            if (entry != null) {
                entryArr2[i] = null;
                while (true) {
                    Entry<V> entry2 = entry.next;
                    int iIndexFor = indexFor(entry.hash, length);
                    entry.next = entryArr[iIndexFor];
                    entryArr[iIndexFor] = entry;
                    if (entry2 == null) {
                        break;
                    } else {
                        entry = entry2;
                    }
                }
            }
        }
    }

    public Entry<V> getOne() {
        for (Entry<V> entry : this.table) {
            if (entry != null) {
                return entry;
            }
        }
        return null;
    }

    public Collection<QName> keySet() {
        HashSet hashSet = new HashSet();
        Iterator<Entry<V>> it = entrySet().iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().createQName());
        }
        return hashSet;
    }

    private abstract class HashIterator<E> implements Iterator<E> {
        int index;
        Entry<V> next;

        HashIterator() {
            Entry<V>[] entryArr = QNameMap.this.table;
            int length = entryArr.length;
            Entry<V> entry = null;
            if (QNameMap.this.size != 0) {
                while (length > 0) {
                    length--;
                    entry = entryArr[length];
                    if (entry != null) {
                        break;
                    }
                }
            }
            this.next = entry;
            this.index = length;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != null;
        }

        Entry<V> nextEntry() {
            Entry<V> entry = this.next;
            if (entry == null) {
                throw new NoSuchElementException();
            }
            Entry<V> entry2 = entry.next;
            Entry<V>[] entryArr = QNameMap.this.table;
            int i = this.index;
            while (entry2 == null && i > 0) {
                i--;
                entry2 = entryArr[i];
            }
            this.index = i;
            this.next = entry2;
            return entry;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public boolean containsKey(String str, String str2) {
        return getEntry(str, str2) != null;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public static final class Entry<V> {
        final int hash;
        public final String localName;
        Entry<V> next;
        public final String nsUri;
        V value;

        Entry(int i, String str, String str2, V v, Entry<V> entry) {
            this.value = v;
            this.next = entry;
            this.nsUri = str;
            this.localName = str2;
            this.hash = i;
        }

        public QName createQName() {
            return new QName(this.nsUri, this.localName);
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V v) {
            V v2 = this.value;
            this.value = v;
            return v2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            String str = this.nsUri;
            String str2 = entry.nsUri;
            String str3 = this.localName;
            String str4 = entry.localName;
            if (str == str2 || (str != null && str.equals(str2) && (str3 == str4 || (str3 != null && str3.equals(str4))))) {
                V value = getValue();
                Object value2 = entry.getValue();
                if (value == value2) {
                    return true;
                }
                if (value != null && value.equals(value2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int iHashCode = this.localName.hashCode();
            V v = this.value;
            return iHashCode ^ (v == null ? 0 : v.hashCode());
        }

        public String toString() {
            return "\"" + this.nsUri + "\",\"" + this.localName + "\"=" + getValue();
        }
    }

    public Set<Entry<V>> entrySet() {
        Set<Entry<V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet = new EntrySet();
        this.entrySet = entrySet;
        return entrySet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Iterator<Entry<V>> newEntryIterator() {
        return new EntryIterator();
    }

    private class EntryIterator extends QNameMap<V>.HashIterator<Entry<V>> {
        private EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Entry<V> next() {
            return nextEntry();
        }
    }

    private class EntrySet extends AbstractSet<Entry<V>> {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Entry<V>> iterator() {
            return QNameMap.this.newEntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Entry entry2 = QNameMap.this.getEntry(entry.nsUri, entry.localName);
            return entry2 != null && entry2.equals(entry);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return QNameMap.this.size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Entry<V> getEntry(String str, String str2) {
        Entry<V> entry = this.table[indexFor(hash(str2), this.table.length)];
        while (entry != null && (str2 != entry.localName || str != entry.nsUri)) {
            entry = entry.next;
        }
        return entry;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (Entry<V> entry : entrySet()) {
            if (sb.length() > 1) {
                sb.append(',');
            }
            sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            sb.append(entry);
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }
}
