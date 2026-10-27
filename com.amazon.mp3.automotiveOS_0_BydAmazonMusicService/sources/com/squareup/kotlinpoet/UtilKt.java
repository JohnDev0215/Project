package com.squareup.kotlinpoet;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amplifyframework.core.model.ModelIdentifier;
import com.fasterxml.jackson.core.JsonPointer;
import com.squareup.kotlinpoet.CodeBlock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt___StringsKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: Util.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000f\u001a0\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0000\u001a\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0000\u001a\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0000\u001aY\u0010\u0011\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0004*\u00028\u00002\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u00002\n\b\u0002\u0010\f\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00018\u0000H\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a3\u0010\u0015\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u00028\u00000\u00052\u0012\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0013\"\u00028\u0000H\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0000\u001a\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0000\u001a$\u0010 \u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u00192\b\b\u0002\u0010\u001e\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020\u0010H\u0000\u001a\f\u0010\"\u001a\u00020!*\u00020!H\u0000\u001a\f\u0010$\u001a\u00020#*\u00020\u0019H\u0002\u001a\u0016\u0010&\u001a\u00020\u0019*\u00020\u00192\b\b\u0002\u0010%\u001a\u00020\u0010H\u0000\u001a\f\u0010'\u001a\u00020\u0010*\u00020\u0019H\u0002\u001a\f\u0010(\u001a\u00020\u0019*\u00020\u0019H\u0002\u001a\f\u0010)\u001a\u00020\u0019*\u00020\u0019H\u0002\u001a\f\u0010*\u001a\u00020\u0019*\u00020\u0019H\u0002\u001a\f\u0010+\u001a\u00020\u0019*\u00020\u0019H\u0002\u001a\u0016\u0010-\u001a\u00020\u0019*\u00020\u00192\b\b\u0002\u0010,\u001a\u00020\u0017H\u0000\"\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100\"\u001a\u00101\u001a\b\u0012\u0004\u0012\u00020\u00190\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102\"\u001a\u00103\u001a\b\u0012\u0004\u0012\u00020\u00170\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00102\"\u0018\u00104\u001a\u00020\u0010*\u00020\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b4\u00105\"\u0018\u00106\u001a\u00020\u0010*\u00020\u00198@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b6\u00107\"\u0018\u00108\u001a\u00020\u0010*\u00020\u00198@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b8\u00107\"\u0018\u0010:\u001a\u00020\u0010*\u00020\u00198@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u00107\"\u0018\u0010<\u001a\u00020\u0010*\u00020\u00198@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b;\u00107¨\u0006="}, d2 = {"K", "V", "", "toImmutableMap", "T", "", "", "toImmutableList", "", "toImmutableSet", "t1", "t2", "t3", "t4", "t5", "t6", "", "isOneOf", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "", "t", "containsAnyOf", "(Ljava/util/Collection;[Ljava/lang/Object;)Z", "", "c", "", "characterLiteralWithoutSingleQuotes", "s", "escapeCharacterLiterals", "value", "escapeDollarSign", "isConstantContext", "stringLiteralWithQuotes", "Lcom/squareup/kotlinpoet/CodeBlock;", "ensureEndsWithNewLine", "", "failIfEscapeInvalid", "validate", "escapeIfNecessary", "alreadyEscaped", "escapeIfKeyword", "escapeIfHasAllowedCharacters", "escapeIfAllCharactersAreUnderscore", "escapeIfNotJavaIdentifier", "delimiter", "escapeSegmentsIfNecessary", "Lkotlin/text/Regex;", "IDENTIFIER_REGEX", "Lkotlin/text/Regex;", "KEYWORDS", "Ljava/util/Set;", "ILLEGAL_CHARACTERS_TO_ESCAPE", "isIsoControl", "(C)Z", "isIdentifier", "(Ljava/lang/String;)Z", "isKeyword", "getHasAllowedCharacters", "hasAllowedCharacters", "getAllCharactersAreUnderscore", "allCharactersAreUnderscore", "kotlinpoet"}, k = 2, mv = {1, 6, 0})
public final class UtilKt {
    private static final Regex IDENTIFIER_REGEX = new Regex("((\\p{gc=Lu}+|\\p{gc=Ll}+|\\p{gc=Lt}+|\\p{gc=Lm}+|\\p{gc=Lo}+|\\p{gc=Nl}+)+\\d*\\p{gc=Lu}*\\p{gc=Ll}*\\p{gc=Lt}*\\p{gc=Lm}*\\p{gc=Lo}*\\p{gc=Nl}*)|(`[^\n\r`]+`)");
    private static final Set<String> KEYWORDS = SetsKt__SetsKt.setOf((Object[]) new String[]{"as", "break", "class", "continue", "do", "else", "false", "for", "fun", "if", "in", ContextMappingConstants.INTERFACE, "is", "null", "object", "package", "return", "super", "this", "throw", "true", "try", "typealias", "typeof", "val", "var", "when", "while", "by", "catch", "constructor", "delegate", "dynamic", "field", "file", "finally", "get", "import", "init", "param", "property", "receiver", "set", "setparam", "where", "actual", "abstract", "annotation", "companion", "const", "crossinline", "data", "enum", "expect", "external", "final", "infix", "inline", "inner", "internal", "lateinit", "noinline", "open", "operator", "out", "override", "private", "protected", "public", "reified", "sealed", "suspend", "tailrec", "value", "vararg", "yield"});
    private static final Set<Character> ILLEGAL_CHARACTERS_TO_ESCAPE = SetsKt__SetsKt.setOf((Object[]) new Character[]{'.', ';', '[', ']', Character.valueOf(JsonPointer.SEPARATOR), Character.valueOf(Typography.less), Character.valueOf(Typography.greater), ':', '\\'});

    private static final boolean isIsoControl(char c) {
        if (c >= 0 && c < ' ') {
            return true;
        }
        return 127 <= c && c < 160;
    }

    public static final <K, V> Map<K, V> toImmutableMap(Map<K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Map<K, V> mapUnmodifiableMap = Collections.unmodifiableMap(new LinkedHashMap(map));
        Intrinsics.checkNotNullExpressionValue(mapUnmodifiableMap, "unmodifiableMap(LinkedHashMap(this))");
        return mapUnmodifiableMap;
    }

    public static final <T> List<T> toImmutableList(Collection<? extends T> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        List<T> listUnmodifiableList = Collections.unmodifiableList(new ArrayList(collection));
        Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(ArrayList(this))");
        return listUnmodifiableList;
    }

    public static final <T> Set<T> toImmutableSet(Collection<? extends T> collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Set<T> setUnmodifiableSet = Collections.unmodifiableSet(new LinkedHashSet(collection));
        Intrinsics.checkNotNullExpressionValue(setUnmodifiableSet, "unmodifiableSet(LinkedHashSet(this))");
        return setUnmodifiableSet;
    }

    public static final <T> boolean isOneOf(T t, T t2, T t3, T t4, T t5, T t6, T t7) {
        return Intrinsics.areEqual(t, t2) || Intrinsics.areEqual(t, t3) || Intrinsics.areEqual(t, t4) || Intrinsics.areEqual(t, t5) || Intrinsics.areEqual(t, t6) || Intrinsics.areEqual(t, t7);
    }

    public static final String characterLiteralWithoutSingleQuotes(char c) {
        if (c == '\b') {
            return "\\b";
        }
        if (c == '\t') {
            return "\\t";
        }
        if (c == '\n') {
            return "\\n";
        }
        if (c == '\r') {
            return "\\r";
        }
        if (c == '\"') {
            return ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR;
        }
        if (c == '\'') {
            return "\\'";
        }
        if (c == '\\') {
            return "\\\\";
        }
        if (!isIsoControl(c)) {
            return String.valueOf(c);
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("\\u%04x", Arrays.copyOf(new Object[]{Integer.valueOf(c)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        return str;
    }

    public static final String escapeCharacterLiterals(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        int i = 0;
        while (i < length) {
            char cCharAt = s.charAt(i);
            i++;
            sb.append(characterLiteralWithoutSingleQuotes(cCharAt));
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static final String stringLiteralWithQuotes(String value, boolean z, boolean z2) {
        int i;
        Intrinsics.checkNotNullParameter(value, "value");
        if (!z2 && StringsKt__StringsKt.contains$default((CharSequence) value, '\n', false, 2, (Object) null)) {
            StringBuilder sb = new StringBuilder(value.length() + 32);
            sb.append("\"\"\"\n|");
            int i2 = 0;
            while (i2 < value.length()) {
                char cCharAt = value.charAt(i2);
                int i3 = i2;
                StringBuilder sb2 = sb;
                if (StringsKt__StringsJVMKt.regionMatches$default(value, i2, "\"\"\"", 0, 3, false, 16, (Object) null)) {
                    sb2.append("\"\"${'\"'}");
                    i = i3 + 2;
                } else {
                    if (cCharAt == '\n') {
                        sb2.append("\n|");
                    } else if (cCharAt == '$' && z) {
                        sb2.append("${'$'}");
                    } else {
                        sb2.append(cCharAt);
                    }
                    i = i3;
                }
                i2 = i + 1;
                sb = sb2;
            }
            StringBuilder sb3 = sb;
            if (!StringsKt__StringsJVMKt.endsWith$default(value, "\n", false, 2, null)) {
                sb3.append("\n");
            }
            sb3.append("\"\"\".trimMargin()");
            String string = sb3.toString();
            Intrinsics.checkNotNullExpressionValue(string, "result.toString()");
            return string;
        }
        int i4 = 0;
        StringBuilder sb4 = new StringBuilder(value.length() + 32);
        if (z) {
            sb4.append('\"');
        } else {
            sb4.append("\"\"\"");
        }
        int length = value.length();
        while (true) {
            int i5 = i4;
            if (i5 >= length) {
                break;
            }
            i4 = i5 + 1;
            char cCharAt2 = value.charAt(i5);
            if (cCharAt2 == '\'') {
                sb4.append("'");
            } else if (cCharAt2 == '\"' && z) {
                sb4.append("\\\"");
            } else if (cCharAt2 == '$' && z) {
                sb4.append("${'$'}");
            } else {
                sb4.append(characterLiteralWithoutSingleQuotes(cCharAt2));
            }
        }
        if (z) {
            sb4.append('\"');
        } else {
            sb4.append("\"\"\"");
        }
        String string2 = sb4.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "result.toString()");
        return string2;
    }

    public static final CodeBlock ensureEndsWithNewLine(CodeBlock codeBlock) {
        Intrinsics.checkNotNullParameter(codeBlock, "<this>");
        if (codeBlock.isEmpty()) {
            return codeBlock;
        }
        CodeBlock.Builder builder = codeBlock.toBuilder();
        String str = (String) CollectionsKt___CollectionsKt.last((List) codeBlock.trim$kotlinpoet().getFormatParts$kotlinpoet());
        if (CodeBlock.Companion.isPlaceholder$kotlinpoet(str) && (!builder.getArgs$kotlinpoet().isEmpty())) {
            Object objLast = CollectionsKt___CollectionsKt.last((List<? extends Object>) builder.getArgs$kotlinpoet());
            if (objLast instanceof String) {
                builder.getArgs$kotlinpoet().set(builder.getArgs$kotlinpoet().size() - 1, Intrinsics.stringPlus(StringsKt__StringsKt.trimEnd((String) objLast, '\n'), "\n"));
            }
        } else {
            builder.getFormatParts$kotlinpoet().set(builder.getFormatParts$kotlinpoet().lastIndexOf(str), StringsKt__StringsKt.trimEnd(str, '\n'));
            builder.getFormatParts$kotlinpoet().add("\n");
        }
        return builder.build();
    }

    public static final boolean isIdentifier(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return IDENTIFIER_REGEX.matches(str);
    }

    public static final boolean isKeyword(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return KEYWORDS.contains(str);
    }

    public static final String escapeIfNecessary(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String strEscapeIfAllCharactersAreUnderscore = escapeIfAllCharactersAreUnderscore(escapeIfHasAllowedCharacters(escapeIfKeyword(escapeIfNotJavaIdentifier(str))));
        if (z) {
            failIfEscapeInvalid(strEscapeIfAllCharactersAreUnderscore);
        }
        return strEscapeIfAllCharactersAreUnderscore;
    }

    public static /* synthetic */ String escapeIfNecessary$default(String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return escapeIfNecessary(str, z);
    }

    private static final boolean alreadyEscaped(String str) {
        return StringsKt__StringsJVMKt.startsWith$default(str, "`", false, 2, null) && StringsKt__StringsJVMKt.endsWith$default(str, "`", false, 2, null);
    }

    private static final String escapeIfKeyword(String str) {
        if (!isKeyword(str) || alreadyEscaped(str)) {
            return str;
        }
        return '`' + str + '`';
    }

    private static final String escapeIfHasAllowedCharacters(String str) {
        if (!getHasAllowedCharacters(str) || alreadyEscaped(str)) {
            return str;
        }
        return '`' + str + '`';
    }

    private static final String escapeIfAllCharactersAreUnderscore(String str) {
        if (!getAllCharactersAreUnderscore(str) || alreadyEscaped(str)) {
            return str;
        }
        return '`' + str + '`';
    }

    private static final String escapeIfNotJavaIdentifier(String str) {
        if (Character.isJavaIdentifierStart(StringsKt___StringsKt.first(str))) {
            boolean z = true;
            String strDrop = StringsKt___StringsKt.drop(str, 1);
            int i = 0;
            while (true) {
                if (i >= strDrop.length()) {
                    z = false;
                    break;
                }
                char cCharAt = strDrop.charAt(i);
                i++;
                if (!Character.isJavaIdentifierPart(cCharAt)) {
                    break;
                }
            }
            if (!z) {
                return str;
            }
        }
        if (alreadyEscaped(str)) {
            return str;
        }
        return StringsKt__StringsJVMKt.replace$default('`' + str + '`', ' ', Typography.middleDot, false, 4, (Object) null);
    }

    public static final String escapeSegmentsIfNecessary(String str, char c) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) str, new char[]{c}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listSplit$default) {
            if (((String) obj).length() > 0) {
                arrayList.add(obj);
            }
        }
        return CollectionsKt___CollectionsKt.joinToString$default(arrayList, String.valueOf(c), null, null, 0, null, new Function1<String, CharSequence>() { // from class: com.squareup.kotlinpoet.UtilKt.escapeSegmentsIfNecessary.2
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return UtilKt.escapeIfNecessary$default(it, false, 1, null);
            }
        }, 30, null);
    }

    public static /* synthetic */ String escapeSegmentsIfNecessary$default(String str, char c, int i, Object obj) {
        if ((i & 1) != 0) {
            c = '.';
        }
        return escapeSegmentsIfNecessary(str, c);
    }

    public static final boolean getAllCharactersAreUnderscore(String str) {
        boolean z;
        Intrinsics.checkNotNullParameter(str, "<this>");
        int i = 0;
        do {
            z = true;
            if (i >= str.length()) {
                return true;
            }
            char cCharAt = str.charAt(i);
            i++;
            if (cCharAt != '_') {
                z = false;
            }
        } while (z);
        return false;
    }

    private static final void failIfEscapeInvalid(String str) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                break;
            }
            char cCharAt = str.charAt(i);
            i++;
            if (ILLEGAL_CHARACTERS_TO_ESCAPE.contains(Character.valueOf(cCharAt))) {
                z = true;
                break;
            }
        }
        if (!z) {
            return;
        }
        throw new IllegalArgumentException(("Can't escape identifier " + str + " because it contains illegal characters: " + CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt___CollectionsKt.intersect(ILLEGAL_CHARACTERS_TO_ESCAPE, StringsKt___StringsKt.toSet(str)), "", null, null, 0, null, null, 62, null)).toString());
    }

    public static final boolean getHasAllowedCharacters(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int i = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            i++;
            if (cCharAt == '$') {
                return true;
            }
        }
        return false;
    }

    public static final <T> boolean containsAnyOf(Collection<? extends T> collection, T... t) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(t, "t");
        int length = t.length;
        int i = 0;
        while (i < length) {
            T t2 = t[i];
            i++;
            if (collection.contains(t2)) {
                return true;
            }
        }
        return false;
    }
}
