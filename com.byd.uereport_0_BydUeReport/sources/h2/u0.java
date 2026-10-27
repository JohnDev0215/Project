package h2;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: PrimitiveArraySerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class u0 implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static u0 f5308a = new u0();

    @Override // h2.t0
    public final void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.c0(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        int i9 = 0;
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            d1Var.write(91);
            while (i9 < iArr.length) {
                if (i9 != 0) {
                    d1Var.write(44);
                }
                d1Var.V(iArr[i9]);
                i9++;
            }
            d1Var.write(93);
            return;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            d1Var.write(91);
            while (i9 < sArr.length) {
                if (i9 != 0) {
                    d1Var.write(44);
                }
                d1Var.V(sArr[i9]);
                i9++;
            }
            d1Var.write(93);
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            d1Var.write(91);
            while (i9 < jArr.length) {
                if (i9 != 0) {
                    d1Var.write(44);
                }
                d1Var.Y(jArr[i9]);
                i9++;
            }
            d1Var.write(93);
            return;
        }
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            d1Var.write(91);
            while (i9 < zArr.length) {
                if (i9 != 0) {
                    d1Var.write(44);
                }
                d1Var.w(zArr[i9]);
                i9++;
            }
            d1Var.write(93);
            return;
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            d1Var.write(91);
            while (i9 < fArr.length) {
                if (i9 != 0) {
                    d1Var.write(44);
                }
                float f8 = fArr[i9];
                if (Float.isNaN(f8)) {
                    d1Var.Z();
                } else {
                    d1Var.append(Float.toString(f8));
                }
                i9++;
            }
            d1Var.write(93);
            return;
        }
        if (!(obj instanceof double[])) {
            if (obj instanceof byte[]) {
                d1Var.x((byte[]) obj);
                return;
            } else {
                d1Var.e0((char[]) obj);
                return;
            }
        }
        double[] dArr = (double[]) obj;
        d1Var.write(91);
        while (i9 < dArr.length) {
            if (i9 != 0) {
                d1Var.write(44);
            }
            double d8 = dArr[i9];
            if (Double.isNaN(d8)) {
                d1Var.Z();
            } else {
                d1Var.append(Double.toString(d8));
            }
            i9++;
        }
        d1Var.write(93);
    }
}
