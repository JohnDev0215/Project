package net.bytebuddy.dynamic.scaffold.subclass;

import java.util.Iterator;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.scaffold.FieldRegistry;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.dynamic.scaffold.subclass.SubclassImplementationTarget;
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
public class SubclassDynamicTypeBuilder<T> extends DynamicType.Builder.AbstractBase.Adapter<T> {
    private final ConstructorStrategy constructorStrategy;

    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Adapter
    protected boolean canEqual(Object obj) {
        return obj instanceof SubclassDynamicTypeBuilder;
    }

    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Adapter
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubclassDynamicTypeBuilder)) {
            return false;
        }
        SubclassDynamicTypeBuilder subclassDynamicTypeBuilder = (SubclassDynamicTypeBuilder) obj;
        if (!subclassDynamicTypeBuilder.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        ConstructorStrategy constructorStrategy = this.constructorStrategy;
        ConstructorStrategy constructorStrategy2 = subclassDynamicTypeBuilder.constructorStrategy;
        return constructorStrategy != null ? constructorStrategy.equals(constructorStrategy2) : constructorStrategy2 == null;
    }

    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Adapter
    public int hashCode() {
        int iHashCode = super.hashCode() + 59;
        ConstructorStrategy constructorStrategy = this.constructorStrategy;
        return (iHashCode * 59) + (constructorStrategy == null ? 43 : constructorStrategy.hashCode());
    }

    public SubclassDynamicTypeBuilder(InstrumentedType.WithFlexibleName withFlexibleName, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation, LatentMatcher<? super MethodDescription> latentMatcher, ConstructorStrategy constructorStrategy) {
        this(withFlexibleName, new FieldRegistry.Default(), new MethodRegistry.Default(), TypeAttributeAppender.ForInstrumentedType.INSTANCE, AsmVisitorWrapper.NoOp.INSTANCE, classFileVersion, namingStrategy, factory, annotationRetention, factory2, compiler, typeValidation, latentMatcher, constructorStrategy);
    }

    protected SubclassDynamicTypeBuilder(InstrumentedType.WithFlexibleName withFlexibleName, FieldRegistry fieldRegistry, MethodRegistry methodRegistry, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation, LatentMatcher<? super MethodDescription> latentMatcher, ConstructorStrategy constructorStrategy) {
        super(withFlexibleName, fieldRegistry, methodRegistry, typeAttributeAppender, asmVisitorWrapper, classFileVersion, namingStrategy, factory, annotationRetention, factory2, compiler, typeValidation, latentMatcher);
        this.constructorStrategy = constructorStrategy;
    }

    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Adapter
    protected DynamicType.Builder<T> materialize(InstrumentedType.WithFlexibleName withFlexibleName, FieldRegistry fieldRegistry, MethodRegistry methodRegistry, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation, LatentMatcher<? super MethodDescription> latentMatcher) {
        return new SubclassDynamicTypeBuilder(withFlexibleName, fieldRegistry, methodRegistry, typeAttributeAppender, asmVisitorWrapper, classFileVersion, namingStrategy, factory, annotationRetention, factory2, compiler, typeValidation, latentMatcher, this.constructorStrategy);
    }

    @Override // net.bytebuddy.dynamic.DynamicType.Builder
    public DynamicType.Unloaded<T> make(TypeResolutionStrategy typeResolutionStrategy) {
        return make(typeResolutionStrategy, TypePool.ClassLoading.ofClassPath());
    }

    @Override // net.bytebuddy.dynamic.DynamicType.Builder
    public DynamicType.Unloaded<T> make(TypeResolutionStrategy typeResolutionStrategy, TypePool typePool) {
        MethodRegistry.Compiled compiledCompile = this.constructorStrategy.inject(this.methodRegistry).prepare(applyConstructorStrategy(this.instrumentedType), this.methodGraphCompiler, this.typeValidation, new InstrumentableMatcher(this.ignoredMethods)).compile(SubclassImplementationTarget.Factory.SUPER_CLASS, this.classFileVersion);
        return TypeWriter.Default.forCreation(compiledCompile, this.fieldRegistry.compile(compiledCompile.getInstrumentedType()), this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.annotationValueFilterFactory, this.annotationRetention, this.auxiliaryTypeNamingStrategy, this.implementationContextFactory, this.typeValidation, typePool).make(typeResolutionStrategy.resolve());
    }

    private InstrumentedType applyConstructorStrategy(InstrumentedType instrumentedType) {
        if (!instrumentedType.isInterface()) {
            Iterator<MethodDescription.Token> it = this.constructorStrategy.extractConstructors(instrumentedType).iterator();
            while (it.hasNext()) {
                instrumentedType = instrumentedType.withMethod(it.next());
            }
        }
        return instrumentedType;
    }

    protected static class InstrumentableMatcher implements LatentMatcher<MethodDescription> {
        private final LatentMatcher<? super MethodDescription> ignoredMethods;

        protected boolean canEqual(Object obj) {
            return obj instanceof InstrumentableMatcher;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof InstrumentableMatcher)) {
                return false;
            }
            InstrumentableMatcher instrumentableMatcher = (InstrumentableMatcher) obj;
            if (!instrumentableMatcher.canEqual(this)) {
                return false;
            }
            LatentMatcher<? super MethodDescription> latentMatcher = this.ignoredMethods;
            LatentMatcher<? super MethodDescription> latentMatcher2 = instrumentableMatcher.ignoredMethods;
            return latentMatcher != null ? latentMatcher.equals(latentMatcher2) : latentMatcher2 == null;
        }

        public int hashCode() {
            LatentMatcher<? super MethodDescription> latentMatcher = this.ignoredMethods;
            return 59 + (latentMatcher == null ? 43 : latentMatcher.hashCode());
        }

        protected InstrumentableMatcher(LatentMatcher<? super MethodDescription> latentMatcher) {
            this.ignoredMethods = latentMatcher;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public ElementMatcher<? super MethodDescription> resolve(TypeDescription typeDescription) {
            return ElementMatchers.isVirtual().and(ElementMatchers.not(ElementMatchers.isFinal())).and(ElementMatchers.isVisibleTo(typeDescription)).and(ElementMatchers.not(this.ignoredMethods.resolve(typeDescription))).or(ElementMatchers.isDeclaredBy(typeDescription));
        }
    }
}
