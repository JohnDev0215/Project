package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.Variance;
import com.squareup.javapoet.TypeName;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: KspTypeArgumentType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001 B)\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ\f\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0014J\f\u0010\u0007\u001a\u00060\u0005j\u0002`\u0006H\u0014J\u0010\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\bH\u0014R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0019\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006!"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspTypeArgumentType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspType;", "Lcom/squareup/javapoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/JTypeName;", "resolveJTypeName", "Lcom/squareup/kotlinpoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/KTypeName;", "resolveKTypeName", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "nullability", "copyWithNullability", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "typeParam", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "getTypeParam", "()Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "typeArg", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "getTypeArg", "()Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "_extendsBound$delegate", "Lkotlin/Lazy;", "get_extendsBound", "()Landroidx/room/compiler/processing/ksp/KspType;", "_extendsBound", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspJvmTypeResolver;", "jvmTypeResolver", "<init>", "(Landroidx/room/compiler/processing/ksp/KspProcessingEnv;Lcom/google/devtools/ksp/symbol/KSTypeParameter;Lcom/google/devtools/ksp/symbol/KSTypeArgument;Landroidx/room/compiler/processing/ksp/KspJvmTypeResolver;)V", "DelegatingTypeArg", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class KspTypeArgumentType extends KspType {

    /* JADX INFO: renamed from: _extendsBound$delegate, reason: from kotlin metadata */
    private final Lazy _extendsBound;
    private final KSTypeArgument typeArg;
    private final KSTypeParameter typeParam;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KspTypeArgumentType(final KspProcessingEnv env, KSTypeParameter typeParam, KSTypeArgument typeArg, KspJvmTypeResolver kspJvmTypeResolver) {
        super(env, KSTypeExtKt.requireType(typeArg), kspJvmTypeResolver);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(typeParam, "typeParam");
        Intrinsics.checkNotNullParameter(typeArg, "typeArg");
        this.typeParam = typeParam;
        this.typeArg = typeArg;
        this._extendsBound = LazyKt__LazyJVMKt.lazy(new Function0<KspType>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspTypeArgumentType$_extendsBound$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final KspType invoke() {
                return env.wrap(this.getKsType(), false);
            }
        });
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
    protected TypeName resolveJTypeName() {
        return KSTypeJavaPoetExtKt.asJTypeName(this.typeArg, getEnv().getResolver());
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
    protected com.squareup.kotlinpoet.TypeName resolveKTypeName() {
        return KSTypeKotlinPoetExtKt.asKTypeName(this.typeArg, getEnv().getResolver());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
    public KspTypeArgumentType copyWithNullability(XNullability nullability) {
        Intrinsics.checkNotNullParameter(nullability, "nullability");
        KspProcessingEnv env = getEnv();
        KSTypeParameter kSTypeParameter = this.typeParam;
        DelegatingTypeArg delegatingTypeArg = new DelegatingTypeArg(this.typeArg, KSTypeReferenceExtKt.createTypeReference(KSTypeExtKt.withNullability(getKsType(), nullability)));
        getJvmTypeResolver();
        return new KspTypeArgumentType(env, kSTypeParameter, delegatingTypeArg, null);
    }

    /* JADX INFO: compiled from: KspTypeArgumentType.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0002\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0013\u001a\u00020\u00108\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspTypeArgumentType$DelegatingTypeArg;", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "original", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "getOriginal", "()Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "getType", "()Lcom/google/devtools/ksp/symbol/KSTypeReference;", "Lkotlin/sequences/Sequence;", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "getAnnotations", "()Lkotlin/sequences/Sequence;", "annotations", "Lcom/google/devtools/ksp/symbol/Variance;", "getVariance", "()Lcom/google/devtools/ksp/symbol/Variance;", "variance", "<init>", "(Lcom/google/devtools/ksp/symbol/KSTypeArgument;Lcom/google/devtools/ksp/symbol/KSTypeReference;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
    private static final class DelegatingTypeArg implements KSTypeArgument {
        private final KSTypeArgument original;
        private final KSTypeReference type;

        @Override // com.google.devtools.ksp.symbol.KSAnnotated
        public Sequence<KSAnnotation> getAnnotations() {
            return this.original.getAnnotations();
        }

        @Override // com.google.devtools.ksp.symbol.KSTypeArgument
        public Variance getVariance() {
            return this.original.getVariance();
        }

        public DelegatingTypeArg(KSTypeArgument original, KSTypeReference type) {
            Intrinsics.checkNotNullParameter(original, "original");
            Intrinsics.checkNotNullParameter(type, "type");
            this.original = original;
            this.type = type;
        }

        @Override // com.google.devtools.ksp.symbol.KSTypeArgument
        public KSTypeReference getType() {
            return this.type;
        }
    }
}
