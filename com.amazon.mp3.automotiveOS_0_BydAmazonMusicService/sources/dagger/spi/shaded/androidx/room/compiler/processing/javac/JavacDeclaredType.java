package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import dagger.spi.shaded.androidx.room.compiler.processing.XEquality;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmType;
import dagger.spi.shaded.auto.common.MoreTypes;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacDeclaredType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u001a\u0010\u001bB!\b\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u001a\u0010\u001cB!\b\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001a\u0010\u001fB-\b\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u001a\u0010 R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR%\u0010\u0012\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\r0\f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0016¨\u0006!"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacDeclaredType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacType;", "Ljavax/lang/model/type/DeclaredType;", "typeMirror", "Ljavax/lang/model/type/DeclaredType;", "getTypeMirror", "()Ljavax/lang/model/type/DeclaredType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "kotlinType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getKotlinType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "", "", "equalityItems$delegate", "Lkotlin/Lazy;", "getEqualityItems", "()[Ljava/lang/Object;", "equalityItems", "", "typeArguments$delegate", "getTypeArguments", "()Ljava/util/List;", "typeArguments", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/type/DeclaredType;)V", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/type/DeclaredType;Landroidx/room/compiler/processing/javac/kotlin/KmType;)V", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "nullability", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/type/DeclaredType;Landroidx/room/compiler/processing/XNullability;)V", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/type/DeclaredType;Landroidx/room/compiler/processing/XNullability;Landroidx/room/compiler/processing/javac/kotlin/KmType;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class JavacDeclaredType extends JavacType {

    /* JADX INFO: renamed from: equalityItems$delegate, reason: from kotlin metadata */
    private final Lazy equalityItems;
    private final KmType kotlinType;

    /* JADX INFO: renamed from: typeArguments$delegate, reason: from kotlin metadata */
    private final Lazy typeArguments;
    private final DeclaredType typeMirror;

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacType
    /* JADX INFO: renamed from: getTypeMirror, reason: from getter and merged with bridge method [inline-methods] */
    public DeclaredType getTypeMirror() {
        return this.typeMirror;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacType
    public KmType getKotlinType() {
        return this.kotlinType;
    }

    private JavacDeclaredType(final JavacProcessingEnv javacProcessingEnv, DeclaredType declaredType, XNullability xNullability, KmType kmType) {
        super(javacProcessingEnv, (TypeMirror) declaredType, xNullability);
        this.typeMirror = declaredType;
        this.kotlinType = kmType;
        this.equalityItems = LazyKt__LazyJVMKt.lazy(new Function0<DeclaredType[]>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacDeclaredType$equalityItems$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final DeclaredType[] invoke() {
                return new DeclaredType[]{this.this$0.mo2801getTypeMirror()};
            }
        });
        this.typeArguments = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends JavacType>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacDeclaredType$typeArguments$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends JavacType> invoke() {
                XEquality javacArrayType;
                XEquality javacArrayType2;
                List<KmType> typeArguments;
                List typeArguments2 = this.this$0.mo2801getTypeMirror().getTypeArguments();
                Intrinsics.checkNotNullExpressionValue(typeArguments2, "typeMirror.typeArguments");
                JavacProcessingEnv javacProcessingEnv2 = javacProcessingEnv;
                JavacDeclaredType javacDeclaredType = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(typeArguments2, 10));
                int i = 0;
                for (Object obj : typeArguments2) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    TypeMirror typeMirror = (TypeMirror) obj;
                    Intrinsics.checkNotNullExpressionValue(typeMirror, "typeMirror");
                    KmType kotlinType = javacDeclaredType.getKotlinType();
                    KmType kmType2 = (kotlinType == null || (typeArguments = kotlinType.getTypeArguments()) == null) ? null : (KmType) CollectionsKt___CollectionsKt.getOrNull(typeArguments, i);
                    XNullability xNullability2 = XNullability.UNKNOWN;
                    TypeKind kind = typeMirror.getKind();
                    int i3 = kind == null ? -1 : JavacProcessingEnv.WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
                    if (i3 != 1) {
                        if (i3 != 2) {
                            if (kmType2 != null) {
                                javacArrayType2 = new DefaultJavacType(javacProcessingEnv2, typeMirror, kmType2);
                            } else if (xNullability2 != null) {
                                javacArrayType = new DefaultJavacType(javacProcessingEnv2, typeMirror, xNullability2);
                                javacArrayType2 = javacArrayType;
                            } else {
                                javacArrayType2 = new DefaultJavacType(javacProcessingEnv2, typeMirror);
                            }
                        } else if (kmType2 != null) {
                            DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(typeMirror);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                            javacArrayType2 = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared, kmType2);
                        } else {
                            if (xNullability2 != null) {
                                DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(typeMirror);
                                Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                                javacArrayType = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared2, xNullability2);
                            } else {
                                DeclaredType declaredTypeAsDeclared3 = MoreTypes.asDeclared(typeMirror);
                                Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared3, "asDeclared(typeMirror)");
                                javacArrayType = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared3);
                            }
                            javacArrayType2 = javacArrayType;
                        }
                    } else if (kmType2 != null) {
                        ArrayType arrayTypeAsArray = MoreTypes.asArray(typeMirror);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
                        javacArrayType2 = new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray, kmType2);
                    } else {
                        if (xNullability2 != null) {
                            ArrayType arrayTypeAsArray2 = MoreTypes.asArray(typeMirror);
                            Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
                            javacArrayType = new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray2, xNullability2, null);
                        } else {
                            ArrayType arrayTypeAsArray3 = MoreTypes.asArray(typeMirror);
                            Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray3, "asArray(typeMirror)");
                            javacArrayType = new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray3);
                        }
                        javacArrayType2 = javacArrayType;
                    }
                    arrayList.add(javacArrayType2);
                    i = i2;
                }
                return arrayList;
            }
        });
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JavacDeclaredType(JavacProcessingEnv env, DeclaredType typeMirror) {
        this(env, typeMirror, null, null);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(typeMirror, "typeMirror");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JavacDeclaredType(JavacProcessingEnv env, DeclaredType typeMirror, KmType kotlinType) {
        this(env, typeMirror, KmTypeExtKt.getNullability(kotlinType), kotlinType);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(typeMirror, "typeMirror");
        Intrinsics.checkNotNullParameter(kotlinType, "kotlinType");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JavacDeclaredType(JavacProcessingEnv env, DeclaredType typeMirror, XNullability nullability) {
        this(env, typeMirror, nullability, null);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(typeMirror, "typeMirror");
        Intrinsics.checkNotNullParameter(nullability, "nullability");
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XEquality
    public Object[] getEqualityItems() {
        return (Object[]) this.equalityItems.getValue();
    }
}
