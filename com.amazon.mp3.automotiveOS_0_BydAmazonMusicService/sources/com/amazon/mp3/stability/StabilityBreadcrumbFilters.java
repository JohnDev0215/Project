package com.amazon.mp3.stability;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: StabilityBreadcrumbFilters.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/stability/StabilityBreadcrumbFilters;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StabilityBreadcrumbFilters {
    private static final Set<String> LOCAL_PLAYBACK_TERMINATED_ALLOWED_ATTRIBUTES;
    private static final Map<String, Set<String>> METRIC_ATTRIBUTE_ALLOW_LIST;
    private static final Set<String> SHARED_STREAMING_TERMINATED_ALLOWED_ATTRIBUTES;
    private static final Set<String> STREAMING_TERMINATED_ALLOWED_ATTRIBUTES;
    private static final Set<String> UI_CLICK_ALLOWED_ATTRIBUTES;
    private static final Set<String> UI_PAGE_VIEW_ALLOWED_ATTRIBUTES;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<String> METRIC_ALLOW_LIST = SetsKt__SetsKt.setOf((Object[]) new String[]{"streamingTerminated", "uiPageView", "uiClick", "localPlaybackTerminated"});

    /* JADX INFO: compiled from: StabilityBreadcrumbFilters.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R)\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/stability/StabilityBreadcrumbFilters$Companion;", "", "", "", "METRIC_ALLOW_LIST", "Ljava/util/Set;", "getMETRIC_ALLOW_LIST", "()Ljava/util/Set;", "", "METRIC_ATTRIBUTE_ALLOW_LIST", "Ljava/util/Map;", "getMETRIC_ATTRIBUTE_ALLOW_LIST", "()Ljava/util/Map;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getMETRIC_ALLOW_LIST() {
            return StabilityBreadcrumbFilters.METRIC_ALLOW_LIST;
        }

        public final Map<String, Set<String>> getMETRIC_ATTRIBUTE_ALLOW_LIST() {
            return StabilityBreadcrumbFilters.METRIC_ATTRIBUTE_ALLOW_LIST;
        }
    }

    static {
        Set<String> of = SetsKt__SetsKt.setOf((Object[]) new String[]{"isCustomerSelectedEntity", "isOnDemandQueue", Splash.PARAMS_LOCALE, "mediaPlayerName", "terminationReason", "contentSubscriptionMode", "durationSeconds", "isDirectedPlay", "isShufflePlay", "pageType", PageUriUtils.SOURCE_QUERY_PARAM, "streamOrDRMTech", "subContentSubscriptionMode", "isPurchased", "scanAndImport", "asin", "isAlexaTtsPlayed", "isVoiceInitiated", "selectionSourceId", "selectionSourceSessionId", "selectionSourceSubType", "selectionSourceType"});
        SHARED_STREAMING_TERMINATED_ALLOWED_ATTRIBUTES = of;
        Set<String> setUnion = CollectionsKt___CollectionsKt.union(SetsKt__SetsKt.setOf((Object[]) new String[]{"resourceType", "transferSpeedBPS"}), of);
        STREAMING_TERMINATED_ALLOWED_ATTRIBUTES = setUnion;
        LOCAL_PLAYBACK_TERMINATED_ALLOWED_ATTRIBUTES = of;
        Set<String> of2 = SetsKt__SetsKt.setOf((Object[]) new String[]{"pageSubType", "refMarker", ContentUtils.KEY_SEARCH_QUERY, "pageType", "actionType", "interactionType", ContextMappingConstants.ENTITY_ID, ContextMappingConstants.ENTITY_ID_TYPE, "entityPos", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "pageEntityId", "pageEntityIdType", "pageEntityType"});
        UI_CLICK_ALLOWED_ATTRIBUTES = of2;
        Set<String> of3 = SetsKt__SetsKt.setOf((Object[]) new String[]{"associateTag", "detailPageItemId", "detailPageItemIdType", "loadTimeMilliseconds", Splash.PARAMS_LOCALE, "pageSubType", "refMarker", "referer", ContentUtils.KEY_SEARCH_QUERY, "pageType"});
        UI_PAGE_VIEW_ALLOWED_ATTRIBUTES = of3;
        METRIC_ATTRIBUTE_ALLOW_LIST = MapsKt__MapsKt.mapOf(TuplesKt.to("streamingTerminated", setUnion), TuplesKt.to("localPlaybackTerminated", of), TuplesKt.to("uiPageView", of3), TuplesKt.to("uiClick", of2));
    }
}
