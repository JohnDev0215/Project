package com.amazon.mp3.capability;

import android.content.Context;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.featuregating.configuration.Configuration;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;

/* JADX INFO: compiled from: TooltipConfig.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0017\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J$\u0010\n\u001a\u00020\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001f\u0010\u0018\u001a\n \u0017*\u0004\u0018\u00010\b0\b8\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/capability/TooltipConfig;", "", "Lcom/amazon/mp3/capability/TooltipMetadata;", "tooltipMetadata", "", "isAnyFieldNull", "populateToolTipInfo", "", "", "tooltipInfo", "validateAndPopulateTooltipMetadata", "", "getHotspots", "Lcom/amazon/music/featuregating/configuration/Configuration;", "appConfig", "Lcom/amazon/music/featuregating/configuration/Configuration;", "getAppConfig", "()Lcom/amazon/music/featuregating/configuration/Configuration;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "<init>", "(Lcom/amazon/music/featuregating/configuration/Configuration;Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class TooltipConfig {
    private final String TAG;
    private final Configuration appConfig;
    private final Context context;

    public TooltipConfig(Configuration appConfig, Context context) {
        Intrinsics.checkNotNullParameter(appConfig, "appConfig");
        Intrinsics.checkNotNullParameter(context, "context");
        this.appConfig = appConfig;
        this.context = context;
        this.TAG = TooltipConfig.class.getSimpleName();
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final List<TooltipMetadata> getHotspots() {
        ArrayList arrayList = new ArrayList();
        try {
            JsonArray jsonArray = (JsonArray) Configuration.DefaultImpls.value$default(this.appConfig, "hotspots", Reflection.getOrCreateKotlinClass(JsonArray.class), null, 4, null);
            if (jsonArray != null) {
                for (JsonElement jsonElement : jsonArray) {
                    try {
                        TooltipMetadata hotspotMetadata = (TooltipMetadata) new Gson().fromJson(jsonElement.toString(), TooltipMetadata.class);
                        Intrinsics.checkNotNullExpressionValue(hotspotMetadata, "it");
                        if (isAnyFieldNull(hotspotMetadata) || !populateToolTipInfo(hotspotMetadata)) {
                            Log.error(getTAG(), "One or more fields in hotspot metadata arcus config is null");
                        } else {
                            Intrinsics.checkNotNullExpressionValue(hotspotMetadata, "hotspotMetadata");
                            arrayList.add(hotspotMetadata);
                        }
                    } catch (Exception e) {
                        Log.error(getTAG(), "Unable to map tooltip metadata for hotspot: " + jsonElement + ", exception: " + ExceptionsKt__ExceptionsKt.stackTraceToString(e));
                    }
                }
            }
        } catch (Exception e2) {
            Log.error(this.TAG, Intrinsics.stringPlus("Error mapping tooltip metadata: ", ExceptionsKt__ExceptionsKt.stackTraceToString(e2)));
        }
        return arrayList;
    }

    private final boolean isAnyFieldNull(TooltipMetadata tooltipMetadata) {
        return tooltipMetadata.getTagIdentifier() == null || tooltipMetadata.getMetadata() == null || tooltipMetadata.getCacheStateKey() == null;
    }

    private final boolean populateToolTipInfo(TooltipMetadata tooltipMetadata) {
        Locale locale = new BrowseLanguageProvider().getLocale(this.context);
        Intrinsics.checkNotNullExpressionValue(locale, "languageProvider.getLocale(context)");
        Map<String, Map<String, String>> metadata = tooltipMetadata.getMetadata();
        Map<String, String> map = metadata.get(locale.toString());
        if (map != null && validateAndPopulateTooltipMetadata(map, tooltipMetadata)) {
            return true;
        }
        Map<String, String> map2 = metadata.get("default");
        return map2 != null && validateAndPopulateTooltipMetadata(map2, tooltipMetadata);
    }

    private final boolean validateAndPopulateTooltipMetadata(Map<String, String> tooltipInfo, TooltipMetadata tooltipMetadata) {
        if (tooltipInfo.get("title") == null || tooltipInfo.get("text") == null || tooltipInfo.get("buttonText") == null) {
            return false;
        }
        tooltipMetadata.setTitle(String.valueOf(tooltipInfo.get("title")));
        tooltipMetadata.setText(String.valueOf(tooltipInfo.get("text")));
        tooltipMetadata.setButtonText(String.valueOf(tooltipInfo.get("buttonText")));
        return true;
    }
}
