package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import dagger.spi.shaded.androidx.room.compiler.processing.XEquality;
import dagger.spi.shaded.androidx.room.compiler.processing.XExecutableElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XHasModifiers;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.JvmDescriptorUtilsKt;
import dagger.spi.shaded.auto.common.MoreTypes;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
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

/* JADX INFO: compiled from: JavacExecutableElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b \u0018\u0000 ,2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001,B\u0017\u0012\u0006\u0010)\u001a\u00020(\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b*\u0010+J\t\u0010\u0005\u001a\u00020\u0004H\u0096\u0001J\t\u0010\u0006\u001a\u00020\u0004H\u0096\u0001J\t\u0010\u0007\u001a\u00020\u0004H\u0096\u0001J\t\u0010\b\u001a\u00020\u0004H\u0096\u0001R\u001a\u0010\n\u001a\u00020\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0013\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0018\u001a\u00020\u00148VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0017R!\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00198&X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u001dR\u0014\u0010#\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0017R\u0016\u0010'\u001a\u0004\u0018\u00010$8&X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006-"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacExecutableElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XExecutableElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XHasModifiers;", "", "isPrivate", "isProtected", "isPublic", "isStatic", "Ljavax/lang/model/element/ExecutableElement;", "element", "Ljavax/lang/model/element/ExecutableElement;", "getElement", "()Ljavax/lang/model/element/ExecutableElement;", "", "descriptor$delegate", "Lkotlin/Lazy;", "getDescriptor", "()Ljava/lang/String;", "descriptor", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement;", "enclosingElement$delegate", "getEnclosingElement", "()Landroidx/room/compiler/processing/javac/JavacTypeElement;", "enclosingElement", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacType;", "thrownTypes$delegate", "getThrownTypes", "()Ljava/util/List;", "thrownTypes", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodParameter;", "getParameters", "parameters", "getClosestMemberContainer", "closestMemberContainer", "", "getKotlinMetadata", "()Landroidx/room/compiler/processing/javac/kotlin/KmExecutable;", "kotlinMetadata", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/element/ExecutableElement;)V", "Companion", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public abstract class JavacExecutableElement extends JavacElement implements XExecutableElement, XHasModifiers {
    private final /* synthetic */ JavacHasModifiers $$delegate_0;

    /* JADX INFO: renamed from: descriptor$delegate, reason: from kotlin metadata */
    private final Lazy descriptor;
    private final ExecutableElement element;

    /* JADX INFO: renamed from: enclosingElement$delegate, reason: from kotlin metadata */
    private final Lazy enclosingElement;

    /* JADX INFO: renamed from: thrownTypes$delegate, reason: from kotlin metadata */
    private final Lazy thrownTypes;

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XExecutableElement
    public abstract List<JavacMethodParameter> getParameters();

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XHasModifiers
    public boolean isPrivate() {
        return this.$$delegate_0.isPrivate();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XHasModifiers
    public boolean isProtected() {
        return this.$$delegate_0.isProtected();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XHasModifiers
    public boolean isPublic() {
        return this.$$delegate_0.isPublic();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XHasModifiers
    public boolean isStatic() {
        return this.$$delegate_0.isStatic();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacElement
    public ExecutableElement getElement() {
        return this.element;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public JavacExecutableElement(final JavacProcessingEnv env, ExecutableElement element) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(element, "element");
        Element element2 = (Element) element;
        super(env, element2);
        this.element = element;
        this.$$delegate_0 = new JavacHasModifiers(element2);
        this.descriptor = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacExecutableElement$descriptor$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return JvmDescriptorUtilsKt.descriptor(this.this$0.getElement());
            }
        });
        this.enclosingElement = LazyKt__LazyJVMKt.lazy(new Function0<JavacTypeElement>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacExecutableElement$enclosingElement$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacTypeElement invoke() {
                return ElementExtKt.requireEnclosingType(this.this$0.getElement(), env);
            }
        });
        this.thrownTypes = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends JavacType>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacExecutableElement$thrownTypes$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends JavacType> invoke() {
                XEquality javacArrayType;
                XEquality javacArrayType2;
                XEquality defaultJavacType;
                List<TypeMirror> thrownTypes = this.this$0.getElement().getThrownTypes();
                Intrinsics.checkNotNullExpressionValue(thrownTypes, "element.thrownTypes");
                JavacProcessingEnv javacProcessingEnv = env;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(thrownTypes, 10));
                for (TypeMirror it : thrownTypes) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    XNullability xNullability = XNullability.UNKNOWN;
                    TypeKind kind = it.getKind();
                    int i = kind == null ? -1 : JavacProcessingEnv.WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
                    if (i != 1) {
                        if (i != 2) {
                            if (xNullability != null) {
                                defaultJavacType = new DefaultJavacType(javacProcessingEnv, it, xNullability);
                            } else {
                                defaultJavacType = new DefaultJavacType(javacProcessingEnv, it);
                            }
                        } else if (xNullability != null) {
                            DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(it);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                            javacArrayType2 = new JavacDeclaredType(javacProcessingEnv, declaredTypeAsDeclared, xNullability);
                            defaultJavacType = javacArrayType2;
                        } else {
                            DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(it);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                            javacArrayType = new JavacDeclaredType(javacProcessingEnv, declaredTypeAsDeclared2);
                            defaultJavacType = javacArrayType;
                        }
                    } else if (xNullability != null) {
                        ArrayType arrayTypeAsArray = MoreTypes.asArray(it);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
                        javacArrayType2 = new JavacArrayType(javacProcessingEnv, arrayTypeAsArray, xNullability, null);
                        defaultJavacType = javacArrayType2;
                    } else {
                        ArrayType arrayTypeAsArray2 = MoreTypes.asArray(it);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(javacProcessingEnv, arrayTypeAsArray2);
                        defaultJavacType = javacArrayType;
                    }
                    arrayList.add(defaultJavacType);
                }
                return arrayList;
            }
        });
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public JavacTypeElement getEnclosingElement() {
        return (JavacTypeElement) this.enclosingElement.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public JavacTypeElement getClosestMemberContainer() {
        return getEnclosingElement();
    }
}
