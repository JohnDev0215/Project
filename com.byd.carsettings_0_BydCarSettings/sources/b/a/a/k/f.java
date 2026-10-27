package b.a.a.k;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: ArraySerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class<?> f363a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t0 f364b;

    public f(Class<?> cls, t0 t0Var) {
        this.f363a = cls;
        this.f364b = t0Var;
    }

    @Override // b.a.a.k.t0
    public final void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.M(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        Object[] objArr = (Object[]) obj;
        int length = objArr.length;
        y0 y0Var = i0Var.r;
        i0Var.D(y0Var, obj, obj2, 0);
        try {
            d1Var.append('[');
            for (int i2 = 0; i2 < length; i2++) {
                if (i2 != 0) {
                    d1Var.append(',');
                }
                Object obj3 = objArr[i2];
                if (obj3 == null) {
                    if (d1Var.l(SerializerFeature.WriteNullStringAsEmpty) && (obj instanceof String[])) {
                        d1Var.N("");
                    } else {
                        d1Var.append("null");
                    }
                } else if (obj3.getClass() == this.f363a) {
                    this.f364b.c(i0Var, obj3, Integer.valueOf(i2), null, 0);
                } else {
                    i0Var.x(obj3.getClass()).c(i0Var, obj3, Integer.valueOf(i2), null, 0);
                }
            }
            d1Var.append(']');
        } finally {
            i0Var.r = y0Var;
        }
    }
}
