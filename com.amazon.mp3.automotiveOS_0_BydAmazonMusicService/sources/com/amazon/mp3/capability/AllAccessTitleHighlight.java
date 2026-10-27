package com.amazon.mp3.capability;

import com.amazon.mp3.util.Log;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;

/* JADX INFO: compiled from: AllAccessTitleHighlight.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u001b\u0010\u001cJ&\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0002J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f2\u0006\u0010\u0005\u001a\u00020\u0004R\u0017\u0010\u0012\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001f\u0010\u0017\u001a\n \u0016*\u0004\u0018\u00010\u00040\u00048\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/capability/AllAccessTitleHighlight;", "", "Lkotlinx/serialization/json/JsonArray;", "highlightKeys", "", "title", "", "allAccessTitleHighlightMetadataList", "", "populateHighlightMetadataList", "Lcom/amazon/mp3/capability/AllAccessTitleHighlightMetadata;", "allAccessTitleHighlightMetadata", "", "isAnyFieldNull", "populateHighlightInfo", "", "getHighlightMap", "Lcom/amazon/music/featuregating/configuration/Configuration;", "appConfig", "Lcom/amazon/music/featuregating/configuration/Configuration;", "getAppConfig", "()Lcom/amazon/music/featuregating/configuration/Configuration;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "<init>", "(Lcom/amazon/music/featuregating/configuration/Configuration;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AllAccessTitleHighlight {
    private final String TAG;
    private final Configuration appConfig;

    public AllAccessTitleHighlight(Configuration appConfig) {
        Intrinsics.checkNotNullParameter(appConfig, "appConfig");
        this.appConfig = appConfig;
        this.TAG = AllAccessTitleHighlight.class.getSimpleName();
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final List<String> getHighlightMap(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        ArrayList arrayList = new ArrayList();
        try {
            JsonArray jsonArray = (JsonArray) Configuration.DefaultImpls.value$default(this.appConfig, "all_access_playlists_highlight", Reflection.getOrCreateKotlinClass(JsonArray.class), null, 4, null);
            if (jsonArray != null) {
                populateHighlightMetadataList(jsonArray, title, arrayList);
            }
        } catch (Exception e) {
            Log.error(this.TAG, Intrinsics.stringPlus("Error mapping highlight metadata: ", ExceptionsKt__ExceptionsKt.stackTraceToString(e)));
        }
        return arrayList;
    }

    private final void populateHighlightMetadataList(JsonArray highlightKeys, String title, List<String> allAccessTitleHighlightMetadataList) {
        for (JsonElement jsonElement : highlightKeys) {
            try {
                AllAccessTitleHighlightMetadata it = (AllAccessTitleHighlightMetadata) new Gson().fromJson(jsonElement.toString(), AllAccessTitleHighlightMetadata.class);
                Intrinsics.checkNotNullExpressionValue(it, "it");
                if (isAnyFieldNull(it) || !populateHighlightInfo(it)) {
                    Log.error(getTAG(), "One or more fields in highlight metadata arcus config is null");
                } else {
                    List<String> list = it.getHighlightMapping().get(title);
                    if (list != null) {
                        allAccessTitleHighlightMetadataList.addAll(list);
                    }
                }
            } catch (Exception e) {
                Log.error(this.TAG, "Unable to map highlight metadata for highlight: " + jsonElement + ", exception: " + ExceptionsKt__ExceptionsKt.stackTraceToString(e));
            }
        }
    }

    private final boolean isAnyFieldNull(AllAccessTitleHighlightMetadata allAccessTitleHighlightMetadata) {
        return allAccessTitleHighlightMetadata.getTagIdentifier() == null || allAccessTitleHighlightMetadata.getMetadata() == null;
    }

    private final boolean populateHighlightInfo(AllAccessTitleHighlightMetadata allAccessTitleHighlightMetadata) {
        Map<String, Map<String, String>> metadata = allAccessTitleHighlightMetadata.getMetadata();
        allAccessTitleHighlightMetadata.setHighlightMapping(new LinkedHashMap());
        for (Map.Entry<String, Map<String, String>> entry : metadata.entrySet()) {
            String str = entry.getValue().get("highlight");
            if (str != null) {
                allAccessTitleHighlightMetadata.getHighlightMapping().put(String.valueOf(entry.getValue().get("title")), StringsKt__StringsKt.split$default((CharSequence) str, new String[]{Attributes.PREDEFINED_ATTRIBUTE_PREFIX}, false, 0, 6, (Object) null));
            }
        }
        return true;
    }
}
