package b.a.a.k;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Enumeration;

/* JADX INFO: compiled from: EnumerationSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class z implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static z f416a = new z();

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.M(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        Type type2 = null;
        int i2 = 0;
        if (d1Var.l(SerializerFeature.WriteClassName) && (type instanceof ParameterizedType)) {
            type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        Enumeration enumeration = (Enumeration) obj;
        y0 y0Var = i0Var.r;
        i0Var.D(y0Var, obj, obj2, 0);
        try {
            d1Var.append('[');
            while (enumeration.hasMoreElements()) {
                Object objNextElement = enumeration.nextElement();
                int i3 = i2 + 1;
                if (i2 != 0) {
                    d1Var.append(',');
                }
                if (objNextElement == null) {
                    d1Var.J();
                } else {
                    i0Var.x(objNextElement.getClass()).c(i0Var, objNextElement, Integer.valueOf(i3 - 1), type2, 0);
                }
                i2 = i3;
            }
            d1Var.append(']');
        } finally {
            i0Var.r = y0Var;
        }
    }
}
