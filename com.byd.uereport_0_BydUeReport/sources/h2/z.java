package h2;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Enumeration;

/* JADX INFO: compiled from: EnumerationSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class z implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static z f5319a = new z();

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.c0(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        Type type2 = null;
        int i9 = 0;
        if (d1Var.t(SerializerFeature.WriteClassName) && (type instanceof ParameterizedType)) {
            type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        Enumeration enumeration = (Enumeration) obj;
        y0 y0Var = i0Var.f5248r;
        i0Var.D(y0Var, obj, obj2, 0);
        try {
            d1Var.append('[');
            while (enumeration.hasMoreElements()) {
                Object objNextElement = enumeration.nextElement();
                int i10 = i9 + 1;
                if (i9 != 0) {
                    d1Var.append(',');
                }
                if (objNextElement == null) {
                    d1Var.Z();
                } else {
                    i0Var.x(objNextElement.getClass()).c(i0Var, objNextElement, Integer.valueOf(i10 - 1), type2, 0);
                }
                i9 = i10;
            }
            d1Var.append(']');
        } finally {
            i0Var.f5248r = y0Var;
        }
    }
}
