package com.amazon.music.featuregating.configuration;

import com.amazon.music.featuregating.configuration.dataclasses.CustomRegions;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: compiled from: AppConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0002¨\u0006\u0004"}, d2 = {"merged", "", "Lcom/amazon/music/featuregating/configuration/dataclasses/CustomRegions;", "other", "MPFeatureGating_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class AppConfigurationKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final List<CustomRegions> merged(List<CustomRegions> list, List<CustomRegions> list2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (CustomRegions customRegions : list) {
            linkedHashMap.put(customRegions.getValue(), customRegions.getRegions());
        }
        for (CustomRegions customRegions2 : list2) {
            linkedHashMap.put(customRegions2.getValue(), customRegions2.getRegions());
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList.add(new CustomRegions((Set) entry.getValue(), (String) entry.getKey()));
        }
        return arrayList;
    }
}
