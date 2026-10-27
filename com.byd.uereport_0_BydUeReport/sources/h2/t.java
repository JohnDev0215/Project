package h2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

/* JADX INFO: compiled from: CollectionCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class t implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t f5307a = new t();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [T, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r4v2, types: [T, com.alibaba.fastjson.JSONArray, java.util.Collection] */
    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        if (aVar.f4861j.q() == 8) {
            aVar.f4861j.V(16);
            return null;
        }
        if (type == JSONArray.class) {
            ?? r42 = (T) new JSONArray();
            aVar.X(r42);
            return r42;
        }
        ?? r02 = (T) l2.l.G(type);
        aVar.V(l2.l.U(type), r02, obj);
        return r02;
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.c0(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        SerializerFeature serializerFeature = SerializerFeature.WriteClassName;
        Type typeU = (d1Var.t(serializerFeature) || SerializerFeature.isEnabled(i8, serializerFeature)) ? l2.l.U(type) : null;
        Collection collection = (Collection) obj;
        y0 y0Var = i0Var.f5248r;
        int i9 = 0;
        i0Var.D(y0Var, obj, obj2, 0);
        if (d1Var.t(serializerFeature)) {
            if (HashSet.class.isAssignableFrom(collection.getClass())) {
                d1Var.append("Set");
            } else if (TreeSet.class == collection.getClass()) {
                d1Var.append("TreeSet");
            }
        }
        try {
            d1Var.append('[');
            for (Object obj3 : collection) {
                int i10 = i9 + 1;
                if (i9 != 0) {
                    d1Var.append(',');
                }
                if (obj3 == null) {
                    d1Var.Z();
                } else {
                    Class<?> cls = obj3.getClass();
                    if (cls == Integer.class) {
                        d1Var.V(((Integer) obj3).intValue());
                    } else if (cls == Long.class) {
                        d1Var.Y(((Long) obj3).longValue());
                        if (d1Var.t(SerializerFeature.WriteClassName)) {
                            d1Var.write(76);
                        }
                    } else {
                        t0 t0VarX = i0Var.x(cls);
                        if (SerializerFeature.isEnabled(i8, SerializerFeature.WriteClassName) && (t0VarX instanceof j0)) {
                            ((j0) t0VarX).D(i0Var, obj3, Integer.valueOf(i10 - 1), typeU, i8);
                        } else {
                            t0VarX.c(i0Var, obj3, Integer.valueOf(i10 - 1), typeU, i8);
                        }
                    }
                }
                i9 = i10;
            }
            d1Var.append(']');
        } finally {
            i0Var.f5248r = y0Var;
        }
    }

    @Override // g2.t
    public int e() {
        return 14;
    }
}
