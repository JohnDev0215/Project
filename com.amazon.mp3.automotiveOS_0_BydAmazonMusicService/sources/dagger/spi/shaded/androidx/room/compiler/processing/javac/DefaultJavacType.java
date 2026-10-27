package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmType;
import javax.lang.model.type.TypeMirror;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultJavacType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0011\u0010\u0012B!\b\u0016\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0013B!\b\u0016\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0011\u0010\u0016B-\b\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0017R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R!\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u00078VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/DefaultJavacType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "kotlinType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getKotlinType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "", "Ljavax/lang/model/type/TypeMirror;", "equalityItems$delegate", "Lkotlin/Lazy;", "getEqualityItems", "()[Ljavax/lang/model/type/TypeMirror;", "equalityItems", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "typeMirror", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/type/TypeMirror;)V", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/type/TypeMirror;Landroidx/room/compiler/processing/javac/kotlin/KmType;)V", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "nullability", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/type/TypeMirror;Landroidx/room/compiler/processing/XNullability;)V", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/type/TypeMirror;Landroidx/room/compiler/processing/XNullability;Landroidx/room/compiler/processing/javac/kotlin/KmType;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class DefaultJavacType extends JavacType {

    /* JADX INFO: renamed from: equalityItems$delegate, reason: from kotlin metadata */
    private final Lazy equalityItems;
    private final KmType kotlinType;

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacType
    public KmType getKotlinType() {
        return this.kotlinType;
    }

    private DefaultJavacType(JavacProcessingEnv javacProcessingEnv, final TypeMirror typeMirror, XNullability xNullability, KmType kmType) {
        super(javacProcessingEnv, typeMirror, xNullability);
        this.kotlinType = kmType;
        this.equalityItems = LazyKt__LazyJVMKt.lazy(new Function0<TypeMirror[]>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.DefaultJavacType$equalityItems$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final TypeMirror[] invoke() {
                return new TypeMirror[]{typeMirror};
            }
        });
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DefaultJavacType(JavacProcessingEnv env, TypeMirror typeMirror) {
        this(env, typeMirror, null, null);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(typeMirror, "typeMirror");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DefaultJavacType(JavacProcessingEnv env, TypeMirror typeMirror, KmType kotlinType) {
        this(env, typeMirror, KmTypeExtKt.getNullability(kotlinType), kotlinType);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(typeMirror, "typeMirror");
        Intrinsics.checkNotNullParameter(kotlinType, "kotlinType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DefaultJavacType(JavacProcessingEnv env, TypeMirror typeMirror, XNullability nullability) {
        this(env, typeMirror, nullability, null);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(typeMirror, "typeMirror");
        Intrinsics.checkNotNullParameter(nullability, "nullability");
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XEquality
    public TypeMirror[] getEqualityItems() {
        return (TypeMirror[]) this.equalityItems.getValue();
    }
}
