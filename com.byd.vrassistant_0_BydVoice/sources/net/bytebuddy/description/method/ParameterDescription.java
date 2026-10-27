package net.bytebuddy.description.method;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationSource;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public interface ParameterDescription extends AnnotationSource, NamedElement.WithRuntimeName, NamedElement.WithOptionalName, ModifierReviewable.ForParameterDescription, ByteCodeElement.TypeDependant<InDefinedShape, Token> {
    public static final String NAME_PREFIX = "arg";

    public interface InDefinedShape extends ParameterDescription {

        public static abstract class AbstractBase extends AbstractBase implements InDefinedShape {
            @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
            public InDefinedShape asDefined() {
                return this;
            }
        }

        MethodDescription.InDefinedShape getDeclaringMethod();
    }

    public interface InGenericShape extends ParameterDescription {
        @Override // net.bytebuddy.description.method.ParameterDescription, net.bytebuddy.description.method.ParameterDescription.InDefinedShape
        MethodDescription.InGenericShape getDeclaringMethod();
    }

    MethodDescription getDeclaringMethod();

    int getIndex();

    int getOffset();

    TypeDescription.Generic getType();

    boolean hasModifiers();

    public static abstract class AbstractBase extends ModifierReviewable.AbstractBase implements ParameterDescription {
        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return 0;
        }

        @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
        public /* bridge */ /* synthetic */ ByteCodeElement.Token asToken(ElementMatcher elementMatcher) {
            return asToken((ElementMatcher<? super TypeDescription>) elementMatcher);
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return ParameterDescription.NAME_PREFIX.concat(String.valueOf(getIndex()));
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getInternalName() {
            return getName();
        }

        @Override // net.bytebuddy.description.NamedElement
        public String getActualName() {
            return isNamed() ? getName() : "";
        }

        @Override // net.bytebuddy.description.method.ParameterDescription
        public int getOffset() {
            int size;
            TypeList typeListAsErasures = getDeclaringMethod().getParameters().asTypeList().asErasures();
            if (getDeclaringMethod().isStatic()) {
                size = StackSize.ZERO.getSize();
            } else {
                size = StackSize.SINGLE.getSize();
            }
            for (int i = 0; i < getIndex(); i++) {
                size += ((TypeDescription) typeListAsErasures.get(i)).getStackSize().getSize();
            }
            return size;
        }

        @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
        public Token asToken(ElementMatcher<? super TypeDescription> elementMatcher) {
            return new Token((TypeDescription.Generic) getType().accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher)), getDeclaredAnnotations(), isNamed() ? getName() : Token.NO_NAME, hasModifiers() ? Integer.valueOf(getModifiers()) : Token.NO_MODIFIERS);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ParameterDescription)) {
                return false;
            }
            ParameterDescription parameterDescription = (ParameterDescription) obj;
            return getDeclaringMethod().equals(parameterDescription.getDeclaringMethod()) && getIndex() == parameterDescription.getIndex();
        }

        public int hashCode() {
            return getDeclaringMethod().hashCode() ^ getIndex();
        }

        public String toString() {
            String name;
            StringBuilder sb = new StringBuilder(Modifier.toString(getModifiers()));
            if (getModifiers() != 0) {
                sb.append(' ');
            }
            if (isVarArgs()) {
                name = getType().asErasure().getName().replaceFirst("\\[\\]$", "...");
            } else {
                name = getType().asErasure().getName();
            }
            sb.append(name);
            return sb.append(' ').append(getName()).toString();
        }
    }

    public static abstract class ForLoadedParameter<T extends AccessibleObject> extends InDefinedShape.AbstractBase {
        private static final Dispatcher DISPATCHER = (Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE);
        protected final T executable;
        protected final int index;

        protected ForLoadedParameter(T t, int i) {
            this.executable = t;
            this.index = i;
        }

        @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return DISPATCHER.getName(this.executable, this.index);
        }

        @Override // net.bytebuddy.description.method.ParameterDescription
        public int getIndex() {
            return this.index;
        }

        @Override // net.bytebuddy.description.NamedElement.WithOptionalName
        public boolean isNamed() {
            return DISPATCHER.isNamePresent(this.executable, this.index);
        }

        @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return DISPATCHER.getModifiers(this.executable, this.index);
        }

        @Override // net.bytebuddy.description.method.ParameterDescription
        public boolean hasModifiers() {
            return isNamed() || getModifiers() != 0;
        }

        protected interface Dispatcher {
            int getModifiers(AccessibleObject accessibleObject, int i);

            String getName(AccessibleObject accessibleObject, int i);

            boolean isNamePresent(AccessibleObject accessibleObject, int i);

            public enum CreationAction implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                @Override // java.security.PrivilegedAction
                public Dispatcher run() {
                    try {
                        Class<?> cls = Class.forName("java.lang.reflect.Executable");
                        Class<?> cls2 = Class.forName("java.lang.reflect.Parameter");
                        return new ForJava8CapableVm(cls.getMethod("getParameters", new Class[0]), cls2.getMethod("getName", new Class[0]), cls2.getMethod("isNamePresent", new Class[0]), cls2.getMethod("getModifiers", new Class[0]));
                    } catch (Exception unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
            }

            public static class ForJava8CapableVm implements Dispatcher {
                private final Method getModifiers;
                private final Method getName;
                private final Method getParameters;
                private final Method isNamePresent;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForJava8CapableVm;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForJava8CapableVm)) {
                        return false;
                    }
                    ForJava8CapableVm forJava8CapableVm = (ForJava8CapableVm) obj;
                    if (!forJava8CapableVm.canEqual(this)) {
                        return false;
                    }
                    Method method = this.getParameters;
                    Method method2 = forJava8CapableVm.getParameters;
                    if (method != null ? !method.equals(method2) : method2 != null) {
                        return false;
                    }
                    Method method3 = this.getName;
                    Method method4 = forJava8CapableVm.getName;
                    if (method3 != null ? !method3.equals(method4) : method4 != null) {
                        return false;
                    }
                    Method method5 = this.isNamePresent;
                    Method method6 = forJava8CapableVm.isNamePresent;
                    if (method5 != null ? !method5.equals(method6) : method6 != null) {
                        return false;
                    }
                    Method method7 = this.getModifiers;
                    Method method8 = forJava8CapableVm.getModifiers;
                    return method7 != null ? method7.equals(method8) : method8 == null;
                }

                public int hashCode() {
                    Method method = this.getParameters;
                    int iHashCode = method == null ? 43 : method.hashCode();
                    Method method2 = this.getName;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (method2 == null ? 43 : method2.hashCode());
                    Method method3 = this.isNamePresent;
                    int iHashCode3 = (iHashCode2 * 59) + (method3 == null ? 43 : method3.hashCode());
                    Method method4 = this.getModifiers;
                    return (iHashCode3 * 59) + (method4 != null ? method4.hashCode() : 43);
                }

                protected ForJava8CapableVm(Method method, Method method2, Method method3, Method method4) {
                    this.getParameters = method;
                    this.getName = method2;
                    this.isNamePresent = method3;
                    this.getModifiers = method4;
                }

                @Override // net.bytebuddy.description.method.ParameterDescription.ForLoadedParameter.Dispatcher
                public int getModifiers(AccessibleObject accessibleObject, int i) {
                    try {
                        return ((Integer) this.getModifiers.invoke(getParameter(accessibleObject, i), new Object[0])).intValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.Parameter#getModifiers", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.Parameter#getModifiers", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.description.method.ParameterDescription.ForLoadedParameter.Dispatcher
                public boolean isNamePresent(AccessibleObject accessibleObject, int i) {
                    try {
                        return ((Boolean) this.isNamePresent.invoke(getParameter(accessibleObject, i), new Object[0])).booleanValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.Parameter#isNamePresent", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.Parameter#isNamePresent", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.description.method.ParameterDescription.ForLoadedParameter.Dispatcher
                public String getName(AccessibleObject accessibleObject, int i) {
                    try {
                        return (String) this.getName.invoke(getParameter(accessibleObject, i), new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.Parameter#getName", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.Parameter#getName", e2.getCause());
                    }
                }

                private Object getParameter(AccessibleObject accessibleObject, int i) {
                    try {
                        return Array.get(this.getParameters.invoke(accessibleObject, new Object[0]), i);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.Executable#getParameters", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.Executable#getParameters", e2.getCause());
                    }
                }
            }

            public enum ForLegacyVm implements Dispatcher {
                INSTANCE;

                @Override // net.bytebuddy.description.method.ParameterDescription.ForLoadedParameter.Dispatcher
                public int getModifiers(AccessibleObject accessibleObject, int i) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.reflect.Parameter");
                }

                @Override // net.bytebuddy.description.method.ParameterDescription.ForLoadedParameter.Dispatcher
                public boolean isNamePresent(AccessibleObject accessibleObject, int i) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.reflect.Parameter");
                }

                @Override // net.bytebuddy.description.method.ParameterDescription.ForLoadedParameter.Dispatcher
                public String getName(AccessibleObject accessibleObject, int i) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.reflect.Parameter");
                }
            }
        }

        protected static class OfMethod extends ForLoadedParameter<Method> {
            protected OfMethod(Method method, int i) {
                super(method, i);
            }

            @Override // net.bytebuddy.description.method.ParameterDescription, net.bytebuddy.description.method.ParameterDescription.InDefinedShape
            public MethodDescription.InDefinedShape getDeclaringMethod() {
                return new MethodDescription.ForLoadedMethod((Method) this.executable);
            }

            @Override // net.bytebuddy.description.method.ParameterDescription
            public TypeDescription.Generic getType() {
                if (TypeDescription.AbstractBase.RAW_TYPES) {
                    return new TypeDescription.Generic.OfNonGenericType.ForLoadedType(((Method) this.executable).getParameterTypes()[this.index]);
                }
                return new TypeDescription.Generic.LazyProjection.OfMethodParameter((Method) this.executable, this.index, ((Method) this.executable).getParameterTypes());
            }

            @Override // net.bytebuddy.description.annotation.AnnotationSource
            public AnnotationList getDeclaredAnnotations() {
                return new AnnotationList.ForLoadedAnnotations(((Method) this.executable).getParameterAnnotations()[this.index]);
            }
        }

        protected static class OfConstructor extends ForLoadedParameter<Constructor<?>> {
            protected OfConstructor(Constructor<?> constructor, int i) {
                super(constructor, i);
            }

            @Override // net.bytebuddy.description.method.ParameterDescription, net.bytebuddy.description.method.ParameterDescription.InDefinedShape
            public MethodDescription.InDefinedShape getDeclaringMethod() {
                return new MethodDescription.ForLoadedConstructor((Constructor) this.executable);
            }

            @Override // net.bytebuddy.description.method.ParameterDescription
            public TypeDescription.Generic getType() {
                if (TypeDescription.AbstractBase.RAW_TYPES) {
                    return new TypeDescription.Generic.OfNonGenericType.ForLoadedType(((Constructor) this.executable).getParameterTypes()[this.index]);
                }
                return new TypeDescription.Generic.LazyProjection.OfConstructorParameter((Constructor) this.executable, this.index, ((Constructor) this.executable).getParameterTypes());
            }

            @Override // net.bytebuddy.description.annotation.AnnotationSource
            public AnnotationList getDeclaredAnnotations() {
                return new AnnotationList.ForLoadedAnnotations(((Constructor) this.executable).getParameterAnnotations()[this.index]);
            }
        }

        protected static class OfLegacyVmMethod extends InDefinedShape.AbstractBase {
            private final int index;
            private final Method method;
            private final Annotation[][] parameterAnnotation;
            private final Class<?>[] parameterType;

            @Override // net.bytebuddy.description.method.ParameterDescription
            public boolean hasModifiers() {
                return false;
            }

            @Override // net.bytebuddy.description.NamedElement.WithOptionalName
            public boolean isNamed() {
                return false;
            }

            protected OfLegacyVmMethod(Method method, int i, Class<?>[] clsArr, Annotation[][] annotationArr) {
                this.method = method;
                this.index = i;
                this.parameterType = clsArr;
                this.parameterAnnotation = annotationArr;
            }

            @Override // net.bytebuddy.description.method.ParameterDescription
            public TypeDescription.Generic getType() {
                if (TypeDescription.AbstractBase.RAW_TYPES) {
                    return new TypeDescription.Generic.OfNonGenericType.ForLoadedType(this.parameterType[this.index]);
                }
                return new TypeDescription.Generic.LazyProjection.OfMethodParameter(this.method, this.index, this.parameterType);
            }

            @Override // net.bytebuddy.description.method.ParameterDescription, net.bytebuddy.description.method.ParameterDescription.InDefinedShape
            public MethodDescription.InDefinedShape getDeclaringMethod() {
                return new MethodDescription.ForLoadedMethod(this.method);
            }

            @Override // net.bytebuddy.description.method.ParameterDescription
            public int getIndex() {
                return this.index;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationSource
            public AnnotationList getDeclaredAnnotations() {
                return new AnnotationList.ForLoadedAnnotations(this.parameterAnnotation[this.index]);
            }
        }

        protected static class OfLegacyVmConstructor extends InDefinedShape.AbstractBase {
            private final Constructor<?> constructor;
            private final int index;
            private final Annotation[][] parameterAnnotation;
            private final Class<?>[] parameterType;

            @Override // net.bytebuddy.description.method.ParameterDescription
            public boolean hasModifiers() {
                return false;
            }

            @Override // net.bytebuddy.description.NamedElement.WithOptionalName
            public boolean isNamed() {
                return false;
            }

            protected OfLegacyVmConstructor(Constructor<?> constructor, int i, Class<?>[] clsArr, Annotation[][] annotationArr) {
                this.constructor = constructor;
                this.index = i;
                this.parameterType = clsArr;
                this.parameterAnnotation = annotationArr;
            }

            @Override // net.bytebuddy.description.method.ParameterDescription
            public TypeDescription.Generic getType() {
                if (TypeDescription.AbstractBase.RAW_TYPES) {
                    return new TypeDescription.Generic.OfNonGenericType.ForLoadedType(this.parameterType[this.index]);
                }
                return new TypeDescription.Generic.LazyProjection.OfConstructorParameter(this.constructor, this.index, this.parameterType);
            }

            @Override // net.bytebuddy.description.method.ParameterDescription, net.bytebuddy.description.method.ParameterDescription.InDefinedShape
            public MethodDescription.InDefinedShape getDeclaringMethod() {
                return new MethodDescription.ForLoadedConstructor(this.constructor);
            }

            @Override // net.bytebuddy.description.method.ParameterDescription
            public int getIndex() {
                return this.index;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationSource
            public AnnotationList getDeclaredAnnotations() {
                return new AnnotationList.ForLoadedAnnotations(this.parameterAnnotation[this.index]);
            }
        }
    }

    public static class Latent extends InDefinedShape.AbstractBase {
        private final List<? extends AnnotationDescription> declaredAnnotations;
        private final MethodDescription.InDefinedShape declaringMethod;
        private final int index;
        private final Integer modifiers;
        private final String name;
        private final int offset;
        private final TypeDescription.Generic parameterType;

        public Latent(MethodDescription.InDefinedShape inDefinedShape, Token token, int i, int i2) {
            this(inDefinedShape, token.getType(), token.getAnnotations(), token.getName(), token.getModifiers(), i, i2);
        }

        public Latent(MethodDescription.InDefinedShape inDefinedShape, TypeDescription.Generic generic, int i, int i2) {
            this(inDefinedShape, generic, Collections.emptyList(), Token.NO_NAME, Token.NO_MODIFIERS, i, i2);
        }

        public Latent(MethodDescription.InDefinedShape inDefinedShape, TypeDescription.Generic generic, List<? extends AnnotationDescription> list, String str, Integer num, int i, int i2) {
            this.declaringMethod = inDefinedShape;
            this.parameterType = generic;
            this.declaredAnnotations = list;
            this.name = str;
            this.modifiers = num;
            this.index = i;
            this.offset = i2;
        }

        @Override // net.bytebuddy.description.method.ParameterDescription
        public TypeDescription.Generic getType() {
            return (TypeDescription.Generic) this.parameterType.accept(TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of(this));
        }

        @Override // net.bytebuddy.description.method.ParameterDescription, net.bytebuddy.description.method.ParameterDescription.InDefinedShape
        public MethodDescription.InDefinedShape getDeclaringMethod() {
            return this.declaringMethod;
        }

        @Override // net.bytebuddy.description.method.ParameterDescription
        public int getIndex() {
            return this.index;
        }

        @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.method.ParameterDescription
        public int getOffset() {
            return this.offset;
        }

        @Override // net.bytebuddy.description.NamedElement.WithOptionalName
        public boolean isNamed() {
            return this.name != null;
        }

        @Override // net.bytebuddy.description.method.ParameterDescription
        public boolean hasModifiers() {
            return this.modifiers != null;
        }

        @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return isNamed() ? this.name : super.getName();
        }

        @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            if (hasModifiers()) {
                return this.modifiers.intValue();
            }
            return super.getModifiers();
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Explicit(this.declaredAnnotations);
        }
    }

    public static class TypeSubstituting extends AbstractBase implements InGenericShape {
        private final MethodDescription.InGenericShape declaringMethod;
        private final ParameterDescription parameterDescription;
        private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

        public TypeSubstituting(MethodDescription.InGenericShape inGenericShape, ParameterDescription parameterDescription, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            this.declaringMethod = inGenericShape;
            this.parameterDescription = parameterDescription;
            this.visitor = visitor;
        }

        @Override // net.bytebuddy.description.method.ParameterDescription
        public TypeDescription.Generic getType() {
            return (TypeDescription.Generic) this.parameterDescription.getType().accept(this.visitor);
        }

        @Override // net.bytebuddy.description.method.ParameterDescription, net.bytebuddy.description.method.ParameterDescription.InDefinedShape
        public MethodDescription.InGenericShape getDeclaringMethod() {
            return this.declaringMethod;
        }

        @Override // net.bytebuddy.description.method.ParameterDescription
        public int getIndex() {
            return this.parameterDescription.getIndex();
        }

        @Override // net.bytebuddy.description.NamedElement.WithOptionalName
        public boolean isNamed() {
            return this.parameterDescription.isNamed();
        }

        @Override // net.bytebuddy.description.method.ParameterDescription
        public boolean hasModifiers() {
            return this.parameterDescription.hasModifiers();
        }

        @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.method.ParameterDescription
        public int getOffset() {
            return this.parameterDescription.getOffset();
        }

        @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return this.parameterDescription.getName();
        }

        @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return this.parameterDescription.getModifiers();
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return this.parameterDescription.getDeclaredAnnotations();
        }

        @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
        public InDefinedShape asDefined() {
            return this.parameterDescription.asDefined();
        }
    }

    public static class Token implements ByteCodeElement.Token<Token> {
        public static final Integer NO_MODIFIERS = null;
        public static final String NO_NAME = null;
        private final List<? extends AnnotationDescription> annotations;
        private final Integer modifiers;
        private final String name;
        private final TypeDescription.Generic type;

        @Override // net.bytebuddy.description.ByteCodeElement.Token
        public /* bridge */ /* synthetic */ ByteCodeElement.Token accept(TypeDescription.Generic.Visitor visitor) {
            return accept((TypeDescription.Generic.Visitor<? extends TypeDescription.Generic>) visitor);
        }

        public Token(TypeDescription.Generic generic) {
            this(generic, Collections.emptyList());
        }

        public Token(TypeDescription.Generic generic, List<? extends AnnotationDescription> list) {
            this(generic, list, NO_NAME, NO_MODIFIERS);
        }

        public Token(TypeDescription.Generic generic, String str, Integer num) {
            this(generic, Collections.emptyList(), str, num);
        }

        public Token(TypeDescription.Generic generic, List<? extends AnnotationDescription> list, String str, Integer num) {
            this.type = generic;
            this.annotations = list;
            this.name = str;
            this.modifiers = num;
        }

        public TypeDescription.Generic getType() {
            return this.type;
        }

        public AnnotationList getAnnotations() {
            return new AnnotationList.Explicit(this.annotations);
        }

        public String getName() {
            return this.name;
        }

        public Integer getModifiers() {
            return this.modifiers;
        }

        @Override // net.bytebuddy.description.ByteCodeElement.Token
        public Token accept(TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            return new Token((TypeDescription.Generic) this.type.accept(visitor), this.annotations, this.name, this.modifiers);
        }

        public boolean equals(Object obj) {
            String str;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            if (this.type.equals(token.type) && this.annotations.equals(token.annotations) && ((str = this.name) == null ? token.name == null : str.equals(token.name))) {
                Integer num = this.modifiers;
                if (num != null) {
                    if (num.equals(token.modifiers)) {
                        return true;
                    }
                } else if (token.modifiers == null) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int iHashCode = ((this.type.hashCode() * 31) + this.annotations.hashCode()) * 31;
            String str = this.name;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            Integer num = this.modifiers;
            return iHashCode2 + (num != null ? num.hashCode() : 0);
        }

        public String toString() {
            return "ParameterDescription.Token{type=" + this.type + ", annotations=" + this.annotations + ", name='" + this.name + "', modifiers=" + this.modifiers + '}';
        }

        public static class TypeList extends AbstractList<Token> {
            private final List<? extends TypeDefinition> typeDescriptions;

            public TypeList(List<? extends TypeDefinition> list) {
                this.typeDescriptions = list;
            }

            @Override // java.util.AbstractList, java.util.List
            public Token get(int i) {
                return new Token(this.typeDescriptions.get(i).asGenericType());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return this.typeDescriptions.size();
            }
        }
    }
}
