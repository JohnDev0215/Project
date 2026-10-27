package com.bugsnag.android;

import com.bugsnag.android.JsonStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FeatureFlags.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0011¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016J\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0006\u0010\u000e\u001a\u00020\u0000R\u0014\u0010\u000f\u001a\u00020\u00038\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R(\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00118\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/bugsnag/android/FeatureFlags;", "Lcom/bugsnag/android/JsonStream$Streamable;", "", "", "name", "variant", "", "addFeatureFlag", "Lcom/bugsnag/android/JsonStream;", "stream", "toStream", "", "Lcom/bugsnag/android/FeatureFlag;", "toList", "copy", "emptyVariant", "Ljava/lang/String;", "", "store", "Ljava/util/Map;", "getStore$bugsnag_android_core_release", "()Ljava/util/Map;", "<init>", "(Ljava/util/Map;)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class FeatureFlags implements JsonStream.Streamable {
    private final String emptyVariant;
    private final Map<String, String> store;

    public FeatureFlags() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public FeatureFlags(Map<String, String> map) {
        this.store = map;
        this.emptyVariant = "__EMPTY_VARIANT_SENTINEL__";
    }

    public /* synthetic */ FeatureFlags(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new LinkedHashMap() : map);
    }

    public synchronized void addFeatureFlag(String name, String variant) {
        this.store.remove(name);
        Map<String, String> map = this.store;
        if (variant == null) {
            variant = this.emptyVariant;
        }
        map.put(name, variant);
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(JsonStream stream) throws IOException {
        Map map;
        synchronized (this) {
            map = MapsKt__MapsKt.toMap(this.store);
        }
        stream.beginArray();
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            stream.beginObject();
            stream.name("featureFlag").value(str);
            if (!kotlin.jvm.internal.Intrinsics.areEqual(str2, this.emptyVariant)) {
                stream.name("variant").value(str2);
            }
            stream.endObject();
        }
        stream.endArray();
    }

    public final synchronized List<FeatureFlag> toList() {
        ArrayList arrayList;
        Set<Map.Entry<String, String>> setEntrySet = this.store.entrySet();
        arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(setEntrySet, 10));
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (kotlin.jvm.internal.Intrinsics.areEqual(str2, this.emptyVariant)) {
                str2 = null;
            }
            arrayList.add(new FeatureFlag(str, str2));
        }
        return arrayList;
    }

    public final synchronized FeatureFlags copy() {
        return new FeatureFlags(MapsKt__MapsKt.toMutableMap(this.store));
    }
}
