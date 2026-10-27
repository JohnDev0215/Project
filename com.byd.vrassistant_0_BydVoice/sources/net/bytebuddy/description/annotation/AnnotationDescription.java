package net.bytebuddy.description.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.IncompleteAnnotationException;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.privilege.SetAccessibleAction;

/* JADX INFO: loaded from: classes3.dex */
public interface AnnotationDescription {
    public static final Loadable<?> UNDEFINED = null;

    public interface Loadable<S extends Annotation> extends AnnotationDescription {
        S load() throws ClassNotFoundException;

        S loadSilent();
    }

    TypeDescription getAnnotationType();

    Set<ElementType> getElementTypes();

    RetentionPolicy getRetention();

    AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape);

    boolean isDocumented();

    boolean isInherited();

    <T extends Annotation> Loadable<T> prepare(Class<T> cls);

    public static class AnnotationInvocationHandler<T extends Annotation> implements InvocationHandler {
        private static final String EQUALS = "equals";
        private static final String HASH_CODE = "hashCode";
        private static final String TO_STRING = "toString";
        private final Class<? extends Annotation> annotationType;
        private final LinkedHashMap<Method, AnnotationValue.Loaded<?>> values;

        /* JADX WARN: Multi-variable type inference failed */
        protected AnnotationInvocationHandler(Class<T> cls, LinkedHashMap<Method, AnnotationValue.Loaded<?>> linkedHashMap) {
            this.annotationType = cls;
            this.values = linkedHashMap;
        }

        public static <S extends Annotation> S of(ClassLoader classLoader, Class<S> cls, Map<String, ? extends AnnotationValue<?, ?>> map) throws ClassNotFoundException {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Method method : cls.getDeclaredMethods()) {
                AnnotationValue<?, ?> annotationValueDefaultValueOf = map.get(method.getName());
                if (annotationValueDefaultValueOf == null) {
                    annotationValueDefaultValueOf = defaultValueOf(method);
                }
                linkedHashMap.put(method, annotationValueDefaultValueOf.load(classLoader));
            }
            return (S) Proxy.newProxyInstance(classLoader, new Class[]{cls}, new AnnotationInvocationHandler(cls, linkedHashMap));
        }

        private static AnnotationValue<?, ?> defaultValueOf(Method method) {
            Object defaultValue = method.getDefaultValue();
            if (defaultValue == null) {
                return MissingValue.of(method);
            }
            return ForLoadedAnnotation.asValue(defaultValue, method.getReturnType());
        }

        private static Class<?> asWrapper(Class<?> cls) {
            if (!cls.isPrimitive()) {
                return cls;
            }
            if (cls == Boolean.TYPE) {
                return Boolean.class;
            }
            if (cls == Byte.TYPE) {
                return Byte.class;
            }
            if (cls == Short.TYPE) {
                return Short.class;
            }
            if (cls == Character.TYPE) {
                return Character.class;
            }
            if (cls == Integer.TYPE) {
                return Integer.class;
            }
            if (cls == Long.TYPE) {
                return Long.class;
            }
            if (cls == Float.TYPE) {
                return Float.class;
            }
            return cls == Double.TYPE ? Double.class : cls;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (method.getDeclaringClass() != this.annotationType) {
                if (method.getName().equals(HASH_CODE)) {
                    return Integer.valueOf(hashCodeRepresentation());
                }
                if (method.getName().equals(EQUALS) && method.getParameterTypes().length == 1) {
                    return Boolean.valueOf(equalsRepresentation(obj, objArr[0]));
                }
                if (method.getName().equals(TO_STRING)) {
                    return toStringRepresentation();
                }
                return this.annotationType;
            }
            Object objResolve = this.values.get(method).resolve();
            if (asWrapper(method.getReturnType()).isAssignableFrom(objResolve.getClass())) {
                return objResolve;
            }
            throw new AnnotationTypeMismatchException(method, objResolve.getClass().toString());
        }

        protected String toStringRepresentation() {
            StringBuilder sb = new StringBuilder("@");
            sb.append(this.annotationType.getName());
            sb.append('(');
            boolean z = true;
            for (Map.Entry<Method, AnnotationValue.Loaded<?>> entry : this.values.entrySet()) {
                if (entry.getValue().getState().isDefined()) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(entry.getKey().getName()).append(SignatureVisitor.INSTANCEOF).append(entry.getValue().toString());
                }
            }
            sb.append(')');
            return sb.toString();
        }

        private int hashCodeRepresentation() {
            int iHashCode = 0;
            for (Map.Entry<Method, AnnotationValue.Loaded<?>> entry : this.values.entrySet()) {
                if (entry.getValue().getState().isDefined()) {
                    iHashCode += entry.getValue().hashCode() ^ (entry.getKey().getName().hashCode() * 127);
                }
            }
            return iHashCode;
        }

        private boolean equalsRepresentation(Object obj, Object obj2) {
            if (obj == obj2) {
                return true;
            }
            if (!this.annotationType.isInstance(obj2)) {
                return false;
            }
            if (Proxy.isProxyClass(obj2.getClass())) {
                InvocationHandler invocationHandler = Proxy.getInvocationHandler(obj2);
                if (invocationHandler instanceof AnnotationInvocationHandler) {
                    return invocationHandler.equals(this);
                }
            }
            try {
                for (Map.Entry<Method, AnnotationValue.Loaded<?>> entry : this.values.entrySet()) {
                    if (!entry.getValue().represents(entry.getKey().invoke(obj2, new Object[0]))) {
                        return false;
                    }
                }
                return true;
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not access annotation property", e);
            } catch (InvocationTargetException unused) {
                return false;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnnotationInvocationHandler)) {
                return false;
            }
            AnnotationInvocationHandler annotationInvocationHandler = (AnnotationInvocationHandler) obj;
            if (!this.annotationType.equals(annotationInvocationHandler.annotationType)) {
                return false;
            }
            for (Map.Entry<Method, AnnotationValue.Loaded<?>> entry : this.values.entrySet()) {
                if (!entry.getValue().equals(annotationInvocationHandler.values.get(entry.getKey()))) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int iHashCode = (this.annotationType.hashCode() * 31) + this.values.hashCode();
            Iterator<Map.Entry<Method, AnnotationValue.Loaded<?>>> it = this.values.entrySet().iterator();
            while (it.hasNext()) {
                iHashCode = (iHashCode * 31) + it.next().getValue().hashCode();
            }
            return iHashCode;
        }

        protected static class MissingValue extends AnnotationValue.Loaded.AbstractBase<Void> implements AnnotationValue<Void, Void> {
            private final Class<? extends Annotation> annotationType;
            private final String property;

            @Override // net.bytebuddy.description.annotation.AnnotationValue
            public AnnotationValue.Loaded<Void> load(ClassLoader classLoader) {
                return this;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue
            public AnnotationValue.Loaded<Void> loadSilent(ClassLoader classLoader) {
                return this;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public boolean represents(Object obj) {
                return false;
            }

            protected MissingValue(Class<? extends Annotation> cls, String str) {
                this.annotationType = cls;
                this.property = str;
            }

            protected static AnnotationValue<?, ?> of(Method method) {
                return new MissingValue(method.getDeclaringClass(), method.getName());
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public AnnotationValue.Loaded.State getState() {
                return AnnotationValue.Loaded.State.UNDEFINED;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded, net.bytebuddy.description.annotation.AnnotationValue
            public Void resolve() {
                throw new IncompleteAnnotationException(this.annotationType, this.property);
            }
        }
    }

    public static abstract class AbstractBase implements AnnotationDescription {
        private static final ElementType[] DEFAULT_TARGET = {ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PACKAGE, ElementType.PARAMETER, ElementType.TYPE};

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public RetentionPolicy getRetention() {
            Loadable loadableOfType = getAnnotationType().getDeclaredAnnotations().ofType(Retention.class);
            if (loadableOfType == null) {
                return RetentionPolicy.CLASS;
            }
            return ((Retention) loadableOfType.loadSilent()).value();
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public Set<ElementType> getElementTypes() {
            Loadable loadableOfType = getAnnotationType().getDeclaredAnnotations().ofType(Target.class);
            return new HashSet(Arrays.asList(loadableOfType == null ? DEFAULT_TARGET : ((Target) loadableOfType.loadSilent()).value()));
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public boolean isInherited() {
            return getAnnotationType().getDeclaredAnnotations().isAnnotationPresent(Inherited.class);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public boolean isDocumented() {
            return getAnnotationType().getDeclaredAnnotations().isAnnotationPresent(Documented.class);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AnnotationDescription)) {
                return false;
            }
            AnnotationDescription annotationDescription = (AnnotationDescription) obj;
            TypeDescription annotationType = getAnnotationType();
            if (!annotationDescription.getAnnotationType().equals(annotationType)) {
                return false;
            }
            for (MethodDescription.InDefinedShape inDefinedShape : annotationType.getDeclaredMethods()) {
                if (!getValue(inDefinedShape).equals(annotationDescription.getValue(inDefinedShape))) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            Iterator it = getAnnotationType().getDeclaredMethods().iterator();
            int iHashCode = 0;
            while (it.hasNext()) {
                iHashCode += getValue((MethodDescription.InDefinedShape) it.next()).hashCode() * 31;
            }
            return iHashCode;
        }

        public String toString() {
            TypeDescription annotationType = getAnnotationType();
            StringBuilder sbAppend = new StringBuilder("@").append(annotationType.getName()).append('(');
            boolean z = true;
            for (MethodDescription.InDefinedShape inDefinedShape : annotationType.getDeclaredMethods()) {
                if (z) {
                    z = false;
                } else {
                    sbAppend.append(", ");
                }
                sbAppend.append(inDefinedShape.getName()).append(SignatureVisitor.INSTANCEOF).append(getValue(inDefinedShape));
            }
            return sbAppend.append(')').toString();
        }

        public static abstract class ForPrepared<S extends Annotation> extends AbstractBase implements Loadable<S> {
            @Override // net.bytebuddy.description.annotation.AnnotationDescription.Loadable
            public S loadSilent() {
                try {
                    return load();
                } catch (ClassNotFoundException e) {
                    throw new IllegalStateException("Could not load annotation type or referenced type", e);
                }
            }
        }
    }

    public static class ForLoadedAnnotation<S extends Annotation> extends AbstractBase.ForPrepared<S> {
        private final S annotation;
        private final Class<S> annotationType;

        protected ForLoadedAnnotation(S s) {
            this(s, s.annotationType());
        }

        private ForLoadedAnnotation(S s, Class<S> cls) {
            this.annotation = s;
            this.annotationType = cls;
        }

        public static <U extends Annotation> Loadable<U> of(U u) {
            return new ForLoadedAnnotation(u);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription.Loadable
        public S load() throws ClassNotFoundException {
            return this.annotationType == this.annotation.annotationType() ? this.annotation : (S) AnnotationInvocationHandler.of(this.annotationType.getClassLoader(), this.annotationType, asValue(this.annotation));
        }

        private static Map<String, AnnotationValue<?, ?>> asValue(Annotation annotation) {
            HashMap map = new HashMap();
            for (Method method : annotation.annotationType().getDeclaredMethods()) {
                try {
                    map.put(method.getName(), asValue(method.invoke(annotation, new Object[0]), method.getReturnType()));
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + method, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot read " + method, e2.getCause());
                }
            }
            return map;
        }

        public static AnnotationValue<?, ?> asValue(Object obj, Class<?> cls) {
            if (Enum.class.isAssignableFrom(cls)) {
                return AnnotationValue.ForEnumerationDescription.of(new EnumerationDescription.ForLoadedEnumeration((Enum) obj));
            }
            int i = 0;
            if (Enum[].class.isAssignableFrom(cls)) {
                Enum[] enumArr = (Enum[]) obj;
                EnumerationDescription[] enumerationDescriptionArr = new EnumerationDescription[enumArr.length];
                int length = enumArr.length;
                int i2 = 0;
                while (i < length) {
                    enumerationDescriptionArr[i2] = new EnumerationDescription.ForLoadedEnumeration(enumArr[i]);
                    i++;
                    i2++;
                }
                return AnnotationValue.ForDescriptionArray.of(new TypeDescription.ForLoadedType(cls.getComponentType()), enumerationDescriptionArr);
            }
            if (Annotation.class.isAssignableFrom(cls)) {
                return AnnotationValue.ForAnnotationDescription.of(new TypeDescription.ForLoadedType(cls), asValue((Annotation) obj));
            }
            if (Annotation[].class.isAssignableFrom(cls)) {
                Annotation[] annotationArr = (Annotation[]) obj;
                AnnotationDescription[] annotationDescriptionArr = new AnnotationDescription[annotationArr.length];
                int length2 = annotationArr.length;
                int i3 = 0;
                while (i < length2) {
                    annotationDescriptionArr[i3] = new Latent(new TypeDescription.ForLoadedType(cls.getComponentType()), asValue(annotationArr[i]));
                    i++;
                    i3++;
                }
                return AnnotationValue.ForDescriptionArray.of(new TypeDescription.ForLoadedType(cls.getComponentType()), annotationDescriptionArr);
            }
            if (Class.class.isAssignableFrom(cls)) {
                return AnnotationValue.ForTypeDescription.of(new TypeDescription.ForLoadedType((Class) obj));
            }
            if (Class[].class.isAssignableFrom(cls)) {
                Class[] clsArr = (Class[]) obj;
                TypeDescription[] typeDescriptionArr = new TypeDescription[clsArr.length];
                int length3 = clsArr.length;
                int i4 = 0;
                while (i < length3) {
                    typeDescriptionArr[i4] = new TypeDescription.ForLoadedType(clsArr[i]);
                    i++;
                    i4++;
                }
                return AnnotationValue.ForDescriptionArray.of(typeDescriptionArr);
            }
            return AnnotationValue.ForConstant.of(obj);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape) {
            if (!inDefinedShape.getDeclaringType().represents(this.annotation.annotationType())) {
                throw new IllegalArgumentException(inDefinedShape + " does not represent " + this.annotation.annotationType());
            }
            try {
                boolean zIsPublic = inDefinedShape.getDeclaringType().isPublic();
                Method loadedMethod = inDefinedShape instanceof MethodDescription.ForLoadedMethod ? ((MethodDescription.ForLoadedMethod) inDefinedShape).getLoadedMethod() : null;
                if (loadedMethod == null || loadedMethod.getDeclaringClass() != this.annotation.annotationType() || (!zIsPublic && !loadedMethod.isAccessible())) {
                    loadedMethod = this.annotation.annotationType().getMethod(inDefinedShape.getName(), new Class[0]);
                    if (!zIsPublic) {
                        AccessController.doPrivileged(new SetAccessibleAction(loadedMethod));
                    }
                }
                return asValue(loadedMethod.invoke(this.annotation, new Object[0]), loadedMethod.getReturnType());
            } catch (InvocationTargetException e) {
                throw new IllegalStateException("Error reading annotation property " + inDefinedShape, e.getCause());
            } catch (Exception e2) {
                throw new IllegalStateException("Cannot access annotation property " + inDefinedShape, e2);
            }
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public <T extends Annotation> Loadable<T> prepare(Class<T> cls) {
            if (this.annotation.annotationType().getName().equals(cls.getName())) {
                return cls == this.annotation.annotationType() ? this : new ForLoadedAnnotation(this.annotation, cls);
            }
            throw new IllegalArgumentException(cls + " does not represent " + this.annotation.annotationType());
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public TypeDescription getAnnotationType() {
            return new TypeDescription.ForLoadedType(this.annotation.annotationType());
        }
    }

    public static class Latent extends AbstractBase {
        private final TypeDescription annotationType;
        private final Map<String, ? extends AnnotationValue<?, ?>> annotationValues;

        protected Latent(TypeDescription typeDescription, Map<String, ? extends AnnotationValue<?, ?>> map) {
            this.annotationType = typeDescription;
            this.annotationValues = map;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationDescription
        public AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape) {
            AnnotationValue<?, ?> annotationValue = this.annotationValues.get(inDefinedShape.getName());
            if (annotationValue != null) {
                return annotationValue;
            }
            AnnotationValue<?, ?> defaultValue = inDefinedShape.getDefaultValue();
            if (defaultValue != null) {
                return defaultValue;
            }
            throw new IllegalArgumentException("No value defined for: " + inDefinedShape);
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
            return new Loadable<>(cls);
        }

        protected class Loadable<S extends Annotation> extends AbstractBase.ForPrepared<S> {
            private final Class<S> annotationType;

            protected Loadable(Class<S> cls) {
                this.annotationType = cls;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationDescription.Loadable
            public S load() throws ClassNotFoundException {
                return (S) AnnotationInvocationHandler.of(this.annotationType.getClassLoader(), this.annotationType, Latent.this.annotationValues);
            }

            @Override // net.bytebuddy.description.annotation.AnnotationDescription
            public AnnotationValue<?, ?> getValue(MethodDescription.InDefinedShape inDefinedShape) {
                return Latent.this.getValue(inDefinedShape);
            }

            @Override // net.bytebuddy.description.annotation.AnnotationDescription
            public TypeDescription getAnnotationType() {
                return new TypeDescription.ForLoadedType(this.annotationType);
            }

            @Override // net.bytebuddy.description.annotation.AnnotationDescription
            public <T extends Annotation> Loadable<T> prepare(Class<T> cls) {
                return Latent.this.prepare((Class) cls);
            }
        }
    }

    public static class Builder {
        private final TypeDescription annotationType;
        private final Map<String, AnnotationValue<?, ?>> annotationValues;

        protected boolean canEqual(Object obj) {
            return obj instanceof Builder;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Builder)) {
                return false;
            }
            Builder builder = (Builder) obj;
            if (!builder.canEqual(this)) {
                return false;
            }
            TypeDescription typeDescription = this.annotationType;
            TypeDescription typeDescription2 = builder.annotationType;
            if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                return false;
            }
            Map<String, AnnotationValue<?, ?>> map = this.annotationValues;
            Map<String, AnnotationValue<?, ?>> map2 = builder.annotationValues;
            return map != null ? map.equals(map2) : map2 == null;
        }

        public int hashCode() {
            TypeDescription typeDescription = this.annotationType;
            int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
            Map<String, AnnotationValue<?, ?>> map = this.annotationValues;
            return ((iHashCode + 59) * 59) + (map != null ? map.hashCode() : 43);
        }

        protected Builder(TypeDescription typeDescription, Map<String, AnnotationValue<?, ?>> map) {
            this.annotationType = typeDescription;
            this.annotationValues = map;
        }

        public static Builder ofType(Class<? extends Annotation> cls) {
            return ofType(new TypeDescription.ForLoadedType(cls));
        }

        public static Builder ofType(TypeDescription typeDescription) {
            if (!typeDescription.isAnnotation()) {
                throw new IllegalArgumentException("Not an annotation type: " + typeDescription);
            }
            return new Builder(typeDescription, Collections.emptyMap());
        }

        public Builder define(String str, AnnotationValue<?, ?> annotationValue) {
            MethodList methodListFilter = this.annotationType.getDeclaredMethods().filter(ElementMatchers.named(str));
            if (methodListFilter.isEmpty()) {
                throw new IllegalArgumentException(this.annotationType + " does not define a property named " + str);
            }
            if (!((MethodDescription) methodListFilter.getOnly()).getReturnType().asErasure().isAnnotationValue(annotationValue.resolve())) {
                throw new IllegalArgumentException(annotationValue + " cannot be assigned to " + str);
            }
            HashMap map = new HashMap();
            map.putAll(this.annotationValues);
            if (map.put(((MethodDescription) methodListFilter.getOnly()).getName(), annotationValue) != null) {
                throw new IllegalArgumentException("Property already defined: " + str);
            }
            return new Builder(this.annotationType, map);
        }

        public Builder define(String str, Enum<?> r3) {
            return define(str, new EnumerationDescription.ForLoadedEnumeration(r3));
        }

        public Builder define(String str, TypeDescription typeDescription, String str2) {
            return define(str, new EnumerationDescription.Latent(typeDescription, str2));
        }

        public Builder define(String str, EnumerationDescription enumerationDescription) {
            return define(str, AnnotationValue.ForEnumerationDescription.of(enumerationDescription));
        }

        public Builder define(String str, Annotation annotation) {
            return define(str, new ForLoadedAnnotation(annotation));
        }

        public Builder define(String str, AnnotationDescription annotationDescription) {
            return define(str, new AnnotationValue.ForAnnotationDescription(annotationDescription));
        }

        public Builder define(String str, Class<?> cls) {
            return define(str, new TypeDescription.ForLoadedType(cls));
        }

        public Builder define(String str, TypeDescription typeDescription) {
            return define(str, AnnotationValue.ForTypeDescription.of(typeDescription));
        }

        public <T extends Enum<?>> Builder defineEnumerationArray(String str, Class<T> cls, T... tArr) {
            EnumerationDescription[] enumerationDescriptionArr = new EnumerationDescription[tArr.length];
            int length = tArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                enumerationDescriptionArr[i2] = new EnumerationDescription.ForLoadedEnumeration(tArr[i]);
                i++;
                i2++;
            }
            return defineEnumerationArray(str, new TypeDescription.ForLoadedType(cls), enumerationDescriptionArr);
        }

        public Builder defineEnumerationArray(String str, TypeDescription typeDescription, String... strArr) {
            if (!typeDescription.isEnum()) {
                throw new IllegalArgumentException("Not an enumeration type: " + typeDescription);
            }
            EnumerationDescription[] enumerationDescriptionArr = new EnumerationDescription[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                enumerationDescriptionArr[i] = new EnumerationDescription.Latent(typeDescription, strArr[i]);
            }
            return defineEnumerationArray(str, typeDescription, enumerationDescriptionArr);
        }

        public Builder defineEnumerationArray(String str, TypeDescription typeDescription, EnumerationDescription... enumerationDescriptionArr) {
            return define(str, AnnotationValue.ForDescriptionArray.of(typeDescription, enumerationDescriptionArr));
        }

        public <T extends Annotation> Builder defineAnnotationArray(String str, Class<T> cls, T... tArr) {
            return defineAnnotationArray(str, new TypeDescription.ForLoadedType(cls), (AnnotationDescription[]) new AnnotationList.ForLoadedAnnotations(tArr).toArray(new AnnotationDescription[tArr.length]));
        }

        public Builder defineAnnotationArray(String str, TypeDescription typeDescription, AnnotationDescription... annotationDescriptionArr) {
            return define(str, AnnotationValue.ForDescriptionArray.of(typeDescription, annotationDescriptionArr));
        }

        public Builder defineTypeArray(String str, Class<?>... clsArr) {
            return defineTypeArray(str, (TypeDescription[]) new TypeList.ForLoadedTypes(clsArr).toArray(new TypeDescription[clsArr.length]));
        }

        public Builder defineTypeArray(String str, TypeDescription... typeDescriptionArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForDescriptionArray.of(typeDescriptionArr));
        }

        public Builder define(String str, boolean z) {
            return define(str, AnnotationValue.ForConstant.of(z));
        }

        public Builder define(String str, byte b) {
            return define(str, AnnotationValue.ForConstant.of(b));
        }

        public Builder define(String str, char c) {
            return define(str, AnnotationValue.ForConstant.of(c));
        }

        public Builder define(String str, short s) {
            return define(str, AnnotationValue.ForConstant.of(s));
        }

        public Builder define(String str, int i) {
            return define(str, AnnotationValue.ForConstant.of(i));
        }

        public Builder define(String str, long j) {
            return define(str, AnnotationValue.ForConstant.of(j));
        }

        public Builder define(String str, float f) {
            return define(str, AnnotationValue.ForConstant.of(f));
        }

        public Builder define(String str, double d) {
            return define(str, AnnotationValue.ForConstant.of(d));
        }

        public Builder define(String str, String str2) {
            return define(str, AnnotationValue.ForConstant.of(str2));
        }

        public Builder defineArray(String str, boolean... zArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(zArr));
        }

        public Builder defineArray(String str, byte... bArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(bArr));
        }

        public Builder defineArray(String str, char... cArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(cArr));
        }

        public Builder defineArray(String str, short... sArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(sArr));
        }

        public Builder defineArray(String str, int... iArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(iArr));
        }

        public Builder defineArray(String str, long... jArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(jArr));
        }

        public Builder defineArray(String str, float... fArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(fArr));
        }

        public Builder defineArray(String str, double... dArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(dArr));
        }

        public Builder defineArray(String str, String... strArr) {
            return define(str, (AnnotationValue<?, ?>) AnnotationValue.ForConstant.of(strArr));
        }

        public AnnotationDescription build() {
            for (MethodDescription methodDescription : this.annotationType.getDeclaredMethods()) {
                if (this.annotationValues.get(methodDescription.getName()) == null && methodDescription.getDefaultValue() == null) {
                    throw new IllegalStateException("No value or default value defined for " + methodDescription.getName());
                }
            }
            return new Latent(this.annotationType, this.annotationValues);
        }
    }
}
