package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.google.devtools.ksp.symbol.KSType;
import com.squareup.javapoet.TypeName;
import dagger.spi.shaded.androidx.room.compiler.processing.JavaPoetExtKt;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KspPrimitiveType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\f\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0014J\f\u0010\u0007\u001a\u00060\u0005j\u0002`\u0006H\u0014J\b\u0010\b\u001a\u00020\u0001H\u0016J\u0010\u0010\u000b\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\tH\u0014¨\u0006\u0014"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspPrimitiveType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspType;", "Lcom/squareup/javapoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/JTypeName;", "resolveJTypeName", "Lcom/squareup/kotlinpoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/KTypeName;", "resolveKTypeName", "boxed", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "nullability", "copyWithNullability", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "env", "Lcom/google/devtools/ksp/symbol/KSType;", "ksType", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspJvmTypeResolver;", "jvmTypeResolver", "<init>", "(Landroidx/room/compiler/processing/ksp/KspProcessingEnv;Lcom/google/devtools/ksp/symbol/KSType;Landroidx/room/compiler/processing/ksp/KspJvmTypeResolver;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class KspPrimitiveType extends KspType {

    /* JADX INFO: compiled from: KspPrimitiveType.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[XNullability.values().length];
            try {
                iArr[XNullability.NONNULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[XNullability.NULLABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KspPrimitiveType(KspProcessingEnv env, KSType ksType, KspJvmTypeResolver kspJvmTypeResolver) {
        super(env, ksType, kspJvmTypeResolver);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(ksType, "ksType");
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
    protected TypeName resolveJTypeName() {
        return JavaPoetExtKt.tryUnbox(KSTypeJavaPoetExtKt.asJTypeName(getKsType(), getEnv().getResolver()));
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
    protected com.squareup.kotlinpoet.TypeName resolveKTypeName() {
        return KSTypeKotlinPoetExtKt.asKTypeName(getKsType(), getEnv().getResolver());
    }

    public KspType boxed() {
        return getEnv().wrap(getKsType(), false);
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
    protected KspType copyWithNullability(XNullability nullability) {
        Intrinsics.checkNotNullParameter(nullability, "nullability");
        int i = WhenMappings.$EnumSwitchMapping$0[nullability.ordinal()];
        if (i == 1) {
            return this;
        }
        if (i == 2) {
            return boxed().makeNullable();
        }
        throw new IllegalStateException("cannot set nullability to unknown in KSP".toString());
    }
}
