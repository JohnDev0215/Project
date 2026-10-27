package java9.util;

import SevenZip.CRC;
import cerence.ark.assistant.library.JsonKey;
import java.util.AbstractList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java9.util.function.Consumer;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
final class LinkedListSpliterator<T> implements Spliterator<T> {
    private static final int BATCH_UNIT = 1024;
    private static final long FIRST_OFF;
    private static final boolean IS_HARMONY;
    private static final boolean IS_JAVA6;
    private static final int MAX_BATCH = 33554432;
    private static final long MODCOUNT_OFF;
    private static final long NODE_ITEM_OFF;
    private static final long NODE_NEXT_OFF;
    private static final long SIZE_OFF;
    private static final Unsafe U;
    private int batch;
    private Object current;
    private final Object endOfList;
    private int est;
    private int expectedModCount;
    private final LinkedList<T> list;

    @Override // java9.util.Spliterator
    public int characteristics() {
        return 16464;
    }

    private LinkedListSpliterator(LinkedList<T> linkedList, int i, int i2) {
        this.list = linkedList;
        this.est = i;
        this.expectedModCount = i2;
        this.endOfList = (IS_JAVA6 || IS_HARMONY) ? getHeader(linkedList) : null;
    }

    static <E> Spliterator<E> spliterator(LinkedList<E> linkedList) {
        return new LinkedListSpliterator(linkedList, -1, 0);
    }

    private int getEst() {
        int i = this.est;
        if (i >= 0) {
            return i;
        }
        LinkedList<T> linkedList = this.list;
        if (linkedList == null) {
            this.est = 0;
            return 0;
        }
        this.expectedModCount = getModCount(linkedList);
        this.current = getFirst(linkedList);
        int size = getSize(linkedList);
        this.est = size;
        return size;
    }

    @Override // java9.util.Spliterator
    public long estimateSize() {
        return getEst();
    }

    @Override // java9.util.Spliterator
    public void forEachRemaining(Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        Object obj = this.endOfList;
        int est = getEst();
        if (est > 0 && (nextNode = this.current) != obj) {
            this.current = obj;
            this.est = 0;
            do {
                CRC crc = (Object) getNodeItem(nextNode);
                Object nextNode = getNextNode(nextNode);
                consumer.accept(crc);
                if (nextNode == obj) {
                    break;
                } else {
                    est--;
                }
            } while (est > 0);
        }
        if (this.expectedModCount != getModCount(this.list)) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java9.util.Spliterator
    public Comparator<? super T> getComparator() {
        return Spliterators.getComparator(this);
    }

    @Override // java9.util.Spliterator
    public long getExactSizeIfKnown() {
        return Spliterators.getExactSizeIfKnown(this);
    }

    @Override // java9.util.Spliterator
    public boolean hasCharacteristics(int i) {
        return Spliterators.hasCharacteristics(this, i);
    }

    @Override // java9.util.Spliterator
    public boolean tryAdvance(Consumer<? super T> consumer) {
        Object obj;
        Objects.requireNonNull(consumer);
        Object obj2 = this.endOfList;
        if (getEst() <= 0 || (obj = this.current) == obj2) {
            return false;
        }
        this.est--;
        CRC crc = (Object) getNodeItem(obj);
        this.current = getNextNode(obj);
        consumer.accept(crc);
        if (this.expectedModCount == getModCount(this.list)) {
            return true;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java9.util.Spliterator
    public Spliterator<T> trySplit() {
        Object nextNode;
        int i;
        Object obj = this.endOfList;
        int est = getEst();
        if (est <= 1 || (nextNode = this.current) == obj) {
            return null;
        }
        int i2 = this.batch + 1024;
        if (i2 > est) {
            i2 = est;
        }
        if (i2 > 33554432) {
            i2 = 33554432;
        }
        Object[] objArr = new Object[i2];
        int i3 = 0;
        while (true) {
            i = i3 + 1;
            objArr[i3] = getNodeItem(nextNode);
            nextNode = getNextNode(nextNode);
            if (nextNode == obj || i >= i2) {
                break;
            }
            i3 = i;
        }
        this.current = nextNode;
        this.batch = i;
        this.est = est - i;
        return Spliterators.spliterator(objArr, 0, i, 16);
    }

    private static Object getHeader(LinkedList<?> linkedList) {
        if (linkedList == null) {
            return null;
        }
        return U.getObject(linkedList, FIRST_OFF);
    }

    private Object getFirst(LinkedList<?> linkedList) {
        if (IS_JAVA6 || IS_HARMONY) {
            return getNextNode(this.endOfList);
        }
        return U.getObject(linkedList, FIRST_OFF);
    }

    private static Object getNextNode(Object obj) {
        if (obj == null) {
            throw new ConcurrentModificationException();
        }
        return U.getObject(obj, NODE_NEXT_OFF);
    }

    private static <E> E getNodeItem(Object obj) {
        if (obj == null) {
            throw new ConcurrentModificationException();
        }
        return (E) U.getObject(obj, NODE_ITEM_OFF);
    }

    private static int getSize(LinkedList<?> linkedList) {
        return U.getInt(linkedList, SIZE_OFF);
    }

    private static int getModCount(LinkedList<?> linkedList) {
        return U.getInt(linkedList, MODCOUNT_OFF);
    }

    static {
        boolean z = Spliterators.IS_HARMONY_ANDROID;
        IS_HARMONY = z;
        boolean z2 = Spliterators.IS_JAVA6;
        IS_JAVA6 = z2;
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            MODCOUNT_OFF = unsafe.objectFieldOffset(AbstractList.class.getDeclaredField("modCount"));
            String str = z ? "voidLink" : z2 ? "header" : "first";
            String str2 = z ? "java.util.LinkedList$Link" : z2 ? "java.util.LinkedList$Entry" : "java.util.LinkedList$Node";
            String str3 = z ? JsonKey.RESULTS_DATA : z2 ? "element" : "item";
            Class<?> cls = Class.forName(str2);
            SIZE_OFF = unsafe.objectFieldOffset(LinkedList.class.getDeclaredField("size"));
            FIRST_OFF = unsafe.objectFieldOffset(LinkedList.class.getDeclaredField(str));
            NODE_ITEM_OFF = unsafe.objectFieldOffset(cls.getDeclaredField(str3));
            NODE_NEXT_OFF = unsafe.objectFieldOffset(cls.getDeclaredField("next"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
