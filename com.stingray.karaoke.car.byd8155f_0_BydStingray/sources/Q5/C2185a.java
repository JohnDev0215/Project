package q5;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Objects;
import l5.AbstractC1776d;

/* JADX INFO: renamed from: q5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2185a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f21253a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Type f21254b;
    public final int c;

    public C2185a() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            if (parameterizedType.getRawType() == C2185a.class) {
                Type typeA = AbstractC1776d.a(parameterizedType.getActualTypeArguments()[0]);
                if (!Objects.equals(System.getProperty("gson.allowCapturingTypeVariables"), "true")) {
                    a(typeA);
                }
                this.f21254b = typeA;
                this.f21253a = AbstractC1776d.h(typeA);
                this.c = typeA.hashCode();
                return;
            }
        } else if (genericSuperclass == C2185a.class) {
            throw new IllegalStateException("TypeToken must be created with a type argument: new TypeToken<...>() {}; When using code shrinkers (ProGuard, R8, ...) make sure that generic signatures are preserved.\nSee " + "https://github.com/google/gson/blob/main/Troubleshooting.md#".concat("type-token-raw"));
        }
        throw new IllegalStateException("Must only create direct subclasses of TypeToken");
    }

    public static void a(Type type) {
        if (type instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type;
            throw new IllegalArgumentException("TypeToken type argument must not contain a type variable; captured type variable " + typeVariable.getName() + " declared by " + typeVariable.getGenericDeclaration() + "\nSee " + "https://github.com/google/gson/blob/main/Troubleshooting.md#".concat("typetoken-type-variable"));
        }
        if (type instanceof GenericArrayType) {
            a(((GenericArrayType) type).getGenericComponentType());
            return;
        }
        int i7 = 0;
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type ownerType = parameterizedType.getOwnerType();
            if (ownerType != null) {
                a(ownerType);
            }
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            int length = actualTypeArguments.length;
            while (i7 < length) {
                a(actualTypeArguments[i7]);
                i7++;
            }
            return;
        }
        if (!(type instanceof WildcardType)) {
            if (type == null) {
                throw new IllegalArgumentException("TypeToken captured `null` as type argument; probably a compiler / runtime bug");
            }
            return;
        }
        WildcardType wildcardType = (WildcardType) type;
        for (Type type2 : wildcardType.getLowerBounds()) {
            a(type2);
        }
        Type[] upperBounds = wildcardType.getUpperBounds();
        int length2 = upperBounds.length;
        while (i7 < length2) {
            a(upperBounds[i7]);
            i7++;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C2185a) {
            if (AbstractC1776d.e(this.f21254b, ((C2185a) obj).f21254b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return AbstractC1776d.k(this.f21254b);
    }

    public C2185a(Type type) {
        Objects.requireNonNull(type);
        Type typeA = AbstractC1776d.a(type);
        this.f21254b = typeA;
        this.f21253a = AbstractC1776d.h(typeA);
        this.c = typeA.hashCode();
    }
}
