package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.google.devtools.ksp.symbol.KSName;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.Variance;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.TypeName;
import dagger.spi.shaded.androidx.room.compiler.codegen.XTypeName;
import dagger.spi.shaded.androidx.room.compiler.processing.XArrayType;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KspArrayType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \u00142\u00020\u00012\u00020\u0002:\u0004\u0015\u0014\u0016\u0017B#\b\u0004\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\f\u0010\u0005\u001a\u00060\u0003j\u0002`\u0004H\u0014J\f\u0010\b\u001a\u00060\u0006j\u0002`\u0007H\u0014R\u0014\u0010\u000b\u001a\u00020\u00018&X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n\u0082\u0001\u0002\u0018\u0019¨\u0006\u001a"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspArrayType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XArrayType;", "Lcom/squareup/javapoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/JTypeName;", "resolveJTypeName", "Lcom/squareup/kotlinpoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/KTypeName;", "resolveKTypeName", "getComponentType", "()Landroidx/room/compiler/processing/ksp/KspType;", "componentType", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "env", "Lcom/google/devtools/ksp/symbol/KSType;", "ksType", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspJvmTypeResolver;", "jvmTypeResolver", "<init>", "(Landroidx/room/compiler/processing/ksp/KspProcessingEnv;Lcom/google/devtools/ksp/symbol/KSType;Landroidx/room/compiler/processing/ksp/KspJvmTypeResolver;)V", "Companion", "BoxedArray", "Factory", "PrimitiveArray", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspArrayType$BoxedArray;", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspArrayType$PrimitiveArray;", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public abstract class KspArrayType extends KspType implements XArrayType {
    public /* synthetic */ KspArrayType(KspProcessingEnv kspProcessingEnv, KSType kSType, KspJvmTypeResolver kspJvmTypeResolver, DefaultConstructorMarker defaultConstructorMarker) {
        this(kspProcessingEnv, kSType, kspJvmTypeResolver);
    }

    private KspArrayType(KspProcessingEnv kspProcessingEnv, KSType kSType, KspJvmTypeResolver kspJvmTypeResolver) {
        super(kspProcessingEnv, kSType, kspJvmTypeResolver);
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
    protected TypeName resolveJTypeName() {
        return asTypeName().getJava();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
    protected com.squareup.kotlinpoet.TypeName resolveKTypeName() {
        return asTypeName().getKotlin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: KspArrayType.kt */
    @Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0014R\u001b\u0010\u000b\u001a\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001b\u0010\u0010\u001a\u00020\f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspArrayType$BoxedArray;", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspArrayType;", "Ldagger/spi/shaded/androidx/room/compiler/codegen/XTypeName;", "asTypeName", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "nullability", "copyWithNullability", "xTypeName$delegate", "Lkotlin/Lazy;", "getXTypeName", "()Landroidx/room/compiler/codegen/XTypeName;", "xTypeName", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspType;", "componentType$delegate", "getComponentType", "()Landroidx/room/compiler/processing/ksp/KspType;", "componentType", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "env", "Lcom/google/devtools/ksp/symbol/KSType;", "ksType", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspJvmTypeResolver;", "jvmTypeResolver", "<init>", "(Landroidx/room/compiler/processing/ksp/KspProcessingEnv;Lcom/google/devtools/ksp/symbol/KSType;Landroidx/room/compiler/processing/ksp/KspJvmTypeResolver;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
    static final class BoxedArray extends KspArrayType {

        /* JADX INFO: renamed from: componentType$delegate, reason: from kotlin metadata */
        private final Lazy componentType;

        /* JADX INFO: renamed from: xTypeName$delegate, reason: from kotlin metadata */
        private final Lazy xTypeName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BoxedArray(final KspProcessingEnv env, final KSType ksType, KspJvmTypeResolver kspJvmTypeResolver) {
            super(env, ksType, kspJvmTypeResolver, null);
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(ksType, "ksType");
            this.xTypeName = LazyKt__LazyJVMKt.lazy(new Function0<XTypeName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspArrayType$BoxedArray$xTypeName$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final XTypeName invoke() {
                    XTypeName xTypeNameAsTypeName = this.this$0.getComponentType().asTypeName();
                    XTypeName.Companion companion = XTypeName.INSTANCE;
                    ArrayTypeName arrayTypeNameOf = ArrayTypeName.of(xTypeNameAsTypeName.getJava().box());
                    Intrinsics.checkNotNullExpressionValue(arrayTypeNameOf, "of(componentTypeName.java.box())");
                    return companion.invoke(arrayTypeNameOf, KSTypeKotlinPoetExtKt.asKTypeName(ksType, env.getResolver()), this.this$0.getNullability());
                }
            });
            this.componentType = LazyKt__LazyJVMKt.lazy(new Function0<KspType>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspArrayType$BoxedArray$componentType$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final KspType invoke() {
                    KSTypeArgument kSTypeArgument = (KSTypeArgument) CollectionsKt___CollectionsKt.single((List) ksType.getArguments());
                    KspProcessingEnv kspProcessingEnv = env;
                    KSTypeReference type = kSTypeArgument.getType();
                    KSType resolved = type != null ? type.getResolved() : null;
                    if (resolved != null) {
                        return kspProcessingEnv.wrap(resolved, false);
                    }
                    throw new IllegalStateException("Required value was null.".toString());
                }
            });
        }

        private final XTypeName getXTypeName() {
            return (XTypeName) this.xTypeName.getValue();
        }

        @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
        public XTypeName asTypeName() {
            return getXTypeName();
        }

        public KspType getComponentType() {
            return (KspType) this.componentType.getValue();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
        public BoxedArray copyWithNullability(XNullability nullability) {
            Intrinsics.checkNotNullParameter(nullability, "nullability");
            KspProcessingEnv env = getEnv();
            KSType kSTypeWithNullability = KSTypeExtKt.withNullability(getKsType(), nullability);
            getJvmTypeResolver();
            return new BoxedArray(env, kSTypeWithNullability, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: KspArrayType.kt */
    @Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0014R\u001a\u0010\b\u001a\u00020\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u0010\u001a\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspArrayType$PrimitiveArray;", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspArrayType;", "Ldagger/spi/shaded/androidx/room/compiler/codegen/XTypeName;", "asTypeName", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "nullability", "copyWithNullability", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspType;", "componentType", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspType;", "getComponentType", "()Landroidx/room/compiler/processing/ksp/KspType;", "xTypeName$delegate", "Lkotlin/Lazy;", "getXTypeName", "()Landroidx/room/compiler/codegen/XTypeName;", "xTypeName", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "env", "Lcom/google/devtools/ksp/symbol/KSType;", "ksType", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspJvmTypeResolver;", "jvmTypeResolver", "<init>", "(Landroidx/room/compiler/processing/ksp/KspProcessingEnv;Lcom/google/devtools/ksp/symbol/KSType;Landroidx/room/compiler/processing/ksp/KspJvmTypeResolver;Landroidx/room/compiler/processing/ksp/KspType;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
    static final class PrimitiveArray extends KspArrayType {
        private final KspType componentType;

        /* JADX INFO: renamed from: xTypeName$delegate, reason: from kotlin metadata */
        private final Lazy xTypeName;

        public KspType getComponentType() {
            return this.componentType;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PrimitiveArray(final KspProcessingEnv env, final KSType ksType, KspJvmTypeResolver kspJvmTypeResolver, KspType componentType) {
            super(env, ksType, kspJvmTypeResolver, null);
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(ksType, "ksType");
            Intrinsics.checkNotNullParameter(componentType, "componentType");
            this.componentType = componentType;
            this.xTypeName = LazyKt__LazyJVMKt.lazy(new Function0<XTypeName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspArrayType$PrimitiveArray$xTypeName$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final XTypeName invoke() {
                    XTypeName xTypeNameAsTypeName = this.this$0.getComponentType().asTypeName();
                    XTypeName.Companion companion = XTypeName.INSTANCE;
                    ArrayTypeName arrayTypeNameOf = ArrayTypeName.of(xTypeNameAsTypeName.getJava().unbox());
                    Intrinsics.checkNotNullExpressionValue(arrayTypeNameOf, "of(componentTypeName.java.unbox())");
                    return companion.invoke(arrayTypeNameOf, KSTypeKotlinPoetExtKt.asKTypeName(ksType, env.getResolver()), this.this$0.getNullability());
                }
            });
        }

        private final XTypeName getXTypeName() {
            return (XTypeName) this.xTypeName.getValue();
        }

        @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
        public XTypeName asTypeName() {
            return getXTypeName();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
        public PrimitiveArray copyWithNullability(XNullability nullability) {
            Intrinsics.checkNotNullParameter(nullability, "nullability");
            KspProcessingEnv env = getEnv();
            KSType kSTypeWithNullability = KSTypeExtKt.withNullability(getKsType(), nullability);
            KspType componentType = getComponentType();
            getJvmTypeResolver();
            return new PrimitiveArray(env, kSTypeWithNullability, null, componentType);
        }
    }

    /* JADX INFO: compiled from: KspArrayType.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\nJ\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\t\u001a\u001a\u0012\u0004\u0012\u00020\n\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspArrayType$Factory;", "", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "(Landroidx/room/compiler/processing/ksp/KspProcessingEnv;)V", "builtInArrays", "", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspPrimitiveType;", "reverseBuiltInArrayLookup", "Lcom/google/devtools/ksp/symbol/KSType;", "", "createIfArray", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspArrayType;", "ksType", "createWithComponentType", "componentType", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspType;", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Factory {
        private final Map<String, KspPrimitiveType> builtInArrays;
        private final KspProcessingEnv env;
        private final Map<KSType, Map.Entry<String, KspPrimitiveType>> reverseBuiltInArrayLookup;

        public final KspArrayType createWithComponentType(KspType componentType) {
            Map.Entry<String, KspPrimitiveType> entry;
            Intrinsics.checkNotNullParameter(componentType, "componentType");
            if (componentType.getNullability() == XNullability.NONNULL && (entry = this.reverseBuiltInArrayLookup.get(componentType.getKsType())) != null) {
                KspProcessingEnv kspProcessingEnv = this.env;
                return new PrimitiveArray(kspProcessingEnv, ResolverExtKt.requireType(kspProcessingEnv.getResolver(), entry.getKey()), null, entry.getValue());
            }
            KspProcessingEnv kspProcessingEnv2 = this.env;
            return new BoxedArray(kspProcessingEnv2, kspProcessingEnv2.getResolver().getBuiltIns().getArrayType().replace(CollectionsKt__CollectionsJVMKt.listOf(this.env.getResolver().getTypeArgument(KSTypeReferenceExtKt.createTypeReference(componentType.getKsType()), Variance.INVARIANT))), null);
        }

        public final KspArrayType createIfArray(KSType ksType) {
            Intrinsics.checkNotNullParameter(ksType, "ksType");
            KSName qualifiedName = ksType.getDeclaration().getQualifiedName();
            String strAsString = qualifiedName != null ? qualifiedName.asString() : null;
            if (Intrinsics.areEqual(strAsString, "kotlin.Array")) {
                return new BoxedArray(this.env, ksType, null);
            }
            KspPrimitiveType kspPrimitiveType = this.builtInArrays.get(strAsString);
            if (kspPrimitiveType != null) {
                return new PrimitiveArray(this.env, ksType, null, kspPrimitiveType);
            }
            return null;
        }
    }
}
