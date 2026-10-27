package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.XMLSerializer;

/* JADX INFO: loaded from: classes3.dex */
final class PrimitiveArrayListerByte<BeanT> extends Lister<BeanT, byte[], Byte, ByteArrayPack> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ void endPacking(ByteArrayPack byteArrayPack, Object obj, Accessor accessor) throws AccessorException {
        endPacking2(byteArrayPack, obj, (Accessor<Object, byte[]>) accessor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ ByteArrayPack startPacking(Object obj, Accessor accessor) throws AccessorException {
        return startPacking(obj, (Accessor<Object, byte[]>) accessor);
    }

    private PrimitiveArrayListerByte() {
    }

    static void register() {
        Lister.primitiveArrayListers.put(Byte.TYPE, new PrimitiveArrayListerByte());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public ListIterator<Byte> iterator(final byte[] bArr, XMLSerializer xMLSerializer) {
        return new ListIterator<Byte>() { // from class: com.sun.xml.bind.v2.runtime.reflect.PrimitiveArrayListerByte.1
            int idx = 0;

            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public boolean hasNext() {
                return this.idx < bArr.length;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public Byte next() {
                byte[] bArr2 = bArr;
                int i = this.idx;
                this.idx = i + 1;
                return Byte.valueOf(bArr2[i]);
            }
        };
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public ByteArrayPack startPacking(BeanT beant, Accessor<BeanT, byte[]> accessor) {
        return new ByteArrayPack();
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void addToPack(ByteArrayPack byteArrayPack, Byte b) {
        byteArrayPack.add(b);
    }

    /* JADX INFO: renamed from: endPacking, reason: avoid collision after fix types in other method */
    public void endPacking2(ByteArrayPack byteArrayPack, BeanT beant, Accessor<BeanT, byte[]> accessor) throws AccessorException {
        accessor.set(beant, byteArrayPack.build());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void reset(BeanT beant, Accessor<BeanT, byte[]> accessor) throws AccessorException {
        accessor.set(beant, new byte[0]);
    }

    static final class ByteArrayPack {
        byte[] buf = new byte[16];
        int size;

        ByteArrayPack() {
        }

        void add(Byte b) {
            byte[] bArr = this.buf;
            if (bArr.length == this.size) {
                byte[] bArr2 = new byte[bArr.length * 2];
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                this.buf = bArr2;
            }
            if (b != null) {
                byte[] bArr3 = this.buf;
                int i = this.size;
                this.size = i + 1;
                bArr3[i] = b.byteValue();
            }
        }

        byte[] build() {
            byte[] bArr = this.buf;
            int length = bArr.length;
            int i = this.size;
            if (length == i) {
                return bArr;
            }
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            return bArr2;
        }
    }
}
