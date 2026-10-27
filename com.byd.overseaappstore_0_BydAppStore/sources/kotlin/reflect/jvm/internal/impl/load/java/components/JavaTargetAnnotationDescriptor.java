package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class JavaTargetAnnotationDescriptor extends JavaAnnotationDescriptor {
    public static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JavaTargetAnnotationDescriptor.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};

    @NotNull
    private final NotNullLazyValue allValueArguments$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavaTargetAnnotationDescriptor(@NotNull JavaAnnotation annotation, @NotNull LazyJavaResolverContext c) {
        super(c, annotation, StandardNames.FqNames.target);
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        Intrinsics.checkNotNullParameter(c, "c");
        this.allValueArguments$delegate = c.getStorageManager().createLazyValue(new Function0<Map<Name, ? extends ConstantValue<? extends Object>>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.components.JavaTargetAnnotationDescriptor$allValueArguments$2
            {
                super(0);
            }

            /* JADX WARN: Removed duplicated region for block: B:12:0x0033  */
            /* JADX WARN: Removed duplicated region for block: B:15:0x0044  */
            /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
            @Override // kotlin.jvm.functions.Function0
            @org.jetbrains.annotations.NotNull
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.util.Map<kotlin.reflect.jvm.internal.impl.name.Name, ? extends kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue<? extends java.lang.Object>> invoke() {
                /*
                    r3 = this;
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaTargetAnnotationDescriptor r0 = r3.this$0
                    kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument r0 = r0.getFirstArgument()
                    boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayAnnotationArgument
                    r2 = 0
                    if (r1 == 0) goto L1e
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationTargetMapper r0 = kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationTargetMapper.INSTANCE
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaTargetAnnotationDescriptor r1 = r3.this$0
                    kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument r1 = r1.getFirstArgument()
                    kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayAnnotationArgument r1 = (kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayAnnotationArgument) r1
                    java.util.List r1 = r1.getElements()
                L19:
                    kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r0 = r0.mapJavaTargetArguments$descriptors_jvm(r1)
                    goto L30
                L1e:
                    boolean r0 = r0 instanceof kotlin.reflect.jvm.internal.impl.load.java.structure.JavaEnumValueAnnotationArgument
                    if (r0 == 0) goto L2f
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationTargetMapper r0 = kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationTargetMapper.INSTANCE
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaTargetAnnotationDescriptor r1 = r3.this$0
                    kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument r1 = r1.getFirstArgument()
                    java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
                    goto L19
                L2f:
                    r0 = r2
                L30:
                    if (r0 != 0) goto L33
                    goto L41
                L33:
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationMapper r1 = kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationMapper.INSTANCE
                    kotlin.reflect.jvm.internal.impl.name.Name r1 = r1.getTARGET_ANNOTATION_ALLOWED_TARGETS$descriptors_jvm()
                    kotlin.Pair r0 = kotlin.TuplesKt.to(r1, r0)
                    java.util.Map r2 = kotlin.collections.MapsKt.mapOf(r0)
                L41:
                    if (r2 == 0) goto L44
                    goto L48
                L44:
                    java.util.Map r2 = kotlin.collections.MapsKt.emptyMap()
                L48:
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.components.JavaTargetAnnotationDescriptor$allValueArguments$2.invoke():java.util.Map");
            }
        });
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.components.JavaAnnotationDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    @NotNull
    public Map<Name, ConstantValue<Object>> getAllValueArguments() {
        return (Map) StorageKt.getValue(this.allValueArguments$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
    }
}
