package com.squareup.kotlinpoet;

import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: ClassName.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0011\n\u0002\b\u0003\u0018\u0000 +2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001+BK\b\u0000\u0012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0005\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0018\b\u0002\u0010\u000b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0004\b'\u0010(B%\b\u0016\u0012\u0006\u0010!\u001a\u00020\u000f\u0012\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0)\"\u00020\u000f¢\u0006\u0004\b'\u0010*J6\u0010\f\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0016\u0010\u000b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\n0\bH\u0016J\b\u0010\r\u001a\u0004\u0018\u00010\u0000J\u0006\u0010\u000e\u001a\u00020\u0000J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000fJ\u0011\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0000H\u0096\u0002J\u0017\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0010¢\u0006\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010!\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b \u0010\u001fR\u0011\u0010#\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\"\u0010\u001fR\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00058F¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006,"}, d2 = {"Lcom/squareup/kotlinpoet/ClassName;", "Lcom/squareup/kotlinpoet/TypeName;", "", "", "nullable", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "annotations", "", "Lkotlin/reflect/KClass;", "", "tags", "copy", "enclosingClassName", "topLevelClassName", "", "name", "nestedClass", "other", "", "compareTo", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "emit$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeWriter;)Lcom/squareup/kotlinpoet/CodeWriter;", "emit", "names", "Ljava/util/List;", "canonicalName", "Ljava/lang/String;", "getCanonicalName", "()Ljava/lang/String;", "getPackageName", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "getSimpleName", "simpleName", "getSimpleNames", "()Ljava/util/List;", "simpleNames", "<init>", "(Ljava/util/List;ZLjava/util/List;Ljava/util/Map;)V", "", "(Ljava/lang/String;[Ljava/lang/String;)V", "Companion", "kotlinpoet"}, k = 1, mv = {1, 6, 0})
public final class ClassName extends TypeName implements Comparable<ClassName> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String canonicalName;
    private final List<String> names;

    @Override // com.squareup.kotlinpoet.TypeName
    public /* bridge */ /* synthetic */ TypeName copy(boolean z, List list, Map map) {
        return copy(z, (List<AnnotationSpec>) list, (Map<KClass<?>, ? extends Object>) map);
    }

    public /* synthetic */ ClassName(List list, boolean z, List list2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? false : z, (i & 4) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list2, (i & 8) != 0 ? MapsKt__MapsKt.emptyMap() : map);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClassName(List<String> names, boolean z, List<AnnotationSpec> annotations, Map<KClass<?>, ? extends Object> tags) {
        String strJoinToString$default;
        super(z, annotations, new TagMap(tags), null);
        Intrinsics.checkNotNullParameter(names, "names");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(tags, "tags");
        this.names = UtilKt.toImmutableList(names);
        if (names.get(0).length() == 0) {
            strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(names.subList(1, names.size()), ".", null, null, 0, null, null, 62, null);
        } else {
            strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(names, ".", null, null, 0, null, null, 62, null);
        }
        this.canonicalName = strJoinToString$default;
    }

    public ClassName(String packageName, String... simpleNames) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(simpleNames, "simpleNames");
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(packageName);
        spreadBuilder.addSpread(simpleNames);
        this(CollectionsKt__CollectionsKt.listOf(spreadBuilder.toArray(new String[spreadBuilder.size()])), false, null, null, 14, null);
        boolean z = false;
        if (!(!(simpleNames.length == 0))) {
            throw new IllegalArgumentException("simpleNames must not be empty".toString());
        }
        int length = simpleNames.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            }
            String str = simpleNames[i];
            i++;
            if (str.length() == 0) {
                break;
            }
        }
        if (z) {
            return;
        }
        String string = Arrays.toString(simpleNames);
        Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
        throw new IllegalArgumentException(Intrinsics.stringPlus("simpleNames must not contain empty items: ", string).toString());
    }

    public final String getCanonicalName() {
        return this.canonicalName;
    }

    public final String getPackageName() {
        return this.names.get(0);
    }

    public final String getSimpleName() {
        return this.names.get(r0.size() - 1);
    }

    public final List<String> getSimpleNames() {
        List<String> list = this.names;
        return list.subList(1, list.size());
    }

    @Override // com.squareup.kotlinpoet.TypeName
    public ClassName copy(boolean nullable, List<AnnotationSpec> annotations, Map<KClass<?>, ? extends Object> tags) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(tags, "tags");
        return new ClassName(this.names, nullable, annotations, tags);
    }

    public final ClassName enclosingClassName() {
        if (this.names.size() == 2) {
            return null;
        }
        return new ClassName(this.names.subList(0, r1.size() - 1), false, null, null, 14, null);
    }

    public final ClassName topLevelClassName() {
        return new ClassName(this.names.subList(0, 2), false, null, null, 14, null);
    }

    public final ClassName nestedClass(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new ClassName(CollectionsKt___CollectionsKt.plus((Collection<? extends String>) this.names, name), false, null, null, 14, null);
    }

    @Override // java.lang.Comparable
    public int compareTo(ClassName other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.canonicalName.compareTo(other.canonicalName);
    }

    @Override // com.squareup.kotlinpoet.TypeName
    public CodeWriter emit$kotlinpoet(CodeWriter out) {
        Intrinsics.checkNotNullParameter(out, "out");
        return CodeWriter.emit$default(out, UtilKt.escapeSegmentsIfNecessary$default(out.lookupName(this), (char) 0, 1, null), false, 2, null);
    }

    /* JADX INFO: compiled from: ClassName.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/squareup/kotlinpoet/ClassName$Companion;", "", "()V", "bestGuess", "Lcom/squareup/kotlinpoet/ClassName;", "classNameString", "", "kotlinpoet"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ClassName bestGuess(String classNameString) {
            boolean z;
            String strSubstring;
            Intrinsics.checkNotNullParameter(classNameString, "classNameString");
            ArrayList arrayList = new ArrayList();
            int iIndexOf$default = 0;
            do {
                z = true;
                if (iIndexOf$default < classNameString.length() && Character.isLowerCase(classNameString.codePointAt(iIndexOf$default))) {
                    iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) classNameString, '.', iIndexOf$default, false, 4, (Object) null) + 1;
                    if (iIndexOf$default == 0) {
                        z = false;
                    }
                } else {
                    if (iIndexOf$default != 0) {
                        strSubstring = classNameString.substring(0, iIndexOf$default - 1);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                    } else {
                        strSubstring = "";
                    }
                    arrayList.add(strSubstring);
                    String strSubstring2 = classNameString.substring(iIndexOf$default);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
                    for (String str : StringsKt__StringsKt.split$default((CharSequence) strSubstring2, new char[]{'.'}, false, 0, 6, (Object) null)) {
                        if (!((str.length() > 0) && Character.isUpperCase(str.codePointAt(0)))) {
                            throw new IllegalArgumentException(Intrinsics.stringPlus("couldn't make a guess for ", classNameString).toString());
                        }
                        arrayList.add(str);
                    }
                    if (!(arrayList.size() >= 2)) {
                        throw new IllegalArgumentException(Intrinsics.stringPlus("couldn't make a guess for ", classNameString).toString());
                    }
                    return new ClassName(arrayList, false, null, null, 14, null);
                }
            } while (z);
            throw new IllegalArgumentException(Intrinsics.stringPlus("couldn't make a guess for ", classNameString).toString());
        }
    }
}
