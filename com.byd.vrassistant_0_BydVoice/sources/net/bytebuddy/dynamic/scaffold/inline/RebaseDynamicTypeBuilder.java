package net.bytebuddy.dynamic.scaffold.inline;

import java.util.HashSet;
import java.util.Set;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.scaffold.FieldRegistry;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.attribute.AnnotationRetention;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.attribute.TypeAttributeAppender;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public class RebaseDynamicTypeBuilder<T> extends AbstractInliningDynamicTypeBuilder<T> {
    private final MethodNameTransformer methodNameTransformer;

    @Override // net.bytebuddy.dynamic.scaffold.inline.AbstractInliningDynamicTypeBuilder, net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Adapter
    protected boolean canEqual(Object obj) {
        return obj instanceof RebaseDynamicTypeBuilder;
    }

    @Override // net.bytebuddy.dynamic.scaffold.inline.AbstractInliningDynamicTypeBuilder, net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Adapter
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RebaseDynamicTypeBuilder)) {
            return false;
        }
        RebaseDynamicTypeBuilder rebaseDynamicTypeBuilder = (RebaseDynamicTypeBuilder) obj;
        if (!rebaseDynamicTypeBuilder.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        MethodNameTransformer methodNameTransformer = this.methodNameTransformer;
        MethodNameTransformer methodNameTransformer2 = rebaseDynamicTypeBuilder.methodNameTransformer;
        return methodNameTransformer != null ? methodNameTransformer.equals(methodNameTransformer2) : methodNameTransformer2 == null;
    }

    @Override // net.bytebuddy.dynamic.scaffold.inline.AbstractInliningDynamicTypeBuilder, net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Adapter
    public int hashCode() {
        int iHashCode = super.hashCode() + 59;
        MethodNameTransformer methodNameTransformer = this.methodNameTransformer;
        return (iHashCode * 59) + (methodNameTransformer == null ? 43 : methodNameTransformer.hashCode());
    }

    public RebaseDynamicTypeBuilder(InstrumentedType.WithFlexibleName withFlexibleName, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation, LatentMatcher<? super MethodDescription> latentMatcher, TypeDescription typeDescription, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
        this(withFlexibleName, new FieldRegistry.Default(), new MethodRegistry.Default(), annotationRetention.isEnabled() ? new TypeAttributeAppender.ForInstrumentedType.Differentiating(typeDescription) : TypeAttributeAppender.ForInstrumentedType.INSTANCE, AsmVisitorWrapper.NoOp.INSTANCE, classFileVersion, namingStrategy, factory, annotationRetention, factory2, compiler, typeValidation, latentMatcher, typeDescription, classFileLocator, methodNameTransformer);
    }

    protected RebaseDynamicTypeBuilder(InstrumentedType.WithFlexibleName withFlexibleName, FieldRegistry fieldRegistry, MethodRegistry methodRegistry, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation, LatentMatcher<? super MethodDescription> latentMatcher, TypeDescription typeDescription, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
        super(withFlexibleName, fieldRegistry, methodRegistry, typeAttributeAppender, asmVisitorWrapper, classFileVersion, namingStrategy, factory, annotationRetention, factory2, compiler, typeValidation, latentMatcher, typeDescription, classFileLocator);
        this.methodNameTransformer = methodNameTransformer;
    }

    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Adapter
    protected DynamicType.Builder<T> materialize(InstrumentedType.WithFlexibleName withFlexibleName, FieldRegistry fieldRegistry, MethodRegistry methodRegistry, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation, LatentMatcher<? super MethodDescription> latentMatcher) {
        return new RebaseDynamicTypeBuilder(withFlexibleName, fieldRegistry, methodRegistry, typeAttributeAppender, asmVisitorWrapper, classFileVersion, namingStrategy, factory, annotationRetention, factory2, compiler, typeValidation, latentMatcher, this.originalType, this.classFileLocator, this.methodNameTransformer);
    }

    @Override // net.bytebuddy.dynamic.DynamicType.Builder
    public DynamicType.Unloaded<T> make(TypeResolutionStrategy typeResolutionStrategy, TypePool typePool) {
        MethodRegistry.Prepared preparedPrepare = this.methodRegistry.prepare(this.instrumentedType, this.methodGraphCompiler, this.typeValidation, InliningImplementationMatcher.of(this.ignoredMethods, this.originalType));
        return TypeWriter.Default.forRebasing(preparedPrepare, this.fieldRegistry.compile(preparedPrepare.getInstrumentedType()), this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.annotationValueFilterFactory, this.annotationRetention, this.auxiliaryTypeNamingStrategy, this.implementationContextFactory, this.typeValidation, typePool, this.originalType, this.classFileLocator, MethodRebaseResolver.Default.make(preparedPrepare.getInstrumentedType(), new HashSet(this.originalType.getDeclaredMethods().asTokenList(ElementMatchers.is(this.originalType)).filter(RebaseableMatcher.of(preparedPrepare.getInstrumentedType(), preparedPrepare.getInstrumentedMethods()))), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.methodNameTransformer)).make(typeResolutionStrategy.resolve());
    }

    protected static class RebaseableMatcher implements ElementMatcher<MethodDescription.Token> {
        private final Set<MethodDescription.Token> tokens;

        protected boolean canEqual(Object obj) {
            return obj instanceof RebaseableMatcher;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RebaseableMatcher)) {
                return false;
            }
            RebaseableMatcher rebaseableMatcher = (RebaseableMatcher) obj;
            if (!rebaseableMatcher.canEqual(this)) {
                return false;
            }
            Set<MethodDescription.Token> set = this.tokens;
            Set<MethodDescription.Token> set2 = rebaseableMatcher.tokens;
            return set != null ? set.equals(set2) : set2 == null;
        }

        public int hashCode() {
            Set<MethodDescription.Token> set = this.tokens;
            return 59 + (set == null ? 43 : set.hashCode());
        }

        protected RebaseableMatcher(Set<MethodDescription.Token> set) {
            this.tokens = set;
        }

        protected static ElementMatcher<MethodDescription.Token> of(TypeDescription typeDescription, MethodList<?> methodList) {
            return new RebaseableMatcher(new HashSet(methodList.asTokenList(ElementMatchers.is(typeDescription))));
        }

        @Override // net.bytebuddy.matcher.ElementMatcher
        public boolean matches(MethodDescription.Token token) {
            return this.tokens.contains(token);
        }
    }
}
