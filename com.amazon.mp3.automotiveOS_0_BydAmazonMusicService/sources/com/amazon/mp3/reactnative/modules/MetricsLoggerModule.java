package com.amazon.mp3.reactnative.modules;

import android.util.Log;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyDuration;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.metrics.event.UIClickWrapper;
import com.amazon.music.platform.metrics.event.UIContentViewWrapper;
import com.amazon.music.platform.metrics.event.UIPageViewWrapper;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import kotlin.text.StringsKt__StringNumberConversionsKt;

/* JADX INFO: compiled from: MetricsLoggerModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\u0010$\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 [2\u00020\u0001:\u0001[B\u0011\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004Jb\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000eH\u0007J¤\u0001\u0010\u0017\u001a\u00020\f2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#H\u0007J\u0010\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020#H\u0007Jl\u0010&\u001a\u00020\f2\b\u0010'\u001a\u0004\u0018\u00010\u000e2\b\u0010(\u001a\u0004\u0018\u00010\u000e2\b\u0010)\u001a\u0004\u0018\u00010\u000e2\b\u0010*\u001a\u0004\u0018\u00010\u000e2\b\u0010+\u001a\u0004\u0018\u00010\u000e2\b\u0010,\u001a\u0004\u0018\u00010\u000e2\b\u0010-\u001a\u0004\u0018\u00010\u000e2\b\u0010.\u001a\u0004\u0018\u00010\u000e2\b\u0010/\u001a\u0004\u0018\u00010\u000e2\b\u00100\u001a\u0004\u0018\u00010\u000eH\u0007J¢\u0002\u00101\u001a\u00020\f2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u000e2\u001c\b\u0002\u00105\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e07\u0018\u0001062\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010@\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#H\u0007J\u0094\u0001\u0010A\u001a\u00020\f2\b\u00103\u001a\u0004\u0018\u00010\u000e2\b\u0010B\u001a\u0004\u0018\u00010\u000e2\b\u0010C\u001a\u0004\u0018\u00010\u000e2\b\u0010D\u001a\u0004\u0018\u00010\u000e2\b\u0010E\u001a\u0004\u0018\u00010\u000e2\b\u0010F\u001a\u0004\u0018\u00010\u000e2\b\u0010G\u001a\u0004\u0018\u00010\u000e2\b\u0010H\u001a\u0004\u0018\u00010\u000e2\b\u0010I\u001a\u0004\u0018\u00010\u000e2\b\u0010;\u001a\u0004\u0018\u00010\u000e2\b\u0010<\u001a\u0004\u0018\u00010\u000e2\b\u0010=\u001a\u0004\u0018\u00010\u000e2\b\u0010>\u001a\u0004\u0018\u00010\u000e2\b\u0010?\u001a\u0004\u0018\u00010\u000eH\u0007J¼\u0001\u0010J\u001a\u00020\f2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010K\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#H\u0007J¨\u0001\u0010L\u001a\u00020\f2\b\u0010,\u001a\u0004\u0018\u00010\u000e2\b\u0010-\u001a\u0004\u0018\u00010\u000e2\b\u0010M\u001a\u0004\u0018\u00010\u000e2\b\u0010N\u001a\u0004\u0018\u00010\u000e2\b\u0010+\u001a\u0004\u0018\u00010\u000e2\b\u0010O\u001a\u0004\u0018\u00010\u000e2\b\u0010P\u001a\u0004\u0018\u00010\u000e2\b\u00103\u001a\u0004\u0018\u00010\u000e2\b\u0010Q\u001a\u0004\u0018\u00010\u000e2\b\u0010=\u001a\u0004\u0018\u00010\u000e2\b\u0010>\u001a\u0004\u0018\u00010\u000e2\b\u0010?\u001a\u0004\u0018\u00010\u000e2\b\u0010R\u001a\u0004\u0018\u00010\u000e2\b\u00102\u001a\u0004\u0018\u00010\u000e2\b\u0010S\u001a\u0004\u0018\u00010\u000e2\b\u0010T\u001a\u0004\u0018\u00010\u000eH\u0007Jì\u0001\u0010U\u001a\u00020\f2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010T\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010V\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u0010W\u001a\n\u0012\u0004\u0012\u00020X\u0018\u0001062\b\u0010Y\u001a\u0004\u0018\u00010#H\u0002J\b\u0010Z\u001a\u00020\u000eH\u0016R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\\"}, d2 = {"Lcom/amazon/mp3/reactnative/modules/MetricsLoggerModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "metricsProvider", "Lcom/amazon/music/platform/providers/MetricsProvider;", "getMetricsProvider", "()Lcom/amazon/music/platform/providers/MetricsProvider;", "metricsProvider$delegate", "Lkotlin/Lazy;", "emitFlexEvent", "", "eventName", "", "flexStr1", "flexStr2", "flexStr3", "flexStr4", "flexNumber1", "flexNumber2", "flexNumber3", "flexNumber4", "emitFlexEventHelper", "flexEventName", "flexHcStr1", "flexHcStr2", "flexHcStr3", "flexHcStr4", "flexNum1", "flexNum2", "flexNum3", "flexNum4", "emitFlexEventV2", "data", "Lcom/facebook/react/bridge/ReadableMap;", "emitLatencyInfoV2", "latencyInfo", "emitUIClickEvent", "actionType", ContextMappingConstants.ENTITY_ID, ContextMappingConstants.ENTITY_ID_TYPE, PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "refMarker", "pageType", "pageSubType", "pageEntityId", "pageEntityIdType", "pageEntityType", "emitUIClickEventHelper", ImagesContract.URL, "blockRef", "interactionType", ContextMappingConstants.CONTENT_INFO, "", "", "entityPos", "experienceMode", "localContentId", ContentUtils.KEY_ACTIVITY_ID, ContentUtils.KEY_ACTIVITY_ID_TYPE, ContentUtils.KEY_RESPONSE_ID, ContentUtils.KEY_RESPONSE_ID_TYPE, ContentUtils.KEY_SEARCH_QUERY, "emitUIClickEventV2", "emitUIContentViewEvent", "contentName", "containerType", "containerIndex", "numItemsAccessible", "emittedOnView", "firstViewableIndex", "lastViewableIndex", "impressionTimestamp", "emitUIContentViewEventHelper", "emitUIContentViewEventV2", "emitUIPageViewEvent", "detailPageItemId", "detailPageItemIdType", "associateTag", "loadTimeMilliseconds", "viewType", Splash.PARAMS_LOCALE, "userAgent", "csSessionId", "emitUIPageViewEventHelper", "emitUIPageViewEventV2", "formatPieceDurations", "Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyDuration;", "pieceDurations", "getName", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MetricsLoggerModule extends ReactContextBaseJavaModule {

    /* JADX INFO: renamed from: metricsProvider$delegate, reason: from kotlin metadata */
    private final Lazy metricsProvider;
    private static final String TAG = MetricsLoggerModule.class.getSimpleName();
    private static final Map<String, LatencyTrackingLeg> LATENCY_LEGS = MapsKt__MapsKt.mapOf(TuplesKt.to(AsmString.METHOD_FIREBASE_PERF_TRACE_START, LatencyTrackingLeg.START), TuplesKt.to("init", LatencyTrackingLeg.INITIALIZING), TuplesKt.to("request", LatencyTrackingLeg.REQUEST), TuplesKt.to("response", LatencyTrackingLeg.RESPONSE), TuplesKt.to("processing", LatencyTrackingLeg.PROCESSING), TuplesKt.to("rendering", LatencyTrackingLeg.RENDERING));

    public MetricsLoggerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.metricsProvider = LazyKt__LazyJVMKt.lazy(new Function0<MetricsProvider>() { // from class: com.amazon.mp3.reactnative.modules.MetricsLoggerModule$metricsProvider$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MetricsProvider invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof MetricsProvider)) {
                    interfaceProvider = null;
                }
                return (MetricsProvider) interfaceProvider;
            }
        });
    }

    private final MetricsProvider getMetricsProvider() {
        return (MetricsProvider) this.metricsProvider.getValue();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void emitUIClickEventHelper$default(MetricsLoggerModule metricsLoggerModule, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, List list, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, int i, Object obj) {
        metricsLoggerModule.emitUIClickEventHelper((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7, (i & 128) != 0 ? null : str8, (i & 256) != 0 ? null : str9, (i & 512) != 0 ? null : list, (i & 1024) != 0 ? null : str10, (i & 2048) != 0 ? null : str11, (i & 4096) != 0 ? null : str12, (i & 8192) != 0 ? null : str13, (i & 16384) != 0 ? null : str14, (i & 32768) != 0 ? null : str15, (i & 65536) != 0 ? null : str16, (i & 131072) != 0 ? null : str17, (i & 262144) != 0 ? null : str18, (i & 524288) != 0 ? null : str19, (i & 1048576) != 0 ? null : str20, (i & 2097152) != 0 ? null : str21);
    }

    private final void emitUIClickEventHelper(String actionType, String url, String blockRef, String interactionType, String pageType, String pageSubType, String refMarker, String entityId, String entityIdType, List<? extends Map<String, String>> contentInfo, String entityType, String entityPos, String experienceMode, String localContentId, String activityId, String activityIdType, String responseId, String responseIdType, String searchQuery, String pageEntityId, String pageEntityIdType, String pageEntityType) {
        UIClickWrapper uIClickWrapperBuild = new UIClickWrapper.Builder().withActionType(actionType).withUrl(url).withBlockRef(blockRef).withInteractionType(interactionType).withEntityId(entityId).withEntityIdType(entityIdType).withContentInfo(contentInfo).withEntityType(entityType).withEntityPos(entityPos == null ? null : StringsKt__StringNumberConversionsKt.toIntOrNull(entityPos)).withExperienceMode(experienceMode).withLocalContentId(localContentId).withActivityId(activityId).withActivityIdType(activityIdType).withResponseId(responseId).withResponseIdType(responseIdType).withSearchQuery(searchQuery).withPageType(pageType).withPageSubType(pageSubType).withRefMarker(refMarker).withPageEntityId(pageEntityId).withPageEntityIdType(pageEntityIdType).withPageEntityType(pageEntityType).build();
        MetricsProvider metricsProvider = getMetricsProvider();
        if (metricsProvider == null) {
            return;
        }
        metricsProvider.sendMetricsEvent(uIClickWrapperBuild);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitUIPageViewEventHelper(String pageType, String pageSubType, String detailPageItemId, String detailPageItemIdType, String refMarker, String associateTag, String viewType, String blockRef, String loadTimeMilliseconds, String experienceMode, String activityId, String activityIdType, String responseId, String responseIdType, String searchQuery, String locale, String url, String userAgent, String csSessionId) {
        UIPageViewWrapper uIPageViewWrapperBuild = new UIPageViewWrapper.Builder().withPageType(pageType).withPageSubType(pageSubType).withDetailPageItemId(detailPageItemId).withDetailPageItemIdType(detailPageItemIdType).withExternalReference(refMarker).withAssociateTag(associateTag).withLoadTime(loadTimeMilliseconds == null ? null : StringsKt__StringNumberConversionsKt.toLongOrNull(loadTimeMilliseconds)).withBlockRef(blockRef).withViewType(viewType).withExperienceMode(experienceMode).withActivityId(activityId).withActivityIdType(activityIdType).withResponseId(responseId).withResponseIdType(responseIdType).withSearchQuery(searchQuery).withLocale(locale).withUrl(url).withUserAgent(userAgent).withCsSessionId(csSessionId).build();
        MetricsProvider metricsProvider = getMetricsProvider();
        if (metricsProvider == null) {
            return;
        }
        metricsProvider.sendMetricsEvent(uIPageViewWrapperBuild);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitUIContentViewEventHelper(String blockRef, String contentName, String containerType, String containerIndex, String numItemsAccessible, String emittedOnView, String firstViewableIndex, String lastViewableIndex, String impressionTimestamp, String activityId, String activityIdType, String responseId, String responseIdType, String searchQuery, String pageType) {
        boolean z = false;
        if (emittedOnView != null && Boolean.parseBoolean(emittedOnView)) {
            z = true;
        }
        UIContentViewWrapper uIContentViewWrapperBuild = new UIContentViewWrapper.Builder().withBlockRef(blockRef).withContentName(contentName).withContainerType(containerType).withContainerIndex(containerIndex == null ? null : StringsKt__StringNumberConversionsKt.toLongOrNull(containerIndex)).withNumItemsAccessible(numItemsAccessible == null ? null : StringsKt__StringNumberConversionsKt.toLongOrNull(numItemsAccessible)).withEmittedOnView(Boolean.valueOf(z)).withFirstViewableIndex(firstViewableIndex == null ? null : StringsKt__StringNumberConversionsKt.toLongOrNull(firstViewableIndex)).withLastViewableIndex(lastViewableIndex == null ? null : StringsKt__StringNumberConversionsKt.toLongOrNull(lastViewableIndex)).withImpressionTimestamp(impressionTimestamp != null ? StringsKt__StringNumberConversionsKt.toLongOrNull(impressionTimestamp) : null).withActivityId(activityId).withActivityIdType(activityIdType).withResponseId(responseId).withResponseIdType(responseIdType).withSearchQuery(searchQuery).withPageType(pageType).build();
        MetricsProvider metricsProvider = getMetricsProvider();
        if (metricsProvider == null) {
            return;
        }
        metricsProvider.sendMetricsEvent(uIContentViewWrapperBuild);
    }

    static /* synthetic */ void emitFlexEventHelper$default(MetricsLoggerModule metricsLoggerModule, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, int i, Object obj) {
        metricsLoggerModule.emitFlexEventHelper((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7, (i & 128) != 0 ? null : str8, (i & 256) != 0 ? null : str9, (i & 512) != 0 ? null : str10, (i & 1024) != 0 ? null : str11, (i & 2048) != 0 ? null : str12, (i & 4096) == 0 ? str13 : null);
    }

    private final void emitFlexEventHelper(String flexEventName, String flexStr1, String flexStr2, String flexStr3, String flexStr4, String flexHcStr1, String flexHcStr2, String flexHcStr3, String flexHcStr4, String flexNum1, String flexNum2, String flexNum3, String flexNum4) {
        FlexWrapper flexWrapperBuild = new FlexWrapper.Builder().withName(flexEventName).withStr1(flexStr1).withStr2(flexStr2).withStr3(flexStr3).withStr3(flexStr3).withStr4(flexStr4).withHcStr1(flexHcStr1).withHcStr2(flexHcStr2).withHcStr3(flexHcStr3).withHcStr4(flexHcStr4).withNum1(flexNum1 == null ? null : StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(flexNum1)).withNum2(flexNum2 == null ? null : StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(flexNum2)).withNum3(flexNum3 == null ? null : StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(flexNum3)).withNum4(flexNum4 != null ? StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(flexNum4) : null).build();
        MetricsProvider metricsProvider = getMetricsProvider();
        if (metricsProvider == null) {
            return;
        }
        metricsProvider.sendMetricsEvent(flexWrapperBuild);
    }

    @ReactMethod
    public final void emitUIClickEvent(String actionType, String entityId, String entityIdType, String entityType, String refMarker, String pageType, String pageSubType, String pageEntityId, String pageEntityIdType, String pageEntityType) {
        emitUIClickEventHelper$default(this, actionType, null, null, null, pageType, pageSubType, refMarker, entityId, entityIdType, null, entityType, null, null, null, null, null, null, null, null, pageEntityId, pageEntityIdType, pageEntityType, 522766, null);
    }

    @ReactMethod
    public final void emitUIClickEventV2(ReadableMap data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        String string = data.getString(ContextMappingConstants.CONTENT_SRC);
        if (string != null) {
            arrayList.add(MapsKt__MapsJVMKt.mapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, string)));
        }
        String string2 = data.getString("contentName");
        if (string2 != null) {
            arrayList.add(MapsKt__MapsJVMKt.mapOf(TuplesKt.to("contentName", string2)));
        }
        emitUIClickEventHelper(data.getString("actionType"), data.getString(ImagesContract.URL), data.getString("blockRef"), data.getString("interactionType"), data.getString("pageType"), data.getString("pageSubType"), data.getString("refMarker"), data.getString(ContextMappingConstants.ENTITY_ID), data.getString(ContextMappingConstants.ENTITY_ID_TYPE), arrayList, data.getString(PageUriUtils.ENTITY_TYPE_QUERY_PARAM), data.getString("entityPos"), data.getString("experienceMode"), data.getString("localContentId"), data.getString(ContentUtils.KEY_ACTIVITY_ID), data.getString(ContentUtils.KEY_ACTIVITY_ID_TYPE), data.getString(ContentUtils.KEY_RESPONSE_ID), data.getString(ContentUtils.KEY_RESPONSE_ID_TYPE), data.getString(ContentUtils.KEY_SEARCH_QUERY), data.getString("pageEntityId"), data.getString("pageEntityIdType"), data.getString("pageEntityType"));
    }

    @ReactMethod
    public final void emitUIPageViewEvent(String pageType, String pageSubType, String detailPageItemId, String detailPageItemIdType, String refMarker, String associateTag, String loadTimeMilliseconds, String blockRef, String viewType, String responseId, String responseIdType, String searchQuery, String locale, String url, String userAgent, String csSessionId) {
        emitUIPageViewEventHelper(pageType, pageSubType, detailPageItemId, detailPageItemIdType, refMarker, associateTag, viewType, blockRef, loadTimeMilliseconds, null, null, null, responseId, responseIdType, searchQuery, locale, url, userAgent, csSessionId);
    }

    @ReactMethod
    public final void emitUIPageViewEventV2(ReadableMap data) {
        Intrinsics.checkNotNullParameter(data, "data");
        emitUIPageViewEventHelper(data.getString("pageType"), data.getString("pageSubType"), data.getString("detailPageItemId"), data.getString("detailPageItemIdType"), data.getString("refMarker"), data.getString("associateTag"), data.getString("viewType"), data.getString("blockRef"), data.getString("loadTimeMilliseconds"), data.getString("experienceMode"), data.getString(ContentUtils.KEY_ACTIVITY_ID), data.getString(ContentUtils.KEY_ACTIVITY_ID_TYPE), data.getString(ContentUtils.KEY_RESPONSE_ID), data.getString(ContentUtils.KEY_RESPONSE_ID_TYPE), data.getString(ContentUtils.KEY_SEARCH_QUERY), data.getString(Splash.PARAMS_LOCALE), data.getString(ImagesContract.URL), data.getString("userAgent"), data.getString("csSessionId"));
    }

    @ReactMethod
    public final void emitUIContentViewEvent(String blockRef, String contentName, String containerType, String containerIndex, String numItemsAccessible, String emittedOnView, String firstViewableIndex, String lastViewableIndex, String impressionTimestamp, String activityId, String activityIdType, String responseId, String responseIdType, String searchQuery) {
        emitUIContentViewEventHelper(blockRef, contentName, containerType, containerIndex, numItemsAccessible, emittedOnView, firstViewableIndex, lastViewableIndex, impressionTimestamp, activityId, activityIdType, responseId, responseIdType, searchQuery, null);
    }

    @ReactMethod
    public final void emitUIContentViewEventV2(ReadableMap data) {
        Intrinsics.checkNotNullParameter(data, "data");
        emitUIContentViewEventHelper(data.getString("blockRef"), data.getString("contentName"), data.getString("containerType"), data.getString("containerIndex"), data.getString("numItemsAccessible"), data.getString("emittedOnView"), data.getString("firstViewableIndex"), data.getString("lastViewableIndex"), data.getString("impressionTimestamp"), data.getString(ContentUtils.KEY_ACTIVITY_ID), data.getString(ContentUtils.KEY_ACTIVITY_ID_TYPE), data.getString(ContentUtils.KEY_RESPONSE_ID), data.getString(ContentUtils.KEY_RESPONSE_ID_TYPE), data.getString(ContentUtils.KEY_SEARCH_QUERY), data.getString("pageType"));
    }

    @ReactMethod
    public final void emitFlexEvent(String eventName, String flexStr1, String flexStr2, String flexStr3, String flexStr4, String flexNumber1, String flexNumber2, String flexNumber3, String flexNumber4) {
        emitFlexEventHelper$default(this, eventName, flexStr1, flexStr2, flexStr3, flexStr4, null, null, null, null, flexNumber1, flexNumber2, flexNumber3, flexNumber4, 480, null);
    }

    @ReactMethod
    public final void emitFlexEventV2(ReadableMap data) {
        Intrinsics.checkNotNullParameter(data, "data");
        emitFlexEventHelper(data.getString("flexEventName"), data.getString("flexStr1"), data.getString("flexStr2"), data.getString("flexStr3"), data.getString("flexStr4"), data.getString("flexHcStr1"), data.getString("flexHcStr2"), data.getString("flexHcStr3"), data.getString("flexHcStr4"), data.getString("flexNum1"), data.getString("flexNum2"), data.getString("flexNum3"), data.getString("flexNum4"));
    }

    @ReactMethod
    public final void emitLatencyInfoV2(ReadableMap latencyInfo) {
        Intrinsics.checkNotNullParameter(latencyInfo, "latencyInfo");
        LatencyTracker latencyTracker = LatencyTracker.INSTANCE;
        String string = latencyInfo.getString("latencyCode");
        if (string == null) {
            string = "";
        }
        String string2 = latencyInfo.getString("latencyOperation");
        if (string2 == null) {
            string2 = "";
        }
        String string3 = latencyInfo.getString("latencySubtype");
        if (string3 == null) {
            string3 = "";
        }
        latencyTracker.emitLatencyEvent(string, string2, string3, (long) latencyInfo.getDouble("durationMilliseconds"), formatPieceDurations(latencyInfo.getMap("pieceDurations")));
    }

    private final List<LatencyDuration> formatPieceDurations(ReadableMap pieceDurations) {
        if (pieceDurations == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, LatencyTrackingLeg> entry : LATENCY_LEGS.entrySet()) {
            String key = entry.getKey();
            LatencyTrackingLeg value = entry.getValue();
            try {
                arrayList.add(new LatencyDuration(0L, 0L, value, Long.valueOf((long) pieceDurations.getDouble(key)), 3, null));
            } catch (Exception e) {
                Log.e(TAG, Intrinsics.stringPlus("Failed to create LatencyDuration for leg ", value), e);
            }
        }
        return arrayList;
    }
}
