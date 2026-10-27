package com.bugsnag.android;

import com.bugsnag.android.JsonStream;
import com.bugsnag.android.internal.StringUtils;
import com.bugsnag.android.internal.TrimMetrics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: Metadata.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\b\t\b\u0080\b\u0018\u0000 /2\u00020\u00012\u00020\u0002:\u0001/B+\b\u0007\u0012 \b\u0002\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u00030\u0003¢\u0006\u0004\b-\u0010.J,\u0010\t\u001a\u00020\b2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0002H\u0002J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016J&\u0010\u0010\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00042\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000eH\u0016J\"\u0010\u0010\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\u0018\u0010\u0011\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0016J\u001e\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0016J\u001e\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u00030\u0003J\u0006\u0010\u0014\u001a\u00020\u0000J\u000e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015J+\u0010\u0014\u001a\u00020\u00002 \b\u0002\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u00030\u0003HÆ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0015HÖ\u0001J\u0013\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÖ\u0003R\u0017\u0010 \u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R2\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u00030\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0019\u0010$\u001a\u0004\b%\u0010&R0\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040'2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040'8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u00060"}, d2 = {"Lcom/bugsnag/android/Metadata;", "Lcom/bugsnag/android/JsonStream$Streamable;", "", "", "", "map", "key", "newValue", "", "insertValue", "Lcom/bugsnag/android/JsonStream;", "writer", "toStream", "section", "", "value", "addMetadata", "clearMetadata", "getMetadata", "toMap", "copy", "", "maxStringLength", "Lcom/bugsnag/android/internal/TrimMetrics;", "trimMetadataStringsTo", "store", "toString", "hashCode", "other", "", "equals", "Lcom/bugsnag/android/ObjectJsonStreamer;", "jsonStreamer", "Lcom/bugsnag/android/ObjectJsonStreamer;", "getJsonStreamer", "()Lcom/bugsnag/android/ObjectJsonStreamer;", "Ljava/util/Map;", "getStore$bugsnag_android_core_release", "()Ljava/util/Map;", "", "getRedactedKeys", "()Ljava/util/Set;", "setRedactedKeys", "(Ljava/util/Set;)V", "redactedKeys", "<init>", "(Ljava/util/Map;)V", "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final /* data */ class Metadata implements JsonStream.Streamable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ObjectJsonStreamer jsonStreamer;
    private final Map<String, Map<String, Object>> store;

    @JvmOverloads
    public Metadata() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public final Metadata copy(Map<String, Map<String, Object>> store) {
        return new Metadata(store);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof Metadata) && kotlin.jvm.internal.Intrinsics.areEqual(this.store, ((Metadata) other).store);
        }
        return true;
    }

    public int hashCode() {
        Map<String, Map<String, Object>> map = this.store;
        if (map != null) {
            return map.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "Metadata(store=" + this.store + ")";
    }

    @JvmOverloads
    public Metadata(Map<String, Map<String, Object>> map) {
        this.store = map;
        this.jsonStreamer = new ObjectJsonStreamer();
    }

    public /* synthetic */ Metadata(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ConcurrentHashMap() : map);
    }

    public final Map<String, Map<String, Object>> getStore$bugsnag_android_core_release() {
        return this.store;
    }

    public final ObjectJsonStreamer getJsonStreamer() {
        return this.jsonStreamer;
    }

    public final Set<String> getRedactedKeys() {
        return this.jsonStreamer.getRedactedKeys();
    }

    public final void setRedactedKeys(Set<String> set) {
        this.jsonStreamer.setRedactedKeys(set);
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(JsonStream writer) throws IOException {
        this.jsonStreamer.objectToStream(this.store, writer, true);
    }

    public void addMetadata(String section, Map<String, ? extends Object> value) {
        Iterator<T> it = value.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            addMetadata(section, (String) entry.getKey(), entry.getValue());
        }
    }

    public void addMetadata(String section, String key, Object value) {
        if (value == null) {
            clearMetadata(section, key);
            return;
        }
        Map<String, Object> concurrentHashMap = this.store.get(section);
        if (concurrentHashMap == null) {
            concurrentHashMap = new ConcurrentHashMap<>();
        }
        this.store.put(section, concurrentHashMap);
        insertValue(concurrentHashMap, key, value);
    }

    private final void insertValue(Map<String, Object> map, String key, Object newValue) {
        Object obj = map.get(key);
        if (obj != null && (newValue instanceof Map)) {
            Map[] mapArr = new Map[2];
            mapArr[0] = (Map) obj;
            if (newValue == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
            }
            mapArr[1] = (Map) newValue;
            newValue = INSTANCE.mergeMaps$bugsnag_android_core_release(CollectionsKt__CollectionsKt.listOf((Object[]) mapArr));
        }
        map.put(key, newValue);
    }

    public void clearMetadata(String section) {
        this.store.remove(section);
    }

    public void clearMetadata(String section, String key) {
        Map<String, Object> map = this.store.get(section);
        if (map != null) {
            map.remove(key);
        }
        if (map == null || map.isEmpty()) {
            this.store.remove(section);
        }
    }

    public Map<String, Object> getMetadata(String section) {
        return this.store.get(section);
    }

    public Object getMetadata(String section, String key) {
        Map<String, Object> metadata = getMetadata(section);
        if (metadata != null) {
            return metadata.get(key);
        }
        return null;
    }

    public final Map<String, Map<String, Object>> toMap() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(this.store);
        Iterator<T> it = this.store.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            concurrentHashMap.put(entry.getKey(), new ConcurrentHashMap((Map) entry.getValue()));
        }
        return concurrentHashMap;
    }

    /* JADX INFO: compiled from: Metadata.kt */
    @kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00062\u0006\u0010\b\u001a\u00020\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\nH\u0002J\u001f\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000e\"\u00020\f¢\u0006\u0002\u0010\u000fJ3\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00062\u0018\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\n0\u0011H\u0000¢\u0006\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/bugsnag/android/Metadata$Companion;", "", "()V", "getMergeValue", "", "result", "", "", "key", "map", "", "merge", "Lcom/bugsnag/android/Metadata;", "data", "", "([Lcom/bugsnag/android/Metadata;)Lcom/bugsnag/android/Metadata;", "mergeMaps", "", "mergeMaps$bugsnag_android_core_release", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final void getMergeValue(Map<String, Object> result, String key, Map<String, ? extends Object> map) {
            Object obj = result.get(key);
            Object obj2 = map.get(key);
            if (obj2 == null) {
                if (obj != null) {
                    result.put(key, obj);
                }
            } else if ((obj instanceof Map) && (obj2 instanceof Map)) {
                result.put(key, mergeMaps$bugsnag_android_core_release(CollectionsKt__CollectionsKt.listOf((Object[]) new Map[]{(Map) obj, (Map) obj2})));
            } else {
                result.put(key, obj2);
            }
        }

        public final Metadata merge(Metadata... data) {
            ArrayList arrayList = new ArrayList(data.length);
            for (Metadata metadata : data) {
                arrayList.add(metadata.toMap());
            }
            ArrayList arrayList2 = new ArrayList();
            for (Metadata metadata2 : data) {
                CollectionsKt__MutableCollectionsKt.addAll(arrayList2, metadata2.getJsonStreamer().getRedactedKeys());
            }
            Map<String, Object> mapMergeMaps$bugsnag_android_core_release = mergeMaps$bugsnag_android_core_release(arrayList);
            if (mapMergeMaps$bugsnag_android_core_release != null) {
                Metadata metadata3 = new Metadata(TypeIntrinsics.asMutableMap(mapMergeMaps$bugsnag_android_core_release));
                metadata3.setRedactedKeys(CollectionsKt___CollectionsKt.toSet(arrayList2));
                return metadata3;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.collections.MutableMap<kotlin.String, kotlin.Any>>");
        }

        public final Map<String, Object> mergeMaps$bugsnag_android_core_release(List<? extends Map<String, ? extends Object>> data) {
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = data.iterator();
            while (it.hasNext()) {
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, ((Map) it.next()).keySet());
            }
            Set set = CollectionsKt___CollectionsKt.toSet(arrayList);
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            for (Map<String, ? extends Object> map : data) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    getMergeValue(concurrentHashMap, (String) it2.next(), map);
                }
            }
            return concurrentHashMap;
        }
    }

    public final Metadata copy() {
        Metadata metadataCopy = copy(toMap());
        metadataCopy.setRedactedKeys(CollectionsKt___CollectionsKt.toSet(getRedactedKeys()));
        return metadataCopy;
    }

    public final TrimMetrics trimMetadataStringsTo(int maxStringLength) {
        int itemsTrimmed = 0;
        int dataTrimmed = 0;
        for (Map.Entry<String, Map<String, Object>> entry : this.store.entrySet()) {
            StringUtils stringUtils = StringUtils.INSTANCE;
            Map<String, Object> value = entry.getValue();
            if (value != null) {
                TrimMetrics trimMetricsTrimStringValuesTo = stringUtils.trimStringValuesTo(maxStringLength, TypeIntrinsics.asMutableMap(value));
                itemsTrimmed += trimMetricsTrimStringValuesTo.getItemsTrimmed();
                dataTrimmed += trimMetricsTrimStringValuesTo.getDataTrimmed();
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Any?>");
            }
        }
        return new TrimMetrics(itemsTrimmed, dataTrimmed);
    }
}
