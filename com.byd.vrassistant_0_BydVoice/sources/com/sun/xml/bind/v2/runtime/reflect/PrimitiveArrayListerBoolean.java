package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.XMLSerializer;

/* JADX INFO: loaded from: classes3.dex */
final class PrimitiveArrayListerBoolean<BeanT> extends Lister<BeanT, boolean[], Boolean, BooleanArrayPack> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ void endPacking(BooleanArrayPack booleanArrayPack, Object obj, Accessor accessor) throws AccessorException {
        endPacking2(booleanArrayPack, obj, (Accessor<Object, boolean[]>) accessor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ BooleanArrayPack startPacking(Object obj, Accessor accessor) throws AccessorException {
        return startPacking(obj, (Accessor<Object, boolean[]>) accessor);
    }

    private PrimitiveArrayListerBoolean() {
    }

    static void register() {
        Lister.primitiveArrayListers.put(Boolean.TYPE, new PrimitiveArrayListerBoolean());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public ListIterator<Boolean> iterator(final boolean[] zArr, XMLSerializer xMLSerializer) {
        return new ListIterator<Boolean>() { // from class: com.sun.xml.bind.v2.runtime.reflect.PrimitiveArrayListerBoolean.1
            int idx = 0;

            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public boolean hasNext() {
                return this.idx < zArr.length;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public Boolean next() {
                boolean[] zArr2 = zArr;
                int i = this.idx;
                this.idx = i + 1;
                return Boolean.valueOf(zArr2[i]);
            }
        };
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public BooleanArrayPack startPacking(BeanT beant, Accessor<BeanT, boolean[]> accessor) {
        return new BooleanArrayPack();
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void addToPack(BooleanArrayPack booleanArrayPack, Boolean bool) {
        booleanArrayPack.add(bool);
    }

    /* JADX INFO: renamed from: endPacking, reason: avoid collision after fix types in other method */
    public void endPacking2(BooleanArrayPack booleanArrayPack, BeanT beant, Accessor<BeanT, boolean[]> accessor) throws AccessorException {
        accessor.set(beant, booleanArrayPack.build());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void reset(BeanT beant, Accessor<BeanT, boolean[]> accessor) throws AccessorException {
        accessor.set(beant, new boolean[0]);
    }

    static final class BooleanArrayPack {
        boolean[] buf = new boolean[16];
        int size;

        BooleanArrayPack() {
        }

        void add(Boolean bool) {
            boolean[] zArr = this.buf;
            if (zArr.length == this.size) {
                boolean[] zArr2 = new boolean[zArr.length * 2];
                System.arraycopy(zArr, 0, zArr2, 0, zArr.length);
                this.buf = zArr2;
            }
            if (bool != null) {
                boolean[] zArr3 = this.buf;
                int i = this.size;
                this.size = i + 1;
                zArr3[i] = bool.booleanValue();
            }
        }

        boolean[] build() {
            boolean[] zArr = this.buf;
            int length = zArr.length;
            int i = this.size;
            if (length == i) {
                return zArr;
            }
            boolean[] zArr2 = new boolean[i];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            return zArr2;
        }
    }
}
