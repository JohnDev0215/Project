package com.amazon.mp3.service.metrics;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.service.error.CirrusError;
import com.amazon.mp3.service.metrics.UxMetricsLogger;
import com.amazon.mp3.service.metrics.mts.ContentSubscriptionModeHelper;
import com.amazon.mp3.service.metrics.mts.MusicClientInfoProvider;
import com.amazon.mp3.service.metrics.mts.MusicDeviceInfoProvider;
import com.amazon.mp3.service.metrics.mts.MusicEventDataProvider;
import com.amazon.mp3.service.metrics.mts.events.ReportCirrusErrorEvent;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.curate.provider.EntityType;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.android.MTSDebugMode;
import com.amazon.music.metrics.mts.android.MetricsFilePathUtil;
import com.amazon.music.metrics.mts.android.StorageNotAvailableException;
import com.amazon.music.metrics.mts.event.definition.clientinfo.ClientInfoEvent;
import com.amazon.music.metrics.mts.event.definition.deeplink.DeeplinkInteractionEvent;
import com.amazon.music.metrics.mts.event.definition.deluxe.DeluxeBookletReadingInitiatedEvent;
import com.amazon.music.metrics.mts.event.definition.deluxe.DeluxeBookletReadingTerminatedEvent;
import com.amazon.music.metrics.mts.event.definition.deluxe.DeluxeVideoStreamingInitiatedEvent;
import com.amazon.music.metrics.mts.event.definition.deluxe.DeluxeVideoStreamingTerminatedEvent;
import com.amazon.music.metrics.mts.event.definition.deviceInfo.DeviceInfoEvent;
import com.amazon.music.metrics.mts.event.definition.download.LocalDownloadRefusedEvent;
import com.amazon.music.metrics.mts.event.definition.download.PlaylistDownloadedEvent;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.definition.lyrics.LyricsViewedNowPlayingEvent;
import com.amazon.music.metrics.mts.event.definition.prime.ClickedAddContentEvent;
import com.amazon.music.metrics.mts.event.definition.prime.PrimeConcurrentMusicDialogEvent;
import com.amazon.music.metrics.mts.event.definition.prime.PrimeConcurrentMusicResponseEvent;
import com.amazon.music.metrics.mts.event.definition.refer.InvalidReferrerException;
import com.amazon.music.metrics.mts.event.definition.refer.ReferredByExternalAppEvent;
import com.amazon.music.metrics.mts.event.definition.store.Campaign;
import com.amazon.music.metrics.mts.event.definition.store.ClickedOnCampaignEvent;
import com.amazon.music.metrics.mts.event.definition.store.ClickedOnStoreLinkEvent;
import com.amazon.music.metrics.mts.event.definition.store.PageLoadedEvent;
import com.amazon.music.metrics.mts.event.definition.sync.IncrementalSyncFinishedEvent;
import com.amazon.music.metrics.mts.event.definition.sync.InitialSyncFinishedEvent;
import com.amazon.music.metrics.mts.event.definition.thirdPartyVoice.ThirdPartyVoiceSearchEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.definition.upsell.ConcurrencyUpsellOneClickViewEvent;
import com.amazon.music.metrics.mts.event.definition.upsell.ConcurrencyUpsellViewEvent;
import com.amazon.music.metrics.mts.event.definition.upsell.EligibilityCheckRequestedEvent;
import com.amazon.music.metrics.mts.event.exception.CirrusInvalidDataException;
import com.amazon.music.metrics.mts.event.types.LinkType;
import com.amazon.music.metrics.mts.event.types.LyricsViewType;
import com.amazon.music.metrics.mts.event.types.ResourceType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.StoreLinkType;
import com.amazon.music.metrics.mts.event.types.StorePageType;
import com.amazon.music.metrics.mts.event.types.StreamingConcurrencyResponse;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ViewType;
import com.amazon.music.platform.data.subscription.NoSubscriptionTracking;
import com.amazon.music.subscription.BrowseMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public final class MetricsLogger {
    private static final long NANO_SECONDS_PER_HALF_MILLISECOND;
    private static final long NANO_SECONDS_PER_MILLISECOND;
    private static final String TAG = "MetricsLogger";
    private static MTSDebugMode sDebugMode;
    private static long sDeluxeBookletStartTimeNanoSeconds;
    private static long sDeluxeVideoStartTimeNanoSeconds;
    private static HashMap<StorePageType, Long> sPageLoadStartTimes;

    static {
        long nanos = TimeUnit.MILLISECONDS.toNanos(1L);
        NANO_SECONDS_PER_MILLISECOND = nanos;
        NANO_SECONDS_PER_HALF_MILLISECOND = nanos / 2;
        sDeluxeBookletStartTimeNanoSeconds = -1L;
        sDeluxeVideoStartTimeNanoSeconds = -1L;
        sPageLoadStartTimes = new HashMap<>();
    }

    private MetricsLogger() {
    }

    public static void clickedOnStoreLink(StoreLinkType linkType) {
        sendEvent(new ClickedOnStoreLinkEvent(linkType));
        UxMetricsLogger.registerUserAction(UxMetricsLogger.UserAction.LIBRARY_TO_STORE_SEARCH);
    }

    public static void clientInfo(final Context context, final String appIdentity, final boolean isAppColdStart, final boolean areNotificationsEnabled) {
        ClientInfoEvent clientInfoEvent = new ClientInfoEvent(new MusicClientInfoProvider(context, isAppColdStart), appIdentity, null, Boolean.valueOf(areNotificationsEnabled));
        addCentralDeviceType(context, clientInfoEvent);
        sendEvent(clientInfoEvent);
    }

    public static void deviceInfo(Context context) {
        sendEvent(new DeviceInfoEvent(new MusicDeviceInfoProvider(context)));
    }

    private static void addCentralDeviceType(final Context context, final ClientInfoEvent event) {
        String centralDeviceType = AccountCredentialStorage.get(context).getCentralDeviceType();
        if (TextUtils.isEmpty(centralDeviceType)) {
            return;
        }
        event.addAttribute("centralDeviceType", centralDeviceType);
    }

    private static long convertToMilliseconds(long nanoseconds) {
        return TimeUnit.NANOSECONDS.toMillis(nanoseconds + NANO_SECONDS_PER_HALF_MILLISECOND);
    }

    public static void incrementalSync(long elapsedNanoseconds) {
        try {
            sendEvent(new IncrementalSyncFinishedEvent(convertToMilliseconds(elapsedNanoseconds)));
        } catch (CirrusInvalidDataException e) {
            sendDataErrorToCirrus(e.getMessage());
        }
    }

    public static void initialSync(long elapsedNanoseconds) {
        try {
            sendEvent(new InitialSyncFinishedEvent(convertToMilliseconds(elapsedNanoseconds)));
            UxMetricsLogger.logMetric(UxMetricsLogger.Metric.FIRST_CLOUD_SYNC, elapsedNanoseconds);
        } catch (CirrusInvalidDataException e) {
            sendDataErrorToCirrus(e.getMessage());
        }
    }

    public static void localDownloadRefused() {
        sendEvent(new LocalDownloadRefusedEvent());
    }

    public static void deluxeBookletReadingInitiated(String asin, String luid) {
        sDeluxeBookletStartTimeNanoSeconds = System.nanoTime();
        sendEvent(new DeluxeBookletReadingInitiatedEvent(luid, asin));
    }

    public static void deluxeBookletReadingCompleted(String asin, String luid) {
        sendEvent(new DeluxeBookletReadingTerminatedEvent(luid, asin, convertToMilliseconds(System.nanoTime() - sDeluxeBookletStartTimeNanoSeconds)));
        sDeluxeBookletStartTimeNanoSeconds = -1L;
    }

    public static void deluxeVideoStreamingInitiated(String asin, String luid) {
        sDeluxeVideoStartTimeNanoSeconds = System.nanoTime();
        sendEvent(new DeluxeVideoStreamingInitiatedEvent(luid, asin));
    }

    public static void deluxeVideoStreamingTerminated(String asin, String luid) {
        sendEvent(new DeluxeVideoStreamingTerminatedEvent(luid, asin, convertToMilliseconds(System.nanoTime() - sDeluxeVideoStartTimeNanoSeconds)));
        sDeluxeVideoStartTimeNanoSeconds = -1L;
    }

    public static void referredByExternalApp(String referrerIdentity) throws InvalidReferrerException {
        sendEvent(new ReferredByExternalAppEvent(referrerIdentity));
    }

    public static void storeSearch(final int msResults) {
        sendEvent(new PageLoadedEvent(StorePageType.STORE_SEARCH, msResults));
    }

    public static void storeHome(final int msResults) {
        sendEvent(new PageLoadedEvent(StorePageType.STORE_HOME, msResults));
    }

    public static void storeAlbumDetail(final int msResults) {
        sendEvent(new PageLoadedEvent(StorePageType.ALBUM_DETAIL, msResults));
    }

    public static void primePlaylistDownloaded(String asin) {
        PlaylistDownloadedEvent playlistDownloadedEvent = new PlaylistDownloadedEvent(asin, retrieveContentSubscriptionMode(), retrieveSubContentSubscriptionMode());
        playlistDownloadedEvent.addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, EntityType.CATALOG_PLAYLIST.name());
        playlistDownloadedEvent.addAttribute(ContextMappingConstants.ENTITY_ID_TYPE, EntityIdType.ASIN.getMetricsValue());
        sendEvent(playlistDownloadedEvent);
    }

    public static void userPlaylistDownloaded(String luid) {
        PlaylistDownloadedEvent playlistDownloadedEvent = new PlaylistDownloadedEvent(luid, retrieveContentSubscriptionMode(), retrieveSubContentSubscriptionMode());
        playlistDownloadedEvent.addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, EntityType.USER_PLAYLIST.name());
        playlistDownloadedEvent.addAttribute(ContextMappingConstants.ENTITY_ID_TYPE, EntityIdType.CD_OBJECT_ID);
        sendEvent(playlistDownloadedEvent);
    }

    public static void purchaseTime(final long msTime) {
        sendEvent(new PageLoadedEvent(StorePageType.PURCHASE_COMPLETED, msTime));
    }

    public static void userClickedonCampaign(final Campaign campaign) {
        sendEvent(new ClickedOnCampaignEvent(campaign));
    }

    public static void webViewStart(StorePageType pageType) {
        sPageLoadStartTimes.put(pageType, Long.valueOf(Calendar.getInstance().getTimeInMillis()));
    }

    public static void webViewFinish(StorePageType pageType) {
        if (sPageLoadStartTimes.containsKey(pageType)) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis() - sPageLoadStartTimes.get(pageType).longValue();
            sPageLoadStartTimes.remove(pageType);
            sendEvent(new PageLoadedEvent(pageType, timeInMillis));
        }
    }

    public static void lyricsViewedFromNowPlaying(final String asin, final boolean isOwned, final LyricsViewType lyricsViewType, final long durationMilliseconds, int charCount) {
        sendEvent(new LyricsViewedNowPlayingEvent(asin, getContentSubscriptionModeForLyricsViewed(isOwned), getSubContentSubscriptionModeForLyricsViewed(isOwned), charCount, lyricsViewType, durationMilliseconds));
    }

    private static String getContentSubscriptionModeForLyricsViewed(final boolean isOwned) {
        return isOwned ? new NoSubscriptionTracking().contentSubscriptionMode() : retrieveContentSubscriptionMode();
    }

    @Nullable
    private static String getSubContentSubscriptionModeForLyricsViewed(final boolean isOwned) {
        return isOwned ? new NoSubscriptionTracking().subContentSubscriptionMode() : retrieveSubContentSubscriptionMode();
    }

    public static void primeConcurrentMusicDialog(String externalDeviceName) {
        sendEvent(new PrimeConcurrentMusicDialogEvent(externalDeviceName));
    }

    public static void primeConcurrentMusicResponse(String externalDeviceName, StreamingConcurrencyResponse response) {
        sendEvent(new PrimeConcurrentMusicResponseEvent(externalDeviceName, response));
    }

    public static void clickedAddPrimeContentFromCatalogPlaylist(LinkType linkType, String trackAsin, String playlistAsin) {
        clickedAddPrimeContent(linkType, trackAsin, new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode().equals(BrowseMode.Mode.PRIME) ? SelectionSourceType.PRIME_PLAYLIST : SelectionSourceType.UNLIMITED_PLAYLIST, playlistAsin);
    }

    public static void clickedAddPrimeContent(LinkType linkType, String asin, SelectionSourceType selectionSourceType, String selectionSourceId) {
        clickedAddPrimeContent(linkType, asin, selectionSourceType, selectionSourceId, ResourceType.CLOUD_PLAYER);
    }

    public static void clickedAddPrimeContent(LinkType linkType, String asin, SelectionSourceType selectionSourceType, String selectionSourceId, ResourceType resourceType) {
        sendEvent(new ClickedAddContentEvent(linkType, asin, new SelectionSourceInfo(selectionSourceType, selectionSourceId), resourceType, retrieveContentSubscriptionMode(), retrieveSubContentSubscriptionMode()));
    }

    public static void sendDataErrorToCirrus(String errorDescription) {
        new ReportCirrusErrorEvent(CirrusError.INVALID_DATA_ERROR, errorDescription).trackEvent();
    }

    public static void sendEvent(MTSEvent event) {
        MetricsHolder.getManager().handleEvent(event);
    }

    private static synchronized MTSDebugMode getDebugMode() {
        if (sDebugMode == null) {
            sDebugMode = new MTSDebugMode();
        }
        return sDebugMode;
    }

    public static void enableDebugLogging(Context context) throws StorageNotAvailableException {
        MTSDebugMode debugMode = getDebugMode();
        debugMode.enableDebugMode(MetricsHolder.getManager(), debugMode.buildMTSDebugHandler(new MusicEventDataProvider(context, AmazonApplication.getCapabilities().getPlatformName()), debugMode.buildDebugFileConfig(MetricsFilePathUtil.getDefaultDebugMetricsPath())));
    }

    public static void sendUiClickMetricForRecentlyPlayedItem(final RecentlyPlayedItem recentlyPlayedItem) {
        sendUiClickMetricForRecentlyPlayedItem(recentlyPlayedItem, null, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.service.metrics.MetricsLogger$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type;

        static {
            int[] iArr = new int[RecentlyPlayedItem.Type.values().length];
            $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type = iArr;
            try {
                iArr[RecentlyPlayedItem.Type.ARTIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PRIME_PLAYLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PLAYLIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.SMART_PLAYLIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.GENRE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PRIME_BROWSE_SONGS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ALL_SONGS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.STATION.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public static void sendUiClickMetricForRecentlyPlayedItem(final RecentlyPlayedItem recentlyPlayedItem, final PageType pageType, final String blockRef) {
        EntityIdType entityIdType;
        String collectionId = null;
        switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[recentlyPlayedItem.getItemType().ordinal()]) {
            case 1:
            case 2:
                if (recentlyPlayedItem.getCollectionAsin() != null) {
                    collectionId = recentlyPlayedItem.getCollectionAsin();
                    entityIdType = EntityIdType.ASIN;
                } else {
                    collectionId = recentlyPlayedItem.getCollectionId();
                    entityIdType = EntityIdType.CD_OBJECT_ID;
                }
                break;
            case 3:
            case 4:
            case 5:
                if (recentlyPlayedItem.getCollectionAsin() != null) {
                    collectionId = recentlyPlayedItem.getCollectionAsin();
                    entityIdType = EntityIdType.ASIN;
                } else {
                    collectionId = recentlyPlayedItem.getCollectionId();
                    entityIdType = EntityIdType.PLAYLIST_ID;
                }
                break;
            case 6:
                collectionId = recentlyPlayedItem.getTitle();
                entityIdType = EntityIdType.GENRE_NAME;
                break;
            case 7:
                collectionId = recentlyPlayedItem.getTrackAsin();
                entityIdType = EntityIdType.ASIN;
                break;
            case 8:
                if (recentlyPlayedItem.getTrackAsin() != null) {
                    collectionId = recentlyPlayedItem.getTrackAsin();
                    entityIdType = EntityIdType.ASIN;
                } else {
                    collectionId = recentlyPlayedItem.getCollectionId();
                    entityIdType = EntityIdType.CD_OBJECT_ID;
                }
                break;
            case 9:
                collectionId = recentlyPlayedItem.getCollectionId();
                entityIdType = EntityIdType.STATION_KEY;
                break;
            default:
                Log.warning(TAG, "Recently played ItemType not recognized for itemType: " + recentlyPlayedItem.getItemType() + ". Omitting entityId and entityIdType from the uiClick metric.");
                entityIdType = null;
                break;
        }
        ActionType actionType = ActionType.PLAY_RECENTLY_PLAYED;
        MetricsUtil.emitLatencyStartLegMetricForAllowedActionTypes(actionType);
        UiClickEvent.Builder builderWithBlockRef = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withEntityId(collectionId).withEntityIdType(entityIdType).withBlockRef(blockRef);
        if (pageType != null) {
            builderWithBlockRef.withPageType(pageType);
        }
        MetricsHolder.getManager().handleEvent(builderWithBlockRef.build());
    }

    public static void disableDebugLogging() {
        getDebugMode().disableDebugMode();
    }

    public static boolean getDebugLoggingState() {
        return getDebugMode().isDebugEnabled();
    }

    public static void uiPageViewed(final String pageType, @Nullable final String pageSubType, @Nullable final String externalReference) {
        Log.debug(TAG, "uiPageViewed PageType = %s, subPageType = %s", pageType, pageSubType);
        sendEvent(UiPageViewEvent.builder(pageType).withPageSubType(pageSubType).withExternalReference(externalReference).build());
    }

    public static void uiPageViewed(final String pageType, @Nullable final String detailPageItemId, @Nullable final String detailPageItemIdType, @Nullable final String externalReference, @Nullable final ViewType viewType) {
        Log.debug(TAG, "uiPageViewed PageType = %s, detailPageItemId = %s, detailPageItemIdType = %s, externalReferences = %s, viewType = %s", pageType, detailPageItemId, detailPageItemIdType, externalReference, viewType);
        sendEvent(UiPageViewEvent.builder(pageType).withDetailPageItemId(detailPageItemId).withDetailPageItemIdType(detailPageItemIdType).withExternalReference(externalReference).withViewType(viewType).build());
    }

    private static String retrieveContentSubscriptionMode() {
        return ContentSubscriptionModeHelper.retrieveContentSubscriptionMode();
    }

    @Nullable
    private static String retrieveSubContentSubscriptionMode() {
        return ContentSubscriptionModeHelper.retrieveSubContentSubscriptionMode();
    }

    public static void ghostListeningDialogViewed() {
        sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.DIALOG).withContentName(ContentName.GHOST_LISTENING_DIALOG).withContainerIndex(0L).withNumItemsAccessible(0L).withImpressionTimestamp(System.currentTimeMillis()).build());
    }

    public static void eligibilityCheckRequestedEvent(final String checkType, final String entityType, final String entityId, final String entityIdType, final String responseReason) {
        sendEvent(new EligibilityCheckRequestedEvent(checkType, entityType, entityId, entityIdType, responseReason));
    }

    public static void ConcurrencyUpsellViewEvent() {
        sendEvent(new ConcurrencyUpsellViewEvent());
    }

    public static void ConcurrencyUpsellOneClickViewEvent() {
        sendEvent(new ConcurrencyUpsellOneClickViewEvent());
    }

    public static void emitUiClickMetricForUpgrade(final ActionType actionType, final String contentName, final String externalReference) {
        sendEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withContentInfo(getContentInfo(contentName)).withRefMarker(externalReference).withEventTime(System.currentTimeMillis()).build());
    }

    public static void emitUiClickMetricForUpsell(final ActionType actionType, final String contentName) {
        sendEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withContentInfo(getContentInfo(contentName)).withEventTime(System.currentTimeMillis()).build());
    }

    public static void emitUiClickMetricForCancelSfa(final ActionType actiontype) {
        sendEvent(UiClickEvent.builder(actiontype).withInteractionType(InteractionType.TAP).build());
    }

    public static void emitUIContentViewMetric(final ContentName contentName, final ContainerType containerType) {
        sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(containerType).withContentName(contentName).withContainerIndex(0L).withImpressionTimestamp(System.currentTimeMillis()).build());
    }

    public static List<Map<String, String>> getContentInfo(final String contentName) {
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        map.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
        map.put("containerIndex", "0");
        map.put("contentName", contentName);
        arrayList.add(map);
        return arrayList;
    }

    public static void sendUpsellContentViewEvent(final ContainerType containerType, final ContentName contentName) {
        sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(containerType).withContentName(contentName).withImpressionTimestamp(System.currentTimeMillis()).withEventTime(System.currentTimeMillis()).build());
    }

    public static void sendHomePageResponseFlexEvent(final Context context, final Float responseLatency, final Boolean isFirstPage, final Boolean isCacheExpired) {
        sendEvent(FlexEvent.builder("homePageResponse").withFlexNum1(responseLatency).withFlexStr1(isFirstPage.booleanValue() ? "HOME_PAGE:INITIAL" : "HOME_PAGE:PAGINATED").withFlexStr2(isCacheExpired.booleanValue() ? "PAGE:FRESH" : "PAGE:CACHED").withFlexStr3(ConnectivityUtil.isWifiConnected(context) ? "WIFI:YES" : "WIFI:NO").build());
    }

    public static void sendDeeplinkIntentReceivedFlexEvent(final Uri deeplink, final Uri referrer, final boolean isBranchLink) {
        if (deeplink == null) {
            return;
        }
        sendEvent(FlexEvent.builder("deeplinkIntentReceived").withFlexStr1(deeplink.toString()).withFlexStr2(deeplink.getQueryParameter("webSessionId")).withFlexStr3(referrer != null ? referrer.getHost() : "").withFlexNum1(Float.valueOf(isBranchLink ? 1.0f : 0.0f)).build());
    }

    public static DeeplinkInteractionEvent createDeeplinkInteractionEvent(final Uri deeplink, final Uri deeplinkIntended, final Uri referer, final String deeplinkStage, final String branchUrl) {
        String host;
        if (deeplink == null || deeplinkIntended == null) {
            Log.error(TAG, "Unable to send DeeplinkInteraction event due to null deeplink.");
            return null;
        }
        String queryParameter = "";
        if (referer != null) {
            host = referer.getHost() != null ? referer.getHost() : referer.toString();
        } else {
            host = "";
        }
        String queryParameter2 = deeplink.getQueryParameter("webSessionId");
        Set<String> queryParameterNames = deeplink.getQueryParameterNames();
        Pattern patternCompile = Pattern.compile("^ref_?$", 2);
        for (String str : queryParameterNames) {
            if (patternCompile.matcher(str).matches()) {
                queryParameter = deeplink.getQueryParameter(str);
            }
        }
        String path = deeplink.getPath();
        if (deeplink.getQuery() != null) {
            path = path + "?" + deeplink.getQuery();
        }
        String path2 = deeplinkIntended.getPath();
        if (deeplinkIntended.getQuery() != null) {
            path2 = path2 + "?" + deeplinkIntended.getQuery();
        }
        return DeeplinkInteractionEvent.builder().withRefTag(queryParameter).withCsSessionId(queryParameter2).withReferer(host).withDeeplinkPath(path).withDeeplinkPathIntended(path2).withDeeplinkStage(deeplinkStage).withIsFallBack(!path.equals(path2)).withIsBranchLink(branchUrl != null).withBranchUrl(branchUrl).withAssociateTag(deeplink.getQueryParameter(ParserUtil.TAG_QUERY_PARAM_NAME)).build();
    }

    public static void sendDeeplinkInteractionEvent(final Uri deeplink, final Uri deeplinkIntended, final Uri referer, final String deeplinkStage, final String branchUrl) {
        DeeplinkInteractionEvent deeplinkInteractionEventCreateDeeplinkInteractionEvent = createDeeplinkInteractionEvent(deeplink, deeplinkIntended, referer, deeplinkStage, branchUrl);
        if (deeplinkInteractionEventCreateDeeplinkInteractionEvent != null) {
            sendEvent(deeplinkInteractionEventCreateDeeplinkInteractionEvent);
        }
    }

    public static void sendSSLHandShakeFailedEvent(final String url, final Exception e) {
        FlexEvent.Builder builder = FlexEvent.builder("SSLHandshakeExceptionOccured");
        builder.withFlexStr1(url);
        if (e != null) {
            builder.withFlexStr2(e.getMessage());
        }
        builder.withFlexStr4(Integer.toString(Build.VERSION.SDK_INT));
        sendEvent(builder.build());
    }

    public static void migrateExistingDownloadsDialogViewed() {
        MetricsHolder.getManager().handleEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.DIALOG).withContainerIndex(0L).withContentName(ContentName.MIGRATE_EXISTING_DOWNLOADS_DIALOG).withImpressionTimestamp(System.currentTimeMillis()).withEventTime(System.currentTimeMillis()).build());
    }

    public static void migrateExistingDownloadsRibbonViewed() {
        MetricsHolder.getManager().handleEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.RIBBON).withContainerIndex(0L).withContentName(ContentName.MIGRATE_EXISTING_DOWNLOADS_RIBBON).withImpressionTimestamp(System.currentTimeMillis()).withEventTime(System.currentTimeMillis()).build());
    }

    public static void downloadsQueuedRibbonViewed() {
        MetricsHolder.getManager().handleEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.RIBBON).withContainerIndex(0L).withContentName(ContentName.DOWNLOADS_QUEUED_RIBBON).withImpressionTimestamp(System.currentTimeMillis()).withEventTime(System.currentTimeMillis()).build());
    }

    public static void removeUnavailableDownloadsDialogViewed() {
        MetricsHolder.getManager().handleEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.DIALOG).withContainerIndex(0L).withContentName(ContentName.REMOVE_UNAVAILABLE_DONWLOADS_DIALOG).withImpressionTimestamp(System.currentTimeMillis()).withEventTime(System.currentTimeMillis()).build());
    }

    public static void qualitySettingsDialogViewed(final ContentName contentName) {
        MetricsHolder.getManager().handleEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.DIALOG).withContainerIndex(0L).withContentName(contentName).withImpressionTimestamp(System.currentTimeMillis()).withEventTime(System.currentTimeMillis()).build());
    }

    public static void redownloadOfflineMusicDialogViewed() {
        MetricsHolder.getManager().handleEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.DIALOG).withContainerIndex(0L).withContentName(ContentName.REDOWNLOAD_OFFLINE_MUSIC_DIALOG).withImpressionTimestamp(System.currentTimeMillis()).withEventTime(System.currentTimeMillis()).build());
    }

    public static void sendUnavailableSongSelectedEvent(final String asin, final PageType pageType) {
        if (asin == null) {
            asin = "DEFAULT_ASIN";
        }
        sendEvent(UiClickEvent.builder(ActionType.PLAY_SONG).withEntityId(asin).withEntityIdType(EntityIdType.ASIN).withInteractionType(InteractionType.TAP).withPageType(pageType).withEventTime(System.currentTimeMillis()).build());
    }

    public static void sendThirdPartyVoiceSearchEvent(final String thirdPartyVoiceAssistantName, final String googleAssistantSearchQuery, final String googleAssistantMediaAlbum, final String googleAssistantMediaArtist, final String googleAssistantMediaGenre, final String googleAssistantMediaPlaylist, final String googleAssistantMediaTitle, final String googleAssistantMediaFocus, final String playbackInstanceId, final String utteranceId) {
        sendEvent(new ThirdPartyVoiceSearchEvent(thirdPartyVoiceAssistantName, googleAssistantSearchQuery, googleAssistantMediaAlbum, googleAssistantMediaArtist, googleAssistantMediaGenre, googleAssistantMediaPlaylist, googleAssistantMediaTitle, googleAssistantMediaFocus, playbackInstanceId, utteranceId));
    }
}
