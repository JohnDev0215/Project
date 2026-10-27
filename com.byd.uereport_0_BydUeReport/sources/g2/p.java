package g2;

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
    public static final p f5049a = new p();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        if (!(type instanceof GenericArrayType)) {
            return (!(type instanceof Class) || type == Object.class || type == Serializable.class || type == Cloneable.class || type == Closeable.class || type == Comparable.class) ? (T) aVar.P(obj) : (T) aVar.e0(type);
        }
        Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
        if (genericComponentType instanceof TypeVariable) {
            genericComponentType = ((TypeVariable) genericComponentType).getBounds()[0];
        }
        ArrayList arrayList = new ArrayList();
        aVar.T(genericComponentType, arrayList);
        T t7 = (T) ((Object[]) Array.newInstance(l2.l.h0(genericComponentType), arrayList.size()));
        arrayList.toArray((Object[]) t7);
        return t7;
    }

    @Override // g2.t
    public int e() {
        return 12;
    }
}
