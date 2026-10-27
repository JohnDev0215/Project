package com.squareup.kotlinpoet;

import com.google.firebase.perf.FirebasePerformance;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnnotationSpec.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 !2\u00020\u0001:\u0002!\"J)\u0010\n\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\f\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016R\u0017\u0010\u0012\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006#"}, d2 = {"Lcom/squareup/kotlinpoet/AnnotationSpec;", "", "Lcom/squareup/kotlinpoet/CodeWriter;", "codeWriter", "", "inline", "asParameter", "", "emit$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeWriter;ZZ)V", "emit", "other", "equals", "", "hashCode", "", "toString", "Lcom/squareup/kotlinpoet/TypeName;", "typeName", "Lcom/squareup/kotlinpoet/TypeName;", "getTypeName", "()Lcom/squareup/kotlinpoet/TypeName;", "", "Lcom/squareup/kotlinpoet/CodeBlock;", "members", "Ljava/util/List;", "getMembers", "()Ljava/util/List;", "Lcom/squareup/kotlinpoet/AnnotationSpec$UseSiteTarget;", "useSiteTarget", "Lcom/squareup/kotlinpoet/AnnotationSpec$UseSiteTarget;", "getUseSiteTarget", "()Lcom/squareup/kotlinpoet/AnnotationSpec$UseSiteTarget;", "Companion", "UseSiteTarget", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public final class AnnotationSpec {
    private final List<CodeBlock> members;
    private final TypeName typeName;
    private final UseSiteTarget useSiteTarget;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        CodeWriter codeWriter = new CodeWriter(sb, null, null, null, null, Integer.MAX_VALUE, 30, null);
        try {
            emit$kotlinpoet(codeWriter, true, false);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(codeWriter, null);
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "stringBuilder.toString()");
            return string;
        } finally {
        }
    }

    public static /* synthetic */ void emit$kotlinpoet$default(AnnotationSpec annotationSpec, CodeWriter codeWriter, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = false;
        }
        annotationSpec.emit$kotlinpoet(codeWriter, z, z2);
    }

    public final void emit$kotlinpoet(CodeWriter codeWriter, boolean inline, boolean asParameter) {
        Intrinsics.checkNotNullParameter(codeWriter, "codeWriter");
        if (!asParameter) {
            CodeWriter.emit$default(codeWriter, "@", false, 2, null);
        }
        UseSiteTarget useSiteTarget = this.useSiteTarget;
        if (useSiteTarget != null) {
            CodeWriter.emit$default(codeWriter, Intrinsics.stringPlus(useSiteTarget.getKeyword(), ":"), false, 2, null);
        }
        codeWriter.emitCode("%T", this.typeName);
        if (!this.members.isEmpty() || asParameter) {
            String str = inline ? "" : "\n";
            String str2 = inline ? ", " : ",\n";
            String str3 = (inline || this.members.size() <= 1) ? "" : ",";
            CodeWriter.emit$default(codeWriter, "(", false, 2, null);
            if (this.members.size() > 1) {
                CodeWriter.emit$default(codeWriter, str, false, 2, null).indent(1);
            }
            List<CodeBlock> list = this.members;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            for (CodeBlock codeBlockReplaceAll$kotlinpoet : list) {
                if (inline) {
                    codeBlockReplaceAll$kotlinpoet = codeBlockReplaceAll$kotlinpoet.replaceAll$kotlinpoet("[⇥|⇤]", "");
                }
                arrayList.add(codeBlockReplaceAll$kotlinpoet);
            }
            CodeWriter.emitCode$default(codeWriter, CodeBlocks.joinToCode$default(arrayList, str2, null, str3, 2, null), true, false, 4, null);
            if (this.members.size() > 1) {
                CodeWriter.emit$default(codeWriter.unindent(1), str, false, 2, null);
            }
            CodeWriter.emit$default(codeWriter, ")", false, 2, null);
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && Intrinsics.areEqual(AnnotationSpec.class, other.getClass())) {
            return Intrinsics.areEqual(toString(), other.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    /* JADX INFO: compiled from: AnnotationSpec.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/squareup/kotlinpoet/AnnotationSpec$UseSiteTarget;", "", "keyword", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKeyword$kotlinpoet", "()Ljava/lang/String;", "FILE", "PROPERTY", "FIELD", FirebasePerformance.HttpMethod.GET, "SET", "RECEIVER", "PARAM", "SETPARAM", "DELEGATE", "kotlinpoet"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum UseSiteTarget {
        FILE("file"),
        PROPERTY("property"),
        FIELD("field"),
        GET("get"),
        SET("set"),
        RECEIVER("receiver"),
        PARAM("param"),
        SETPARAM("setparam"),
        DELEGATE("delegate");

        private final String keyword;

        UseSiteTarget(String str) {
            this.keyword = str;
        }

        /* JADX INFO: renamed from: getKeyword$kotlinpoet, reason: from getter */
        public final String getKeyword() {
            return this.keyword;
        }
    }
}
