package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.CodeBlock;
import com.squareup.kotlinpoet.TypeSpec;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Element;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FunSpec.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 R2\u00020\u00012\u00020\u0002:\u0001RJ\u0016\u0010\u0007\u001a\u00020\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J\u0016\u0010\b\u001a\u00020\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J\u0016\u0010\t\u001a\u00020\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J\u001a\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0006H\u0002J\u000e\u0010\u0013\u001a\u0004\u0018\u00010\u0010*\u00020\u0010H\u0002J\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0014\u001a\u00020\fH\u0000¢\u0006\u0004\b\u0016\u0010\u0017J9\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u0013\u0010\u001e\u001a\u00020\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010 \u001a\u00020\u001fH\u0016J\b\u0010!\u001a\u00020\fH\u0016R\u0014\u0010\"\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0017\u0010\u0014\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0014\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010'\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\u0017\u0010+\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b+\u0010(\u001a\u0004\b,\u0010*R\u0017\u0010-\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b-\u0010(\u001a\u0004\b.\u0010*R\u001d\u00101\u001a\b\u0012\u0004\u0012\u0002000/8\u0006¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u001d\u00105\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u001d\u0010:\u001a\b\u0012\u0004\u0012\u0002090/8\u0006¢\u0006\f\n\u0004\b:\u00102\u001a\u0004\b;\u00104R\u0019\u0010=\u001a\u0004\u0018\u00010<8\u0006¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\u0019\u0010A\u001a\u0004\u0018\u00010<8\u0006¢\u0006\f\n\u0004\bA\u0010>\u001a\u0004\bB\u0010@R\u001d\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00150/8\u0006¢\u0006\f\n\u0004\bC\u00102\u001a\u0004\bD\u00104R\u0019\u0010E\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\bE\u0010$\u001a\u0004\bF\u0010&R\u001d\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00100/8\u0006¢\u0006\f\n\u0004\bG\u00102\u001a\u0004\bH\u00104R\u0017\u0010I\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\bI\u0010(\u001a\u0004\bJ\u0010*R\u0014\u0010K\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010LR\u001a\u0010O\u001a\b\u0012\u0004\u0012\u00020M0/8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bN\u00104R\u0011\u0010P\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\bP\u0010Q¨\u0006S"}, d2 = {"Lcom/squareup/kotlinpoet/FunSpec;", "", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder;", "", "Lcom/squareup/kotlinpoet/KModifier;", "implicitModifiers", "", "shouldOmitBody", "canNotHaveBody", "canBodyBeOmitted", "Lcom/squareup/kotlinpoet/CodeWriter;", "codeWriter", "", "enclosingName", "", "emitSignature", "Lcom/squareup/kotlinpoet/CodeBlock;", "kdocWithTags", "emitUnitReturnType", "asExpressionBody", "name", "Lcom/squareup/kotlinpoet/ParameterSpec;", "parameter$kotlinpoet", "(Ljava/lang/String;)Lcom/squareup/kotlinpoet/ParameterSpec;", "parameter", "includeKdocTags", "emit$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeWriter;Ljava/lang/String;Ljava/util/Set;Z)V", "emit", "other", "equals", "", "hashCode", "toString", "delegateOriginatingElementsHolder", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder;", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "kdoc", "Lcom/squareup/kotlinpoet/CodeBlock;", "getKdoc", "()Lcom/squareup/kotlinpoet/CodeBlock;", "returnKdoc", "getReturnKdoc", "receiverKdoc", "getReceiverKdoc", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "annotations", "Ljava/util/List;", "getAnnotations", "()Ljava/util/List;", "modifiers", "Ljava/util/Set;", "getModifiers", "()Ljava/util/Set;", "Lcom/squareup/kotlinpoet/TypeVariableName;", "typeVariables", "getTypeVariables", "Lcom/squareup/kotlinpoet/TypeName;", "receiverType", "Lcom/squareup/kotlinpoet/TypeName;", "getReceiverType", "()Lcom/squareup/kotlinpoet/TypeName;", "returnType", "getReturnType", "parameters", "getParameters", "delegateConstructor", "getDelegateConstructor", "delegateConstructorArguments", "getDelegateConstructorArguments", "body", "getBody", "isEmptySetter", "Z", "Ljavax/lang/model/element/Element;", "getOriginatingElements", "originatingElements", "isConstructor", "()Z", "Companion", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public final class FunSpec implements OriginatingElementsHolder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final CodeBlock RETURN_EXPRESSION_BODY_PREFIX_NBSP;
    private static final CodeBlock RETURN_EXPRESSION_BODY_PREFIX_SPACE;
    private static final CodeBlock THROW_EXPRESSION_BODY_PREFIX_NBSP;
    private static final CodeBlock THROW_EXPRESSION_BODY_PREFIX_SPACE;
    private final List<AnnotationSpec> annotations;
    private final CodeBlock body;
    private final String delegateConstructor;
    private final List<CodeBlock> delegateConstructorArguments;
    private final OriginatingElementsHolder delegateOriginatingElementsHolder;
    private final boolean isEmptySetter;
    private final CodeBlock kdoc;
    private final Set<KModifier> modifiers;
    private final String name;
    private final List<ParameterSpec> parameters;
    private final CodeBlock receiverKdoc;
    private final TypeName receiverType;
    private final CodeBlock returnKdoc;
    private final TypeName returnType;
    private final List<TypeVariableName> typeVariables;

    @Override // com.squareup.kotlinpoet.OriginatingElementsHolder
    public List<Element> getOriginatingElements() {
        return this.delegateOriginatingElementsHolder.getOriginatingElements();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        CodeWriter codeWriter = new CodeWriter(sb, null, null, null, null, Integer.MAX_VALUE, 30, null);
        try {
            emit$kotlinpoet(codeWriter, "Constructor", TypeSpec.Kind.implicitFunctionModifiers$kotlinpoet$default(TypeSpec.Kind.CLASS, null, 1, null), true);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(codeWriter, null);
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "stringBuilder.toString()");
            return string;
        } finally {
        }
    }

    public final String getName() {
        return this.name;
    }

    public final CodeBlock getReturnKdoc() {
        return this.returnKdoc;
    }

    public final CodeBlock getReceiverKdoc() {
        return this.receiverKdoc;
    }

    public final List<AnnotationSpec> getAnnotations() {
        return this.annotations;
    }

    public final Set<KModifier> getModifiers() {
        return this.modifiers;
    }

    public final List<ParameterSpec> getParameters() {
        return this.parameters;
    }

    public final CodeBlock getBody() {
        return this.body;
    }

    public final ParameterSpec parameter$kotlinpoet(String name) {
        Object next;
        Intrinsics.checkNotNullParameter(name, "name");
        Iterator<T> it = this.parameters.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((ParameterSpec) next).getName(), name)) {
                break;
            }
        }
        return (ParameterSpec) next;
    }

    public final void emit$kotlinpoet(CodeWriter codeWriter, String enclosingName, Set<? extends KModifier> implicitModifiers, boolean includeKdocTags) {
        Intrinsics.checkNotNullParameter(codeWriter, "codeWriter");
        Intrinsics.checkNotNullParameter(implicitModifiers, "implicitModifiers");
        if (includeKdocTags) {
            codeWriter.emitKdoc(kdocWithTags());
        } else {
            codeWriter.emitKdoc(UtilKt.ensureEndsWithNewLine(this.kdoc));
        }
        codeWriter.emitAnnotations(this.annotations, false);
        codeWriter.emitModifiers(this.modifiers, implicitModifiers);
        if (!isConstructor() && !INSTANCE.isAccessor$kotlinpoet(this.name)) {
            codeWriter.emitCode("fun·");
        }
        if (!this.typeVariables.isEmpty()) {
            codeWriter.emitTypeVariables(this.typeVariables);
            CodeWriter.emit$default(codeWriter, " ", false, 2, null);
        }
        emitSignature(codeWriter, enclosingName);
        codeWriter.emitWhereBlock(this.typeVariables);
        if (shouldOmitBody(implicitModifiers)) {
            CodeWriter.emit$default(codeWriter, "\n", false, 2, null);
            return;
        }
        CodeBlock codeBlockAsExpressionBody = asExpressionBody(this.body);
        if (codeBlockAsExpressionBody != null) {
            CodeWriter.emitCode$default(codeWriter, CodeBlock.INSTANCE.of(" = %L", codeBlockAsExpressionBody), false, true, 2, null);
            return;
        }
        if (!this.isEmptySetter) {
            codeWriter.emitCode("·{\n");
            CodeWriter.indent$default(codeWriter, 0, 1, null);
            CodeWriter.emitCode$default(codeWriter, this.body, false, true, 2, null);
            CodeWriter.unindent$default(codeWriter, 0, 1, null);
            CodeWriter.emit$default(codeWriter, "}\n", false, 2, null);
            return;
        }
        CodeWriter.emit$default(codeWriter, "\n", false, 2, null);
    }

    private final boolean shouldOmitBody(Set<? extends KModifier> implicitModifiers) {
        if (!canNotHaveBody(implicitModifiers)) {
            return canBodyBeOmitted(implicitModifiers) && this.body.isEmpty();
        }
        if (this.body.isEmpty()) {
            return true;
        }
        throw new IllegalStateException(("function " + getName() + " cannot have code").toString());
    }

    private final boolean canNotHaveBody(Set<? extends KModifier> implicitModifiers) {
        return this.modifiers.contains(KModifier.ABSTRACT) || SetsKt___SetsKt.plus((Set) this.modifiers, (Iterable) implicitModifiers).contains(KModifier.EXPECT);
    }

    private final boolean canBodyBeOmitted(Set<? extends KModifier> implicitModifiers) {
        return isConstructor() || SetsKt___SetsKt.plus((Set) this.modifiers, (Iterable) implicitModifiers).contains(KModifier.EXTERNAL) || this.modifiers.contains(KModifier.ABSTRACT);
    }

    private final void emitSignature(final CodeWriter codeWriter, String enclosingName) {
        if (isConstructor()) {
            codeWriter.emitCode("constructor", enclosingName);
        } else if (Intrinsics.areEqual(this.name, "get()")) {
            codeWriter.emitCode("get");
        } else if (Intrinsics.areEqual(this.name, "set()")) {
            codeWriter.emitCode("set");
        } else {
            TypeName typeName = this.receiverType;
            if (typeName != null) {
                codeWriter.emitCode("%T.", typeName);
            }
            codeWriter.emitCode("%N", this);
        }
        if (!this.isEmptySetter) {
            ParameterSpecKt.emit$default(this.parameters, codeWriter, false, new Function1<ParameterSpec, Unit>() { // from class: com.squareup.kotlinpoet.FunSpec.emitSignature.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ParameterSpec parameterSpec) {
                    invoke2(parameterSpec);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ParameterSpec param) {
                    Intrinsics.checkNotNullParameter(param, "param");
                    ParameterSpec.emit$kotlinpoet$default(param, codeWriter, !Intrinsics.areEqual(this.getName(), "set()"), false, false, 12, null);
                }
            }, 2, null);
        }
        TypeName typeName2 = this.returnType;
        if (typeName2 != null) {
            codeWriter.emitCode(": %T", typeName2);
        } else if (emitUnitReturnType()) {
            codeWriter.emitCode(": %T", TypeNames.UNIT);
        }
        if (this.delegateConstructor != null) {
            CodeWriter.emitCode$default(codeWriter, CodeBlocks.joinToCode$default(this.delegateConstructorArguments, null, " : " + ((Object) this.delegateConstructor) + '(', ")", 1, null), false, false, 6, null);
        }
    }

    public final boolean isConstructor() {
        return INSTANCE.isConstructor$kotlinpoet(this.name);
    }

    private final CodeBlock kdocWithTags() {
        boolean z;
        CodeBlock.Builder builder = UtilKt.ensureEndsWithNewLine(this.kdoc).toBuilder();
        boolean zIsNotEmpty = builder.isNotEmpty();
        if (getReceiverKdoc().isNotEmpty()) {
            if (zIsNotEmpty) {
                builder.add("\n", new Object[0]);
                z = true;
            } else {
                z = false;
            }
            builder.add("@receiver %L", UtilKt.ensureEndsWithNewLine(getReceiverKdoc()));
        } else {
            z = false;
        }
        int i = 0;
        for (Object obj : getParameters()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            ParameterSpec parameterSpec = (ParameterSpec) obj;
            if (parameterSpec.getKdoc().isNotEmpty()) {
                if (!z && i == 0 && zIsNotEmpty) {
                    builder.add("\n", new Object[0]);
                    z = true;
                }
                builder.add("@param %L %L", parameterSpec.getName(), UtilKt.ensureEndsWithNewLine(parameterSpec.getKdoc()));
            }
            i = i2;
        }
        if (getReturnKdoc().isNotEmpty()) {
            if (!z && zIsNotEmpty) {
                builder.add("\n", new Object[0]);
            }
            builder.add("@return %L", UtilKt.ensureEndsWithNewLine(getReturnKdoc()));
        }
        return builder.build();
    }

    private final boolean emitUnitReturnType() {
        return (isConstructor() || Intrinsics.areEqual(this.name, "get()") || Intrinsics.areEqual(this.name, "set()") || asExpressionBody(this.body) != null) ? false : true;
    }

    private final CodeBlock asExpressionBody(CodeBlock codeBlock) {
        CodeBlock codeBlockTrim$kotlinpoet = codeBlock.trim$kotlinpoet();
        CodeBlock codeBlockWithoutPrefix$kotlinpoet = codeBlockTrim$kotlinpoet.withoutPrefix$kotlinpoet(RETURN_EXPRESSION_BODY_PREFIX_SPACE);
        if (codeBlockWithoutPrefix$kotlinpoet == null) {
            codeBlockWithoutPrefix$kotlinpoet = codeBlockTrim$kotlinpoet.withoutPrefix$kotlinpoet(RETURN_EXPRESSION_BODY_PREFIX_NBSP);
        }
        if (codeBlockWithoutPrefix$kotlinpoet != null) {
            return codeBlockWithoutPrefix$kotlinpoet;
        }
        if (codeBlockTrim$kotlinpoet.withoutPrefix$kotlinpoet(THROW_EXPRESSION_BODY_PREFIX_SPACE) == null && codeBlockTrim$kotlinpoet.withoutPrefix$kotlinpoet(THROW_EXPRESSION_BODY_PREFIX_NBSP) == null) {
            return null;
        }
        return codeBlockTrim$kotlinpoet;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && Intrinsics.areEqual(FunSpec.class, other.getClass())) {
            return Intrinsics.areEqual(toString(), other.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    /* JADX INFO: compiled from: FunSpec.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0006\u001a\u00020\u0003*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\b\u001a\u00020\u0003*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\t\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u000b\u0010\nR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0010\u0010\nR\u0014\u0010\u0011\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u000eR\u0014\u0010\u0012\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/squareup/kotlinpoet/FunSpec$Companion;", "", "", "", "isConstructor$kotlinpoet", "(Ljava/lang/String;)Z", "isConstructor", "isAccessor$kotlinpoet", "isAccessor", "CONSTRUCTOR", "Ljava/lang/String;", "GETTER", "Lcom/squareup/kotlinpoet/CodeBlock;", "RETURN_EXPRESSION_BODY_PREFIX_NBSP", "Lcom/squareup/kotlinpoet/CodeBlock;", "RETURN_EXPRESSION_BODY_PREFIX_SPACE", "SETTER", "THROW_EXPRESSION_BODY_PREFIX_NBSP", "THROW_EXPRESSION_BODY_PREFIX_SPACE", "<init>", "()V", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isConstructor$kotlinpoet(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            return Intrinsics.areEqual(str, "constructor()");
        }

        public final boolean isAccessor$kotlinpoet(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            return UtilKt.isOneOf(str, "get()", "set()", (60 & 4) != 0 ? null : null, (60 & 8) != 0 ? null : null, (60 & 16) != 0 ? null : null, (60 & 32) != 0 ? null : null);
        }
    }

    static {
        CodeBlock.Companion companion = CodeBlock.INSTANCE;
        RETURN_EXPRESSION_BODY_PREFIX_SPACE = companion.of("return ", new Object[0]);
        RETURN_EXPRESSION_BODY_PREFIX_NBSP = companion.of("return·", new Object[0]);
        THROW_EXPRESSION_BODY_PREFIX_SPACE = companion.of("throw ", new Object[0]);
        THROW_EXPRESSION_BODY_PREFIX_NBSP = companion.of("throw·", new Object[0]);
    }
}
