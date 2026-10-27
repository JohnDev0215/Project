package dagger.spi.shaded.androidx.room.compiler.processing;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavaPoetExt.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\f\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0001*\u00020\u0001H\u0000\"\u001e\u0010\b\u001a\u00060\u0006j\u0002`\u00078\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ljavax/lang/model/type/TypeMirror;", "Lcom/squareup/javapoet/TypeName;", "safeTypeName", "rawTypeName", "tryUnbox", "tryBox", "Lcom/squareup/javapoet/ClassName;", "Lcom/squareup/kotlinpoet/javapoet/JClassName;", "JAVA_NONE_TYPE_NAME", "Lcom/squareup/javapoet/ClassName;", "getJAVA_NONE_TYPE_NAME", "()Lcom/squareup/javapoet/ClassName;", "room-compiler-processing"}, k = 2, mv = {1, 7, 1})
public final class JavaPoetExtKt {
    private static final ClassName JAVA_NONE_TYPE_NAME;

    static {
        ClassName className = ClassName.get("dagger.spi.shaded.androidx.room.compiler.processing.error", "NotAType", new String[0]);
        Intrinsics.checkNotNullExpressionValue(className, "get(\"androidx.room.compi…ssing.error\", \"NotAType\")");
        JAVA_NONE_TYPE_NAME = className;
    }

    public static final TypeName safeTypeName(TypeMirror typeMirror) {
        Intrinsics.checkNotNullParameter(typeMirror, "<this>");
        if (typeMirror.getKind() == TypeKind.NONE) {
            return JAVA_NONE_TYPE_NAME;
        }
        TypeName typeName = TypeName.get(typeMirror);
        Intrinsics.checkNotNullExpressionValue(typeName, "{\n    TypeName.get(this)\n}");
        return typeName;
    }

    public static final TypeName rawTypeName(TypeName typeName) {
        Intrinsics.checkNotNullParameter(typeName, "<this>");
        if (!(typeName instanceof ParameterizedTypeName)) {
            return typeName;
        }
        ClassName className = ((ParameterizedTypeName) typeName).rawType;
        Intrinsics.checkNotNullExpressionValue(className, "{\n        this.rawType\n    }");
        return className;
    }

    public static final TypeName tryUnbox(TypeName typeName) {
        Intrinsics.checkNotNullParameter(typeName, "<this>");
        if (!typeName.isBoxedPrimitive()) {
            return typeName;
        }
        TypeName typeNameUnbox = typeName.unbox();
        Intrinsics.checkNotNullExpressionValue(typeNameUnbox, "{\n        unbox()\n    }");
        return typeNameUnbox;
    }

    public static final TypeName tryBox(TypeName typeName) {
        Intrinsics.checkNotNullParameter(typeName, "<this>");
        try {
            TypeName typeNameBox = typeName.box();
            Intrinsics.checkNotNullExpressionValue(typeNameBox, "{\n        box()\n    }");
            return typeNameBox;
        } catch (AssertionError unused) {
            return typeName;
        }
    }
}
