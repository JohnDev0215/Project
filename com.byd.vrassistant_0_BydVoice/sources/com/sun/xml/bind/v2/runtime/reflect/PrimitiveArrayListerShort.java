package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.XMLSerializer;

/* JADX INFO: loaded from: classes3.dex */
final class PrimitiveArrayListerShort<BeanT> extends Lister<BeanT, short[], Short, ShortArrayPack> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ void endPacking(ShortArrayPack shortArrayPack, Object obj, Accessor accessor) throws AccessorException {
        endPacking2(shortArrayPack, obj, (Accessor<Object, short[]>) accessor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ ShortArrayPack startPacking(Object obj, Accessor accessor) throws AccessorException {
        return startPacking(obj, (Accessor<Object, short[]>) accessor);
    }

    private PrimitiveArrayListerShort() {
    }

    static void register() {
        Lister.primitiveArrayListers.put(Short.TYPE, new PrimitiveArrayListerShort());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public ListIterator<Short> iterator(final short[] sArr, XMLSerializer xMLSerializer) {
        return new ListIterator<Short>() { // from class: com.sun.xml.bind.v2.runtime.reflect.PrimitiveArrayListerShort.1
            int idx = 0;

            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public boolean hasNext() {
                return this.idx < sArr.length;
            }

            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public Short next() {
                short[] sArr2 = sArr;
                int i = this.idx;
                this.idx = i + 1;
                return Short.valueOf(sArr2[i]);
            }
        };
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public ShortArrayPack startPacking(BeanT beant, Accessor<BeanT, short[]> accessor) {
        return new ShortArrayPack();
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void addToPack(ShortArrayPack shortArrayPack, Short sh) {
        shortArrayPack.add(sh);
    }

    /* JADX INFO: renamed from: endPacking, reason: avoid collision after fix types in other method */
    public void endPacking2(ShortArrayPack shortArrayPack, BeanT beant, Accessor<BeanT, short[]> accessor) throws AccessorException {
        accessor.set(beant, shortArrayPack.build());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void reset(BeanT beant, Accessor<BeanT, short[]> accessor) throws AccessorException {
        accessor.set(beant, new short[0]);
    }

    static final class ShortArrayPack {
        short[] buf = new short[16];
        int size;

        ShortArrayPack() {
        }

        void add(Short sh) {
            short[] sArr = this.buf;
            if (sArr.length == this.size) {
                short[] sArr2 = new short[sArr.length * 2];
                System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
                this.buf = sArr2;
            }
            if (sh != null) {
                short[] sArr3 = this.buf;
                int i = this.size;
                this.size = i + 1;
                sArr3[i] = sh.shortValue();
            }
        }

        short[] build() {
            short[] sArr = this.buf;
            int length = sArr.length;
            int i = this.size;
            if (length == i) {
                return sArr;
            }
            short[] sArr2 = new short[i];
            System.arraycopy(sArr, 0, sArr2, 0, i);
            return sArr2;
        }
    }
}
