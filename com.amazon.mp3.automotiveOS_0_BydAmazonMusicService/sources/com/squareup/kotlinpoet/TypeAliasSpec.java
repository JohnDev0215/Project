package com.squareup.kotlinpoet;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TypeAliasSpec.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 ,2\u00020\u0001:\u0001,J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016R\u0017\u0010\u000f\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010%\u001a\u00020$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u001d\u0010*\u001a\b\u0012\u0004\u0012\u00020)0\u001e8\u0006¢\u0006\f\n\u0004\b*\u0010!\u001a\u0004\b+\u0010#¨\u0006-"}, d2 = {"Lcom/squareup/kotlinpoet/TypeAliasSpec;", "", "Lcom/squareup/kotlinpoet/CodeWriter;", "codeWriter", "", "emit$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeWriter;)V", "emit", "other", "", "equals", "", "hashCode", "", "toString", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Lcom/squareup/kotlinpoet/TypeName;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/squareup/kotlinpoet/TypeName;", "getType", "()Lcom/squareup/kotlinpoet/TypeName;", "", "Lcom/squareup/kotlinpoet/KModifier;", "modifiers", "Ljava/util/Set;", "getModifiers", "()Ljava/util/Set;", "", "Lcom/squareup/kotlinpoet/TypeVariableName;", "typeVariables", "Ljava/util/List;", "getTypeVariables", "()Ljava/util/List;", "Lcom/squareup/kotlinpoet/CodeBlock;", "kdoc", "Lcom/squareup/kotlinpoet/CodeBlock;", "getKdoc", "()Lcom/squareup/kotlinpoet/CodeBlock;", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "annotations", "getAnnotations", "Companion", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public final class TypeAliasSpec {
    private final List<AnnotationSpec> annotations;
    private final CodeBlock kdoc;
    private final Set<KModifier> modifiers;
    private final String name;
    private final TypeName type;
    private final List<TypeVariableName> typeVariables;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        CodeWriter codeWriter = new CodeWriter(sb, null, null, null, null, Integer.MAX_VALUE, 30, null);
        try {
            emit$kotlinpoet(codeWriter);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(codeWriter, null);
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "stringBuilder.toString()");
            return string;
        } finally {
        }
    }

    public final void emit$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "codeWriter");
        codeWriter.emitKdoc(UtilKt.ensureEndsWithNewLine(this.kdoc));
        codeWriter.emitAnnotations(this.annotations, false);
        codeWriter.emitModifiers(this.modifiers, SetsKt__SetsJVMKt.setOf(KModifier.PUBLIC));
        codeWriter.emitCode("typealias %N", this.name);
        codeWriter.emitTypeVariables(this.typeVariables);
        codeWriter.emitCode(" = %T", this.type);
        CodeWriter.emit$default(codeWriter, "\n", false, 2, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && Intrinsics.areEqual(TypeAliasSpec.class, other.getClass())) {
            return Intrinsics.areEqual(toString(), other.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
