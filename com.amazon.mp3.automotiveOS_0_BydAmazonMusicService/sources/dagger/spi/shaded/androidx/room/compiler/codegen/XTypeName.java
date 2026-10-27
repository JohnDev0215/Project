package dagger.spi.shaded.androidx.room.compiler.codegen;

import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.WildcardTypeName;
import com.squareup.kotlinpoet.ClassName;
import com.squareup.kotlinpoet.TypeNames;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: XTypeName.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB)\b\u0004\u0012\n\u0010\u000b\u001a\u00060\tj\u0002`\n\u0012\n\u0010\u0011\u001a\u00060\u000fj\u0002`\u0010\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u001a\u0010\u001bJ\u0013\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016R\u001e\u0010\u000b\u001a\u00060\tj\u0002`\n8\u0010X\u0090\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0011\u001a\u00060\u000fj\u0002`\u00108\u0010X\u0090\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0016\u001a\u00020\u00158\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/codegen/XTypeName;", "", "other", "", "equals", "", "hashCode", "", "toString", "Lcom/squareup/javapoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/JTypeName;", "java", "Lcom/squareup/javapoet/TypeName;", "getJava$room_compiler_processing", "()Lcom/squareup/javapoet/TypeName;", "Lcom/squareup/kotlinpoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/KTypeName;", "kotlin", "Lcom/squareup/kotlinpoet/TypeName;", "getKotlin$room_compiler_processing", "()Lcom/squareup/kotlinpoet/TypeName;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "nullability", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "getNullability$room_compiler_processing", "()Landroidx/room/compiler/processing/XNullability;", "<init>", "(Lcom/squareup/javapoet/TypeName;Lcom/squareup/kotlinpoet/TypeName;Landroidx/room/compiler/processing/XNullability;)V", "Companion", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public class XTypeName {
    private static final XTypeName ANY_WILDCARD;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final XTypeName PRIMITIVE_BOOLEAN;
    private static final XTypeName PRIMITIVE_BYTE;
    private static final XTypeName PRIMITIVE_CHAR;
    private static final XTypeName PRIMITIVE_DOUBLE;
    private static final XTypeName PRIMITIVE_FLOAT;
    private static final XTypeName PRIMITIVE_INT;
    private static final XTypeName PRIMITIVE_LONG;
    private static final XTypeName PRIMITIVE_SHORT;
    private static final ClassName UNAVAILABLE_KTYPE_NAME;
    private static final XTypeName UNIT_VOID;
    private final TypeName java;
    private final com.squareup.kotlinpoet.TypeName kotlin;
    private final XNullability nullability;

    protected XTypeName(TypeName java, com.squareup.kotlinpoet.TypeName kotlin2, XNullability nullability) {
        Intrinsics.checkNotNullParameter(java, "java");
        Intrinsics.checkNotNullParameter(kotlin2, "kotlin");
        Intrinsics.checkNotNullParameter(nullability, "nullability");
        this.java = java;
        this.kotlin = kotlin2;
        this.nullability = nullability;
    }

    /* JADX INFO: renamed from: getJava$room_compiler_processing, reason: from getter */
    public TypeName getJava() {
        return this.java;
    }

    /* JADX INFO: renamed from: getKotlin$room_compiler_processing, reason: from getter */
    public com.squareup.kotlinpoet.TypeName getKotlin() {
        return this.kotlin;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof XTypeName)) {
            return false;
        }
        XTypeName xTypeName = (XTypeName) other;
        if (!Intrinsics.areEqual(getJava(), xTypeName.getJava())) {
            return false;
        }
        com.squareup.kotlinpoet.TypeName kotlin2 = getKotlin();
        ClassName className = UNAVAILABLE_KTYPE_NAME;
        return Intrinsics.areEqual(kotlin2, className) || Intrinsics.areEqual(xTypeName.getKotlin(), className) || Intrinsics.areEqual(getKotlin(), xTypeName.getKotlin());
    }

    public int hashCode() {
        return getJava().hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("XTypeName[");
        sb.append(getJava());
        sb.append(" / ");
        if (!Intrinsics.areEqual(getKotlin(), UNAVAILABLE_KTYPE_NAME)) {
            sb.append(getKotlin());
        } else {
            sb.append("UNAVAILABLE");
        }
        sb.append("]");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    /* JADX INFO: compiled from: XTypeName.kt */
    @Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b(\u0010)J+\u0010\u000b\u001a\u00020\n2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0007\u001a\u00060\u0005j\u0002`\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u0086\u0002R\u001e\u0010\u000e\u001a\u00060\fj\u0002`\r8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u0017\u0010\u0018\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015R\u0017\u0010\u001a\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0013\u001a\u0004\b\u001b\u0010\u0015R\u0017\u0010\u001c\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0013\u001a\u0004\b\u001d\u0010\u0015R\u0017\u0010\u001e\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u0013\u001a\u0004\b\u001f\u0010\u0015R\u0017\u0010 \u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b \u0010\u0013\u001a\u0004\b!\u0010\u0015R\u0017\u0010\"\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\"\u0010\u0013\u001a\u0004\b#\u0010\u0015R\u0017\u0010$\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b$\u0010\u0013\u001a\u0004\b%\u0010\u0015R\u0017\u0010&\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b&\u0010\u0013\u001a\u0004\b'\u0010\u0015¨\u0006*"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/codegen/XTypeName$Companion;", "", "Lcom/squareup/javapoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/JTypeName;", "java", "Lcom/squareup/kotlinpoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/KTypeName;", "kotlin", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "nullability", "Ldagger/spi/shaded/androidx/room/compiler/codegen/XTypeName;", "invoke", "Lcom/squareup/kotlinpoet/ClassName;", "Lcom/squareup/kotlinpoet/javapoet/KClassName;", "UNAVAILABLE_KTYPE_NAME", "Lcom/squareup/kotlinpoet/ClassName;", "getUNAVAILABLE_KTYPE_NAME$room_compiler_processing", "()Lcom/squareup/kotlinpoet/ClassName;", "ANY_WILDCARD", "Ldagger/spi/shaded/androidx/room/compiler/codegen/XTypeName;", "getANY_WILDCARD", "()Landroidx/room/compiler/codegen/XTypeName;", "PRIMITIVE_BOOLEAN", "getPRIMITIVE_BOOLEAN", "PRIMITIVE_BYTE", "getPRIMITIVE_BYTE", "PRIMITIVE_CHAR", "getPRIMITIVE_CHAR", "PRIMITIVE_DOUBLE", "getPRIMITIVE_DOUBLE", "PRIMITIVE_FLOAT", "getPRIMITIVE_FLOAT", "PRIMITIVE_INT", "getPRIMITIVE_INT", "PRIMITIVE_LONG", "getPRIMITIVE_LONG", "PRIMITIVE_SHORT", "getPRIMITIVE_SHORT", "UNIT_VOID", "getUNIT_VOID", "<init>", "()V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ClassName getUNAVAILABLE_KTYPE_NAME$room_compiler_processing() {
            return XTypeName.UNAVAILABLE_KTYPE_NAME;
        }

        public static /* synthetic */ XTypeName invoke$default(Companion companion, TypeName typeName, com.squareup.kotlinpoet.TypeName typeName2, XNullability xNullability, int i, Object obj) {
            if ((i & 4) != 0) {
                xNullability = XNullability.NONNULL;
            }
            return companion.invoke(typeName, typeName2, xNullability);
        }

        public final XTypeName invoke(TypeName java, com.squareup.kotlinpoet.TypeName kotlin2, XNullability nullability) {
            Intrinsics.checkNotNullParameter(java, "java");
            Intrinsics.checkNotNullParameter(kotlin2, "kotlin");
            Intrinsics.checkNotNullParameter(nullability, "nullability");
            return new XTypeName(java, kotlin2, nullability);
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        TypeName VOID = TypeName.VOID;
        Intrinsics.checkNotNullExpressionValue(VOID, "VOID");
        UNIT_VOID = Companion.invoke$default(companion, VOID, TypeNames.UNIT, null, 4, null);
        PRIMITIVE_BOOLEAN = XTypeNameKt.asPrimitiveTypeName(Reflection.getOrCreateKotlinClass(Boolean.TYPE));
        PRIMITIVE_BYTE = XTypeNameKt.asPrimitiveTypeName(Reflection.getOrCreateKotlinClass(Byte.TYPE));
        PRIMITIVE_SHORT = XTypeNameKt.asPrimitiveTypeName(Reflection.getOrCreateKotlinClass(Short.TYPE));
        PRIMITIVE_INT = XTypeNameKt.asPrimitiveTypeName(Reflection.getOrCreateKotlinClass(Integer.TYPE));
        PRIMITIVE_LONG = XTypeNameKt.asPrimitiveTypeName(Reflection.getOrCreateKotlinClass(Long.TYPE));
        PRIMITIVE_CHAR = XTypeNameKt.asPrimitiveTypeName(Reflection.getOrCreateKotlinClass(Character.TYPE));
        PRIMITIVE_FLOAT = XTypeNameKt.asPrimitiveTypeName(Reflection.getOrCreateKotlinClass(Float.TYPE));
        PRIMITIVE_DOUBLE = XTypeNameKt.asPrimitiveTypeName(Reflection.getOrCreateKotlinClass(Double.TYPE));
        WildcardTypeName wildcardTypeNameSubtypeOf = WildcardTypeName.subtypeOf(Object.class);
        Intrinsics.checkNotNullExpressionValue(wildcardTypeNameSubtypeOf, "subtypeOf(Object::class.java)");
        ANY_WILDCARD = Companion.invoke$default(companion, wildcardTypeNameSubtypeOf, TypeNames.STAR, null, 4, null);
        UNAVAILABLE_KTYPE_NAME = new ClassName("dagger.spi.shaded.androidx.room.compiler.codegen", "Unavailable");
    }
}
