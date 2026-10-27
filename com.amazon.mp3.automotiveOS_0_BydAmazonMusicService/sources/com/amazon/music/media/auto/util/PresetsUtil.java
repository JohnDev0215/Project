package com.amazon.music.media.auto.util;

import android.content.Context;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.auto.feature.AutomotiveFeature;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: PresetsUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u0012\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0002J\"\u0010\f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/media/auto/util/PresetsUtil;", "", "", "jsonString", "filterOutPodcasts", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "featureGateProvider", "", "isPodcastsInPresetsFeatureEnabled", "Landroid/content/Context;", "context", "key", "readSharedPreferences", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class PresetsUtil {
    public static final PresetsUtil INSTANCE = new PresetsUtil();

    private PresetsUtil() {
    }

    public final String readSharedPreferences(Context context, String key, FeatureGateProvider featureGateProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(key, "key");
        String string = context.getSharedPreferences("PresetsPrefs", 0).getString(key, null);
        return isPodcastsInPresetsFeatureEnabled(featureGateProvider) ? string : filterOutPodcasts(string);
    }

    private final String filterOutPodcasts(String jsonString) {
        if (jsonString == null || StringsKt__StringsJVMKt.isBlank(jsonString)) {
            return jsonString;
        }
        List objects = (List) new Gson().fromJson(jsonString, new TypeToken<List<? extends Map<String, ? extends String>>>() { // from class: com.amazon.music.media.auto.util.PresetsUtil$filterOutPodcasts$objects$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(objects, "objects");
        ArrayList arrayList = new ArrayList();
        for (Object obj : objects) {
            if (!Intrinsics.areEqual(((Map) obj).get(PageUriUtils.ENTITY_TYPE_QUERY_PARAM), "PodcastShow")) {
                arrayList.add(obj);
            }
        }
        return new Gson().toJson(arrayList);
    }

    private final boolean isPodcastsInPresetsFeatureEnabled(FeatureGateProvider featureGateProvider) {
        return featureGateProvider != null && AutomotiveFeature.MY_PRESETS_WITH_PODCAST.isEnabled(featureGateProvider);
    }
}
