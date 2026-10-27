package net.bytebuddy.dynamic.scaffold.subclass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;

/* JADX INFO: loaded from: classes3.dex */
public interface ConstructorStrategy {
    List<MethodDescription.Token> extractConstructors(TypeDescription typeDescription);

    MethodRegistry inject(MethodRegistry methodRegistry);

    public enum Default implements ConstructorStrategy {
        NO_CONSTRUCTORS { // from class: net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default.1
            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry;
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                return Collections.emptyList();
            }
        },
        DEFAULT_CONSTRUCTOR { // from class: net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default.2
            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                MethodList methodListFilter;
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                if (superClass == null) {
                    methodListFilter = new MethodList.Empty();
                } else {
                    methodListFilter = superClass.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments(0)).and(ElementMatchers.isVisibleTo(typeDescription)));
                }
                if (methodListFilter.size() == 1) {
                    return Collections.singletonList(new MethodDescription.Token(1));
                }
                throw new IllegalArgumentException(typeDescription.getSuperClass() + " declares no constructor that is visible to " + typeDescription);
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor()), new MethodRegistry.Handler.ForImplementation(SuperMethodCall.INSTANCE), factory, Transformer.NoOp.make());
            }
        },
        IMITATE_SUPER_CLASS { // from class: net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default.3
            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                MethodList methodListFilter;
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                if (superClass == null) {
                    methodListFilter = new MethodList.Empty();
                } else {
                    methodListFilter = superClass.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.isVisibleTo(typeDescription)));
                }
                return methodListFilter.asTokenList(ElementMatchers.is(typeDescription));
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            public MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor()), new MethodRegistry.Handler.ForImplementation(SuperMethodCall.INSTANCE), factory, Transformer.NoOp.make());
            }
        },
        IMITATE_SUPER_CLASS_PUBLIC { // from class: net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default.4
            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                MethodList methodListFilter;
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                if (superClass == null) {
                    methodListFilter = new MethodList.Empty();
                } else {
                    methodListFilter = superClass.getDeclaredMethods().filter(ElementMatchers.isPublic().and(ElementMatchers.isConstructor()));
                }
                return methodListFilter.asTokenList(ElementMatchers.is(typeDescription));
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            public MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor()), new MethodRegistry.Handler.ForImplementation(SuperMethodCall.INSTANCE), factory, Transformer.NoOp.make());
            }
        },
        IMITATE_SUPER_CLASS_OPENING { // from class: net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default.5
            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected int resolveModifier(int i) {
                return 1;
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            protected List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                MethodList methodListFilter;
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                if (superClass == null) {
                    methodListFilter = new MethodList.Empty();
                } else {
                    methodListFilter = superClass.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.isVisibleTo(typeDescription)));
                }
                return methodListFilter.asTokenList(ElementMatchers.is(typeDescription));
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy.Default
            public MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor()), new MethodRegistry.Handler.ForImplementation(SuperMethodCall.INSTANCE), factory, Transformer.NoOp.make());
            }
        };

        protected abstract List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription);

        protected abstract MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory);

        protected int resolveModifier(int i) {
            return i;
        }

        @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy
        public List<MethodDescription.Token> extractConstructors(TypeDescription typeDescription) {
            List<MethodDescription.Token> listDoExtractConstructors = doExtractConstructors(typeDescription);
            ArrayList arrayList = new ArrayList(listDoExtractConstructors.size());
            for (MethodDescription.Token token : listDoExtractConstructors) {
                arrayList.add(new MethodDescription.Token(token.getName(), resolveModifier(token.getModifiers()), token.getTypeVariableTokens(), token.getReturnType(), token.getParameterTokens(), token.getExceptionTypes(), token.getAnnotations(), token.getDefaultValue(), TypeDescription.Generic.UNDEFINED));
            }
            return arrayList;
        }

        @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy
        public MethodRegistry inject(MethodRegistry methodRegistry) {
            return doInject(methodRegistry, MethodAttributeAppender.NoOp.INSTANCE);
        }

        public ConstructorStrategy with(MethodAttributeAppender.Factory factory) {
            return new WithMethodAttributeAppenderFactory(this, factory);
        }

        public ConstructorStrategy withInheritedAnnotations() {
            return new WithMethodAttributeAppenderFactory(this, MethodAttributeAppender.ForInstrumentedMethod.EXCLUDING_RECEIVER);
        }

        protected static class WithMethodAttributeAppenderFactory implements ConstructorStrategy {
            private final Default delegate;
            private final MethodAttributeAppender.Factory methodAttributeAppenderFactory;

            protected boolean canEqual(Object obj) {
                return obj instanceof WithMethodAttributeAppenderFactory;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WithMethodAttributeAppenderFactory)) {
                    return false;
                }
                WithMethodAttributeAppenderFactory withMethodAttributeAppenderFactory = (WithMethodAttributeAppenderFactory) obj;
                if (!withMethodAttributeAppenderFactory.canEqual(this)) {
                    return false;
                }
                Default r1 = this.delegate;
                Default r3 = withMethodAttributeAppenderFactory.delegate;
                if (r1 != null ? !r1.equals(r3) : r3 != null) {
                    return false;
                }
                MethodAttributeAppender.Factory factory = this.methodAttributeAppenderFactory;
                MethodAttributeAppender.Factory factory2 = withMethodAttributeAppenderFactory.methodAttributeAppenderFactory;
                return factory != null ? factory.equals(factory2) : factory2 == null;
            }

            public int hashCode() {
                Default r0 = this.delegate;
                int iHashCode = r0 == null ? 43 : r0.hashCode();
                MethodAttributeAppender.Factory factory = this.methodAttributeAppenderFactory;
                return ((iHashCode + 59) * 59) + (factory != null ? factory.hashCode() : 43);
            }

            protected WithMethodAttributeAppenderFactory(Default r1, MethodAttributeAppender.Factory factory) {
                this.delegate = r1;
                this.methodAttributeAppenderFactory = factory;
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy
            public List<MethodDescription.Token> extractConstructors(TypeDescription typeDescription) {
                return this.delegate.extractConstructors(typeDescription);
            }

            @Override // net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy
            public MethodRegistry inject(MethodRegistry methodRegistry) {
                return this.delegate.doInject(methodRegistry, this.methodAttributeAppenderFactory);
            }
        }
    }
}
