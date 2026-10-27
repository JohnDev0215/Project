package kotlinx.metadata.internal.metadata.jvm.deserialization;

import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import com.fasterxml.jackson.core.JsonPointer;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: ClassMapperLite.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlinx/metadata/internal/metadata/jvm/deserialization/ClassMapperLite;", "", "()V", "kotlin", "", "map", "", "mapClass", "classId", "metadata.jvm"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ClassMapperLite {
    public static final ClassMapperLite INSTANCE = new ClassMapperLite();
    private static final String kotlin = CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt__CollectionsKt.listOf((Object[]) new Character[]{'k', 'o', 't', 'l', 'i', 'n'}), "", null, null, 0, null, null, 62, null);
    private static final Map<String, String> map;

    private ClassMapperLite() {
    }

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"Boolean", "Z", "Char", PlatformWeblabs.C, "Byte", "B", "Short", "S", "Int", "I", "Float", "F", "Long", "J", "Double", "D"});
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, listListOf.size() - 1, 2);
        if (progressionLastElement >= 0) {
            int i = 0;
            while (true) {
                StringBuilder sb = new StringBuilder();
                String str = kotlin;
                sb.append(str);
                sb.append(JsonPointer.SEPARATOR);
                sb.append((String) listListOf.get(i));
                int i2 = i + 1;
                linkedHashMap.put(sb.toString(), listListOf.get(i2));
                linkedHashMap.put(str + JsonPointer.SEPARATOR + ((String) listListOf.get(i)) + "Array", '[' + ((String) listListOf.get(i2)));
                if (i == progressionLastElement) {
                    break;
                } else {
                    i += 2;
                }
            }
        }
        linkedHashMap.put(kotlin + "/Unit", "V");
        m4365map$lambda0$add(linkedHashMap, "Any", "java/lang/Object");
        m4365map$lambda0$add(linkedHashMap, "Nothing", "java/lang/Void");
        m4365map$lambda0$add(linkedHashMap, "Annotation", "java/lang/annotation/Annotation");
        for (String str2 : CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"String", "CharSequence", "Throwable", "Cloneable", "Number", "Comparable", "Enum"})) {
            m4365map$lambda0$add(linkedHashMap, str2, "java/lang/" + str2);
        }
        for (String str3 : CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"Iterator", "Collection", "List", "Set", "Map", "ListIterator"})) {
            m4365map$lambda0$add(linkedHashMap, "collections/" + str3, "java/util/" + str3);
            m4365map$lambda0$add(linkedHashMap, "collections/Mutable" + str3, "java/util/" + str3);
        }
        m4365map$lambda0$add(linkedHashMap, "collections/Iterable", "java/lang/Iterable");
        m4365map$lambda0$add(linkedHashMap, "collections/MutableIterable", "java/lang/Iterable");
        m4365map$lambda0$add(linkedHashMap, "collections/Map.Entry", "java/util/Map$Entry");
        m4365map$lambda0$add(linkedHashMap, "collections/MutableMap.MutableEntry", "java/util/Map$Entry");
        for (int i3 = 0; i3 < 23; i3++) {
            StringBuilder sb2 = new StringBuilder();
            String str4 = kotlin;
            sb2.append(str4);
            sb2.append("/jvm/functions/Function");
            sb2.append(i3);
            m4365map$lambda0$add(linkedHashMap, "Function" + i3, sb2.toString());
            m4365map$lambda0$add(linkedHashMap, "reflect/KFunction" + i3, str4 + "/reflect/KFunction");
        }
        for (String str5 : CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"Char", "Byte", "Short", "Int", "Float", "Long", "Double", "String", "Enum"})) {
            m4365map$lambda0$add(linkedHashMap, str5 + ".Companion", kotlin + "/jvm/internal/" + str5 + "CompanionObject");
        }
        map = linkedHashMap;
    }

    /* JADX INFO: renamed from: map$lambda-0$add, reason: not valid java name */
    private static final void m4365map$lambda0$add(Map<String, String> map2, String str, String str2) {
        map2.put(kotlin + JsonPointer.SEPARATOR + str, 'L' + str2 + ';');
    }

    @JvmStatic
    public static final String mapClass(String classId) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        String str = map.get(classId);
        if (str != null) {
            return str;
        }
        return 'L' + StringsKt__StringsJVMKt.replace$default(classId, '.', Typography.dollar, false, 4, (Object) null) + ';';
    }
}
