package c2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import l2.h;

/* JADX INFO: compiled from: TypeReference.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ConcurrentMap<Type, Type> f2304a = new ConcurrentHashMap(16, 0.75f, 1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Type f2305b = new a().a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f2306c;

    /* JADX INFO: compiled from: TypeReference.java */
    public static class a extends g<List<String>> {
    }

    public g() {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Type type2 = f2304a.get(type);
        if (type2 == null) {
            f2304a.putIfAbsent(type, type);
            type2 = f2304a.get(type);
        }
        this.f2306c = type2;
    }

    public static Type b(h hVar) {
        Type type = f2304a.get(hVar);
        if (type != null) {
            return type;
        }
        f2304a.putIfAbsent(hVar, hVar);
        return f2304a.get(hVar);
    }

    public Type a() {
        return this.f2306c;
    }
}
