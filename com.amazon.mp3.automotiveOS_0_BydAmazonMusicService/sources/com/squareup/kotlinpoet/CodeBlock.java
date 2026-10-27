package com.squareup.kotlinpoet;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KClass;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt___StringsKt;

/* JADX INFO: compiled from: CodeBlock.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 #2\u00020\u0001:\u0002\"#B%\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\r\u0010\r\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0006\u0010\u0011\u001a\u00020\u000bJ\u0006\u0010\u0012\u001a\u00020\u000bJ\u001d\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\u0015\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001cJ\r\u0010\u001d\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\u001eJ\u0017\u0010\u001f\u001a\u0004\u0018\u00010\u00002\u0006\u0010 \u001a\u00020\u0000H\u0000¢\u0006\u0002\b!R\u001c\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006$"}, d2 = {"Lcom/squareup/kotlinpoet/CodeBlock;", "", "formatParts", "", "", "args", "(Ljava/util/List;Ljava/util/List;)V", "getArgs$kotlinpoet", "()Ljava/util/List;", "getFormatParts$kotlinpoet", "equals", "", "other", "hasStatements", "hasStatements$kotlinpoet", "hashCode", "", "isEmpty", "isNotEmpty", "replaceAll", "oldValue", "newValue", "replaceAll$kotlinpoet", "toBuilder", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "toString", "codeWriter", "Lcom/squareup/kotlinpoet/CodeWriter;", "toString$kotlinpoet", "trim", "trim$kotlinpoet", "withoutPrefix", "prefix", "withoutPrefix$kotlinpoet", "Builder", "Companion", "kotlinpoet"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CodeBlock {
    private final List<Object> args;
    private final List<String> formatParts;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Regex NAMED_ARGUMENT = new Regex("%([\\w_]+):([\\w]).*");
    private static final Regex LOWERCASE = new Regex("[a-z]+[\\w_]*");
    private static final Set<String> NO_ARG_PLACEHOLDERS = SetsKt__SetsKt.setOf((Object[]) new String[]{"⇥", "⇤", "«", "»"});
    private static final CodeBlock EMPTY = new CodeBlock(CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList());

    public /* synthetic */ CodeBlock(List list, List list2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        CodeWriter codeWriter = new CodeWriter(sb, null, null, null, null, Integer.MAX_VALUE, 30, null);
        try {
            CodeWriter.emitCode$default(codeWriter, this, false, false, 6, null);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(codeWriter, null);
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "stringBuilder.toString()");
            return string;
        } finally {
        }
    }

    private CodeBlock(List<String> list, List<? extends Object> list2) {
        this.formatParts = list;
        this.args = list2;
    }

    public final List<String> getFormatParts$kotlinpoet() {
        return this.formatParts;
    }

    public final List<Object> getArgs$kotlinpoet() {
        return this.args;
    }

    public final boolean isEmpty() {
        return this.formatParts.isEmpty();
    }

    public final boolean isNotEmpty() {
        return !isEmpty();
    }

    public final CodeBlock withoutPrefix$kotlinpoet(CodeBlock prefix) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (this.formatParts.size() < prefix.formatParts.size() || this.args.size() < prefix.args.size()) {
            return null;
        }
        int i = 0;
        int i2 = 0;
        String strSubstring = null;
        for (Object obj : prefix.formatParts) {
            int i3 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            if (!Intrinsics.areEqual(getFormatParts$kotlinpoet().get(i), str)) {
                if (i != prefix.getFormatParts$kotlinpoet().size() - 1 || !StringsKt__StringsJVMKt.startsWith$default(getFormatParts$kotlinpoet().get(i), str, false, 2, null)) {
                    return null;
                }
                strSubstring = getFormatParts$kotlinpoet().get(i).substring(str.length());
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
            }
            if (StringsKt__StringsJVMKt.startsWith$default(str, "%", false, 2, null) && !INSTANCE.isMultiCharNoArgPlaceholder$kotlinpoet(str.charAt(1))) {
                if (!Intrinsics.areEqual(getArgs$kotlinpoet().get(i2), prefix.getArgs$kotlinpoet().get(i2))) {
                    return null;
                }
                i2++;
            }
            i = i3;
        }
        ArrayList arrayList = new ArrayList();
        if (strSubstring != null) {
            arrayList.add(strSubstring);
        }
        int size = this.formatParts.size();
        for (int size2 = prefix.formatParts.size(); size2 < size; size2++) {
            arrayList.add(this.formatParts.get(size2));
        }
        ArrayList arrayList2 = new ArrayList();
        int size3 = this.args.size();
        for (int size4 = prefix.args.size(); size4 < size3; size4++) {
            arrayList2.add(this.args.get(size4));
        }
        return new CodeBlock(arrayList, arrayList2);
    }

    public final CodeBlock trim$kotlinpoet() {
        int size = this.formatParts.size();
        int i = 0;
        while (i < size && NO_ARG_PLACEHOLDERS.contains(this.formatParts.get(i))) {
            i++;
        }
        while (i < size && NO_ARG_PLACEHOLDERS.contains(this.formatParts.get(size - 1))) {
            size--;
        }
        return (i > 0 || size < this.formatParts.size()) ? new CodeBlock(this.formatParts.subList(i, size), this.args) : this;
    }

    public final CodeBlock replaceAll$kotlinpoet(String oldValue, String newValue) {
        Intrinsics.checkNotNullParameter(oldValue, "oldValue");
        Intrinsics.checkNotNullParameter(newValue, "newValue");
        List<String> list = this.formatParts;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(StringsKt__StringsJVMKt.replace$default((String) it.next(), oldValue, newValue, false, 4, (Object) null));
        }
        return new CodeBlock(arrayList, this.args);
    }

    public final boolean hasStatements$kotlinpoet() {
        List<String> list = this.formatParts;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (StringsKt__StringsKt.contains$default((CharSequence) it.next(), (CharSequence) "«", false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && Intrinsics.areEqual(CodeBlock.class, other.getClass())) {
            return Intrinsics.areEqual(toString(), other.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public final String toString$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "codeWriter");
        return CodeWriterKt.buildCodeString(codeWriter, new Function1<CodeWriter, Unit>() { // from class: com.squareup.kotlinpoet.CodeBlock.toString.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CodeWriter codeWriter2) {
                invoke2(codeWriter2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CodeWriter buildCodeString) {
                Intrinsics.checkNotNullParameter(buildCodeString, "$this$buildCodeString");
                CodeWriter.emitCode$default(buildCodeString, CodeBlock.this, false, false, 6, null);
            }
        });
    }

    public final Builder toBuilder() {
        Builder builder = new Builder();
        CollectionsKt__MutableCollectionsKt.addAll(builder.getFormatParts$kotlinpoet(), this.formatParts);
        builder.getArgs$kotlinpoet().addAll(this.args);
        return builder;
    }

    /* JADX INFO: compiled from: CodeBlock.kt */
    @Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b \u0010!J\"\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0002J\u0012\u0010\n\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0002J\u0014\u0010\u000b\u001a\u0004\u0018\u00010\u00012\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0002J\u0014\u0010\f\u001a\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0002J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0001H\u0002J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0002J\u0006\u0010\u0011\u001a\u00020\u0010J\u0006\u0010\u0012\u001a\u00020\u0010J-\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0016\u0010\u0014\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0013\"\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0017J\u0006\u0010\u0019\u001a\u00020\u0017R \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\"\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001a8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u001c\u001a\u0004\b\u001f\u0010\u001e¨\u0006\""}, d2 = {"Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "", "", "format", "", "c", "arg", "", "addArgument", "o", "argToName", "argToLiteral", "argToString", "logDeprecationWarning", "Lcom/squareup/kotlinpoet/TypeName;", "argToType", "", "isEmpty", "isNotEmpty", "", "args", "add", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "Lcom/squareup/kotlinpoet/CodeBlock;", "codeBlock", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "formatParts", "Ljava/util/List;", "getFormatParts$kotlinpoet", "()Ljava/util/List;", "getArgs$kotlinpoet", "<init>", "()V", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
    public static final class Builder {
        private final List<String> formatParts = new ArrayList();
        private final List<Object> args = new ArrayList();

        private final Object argToLiteral(Object o) {
            return o;
        }

        public final List<String> getFormatParts$kotlinpoet() {
            return this.formatParts;
        }

        public final List<Object> getArgs$kotlinpoet() {
            return this.args;
        }

        public final boolean isEmpty() {
            return this.formatParts.isEmpty();
        }

        public final boolean isNotEmpty() {
            return !isEmpty();
        }

        public final Builder add(String format, Object... args) {
            boolean z;
            int i;
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(args, "args");
            int[] iArr = new int[args.length];
            int i2 = 0;
            boolean z2 = false;
            int i3 = 0;
            boolean z3 = false;
            while (true) {
                if (i2 >= format.length()) {
                    if (z2) {
                        if (!(i3 >= args.length)) {
                            throw new IllegalArgumentException(("unused arguments: expected " + i3 + ", received " + args.length).toString());
                        }
                    }
                    if (z3) {
                        ArrayList arrayList = new ArrayList();
                        int length = args.length;
                        int i4 = 0;
                        while (i4 < length) {
                            int i5 = i4 + 1;
                            if (iArr[i4] == 0) {
                                arrayList.add(Intrinsics.stringPlus("%", Integer.valueOf(i5)));
                            }
                            i4 = i5;
                        }
                        String str = arrayList.size() == 1 ? "" : "s";
                        if (!arrayList.isEmpty()) {
                            throw new IllegalArgumentException(("unused argument" + str + ": " + CollectionsKt___CollectionsKt.joinToString$default(arrayList, ", ", null, null, 0, null, null, 62, null)).toString());
                        }
                    }
                    return this;
                }
                Companion companion = CodeBlock.INSTANCE;
                if (companion.isSingleCharNoArgPlaceholder$kotlinpoet(format.charAt(i2))) {
                    getFormatParts$kotlinpoet().add(String.valueOf(format.charAt(i2)));
                    i2++;
                } else if (format.charAt(i2) != '%') {
                    int iNextPotentialPlaceholderPosition$kotlinpoet = companion.nextPotentialPlaceholderPosition$kotlinpoet(format, i2 + 1);
                    if (iNextPotentialPlaceholderPosition$kotlinpoet == -1) {
                        iNextPotentialPlaceholderPosition$kotlinpoet = format.length();
                    }
                    List<String> formatParts$kotlinpoet = getFormatParts$kotlinpoet();
                    String strSubstring = format.substring(i2, iNextPotentialPlaceholderPosition$kotlinpoet);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                    formatParts$kotlinpoet.add(strSubstring);
                    i2 = iNextPotentialPlaceholderPosition$kotlinpoet;
                } else {
                    int i6 = i2 + 1;
                    int i7 = i6;
                    while (true) {
                        if (!(i7 < format.length())) {
                            throw new IllegalArgumentException(("dangling format characters in '" + format + '\'').toString());
                        }
                        int i8 = i7 + 1;
                        char cCharAt = format.charAt(i7);
                        if ('0' <= cCharAt && cCharAt < ':') {
                            i7 = i8;
                        } else {
                            int i9 = i8 - 1;
                            if (CodeBlock.INSTANCE.isMultiCharNoArgPlaceholder$kotlinpoet(cCharAt)) {
                                if (!(i6 == i9)) {
                                    throw new IllegalArgumentException("%% may not have an index".toString());
                                }
                                getFormatParts$kotlinpoet().add(Intrinsics.stringPlus("%", Character.valueOf(cCharAt)));
                                i2 = i8;
                            } else {
                                if (i6 < i9) {
                                    String strSubstring2 = format.substring(i6, i9);
                                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                                    int i10 = Integer.parseInt(strSubstring2) - 1;
                                    if (!(args.length == 0)) {
                                        int length2 = i10 % args.length;
                                        iArr[length2] = iArr[length2] + 1;
                                    }
                                    z = true;
                                    i = i3;
                                    i3 = i10;
                                } else {
                                    z = z3;
                                    i = i3 + 1;
                                    z2 = true;
                                }
                                if (!(i3 >= 0 && i3 < args.length)) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("index ");
                                    sb.append(i3 + 1);
                                    sb.append(" for '");
                                    String strSubstring3 = format.substring(i6 - 1, i9 + 1);
                                    Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
                                    sb.append(strSubstring3);
                                    sb.append("' not in range (received ");
                                    sb.append(args.length);
                                    sb.append(" arguments)");
                                    throw new IllegalArgumentException(sb.toString().toString());
                                }
                                if (z && z2) {
                                    z = false;
                                }
                                if (!z) {
                                    throw new IllegalArgumentException("cannot mix indexed and positional parameters".toString());
                                }
                                addArgument(format, cCharAt, args[i3]);
                                getFormatParts$kotlinpoet().add(Intrinsics.stringPlus("%", Character.valueOf(cCharAt)));
                                i3 = i;
                                i2 = i8;
                                z3 = z;
                            }
                        }
                    }
                }
            }
        }

        private final void addArgument(String format, char c, Object arg) {
            if (c == 'N') {
                this.args.add(UtilKt.escapeIfNecessary$default(argToName(arg), false, 1, null));
                return;
            }
            if (c == 'L') {
                this.args.add(argToLiteral(arg));
                return;
            }
            if (c == 'S') {
                this.args.add(argToString(arg));
                return;
            }
            if (c == 'P') {
                List<Object> list = this.args;
                if (!(arg instanceof CodeBlock)) {
                    arg = argToString(arg);
                }
                list.add(arg);
                return;
            }
            if (c == 'T') {
                this.args.add(argToType(arg));
            } else {
                if (c == 'M') {
                    this.args.add(arg);
                    return;
                }
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("invalid format string: '%s'", Arrays.copyOf(new Object[]{format}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                throw new IllegalArgumentException(str);
            }
        }

        private final String argToName(Object o) {
            if (o instanceof CharSequence) {
                return o.toString();
            }
            if (o instanceof ParameterSpec) {
                return ((ParameterSpec) o).getName();
            }
            if (o instanceof PropertySpec) {
                return ((PropertySpec) o).getName();
            }
            if (o instanceof FunSpec) {
                return ((FunSpec) o).getName();
            }
            if (o instanceof TypeSpec) {
                String name = ((TypeSpec) o).getName();
                Intrinsics.checkNotNull(name);
                return name;
            }
            if (o instanceof MemberName) {
                return ((MemberName) o).getSimpleName();
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("expected name but was ", o));
        }

        private final String argToString(Object o) {
            if (o == null) {
                return null;
            }
            return o.toString();
        }

        private final void logDeprecationWarning(Object o) {
            System.out.println((Object) ("Deprecation warning: converting " + o + " to TypeName. Conversion of TypeMirror and TypeElement is deprecated in KotlinPoet, use kotlin-metadata APIs instead."));
        }

        private final TypeName argToType(Object o) {
            if (o instanceof TypeName) {
                return (TypeName) o;
            }
            if (o instanceof TypeMirror) {
                logDeprecationWarning(o);
                return TypeNames.get((TypeMirror) o);
            }
            if (o instanceof Element) {
                logDeprecationWarning(o);
                TypeMirror typeMirrorAsType = ((Element) o).asType();
                Intrinsics.checkNotNullExpressionValue(typeMirrorAsType, "o.asType()");
                return TypeNames.get(typeMirrorAsType);
            }
            if (o instanceof Type) {
                return TypeNames.get((Type) o);
            }
            if (o instanceof KClass) {
                return TypeNames.get((KClass<?>) o);
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("expected type but was ", o));
        }

        public final Builder add(CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(codeBlock, "codeBlock");
            CollectionsKt__MutableCollectionsKt.addAll(getFormatParts$kotlinpoet(), codeBlock.getFormatParts$kotlinpoet());
            getArgs$kotlinpoet().addAll(codeBlock.getArgs$kotlinpoet());
            return this;
        }

        public final CodeBlock build() {
            return new CodeBlock(UtilKt.toImmutableList(this.formatParts), UtilKt.toImmutableList(this.args), null);
        }
    }

    /* JADX INFO: compiled from: CodeBlock.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0007J-\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u000e2\u0016\u0010\u001e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u001f\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010 J\u0019\u0010!\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\"\u001a\u00020\u0004H\u0000¢\u0006\u0002\b#R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010\u0010\u001a\u00020\u0011*\u00020\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\u00020\u0011*\u00020\u000e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\u00020\u0011*\u00020\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0014¨\u0006$"}, d2 = {"Lcom/squareup/kotlinpoet/CodeBlock$Companion;", "", "()V", "ARG_NAME", "", "EMPTY", "Lcom/squareup/kotlinpoet/CodeBlock;", "getEMPTY$kotlinpoet", "()Lcom/squareup/kotlinpoet/CodeBlock;", "LOWERCASE", "Lkotlin/text/Regex;", "NAMED_ARGUMENT", "NO_ARG_PLACEHOLDERS", "", "", "TYPE_NAME", "isMultiCharNoArgPlaceholder", "", "", "isMultiCharNoArgPlaceholder$kotlinpoet", "(C)Z", "isPlaceholder", "isPlaceholder$kotlinpoet", "(Ljava/lang/String;)Z", "isSingleCharNoArgPlaceholder", "isSingleCharNoArgPlaceholder$kotlinpoet", "builder", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "of", "format", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/CodeBlock;", "nextPotentialPlaceholderPosition", "startIndex", "nextPotentialPlaceholderPosition$kotlinpoet", "kotlinpoet"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isMultiCharNoArgPlaceholder$kotlinpoet(char c) {
            return c == '%';
        }

        private Companion() {
        }

        public final CodeBlock getEMPTY$kotlinpoet() {
            return CodeBlock.EMPTY;
        }

        @JvmStatic
        public final CodeBlock of(String format, Object... args) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(args, "args");
            return new Builder().add(format, Arrays.copyOf(args, args.length)).build();
        }

        @JvmStatic
        public final Builder builder() {
            return new Builder();
        }

        public final boolean isSingleCharNoArgPlaceholder$kotlinpoet(char c) {
            return UtilKt.isOneOf(Character.valueOf(c), (char) 8677, (char) 8676, (60 & 4) != 0 ? null : (char) 171, (60 & 8) != 0 ? null : (char) 187, (60 & 16) != 0 ? null : null, (60 & 32) != 0 ? null : null);
        }

        public final boolean isPlaceholder$kotlinpoet(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            if (str.length() == 1 && isSingleCharNoArgPlaceholder$kotlinpoet(StringsKt___StringsKt.first(str))) {
                return true;
            }
            return str.length() == 2 && isMultiCharNoArgPlaceholder$kotlinpoet(StringsKt___StringsKt.first(str));
        }

        public final int nextPotentialPlaceholderPosition$kotlinpoet(String str, int i) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            return StringsKt__StringsKt.indexOfAny$default((CharSequence) str, new char[]{'%', 171, 187, 8677, 8676}, i, false, 4, (Object) null);
        }
    }
}
