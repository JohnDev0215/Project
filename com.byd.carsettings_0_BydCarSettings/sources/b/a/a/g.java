package b.a.a;

import b.a.a.n.h;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: TypeReference.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ConcurrentMap<Type, Type> f181a = new ConcurrentHashMap(16, 0.75f, 1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Type f182b = new a().a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f183c;

    /* JADX INFO: compiled from: TypeReference.java */
    public static class a extends g<List<String>> {
    }

    public g() {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Type type2 = f181a.get(type);
        if (type2 == null) {
            f181a.putIfAbsent(type, type);
            type2 = f181a.get(type);
        }
        this.f183c = type2;
    }

    public static Type b(h hVar) {
        Type type = f181a.get(hVar);
        if (type != null) {
            return type;
        }
        f181a.putIfAbsent(hVar, hVar);
        return f181a.get(hVar);
    }

    public Type a() {
        return this.f183c;
    }
}
