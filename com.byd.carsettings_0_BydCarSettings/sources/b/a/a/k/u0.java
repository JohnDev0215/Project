package b.a.a.k;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: PrimitiveArraySerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class u0 implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static u0 f405a = new u0();

    @Override // b.a.a.k.t0
    public final void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.M(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        int i2 = 0;
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            d1Var.write(91);
            while (i2 < iArr.length) {
                if (i2 != 0) {
                    d1Var.write(44);
                }
                d1Var.F(iArr[i2]);
                i2++;
            }
            d1Var.write(93);
            return;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            d1Var.write(91);
            while (i2 < sArr.length) {
                if (i2 != 0) {
                    d1Var.write(44);
                }
                d1Var.F(sArr[i2]);
                i2++;
            }
            d1Var.write(93);
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            d1Var.write(91);
            while (i2 < jArr.length) {
                if (i2 != 0) {
                    d1Var.write(44);
                }
                d1Var.H(jArr[i2]);
                i2++;
            }
            d1Var.write(93);
            return;
        }
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            d1Var.write(91);
            while (i2 < zArr.length) {
                if (i2 != 0) {
                    d1Var.write(44);
                }
                d1Var.o(zArr[i2]);
                i2++;
            }
            d1Var.write(93);
            return;
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            d1Var.write(91);
            while (i2 < fArr.length) {
                if (i2 != 0) {
                    d1Var.write(44);
                }
                float f2 = fArr[i2];
                if (Float.isNaN(f2)) {
                    d1Var.J();
                } else {
                    d1Var.append(Float.toString(f2));
                }
                i2++;
            }
            d1Var.write(93);
            return;
        }
        if (!(obj instanceof double[])) {
            if (obj instanceof byte[]) {
                d1Var.q((byte[]) obj);
                return;
            } else {
                d1Var.O((char[]) obj);
                return;
            }
        }
        double[] dArr = (double[]) obj;
        d1Var.write(91);
        while (i2 < dArr.length) {
            if (i2 != 0) {
                d1Var.write(44);
            }
            double d2 = dArr[i2];
            if (Double.isNaN(d2)) {
                d1Var.J();
            } else {
                d1Var.append(Double.toString(d2));
            }
            i2++;
        }
        d1Var.write(93);
    }
}
