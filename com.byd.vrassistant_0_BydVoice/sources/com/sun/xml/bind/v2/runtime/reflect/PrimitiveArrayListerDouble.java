package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.XMLSerializer;

/* JADX INFO: loaded from: classes3.dex */
final class PrimitiveArrayListerDouble<BeanT> extends Lister<BeanT, double[], Double, DoubleArrayPack> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ void endPacking(DoubleArrayPack doubleArrayPack, Object obj, Accessor accessor) throws AccessorException {
        endPacking2(doubleArrayPack, obj, (Accessor<Object, double[]>) accessor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ DoubleArrayPack startPacking(Object obj, Accessor accessor) throws AccessorException {
        return startPacking(obj, (Accessor<Object, double[]>) accessor);
    }

    private PrimitiveArrayListerDouble() {
    }

    static void register() {
        Lister.primitiveArrayListers.put(Double.TYPE, new PrimitiveArrayListerDouble());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public ListIterator<Double> iterator(final double[] dArr, XMLSerializer xMLSerializer) {
        return new ListIterator<Double>() { // from class: com.sun.xml.bind.v2.runtime.reflect.PrimitiveArrayListerDouble.1
            int idx = 0;

            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public boolean hasNext() {
                return this.idx < dArr.length;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public Double next() {
                double[] dArr2 = dArr;
                int i = this.idx;
                this.idx = i + 1;
                return Double.valueOf(dArr2[i]);
            }
        };
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public DoubleArrayPack startPacking(BeanT beant, Accessor<BeanT, double[]> accessor) {
        return new DoubleArrayPack();
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void addToPack(DoubleArrayPack doubleArrayPack, Double d) {
        doubleArrayPack.add(d);
    }

    /* JADX INFO: renamed from: endPacking, reason: avoid collision after fix types in other method */
    public void endPacking2(DoubleArrayPack doubleArrayPack, BeanT beant, Accessor<BeanT, double[]> accessor) throws AccessorException {
        accessor.set(beant, doubleArrayPack.build());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void reset(BeanT beant, Accessor<BeanT, double[]> accessor) throws AccessorException {
        accessor.set(beant, new double[0]);
    }

    static final class DoubleArrayPack {
        double[] buf = new double[16];
        int size;

        DoubleArrayPack() {
        }

        void add(Double d) {
            double[] dArr = this.buf;
            if (dArr.length == this.size) {
                double[] dArr2 = new double[dArr.length * 2];
                System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
                this.buf = dArr2;
            }
            if (d != null) {
                double[] dArr3 = this.buf;
                int i = this.size;
                this.size = i + 1;
                dArr3[i] = d.doubleValue();
            }
        }

        double[] build() {
            double[] dArr = this.buf;
            int length = dArr.length;
            int i = this.size;
            if (length == i) {
                return dArr;
            }
            double[] dArr2 = new double[i];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            return dArr2;
        }
    }
}
