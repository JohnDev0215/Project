package b.a.a.n;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/* JADX INFO: compiled from: ParameterizedTypeImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements ParameterizedType {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Type[] f454a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Type f455b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f456c;

    public h(Type[] typeArr, Type type, Type type2) {
        this.f454a = typeArr;
        this.f455b = type;
        this.f456c = type2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || h.class != obj.getClass()) {
            return false;
        }
        h hVar = (h) obj;
        if (!Arrays.equals(this.f454a, hVar.f454a)) {
            return false;
        }
        Type type = this.f455b;
        if (type == null ? hVar.f455b != null : !type.equals(hVar.f455b)) {
            return false;
        }
        Type type2 = this.f456c;
        Type type3 = hVar.f456c;
        return type2 != null ? type2.equals(type3) : type3 == null;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return this.f454a;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return this.f455b;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getRawType() {
        return this.f456c;
    }

    public int hashCode() {
        Type[] typeArr = this.f454a;
        int iHashCode = (typeArr != null ? Arrays.hashCode(typeArr) : 0) * 31;
        Type type = this.f455b;
        int iHashCode2 = (iHashCode + (type != null ? type.hashCode() : 0)) * 31;
        Type type2 = this.f456c;
        return iHashCode2 + (type2 != null ? type2.hashCode() : 0);
    }
}
