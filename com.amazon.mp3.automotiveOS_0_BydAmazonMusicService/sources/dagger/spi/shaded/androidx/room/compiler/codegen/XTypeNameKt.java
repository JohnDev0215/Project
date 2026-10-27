package dagger.spi.shaded.androidx.room.compiler.codegen;

import com.squareup.javapoet.TypeName;
import com.squareup.kotlinpoet.TypeNames;
import dagger.spi.shaded.androidx.room.compiler.codegen.XTypeName;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: XTypeName.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0002\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0000H\u0000\u001a\u0018\u0010\u0007\u001a\u00060\u0005j\u0002`\u00062\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0002¨\u0006\b"}, d2 = {"Lkotlin/reflect/KClass;", "Ldagger/spi/shaded/androidx/room/compiler/codegen/XTypeName;", "asPrimitiveTypeName", "Ljava/lang/Class;", "klass", "Lcom/squareup/javapoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/JTypeName;", "getPrimitiveJTypeName", "room-compiler-processing"}, k = 2, mv = {1, 7, 1})
public final class XTypeNameKt {
    public static final XTypeName asPrimitiveTypeName(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        if (!JvmClassMappingKt.getJavaClass((KClass) kClass).isPrimitive()) {
            throw new IllegalArgumentException((kClass + " does not represent a primitive.").toString());
        }
        return XTypeName.Companion.invoke$default(XTypeName.INSTANCE, getPrimitiveJTypeName(JvmClassMappingKt.getJavaClass((KClass) kClass)), TypeNames.get(kClass), null, 4, null);
    }

    private static final TypeName getPrimitiveJTypeName(Class<?> cls) {
        if (Intrinsics.areEqual(cls, Void.TYPE)) {
            TypeName VOID = TypeName.VOID;
            Intrinsics.checkNotNullExpressionValue(VOID, "VOID");
            return VOID;
        }
        if (Intrinsics.areEqual(cls, Boolean.TYPE)) {
            TypeName BOOLEAN = TypeName.BOOLEAN;
            Intrinsics.checkNotNullExpressionValue(BOOLEAN, "BOOLEAN");
            return BOOLEAN;
        }
        if (Intrinsics.areEqual(cls, Byte.TYPE)) {
            TypeName BYTE = TypeName.BYTE;
            Intrinsics.checkNotNullExpressionValue(BYTE, "BYTE");
            return BYTE;
        }
        if (Intrinsics.areEqual(cls, Short.TYPE)) {
            TypeName SHORT = TypeName.SHORT;
            Intrinsics.checkNotNullExpressionValue(SHORT, "SHORT");
            return SHORT;
        }
        if (Intrinsics.areEqual(cls, Integer.TYPE)) {
            TypeName INT = TypeName.INT;
            Intrinsics.checkNotNullExpressionValue(INT, "INT");
            return INT;
        }
        if (Intrinsics.areEqual(cls, Long.TYPE)) {
            TypeName LONG = TypeName.LONG;
            Intrinsics.checkNotNullExpressionValue(LONG, "LONG");
            return LONG;
        }
        if (Intrinsics.areEqual(cls, Character.TYPE)) {
            TypeName CHAR = TypeName.CHAR;
            Intrinsics.checkNotNullExpressionValue(CHAR, "CHAR");
            return CHAR;
        }
        if (Intrinsics.areEqual(cls, Float.TYPE)) {
            TypeName FLOAT = TypeName.FLOAT;
            Intrinsics.checkNotNullExpressionValue(FLOAT, "FLOAT");
            return FLOAT;
        }
        if (Intrinsics.areEqual(cls, Double.TYPE)) {
            TypeName DOUBLE = TypeName.DOUBLE;
            Intrinsics.checkNotNullExpressionValue(DOUBLE, "DOUBLE");
            return DOUBLE;
        }
        throw new IllegalStateException(("Can't get JTypeName from java.lang.Class: " + cls).toString());
    }
}
