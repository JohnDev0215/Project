package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import com.amazon.identity.auth.device.api.MultipleAccountManager;
import dagger.spi.shaded.androidx.room.compiler.processing.XMethodElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacMethodType;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmFunction;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmType;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmValueParameter;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement;
import dagger.spi.shaded.auto.common.MoreElements;
import dagger.spi.shaded.auto.common.MoreTypes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
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

/* JADX INFO: compiled from: JavacMethodElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u00103\u001a\u000202\u0012\u0006\u00105\u001a\u000204¢\u0006\u0004\b6\u00107J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0016R\u001b\u0010\u000f\u001a\u00020\n8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0013\u0010\u0014R!\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u00108VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\f\u001a\u0004\b\u0018\u0010\u0014R\u001d\u0010\u001e\u001a\u0004\u0018\u00010\u001a8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\f\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010#\u001a\u00020\u001f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b \u0010\f\u001a\u0004\b!\u0010\"R\u001b\u0010(\u001a\u00020$8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\f\u001a\u0004\b&\u0010'R#\u0010/\u001a\u0004\u0018\u00010)8BX\u0082\u0084\u0002¢\u0006\u0012\n\u0004\b*\u0010\f\u0012\u0004\b-\u0010.\u001a\u0004\b+\u0010,R\u0014\u00101\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u0010\u000e¨\u00068"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacExecutableElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMethodElement;", "", "isSuspendFunction", "isExtensionFunction", "other", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeElement;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "overrides", "", "name$delegate", "Lkotlin/Lazy;", "getName", "()Ljava/lang/String;", "name", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeParameterElement;", "typeParameters$delegate", "getTypeParameters", "()Ljava/util/List;", "typeParameters", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodParameter;", "parameters$delegate", "getParameters", "parameters", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmFunction;", "kotlinMetadata$delegate", "getKotlinMetadata", "()Landroidx/room/compiler/processing/javac/kotlin/KmFunction;", "kotlinMetadata", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodType;", "executableType$delegate", "getExecutableType", "()Landroidx/room/compiler/processing/javac/JavacMethodType;", "executableType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacType;", "returnType$delegate", "getReturnType", "()Landroidx/room/compiler/processing/javac/JavacType;", "returnType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement;", "kotlinDefaultImplClass$delegate", "getKotlinDefaultImplClass", "()Landroidx/room/compiler/processing/javac/JavacTypeElement;", "getKotlinDefaultImplClass$annotations", "()V", "kotlinDefaultImplClass", "getJvmName", "jvmName", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "Ljavax/lang/model/element/ExecutableElement;", "element", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/element/ExecutableElement;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class JavacMethodElement extends JavacExecutableElement implements XMethodElement {

    /* JADX INFO: renamed from: executableType$delegate, reason: from kotlin metadata */
    private final Lazy executableType;

    /* JADX INFO: renamed from: kotlinDefaultImplClass$delegate, reason: from kotlin metadata */
    private final Lazy kotlinDefaultImplClass;

    /* JADX INFO: renamed from: kotlinMetadata$delegate, reason: from kotlin metadata */
    private final Lazy kotlinMetadata;

    /* JADX INFO: renamed from: name$delegate, reason: from kotlin metadata */
    private final Lazy name;

    /* JADX INFO: renamed from: parameters$delegate, reason: from kotlin metadata */
    private final Lazy parameters;

    /* JADX INFO: renamed from: returnType$delegate, reason: from kotlin metadata */
    private final Lazy returnType;

    /* JADX INFO: renamed from: typeParameters$delegate, reason: from kotlin metadata */
    private final Lazy typeParameters;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavacMethodElement(final JavacProcessingEnv env, final ExecutableElement element) {
        super(env, element);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(element, "element");
        if (!(element.getKind() == ElementKind.METHOD)) {
            throw new IllegalStateException(("Method element is constructed with invalid type: " + element).toString());
        }
        this.name = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacMethodElement$name$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String name;
                KmFunction kotlinMetadata = this.this$0.getKotlinMetadata();
                return (kotlinMetadata == null || (name = kotlinMetadata.getName()) == null) ? this.this$0.getJvmName() : name;
            }
        });
        this.typeParameters = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends JavacTypeParameterElement>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacMethodElement$typeParameters$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends JavacTypeParameterElement> invoke() {
                List<KmType> typeArguments;
                List typeParameters = element.getTypeParameters();
                Intrinsics.checkNotNullExpressionValue(typeParameters, "element.typeParameters");
                JavacMethodElement javacMethodElement = this;
                JavacProcessingEnv javacProcessingEnv = env;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(typeParameters, 10));
                int i = 0;
                for (Object obj : typeParameters) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    TypeParameterElement typeParameter = (TypeParameterElement) obj;
                    KmFunction kotlinMetadata = javacMethodElement.getKotlinMetadata();
                    KmType kmType = (kotlinMetadata == null || (typeArguments = kotlinMetadata.getTypeArguments()) == null) ? null : typeArguments.get(i);
                    Intrinsics.checkNotNullExpressionValue(typeParameter, "typeParameter");
                    arrayList.add(new JavacTypeParameterElement(javacProcessingEnv, javacMethodElement, typeParameter, kmType));
                    i = i2;
                }
                return arrayList;
            }
        });
        this.parameters = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends JavacMethodParameter>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacMethodElement$parameters$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends JavacMethodParameter> invoke() {
                List parameters = element.getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters, "element.parameters");
                JavacProcessingEnv javacProcessingEnv = env;
                final JavacMethodElement javacMethodElement = this;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters, 10));
                final int i = 0;
                for (Object obj : parameters) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    VariableElement variable = (VariableElement) obj;
                    Intrinsics.checkNotNullExpressionValue(variable, "variable");
                    arrayList.add(new JavacMethodParameter(javacProcessingEnv, javacMethodElement, variable, new Function0<KmValueParameter>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacMethodElement$parameters$2$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final KmValueParameter invoke() {
                            List<KmValueParameter> parameters2;
                            int i3 = javacMethodElement.isExtensionFunction() ? i - 1 : i;
                            KmFunction kotlinMetadata = javacMethodElement.getKotlinMetadata();
                            if (kotlinMetadata == null || (parameters2 = kotlinMetadata.getParameters()) == null) {
                                return null;
                            }
                            return (KmValueParameter) CollectionsKt___CollectionsKt.getOrNull(parameters2, i3);
                        }
                    }, i));
                    i = i2;
                }
                return arrayList;
            }
        });
        this.kotlinMetadata = LazyKt__LazyJVMKt.lazy(new Function0<KmFunction>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacMethodElement$kotlinMetadata$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final KmFunction invoke() {
                KotlinMetadataElement kotlinMetadata;
                JavacTypeElement enclosingElement = this.this$0.getEnclosingElement();
                if (!(enclosingElement instanceof JavacTypeElement)) {
                    enclosingElement = null;
                }
                if (enclosingElement == null || (kotlinMetadata = enclosingElement.getKotlinMetadata()) == null) {
                    return null;
                }
                return kotlinMetadata.getFunctionMetadata(element);
            }
        });
        this.executableType = LazyKt__LazyJVMKt.lazy(new Function0<JavacMethodType>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacMethodElement$executableType$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacMethodType invoke() {
                JavacMethodType.Companion companion = JavacMethodType.Companion;
                JavacProcessingEnv javacProcessingEnv = env;
                JavacMethodElement javacMethodElement = this;
                ExecutableType executableTypeAsExecutable = MoreTypes.asExecutable(element.asType());
                Intrinsics.checkNotNullExpressionValue(executableTypeAsExecutable, "asExecutable(element.asType())");
                return companion.create(javacProcessingEnv, javacMethodElement, executableTypeAsExecutable);
            }
        });
        this.returnType = LazyKt__LazyJVMKt.lazy(new Function0<JavacType>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacMethodElement$returnType$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacType invoke() {
                KmFunction kotlinMetadata;
                JavacType javacArrayType;
                JavacProcessingEnv javacProcessingEnv = env;
                TypeMirror returnType = element.getReturnType();
                Intrinsics.checkNotNullExpressionValue(returnType, "element.returnType");
                KmType returnType2 = (this.isSuspendFunction() || (kotlinMetadata = this.getKotlinMetadata()) == null) ? null : kotlinMetadata.getReturnType();
                XNullability nullability = ElementExtKt.getNullability(element);
                TypeKind kind = returnType.getKind();
                int i = kind == null ? -1 : JavacProcessingEnv.WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        if (returnType2 != null) {
                            return new DefaultJavacType(javacProcessingEnv, returnType, returnType2);
                        }
                        if (nullability != null) {
                            return new DefaultJavacType(javacProcessingEnv, returnType, nullability);
                        }
                        return new DefaultJavacType(javacProcessingEnv, returnType);
                    }
                    if (returnType2 != null) {
                        DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(returnType);
                        Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                        return new JavacDeclaredType(javacProcessingEnv, declaredTypeAsDeclared, returnType2);
                    }
                    if (nullability != null) {
                        DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(returnType);
                        Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                        javacArrayType = new JavacDeclaredType(javacProcessingEnv, declaredTypeAsDeclared2, nullability);
                    } else {
                        DeclaredType declaredTypeAsDeclared3 = MoreTypes.asDeclared(returnType);
                        Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared3, "asDeclared(typeMirror)");
                        javacArrayType = new JavacDeclaredType(javacProcessingEnv, declaredTypeAsDeclared3);
                    }
                } else {
                    if (returnType2 != null) {
                        ArrayType arrayTypeAsArray = MoreTypes.asArray(returnType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
                        return new JavacArrayType(javacProcessingEnv, arrayTypeAsArray, returnType2);
                    }
                    if (nullability != null) {
                        ArrayType arrayTypeAsArray2 = MoreTypes.asArray(returnType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(javacProcessingEnv, arrayTypeAsArray2, nullability, null);
                    } else {
                        ArrayType arrayTypeAsArray3 = MoreTypes.asArray(returnType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray3, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(javacProcessingEnv, arrayTypeAsArray3);
                    }
                }
                return javacArrayType;
            }
        });
        this.kotlinDefaultImplClass = LazyKt__LazyJVMKt.lazy(new Function0<JavacTypeElement>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacMethodElement$kotlinDefaultImplClass$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacTypeElement invoke() {
                Element element2;
                List enclosedElements;
                Object next;
                TypeElement enclosingElement = element.getEnclosingElement();
                TypeElement typeElement = enclosingElement instanceof TypeElement ? enclosingElement : null;
                if (typeElement == null || (enclosedElements = typeElement.getEnclosedElements()) == null) {
                    element2 = null;
                } else {
                    Iterator it = enclosedElements.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                        Element element3 = (Element) next;
                        if (MoreElements.isType(element3) && element3.getSimpleName().contentEquals("DefaultImpls")) {
                            break;
                        }
                    }
                    element2 = (Element) next;
                }
                TypeElement typeElement2 = element2 instanceof TypeElement ? (TypeElement) element2 : null;
                if (typeElement2 != null) {
                    return env.wrapTypeElement(typeElement2);
                }
                return null;
            }
        });
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public String getName() {
        return (String) this.name.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XMethodElement
    public String getJvmName() {
        return getElement().getSimpleName().toString();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacExecutableElement, dagger.spi.shaded.androidx.room.compiler.processing.XExecutableElement
    public List<JavacMethodParameter> getParameters() {
        return (List) this.parameters.getValue();
    }

    public KmFunction getKotlinMetadata() {
        return (KmFunction) this.kotlinMetadata.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XMethodElement
    public boolean isSuspendFunction() {
        KmFunction kotlinMetadata = getKotlinMetadata();
        return kotlinMetadata != null && kotlinMetadata.isSuspend();
    }

    public boolean isExtensionFunction() {
        KmFunction kotlinMetadata = getKotlinMetadata();
        return kotlinMetadata != null && kotlinMetadata.isExtension();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XMethodElement
    public boolean overrides(XMethodElement other, XTypeElement owner) {
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(owner, "owner");
        if (!(other instanceof JavacMethodElement)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!(owner instanceof JavacTypeElement)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (getEnv().getBackend() == XProcessingEnv.Backend.JAVAC && isSuspendFunction() && other.isSuspendFunction()) {
            return ElementExtKt.suspendOverrides(getElement(), ((JavacMethodElement) other).getElement(), ((JavacTypeElement) owner).getElement(), getEnv().getTypeUtils());
        }
        return MoreElements.overrides(getElement(), ((JavacMethodElement) other).getElement(), ((JavacTypeElement) owner).getElement(), getEnv().getTypeUtils());
    }
}
