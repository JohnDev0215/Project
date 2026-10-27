package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import com.squareup.javapoet.TypeName;
import com.squareup.kotlinpoet.ClassName;
import dagger.spi.shaded.androidx.room.compiler.codegen.XTypeName;
import dagger.spi.shaded.androidx.room.compiler.processing.JavaPoetExtKt;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.XRawType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacRawType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b\"\u0010#J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u0013\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0096\u0002J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016R\u001c\u0010\u000e\u001a\n \r*\u0004\u0018\u00010\f0\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0011\u001a\n \r*\u0004\u0018\u00010\u00100\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001f\u0010\u0019\u001a\u00060\u0013j\u0002`\u00148VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001d\u001a\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u001b\u0010\u001c¨\u0006$"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacRawType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XRawType;", "Ldagger/spi/shaded/androidx/room/compiler/codegen/XTypeName;", "asTypeName", "", "other", "", "equals", "", "hashCode", "", "toString", "Ljavax/lang/model/type/TypeMirror;", "kotlin.jvm.PlatformType", "erased", "Ljavax/lang/model/type/TypeMirror;", "Ljavax/lang/model/util/Types;", "typeUtils", "Ljavax/lang/model/util/Types;", "Lcom/squareup/javapoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/JTypeName;", "typeName$delegate", "Lkotlin/Lazy;", "getTypeName", "()Lcom/squareup/javapoet/TypeName;", "typeName", "xTypeName$delegate", "getXTypeName", "()Landroidx/room/compiler/codegen/XTypeName;", "xTypeName", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacType;", "original", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Landroidx/room/compiler/processing/javac/JavacType;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class JavacRawType implements XRawType {
    private final TypeMirror erased;

    /* JADX INFO: renamed from: typeName$delegate, reason: from kotlin metadata */
    private final Lazy typeName;
    private final Types typeUtils;

    /* JADX INFO: renamed from: xTypeName$delegate, reason: from kotlin metadata */
    private final Lazy xTypeName;

    public JavacRawType(JavacProcessingEnv env, final JavacType original) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(original, "original");
        this.erased = env.getTypeUtils().erasure(original.getTypeMirror());
        this.typeUtils = env.getDelegate().getTypeUtils();
        this.typeName = LazyKt__LazyJVMKt.lazy(new Function0<TypeName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacRawType$typeName$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TypeName invoke() {
                return this.this$0.getXTypeName().getJava();
            }
        });
        this.xTypeName = LazyKt__LazyJVMKt.lazy(new Function0<XTypeName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacRawType$xTypeName$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final XTypeName invoke() {
                XTypeName.Companion companion = XTypeName.INSTANCE;
                TypeMirror erased = this.this$0.erased;
                Intrinsics.checkNotNullExpressionValue(erased, "erased");
                TypeName typeNameSafeTypeName = JavaPoetExtKt.safeTypeName(erased);
                ClassName uNAVAILABLE_KTYPE_NAME$room_compiler_processing = companion.getUNAVAILABLE_KTYPE_NAME$room_compiler_processing();
                XNullability maybeNullability = original.getMaybeNullability();
                if (maybeNullability == null) {
                    maybeNullability = XNullability.UNKNOWN;
                }
                return companion.invoke(typeNameSafeTypeName, uNAVAILABLE_KTYPE_NAME$room_compiler_processing, maybeNullability);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final XTypeName getXTypeName() {
        return (XTypeName) this.xTypeName.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XRawType
    public XTypeName asTypeName() {
        return getXTypeName();
    }

    public boolean equals(Object other) {
        if (this != other) {
            XTypeName xTypeName = getXTypeName();
            XRawType xRawType = other instanceof XRawType ? (XRawType) other : null;
            if (!Intrinsics.areEqual(xTypeName, xRawType != null ? xRawType.asTypeName() : null)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return getXTypeName().hashCode();
    }

    public String toString() {
        String string = getXTypeName().getJava().toString();
        Intrinsics.checkNotNullExpressionValue(string, "xTypeName.java.toString()");
        return string;
    }
}
