package b.a.a.k;

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
public class c0 implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static c0 f341a = new c0();

    /* JADX WARN: Type inference failed for: r4v2, types: [T, com.google.common.collect.ArrayListMultimap] */
    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
        }
        if (type != ArrayListMultimap.class) {
            return null;
        }
        ?? r4 = (T) ArrayListMultimap.create();
        for (Map.Entry<String, Object> entry : aVar.M().entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Collection) {
                r4.putAll(entry.getKey(), (List) value);
            } else {
                r4.put(entry.getKey(), value);
            }
        }
        return r4;
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj instanceof Multimap) {
            i0Var.H(((Multimap) obj).asMap());
        }
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 0;
    }
}
