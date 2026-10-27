package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import com.squareup.javapoet.TypeVariableName;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmFunction;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmType;
import dagger.spi.shaded.auto.common.MoreTypes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacMethodType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \u001b2\u00020\u00012\u00020\u0002:\u0003\u001b\u001c\u001dB!\b\u0004\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\r\u001a\u00020\b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR)\u0010\u0014\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f0\u000e8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\u0013\u0082\u0001\u0002\u001e\u001f¨\u0006 "}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacExecutableType;", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodElement;", "element", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodElement;", "getElement", "()Landroidx/room/compiler/processing/javac/JavacMethodElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacType;", "returnType$delegate", "Lkotlin/Lazy;", "getReturnType", "()Landroidx/room/compiler/processing/javac/JavacType;", "returnType", "", "Lcom/squareup/javapoet/TypeVariableName;", "kotlin.jvm.PlatformType", "typeVariableNames$delegate", "getTypeVariableNames", "()Ljava/util/List;", "typeVariableNames", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "Ljavax/lang/model/type/ExecutableType;", "executableType", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Landroidx/room/compiler/processing/javac/JavacMethodElement;Ljavax/lang/model/type/ExecutableType;)V", "Companion", "NormalMethodType", "SuspendMethodType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodType$NormalMethodType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodType$SuspendMethodType;", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public abstract class JavacMethodType extends JavacExecutableType {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final JavacMethodElement element;

    /* JADX INFO: renamed from: returnType$delegate, reason: from kotlin metadata */
    private final Lazy returnType;

    /* JADX INFO: renamed from: typeVariableNames$delegate, reason: from kotlin metadata */
    private final Lazy typeVariableNames;

    public /* synthetic */ JavacMethodType(JavacProcessingEnv javacProcessingEnv, JavacMethodElement javacMethodElement, ExecutableType executableType, DefaultConstructorMarker defaultConstructorMarker) {
        this(javacProcessingEnv, javacMethodElement, executableType);
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacExecutableType
    public JavacMethodElement getElement() {
        return this.element;
    }

    private JavacMethodType(final JavacProcessingEnv javacProcessingEnv, JavacMethodElement javacMethodElement, final ExecutableType executableType) {
        super(javacProcessingEnv, javacMethodElement, executableType);
        this.element = javacMethodElement;
        this.returnType = LazyKt__LazyJVMKt.lazy(new Function0<JavacType>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacMethodType$returnType$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacType invoke() {
                KmFunction kotlinMetadata;
                JavacType javacArrayType;
                JavacProcessingEnv javacProcessingEnv2 = javacProcessingEnv;
                TypeMirror returnType = executableType.getReturnType();
                Intrinsics.checkNotNullExpressionValue(returnType, "executableType.returnType");
                KmType returnType2 = (this.getElement().isSuspendFunction() || (kotlinMetadata = this.getElement().getKotlinMetadata()) == null) ? null : kotlinMetadata.getReturnType();
                XNullability nullability = ElementExtKt.getNullability(this.getElement().getElement());
                TypeKind kind = returnType.getKind();
                int i = kind == null ? -1 : JavacProcessingEnv.WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        if (returnType2 != null) {
                            return new DefaultJavacType(javacProcessingEnv2, returnType, returnType2);
                        }
                        if (nullability != null) {
                            return new DefaultJavacType(javacProcessingEnv2, returnType, nullability);
                        }
                        return new DefaultJavacType(javacProcessingEnv2, returnType);
                    }
                    if (returnType2 != null) {
                        DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(returnType);
                        Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                        return new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared, returnType2);
                    }
                    if (nullability != null) {
                        DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(returnType);
                        Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                        javacArrayType = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared2, nullability);
                    } else {
                        DeclaredType declaredTypeAsDeclared3 = MoreTypes.asDeclared(returnType);
                        Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared3, "asDeclared(typeMirror)");
                        javacArrayType = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared3);
                    }
                } else {
                    if (returnType2 != null) {
                        ArrayType arrayTypeAsArray = MoreTypes.asArray(returnType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
                        return new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray, returnType2);
                    }
                    if (nullability != null) {
                        ArrayType arrayTypeAsArray2 = MoreTypes.asArray(returnType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray2, nullability, null);
                    } else {
                        ArrayType arrayTypeAsArray3 = MoreTypes.asArray(returnType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray3, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray3);
                    }
                }
                return javacArrayType;
            }
        });
        this.typeVariableNames = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends TypeVariableName>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacMethodType$typeVariableNames$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends TypeVariableName> invoke() {
                List typeVariables = executableType.getTypeVariables();
                Intrinsics.checkNotNullExpressionValue(typeVariables, "executableType.typeVariables");
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(typeVariables, 10));
                Iterator it = typeVariables.iterator();
                while (it.hasNext()) {
                    arrayList.add(TypeVariableName.get((TypeVariable) it.next()));
                }
                return arrayList;
            }
        });
    }

    /* JADX INFO: compiled from: JavacMethodType.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodType$NormalMethodType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodType;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "element", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodElement;", "executableType", "Ljavax/lang/model/type/ExecutableType;", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Landroidx/room/compiler/processing/javac/JavacMethodElement;Ljavax/lang/model/type/ExecutableType;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
    private static final class NormalMethodType extends JavacMethodType {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NormalMethodType(JavacProcessingEnv env, JavacMethodElement element, ExecutableType executableType) {
            super(env, element, executableType, null);
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(element, "element");
            Intrinsics.checkNotNullParameter(executableType, "executableType");
        }
    }

    /* JADX INFO: compiled from: JavacMethodType.kt */
    @Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodType$SuspendMethodType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodType;", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodElement;", "element", "Ljavax/lang/model/type/ExecutableType;", "executableType", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Landroidx/room/compiler/processing/javac/JavacMethodElement;Ljavax/lang/model/type/ExecutableType;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
    private static final class SuspendMethodType extends JavacMethodType {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SuspendMethodType(JavacProcessingEnv env, JavacMethodElement element, ExecutableType executableType) {
            super(env, element, executableType, null);
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(element, "element");
            Intrinsics.checkNotNullParameter(executableType, "executableType");
        }
    }

    /* JADX INFO: compiled from: JavacMethodType.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodType$Companion;", "", "()V", "create", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodType;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "element", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodElement;", "executableType", "Ljavax/lang/model/type/ExecutableType;", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final JavacMethodType create(JavacProcessingEnv env, JavacMethodElement element, ExecutableType executableType) {
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(element, "element");
            Intrinsics.checkNotNullParameter(executableType, "executableType");
            if (element.isSuspendFunction()) {
                return new SuspendMethodType(env, element, executableType);
            }
            return new NormalMethodType(env, element, executableType);
        }
    }
}
