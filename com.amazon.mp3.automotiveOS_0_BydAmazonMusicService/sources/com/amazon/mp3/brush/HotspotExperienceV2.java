package com.amazon.mp3.brush;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.capability.TooltipMetadata;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.HotspotTooltipModel;
import com.amazon.music.views.library.models.base.HotspotSupportedModel;
import com.amazon.music.views.library.utils.HotSpotLocation;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: HotspotExperienceV2.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J \u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0018\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0014H\u0002J\u0016\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/brush/HotspotExperienceV2;", "", "Landroid/content/Context;", "context", "", "hotspotTag", "Lcom/amazon/mp3/capability/TooltipMetadata;", "getHotspotData", "", "getTooltipMetadata", "Landroid/content/SharedPreferences;", "prefs", "hotspotCacheStateKey", "", "canPresentHotSpot", "canPresentExperience", "", "incrementNumHotSpotViews", "", "getNumHotSpotViews", "Lcom/amazon/music/views/library/utils/HotSpotLocation;", "determineHotSpotLocation", "hotspotLocation", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "Lcom/amazon/music/views/library/models/base/HotspotSupportedModel;", "model", "addHotspot", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class HotspotExperienceV2 {
    public static final HotspotExperienceV2 INSTANCE = new HotspotExperienceV2();

    /* JADX INFO: compiled from: HotspotExperienceV2.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HotSpotLocation.values().length];
            iArr[HotSpotLocation.hotspot_on_demand_playlists.ordinal()] = 1;
            iArr[HotSpotLocation.hotspot_on_demand_playlist_tracks.ordinal()] = 2;
            iArr[HotSpotLocation.hotspot_search_page_top_results_ondemand_widget.ordinal()] = 3;
            iArr[HotSpotLocation.hotspot_search_page_top_results_shuffle_track.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private HotspotExperienceV2() {
    }

    public final void addHotspot(String hotspotTag, HotspotSupportedModel model) {
        Intrinsics.checkNotNullParameter(hotspotTag, "hotspotTag");
        Intrinsics.checkNotNullParameter(model, "model");
        if (FMPMFeatureGating.IS_HOTSPOT_REFACTOR_ENABLED.isDisabled()) {
            return;
        }
        Context context = AmazonApplication.getApplication().getApplicationContext();
        SharedPreferences prefs = SettingsUtil.getPrefs(context);
        Intrinsics.checkNotNullExpressionValue(context, "context");
        TooltipMetadata hotspotData = getHotspotData(context, hotspotTag);
        HotSpotLocation hotSpotLocationDetermineHotSpotLocation = determineHotSpotLocation(hotspotTag);
        if (hotspotData == null || hotSpotLocationDetermineHotSpotLocation == null) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(prefs, "prefs");
        if (canPresentHotSpot(prefs, hotspotData.getCacheStateKey(), hotspotData.getTagIdentifier())) {
            model.setHotspotModel(new HotspotTooltipModel(hotspotData.getTitle(), hotspotData.getText(), hotspotData.getButtonText(), hotspotData.getTagIdentifier(), getPageType(hotSpotLocationDetermineHotSpotLocation)));
            incrementNumHotSpotViews(prefs, hotspotData.getCacheStateKey());
        }
    }

    private final TooltipMetadata getHotspotData(Context context, String hotspotTag) {
        for (TooltipMetadata tooltipMetadata : getTooltipMetadata(context)) {
            if (Intrinsics.areEqual(tooltipMetadata.getTagIdentifier(), hotspotTag)) {
                return tooltipMetadata;
            }
        }
        return null;
    }

    private final List<TooltipMetadata> getTooltipMetadata(Context context) {
        List<TooltipMetadata> tooltipMetadataList = AmazonApplication.getCapabilities().getTooltipConfigurations(context);
        Intrinsics.checkNotNullExpressionValue(tooltipMetadataList, "tooltipMetadataList");
        return tooltipMetadataList;
    }

    private final boolean canPresentHotSpot(SharedPreferences prefs, String hotspotCacheStateKey, String hotspotTag) {
        return canPresentExperience(hotspotTag, prefs) && getNumHotSpotViews(prefs, hotspotCacheStateKey) <= 3;
    }

    private final boolean canPresentExperience(String hotspotTag, SharedPreferences prefs) {
        return !prefs.getBoolean(hotspotTag, false);
    }

    private final void incrementNumHotSpotViews(SharedPreferences prefs, String hotspotCacheStateKey) {
        int numHotSpotViews = getNumHotSpotViews(prefs, hotspotCacheStateKey) + 1;
        SharedPreferences.Editor editorEdit = prefs.edit();
        editorEdit.putInt(hotspotCacheStateKey, numHotSpotViews);
        editorEdit.apply();
    }

    private final int getNumHotSpotViews(SharedPreferences prefs, String hotspotCacheStateKey) {
        return prefs.getInt(hotspotCacheStateKey, 0);
    }

    private final HotSpotLocation determineHotSpotLocation(String hotspotTag) {
        try {
            return HotSpotLocation.valueOf(StringsKt__StringsJVMKt.replace$default(hotspotTag, '-', '_', false, 4, (Object) null));
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private final PageType getPageType(HotSpotLocation hotspotLocation) {
        if (hotspotLocation == null) {
            return PageType.UNKNOWN;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[hotspotLocation.ordinal()];
        if (i == 1 || i == 2) {
            return PageType.BROWSE_HOME;
        }
        if (i == 3 || i == 4) {
            return PageType.SEARCH_RESULTS;
        }
        return PageType.UNKNOWN;
    }
}
