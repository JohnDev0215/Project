package com.squareup.kotlinpoet;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ParameterSpec.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 12\u00020\u0001:\u00011J5\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u0010\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016R\u0017\u0010\u0015\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u001d\u0010&\u001a\b\u0012\u0004\u0012\u00020%0$8\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010+\u001a\u00020*8\u0006¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u0019\u0010/\u001a\u0004\u0018\u00010\u00198\u0006¢\u0006\f\n\u0004\b/\u0010\u001b\u001a\u0004\b0\u0010\u001d¨\u00062"}, d2 = {"Lcom/squareup/kotlinpoet/ParameterSpec;", "", "Lcom/squareup/kotlinpoet/CodeWriter;", "codeWriter", "", "includeType", "emitKdoc", "inlineAnnotations", "", "emit$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeWriter;ZZZ)V", "emit", "emitDefaultValue$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeWriter;)V", "emitDefaultValue", "other", "equals", "", "hashCode", "", "toString", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Lcom/squareup/kotlinpoet/CodeBlock;", "kdoc", "Lcom/squareup/kotlinpoet/CodeBlock;", "getKdoc", "()Lcom/squareup/kotlinpoet/CodeBlock;", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "annotations", "Ljava/util/List;", "getAnnotations", "()Ljava/util/List;", "", "Lcom/squareup/kotlinpoet/KModifier;", "modifiers", "Ljava/util/Set;", "getModifiers", "()Ljava/util/Set;", "Lcom/squareup/kotlinpoet/TypeName;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/squareup/kotlinpoet/TypeName;", "getType", "()Lcom/squareup/kotlinpoet/TypeName;", "defaultValue", "getDefaultValue", "Companion", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public final class ParameterSpec {
    private final List<AnnotationSpec> annotations;
    private final CodeBlock defaultValue;
    private final CodeBlock kdoc;
    private final Set<KModifier> modifiers;
    private final String name;
    private final TypeName type;

    public final String getName() {
        return this.name;
    }

    public final CodeBlock getKdoc() {
        return this.kdoc;
    }

    public final List<AnnotationSpec> getAnnotations() {
        return this.annotations;
    }

    public final Set<KModifier> getModifiers() {
        return this.modifiers;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        CodeWriter codeWriter = new CodeWriter(sb, null, null, null, null, Integer.MAX_VALUE, 30, null);
        try {
            emit$kotlinpoet$default(this, codeWriter, false, false, false, 14, null);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(codeWriter, null);
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "stringBuilder.toString()");
            return string;
        } finally {
        }
    }

    public final TypeName getType() {
        return this.type;
    }

    public static /* synthetic */ void emit$kotlinpoet$default(ParameterSpec parameterSpec, CodeWriter codeWriter, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        if ((i & 8) != 0) {
            z3 = true;
        }
        parameterSpec.emit$kotlinpoet(codeWriter, z, z2, z3);
    }

    public final void emit$kotlinpoet(CodeWriter codeWriter, boolean includeType, boolean emitKdoc, boolean inlineAnnotations) {
        Intrinsics.checkNotNullParameter(codeWriter, "codeWriter");
        if (emitKdoc) {
            codeWriter.emitKdoc(UtilKt.ensureEndsWithNewLine(this.kdoc));
        }
        codeWriter.emitAnnotations(this.annotations, inlineAnnotations);
        CodeWriter.emitModifiers$default(codeWriter, this.modifiers, null, 2, null);
        if (this.name.length() > 0) {
            codeWriter.emitCode("%N", this);
        }
        if ((this.name.length() > 0) && includeType) {
            codeWriter.emitCode(":·");
        }
        if (includeType) {
            codeWriter.emitCode("%T", this.type);
        }
        emitDefaultValue$kotlinpoet(codeWriter);
    }

    public final void emitDefaultValue$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "codeWriter");
        CodeBlock codeBlock = this.defaultValue;
        if (codeBlock != null) {
            codeWriter.emitCode(codeBlock.hasStatements$kotlinpoet() ? " = %L" : " = «%L»", this.defaultValue);
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && Intrinsics.areEqual(ParameterSpec.class, other.getClass())) {
            return Intrinsics.areEqual(toString(), other.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
