package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import androidx.exifinterface.media.ExifInterface;
import com.byd.car.VehicleBrand;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import spi.d;
import spi.hi;

/* JADX INFO: loaded from: classes2.dex */
public final class ClassMapperLite {

    @NotNull
    public static final ClassMapperLite INSTANCE = new ClassMapperLite();

    /* JADX INFO: renamed from: kotlin, reason: collision with root package name */
    @NotNull
    private static final String f5kotlin = CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new Character[]{'k', 'o', 't', 'l', 'i', 'n'}), "", null, null, 0, null, null, 62, null);

    @NotNull
    private static final Map<String, String> map;

    static {
        int i = 0;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List listListOf = CollectionsKt.listOf((Object[]) new String[]{"Boolean", "Z", "Char", "C", "Byte", "B", "Short", ExifInterface.LATITUDE_SOUTH, "Int", "I", "Float", VehicleBrand.VEHICLE_BRAND_F, "Long", "J", "Double", "D"});
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, listListOf.size() - 1, 2);
        if (progressionLastElement >= 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 2;
                StringBuilder sb = new StringBuilder();
                String str = f5kotlin;
                sb.append(str);
                sb.append('/');
                sb.append((String) listListOf.get(i2));
                int i4 = i2 + 1;
                linkedHashMap.put(sb.toString(), listListOf.get(i4));
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append('/');
                linkedHashMap.put(hi.f(sb2, (String) listListOf.get(i2), "Array"), Intrinsics.stringPlus("[", listListOf.get(i4)));
                if (i2 == progressionLastElement) {
                    break;
                } else {
                    i2 = i3;
                }
            }
        }
        linkedHashMap.put(Intrinsics.stringPlus(f5kotlin, "/Unit"), ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        m1343map$lambda0$add(linkedHashMap, "Any", "java/lang/Object");
        m1343map$lambda0$add(linkedHashMap, "Nothing", "java/lang/Void");
        m1343map$lambda0$add(linkedHashMap, "Annotation", "java/lang/annotation/Annotation");
        for (String str2 : CollectionsKt.listOf((Object[]) new String[]{"String", "CharSequence", "Throwable", "Cloneable", "Number", "Comparable", "Enum"})) {
            m1343map$lambda0$add(linkedHashMap, str2, Intrinsics.stringPlus("java/lang/", str2));
        }
        for (String str3 : CollectionsKt.listOf((Object[]) new String[]{"Iterator", "Collection", "List", "Set", "Map", "ListIterator"})) {
            m1343map$lambda0$add(linkedHashMap, Intrinsics.stringPlus("collections/", str3), Intrinsics.stringPlus("java/util/", str3));
            m1343map$lambda0$add(linkedHashMap, Intrinsics.stringPlus("collections/Mutable", str3), Intrinsics.stringPlus("java/util/", str3));
        }
        m1343map$lambda0$add(linkedHashMap, "collections/Iterable", "java/lang/Iterable");
        m1343map$lambda0$add(linkedHashMap, "collections/MutableIterable", "java/lang/Iterable");
        m1343map$lambda0$add(linkedHashMap, "collections/Map.Entry", "java/util/Map$Entry");
        m1343map$lambda0$add(linkedHashMap, "collections/MutableMap.MutableEntry", "java/util/Map$Entry");
        while (true) {
            int i5 = i + 1;
            String strStringPlus = Intrinsics.stringPlus("Function", Integer.valueOf(i));
            StringBuilder sb3 = new StringBuilder();
            String str4 = f5kotlin;
            m1343map$lambda0$add(linkedHashMap, strStringPlus, hi.g(sb3, str4, "/jvm/functions/Function", i));
            m1343map$lambda0$add(linkedHashMap, Intrinsics.stringPlus("reflect/KFunction", Integer.valueOf(i)), Intrinsics.stringPlus(str4, "/reflect/KFunction"));
            if (i5 > 22) {
                break;
            } else {
                i = i5;
            }
        }
        for (String str5 : CollectionsKt.listOf((Object[]) new String[]{"Char", "Byte", "Short", "Int", "Float", "Long", "Double", "String", "Enum"})) {
            m1343map$lambda0$add(linkedHashMap, Intrinsics.stringPlus(str5, ".Companion"), f5kotlin + "/jvm/internal/" + str5 + "CompanionObject");
        }
        map = linkedHashMap;
    }

    private ClassMapperLite() {
    }

    /* JADX INFO: renamed from: map$lambda-0$add, reason: not valid java name */
    private static final void m1343map$lambda0$add(Map<String, String> map2, String str, String str2) {
        map2.put(f5kotlin + '/' + str, 'L' + str2 + ';');
    }

    @JvmStatic
    @NotNull
    public static final String mapClass(@NotNull String classId) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        String str = map.get(classId);
        if (str != null) {
            return str;
        }
        StringBuilder sbI = d.i('L');
        sbI.append(StringsKt__StringsJVMKt.replace$default(classId, '.', Typography.dollar, false, 4, (Object) null));
        sbI.append(';');
        return sbI.toString();
    }
}
