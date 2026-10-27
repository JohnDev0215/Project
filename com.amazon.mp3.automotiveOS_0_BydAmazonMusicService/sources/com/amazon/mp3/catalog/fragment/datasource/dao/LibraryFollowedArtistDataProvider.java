package com.amazon.mp3.catalog.fragment.datasource.dao;

import android.content.Context;
import com.amazon.mp3.catalog.fragment.converter.LibraryFollowedArtistPageConverter;
import com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao;
import com.amazon.mp3.catalog.fragment.model.FollowedArtistModel;
import com.amazon.mp3.catalog.fragment.provider.FireFlyServiceProvider;
import com.amazon.mp3.catalog.fragment.provider.FollowedArtistRetriever;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.music.connect.firefly.FireFlyService;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryFollowedArtistDataProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J0\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/LibraryFollowedArtistDataProvider;", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;", "context", "Landroid/content/Context;", "pageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "(Landroid/content/Context;Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;)V", "pageConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryFollowedArtistPageConverter;", "fetchViewModelItems", "Lio/reactivex/Single;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageIndex", "", "pageSize", "fetchType", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "nextPageToken", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryFollowedArtistDataProvider implements PageViewModelDao {
    private final Context context;
    private final LibraryFollowedArtistPageConverter pageConverter;
    private final PageLoadLatencyCode pageLoadLatencyCode;

    public LibraryFollowedArtistDataProvider(Context context, PageLoadLatencyCode pageLoadLatencyCode) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.pageLoadLatencyCode = pageLoadLatencyCode;
        this.pageConverter = new LibraryFollowedArtistPageConverter(context);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public int getDefaultPageSize() {
        return PageViewModelDao.DefaultImpls.getDefaultPageSize(this);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public Single<PageGridViewModel> fetchViewModelItems(final int pageIndex, int pageSize, FetchType fetchType, final String nextPageToken) {
        Intrinsics.checkNotNullParameter(fetchType, "fetchType");
        final FireFlyServiceProvider fireFlyServiceProvider = new FireFlyServiceProvider(this.context);
        Single singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.LibraryFollowedArtistDataProvider$$ExternalSyntheticLambda0
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                LibraryFollowedArtistDataProvider.m553fetchViewModelItems$lambda3(this.f$0, fireFlyServiceProvider, nextPageToken, pageIndex, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create { emitter ->\n    …)\n            }\n        }");
        Single<PageGridViewModel> singleSubscribeOn = singleCreate.subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(singleSubscribeOn, "single.subscribeOn(Schedulers.io())");
        return singleSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchViewModelItems$lambda-3, reason: not valid java name */
    public static final void m553fetchViewModelItems$lambda3(LibraryFollowedArtistDataProvider this$0, FireFlyServiceProvider fireFlyServiceProvider, String str, int i, SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fireFlyServiceProvider, "$fireFlyServiceProvider");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        PageLoadLatencyCode pageLoadLatencyCode = this$0.pageLoadLatencyCode;
        if (pageLoadLatencyCode != null) {
            LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.RESPONSE, pageLoadLatencyCode));
        }
        FireFlyService fireFlyService = fireFlyServiceProvider.get();
        if (fireFlyService == null) {
            return;
        }
        FollowedArtistRetriever followedArtistRetriever = new FollowedArtistRetriever(fireFlyService);
        if (str == null) {
            str = "";
        }
        FollowedArtistModel followedArtist = followedArtistRetriever.getFollowedArtist(str, 50);
        PageLoadLatencyCode pageLoadLatencyCode2 = this$0.pageLoadLatencyCode;
        if (pageLoadLatencyCode2 != null) {
            LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.PROCESSING, pageLoadLatencyCode2));
        }
        LibraryFollowedArtistPageConverter libraryFollowedArtistPageConverter = this$0.pageConverter;
        BaseViewContainerModel<? extends BaseViewModel> baseViewContainerModelConvert2 = libraryFollowedArtistPageConverter.convert2(followedArtist, (List<? extends BaseViewModel>) libraryFollowedArtistPageConverter.getChildrenModels(followedArtist, i));
        if (baseViewContainerModelConvert2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.PageGridViewModel");
        }
        emitter.onSuccess((PageGridViewModel) baseViewContainerModelConvert2);
    }
}
