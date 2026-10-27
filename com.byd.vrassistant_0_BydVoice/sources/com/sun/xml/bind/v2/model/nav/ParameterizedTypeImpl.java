package com.sun.xml.bind.v2.model.nav;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
class ParameterizedTypeImpl implements ParameterizedType {
    private Type[] actualTypeArguments;
    private Type ownerType;
    private Class<?> rawType;

    ParameterizedTypeImpl(Class<?> cls, Type[] typeArr, Type type) {
        this.actualTypeArguments = typeArr;
        this.rawType = cls;
        if (type != null) {
            this.ownerType = type;
        } else {
            this.ownerType = cls.getDeclaringClass();
        }
        validateConstructorArguments();
    }

    private void validateConstructorArguments() {
        if (this.rawType.getTypeParameters().length != this.actualTypeArguments.length) {
            throw new MalformedParameterizedTypeException();
        }
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return (Type[]) this.actualTypeArguments.clone();
    }

    @Override // java.lang.reflect.ParameterizedType
    public Class<?> getRawType() {
        return this.rawType;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return this.ownerType;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ParameterizedType)) {
            return false;
        }
        ParameterizedType parameterizedType = (ParameterizedType) obj;
        if (this == parameterizedType) {
            return true;
        }
        Type ownerType = parameterizedType.getOwnerType();
        Type rawType = parameterizedType.getRawType();
        Type type = this.ownerType;
        if (type == null) {
            if (ownerType != null) {
                return false;
            }
        } else if (!type.equals(ownerType)) {
            return false;
        }
        Class<?> cls = this.rawType;
        if (cls == null) {
            if (rawType != null) {
                return false;
            }
        } else if (!cls.equals(rawType)) {
            return false;
        }
        return Arrays.equals(this.actualTypeArguments, parameterizedType.getActualTypeArguments());
    }

    public int hashCode() {
        int iHashCode = Arrays.hashCode(this.actualTypeArguments);
        Type type = this.ownerType;
        int iHashCode2 = iHashCode ^ (type == null ? 0 : type.hashCode());
        Class<?> cls = this.rawType;
        return iHashCode2 ^ (cls != null ? cls.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Type type = this.ownerType;
        if (type != null) {
            if (type instanceof Class) {
                sb.append(((Class) type).getName());
            } else {
                sb.append(type.toString());
            }
            sb.append(".");
            if (this.ownerType instanceof ParameterizedTypeImpl) {
                sb.append(this.rawType.getName().replace(((ParameterizedTypeImpl) this.ownerType).rawType.getName() + "$", ""));
            } else {
                sb.append(this.rawType.getName());
            }
        } else {
            sb.append(this.rawType.getName());
        }
        Type[] typeArr = this.actualTypeArguments;
        if (typeArr != null && typeArr.length > 0) {
            sb.append("<");
            Type[] typeArr2 = this.actualTypeArguments;
            int length = typeArr2.length;
            boolean z = true;
            int i = 0;
            while (i < length) {
                Type type2 = typeArr2[i];
                if (!z) {
                    sb.append(", ");
                }
                if (type2 instanceof Class) {
                    sb.append(((Class) type2).getName());
                } else {
                    sb.append(type2.toString());
                }
                i++;
                z = false;
            }
            sb.append(">");
        }
        return sb.toString();
    }
}
