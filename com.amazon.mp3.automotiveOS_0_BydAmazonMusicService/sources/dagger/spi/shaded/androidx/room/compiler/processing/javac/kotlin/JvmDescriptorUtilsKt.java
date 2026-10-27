package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import com.fasterxml.jackson.core.JsonPointer;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import dagger.spi.shaded.auto.common.MoreTypes;
import java.util.Arrays;
import java.util.List;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: JvmDescriptorUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0003*\u00020\u0001H\u0000\u001a\f\u0010\u0002\u001a\u00020\u0001*\u00020\u0005H\u0000\u001a\f\u0010\u0002\u001a\u00020\u0001*\u00020\u0006H\u0000¨\u0006\u0007"}, d2 = {"Ljavax/lang/model/element/ExecutableElement;", "", "descriptor", "Lcom/squareup/javapoet/TypeName;", "typeNameFromJvmSignature", "Ljavax/lang/model/type/TypeMirror;", "Ljavax/lang/model/type/ExecutableType;", "room-compiler-processing"}, k = 2, mv = {1, 7, 1})
public final class JvmDescriptorUtilsKt {
    public static final String descriptor(ExecutableElement executableElement) {
        Intrinsics.checkNotNullParameter(executableElement, "<this>");
        StringBuilder sb = new StringBuilder();
        sb.append(executableElement.getSimpleName());
        ExecutableType executableTypeAsExecutable = MoreTypes.asExecutable(executableElement.asType());
        Intrinsics.checkNotNullExpressionValue(executableTypeAsExecutable, "asExecutable(asType())");
        sb.append(descriptor(executableTypeAsExecutable));
        return sb.toString();
    }

    public static final TypeName typeNameFromJvmSignature(String str) {
        String strReplace$default;
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (!(str.length() > 0)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        char cCharAt = str.charAt(0);
        if (cCharAt == 'B') {
            TypeName BYTE = TypeName.BYTE;
            Intrinsics.checkNotNullExpressionValue(BYTE, "BYTE");
            return BYTE;
        }
        if (cCharAt == 'C') {
            TypeName CHAR = TypeName.CHAR;
            Intrinsics.checkNotNullExpressionValue(CHAR, "CHAR");
            return CHAR;
        }
        if (cCharAt == 'D') {
            TypeName DOUBLE = TypeName.DOUBLE;
            Intrinsics.checkNotNullExpressionValue(DOUBLE, "DOUBLE");
            return DOUBLE;
        }
        if (cCharAt == 'F') {
            TypeName FLOAT = TypeName.FLOAT;
            Intrinsics.checkNotNullExpressionValue(FLOAT, "FLOAT");
            return FLOAT;
        }
        if (cCharAt == 'I') {
            TypeName INT = TypeName.INT;
            Intrinsics.checkNotNullExpressionValue(INT, "INT");
            return INT;
        }
        if (cCharAt == 'J') {
            TypeName LONG = TypeName.LONG;
            Intrinsics.checkNotNullExpressionValue(LONG, "LONG");
            return LONG;
        }
        if (cCharAt == 'S') {
            TypeName SHORT = TypeName.SHORT;
            Intrinsics.checkNotNullExpressionValue(SHORT, "SHORT");
            return SHORT;
        }
        if (cCharAt == 'Z') {
            TypeName BOOLEAN = TypeName.BOOLEAN;
            Intrinsics.checkNotNullExpressionValue(BOOLEAN, "BOOLEAN");
            return BOOLEAN;
        }
        if (cCharAt != 'L') {
            if (cCharAt == '[') {
                String strSubstring = str.substring(1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                ArrayTypeName arrayTypeNameOf = ArrayTypeName.of(typeNameFromJvmSignature(strSubstring));
                Intrinsics.checkNotNullExpressionValue(arrayTypeNameOf, "of(substring(1).typeNameFromJvmSignature())");
                return arrayTypeNameOf;
            }
            throw new IllegalStateException(("unexpected jvm signature " + str).toString());
        }
        int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) str, ";", 0, false, 6, (Object) null);
        if (!(iLastIndexOf$default > 0)) {
            throw new IllegalStateException(("invalid input " + str).toString());
        }
        int iLastIndexOf$default2 = StringsKt__StringsKt.lastIndexOf$default((CharSequence) str, JsonPointer.SEPARATOR, 0, false, 6, (Object) null);
        int i = iLastIndexOf$default2 < 0 ? 1 : iLastIndexOf$default2 + 1;
        if (iLastIndexOf$default2 < 0) {
            strReplace$default = "";
        } else {
            String strSubstring2 = str.substring(1, iLastIndexOf$default2);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
            strReplace$default = StringsKt__StringsJVMKt.replace$default(strSubstring2, JsonPointer.SEPARATOR, '.', false, 4, (Object) null);
        }
        String str2 = strReplace$default;
        int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, Typography.dollar, i, false, 4, (Object) null);
        if (iIndexOf$default < 0) {
            String strSubstring3 = str.substring(i, iLastIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
            ClassName className = ClassName.get(str2, strSubstring3, new String[0]);
            Intrinsics.checkNotNullExpressionValue(className, "{\n                // not…tart, end))\n            }");
            return className;
        }
        String strSubstring4 = str.substring(i, iIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring4, "this as java.lang.String…ing(startIndex, endIndex)");
        String strSubstring5 = str.substring(iIndexOf$default + 1, iLastIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring5, "this as java.lang.String…ing(startIndex, endIndex)");
        Object[] array = StringsKt__StringsKt.split$default((CharSequence) strSubstring5, new char[]{Typography.dollar}, false, 0, 6, (Object) null).toArray(new String[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String[] strArr = (String[]) array;
        ClassName className2 = ClassName.get(str2, strSubstring4, (String[]) Arrays.copyOf(strArr, strArr.length));
        Intrinsics.checkNotNullExpressionValue(className2, "{\n                // nes…impleNames)\n            }");
        return className2;
    }

    public static final String descriptor(TypeMirror typeMirror) {
        Intrinsics.checkNotNullParameter(typeMirror, "<this>");
        Object objAccept = typeMirror.accept(JvmDescriptorTypeVisitor.INSTANCE, Unit.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(objAccept, "accept(JvmDescriptorTypeVisitor, Unit)");
        return (String) objAccept;
    }

    public static final String descriptor(ExecutableType executableType) {
        Intrinsics.checkNotNullParameter(executableType, "<this>");
        List parameterTypes = executableType.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes, "parameterTypes");
        String strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(parameterTypes, "", null, null, 0, null, new Function1<TypeMirror, CharSequence>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.JvmDescriptorUtilsKt$descriptor$parameterDescriptors$1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(TypeMirror it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                return JvmDescriptorUtilsKt.descriptor(it);
            }
        }, 30, null);
        TypeMirror returnType = executableType.getReturnType();
        Intrinsics.checkNotNullExpressionValue(returnType, "returnType");
        return '(' + strJoinToString$default + ')' + descriptor(returnType);
    }
}
