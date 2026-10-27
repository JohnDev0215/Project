package com.amazon.mp3.brush;

import android.content.Context;
import android.net.Uri;
import android.os.TransactionTooLargeException;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.brush.converters.BrushPageGridConverter;
import com.amazon.mp3.cache.AlbumCache;
import com.amazon.mp3.cache.BrushAlbumCacheHelper;
import com.amazon.mp3.detailpages.album.converter.AlbumBrushPageConverter;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.browse.BrowsePageFactory;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.data.AsinSubstitutionException;
import com.amazon.mp3.prime.data.AsinSubstitutionHandler;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.browse.page.PageRequest;
import com.amazon.music.browse.page.UiPage;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Page;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.amazon.musicensembleservice.brush.Track;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: compiled from: RxUiPage.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 $2\u00020\u0001:\u0001$B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001a\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\r\u001a\u00020\u000eH\u0002J)\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\u0005H\u0086\u0002J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u0018\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u001a\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/mp3/brush/RxUiPage;", "", "mUiPage", "Lcom/amazon/music/browse/page/UiPage;", "withResponseLatency", "", "withProcessingLatency", "(Lcom/amazon/music/browse/page/UiPage;ZZ)V", "isCacheExpired", "addAlbumTracksToLocalDb", "", "album", "Lcom/amazon/musicensembleservice/brush/Album;", "context", "Landroid/content/Context;", "addCatalogEntityToDb", "entity", "Lcom/amazon/musicensembleservice/brush/Entity;", "get", "Lrx/Observable;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "request", "Lcom/amazon/music/browse/page/PageRequest;", "getCached", "getAlbumPageConverter", "Lcom/amazon/mp3/detailpages/album/converter/AlbumBrushPageConverter;", "response", "Lcom/amazon/musicensembleservice/brush/PageResponse;", "invalidateCache", "profileId", "", "isPageExpired", "retrieveCachedAlbum", "Lcom/amazon/mp3/library/item/Album;", "uri", "Landroid/net/Uri;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RxUiPage {
    private static final String TAG = RxUiPage.class.getSimpleName();
    private boolean isCacheExpired;
    private final UiPage mUiPage;
    private final boolean withProcessingLatency;
    private final boolean withResponseLatency;

    public RxUiPage(UiPage mUiPage, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(mUiPage, "mUiPage");
        this.mUiPage = mUiPage;
        this.withResponseLatency = z;
        this.withProcessingLatency = z2;
    }

    public static /* synthetic */ Observable get$default(RxUiPage rxUiPage, PageRequest pageRequest, Context context, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return rxUiPage.get(pageRequest, context, z);
    }

    public final Observable<PageGridViewModel> get(final PageRequest request, final Context context, final boolean getCached) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(context, "context");
        Observable<PageGridViewModel> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.brush.RxUiPage$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RxUiPage.m442get$lambda2(context, this, request, getCached, (Subscriber) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …}\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: get$lambda-2, reason: not valid java name */
    public static final void m442get$lambda2(Context context, RxUiPage this$0, PageRequest request, boolean z, Subscriber subscriber) {
        BrushPageGridConverter albumPageConverter;
        PageGridViewModel pageGridViewModelConvert2;
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(request, "$request");
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String profileId = BrowsePageFactory.INSTANCE.getProfileId(context);
            if (!this$0.isCacheExpired) {
                this$0.isCacheExpired = this$0.isPageExpired(request, profileId);
            }
            if (this$0.withResponseLatency) {
                BrushLatencyReportingUtil brushLatencyReportingUtil = BrushLatencyReportingUtil.INSTANCE;
                String uri = request.getUri();
                Intrinsics.checkNotNullExpressionValue(uri, "request.uri");
                brushLatencyReportingUtil.trackLatency(uri, LatencyTrackingLeg.RESPONSE);
            }
            PageResponse pageResponse = this$0.mUiPage.get(request, Boolean.valueOf(z), profileId);
            if (this$0.withProcessingLatency) {
                BrushLatencyReportingUtil brushLatencyReportingUtil2 = BrushLatencyReportingUtil.INSTANCE;
                String uri2 = request.getUri();
                Intrinsics.checkNotNullExpressionValue(uri2, "request.uri");
                brushLatencyReportingUtil2.trackLatency(uri2, LatencyTrackingLeg.PROCESSING);
            }
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            if (Intrinsics.areEqual(request.getUri(), "home")) {
                MetricsLogger.sendHomePageResponseFlexEvent(context, Float.valueOf(jCurrentTimeMillis2), Boolean.valueOf(request.getNextToken() == null), Boolean.valueOf(this$0.isCacheExpired));
            }
            if (pageResponse == null) {
                albumPageConverter = null;
                pageGridViewModelConvert2 = null;
            } else {
                Entity entity = pageResponse.getEntity();
                albumPageConverter = entity instanceof Album ? this$0.getAlbumPageConverter((Album) entity, pageResponse, context) : new BrushPageGridConverter(context);
                pageGridViewModelConvert2 = albumPageConverter == null ? null : albumPageConverter.convert2((Page) pageResponse, (List<? extends BaseViewModel>) albumPageConverter.getChildrenModels((Page) pageResponse));
            }
            subscriber.onNext(pageGridViewModelConvert2);
            subscriber.onCompleted();
            AlbumBrushPageConverter albumBrushPageConverter = albumPageConverter instanceof AlbumBrushPageConverter ? (AlbumBrushPageConverter) albumPageConverter : null;
            if (albumBrushPageConverter == null) {
                return;
            }
            albumBrushPageConverter.clear();
        } catch (Exception e) {
            if (e instanceof BrowseException) {
                subscriber.onError(e);
                return;
            }
            if (e instanceof TransactionTooLargeException) {
                Log.error(TAG, "Transaction too large", e);
                subscriber.onError(e);
            } else if (e instanceof AsinSubstitutionException) {
                Log.warning(TAG, "Asin substitution attempt failed", e);
                subscriber.onError(e);
            } else {
                Log.error(TAG, "Unknown exception trying to fetch RxUiPage", e);
                subscriber.onError(e);
            }
        }
    }

    public final boolean isPageExpired(PageRequest request, String profileId) {
        Intrinsics.checkNotNullParameter(request, "request");
        Boolean boolIsCacheExpired = this.mUiPage.isCacheExpired(request, profileId);
        Intrinsics.checkNotNullExpressionValue(boolIsCacheExpired, "mUiPage.isCacheExpired(request, profileId)");
        return boolIsCacheExpired.booleanValue();
    }

    public final boolean invalidateCache(PageRequest request, String profileId) {
        Intrinsics.checkNotNullParameter(request, "request");
        this.isCacheExpired = true;
        Boolean boolInvalidateCache = this.mUiPage.invalidateCache(request, profileId);
        Intrinsics.checkNotNullExpressionValue(boolInvalidateCache, "mUiPage.invalidateCache(request, profileId)");
        return boolInvalidateCache.booleanValue();
    }

    private final AlbumBrushPageConverter getAlbumPageConverter(Album album, PageResponse response, Context context) throws Exception {
        AlbumCache albumCacheBuildCacheFromBrushAlbum;
        if (Feature.album_detail_performance_improvements_phase1.isEnabled()) {
            Log.debug(TAG, "Feature album_detail_performance_improvements_phase1 isEnabled");
            albumCacheBuildCacheFromBrushAlbum = BrushAlbumCacheHelper.INSTANCE.buildCacheFromBrushAlbum(album);
        } else {
            albumCacheBuildCacheFromBrushAlbum = null;
        }
        Uri collectionUri = albumCacheBuildCacheFromBrushAlbum == null ? null : albumCacheBuildCacheFromBrushAlbum.getAlbumUri();
        if (collectionUri == null) {
            ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
            List<Artist> artists = album.getArtists();
            Intrinsics.checkNotNullExpressionValue(artists, "album.artists");
            Artist artist = (Artist) CollectionsKt___CollectionsKt.firstOrNull((List) artists);
            String name = artist != null ? artist.getName() : null;
            String title = album.getTitle();
            String asin = album.getAsin();
            Intrinsics.checkNotNullExpressionValue(asin, "album.asin");
            collectionUri = contentPlaybackUtils.getAlbumUri(name, title, asin);
        }
        addCatalogEntityToDb(response.getEntity(), context);
        if (albumCacheBuildCacheFromBrushAlbum != null) {
            com.amazon.mp3.library.item.Album albumRetrieveCachedAlbum = retrieveCachedAlbum(collectionUri, context);
            if (albumRetrieveCachedAlbum != null) {
                BrushAlbumCacheHelper.INSTANCE.updateCacheWithLibraryAlbum(albumCacheBuildCacheFromBrushAlbum, albumRetrieveCachedAlbum);
            }
            BrushAlbumCacheHelper brushAlbumCacheHelper = BrushAlbumCacheHelper.INSTANCE;
            List<Track> tracks = album.getTracks();
            Intrinsics.checkNotNullExpressionValue(tracks, "album.tracks");
            brushAlbumCacheHelper.updateCacheWithBrushTrackUris(albumCacheBuildCacheFromBrushAlbum, tracks);
        }
        if (Feature.album_detail_performance_improvements_phase1B.isEnabled(true)) {
            Log.debug(TAG, "FeatureFlag caching isEnabled for Album DP");
        }
        return new AlbumBrushPageConverter(context, albumCacheBuildCacheFromBrushAlbum);
    }

    private final com.amazon.mp3.library.item.Album retrieveCachedAlbum(Uri uri, Context context) {
        return new CirrusSourceLibraryItemFactory(context).getAlbum(uri);
    }

    private final void addCatalogEntityToDb(Entity entity, Context context) throws Exception {
        try {
            if (entity instanceof Album) {
                addAlbumTracksToLocalDb((Album) entity, context);
            }
        } catch (Exception e) {
            if (e instanceof AsinSubstitutionException) {
                throw e;
            }
            Log.error(TAG, "Error adding catalog tracks from Brush entity to local database: exception = " + e + ", message " + ((Object) e.getMessage()));
        }
    }

    private final void addAlbumTracksToLocalDb(Album album, Context context) throws AsinSubstitutionException {
        Map<String, String> mapEmptyMap;
        List<PrimeTrack> catalogTracksToInsert = PrimeItemsTransformationUtil.convertAlbumTracksToPrimeTracks(album);
        Intrinsics.checkNotNullExpressionValue(catalogTracksToInsert, "catalogTracksToInsert");
        if (!catalogTracksToInsert.isEmpty()) {
            if (Feature.update_purchased_track_asin_substitution.isEnabled() && !Intrinsics.areEqual(album.getRequestedAsin(), album.getAsin())) {
                AsinSubstitutionHandler asinSubstitutionHandler = new AsinSubstitutionHandler();
                String asin = album.getAsin();
                Intrinsics.checkNotNullExpressionValue(asin, "album.asin");
                String requestedAsin = album.getRequestedAsin();
                Intrinsics.checkNotNullExpressionValue(requestedAsin, "album.requestedAsin");
                mapEmptyMap = asinSubstitutionHandler.handleAlbumAsinSubstitution(asin, requestedAsin, catalogTracksToInsert);
            } else {
                mapEmptyMap = MapsKt__MapsKt.emptyMap();
            }
            new CTAPrimeCache(context).insertPrimeTracks(album.getAsin(), null, null, catalogTracksToInsert, mapEmptyMap);
        }
    }
}
