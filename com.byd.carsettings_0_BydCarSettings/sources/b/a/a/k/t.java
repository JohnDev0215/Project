package b.a.a.k;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

/* JADX INFO: compiled from: CollectionCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class t implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t f404a = new t();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [T, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r4v2, types: [T, com.alibaba.fastjson.JSONArray, java.util.Collection] */
    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        if (aVar.g.j() == 8) {
            aVar.g.F(16);
            return null;
        }
        if (type == JSONArray.class) {
            ?? r4 = (T) new JSONArray();
            aVar.G(r4);
            return r4;
        }
        ?? r0 = (T) b.a.a.n.l.G(type);
        aVar.F(b.a.a.n.l.U(type), r0, obj);
        return r0;
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.M(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        SerializerFeature serializerFeature = SerializerFeature.WriteClassName;
        Type typeU = (d1Var.l(serializerFeature) || SerializerFeature.isEnabled(i, serializerFeature)) ? b.a.a.n.l.U(type) : null;
        Collection collection = (Collection) obj;
        y0 y0Var = i0Var.r;
        int i2 = 0;
        i0Var.D(y0Var, obj, obj2, 0);
        if (d1Var.l(serializerFeature)) {
            if (HashSet.class.isAssignableFrom(collection.getClass())) {
                d1Var.append("Set");
            } else if (TreeSet.class == collection.getClass()) {
                d1Var.append("TreeSet");
            }
        }
        try {
            d1Var.append('[');
            for (Object obj3 : collection) {
                int i3 = i2 + 1;
                if (i2 != 0) {
                    d1Var.append(',');
                }
                if (obj3 == null) {
                    d1Var.J();
                } else {
                    Class<?> cls = obj3.getClass();
                    if (cls == Integer.class) {
                        d1Var.F(((Integer) obj3).intValue());
                    } else if (cls == Long.class) {
                        d1Var.H(((Long) obj3).longValue());
                        if (d1Var.l(SerializerFeature.WriteClassName)) {
                            d1Var.write(76);
                        }
                    } else {
                        t0 t0VarX = i0Var.x(cls);
                        if (SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName) && (t0VarX instanceof j0)) {
                            ((j0) t0VarX).D(i0Var, obj3, Integer.valueOf(i3 - 1), typeU, i);
                        } else {
                            t0VarX.c(i0Var, obj3, Integer.valueOf(i3 - 1), typeU, i);
                        }
                    }
                }
                i2 = i3;
            }
            d1Var.append(']');
        } finally {
            i0Var.r = y0Var;
        }
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 14;
    }
}
