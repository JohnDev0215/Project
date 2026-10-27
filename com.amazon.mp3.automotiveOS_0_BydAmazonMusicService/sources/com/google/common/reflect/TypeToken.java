package com.google.common.reflect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.reflect.Invokable;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public abstract class TypeToken<T> extends TypeCapture<T> implements Serializable {

    @CheckForNull
    private transient TypeResolver covariantTypeResolver;

    @CheckForNull
    private transient TypeResolver invariantTypeResolver;
    private final Type runtimeType;

    /* synthetic */ TypeToken(Type type, AnonymousClass1 anonymousClass1) {
        this(type);
    }

    protected TypeToken() {
        Type typeCapture = capture();
        this.runtimeType = typeCapture;
        Preconditions.checkState(!(typeCapture instanceof TypeVariable), "Cannot construct a TypeToken for a type variable.\nYou probably meant to call new TypeToken<%s>(getClass()) that can resolve the type variable for you.\nIf you do need to create a TypeToken of a type variable, please use TypeToken.of() instead.", typeCapture);
    }

    private TypeToken(Type type) {
        this.runtimeType = (Type) Preconditions.checkNotNull(type);
    }

    public static <T> TypeToken<T> of(Class<T> cls) {
        return new SimpleTypeToken(cls);
    }

    public static TypeToken<?> of(Type type) {
        return new SimpleTypeToken(type);
    }

    public final Class<? super T> getRawType() {
        return getRawTypes().iterator().next();
    }

    private TypeToken<?> resolveSupertype(Type type) {
        TypeToken<?> typeTokenOf = of(getCovariantTypeResolver().resolveType(type));
        typeTokenOf.covariantTypeResolver = this.covariantTypeResolver;
        typeTokenOf.invariantTypeResolver = this.invariantTypeResolver;
        return typeTokenOf;
    }

    @CheckForNull
    final TypeToken<? super T> getGenericSuperclass() {
        Type type = this.runtimeType;
        if (type instanceof TypeVariable) {
            return boundAsSuperclass(((TypeVariable) type).getBounds()[0]);
        }
        if (type instanceof WildcardType) {
            return boundAsSuperclass(((WildcardType) type).getUpperBounds()[0]);
        }
        Type genericSuperclass = getRawType().getGenericSuperclass();
        if (genericSuperclass == null) {
            return null;
        }
        return (TypeToken<? super T>) resolveSupertype(genericSuperclass);
    }

    @CheckForNull
    private TypeToken<? super T> boundAsSuperclass(Type type) {
        TypeToken<? super T> typeToken = (TypeToken<? super T>) of(type);
        if (typeToken.getRawType().isInterface()) {
            return null;
        }
        return typeToken;
    }

    final ImmutableList<TypeToken<? super T>> getGenericInterfaces() {
        Type type = this.runtimeType;
        if (type instanceof TypeVariable) {
            return boundsAsInterfaces(((TypeVariable) type).getBounds());
        }
        if (type instanceof WildcardType) {
            return boundsAsInterfaces(((WildcardType) type).getUpperBounds());
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type type2 : getRawType().getGenericInterfaces()) {
            builder.add(resolveSupertype(type2));
        }
        return builder.build();
    }

    private ImmutableList<TypeToken<? super T>> boundsAsInterfaces(Type[] typeArr) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type type : typeArr) {
            TypeToken<?> typeTokenOf = of(type);
            if (typeTokenOf.getRawType().isInterface()) {
                builder.add(typeTokenOf);
            }
        }
        return builder.build();
    }

    public final TypeToken<T>.TypeSet getTypes() {
        return new TypeSet();
    }

    /* JADX INFO: renamed from: com.google.common.reflect.TypeToken$1, reason: invalid class name */
    class AnonymousClass1 extends Invokable.MethodInvokable<Object> {
        final /* synthetic */ TypeToken this$0;

        @Override // com.google.common.reflect.Invokable
        public TypeToken<Object> getOwnerType() {
            return this.this$0;
        }

        @Override // com.google.common.reflect.Invokable
        public String toString() {
            String strValueOf = String.valueOf(getOwnerType());
            String string = super.toString();
            StringBuilder sb = new StringBuilder(strValueOf.length() + 1 + String.valueOf(string).length());
            sb.append(strValueOf);
            sb.append(".");
            sb.append(string);
            return sb.toString();
        }
    }

    public class TypeSet extends ForwardingSet<TypeToken<? super T>> implements Serializable {

        @CheckForNull
        private transient ImmutableSet<TypeToken<? super T>> types;

        TypeSet() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.types;
            if (immutableSet != null) {
                return immutableSet;
            }
            ImmutableSet<TypeToken<? super T>> set = FluentIterable.from(TypeCollector.FOR_GENERIC_TYPE.collectTypes(TypeToken.this)).filter(TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
            this.types = set;
            return set;
        }

        public Set<Class<? super T>> rawTypes() {
            return ImmutableSet.copyOf((Collection) TypeCollector.FOR_RAW_TYPE.collectTypes(TypeToken.this.getRawTypes()));
        }
    }

    private enum TypeFilter implements Predicate<TypeToken<?>> {
        IGNORE_TYPE_VARIABLE_OR_WILDCARD { // from class: com.google.common.reflect.TypeToken.TypeFilter.1
            @Override // com.google.common.reflect.TypeToken.TypeFilter, com.google.common.base.Predicate
            public boolean apply(TypeToken<?> typeToken) {
                return ((((TypeToken) typeToken).runtimeType instanceof TypeVariable) || (((TypeToken) typeToken).runtimeType instanceof WildcardType)) ? false : true;
            }
        },
        INTERFACE_ONLY { // from class: com.google.common.reflect.TypeToken.TypeFilter.2
            @Override // com.google.common.reflect.TypeToken.TypeFilter, com.google.common.base.Predicate
            public boolean apply(TypeToken<?> typeToken) {
                return typeToken.getRawType().isInterface();
            }
        };

        @Override // com.google.common.base.Predicate
        public abstract /* synthetic */ boolean apply(T t);

        /* synthetic */ TypeFilter(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj instanceof TypeToken) {
            return this.runtimeType.equals(((TypeToken) obj).runtimeType);
        }
        return false;
    }

    public int hashCode() {
        return this.runtimeType.hashCode();
    }

    public String toString() {
        return Types.toString(this.runtimeType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImmutableSet<Class<? super T>> getRawTypes() {
        final ImmutableSet.Builder builder = ImmutableSet.builder();
        new TypeVisitor(this) { // from class: com.google.common.reflect.TypeToken.4
            @Override // com.google.common.reflect.TypeVisitor
            void visitTypeVariable(TypeVariable<?> typeVariable) {
                visit(typeVariable.getBounds());
            }

            @Override // com.google.common.reflect.TypeVisitor
            void visitWildcardType(WildcardType wildcardType) {
                visit(wildcardType.getUpperBounds());
            }

            @Override // com.google.common.reflect.TypeVisitor
            void visitParameterizedType(ParameterizedType parameterizedType) {
                builder.add((Class) parameterizedType.getRawType());
            }

            @Override // com.google.common.reflect.TypeVisitor
            void visitClass(Class<?> cls) {
                builder.add(cls);
            }

            @Override // com.google.common.reflect.TypeVisitor
            void visitGenericArrayType(GenericArrayType genericArrayType) {
                builder.add(Types.getArrayClass(TypeToken.of(genericArrayType.getGenericComponentType()).getRawType()));
            }
        }.visit(this.runtimeType);
        return builder.build();
    }

    private TypeResolver getCovariantTypeResolver() {
        TypeResolver typeResolver = this.covariantTypeResolver;
        if (typeResolver != null) {
            return typeResolver;
        }
        TypeResolver typeResolverCovariantly = TypeResolver.covariantly(this.runtimeType);
        this.covariantTypeResolver = typeResolverCovariantly;
        return typeResolverCovariantly;
    }

    private static final class SimpleTypeToken<T> extends TypeToken<T> {
        SimpleTypeToken(Type type) {
            super(type, null);
        }
    }

    private static abstract class TypeCollector<K> {
        static final TypeCollector<TypeToken<?>> FOR_GENERIC_TYPE = new TypeCollector<TypeToken<?>>() { // from class: com.google.common.reflect.TypeToken.TypeCollector.1
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Class<?> getRawType(TypeToken<?> typeToken) {
                return typeToken.getRawType();
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Iterable<? extends TypeToken<?>> getInterfaces(TypeToken<?> typeToken) {
                return typeToken.getGenericInterfaces();
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeToken.TypeCollector
            @CheckForNull
            public TypeToken<?> getSuperclass(TypeToken<?> typeToken) {
                return typeToken.getGenericSuperclass();
            }
        };
        static final TypeCollector<Class<?>> FOR_RAW_TYPE = new TypeCollector<Class<?>>() { // from class: com.google.common.reflect.TypeToken.TypeCollector.2
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Class<?> getRawType(Class<?> cls) {
                return cls;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Iterable<? extends Class<?>> getInterfaces(Class<?> cls) {
                return Arrays.asList(cls.getInterfaces());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeToken.TypeCollector
            @CheckForNull
            public Class<?> getSuperclass(Class<?> cls) {
                return cls.getSuperclass();
            }
        };

        abstract Iterable<? extends K> getInterfaces(K k);

        abstract Class<?> getRawType(K k);

        @CheckForNull
        abstract K getSuperclass(K k);

        private TypeCollector() {
        }

        /* synthetic */ TypeCollector(AnonymousClass1 anonymousClass1) {
            this();
        }

        final ImmutableList<K> collectTypes(K k) {
            return collectTypes((Iterable) ImmutableList.of(k));
        }

        ImmutableList<K> collectTypes(Iterable<? extends K> iterable) {
            HashMap mapNewHashMap = Maps.newHashMap();
            Iterator<? extends K> it = iterable.iterator();
            while (it.hasNext()) {
                collectTypes(it.next(), mapNewHashMap);
            }
            return sortKeysByValue(mapNewHashMap, Ordering.natural().reverse());
        }

        /* JADX WARN: Multi-variable type inference failed */
        private int collectTypes(K k, Map<? super K, Integer> map) {
            Integer num = map.get(k);
            if (num != null) {
                return num.intValue();
            }
            boolean zIsInterface = getRawType(k).isInterface();
            Iterator<? extends K> it = getInterfaces(k).iterator();
            int iMax = zIsInterface;
            while (it.hasNext()) {
                iMax = Math.max(iMax, collectTypes(it.next(), map));
            }
            K superclass = getSuperclass(k);
            int iMax2 = iMax;
            if (superclass != null) {
                iMax2 = Math.max(iMax, collectTypes(superclass, map));
            }
            int i = iMax2 + 1;
            map.put(k, Integer.valueOf(i));
            return i;
        }

        private static <K, V> ImmutableList<K> sortKeysByValue(final Map<K, V> map, final Comparator<? super V> comparator) {
            return (ImmutableList<K>) new Ordering<K>() { // from class: com.google.common.reflect.TypeToken.TypeCollector.4
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.collect.Ordering, java.util.Comparator
                public int compare(K k, K k2) {
                    Comparator comparator2 = comparator;
                    Object obj = map.get(k);
                    Objects.requireNonNull(obj);
                    Object obj2 = map.get(k2);
                    Objects.requireNonNull(obj2);
                    return comparator2.compare(obj, obj2);
                }
            }.immutableSortedCopy(map.keySet());
        }
    }
}
