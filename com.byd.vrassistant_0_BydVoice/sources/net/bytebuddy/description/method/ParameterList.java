package net.bytebuddy.description.method;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.FilterableList;

/* JADX INFO: loaded from: classes3.dex */
public interface ParameterList<T extends ParameterDescription> extends FilterableList<T, ParameterList<T>> {
    ParameterList<ParameterDescription.InDefinedShape> asDefined();

    ByteCodeElement.Token.TokenList<ParameterDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher);

    TypeList.Generic asTypeList();

    boolean hasExplicitMetaData();

    public static abstract class AbstractBase<S extends ParameterDescription> extends FilterableList.AbstractBase<S, ParameterList<S>> implements ParameterList<S> {
        @Override // net.bytebuddy.description.method.ParameterList
        public boolean hasExplicitMetaData() {
            for (S s : this) {
                if (!s.isNamed() || !s.hasModifiers()) {
                    return false;
                }
            }
            return true;
        }

        @Override // net.bytebuddy.description.method.ParameterList
        public ByteCodeElement.Token.TokenList<ParameterDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher) {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((ParameterDescription) it.next()).asToken(elementMatcher));
            }
            return new ByteCodeElement.Token.TokenList<>(arrayList);
        }

        @Override // net.bytebuddy.description.method.ParameterList
        public TypeList.Generic asTypeList() {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((ParameterDescription) it.next()).getType());
            }
            return new TypeList.Generic.Explicit(arrayList);
        }

        @Override // net.bytebuddy.description.method.ParameterList
        public ParameterList<ParameterDescription.InDefinedShape> asDefined() {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((ParameterDescription) it.next()).asDefined());
            }
            return new Explicit(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // net.bytebuddy.matcher.FilterableList.AbstractBase
        public ParameterList<S> wrap(List<S> list) {
            return new Explicit(list);
        }
    }

    public static abstract class ForLoadedExecutable<T> extends AbstractBase<ParameterDescription.InDefinedShape> {
        private static final Dispatcher DISPATCHER = (Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE);
        protected final T executable;

        protected ForLoadedExecutable(T t) {
            this.executable = t;
        }

        public static ParameterList<ParameterDescription.InDefinedShape> of(Constructor<?> constructor) {
            return DISPATCHER.describe(constructor);
        }

        public static ParameterList<ParameterDescription.InDefinedShape> of(Method method) {
            return DISPATCHER.describe(method);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return DISPATCHER.getParameterCount(this.executable);
        }

        protected interface Dispatcher {
            ParameterList<ParameterDescription.InDefinedShape> describe(Constructor<?> constructor);

            ParameterList<ParameterDescription.InDefinedShape> describe(Method method);

            int getParameterCount(Object obj);

            public enum CreationAction implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                @Override // java.security.PrivilegedAction
                public Dispatcher run() {
                    try {
                        return new ForJava8CapableVm(Class.forName("java.lang.reflect.Executable").getMethod("getParameterCount", new Class[0]));
                    } catch (Exception unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
            }

            public enum ForLegacyVm implements Dispatcher {
                INSTANCE;

                @Override // net.bytebuddy.description.method.ParameterList.ForLoadedExecutable.Dispatcher
                public int getParameterCount(Object obj) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.reflect.Executable");
                }

                @Override // net.bytebuddy.description.method.ParameterList.ForLoadedExecutable.Dispatcher
                public ParameterList<ParameterDescription.InDefinedShape> describe(Constructor<?> constructor) {
                    return new OfLegacyVmConstructor(constructor);
                }

                @Override // net.bytebuddy.description.method.ParameterList.ForLoadedExecutable.Dispatcher
                public ParameterList<ParameterDescription.InDefinedShape> describe(Method method) {
                    return new OfLegacyVmMethod(method);
                }
            }

            public static class ForJava8CapableVm implements Dispatcher {
                private final Method getParameterCount;

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
                    Method method = this.getParameterCount;
                    Method method2 = forJava8CapableVm.getParameterCount;
                    return method != null ? method.equals(method2) : method2 == null;
                }

                public int hashCode() {
                    Method method = this.getParameterCount;
                    return 59 + (method == null ? 43 : method.hashCode());
                }

                protected ForJava8CapableVm(Method method) {
                    this.getParameterCount = method;
                }

                @Override // net.bytebuddy.description.method.ParameterList.ForLoadedExecutable.Dispatcher
                public int getParameterCount(Object obj) {
                    try {
                        return ((Integer) this.getParameterCount.invoke(obj, new Object[0])).intValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.Parameter#getModifiers", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.Parameter#getModifiers", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.description.method.ParameterList.ForLoadedExecutable.Dispatcher
                public ParameterList<ParameterDescription.InDefinedShape> describe(Constructor<?> constructor) {
                    return new OfConstructor(constructor);
                }

                @Override // net.bytebuddy.description.method.ParameterList.ForLoadedExecutable.Dispatcher
                public ParameterList<ParameterDescription.InDefinedShape> describe(Method method) {
                    return new OfMethod(method);
                }
            }
        }

        protected static class OfConstructor extends ForLoadedExecutable<Constructor<?>> {
            protected OfConstructor(Constructor<?> constructor) {
                super(constructor);
            }

            @Override // java.util.AbstractList, java.util.List
            public ParameterDescription.InDefinedShape get(int i) {
                return new ParameterDescription.ForLoadedParameter.OfConstructor((Constructor) this.executable, i);
            }
        }

        protected static class OfMethod extends ForLoadedExecutable<Method> {
            protected OfMethod(Method method) {
                super(method);
            }

            @Override // java.util.AbstractList, java.util.List
            public ParameterDescription.InDefinedShape get(int i) {
                return new ParameterDescription.ForLoadedParameter.OfMethod((Method) this.executable, i);
            }
        }

        protected static class OfLegacyVmConstructor extends AbstractBase<ParameterDescription.InDefinedShape> {
            private final Constructor<?> constructor;
            private final Annotation[][] parameterAnnotation;
            private final Class<?>[] parameterType;

            public OfLegacyVmConstructor(Constructor<?> constructor) {
                this.constructor = constructor;
                this.parameterType = constructor.getParameterTypes();
                this.parameterAnnotation = constructor.getParameterAnnotations();
            }

            @Override // java.util.AbstractList, java.util.List
            public ParameterDescription.InDefinedShape get(int i) {
                return new ParameterDescription.ForLoadedParameter.OfLegacyVmConstructor(this.constructor, i, this.parameterType, this.parameterAnnotation);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return this.parameterType.length;
            }
        }

        protected static class OfLegacyVmMethod extends AbstractBase<ParameterDescription.InDefinedShape> {
            private final Method method;
            private final Annotation[][] parameterAnnotation;
            private final Class<?>[] parameterType;

            protected OfLegacyVmMethod(Method method) {
                this.method = method;
                this.parameterType = method.getParameterTypes();
                this.parameterAnnotation = method.getParameterAnnotations();
            }

            @Override // java.util.AbstractList, java.util.List
            public ParameterDescription.InDefinedShape get(int i) {
                return new ParameterDescription.ForLoadedParameter.OfLegacyVmMethod(this.method, i, this.parameterType, this.parameterAnnotation);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return this.parameterType.length;
            }
        }
    }

    public static class Explicit<S extends ParameterDescription> extends AbstractBase<S> {
        private final List<? extends S> parameterDescriptions;

        public Explicit(S... sArr) {
            this(Arrays.asList(sArr));
        }

        public Explicit(List<? extends S> list) {
            this.parameterDescriptions = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public S get(int i) {
            return this.parameterDescriptions.get(i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.parameterDescriptions.size();
        }

        public static class ForTypes extends AbstractBase<ParameterDescription.InDefinedShape> {
            private final MethodDescription.InDefinedShape methodDescription;
            private final List<? extends TypeDefinition> typeDefinitions;

            public ForTypes(MethodDescription.InDefinedShape inDefinedShape, TypeDefinition... typeDefinitionArr) {
                this(inDefinedShape, (List<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
            }

            public ForTypes(MethodDescription.InDefinedShape inDefinedShape, List<? extends TypeDefinition> list) {
                this.methodDescription = inDefinedShape;
                this.typeDefinitions = list;
            }

            @Override // java.util.AbstractList, java.util.List
            public ParameterDescription.InDefinedShape get(int i) {
                int size = !this.methodDescription.isStatic() ? 1 : 0;
                Iterator<? extends TypeDefinition> it = this.typeDefinitions.subList(0, i).iterator();
                while (it.hasNext()) {
                    size += it.next().getStackSize().getSize();
                }
                return new ParameterDescription.Latent(this.methodDescription, this.typeDefinitions.get(i).asGenericType(), i, size);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return this.typeDefinitions.size();
            }
        }
    }

    public static class ForTokens extends AbstractBase<ParameterDescription.InDefinedShape> {
        private final MethodDescription.InDefinedShape declaringMethod;
        private final List<? extends ParameterDescription.Token> tokens;

        public ForTokens(MethodDescription.InDefinedShape inDefinedShape, List<? extends ParameterDescription.Token> list) {
            this.declaringMethod = inDefinedShape;
            this.tokens = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public ParameterDescription.InDefinedShape get(int i) {
            int size = !this.declaringMethod.isStatic() ? 1 : 0;
            Iterator<? extends ParameterDescription.Token> it = this.tokens.subList(0, i).iterator();
            while (it.hasNext()) {
                size += it.next().getType().getStackSize().getSize();
            }
            return new ParameterDescription.Latent(this.declaringMethod, this.tokens.get(i), i, size);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.tokens.size();
        }
    }

    public static class TypeSubstituting extends AbstractBase<ParameterDescription.InGenericShape> {
        private final MethodDescription.InGenericShape declaringMethod;
        private final List<? extends ParameterDescription> parameterDescriptions;
        private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

        public TypeSubstituting(MethodDescription.InGenericShape inGenericShape, List<? extends ParameterDescription> list, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            this.declaringMethod = inGenericShape;
            this.parameterDescriptions = list;
            this.visitor = visitor;
        }

        @Override // java.util.AbstractList, java.util.List
        public ParameterDescription.InGenericShape get(int i) {
            return new ParameterDescription.TypeSubstituting(this.declaringMethod, this.parameterDescriptions.get(i), this.visitor);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.parameterDescriptions.size();
        }
    }

    public static class Empty<S extends ParameterDescription> extends FilterableList.Empty<S, ParameterList<S>> implements ParameterList<S> {
        @Override // net.bytebuddy.description.method.ParameterList
        public ParameterList<ParameterDescription.InDefinedShape> asDefined() {
            return this;
        }

        @Override // net.bytebuddy.description.method.ParameterList
        public boolean hasExplicitMetaData() {
            return true;
        }

        @Override // net.bytebuddy.description.method.ParameterList
        public TypeList.Generic asTypeList() {
            return new TypeList.Generic.Empty();
        }

        @Override // net.bytebuddy.description.method.ParameterList
        public ByteCodeElement.Token.TokenList<ParameterDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher) {
            return new ByteCodeElement.Token.TokenList<>(new ParameterDescription.Token[0]);
        }
    }
}
