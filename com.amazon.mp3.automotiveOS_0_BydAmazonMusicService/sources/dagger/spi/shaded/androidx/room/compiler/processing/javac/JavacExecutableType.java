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
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacExecutableType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\b \u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u001f\u0010 J\u0013\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\u00020\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R!\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d¨\u0006!"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacExecutableType;", "", "other", "", "equals", "", "hashCode", "", "toString", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "getEnv", "()Landroidx/room/compiler/processing/javac/JavacProcessingEnv;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacExecutableElement;", "element", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacExecutableElement;", "getElement", "()Landroidx/room/compiler/processing/javac/JavacExecutableElement;", "Ljavax/lang/model/type/ExecutableType;", "executableType", "Ljavax/lang/model/type/ExecutableType;", "getExecutableType", "()Ljavax/lang/model/type/ExecutableType;", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacType;", "parameterTypes$delegate", "Lkotlin/Lazy;", "getParameterTypes", "()Ljava/util/List;", "parameterTypes", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Landroidx/room/compiler/processing/javac/JavacExecutableElement;Ljavax/lang/model/type/ExecutableType;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public abstract class JavacExecutableType {
    private final JavacExecutableElement element;
    private final JavacProcessingEnv env;
    private final ExecutableType executableType;

    /* JADX INFO: renamed from: parameterTypes$delegate, reason: from kotlin metadata */
    private final Lazy parameterTypes;

    public JavacExecutableType(JavacProcessingEnv env, JavacExecutableElement element, ExecutableType executableType) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(executableType, "executableType");
        this.env = env;
        this.element = element;
        this.executableType = executableType;
        this.parameterTypes = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends JavacType>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacExecutableType$parameterTypes$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends JavacType> invoke() {
                XEquality javacArrayType;
                XEquality javacArrayType2;
                List parameterTypes = this.this$0.getExecutableType().getParameterTypes();
                Intrinsics.checkNotNullExpressionValue(parameterTypes, "executableType.parameterTypes");
                JavacExecutableType javacExecutableType = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(parameterTypes, 10));
                int i = 0;
                for (Object obj : parameterTypes) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    TypeMirror typeMirror = (TypeMirror) obj;
                    JavacProcessingEnv env2 = javacExecutableType.getEnv();
                    Intrinsics.checkNotNullExpressionValue(typeMirror, "typeMirror");
                    KmType kotlinType = javacExecutableType.getElement().getParameters().get(i).getKotlinType();
                    XNullability nullability = ElementExtKt.getNullability(javacExecutableType.getElement().getParameters().get(i).m2805getElement());
                    TypeKind kind = typeMirror.getKind();
                    int i3 = kind == null ? -1 : JavacProcessingEnv.WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
                    if (i3 != 1) {
                        if (i3 != 2) {
                            if (kotlinType != null) {
                                javacArrayType = new DefaultJavacType(env2, typeMirror, kotlinType);
                            } else if (nullability != null) {
                                javacArrayType2 = new DefaultJavacType(env2, typeMirror, nullability);
                                javacArrayType = javacArrayType2;
                            } else {
                                javacArrayType = new DefaultJavacType(env2, typeMirror);
                            }
                        } else if (kotlinType != null) {
                            DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(typeMirror);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                            javacArrayType = new JavacDeclaredType(env2, declaredTypeAsDeclared, kotlinType);
                        } else if (nullability != null) {
                            DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(typeMirror);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                            javacArrayType2 = new JavacDeclaredType(env2, declaredTypeAsDeclared2, nullability);
                            javacArrayType = javacArrayType2;
                        } else {
                            DeclaredType declaredTypeAsDeclared3 = MoreTypes.asDeclared(typeMirror);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared3, "asDeclared(typeMirror)");
                            javacArrayType = new JavacDeclaredType(env2, declaredTypeAsDeclared3);
                        }
                    } else if (kotlinType != null) {
                        ArrayType arrayTypeAsArray = MoreTypes.asArray(typeMirror);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(env2, arrayTypeAsArray, kotlinType);
                    } else if (nullability != null) {
                        ArrayType arrayTypeAsArray2 = MoreTypes.asArray(typeMirror);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
                        javacArrayType2 = new JavacArrayType(env2, arrayTypeAsArray2, nullability, null);
                        javacArrayType = javacArrayType2;
                    } else {
                        ArrayType arrayTypeAsArray3 = MoreTypes.asArray(typeMirror);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray3, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(env2, arrayTypeAsArray3);
                    }
                    arrayList.add(javacArrayType);
                    i = i2;
                }
                return arrayList;
            }
        });
    }

    public final JavacProcessingEnv getEnv() {
        return this.env;
    }

    public JavacExecutableElement getElement() {
        return this.element;
    }

    public final ExecutableType getExecutableType() {
        return this.executableType;
    }

    public boolean equals(Object other) {
        if (other instanceof JavacExecutableType) {
            return Intrinsics.areEqual(this.executableType, ((JavacExecutableType) other).executableType);
        }
        return false;
    }

    public int hashCode() {
        return this.executableType.hashCode();
    }

    public String toString() {
        return this.executableType.toString();
    }
}
