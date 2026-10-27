package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX INFO: compiled from: ReflectJavaTypeParameter.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class ReflectJavaTypeParameter extends ReflectJavaElement implements ReflectJavaAnnotationOwner, JavaTypeParameter {
    private final TypeVariable<?> typeVariable;

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public ReflectJavaAnnotation findAnnotation(FqName fqName) {
        return ReflectJavaAnnotationOwner.DefaultImpls.findAnnotation(this, fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public List<ReflectJavaAnnotation> getAnnotations() {
        return ReflectJavaAnnotationOwner.DefaultImpls.getAnnotations(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return ReflectJavaAnnotationOwner.DefaultImpls.isDeprecatedInJavaDoc(this);
    }

    public ReflectJavaTypeParameter(TypeVariable<?> typeVariable) {
        Intrinsics.checkNotNullParameter(typeVariable, "typeVariable");
        this.typeVariable = typeVariable;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter
    public List<ReflectJavaClassifierType> getUpperBounds() {
        Type[] bounds = this.typeVariable.getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "typeVariable.bounds");
        ArrayList arrayList = new ArrayList(bounds.length);
        int length = bounds.length;
        int i = 0;
        while (i < length) {
            Type type = bounds[i];
            i++;
            arrayList.add(new ReflectJavaClassifierType(type));
        }
        ReflectJavaClassifierType reflectJavaClassifierType = (ReflectJavaClassifierType) CollectionsKt___CollectionsKt.singleOrNull((List) arrayList);
        return Intrinsics.areEqual(reflectJavaClassifierType == null ? null : reflectJavaClassifierType.getReflectType(), Object.class) ? CollectionsKt__CollectionsKt.emptyList() : arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    public AnnotatedElement getElement() {
        TypeVariable<?> typeVariable = this.typeVariable;
        if (typeVariable instanceof AnnotatedElement) {
            return (AnnotatedElement) typeVariable;
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaNamedElement
    public Name getName() {
        Name nameIdentifier = Name.identifier(this.typeVariable.getName());
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(typeVariable.name)");
        return nameIdentifier;
    }

    public boolean equals(Object obj) {
        return (obj instanceof ReflectJavaTypeParameter) && Intrinsics.areEqual(this.typeVariable, ((ReflectJavaTypeParameter) obj).typeVariable);
    }

    public int hashCode() {
        return this.typeVariable.hashCode();
    }

    public String toString() {
        return ReflectJavaTypeParameter.class.getName() + ": " + this.typeVariable;
    }
}
