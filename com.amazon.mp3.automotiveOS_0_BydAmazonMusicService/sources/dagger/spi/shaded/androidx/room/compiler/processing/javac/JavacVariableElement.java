package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import com.amazon.identity.auth.device.utils.AccountConstants;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.XVariableElement;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmType;
import dagger.spi.shaded.auto.common.MoreTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacVariableElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\r\u001a\u00020\b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0011\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00128&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001a"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacVariableElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XVariableElement;", "Ljavax/lang/model/element/VariableElement;", "element", "Ljavax/lang/model/element/VariableElement;", "getElement", "()Ljavax/lang/model/element/VariableElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacType;", "type$delegate", "Lkotlin/Lazy;", "getType", "()Landroidx/room/compiler/processing/javac/JavacType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "getName", "()Ljava/lang/String;", "name", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getKotlinType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "kotlinType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/element/VariableElement;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public abstract class JavacVariableElement extends JavacElement implements XVariableElement {
    private final VariableElement element;

    /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
    private final Lazy type;

    public abstract KmType getKotlinType();

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacElement
    public VariableElement getElement() {
        return this.element;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavacVariableElement(final JavacProcessingEnv env, VariableElement element) {
        super(env, (Element) element);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(element, "element");
        this.element = element;
        this.type = LazyKt__LazyJVMKt.lazy(new Function0<JavacType>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacVariableElement$type$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacType invoke() {
                JavacType javacArrayType;
                JavacProcessingEnv javacProcessingEnv = env;
                TypeMirror typeMirrorAsType = this.getElement().asType();
                Intrinsics.checkNotNullExpressionValue(typeMirrorAsType, "element.asType()");
                KmType kotlinType = this.getKotlinType();
                XNullability nullability = ElementExtKt.getNullability(this.getElement());
                TypeKind kind = typeMirrorAsType.getKind();
                int i = kind == null ? -1 : JavacProcessingEnv.WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        if (kotlinType != null) {
                            return new DefaultJavacType(javacProcessingEnv, typeMirrorAsType, kotlinType);
                        }
                        if (nullability != null) {
                            javacArrayType = new DefaultJavacType(javacProcessingEnv, typeMirrorAsType, nullability);
                        } else {
                            return new DefaultJavacType(javacProcessingEnv, typeMirrorAsType);
                        }
                    } else {
                        if (kotlinType != null) {
                            DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(typeMirrorAsType);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                            return new JavacDeclaredType(javacProcessingEnv, declaredTypeAsDeclared, kotlinType);
                        }
                        if (nullability != null) {
                            DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(typeMirrorAsType);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                            javacArrayType = new JavacDeclaredType(javacProcessingEnv, declaredTypeAsDeclared2, nullability);
                        } else {
                            DeclaredType declaredTypeAsDeclared3 = MoreTypes.asDeclared(typeMirrorAsType);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared3, "asDeclared(typeMirror)");
                            javacArrayType = new JavacDeclaredType(javacProcessingEnv, declaredTypeAsDeclared3);
                        }
                    }
                } else {
                    if (kotlinType != null) {
                        ArrayType arrayTypeAsArray = MoreTypes.asArray(typeMirrorAsType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
                        return new JavacArrayType(javacProcessingEnv, arrayTypeAsArray, kotlinType);
                    }
                    if (nullability != null) {
                        ArrayType arrayTypeAsArray2 = MoreTypes.asArray(typeMirrorAsType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(javacProcessingEnv, arrayTypeAsArray2, nullability, null);
                    } else {
                        ArrayType arrayTypeAsArray3 = MoreTypes.asArray(typeMirrorAsType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray3, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(javacProcessingEnv, arrayTypeAsArray3);
                    }
                }
                return javacArrayType;
            }
        });
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public String getName() {
        return getElement().getSimpleName().toString();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XVariableElement
    public JavacType getType() {
        return (JavacType) this.type.getValue();
    }
}
