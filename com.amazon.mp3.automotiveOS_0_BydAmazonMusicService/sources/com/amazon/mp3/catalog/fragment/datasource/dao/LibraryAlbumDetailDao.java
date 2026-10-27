package com.amazon.mp3.catalog.fragment.datasource.dao;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.catalog.fragment.converter.LibraryDescriptiveRowItemConverter;
import com.amazon.mp3.catalog.fragment.converter.LibraryDetailHeaderConverter;
import com.amazon.mp3.catalog.fragment.converter.LibraryDetailPageConverter;
import com.amazon.mp3.catalog.fragment.converter.LibraryDetailRefinementsConverter;
import com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil;
import com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryAlbumDetailDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B?\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0002\u0010\rJ*\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\t2\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fJ0\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00160\u001f2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\b\u0010\u0019\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014¨\u0006)"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/LibraryAlbumDetailDao;", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;", "context", "Landroid/content/Context;", "albumUri", "Landroid/net/Uri;", "pageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "supportsAudioBadging", "", "defaultFilters", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "(Landroid/content/Context;Landroid/net/Uri;Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;Ljava/lang/Boolean;Ljava/util/Set;)V", "contentConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDescriptiveRowItemConverter;", "headerConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailHeaderConverter;", "refinementConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailRefinementsConverter;", "Ljava/lang/Boolean;", "convertAlbumToPageModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "album", "Lcom/amazon/mp3/library/item/Album;", "isOffline", "albumTracks", "", "Lcom/amazon/mp3/library/item/MusicTrack;", "emptyPageGridViewModel", "fetchItem", "Lio/reactivex/Single;", "Lcom/amazon/mp3/library/item/AbstractItem;", "fetchViewModelItems", "pageIndex", "", "pageSize", "fetchType", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "nextPageToken", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryAlbumDetailDao implements PageViewModelDao {
    private final Uri albumUri;
    private final LibraryDescriptiveRowItemConverter contentConverter;
    private final Context context;
    private final Set<RefinementFilterType> defaultFilters;
    private final LibraryDetailHeaderConverter headerConverter;
    private final PageLoadLatencyCode pageLoadLatencyCode;
    private LibraryDetailRefinementsConverter refinementConverter;
    private final Boolean supportsAudioBadging;

    /* JADX WARN: Multi-variable type inference failed */
    public LibraryAlbumDetailDao(Context context, Uri albumUri, PageLoadLatencyCode pageLoadLatencyCode, Boolean bool, Set<? extends RefinementFilterType> set) {
        Intrinsics.checkNotNullParameter(albumUri, "albumUri");
        this.context = context;
        this.albumUri = albumUri;
        this.pageLoadLatencyCode = pageLoadLatencyCode;
        this.supportsAudioBadging = bool;
        this.defaultFilters = set;
        this.contentConverter = new LibraryDescriptiveRowItemConverter();
        this.headerConverter = new LibraryDetailHeaderConverter(context, null, 2, 0 == true ? 1 : 0);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public int getDefaultPageSize() {
        return PageViewModelDao.DefaultImpls.getDefaultPageSize(this);
    }

    public final Single<AbstractItem> fetchItem() {
        Single<AbstractItem> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.LibraryAlbumDetailDao$$ExternalSyntheticLambda1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                LibraryAlbumDetailDao.m549fetchItem$lambda0(this.f$0, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create{ emitter ->\n     …bum ?: Album())\n        }");
        return singleCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchItem$lambda-0, reason: not valid java name */
    public static final void m549fetchItem$lambda0(LibraryAlbumDetailDao this$0, SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        DBQueryUtil dBQueryUtil = DBQueryUtil.INSTANCE;
        Context context = this$0.context;
        Album albumConvertAlbumDetailCursorToAlbum = dBQueryUtil.convertAlbumDetailCursorToAlbum(dBQueryUtil.runAlbumDetailQuery(context == null ? null : context.getContentResolver(), this$0.albumUri, Intrinsics.areEqual(this$0.supportsAudioBadging, Boolean.TRUE)), this$0.albumUri);
        if (albumConvertAlbumDetailCursorToAlbum == null) {
            albumConvertAlbumDetailCursorToAlbum = new Album();
        }
        emitter.onSuccess(albumConvertAlbumDetailCursorToAlbum);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public synchronized Single<PageGridViewModel> fetchViewModelItems(int pageIndex, int pageSize, FetchType fetchType, String nextPageToken) {
        Single<PageGridViewModel> singleSubscribeOn;
        Intrinsics.checkNotNullParameter(fetchType, "fetchType");
        Single singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.LibraryAlbumDetailDao$$ExternalSyntheticLambda0
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                LibraryAlbumDetailDao.m550fetchViewModelItems$lambda4(this.f$0, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create { emitter ->\n    …ridViewModel())\n        }");
        singleSubscribeOn = singleCreate.subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(singleSubscribeOn, "single.subscribeOn(Schedulers.io())");
        return singleSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchViewModelItems$lambda-4, reason: not valid java name */
    public static final void m550fetchViewModelItems$lambda4(LibraryAlbumDetailDao this$0, SingleEmitter emitter) {
        Collection<MusicTrack> tracksWithDiscNumberSortOrder;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        PageLoadLatencyCode pageLoadLatencyCode = this$0.pageLoadLatencyCode;
        if (pageLoadLatencyCode != null) {
            LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.RESPONSE, pageLoadLatencyCode));
        }
        DBQueryUtil dBQueryUtil = DBQueryUtil.INSTANCE;
        Context context = this$0.context;
        Album albumConvertAlbumDetailCursorToAlbum = dBQueryUtil.convertAlbumDetailCursorToAlbum(dBQueryUtil.runAlbumDetailQuery(context == null ? null : context.getContentResolver(), this$0.albumUri, Intrinsics.areEqual(this$0.supportsAudioBadging, Boolean.TRUE)), this$0.albumUri);
        PageLoadLatencyCode pageLoadLatencyCode2 = this$0.pageLoadLatencyCode;
        if (pageLoadLatencyCode2 != null) {
            LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.PROCESSING, pageLoadLatencyCode2));
        }
        boolean zIsOffline = this$0.isOffline();
        List<? extends MusicTrack> list = (albumConvertAlbumDetailCursorToAlbum == null || (tracksWithDiscNumberSortOrder = albumConvertAlbumDetailCursorToAlbum.getTracksWithDiscNumberSortOrder()) == null) ? null : CollectionsKt___CollectionsKt.toList(tracksWithDiscNumberSortOrder);
        LikesServiceProxy.INSTANCE.getLikesStateForMusicTrack(this$0.context, list);
        LibraryDetailHeaderConverter.refreshUserSubscriptionValues$default(this$0.headerConverter, false, null, list, false, 11, null);
        PageGridViewModel pageGridViewModelConvertAlbumToPageModel = albumConvertAlbumDetailCursorToAlbum != null ? this$0.convertAlbumToPageModel(albumConvertAlbumDetailCursorToAlbum, zIsOffline, list) : null;
        if (pageGridViewModelConvertAlbumToPageModel == null) {
            pageGridViewModelConvertAlbumToPageModel = this$0.emptyPageGridViewModel();
        }
        emitter.onSuccess(pageGridViewModelConvertAlbumToPageModel);
    }

    private final PageGridViewModel convertAlbumToPageModel(Album album, boolean isOffline, List<? extends MusicTrack> albumTracks) {
        if (this.refinementConverter == null) {
            this.refinementConverter = new LibraryDetailRefinementsConverter(isOffline, this.defaultFilters, null, 4, null);
        }
        LibraryDetailPageConverter libraryDetailPageConverter = new LibraryDetailPageConverter(this.context, this.contentConverter, this.headerConverter, isOffline, this.refinementConverter, null, null, null, null, 480, null);
        return libraryDetailPageConverter.convert2((AbstractItem) album, (List<? extends BaseViewModel>) libraryDetailPageConverter.getAlbumChildrenModels(album, albumTracks));
    }

    private final PageGridViewModel emptyPageGridViewModel() {
        return new PageGridViewModel(null, null, null, null, CollectionsKt__CollectionsKt.emptyList(), 2, 0, null, null, null, null, 1807, null);
    }

    private final boolean isOffline() {
        return Intrinsics.areEqual("cirrus-local", MediaProvider.getSource(this.albumUri));
    }
}
