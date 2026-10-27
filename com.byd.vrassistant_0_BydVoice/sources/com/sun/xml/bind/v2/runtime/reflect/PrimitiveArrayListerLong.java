package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.XMLSerializer;

/* JADX INFO: loaded from: classes3.dex */
final class PrimitiveArrayListerLong<BeanT> extends Lister<BeanT, long[], Long, LongArrayPack> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ void endPacking(LongArrayPack longArrayPack, Object obj, Accessor accessor) throws AccessorException {
        endPacking2(longArrayPack, obj, (Accessor<Object, long[]>) accessor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ LongArrayPack startPacking(Object obj, Accessor accessor) throws AccessorException {
        return startPacking(obj, (Accessor<Object, long[]>) accessor);
    }

    private PrimitiveArrayListerLong() {
    }

    static void register() {
        Lister.primitiveArrayListers.put(Long.TYPE, new PrimitiveArrayListerLong());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public ListIterator<Long> iterator(final long[] jArr, XMLSerializer xMLSerializer) {
        return new ListIterator<Long>() { // from class: com.sun.xml.bind.v2.runtime.reflect.PrimitiveArrayListerLong.1
            int idx = 0;

            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public boolean hasNext() {
                return this.idx < jArr.length;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public Long next() {
                long[] jArr2 = jArr;
                int i = this.idx;
                this.idx = i + 1;
                return Long.valueOf(jArr2[i]);
            }
        };
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public LongArrayPack startPacking(BeanT beant, Accessor<BeanT, long[]> accessor) {
        return new LongArrayPack();
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void addToPack(LongArrayPack longArrayPack, Long l) {
        longArrayPack.add(l);
    }

    /* JADX INFO: renamed from: endPacking, reason: avoid collision after fix types in other method */
    public void endPacking2(LongArrayPack longArrayPack, BeanT beant, Accessor<BeanT, long[]> accessor) throws AccessorException {
        accessor.set(beant, longArrayPack.build());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void reset(BeanT beant, Accessor<BeanT, long[]> accessor) throws AccessorException {
        accessor.set(beant, new long[0]);
    }

    static final class LongArrayPack {
        long[] buf = new long[16];
        int size;

        LongArrayPack() {
        }

        void add(Long l) {
            long[] jArr = this.buf;
            if (jArr.length == this.size) {
                long[] jArr2 = new long[jArr.length * 2];
                System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
                this.buf = jArr2;
            }
            if (l != null) {
                long[] jArr3 = this.buf;
                int i = this.size;
                this.size = i + 1;
                jArr3[i] = l.longValue();
            }
        }

        long[] build() {
            long[] jArr = this.buf;
            int length = jArr.length;
            int i = this.size;
            if (length == i) {
                return jArr;
            }
            long[] jArr2 = new long[i];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            return jArr2;
        }
    }
}
