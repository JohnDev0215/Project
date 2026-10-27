package net.bytebuddy.pool;

import cerence.ark.assistant.controller.enumeration.TemperatureUnitEnum;
import com.android.multidex.ClassPathElement;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.GenericSignatureFormatError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.bytebuddy.description.TypeVariableSource;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.PackageDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.jar.asm.TypeReference;
import net.bytebuddy.jar.asm.signature.SignatureReader;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.matcher.ElementMatchers;

/* JADX INFO: loaded from: classes3.dex */
public interface TypePool {
    void clear();

    Resolution describe(String str);

    public interface Resolution {
        boolean isResolved();

        TypeDescription resolve();

        public static class Simple implements Resolution {
            private final TypeDescription typeDescription;

            @Override // net.bytebuddy.pool.TypePool.Resolution
            public boolean isResolved() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Simple;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Simple)) {
                    return false;
                }
                Simple simple = (Simple) obj;
                if (!simple.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.typeDescription;
                TypeDescription typeDescription2 = simple.typeDescription;
                return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.typeDescription;
                return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
            }

            public Simple(TypeDescription typeDescription) {
                this.typeDescription = typeDescription;
            }

            @Override // net.bytebuddy.pool.TypePool.Resolution
            public TypeDescription resolve() {
                return this.typeDescription;
            }
        }

        public static class Illegal implements Resolution {
            private final String name;

            @Override // net.bytebuddy.pool.TypePool.Resolution
            public boolean isResolved() {
                return false;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Illegal;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Illegal)) {
                    return false;
                }
                Illegal illegal = (Illegal) obj;
                if (!illegal.canEqual(this)) {
                    return false;
                }
                String str = this.name;
                String str2 = illegal.name;
                return str != null ? str.equals(str2) : str2 == null;
            }

            public int hashCode() {
                String str = this.name;
                return 59 + (str == null ? 43 : str.hashCode());
            }

            public Illegal(String str) {
                this.name = str;
            }

            @Override // net.bytebuddy.pool.TypePool.Resolution
            public TypeDescription resolve() {
                throw new IllegalStateException("Cannot resolve type description for " + this.name);
            }
        }
    }

    public interface CacheProvider {
        public static final Resolution UNRESOLVED = null;

        void clear();

        Resolution find(String str);

        Resolution register(String str, Resolution resolution);

        public enum NoOp implements CacheProvider {
            INSTANCE;

            @Override // net.bytebuddy.pool.TypePool.CacheProvider
            public void clear() {
            }

            @Override // net.bytebuddy.pool.TypePool.CacheProvider
            public Resolution register(String str, Resolution resolution) {
                return resolution;
            }

            @Override // net.bytebuddy.pool.TypePool.CacheProvider
            public Resolution find(String str) {
                return UNRESOLVED;
            }
        }

        public static class Simple implements CacheProvider {
            private final ConcurrentMap<String, Resolution> cache = new ConcurrentHashMap();

            public static CacheProvider withObjectType() {
                Simple simple = new Simple();
                simple.register(Object.class.getName(), new Resolution.Simple(TypeDescription.OBJECT));
                return simple;
            }

            @Override // net.bytebuddy.pool.TypePool.CacheProvider
            public Resolution find(String str) {
                return this.cache.get(str);
            }

            @Override // net.bytebuddy.pool.TypePool.CacheProvider
            public Resolution register(String str, Resolution resolution) {
                Resolution resolutionPutIfAbsent = this.cache.putIfAbsent(str, resolution);
                return resolutionPutIfAbsent == null ? resolution : resolutionPutIfAbsent;
            }

            @Override // net.bytebuddy.pool.TypePool.CacheProvider
            public void clear() {
                this.cache.clear();
            }
        }
    }

    public enum Empty implements TypePool {
        INSTANCE;

        @Override // net.bytebuddy.pool.TypePool
        public void clear() {
        }

        @Override // net.bytebuddy.pool.TypePool
        public Resolution describe(String str) {
            return new Resolution.Illegal(str);
        }
    }

    public static abstract class AbstractBase implements TypePool {
        private static final String ARRAY_SYMBOL = "[";
        protected static final Map<String, String> PRIMITIVE_DESCRIPTORS;
        protected static final Map<String, TypeDescription> PRIMITIVE_TYPES;
        protected final CacheProvider cacheProvider;

        protected abstract Resolution doDescribe(String str);

        protected boolean canEqual(Object obj) {
            return obj instanceof AbstractBase;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AbstractBase)) {
                return false;
            }
            AbstractBase abstractBase = (AbstractBase) obj;
            if (!abstractBase.canEqual(this)) {
                return false;
            }
            CacheProvider cacheProvider = this.cacheProvider;
            CacheProvider cacheProvider2 = abstractBase.cacheProvider;
            return cacheProvider != null ? cacheProvider.equals(cacheProvider2) : cacheProvider2 == null;
        }

        public int hashCode() {
            CacheProvider cacheProvider = this.cacheProvider;
            return 59 + (cacheProvider == null ? 43 : cacheProvider.hashCode());
        }

        static {
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            Class[] clsArr = {Boolean.TYPE, Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Void.TYPE};
            for (int i = 0; i < 9; i++) {
                Class cls = clsArr[i];
                map.put(cls.getName(), new TypeDescription.ForLoadedType(cls));
                map2.put(Type.getDescriptor((Class<?>) cls), cls.getName());
            }
            PRIMITIVE_TYPES = Collections.unmodifiableMap(map);
            PRIMITIVE_DESCRIPTORS = Collections.unmodifiableMap(map2);
        }

        protected AbstractBase(CacheProvider cacheProvider) {
            this.cacheProvider = cacheProvider;
        }

        @Override // net.bytebuddy.pool.TypePool
        public Resolution describe(String str) {
            if (str.contains("/")) {
                throw new IllegalArgumentException(str + " contains the illegal character '/'");
            }
            int i = 0;
            while (str.startsWith(ARRAY_SYMBOL)) {
                i++;
                str = str.substring(1);
            }
            if (i > 0) {
                String str2 = PRIMITIVE_DESCRIPTORS.get(str);
                str = str2 == null ? str.substring(1, str.length() - 1) : str2;
            }
            TypeDescription typeDescription = PRIMITIVE_TYPES.get(str);
            Resolution resolutionFind = typeDescription == null ? this.cacheProvider.find(str) : new Resolution.Simple(typeDescription);
            if (resolutionFind == null) {
                resolutionFind = doCache(str, doDescribe(str));
            }
            return ArrayTypeResolution.of(resolutionFind, i);
        }

        protected Resolution doCache(String str, Resolution resolution) {
            return this.cacheProvider.register(str, resolution);
        }

        @Override // net.bytebuddy.pool.TypePool
        public void clear() {
            this.cacheProvider.clear();
        }

        public static abstract class Hierarchical extends AbstractBase {
            private final TypePool parent;

            @Override // net.bytebuddy.pool.TypePool.AbstractBase
            protected boolean canEqual(Object obj) {
                return obj instanceof Hierarchical;
            }

            @Override // net.bytebuddy.pool.TypePool.AbstractBase
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Hierarchical)) {
                    return false;
                }
                Hierarchical hierarchical = (Hierarchical) obj;
                if (!hierarchical.canEqual(this) || !super.equals(obj)) {
                    return false;
                }
                TypePool typePool = this.parent;
                TypePool typePool2 = hierarchical.parent;
                return typePool != null ? typePool.equals(typePool2) : typePool2 == null;
            }

            @Override // net.bytebuddy.pool.TypePool.AbstractBase
            public int hashCode() {
                int iHashCode = super.hashCode() + 59;
                TypePool typePool = this.parent;
                return (iHashCode * 59) + (typePool == null ? 43 : typePool.hashCode());
            }

            protected Hierarchical(CacheProvider cacheProvider, TypePool typePool) {
                super(cacheProvider);
                this.parent = typePool;
            }

            @Override // net.bytebuddy.pool.TypePool.AbstractBase, net.bytebuddy.pool.TypePool
            public Resolution describe(String str) {
                Resolution resolutionDescribe = this.parent.describe(str);
                return resolutionDescribe.isResolved() ? resolutionDescribe : super.describe(str);
            }

            @Override // net.bytebuddy.pool.TypePool.AbstractBase, net.bytebuddy.pool.TypePool
            public void clear() {
                try {
                    this.parent.clear();
                } finally {
                    super.clear();
                }
            }
        }

        protected static class ArrayTypeResolution implements Resolution {
            private final int arity;
            private final Resolution resolution;

            protected boolean canEqual(Object obj) {
                return obj instanceof ArrayTypeResolution;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ArrayTypeResolution)) {
                    return false;
                }
                ArrayTypeResolution arrayTypeResolution = (ArrayTypeResolution) obj;
                if (!arrayTypeResolution.canEqual(this)) {
                    return false;
                }
                Resolution resolution = this.resolution;
                Resolution resolution2 = arrayTypeResolution.resolution;
                if (resolution != null ? resolution.equals(resolution2) : resolution2 == null) {
                    return this.arity == arrayTypeResolution.arity;
                }
                return false;
            }

            public int hashCode() {
                Resolution resolution = this.resolution;
                return (((resolution == null ? 43 : resolution.hashCode()) + 59) * 59) + this.arity;
            }

            protected ArrayTypeResolution(Resolution resolution, int i) {
                this.resolution = resolution;
                this.arity = i;
            }

            protected static Resolution of(Resolution resolution, int i) {
                return i == 0 ? resolution : new ArrayTypeResolution(resolution, i);
            }

            @Override // net.bytebuddy.pool.TypePool.Resolution
            public boolean isResolved() {
                return this.resolution.isResolved();
            }

            @Override // net.bytebuddy.pool.TypePool.Resolution
            public TypeDescription resolve() {
                return TypeDescription.ArrayProjection.of(this.resolution.resolve(), this.arity);
            }
        }

        protected static class RawAnnotationValue extends AnnotationValue.AbstractBase<AnnotationDescription, Annotation> {
            private final Default.LazyTypeDescription.AnnotationToken annotationToken;
            private final TypePool typePool;

            public RawAnnotationValue(TypePool typePool, Default.LazyTypeDescription.AnnotationToken annotationToken) {
                this.typePool = typePool;
                this.annotationToken = annotationToken;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue
            public AnnotationDescription resolve() {
                return this.annotationToken.toAnnotationDescription(this.typePool).resolve();
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue
            public AnnotationValue.Loaded<Annotation> load(ClassLoader classLoader) throws ClassNotFoundException {
                Class<?> cls = Class.forName(this.annotationToken.getBinaryName(), false, classLoader);
                if (cls.isAnnotation()) {
                    return new AnnotationValue.ForAnnotationDescription.Loaded(AnnotationDescription.AnnotationInvocationHandler.of(classLoader, cls, this.annotationToken.getValues()));
                }
                return new AnnotationValue.ForAnnotationDescription.IncompatibleRuntimeType(cls);
            }

            public boolean equals(Object obj) {
                return this == obj || ((obj instanceof AnnotationValue) && resolve().equals(((AnnotationValue) obj).resolve()));
            }

            public int hashCode() {
                return resolve().hashCode();
            }

            public String toString() {
                return resolve().toString();
            }
        }

        protected static class RawEnumerationValue extends AnnotationValue.AbstractBase<EnumerationDescription, Enum<?>> {
            private final String descriptor;
            private final TypePool typePool;
            private final String value;

            public RawEnumerationValue(TypePool typePool, String str, String str2) {
                this.typePool = typePool;
                this.descriptor = str;
                this.value = str2;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue
            public EnumerationDescription resolve() {
                return new LazyEnumerationDescription();
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue
            public AnnotationValue.Loaded<Enum<?>> load(ClassLoader classLoader) throws ClassNotFoundException {
                String str = this.descriptor;
                Class<?> cls = Class.forName(str.substring(1, str.length() - 1).replace(ClassPathElement.SEPARATOR_CHAR, Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH), false, classLoader);
                try {
                    return cls.isEnum() ? new AnnotationValue.ForEnumerationDescription.Loaded(Enum.valueOf(cls, this.value)) : new AnnotationValue.ForEnumerationDescription.IncompatibleRuntimeType(cls);
                } catch (IllegalArgumentException unused) {
                    return new AnnotationValue.ForEnumerationDescription.UnknownRuntimeEnumeration(cls, this.value);
                }
            }

            public boolean equals(Object obj) {
                return this == obj || ((obj instanceof AnnotationValue) && resolve().equals(((AnnotationValue) obj).resolve()));
            }

            public int hashCode() {
                return resolve().hashCode();
            }

            public String toString() {
                return resolve().toString();
            }

            protected class LazyEnumerationDescription extends EnumerationDescription.AbstractBase {
                protected LazyEnumerationDescription() {
                }

                @Override // net.bytebuddy.description.enumeration.EnumerationDescription
                public String getValue() {
                    return RawEnumerationValue.this.value;
                }

                @Override // net.bytebuddy.description.enumeration.EnumerationDescription
                public TypeDescription getEnumerationType() {
                    return RawEnumerationValue.this.typePool.describe(RawEnumerationValue.this.descriptor.substring(1, RawEnumerationValue.this.descriptor.length() - 1).replace(ClassPathElement.SEPARATOR_CHAR, Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH)).resolve();
                }

                @Override // net.bytebuddy.description.enumeration.EnumerationDescription
                public <T extends Enum<T>> T load(Class<T> cls) {
                    return (T) Enum.valueOf(cls, RawEnumerationValue.this.value);
                }
            }
        }

        protected static class RawTypeValue extends AnnotationValue.AbstractBase<TypeDescription, Class<?>> {
            private static final boolean NO_INITIALIZATION = false;
            private final String name;
            private final TypePool typePool;

            protected RawTypeValue(TypePool typePool, Type type) {
                String className;
                this.typePool = typePool;
                if (type.getSort() == 9) {
                    className = type.getInternalName().replace(ClassPathElement.SEPARATOR_CHAR, Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH);
                } else {
                    className = type.getClassName();
                }
                this.name = className;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue
            public TypeDescription resolve() {
                return this.typePool.describe(this.name).resolve();
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue
            public AnnotationValue.Loaded<Class<?>> load(ClassLoader classLoader) throws ClassNotFoundException {
                return new Loaded(Class.forName(this.name, false, classLoader));
            }

            public boolean equals(Object obj) {
                return this == obj || ((obj instanceof AnnotationValue) && resolve().equals(((AnnotationValue) obj).resolve()));
            }

            public int hashCode() {
                return resolve().hashCode();
            }

            public String toString() {
                return AnnotationValue.RenderingDispatcher.CURRENT.toSourceString(resolve());
            }

            protected static class Loaded extends AnnotationValue.Loaded.AbstractBase<Class<?>> {
                private final Class<?> type;

                public Loaded(Class<?> cls) {
                    this.type = cls;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
                public AnnotationValue.Loaded.State getState() {
                    return AnnotationValue.Loaded.State.RESOLVED;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded, net.bytebuddy.description.annotation.AnnotationValue
                public Class<?> resolve() {
                    return this.type;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
                public boolean represents(Object obj) {
                    return this.type.equals(obj);
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof AnnotationValue.Loaded)) {
                        return false;
                    }
                    AnnotationValue.Loaded loaded = (AnnotationValue.Loaded) obj;
                    return loaded.getState().isResolved() && this.type.equals(loaded.resolve());
                }

                public int hashCode() {
                    return this.type.hashCode();
                }

                public String toString() {
                    return AnnotationValue.RenderingDispatcher.CURRENT.toSourceString(new TypeDescription.ForLoadedType(this.type));
                }
            }
        }

        protected static class RawDescriptionArray extends AnnotationValue.AbstractBase<Object[], Object[]> {
            private final ComponentTypeReference componentTypeReference;
            private final TypePool typePool;
            private List<AnnotationValue<?, ?>> values;

            public interface ComponentTypeReference {
                String lookup();
            }

            public RawDescriptionArray(TypePool typePool, ComponentTypeReference componentTypeReference, List<AnnotationValue<?, ?>> list) {
                this.typePool = typePool;
                this.values = list;
                this.componentTypeReference = componentTypeReference;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue
            public Object[] resolve() {
                Class cls;
                TypeDescription typeDescriptionResolve = this.typePool.describe(this.componentTypeReference.lookup()).resolve();
                if (typeDescriptionResolve.represents(Class.class)) {
                    cls = TypeDescription.class;
                } else if (typeDescriptionResolve.isAssignableTo(Enum.class)) {
                    cls = EnumerationDescription.class;
                } else if (typeDescriptionResolve.isAssignableTo(Annotation.class)) {
                    cls = AnnotationDescription.class;
                } else if (typeDescriptionResolve.represents(String.class)) {
                    cls = String.class;
                } else {
                    throw new IllegalStateException("Unexpected complex array component type " + typeDescriptionResolve);
                }
                Object[] objArr = (Object[]) Array.newInstance((Class<?>) cls, this.values.size());
                Iterator<AnnotationValue<?, ?>> it = this.values.iterator();
                int i = 0;
                while (it.hasNext()) {
                    Array.set(objArr, i, it.next().resolve());
                    i++;
                }
                return objArr;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue
            public AnnotationValue.Loaded<Object[]> load(ClassLoader classLoader) throws ClassNotFoundException {
                ArrayList arrayList = new ArrayList(this.values.size());
                Iterator<AnnotationValue<?, ?>> it = this.values.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().load(classLoader));
                }
                return new Loaded(Class.forName(this.componentTypeReference.lookup(), false, classLoader), arrayList);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof AnnotationValue)) {
                    return false;
                }
                Object objResolve = ((AnnotationValue) obj).resolve();
                return (objResolve instanceof Object[]) && Arrays.equals(resolve(), (Object[]) objResolve);
            }

            public int hashCode() {
                return Arrays.hashCode(resolve());
            }

            public String toString() {
                return AnnotationValue.RenderingDispatcher.CURRENT.toSourceString(this.values);
            }

            protected static class Loaded extends AnnotationValue.Loaded.AbstractBase<Object[]> {
                private final Class<?> componentType;
                private final List<AnnotationValue.Loaded<?>> values;

                public Loaded(Class<?> cls, List<AnnotationValue.Loaded<?>> list) {
                    this.componentType = cls;
                    this.values = list;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
                public AnnotationValue.Loaded.State getState() {
                    Iterator<AnnotationValue.Loaded<?>> it = this.values.iterator();
                    while (it.hasNext()) {
                        if (!it.next().getState().isResolved()) {
                            return AnnotationValue.Loaded.State.UNRESOLVED;
                        }
                    }
                    return AnnotationValue.Loaded.State.RESOLVED;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded, net.bytebuddy.description.annotation.AnnotationValue
                public Object[] resolve() {
                    Object[] objArr = (Object[]) Array.newInstance(this.componentType, this.values.size());
                    Iterator<AnnotationValue.Loaded<?>> it = this.values.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        Array.set(objArr, i, it.next().resolve());
                        i++;
                    }
                    return objArr;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
                public boolean represents(Object obj) {
                    if (!(obj instanceof Object[]) || obj.getClass().getComponentType() != this.componentType) {
                        return false;
                    }
                    Object[] objArr = (Object[]) obj;
                    if (this.values.size() != objArr.length) {
                        return false;
                    }
                    Iterator<AnnotationValue.Loaded<?>> it = this.values.iterator();
                    for (Object obj2 : objArr) {
                        AnnotationValue.Loaded<?> next = it.next();
                        if (!next.getState().isResolved() || !next.represents(obj2)) {
                            return false;
                        }
                    }
                    return true;
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof AnnotationValue.Loaded)) {
                        return false;
                    }
                    AnnotationValue.Loaded loaded = (AnnotationValue.Loaded) obj;
                    if (!loaded.getState().isResolved()) {
                        return false;
                    }
                    Object objResolve = loaded.resolve();
                    if (!(objResolve instanceof Object[])) {
                        return false;
                    }
                    Object[] objArr = (Object[]) objResolve;
                    if (this.values.size() != objArr.length) {
                        return false;
                    }
                    Iterator<AnnotationValue.Loaded<?>> it = this.values.iterator();
                    for (Object obj2 : objArr) {
                        if (!it.next().resolve().equals(obj2)) {
                            return false;
                        }
                    }
                    return true;
                }

                public int hashCode() {
                    Iterator<AnnotationValue.Loaded<?>> it = this.values.iterator();
                    int iHashCode = 1;
                    while (it.hasNext()) {
                        iHashCode = (iHashCode * 31) + it.next().hashCode();
                    }
                    return iHashCode;
                }

                public String toString() {
                    return AnnotationValue.RenderingDispatcher.CURRENT.toSourceString(this.values);
                }
            }
        }
    }

    public static class Default extends AbstractBase.Hierarchical {
        private static final MethodVisitor IGNORE_METHOD = null;
        protected final ClassFileLocator classFileLocator;
        protected final ReaderMode readerMode;

        @Override // net.bytebuddy.pool.TypePool.AbstractBase.Hierarchical, net.bytebuddy.pool.TypePool.AbstractBase
        protected boolean canEqual(Object obj) {
            return obj instanceof Default;
        }

        @Override // net.bytebuddy.pool.TypePool.AbstractBase.Hierarchical, net.bytebuddy.pool.TypePool.AbstractBase
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Default)) {
                return false;
            }
            Default r1 = (Default) obj;
            if (!r1.canEqual(this) || !super.equals(obj)) {
                return false;
            }
            ClassFileLocator classFileLocator = this.classFileLocator;
            ClassFileLocator classFileLocator2 = r1.classFileLocator;
            if (classFileLocator != null ? !classFileLocator.equals(classFileLocator2) : classFileLocator2 != null) {
                return false;
            }
            ReaderMode readerMode = this.readerMode;
            ReaderMode readerMode2 = r1.readerMode;
            return readerMode != null ? readerMode.equals(readerMode2) : readerMode2 == null;
        }

        @Override // net.bytebuddy.pool.TypePool.AbstractBase.Hierarchical, net.bytebuddy.pool.TypePool.AbstractBase
        public int hashCode() {
            int iHashCode = super.hashCode() + 59;
            ClassFileLocator classFileLocator = this.classFileLocator;
            int iHashCode2 = (iHashCode * 59) + (classFileLocator == null ? 43 : classFileLocator.hashCode());
            ReaderMode readerMode = this.readerMode;
            return (iHashCode2 * 59) + (readerMode != null ? readerMode.hashCode() : 43);
        }

        public Default(CacheProvider cacheProvider, ClassFileLocator classFileLocator, ReaderMode readerMode) {
            this(cacheProvider, classFileLocator, readerMode, Empty.INSTANCE);
        }

        public Default(CacheProvider cacheProvider, ClassFileLocator classFileLocator, ReaderMode readerMode, TypePool typePool) {
            super(cacheProvider, typePool);
            this.classFileLocator = classFileLocator;
            this.readerMode = readerMode;
        }

        public static TypePool ofClassPath() {
            return of(ClassFileLocator.ForClassLoader.ofClassPath());
        }

        public static TypePool of(ClassLoader classLoader) {
            return of(ClassFileLocator.ForClassLoader.of(classLoader));
        }

        public static TypePool of(ClassFileLocator classFileLocator) {
            return new Default(new CacheProvider.Simple(), classFileLocator, ReaderMode.FAST);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // net.bytebuddy.pool.TypePool.AbstractBase
        public Resolution doDescribe(String str) {
            try {
                ClassFileLocator.Resolution resolutionLocate = this.classFileLocator.locate(str);
                return resolutionLocate.isResolved() ? new Resolution.Simple(parse(resolutionLocate.resolve())) : new Resolution.Illegal(str);
            } catch (IOException e) {
                throw new IllegalStateException("Error while reading class file", e);
            }
        }

        private TypeDescription parse(byte[] bArr) {
            ClassReader classReader = new ClassReader(bArr);
            TypeExtractor typeExtractor = new TypeExtractor();
            classReader.accept(typeExtractor, this.readerMode.getFlags());
            return typeExtractor.toTypeDescription();
        }

        public enum ReaderMode {
            EXTENDED(4),
            FAST(1);

            private final int flags;

            ReaderMode(int i) {
                this.flags = i;
            }

            protected int getFlags() {
                return this.flags;
            }

            public boolean isExtended() {
                return this == EXTENDED;
            }
        }

        public static class WithLazyResolution extends Default {
            @Override // net.bytebuddy.pool.TypePool.AbstractBase
            protected Resolution doCache(String str, Resolution resolution) {
                return resolution;
            }

            public WithLazyResolution(CacheProvider cacheProvider, ClassFileLocator classFileLocator, ReaderMode readerMode) {
                this(cacheProvider, classFileLocator, readerMode, Empty.INSTANCE);
            }

            public WithLazyResolution(CacheProvider cacheProvider, ClassFileLocator classFileLocator, ReaderMode readerMode, TypePool typePool) {
                super(cacheProvider, classFileLocator, readerMode, typePool);
            }

            public static TypePool ofClassPath() {
                return of(ClassFileLocator.ForClassLoader.ofClassPath());
            }

            public static TypePool of(ClassLoader classLoader) {
                return of(ClassFileLocator.ForClassLoader.of(classLoader));
            }

            public static TypePool of(ClassFileLocator classFileLocator) {
                return new WithLazyResolution(new CacheProvider.Simple(), classFileLocator, ReaderMode.FAST);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // net.bytebuddy.pool.TypePool.Default, net.bytebuddy.pool.TypePool.AbstractBase
            public Resolution doDescribe(String str) {
                return new LazyResolution(str);
            }

            protected Resolution doResolve(String str) {
                Resolution resolutionFind = this.cacheProvider.find(str);
                return resolutionFind == null ? this.cacheProvider.register(str, super.doDescribe(str)) : resolutionFind;
            }

            protected class LazyResolution implements Resolution {
                private final String name;

                protected LazyResolution(String str) {
                    this.name = str;
                }

                @Override // net.bytebuddy.pool.TypePool.Resolution
                public boolean isResolved() {
                    return WithLazyResolution.this.doResolve(this.name).isResolved();
                }

                @Override // net.bytebuddy.pool.TypePool.Resolution
                public TypeDescription resolve() {
                    return WithLazyResolution.this.new LazyTypeDescription(this.name);
                }

                private WithLazyResolution getOuter() {
                    return WithLazyResolution.this;
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    LazyResolution lazyResolution = (LazyResolution) obj;
                    return this.name.equals(lazyResolution.name) && getOuter().equals(lazyResolution.getOuter());
                }

                public int hashCode() {
                    return this.name.hashCode() + (getOuter().hashCode() * 31);
                }
            }

            protected class LazyTypeDescription extends TypeDescription.AbstractBase.OfSimpleType.WithDelegation {
                private final String name;

                protected LazyTypeDescription(String str) {
                    this.name = str;
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getName() {
                    return this.name;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.AbstractBase.OfSimpleType.WithDelegation
                protected TypeDescription delegate() {
                    return WithLazyResolution.this.doResolve(this.name).resolve();
                }
            }
        }

        protected interface AnnotationRegistrant {
            void onComplete();

            void register(String str, AnnotationValue<?, ?> annotationValue);

            public static abstract class AbstractBase implements AnnotationRegistrant {
                private final String descriptor;
                private final Map<String, AnnotationValue<?, ?>> values = new HashMap();

                protected abstract List<LazyTypeDescription.AnnotationToken> getTokens();

                protected AbstractBase(String str) {
                    this.descriptor = str;
                }

                @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant
                public void register(String str, AnnotationValue<?, ?> annotationValue) {
                    this.values.put(str, annotationValue);
                }

                @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant
                public void onComplete() {
                    getTokens().add(new LazyTypeDescription.AnnotationToken(this.descriptor, this.values));
                }

                protected static abstract class ForTypeVariable extends AbstractBase {
                    private final String typePath;

                    protected abstract Map<String, List<LazyTypeDescription.AnnotationToken>> getPathMap();

                    protected ForTypeVariable(String str, TypePath typePath) {
                        super(str);
                        this.typePath = typePath == null ? "" : typePath.toString();
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant.AbstractBase
                    protected List<LazyTypeDescription.AnnotationToken> getTokens() {
                        Map<String, List<LazyTypeDescription.AnnotationToken>> pathMap = getPathMap();
                        List<LazyTypeDescription.AnnotationToken> list = pathMap.get(this.typePath);
                        if (list != null) {
                            return list;
                        }
                        ArrayList arrayList = new ArrayList();
                        pathMap.put(this.typePath, arrayList);
                        return arrayList;
                    }

                    protected static abstract class WithIndex extends ForTypeVariable {
                        private final int index;

                        protected abstract Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> getIndexedPathMap();

                        protected WithIndex(String str, TypePath typePath, int i) {
                            super(str, typePath);
                            this.index = i;
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant.AbstractBase.ForTypeVariable
                        protected Map<String, List<LazyTypeDescription.AnnotationToken>> getPathMap() {
                            Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> indexedPathMap = getIndexedPathMap();
                            Map<String, List<LazyTypeDescription.AnnotationToken>> map = indexedPathMap.get(Integer.valueOf(this.index));
                            if (map != null) {
                                return map;
                            }
                            HashMap map2 = new HashMap();
                            indexedPathMap.put(Integer.valueOf(this.index), map2);
                            return map2;
                        }

                        protected static abstract class DoubleIndexed extends WithIndex {
                            private final int preIndex;

                            protected abstract Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> getDoubleIndexedPathMap();

                            protected DoubleIndexed(String str, TypePath typePath, int i, int i2) {
                                super(str, typePath, i);
                                this.preIndex = i2;
                            }

                            @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant.AbstractBase.ForTypeVariable.WithIndex
                            protected Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> getIndexedPathMap() {
                                Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> doubleIndexedPathMap = getDoubleIndexedPathMap();
                                Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> map = doubleIndexedPathMap.get(Integer.valueOf(this.preIndex));
                                if (map != null) {
                                    return map;
                                }
                                HashMap map2 = new HashMap();
                                doubleIndexedPathMap.put(Integer.valueOf(this.preIndex), map2);
                                return map2;
                            }
                        }
                    }
                }
            }

            public static class ForByteCodeElement extends AbstractBase {
                private final List<LazyTypeDescription.AnnotationToken> annotationTokens;

                protected ForByteCodeElement(String str, List<LazyTypeDescription.AnnotationToken> list) {
                    super(str);
                    this.annotationTokens = list;
                }

                @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant.AbstractBase
                protected List<LazyTypeDescription.AnnotationToken> getTokens() {
                    return this.annotationTokens;
                }

                public static class WithIndex extends AbstractBase {
                    private final Map<Integer, List<LazyTypeDescription.AnnotationToken>> annotationTokens;
                    private final int index;

                    protected WithIndex(String str, int i, Map<Integer, List<LazyTypeDescription.AnnotationToken>> map) {
                        super(str);
                        this.index = i;
                        this.annotationTokens = map;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant.AbstractBase
                    protected List<LazyTypeDescription.AnnotationToken> getTokens() {
                        List<LazyTypeDescription.AnnotationToken> list = this.annotationTokens.get(Integer.valueOf(this.index));
                        if (list != null) {
                            return list;
                        }
                        ArrayList arrayList = new ArrayList();
                        this.annotationTokens.put(Integer.valueOf(this.index), arrayList);
                        return arrayList;
                    }
                }
            }

            public static class ForTypeVariable extends AbstractBase.ForTypeVariable {
                private final Map<String, List<LazyTypeDescription.AnnotationToken>> pathMap;

                protected ForTypeVariable(String str, TypePath typePath, Map<String, List<LazyTypeDescription.AnnotationToken>> map) {
                    super(str, typePath);
                    this.pathMap = map;
                }

                @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant.AbstractBase.ForTypeVariable
                protected Map<String, List<LazyTypeDescription.AnnotationToken>> getPathMap() {
                    return this.pathMap;
                }

                public static class WithIndex extends AbstractBase.ForTypeVariable.WithIndex {
                    private final Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> indexedPathMap;

                    protected WithIndex(String str, TypePath typePath, int i, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> map) {
                        super(str, typePath, i);
                        this.indexedPathMap = map;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant.AbstractBase.ForTypeVariable.WithIndex
                    protected Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> getIndexedPathMap() {
                        return this.indexedPathMap;
                    }

                    public static class DoubleIndexed extends AbstractBase.ForTypeVariable.WithIndex.DoubleIndexed {
                        private final Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> doubleIndexedPathMap;

                        protected DoubleIndexed(String str, TypePath typePath, int i, int i2, Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> map) {
                            super(str, typePath, i, i2);
                            this.doubleIndexedPathMap = map;
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant.AbstractBase.ForTypeVariable.WithIndex.DoubleIndexed
                        protected Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> getDoubleIndexedPathMap() {
                            return this.doubleIndexedPathMap;
                        }
                    }
                }
            }
        }

        protected interface ComponentTypeLocator {
            AbstractBase.RawDescriptionArray.ComponentTypeReference bind(String str);

            public enum Illegal implements ComponentTypeLocator {
                INSTANCE;

                @Override // net.bytebuddy.pool.TypePool.Default.ComponentTypeLocator
                public AbstractBase.RawDescriptionArray.ComponentTypeReference bind(String str) {
                    throw new IllegalStateException("Unexpected lookup of component type for " + str);
                }
            }

            public static class ForAnnotationProperty implements ComponentTypeLocator {
                private final String annotationName;
                private final TypePool typePool;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForAnnotationProperty;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForAnnotationProperty)) {
                        return false;
                    }
                    ForAnnotationProperty forAnnotationProperty = (ForAnnotationProperty) obj;
                    if (!forAnnotationProperty.canEqual(this)) {
                        return false;
                    }
                    TypePool typePool = this.typePool;
                    TypePool typePool2 = forAnnotationProperty.typePool;
                    if (typePool != null ? !typePool.equals(typePool2) : typePool2 != null) {
                        return false;
                    }
                    String str = this.annotationName;
                    String str2 = forAnnotationProperty.annotationName;
                    return str != null ? str.equals(str2) : str2 == null;
                }

                public int hashCode() {
                    TypePool typePool = this.typePool;
                    int iHashCode = typePool == null ? 43 : typePool.hashCode();
                    String str = this.annotationName;
                    return ((iHashCode + 59) * 59) + (str != null ? str.hashCode() : 43);
                }

                public ForAnnotationProperty(TypePool typePool, String str) {
                    this.typePool = typePool;
                    this.annotationName = str.substring(1, str.length() - 1).replace(ClassPathElement.SEPARATOR_CHAR, LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH);
                }

                @Override // net.bytebuddy.pool.TypePool.Default.ComponentTypeLocator
                public AbstractBase.RawDescriptionArray.ComponentTypeReference bind(String str) {
                    return new Bound(str);
                }

                protected class Bound implements AbstractBase.RawDescriptionArray.ComponentTypeReference {
                    private final String name;

                    protected Bound(String str) {
                        this.name = str;
                    }

                    @Override // net.bytebuddy.pool.TypePool.AbstractBase.RawDescriptionArray.ComponentTypeReference
                    public String lookup() {
                        return ((MethodDescription.InDefinedShape) ForAnnotationProperty.this.typePool.describe(ForAnnotationProperty.this.annotationName).resolve().getDeclaredMethods().filter(ElementMatchers.named(this.name)).getOnly()).getReturnType().asErasure().getComponentType().getName();
                    }

                    public boolean equals(Object obj) {
                        if (this != obj) {
                            if (obj != null && getClass() == obj.getClass()) {
                                Bound bound = (Bound) obj;
                                if (!this.name.equals(bound.name) || !ForAnnotationProperty.this.equals(bound.getOuter())) {
                                }
                            }
                            return false;
                        }
                        return true;
                    }

                    public int hashCode() {
                        return this.name.hashCode() + (ForAnnotationProperty.this.hashCode() * 31);
                    }

                    private ForAnnotationProperty getOuter() {
                        return ForAnnotationProperty.this;
                    }
                }
            }

            public static class ForArrayType implements ComponentTypeLocator, AbstractBase.RawDescriptionArray.ComponentTypeReference {
                private final String componentType;

                @Override // net.bytebuddy.pool.TypePool.Default.ComponentTypeLocator
                public AbstractBase.RawDescriptionArray.ComponentTypeReference bind(String str) {
                    return this;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForArrayType;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForArrayType)) {
                        return false;
                    }
                    ForArrayType forArrayType = (ForArrayType) obj;
                    if (!forArrayType.canEqual(this)) {
                        return false;
                    }
                    String str = this.componentType;
                    String str2 = forArrayType.componentType;
                    return str != null ? str.equals(str2) : str2 == null;
                }

                public int hashCode() {
                    String str = this.componentType;
                    return 59 + (str == null ? 43 : str.hashCode());
                }

                public ForArrayType(String str) {
                    this.componentType = Type.getMethodType(str).getReturnType().getClassName().substring(0, r3.length() - 2);
                }

                @Override // net.bytebuddy.pool.TypePool.AbstractBase.RawDescriptionArray.ComponentTypeReference
                public String lookup() {
                    return this.componentType;
                }
            }
        }

        protected interface GenericTypeRegistrant {
            void register(LazyTypeDescription.GenericTypeToken genericTypeToken);

            public static class RejectingSignatureVisitor extends SignatureVisitor {
                private static final String MESSAGE = "Unexpected token in generic signature";

                public RejectingSignatureVisitor() {
                    super(Opcodes.ASM6);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public void visitFormalTypeParameter(String str) {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public SignatureVisitor visitClassBound() {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public SignatureVisitor visitInterfaceBound() {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public SignatureVisitor visitSuperclass() {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public SignatureVisitor visitInterface() {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public SignatureVisitor visitParameterType() {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public SignatureVisitor visitReturnType() {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public SignatureVisitor visitExceptionType() {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public void visitBaseType(char c) {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public void visitTypeVariable(String str) {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public SignatureVisitor visitArrayType() {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public void visitClassType(String str) {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public void visitInnerClassType(String str) {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public void visitTypeArgument() {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public SignatureVisitor visitTypeArgument(char c) {
                    throw new IllegalStateException(MESSAGE);
                }

                @Override // net.bytebuddy.jar.asm.signature.SignatureVisitor
                public void visitEnd() {
                    throw new IllegalStateException(MESSAGE);
                }
            }
        }

        protected static class ParameterBag {
            private final Map<Integer, String> parameterRegistry = new HashMap();
            private final Type[] parameterType;

            protected ParameterBag(Type[] typeArr) {
                this.parameterType = typeArr;
            }

            protected void register(int i, String str) {
                this.parameterRegistry.put(Integer.valueOf(i), str);
            }

            protected List<LazyTypeDescription.MethodToken.ParameterToken> resolve(boolean z) {
                int size;
                ArrayList arrayList = new ArrayList(this.parameterType.length);
                if (z) {
                    size = StackSize.ZERO.getSize();
                } else {
                    size = StackSize.SINGLE.getSize();
                }
                for (Type type : this.parameterType) {
                    String str = this.parameterRegistry.get(Integer.valueOf(size));
                    arrayList.add(str == null ? new LazyTypeDescription.MethodToken.ParameterToken() : new LazyTypeDescription.MethodToken.ParameterToken(str));
                    size += type.getSize();
                }
                return arrayList;
            }
        }

        protected static class GenericTypeExtractor extends GenericTypeRegistrant.RejectingSignatureVisitor implements GenericTypeRegistrant {
            private final GenericTypeRegistrant genericTypeRegistrant;
            private IncompleteToken incompleteToken;

            protected GenericTypeExtractor(GenericTypeRegistrant genericTypeRegistrant) {
                this.genericTypeRegistrant = genericTypeRegistrant;
            }

            @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
            public void visitBaseType(char c) {
                this.genericTypeRegistrant.register(LazyTypeDescription.GenericTypeToken.ForPrimitiveType.of(c));
            }

            @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
            public void visitTypeVariable(String str) {
                this.genericTypeRegistrant.register(new LazyTypeDescription.GenericTypeToken.ForTypeVariable(str));
            }

            @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
            public SignatureVisitor visitArrayType() {
                return new GenericTypeExtractor(this);
            }

            @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant
            public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                this.genericTypeRegistrant.register(new LazyTypeDescription.GenericTypeToken.ForGenericArray(genericTypeToken));
            }

            @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
            public void visitClassType(String str) {
                this.incompleteToken = new IncompleteToken.ForTopLevelType(str);
            }

            @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
            public void visitInnerClassType(String str) {
                this.incompleteToken = new IncompleteToken.ForInnerClass(str, this.incompleteToken);
            }

            @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
            public void visitTypeArgument() {
                this.incompleteToken.appendPlaceholder();
            }

            @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
            public SignatureVisitor visitTypeArgument(char c) {
                if (c == '+') {
                    return this.incompleteToken.appendUpperBound();
                }
                if (c == '-') {
                    return this.incompleteToken.appendLowerBound();
                }
                if (c == '=') {
                    return this.incompleteToken.appendDirectBound();
                }
                throw new IllegalArgumentException("Unknown wildcard: " + c);
            }

            @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
            public void visitEnd() {
                this.genericTypeRegistrant.register(this.incompleteToken.toToken());
            }

            protected interface IncompleteToken {
                SignatureVisitor appendDirectBound();

                SignatureVisitor appendLowerBound();

                void appendPlaceholder();

                SignatureVisitor appendUpperBound();

                String getName();

                boolean isParameterized();

                LazyTypeDescription.GenericTypeToken toToken();

                public static abstract class AbstractBase implements IncompleteToken {
                    protected final List<LazyTypeDescription.GenericTypeToken> parameters = new ArrayList();

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeExtractor.IncompleteToken
                    public SignatureVisitor appendDirectBound() {
                        return new GenericTypeExtractor(new ForDirectBound());
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeExtractor.IncompleteToken
                    public SignatureVisitor appendUpperBound() {
                        return new GenericTypeExtractor(new ForUpperBound());
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeExtractor.IncompleteToken
                    public SignatureVisitor appendLowerBound() {
                        return new GenericTypeExtractor(new ForLowerBound());
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeExtractor.IncompleteToken
                    public void appendPlaceholder() {
                        this.parameters.add(LazyTypeDescription.GenericTypeToken.ForUnboundWildcard.INSTANCE);
                    }

                    protected class ForDirectBound implements GenericTypeRegistrant {
                        protected ForDirectBound() {
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant
                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            AbstractBase.this.parameters.add(genericTypeToken);
                        }
                    }

                    protected class ForUpperBound implements GenericTypeRegistrant {
                        protected ForUpperBound() {
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant
                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            AbstractBase.this.parameters.add(new LazyTypeDescription.GenericTypeToken.ForUpperBoundWildcard(genericTypeToken));
                        }
                    }

                    protected class ForLowerBound implements GenericTypeRegistrant {
                        protected ForLowerBound() {
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant
                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            AbstractBase.this.parameters.add(new LazyTypeDescription.GenericTypeToken.ForLowerBoundWildcard(genericTypeToken));
                        }
                    }
                }

                public static class ForTopLevelType extends AbstractBase {
                    private final String internalName;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForTopLevelType;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForTopLevelType)) {
                            return false;
                        }
                        ForTopLevelType forTopLevelType = (ForTopLevelType) obj;
                        if (!forTopLevelType.canEqual(this)) {
                            return false;
                        }
                        String str = this.internalName;
                        String str2 = forTopLevelType.internalName;
                        return str != null ? str.equals(str2) : str2 == null;
                    }

                    public int hashCode() {
                        String str = this.internalName;
                        return 59 + (str == null ? 43 : str.hashCode());
                    }

                    public ForTopLevelType(String str) {
                        this.internalName = str;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeExtractor.IncompleteToken
                    public LazyTypeDescription.GenericTypeToken toToken() {
                        if (isParameterized()) {
                            return new LazyTypeDescription.GenericTypeToken.ForParameterizedType(getName(), this.parameters);
                        }
                        return new LazyTypeDescription.GenericTypeToken.ForRawType(getName());
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeExtractor.IncompleteToken
                    public boolean isParameterized() {
                        return !this.parameters.isEmpty();
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeExtractor.IncompleteToken
                    public String getName() {
                        return this.internalName.replace(ClassPathElement.SEPARATOR_CHAR, LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH);
                    }
                }

                public static class ForInnerClass extends AbstractBase {
                    private static final char INNER_CLASS_SEPARATOR = '$';
                    private final String internalName;
                    private final IncompleteToken outerTypeToken;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForInnerClass;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForInnerClass)) {
                            return false;
                        }
                        ForInnerClass forInnerClass = (ForInnerClass) obj;
                        if (!forInnerClass.canEqual(this)) {
                            return false;
                        }
                        String str = this.internalName;
                        String str2 = forInnerClass.internalName;
                        if (str != null ? !str.equals(str2) : str2 != null) {
                            return false;
                        }
                        IncompleteToken incompleteToken = this.outerTypeToken;
                        IncompleteToken incompleteToken2 = forInnerClass.outerTypeToken;
                        return incompleteToken != null ? incompleteToken.equals(incompleteToken2) : incompleteToken2 == null;
                    }

                    public int hashCode() {
                        String str = this.internalName;
                        int iHashCode = str == null ? 43 : str.hashCode();
                        IncompleteToken incompleteToken = this.outerTypeToken;
                        return ((iHashCode + 59) * 59) + (incompleteToken != null ? incompleteToken.hashCode() : 43);
                    }

                    public ForInnerClass(String str, IncompleteToken incompleteToken) {
                        this.internalName = str;
                        this.outerTypeToken = incompleteToken;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeExtractor.IncompleteToken
                    public LazyTypeDescription.GenericTypeToken toToken() {
                        if (isParameterized() || this.outerTypeToken.isParameterized()) {
                            return new LazyTypeDescription.GenericTypeToken.ForParameterizedType.Nested(getName(), this.parameters, this.outerTypeToken.toToken());
                        }
                        return new LazyTypeDescription.GenericTypeToken.ForRawType(getName());
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeExtractor.IncompleteToken
                    public boolean isParameterized() {
                        return (this.parameters.isEmpty() && this.outerTypeToken.isParameterized()) ? false : true;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeExtractor.IncompleteToken
                    public String getName() {
                        return this.outerTypeToken.getName() + '$' + this.internalName.replace(ClassPathElement.SEPARATOR_CHAR, LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH);
                    }
                }
            }

            protected static abstract class ForSignature<T extends LazyTypeDescription.GenericTypeToken.Resolution> extends GenericTypeRegistrant.RejectingSignatureVisitor implements GenericTypeRegistrant {
                protected List<LazyTypeDescription.GenericTypeToken> currentBounds;
                protected String currentTypeParameter;
                protected final List<LazyTypeDescription.GenericTypeToken.OfFormalTypeVariable> typeVariableTokens = new ArrayList();

                public abstract T resolve();

                protected static <S extends LazyTypeDescription.GenericTypeToken.Resolution> S extract(String str, ForSignature<S> forSignature) {
                    new SignatureReader(str).accept(forSignature);
                    return (S) forSignature.resolve();
                }

                @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
                public void visitFormalTypeParameter(String str) {
                    collectTypeParameter();
                    this.currentTypeParameter = str;
                    this.currentBounds = new ArrayList();
                }

                @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
                public SignatureVisitor visitClassBound() {
                    return new GenericTypeExtractor(this);
                }

                @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
                public SignatureVisitor visitInterfaceBound() {
                    return new GenericTypeExtractor(this);
                }

                @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant
                public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                    List<LazyTypeDescription.GenericTypeToken> list = this.currentBounds;
                    if (list == null) {
                        throw new IllegalStateException("Did not expect " + genericTypeToken + " before finding formal parameter");
                    }
                    list.add(genericTypeToken);
                }

                protected void collectTypeParameter() {
                    if (this.currentTypeParameter != null) {
                        this.typeVariableTokens.add(new LazyTypeDescription.GenericTypeToken.ForTypeVariable.Formal(this.currentTypeParameter, this.currentBounds));
                    }
                }

                protected static class OfType extends ForSignature<LazyTypeDescription.GenericTypeToken.Resolution.ForType> {
                    private final List<LazyTypeDescription.GenericTypeToken> interfaceTypeTokens = new ArrayList();
                    private LazyTypeDescription.GenericTypeToken superClassToken;

                    protected OfType() {
                    }

                    public static LazyTypeDescription.GenericTypeToken.Resolution.ForType extract(String str) {
                        LazyTypeDescription.GenericTypeToken.Resolution.ForType forType;
                        try {
                            if (str == null) {
                                forType = LazyTypeDescription.GenericTypeToken.Resolution.Raw.INSTANCE;
                            } else {
                                forType = (LazyTypeDescription.GenericTypeToken.Resolution.ForType) ForSignature.extract(str, new OfType());
                            }
                            return forType;
                        } catch (RuntimeException unused) {
                            return LazyTypeDescription.GenericTypeToken.Resolution.Malformed.INSTANCE;
                        }
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
                    public SignatureVisitor visitSuperclass() {
                        collectTypeParameter();
                        return new GenericTypeExtractor(new SuperClassRegistrant());
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
                    public SignatureVisitor visitInterface() {
                        return new GenericTypeExtractor(new InterfaceTypeRegistrant());
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeExtractor.ForSignature
                    public LazyTypeDescription.GenericTypeToken.Resolution.ForType resolve() {
                        return new LazyTypeDescription.GenericTypeToken.Resolution.ForType.Tokenized(this.superClassToken, this.interfaceTypeTokens, this.typeVariableTokens);
                    }

                    protected class SuperClassRegistrant implements GenericTypeRegistrant {
                        protected SuperClassRegistrant() {
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant
                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            OfType.this.superClassToken = genericTypeToken;
                        }

                        public int hashCode() {
                            return OfType.this.hashCode();
                        }

                        public boolean equals(Object obj) {
                            return obj != null && getClass() == obj.getClass() && OfType.this.equals(((SuperClassRegistrant) obj).getOuter());
                        }

                        private OfType getOuter() {
                            return OfType.this;
                        }
                    }

                    protected class InterfaceTypeRegistrant implements GenericTypeRegistrant {
                        protected InterfaceTypeRegistrant() {
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant
                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            OfType.this.interfaceTypeTokens.add(genericTypeToken);
                        }

                        public int hashCode() {
                            return OfType.this.hashCode();
                        }

                        public boolean equals(Object obj) {
                            return obj != null && getClass() == obj.getClass() && OfType.this.equals(((InterfaceTypeRegistrant) obj).getOuter());
                        }

                        private OfType getOuter() {
                            return OfType.this;
                        }
                    }
                }

                protected static class OfMethod extends ForSignature<LazyTypeDescription.GenericTypeToken.Resolution.ForMethod> {
                    private LazyTypeDescription.GenericTypeToken returnTypeToken;
                    private final List<LazyTypeDescription.GenericTypeToken> parameterTypeTokens = new ArrayList();
                    private final List<LazyTypeDescription.GenericTypeToken> exceptionTypeTokens = new ArrayList();

                    public static LazyTypeDescription.GenericTypeToken.Resolution.ForMethod extract(String str) {
                        LazyTypeDescription.GenericTypeToken.Resolution.ForMethod forMethod;
                        try {
                            if (str == null) {
                                forMethod = LazyTypeDescription.GenericTypeToken.Resolution.Raw.INSTANCE;
                            } else {
                                forMethod = (LazyTypeDescription.GenericTypeToken.Resolution.ForMethod) ForSignature.extract(str, new OfMethod());
                            }
                            return forMethod;
                        } catch (RuntimeException unused) {
                            return LazyTypeDescription.GenericTypeToken.Resolution.Malformed.INSTANCE;
                        }
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
                    public SignatureVisitor visitParameterType() {
                        return new GenericTypeExtractor(new ParameterTypeRegistrant());
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
                    public SignatureVisitor visitReturnType() {
                        collectTypeParameter();
                        return new GenericTypeExtractor(new ReturnTypeTypeRegistrant());
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant.RejectingSignatureVisitor, net.bytebuddy.jar.asm.signature.SignatureVisitor
                    public SignatureVisitor visitExceptionType() {
                        return new GenericTypeExtractor(new ExceptionTypeRegistrant());
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeExtractor.ForSignature
                    public LazyTypeDescription.GenericTypeToken.Resolution.ForMethod resolve() {
                        return new LazyTypeDescription.GenericTypeToken.Resolution.ForMethod.Tokenized(this.returnTypeToken, this.parameterTypeTokens, this.exceptionTypeTokens, this.typeVariableTokens);
                    }

                    protected class ParameterTypeRegistrant implements GenericTypeRegistrant {
                        protected ParameterTypeRegistrant() {
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant
                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            OfMethod.this.parameterTypeTokens.add(genericTypeToken);
                        }

                        public int hashCode() {
                            return OfMethod.this.hashCode();
                        }

                        public boolean equals(Object obj) {
                            return obj != null && getClass() == obj.getClass() && OfMethod.this.equals(((ParameterTypeRegistrant) obj).getOuter());
                        }

                        private OfMethod getOuter() {
                            return OfMethod.this;
                        }
                    }

                    protected class ReturnTypeTypeRegistrant implements GenericTypeRegistrant {
                        protected ReturnTypeTypeRegistrant() {
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant
                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            OfMethod.this.returnTypeToken = genericTypeToken;
                        }

                        public int hashCode() {
                            return OfMethod.this.hashCode();
                        }

                        public boolean equals(Object obj) {
                            return obj != null && getClass() == obj.getClass() && OfMethod.this.equals(((ReturnTypeTypeRegistrant) obj).getOuter());
                        }

                        private OfMethod getOuter() {
                            return OfMethod.this;
                        }
                    }

                    protected class ExceptionTypeRegistrant implements GenericTypeRegistrant {
                        protected ExceptionTypeRegistrant() {
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant
                        public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                            OfMethod.this.exceptionTypeTokens.add(genericTypeToken);
                        }

                        public int hashCode() {
                            return OfMethod.this.hashCode();
                        }

                        public boolean equals(Object obj) {
                            return obj != null && getClass() == obj.getClass() && OfMethod.this.equals(((ExceptionTypeRegistrant) obj).getOuter());
                        }

                        private OfMethod getOuter() {
                            return OfMethod.this;
                        }
                    }
                }

                protected static class OfField implements GenericTypeRegistrant {
                    private LazyTypeDescription.GenericTypeToken fieldTypeToken;

                    protected OfField() {
                    }

                    public static LazyTypeDescription.GenericTypeToken.Resolution.ForField extract(String str) {
                        if (str == null) {
                            return LazyTypeDescription.GenericTypeToken.Resolution.Raw.INSTANCE;
                        }
                        SignatureReader signatureReader = new SignatureReader(str);
                        OfField ofField = new OfField();
                        try {
                            signatureReader.acceptType(new GenericTypeExtractor(ofField));
                            return ofField.resolve();
                        } catch (RuntimeException unused) {
                            return LazyTypeDescription.GenericTypeToken.Resolution.Malformed.INSTANCE;
                        }
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.GenericTypeRegistrant
                    public void register(LazyTypeDescription.GenericTypeToken genericTypeToken) {
                        this.fieldTypeToken = genericTypeToken;
                    }

                    protected LazyTypeDescription.GenericTypeToken.Resolution.ForField resolve() {
                        return new LazyTypeDescription.GenericTypeToken.Resolution.ForField.Tokenized(this.fieldTypeToken);
                    }
                }
            }
        }

        protected static class LazyTypeDescription extends TypeDescription.AbstractBase.OfSimpleType {
            private static final String NO_TYPE = null;
            private static final int SUPER_CLASS_INDEX = -1;
            private final int actualModifiers;
            private final List<AnnotationToken> annotationTokens;
            private final boolean anonymousType;
            private final List<String> declaredTypes;
            private final String declaringTypeName;
            private final List<FieldToken> fieldTokens;
            private final String genericSignature;
            private final List<String> interfaceTypeDescriptors;
            private final List<MethodToken> methodTokens;
            private final int modifiers;
            private final String name;
            private final GenericTypeToken.Resolution.ForType signatureResolution;
            private final String superClassDescriptor;
            private final Map<Integer, Map<String, List<AnnotationToken>>> superTypeAnnotationTokens;
            private final TypeContainment typeContainment;
            private final TypePool typePool;
            private final Map<Integer, Map<String, List<AnnotationToken>>> typeVariableAnnotationTokens;
            private final Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> typeVariableBoundsAnnotationTokens;

            protected LazyTypeDescription(TypePool typePool, int i, int i2, String str, String str2, String[] strArr, String str3, TypeContainment typeContainment, String str4, List<String> list, boolean z, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<String, List<AnnotationToken>>> map2, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map3, List<AnnotationToken> list2, List<FieldToken> list3, List<MethodToken> list4) {
                String descriptor;
                String strReplace;
                this.typePool = typePool;
                this.actualModifiers = i & (-33);
                this.modifiers = (-131105) & i2;
                this.name = Type.getObjectType(str).getClassName();
                if (str2 == null) {
                    descriptor = NO_TYPE;
                } else {
                    descriptor = Type.getObjectType(str2).getDescriptor();
                }
                this.superClassDescriptor = descriptor;
                this.genericSignature = str3;
                this.signatureResolution = RAW_TYPES ? GenericTypeToken.Resolution.Raw.INSTANCE : GenericTypeExtractor.ForSignature.OfType.extract(str3);
                if (strArr == null) {
                    this.interfaceTypeDescriptors = Collections.emptyList();
                } else {
                    this.interfaceTypeDescriptors = new ArrayList(strArr.length);
                    for (String str5 : strArr) {
                        this.interfaceTypeDescriptors.add(Type.getObjectType(str5).getDescriptor());
                    }
                }
                this.typeContainment = typeContainment;
                if (str4 == null) {
                    strReplace = NO_TYPE;
                } else {
                    strReplace = str4.replace(ClassPathElement.SEPARATOR_CHAR, GenericTypeToken.INNER_CLASS_PATH);
                }
                this.declaringTypeName = strReplace;
                this.declaredTypes = list;
                this.anonymousType = z;
                this.superTypeAnnotationTokens = map;
                this.typeVariableAnnotationTokens = map2;
                this.typeVariableBoundsAnnotationTokens = map3;
                this.annotationTokens = list2;
                this.fieldTokens = list3;
                this.methodTokens = list4;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeDescription.Generic getSuperClass() {
                if (this.superClassDescriptor == null || isInterface()) {
                    return TypeDescription.Generic.UNDEFINED;
                }
                return this.signatureResolution.resolveSuperClass(this.superClassDescriptor, this.typePool, this.superTypeAnnotationTokens.get(-1), this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeList.Generic getInterfaces() {
                return this.signatureResolution.resolveInterfaceTypes(this.interfaceTypeDescriptors, this.typePool, this.superTypeAnnotationTokens, this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription
            public MethodDescription getEnclosingMethod() {
                return this.typeContainment.getEnclosingMethod(this.typePool);
            }

            @Override // net.bytebuddy.description.type.TypeDescription
            public TypeDescription getEnclosingType() {
                return this.typeContainment.getEnclosingType(this.typePool);
            }

            @Override // net.bytebuddy.description.type.TypeDescription
            public TypeList getDeclaredTypes() {
                return new LazyTypeList(this.typePool, this.declaredTypes);
            }

            @Override // net.bytebuddy.description.type.TypeDescription
            public boolean isAnonymousClass() {
                return this.anonymousType;
            }

            @Override // net.bytebuddy.description.type.TypeDescription
            public boolean isLocalClass() {
                return !this.anonymousType && this.typeContainment.isLocalType();
            }

            @Override // net.bytebuddy.description.type.TypeDescription
            public boolean isMemberClass() {
                return this.typeContainment.isMemberClass();
            }

            @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
            public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
                return new FieldTokenList();
            }

            @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
            public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
                return new MethodTokenList();
            }

            @Override // net.bytebuddy.description.type.TypeDescription
            public PackageDescription getPackage() {
                String name = getName();
                int iLastIndexOf = name.lastIndexOf(46);
                if (iLastIndexOf == -1) {
                    return PackageDescription.UNDEFINED;
                }
                return new LazyPackageDescription(this.typePool, name.substring(0, iLastIndexOf));
            }

            @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
            public String getName() {
                return this.name;
            }

            @Override // net.bytebuddy.description.DeclaredByType
            public TypeDescription getDeclaringType() {
                String str = this.declaringTypeName;
                return str == null ? TypeDescription.UNDEFINED : this.typePool.describe(str).resolve();
            }

            @Override // net.bytebuddy.description.ModifierReviewable
            public int getModifiers() {
                return this.modifiers;
            }

            @Override // net.bytebuddy.description.type.TypeDescription.AbstractBase, net.bytebuddy.description.type.TypeDescription
            public int getActualModifiers(boolean z) {
                return z ? this.actualModifiers | 32 : this.actualModifiers;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationSource
            public AnnotationList getDeclaredAnnotations() {
                return LazyAnnotationDescription.asList(this.typePool, this.annotationTokens);
            }

            @Override // net.bytebuddy.description.TypeVariableSource
            public TypeList.Generic getTypeVariables() {
                return this.signatureResolution.resolveTypeVariables(this.typePool, this, this.typeVariableAnnotationTokens, this.typeVariableBoundsAnnotationTokens);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.AbstractBase, net.bytebuddy.description.ByteCodeElement
            public String getGenericSignature() {
                return this.genericSignature;
            }

            protected class FieldTokenList extends FieldList.AbstractBase<FieldDescription.InDefinedShape> {
                protected FieldTokenList() {
                }

                @Override // java.util.AbstractList, java.util.List
                public FieldDescription.InDefinedShape get(int i) {
                    return ((FieldToken) LazyTypeDescription.this.fieldTokens.get(i)).toFieldDescription(LazyTypeDescription.this);
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return LazyTypeDescription.this.fieldTokens.size();
                }
            }

            protected class MethodTokenList extends MethodList.AbstractBase<MethodDescription.InDefinedShape> {
                protected MethodTokenList() {
                }

                @Override // java.util.AbstractList, java.util.List
                public MethodDescription.InDefinedShape get(int i) {
                    return ((MethodToken) LazyTypeDescription.this.methodTokens.get(i)).toMethodDescription(LazyTypeDescription.this);
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return LazyTypeDescription.this.methodTokens.size();
                }
            }

            protected interface TypeContainment {
                MethodDescription getEnclosingMethod(TypePool typePool);

                TypeDescription getEnclosingType(TypePool typePool);

                boolean isLocalType();

                boolean isMemberClass();

                boolean isSelfContained();

                public enum SelfContained implements TypeContainment {
                    INSTANCE;

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public boolean isLocalType() {
                        return false;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public boolean isMemberClass() {
                        return false;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public boolean isSelfContained() {
                        return true;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public MethodDescription getEnclosingMethod(TypePool typePool) {
                        return MethodDescription.UNDEFINED;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public TypeDescription getEnclosingType(TypePool typePool) {
                        return TypeDescription.UNDEFINED;
                    }
                }

                public static class WithinType implements TypeContainment {
                    private final boolean localType;
                    private final String name;

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public boolean isSelfContained() {
                        return false;
                    }

                    protected boolean canEqual(Object obj) {
                        return obj instanceof WithinType;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof WithinType)) {
                            return false;
                        }
                        WithinType withinType = (WithinType) obj;
                        if (!withinType.canEqual(this)) {
                            return false;
                        }
                        String str = this.name;
                        String str2 = withinType.name;
                        if (str != null ? str.equals(str2) : str2 == null) {
                            return isLocalType() == withinType.isLocalType();
                        }
                        return false;
                    }

                    public int hashCode() {
                        String str = this.name;
                        return (((str == null ? 43 : str.hashCode()) + 59) * 59) + (isLocalType() ? 79 : 97);
                    }

                    public WithinType(String str, boolean z) {
                        this.name = str.replace(ClassPathElement.SEPARATOR_CHAR, GenericTypeToken.INNER_CLASS_PATH);
                        this.localType = z;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public MethodDescription getEnclosingMethod(TypePool typePool) {
                        return MethodDescription.UNDEFINED;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public TypeDescription getEnclosingType(TypePool typePool) {
                        return typePool.describe(this.name).resolve();
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public boolean isMemberClass() {
                        return !this.localType;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public boolean isLocalType() {
                        return this.localType;
                    }
                }

                public static class WithinMethod implements TypeContainment {
                    private final String methodDescriptor;
                    private final String methodName;
                    private final String name;

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public boolean isLocalType() {
                        return true;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public boolean isMemberClass() {
                        return false;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public boolean isSelfContained() {
                        return false;
                    }

                    protected boolean canEqual(Object obj) {
                        return obj instanceof WithinMethod;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof WithinMethod)) {
                            return false;
                        }
                        WithinMethod withinMethod = (WithinMethod) obj;
                        if (!withinMethod.canEqual(this)) {
                            return false;
                        }
                        String str = this.name;
                        String str2 = withinMethod.name;
                        if (str != null ? !str.equals(str2) : str2 != null) {
                            return false;
                        }
                        String str3 = this.methodName;
                        String str4 = withinMethod.methodName;
                        if (str3 != null ? !str3.equals(str4) : str4 != null) {
                            return false;
                        }
                        String str5 = this.methodDescriptor;
                        String str6 = withinMethod.methodDescriptor;
                        return str5 != null ? str5.equals(str6) : str6 == null;
                    }

                    public int hashCode() {
                        String str = this.name;
                        int iHashCode = str == null ? 43 : str.hashCode();
                        String str2 = this.methodName;
                        int iHashCode2 = ((iHashCode + 59) * 59) + (str2 == null ? 43 : str2.hashCode());
                        String str3 = this.methodDescriptor;
                        return (iHashCode2 * 59) + (str3 != null ? str3.hashCode() : 43);
                    }

                    public WithinMethod(String str, String str2, String str3) {
                        this.name = str.replace(ClassPathElement.SEPARATOR_CHAR, GenericTypeToken.INNER_CLASS_PATH);
                        this.methodName = str2;
                        this.methodDescriptor = str3;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public MethodDescription getEnclosingMethod(TypePool typePool) {
                        return (MethodDescription) getEnclosingType(typePool).getDeclaredMethods().filter(ElementMatchers.hasMethodName(this.methodName).and(ElementMatchers.hasDescriptor(this.methodDescriptor))).getOnly();
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.TypeContainment
                    public TypeDescription getEnclosingType(TypePool typePool) {
                        return typePool.describe(this.name).resolve();
                    }
                }
            }

            protected interface GenericTypeToken {
                public static final char COMPONENT_TYPE_PATH = '[';
                public static final String EMPTY_TYPE_PATH = "";
                public static final char INDEXED_TYPE_DELIMITER = ';';
                public static final char INNER_CLASS_PATH = '.';
                public static final char WILDCARD_TYPE_PATH = '*';

                public interface OfFormalTypeVariable {
                    TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, Map<String, List<AnnotationToken>> map, Map<Integer, Map<String, List<AnnotationToken>>> map2);
                }

                String getTypePathPrefix();

                boolean isPrimaryBound(TypePool typePool);

                TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map);

                public enum ForPrimitiveType implements GenericTypeToken {
                    BOOLEAN(Boolean.TYPE),
                    BYTE(Byte.TYPE),
                    SHORT(Short.TYPE),
                    CHAR(Character.TYPE),
                    INTEGER(Integer.TYPE),
                    LONG(Long.TYPE),
                    FLOAT(Float.TYPE),
                    DOUBLE(Double.TYPE),
                    VOID(Void.TYPE);

                    private final TypeDescription typeDescription;

                    ForPrimitiveType(Class cls) {
                        this.typeDescription = new TypeDescription.ForLoadedType(cls);
                    }

                    public static GenericTypeToken of(char c) {
                        if (c == 'F') {
                            return FLOAT;
                        }
                        if (c == 'S') {
                            return SHORT;
                        }
                        if (c == 'V') {
                            return VOID;
                        }
                        if (c == 'Z') {
                            return BOOLEAN;
                        }
                        if (c == 'I') {
                            return INTEGER;
                        }
                        if (c != 'J') {
                            switch (c) {
                                case 'B':
                                    return BYTE;
                                case 'C':
                                    return CHAR;
                                case 'D':
                                    return DOUBLE;
                                default:
                                    throw new IllegalArgumentException("Not a valid primitive type descriptor: " + c);
                            }
                        }
                        return LONG;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        if (map == null) {
                            map = Collections.emptyMap();
                        }
                        return new LazyPrimitiveType(typePool, str, map, this.typeDescription);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public boolean isPrimaryBound(TypePool typePool) {
                        throw new IllegalStateException("A primitive type cannot be a type variable bound: " + this);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public String getTypePathPrefix() {
                        throw new IllegalStateException("A primitive type cannot be the owner of a nested type: " + this);
                    }

                    protected static class LazyPrimitiveType extends TypeDescription.Generic.OfNonGenericType {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final TypeDescription typeDescription;
                        private final String typePath;
                        private final TypePool typePool;

                        protected LazyPrimitiveType(TypePool typePool, String str, Map<String, List<AnnotationToken>> map, TypeDescription typeDescription) {
                            this.typePool = typePool;
                            this.typePath = str;
                            this.annotationTokens = map;
                            this.typeDescription = typeDescription;
                        }

                        @Override // net.bytebuddy.description.type.TypeDefinition
                        public TypeDescription asErasure() {
                            return this.typeDescription;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeDescription.Generic getOwnerType() {
                            return TypeDescription.Generic.UNDEFINED;
                        }

                        @Override // net.bytebuddy.description.type.TypeDefinition
                        public TypeDescription.Generic getComponentType() {
                            return TypeDescription.Generic.UNDEFINED;
                        }

                        @Override // net.bytebuddy.description.annotation.AnnotationSource
                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(this.typePath));
                        }
                    }
                }

                public enum ForUnboundWildcard implements GenericTypeToken {
                    INSTANCE;

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        if (map == null) {
                            map = Collections.emptyMap();
                        }
                        return new LazyUnboundWildcard(typePool, str, map);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public boolean isPrimaryBound(TypePool typePool) {
                        throw new IllegalStateException("A wildcard type cannot be a type variable bound: " + this);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public String getTypePathPrefix() {
                        throw new IllegalStateException("An unbound wildcard cannot be the owner of a nested type: " + this);
                    }

                    protected static class LazyUnboundWildcard extends TypeDescription.Generic.OfWildcardType {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final String typePath;
                        private final TypePool typePool;

                        protected LazyUnboundWildcard(TypePool typePool, String str, Map<String, List<AnnotationToken>> map) {
                            this.typePool = typePool;
                            this.typePath = str;
                            this.annotationTokens = map;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeList.Generic getUpperBounds() {
                            return new TypeList.Generic.Explicit(TypeDescription.Generic.OBJECT);
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeList.Generic getLowerBounds() {
                            return new TypeList.Generic.Empty();
                        }

                        @Override // net.bytebuddy.description.annotation.AnnotationSource
                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(this.typePath));
                        }
                    }
                }

                public interface Resolution {
                    TypeList.Generic resolveTypeVariables(TypePool typePool, TypeVariableSource typeVariableSource, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2);

                    public enum Raw implements ForType, ForMethod, ForField {
                        INSTANCE;

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForField
                        public TypeDescription.Generic resolveFieldType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, FieldDescription.InDefinedShape inDefinedShape) {
                            return RawAnnotatedType.of(typePool, map, str);
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForMethod
                        public TypeDescription.Generic resolveReturnType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, MethodDescription.InDefinedShape inDefinedShape) {
                            return RawAnnotatedType.of(typePool, map, str);
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForMethod
                        public TypeList.Generic resolveParameterTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape) {
                            return RawAnnotatedType.LazyRawAnnotatedTypeList.of(typePool, map, list);
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForMethod
                        public TypeList.Generic resolveExceptionTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape) {
                            return RawAnnotatedType.LazyRawAnnotatedTypeList.of(typePool, map, list);
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForType
                        public TypeDescription.Generic resolveSuperClass(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, TypeDescription typeDescription) {
                            return RawAnnotatedType.of(typePool, map, str);
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForType
                        public TypeList.Generic resolveInterfaceTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, TypeDescription typeDescription) {
                            return RawAnnotatedType.LazyRawAnnotatedTypeList.of(typePool, map, list);
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution
                        public TypeList.Generic resolveTypeVariables(TypePool typePool, TypeVariableSource typeVariableSource, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2) {
                            return new TypeList.Generic.Empty();
                        }

                        protected static class RawAnnotatedType extends TypeDescription.Generic.OfNonGenericType {
                            private final Map<String, List<AnnotationToken>> annotationTokens;
                            private final TypeDescription typeDescription;
                            private final String typePath;
                            private final TypePool typePool;

                            protected RawAnnotatedType(TypePool typePool, String str, Map<String, List<AnnotationToken>> map, TypeDescription typeDescription) {
                                this.typePool = typePool;
                                this.typePath = str;
                                this.annotationTokens = map;
                                this.typeDescription = typeDescription;
                            }

                            protected static TypeDescription.Generic of(TypePool typePool, Map<String, List<AnnotationToken>> map, String str) {
                                if (map == null) {
                                    map = Collections.emptyMap();
                                }
                                return new RawAnnotatedType(typePool, "", map, TokenizedGenericType.toErasure(typePool, str));
                            }

                            @Override // net.bytebuddy.description.type.TypeDefinition
                            public TypeDescription asErasure() {
                                return this.typeDescription;
                            }

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic
                            public TypeDescription.Generic getOwnerType() {
                                TypeDescription declaringType = this.typeDescription.getDeclaringType();
                                return declaringType == null ? TypeDescription.Generic.UNDEFINED : new RawAnnotatedType(this.typePool, this.typePath, this.annotationTokens, declaringType);
                            }

                            @Override // net.bytebuddy.description.type.TypeDefinition
                            public TypeDescription.Generic getComponentType() {
                                TypeDescription componentType = this.typeDescription.getComponentType();
                                return componentType == null ? TypeDescription.Generic.UNDEFINED : new RawAnnotatedType(this.typePool, this.typePath + GenericTypeToken.COMPONENT_TYPE_PATH, this.annotationTokens, componentType);
                            }

                            @Override // net.bytebuddy.description.annotation.AnnotationSource
                            public AnnotationList getDeclaredAnnotations() {
                                StringBuilder sb = new StringBuilder(this.typePath);
                                for (int i = 0; i < this.typeDescription.getSegmentCount(); i++) {
                                    sb = sb.append(GenericTypeToken.INNER_CLASS_PATH);
                                }
                                return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(sb.toString()));
                            }

                            protected static class LazyRawAnnotatedTypeList extends TypeList.Generic.AbstractBase {
                                private final Map<Integer, Map<String, List<AnnotationToken>>> annotationTokens;
                                private final List<String> descriptors;
                                private final TypePool typePool;

                                @Override // net.bytebuddy.description.type.TypeList.Generic.AbstractBase, net.bytebuddy.description.type.TypeList.Generic
                                public TypeList.Generic asRawTypes() {
                                    return this;
                                }

                                protected LazyRawAnnotatedTypeList(TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, List<String> list) {
                                    this.typePool = typePool;
                                    this.annotationTokens = map;
                                    this.descriptors = list;
                                }

                                protected static TypeList.Generic of(TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, List<String> list) {
                                    if (map == null) {
                                        map = Collections.emptyMap();
                                    }
                                    return new LazyRawAnnotatedTypeList(typePool, map, list);
                                }

                                @Override // java.util.AbstractList, java.util.List
                                public TypeDescription.Generic get(int i) {
                                    return RawAnnotatedType.of(this.typePool, this.annotationTokens.get(Integer.valueOf(i)), this.descriptors.get(i));
                                }

                                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                                public int size() {
                                    return this.descriptors.size();
                                }

                                @Override // net.bytebuddy.description.type.TypeList.Generic.AbstractBase, net.bytebuddy.description.type.TypeList.Generic
                                public TypeList asErasures() {
                                    return new LazyTypeList(this.typePool, this.descriptors);
                                }

                                @Override // net.bytebuddy.description.type.TypeList.Generic.AbstractBase, net.bytebuddy.description.type.TypeList.Generic
                                public int getStackSize() {
                                    Iterator<String> it = this.descriptors.iterator();
                                    int size = 0;
                                    while (it.hasNext()) {
                                        size += Type.getType(it.next()).getSize();
                                    }
                                    return size;
                                }
                            }
                        }
                    }

                    public enum Malformed implements ForType, ForMethod, ForField {
                        INSTANCE;

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForField
                        public TypeDescription.Generic resolveFieldType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, FieldDescription.InDefinedShape inDefinedShape) {
                            return new TokenizedGenericType.Malformed(typePool, str);
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForMethod
                        public TypeDescription.Generic resolveReturnType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, MethodDescription.InDefinedShape inDefinedShape) {
                            return new TokenizedGenericType.Malformed(typePool, str);
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForMethod
                        public TypeList.Generic resolveParameterTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape) {
                            return new TokenizedGenericType.Malformed.TokenList(typePool, list);
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForMethod
                        public TypeList.Generic resolveExceptionTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape) {
                            return new TokenizedGenericType.Malformed.TokenList(typePool, list);
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForType
                        public TypeDescription.Generic resolveSuperClass(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, TypeDescription typeDescription) {
                            return new TokenizedGenericType.Malformed(typePool, str);
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForType
                        public TypeList.Generic resolveInterfaceTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, TypeDescription typeDescription) {
                            return new TokenizedGenericType.Malformed.TokenList(typePool, list);
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution
                        public TypeList.Generic resolveTypeVariables(TypePool typePool, TypeVariableSource typeVariableSource, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2) {
                            throw new GenericSignatureFormatError();
                        }
                    }

                    public interface ForType extends Resolution {
                        TypeList.Generic resolveInterfaceTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, TypeDescription typeDescription);

                        TypeDescription.Generic resolveSuperClass(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, TypeDescription typeDescription);

                        public static class Tokenized implements ForType {
                            private final List<GenericTypeToken> interfaceTypeTokens;
                            private final GenericTypeToken superClassToken;
                            private final List<OfFormalTypeVariable> typeVariableTokens;

                            protected boolean canEqual(Object obj) {
                                return obj instanceof Tokenized;
                            }

                            public boolean equals(Object obj) {
                                if (obj == this) {
                                    return true;
                                }
                                if (!(obj instanceof Tokenized)) {
                                    return false;
                                }
                                Tokenized tokenized = (Tokenized) obj;
                                if (!tokenized.canEqual(this)) {
                                    return false;
                                }
                                GenericTypeToken genericTypeToken = this.superClassToken;
                                GenericTypeToken genericTypeToken2 = tokenized.superClassToken;
                                if (genericTypeToken != null ? !genericTypeToken.equals(genericTypeToken2) : genericTypeToken2 != null) {
                                    return false;
                                }
                                List<GenericTypeToken> list = this.interfaceTypeTokens;
                                List<GenericTypeToken> list2 = tokenized.interfaceTypeTokens;
                                if (list != null ? !list.equals(list2) : list2 != null) {
                                    return false;
                                }
                                List<OfFormalTypeVariable> list3 = this.typeVariableTokens;
                                List<OfFormalTypeVariable> list4 = tokenized.typeVariableTokens;
                                return list3 != null ? list3.equals(list4) : list4 == null;
                            }

                            public int hashCode() {
                                GenericTypeToken genericTypeToken = this.superClassToken;
                                int iHashCode = genericTypeToken == null ? 43 : genericTypeToken.hashCode();
                                List<GenericTypeToken> list = this.interfaceTypeTokens;
                                int iHashCode2 = ((iHashCode + 59) * 59) + (list == null ? 43 : list.hashCode());
                                List<OfFormalTypeVariable> list2 = this.typeVariableTokens;
                                return (iHashCode2 * 59) + (list2 != null ? list2.hashCode() : 43);
                            }

                            public Tokenized(GenericTypeToken genericTypeToken, List<GenericTypeToken> list, List<OfFormalTypeVariable> list2) {
                                this.superClassToken = genericTypeToken;
                                this.interfaceTypeTokens = list;
                                this.typeVariableTokens = list2;
                            }

                            @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForType
                            public TypeDescription.Generic resolveSuperClass(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, TypeDescription typeDescription) {
                                return TokenizedGenericType.of(typePool, this.superClassToken, str, map, typeDescription);
                            }

                            @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForType
                            public TypeList.Generic resolveInterfaceTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, TypeDescription typeDescription) {
                                return new TokenizedGenericType.TokenList(typePool, this.interfaceTypeTokens, map, list, typeDescription);
                            }

                            @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution
                            public TypeList.Generic resolveTypeVariables(TypePool typePool, TypeVariableSource typeVariableSource, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2) {
                                return new TokenizedGenericType.TypeVariableList(typePool, this.typeVariableTokens, typeVariableSource, map, map2);
                            }
                        }
                    }

                    public interface ForMethod extends Resolution {
                        TypeList.Generic resolveExceptionTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape);

                        TypeList.Generic resolveParameterTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape);

                        TypeDescription.Generic resolveReturnType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, MethodDescription.InDefinedShape inDefinedShape);

                        public static class Tokenized implements ForMethod {
                            private final List<GenericTypeToken> exceptionTypeTokens;
                            private final List<GenericTypeToken> parameterTypeTokens;
                            private final GenericTypeToken returnTypeToken;
                            private final List<OfFormalTypeVariable> typeVariableTokens;

                            protected boolean canEqual(Object obj) {
                                return obj instanceof Tokenized;
                            }

                            public boolean equals(Object obj) {
                                if (obj == this) {
                                    return true;
                                }
                                if (!(obj instanceof Tokenized)) {
                                    return false;
                                }
                                Tokenized tokenized = (Tokenized) obj;
                                if (!tokenized.canEqual(this)) {
                                    return false;
                                }
                                GenericTypeToken genericTypeToken = this.returnTypeToken;
                                GenericTypeToken genericTypeToken2 = tokenized.returnTypeToken;
                                if (genericTypeToken != null ? !genericTypeToken.equals(genericTypeToken2) : genericTypeToken2 != null) {
                                    return false;
                                }
                                List<GenericTypeToken> list = this.parameterTypeTokens;
                                List<GenericTypeToken> list2 = tokenized.parameterTypeTokens;
                                if (list != null ? !list.equals(list2) : list2 != null) {
                                    return false;
                                }
                                List<GenericTypeToken> list3 = this.exceptionTypeTokens;
                                List<GenericTypeToken> list4 = tokenized.exceptionTypeTokens;
                                if (list3 != null ? !list3.equals(list4) : list4 != null) {
                                    return false;
                                }
                                List<OfFormalTypeVariable> list5 = this.typeVariableTokens;
                                List<OfFormalTypeVariable> list6 = tokenized.typeVariableTokens;
                                return list5 != null ? list5.equals(list6) : list6 == null;
                            }

                            public int hashCode() {
                                GenericTypeToken genericTypeToken = this.returnTypeToken;
                                int iHashCode = genericTypeToken == null ? 43 : genericTypeToken.hashCode();
                                List<GenericTypeToken> list = this.parameterTypeTokens;
                                int iHashCode2 = ((iHashCode + 59) * 59) + (list == null ? 43 : list.hashCode());
                                List<GenericTypeToken> list2 = this.exceptionTypeTokens;
                                int iHashCode3 = (iHashCode2 * 59) + (list2 == null ? 43 : list2.hashCode());
                                List<OfFormalTypeVariable> list3 = this.typeVariableTokens;
                                return (iHashCode3 * 59) + (list3 != null ? list3.hashCode() : 43);
                            }

                            public Tokenized(GenericTypeToken genericTypeToken, List<GenericTypeToken> list, List<GenericTypeToken> list2, List<OfFormalTypeVariable> list3) {
                                this.returnTypeToken = genericTypeToken;
                                this.parameterTypeTokens = list;
                                this.exceptionTypeTokens = list2;
                                this.typeVariableTokens = list3;
                            }

                            @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForMethod
                            public TypeDescription.Generic resolveReturnType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, MethodDescription.InDefinedShape inDefinedShape) {
                                return TokenizedGenericType.of(typePool, this.returnTypeToken, str, map, inDefinedShape);
                            }

                            @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForMethod
                            public TypeList.Generic resolveParameterTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape) {
                                return new TokenizedGenericType.TokenList(typePool, this.parameterTypeTokens, map, list, inDefinedShape);
                            }

                            @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForMethod
                            public TypeList.Generic resolveExceptionTypes(List<String> list, TypePool typePool, Map<Integer, Map<String, List<AnnotationToken>>> map, MethodDescription.InDefinedShape inDefinedShape) {
                                return this.exceptionTypeTokens.isEmpty() ? Raw.INSTANCE.resolveExceptionTypes(list, typePool, map, inDefinedShape) : new TokenizedGenericType.TokenList(typePool, this.exceptionTypeTokens, map, list, inDefinedShape);
                            }

                            @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution
                            public TypeList.Generic resolveTypeVariables(TypePool typePool, TypeVariableSource typeVariableSource, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2) {
                                return new TokenizedGenericType.TypeVariableList(typePool, this.typeVariableTokens, typeVariableSource, map, map2);
                            }
                        }
                    }

                    public interface ForField {
                        TypeDescription.Generic resolveFieldType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, FieldDescription.InDefinedShape inDefinedShape);

                        public static class Tokenized implements ForField {
                            private final GenericTypeToken fieldTypeToken;

                            protected boolean canEqual(Object obj) {
                                return obj instanceof Tokenized;
                            }

                            public boolean equals(Object obj) {
                                if (obj == this) {
                                    return true;
                                }
                                if (!(obj instanceof Tokenized)) {
                                    return false;
                                }
                                Tokenized tokenized = (Tokenized) obj;
                                if (!tokenized.canEqual(this)) {
                                    return false;
                                }
                                GenericTypeToken genericTypeToken = this.fieldTypeToken;
                                GenericTypeToken genericTypeToken2 = tokenized.fieldTypeToken;
                                return genericTypeToken != null ? genericTypeToken.equals(genericTypeToken2) : genericTypeToken2 == null;
                            }

                            public int hashCode() {
                                GenericTypeToken genericTypeToken = this.fieldTypeToken;
                                return 59 + (genericTypeToken == null ? 43 : genericTypeToken.hashCode());
                            }

                            public Tokenized(GenericTypeToken genericTypeToken) {
                                this.fieldTypeToken = genericTypeToken;
                            }

                            @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.ForField
                            public TypeDescription.Generic resolveFieldType(String str, TypePool typePool, Map<String, List<AnnotationToken>> map, FieldDescription.InDefinedShape inDefinedShape) {
                                return TokenizedGenericType.of(typePool, this.fieldTypeToken, str, map, inDefinedShape.getDeclaringType());
                            }
                        }
                    }
                }

                public static class ForRawType implements GenericTypeToken {
                    private final String name;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForRawType;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForRawType)) {
                            return false;
                        }
                        ForRawType forRawType = (ForRawType) obj;
                        if (!forRawType.canEqual(this)) {
                            return false;
                        }
                        String str = this.name;
                        String str2 = forRawType.name;
                        return str != null ? str.equals(str2) : str2 == null;
                    }

                    public int hashCode() {
                        String str = this.name;
                        return 59 + (str == null ? 43 : str.hashCode());
                    }

                    protected ForRawType(String str) {
                        this.name = str;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        if (map == null) {
                            map = Collections.emptyMap();
                        }
                        return new Resolution.Raw.RawAnnotatedType(typePool, str, map, typePool.describe(this.name).resolve());
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public boolean isPrimaryBound(TypePool typePool) {
                        return !typePool.describe(this.name).resolve().isInterface();
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public String getTypePathPrefix() {
                        throw new IllegalStateException("A non-generic type cannot be the owner of a nested type: " + this);
                    }
                }

                public static class ForTypeVariable implements GenericTypeToken {
                    private final String symbol;

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public boolean isPrimaryBound(TypePool typePool) {
                        return true;
                    }

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForTypeVariable;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForTypeVariable)) {
                            return false;
                        }
                        ForTypeVariable forTypeVariable = (ForTypeVariable) obj;
                        if (!forTypeVariable.canEqual(this)) {
                            return false;
                        }
                        String str = this.symbol;
                        String str2 = forTypeVariable.symbol;
                        return str != null ? str.equals(str2) : str2 == null;
                    }

                    public int hashCode() {
                        String str = this.symbol;
                        return 59 + (str == null ? 43 : str.hashCode());
                    }

                    protected ForTypeVariable(String str) {
                        this.symbol = str;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        TypeDescription.Generic genericFindVariable = typeVariableSource.findVariable(this.symbol);
                        if (genericFindVariable == null) {
                            return new UnresolvedTypeVariable(typeVariableSource, typePool, this.symbol, map.get(str));
                        }
                        return new AnnotatedTypeVariable(typePool, map.get(str), genericFindVariable);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public String getTypePathPrefix() {
                        throw new IllegalStateException("A type variable cannot be the owner of a nested type: " + this);
                    }

                    protected static class AnnotatedTypeVariable extends TypeDescription.Generic.OfTypeVariable {
                        private final List<AnnotationToken> annotationTokens;
                        private final TypePool typePool;
                        private final TypeDescription.Generic typeVariable;

                        protected AnnotatedTypeVariable(TypePool typePool, List<AnnotationToken> list, TypeDescription.Generic generic) {
                            this.typePool = typePool;
                            this.annotationTokens = list;
                            this.typeVariable = generic;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeList.Generic getUpperBounds() {
                            return this.typeVariable.getUpperBounds();
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeVariableSource getTypeVariableSource() {
                            return this.typeVariable.getTypeVariableSource();
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public String getSymbol() {
                            return this.typeVariable.getSymbol();
                        }

                        @Override // net.bytebuddy.description.annotation.AnnotationSource
                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens);
                        }
                    }

                    protected static class UnresolvedTypeVariable extends TypeDescription.Generic.OfTypeVariable {
                        private final List<AnnotationToken> annotationTokens;
                        private final String symbol;
                        private final TypePool typePool;
                        private final TypeVariableSource typeVariableSource;

                        protected UnresolvedTypeVariable(TypeVariableSource typeVariableSource, TypePool typePool, String str, List<AnnotationToken> list) {
                            this.typeVariableSource = typeVariableSource;
                            this.typePool = typePool;
                            this.symbol = str;
                            this.annotationTokens = list;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeList.Generic getUpperBounds() {
                            throw new IllegalStateException("Cannot resolve bounds of unresolved type variable " + this + " by " + this.typeVariableSource);
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeVariableSource getTypeVariableSource() {
                            return this.typeVariableSource;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public String getSymbol() {
                            return this.symbol;
                        }

                        @Override // net.bytebuddy.description.annotation.AnnotationSource
                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens);
                        }
                    }

                    protected static class Formal implements OfFormalTypeVariable {
                        private final List<GenericTypeToken> boundTypeTokens;
                        private final String symbol;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof Formal;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof Formal)) {
                                return false;
                            }
                            Formal formal = (Formal) obj;
                            if (!formal.canEqual(this)) {
                                return false;
                            }
                            String str = this.symbol;
                            String str2 = formal.symbol;
                            if (str != null ? !str.equals(str2) : str2 != null) {
                                return false;
                            }
                            List<GenericTypeToken> list = this.boundTypeTokens;
                            List<GenericTypeToken> list2 = formal.boundTypeTokens;
                            return list != null ? list.equals(list2) : list2 == null;
                        }

                        public int hashCode() {
                            String str = this.symbol;
                            int iHashCode = str == null ? 43 : str.hashCode();
                            List<GenericTypeToken> list = this.boundTypeTokens;
                            return ((iHashCode + 59) * 59) + (list != null ? list.hashCode() : 43);
                        }

                        protected Formal(String str, List<GenericTypeToken> list) {
                            this.symbol = str;
                            this.boundTypeTokens = list;
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.OfFormalTypeVariable
                        public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, Map<String, List<AnnotationToken>> map, Map<Integer, Map<String, List<AnnotationToken>>> map2) {
                            if (map == null) {
                                map = Collections.emptyMap();
                            }
                            Map<String, List<AnnotationToken>> map3 = map;
                            if (map2 == null) {
                                map2 = Collections.emptyMap();
                            }
                            return new LazyTypeVariable(typePool, typeVariableSource, map3, map2, this.symbol, this.boundTypeTokens);
                        }

                        protected static class LazyTypeVariable extends TypeDescription.Generic.OfTypeVariable {
                            private final Map<String, List<AnnotationToken>> annotationTokens;
                            private final List<GenericTypeToken> boundTypeTokens;
                            private final Map<Integer, Map<String, List<AnnotationToken>>> boundaryAnnotationTokens;
                            private final String symbol;
                            private final TypePool typePool;
                            private final TypeVariableSource typeVariableSource;

                            protected LazyTypeVariable(TypePool typePool, TypeVariableSource typeVariableSource, Map<String, List<AnnotationToken>> map, Map<Integer, Map<String, List<AnnotationToken>>> map2, String str, List<GenericTypeToken> list) {
                                this.typePool = typePool;
                                this.typeVariableSource = typeVariableSource;
                                this.annotationTokens = map;
                                this.boundaryAnnotationTokens = map2;
                                this.symbol = str;
                                this.boundTypeTokens = list;
                            }

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic
                            public TypeList.Generic getUpperBounds() {
                                return new LazyBoundTokenList(this.typePool, this.typeVariableSource, this.boundaryAnnotationTokens, this.boundTypeTokens);
                            }

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic
                            public TypeVariableSource getTypeVariableSource() {
                                return this.typeVariableSource;
                            }

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic
                            public String getSymbol() {
                                return this.symbol;
                            }

                            @Override // net.bytebuddy.description.annotation.AnnotationSource
                            public AnnotationList getDeclaredAnnotations() {
                                return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(""));
                            }

                            protected static class LazyBoundTokenList extends TypeList.Generic.AbstractBase {
                                private final Map<Integer, Map<String, List<AnnotationToken>>> annotationTokens;
                                private final List<GenericTypeToken> boundTypeTokens;
                                private final TypePool typePool;
                                private final TypeVariableSource typeVariableSource;

                                protected LazyBoundTokenList(TypePool typePool, TypeVariableSource typeVariableSource, Map<Integer, Map<String, List<AnnotationToken>>> map, List<GenericTypeToken> list) {
                                    this.typePool = typePool;
                                    this.typeVariableSource = typeVariableSource;
                                    this.annotationTokens = map;
                                    this.boundTypeTokens = list;
                                }

                                @Override // java.util.AbstractList, java.util.List
                                public TypeDescription.Generic get(int i) {
                                    Map<String, List<AnnotationToken>> mapEmptyMap;
                                    if (this.annotationTokens.containsKey(Integer.valueOf(i)) || this.annotationTokens.containsKey(Integer.valueOf(i + 1))) {
                                        mapEmptyMap = this.annotationTokens.get(Integer.valueOf((!this.boundTypeTokens.get(0).isPrimaryBound(this.typePool) ? 1 : 0) + i));
                                    } else {
                                        mapEmptyMap = Collections.emptyMap();
                                    }
                                    GenericTypeToken genericTypeToken = this.boundTypeTokens.get(i);
                                    TypePool typePool = this.typePool;
                                    TypeVariableSource typeVariableSource = this.typeVariableSource;
                                    if (mapEmptyMap == null) {
                                        mapEmptyMap = Collections.emptyMap();
                                    }
                                    return genericTypeToken.toGenericType(typePool, typeVariableSource, "", mapEmptyMap);
                                }

                                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                                public int size() {
                                    return this.boundTypeTokens.size();
                                }
                            }
                        }
                    }
                }

                public static class ForGenericArray implements GenericTypeToken {
                    private final GenericTypeToken componentTypeToken;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForGenericArray;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForGenericArray)) {
                            return false;
                        }
                        ForGenericArray forGenericArray = (ForGenericArray) obj;
                        if (!forGenericArray.canEqual(this)) {
                            return false;
                        }
                        GenericTypeToken genericTypeToken = this.componentTypeToken;
                        GenericTypeToken genericTypeToken2 = forGenericArray.componentTypeToken;
                        return genericTypeToken != null ? genericTypeToken.equals(genericTypeToken2) : genericTypeToken2 == null;
                    }

                    public int hashCode() {
                        GenericTypeToken genericTypeToken = this.componentTypeToken;
                        return 59 + (genericTypeToken == null ? 43 : genericTypeToken.hashCode());
                    }

                    protected ForGenericArray(GenericTypeToken genericTypeToken) {
                        this.componentTypeToken = genericTypeToken;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        return new LazyGenericArray(typePool, typeVariableSource, str, map, this.componentTypeToken);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public boolean isPrimaryBound(TypePool typePool) {
                        throw new IllegalStateException("A generic array type cannot be a type variable bound: " + this);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public String getTypePathPrefix() {
                        throw new IllegalStateException("A generic array type cannot be the owner of a nested type: " + this);
                    }

                    protected static class LazyGenericArray extends TypeDescription.Generic.OfGenericArray {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final GenericTypeToken componentTypeToken;
                        private final String typePath;
                        private final TypePool typePool;
                        private final TypeVariableSource typeVariableSource;

                        protected LazyGenericArray(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map, GenericTypeToken genericTypeToken) {
                            this.typePool = typePool;
                            this.typeVariableSource = typeVariableSource;
                            this.typePath = str;
                            this.annotationTokens = map;
                            this.componentTypeToken = genericTypeToken;
                        }

                        @Override // net.bytebuddy.description.type.TypeDefinition
                        public TypeDescription.Generic getComponentType() {
                            return this.componentTypeToken.toGenericType(this.typePool, this.typeVariableSource, this.typePath + GenericTypeToken.COMPONENT_TYPE_PATH, this.annotationTokens);
                        }

                        @Override // net.bytebuddy.description.annotation.AnnotationSource
                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(this.typePath));
                        }
                    }
                }

                public static class ForLowerBoundWildcard implements GenericTypeToken {
                    private final GenericTypeToken boundTypeToken;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForLowerBoundWildcard;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForLowerBoundWildcard)) {
                            return false;
                        }
                        ForLowerBoundWildcard forLowerBoundWildcard = (ForLowerBoundWildcard) obj;
                        if (!forLowerBoundWildcard.canEqual(this)) {
                            return false;
                        }
                        GenericTypeToken genericTypeToken = this.boundTypeToken;
                        GenericTypeToken genericTypeToken2 = forLowerBoundWildcard.boundTypeToken;
                        return genericTypeToken != null ? genericTypeToken.equals(genericTypeToken2) : genericTypeToken2 == null;
                    }

                    public int hashCode() {
                        GenericTypeToken genericTypeToken = this.boundTypeToken;
                        return 59 + (genericTypeToken == null ? 43 : genericTypeToken.hashCode());
                    }

                    protected ForLowerBoundWildcard(GenericTypeToken genericTypeToken) {
                        this.boundTypeToken = genericTypeToken;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        return new LazyLowerBoundWildcard(typePool, typeVariableSource, str, map, this.boundTypeToken);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public boolean isPrimaryBound(TypePool typePool) {
                        throw new IllegalStateException("A wildcard type cannot be a type variable bound: " + this);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public String getTypePathPrefix() {
                        throw new IllegalStateException("A lower bound wildcard cannot be the owner of a nested type: " + this);
                    }

                    protected static class LazyLowerBoundWildcard extends TypeDescription.Generic.OfWildcardType {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final GenericTypeToken boundTypeToken;
                        private final String typePath;
                        private final TypePool typePool;
                        private final TypeVariableSource typeVariableSource;

                        protected LazyLowerBoundWildcard(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map, GenericTypeToken genericTypeToken) {
                            this.typePool = typePool;
                            this.typeVariableSource = typeVariableSource;
                            this.typePath = str;
                            this.annotationTokens = map;
                            this.boundTypeToken = genericTypeToken;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeList.Generic getUpperBounds() {
                            return new TypeList.Generic.Explicit(TypeDescription.Generic.OBJECT);
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeList.Generic getLowerBounds() {
                            return new LazyTokenList.ForWildcardBound(this.typePool, this.typeVariableSource, this.typePath, this.annotationTokens, this.boundTypeToken);
                        }

                        @Override // net.bytebuddy.description.annotation.AnnotationSource
                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(this.typePath));
                        }
                    }
                }

                public static class ForUpperBoundWildcard implements GenericTypeToken {
                    private final GenericTypeToken boundTypeToken;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForUpperBoundWildcard;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForUpperBoundWildcard)) {
                            return false;
                        }
                        ForUpperBoundWildcard forUpperBoundWildcard = (ForUpperBoundWildcard) obj;
                        if (!forUpperBoundWildcard.canEqual(this)) {
                            return false;
                        }
                        GenericTypeToken genericTypeToken = this.boundTypeToken;
                        GenericTypeToken genericTypeToken2 = forUpperBoundWildcard.boundTypeToken;
                        return genericTypeToken != null ? genericTypeToken.equals(genericTypeToken2) : genericTypeToken2 == null;
                    }

                    public int hashCode() {
                        GenericTypeToken genericTypeToken = this.boundTypeToken;
                        return 59 + (genericTypeToken == null ? 43 : genericTypeToken.hashCode());
                    }

                    protected ForUpperBoundWildcard(GenericTypeToken genericTypeToken) {
                        this.boundTypeToken = genericTypeToken;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        return new LazyUpperBoundWildcard(typePool, typeVariableSource, str, map, this.boundTypeToken);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public boolean isPrimaryBound(TypePool typePool) {
                        throw new IllegalStateException("A wildcard type cannot be a type variable bound: " + this);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public String getTypePathPrefix() {
                        throw new IllegalStateException("An upper bound wildcard cannot be the owner of a nested type: " + this);
                    }

                    protected static class LazyUpperBoundWildcard extends TypeDescription.Generic.OfWildcardType {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final GenericTypeToken boundTypeToken;
                        private final String typePath;
                        private final TypePool typePool;
                        private final TypeVariableSource typeVariableSource;

                        protected LazyUpperBoundWildcard(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map, GenericTypeToken genericTypeToken) {
                            this.typePool = typePool;
                            this.typeVariableSource = typeVariableSource;
                            this.typePath = str;
                            this.annotationTokens = map;
                            this.boundTypeToken = genericTypeToken;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeList.Generic getUpperBounds() {
                            return new LazyTokenList.ForWildcardBound(this.typePool, this.typeVariableSource, this.typePath, this.annotationTokens, this.boundTypeToken);
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeList.Generic getLowerBounds() {
                            return new TypeList.Generic.Empty();
                        }

                        @Override // net.bytebuddy.description.annotation.AnnotationSource
                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(this.typePath));
                        }
                    }
                }

                public static class ForParameterizedType implements GenericTypeToken {
                    private final String name;
                    private final List<GenericTypeToken> parameterTypeTokens;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForParameterizedType;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForParameterizedType)) {
                            return false;
                        }
                        ForParameterizedType forParameterizedType = (ForParameterizedType) obj;
                        if (!forParameterizedType.canEqual(this)) {
                            return false;
                        }
                        String str = this.name;
                        String str2 = forParameterizedType.name;
                        if (str != null ? !str.equals(str2) : str2 != null) {
                            return false;
                        }
                        List<GenericTypeToken> list = this.parameterTypeTokens;
                        List<GenericTypeToken> list2 = forParameterizedType.parameterTypeTokens;
                        return list != null ? list.equals(list2) : list2 == null;
                    }

                    public int hashCode() {
                        String str = this.name;
                        int iHashCode = str == null ? 43 : str.hashCode();
                        List<GenericTypeToken> list = this.parameterTypeTokens;
                        return ((iHashCode + 59) * 59) + (list != null ? list.hashCode() : 43);
                    }

                    protected ForParameterizedType(String str, List<GenericTypeToken> list) {
                        this.name = str;
                        this.parameterTypeTokens = list;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                        return new LazyParameterizedType(typePool, typeVariableSource, str, map, this.name, this.parameterTypeTokens);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public boolean isPrimaryBound(TypePool typePool) {
                        return !typePool.describe(this.name).resolve().isInterface();
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                    public String getTypePathPrefix() {
                        return String.valueOf(GenericTypeToken.INNER_CLASS_PATH);
                    }

                    public static class Nested implements GenericTypeToken {
                        private final String name;
                        private final GenericTypeToken ownerTypeToken;
                        private final List<GenericTypeToken> parameterTypeTokens;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof Nested;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof Nested)) {
                                return false;
                            }
                            Nested nested = (Nested) obj;
                            if (!nested.canEqual(this)) {
                                return false;
                            }
                            String str = this.name;
                            String str2 = nested.name;
                            if (str != null ? !str.equals(str2) : str2 != null) {
                                return false;
                            }
                            List<GenericTypeToken> list = this.parameterTypeTokens;
                            List<GenericTypeToken> list2 = nested.parameterTypeTokens;
                            if (list != null ? !list.equals(list2) : list2 != null) {
                                return false;
                            }
                            GenericTypeToken genericTypeToken = this.ownerTypeToken;
                            GenericTypeToken genericTypeToken2 = nested.ownerTypeToken;
                            return genericTypeToken != null ? genericTypeToken.equals(genericTypeToken2) : genericTypeToken2 == null;
                        }

                        public int hashCode() {
                            String str = this.name;
                            int iHashCode = str == null ? 43 : str.hashCode();
                            List<GenericTypeToken> list = this.parameterTypeTokens;
                            int iHashCode2 = ((iHashCode + 59) * 59) + (list == null ? 43 : list.hashCode());
                            GenericTypeToken genericTypeToken = this.ownerTypeToken;
                            return (iHashCode2 * 59) + (genericTypeToken != null ? genericTypeToken.hashCode() : 43);
                        }

                        protected Nested(String str, List<GenericTypeToken> list, GenericTypeToken genericTypeToken) {
                            this.name = str;
                            this.parameterTypeTokens = list;
                            this.ownerTypeToken = genericTypeToken;
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                        public TypeDescription.Generic toGenericType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map) {
                            return new LazyParameterizedType(typePool, typeVariableSource, str, map, this.name, this.parameterTypeTokens, this.ownerTypeToken);
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                        public String getTypePathPrefix() {
                            return this.ownerTypeToken.getTypePathPrefix() + GenericTypeToken.INNER_CLASS_PATH;
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken
                        public boolean isPrimaryBound(TypePool typePool) {
                            return !typePool.describe(this.name).resolve().isInterface();
                        }

                        protected static class LazyParameterizedType extends TypeDescription.Generic.OfParameterizedType {
                            private final Map<String, List<AnnotationToken>> annotationTokens;
                            private final String name;
                            private final GenericTypeToken ownerTypeToken;
                            private final List<GenericTypeToken> parameterTypeTokens;
                            private final String typePath;
                            private final TypePool typePool;
                            private final TypeVariableSource typeVariableSource;

                            protected LazyParameterizedType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map, String str2, List<GenericTypeToken> list, GenericTypeToken genericTypeToken) {
                                this.typePool = typePool;
                                this.typeVariableSource = typeVariableSource;
                                this.typePath = str;
                                this.annotationTokens = map;
                                this.name = str2;
                                this.parameterTypeTokens = list;
                                this.ownerTypeToken = genericTypeToken;
                            }

                            @Override // net.bytebuddy.description.type.TypeDefinition
                            public TypeDescription asErasure() {
                                return this.typePool.describe(this.name).resolve();
                            }

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic
                            public TypeList.Generic getTypeArguments() {
                                return new LazyTokenList(this.typePool, this.typeVariableSource, this.typePath + this.ownerTypeToken.getTypePathPrefix(), this.annotationTokens, this.parameterTypeTokens);
                            }

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic
                            public TypeDescription.Generic getOwnerType() {
                                return this.ownerTypeToken.toGenericType(this.typePool, this.typeVariableSource, this.typePath, this.annotationTokens);
                            }

                            @Override // net.bytebuddy.description.annotation.AnnotationSource
                            public AnnotationList getDeclaredAnnotations() {
                                return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(this.typePath + this.ownerTypeToken.getTypePathPrefix()));
                            }
                        }
                    }

                    protected static class LazyParameterizedType extends TypeDescription.Generic.OfParameterizedType {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final String name;
                        private final List<GenericTypeToken> parameterTypeTokens;
                        private final String typePath;
                        private final TypePool typePool;
                        private final TypeVariableSource typeVariableSource;

                        protected LazyParameterizedType(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map, String str2, List<GenericTypeToken> list) {
                            this.typePool = typePool;
                            this.typeVariableSource = typeVariableSource;
                            this.typePath = str;
                            this.annotationTokens = map;
                            this.name = str2;
                            this.parameterTypeTokens = list;
                        }

                        @Override // net.bytebuddy.description.type.TypeDefinition
                        public TypeDescription asErasure() {
                            return this.typePool.describe(this.name).resolve();
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeList.Generic getTypeArguments() {
                            return new LazyTokenList(this.typePool, this.typeVariableSource, this.typePath, this.annotationTokens, this.parameterTypeTokens);
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeDescription.Generic getOwnerType() {
                            TypeDescription enclosingType = this.typePool.describe(this.name).resolve().getEnclosingType();
                            if (enclosingType == null) {
                                return TypeDescription.Generic.UNDEFINED;
                            }
                            return enclosingType.asGenericType();
                        }

                        @Override // net.bytebuddy.description.annotation.AnnotationSource
                        public AnnotationList getDeclaredAnnotations() {
                            return LazyAnnotationDescription.asListOfNullable(this.typePool, this.annotationTokens.get(this.typePath));
                        }
                    }
                }

                public static class LazyTokenList extends TypeList.Generic.AbstractBase {
                    private final Map<String, List<AnnotationToken>> annotationTokens;
                    private final List<GenericTypeToken> genericTypeTokens;
                    private final String typePath;
                    private final TypePool typePool;
                    private final TypeVariableSource typeVariableSource;

                    protected LazyTokenList(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map, List<GenericTypeToken> list) {
                        this.typePool = typePool;
                        this.typeVariableSource = typeVariableSource;
                        this.typePath = str;
                        this.annotationTokens = map;
                        this.genericTypeTokens = list;
                    }

                    @Override // java.util.AbstractList, java.util.List
                    public TypeDescription.Generic get(int i) {
                        return this.genericTypeTokens.get(i).toGenericType(this.typePool, this.typeVariableSource, this.typePath + i + GenericTypeToken.INDEXED_TYPE_DELIMITER, this.annotationTokens);
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                    public int size() {
                        return this.genericTypeTokens.size();
                    }

                    protected static class ForWildcardBound extends TypeList.Generic.AbstractBase {
                        private final Map<String, List<AnnotationToken>> annotationTokens;
                        private final GenericTypeToken genericTypeToken;
                        private final String typePath;
                        private final TypePool typePool;
                        private final TypeVariableSource typeVariableSource;

                        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                        public int size() {
                            return 1;
                        }

                        protected ForWildcardBound(TypePool typePool, TypeVariableSource typeVariableSource, String str, Map<String, List<AnnotationToken>> map, GenericTypeToken genericTypeToken) {
                            this.typePool = typePool;
                            this.typeVariableSource = typeVariableSource;
                            this.typePath = str;
                            this.annotationTokens = map;
                            this.genericTypeToken = genericTypeToken;
                        }

                        @Override // java.util.AbstractList, java.util.List
                        public TypeDescription.Generic get(int i) {
                            if (i == 0) {
                                return this.genericTypeToken.toGenericType(this.typePool, this.typeVariableSource, this.typePath + GenericTypeToken.WILDCARD_TYPE_PATH, this.annotationTokens);
                            }
                            throw new IndexOutOfBoundsException("index = " + i);
                        }
                    }
                }
            }

            protected static class AnnotationToken {
                private final String descriptor;
                private final Map<String, AnnotationValue<?, ?>> values;

                protected boolean canEqual(Object obj) {
                    return obj instanceof AnnotationToken;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof AnnotationToken)) {
                        return false;
                    }
                    AnnotationToken annotationToken = (AnnotationToken) obj;
                    if (!annotationToken.canEqual(this)) {
                        return false;
                    }
                    String str = this.descriptor;
                    String str2 = annotationToken.descriptor;
                    if (str != null ? !str.equals(str2) : str2 != null) {
                        return false;
                    }
                    Map<String, AnnotationValue<?, ?>> values = getValues();
                    Map<String, AnnotationValue<?, ?>> values2 = annotationToken.getValues();
                    return values != null ? values.equals(values2) : values2 == null;
                }

                public int hashCode() {
                    String str = this.descriptor;
                    int iHashCode = str == null ? 43 : str.hashCode();
                    Map<String, AnnotationValue<?, ?>> values = getValues();
                    return ((iHashCode + 59) * 59) + (values != null ? values.hashCode() : 43);
                }

                protected AnnotationToken(String str, Map<String, AnnotationValue<?, ?>> map) {
                    this.descriptor = str;
                    this.values = map;
                }

                protected Map<String, AnnotationValue<?, ?>> getValues() {
                    return this.values;
                }

                protected String getBinaryName() {
                    String str = this.descriptor;
                    return str.substring(1, str.length() - 1).replace(ClassPathElement.SEPARATOR_CHAR, GenericTypeToken.INNER_CLASS_PATH);
                }

                /* JADX INFO: Access modifiers changed from: private */
                public Resolution toAnnotationDescription(TypePool typePool) {
                    Resolution resolutionDescribe = typePool.describe(getBinaryName());
                    if (resolutionDescribe.isResolved()) {
                        return new Resolution.Simple(new LazyAnnotationDescription(typePool, resolutionDescribe.resolve(), this.values));
                    }
                    return new Resolution.Illegal(getBinaryName());
                }

                protected interface Resolution {
                    boolean isResolved();

                    AnnotationDescription resolve();

                    public static class Simple implements Resolution {
                        private final AnnotationDescription annotationDescription;

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.AnnotationToken.Resolution
                        public boolean isResolved() {
                            return true;
                        }

                        protected boolean canEqual(Object obj) {
                            return obj instanceof Simple;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof Simple)) {
                                return false;
                            }
                            Simple simple = (Simple) obj;
                            if (!simple.canEqual(this)) {
                                return false;
                            }
                            AnnotationDescription annotationDescription = this.annotationDescription;
                            AnnotationDescription annotationDescription2 = simple.annotationDescription;
                            return annotationDescription != null ? annotationDescription.equals(annotationDescription2) : annotationDescription2 == null;
                        }

                        public int hashCode() {
                            AnnotationDescription annotationDescription = this.annotationDescription;
                            return 59 + (annotationDescription == null ? 43 : annotationDescription.hashCode());
                        }

                        protected Simple(AnnotationDescription annotationDescription) {
                            this.annotationDescription = annotationDescription;
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.AnnotationToken.Resolution
                        public AnnotationDescription resolve() {
                            return this.annotationDescription;
                        }
                    }

                    public static class Illegal implements Resolution {
                        private final String annotationType;

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.AnnotationToken.Resolution
                        public boolean isResolved() {
                            return false;
                        }

                        protected boolean canEqual(Object obj) {
                            return obj instanceof Illegal;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof Illegal)) {
                                return false;
                            }
                            Illegal illegal = (Illegal) obj;
                            if (!illegal.canEqual(this)) {
                                return false;
                            }
                            String str = this.annotationType;
                            String str2 = illegal.annotationType;
                            return str != null ? str.equals(str2) : str2 == null;
                        }

                        public int hashCode() {
                            String str = this.annotationType;
                            return 59 + (str == null ? 43 : str.hashCode());
                        }

                        public Illegal(String str) {
                            this.annotationType = str;
                        }

                        @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.AnnotationToken.Resolution
                        public AnnotationDescription resolve() {
                            throw new IllegalStateException("Annotation type is not available: " + this.annotationType);
                        }
                    }
                }
            }

            protected static class FieldToken {
                private final List<AnnotationToken> annotationTokens;
                private final String descriptor;
                private final String genericSignature;
                private final int modifiers;
                private final String name;
                private final GenericTypeToken.Resolution.ForField signatureResolution;
                private final Map<String, List<AnnotationToken>> typeAnnotationTokens;

                protected boolean canEqual(Object obj) {
                    return obj instanceof FieldToken;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof FieldToken)) {
                        return false;
                    }
                    FieldToken fieldToken = (FieldToken) obj;
                    if (!fieldToken.canEqual(this)) {
                        return false;
                    }
                    String str = this.name;
                    String str2 = fieldToken.name;
                    if (str != null ? !str.equals(str2) : str2 != null) {
                        return false;
                    }
                    if (this.modifiers != fieldToken.modifiers) {
                        return false;
                    }
                    String str3 = this.descriptor;
                    String str4 = fieldToken.descriptor;
                    if (str3 != null ? !str3.equals(str4) : str4 != null) {
                        return false;
                    }
                    String str5 = this.genericSignature;
                    String str6 = fieldToken.genericSignature;
                    if (str5 != null ? !str5.equals(str6) : str6 != null) {
                        return false;
                    }
                    GenericTypeToken.Resolution.ForField forField = this.signatureResolution;
                    GenericTypeToken.Resolution.ForField forField2 = fieldToken.signatureResolution;
                    if (forField != null ? !forField.equals(forField2) : forField2 != null) {
                        return false;
                    }
                    Map<String, List<AnnotationToken>> map = this.typeAnnotationTokens;
                    Map<String, List<AnnotationToken>> map2 = fieldToken.typeAnnotationTokens;
                    if (map != null ? !map.equals(map2) : map2 != null) {
                        return false;
                    }
                    List<AnnotationToken> list = this.annotationTokens;
                    List<AnnotationToken> list2 = fieldToken.annotationTokens;
                    return list != null ? list.equals(list2) : list2 == null;
                }

                public int hashCode() {
                    String str = this.name;
                    int iHashCode = (((str == null ? 43 : str.hashCode()) + 59) * 59) + this.modifiers;
                    String str2 = this.descriptor;
                    int iHashCode2 = (iHashCode * 59) + (str2 == null ? 43 : str2.hashCode());
                    String str3 = this.genericSignature;
                    int iHashCode3 = (iHashCode2 * 59) + (str3 == null ? 43 : str3.hashCode());
                    GenericTypeToken.Resolution.ForField forField = this.signatureResolution;
                    int iHashCode4 = (iHashCode3 * 59) + (forField == null ? 43 : forField.hashCode());
                    Map<String, List<AnnotationToken>> map = this.typeAnnotationTokens;
                    int iHashCode5 = (iHashCode4 * 59) + (map == null ? 43 : map.hashCode());
                    List<AnnotationToken> list = this.annotationTokens;
                    return (iHashCode5 * 59) + (list != null ? list.hashCode() : 43);
                }

                protected FieldToken(String str, int i, String str2, String str3, Map<String, List<AnnotationToken>> map, List<AnnotationToken> list) {
                    this.modifiers = i & (-131073);
                    this.name = str;
                    this.descriptor = str2;
                    this.genericSignature = str3;
                    this.signatureResolution = TypeDescription.AbstractBase.RAW_TYPES ? GenericTypeToken.Resolution.Raw.INSTANCE : GenericTypeExtractor.ForSignature.OfField.extract(str3);
                    this.typeAnnotationTokens = map;
                    this.annotationTokens = list;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public LazyFieldDescription toFieldDescription(LazyTypeDescription lazyTypeDescription) {
                    lazyTypeDescription.getClass();
                    return new LazyFieldDescription(this.name, this.modifiers, this.descriptor, this.genericSignature, this.signatureResolution, this.typeAnnotationTokens, this.annotationTokens);
                }
            }

            protected static class MethodToken {
                private final List<AnnotationToken> annotationTokens;
                private final AnnotationValue<?, ?> defaultValue;
                private final String descriptor;
                private final String[] exceptionName;
                private final Map<Integer, Map<String, List<AnnotationToken>>> exceptionTypeAnnotationTokens;
                private final String genericSignature;
                private final int modifiers;
                private final String name;
                private final Map<Integer, List<AnnotationToken>> parameterAnnotationTokens;
                private final List<ParameterToken> parameterTokens;
                private final Map<Integer, Map<String, List<AnnotationToken>>> parameterTypeAnnotationTokens;
                private final Map<String, List<AnnotationToken>> receiverTypeAnnotationTokens;
                private final Map<String, List<AnnotationToken>> returnTypeAnnotationTokens;
                private final GenericTypeToken.Resolution.ForMethod signatureResolution;
                private final Map<Integer, Map<String, List<AnnotationToken>>> typeVariableAnnotationTokens;
                private final Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> typeVariableBoundAnnotationTokens;

                protected boolean canEqual(Object obj) {
                    return obj instanceof MethodToken;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof MethodToken)) {
                        return false;
                    }
                    MethodToken methodToken = (MethodToken) obj;
                    if (!methodToken.canEqual(this)) {
                        return false;
                    }
                    String str = this.name;
                    String str2 = methodToken.name;
                    if (str != null ? !str.equals(str2) : str2 != null) {
                        return false;
                    }
                    if (this.modifiers != methodToken.modifiers) {
                        return false;
                    }
                    String str3 = this.descriptor;
                    String str4 = methodToken.descriptor;
                    if (str3 != null ? !str3.equals(str4) : str4 != null) {
                        return false;
                    }
                    String str5 = this.genericSignature;
                    String str6 = methodToken.genericSignature;
                    if (str5 != null ? !str5.equals(str6) : str6 != null) {
                        return false;
                    }
                    GenericTypeToken.Resolution.ForMethod forMethod = this.signatureResolution;
                    GenericTypeToken.Resolution.ForMethod forMethod2 = methodToken.signatureResolution;
                    if (forMethod != null ? !forMethod.equals(forMethod2) : forMethod2 != null) {
                        return false;
                    }
                    if (!Arrays.deepEquals(this.exceptionName, methodToken.exceptionName)) {
                        return false;
                    }
                    Map<Integer, Map<String, List<AnnotationToken>>> map = this.typeVariableAnnotationTokens;
                    Map<Integer, Map<String, List<AnnotationToken>>> map2 = methodToken.typeVariableAnnotationTokens;
                    if (map != null ? !map.equals(map2) : map2 != null) {
                        return false;
                    }
                    Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map3 = this.typeVariableBoundAnnotationTokens;
                    Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map4 = methodToken.typeVariableBoundAnnotationTokens;
                    if (map3 != null ? !map3.equals(map4) : map4 != null) {
                        return false;
                    }
                    Map<String, List<AnnotationToken>> map5 = this.returnTypeAnnotationTokens;
                    Map<String, List<AnnotationToken>> map6 = methodToken.returnTypeAnnotationTokens;
                    if (map5 != null ? !map5.equals(map6) : map6 != null) {
                        return false;
                    }
                    Map<Integer, Map<String, List<AnnotationToken>>> map7 = this.parameterTypeAnnotationTokens;
                    Map<Integer, Map<String, List<AnnotationToken>>> map8 = methodToken.parameterTypeAnnotationTokens;
                    if (map7 != null ? !map7.equals(map8) : map8 != null) {
                        return false;
                    }
                    Map<Integer, Map<String, List<AnnotationToken>>> map9 = this.exceptionTypeAnnotationTokens;
                    Map<Integer, Map<String, List<AnnotationToken>>> map10 = methodToken.exceptionTypeAnnotationTokens;
                    if (map9 != null ? !map9.equals(map10) : map10 != null) {
                        return false;
                    }
                    Map<String, List<AnnotationToken>> map11 = this.receiverTypeAnnotationTokens;
                    Map<String, List<AnnotationToken>> map12 = methodToken.receiverTypeAnnotationTokens;
                    if (map11 != null ? !map11.equals(map12) : map12 != null) {
                        return false;
                    }
                    List<AnnotationToken> list = this.annotationTokens;
                    List<AnnotationToken> list2 = methodToken.annotationTokens;
                    if (list != null ? !list.equals(list2) : list2 != null) {
                        return false;
                    }
                    Map<Integer, List<AnnotationToken>> map13 = this.parameterAnnotationTokens;
                    Map<Integer, List<AnnotationToken>> map14 = methodToken.parameterAnnotationTokens;
                    if (map13 != null ? !map13.equals(map14) : map14 != null) {
                        return false;
                    }
                    List<ParameterToken> list3 = this.parameterTokens;
                    List<ParameterToken> list4 = methodToken.parameterTokens;
                    if (list3 != null ? !list3.equals(list4) : list4 != null) {
                        return false;
                    }
                    AnnotationValue<?, ?> annotationValue = this.defaultValue;
                    AnnotationValue<?, ?> annotationValue2 = methodToken.defaultValue;
                    return annotationValue != null ? annotationValue.equals(annotationValue2) : annotationValue2 == null;
                }

                public int hashCode() {
                    String str = this.name;
                    int iHashCode = (((str == null ? 43 : str.hashCode()) + 59) * 59) + this.modifiers;
                    String str2 = this.descriptor;
                    int iHashCode2 = (iHashCode * 59) + (str2 == null ? 43 : str2.hashCode());
                    String str3 = this.genericSignature;
                    int iHashCode3 = (iHashCode2 * 59) + (str3 == null ? 43 : str3.hashCode());
                    GenericTypeToken.Resolution.ForMethod forMethod = this.signatureResolution;
                    int iHashCode4 = (((iHashCode3 * 59) + (forMethod == null ? 43 : forMethod.hashCode())) * 59) + Arrays.deepHashCode(this.exceptionName);
                    Map<Integer, Map<String, List<AnnotationToken>>> map = this.typeVariableAnnotationTokens;
                    int iHashCode5 = (iHashCode4 * 59) + (map == null ? 43 : map.hashCode());
                    Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2 = this.typeVariableBoundAnnotationTokens;
                    int iHashCode6 = (iHashCode5 * 59) + (map2 == null ? 43 : map2.hashCode());
                    Map<String, List<AnnotationToken>> map3 = this.returnTypeAnnotationTokens;
                    int iHashCode7 = (iHashCode6 * 59) + (map3 == null ? 43 : map3.hashCode());
                    Map<Integer, Map<String, List<AnnotationToken>>> map4 = this.parameterTypeAnnotationTokens;
                    int iHashCode8 = (iHashCode7 * 59) + (map4 == null ? 43 : map4.hashCode());
                    Map<Integer, Map<String, List<AnnotationToken>>> map5 = this.exceptionTypeAnnotationTokens;
                    int iHashCode9 = (iHashCode8 * 59) + (map5 == null ? 43 : map5.hashCode());
                    Map<String, List<AnnotationToken>> map6 = this.receiverTypeAnnotationTokens;
                    int iHashCode10 = (iHashCode9 * 59) + (map6 == null ? 43 : map6.hashCode());
                    List<AnnotationToken> list = this.annotationTokens;
                    int iHashCode11 = (iHashCode10 * 59) + (list == null ? 43 : list.hashCode());
                    Map<Integer, List<AnnotationToken>> map7 = this.parameterAnnotationTokens;
                    int iHashCode12 = (iHashCode11 * 59) + (map7 == null ? 43 : map7.hashCode());
                    List<ParameterToken> list2 = this.parameterTokens;
                    int iHashCode13 = (iHashCode12 * 59) + (list2 == null ? 43 : list2.hashCode());
                    AnnotationValue<?, ?> annotationValue = this.defaultValue;
                    return (iHashCode13 * 59) + (annotationValue != null ? annotationValue.hashCode() : 43);
                }

                protected MethodToken(String str, int i, String str2, String str3, String[] strArr, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2, Map<String, List<AnnotationToken>> map3, Map<Integer, Map<String, List<AnnotationToken>>> map4, Map<Integer, Map<String, List<AnnotationToken>>> map5, Map<String, List<AnnotationToken>> map6, List<AnnotationToken> list, Map<Integer, List<AnnotationToken>> map7, List<ParameterToken> list2, AnnotationValue<?, ?> annotationValue) {
                    this.modifiers = (-131073) & i;
                    this.name = str;
                    this.descriptor = str2;
                    this.genericSignature = str3;
                    this.signatureResolution = TypeDescription.AbstractBase.RAW_TYPES ? GenericTypeToken.Resolution.Raw.INSTANCE : GenericTypeExtractor.ForSignature.OfMethod.extract(str3);
                    this.exceptionName = strArr;
                    this.typeVariableAnnotationTokens = map;
                    this.typeVariableBoundAnnotationTokens = map2;
                    this.returnTypeAnnotationTokens = map3;
                    this.parameterTypeAnnotationTokens = map4;
                    this.exceptionTypeAnnotationTokens = map5;
                    this.receiverTypeAnnotationTokens = map6;
                    this.annotationTokens = list;
                    this.parameterAnnotationTokens = map7;
                    this.parameterTokens = list2;
                    this.defaultValue = annotationValue;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public MethodDescription.InDefinedShape toMethodDescription(LazyTypeDescription lazyTypeDescription) {
                    lazyTypeDescription.getClass();
                    return new LazyMethodDescription(this.name, this.modifiers, this.descriptor, this.genericSignature, this.signatureResolution, this.exceptionName, this.typeVariableAnnotationTokens, this.typeVariableBoundAnnotationTokens, this.returnTypeAnnotationTokens, this.parameterTypeAnnotationTokens, this.exceptionTypeAnnotationTokens, this.receiverTypeAnnotationTokens, this.annotationTokens, this.parameterAnnotationTokens, this.parameterTokens, this.defaultValue);
                }

                protected static class ParameterToken {
                    protected static final Integer NO_MODIFIERS = null;
                    protected static final String NO_NAME = null;
                    private final Integer modifiers;
                    private final String name;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ParameterToken;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ParameterToken)) {
                            return false;
                        }
                        ParameterToken parameterToken = (ParameterToken) obj;
                        if (!parameterToken.canEqual(this)) {
                            return false;
                        }
                        String name = getName();
                        String name2 = parameterToken.getName();
                        if (name != null ? !name.equals(name2) : name2 != null) {
                            return false;
                        }
                        Integer modifiers = getModifiers();
                        Integer modifiers2 = parameterToken.getModifiers();
                        return modifiers != null ? modifiers.equals(modifiers2) : modifiers2 == null;
                    }

                    public int hashCode() {
                        String name = getName();
                        int iHashCode = name == null ? 43 : name.hashCode();
                        Integer modifiers = getModifiers();
                        return ((iHashCode + 59) * 59) + (modifiers != null ? modifiers.hashCode() : 43);
                    }

                    protected ParameterToken() {
                        this(NO_NAME);
                    }

                    protected ParameterToken(String str) {
                        this(str, NO_MODIFIERS);
                    }

                    protected ParameterToken(String str, Integer num) {
                        this.name = str;
                        this.modifiers = num;
                    }

                    protected String getName() {
                        return this.name;
                    }

                    protected Integer getModifiers() {
                        return this.modifiers;
                    }
                }
            }

            private static class LazyAnnotationDescription extends AnnotationDescription.AbstractBase {
                private final TypeDescription annotationType;
                protected final TypePool typePool;
                protected final Map<String, AnnotationValue<?, ?>> values;

                private LazyAnnotationDescription(TypePool typePool, TypeDescription typeDescription, Map<String, AnnotationValue<?, ?>> map) {
                    this.typePool = typePool;
                    this.annotationType = typeDescription;
                    this.values = map;
                }

                protected static AnnotationList asListOfNullable(TypePool typePool, List<? extends AnnotationToken> list) {
                    if (list == null) {
                        return new AnnotationList.Empty();
                    }
                    return asList(typePool, list);
                }

                protected static AnnotationList asList(TypePool typePool, List<? extends AnnotationToken> list) {
                    ArrayList arrayList = new ArrayList(list.size());
                    Iterator<? extends AnnotationToken> it = list.iterator();
                    while (it.hasNext()) {
                        AnnotationToken.Resolution annotationDescription = it.next().toAnnotationDescription(typePool);
                        if (annotationDescription.isResolved()) {
                            arrayList.add(annotationDescription.resolve());
                        }
                    }
                    return new AnnotationList.Explicit(arrayList);
                }

                @Override // net.bytebuddy.description.annotation.AnnotationDescription
                public AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape) {
                    if (!inDefinedShape.getDeclaringType().asErasure().equals(this.annotationType)) {
                        throw new IllegalArgumentException(inDefinedShape + " is not declared by " + getAnnotationType());
                    }
                    AnnotationValue<?, ?> defaultValue = this.values.get(inDefinedShape.getName());
                    if (defaultValue == null) {
                        defaultValue = ((MethodDescription.InDefinedShape) getAnnotationType().getDeclaredMethods().filter(ElementMatchers.is(inDefinedShape)).getOnly()).getDefaultValue();
                    }
                    if (defaultValue != null) {
                        return defaultValue;
                    }
                    throw new IllegalStateException(inDefinedShape + " is not defined on annotation");
                }

                @Override // net.bytebuddy.description.annotation.AnnotationDescription
                public TypeDescription getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationDescription
                public <T extends Annotation> Loadable<T> prepare(Class<T> cls) {
                    if (!this.annotationType.represents(cls)) {
                        throw new IllegalArgumentException(cls + " does not represent " + this.annotationType);
                    }
                    return new Loadable<>(this.typePool, cls, this.values);
                }

                private static class Loadable<S extends Annotation> extends LazyAnnotationDescription implements AnnotationDescription.Loadable<S> {
                    private final Class<S> annotationType;

                    @Override // net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.LazyAnnotationDescription, net.bytebuddy.description.annotation.AnnotationDescription
                    public /* bridge */ /* synthetic */ AnnotationDescription.Loadable prepare(Class cls) {
                        return super.prepare(cls);
                    }

                    private Loadable(TypePool typePool, Class<S> cls, Map<String, AnnotationValue<?, ?>> map) {
                        super(typePool, new TypeDescription.ForLoadedType(cls), map);
                        this.annotationType = cls;
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationDescription.Loadable
                    public S load() throws ClassNotFoundException {
                        return (S) AnnotationDescription.AnnotationInvocationHandler.of(this.annotationType.getClassLoader(), this.annotationType, this.values);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationDescription.Loadable
                    public S loadSilent() {
                        try {
                            return (S) load();
                        } catch (ClassNotFoundException e) {
                            throw new IllegalStateException("Could not load annotation type or referenced type", e);
                        }
                    }
                }
            }

            private static class LazyPackageDescription extends PackageDescription.AbstractBase {
                private final String name;
                private final TypePool typePool;

                private LazyPackageDescription(TypePool typePool, String str) {
                    this.typePool = typePool;
                    this.name = str;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    Resolution resolutionDescribe = this.typePool.describe(this.name + ".package-info");
                    return resolutionDescribe.isResolved() ? resolutionDescribe.resolve().getDeclaredAnnotations() : new AnnotationList.Empty();
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getName() {
                    return this.name;
                }
            }

            private static class LazyTypeList extends TypeList.AbstractBase {
                private final List<String> descriptors;
                private final TypePool typePool;

                private LazyTypeList(TypePool typePool, List<String> list) {
                    this.typePool = typePool;
                    this.descriptors = list;
                }

                @Override // java.util.AbstractList, java.util.List
                public TypeDescription get(int i) {
                    return TokenizedGenericType.toErasure(this.typePool, this.descriptors.get(i));
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return this.descriptors.size();
                }

                @Override // net.bytebuddy.description.type.TypeList
                public String[] toInternalNames() {
                    int size = this.descriptors.size();
                    String[] strArr = new String[size];
                    Iterator<String> it = this.descriptors.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        strArr[i] = Type.getType(it.next()).getInternalName();
                        i++;
                    }
                    return size == 0 ? NO_INTERFACES : strArr;
                }

                @Override // net.bytebuddy.description.type.TypeList
                public int getStackSize() {
                    Iterator<String> it = this.descriptors.iterator();
                    int size = 0;
                    while (it.hasNext()) {
                        size += Type.getType(it.next()).getSize();
                    }
                    return size;
                }
            }

            private static class TokenizedGenericType extends TypeDescription.Generic.LazyProjection.WithEagerNavigation {
                private final Map<String, List<AnnotationToken>> annotationTokens;
                private final GenericTypeToken genericTypeToken;
                private final String rawTypeDescriptor;
                private final TypePool typePool;
                private final TypeVariableSource typeVariableSource;

                protected TokenizedGenericType(TypePool typePool, GenericTypeToken genericTypeToken, String str, Map<String, List<AnnotationToken>> map, TypeVariableSource typeVariableSource) {
                    this.typePool = typePool;
                    this.genericTypeToken = genericTypeToken;
                    this.rawTypeDescriptor = str;
                    this.annotationTokens = map;
                    this.typeVariableSource = typeVariableSource;
                }

                protected static TypeDescription.Generic of(TypePool typePool, GenericTypeToken genericTypeToken, String str, Map<String, List<AnnotationToken>> map, TypeVariableSource typeVariableSource) {
                    if (map == null) {
                        map = Collections.emptyMap();
                    }
                    return new TokenizedGenericType(typePool, genericTypeToken, str, map, typeVariableSource);
                }

                protected static TypeDescription toErasure(TypePool typePool, String str) {
                    String className;
                    Type type = Type.getType(str);
                    if (type.getSort() == 9) {
                        className = type.getInternalName().replace(ClassPathElement.SEPARATOR_CHAR, GenericTypeToken.INNER_CLASS_PATH);
                    } else {
                        className = type.getClassName();
                    }
                    return typePool.describe(className).resolve();
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection
                protected TypeDescription.Generic resolve() {
                    return this.genericTypeToken.toGenericType(this.typePool, this.typeVariableSource, "", this.annotationTokens);
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return toErasure(this.typePool, this.rawTypeDescriptor);
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return resolve().getDeclaredAnnotations();
                }

                protected static class TokenList extends TypeList.Generic.AbstractBase {
                    private final Map<Integer, Map<String, List<AnnotationToken>>> annotationTokens;
                    private final List<GenericTypeToken> genericTypeTokens;
                    private final List<String> rawTypeDescriptors;
                    private final TypePool typePool;
                    private final TypeVariableSource typeVariableSource;

                    private TokenList(TypePool typePool, List<GenericTypeToken> list, Map<Integer, Map<String, List<AnnotationToken>>> map, List<String> list2, TypeVariableSource typeVariableSource) {
                        this.typePool = typePool;
                        this.genericTypeTokens = list;
                        this.annotationTokens = map;
                        this.rawTypeDescriptors = list2;
                        this.typeVariableSource = typeVariableSource;
                    }

                    @Override // java.util.AbstractList, java.util.List
                    public TypeDescription.Generic get(int i) {
                        if (this.rawTypeDescriptors.size() == this.genericTypeTokens.size()) {
                            return TokenizedGenericType.of(this.typePool, this.genericTypeTokens.get(i), this.rawTypeDescriptors.get(i), this.annotationTokens.get(Integer.valueOf(i)), this.typeVariableSource);
                        }
                        return TokenizedGenericType.toErasure(this.typePool, this.rawTypeDescriptors.get(i)).asGenericType();
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                    public int size() {
                        return this.rawTypeDescriptors.size();
                    }

                    @Override // net.bytebuddy.description.type.TypeList.Generic.AbstractBase, net.bytebuddy.description.type.TypeList.Generic
                    public TypeList asErasures() {
                        return new LazyTypeList(this.typePool, this.rawTypeDescriptors);
                    }
                }

                protected static class TypeVariableList extends TypeList.Generic.AbstractBase {
                    private final Map<Integer, Map<String, List<AnnotationToken>>> annotationTokens;
                    private final Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> boundAnnotationTokens;
                    private final TypePool typePool;
                    private final TypeVariableSource typeVariableSource;
                    private final List<GenericTypeToken.OfFormalTypeVariable> typeVariables;

                    protected TypeVariableList(TypePool typePool, List<GenericTypeToken.OfFormalTypeVariable> list, TypeVariableSource typeVariableSource, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2) {
                        this.typePool = typePool;
                        this.typeVariables = list;
                        this.typeVariableSource = typeVariableSource;
                        this.annotationTokens = map;
                        this.boundAnnotationTokens = map2;
                    }

                    @Override // java.util.AbstractList, java.util.List
                    public TypeDescription.Generic get(int i) {
                        return this.typeVariables.get(i).toGenericType(this.typePool, this.typeVariableSource, this.annotationTokens.get(Integer.valueOf(i)), this.boundAnnotationTokens.get(Integer.valueOf(i)));
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                    public int size() {
                        return this.typeVariables.size();
                    }
                }

                protected static class Malformed extends TypeDescription.Generic.LazyProjection.WithEagerNavigation {
                    private final String rawTypeDescriptor;
                    private final TypePool typePool;

                    protected Malformed(TypePool typePool, String str) {
                        this.typePool = typePool;
                        this.rawTypeDescriptor = str;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection
                    protected TypeDescription.Generic resolve() {
                        throw new GenericSignatureFormatError();
                    }

                    @Override // net.bytebuddy.description.type.TypeDefinition
                    public TypeDescription asErasure() {
                        return TokenizedGenericType.toErasure(this.typePool, this.rawTypeDescriptor);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationSource
                    public AnnotationList getDeclaredAnnotations() {
                        throw new GenericSignatureFormatError();
                    }

                    protected static class TokenList extends TypeList.Generic.AbstractBase {
                        private final List<String> rawTypeDescriptors;
                        private final TypePool typePool;

                        protected TokenList(TypePool typePool, List<String> list) {
                            this.typePool = typePool;
                            this.rawTypeDescriptors = list;
                        }

                        @Override // java.util.AbstractList, java.util.List
                        public TypeDescription.Generic get(int i) {
                            return new Malformed(this.typePool, this.rawTypeDescriptors.get(i));
                        }

                        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                        public int size() {
                            return this.rawTypeDescriptors.size();
                        }

                        @Override // net.bytebuddy.description.type.TypeList.Generic.AbstractBase, net.bytebuddy.description.type.TypeList.Generic
                        public TypeList asErasures() {
                            return new LazyTypeList(this.typePool, this.rawTypeDescriptors);
                        }
                    }
                }
            }

            private class LazyFieldDescription extends FieldDescription.InDefinedShape.AbstractBase {
                private final List<AnnotationToken> annotationTokens;
                private final String descriptor;
                private final String genericSignature;
                private final int modifiers;
                private final String name;
                private final GenericTypeToken.Resolution.ForField signatureResolution;
                private final Map<String, List<AnnotationToken>> typeAnnotationTokens;

                private LazyFieldDescription(String str, int i, String str2, String str3, GenericTypeToken.Resolution.ForField forField, Map<String, List<AnnotationToken>> map, List<AnnotationToken> list) {
                    this.modifiers = i;
                    this.name = str;
                    this.descriptor = str2;
                    this.genericSignature = str3;
                    this.signatureResolution = forField;
                    this.typeAnnotationTokens = map;
                    this.annotationTokens = list;
                }

                @Override // net.bytebuddy.description.field.FieldDescription
                public TypeDescription.Generic getType() {
                    return this.signatureResolution.resolveFieldType(this.descriptor, LazyTypeDescription.this.typePool, this.typeAnnotationTokens, this);
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return LazyAnnotationDescription.asListOfNullable(LazyTypeDescription.this.typePool, this.annotationTokens);
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getName() {
                    return this.name;
                }

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return LazyTypeDescription.this;
                }

                @Override // net.bytebuddy.description.ModifierReviewable
                public int getModifiers() {
                    return this.modifiers;
                }

                @Override // net.bytebuddy.description.field.FieldDescription.AbstractBase, net.bytebuddy.description.ByteCodeElement
                public String getGenericSignature() {
                    return this.genericSignature;
                }
            }

            private class LazyMethodDescription extends MethodDescription.InDefinedShape.AbstractBase {
                private final List<AnnotationToken> annotationTokens;
                private final AnnotationValue<?, ?> defaultValue;
                private final Map<Integer, Map<String, List<AnnotationToken>>> exceptionTypeAnnotationTokens;
                private final List<String> exceptionTypeDescriptors;
                private final String genericSignature;
                private final String internalName;
                private final int modifiers;
                private final Map<Integer, List<AnnotationToken>> parameterAnnotationTokens;
                private final Integer[] parameterModifiers;
                private final String[] parameterNames;
                private final Map<Integer, Map<String, List<AnnotationToken>>> parameterTypeAnnotationTokens;
                private final List<String> parameterTypeDescriptors;
                private final Map<String, List<AnnotationToken>> receiverTypeAnnotationTokens;
                private final Map<String, List<AnnotationToken>> returnTypeAnnotationTokens;
                private final String returnTypeDescriptor;
                private final GenericTypeToken.Resolution.ForMethod signatureResolution;
                private final Map<Integer, Map<String, List<AnnotationToken>>> typeVariableAnnotationTokens;
                private final Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> typeVariableBoundAnnotationTokens;

                private LazyMethodDescription(String str, int i, String str2, String str3, GenericTypeToken.Resolution.ForMethod forMethod, String[] strArr, Map<Integer, Map<String, List<AnnotationToken>>> map, Map<Integer, Map<Integer, Map<String, List<AnnotationToken>>>> map2, Map<String, List<AnnotationToken>> map3, Map<Integer, Map<String, List<AnnotationToken>>> map4, Map<Integer, Map<String, List<AnnotationToken>>> map5, Map<String, List<AnnotationToken>> map6, List<AnnotationToken> list, Map<Integer, List<AnnotationToken>> map7, List<MethodToken.ParameterToken> list2, AnnotationValue<?, ?> annotationValue) {
                    this.modifiers = i;
                    this.internalName = str;
                    Type methodType = Type.getMethodType(str2);
                    Type returnType = methodType.getReturnType();
                    Type[] argumentTypes = methodType.getArgumentTypes();
                    this.returnTypeDescriptor = returnType.getDescriptor();
                    this.parameterTypeDescriptors = new ArrayList(argumentTypes.length);
                    int i2 = 0;
                    for (Type type : argumentTypes) {
                        this.parameterTypeDescriptors.add(type.getDescriptor());
                    }
                    this.genericSignature = str3;
                    this.signatureResolution = forMethod;
                    if (strArr == null) {
                        this.exceptionTypeDescriptors = Collections.emptyList();
                    } else {
                        this.exceptionTypeDescriptors = new ArrayList(strArr.length);
                        for (String str4 : strArr) {
                            this.exceptionTypeDescriptors.add(Type.getObjectType(str4).getDescriptor());
                        }
                    }
                    this.typeVariableAnnotationTokens = map;
                    this.typeVariableBoundAnnotationTokens = map2;
                    this.returnTypeAnnotationTokens = map3;
                    this.parameterTypeAnnotationTokens = map4;
                    this.exceptionTypeAnnotationTokens = map5;
                    this.receiverTypeAnnotationTokens = map6;
                    this.annotationTokens = list;
                    this.parameterAnnotationTokens = map7;
                    this.parameterNames = new String[argumentTypes.length];
                    this.parameterModifiers = new Integer[argumentTypes.length];
                    if (list2.size() == argumentTypes.length) {
                        for (MethodToken.ParameterToken parameterToken : list2) {
                            this.parameterNames[i2] = parameterToken.getName();
                            this.parameterModifiers[i2] = parameterToken.getModifiers();
                            i2++;
                        }
                    }
                    this.defaultValue = annotationValue;
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeDescription.Generic getReturnType() {
                    return this.signatureResolution.resolveReturnType(this.returnTypeDescriptor, LazyTypeDescription.this.typePool, this.returnTypeAnnotationTokens, this);
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeList.Generic getExceptionTypes() {
                    return this.signatureResolution.resolveExceptionTypes(this.exceptionTypeDescriptors, LazyTypeDescription.this.typePool, this.exceptionTypeAnnotationTokens, this);
                }

                @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new LazyParameterList();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return LazyAnnotationDescription.asList(LazyTypeDescription.this.typePool, this.annotationTokens);
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getInternalName() {
                    return this.internalName;
                }

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return LazyTypeDescription.this;
                }

                @Override // net.bytebuddy.description.ModifierReviewable
                public int getModifiers() {
                    return this.modifiers;
                }

                @Override // net.bytebuddy.description.TypeVariableSource
                public TypeList.Generic getTypeVariables() {
                    return this.signatureResolution.resolveTypeVariables(LazyTypeDescription.this.typePool, this, this.typeVariableAnnotationTokens, this.typeVariableBoundAnnotationTokens);
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public AnnotationValue<?, ?> getDefaultValue() {
                    return this.defaultValue;
                }

                @Override // net.bytebuddy.description.method.MethodDescription.InDefinedShape.AbstractBase, net.bytebuddy.description.method.MethodDescription
                public TypeDescription.Generic getReceiverType() {
                    if (isStatic()) {
                        return TypeDescription.Generic.UNDEFINED;
                    }
                    if (!isConstructor()) {
                        return LazyTypeDescription.this.isGenerified() ? new LazyParameterizedReceiverType(this) : new LazyNonGenericReceiverType(this);
                    }
                    TypeDescription declaringType = getDeclaringType();
                    TypeDescription enclosingType = declaringType.getEnclosingType();
                    return enclosingType == null ? declaringType.isGenerified() ? new LazyParameterizedReceiverType(declaringType) : new LazyNonGenericReceiverType(declaringType) : (declaringType.isStatic() || !declaringType.isGenerified()) ? new LazyNonGenericReceiverType(enclosingType) : new LazyParameterizedReceiverType(enclosingType);
                }

                @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.ByteCodeElement
                public String getGenericSignature() {
                    return this.genericSignature;
                }

                private class LazyParameterList extends ParameterList.AbstractBase<ParameterDescription.InDefinedShape> {
                    private LazyParameterList() {
                    }

                    @Override // java.util.AbstractList, java.util.List
                    public ParameterDescription.InDefinedShape get(int i) {
                        return LazyMethodDescription.this.new LazyParameterDescription(i);
                    }

                    @Override // net.bytebuddy.description.method.ParameterList.AbstractBase, net.bytebuddy.description.method.ParameterList
                    public boolean hasExplicitMetaData() {
                        for (int i = 0; i < size(); i++) {
                            if (LazyMethodDescription.this.parameterNames[i] == null || LazyMethodDescription.this.parameterModifiers[i] == null) {
                                return false;
                            }
                        }
                        return true;
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                    public int size() {
                        return LazyMethodDescription.this.parameterTypeDescriptors.size();
                    }

                    @Override // net.bytebuddy.description.method.ParameterList.AbstractBase, net.bytebuddy.description.method.ParameterList
                    public TypeList.Generic asTypeList() {
                        return LazyMethodDescription.this.signatureResolution.resolveParameterTypes(LazyMethodDescription.this.parameterTypeDescriptors, LazyTypeDescription.this.typePool, LazyMethodDescription.this.parameterTypeAnnotationTokens, LazyMethodDescription.this);
                    }
                }

                private class LazyParameterDescription extends ParameterDescription.InDefinedShape.AbstractBase {
                    private final int index;

                    protected LazyParameterDescription(int i) {
                        this.index = i;
                    }

                    @Override // net.bytebuddy.description.method.ParameterDescription, net.bytebuddy.description.method.ParameterDescription.InDefinedShape
                    public MethodDescription.InDefinedShape getDeclaringMethod() {
                        return LazyMethodDescription.this;
                    }

                    @Override // net.bytebuddy.description.method.ParameterDescription
                    public int getIndex() {
                        return this.index;
                    }

                    @Override // net.bytebuddy.description.NamedElement.WithOptionalName
                    public boolean isNamed() {
                        return LazyMethodDescription.this.parameterNames[this.index] != null;
                    }

                    @Override // net.bytebuddy.description.method.ParameterDescription
                    public boolean hasModifiers() {
                        return LazyMethodDescription.this.parameterModifiers[this.index] != null;
                    }

                    @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.NamedElement.WithRuntimeName
                    public String getName() {
                        if (isNamed()) {
                            return LazyMethodDescription.this.parameterNames[this.index];
                        }
                        return super.getName();
                    }

                    @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.ModifierReviewable
                    public int getModifiers() {
                        if (hasModifiers()) {
                            return LazyMethodDescription.this.parameterModifiers[this.index].intValue();
                        }
                        return super.getModifiers();
                    }

                    @Override // net.bytebuddy.description.method.ParameterDescription
                    public TypeDescription.Generic getType() {
                        return (TypeDescription.Generic) LazyMethodDescription.this.signatureResolution.resolveParameterTypes(LazyMethodDescription.this.parameterTypeDescriptors, LazyTypeDescription.this.typePool, LazyMethodDescription.this.parameterTypeAnnotationTokens, LazyMethodDescription.this).get(this.index);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationSource
                    public AnnotationList getDeclaredAnnotations() {
                        return LazyAnnotationDescription.asListOfNullable(LazyTypeDescription.this.typePool, (List) LazyMethodDescription.this.parameterAnnotationTokens.get(Integer.valueOf(this.index)));
                    }
                }

                private class LazyParameterizedReceiverType extends TypeDescription.Generic.OfParameterizedType {
                    private final TypeDescription typeDescription;

                    protected LazyParameterizedReceiverType(LazyMethodDescription lazyMethodDescription) {
                        this(LazyTypeDescription.this);
                    }

                    protected LazyParameterizedReceiverType(TypeDescription typeDescription) {
                        this.typeDescription = typeDescription;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic
                    public TypeList.Generic getTypeArguments() {
                        return new TypeArgumentList(this.typeDescription.getTypeVariables());
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic
                    public TypeDescription.Generic getOwnerType() {
                        TypeDescription declaringType = this.typeDescription.getDeclaringType();
                        if (declaringType == null) {
                            return TypeDescription.Generic.UNDEFINED;
                        }
                        return (this.typeDescription.isStatic() || !declaringType.isGenerified()) ? LazyMethodDescription.this.new LazyNonGenericReceiverType(declaringType) : LazyMethodDescription.this.new LazyParameterizedReceiverType(declaringType);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationSource
                    public AnnotationList getDeclaredAnnotations() {
                        return LazyAnnotationDescription.asListOfNullable(LazyTypeDescription.this.typePool, (List) LazyMethodDescription.this.receiverTypeAnnotationTokens.get(getTypePath()));
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public String getTypePath() {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < this.typeDescription.getSegmentCount(); i++) {
                            sb = sb.append(GenericTypeToken.INNER_CLASS_PATH);
                        }
                        return sb.toString();
                    }

                    @Override // net.bytebuddy.description.type.TypeDefinition
                    public TypeDescription asErasure() {
                        return this.typeDescription;
                    }

                    protected class TypeArgumentList extends TypeList.Generic.AbstractBase {
                        private final List<? extends TypeDescription.Generic> typeVariables;

                        protected TypeArgumentList(List<? extends TypeDescription.Generic> list) {
                            this.typeVariables = list;
                        }

                        @Override // java.util.AbstractList, java.util.List
                        public TypeDescription.Generic get(int i) {
                            return new AnnotatedTypeVariable(this.typeVariables.get(i), i);
                        }

                        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                        public int size() {
                            return this.typeVariables.size();
                        }

                        protected class AnnotatedTypeVariable extends TypeDescription.Generic.OfTypeVariable {
                            private final int index;
                            private final TypeDescription.Generic typeVariable;

                            protected AnnotatedTypeVariable(TypeDescription.Generic generic, int i) {
                                this.typeVariable = generic;
                                this.index = i;
                            }

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic
                            public TypeList.Generic getUpperBounds() {
                                return this.typeVariable.getUpperBounds();
                            }

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic
                            public TypeVariableSource getTypeVariableSource() {
                                return this.typeVariable.getTypeVariableSource();
                            }

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic
                            public String getSymbol() {
                                return this.typeVariable.getSymbol();
                            }

                            @Override // net.bytebuddy.description.annotation.AnnotationSource
                            public AnnotationList getDeclaredAnnotations() {
                                return LazyAnnotationDescription.asListOfNullable(LazyTypeDescription.this.typePool, (List) LazyMethodDescription.this.receiverTypeAnnotationTokens.get(LazyParameterizedReceiverType.this.getTypePath() + this.index + GenericTypeToken.INDEXED_TYPE_DELIMITER));
                            }
                        }
                    }
                }

                protected class LazyNonGenericReceiverType extends TypeDescription.Generic.OfNonGenericType {
                    private final TypeDescription typeDescription;

                    protected LazyNonGenericReceiverType(LazyMethodDescription lazyMethodDescription) {
                        this(LazyTypeDescription.this);
                    }

                    protected LazyNonGenericReceiverType(TypeDescription typeDescription) {
                        this.typeDescription = typeDescription;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic
                    public TypeDescription.Generic getOwnerType() {
                        TypeDescription declaringType = this.typeDescription.getDeclaringType();
                        return declaringType == null ? TypeDescription.Generic.UNDEFINED : LazyMethodDescription.this.new LazyNonGenericReceiverType(declaringType);
                    }

                    @Override // net.bytebuddy.description.type.TypeDefinition
                    public TypeDescription.Generic getComponentType() {
                        return TypeDescription.Generic.UNDEFINED;
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationSource
                    public AnnotationList getDeclaredAnnotations() {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < this.typeDescription.getSegmentCount(); i++) {
                            sb = sb.append(GenericTypeToken.INNER_CLASS_PATH);
                        }
                        return LazyAnnotationDescription.asListOfNullable(LazyTypeDescription.this.typePool, (List) LazyMethodDescription.this.receiverTypeAnnotationTokens.get(sb.toString()));
                    }

                    @Override // net.bytebuddy.description.type.TypeDefinition
                    public TypeDescription asErasure() {
                        return this.typeDescription;
                    }
                }
            }
        }

        protected class TypeExtractor extends ClassVisitor {
            private static final int REAL_MODIFIER_MASK = 65535;
            private int actualModifiers;
            private final List<LazyTypeDescription.AnnotationToken> annotationTokens;
            private boolean anonymousType;
            private final List<String> declaredTypes;
            private String declaringTypeName;
            private final List<LazyTypeDescription.FieldToken> fieldTokens;
            private String genericSignature;
            private String[] interfaceName;
            private String internalName;
            private final List<LazyTypeDescription.MethodToken> methodTokens;
            private int modifiers;
            private String superClassName;
            private final Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> superTypeAnnotationTokens;
            private LazyTypeDescription.TypeContainment typeContainment;
            private final Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> typeVariableAnnotationTokens;
            private final Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> typeVariableBoundsAnnotationTokens;

            protected TypeExtractor() {
                super(Opcodes.ASM6);
                this.superTypeAnnotationTokens = new HashMap();
                this.typeVariableAnnotationTokens = new HashMap();
                this.typeVariableBoundsAnnotationTokens = new HashMap();
                this.annotationTokens = new ArrayList();
                this.fieldTokens = new ArrayList();
                this.methodTokens = new ArrayList();
                this.anonymousType = false;
                this.typeContainment = LazyTypeDescription.TypeContainment.SelfContained.INSTANCE;
                this.declaredTypes = new ArrayList();
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
                this.modifiers = 65535 & i2;
                this.actualModifiers = i2;
                this.internalName = str;
                this.genericSignature = str2;
                this.superClassName = str3;
                this.interfaceName = strArr;
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public void visitOuterClass(String str, String str2, String str3) {
                if (str2 != null) {
                    this.typeContainment = new LazyTypeDescription.TypeContainment.WithinMethod(str, str2, str3);
                } else if (str != null) {
                    this.typeContainment = new LazyTypeDescription.TypeContainment.WithinType(str, true);
                }
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public void visitInnerClass(String str, String str2, String str3, int i) {
                if (str.equals(this.internalName)) {
                    this.modifiers = 65535 & i;
                    if (str3 == null) {
                        this.anonymousType = true;
                    }
                    if (str2 != null) {
                        this.declaringTypeName = str2;
                        if (this.typeContainment.isSelfContained()) {
                            this.typeContainment = new LazyTypeDescription.TypeContainment.WithinType(str2, false);
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (str2 == null || str3 == null || !str.equals(this.internalName + "$" + str3)) {
                    return;
                }
                this.declaredTypes.add(TemperatureUnitEnum.L + str + ";");
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                AnnotationRegistrant withIndex;
                TypeReference typeReference = new TypeReference(i);
                int sort = typeReference.getSort();
                if (sort == 0) {
                    withIndex = new AnnotationRegistrant.ForTypeVariable.WithIndex(str, typePath, typeReference.getTypeParameterIndex(), this.typeVariableAnnotationTokens);
                } else if (sort == 16) {
                    withIndex = new AnnotationRegistrant.ForTypeVariable.WithIndex(str, typePath, typeReference.getSuperTypeIndex(), this.superTypeAnnotationTokens);
                } else if (sort == 17) {
                    withIndex = new AnnotationRegistrant.ForTypeVariable.WithIndex.DoubleIndexed(str, typePath, typeReference.getTypeParameterBoundIndex(), typeReference.getTypeParameterIndex(), this.typeVariableBoundsAnnotationTokens);
                } else {
                    throw new IllegalArgumentException("Unexpected type reference: " + typeReference.getSort());
                }
                return new AnnotationExtractor(withIndex, new ComponentTypeLocator.ForAnnotationProperty(Default.this, str));
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public AnnotationVisitor visitAnnotation(String str, boolean z) {
                return new AnnotationExtractor(this, str, this.annotationTokens, new ComponentTypeLocator.ForAnnotationProperty(Default.this, str));
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
                return new FieldExtractor(i & 65535, str, str2, str3);
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
                return str.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME) ? Default.IGNORE_METHOD : new MethodExtractor(i & 65535, str, str2, str3, strArr);
            }

            protected TypeDescription toTypeDescription() {
                return new LazyTypeDescription(Default.this, this.actualModifiers, this.modifiers, this.internalName, this.superClassName, this.interfaceName, this.genericSignature, this.typeContainment, this.declaringTypeName, this.declaredTypes, this.anonymousType, this.superTypeAnnotationTokens, this.typeVariableAnnotationTokens, this.typeVariableBoundsAnnotationTokens, this.annotationTokens, this.fieldTokens, this.methodTokens);
            }

            protected class AnnotationExtractor extends AnnotationVisitor {
                private final AnnotationRegistrant annotationRegistrant;
                private final ComponentTypeLocator componentTypeLocator;

                protected AnnotationExtractor(TypeExtractor typeExtractor, String str, List<LazyTypeDescription.AnnotationToken> list, ComponentTypeLocator componentTypeLocator) {
                    this(new AnnotationRegistrant.ForByteCodeElement(str, list), componentTypeLocator);
                }

                protected AnnotationExtractor(TypeExtractor typeExtractor, String str, int i, Map<Integer, List<LazyTypeDescription.AnnotationToken>> map, ComponentTypeLocator componentTypeLocator) {
                    this(new AnnotationRegistrant.ForByteCodeElement.WithIndex(str, i, map), componentTypeLocator);
                }

                protected AnnotationExtractor(AnnotationRegistrant annotationRegistrant, ComponentTypeLocator componentTypeLocator) {
                    super(Opcodes.ASM6);
                    this.annotationRegistrant = annotationRegistrant;
                    this.componentTypeLocator = componentTypeLocator;
                }

                @Override // net.bytebuddy.jar.asm.AnnotationVisitor
                public void visit(String str, Object obj) {
                    this.annotationRegistrant.register(str, obj instanceof Type ? new AbstractBase.RawTypeValue(Default.this, (Type) obj) : AnnotationValue.ForConstant.of(obj));
                }

                @Override // net.bytebuddy.jar.asm.AnnotationVisitor
                public void visitEnum(String str, String str2, String str3) {
                    this.annotationRegistrant.register(str, new AbstractBase.RawEnumerationValue(Default.this, str2, str3));
                }

                @Override // net.bytebuddy.jar.asm.AnnotationVisitor
                public AnnotationVisitor visitAnnotation(String str, String str2) {
                    return TypeExtractor.this.new AnnotationExtractor(new AnnotationLookup(str2, str), new ComponentTypeLocator.ForAnnotationProperty(Default.this, str2));
                }

                @Override // net.bytebuddy.jar.asm.AnnotationVisitor
                public AnnotationVisitor visitArray(String str) {
                    return TypeExtractor.this.new AnnotationExtractor(new ArrayLookup(str, this.componentTypeLocator.bind(str)), ComponentTypeLocator.Illegal.INSTANCE);
                }

                @Override // net.bytebuddy.jar.asm.AnnotationVisitor
                public void visitEnd() {
                    this.annotationRegistrant.onComplete();
                }

                protected class ArrayLookup implements AnnotationRegistrant {
                    private final AbstractBase.RawDescriptionArray.ComponentTypeReference componentTypeReference;
                    private final String name;
                    private final List<AnnotationValue<?, ?>> values = new ArrayList();

                    protected ArrayLookup(String str, AbstractBase.RawDescriptionArray.ComponentTypeReference componentTypeReference) {
                        this.name = str;
                        this.componentTypeReference = componentTypeReference;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant
                    public void register(String str, AnnotationValue<?, ?> annotationValue) {
                        this.values.add(annotationValue);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant
                    public void onComplete() {
                        AnnotationExtractor.this.annotationRegistrant.register(this.name, new AbstractBase.RawDescriptionArray(Default.this, this.componentTypeReference, this.values));
                    }
                }

                protected class AnnotationLookup implements AnnotationRegistrant {
                    private final String descriptor;
                    private final String name;
                    private final Map<String, AnnotationValue<?, ?>> values = new HashMap();

                    protected AnnotationLookup(String str, String str2) {
                        this.descriptor = str;
                        this.name = str2;
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant
                    public void register(String str, AnnotationValue<?, ?> annotationValue) {
                        this.values.put(str, annotationValue);
                    }

                    @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant
                    public void onComplete() {
                        AnnotationExtractor.this.annotationRegistrant.register(this.name, new AbstractBase.RawAnnotationValue(Default.this, new LazyTypeDescription.AnnotationToken(this.descriptor, this.values)));
                    }
                }
            }

            protected class FieldExtractor extends FieldVisitor {
                private final List<LazyTypeDescription.AnnotationToken> annotationTokens;
                private final String descriptor;
                private final String genericSignature;
                private final String internalName;
                private final int modifiers;
                private final Map<String, List<LazyTypeDescription.AnnotationToken>> typeAnnotationTokens;

                protected FieldExtractor(int i, String str, String str2, String str3) {
                    super(Opcodes.ASM6);
                    this.modifiers = i;
                    this.internalName = str;
                    this.descriptor = str2;
                    this.genericSignature = str3;
                    this.typeAnnotationTokens = new HashMap();
                    this.annotationTokens = new ArrayList();
                }

                @Override // net.bytebuddy.jar.asm.FieldVisitor
                public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                    TypeReference typeReference = new TypeReference(i);
                    if (typeReference.getSort() == 19) {
                        return TypeExtractor.this.new AnnotationExtractor(new AnnotationRegistrant.ForTypeVariable(str, typePath, this.typeAnnotationTokens), new ComponentTypeLocator.ForAnnotationProperty(Default.this, str));
                    }
                    throw new IllegalStateException("Unexpected type reference on field: " + typeReference.getSort());
                }

                @Override // net.bytebuddy.jar.asm.FieldVisitor
                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    return new AnnotationExtractor(TypeExtractor.this, str, this.annotationTokens, new ComponentTypeLocator.ForAnnotationProperty(Default.this, str));
                }

                @Override // net.bytebuddy.jar.asm.FieldVisitor
                public void visitEnd() {
                    TypeExtractor.this.fieldTokens.add(new LazyTypeDescription.FieldToken(this.internalName, this.modifiers, this.descriptor, this.genericSignature, this.typeAnnotationTokens, this.annotationTokens));
                }
            }

            protected class MethodExtractor extends MethodVisitor implements AnnotationRegistrant {
                private final List<LazyTypeDescription.AnnotationToken> annotationTokens;
                private AnnotationValue<?, ?> defaultValue;
                private final String descriptor;
                private final String[] exceptionName;
                private final Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> exceptionTypeAnnotationTokens;
                private Label firstLabel;
                private final String genericSignature;
                private final String internalName;
                private final ParameterBag legacyParameterBag;
                private final int modifiers;
                private final Map<Integer, List<LazyTypeDescription.AnnotationToken>> parameterAnnotationTokens;
                private final List<LazyTypeDescription.MethodToken.ParameterToken> parameterTokens;
                private final Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> parameterTypeAnnotationTokens;
                private final Map<String, List<LazyTypeDescription.AnnotationToken>> receiverTypeAnnotationTokens;
                private final Map<String, List<LazyTypeDescription.AnnotationToken>> returnTypeAnnotationTokens;
                private final Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> typeVariableAnnotationTokens;
                private final Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> typeVariableBoundAnnotationTokens;

                @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant
                public void onComplete() {
                }

                protected MethodExtractor(int i, String str, String str2, String str3, String[] strArr) {
                    super(Opcodes.ASM6);
                    this.modifiers = i;
                    this.internalName = str;
                    this.descriptor = str2;
                    this.genericSignature = str3;
                    this.exceptionName = strArr;
                    this.typeVariableAnnotationTokens = new HashMap();
                    this.typeVariableBoundAnnotationTokens = new HashMap();
                    this.returnTypeAnnotationTokens = new HashMap();
                    this.parameterTypeAnnotationTokens = new HashMap();
                    this.exceptionTypeAnnotationTokens = new HashMap();
                    this.receiverTypeAnnotationTokens = new HashMap();
                    this.annotationTokens = new ArrayList();
                    this.parameterAnnotationTokens = new HashMap();
                    this.parameterTokens = new ArrayList();
                    this.legacyParameterBag = new ParameterBag(Type.getMethodType(str2).getArgumentTypes());
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                    AnnotationRegistrant withIndex;
                    TypeReference typeReference = new TypeReference(i);
                    int sort = typeReference.getSort();
                    if (sort == 1) {
                        withIndex = new AnnotationRegistrant.ForTypeVariable.WithIndex(str, typePath, typeReference.getTypeParameterIndex(), this.typeVariableAnnotationTokens);
                    } else if (sort == 18) {
                        withIndex = new AnnotationRegistrant.ForTypeVariable.WithIndex.DoubleIndexed(str, typePath, typeReference.getTypeParameterBoundIndex(), typeReference.getTypeParameterIndex(), this.typeVariableBoundAnnotationTokens);
                    } else {
                        switch (sort) {
                            case 20:
                                withIndex = new AnnotationRegistrant.ForTypeVariable(str, typePath, this.returnTypeAnnotationTokens);
                                break;
                            case 21:
                                withIndex = new AnnotationRegistrant.ForTypeVariable(str, typePath, this.receiverTypeAnnotationTokens);
                                break;
                            case 22:
                                withIndex = new AnnotationRegistrant.ForTypeVariable.WithIndex(str, typePath, typeReference.getFormalParameterIndex(), this.parameterTypeAnnotationTokens);
                                break;
                            case 23:
                                withIndex = new AnnotationRegistrant.ForTypeVariable.WithIndex(str, typePath, typeReference.getExceptionIndex(), this.exceptionTypeAnnotationTokens);
                                break;
                            default:
                                throw new IllegalStateException("Unexpected type reference on method: " + typeReference.getSort());
                        }
                    }
                    return TypeExtractor.this.new AnnotationExtractor(withIndex, new ComponentTypeLocator.ForAnnotationProperty(Default.this, str));
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    return new AnnotationExtractor(TypeExtractor.this, str, this.annotationTokens, new ComponentTypeLocator.ForAnnotationProperty(Default.this, str));
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
                    return new AnnotationExtractor(TypeExtractor.this, str, i, this.parameterAnnotationTokens, new ComponentTypeLocator.ForAnnotationProperty(Default.this, str));
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitLabel(Label label) {
                    if (Default.this.readerMode.isExtended() && this.firstLabel == null) {
                        this.firstLabel = label;
                    }
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
                    if (Default.this.readerMode.isExtended() && label == this.firstLabel) {
                        this.legacyParameterBag.register(i, str);
                    }
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitParameter(String str, int i) {
                    this.parameterTokens.add(new LazyTypeDescription.MethodToken.ParameterToken(str, Integer.valueOf(i)));
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public AnnotationVisitor visitAnnotationDefault() {
                    return TypeExtractor.this.new AnnotationExtractor(this, new ComponentTypeLocator.ForArrayType(this.descriptor));
                }

                @Override // net.bytebuddy.pool.TypePool.Default.AnnotationRegistrant
                public void register(String str, AnnotationValue<?, ?> annotationValue) {
                    this.defaultValue = annotationValue;
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitEnd() {
                    List list;
                    List<LazyTypeDescription.MethodToken.ParameterToken> listResolve;
                    List list2 = TypeExtractor.this.methodTokens;
                    String str = this.internalName;
                    int i = this.modifiers;
                    String str2 = this.descriptor;
                    String str3 = this.genericSignature;
                    String[] strArr = this.exceptionName;
                    Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> map = this.typeVariableAnnotationTokens;
                    Map<Integer, Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>>> map2 = this.typeVariableBoundAnnotationTokens;
                    Map<String, List<LazyTypeDescription.AnnotationToken>> map3 = this.returnTypeAnnotationTokens;
                    Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> map4 = this.parameterTypeAnnotationTokens;
                    Map<Integer, Map<String, List<LazyTypeDescription.AnnotationToken>>> map5 = this.exceptionTypeAnnotationTokens;
                    Map<String, List<LazyTypeDescription.AnnotationToken>> map6 = this.receiverTypeAnnotationTokens;
                    List<LazyTypeDescription.AnnotationToken> list3 = this.annotationTokens;
                    Map<Integer, List<LazyTypeDescription.AnnotationToken>> map7 = this.parameterAnnotationTokens;
                    if (this.parameterTokens.isEmpty()) {
                        list = list2;
                        listResolve = this.legacyParameterBag.resolve((this.modifiers & 8) != 0);
                    } else {
                        list = list2;
                        listResolve = this.parameterTokens;
                    }
                    list.add(new LazyTypeDescription.MethodToken(str, i, str2, str3, strArr, map, map2, map3, map4, map5, map6, list3, map7, listResolve, this.defaultValue));
                }
            }
        }
    }

    public static class LazyFacade extends AbstractBase {
        private final TypePool typePool;

        @Override // net.bytebuddy.pool.TypePool.AbstractBase
        protected boolean canEqual(Object obj) {
            return obj instanceof LazyFacade;
        }

        @Override // net.bytebuddy.pool.TypePool.AbstractBase
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof LazyFacade)) {
                return false;
            }
            LazyFacade lazyFacade = (LazyFacade) obj;
            if (!lazyFacade.canEqual(this)) {
                return false;
            }
            TypePool typePool = this.typePool;
            TypePool typePool2 = lazyFacade.typePool;
            return typePool != null ? typePool.equals(typePool2) : typePool2 == null;
        }

        @Override // net.bytebuddy.pool.TypePool.AbstractBase
        public int hashCode() {
            TypePool typePool = this.typePool;
            return 59 + (typePool == null ? 43 : typePool.hashCode());
        }

        public LazyFacade(TypePool typePool) {
            super(CacheProvider.NoOp.INSTANCE);
            this.typePool = typePool;
        }

        @Override // net.bytebuddy.pool.TypePool.AbstractBase
        protected Resolution doDescribe(String str) {
            return new LazyResolution(this.typePool, str);
        }

        @Override // net.bytebuddy.pool.TypePool.AbstractBase, net.bytebuddy.pool.TypePool
        public void clear() {
            this.typePool.clear();
        }

        protected static class LazyResolution implements Resolution {
            private final String name;
            private final TypePool typePool;

            protected boolean canEqual(Object obj) {
                return obj instanceof LazyResolution;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof LazyResolution)) {
                    return false;
                }
                LazyResolution lazyResolution = (LazyResolution) obj;
                if (!lazyResolution.canEqual(this)) {
                    return false;
                }
                TypePool typePool = this.typePool;
                TypePool typePool2 = lazyResolution.typePool;
                if (typePool != null ? !typePool.equals(typePool2) : typePool2 != null) {
                    return false;
                }
                String str = this.name;
                String str2 = lazyResolution.name;
                return str != null ? str.equals(str2) : str2 == null;
            }

            public int hashCode() {
                TypePool typePool = this.typePool;
                int iHashCode = typePool == null ? 43 : typePool.hashCode();
                String str = this.name;
                return ((iHashCode + 59) * 59) + (str != null ? str.hashCode() : 43);
            }

            protected LazyResolution(TypePool typePool, String str) {
                this.typePool = typePool;
                this.name = str;
            }

            @Override // net.bytebuddy.pool.TypePool.Resolution
            public boolean isResolved() {
                return this.typePool.describe(this.name).isResolved();
            }

            @Override // net.bytebuddy.pool.TypePool.Resolution
            public TypeDescription resolve() {
                return new LazyTypeDescription(this.typePool, this.name);
            }
        }

        protected static class LazyTypeDescription extends TypeDescription.AbstractBase.OfSimpleType.WithDelegation {
            private final String name;
            private final TypePool typePool;

            protected LazyTypeDescription(TypePool typePool, String str) {
                this.typePool = typePool;
                this.name = str;
            }

            @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
            public String getName() {
                return this.name;
            }

            @Override // net.bytebuddy.description.type.TypeDescription.AbstractBase.OfSimpleType.WithDelegation
            protected TypeDescription delegate() {
                return this.typePool.describe(this.name).resolve();
            }
        }
    }

    public static class ClassLoading extends AbstractBase.Hierarchical {
        private static final ClassLoader BOOTSTRAP_CLASS_LOADER = null;
        private final ClassLoader classLoader;

        @Override // net.bytebuddy.pool.TypePool.AbstractBase.Hierarchical, net.bytebuddy.pool.TypePool.AbstractBase
        protected boolean canEqual(Object obj) {
            return obj instanceof ClassLoading;
        }

        @Override // net.bytebuddy.pool.TypePool.AbstractBase.Hierarchical, net.bytebuddy.pool.TypePool.AbstractBase
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ClassLoading)) {
                return false;
            }
            ClassLoading classLoading = (ClassLoading) obj;
            if (!classLoading.canEqual(this) || !super.equals(obj)) {
                return false;
            }
            ClassLoader classLoader = this.classLoader;
            ClassLoader classLoader2 = classLoading.classLoader;
            return classLoader != null ? classLoader.equals(classLoader2) : classLoader2 == null;
        }

        @Override // net.bytebuddy.pool.TypePool.AbstractBase.Hierarchical, net.bytebuddy.pool.TypePool.AbstractBase
        public int hashCode() {
            int iHashCode = super.hashCode() + 59;
            ClassLoader classLoader = this.classLoader;
            return (iHashCode * 59) + (classLoader == null ? 43 : classLoader.hashCode());
        }

        public ClassLoading(CacheProvider cacheProvider, TypePool typePool, ClassLoader classLoader) {
            super(cacheProvider, typePool);
            this.classLoader = classLoader;
        }

        public static TypePool of(ClassLoader classLoader) {
            return of(classLoader, Empty.INSTANCE);
        }

        public static TypePool of(ClassLoader classLoader, TypePool typePool) {
            return new ClassLoading(CacheProvider.NoOp.INSTANCE, typePool, classLoader);
        }

        public static TypePool ofBootPath() {
            return of(BOOTSTRAP_CLASS_LOADER);
        }

        public static TypePool ofClassPath() {
            return of(ClassLoader.getSystemClassLoader());
        }

        @Override // net.bytebuddy.pool.TypePool.AbstractBase
        public Resolution doDescribe(String str) {
            try {
                return new Resolution.Simple(new TypeDescription.ForLoadedType(Class.forName(str, false, this.classLoader)));
            } catch (ClassNotFoundException unused) {
                return new Resolution.Illegal(str);
            }
        }
    }

    public static class Explicit extends AbstractBase.Hierarchical {
        private final Map<String, TypeDescription> types;

        @Override // net.bytebuddy.pool.TypePool.AbstractBase.Hierarchical, net.bytebuddy.pool.TypePool.AbstractBase
        protected boolean canEqual(Object obj) {
            return obj instanceof Explicit;
        }

        @Override // net.bytebuddy.pool.TypePool.AbstractBase.Hierarchical, net.bytebuddy.pool.TypePool.AbstractBase
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Explicit)) {
                return false;
            }
            Explicit explicit = (Explicit) obj;
            if (!explicit.canEqual(this) || !super.equals(obj)) {
                return false;
            }
            Map<String, TypeDescription> map = this.types;
            Map<String, TypeDescription> map2 = explicit.types;
            return map != null ? map.equals(map2) : map2 == null;
        }

        @Override // net.bytebuddy.pool.TypePool.AbstractBase.Hierarchical, net.bytebuddy.pool.TypePool.AbstractBase
        public int hashCode() {
            int iHashCode = super.hashCode() + 59;
            Map<String, TypeDescription> map = this.types;
            return (iHashCode * 59) + (map == null ? 43 : map.hashCode());
        }

        public Explicit(Map<String, TypeDescription> map) {
            this(Empty.INSTANCE, map);
        }

        public Explicit(TypePool typePool, Map<String, TypeDescription> map) {
            super(CacheProvider.NoOp.INSTANCE, typePool);
            this.types = map;
        }

        @Override // net.bytebuddy.pool.TypePool.AbstractBase
        protected Resolution doDescribe(String str) {
            TypeDescription typeDescription = this.types.get(str);
            return typeDescription == null ? new Resolution.Illegal(str) : new Resolution.Simple(typeDescription);
        }
    }
}
