package l2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/* JADX INFO: compiled from: ParameterizedTypeImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements ParameterizedType {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Type[] f6233d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Type f6234e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Type f6235f;

    public h(Type[] typeArr, Type type, Type type2) {
        this.f6233d = typeArr;
        this.f6234e = type;
        this.f6235f = type2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || h.class != obj.getClass()) {
            return false;
        }
        h hVar = (h) obj;
        if (!Arrays.equals(this.f6233d, hVar.f6233d)) {
            return false;
        }
        Type type = this.f6234e;
        if (type == null ? hVar.f6234e != null : !type.equals(hVar.f6234e)) {
            return false;
        }
        Type type2 = this.f6235f;
        Type type3 = hVar.f6235f;
        return type2 != null ? type2.equals(type3) : type3 == null;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return this.f6233d;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return this.f6234e;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getRawType() {
        return this.f6235f;
    }

    public int hashCode() {
        Type[] typeArr = this.f6233d;
        int iHashCode = (typeArr != null ? Arrays.hashCode(typeArr) : 0) * 31;
        Type type = this.f6234e;
        int iHashCode2 = (iHashCode + (type != null ? type.hashCode() : 0)) * 31;
        Type type2 = this.f6235f;
        return iHashCode2 + (type2 != null ? type2.hashCode() : 0);
    }
}
