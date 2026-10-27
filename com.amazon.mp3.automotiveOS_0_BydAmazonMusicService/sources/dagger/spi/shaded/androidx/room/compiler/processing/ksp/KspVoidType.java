package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.google.devtools.ksp.symbol.KSType;
import com.squareup.javapoet.TypeName;
import com.squareup.kotlinpoet.TypeNames;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KspVoidType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\f\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0014J\f\u0010\u0007\u001a\u00060\u0005j\u0002`\u0006H\u0014J\u0010\u0010\n\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\bH\u0014R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspVoidType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspType;", "Lcom/squareup/javapoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/JTypeName;", "resolveJTypeName", "Lcom/squareup/kotlinpoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/KTypeName;", "resolveKTypeName", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "nullability", "copyWithNullability", "", "boxed", "Z", "getBoxed", "()Z", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "env", "Lcom/google/devtools/ksp/symbol/KSType;", "ksType", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspJvmTypeResolver;", "jvmTypeResolver", "<init>", "(Landroidx/room/compiler/processing/ksp/KspProcessingEnv;Lcom/google/devtools/ksp/symbol/KSType;ZLandroidx/room/compiler/processing/ksp/KspJvmTypeResolver;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class KspVoidType extends KspType {
    private final boolean boxed;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KspVoidType(KspProcessingEnv env, KSType ksType, boolean z, KspJvmTypeResolver kspJvmTypeResolver) {
        super(env, ksType, kspJvmTypeResolver);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(ksType, "ksType");
        this.boxed = z;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
    protected TypeName resolveJTypeName() {
        if (this.boxed || getNullability() == XNullability.NULLABLE) {
            TypeName typeNameBox = TypeName.VOID.box();
            Intrinsics.checkNotNullExpressionValue(typeNameBox, "{\n            JTypeName.VOID.box()\n        }");
            return typeNameBox;
        }
        TypeName typeName = TypeName.VOID;
        Intrinsics.checkNotNullExpressionValue(typeName, "{\n            JTypeName.VOID\n        }");
        return typeName;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
    protected com.squareup.kotlinpoet.TypeName resolveKTypeName() {
        return TypeNames.UNIT;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType
    protected KspType copyWithNullability(XNullability nullability) {
        Intrinsics.checkNotNullParameter(nullability, "nullability");
        KspProcessingEnv env = getEnv();
        KSType kSTypeWithNullability = KSTypeExtKt.withNullability(getKsType(), nullability);
        boolean z = this.boxed || nullability == XNullability.NULLABLE;
        getJvmTypeResolver();
        return new KspVoidType(env, kSTypeWithNullability, z, null);
    }
}
