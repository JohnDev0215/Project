package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.kotlinpoet.ClassName;
import dagger.spi.shaded.androidx.room.compiler.codegen.XTypeName;
import dagger.spi.shaded.androidx.room.compiler.processing.XArrayType;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmType;
import dagger.spi.shaded.auto.common.MoreTypes;
import java.util.List;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacArrayType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\b\u0016\u0012\u0006\u0010#\u001a\u00020\"\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b$\u0010%B!\b\u0016\u0012\u0006\u0010#\u001a\u00020\"\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b$\u0010&B+\b\u0016\u0012\u0006\u0010#\u001a\u00020\"\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010'\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b$\u0010(B7\b\u0002\u0012\u0006\u0010#\u001a\u00020\"\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010'\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b$\u0010)J\b\u0010\u0004\u001a\u00020\u0003H\u0016R\u001a\u0010\u0006\u001a\u00020\u00058\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R%\u0010\u0018\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00130\u00128VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u001c\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u0015\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010!\u001a\u00020\u001d8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0015\u001a\u0004\b\u001f\u0010 ¨\u0006*"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacArrayType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XArrayType;", "Ldagger/spi/shaded/androidx/room/compiler/codegen/XTypeName;", "asTypeName", "Ljavax/lang/model/type/ArrayType;", "typeMirror", "Ljavax/lang/model/type/ArrayType;", "getTypeMirror", "()Ljavax/lang/model/type/ArrayType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "knownComponentNullability", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "kotlinType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getKotlinType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "", "", "equalityItems$delegate", "Lkotlin/Lazy;", "getEqualityItems", "()[Ljava/lang/Object;", "equalityItems", "xTypeName$delegate", "getXTypeName", "()Landroidx/room/compiler/codegen/XTypeName;", "xTypeName", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "componentType$delegate", "getComponentType", "()Landroidx/room/compiler/processing/XType;", "componentType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/type/ArrayType;)V", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/type/ArrayType;Landroidx/room/compiler/processing/javac/kotlin/KmType;)V", "nullability", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/type/ArrayType;Landroidx/room/compiler/processing/XNullability;Landroidx/room/compiler/processing/XNullability;)V", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/type/ArrayType;Landroidx/room/compiler/processing/XNullability;Landroidx/room/compiler/processing/XNullability;Landroidx/room/compiler/processing/javac/kotlin/KmType;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class JavacArrayType extends JavacType implements XArrayType {

    /* JADX INFO: renamed from: componentType$delegate, reason: from kotlin metadata */
    private final Lazy componentType;

    /* JADX INFO: renamed from: equalityItems$delegate, reason: from kotlin metadata */
    private final Lazy equalityItems;
    private final XNullability knownComponentNullability;
    private final KmType kotlinType;
    private final ArrayType typeMirror;

    /* JADX INFO: renamed from: xTypeName$delegate, reason: from kotlin metadata */
    private final Lazy xTypeName;

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacType
    /* JADX INFO: renamed from: getTypeMirror, reason: from getter and merged with bridge method [inline-methods] */
    public ArrayType getTypeMirror() {
        return this.typeMirror;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacType
    public KmType getKotlinType() {
        return this.kotlinType;
    }

    private JavacArrayType(final JavacProcessingEnv javacProcessingEnv, ArrayType arrayType, XNullability xNullability, XNullability xNullability2, KmType kmType) {
        super(javacProcessingEnv, (TypeMirror) arrayType, xNullability);
        this.typeMirror = arrayType;
        this.knownComponentNullability = xNullability2;
        this.kotlinType = kmType;
        this.equalityItems = LazyKt__LazyJVMKt.lazy(new Function0<ArrayType[]>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacArrayType$equalityItems$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ArrayType[] invoke() {
                return new ArrayType[]{this.this$0.mo2801getTypeMirror()};
            }
        });
        this.xTypeName = LazyKt__LazyJVMKt.lazy(new Function0<XTypeName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacArrayType$xTypeName$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final XTypeName invoke() {
                XTypeName.Companion companion = XTypeName.INSTANCE;
                ArrayTypeName arrayTypeName = ArrayTypeName.get(this.this$0.mo2801getTypeMirror());
                Intrinsics.checkNotNullExpressionValue(arrayTypeName, "get(typeMirror)");
                ClassName uNAVAILABLE_KTYPE_NAME$room_compiler_processing = companion.getUNAVAILABLE_KTYPE_NAME$room_compiler_processing();
                XNullability xNullability3 = this.this$0.knownComponentNullability;
                if (xNullability3 == null) {
                    xNullability3 = XNullability.UNKNOWN;
                }
                return companion.invoke(arrayTypeName, uNAVAILABLE_KTYPE_NAME$room_compiler_processing, xNullability3);
            }
        });
        this.componentType = LazyKt__LazyJVMKt.lazy(new Function0<JavacType>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacArrayType$componentType$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacType invoke() {
                JavacType javacArrayType;
                List<KmType> typeArguments;
                TypeMirror componentType = this.this$0.mo2801getTypeMirror().getComponentType();
                XNullability xNullability3 = this.this$0.knownComponentNullability;
                if (xNullability3 == null) {
                    if (componentType.getKind().isPrimitive()) {
                        xNullability3 = XNullability.NONNULL;
                    } else {
                        xNullability3 = XNullability.UNKNOWN;
                    }
                }
                JavacProcessingEnv javacProcessingEnv2 = javacProcessingEnv;
                Intrinsics.checkNotNullExpressionValue(componentType, "componentType");
                KmType kotlinType = this.this$0.getKotlinType();
                KmType kmType2 = (kotlinType == null || (typeArguments = kotlinType.getTypeArguments()) == null) ? null : (KmType) CollectionsKt___CollectionsKt.firstOrNull((List) typeArguments);
                TypeKind kind = componentType.getKind();
                int i = kind == null ? -1 : JavacProcessingEnv.WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        if (kmType2 != null) {
                            return new DefaultJavacType(javacProcessingEnv2, componentType, kmType2);
                        }
                        if (xNullability3 != null) {
                            javacArrayType = new DefaultJavacType(javacProcessingEnv2, componentType, xNullability3);
                        } else {
                            return new DefaultJavacType(javacProcessingEnv2, componentType);
                        }
                    } else {
                        if (kmType2 != null) {
                            DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(componentType);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                            return new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared, kmType2);
                        }
                        if (xNullability3 != null) {
                            DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(componentType);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                            javacArrayType = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared2, xNullability3);
                        } else {
                            DeclaredType declaredTypeAsDeclared3 = MoreTypes.asDeclared(componentType);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared3, "asDeclared(typeMirror)");
                            return new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared3);
                        }
                    }
                } else {
                    if (kmType2 != null) {
                        ArrayType arrayTypeAsArray = MoreTypes.asArray(componentType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
                        return new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray, kmType2);
                    }
                    if (xNullability3 != null) {
                        ArrayType arrayTypeAsArray2 = MoreTypes.asArray(componentType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray2, xNullability3, null);
                    } else {
                        ArrayType arrayTypeAsArray3 = MoreTypes.asArray(componentType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray3, "asArray(typeMirror)");
                        return new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray3);
                    }
                }
                return javacArrayType;
            }
        });
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JavacArrayType(JavacProcessingEnv env, ArrayType typeMirror) {
        this(env, typeMirror, null, null, null);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(typeMirror, "typeMirror");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public JavacArrayType(JavacProcessingEnv env, ArrayType typeMirror, KmType kotlinType) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(typeMirror, "typeMirror");
        Intrinsics.checkNotNullParameter(kotlinType, "kotlinType");
        XNullability nullability = KmTypeExtKt.getNullability(kotlinType);
        KmType kmType = (KmType) CollectionsKt___CollectionsKt.firstOrNull((List) kotlinType.getTypeArguments());
        this(env, typeMirror, nullability, kmType != null ? KmTypeExtKt.getNullability(kmType) : null, kotlinType);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JavacArrayType(JavacProcessingEnv env, ArrayType typeMirror, XNullability nullability, XNullability xNullability) {
        this(env, typeMirror, nullability, xNullability, null);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(typeMirror, "typeMirror");
        Intrinsics.checkNotNullParameter(nullability, "nullability");
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XEquality
    public Object[] getEqualityItems() {
        return (Object[]) this.equalityItems.getValue();
    }

    private final XTypeName getXTypeName() {
        return (XTypeName) this.xTypeName.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacType
    public XTypeName asTypeName() {
        return getXTypeName();
    }
}
