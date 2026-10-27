package com.amazon.mp3.catalog.fragment.datasource.dao;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.catalog.fragment.converter.LibraryDetailHeaderConverter;
import com.amazon.mp3.catalog.fragment.converter.LibraryDetailPageConverter;
import com.amazon.mp3.catalog.fragment.converter.LibraryVisualRowItemConverter;
import com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil;
import com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.provider.MediaProvider;
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
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryArtistDetailDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u001c\u0010\u0011\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J8\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tH\u0002J\b\u0010\u0019\u001a\u00020\u0013H\u0002J\"\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00172\u0006\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J0\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/LibraryArtistDetailDao;", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;", "context", "Landroid/content/Context;", "artistUri", "Landroid/net/Uri;", "pageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "followerCount", "", "catalogArtistName", "(Landroid/content/Context;Landroid/net/Uri;Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;Ljava/lang/String;Ljava/lang/String;)V", "buildAlbumCursor", "Landroid/database/Cursor;", "contentUri", "contentResolver", "Landroid/content/ContentResolver;", "buildArtistCursor", "convertArtistToPageModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "artist", "Lcom/amazon/mp3/library/item/Artist;", "albums", "", "Lcom/amazon/mp3/library/item/Album;", "emptyPageGridViewModel", "fetchAlbums", "fetchArtist", "fetchViewModelItems", "Lio/reactivex/Single;", "pageIndex", "", "pageSize", "fetchType", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "nextPageToken", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryArtistDetailDao implements PageViewModelDao {
    private final Uri artistUri;
    private final String catalogArtistName;
    private final Context context;
    private final String followerCount;
    private final PageLoadLatencyCode pageLoadLatencyCode;

    public LibraryArtistDetailDao(Context context, Uri artistUri, PageLoadLatencyCode pageLoadLatencyCode, String str, String str2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(artistUri, "artistUri");
        this.context = context;
        this.artistUri = artistUri;
        this.pageLoadLatencyCode = pageLoadLatencyCode;
        this.followerCount = str;
        this.catalogArtistName = str2;
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public int getDefaultPageSize() {
        return PageViewModelDao.DefaultImpls.getDefaultPageSize(this);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public synchronized Single<PageGridViewModel> fetchViewModelItems(int pageIndex, int pageSize, FetchType fetchType, String nextPageToken) {
        Single<PageGridViewModel> singleSubscribeOn;
        Intrinsics.checkNotNullParameter(fetchType, "fetchType");
        Single singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.LibraryArtistDetailDao$$ExternalSyntheticLambda0
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                LibraryArtistDetailDao.m552fetchViewModelItems$lambda2(this.f$0, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create { emitter ->\n\t\t\tp…yPageGridViewModel())\n\t\t}");
        singleSubscribeOn = singleCreate.subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(singleSubscribeOn, "single.subscribeOn(Schedulers.io())");
        return singleSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchViewModelItems$lambda-2, reason: not valid java name */
    public static final void m552fetchViewModelItems$lambda2(LibraryArtistDetailDao this$0, SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        PageLoadLatencyCode pageLoadLatencyCode = this$0.pageLoadLatencyCode;
        if (pageLoadLatencyCode != null) {
            LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.RESPONSE, pageLoadLatencyCode));
        }
        Artist artistFetchArtist = this$0.fetchArtist(this$0.artistUri, this$0.context.getContentResolver());
        List<Album> listFetchAlbums = this$0.fetchAlbums(this$0.artistUri, this$0.context.getContentResolver());
        PageLoadLatencyCode pageLoadLatencyCode2 = this$0.pageLoadLatencyCode;
        if (pageLoadLatencyCode2 != null) {
            LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.PROCESSING, pageLoadLatencyCode2));
        }
        PageGridViewModel pageGridViewModelConvertArtistToPageModel = (artistFetchArtist == null || listFetchAlbums == null) ? null : this$0.convertArtistToPageModel(artistFetchArtist, listFetchAlbums, this$0.followerCount, this$0.catalogArtistName);
        if (pageGridViewModelConvertArtistToPageModel == null) {
            pageGridViewModelConvertArtistToPageModel = this$0.emptyPageGridViewModel();
        }
        emitter.onSuccess(pageGridViewModelConvertArtistToPageModel);
    }

    private final Artist fetchArtist(Uri contentUri, ContentResolver contentResolver) {
        Cursor cursorBuildArtistCursor = buildArtistCursor(contentUri, contentResolver);
        if (cursorBuildArtistCursor == null) {
            return null;
        }
        return DBQueryUtil.INSTANCE.convertCursorToArtist(cursorBuildArtistCursor);
    }

    private final Cursor buildArtistCursor(Uri contentUri, ContentResolver contentResolver) {
        return DBQueryUtil.runArtistListQuery$default(DBQueryUtil.INSTANCE, contentResolver, contentUri, null, null, null, null, 60, null);
    }

    private final List<Album> fetchAlbums(Uri contentUri, ContentResolver contentResolver) {
        Uri artistAlbumsUri = contentUri.buildUpon().appendEncodedPath("albums").build();
        Intrinsics.checkNotNullExpressionValue(artistAlbumsUri, "artistAlbumsUri");
        Cursor cursorBuildAlbumCursor = buildAlbumCursor(artistAlbumsUri, contentResolver);
        if (cursorBuildAlbumCursor == null) {
            return null;
        }
        return DBQueryUtil.INSTANCE.convertCursorToAlbums(cursorBuildAlbumCursor);
    }

    private final Cursor buildAlbumCursor(Uri contentUri, ContentResolver contentResolver) {
        return DBQueryUtil.runAlbumListQuery$default(DBQueryUtil.INSTANCE, contentResolver, contentUri, null, null, null, null, 60, null);
    }

    private final PageGridViewModel convertArtistToPageModel(Artist artist, List<? extends Album> albums, String followerCount, String catalogArtistName) {
        LibraryVisualRowItemConverter libraryVisualRowItemConverter = new LibraryVisualRowItemConverter(this.context, null, Boolean.FALSE, false, false, null, null, 122, null);
        LibraryDetailHeaderConverter libraryDetailHeaderConverter = new LibraryDetailHeaderConverter(this.context, null, 2, null);
        LibraryDetailHeaderConverter.refreshUserSubscriptionValues$default(libraryDetailHeaderConverter, false, null, null, false, 15, null);
        LibraryDetailPageConverter libraryDetailPageConverter = new LibraryDetailPageConverter(this.context, libraryVisualRowItemConverter, libraryDetailHeaderConverter, Intrinsics.areEqual("cirrus-local", MediaProvider.getSource(this.artistUri)), null, null, null, null, null, 496, null);
        return libraryDetailPageConverter.convert2((AbstractItem) artist, (List<? extends BaseViewModel>) libraryDetailPageConverter.getArtistChildrenModels(artist, albums, followerCount, catalogArtistName));
    }

    private final PageGridViewModel emptyPageGridViewModel() {
        return new PageGridViewModel(null, null, null, null, CollectionsKt__CollectionsKt.emptyList(), 2, 0, null, null, null, null, 1807, null);
    }
}
