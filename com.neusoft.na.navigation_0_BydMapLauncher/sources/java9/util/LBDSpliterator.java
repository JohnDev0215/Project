package java9.util;

import SevenZip.CRC;
import java.util.Comparator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;
import java9.util.function.Consumer;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
final class LBDSpliterator<E> implements Spliterator<E> {
    private static final long FIRST_OFF;
    private static final long LOCK_OFF;
    private static final int MAX_BATCH = 33554432;
    private static final long NODE_ITEM_OFF;
    private static final long NODE_NEXT_OFF;
    private static final Unsafe U;
    private int batch;
    private Object current;
    private long est;
    private boolean exhausted;
    private final LinkedBlockingDeque<E> queue;
    private final ReentrantLock queueLock;

    @Override // java9.util.Spliterator
    public int characteristics() {
        return 4368;
    }

    private LBDSpliterator(LinkedBlockingDeque<E> linkedBlockingDeque) {
        this.queue = linkedBlockingDeque;
        this.est = linkedBlockingDeque.size();
        this.queueLock = getQueueLock(linkedBlockingDeque);
    }

    static <T> Spliterator<T> spliterator(LinkedBlockingDeque<T> linkedBlockingDeque) {
        return new LBDSpliterator(linkedBlockingDeque);
    }

    Object succ(Object obj) {
        Object nextNode = getNextNode(obj);
        return obj == nextNode ? getQueueFirst(this.queue) : nextNode;
    }

    @Override // java9.util.Spliterator
    public long estimateSize() {
        return this.est;
    }

    @Override // java9.util.Spliterator
    public void forEachRemaining(Consumer<? super E> consumer) {
        Objects.requireNonNull(consumer);
        if (this.exhausted) {
            return;
        }
        this.exhausted = true;
        Object obj = this.current;
        this.current = null;
        forEachFrom(consumer, obj);
    }

    @Override // java9.util.Spliterator
    public Comparator<? super E> getComparator() {
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
    public boolean tryAdvance(Consumer<? super E> consumer) {
        CRC crc;
        Objects.requireNonNull(consumer);
        if (this.exhausted) {
            return false;
        }
        ReentrantLock reentrantLock = this.queueLock;
        reentrantLock.lock();
        try {
            Object objSucc = this.current;
            if (objSucc != null || (objSucc = getQueueFirst(this.queue)) != null) {
                do {
                    crc = (Object) getNodeItem(objSucc);
                    objSucc = succ(objSucc);
                    if (crc != null) {
                        break;
                    }
                } while (objSucc != null);
            } else {
                crc = null;
            }
            this.current = objSucc;
            if (objSucc == null) {
                this.exhausted = true;
            }
            if (crc == null) {
                return false;
            }
            consumer.accept(crc);
            return true;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0046 A[Catch: all -> 0x0037, TryCatch #0 {all -> 0x0037, blocks: (B:12:0x002e, B:21:0x003e, B:23:0x0046, B:24:0x0048), top: B:39:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0048 A[SYNTHETIC] */
    @Override // java9.util.Spliterator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java9.util.Spliterator<E> trySplit() {
        /*
            r9 = this;
            java.util.concurrent.LinkedBlockingDeque<E> r0 = r9.queue
            boolean r1 = r9.exhausted
            if (r1 != 0) goto L74
            java.lang.Object r1 = r9.current
            if (r1 != 0) goto L10
            java.lang.Object r1 = getQueueFirst(r0)
            if (r1 == 0) goto L74
        L10:
            java.lang.Object r1 = getNextNode(r1)
            if (r1 == 0) goto L74
            int r1 = r9.batch
            r2 = 1
            int r1 = r1 + r2
            r3 = 33554432(0x2000000, float:9.403955E-38)
            int r1 = java.lang.Math.min(r1, r3)
            r9.batch = r1
            java.lang.Object[] r3 = new java.lang.Object[r1]
            java.util.concurrent.locks.ReentrantLock r4 = r9.queueLock
            java.lang.Object r5 = r9.current
            r4.lock()
            r6 = 0
            if (r5 != 0) goto L39
            java.lang.Object r5 = getQueueFirst(r0)     // Catch: java.lang.Throwable -> L37
            if (r5 == 0) goto L35
            goto L39
        L35:
            r0 = r6
            goto L51
        L37:
            r0 = move-exception
            goto L4d
        L39:
            r0 = r6
        L3a:
            if (r5 == 0) goto L51
            if (r0 >= r1) goto L51
            java.lang.Object r7 = getNodeItem(r5)     // Catch: java.lang.Throwable -> L37
            r3[r0] = r7     // Catch: java.lang.Throwable -> L37
            if (r7 == 0) goto L48
            int r0 = r0 + 1
        L48:
            java.lang.Object r5 = r9.succ(r5)     // Catch: java.lang.Throwable -> L37
            goto L3a
        L4d:
            r4.unlock()
            throw r0
        L51:
            r4.unlock()
            r9.current = r5
            r7 = 0
            if (r5 != 0) goto L5f
            r9.est = r7
            r9.exhausted = r2
            goto L6b
        L5f:
            long r1 = r9.est
            long r4 = (long) r0
            long r1 = r1 - r4
            r9.est = r1
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 >= 0) goto L6b
            r9.est = r7
        L6b:
            if (r0 <= 0) goto L74
            r1 = 4368(0x1110, float:6.121E-42)
            java9.util.Spliterator r0 = java9.util.Spliterators.spliterator(r3, r6, r0, r1)
            return r0
        L74:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.LBDSpliterator.trySplit():java9.util.Spliterator");
    }

    void forEachFrom(Consumer<? super E> consumer, Object obj) {
        ReentrantLock reentrantLock = this.queueLock;
        Object[] objArr = null;
        int i = 0;
        do {
            reentrantLock.lock();
            if (objArr == null) {
                if (obj == null) {
                    try {
                        obj = getQueueFirst(this.queue);
                    } catch (Throwable th) {
                        reentrantLock.unlock();
                        throw th;
                    }
                }
                Object objSucc = obj;
                while (objSucc != null && (getNodeItem(objSucc) == null || (i = i + 1) != 64)) {
                    objSucc = succ(objSucc);
                }
                objArr = new Object[i];
            }
            int i2 = 0;
            while (obj != null && i2 < i) {
                Object nodeItem = getNodeItem(obj);
                objArr[i2] = nodeItem;
                if (nodeItem != null) {
                    i2++;
                }
                obj = succ(obj);
            }
            reentrantLock.unlock();
            for (int i3 = 0; i3 < i2; i3++) {
                consumer.accept(objArr[i3]);
            }
            if (i2 <= 0) {
                return;
            }
        } while (obj != null);
    }

    private static ReentrantLock getQueueLock(LinkedBlockingDeque<?> linkedBlockingDeque) {
        return (ReentrantLock) U.getObject(linkedBlockingDeque, LOCK_OFF);
    }

    private static Object getQueueFirst(LinkedBlockingDeque<?> linkedBlockingDeque) {
        return U.getObject(linkedBlockingDeque, FIRST_OFF);
    }

    private static Object getNextNode(Object obj) {
        return U.getObject(obj, NODE_NEXT_OFF);
    }

    private static <T> T getNodeItem(Object obj) {
        return (T) U.getObject(obj, NODE_ITEM_OFF);
    }

    static {
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            Class<?> cls = Class.forName("java.util.concurrent.LinkedBlockingDeque$Node");
            FIRST_OFF = unsafe.objectFieldOffset(LinkedBlockingDeque.class.getDeclaredField("first"));
            LOCK_OFF = unsafe.objectFieldOffset(LinkedBlockingDeque.class.getDeclaredField("lock"));
            NODE_ITEM_OFF = unsafe.objectFieldOffset(cls.getDeclaredField("item"));
            NODE_NEXT_OFF = unsafe.objectFieldOffset(cls.getDeclaredField("next"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
