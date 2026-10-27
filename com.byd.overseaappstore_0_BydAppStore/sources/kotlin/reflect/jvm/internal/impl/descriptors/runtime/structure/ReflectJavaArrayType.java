package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import org.jetbrains.annotations.NotNull;
import spi.e;

/* JADX INFO: loaded from: classes2.dex */
public final class ReflectJavaArrayType extends ReflectJavaType implements JavaArrayType {

    @NotNull
    private final Collection<JavaAnnotation> annotations;

    @NotNull
    private final ReflectJavaType componentType;
    private final boolean isDeprecatedInJavaDoc;

    @NotNull
    private final Type reflectType;

    public ReflectJavaArrayType(@NotNull Type reflectType) {
        ReflectJavaType.Factory factory;
        Type componentType;
        String str;
        Intrinsics.checkNotNullParameter(reflectType, "reflectType");
        this.reflectType = reflectType;
        Type reflectType2 = getReflectType();
        if (!(reflectType2 instanceof GenericArrayType)) {
            if (reflectType2 instanceof Class) {
                Class cls = (Class) reflectType2;
                if (cls.isArray()) {
                    factory = ReflectJavaType.Factory;
                    componentType = cls.getComponentType();
                    str = "getComponentType()";
                }
            }
            StringBuilder sbK = e.k("Not an array type (");
            sbK.append(getReflectType().getClass());
            sbK.append("): ");
            sbK.append(getReflectType());
            throw new IllegalArgumentException(sbK.toString());
        }
        factory = ReflectJavaType.Factory;
        componentType = ((GenericArrayType) reflectType2).getGenericComponentType();
        str = "genericComponentType";
        Intrinsics.checkNotNullExpressionValue(componentType, str);
        this.componentType = factory.create(componentType);
        this.annotations = CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    @NotNull
    public Collection<JavaAnnotation> getAnnotations() {
        return this.annotations;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType
    @NotNull
    public ReflectJavaType getComponentType() {
        return this.componentType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType
    @NotNull
    public Type getReflectType() {
        return this.reflectType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return this.isDeprecatedInJavaDoc;
    }
}
