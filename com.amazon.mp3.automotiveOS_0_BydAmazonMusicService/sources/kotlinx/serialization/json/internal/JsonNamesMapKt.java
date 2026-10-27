package kotlinx.serialization.json.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonNames;
import kotlinx.serialization.json.JsonSchemaCacheKt;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;

/* JADX INFO: compiled from: JsonNamesMap.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0018\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0000H\u0000\u001a\u001c\u0010\b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002H\u0000\u001a&\u0010\n\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u0002H\u0000\"2\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00010\u000b8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\f\u0010\r\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lkotlinx/serialization/descriptors/SerialDescriptor;", "", "", "", "buildAlternativeNamesMap", "Lkotlinx/serialization/json/Json;", "json", "name", "getJsonNameIndex", "suffix", "getJsonNameIndexOrThrow", "Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "JsonAlternativeNamesKey", "Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "getJsonAlternativeNamesKey", "()Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "getJsonAlternativeNamesKey$annotations", "()V", "kotlinx-serialization-json"}, k = 2, mv = {1, 7, 1})
public final class JsonNamesMapKt {
    private static final DescriptorSchemaCache.Key<Map<String, Integer>> JsonAlternativeNamesKey = new DescriptorSchemaCache.Key<>();

    public static final DescriptorSchemaCache.Key<Map<String, Integer>> getJsonAlternativeNamesKey() {
        return JsonAlternativeNamesKey;
    }

    private static final void buildAlternativeNamesMap$putOrThrow(Map<String, Integer> map, SerialDescriptor serialDescriptor, String str, int i) {
        if (map.containsKey(str)) {
            throw new JsonException("The suggested name '" + str + "' for property " + serialDescriptor.getElementName(i) + " is already one of the names for property " + serialDescriptor.getElementName(((Number) MapsKt__MapsKt.getValue(map, str)).intValue()) + " in " + serialDescriptor);
        }
        map.put(str, Integer.valueOf(i));
    }

    public static final Map<String, Integer> buildAlternativeNamesMap(SerialDescriptor serialDescriptor) {
        String[] strArrNames;
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        int elementsCount = serialDescriptor.getElementsCount();
        Map<String, Integer> mapCreateMapForCache = null;
        for (int i = 0; i < elementsCount; i++) {
            List<Annotation> elementAnnotations = serialDescriptor.getElementAnnotations(i);
            ArrayList arrayList = new ArrayList();
            for (Object obj : elementAnnotations) {
                if (obj instanceof JsonNames) {
                    arrayList.add(obj);
                }
            }
            JsonNames jsonNames = (JsonNames) CollectionsKt___CollectionsKt.singleOrNull((List) arrayList);
            if (jsonNames != null && (strArrNames = jsonNames.names()) != null) {
                for (String str : strArrNames) {
                    if (mapCreateMapForCache == null) {
                        mapCreateMapForCache = CreateMapForCacheKt.createMapForCache(serialDescriptor.getElementsCount());
                    }
                    Intrinsics.checkNotNull(mapCreateMapForCache);
                    buildAlternativeNamesMap$putOrThrow(mapCreateMapForCache, serialDescriptor, str, i);
                }
            }
        }
        return mapCreateMapForCache == null ? MapsKt__MapsKt.emptyMap() : mapCreateMapForCache;
    }

    public static final int getJsonNameIndex(SerialDescriptor serialDescriptor, Json json, String name) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        int elementIndex = serialDescriptor.getElementIndex(name);
        if (elementIndex != -3 || !json.getConfiguration().getUseAlternativeNames()) {
            return elementIndex;
        }
        Integer num = (Integer) ((Map) JsonSchemaCacheKt.getSchemaCache(json).getOrPut(serialDescriptor, JsonAlternativeNamesKey, new JsonNamesMapKt$getJsonNameIndex$alternativeNamesMap$1(serialDescriptor))).get(name);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    public static /* synthetic */ int getJsonNameIndexOrThrow$default(SerialDescriptor serialDescriptor, Json json, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = "";
        }
        return getJsonNameIndexOrThrow(serialDescriptor, json, str, str2);
    }

    public static final int getJsonNameIndexOrThrow(SerialDescriptor serialDescriptor, Json json, String name, String suffix) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        int jsonNameIndex = getJsonNameIndex(serialDescriptor, json, name);
        if (jsonNameIndex != -3) {
            return jsonNameIndex;
        }
        throw new SerializationException(serialDescriptor.getSerialName() + " does not contain element with name '" + name + '\'' + suffix);
    }
}
