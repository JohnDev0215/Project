package com.amazon.music.featuregating.weblabs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;

/* JADX INFO: compiled from: WeblabConfigurationReader.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabConfigurationReader;", "", "()V", "read", "", "Lcom/amazon/music/featuregating/weblabs/WeblabDescriptor;", "jsonString", "", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class WeblabConfigurationReader {
    public static final WeblabConfigurationReader INSTANCE = new WeblabConfigurationReader();

    private WeblabConfigurationReader() {
    }

    public final List<WeblabDescriptor> read(String jsonString) {
        Intrinsics.checkNotNullParameter(jsonString, "jsonString");
        List<WeblabStringDescriptor> weblabs = ((Weblabs) Json.INSTANCE.decodeFromString(new WeblabConfigurationSerializer(), jsonString)).getWeblabs();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(weblabs, 10));
        Iterator<T> it = weblabs.iterator();
        while (it.hasNext()) {
            arrayList.add(((WeblabStringDescriptor) it.next()).getWeblabDescriptor());
        }
        return arrayList;
    }
}
