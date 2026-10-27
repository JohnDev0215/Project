package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.XMLSerializer;

/* JADX INFO: loaded from: classes3.dex */
final class PrimitiveArrayListerInteger<BeanT> extends Lister<BeanT, int[], Integer, IntegerArrayPack> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ void endPacking(IntegerArrayPack integerArrayPack, Object obj, Accessor accessor) throws AccessorException {
        endPacking2(integerArrayPack, obj, (Accessor<Object, int[]>) accessor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ IntegerArrayPack startPacking(Object obj, Accessor accessor) throws AccessorException {
        return startPacking(obj, (Accessor<Object, int[]>) accessor);
    }

    private PrimitiveArrayListerInteger() {
    }

    static void register() {
        Lister.primitiveArrayListers.put(Integer.TYPE, new PrimitiveArrayListerInteger());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public ListIterator<Integer> iterator(final int[] iArr, XMLSerializer xMLSerializer) {
        return new ListIterator<Integer>() { // from class: com.sun.xml.bind.v2.runtime.reflect.PrimitiveArrayListerInteger.1
            int idx = 0;

            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public boolean hasNext() {
                return this.idx < iArr.length;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public Integer next() {
                int[] iArr2 = iArr;
                int i = this.idx;
                this.idx = i + 1;
                return Integer.valueOf(iArr2[i]);
            }
        };
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public IntegerArrayPack startPacking(BeanT beant, Accessor<BeanT, int[]> accessor) {
        return new IntegerArrayPack();
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void addToPack(IntegerArrayPack integerArrayPack, Integer num) {
        integerArrayPack.add(num);
    }

    /* JADX INFO: renamed from: endPacking, reason: avoid collision after fix types in other method */
    public void endPacking2(IntegerArrayPack integerArrayPack, BeanT beant, Accessor<BeanT, int[]> accessor) throws AccessorException {
        accessor.set(beant, integerArrayPack.build());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void reset(BeanT beant, Accessor<BeanT, int[]> accessor) throws AccessorException {
        accessor.set(beant, new int[0]);
    }

    static final class IntegerArrayPack {
        int[] buf = new int[16];
        int size;

        IntegerArrayPack() {
        }

        void add(Integer num) {
            int[] iArr = this.buf;
            if (iArr.length == this.size) {
                int[] iArr2 = new int[iArr.length * 2];
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                this.buf = iArr2;
            }
            if (num != null) {
                int[] iArr3 = this.buf;
                int i = this.size;
                this.size = i + 1;
                iArr3[i] = num.intValue();
            }
        }

        int[] build() {
            int[] iArr = this.buf;
            int length = iArr.length;
            int i = this.size;
            if (length == i) {
                return iArr;
            }
            int[] iArr2 = new int[i];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            return iArr2;
        }
    }
}
