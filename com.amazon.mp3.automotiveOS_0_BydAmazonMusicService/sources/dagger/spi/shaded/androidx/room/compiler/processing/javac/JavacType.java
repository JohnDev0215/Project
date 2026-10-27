package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import com.squareup.javapoet.TypeName;
import com.squareup.kotlinpoet.ClassName;
import dagger.spi.shaded.androidx.room.compiler.codegen.XTypeName;
import dagger.spi.shaded.androidx.room.compiler.processing.JavaPoetExtKt;
import dagger.spi.shaded.androidx.room.compiler.processing.XEquality;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.XType;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmType;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement;
import dagger.spi.shaded.androidx.room.compiler.processing.ksp.KSTypeJavaPoetExtKt;
import dagger.spi.shaded.auto.common.MoreTypes;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\b \u0018\u00002\u00020\u00012\u00020\u0002B!\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b=\u0010>J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0096\u0002J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\fH\u0016R\u001a\u0010\u000f\u001a\u00020\u000e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0014\u001a\u00020\u00138\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\"\u001a\u00020\u001d8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R!\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00000#8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u001f\u001a\u0004\b%\u0010&R\u001d\u0010,\u001a\u0004\u0018\u00010(8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u001f\u001a\u0004\b*\u0010+R\u001f\u00102\u001a\u00060-j\u0002`.8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b/\u0010\u001f\u001a\u0004\b0\u00101R\u001b\u00106\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b3\u0010\u001f\u001a\u0004\b4\u00105R\u0016\u0010:\u001a\u0004\u0018\u0001078&X¦\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u0014\u0010<\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010\u001c¨\u0006?"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XEquality;", "", "isError", "Ldagger/spi/shaded/androidx/room/compiler/codegen/XTypeName;", "asTypeName", "", "other", "equals", "", "hashCode", "", "toString", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "getEnv$room_compiler_processing", "()Landroidx/room/compiler/processing/javac/JavacProcessingEnv;", "Ljavax/lang/model/type/TypeMirror;", "typeMirror", "Ljavax/lang/model/type/TypeMirror;", "getTypeMirror", "()Ljavax/lang/model/type/TypeMirror;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "maybeNullability", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "getMaybeNullability$room_compiler_processing", "()Landroidx/room/compiler/processing/XNullability;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XRawType;", "rawType$delegate", "Lkotlin/Lazy;", "getRawType", "()Landroidx/room/compiler/processing/XRawType;", "rawType", "", "superTypes$delegate", "getSuperTypes", "()Ljava/util/List;", "superTypes", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement;", "typeElement$delegate", "getTypeElement", "()Landroidx/room/compiler/processing/javac/JavacTypeElement;", "typeElement", "Lcom/squareup/javapoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/JTypeName;", "typeName$delegate", "getTypeName", "()Lcom/squareup/javapoet/TypeName;", "typeName", "xTypeName$delegate", "getXTypeName", "()Landroidx/room/compiler/codegen/XTypeName;", "xTypeName", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getKotlinType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "kotlinType", "getNullability", "nullability", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/type/TypeMirror;Landroidx/room/compiler/processing/XNullability;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public abstract class JavacType implements XType, XEquality {
    private final JavacProcessingEnv env;
    private final XNullability maybeNullability;

    /* JADX INFO: renamed from: rawType$delegate, reason: from kotlin metadata */
    private final Lazy rawType;

    /* JADX INFO: renamed from: superTypes$delegate, reason: from kotlin metadata */
    private final Lazy superTypes;

    /* JADX INFO: renamed from: typeElement$delegate, reason: from kotlin metadata */
    private final Lazy typeElement;
    private final TypeMirror typeMirror;

    /* JADX INFO: renamed from: typeName$delegate, reason: from kotlin metadata */
    private final Lazy typeName;

    /* JADX INFO: renamed from: xTypeName$delegate, reason: from kotlin metadata */
    private final Lazy xTypeName;

    public abstract KmType getKotlinType();

    public JavacType(JavacProcessingEnv env, TypeMirror typeMirror, XNullability xNullability) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(typeMirror, "typeMirror");
        this.env = env;
        this.typeMirror = typeMirror;
        this.maybeNullability = xNullability;
        this.rawType = LazyKt__LazyJVMKt.lazy(new Function0<JavacRawType>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacType$rawType$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacRawType invoke() {
                return new JavacRawType(this.this$0.getEnv(), this.this$0);
            }
        });
        this.superTypes = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends JavacType>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacType$superTypes$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends JavacType> invoke() {
                XEquality javacArrayType;
                XEquality javacArrayType2;
                List<TypeMirror> superTypes = this.this$0.getEnv().getTypeUtils().directSupertypes(this.this$0.getTypeMirror());
                Intrinsics.checkNotNullExpressionValue(superTypes, "superTypes");
                JavacType javacType = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(superTypes, 10));
                for (TypeMirror it : superTypes) {
                    Element element = MoreTypes.asTypeElement(it);
                    JavacProcessingEnv env2 = javacType.getEnv();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    KotlinMetadataElement.Companion companion = KotlinMetadataElement.Companion;
                    Intrinsics.checkNotNullExpressionValue(element, "element");
                    Element element2 = element;
                    KotlinMetadataElement kotlinMetadataElementCreateFor = companion.createFor(element2);
                    KmType kmType = kotlinMetadataElementCreateFor != null ? kotlinMetadataElementCreateFor.getKmType() : null;
                    XNullability nullability = ElementExtKt.getNullability(element2);
                    TypeKind kind = it.getKind();
                    int i = kind == null ? -1 : JavacProcessingEnv.WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
                    if (i != 1) {
                        if (i != 2) {
                            if (kmType != null) {
                                javacArrayType = new DefaultJavacType(env2, it, kmType);
                            } else if (nullability != null) {
                                javacArrayType2 = new DefaultJavacType(env2, it, nullability);
                                javacArrayType = javacArrayType2;
                            } else {
                                javacArrayType = new DefaultJavacType(env2, it);
                            }
                        } else if (kmType != null) {
                            DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(it);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                            javacArrayType = new JavacDeclaredType(env2, declaredTypeAsDeclared, kmType);
                        } else if (nullability != null) {
                            DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(it);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                            javacArrayType2 = new JavacDeclaredType(env2, declaredTypeAsDeclared2, nullability);
                            javacArrayType = javacArrayType2;
                        } else {
                            DeclaredType declaredTypeAsDeclared3 = MoreTypes.asDeclared(it);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared3, "asDeclared(typeMirror)");
                            javacArrayType = new JavacDeclaredType(env2, declaredTypeAsDeclared3);
                        }
                    } else if (kmType != null) {
                        ArrayType arrayTypeAsArray = MoreTypes.asArray(it);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(env2, arrayTypeAsArray, kmType);
                    } else if (nullability != null) {
                        ArrayType arrayTypeAsArray2 = MoreTypes.asArray(it);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
                        javacArrayType2 = new JavacArrayType(env2, arrayTypeAsArray2, nullability, null);
                        javacArrayType = javacArrayType2;
                    } else {
                        ArrayType arrayTypeAsArray3 = MoreTypes.asArray(it);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray3, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(env2, arrayTypeAsArray3);
                    }
                    arrayList.add(javacArrayType);
                }
                return arrayList;
            }
        });
        this.typeElement = LazyKt__LazyJVMKt.lazy(new Function0<JavacTypeElement>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacType$typeElement$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacTypeElement invoke() {
                TypeElement typeElementAsTypeElement;
                try {
                    typeElementAsTypeElement = MoreTypes.asTypeElement(this.this$0.getTypeMirror());
                } catch (IllegalArgumentException unused) {
                    typeElementAsTypeElement = null;
                }
                if (typeElementAsTypeElement != null) {
                    return this.this$0.getEnv().wrapTypeElement(typeElementAsTypeElement);
                }
                return null;
            }
        });
        this.typeName = LazyKt__LazyJVMKt.lazy(new Function0<TypeName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacType$typeName$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TypeName invoke() {
                return this.this$0.getXTypeName().getJava();
            }
        });
        this.xTypeName = LazyKt__LazyJVMKt.lazy(new Function0<XTypeName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacType$xTypeName$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final XTypeName invoke() {
                XTypeName.Companion companion = XTypeName.INSTANCE;
                TypeName typeNameSafeTypeName = JavaPoetExtKt.safeTypeName(this.this$0.getTypeMirror());
                ClassName uNAVAILABLE_KTYPE_NAME$room_compiler_processing = companion.getUNAVAILABLE_KTYPE_NAME$room_compiler_processing();
                XNullability maybeNullability = this.this$0.getMaybeNullability();
                if (maybeNullability == null) {
                    maybeNullability = XNullability.UNKNOWN;
                }
                return companion.invoke(typeNameSafeTypeName, uNAVAILABLE_KTYPE_NAME$room_compiler_processing, maybeNullability);
            }
        });
    }

    /* JADX INFO: renamed from: getEnv$room_compiler_processing, reason: from getter */
    public final JavacProcessingEnv getEnv() {
        return this.env;
    }

    public TypeMirror getTypeMirror() {
        return this.typeMirror;
    }

    /* JADX INFO: renamed from: getMaybeNullability$room_compiler_processing, reason: from getter */
    public final XNullability getMaybeNullability() {
        return this.maybeNullability;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XType
    public JavacTypeElement getTypeElement() {
        return (JavacTypeElement) this.typeElement.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XType
    public boolean isError() {
        return getTypeMirror().getKind() == TypeKind.ERROR || (getKotlinType() != null && Intrinsics.areEqual(asTypeName().getJava(), KSTypeJavaPoetExtKt.getERROR_JTYPE_NAME()));
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XType
    public TypeName getTypeName() {
        return (TypeName) this.typeName.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final XTypeName getXTypeName() {
        return (XTypeName) this.xTypeName.getValue();
    }

    public XTypeName asTypeName() {
        return getXTypeName();
    }

    public boolean equals(Object other) {
        return XEquality.INSTANCE.equals(this, other);
    }

    public int hashCode() {
        return XEquality.INSTANCE.hashCode(getEqualityItems());
    }

    public String toString() {
        return getTypeMirror().toString();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XType
    public XNullability getNullability() {
        XNullability xNullability = this.maybeNullability;
        if (xNullability != null) {
            return xNullability;
        }
        throw new IllegalStateException("XType#nullibility cannot be called from this type because it is missing nullability information. Was this type derived from a type created with TypeMirror#toXProcessing(XProcessingEnv)?".toString());
    }
}
