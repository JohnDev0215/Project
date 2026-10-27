package net.bytebuddy.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.ElementMatchers;

/* JADX INFO: loaded from: classes3.dex */
public interface Transformer<T> {
    T transform(TypeDescription typeDescription, T t);

    public enum NoOp implements Transformer<Object> {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.Transformer
        public Object transform(TypeDescription typeDescription, Object obj) {
            return obj;
        }

        public static <T> Transformer<T> make() {
            return INSTANCE;
        }
    }

    public static class ForField implements Transformer<FieldDescription> {
        private final Transformer<FieldDescription.Token> transformer;

        protected boolean canEqual(Object obj) {
            return obj instanceof ForField;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForField)) {
                return false;
            }
            ForField forField = (ForField) obj;
            if (!forField.canEqual(this)) {
                return false;
            }
            Transformer<FieldDescription.Token> transformer = this.transformer;
            Transformer<FieldDescription.Token> transformer2 = forField.transformer;
            return transformer != null ? transformer.equals(transformer2) : transformer2 == null;
        }

        public int hashCode() {
            Transformer<FieldDescription.Token> transformer = this.transformer;
            return 59 + (transformer == null ? 43 : transformer.hashCode());
        }

        public ForField(Transformer<FieldDescription.Token> transformer) {
            this.transformer = transformer;
        }

        public static Transformer<FieldDescription> withModifiers(ModifierContributor.ForField... forFieldArr) {
            return withModifiers((List<? extends ModifierContributor.ForField>) Arrays.asList(forFieldArr));
        }

        public static Transformer<FieldDescription> withModifiers(List<? extends ModifierContributor.ForField> list) {
            return new ForField(new FieldModifierTransformer(ModifierContributor.Resolver.of(list)));
        }

        @Override // net.bytebuddy.dynamic.Transformer
        public FieldDescription transform(TypeDescription typeDescription, FieldDescription fieldDescription) {
            return new TransformedField(typeDescription, fieldDescription.getDeclaringType(), this.transformer.transform(typeDescription, fieldDescription.asToken(ElementMatchers.none())), fieldDescription.asDefined());
        }

        protected static class FieldModifierTransformer implements Transformer<FieldDescription.Token> {
            private final ModifierContributor.Resolver<ModifierContributor.ForField> resolver;

            protected boolean canEqual(Object obj) {
                return obj instanceof FieldModifierTransformer;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof FieldModifierTransformer)) {
                    return false;
                }
                FieldModifierTransformer fieldModifierTransformer = (FieldModifierTransformer) obj;
                if (!fieldModifierTransformer.canEqual(this)) {
                    return false;
                }
                ModifierContributor.Resolver<ModifierContributor.ForField> resolver = this.resolver;
                ModifierContributor.Resolver<ModifierContributor.ForField> resolver2 = fieldModifierTransformer.resolver;
                return resolver != null ? resolver.equals(resolver2) : resolver2 == null;
            }

            public int hashCode() {
                ModifierContributor.Resolver<ModifierContributor.ForField> resolver = this.resolver;
                return 59 + (resolver == null ? 43 : resolver.hashCode());
            }

            protected FieldModifierTransformer(ModifierContributor.Resolver<ModifierContributor.ForField> resolver) {
                this.resolver = resolver;
            }

            @Override // net.bytebuddy.dynamic.Transformer
            public FieldDescription.Token transform(TypeDescription typeDescription, FieldDescription.Token token) {
                return new FieldDescription.Token(token.getName(), this.resolver.resolve(token.getModifiers()), token.getType(), token.getAnnotations());
            }
        }

        protected static class TransformedField extends FieldDescription.AbstractBase {
            private final TypeDefinition declaringType;
            private final FieldDescription.InDefinedShape fieldDescription;
            private final TypeDescription instrumentedType;
            private final FieldDescription.Token token;

            protected TransformedField(TypeDescription typeDescription, TypeDefinition typeDefinition, FieldDescription.Token token, FieldDescription.InDefinedShape inDefinedShape) {
                this.instrumentedType = typeDescription;
                this.declaringType = typeDefinition;
                this.token = token;
                this.fieldDescription = inDefinedShape;
            }

            @Override // net.bytebuddy.description.field.FieldDescription
            public TypeDescription.Generic getType() {
                return (TypeDescription.Generic) this.token.getType().accept(TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of(this.instrumentedType));
            }

            @Override // net.bytebuddy.description.annotation.AnnotationSource
            public AnnotationList getDeclaredAnnotations() {
                return this.token.getAnnotations();
            }

            @Override // net.bytebuddy.description.DeclaredByType
            public TypeDefinition getDeclaringType() {
                return this.declaringType;
            }

            @Override // net.bytebuddy.description.ModifierReviewable
            public int getModifiers() {
                return this.token.getModifiers();
            }

            @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
            public FieldDescription.InDefinedShape asDefined() {
                return this.fieldDescription;
            }

            @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
            public String getName() {
                return this.token.getName();
            }
        }
    }

    public static class ForMethod implements Transformer<MethodDescription> {
        private final Transformer<MethodDescription.Token> transformer;

        protected boolean canEqual(Object obj) {
            return obj instanceof ForMethod;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForMethod)) {
                return false;
            }
            ForMethod forMethod = (ForMethod) obj;
            if (!forMethod.canEqual(this)) {
                return false;
            }
            Transformer<MethodDescription.Token> transformer = this.transformer;
            Transformer<MethodDescription.Token> transformer2 = forMethod.transformer;
            return transformer != null ? transformer.equals(transformer2) : transformer2 == null;
        }

        public int hashCode() {
            Transformer<MethodDescription.Token> transformer = this.transformer;
            return 59 + (transformer == null ? 43 : transformer.hashCode());
        }

        public ForMethod(Transformer<MethodDescription.Token> transformer) {
            this.transformer = transformer;
        }

        public static Transformer<MethodDescription> withModifiers(ModifierContributor.ForMethod... forMethodArr) {
            return withModifiers((List<? extends ModifierContributor.ForMethod>) Arrays.asList(forMethodArr));
        }

        public static Transformer<MethodDescription> withModifiers(List<? extends ModifierContributor.ForMethod> list) {
            return new ForMethod(new MethodModifierTransformer(ModifierContributor.Resolver.of(list)));
        }

        @Override // net.bytebuddy.dynamic.Transformer
        public MethodDescription transform(TypeDescription typeDescription, MethodDescription methodDescription) {
            return new TransformedMethod(typeDescription, methodDescription.getDeclaringType(), this.transformer.transform(typeDescription, methodDescription.asToken(ElementMatchers.none())), methodDescription.asDefined());
        }

        protected static class MethodModifierTransformer implements Transformer<MethodDescription.Token> {
            private final ModifierContributor.Resolver<ModifierContributor.ForMethod> resolver;

            protected boolean canEqual(Object obj) {
                return obj instanceof MethodModifierTransformer;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof MethodModifierTransformer)) {
                    return false;
                }
                MethodModifierTransformer methodModifierTransformer = (MethodModifierTransformer) obj;
                if (!methodModifierTransformer.canEqual(this)) {
                    return false;
                }
                ModifierContributor.Resolver<ModifierContributor.ForMethod> resolver = this.resolver;
                ModifierContributor.Resolver<ModifierContributor.ForMethod> resolver2 = methodModifierTransformer.resolver;
                return resolver != null ? resolver.equals(resolver2) : resolver2 == null;
            }

            public int hashCode() {
                ModifierContributor.Resolver<ModifierContributor.ForMethod> resolver = this.resolver;
                return 59 + (resolver == null ? 43 : resolver.hashCode());
            }

            protected MethodModifierTransformer(ModifierContributor.Resolver<ModifierContributor.ForMethod> resolver) {
                this.resolver = resolver;
            }

            @Override // net.bytebuddy.dynamic.Transformer
            public MethodDescription.Token transform(TypeDescription typeDescription, MethodDescription.Token token) {
                return new MethodDescription.Token(token.getName(), this.resolver.resolve(token.getModifiers()), token.getTypeVariableTokens(), token.getReturnType(), token.getParameterTokens(), token.getExceptionTypes(), token.getAnnotations(), token.getDefaultValue(), token.getReceiverType());
            }
        }

        protected static class TransformedMethod extends MethodDescription.AbstractBase {
            private final TypeDefinition declaringType;
            private final TypeDescription instrumentedType;
            private final MethodDescription.InDefinedShape methodDescription;
            private final MethodDescription.Token token;

            protected TransformedMethod(TypeDescription typeDescription, TypeDefinition typeDefinition, MethodDescription.Token token, MethodDescription.InDefinedShape inDefinedShape) {
                this.instrumentedType = typeDescription;
                this.declaringType = typeDefinition;
                this.token = token;
                this.methodDescription = inDefinedShape;
            }

            @Override // net.bytebuddy.description.TypeVariableSource
            public TypeList.Generic getTypeVariables() {
                return new TypeList.Generic.ForDetachedTypes.OfTypeVariables(this, this.token.getTypeVariableTokens(), new AttachmentVisitor());
            }

            @Override // net.bytebuddy.description.method.MethodDescription
            public TypeDescription.Generic getReturnType() {
                return (TypeDescription.Generic) this.token.getReturnType().accept(new AttachmentVisitor());
            }

            @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
            public ParameterList<?> getParameters() {
                return new TransformedParameterList();
            }

            @Override // net.bytebuddy.description.method.MethodDescription
            public TypeList.Generic getExceptionTypes() {
                return new TypeList.Generic.ForDetachedTypes(this.token.getExceptionTypes(), new AttachmentVisitor());
            }

            @Override // net.bytebuddy.description.annotation.AnnotationSource
            public AnnotationList getDeclaredAnnotations() {
                return this.token.getAnnotations();
            }

            @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
            public String getInternalName() {
                return this.token.getName();
            }

            @Override // net.bytebuddy.description.DeclaredByType
            public TypeDefinition getDeclaringType() {
                return this.declaringType;
            }

            @Override // net.bytebuddy.description.ModifierReviewable
            public int getModifiers() {
                return this.token.getModifiers();
            }

            @Override // net.bytebuddy.description.method.MethodDescription
            public AnnotationValue<?, ?> getDefaultValue() {
                return this.token.getDefaultValue();
            }

            @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
            public MethodDescription.InDefinedShape asDefined() {
                return this.methodDescription;
            }

            @Override // net.bytebuddy.description.method.MethodDescription
            public TypeDescription.Generic getReceiverType() {
                TypeDescription.Generic receiverType = this.token.getReceiverType();
                if (receiverType == null) {
                    return TypeDescription.Generic.UNDEFINED;
                }
                return (TypeDescription.Generic) receiverType.accept(new AttachmentVisitor());
            }

            protected class TransformedParameterList extends ParameterList.AbstractBase<ParameterDescription> {
                protected TransformedParameterList() {
                }

                @Override // java.util.AbstractList, java.util.List
                public ParameterDescription get(int i) {
                    TransformedMethod transformedMethod = TransformedMethod.this;
                    return transformedMethod.new TransformedParameter(i, (ParameterDescription.Token) transformedMethod.token.getParameterTokens().get(i));
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return TransformedMethod.this.token.getParameterTokens().size();
                }
            }

            protected class TransformedParameter extends ParameterDescription.AbstractBase {
                private final int index;
                private final ParameterDescription.Token parameterToken;

                protected TransformedParameter(int i, ParameterDescription.Token token) {
                    this.index = i;
                    this.parameterToken = token;
                }

                @Override // net.bytebuddy.description.method.ParameterDescription
                public TypeDescription.Generic getType() {
                    return (TypeDescription.Generic) this.parameterToken.getType().accept(TransformedMethod.this.new AttachmentVisitor());
                }

                @Override // net.bytebuddy.description.method.ParameterDescription, net.bytebuddy.description.method.ParameterDescription.InDefinedShape
                public MethodDescription getDeclaringMethod() {
                    return TransformedMethod.this;
                }

                @Override // net.bytebuddy.description.method.ParameterDescription
                public int getIndex() {
                    return this.index;
                }

                @Override // net.bytebuddy.description.NamedElement.WithOptionalName
                public boolean isNamed() {
                    return this.parameterToken.getName() != null;
                }

                @Override // net.bytebuddy.description.method.ParameterDescription
                public boolean hasModifiers() {
                    return this.parameterToken.getModifiers() != null;
                }

                @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getName() {
                    if (isNamed()) {
                        return this.parameterToken.getName();
                    }
                    return super.getName();
                }

                @Override // net.bytebuddy.description.method.ParameterDescription.AbstractBase, net.bytebuddy.description.ModifierReviewable
                public int getModifiers() {
                    if (hasModifiers()) {
                        return this.parameterToken.getModifiers().intValue();
                    }
                    return super.getModifiers();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.parameterToken.getAnnotations();
                }

                @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
                public ParameterDescription.InDefinedShape asDefined() {
                    return (ParameterDescription.InDefinedShape) TransformedMethod.this.methodDescription.getParameters().get(this.index);
                }
            }

            protected class AttachmentVisitor extends TypeDescription.Generic.Visitor.Substitutor.WithoutTypeSubstitution {
                protected AttachmentVisitor() {
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public TypeDescription.Generic onTypeVariable(TypeDescription.Generic generic) {
                    TypeDescription.Generic only;
                    TypeList.Generic genericFilter = TransformedMethod.this.getTypeVariables().filter(ElementMatchers.named(generic.getSymbol()));
                    if (genericFilter.isEmpty()) {
                        only = TransformedMethod.this.instrumentedType.findVariable(generic.getSymbol());
                    } else {
                        only = genericFilter.getOnly();
                    }
                    if (only == null) {
                        throw new IllegalArgumentException("Cannot attach undefined variable: " + generic);
                    }
                    return new TypeDescription.Generic.OfTypeVariable.WithAnnotationOverlay(only, generic);
                }

                public int hashCode() {
                    return TransformedMethod.this.hashCode();
                }

                public boolean equals(Object obj) {
                    return this == obj || ((obj instanceof AttachmentVisitor) && ((AttachmentVisitor) obj).getOuter().equals(TransformedMethod.this));
                }

                private TransformedMethod getOuter() {
                    return TransformedMethod.this;
                }
            }
        }
    }

    public static class Compound<S> implements Transformer<S> {
        private final List<Transformer<S>> transformers;

        protected boolean canEqual(Object obj) {
            return obj instanceof Compound;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Compound)) {
                return false;
            }
            Compound compound = (Compound) obj;
            if (!compound.canEqual(this)) {
                return false;
            }
            List<Transformer<S>> list = this.transformers;
            List<Transformer<S>> list2 = compound.transformers;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<Transformer<S>> list = this.transformers;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Compound(Transformer<S>... transformerArr) {
            this(Arrays.asList(transformerArr));
        }

        public Compound(List<? extends Transformer<S>> list) {
            this.transformers = new ArrayList();
            for (Transformer<S> transformer : list) {
                if (transformer instanceof Compound) {
                    this.transformers.addAll(((Compound) transformer).transformers);
                } else if (!(transformer instanceof NoOp)) {
                    this.transformers.add(transformer);
                }
            }
        }

        @Override // net.bytebuddy.dynamic.Transformer
        public S transform(TypeDescription typeDescription, S s) {
            Iterator<Transformer<S>> it = this.transformers.iterator();
            while (it.hasNext()) {
                s = it.next().transform(typeDescription, s);
            }
            return s;
        }
    }
}
