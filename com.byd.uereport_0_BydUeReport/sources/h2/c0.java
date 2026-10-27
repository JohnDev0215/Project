package h2;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GuavaCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class c0 implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static c0 f5191a = new c0();

    /* JADX WARN: Type inference failed for: r4v2, types: [T, com.google.common.collect.ArrayListMultimap] */
    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
        }
        if (type != ArrayListMultimap.class) {
            return null;
        }
        ?? r42 = (T) ArrayListMultimap.create();
        for (Map.Entry<String, Object> entry : aVar.c0().entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Collection) {
                r42.putAll(entry.getKey(), (List) value);
            } else {
                r42.put(entry.getKey(), value);
            }
        }
        return r42;
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (obj instanceof Multimap) {
            i0Var.H(((Multimap) obj).asMap());
        }
    }

    @Override // g2.t
    public int e() {
        return 0;
    }
}
