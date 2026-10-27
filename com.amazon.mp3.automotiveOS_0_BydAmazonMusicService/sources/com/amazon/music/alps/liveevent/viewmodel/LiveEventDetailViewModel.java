package com.amazon.music.alps.liveevent.viewmodel;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.amazon.music.alps.R$string;
import com.amazon.music.alps.config.URLConfig;
import com.amazon.music.alps.liveevent.LiveEventModelUseCase;
import com.amazon.music.alps.liveevent.enums.ImageType;
import com.amazon.music.alps.liveevent.ktx.LiveEventKt;
import com.amazon.music.alps.liveevent.ktx.UriKt;
import com.amazon.music.alps.providers.SharingProvider;
import com.amazon.music.alps.util.Event;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ViewType;
import com.amazon.music.platform.graphql.generated.ArtistMerchQuery;
import com.amazon.music.platform.graphql.generated.fragment.Image;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.platform.metrics.event.UIClickWrapper;
import com.amazon.music.platform.metrics.event.UIPageViewWrapper;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.MerchMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.perf.plugin.util.AsmString;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/* JADX INFO: compiled from: LiveEventDetailViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 ]2\u00020\u0001:\u0001]B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u001a\u00106\u001a\u0004\u0018\u0001072\u000e\u00108\u001a\n\u0012\u0004\u0012\u000207\u0018\u000109H\u0002J*\u0010:\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u001c\u0018\u00010\u001b2\u0006\u0010;\u001a\u00020\u00132\u000e\u00108\u001a\n\u0012\u0004\u0012\u000207\u0018\u000109H\u0002J\b\u0010<\u001a\u00020=H\u0014J\u000e\u0010>\u001a\u00020=2\u0006\u0010?\u001a\u00020@J\u000e\u0010A\u001a\u00020=2\u0006\u0010B\u001a\u00020\tJ\u0006\u0010(\u001a\u00020=J\u0006\u0010C\u001a\u00020DJ\"\u0010*\u001a\u00020=2\b\u0010E\u001a\u0004\u0018\u00010\t2\u0006\u0010F\u001a\u00020G2\b\u0010H\u001a\u0004\u0018\u00010\tJ\u000e\u0010I\u001a\u00020=2\u0006\u0010J\u001a\u00020KJ\u000e\u0010L\u001a\u00020=2\u0006\u0010B\u001a\u00020\tJ\u000e\u0010M\u001a\u00020=2\u0006\u0010N\u001a\u00020OJ\u000e\u0010P\u001a\u00020=2\u0006\u0010Q\u001a\u00020RJ\u001a\u0010S\u001a\u00020=2\b\u0010T\u001a\u0004\u0018\u00010\t2\b\u0010H\u001a\u0004\u0018\u00010\tJ\u0006\u0010U\u001a\u00020VJ\u000e\u0010,\u001a\u00020=2\u0006\u0010W\u001a\u00020XJ\u000e\u0010.\u001a\u00020=2\u0006\u0010W\u001a\u00020XJ\u001a\u0010Y\u001a\u00020=2\b\u0010Z\u001a\u0004\u0018\u00010\t2\b\u0010H\u001a\u0004\u0018\u00010\tJ\u0010\u0010[\u001a\u00020=2\b\u0010\\\u001a\u0004\u0018\u00010\tR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00120\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00120\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00120\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u001c0\u001b0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001e8F¢\u0006\u0006\u001a\u0004\b\"\u0010 R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001e8F¢\u0006\u0006\u001a\u0004\b&\u0010 R\u0010\u0010'\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010(\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00120\u001e8F¢\u0006\u0006\u001a\u0004\b)\u0010 R\u001d\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120\u001e8F¢\u0006\u0006\u001a\u0004\b+\u0010 R\u001d\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00120\u001e8F¢\u0006\u0006\u001a\u0004\b-\u0010 R\u001d\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00120\u001e8F¢\u0006\u0006\u001a\u0004\b/\u0010 R\u001d\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120\u001e8F¢\u0006\u0006\u001a\u0004\b1\u0010 R\u001f\u00102\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u001c0\u001b0\u001e8F¢\u0006\u0006\u001a\u0004\b3\u0010 R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u00104\u001a\n 5*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006^"}, d2 = {"Lcom/amazon/music/alps/liveevent/viewmodel/LiveEventDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "useCase", "Lcom/amazon/music/alps/liveevent/LiveEventModelUseCase;", "metricsProvider", "Lcom/amazon/music/platform/providers/MetricsProvider;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "refMarker", "", "associateTag", "(Lcom/amazon/music/alps/liveevent/LiveEventModelUseCase;Lcom/amazon/music/platform/providers/MetricsProvider;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Ljava/lang/String;Ljava/lang/String;)V", "_backdropImage", "Landroidx/lifecycle/MutableLiveData;", "_dataLoading", "", "_error", "_openCalendar", "Lcom/amazon/music/alps/util/Event;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "_openExternalUri", "Landroid/net/Uri;", "_openShare", "Lcom/amazon/music/alps/providers/SharingProvider$ShareContent;", "_openShareExperiment", "_openUri", "_pageGridModel", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "backdropImage", "Landroidx/lifecycle/LiveData;", "getBackdropImage", "()Landroidx/lifecycle/LiveData;", "dataLoading", "getDataLoading", "disposable", "Lio/reactivex/rxjava3/disposables/Disposable;", "error", "getError", "liveEvent", "openCalendar", "getOpenCalendar", "openExternalUri", "getOpenExternalUri", "openShare", "getOpenShare", "openShareExperiment", "getOpenShareExperiment", "openUri", "getOpenUri", "pageGridModel", "getPageGridModel", ParserUtil.TAG_QUERY_PARAM_NAME, "kotlin.jvm.PlatformType", "getArtistFromOptional", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;", "artist", "Ljava/util/Optional;", "model", "data", "onCleared", "", "openAlbum", "albumMetadata", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "openArtistMerchSeeAll", "uri", "openContent", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", ImagesContract.URL, "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "pageTypeName", "openLineup", "artistMetadata", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "openLineupSeeAll", "openMerch", "merchMetadata", "Lcom/amazon/music/views/library/metadata/MerchMetadata;", "openPlaylist", "playlistMetadata", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "openPrimeVideoLink", "primeVideoLink", "openSeeMore", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "context", "Landroid/content/Context;", "openTwitchLink", "twitchId", AsmString.METHOD_FIREBASE_PERF_TRACE_START, ContentUtils.KEY_EVENT_ID, "Companion", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class LiveEventDetailViewModel extends ViewModel {
    private final MutableLiveData<String> _backdropImage;
    private final MutableLiveData<Boolean> _dataLoading;
    private final MutableLiveData<Boolean> _error;
    private final MutableLiveData<Event<LiveEvent>> _openCalendar;
    private final MutableLiveData<Event<Uri>> _openExternalUri;
    private final MutableLiveData<Event<SharingProvider.ShareContent>> _openShare;
    private final MutableLiveData<Event<SharingProvider.ShareContent>> _openShareExperiment;
    private final MutableLiveData<Event<Uri>> _openUri;
    private final MutableLiveData<BaseViewContainerModel<? extends BaseViewModel>> _pageGridModel;
    private final String associateTag;
    private Disposable disposable;
    private LiveEvent liveEvent;
    private final MetricsProvider metricsProvider;
    private final PageType pageType;
    private final String refMarker;
    private final String tag;
    private final LiveEventModelUseCase useCase;

    /* JADX INFO: compiled from: LiveEventDetailViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PageType.values().length];
            iArr[PageType.LIVE_EVENT_DETAIL.ordinal()] = 1;
            iArr[PageType.LIVE_EVENT_LINEUP.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LiveEventDetailViewModel(LiveEventModelUseCase useCase, MetricsProvider metricsProvider, PageType pageType, String refMarker, String associateTag) {
        Intrinsics.checkNotNullParameter(useCase, "useCase");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(refMarker, "refMarker");
        Intrinsics.checkNotNullParameter(associateTag, "associateTag");
        this.useCase = useCase;
        this.metricsProvider = metricsProvider;
        this.pageType = pageType;
        this.refMarker = refMarker;
        this.associateTag = associateTag;
        this._dataLoading = new MutableLiveData<>();
        this._error = new MutableLiveData<>();
        this._backdropImage = new MutableLiveData<>();
        this._pageGridModel = new MutableLiveData<>();
        this._openUri = new MutableLiveData<>();
        this._openExternalUri = new MutableLiveData<>();
        this._openCalendar = new MutableLiveData<>();
        this._openShare = new MutableLiveData<>();
        this._openShareExperiment = new MutableLiveData<>();
        this.tag = LiveEventDetailViewModel.class.getSimpleName();
    }

    public final LiveData<Boolean> getDataLoading() {
        return this._dataLoading;
    }

    public final LiveData<Boolean> getError() {
        return this._error;
    }

    public final LiveData<String> getBackdropImage() {
        return this._backdropImage;
    }

    public final LiveData<BaseViewContainerModel<? extends BaseViewModel>> getPageGridModel() {
        return this._pageGridModel;
    }

    public final LiveData<Event<Uri>> getOpenUri() {
        return this._openUri;
    }

    public final LiveData<Event<Uri>> getOpenExternalUri() {
        return this._openExternalUri;
    }

    public final LiveData<Event<LiveEvent>> getOpenCalendar() {
        return this._openCalendar;
    }

    public final LiveData<Event<SharingProvider.ShareContent>> getOpenShare() {
        return this._openShare;
    }

    public final LiveData<Event<SharingProvider.ShareContent>> getOpenShareExperiment() {
        return this._openShareExperiment;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.disposable = null;
    }

    public final void start(String eventId) {
        if (eventId == null || eventId.length() == 0) {
            this._error.setValue(Boolean.TRUE);
            return;
        }
        final long jCurrentTimeMillis = System.currentTimeMillis();
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.disposable = this.useCase.getLiveEventDetail(eventId).flatMap(new Function() { // from class: com.amazon.music.alps.liveevent.viewmodel.LiveEventDetailViewModel$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return LiveEventDetailViewModel.m1878start$lambda0(this.f$0, (LiveEvent) obj);
            }
        }, new BiFunction() { // from class: com.amazon.music.alps.liveevent.viewmodel.LiveEventDetailViewModel$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return LiveEventDetailViewModel.m1879start$lambda1((LiveEvent) obj, (Optional) obj2);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer() { // from class: com.amazon.music.alps.liveevent.viewmodel.LiveEventDetailViewModel$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                LiveEventDetailViewModel.m1880start$lambda2(this.f$0, (Disposable) obj);
            }
        }).doOnNext(new Consumer() { // from class: com.amazon.music.alps.liveevent.viewmodel.LiveEventDetailViewModel$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                LiveEventDetailViewModel.m1881start$lambda3(this.f$0, jCurrentTimeMillis, (Pair) obj);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.music.alps.liveevent.viewmodel.LiveEventDetailViewModel$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                LiveEventDetailViewModel.m1882start$lambda6(this.f$0, (Pair) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.alps.liveevent.viewmodel.LiveEventDetailViewModel$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                LiveEventDetailViewModel.m1883start$lambda7(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: start$lambda-0, reason: not valid java name */
    public static final ObservableSource m1878start$lambda0(LiveEventDetailViewModel this$0, LiveEvent liveEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return this$0.useCase.getArtist(liveEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: start$lambda-1, reason: not valid java name */
    public static final Pair m1879start$lambda1(LiveEvent liveEvent, Optional optional) {
        return new Pair(liveEvent, optional);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: start$lambda-2, reason: not valid java name */
    public static final void m1880start$lambda2(LiveEventDetailViewModel this$0, Disposable disposable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0._dataLoading.setValue(Boolean.TRUE);
        this$0._error.setValue(Boolean.FALSE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: start$lambda-3, reason: not valid java name */
    public static final void m1881start$lambda3(LiveEventDetailViewModel this$0, long j, Pair pair) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LiveEvent liveEvent = (LiveEvent) pair.getFirst();
        UIPageViewWrapper.Builder builderWithLoadTime = new UIPageViewWrapper.Builder().withPageType(this$0.pageType.name()).withAssociateTag(this$0.associateTag).withDetailPageItemId(liveEvent.getId()).withDetailPageItemIdType(EntityIdType.LIVE_EVENT_ID.name()).withEventTime(Long.valueOf(System.currentTimeMillis())).withLoadTime(Long.valueOf(System.currentTimeMillis() - j));
        PageSubType pageSubType = LiveEventKt.pageSubType(liveEvent);
        UIPageViewWrapper uIPageViewWrapperBuild = builderWithLoadTime.withPageSubType(pageSubType == null ? null : pageSubType.name()).withViewType(ViewType.PORTRAIT.name()).build();
        MetricsProvider metricsProvider = this$0.metricsProvider;
        if (metricsProvider == null) {
            return;
        }
        metricsProvider.sendMetricsEvent(uIPageViewWrapperBuild);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: start$lambda-6, reason: not valid java name */
    public static final void m1882start$lambda6(LiveEventDetailViewModel this$0, Pair pair) {
        Image image;
        String url;
        Image image2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.liveEvent = (LiveEvent) pair.getFirst();
        List<LiveEvent.Image> images = ((LiveEvent) pair.getFirst()).getImages();
        LiveEvent.Image image3 = null;
        Object obj = null;
        if (images != null) {
            Iterator<T> it = images.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                LiveEvent.Image image4 = (LiveEvent.Image) next;
                if (ImageType.INSTANCE.from((image4 != null && (image2 = image4.getImage()) != null) ? image2.getImageType() : null) == ImageType.BACKGROUND) {
                    obj = next;
                    break;
                }
            }
            image3 = (LiveEvent.Image) obj;
        }
        if (image3 != null && (image = image3.getImage()) != null && (url = image.getUrl()) != null) {
            this$0._backdropImage.setValue(url);
        }
        this$0._dataLoading.setValue(Boolean.FALSE);
        MutableLiveData<BaseViewContainerModel<? extends BaseViewModel>> mutableLiveData = this$0._pageGridModel;
        Object first = pair.getFirst();
        Intrinsics.checkNotNullExpressionValue(first, "data.first");
        mutableLiveData.setValue(this$0.model((LiveEvent) first, (Optional) pair.getSecond()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: start$lambda-7, reason: not valid java name */
    public static final void m1883start$lambda7(LiveEventDetailViewModel this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0._dataLoading.setValue(Boolean.FALSE);
        this$0._error.setValue(Boolean.TRUE);
        Log.e(this$0.tag, Intrinsics.stringPlus("Error fetching Live Event Detail data: ", th));
    }

    public final ContentMetadataOnClickListener openContent() {
        return new ContentMetadataOnClickListener() { // from class: com.amazon.music.alps.liveevent.viewmodel.LiveEventDetailViewModel.openContent.1
            @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
            public void onContentListClicked(List<? extends ContentMetadata> contentMetadataList, int positionToStart, String contentListTitle) {
                Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
            }

            @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
            public void onContentClicked(ContentMetadata contentMetadata) {
                Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
                if (contentMetadata instanceof AlbumMetadata) {
                    LiveEventDetailViewModel.this.openAlbum((AlbumMetadata) contentMetadata);
                    return;
                }
                if (contentMetadata instanceof ArtistMetadata) {
                    LiveEventDetailViewModel.this.openLineup((ArtistMetadata) contentMetadata);
                } else if (contentMetadata instanceof MerchMetadata) {
                    LiveEventDetailViewModel.this.openMerch((MerchMetadata) contentMetadata);
                } else if (contentMetadata instanceof PlaylistMetadata) {
                    LiveEventDetailViewModel.this.openPlaylist((PlaylistMetadata) contentMetadata);
                }
            }
        };
    }

    public final SeeMoreProvider openSeeMore() {
        return new SeeMoreProvider() { // from class: com.amazon.music.alps.liveevent.viewmodel.LiveEventDetailViewModel.openSeeMore.1

            /* JADX INFO: renamed from: com.amazon.music.alps.liveevent.viewmodel.LiveEventDetailViewModel$openSeeMore$1$WhenMappings */
            /* JADX INFO: compiled from: LiveEventDetailViewModel.kt */
            @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[ContentName.values().length];
                    iArr[ContentName.ARTIST_MERCH_SHOVELER.ordinal()] = 1;
                    iArr[ContentName.LIVE_EVENT_LINEUP.ordinal()] = 2;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.amazon.music.views.library.providers.SeeMoreProvider
            public void onSeeMoreClick(String uri, String viewId, String nextPageToken) {
                ContentName contentName;
                int i = 0;
                if (uri == null || uri.length() == 0) {
                    return;
                }
                ContentName[] contentNameArrValues = ContentName.values();
                int length = contentNameArrValues.length;
                while (true) {
                    if (i >= length) {
                        contentName = null;
                        break;
                    }
                    contentName = contentNameArrValues[i];
                    i++;
                    if (Intrinsics.areEqual(contentName.name(), viewId)) {
                        break;
                    }
                }
                int i2 = contentName == null ? -1 : WhenMappings.$EnumSwitchMapping$0[contentName.ordinal()];
                if (i2 == 1) {
                    LiveEventDetailViewModel.this.openArtistMerchSeeAll(uri);
                } else if (i2 != 2) {
                    LiveEventDetailViewModel.this._openUri.setValue(new Event(Uri.parse(uri)));
                } else {
                    LiveEventDetailViewModel.this.openLineupSeeAll(uri);
                }
            }
        };
    }

    public final void openAlbum(AlbumMetadata albumMetadata) {
        PageSubType pageSubType;
        Intrinsics.checkNotNullParameter(albumMetadata, "albumMetadata");
        MetricsProvider metricsProvider = this.metricsProvider;
        if (metricsProvider != null) {
            UIClickWrapper.Builder builderWithPageType = new UIClickWrapper.Builder().withActionType(ActionType.SELECT_ALBUM.name()).withContentInfo(CollectionsKt__CollectionsKt.emptyList()).withPageType(this.pageType.name());
            LiveEvent liveEvent = this.liveEvent;
            String strName = null;
            if (liveEvent != null && (pageSubType = LiveEventKt.pageSubType(liveEvent)) != null) {
                strName = pageSubType.name();
            }
            metricsProvider.sendMetricsEvent(builderWithPageType.withPageSubType(strName).withRefMarker(this.refMarker).withEntityType(EntityType.ALBUM.name()).withEntityId(albumMetadata.getAsin()).withEntityIdType(EntityIdType.ASIN.name()).withInteractionType(InteractionType.TAP.name()).withUrl(albumMetadata.getUri().toString()).build());
        }
        this._openUri.setValue(new Event<>(UriKt.withRefAndTag(albumMetadata.getUri(), this.refMarker, this.associateTag)));
    }

    public final void openCalendar() {
        PageSubType pageSubType;
        MetricsProvider metricsProvider = this.metricsProvider;
        if (metricsProvider != null) {
            UIClickWrapper.Builder builderWithPageType = new UIClickWrapper.Builder().withActionType(ActionType.ADD_TO_CALENDAR_LIVE_EVENT.name()).withContentInfo(CollectionsKt__CollectionsKt.emptyList()).withPageType(this.pageType.name());
            LiveEvent liveEvent = this.liveEvent;
            UIClickWrapper.Builder builderWithEntityType = builderWithPageType.withPageSubType((liveEvent == null || (pageSubType = LiveEventKt.pageSubType(liveEvent)) == null) ? null : pageSubType.name()).withRefMarker(this.refMarker).withEntityType(EntityType.LIVE_EVENT.name());
            LiveEvent liveEvent2 = this.liveEvent;
            metricsProvider.sendMetricsEvent(builderWithEntityType.withEntityId(liveEvent2 != null ? liveEvent2.getId() : null).withEntityIdType(EntityIdType.LIVE_EVENT_ID.name()).withInteractionType(InteractionType.TAP.name()).build());
        }
        this._openCalendar.setValue(new Event<>(this.liveEvent));
    }

    public final void openLineup(ArtistMetadata artistMetadata) {
        PageSubType pageSubType;
        Intrinsics.checkNotNullParameter(artistMetadata, "artistMetadata");
        MetricsProvider metricsProvider = this.metricsProvider;
        if (metricsProvider != null) {
            UIClickWrapper.Builder builderWithPageType = new UIClickWrapper.Builder().withActionType(ActionType.SELECT_ARTIST.name()).withContentInfo(CollectionsKt__CollectionsKt.emptyList()).withPageType(this.pageType.name());
            LiveEvent liveEvent = this.liveEvent;
            String strName = null;
            if (liveEvent != null && (pageSubType = LiveEventKt.pageSubType(liveEvent)) != null) {
                strName = pageSubType.name();
            }
            metricsProvider.sendMetricsEvent(builderWithPageType.withPageSubType(strName).withRefMarker(this.refMarker).withEntityType(EntityType.ARTIST.name()).withEntityId(artistMetadata.getAsin()).withEntityIdType(EntityIdType.ASIN.name()).withInteractionType(InteractionType.TAP.name()).withUrl(artistMetadata.getUri().toString()).build());
        }
        this._openUri.setValue(new Event<>(UriKt.withRefAndTag(artistMetadata.getUri(), this.refMarker, this.associateTag)));
    }

    public final void openLineupSeeAll(String uri) {
        PageSubType pageSubType;
        Intrinsics.checkNotNullParameter(uri, "uri");
        MetricsProvider metricsProvider = this.metricsProvider;
        if (metricsProvider != null) {
            UIClickWrapper.Builder builderWithPageType = new UIClickWrapper.Builder().withActionType(ActionType.SELECT_LINEUP_SEE_ALL.name()).withContentInfo(CollectionsKt__CollectionsKt.emptyList()).withPageType(this.pageType.name());
            LiveEvent liveEvent = this.liveEvent;
            UIClickWrapper.Builder builderWithEntityType = builderWithPageType.withPageSubType((liveEvent == null || (pageSubType = LiveEventKt.pageSubType(liveEvent)) == null) ? null : pageSubType.name()).withRefMarker(this.refMarker).withEntityType(EntityType.LIVE_EVENT.name());
            LiveEvent liveEvent2 = this.liveEvent;
            metricsProvider.sendMetricsEvent(builderWithEntityType.withEntityId(liveEvent2 != null ? liveEvent2.getId() : null).withEntityIdType(EntityIdType.LIVE_EVENT_ID.name()).withInteractionType(InteractionType.TAP.name()).withUrl(uri).build());
        }
        MutableLiveData<Event<Uri>> mutableLiveData = this._openUri;
        Uri uri2 = Uri.parse(uri);
        Intrinsics.checkNotNullExpressionValue(uri2, "parse(uri)");
        mutableLiveData.setValue(new Event<>(UriKt.withRefAndTag(uri2, this.refMarker, this.associateTag)));
    }

    public final void openMerch(MerchMetadata merchMetadata) {
        PageSubType pageSubType;
        Intrinsics.checkNotNullParameter(merchMetadata, "merchMetadata");
        MetricsProvider metricsProvider = this.metricsProvider;
        if (metricsProvider != null) {
            UIClickWrapper.Builder builderWithPageType = new UIClickWrapper.Builder().withActionType(ActionType.SELECT_MERCH.name()).withContentInfo(CollectionsKt__CollectionsKt.emptyList()).withPageType(this.pageType.name());
            LiveEvent liveEvent = this.liveEvent;
            String strName = null;
            if (liveEvent != null && (pageSubType = LiveEventKt.pageSubType(liveEvent)) != null) {
                strName = pageSubType.name();
            }
            metricsProvider.sendMetricsEvent(builderWithPageType.withPageSubType(strName).withRefMarker(this.refMarker).withEntityType(EntityType.MERCH.name()).withEntityId(merchMetadata.getAsin()).withEntityIdType(EntityIdType.ASIN.name()).withInteractionType(InteractionType.TAP.name()).withUrl(merchMetadata.getUri().toString()).build());
        }
        this._openUri.setValue(new Event<>(UriKt.withRefAndTag(merchMetadata.getUri(), this.refMarker, this.associateTag)));
    }

    public final void openArtistMerchSeeAll(String uri) {
        PageSubType pageSubType;
        Intrinsics.checkNotNullParameter(uri, "uri");
        MetricsProvider metricsProvider = this.metricsProvider;
        if (metricsProvider != null) {
            UIClickWrapper.Builder builderWithPageType = new UIClickWrapper.Builder().withActionType(ActionType.SELECT_MERCH_SEE_ALL.name()).withContentInfo(CollectionsKt__CollectionsKt.emptyList()).withPageType(this.pageType.name());
            LiveEvent liveEvent = this.liveEvent;
            UIClickWrapper.Builder builderWithEntityType = builderWithPageType.withPageSubType((liveEvent == null || (pageSubType = LiveEventKt.pageSubType(liveEvent)) == null) ? null : pageSubType.name()).withRefMarker(this.refMarker).withEntityType(EntityType.LIVE_EVENT.name());
            LiveEvent liveEvent2 = this.liveEvent;
            metricsProvider.sendMetricsEvent(builderWithEntityType.withEntityId(liveEvent2 != null ? liveEvent2.getId() : null).withEntityIdType(EntityIdType.LIVE_EVENT_ID.name()).withInteractionType(InteractionType.TAP.name()).withUrl(uri).build());
        }
        MutableLiveData<Event<Uri>> mutableLiveData = this._openUri;
        Uri uri2 = Uri.parse(uri);
        Intrinsics.checkNotNullExpressionValue(uri2, "parse(uri)");
        mutableLiveData.setValue(new Event<>(UriKt.withRefAndTag(uri2, this.refMarker, this.associateTag)));
    }

    public final void openPlaylist(PlaylistMetadata playlistMetadata) {
        PageSubType pageSubType;
        Intrinsics.checkNotNullParameter(playlistMetadata, "playlistMetadata");
        MetricsProvider metricsProvider = this.metricsProvider;
        if (metricsProvider != null) {
            UIClickWrapper.Builder builderWithPageType = new UIClickWrapper.Builder().withActionType(ActionType.SELECT_PLAYLIST.name()).withContentInfo(CollectionsKt__CollectionsKt.emptyList()).withPageType(this.pageType.name());
            LiveEvent liveEvent = this.liveEvent;
            String strName = null;
            if (liveEvent != null && (pageSubType = LiveEventKt.pageSubType(liveEvent)) != null) {
                strName = pageSubType.name();
            }
            metricsProvider.sendMetricsEvent(builderWithPageType.withPageSubType(strName).withRefMarker(this.refMarker).withEntityType(EntityType.PLAYLIST.name()).withEntityId(playlistMetadata.getAsin()).withEntityIdType(EntityIdType.ASIN.name()).withInteractionType(InteractionType.TAP.name()).withUrl(playlistMetadata.getUri().toString()).build());
        }
        this._openUri.setValue(new Event<>(UriKt.withRefAndTag(playlistMetadata.getUri(), this.refMarker, this.associateTag)));
    }

    public final void openPrimeVideoLink(String primeVideoLink, String pageTypeName) {
        if (primeVideoLink == null || StringsKt__StringsJVMKt.isBlank(primeVideoLink)) {
            Log.e(this.tag, "missing required link, fallback to https://www.primevideo.com/fake-event");
            primeVideoLink = "https://www.primevideo.com/fake-event";
        }
        openExternalUri(primeVideoLink, ActionType.GO_PRIME_VIDEO, pageTypeName);
    }

    public final void openShare(Context context) {
        DateTime startDateTime;
        PageSubType pageSubType;
        Intrinsics.checkNotNullParameter(context, "context");
        String value = URLConfig.SHARE_BASE_URL.getValue();
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        sb.append("live/events/");
        LiveEvent liveEvent = this.liveEvent;
        sb.append((Object) (liveEvent == null ? null : liveEvent.getId()));
        Uri uri = Uri.parse(sb.toString());
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        String string = UriKt.withRefAndTag(uri, this.refMarker, this.associateTag).toString();
        Intrinsics.checkNotNullExpressionValue(string, "uri.withRefAndTag(refMar… associateTag).toString()");
        MetricsProvider metricsProvider = this.metricsProvider;
        if (metricsProvider != null) {
            UIClickWrapper.Builder builderWithPageType = new UIClickWrapper.Builder().withActionType(ActionType.SHARE_LIVE_EVENT.name()).withContentInfo(CollectionsKt__CollectionsKt.emptyList()).withPageType(this.pageType.name());
            LiveEvent liveEvent2 = this.liveEvent;
            UIClickWrapper.Builder builderWithEntityType = builderWithPageType.withPageSubType((liveEvent2 == null || (pageSubType = LiveEventKt.pageSubType(liveEvent2)) == null) ? null : pageSubType.name()).withRefMarker(this.refMarker).withEntityType(EntityType.LIVE_EVENT.name());
            LiveEvent liveEvent3 = this.liveEvent;
            metricsProvider.sendMetricsEvent(builderWithEntityType.withEntityId(liveEvent3 == null ? null : liveEvent3.getId()).withEntityIdType(EntityIdType.LIVE_EVENT_ID.name()).withInteractionType(InteractionType.TAP.name()).withUrl(string).build());
        }
        MutableLiveData<Event<SharingProvider.ShareContent>> mutableLiveData = this._openShare;
        LiveEvent liveEvent4 = this.liveEvent;
        String name = liveEvent4 == null ? null : liveEvent4.getName();
        String string2 = context.getString(R$string.livestream_on_amazon_music);
        LiveEvent liveEvent5 = this.liveEvent;
        String thumbnail = liveEvent5 == null ? null : LiveEventKt.getThumbnail(liveEvent5);
        StringBuilder sb2 = new StringBuilder();
        int i = R$string.liveevent_share_content;
        Object[] objArr = new Object[2];
        LiveEvent liveEvent6 = this.liveEvent;
        objArr[0] = liveEvent6 == null ? null : liveEvent6.getName();
        LiveEvent liveEvent7 = this.liveEvent;
        objArr[1] = (liveEvent7 == null || (startDateTime = LiveEventKt.getStartDateTime(liveEvent7)) == null) ? null : startDateTime.toString(DateTimeFormat.longDateTime());
        sb2.append(context.getString(i, objArr));
        sb2.append('\n');
        sb2.append(string);
        String string3 = sb2.toString();
        LiveEvent liveEvent8 = this.liveEvent;
        mutableLiveData.setValue(new Event<>(new SharingProvider.ShareContent(0, null, string3, Float.valueOf(1.0f), liveEvent8 != null ? LiveEventKt.getThumbnail(liveEvent8) : null, thumbnail, null, null, null, null, string2, name, string, 963, null)));
    }

    public final void openShareExperiment(Context context) {
        String str;
        DateTime startDateTime;
        PageSubType pageSubType;
        Intrinsics.checkNotNullParameter(context, "context");
        String value = URLConfig.SHARE_BASE_URL.getValue();
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        sb.append("live/events/");
        LiveEvent liveEvent = this.liveEvent;
        sb.append((Object) (liveEvent == null ? null : liveEvent.getId()));
        Uri uri = Uri.parse(sb.toString());
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        String string = UriKt.withRefAndTag(uri, this.refMarker, this.associateTag).toString();
        Intrinsics.checkNotNullExpressionValue(string, "uri.withRefAndTag(refMar… associateTag).toString()");
        MetricsProvider metricsProvider = this.metricsProvider;
        if (metricsProvider != null) {
            UIClickWrapper.Builder builderWithPageType = new UIClickWrapper.Builder().withActionType(ActionType.SHARE_LIVE_EVENT.name()).withContentInfo(CollectionsKt__CollectionsKt.emptyList()).withPageType(this.pageType.name());
            LiveEvent liveEvent2 = this.liveEvent;
            UIClickWrapper.Builder builderWithEntityType = builderWithPageType.withPageSubType((liveEvent2 == null || (pageSubType = LiveEventKt.pageSubType(liveEvent2)) == null) ? null : pageSubType.name()).withRefMarker(this.refMarker).withEntityType(EntityType.LIVE_EVENT.name());
            LiveEvent liveEvent3 = this.liveEvent;
            metricsProvider.sendMetricsEvent(builderWithEntityType.withEntityId(liveEvent3 == null ? null : liveEvent3.getId()).withEntityIdType(EntityIdType.LIVE_EVENT_ID.name()).withInteractionType(InteractionType.TAP.name()).withUrl(string).build());
        }
        MutableLiveData<Event<SharingProvider.ShareContent>> mutableLiveData = this._openShareExperiment;
        LiveEvent liveEvent4 = this.liveEvent;
        String name = liveEvent4 == null ? null : liveEvent4.getName();
        String string2 = context.getString(R$string.livestream_on_amazon_music);
        LiveEvent liveEvent5 = this.liveEvent;
        String sharingImageUrl = liveEvent5 == null ? null : LiveEventKt.getSharingImageUrl(liveEvent5);
        if (sharingImageUrl != null) {
            str = sharingImageUrl;
        } else {
            LiveEvent liveEvent6 = this.liveEvent;
            if (liveEvent6 == null) {
                str = null;
            } else {
                sharingImageUrl = LiveEventKt.getBackgroundImageUrl(liveEvent6);
                str = sharingImageUrl;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        int i = R$string.liveevent_share_content;
        Object[] objArr = new Object[2];
        LiveEvent liveEvent7 = this.liveEvent;
        objArr[0] = liveEvent7 == null ? null : liveEvent7.getName();
        LiveEvent liveEvent8 = this.liveEvent;
        objArr[1] = (liveEvent8 == null || (startDateTime = LiveEventKt.getStartDateTime(liveEvent8)) == null) ? null : startDateTime.toString(DateTimeFormat.longDateTime());
        sb2.append(context.getString(i, objArr));
        sb2.append('\n');
        sb2.append(string);
        String string3 = sb2.toString();
        LiveEvent liveEvent9 = this.liveEvent;
        mutableLiveData.setValue(new Event<>(new SharingProvider.ShareContent(0, null, string3, Float.valueOf(1.0f), liveEvent9 != null ? LiveEventKt.getBackgroundImageUrl(liveEvent9) : null, str, null, null, null, null, string2, name, string, 963, null)));
    }

    public final void openTwitchLink(String twitchId, String pageTypeName) {
        PageSubType pageSubType;
        if (twitchId == null || StringsKt__StringsJVMKt.isBlank(twitchId)) {
            Log.e(this.tag, "missing required twitchId, fallback to tw-AmazonMusic");
            twitchId = "tw-AmazonMusic";
        }
        Uri uri = Uri.parse(URLConfig.SHARE_BASE_URL.getValue() + "live/streams/" + twitchId);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(url)");
        Uri uriWithRefAndTag = UriKt.withRefAndTag(uri, this.refMarker, this.associateTag);
        MetricsProvider metricsProvider = this.metricsProvider;
        if (metricsProvider != null) {
            UIClickWrapper.Builder builderWithContentInfo = new UIClickWrapper.Builder().withActionType(ActionType.GO_LIVE_STREAM.name()).withContentInfo(CollectionsKt__CollectionsKt.emptyList());
            if (pageTypeName == null) {
                pageTypeName = this.pageType.name();
            }
            UIClickWrapper.Builder builderWithPageType = builderWithContentInfo.withPageType(pageTypeName);
            LiveEvent liveEvent = this.liveEvent;
            UIClickWrapper.Builder builderWithEntityType = builderWithPageType.withPageSubType((liveEvent == null || (pageSubType = LiveEventKt.pageSubType(liveEvent)) == null) ? null : pageSubType.name()).withRefMarker(this.refMarker).withEntityType(EntityType.LIVE_EVENT.name());
            LiveEvent liveEvent2 = this.liveEvent;
            metricsProvider.sendMetricsEvent(builderWithEntityType.withEntityId(liveEvent2 != null ? liveEvent2.getId() : null).withEntityIdType(EntityIdType.LIVE_EVENT_ID.name()).withInteractionType(InteractionType.TAP.name()).withUrl(uriWithRefAndTag.toString()).build());
        }
        this._openUri.setValue(new Event<>(uriWithRefAndTag));
    }

    public final void openExternalUri(String url, ActionType actionType, String pageTypeName) {
        PageSubType pageSubType;
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        if (url == null || url.length() == 0) {
            return;
        }
        Uri uri = Uri.parse(url);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(url)");
        Uri uriWithRefAndTag = UriKt.withRefAndTag(uri, this.refMarker, this.associateTag);
        MetricsProvider metricsProvider = this.metricsProvider;
        if (metricsProvider != null) {
            UIClickWrapper.Builder builderWithContentInfo = new UIClickWrapper.Builder().withActionType(actionType.name()).withContentInfo(CollectionsKt__CollectionsKt.emptyList());
            if (pageTypeName == null) {
                pageTypeName = this.pageType.name();
            }
            UIClickWrapper.Builder builderWithPageType = builderWithContentInfo.withPageType(pageTypeName);
            LiveEvent liveEvent = this.liveEvent;
            UIClickWrapper.Builder builderWithEntityType = builderWithPageType.withPageSubType((liveEvent == null || (pageSubType = LiveEventKt.pageSubType(liveEvent)) == null) ? null : pageSubType.name()).withRefMarker(this.refMarker).withEntityType(EntityType.LIVE_EVENT.name());
            LiveEvent liveEvent2 = this.liveEvent;
            metricsProvider.sendMetricsEvent(builderWithEntityType.withEntityId(liveEvent2 != null ? liveEvent2.getId() : null).withEntityIdType(EntityIdType.LIVE_EVENT_ID.name()).withInteractionType(InteractionType.TAP.name()).withUrl(uriWithRefAndTag.toString()).build());
        }
        this._openExternalUri.setValue(new Event<>(uriWithRefAndTag));
    }

    private final BaseViewContainerModel<? extends BaseViewModel> model(LiveEvent data, Optional<ArtistMerchQuery.Artist> artist) {
        int i = WhenMappings.$EnumSwitchMapping$0[this.pageType.ordinal()];
        if (i == 1) {
            return this.useCase.detail(data, getArtistFromOptional(artist));
        }
        if (i == 2) {
            return this.useCase.lineups(data);
        }
        return this.useCase.detail(data, getArtistFromOptional(artist));
    }

    private final ArtistMerchQuery.Artist getArtistFromOptional(Optional<ArtistMerchQuery.Artist> artist) {
        boolean z = false;
        if (artist != null && artist.isPresent()) {
            z = true;
        }
        if (!z || artist == null) {
            return null;
        }
        return artist.get();
    }
}
