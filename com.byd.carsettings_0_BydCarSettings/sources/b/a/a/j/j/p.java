package b.a.a.j.j;

import java.io.Closeable;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

/* JADX INFO: compiled from: JavaObjectDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class p implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f291a = new p();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        if (!(type instanceof GenericArrayType)) {
            return (!(type instanceof Class) || type == Object.class || type == Serializable.class || type == Cloneable.class || type == Closeable.class || type == Comparable.class) ? (T) aVar.B(obj) : (T) aVar.O(type);
        }
        Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
        if (genericComponentType instanceof TypeVariable) {
            genericComponentType = ((TypeVariable) genericComponentType).getBounds()[0];
        }
        ArrayList arrayList = new ArrayList();
        aVar.E(genericComponentType, arrayList);
        T t = (T) ((Object[]) Array.newInstance(b.a.a.n.l.h0(genericComponentType), arrayList.size()));
        arrayList.toArray((Object[]) t);
        return t;
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 12;
    }
}
