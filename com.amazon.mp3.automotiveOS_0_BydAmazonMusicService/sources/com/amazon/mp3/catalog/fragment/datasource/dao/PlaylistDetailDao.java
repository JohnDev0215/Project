package com.amazon.mp3.catalog.fragment.datasource.dao;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration;
import com.amazon.mp3.catalog.fragment.converter.LibraryContentMetadataConverter;
import com.amazon.mp3.catalog.fragment.converter.LibraryDetailButtonNavigatorConverter;
import com.amazon.mp3.catalog.fragment.converter.LibraryDetailHeaderConverter;
import com.amazon.mp3.catalog.fragment.converter.LibraryDetailPageConverter;
import com.amazon.mp3.catalog.fragment.converter.LibraryVisualRowItemConverter;
import com.amazon.mp3.catalog.fragment.converter.PlaylistDetailAddSongsButtonNavigatorConverter;
import com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil;
import com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistReorderingAction;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: PlaylistDetailDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0007\u0018\u0000 Z2\u00020\u0001:\u0001ZBU\u0012\u0006\u0010#\u001a\u00020\"\u0012\u0006\u0010%\u001a\u00020\u001e\u0012\u0006\u0010'\u001a\u00020\b\u0012\u0006\u0010)\u001a\u00020\b\u0012\b\u0010*\u001a\u0004\u0018\u00010\b\u0012\b\u0010-\u001a\u0004\u0018\u00010,\u0012\b\b\u0002\u00100\u001a\u00020/\u0012\u0006\u00102\u001a\u00020\b\u0012\u0006\u00104\u001a\u000203¢\u0006\u0004\bX\u0010YJ\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J,\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\bH\u0002J\b\u0010\f\u001a\u00020\nH\u0002J\u0018\u0010\u000f\u001a\u00020\u000e2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0002J0\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00172\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0019\u001a\u0004\u0018\u00010\nJ\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00150\u001c2\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0010J\u0012\u0010 \u001a\u0004\u0018\u00010\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eJ\u000e\u0010!\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u001eR\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010(R\u0016\u0010*\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010-\u001a\u0004\u0018\u00010,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u0010(R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010@\u001a\u00020?8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010C\u001a\u00020B8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010DR\u0016\u0010E\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bE\u0010(R$\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010F\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001b\u0010P\u001a\u00020K8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bL\u0010M\u001a\u0004\bN\u0010OR\u0014\u0010R\u001a\u00020Q8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\"\u0010U\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00100T8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bU\u0010VR\u001e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010W¨\u0006["}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/PlaylistDetailDao;", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;", "Lcom/amazon/mp3/library/item/AbstractItem;", "fetchPlaylist", Environment.PLAYLIST_PATH, "", "Lcom/amazon/mp3/library/item/MusicTrack;", "tracks", "", "isOnDemandGoldenPlaylist", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "convertToViewModel", "emptyPageGridViewModel", "trackList", "", "updateTrackCache", "", "pageIndex", "pageSize", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "fetchType", "", "nextPageToken", "Lio/reactivex/Single;", "fetchViewModelItems", "fetchPageModel", "from", "to", "Lio/reactivex/Observable;", "reorderPlaylist", "Landroid/net/Uri;", "uri", "getMusicTrack", "updatePlaylistUri", "Landroid/content/Context;", "context", "Landroid/content/Context;", "playlistUri", "Landroid/net/Uri;", "isCatalog", "Z", "isSourceLocal", "hasVideoStory", "Ljava/lang/Boolean;", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "pageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "Lcom/amazon/mp3/library/item/factory/LibraryItemFactory;", "libraryItemFactory", "Lcom/amazon/mp3/library/item/factory/LibraryItemFactory;", "isVideo", "Lcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;", "configuration", "Lcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryVisualRowItemConverter;", "contentConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryVisualRowItemConverter;", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailHeaderConverter;", "headerConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailHeaderConverter;", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailPageConverter;", "pageGridConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailPageConverter;", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailButtonNavigatorConverter;", "buttonNavigatorConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailButtonNavigatorConverter;", "Lcom/amazon/mp3/catalog/fragment/converter/PlaylistDetailAddSongsButtonNavigatorConverter;", "addSongsButtonNavigatorConverter", "Lcom/amazon/mp3/catalog/fragment/converter/PlaylistDetailAddSongsButtonNavigatorConverter;", "initialPageLoadSent", "Lcom/amazon/mp3/library/item/AbstractItem;", "getPlaylist", "()Lcom/amazon/mp3/library/item/AbstractItem;", "setPlaylist", "(Lcom/amazon/mp3/library/item/AbstractItem;)V", "Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistReorderingAction;", "playlistReorderingAction$delegate", "Lkotlin/Lazy;", "getPlaylistReorderingAction", "()Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistReorderingAction;", "playlistReorderingAction", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "cachedCapabilityActionProviderImpl", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "", "trackIndexMap", "Ljava/util/Map;", "Ljava/util/List;", "<init>", "(Landroid/content/Context;Landroid/net/Uri;ZZLjava/lang/Boolean;Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;Lcom/amazon/mp3/library/item/factory/LibraryItemFactory;ZLcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaylistDetailDao implements PageViewModelDao {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final String TAG;
    private static final Logger logger;
    private final PlaylistDetailAddSongsButtonNavigatorConverter addSongsButtonNavigatorConverter;
    private final LibraryDetailButtonNavigatorConverter buttonNavigatorConverter;
    private final CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl;
    private final PlaylistModelConfiguration configuration;
    private final LibraryVisualRowItemConverter contentConverter;
    private final Context context;
    private final Boolean hasVideoStory;
    private final LibraryDetailHeaderConverter headerConverter;
    private boolean initialPageLoadSent;
    private final boolean isCatalog;
    private final boolean isSourceLocal;
    private final boolean isVideo;
    private final LibraryItemFactory libraryItemFactory;
    private final LibraryDetailPageConverter pageGridConverter;
    private final PageLoadLatencyCode pageLoadLatencyCode;
    private AbstractItem playlist;

    /* JADX INFO: renamed from: playlistReorderingAction$delegate, reason: from kotlin metadata */
    private final Lazy playlistReorderingAction;
    private Uri playlistUri;
    private Map<Uri, Integer> trackIndexMap;
    private List<? extends MusicTrack> trackList;

    public PlaylistDetailDao(Context context, Uri playlistUri, boolean z, boolean z2, Boolean bool, PageLoadLatencyCode pageLoadLatencyCode, LibraryItemFactory libraryItemFactory, boolean z3, PlaylistModelConfiguration configuration) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        Intrinsics.checkNotNullParameter(libraryItemFactory, "libraryItemFactory");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.context = context;
        this.playlistUri = playlistUri;
        this.isCatalog = z;
        this.isSourceLocal = z2;
        this.hasVideoStory = bool;
        this.pageLoadLatencyCode = pageLoadLatencyCode;
        this.libraryItemFactory = libraryItemFactory;
        this.isVideo = z3;
        this.configuration = configuration;
        LibraryDetailHeaderConverter libraryDetailHeaderConverter = new LibraryDetailHeaderConverter(context, 0 == true ? 1 : 0, 2, 0 == true ? 1 : 0);
        this.headerConverter = libraryDetailHeaderConverter;
        this.playlistReorderingAction = LazyKt__LazyJVMKt.lazy(new Function0<PlaylistReorderingAction>() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.PlaylistDetailDao$playlistReorderingAction$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlaylistReorderingAction invoke() {
                return new PlaylistReorderingAction(this.this$0.context);
            }
        });
        CachedCapabilityActionProviderImpl cachedActionProviderImpl = configuration.getCachedActionProviderImpl();
        CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = cachedActionProviderImpl == null ? new CachedCapabilityActionProviderImpl(null, 1, null) : cachedActionProviderImpl;
        this.cachedCapabilityActionProviderImpl = cachedCapabilityActionProviderImpl;
        this.trackIndexMap = new LinkedHashMap();
        LibraryVisualRowItemConverter libraryVisualRowItemConverter = new LibraryVisualRowItemConverter(context, Boolean.valueOf(z), null, true ^ z, false, z3 ? 0 : null, cachedCapabilityActionProviderImpl, 20, null);
        this.contentConverter = libraryVisualRowItemConverter;
        LibraryDetailButtonNavigatorConverter libraryDetailButtonNavigatorConverter = new LibraryDetailButtonNavigatorConverter(configuration);
        this.buttonNavigatorConverter = libraryDetailButtonNavigatorConverter;
        PlaylistDetailAddSongsButtonNavigatorConverter playlistDetailAddSongsButtonNavigatorConverter = new PlaylistDetailAddSongsButtonNavigatorConverter(context, configuration);
        this.addSongsButtonNavigatorConverter = playlistDetailAddSongsButtonNavigatorConverter;
        this.pageGridConverter = new LibraryDetailPageConverter(context, libraryVisualRowItemConverter, libraryDetailHeaderConverter, z2, null, libraryDetailButtonNavigatorConverter, playlistDetailAddSongsButtonNavigatorConverter, configuration, cachedCapabilityActionProviderImpl, 16, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public int getDefaultPageSize() {
        return PageViewModelDao.DefaultImpls.getDefaultPageSize(this);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ PlaylistDetailDao(Context context, Uri uri, boolean z, boolean z2, Boolean bool, PageLoadLatencyCode pageLoadLatencyCode, LibraryItemFactory libraryItemFactory, boolean z3, PlaylistModelConfiguration playlistModelConfiguration, int i, DefaultConstructorMarker defaultConstructorMarker) {
        LibraryItemFactory libraryItemFactory2;
        if ((i & 64) != 0) {
            LibraryItemFactory libraryItemFactory3 = AmazonApplication.getLibraryItemFactory();
            Intrinsics.checkNotNullExpressionValue(libraryItemFactory3, "getLibraryItemFactory()");
            libraryItemFactory2 = libraryItemFactory3;
        } else {
            libraryItemFactory2 = libraryItemFactory;
        }
        this(context, uri, z, z2, bool, pageLoadLatencyCode, libraryItemFactory2, z3, playlistModelConfiguration);
    }

    public final AbstractItem getPlaylist() {
        return this.playlist;
    }

    private final PlaylistReorderingAction getPlaylistReorderingAction() {
        return (PlaylistReorderingAction) this.playlistReorderingAction.getValue();
    }

    /* JADX INFO: compiled from: PlaylistDetailDao.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J*\u0010\u000e\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J*\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013H\u0002J8\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0010\u001a\u00020\u0011J2\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/PlaylistDetailDao$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "buildCatalogTrackCursor", "Landroid/database/Cursor;", "contentUri", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "buildUserTrackCursor", "playlistUri", "isSourceLocal", "", "fetchCatalogPlaylistTracks", "", "Lcom/amazon/mp3/library/item/MusicTrack;", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/library/item/AbstractItem;", "fetchTrackLikes", "", "tracks", "fetchTracks", "isCatalog", "fetchUserPlaylistTracks", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<MusicTrack> fetchTracks(Context context, boolean isCatalog, Uri playlistUri, AbstractItem playlist, boolean isSourceLocal) {
            List<MusicTrack> listFetchUserPlaylistTracks;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (isCatalog) {
                listFetchUserPlaylistTracks = fetchCatalogPlaylistTracks(context, playlistUri, playlist);
            } else {
                listFetchUserPlaylistTracks = fetchUserPlaylistTracks(context, playlistUri, playlist, isSourceLocal);
            }
            Log.debugBuildOnly(PlaylistDetailDao.TAG, "fetchTracks takes " + (System.currentTimeMillis() - jCurrentTimeMillis) + " before fetchTrackLikes", new Object[0]);
            if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
                try {
                    fetchTrackLikes(context, listFetchUserPlaylistTracks);
                } catch (Exception e) {
                    PlaylistDetailDao.logger.error(e.getMessage());
                }
            }
            Log.debugBuildOnly(PlaylistDetailDao.TAG, "fetchTracks takes " + (System.currentTimeMillis() - jCurrentTimeMillis) + " after fetchTrackLikes", new Object[0]);
            return listFetchUserPlaylistTracks;
        }

        private final void fetchTrackLikes(Context context, List<? extends MusicTrack> tracks) {
            LikesServiceProxy.INSTANCE.getLikesStateForMusicTrack(context, tracks);
        }

        private final List<MusicTrack> fetchCatalogPlaylistTracks(Context context, Uri playlistUri, AbstractItem playlist) {
            Uri[] uriArr;
            List<MusicTrack> listConvertTracksCursorToTracks;
            Map map;
            MusicTrack musicTrack;
            Uri playlistTracksUri = ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(playlistUri);
            if (playlistTracksUri == null) {
                playlistTracksUri = playlistUri.buildUpon().appendEncodedPath("tracks").build();
            }
            LibraryItemFactory libraryItemFactory = AmazonApplication.getLibraryItemFactory();
            Intrinsics.checkNotNullExpressionValue(playlistTracksUri, "playlistTracksUri");
            Cursor cursorBuildCatalogTrackCursor = buildCatalogTrackCursor(playlistTracksUri, context);
            ArrayList arrayList = null;
            List<MusicTrack> listConvertTracksCursorToTracks2 = cursorBuildCatalogTrackCursor == null ? null : DBQueryUtil.INSTANCE.convertTracksCursorToTracks(cursorBuildCatalogTrackCursor, playlist);
            if (listConvertTracksCursorToTracks2 == null) {
                uriArr = null;
            } else {
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listConvertTracksCursorToTracks2, 10));
                Iterator<T> it = listConvertTracksCursorToTracks2.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((MusicTrack) it.next()).getContentUri());
                }
                Object[] array = arrayList2.toArray(new Uri[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                }
                uriArr = (Uri[]) array;
            }
            if (libraryItemFactory.getPrimePlaylistTracksCursor(uriArr, context) == null || (listConvertTracksCursorToTracks = DBQueryUtil.INSTANCE.convertTracksCursorToTracks(cursorBuildCatalogTrackCursor, playlist)) == null) {
                map = null;
            } else {
                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listConvertTracksCursorToTracks, 10));
                for (MusicTrack musicTrack2 : listConvertTracksCursorToTracks) {
                    arrayList3.add(TuplesKt.to(musicTrack2.getAsin(), musicTrack2));
                }
                map = MapsKt__MapsKt.toMap(arrayList3);
            }
            if (listConvertTracksCursorToTracks2 != null) {
                arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listConvertTracksCursorToTracks2, 10));
                for (MusicTrack musicTrack3 : listConvertTracksCursorToTracks2) {
                    if (map != null && (musicTrack = (MusicTrack) map.get(musicTrack3.getAsin())) != null) {
                        musicTrack3 = musicTrack;
                    }
                    arrayList.add(musicTrack3);
                }
            }
            return arrayList;
        }

        private final List<MusicTrack> fetchUserPlaylistTracks(Context context, Uri playlistUri, AbstractItem playlist, boolean isSourceLocal) {
            Uri playlistTracksUri = playlistUri.buildUpon().appendEncodedPath("tracks").build();
            Intrinsics.checkNotNullExpressionValue(playlistTracksUri, "playlistTracksUri");
            Cursor cursorBuildUserTrackCursor = buildUserTrackCursor(playlistTracksUri, playlistUri, context, isSourceLocal);
            if (cursorBuildUserTrackCursor == null) {
                return null;
            }
            return DBQueryUtil.INSTANCE.convertTracksCursorToTracks(cursorBuildUserTrackCursor, playlist);
        }

        private final Cursor buildUserTrackCursor(Uri contentUri, Uri playlistUri, Context context, boolean isSourceLocal) {
            return DBQueryUtil.runTrackQuery$default(DBQueryUtil.INSTANCE, context.getContentResolver(), contentUri, null, isSourceLocal ? null : PlaylistUtil.getSelection(playlistUri), isSourceLocal ? null : PlaylistUtil.getSelectionArgs(playlistUri), MediaProvider.UdoPlaylists.isUdoPlaylist(playlistUri) ? "udo" : null, 4, null);
        }

        private final Cursor buildCatalogTrackCursor(Uri contentUri, Context context) {
            return DBQueryUtil.runTrackQuery$default(DBQueryUtil.INSTANCE, context.getContentResolver(), contentUri, null, null, null, "order_num", 4, null);
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        TAG = PlaylistDetailDao.class.getSimpleName();
        logger = LoggerFactory.getLogger(companion.getClass().getName());
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public synchronized Single<PageGridViewModel> fetchViewModelItems(int pageIndex, int pageSize, FetchType fetchType, String nextPageToken) {
        Single<PageGridViewModel> singleSubscribeOn;
        Intrinsics.checkNotNullParameter(fetchType, "fetchType");
        Single singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.PlaylistDetailDao$$ExternalSyntheticLambda0
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                PlaylistDetailDao.m568fetchViewModelItems$lambda0(this.f$0, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create { emitter ->\n    …ridViewModel())\n        }");
        singleSubscribeOn = singleCreate.subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(singleSubscribeOn, "single.subscribeOn(Schedulers.io())");
        return singleSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchViewModelItems$lambda-0, reason: not valid java name */
    public static final void m568fetchViewModelItems$lambda0(PlaylistDetailDao this$0, SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        PageGridViewModel pageGridViewModelFetchPageModel = this$0.fetchPageModel();
        if (pageGridViewModelFetchPageModel == null) {
            pageGridViewModelFetchPageModel = this$0.emptyPageGridViewModel();
        }
        emitter.onSuccess(pageGridViewModelFetchPageModel);
    }

    public final PageGridViewModel fetchPageModel() {
        String asin;
        PageLoadLatencyCode pageLoadLatencyCode;
        if (!this.initialPageLoadSent && (pageLoadLatencyCode = this.pageLoadLatencyCode) != null) {
            LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.RESPONSE, pageLoadLatencyCode));
        }
        AbstractItem abstractItemFetchPlaylist = fetchPlaylist();
        this.playlist = abstractItemFetchPlaylist;
        List<MusicTrack> listFetchTracks = INSTANCE.fetchTracks(this.context, this.isCatalog, this.playlistUri, abstractItemFetchPlaylist, this.isSourceLocal);
        if (!this.initialPageLoadSent) {
            this.initialPageLoadSent = true;
            PageLoadLatencyCode pageLoadLatencyCode2 = this.pageLoadLatencyCode;
            if (pageLoadLatencyCode2 != null) {
                LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.PROCESSING, pageLoadLatencyCode2));
            }
        }
        this.cachedCapabilityActionProviderImpl.resetCacheForNewRequest();
        AbstractItem abstractItem = this.playlist;
        boolean z = false;
        if (abstractItem != null && abstractItem.isAnyCatalog()) {
            this.contentConverter.setCanDrag(Boolean.FALSE);
        }
        this.contentConverter.setPlaylist(this.playlist);
        AbstractItem abstractItem2 = this.playlist;
        if (abstractItem2 != null) {
            this.contentConverter.setContainerMetadata(LibraryContentMetadataConverter.convert$default(abstractItem2, null, 2, null));
        }
        boolean zIsIvy = UserSubscriptionUtil.getUserSubscription().isIvy();
        boolean zIsNightwingOnly = UserSubscriptionUtil.getUserSubscription().isNightwingOnly();
        boolean zIsOnDemandPlaylistsEnabled = AmazonApplication.getCapabilities().isOnDemandPlaylistsEnabled();
        this.contentConverter.refreshUserSubscriptionValues(zIsIvy, zIsNightwingOnly);
        AbstractItem abstractItem3 = this.playlist;
        if (abstractItem3 != null && (asin = abstractItem3.getAsin()) != null) {
            z = zIsOnDemandPlaylistsEnabled && zIsNightwingOnly && GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandPlaylist(asin);
        }
        this.contentConverter.setOnDemandGoldenPlaylist(z);
        updateTrackCache(listFetchTracks);
        this.headerConverter.refreshUserSubscriptionValues(zIsNightwingOnly, this.playlist, listFetchTracks, z);
        return convertToViewModel(this.playlist, listFetchTracks, z);
    }

    public final Observable<String> reorderPlaylist(int from, int to) {
        return getPlaylistReorderingAction().reorderPlaylist(this.playlistUri, from, to);
    }

    public final MusicTrack getMusicTrack(Uri uri) {
        Integer num;
        if (uri == null || (num = this.trackIndexMap.get(uri)) == null) {
            return null;
        }
        int iIntValue = num.intValue();
        List<? extends MusicTrack> list = this.trackList;
        if (list == null) {
            return null;
        }
        return list.get(iIntValue);
    }

    public final void updatePlaylistUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.playlistUri = uri;
    }

    private final AbstractItem fetchPlaylist() {
        if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(this.playlistUri)) {
            return this.libraryItemFactory.getSharedUserPlaylist(this.playlistUri);
        }
        if (this.isCatalog) {
            CatalogPlaylist catalogPlaylist = this.libraryItemFactory.getCatalogPlaylist(this.playlistUri);
            if (catalogPlaylist == null) {
                return catalogPlaylist;
            }
            catalogPlaylist.setHasVideoStory(Intrinsics.areEqual(this.hasVideoStory, Boolean.TRUE));
            return catalogPlaylist;
        }
        return this.libraryItemFactory.getPlaylist(this.playlistUri);
    }

    private final PageGridViewModel convertToViewModel(AbstractItem playlist, List<? extends MusicTrack> tracks, boolean isOnDemandGoldenPlaylist) {
        if (playlist == null) {
            return null;
        }
        List<BaseViewModel> playlistChildrenModels = this.pageGridConverter.getPlaylistChildrenModels(playlist, tracks);
        return this.pageGridConverter.convert2(playlist, playlistChildrenModels != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) playlistChildrenModels) : null);
    }

    private final PageGridViewModel emptyPageGridViewModel() {
        return new PageGridViewModel(null, null, null, null, CollectionsKt__CollectionsKt.emptyList(), 2, 0, null, null, null, null, 1807, null);
    }

    private final synchronized void updateTrackCache(List<? extends MusicTrack> trackList) {
        this.trackIndexMap.clear();
        this.trackList = trackList;
        if (trackList == null) {
            return;
        }
        int i = 0;
        for (MusicTrack musicTrack : trackList) {
            int i2 = i + 1;
            Map<Uri, Integer> map = this.trackIndexMap;
            Uri contentUri = musicTrack.getContentUri();
            Intrinsics.checkNotNullExpressionValue(contentUri, "track.contentUri");
            map.put(contentUri, Integer.valueOf(i));
            i = i2;
        }
    }
}
