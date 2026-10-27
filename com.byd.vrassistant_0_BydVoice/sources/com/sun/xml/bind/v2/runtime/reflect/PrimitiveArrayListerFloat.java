package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.XMLSerializer;

/* JADX INFO: loaded from: classes3.dex */
final class PrimitiveArrayListerFloat<BeanT> extends Lister<BeanT, float[], Float, FloatArrayPack> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ void endPacking(FloatArrayPack floatArrayPack, Object obj, Accessor accessor) throws AccessorException {
        endPacking2(floatArrayPack, obj, (Accessor<Object, float[]>) accessor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ FloatArrayPack startPacking(Object obj, Accessor accessor) throws AccessorException {
        return startPacking(obj, (Accessor<Object, float[]>) accessor);
    }

    private PrimitiveArrayListerFloat() {
    }

    static void register() {
        Lister.primitiveArrayListers.put(Float.TYPE, new PrimitiveArrayListerFloat());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public ListIterator<Float> iterator(final float[] fArr, XMLSerializer xMLSerializer) {
        return new ListIterator<Float>() { // from class: com.sun.xml.bind.v2.runtime.reflect.PrimitiveArrayListerFloat.1
            int idx = 0;

            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public boolean hasNext() {
                return this.idx < fArr.length;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public Float next() {
                float[] fArr2 = fArr;
                int i = this.idx;
                this.idx = i + 1;
                return Float.valueOf(fArr2[i]);
            }
        };
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public FloatArrayPack startPacking(BeanT beant, Accessor<BeanT, float[]> accessor) {
        return new FloatArrayPack();
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void addToPack(FloatArrayPack floatArrayPack, Float f) {
        floatArrayPack.add(f);
    }

    /* JADX INFO: renamed from: endPacking, reason: avoid collision after fix types in other method */
    public void endPacking2(FloatArrayPack floatArrayPack, BeanT beant, Accessor<BeanT, float[]> accessor) throws AccessorException {
        accessor.set(beant, floatArrayPack.build());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void reset(BeanT beant, Accessor<BeanT, float[]> accessor) throws AccessorException {
        accessor.set(beant, new float[0]);
    }

    static final class FloatArrayPack {
        float[] buf = new float[16];
        int size;

        FloatArrayPack() {
        }

        void add(Float f) {
            float[] fArr = this.buf;
            if (fArr.length == this.size) {
                float[] fArr2 = new float[fArr.length * 2];
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                this.buf = fArr2;
            }
            if (f != null) {
                float[] fArr3 = this.buf;
                int i = this.size;
                this.size = i + 1;
                fArr3[i] = f.floatValue();
            }
        }

        float[] build() {
            float[] fArr = this.buf;
            int length = fArr.length;
            int i = this.size;
            if (length == i) {
                return fArr;
            }
            float[] fArr2 = new float[i];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            return fArr2;
        }
    }
}
