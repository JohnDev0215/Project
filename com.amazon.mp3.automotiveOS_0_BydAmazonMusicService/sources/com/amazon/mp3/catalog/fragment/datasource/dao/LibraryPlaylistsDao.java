package com.amazon.mp3.catalog.fragment.datasource.dao;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.converter.LibraryListPageConverter;
import com.amazon.mp3.catalog.fragment.converter.LibraryVisualRowItemConverter;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: LibraryPlaylistsDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001BC\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\u0002\u0010\u0010J0\u0010$\u001a\b\u0012\u0004\u0012\u00020!0%2\u0006\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u001e\u0010,\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0003\u0018\u00010\u00142\u0006\u0010-\u001a\u00020\u0007H\u0002R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0013\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0003\u0018\u00010\u0014X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0017\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0003\u0018\u00010\u0014X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR,\u0010\u001d\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u001f\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001e8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/LibraryPlaylistsDao;", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/RefinablePageViewModelDao;", "Lcom/amazon/mp3/library/item/Playlist;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "context", "Landroid/content/Context;", "onlineUri", "Landroid/net/Uri;", "offlineUri", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "defaultFilters", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "(Landroid/content/Context;Landroid/net/Uri;Landroid/net/Uri;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;Ljava/util/Set;)V", "contentConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryVisualRowItemConverter;", "offlineDataSource", "Lkotlinx/coroutines/Deferred;", "getOfflineDataSource", "()Lkotlinx/coroutines/Deferred;", "onlineDataSource", "getOnlineDataSource", "pageConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryListPageConverter;", "getPageLoadLatencyCode", "()Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "pageModelConverter", "Lkotlin/Function2;", "", "", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "getPageModelConverter", "()Lkotlin/jvm/functions/Function2;", "fetchViewModelItems", "Lio/reactivex/Single;", "pageIndex", "pageSize", "fetchType", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "nextPageToken", "", "getDataSourceForUri", "uri", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryPlaylistsDao extends RefinablePageViewModelDao<Playlist, DatabaseSource<Playlist>> {
    private final LibraryVisualRowItemConverter contentConverter;
    private final Context context;
    private final Set<RefinementFilterType> defaultFilters;
    private final Deferred<DatabaseSource<Playlist>> offlineDataSource;
    private final Uri offlineUri;
    private final Deferred<DatabaseSource<Playlist>> onlineDataSource;
    private final Uri onlineUri;
    private final LibraryListPageConverter pageConverter;
    private final PageLoadLatencyCode pageLoadLatencyCode;
    private final PageType pageType;

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao
    protected PageLoadLatencyCode getPageLoadLatencyCode() {
        return this.pageLoadLatencyCode;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LibraryPlaylistsDao(Context context, Uri onlineUri, Uri offlineUri, PageType pageType, PageLoadLatencyCode pageLoadLatencyCode, Set<? extends RefinementFilterType> set) {
        LibraryVisualRowItemConverter libraryVisualRowItemConverter;
        Set set2;
        Uri uri;
        Uri uri2;
        super(pageLoadLatencyCode, false, false, 6, null);
        Intrinsics.checkNotNullParameter(onlineUri, "onlineUri");
        Intrinsics.checkNotNullParameter(offlineUri, "offlineUri");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.context = context;
        this.onlineUri = onlineUri;
        this.offlineUri = offlineUri;
        this.pageType = pageType;
        this.pageLoadLatencyCode = pageLoadLatencyCode;
        this.defaultFilters = set;
        if (context == null) {
            libraryVisualRowItemConverter = null;
            set2 = set;
            uri = offlineUri;
            uri2 = onlineUri;
        } else {
            set2 = set;
            uri = offlineUri;
            uri2 = onlineUri;
            libraryVisualRowItemConverter = new LibraryVisualRowItemConverter(context, null, Boolean.FALSE, false, false, null, null, 122, null);
        }
        this.contentConverter = libraryVisualRowItemConverter;
        this.pageConverter = new LibraryListPageConverter(context, libraryVisualRowItemConverter, true, set, null, 16, null);
        if (set2 != null && set2.contains(RefinementFilterType.DOWNLOADED)) {
            setUseOnlineSource(false);
        }
        if (libraryVisualRowItemConverter != null) {
            libraryVisualRowItemConverter.setArtworkId(Integer.valueOf(R.id.PlaylistArtwork));
        }
        if (libraryVisualRowItemConverter != null) {
            libraryVisualRowItemConverter.setTitleId(Integer.valueOf(R.id.PlaylistName));
        }
        this.onlineDataSource = getDataSourceForUri(uri2);
        this.offlineDataSource = getDataSourceForUri(uri);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao
    protected Deferred<DatabaseSource<Playlist>> getOnlineDataSource() {
        return this.onlineDataSource;
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao
    protected Deferred<DatabaseSource<Playlist>> getOfflineDataSource() {
        return this.offlineDataSource;
    }

    private final Deferred<DatabaseSource<Playlist>> getDataSourceForUri(Uri uri) {
        if (this.context == null) {
            return null;
        }
        return BuildersKt__Builders_commonKt.async$default(GlobalScope.INSTANCE, null, null, new LibraryPlaylistsDao$getDataSourceForUri$1$1(uri, this, null), 3, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao
    protected Function2<List<? extends Playlist>, Integer, PageGridViewModel> getPageModelConverter() {
        return new Function2<List<? extends Playlist>, Integer, PageGridViewModel>() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.LibraryPlaylistsDao$pageModelConverter$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ PageGridViewModel mo4invoke(List<? extends Playlist> list, Integer num) {
                return invoke(list, num.intValue());
            }

            public final PageGridViewModel invoke(List<? extends Playlist> data, int i) {
                Intrinsics.checkNotNullParameter(data, "data");
                return this.this$0.pageConverter.convert2((List<? extends AbstractItem>) data, (List<? extends BaseViewModel>) this.this$0.pageConverter.getPlaylistChildrenModels(data, i));
            }
        };
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao, com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public synchronized Single<PageGridViewModel> fetchViewModelItems(final int pageIndex, int pageSize, FetchType fetchType, String nextPageToken) {
        Single<PageGridViewModel> singleSubscribeOn;
        Intrinsics.checkNotNullParameter(fetchType, "fetchType");
        Single singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.LibraryPlaylistsDao$$ExternalSyntheticLambda0
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws InterruptedException {
                LibraryPlaylistsDao.m555fetchViewModelItems$lambda2(this.f$0, pageIndex, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create {\n               …          }\n            }");
        singleSubscribeOn = singleCreate.subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(singleSubscribeOn, "single.subscribeOn(Schedulers.io())");
        return singleSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchViewModelItems$lambda-2, reason: not valid java name */
    public static final void m555fetchViewModelItems$lambda2(LibraryPlaylistsDao this$0, int i, SingleEmitter it) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        BuildersKt__BuildersKt.runBlocking$default(null, new LibraryPlaylistsDao$fetchViewModelItems$single$1$1(this$0, it, i, null), 1, null);
    }
}
