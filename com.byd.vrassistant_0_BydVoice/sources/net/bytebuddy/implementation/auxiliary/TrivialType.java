package net.bytebuddy.implementation.auxiliary;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;

/* JADX INFO: loaded from: classes3.dex */
public enum TrivialType implements AuxiliaryType {
    SIGNATURE_RELEVANT(true),
    PLAIN(false);

    private final boolean eager;

    TrivialType(boolean z) {
        this.eager = z;
    }

    @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType
    public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
        List listEmptyList;
        DynamicType.Builder builderSubclass = new ByteBuddy(classFileVersion).with(MethodGraph.Empty.INSTANCE).subclass(Object.class, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS);
        if (this.eager) {
            listEmptyList = Collections.singletonList(AnnotationDescription.Builder.ofType((Class<? extends Annotation>) AuxiliaryType.SignatureRelevant.class).build());
        } else {
            listEmptyList = Collections.emptyList();
        }
        return builderSubclass.annotateType((Collection<? extends AnnotationDescription>) listEmptyList).name(str).modifiers(DEFAULT_TYPE_MODIFIER).make();
    }
}
