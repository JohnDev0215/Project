package com.amazon.music.media.auto;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Response;
import Touch.SyncInterface.v1_0.WidgetContext;
import android.content.Context;
import android.support.v4.media.MediaBrowserCompat;
import androidx.media.MediaBrowserServiceCompat;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.media.auto.feature.AutomotiveFeature;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.provider.LocalImageUriProvider;
import com.amazon.music.media.auto.provider.MediaBrowserNodeProvider;
import com.amazon.music.media.auto.provider.MuseBrushPageProvider;
import com.amazon.music.media.auto.provider.OnMusicLoadedListener;
import com.amazon.music.media.auto.provider.SubDeviceTypeProvider;
import com.amazon.music.media.auto.provider.data.AutoTabItem;
import com.amazon.music.media.auto.provider.data.AutoWidgetItem;
import com.amazon.music.media.auto.provider.network.DefaultMESKRequestProvider;
import com.amazon.music.media.auto.provider.network.HomeTabRequestProvider;
import com.amazon.music.media.auto.provider.network.LibraryRequestProvider;
import com.amazon.music.media.auto.provider.network.MESKRequestProvider;
import com.amazon.music.media.auto.tab.MESKNetworkTask;
import com.amazon.music.media.auto.tab.NaviAppPage;
import com.amazon.music.media.auto.tab.TabType;
import com.amazon.music.media.auto.tab.home.HomeTab;
import com.amazon.music.media.auto.tab.home.MESKHomeTab;
import com.amazon.music.media.auto.tab.home.brush.BrushPageConverter;
import com.amazon.music.media.auto.tab.home.mesk.converters.MESKHomePageConverter;
import com.amazon.music.media.auto.tab.library.LibraryTab;
import com.amazon.music.media.auto.tab.library.skyfire.SkyfireContentMetadataConverter;
import com.amazon.music.media.auto.tab.search.SearchContentConverter;
import com.amazon.music.media.auto.tab.search.TextSearchProvider;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.CredentialStorage;
import com.amazon.music.media.auto.util.LatencyCodePageUri;
import com.amazon.music.media.auto.util.MediaBrowserUtil;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.search.SearchService;
import com.google.android.gms.actions.SearchIntents;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AutoMediaBrowser.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u0080\u00012\u00020\u0001:\u0002\u0080\u0001BY\u0012\u0006\u0010=\u001a\u00020<\u0012\u0006\u0010@\u001a\u00020?\u0012\u0006\u0010C\u001a\u00020B\u0012\u0006\u0010F\u001a\u00020E\u0012\u0006\u0010I\u001a\u00020H\u0012\u0006\u0010L\u001a\u00020K\u0012\b\u0010O\u001a\u0004\u0018\u00010N\u0012\u0006\u0010R\u001a\u00020Q\u0012\u0006\u0010{\u001a\u00020z\u0012\u0006\u0010}\u001a\u00020|¢\u0006\u0004\b~\u0010\u007fJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\b\u0010\b\u001a\u00020\u0005H\u0002J\u0006\u0010\t\u001a\u00020\u0002J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rJ\u001e\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0012J\"\u0010\u0017\u001a\u00020\u00022\u0018\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\n0\u0015H\u0016J*\u0010\u001a\u001a\u00020\u00022\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\n0\u00152\u0006\u0010\u0019\u001a\u00020\rH\u0016J$\u0010\u001c\u001a\u00020\u00022\u001a\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\n\u0018\u00010\u0015H\u0016J\u0010\u0010\u001f\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001dH\u0016J\u0010\u0010\"\u001a\u00020\u00022\u0006\u0010!\u001a\u00020 H\u0016J\u0006\u0010#\u001a\u00020\u0002J\u0006\u0010$\u001a\u00020\u0002J\u0014\u0010&\u001a\u00020\u00022\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u001a\u0010*\u001a\u00020\u00022\u0012\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0(0'J\u0010\u0010-\u001a\u00020\u00022\b\u0010,\u001a\u0004\u0018\u00010+J\u001a\u00102\u001a\u00020\u00022\b\u0010/\u001a\u0004\u0018\u00010.2\b\u00101\u001a\u0004\u0018\u000100J\u0016\u00103\u001a\u00020\u00022\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nJ\u001a\u00104\u001a\u00020\u00022\u0012\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0(0'J\u0010\u00106\u001a\u00020\u00022\b\b\u0002\u00105\u001a\u00020\u0005J\u000e\u00109\u001a\u00020\u00022\u0006\u00108\u001a\u000207J\u0006\u0010:\u001a\u00020\u0005J\u0006\u0010;\u001a\u00020\u0002R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010@\u001a\u00020?8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010C\u001a\u00020B8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010DR\u0014\u0010F\u001a\u00020E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010GR\u0014\u0010I\u001a\u00020H8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010JR\u0014\u0010L\u001a\u00020K8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010MR\u0016\u0010O\u001a\u0004\u0018\u00010N8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bO\u0010PR\u0014\u0010R\u001a\u00020Q8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\u0014\u0010T\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010>R\u0017\u0010V\u001a\u00020U8\u0006¢\u0006\f\n\u0004\bV\u0010W\u001a\u0004\bX\u0010YR\u0014\u0010[\u001a\u00020Z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010bR\"\u0010c\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bc\u0010d\u001a\u0004\bc\u0010e\"\u0004\bf\u0010gR\"\u0010h\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bh\u0010d\u001a\u0004\bh\u0010e\"\u0004\bi\u0010gR.\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020k0j8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bl\u0010m\u001a\u0004\bn\u0010o\"\u0004\bp\u0010qR$\u0010r\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0(\u0018\u00010'8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\br\u0010sR$\u0010t\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0(\u0018\u00010'8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bt\u0010sR\u0018\u0010u\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bu\u0010vR\u0016\u0010w\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bw\u0010dR\u0018\u0010x\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bx\u0010y¨\u0006\u0081\u0001"}, d2 = {"Lcom/amazon/music/media/auto/AutoMediaBrowser;", "Lcom/amazon/music/media/auto/provider/OnMusicLoadedListener;", "", "setNaviAppPage", "fetchMESKHomePage", "", "isMESKHomeTabEnabled", "isLibraryTabEnabled", "isPresetsFeatureEnabled", "clear", "", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "getFlatRootChildren", "", "mediaId", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "getContent", "startPlayback", "Lcom/amazon/music/media/auto/PendingPlayback;", "playbackHandler", "prepareForPlayback", "", "widgetsMap", "onBrushHomePageFetched", "widgetMap", "widgetTitle", "onBrushHomeSeeMorePageFetched", "LTouch/SyncInterface/v1_0/WidgetContext;", "onLibraryPageFetched", "Lcom/amazon/music/media/auto/provider/data/AutoTabItem;", "tabItem", "onMESKHomePageFetched", "Lcom/amazon/music/media/auto/provider/data/AutoWidgetItem;", "widgetItem", "onMESKHomeSeeMorePageFetched", "initFirstLayerDrawer", "playPendingPlayback", "results", "sendHomePageResult", "Landroidx/media/MediaBrowserServiceCompat$Result;", "", "result", "setHomePageResult", "Lcom/amazon/music/search/SearchService$Configuration;", "config", "configureSearchService", "Lcom/amazon/music/media/auto/util/CredentialStorage$Credentials;", "credentials", "Lcom/amazon/music/media/auto/util/CredentialStorage$GoogleAssistantGatewayCredentials;", "gaCredentials", "configureCredentialStorage", "sendTextSearchResult", "setTextSearchResult", "forceFetch", "fetchHomePage", "Lcom/amazon/music/media/auto/AutoTextSearchQuery;", SearchIntents.EXTRA_QUERY, "fetchTextSearchResults", "areAllPagesReady", "fetchLibraryPage", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/media/auto/AutoMusicMediaBrowserService;", "browserService", "Lcom/amazon/music/media/auto/AutoMusicMediaBrowserService;", "Lcom/amazon/music/media/auto/tab/home/brush/BrushPageConverter;", "brushConverter", "Lcom/amazon/music/media/auto/tab/home/brush/BrushPageConverter;", "Lcom/amazon/music/media/auto/tab/library/skyfire/SkyfireContentMetadataConverter;", "skyfireConverter", "Lcom/amazon/music/media/auto/tab/library/skyfire/SkyfireContentMetadataConverter;", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/MESKHomePageConverter;", "meskHomePageConverter", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/MESKHomePageConverter;", "Lcom/amazon/music/media/auto/util/CredentialStorage;", "credentialStorage", "Lcom/amazon/music/media/auto/util/CredentialStorage;", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "featureGateProvider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "Lkotlinx/coroutines/CoroutineScope;", "mediaBrowserScope", "Lkotlinx/coroutines/CoroutineScope;", "applicationContext", "Lcom/amazon/music/media/auto/MediaItemNodeManager;", "nodeManager", "Lcom/amazon/music/media/auto/MediaItemNodeManager;", "getNodeManager", "()Lcom/amazon/music/media/auto/MediaItemNodeManager;", "Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;", "nodeProvider", "Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;", "Lcom/amazon/music/media/auto/tab/NaviAppPage;", "naviAppPage", "Lcom/amazon/music/media/auto/tab/NaviAppPage;", "Lcom/amazon/music/media/auto/tab/search/TextSearchProvider;", "textSearchProvider", "Lcom/amazon/music/media/auto/tab/search/TextSearchProvider;", "isHomePageReady", "Z", "()Z", "setHomePageReady", "(Z)V", "isLibraryPageReady", "setLibraryPageReady", "", "", "widgetEntitiesCount", "Ljava/util/Map;", "getWidgetEntitiesCount", "()Ljava/util/Map;", "setWidgetEntitiesCount", "(Ljava/util/Map;)V", "homePageResult", "Landroidx/media/MediaBrowserServiceCompat$Result;", "textSearchResult", "pendingMediaId", "Ljava/lang/String;", "pendingStartPlayback", "pendingPlaybackHandler", "Lcom/amazon/music/media/auto/PendingPlayback;", "Lcom/amazon/music/media/auto/provider/LocalImageUriProvider;", "imageUriProvider", "Lcom/amazon/music/media/auto/tab/search/SearchContentConverter;", "searchConverter", "<init>", "(Landroid/content/Context;Lcom/amazon/music/media/auto/AutoMusicMediaBrowserService;Lcom/amazon/music/media/auto/tab/home/brush/BrushPageConverter;Lcom/amazon/music/media/auto/tab/library/skyfire/SkyfireContentMetadataConverter;Lcom/amazon/music/media/auto/tab/home/mesk/converters/MESKHomePageConverter;Lcom/amazon/music/media/auto/util/CredentialStorage;Lcom/amazon/music/platform/providers/FeatureGateProvider;Lkotlinx/coroutines/CoroutineScope;Lcom/amazon/music/media/auto/provider/LocalImageUriProvider;Lcom/amazon/music/media/auto/tab/search/SearchContentConverter;)V", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class AutoMediaBrowser implements OnMusicLoadedListener {
    private final Context applicationContext;
    private final AutoMusicMediaBrowserService browserService;
    private final BrushPageConverter brushConverter;
    private final Context context;
    private final CredentialStorage credentialStorage;
    private final FeatureGateProvider featureGateProvider;
    private MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> homePageResult;
    private boolean isHomePageReady;
    private boolean isLibraryPageReady;
    private final CoroutineScope mediaBrowserScope;
    private final MESKHomePageConverter meskHomePageConverter;
    private final NaviAppPage naviAppPage;
    private final MediaItemNodeManager nodeManager;
    private final MediaBrowserNodeProvider nodeProvider;
    private String pendingMediaId;
    private PendingPlayback pendingPlaybackHandler;
    private boolean pendingStartPlayback;
    private final SkyfireContentMetadataConverter skyfireConverter;
    private final TextSearchProvider textSearchProvider;
    private MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> textSearchResult;
    private Map<String, Integer> widgetEntitiesCount;
    private static final String TAG = AutoMediaBrowser.class.getSimpleName();
    private static final AtomicBoolean isLibrarySkyfireCalled = new AtomicBoolean(false);
    private static final AtomicBoolean isHomeMESKCalled = new AtomicBoolean(false);
    private static final AtomicBoolean isPodcastMESKCalled = new AtomicBoolean(false);

    public AutoMediaBrowser(Context context, AutoMusicMediaBrowserService browserService, BrushPageConverter brushConverter, SkyfireContentMetadataConverter skyfireConverter, MESKHomePageConverter meskHomePageConverter, CredentialStorage credentialStorage, FeatureGateProvider featureGateProvider, CoroutineScope mediaBrowserScope, LocalImageUriProvider imageUriProvider, SearchContentConverter searchConverter) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(browserService, "browserService");
        Intrinsics.checkNotNullParameter(brushConverter, "brushConverter");
        Intrinsics.checkNotNullParameter(skyfireConverter, "skyfireConverter");
        Intrinsics.checkNotNullParameter(meskHomePageConverter, "meskHomePageConverter");
        Intrinsics.checkNotNullParameter(credentialStorage, "credentialStorage");
        Intrinsics.checkNotNullParameter(mediaBrowserScope, "mediaBrowserScope");
        Intrinsics.checkNotNullParameter(imageUriProvider, "imageUriProvider");
        Intrinsics.checkNotNullParameter(searchConverter, "searchConverter");
        this.context = context;
        this.browserService = browserService;
        this.brushConverter = brushConverter;
        this.skyfireConverter = skyfireConverter;
        this.meskHomePageConverter = meskHomePageConverter;
        this.credentialStorage = credentialStorage;
        this.featureGateProvider = featureGateProvider;
        this.mediaBrowserScope = mediaBrowserScope;
        Context applicationContext = browserService.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "browserService.applicationContext");
        this.applicationContext = applicationContext;
        MediaItemNodeManager mediaItemNodeManager = new MediaItemNodeManager(applicationContext, featureGateProvider);
        this.nodeManager = mediaItemNodeManager;
        MediaBrowserNodeProvider mediaBrowserNodeProvider = new MediaBrowserNodeProvider(applicationContext, browserService, mediaItemNodeManager, mediaBrowserScope, imageUriProvider);
        this.nodeProvider = mediaBrowserNodeProvider;
        this.naviAppPage = new NaviAppPage(mediaBrowserNodeProvider, mediaItemNodeManager, this, browserService, context);
        this.textSearchProvider = new TextSearchProvider(mediaBrowserNodeProvider, searchConverter, mediaBrowserScope);
        this.widgetEntitiesCount = new LinkedHashMap();
    }

    public final MediaItemNodeManager getNodeManager() {
        return this.nodeManager;
    }

    /* JADX INFO: renamed from: isHomePageReady, reason: from getter */
    public final boolean getIsHomePageReady() {
        return this.isHomePageReady;
    }

    public final void setHomePageReady(boolean z) {
        this.isHomePageReady = z;
    }

    public final void setLibraryPageReady(boolean z) {
        this.isLibraryPageReady = z;
    }

    public final void clear() {
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, "AutoMediaBrowser clear");
        this.isHomePageReady = false;
        this.isLibraryPageReady = false;
        this.nodeManager.resetRootNode();
        this.nodeManager.resetSearchResults();
        this.widgetEntitiesCount.clear();
        this.naviAppPage.clear();
        isLibrarySkyfireCalled.set(false);
        isHomeMESKCalled.set(false);
        isPodcastMESKCalled.set(false);
    }

    public final List<MediaBrowserCompat.MediaItem> getFlatRootChildren() {
        return this.nodeManager.getFlatMapOfContent();
    }

    public final AutoMediaItem getContent(String mediaId) {
        MediaItemNode node = this.nodeManager.getNode(mediaId);
        if (node == null) {
            return null;
        }
        return node.getAutoMediaItem();
    }

    public final void prepareForPlayback(String mediaId, boolean startPlayback, PendingPlayback playbackHandler) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        Intrinsics.checkNotNullParameter(playbackHandler, "playbackHandler");
        this.pendingMediaId = mediaId;
        this.pendingStartPlayback = startPlayback;
        this.pendingPlaybackHandler = playbackHandler;
    }

    @Override // com.amazon.music.media.auto.provider.OnMusicLoadedListener
    public void onBrushHomePageFetched(Map<String, ? extends List<AutoMediaItem>> widgetsMap) {
        Intrinsics.checkNotNullParameter(widgetsMap, "widgetsMap");
        if (SubDeviceTypeProvider.INSTANCE.isMultiLevelMediaBrowserHierarchy()) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "onBrushHomePageFetched for multiLevelMediaBrowserHierarchy");
            CredentialStorage.Credentials credentials = this.credentialStorage.getCredentials();
            HomeTab homeTab = credentials == null ? null : new HomeTab(widgetsMap, this.nodeProvider, getNodeManager(), this, this.browserService, this.brushConverter, credentials);
            if (homeTab != null) {
                homeTab.getMediaNodes();
            }
            if (homeTab == null) {
                return;
            }
            homeTab.notifyMediaBrowserUpdate();
            return;
        }
        AutoLog autoLog2 = AutoLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
        autoLog2.debug(TAG3, "onBrushHomePageFetched for singleLevelMediaBrowserHierarchy");
        this.naviAppPage.setHomeWidgetsMap(widgetsMap);
        if (this.naviAppPage.getIsLibraryWidgetsMapSet().get()) {
            setNaviAppPage();
        }
    }

    @Override // com.amazon.music.media.auto.provider.OnMusicLoadedListener
    public void onBrushHomeSeeMorePageFetched(Map<String, ? extends List<AutoMediaItem>> widgetMap, String widgetTitle) {
        Intrinsics.checkNotNullParameter(widgetMap, "widgetMap");
        Intrinsics.checkNotNullParameter(widgetTitle, "widgetTitle");
        this.nodeProvider.expandSeeMoreNode(widgetMap, widgetTitle);
    }

    public void onLibraryPageFetched(Map<WidgetContext, ? extends List<AutoMediaItem>> widgetsMap) {
        if (SubDeviceTypeProvider.INSTANCE.isMultiLevelMediaBrowserHierarchy()) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "onLibraryPageFetched for multiLevelMediaBrowserHierarchy");
            if (widgetsMap == null) {
                return;
            }
            BrushLatencyReportingUtil brushLatencyReportingUtil = BrushLatencyReportingUtil.INSTANCE;
            LatencyCodePageUri latencyCodePageUri = LatencyCodePageUri.MEDIA_BROWSER_LIBRARY;
            brushLatencyReportingUtil.trackLatency(latencyCodePageUri.getURI(), LatencyTrackingLeg.RENDERING);
            LibraryTab libraryTab = new LibraryTab(widgetsMap, this.nodeProvider, this.nodeManager, this.browserService, this);
            libraryTab.getMediaNodes();
            libraryTab.notifyMediaBrowserUpdate();
            brushLatencyReportingUtil.trackLatency(latencyCodePageUri.getURI(), LatencyTrackingLeg.LOAD_COMPLETE);
            return;
        }
        AutoLog autoLog2 = AutoLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
        autoLog2.debug(TAG3, "onLibraryPageFetched for singleLevelMediaBrowserHierarchy");
        this.naviAppPage.setLibraryWidgetsMap(widgetsMap);
        if (this.naviAppPage.getIsHomeWidgetsMapSet().get()) {
            Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
            autoLog2.debug(TAG3, "setNaviAppPage() called by library");
            setNaviAppPage();
        }
    }

    private final synchronized void setNaviAppPage() {
        this.naviAppPage.setMixedWidgetsMap();
        sendHomePageResult(CollectionsKt___CollectionsKt.drop(this.naviAppPage.getMediaNodes(), 1));
        this.naviAppPage.notifyMediaBrowserUpdate();
    }

    public void onMESKHomePageFetched(AutoTabItem tabItem) {
        Intrinsics.checkNotNullParameter(tabItem, "tabItem");
        SubDeviceTypeProvider subDeviceTypeProvider = SubDeviceTypeProvider.INSTANCE;
        if (subDeviceTypeProvider.isMultiLevelMediaBrowserHierarchy()) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "onMESKHomePageFetched for multiLevelMediaBrowserHierarchy");
            MESKHomeTab mESKHomeTab = new MESKHomeTab(tabItem, this.nodeProvider, this.nodeManager, this, this.browserService, subDeviceTypeProvider);
            BrushLatencyReportingUtil brushLatencyReportingUtil = BrushLatencyReportingUtil.INSTANCE;
            LatencyCodePageUri latencyCodePageUri = LatencyCodePageUri.MEDIA_BROWSER_HOME;
            brushLatencyReportingUtil.trackLatency(latencyCodePageUri.getURI(), LatencyTrackingLeg.RENDERING);
            mESKHomeTab.getMediaNodes();
            mESKHomeTab.notifyMediaBrowserUpdate();
            brushLatencyReportingUtil.trackLatency(latencyCodePageUri.getURI(), LatencyTrackingLeg.LOAD_COMPLETE);
            return;
        }
        AutoLog autoLog2 = AutoLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
        autoLog2.debug(TAG3, "onMESKHomePageFetched for singleLevelMediaBrowserHierarchy");
        this.naviAppPage.setHomeWidgetsMap(MediaBrowserUtil.INSTANCE.createWidgetMapForHomeTab(tabItem));
        if (this.naviAppPage.getIsLibraryWidgetsMapSet().get()) {
            Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
            autoLog2.debug(TAG3, "setNaviAppPage() called by MESKHome");
            setNaviAppPage();
        }
    }

    public void onMESKHomeSeeMorePageFetched(AutoWidgetItem widgetItem) {
        Intrinsics.checkNotNullParameter(widgetItem, "widgetItem");
        this.nodeProvider.expandMeskSeeMoreNode(widgetItem);
    }

    public final void initFirstLayerDrawer() {
        if (SubDeviceTypeProvider.INSTANCE.isMultiLevelMediaBrowserHierarchy()) {
            this.nodeManager.getHomeNode();
            if (isLibraryTabEnabled()) {
                this.nodeManager.getLibraryNode();
            }
        }
    }

    public final void playPendingPlayback() {
        if (!this.pendingStartPlayback || this.pendingMediaId == null) {
            return;
        }
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, Intrinsics.stringPlus("Starting pending playback for mediaId ", this.pendingMediaId));
        PendingPlayback pendingPlayback = this.pendingPlaybackHandler;
        if (pendingPlayback != null) {
            pendingPlayback.readyForPlayback(this.pendingMediaId, true);
        }
        this.pendingPlaybackHandler = null;
        this.pendingMediaId = null;
        this.pendingStartPlayback = false;
    }

    public final void sendHomePageResult(List<? extends MediaBrowserCompat.MediaItem> results) {
        Intrinsics.checkNotNullParameter(results, "results");
        MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result = this.homePageResult;
        if (result != null) {
            result.sendResult(CollectionsKt___CollectionsKt.toMutableList((Collection) results));
        }
        this.homePageResult = null;
    }

    public final void setHomePageResult(MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.homePageResult = result;
    }

    public final void configureSearchService(SearchService.Configuration config) {
        this.textSearchProvider.configureSearchService(config);
    }

    public final void configureCredentialStorage(CredentialStorage.Credentials credentials, CredentialStorage.GoogleAssistantGatewayCredentials gaCredentials) {
        this.credentialStorage.setCredentials(credentials);
        this.credentialStorage.setGoogleAssistantGatewayCredentials(gaCredentials);
        this.textSearchProvider.setContentType(this.credentialStorage.getCredentials());
    }

    public final void sendTextSearchResult(List<? extends MediaBrowserCompat.MediaItem> results) {
        MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result = this.textSearchResult;
        if (result != null) {
            result.sendResult(results == null ? null : CollectionsKt___CollectionsKt.toMutableList((Collection) results));
        }
        this.textSearchResult = null;
    }

    public final void setTextSearchResult(MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.textSearchResult = result;
    }

    public static /* synthetic */ void fetchHomePage$default(AutoMediaBrowser autoMediaBrowser, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        autoMediaBrowser.fetchHomePage(z);
    }

    public final synchronized void fetchHomePage(boolean forceFetch) {
        SubDeviceTypeProvider subDeviceTypeProvider = SubDeviceTypeProvider.INSTANCE;
        if (!SubDeviceTypeProvider.isMediaTreeSupported$default(subDeviceTypeProvider, null, 1, null) && !forceFetch) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, Intrinsics.stringPlus("Not fetching home or library tabs for unsupported sub Device Type", subDeviceTypeProvider.getLatestSubDeviceType()));
            return;
        }
        AutoLog autoLog2 = AutoLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
        autoLog2.debug(TAG3, "Fetching subpages");
        if (forceFetch || !this.isHomePageReady) {
            if (isMESKHomeTabEnabled()) {
                fetchMESKHomePage();
            } else {
                Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                autoLog2.debug(TAG3, "Fetching home page");
                CredentialStorage.Credentials credentials = this.credentialStorage.getCredentials();
                if (credentials != null) {
                    MuseBrushPageProvider.INSTANCE.getHomePage(this.context, credentials, this, this.brushConverter);
                }
            }
        }
        if (forceFetch || (isLibraryTabEnabled() && !this.isLibraryPageReady)) {
            fetchLibraryPage();
        }
    }

    private final void fetchMESKHomePage() {
        if (isHomeMESKCalled.compareAndSet(false, true)) {
            CredentialStorage.Credentials credentials = this.credentialStorage.getCredentials();
            if (credentials != null) {
                AutoLog autoLog = AutoLog.INSTANCE;
                String TAG2 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog.debug(TAG2, "Fetching MESK home page");
                BrushLatencyReportingUtil.INSTANCE.trackLatency(LatencyCodePageUri.MEDIA_BROWSER_HOME.getURI(), LatencyTrackingLeg.REQUEST);
                BuildersKt__Builders_commonKt.launch$default(this.mediaBrowserScope, null, null, new C04291(new MESKNetworkTask(new HomeTabRequestProvider(credentials), null, TabType.HOME, 2, null), this, null), 3, null);
                return;
            }
            AutoLog autoLog2 = AutoLog.INSTANCE;
            String TAG3 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
            autoLog2.debug(TAG3, "Not calling MESK home as credentials are null");
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.auto.AutoMediaBrowser$fetchMESKHomePage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AutoMediaBrowser.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.media.auto.AutoMediaBrowser$fetchMESKHomePage$1", f = "AutoMediaBrowser.kt", i = {}, l = {355}, m = "invokeSuspend", n = {}, s = {})
    static final class C04291 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MESKNetworkTask $homeNetworkTask;
        int label;
        final /* synthetic */ AutoMediaBrowser this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04291(MESKNetworkTask mESKNetworkTask, AutoMediaBrowser autoMediaBrowser, Continuation<? super C04291> continuation) {
            super(2, continuation);
            this.$homeNetworkTask = mESKNetworkTask;
            this.this$0 = autoMediaBrowser;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04291(this.$homeNetworkTask, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04291) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                BrushLatencyReportingUtil.INSTANCE.trackLatency(LatencyCodePageUri.MEDIA_BROWSER_HOME.getURI(), LatencyTrackingLeg.RESPONSE);
                MESKNetworkTask mESKNetworkTask = this.$homeNetworkTask;
                this.label = 1;
                obj = mESKNetworkTask.execute(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            Response response = (Response) obj;
            if (response != null) {
                AutoMediaBrowser autoMediaBrowser = this.this$0;
                BrushLatencyReportingUtil.INSTANCE.trackLatency(LatencyCodePageUri.MEDIA_BROWSER_HOME.getURI(), LatencyTrackingLeg.PROCESSING);
                if (response.methods() == null || response.methods().isEmpty()) {
                    AutoLog autoLog = AutoLog.INSTANCE;
                    String TAG = AutoMediaBrowser.TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
                    autoLog.error(TAG, "no response from MESK home");
                }
                MESKHomePageConverter mESKHomePageConverter = autoMediaBrowser.meskHomePageConverter;
                List<Method> listMethods = response.methods();
                Intrinsics.checkNotNullExpressionValue(listMethods, "meskResponse.methods()");
                AutoTabItem autoTabItemConvertResponse = mESKHomePageConverter.convertResponse(listMethods);
                if (autoTabItemConvertResponse == null) {
                    AutoLog autoLog2 = AutoLog.INSTANCE;
                    String TAG2 = AutoMediaBrowser.TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    autoLog2.error(TAG2, "Unable to convert MESK home response");
                }
                if (autoTabItemConvertResponse != null) {
                    autoMediaBrowser.onMESKHomePageFetched(autoTabItemConvertResponse);
                }
            }
            return Unit.INSTANCE;
        }
    }

    private final boolean isMESKHomeTabEnabled() {
        FeatureGateProvider featureGateProvider = this.featureGateProvider;
        return featureGateProvider != null && AutomotiveFeature.MESK_BASED_HOME.isEnabled(featureGateProvider);
    }

    public final void fetchTextSearchResults(AutoTextSearchQuery query) {
        Intrinsics.checkNotNullParameter(query, "query");
        this.textSearchProvider.scheduleSearchJob(query, new Function1<List<? extends MediaBrowserCompat.MediaItem>, Unit>() { // from class: com.amazon.music.media.auto.AutoMediaBrowser.fetchTextSearchResults.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends MediaBrowserCompat.MediaItem> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<? extends MediaBrowserCompat.MediaItem> list) {
                AutoMediaBrowser.this.sendTextSearchResult(list);
            }
        });
    }

    private final boolean isLibraryTabEnabled() {
        FeatureGateProvider featureGateProvider = this.featureGateProvider;
        return featureGateProvider != null && AutomotiveFeature.MEDIA_BROWSER_LIBRARY_TAB.isEnabled(featureGateProvider);
    }

    private final boolean isPresetsFeatureEnabled() {
        FeatureGateProvider featureGateProvider = this.featureGateProvider;
        if (featureGateProvider == null) {
            return false;
        }
        return AutomotiveFeature.MY_PRESETS.isEnabled(featureGateProvider);
    }

    public final boolean areAllPagesReady() {
        return this.isLibraryPageReady && this.isHomePageReady;
    }

    public final synchronized void fetchLibraryPage() {
        MESKRequestProvider defaultMESKRequestProvider;
        if (isLibrarySkyfireCalled.compareAndSet(false, true)) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "Fetching library page");
            BrushLatencyReportingUtil.INSTANCE.trackLatency(LatencyCodePageUri.MEDIA_BROWSER_LIBRARY.getURI(), LatencyTrackingLeg.REQUEST);
            if (isPresetsFeatureEnabled()) {
                defaultMESKRequestProvider = new LibraryRequestProvider(this.context, this.featureGateProvider);
            } else {
                defaultMESKRequestProvider = new DefaultMESKRequestProvider();
            }
            BuildersKt__Builders_commonKt.launch$default(this.mediaBrowserScope, null, null, new AnonymousClass1(new MESKNetworkTask(defaultMESKRequestProvider, null, TabType.LIBRARY, 2, null), this, null), 3, null);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.auto.AutoMediaBrowser$fetchLibraryPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: AutoMediaBrowser.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.media.auto.AutoMediaBrowser$fetchLibraryPage$1", f = "AutoMediaBrowser.kt", i = {}, l = {425}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MESKNetworkTask $libraryNetworkTask;
        int label;
        final /* synthetic */ AutoMediaBrowser this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(MESKNetworkTask mESKNetworkTask, AutoMediaBrowser autoMediaBrowser, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$libraryNetworkTask = mESKNetworkTask;
            this.this$0 = autoMediaBrowser;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$libraryNetworkTask, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Unit unit;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                BrushLatencyReportingUtil.INSTANCE.trackLatency(LatencyCodePageUri.MEDIA_BROWSER_LIBRARY.getURI(), LatencyTrackingLeg.RESPONSE);
                MESKNetworkTask mESKNetworkTask = this.$libraryNetworkTask;
                this.label = 1;
                obj = mESKNetworkTask.execute(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            Response response = (Response) obj;
            if (response == null) {
                unit = null;
            } else {
                AutoMediaBrowser autoMediaBrowser = this.this$0;
                BrushLatencyReportingUtil.INSTANCE.trackLatency(LatencyCodePageUri.MEDIA_BROWSER_LIBRARY.getURI(), LatencyTrackingLeg.PROCESSING);
                if (response.methods() == null || response.methods().isEmpty()) {
                    AutoLog autoLog = AutoLog.INSTANCE;
                    String TAG = AutoMediaBrowser.TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
                    autoLog.error(TAG, "no response from Curate Skyfire");
                }
                SkyfireContentMetadataConverter skyfireContentMetadataConverter = autoMediaBrowser.skyfireConverter;
                List<Method> listMethods = response.methods();
                Intrinsics.checkNotNullExpressionValue(listMethods, "response.methods()");
                Map<WidgetContext, List<AutoMediaItem>> mapConvertResponse = skyfireContentMetadataConverter.convertResponse(listMethods);
                if (mapConvertResponse == null) {
                    AutoLog autoLog2 = AutoLog.INSTANCE;
                    String TAG2 = AutoMediaBrowser.TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    autoLog2.error(TAG2, "Unable to convert Skyfire response");
                }
                autoMediaBrowser.onLibraryPageFetched(mapConvertResponse);
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                this.this$0.onLibraryPageFetched(null);
            }
            return Unit.INSTANCE;
        }
    }
}
