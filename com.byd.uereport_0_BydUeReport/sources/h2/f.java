package h2;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: ArraySerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class<?> f5233a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t0 f5234b;

    public f(Class<?> cls, t0 t0Var) {
        this.f5233a = cls;
        this.f5234b = t0Var;
    }

    @Override // h2.t0
    public final void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.c0(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        Object[] objArr = (Object[]) obj;
        int length = objArr.length;
        y0 y0Var = i0Var.f5248r;
        i0Var.D(y0Var, obj, obj2, 0);
        try {
            d1Var.append('[');
            for (int i9 = 0; i9 < length; i9++) {
                if (i9 != 0) {
                    d1Var.append(',');
                }
                Object obj3 = objArr[i9];
                if (obj3 == null) {
                    if (d1Var.t(SerializerFeature.WriteNullStringAsEmpty) && (obj instanceof String[])) {
                        d1Var.d0("");
                    } else {
                        d1Var.append("null");
                    }
                } else if (obj3.getClass() == this.f5233a) {
                    this.f5234b.c(i0Var, obj3, Integer.valueOf(i9), null, 0);
                } else {
                    i0Var.x(obj3.getClass()).c(i0Var, obj3, Integer.valueOf(i9), null, 0);
                }
            }
            d1Var.append(']');
        } finally {
            i0Var.f5248r = y0Var;
        }
    }
}
