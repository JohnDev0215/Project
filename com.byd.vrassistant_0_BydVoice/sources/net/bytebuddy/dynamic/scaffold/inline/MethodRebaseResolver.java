package net.bytebuddy.dynamic.scaffold.inline;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.auxiliary.TrivialType;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.CompoundList;

/* JADX INFO: loaded from: classes3.dex */
public interface MethodRebaseResolver {
    Map<MethodDescription.SignatureToken, Resolution> asTokenMap();

    List<DynamicType> getAuxiliaryTypes();

    Resolution resolve(MethodDescription.InDefinedShape inDefinedShape);

    public enum Disabled implements MethodRebaseResolver {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver
        public Resolution resolve(MethodDescription.InDefinedShape inDefinedShape) {
            return new Resolution.Preserved(inDefinedShape);
        }

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver
        public List<DynamicType> getAuxiliaryTypes() {
            return Collections.emptyList();
        }

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver
        public Map<MethodDescription.SignatureToken, Resolution> asTokenMap() {
            return Collections.emptyMap();
        }
    }

    public interface Resolution {
        StackManipulation getAdditionalArguments();

        MethodDescription.InDefinedShape getResolvedMethod();

        boolean isRebased();

        public static class Preserved implements Resolution {
            private final MethodDescription.InDefinedShape methodDescription;

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public boolean isRebased() {
                return false;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Preserved;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Preserved)) {
                    return false;
                }
                Preserved preserved = (Preserved) obj;
                if (!preserved.canEqual(this)) {
                    return false;
                }
                MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
                MethodDescription.InDefinedShape inDefinedShape2 = preserved.methodDescription;
                return inDefinedShape != null ? inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 == null;
            }

            public int hashCode() {
                MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
                return 59 + (inDefinedShape == null ? 43 : inDefinedShape.hashCode());
            }

            public Preserved(MethodDescription.InDefinedShape inDefinedShape) {
                this.methodDescription = inDefinedShape;
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public MethodDescription.InDefinedShape getResolvedMethod() {
                return this.methodDescription;
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public StackManipulation getAdditionalArguments() {
                throw new IllegalStateException("Cannot process additional arguments for non-rebased method: " + this.methodDescription);
            }
        }

        public static class ForRebasedMethod implements Resolution {
            private final MethodDescription.InDefinedShape methodDescription;

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public boolean isRebased() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof ForRebasedMethod;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForRebasedMethod)) {
                    return false;
                }
                ForRebasedMethod forRebasedMethod = (ForRebasedMethod) obj;
                if (!forRebasedMethod.canEqual(this)) {
                    return false;
                }
                MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
                MethodDescription.InDefinedShape inDefinedShape2 = forRebasedMethod.methodDescription;
                return inDefinedShape != null ? inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 == null;
            }

            public int hashCode() {
                MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
                return 59 + (inDefinedShape == null ? 43 : inDefinedShape.hashCode());
            }

            protected ForRebasedMethod(MethodDescription.InDefinedShape inDefinedShape) {
                this.methodDescription = inDefinedShape;
            }

            public static Resolution of(TypeDescription typeDescription, MethodDescription.InDefinedShape inDefinedShape, MethodNameTransformer methodNameTransformer) {
                return new ForRebasedMethod(new RebasedMethod(typeDescription, inDefinedShape, methodNameTransformer));
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public MethodDescription.InDefinedShape getResolvedMethod() {
                return this.methodDescription;
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public StackManipulation getAdditionalArguments() {
                return StackManipulation.Trivial.INSTANCE;
            }

            protected static class RebasedMethod extends MethodDescription.InDefinedShape.AbstractBase {
                private final TypeDescription instrumentedType;
                private final MethodDescription.InDefinedShape methodDescription;
                private final MethodNameTransformer methodNameTransformer;

                protected RebasedMethod(TypeDescription typeDescription, MethodDescription.InDefinedShape inDefinedShape, MethodNameTransformer methodNameTransformer) {
                    this.instrumentedType = typeDescription;
                    this.methodDescription = inDefinedShape;
                    this.methodNameTransformer = methodNameTransformer;
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeDescription.Generic getReturnType() {
                    return this.methodDescription.getReturnType().asRawType();
                }

                @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new ParameterList.Explicit.ForTypes(this, this.methodDescription.getParameters().asTypeList().asRawTypes());
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeList.Generic getExceptionTypes() {
                    return this.methodDescription.getExceptionTypes().asRawTypes();
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public AnnotationValue<?, ?> getDefaultValue() {
                    return AnnotationValue.UNDEFINED;
                }

                @Override // net.bytebuddy.description.TypeVariableSource
                public TypeList.Generic getTypeVariables() {
                    return new TypeList.Generic.Empty();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return this.methodDescription.getDeclaringType();
                }

                @Override // net.bytebuddy.description.ModifierReviewable
                public int getModifiers() {
                    return (this.methodDescription.isStatic() ? 8 : 0) | 4096 | (this.methodDescription.isNative() ? 256 : 0) | (this.instrumentedType.isInterface() ? 1 : 2);
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getInternalName() {
                    return this.methodNameTransformer.transform(this.methodDescription);
                }
            }
        }

        public static class ForRebasedConstructor implements Resolution {
            private final MethodDescription.InDefinedShape methodDescription;

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public boolean isRebased() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof ForRebasedConstructor;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForRebasedConstructor)) {
                    return false;
                }
                ForRebasedConstructor forRebasedConstructor = (ForRebasedConstructor) obj;
                if (!forRebasedConstructor.canEqual(this)) {
                    return false;
                }
                MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
                MethodDescription.InDefinedShape inDefinedShape2 = forRebasedConstructor.methodDescription;
                return inDefinedShape != null ? inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 == null;
            }

            public int hashCode() {
                MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
                return 59 + (inDefinedShape == null ? 43 : inDefinedShape.hashCode());
            }

            protected ForRebasedConstructor(MethodDescription.InDefinedShape inDefinedShape) {
                this.methodDescription = inDefinedShape;
            }

            public static Resolution of(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
                return new ForRebasedConstructor(new RebasedConstructor(inDefinedShape, typeDescription));
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public MethodDescription.InDefinedShape getResolvedMethod() {
                return this.methodDescription;
            }

            @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Resolution
            public StackManipulation getAdditionalArguments() {
                return NullConstant.INSTANCE;
            }

            protected static class RebasedConstructor extends MethodDescription.InDefinedShape.AbstractBase {
                private final MethodDescription.InDefinedShape methodDescription;
                private final TypeDescription placeholderType;

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getInternalName() {
                    return MethodDescription.CONSTRUCTOR_INTERNAL_NAME;
                }

                @Override // net.bytebuddy.description.ModifierReviewable
                public int getModifiers() {
                    return 4098;
                }

                protected RebasedConstructor(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
                    this.methodDescription = inDefinedShape;
                    this.placeholderType = typeDescription;
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeDescription.Generic getReturnType() {
                    return TypeDescription.Generic.VOID;
                }

                @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new ParameterList.Explicit.ForTypes(this, (List<? extends TypeDefinition>) CompoundList.of(this.methodDescription.getParameters().asTypeList().asErasures(), this.placeholderType));
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeList.Generic getExceptionTypes() {
                    return this.methodDescription.getExceptionTypes().asRawTypes();
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public AnnotationValue<?, ?> getDefaultValue() {
                    return AnnotationValue.UNDEFINED;
                }

                @Override // net.bytebuddy.description.TypeVariableSource
                public TypeList.Generic getTypeVariables() {
                    return new TypeList.Generic.Empty();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return this.methodDescription.getDeclaringType();
                }
            }
        }
    }

    public static class Default implements MethodRebaseResolver {
        private final List<DynamicType> dynamicTypes;
        private final Map<MethodDescription.InDefinedShape, Resolution> resolutions;

        protected boolean canEqual(Object obj) {
            return obj instanceof Default;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Default)) {
                return false;
            }
            Default r5 = (Default) obj;
            if (!r5.canEqual(this)) {
                return false;
            }
            Map<MethodDescription.InDefinedShape, Resolution> map = this.resolutions;
            Map<MethodDescription.InDefinedShape, Resolution> map2 = r5.resolutions;
            if (map != null ? !map.equals(map2) : map2 != null) {
                return false;
            }
            List<DynamicType> list = this.dynamicTypes;
            List<DynamicType> list2 = r5.dynamicTypes;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            Map<MethodDescription.InDefinedShape, Resolution> map = this.resolutions;
            int iHashCode = map == null ? 43 : map.hashCode();
            List<DynamicType> list = this.dynamicTypes;
            return ((iHashCode + 59) * 59) + (list != null ? list.hashCode() : 43);
        }

        protected Default(Map<MethodDescription.InDefinedShape, Resolution> map, List<DynamicType> list) {
            this.resolutions = map;
            this.dynamicTypes = list;
        }

        public static MethodRebaseResolver make(TypeDescription typeDescription, Set<? extends MethodDescription.Token> set, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, MethodNameTransformer methodNameTransformer) {
            Resolution resolutionOf;
            HashMap map = new HashMap();
            DynamicType dynamicTypeMake = null;
            for (MethodDescription.InDefinedShape inDefinedShape : typeDescription.getDeclaredMethods()) {
                if (set.contains(inDefinedShape.asToken(ElementMatchers.is(typeDescription)))) {
                    if (inDefinedShape.isConstructor()) {
                        if (dynamicTypeMake == null) {
                            dynamicTypeMake = TrivialType.SIGNATURE_RELEVANT.make(namingStrategy.name(typeDescription), classFileVersion, MethodAccessorFactory.Illegal.INSTANCE);
                        }
                        resolutionOf = Resolution.ForRebasedConstructor.of(inDefinedShape, dynamicTypeMake.getTypeDescription());
                    } else {
                        resolutionOf = Resolution.ForRebasedMethod.of(typeDescription, inDefinedShape, methodNameTransformer);
                    }
                    map.put(inDefinedShape, resolutionOf);
                }
            }
            if (dynamicTypeMake == null) {
                return new Default(map, Collections.emptyList());
            }
            return new Default(map, Collections.singletonList(dynamicTypeMake));
        }

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver
        public Resolution resolve(MethodDescription.InDefinedShape inDefinedShape) {
            Resolution resolution = this.resolutions.get(inDefinedShape);
            return resolution == null ? new Resolution.Preserved(inDefinedShape) : resolution;
        }

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver
        public List<DynamicType> getAuxiliaryTypes() {
            return this.dynamicTypes;
        }

        @Override // net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver
        public Map<MethodDescription.SignatureToken, Resolution> asTokenMap() {
            HashMap map = new HashMap();
            for (Map.Entry<MethodDescription.InDefinedShape, Resolution> entry : this.resolutions.entrySet()) {
                map.put(entry.getKey().asSignatureToken(), entry.getValue());
            }
            return map;
        }
    }
}
