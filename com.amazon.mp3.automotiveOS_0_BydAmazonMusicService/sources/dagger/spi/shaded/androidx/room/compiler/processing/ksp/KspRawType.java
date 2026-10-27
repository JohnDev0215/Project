package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.google.devtools.ksp.symbol.KSType;
import com.squareup.javapoet.TypeName;
import dagger.spi.shaded.androidx.room.compiler.codegen.XTypeName;
import dagger.spi.shaded.androidx.room.compiler.processing.JavaPoetExtKt;
import dagger.spi.shaded.androidx.room.compiler.processing.KotlinPoetExtKt;
import dagger.spi.shaded.androidx.room.compiler.processing.XRawType;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KspRawType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u001f\u0010 J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u0013\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0096\u0002J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001b\u0010\u0014\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001f\u0010\u001a\u001a\u00060\u0015j\u0002`\u00168VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001e\u001a\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001c\u0010\u001d¨\u0006!"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspRawType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XRawType;", "Ldagger/spi/shaded/androidx/room/compiler/codegen/XTypeName;", "asTypeName", "", "other", "", "equals", "", "hashCode", "", "toString", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspType;", "original", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspType;", "Lcom/google/devtools/ksp/symbol/KSType;", "ksType$delegate", "Lkotlin/Lazy;", "getKsType", "()Lcom/google/devtools/ksp/symbol/KSType;", "ksType", "Lcom/squareup/javapoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/JTypeName;", "typeName$delegate", "getTypeName", "()Lcom/squareup/javapoet/TypeName;", "typeName", "xTypeName$delegate", "getXTypeName", "()Landroidx/room/compiler/codegen/XTypeName;", "xTypeName", "<init>", "(Landroidx/room/compiler/processing/ksp/KspType;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class KspRawType implements XRawType {

    /* JADX INFO: renamed from: ksType$delegate, reason: from kotlin metadata */
    private final Lazy ksType;
    private final KspType original;

    /* JADX INFO: renamed from: typeName$delegate, reason: from kotlin metadata */
    private final Lazy typeName;

    /* JADX INFO: renamed from: xTypeName$delegate, reason: from kotlin metadata */
    private final Lazy xTypeName;

    public KspRawType(KspType original) {
        Intrinsics.checkNotNullParameter(original, "original");
        this.original = original;
        this.ksType = LazyKt__LazyJVMKt.lazy(new Function0<KSType>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspRawType$ksType$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final KSType invoke() {
                return this.this$0.original.getKsType().starProjection().makeNotNullable();
            }
        });
        this.typeName = LazyKt__LazyJVMKt.lazy(new Function0<TypeName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspRawType$typeName$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TypeName invoke() {
                return this.this$0.getXTypeName().getJava();
            }
        });
        this.xTypeName = LazyKt__LazyJVMKt.lazy(new Function0<XTypeName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspRawType$xTypeName$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final XTypeName invoke() {
                return XTypeName.INSTANCE.invoke(JavaPoetExtKt.rawTypeName(this.this$0.original.asTypeName().getJava()), KotlinPoetExtKt.rawTypeName(this.this$0.original.asTypeName().getKotlin()), this.this$0.original.getNullability());
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
        return getXTypeName().getKotlin().toString();
    }
}
