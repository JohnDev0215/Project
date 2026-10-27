package java9.util;

import SevenZip.CRC;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java9.util.function.Consumer;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
final class HMSpliterators {
    private static final long ENTRYSET_$0_OFF;
    private static final long HASHSET_MAP_OFF;
    private static final long KEYSET_$0_OFF;
    private static final Unsafe U;
    private static final long VALUES_$0_OFF;

    private HMSpliterators() {
    }

    static <K> Spliterator<K> getKeySetSpliterator(Set<K> set) {
        return new KeySpliterator(getHashMapFromKeySet(set), 0, -1, 0, 0);
    }

    static <K, V> Spliterator<Map.Entry<K, V>> getEntrySetSpliterator(Set<Map.Entry<K, V>> set) {
        return new EntrySpliterator(getHashMapFromEntrySet(set), 0, -1, 0, 0);
    }

    static <V> Spliterator<V> getValuesSpliterator(Collection<V> collection) {
        return new ValueSpliterator(getHashMapFromValues(collection), 0, -1, 0, 0);
    }

    static <E> Spliterator<E> getHashSetSpliterator(HashSet<E> hashSet) {
        return new KeySpliterator(getHashMapFromHashSet(hashSet), 0, -1, 0, 0);
    }

    private static final class KeySpliterator<K, V> extends HashMapSpliterator<K, V> implements Spliterator<K> {
        KeySpliterator(HashMap<K, V> map, int i, int i2, int i3, int i4) {
            super(map, i, i2, i3, i4);
        }

        @Override // java9.util.Spliterator
        public KeySpliterator<K, V> trySplit() {
            int fence = getFence();
            int i = this.index;
            int i2 = (fence + i) >>> 1;
            if (i >= i2 || this.current != null) {
                return null;
            }
            HashMap<K, V> map = this.map;
            this.index = i2;
            int i3 = this.est >>> 1;
            this.est = i3;
            return new KeySpliterator<>(map, i, i2, i3, this.expectedModCount);
        }

        @Override // java9.util.Spliterator
        public void forEachRemaining(Consumer<? super K> consumer) {
            int i;
            int i2;
            Objects.requireNonNull(consumer);
            HashMap<K, V> map = this.map;
            Object[] table = getTable(map);
            int i3 = this.fence;
            if (i3 < 0) {
                int modCount = getModCount(map);
                this.expectedModCount = modCount;
                int length = table == null ? 0 : table.length;
                this.fence = length;
                int i4 = length;
                i = modCount;
                i3 = i4;
            } else {
                i = this.expectedModCount;
            }
            if (table == null || table.length < i3 || (i2 = this.index) < 0) {
                return;
            }
            this.index = i3;
            if (i2 < i3 || this.current != null) {
                Object nextNode = this.current;
                this.current = null;
                while (true) {
                    if (nextNode == null) {
                        nextNode = table[i2];
                        i2++;
                    } else {
                        consumer.accept((Object) HashMapSpliterator.getNodeKey(nextNode));
                        nextNode = getNextNode(nextNode);
                    }
                    if (nextNode == null && i2 >= i3) {
                        break;
                    }
                }
                if (i != getModCount(map)) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override // java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super K> consumer) {
            Objects.requireNonNull(consumer);
            Object[] table = getTable(this.map);
            if (table == null) {
                return false;
            }
            int length = table.length;
            int fence = getFence();
            if (length < fence || this.index < 0) {
                return false;
            }
            while (true) {
                if (this.current == null && this.index >= fence) {
                    return false;
                }
                if (this.current == null) {
                    int i = this.index;
                    this.index = i + 1;
                    this.current = table[i];
                } else {
                    CRC crc = (Object) getNodeKey(this.current);
                    this.current = getNextNode(this.current);
                    consumer.accept(crc);
                    if (this.expectedModCount == getModCount(this.map)) {
                        return true;
                    }
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override // java9.util.HMSpliterators.HashMapSpliterator, java9.util.Spliterator
        public int characteristics() {
            return ((this.fence < 0 || this.est == this.map.size()) ? 64 : 0) | 1;
        }

        @Override // java9.util.Spliterator
        public Comparator<? super K> getComparator() {
            return Spliterators.getComparator(null);
        }
    }

    private static final class ValueSpliterator<K, V> extends HashMapSpliterator<K, V> implements Spliterator<V> {
        ValueSpliterator(HashMap<K, V> map, int i, int i2, int i3, int i4) {
            super(map, i, i2, i3, i4);
        }

        @Override // java9.util.Spliterator
        public ValueSpliterator<K, V> trySplit() {
            int fence = getFence();
            int i = this.index;
            int i2 = (fence + i) >>> 1;
            if (i >= i2 || this.current != null) {
                return null;
            }
            HashMap<K, V> map = this.map;
            this.index = i2;
            int i3 = this.est >>> 1;
            this.est = i3;
            return new ValueSpliterator<>(map, i, i2, i3, this.expectedModCount);
        }

        @Override // java9.util.Spliterator
        public void forEachRemaining(Consumer<? super V> consumer) {
            int i;
            int i2;
            Objects.requireNonNull(consumer);
            HashMap<K, V> map = this.map;
            Object[] table = getTable(map);
            int i3 = this.fence;
            if (i3 < 0) {
                int modCount = getModCount(map);
                this.expectedModCount = modCount;
                int length = table == null ? 0 : table.length;
                this.fence = length;
                int i4 = length;
                i = modCount;
                i3 = i4;
            } else {
                i = this.expectedModCount;
            }
            if (table == null || table.length < i3 || (i2 = this.index) < 0) {
                return;
            }
            this.index = i3;
            if (i2 < i3 || this.current != null) {
                Object nextNode = this.current;
                this.current = null;
                while (true) {
                    if (nextNode == null) {
                        nextNode = table[i2];
                        i2++;
                    } else {
                        consumer.accept((Object) HashMapSpliterator.getNodeValue(nextNode));
                        nextNode = getNextNode(nextNode);
                    }
                    if (nextNode == null && i2 >= i3) {
                        break;
                    }
                }
                if (i != getModCount(map)) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override // java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super V> consumer) {
            Objects.requireNonNull(consumer);
            Object[] table = getTable(this.map);
            if (table == null) {
                return false;
            }
            int length = table.length;
            int fence = getFence();
            if (length < fence || this.index < 0) {
                return false;
            }
            while (true) {
                if (this.current == null && this.index >= fence) {
                    return false;
                }
                if (this.current == null) {
                    int i = this.index;
                    this.index = i + 1;
                    this.current = table[i];
                } else {
                    CRC crc = (Object) getNodeValue(this.current);
                    this.current = getNextNode(this.current);
                    consumer.accept(crc);
                    if (this.expectedModCount == getModCount(this.map)) {
                        return true;
                    }
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override // java9.util.HMSpliterators.HashMapSpliterator, java9.util.Spliterator
        public int characteristics() {
            return (this.fence < 0 || this.est == this.map.size()) ? 64 : 0;
        }

        @Override // java9.util.Spliterator
        public Comparator<? super V> getComparator() {
            return Spliterators.getComparator(null);
        }
    }

    private static final class EntrySpliterator<K, V> extends HashMapSpliterator<K, V> implements Spliterator<Map.Entry<K, V>> {
        EntrySpliterator(HashMap<K, V> map, int i, int i2, int i3, int i4) {
            super(map, i, i2, i3, i4);
        }

        @Override // java9.util.Spliterator
        public EntrySpliterator<K, V> trySplit() {
            int fence = getFence();
            int i = this.index;
            int i2 = (fence + i) >>> 1;
            if (i >= i2 || this.current != null) {
                return null;
            }
            HashMap<K, V> map = this.map;
            this.index = i2;
            int i3 = this.est >>> 1;
            this.est = i3;
            return new EntrySpliterator<>(map, i, i2, i3, this.expectedModCount);
        }

        @Override // java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> consumer) {
            int i;
            int i2;
            Objects.requireNonNull(consumer);
            HashMap<K, V> map = this.map;
            Object[] table = getTable(map);
            int i3 = this.fence;
            if (i3 < 0) {
                int modCount = getModCount(map);
                this.expectedModCount = modCount;
                int length = table == null ? 0 : table.length;
                this.fence = length;
                int i4 = length;
                i = modCount;
                i3 = i4;
            } else {
                i = this.expectedModCount;
            }
            if (table == null || table.length < i3 || (i2 = this.index) < 0) {
                return;
            }
            this.index = i3;
            if (i2 < i3 || this.current != null) {
                Object nextNode = this.current;
                this.current = null;
                while (true) {
                    if (nextNode == null) {
                        nextNode = table[i2];
                        i2++;
                    } else {
                        consumer.accept((Map.Entry) nextNode);
                        nextNode = getNextNode(nextNode);
                    }
                    if (nextNode == null && i2 >= i3) {
                        break;
                    }
                }
                if (i != getModCount(map)) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override // java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> consumer) {
            Objects.requireNonNull(consumer);
            Object[] table = getTable(this.map);
            if (table == null) {
                return false;
            }
            int length = table.length;
            int fence = getFence();
            if (length < fence || this.index < 0) {
                return false;
            }
            while (true) {
                if (this.current == null && this.index >= fence) {
                    return false;
                }
                if (this.current == null) {
                    int i = this.index;
                    this.index = i + 1;
                    this.current = table[i];
                } else {
                    Map.Entry entry = (Map.Entry) this.current;
                    this.current = getNextNode(this.current);
                    consumer.accept(entry);
                    if (this.expectedModCount == getModCount(this.map)) {
                        return true;
                    }
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override // java9.util.HMSpliterators.HashMapSpliterator, java9.util.Spliterator
        public int characteristics() {
            return ((this.fence < 0 || this.est == this.map.size()) ? 64 : 0) | 1;
        }

        @Override // java9.util.Spliterator
        public Comparator<? super Map.Entry<K, V>> getComparator() {
            return Spliterators.getComparator(null);
        }
    }

    private static abstract class HashMapSpliterator<K, V> {
        private static final long MODCOUNT_OFF;
        private static final long NODE_KEY_OFF;
        private static final long NODE_NXT_OFF;
        private static final long NODE_VAL_OFF;
        private static final long TABLE_OFF;
        private static final Unsafe U;
        Object current;
        int est;
        int expectedModCount;
        int fence;
        int index;
        final HashMap<K, V> map;

        public abstract int characteristics();

        HashMapSpliterator(HashMap<K, V> map, int i, int i2, int i3, int i4) {
            this.map = map;
            this.index = i;
            this.fence = i2;
            this.est = i3;
            this.expectedModCount = i4;
        }

        final int getFence() {
            int length = this.fence;
            if (length < 0) {
                HashMap<K, V> map = this.map;
                this.est = map.size();
                this.expectedModCount = getModCount(map);
                Object[] table = getTable(map);
                length = table == null ? 0 : table.length;
                this.fence = length;
            }
            return length;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final long getExactSizeIfKnown() {
            return Spliterators.getExactSizeIfKnown((Spliterator) this);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final boolean hasCharacteristics(int i) {
            return Spliterators.hasCharacteristics((Spliterator) this, i);
        }

        public final long estimateSize() {
            getFence();
            return this.est;
        }

        static int getModCount(HashMap<?, ?> map) {
            return U.getInt(map, MODCOUNT_OFF);
        }

        static Object[] getTable(HashMap<?, ?> map) {
            return (Object[]) U.getObject(map, TABLE_OFF);
        }

        static <K> K getNodeKey(Object obj) {
            return (K) U.getObject(obj, NODE_KEY_OFF);
        }

        static <T> T getNodeValue(Object obj) {
            return (T) U.getObject(obj, NODE_VAL_OFF);
        }

        static Object getNextNode(Object obj) {
            return U.getObject(obj, NODE_NXT_OFF);
        }

        static Class<?> nodeClass() throws ClassNotFoundException {
            try {
                return Class.forName("java.util.HashMap$".concat((Spliterators.IS_ANDROID || Spliterators.HAS_STREAMS) ? "Node" : "Entry"));
            } catch (ClassNotFoundException e) {
                if (Spliterators.IS_ANDROID) {
                    return Class.forName("java.util.HashMap$HashMapEntry");
                }
                throw e;
            }
        }

        static {
            Unsafe unsafe = UnsafeAccess.unsafe;
            U = unsafe;
            try {
                TABLE_OFF = unsafe.objectFieldOffset(HashMap.class.getDeclaredField("table"));
                MODCOUNT_OFF = unsafe.objectFieldOffset(HashMap.class.getDeclaredField("modCount"));
                Class<?> clsNodeClass = nodeClass();
                NODE_KEY_OFF = unsafe.objectFieldOffset(clsNodeClass.getDeclaredField("key"));
                NODE_VAL_OFF = unsafe.objectFieldOffset(clsNodeClass.getDeclaredField(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE));
                NODE_NXT_OFF = unsafe.objectFieldOffset(clsNodeClass.getDeclaredField("next"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    private static <K, V> HashMap<K, V> getHashMapFromKeySet(Set<K> set) {
        return (HashMap) U.getObject(set, KEYSET_$0_OFF);
    }

    private static <K, V> HashMap<K, V> getHashMapFromEntrySet(Set<Map.Entry<K, V>> set) {
        return (HashMap) U.getObject(set, ENTRYSET_$0_OFF);
    }

    private static <K, V> HashMap<K, V> getHashMapFromValues(Collection<V> collection) {
        return (HashMap) U.getObject(collection, VALUES_$0_OFF);
    }

    private static <K, V> HashMap<K, V> getHashMapFromHashSet(HashSet<K> hashSet) {
        return (HashMap) U.getObject(hashSet, HASHSET_MAP_OFF);
    }

    static {
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            Class<?> cls = Class.forName("java.util.HashMap$Values");
            Class<?> cls2 = Class.forName("java.util.HashMap$KeySet");
            Class<?> cls3 = Class.forName("java.util.HashMap$EntrySet");
            VALUES_$0_OFF = unsafe.objectFieldOffset(cls.getDeclaredField("this$0"));
            KEYSET_$0_OFF = unsafe.objectFieldOffset(cls2.getDeclaredField("this$0"));
            ENTRYSET_$0_OFF = unsafe.objectFieldOffset(cls3.getDeclaredField("this$0"));
            HASHSET_MAP_OFF = unsafe.objectFieldOffset(HashSet.class.getDeclaredField("map"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
