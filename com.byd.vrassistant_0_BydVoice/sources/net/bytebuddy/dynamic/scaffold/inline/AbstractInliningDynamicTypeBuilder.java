package net.bytebuddy.dynamic.scaffold.inline;

import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.scaffold.FieldRegistry;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.attribute.AnnotationRetention;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.attribute.TypeAttributeAppender;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractInliningDynamicTypeBuilder<T> extends DynamicType.Builder.AbstractBase.Adapter<T> {
    protected final ClassFileLocator classFileLocator;
    protected final TypeDescription originalType;

    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Adapter
    protected boolean canEqual(Object obj) {
        return obj instanceof AbstractInliningDynamicTypeBuilder;
    }

    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Adapter
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractInliningDynamicTypeBuilder)) {
            return false;
        }
        AbstractInliningDynamicTypeBuilder abstractInliningDynamicTypeBuilder = (AbstractInliningDynamicTypeBuilder) obj;
        if (!abstractInliningDynamicTypeBuilder.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        TypeDescription typeDescription = this.originalType;
        TypeDescription typeDescription2 = abstractInliningDynamicTypeBuilder.originalType;
        if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
            return false;
        }
        ClassFileLocator classFileLocator = this.classFileLocator;
        ClassFileLocator classFileLocator2 = abstractInliningDynamicTypeBuilder.classFileLocator;
        return classFileLocator != null ? classFileLocator.equals(classFileLocator2) : classFileLocator2 == null;
    }

    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Adapter
    public int hashCode() {
        int iHashCode = super.hashCode() + 59;
        TypeDescription typeDescription = this.originalType;
        int iHashCode2 = (iHashCode * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
        ClassFileLocator classFileLocator = this.classFileLocator;
        return (iHashCode2 * 59) + (classFileLocator != null ? classFileLocator.hashCode() : 43);
    }

    protected AbstractInliningDynamicTypeBuilder(InstrumentedType.WithFlexibleName withFlexibleName, FieldRegistry fieldRegistry, MethodRegistry methodRegistry, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation, LatentMatcher<? super MethodDescription> latentMatcher, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        super(withFlexibleName, fieldRegistry, methodRegistry, typeAttributeAppender, asmVisitorWrapper, classFileVersion, namingStrategy, factory, annotationRetention, factory2, compiler, typeValidation, latentMatcher);
        this.originalType = typeDescription;
        this.classFileLocator = classFileLocator;
    }

    @Override // net.bytebuddy.dynamic.DynamicType.Builder
    public DynamicType.Unloaded<T> make(TypeResolutionStrategy typeResolutionStrategy) {
        return make(typeResolutionStrategy, TypePool.Default.of(this.classFileLocator));
    }
}
