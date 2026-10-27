package com.squareup.kotlinpoet;

import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: CodeWriter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001Bi\u0012\n\u0010I\u001a\u00060aj\u0002`b\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0002\u0012\u0014\b\u0002\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020@0?\u0012\u0014\b\u0002\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0?\u0012\u0014\b\u0002\u0010F\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00110?\u0012\b\b\u0002\u0010c\u001a\u00020\u0015¢\u0006\u0004\bd\u0010eJ\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u0005H\u0002J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0002H\u0002J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\f\u001a\u00020\u0002H\u0002J\u0018\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u0002H\u0002J\b\u0010\u0018\u001a\u00020\nH\u0002J$\u0010\u001d\u001a\u00020\u00052\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002J\u0010\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u001e\u001a\u00020\u0015J\u0010\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u001e\u001a\u00020\u0015J\u000e\u0010#\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020!J\u0006\u0010$\u001a\u00020\u0000J\u000e\u0010'\u001a\u00020\n2\u0006\u0010&\u001a\u00020%J\u001c\u0010,\u001a\u00020\n2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010+\u001a\u00020\u0005J$\u0010-\u001a\u00020\n2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019J\u0014\u00100\u001a\u00020\n2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020.0(J\u0014\u00101\u001a\u00020\n2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020.0(J\u000e\u00103\u001a\u00020\u00002\u0006\u00102\u001a\u00020\u0002J-\u00103\u001a\u00020\u00002\u0006\u00104\u001a\u00020\u00022\u0016\u00106\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000705\"\u0004\u0018\u00010\u0007¢\u0006\u0004\b3\u00107J\"\u00103\u001a\u00020\u00002\u0006\u00108\u001a\u00020%2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u00109\u001a\u00020\u0005J\u000e\u0010:\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eJ\u000e\u0010:\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0011J\u0018\u0010<\u001a\u00020\u00002\u0006\u00102\u001a\u00020\u00022\b\b\u0002\u0010;\u001a\u00020\u0005J\b\u0010=\u001a\u00020\nH\u0016R\u0014\u0010\u001f\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010>R \u0010A\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020@0?8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010BR#\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0?8\u0006¢\u0006\f\n\u0004\bC\u0010B\u001a\u0004\bD\u0010ER#\u0010F\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00110?8\u0006¢\u0006\f\n\u0004\bF\u0010B\u001a\u0004\bG\u0010ER\u0016\u0010I\u001a\u00020H8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010M\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010O\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bO\u0010NR\u0016\u0010P\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bP\u0010>R\u001a\u0010R\u001a\b\u0012\u0004\u0012\u00020!0Q8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\u001a\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00020T8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010VR \u0010X\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0W8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010BR \u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00110W8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bY\u0010BR\u001a\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u00020T8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bZ\u0010VR\u0016\u0010[\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b[\u0010NR\"\u0010\\\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\\\u0010L\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`¨\u0006f"}, d2 = {"Lcom/squareup/kotlinpoet/CodeWriter;", "Ljava/io/Closeable;", "", "canonical", "part", "", "emitStaticImportMember", "", "o", "isConstantContext", "", "emitLiteral", "simpleName", "isMethodNameUsedInCurrentContext", "Lcom/squareup/kotlinpoet/ClassName;", "className", "importableType", "Lcom/squareup/kotlinpoet/MemberName;", "memberName", "importableMember", "resolve", "", "stackDepth", "stackClassName", "emitIndentation", "", "Lcom/squareup/kotlinpoet/KModifier;", "modifiers", "implicitModifiers", "shouldEmitPublicModifier", "levels", "indent", "unindent", "Lcom/squareup/kotlinpoet/TypeSpec;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "pushType", "popType", "Lcom/squareup/kotlinpoet/CodeBlock;", "kdocCodeBlock", "emitKdoc", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "annotations", "inline", "emitAnnotations", "emitModifiers", "Lcom/squareup/kotlinpoet/TypeVariableName;", "typeVariables", "emitTypeVariables", "emitWhereBlock", "s", "emitCode", "format", "", "args", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/CodeWriter;", "codeBlock", "ensureTrailingNewline", "lookupName", "nonWrapping", "emit", "close", "Ljava/lang/String;", "", "Lcom/squareup/kotlinpoet/Import;", "memberImports", "Ljava/util/Map;", "importedTypes", "getImportedTypes", "()Ljava/util/Map;", "importedMembers", "getImportedMembers", "Lcom/squareup/kotlinpoet/LineWrapper;", "out", "Lcom/squareup/kotlinpoet/LineWrapper;", "indentLevel", "I", "kdoc", "Z", "comment", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "", "typeSpecStack", "Ljava/util/List;", "", "memberImportNames", "Ljava/util/Set;", "", "importableTypes", "importableMembers", "referencedNames", "trailingNewline", "statementLine", "getStatementLine", "()I", "setStatementLine", "(I)V", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "columnLimit", "<init>", "(Ljava/lang/Appendable;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;I)V", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public final class CodeWriter implements Closeable {
    private boolean comment;
    private final Map<String, MemberName> importableMembers;
    private final Map<String, ClassName> importableTypes;
    private final Map<String, MemberName> importedMembers;
    private final Map<String, ClassName> importedTypes;
    private final String indent;
    private int indentLevel;
    private boolean kdoc;
    private final Set<String> memberImportNames;
    private final Map<String, Import> memberImports;
    private LineWrapper out;
    private String packageName;
    private final Set<String> referencedNames;
    private int statementLine;
    private boolean trailingNewline;
    private final List<TypeSpec> typeSpecStack;

    public CodeWriter(Appendable out, String indent, Map<String, Import> memberImports, Map<String, ClassName> importedTypes, Map<String, MemberName> importedMembers, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(indent, "indent");
        Intrinsics.checkNotNullParameter(memberImports, "memberImports");
        Intrinsics.checkNotNullParameter(importedTypes, "importedTypes");
        Intrinsics.checkNotNullParameter(importedMembers, "importedMembers");
        this.indent = indent;
        this.memberImports = memberImports;
        this.importedTypes = importedTypes;
        this.importedMembers = importedMembers;
        this.out = new LineWrapper(out, indent, i);
        this.packageName = CodeWriterKt.NO_PACKAGE;
        this.typeSpecStack = new ArrayList();
        this.memberImportNames = new LinkedHashSet();
        this.importableTypes = new LinkedHashMap();
        this.importableMembers = new LinkedHashMap();
        this.referencedNames = new LinkedHashSet();
        this.statementLine = -1;
        Iterator<Map.Entry<String, Import>> it = memberImports.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) key, '.', 0, false, 6, (Object) null);
            if (iLastIndexOf$default >= 0) {
                Set<String> set = this.memberImportNames;
                String strSubstring = key.substring(0, iLastIndexOf$default);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                set.add(strSubstring);
            }
        }
    }

    public /* synthetic */ CodeWriter(Appendable appendable, String str, Map map, Map map2, Map map3, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(appendable, (i2 & 2) != 0 ? "  " : str, (i2 & 4) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i2 & 8) != 0 ? MapsKt__MapsKt.emptyMap() : map2, (i2 & 16) != 0 ? MapsKt__MapsKt.emptyMap() : map3, (i2 & 32) != 0 ? 100 : i);
    }

    public final int getStatementLine() {
        return this.statementLine;
    }

    public final void setStatementLine(int i) {
        this.statementLine = i;
    }

    public static /* synthetic */ CodeWriter indent$default(CodeWriter codeWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        return codeWriter.indent(i);
    }

    public final CodeWriter indent(int levels) {
        this.indentLevel += levels;
        return this;
    }

    public static /* synthetic */ CodeWriter unindent$default(CodeWriter codeWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        return codeWriter.unindent(i);
    }

    public final CodeWriter unindent(int levels) {
        int i = this.indentLevel;
        if (!(i - levels >= 0)) {
            throw new IllegalArgumentException(("cannot unindent " + levels + " from " + this.indentLevel).toString());
        }
        this.indentLevel = i - levels;
        return this;
    }

    public final CodeWriter pushType(TypeSpec type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.typeSpecStack.add(type);
        return this;
    }

    public final CodeWriter popType() {
        this.typeSpecStack.remove(r0.size() - 1);
        return this;
    }

    public final void emitKdoc(CodeBlock kdocCodeBlock) {
        Intrinsics.checkNotNullParameter(kdocCodeBlock, "kdocCodeBlock");
        if (kdocCodeBlock.isEmpty()) {
            return;
        }
        emit$default(this, "/**\n", false, 2, null);
        this.kdoc = true;
        try {
            emitCode$default(this, kdocCodeBlock, false, true, 2, null);
            this.kdoc = false;
            emit$default(this, " */\n", false, 2, null);
        } catch (Throwable th) {
            this.kdoc = false;
            throw th;
        }
    }

    public final void emitAnnotations(List<AnnotationSpec> annotations, boolean inline) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Iterator<AnnotationSpec> it = annotations.iterator();
        while (it.hasNext()) {
            AnnotationSpec.emit$kotlinpoet$default(it.next(), this, inline, false, 4, null);
            emit$default(this, inline ? " " : "\n", false, 2, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void emitModifiers$default(CodeWriter codeWriter, Set set, Set set2, int i, Object obj) {
        if ((i & 2) != 0) {
            set2 = SetsKt__SetsKt.emptySet();
        }
        codeWriter.emitModifiers(set, set2);
    }

    public final void emitModifiers(Set<? extends KModifier> modifiers, Set<? extends KModifier> implicitModifiers) {
        Intrinsics.checkNotNullParameter(modifiers, "modifiers");
        Intrinsics.checkNotNullParameter(implicitModifiers, "implicitModifiers");
        if (shouldEmitPublicModifier(modifiers, implicitModifiers)) {
            emit$default(this, KModifier.PUBLIC.getKeyword(), false, 2, null);
            emit$default(this, " ", false, 2, null);
        }
        KModifier[] kModifierArrValues = KModifier.values();
        LinkedHashSet<KModifier> linkedHashSet = new LinkedHashSet();
        int length = kModifierArrValues.length;
        int i = 0;
        while (i < length) {
            KModifier kModifier = kModifierArrValues[i];
            i++;
            if (modifiers.contains(kModifier)) {
                linkedHashSet.add(kModifier);
            }
        }
        for (KModifier kModifier2 : linkedHashSet) {
            if (!implicitModifiers.contains(kModifier2)) {
                emit$default(this, kModifier2.getKeyword(), false, 2, null);
                emit$default(this, " ", false, 2, null);
            }
        }
    }

    public final void emitTypeVariables(List<TypeVariableName> typeVariables) {
        Intrinsics.checkNotNullParameter(typeVariables, "typeVariables");
        if (typeVariables.isEmpty()) {
            return;
        }
        emit$default(this, "<", false, 2, null);
        int i = 0;
        for (Object obj : typeVariables) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            TypeVariableName typeVariableName = (TypeVariableName) obj;
            if (i > 0) {
                emit$default(this, ", ", false, 2, null);
            }
            if (typeVariableName.getVariance() != null) {
                emit$default(this, Intrinsics.stringPlus(typeVariableName.getVariance().getKeyword(), " "), false, 2, null);
            }
            if (typeVariableName.getIsReified()) {
                emit$default(this, "reified ", false, 2, null);
            }
            emitCode("%L", typeVariableName.getName());
            if (typeVariableName.getBounds().size() == 1 && !Intrinsics.areEqual(typeVariableName.getBounds().get(0), CodeWriterKt.getNULLABLE_ANY())) {
                emitCode(" : %T", typeVariableName.getBounds().get(0));
            }
            i = i2;
        }
        emit$default(this, ">", false, 2, null);
    }

    public final void emitWhereBlock(List<TypeVariableName> typeVariables) {
        Intrinsics.checkNotNullParameter(typeVariables, "typeVariables");
        if (typeVariables.isEmpty()) {
            return;
        }
        boolean z = true;
        for (TypeVariableName typeVariableName : typeVariables) {
            if (typeVariableName.getBounds().size() > 1) {
                for (TypeName typeName : typeVariableName.getBounds()) {
                    emitCode(!z ? ", " : " where ");
                    emitCode("%L : %T", typeVariableName.getName(), typeName);
                    z = false;
                }
            }
        }
    }

    public final CodeWriter emitCode(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        return emitCode$default(this, CodeBlock.INSTANCE.of(s, new Object[0]), false, false, 6, null);
    }

    public final CodeWriter emitCode(String format, Object... args) {
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        return emitCode$default(this, CodeBlock.INSTANCE.of(format, Arrays.copyOf(args, args.length)), false, false, 6, null);
    }

    public static /* synthetic */ CodeWriter emitCode$default(CodeWriter codeWriter, CodeBlock codeBlock, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return codeWriter.emitCode(codeBlock, z, z2);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0259 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0017 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0147  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.squareup.kotlinpoet.CodeWriter emitCode(com.squareup.kotlinpoet.CodeBlock r17, boolean r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 732
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.CodeWriter.emitCode(com.squareup.kotlinpoet.CodeBlock, boolean, boolean):com.squareup.kotlinpoet.CodeWriter");
    }

    private final boolean emitStaticImportMember(String canonical, String part) {
        String strSubstring = part.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
        if ((strSubstring.length() == 0) || !Character.isJavaIdentifierStart(strSubstring.charAt(0))) {
            return false;
        }
        Import r11 = this.memberImports.get(canonical + '.' + CodeWriterKt.extractMemberName(strSubstring));
        if (r11 == null) {
            return false;
        }
        if (r11.getAlias() != null) {
            emit$default(this, StringsKt__StringsJVMKt.replaceFirst$default(strSubstring, CodeWriterKt.extractMemberName(strSubstring), r11.getAlias(), false, 4, (Object) null), false, 2, null);
        } else {
            emit$default(this, strSubstring, false, 2, null);
        }
        return true;
    }

    private final void emitLiteral(Object o, boolean isConstantContext) {
        if (o instanceof TypeSpec) {
            TypeSpec.emit$kotlinpoet$default((TypeSpec) o, this, null, null, false, 12, null);
            return;
        }
        if (o instanceof AnnotationSpec) {
            ((AnnotationSpec) o).emit$kotlinpoet(this, true, isConstantContext);
            return;
        }
        if (o instanceof PropertySpec) {
            PropertySpec.emit$kotlinpoet$default((PropertySpec) o, this, SetsKt__SetsKt.emptySet(), false, false, false, false, 60, null);
            return;
        }
        if (o instanceof FunSpec) {
            ((FunSpec) o).emit$kotlinpoet(this, null, SetsKt__SetsJVMKt.setOf(KModifier.PUBLIC), true);
            return;
        }
        if (o instanceof TypeAliasSpec) {
            ((TypeAliasSpec) o).emit$kotlinpoet(this);
        } else if (o instanceof CodeBlock) {
            emitCode$default(this, (CodeBlock) o, isConstantContext, false, 4, null);
        } else {
            emit$default(this, String.valueOf(o), false, 2, null);
        }
    }

    public final String lookupName(ClassName className) {
        Intrinsics.checkNotNullParameter(className, "className");
        ClassName classNameEnclosingClassName = className;
        boolean z = false;
        while (classNameEnclosingClassName != null) {
            Import r2 = this.memberImports.get(classNameEnclosingClassName.getCanonicalName());
            String alias = r2 == null ? null : r2.getAlias();
            ClassName classNameResolve = resolve(alias == null ? classNameEnclosingClassName.getSimpleName() : alias);
            boolean z2 = classNameResolve != null;
            if (Intrinsics.areEqual(classNameResolve, classNameEnclosingClassName.copy(false, CollectionsKt__CollectionsKt.emptyList()))) {
                if (alias != null) {
                    return alias;
                }
                int size = classNameEnclosingClassName.getSimpleNames().size() - 1;
                this.referencedNames.add(className.topLevelClassName().getSimpleName());
                return CollectionsKt___CollectionsKt.joinToString$default(className.getSimpleNames().subList(size, className.getSimpleNames().size()), ".", null, null, 0, null, null, 62, null);
            }
            classNameEnclosingClassName = classNameEnclosingClassName.enclosingClassName();
            z = z2;
        }
        if (z) {
            return className.getCanonicalName();
        }
        if (Intrinsics.areEqual(this.packageName, className.getPackageName())) {
            this.referencedNames.add(className.topLevelClassName().getSimpleName());
            return CollectionsKt___CollectionsKt.joinToString$default(className.getSimpleNames(), ".", null, null, 0, null, null, 62, null);
        }
        if (!this.kdoc) {
            importableType(className);
        }
        return className.getCanonicalName();
    }

    public final String lookupName(MemberName memberName) {
        Intrinsics.checkNotNullParameter(memberName, "memberName");
        Import r0 = this.memberImports.get(memberName.getCanonicalName());
        String alias = r0 == null ? null : r0.getAlias();
        if (alias == null) {
            alias = memberName.getSimpleName();
        }
        MemberName memberName2 = this.importedMembers.get(alias);
        if (Intrinsics.areEqual(memberName2, memberName)) {
            return alias;
        }
        if (memberName2 != null && memberName.getEnclosingClassName() != null) {
            return lookupName(memberName.getEnclosingClassName()) + '.' + alias;
        }
        if (Intrinsics.areEqual(this.packageName, memberName.getPackageName()) && memberName.getEnclosingClassName() == null) {
            this.referencedNames.add(memberName.getSimpleName());
            return memberName.getSimpleName();
        }
        if (!this.kdoc && (memberName.getIsExtension() || !isMethodNameUsedInCurrentContext(memberName.getSimpleName()))) {
            importableMember(memberName);
        }
        return memberName.getCanonicalName();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0045 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean isMethodNameUsedInCurrentContext(java.lang.String r7) {
        /*
            r6 = this;
            java.util.List<com.squareup.kotlinpoet.TypeSpec> r0 = r6.typeSpecStack
            java.util.List r0 = kotlin.collections.CollectionsKt.reversed(r0)
            java.util.Iterator r0 = r0.iterator()
        La:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L52
            java.lang.Object r1 = r0.next()
            com.squareup.kotlinpoet.TypeSpec r1 = (com.squareup.kotlinpoet.TypeSpec) r1
            java.util.List r3 = r1.getFunSpecs()
            boolean r4 = r3 instanceof java.util.Collection
            r5 = 1
            if (r4 == 0) goto L28
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L28
        L26:
            r3 = r2
            goto L43
        L28:
            java.util.Iterator r3 = r3.iterator()
        L2c:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L26
            java.lang.Object r4 = r3.next()
            com.squareup.kotlinpoet.FunSpec r4 = (com.squareup.kotlinpoet.FunSpec) r4
            java.lang.String r4 = r4.getName()
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r7)
            if (r4 == 0) goto L2c
            r3 = r5
        L43:
            if (r3 == 0) goto L46
            return r5
        L46:
            java.util.Set r1 = r1.getModifiers()
            com.squareup.kotlinpoet.KModifier r3 = com.squareup.kotlinpoet.KModifier.INNER
            boolean r1 = r1.contains(r3)
            if (r1 != 0) goto La
        L52:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.CodeWriter.isMethodNameUsedInCurrentContext(java.lang.String):boolean");
    }

    private final void importableType(ClassName className) {
        ClassName className2 = className.topLevelClassName();
        Import r3 = this.memberImports.get(className.getCanonicalName());
        String alias = r3 == null ? null : r3.getAlias();
        if (alias == null) {
            alias = className2.getSimpleName();
        }
        if (this.importableMembers.containsKey(alias)) {
            return;
        }
        this.importableTypes.putIfAbsent(alias, className2);
    }

    private final void importableMember(MemberName memberName) {
        if (memberName.getPackageName().length() > 0) {
            Import r0 = this.memberImports.get(memberName.getCanonicalName());
            String alias = r0 == null ? null : r0.getAlias();
            if (alias == null) {
                alias = memberName.getSimpleName();
            }
            if (this.importableTypes.containsKey(alias) || this.importableMembers.putIfAbsent(alias, memberName) == null || memberName.getEnclosingClassName() == null) {
                return;
            }
            importableType(memberName.getEnclosingClassName());
        }
    }

    private final ClassName resolve(String simpleName) {
        int size = this.typeSpecStack.size() - 1;
        if (size >= 0) {
            while (true) {
                int i = size - 1;
                if (this.typeSpecStack.get(size).getNestedTypesSimpleNames$kotlinpoet().contains(simpleName)) {
                    return stackClassName(size, simpleName);
                }
                if (i < 0) {
                    break;
                }
                size = i;
            }
        }
        if (this.typeSpecStack.size() > 0 && Intrinsics.areEqual(this.typeSpecStack.get(0).getName(), simpleName)) {
            return new ClassName(this.packageName, simpleName);
        }
        ClassName className = this.importedTypes.get(simpleName);
        if (className != null) {
            return className;
        }
        return null;
    }

    private final ClassName stackClassName(int stackDepth, String simpleName) {
        String str = this.packageName;
        int i = 1;
        String name = this.typeSpecStack.get(0).getName();
        Intrinsics.checkNotNull(name);
        ClassName className = new ClassName(str, name);
        if (1 <= stackDepth) {
            while (true) {
                int i2 = i + 1;
                String name2 = this.typeSpecStack.get(i).getName();
                Intrinsics.checkNotNull(name2);
                className = className.nestedClass(name2);
                if (i == stackDepth) {
                    break;
                }
                i = i2;
            }
        }
        return className.nestedClass(simpleName);
    }

    public static /* synthetic */ CodeWriter emit$default(CodeWriter codeWriter, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return codeWriter.emit(str, z);
    }

    public final CodeWriter emit(String s, boolean nonWrapping) {
        Intrinsics.checkNotNullParameter(s, "s");
        boolean z = true;
        for (String str : StringsKt__StringsKt.split$default((CharSequence) s, new char[]{'\n'}, false, 0, 6, (Object) null)) {
            if (!z) {
                if ((this.kdoc || this.comment) && this.trailingNewline) {
                    emitIndentation();
                    this.out.appendNonWrapping(this.kdoc ? " *" : "//");
                }
                this.out.newline();
                this.trailingNewline = true;
                if (getStatementLine() != -1) {
                    if (getStatementLine() == 0) {
                        indent(2);
                    }
                    setStatementLine(getStatementLine() + 1);
                }
            }
            if (!(str.length() == 0)) {
                if (this.trailingNewline) {
                    emitIndentation();
                    if (this.kdoc) {
                        this.out.appendNonWrapping(" * ");
                    } else if (this.comment) {
                        this.out.appendNonWrapping("// ");
                    }
                }
                if (nonWrapping) {
                    this.out.appendNonWrapping(str);
                } else {
                    LineWrapper lineWrapper = this.out;
                    boolean z2 = this.kdoc;
                    lineWrapper.append(str, z2 ? this.indentLevel : 2 + this.indentLevel, z2 ? " * " : "");
                }
                this.trailingNewline = false;
            }
            z = false;
        }
        return this;
    }

    private final void emitIndentation() {
        int i = this.indentLevel;
        int i2 = 0;
        while (i2 < i) {
            i2++;
            this.out.appendNonWrapping(this.indent);
        }
    }

    private final boolean shouldEmitPublicModifier(Set<? extends KModifier> modifiers, Set<? extends KModifier> implicitModifiers) {
        KModifier kModifier = KModifier.PUBLIC;
        if (modifiers.contains(kModifier)) {
            return true;
        }
        if (implicitModifiers.contains(kModifier)) {
            return !UtilKt.containsAnyOf(modifiers, KModifier.PRIVATE, KModifier.INTERNAL, KModifier.PROTECTED);
        }
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.out.close();
    }
}
