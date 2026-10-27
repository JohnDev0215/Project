package com.amazon.mp3.detailpages.track;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel;
import com.amazon.mp3.detailpages.refinement.RefinementsMapping;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.library.refinement.datasource.DatabaseSourcePaged;
import com.amazon.mp3.library.refinement.filter.Filter;
import com.amazon.mp3.library.refinement.sort.Sort;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.SupervisorKt;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: LibraryTracksViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Ç\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u0010\u0018\u0000 a2\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001:\u0001aBG\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ \u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\u0014H\u0014J\u001a\u0010)\u001a\u00020\r2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020\u001dH\u0014J\u0018\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u0014H\u0016J\u001e\u0010/\u001a\u00020\r2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u0014012\u0006\u00102\u001a\u000203H\u0002J8\u00104\u001a\u00020\r2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u0014012\u0006\u00105\u001a\u00020\u00142\u0006\u00106\u001a\u00020\u00142\u0006\u00107\u001a\u0002082\b\b\u0002\u00102\u001a\u000203H\u0002J\u0018\u00109\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020:2\u0006\u0010;\u001a\u00020\u0014H\u0002J9\u0010<\u001a\b\u0012\u0004\u0012\u00020\u0002012\u0006\u0010=\u001a\u00020\u00142\u0018\u0010>\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0007H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010?J\"\u0010@\u001a\u00020\r2\u0006\u0010'\u001a\u00020\u00142\u0006\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010DH\u0016J\b\u0010E\u001a\u00020\rH\u0002J\b\u0010F\u001a\u0004\u0018\u00010\u001dJ\b\u0010G\u001a\u00020\u0014H\u0016J\u001e\u0010H\u001a\b\u0012\u0004\u0012\u00020\u0014012\u0006\u00105\u001a\u00020\u00142\u0006\u00106\u001a\u00020\u0014H\u0002J \u0010I\u001a\u0004\u0018\u00010\u00022\u0006\u0010;\u001a\u00020\u00142\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00020KH\u0002J?\u0010L\u001a\u00020\r2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020P2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010R2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010T\u001a\u0004\u0018\u000103¢\u0006\u0002\u0010UJ\u0006\u0010V\u001a\u00020\rJ\b\u0010W\u001a\u00020\rH\u0014J\u0018\u0010X\u001a\u00020\r2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010A\u001a\u00020BH\u0014J\u0010\u0010[\u001a\u00020\r2\u0006\u0010A\u001a\u00020BH\u0016J \u0010\\\u001a\u00020\r2\u0006\u00107\u001a\u0002082\u0006\u00105\u001a\u00020\u00142\u0006\u00106\u001a\u00020\u0014H\u0002J\u0010\u0010]\u001a\u00020\r2\b\u0010^\u001a\u0004\u0018\u00010PJ\u001e\u0010_\u001a\u00020\r2\u0006\u00105\u001a\u00020\u00142\u0006\u00106\u001a\u00020\u00142\u0006\u00107\u001a\u000208J\u0006\u0010`\u001a\u00020\rR\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138BX\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\u0015\u001a$\u0012\u0004\u0012\u00020\n\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u00170\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\"\u001a \u0012\u0004\u0012\u00020\n\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030#0\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006b"}, d2 = {"Lcom/amazon/mp3/detailpages/track/LibraryTracksViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinableViewModel;", "Lcom/amazon/mp3/library/item/MusicTrack;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "application", "Landroid/app/Application;", "pageDataRepo", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;", "defaultFilters", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "refinementSelectionOccurred", "Lkotlin/Function0;", "", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;Ljava/util/Set;Lkotlin/jvm/functions/Function0;)V", "downloadStateModelProviderListener", "com/amazon/mp3/detailpages/track/LibraryTracksViewModel$downloadStateModelProviderListener$1", "Lcom/amazon/mp3/detailpages/track/LibraryTracksViewModel$downloadStateModelProviderListener$1;", "fetchedPages", "", "", "filterMapping", "", "Lcom/amazon/mp3/library/refinement/filter/Filter;", "getFilterMapping", "()Ljava/util/Map;", "groupDownloadStateModelProvider", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider;", "lastUpdatedIndices", "Lkotlin/ranges/IntRange;", "playbackJob", "Lkotlinx/coroutines/CompletableJob;", "playbackScope", "Lkotlinx/coroutines/CoroutineScope;", "sortMapping", "Lcom/amazon/mp3/library/refinement/sort/Sort;", "getSortMapping", "didCancelPageRequest", "startPageIndex", "pageSize", "pageCount", "didReplaceItemsAtRange", "pageModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "range", "fetchMorePage", "pageIndex", "fetchNewPages", "pagesToFetch", "", "isPrefetching", "", "fetchPagesIfNeeded", "firstVisibleIndex", "lastVisibleIndex", "scrollDirection", "Lcom/amazon/mp3/detailpages/track/ScrollDirection;", "fetchTrackAtIndex", "Lrx/Observable;", "index", "fetchTrackList", "trackCount", "repository", "(ILcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchUpdates", "diffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "fetchType", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "fetchUpdatesForSync", "getIndicesToUpdate", "getPageSize", "getPagesToFetch", "getTrackAtIndex", "dataSource", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSourcePaged;", "handlePlayback", "context", "Landroid/content/Context;", "tracksUri", "Landroid/net/Uri;", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "position", "shuffleAll", "(Landroid/content/Context;Landroid/net/Uri;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/Integer;Ljava/lang/Boolean;)V", "hasUpdatedPage", "onCleared", "onFilterClicked", "model", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "onFilterReset", "prefetchNextPage", "registerDownloadStateListener", "uri", "scrolledToIndex", "unregisterDownloadStateListener", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryTracksViewModel extends RefinableViewModel<MusicTrack, DatabaseSource<MusicTrack>> {
    private final LibraryTracksViewModel$downloadStateModelProviderListener$1 downloadStateModelProviderListener;
    private final Set<Integer> fetchedPages;
    private final GroupDownloadStateModelProvider groupDownloadStateModelProvider;
    private IntRange lastUpdatedIndices;
    private final CompletableJob playbackJob;
    private final CoroutineScope playbackScope;
    private final Function0<Unit> refinementSelectionOccurred;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = LibraryTracksViewModel.class.getSimpleName();
    private static final int PAGE_SIZE = 50;
    private static final int PAGINATION_THRESHOLD = 25;

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.track.LibraryTracksViewModel$fetchTrackList$1, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryTracksViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.detailpages.track.LibraryTracksViewModel", f = "LibraryTracksViewModel.kt", i = {0}, l = {308}, m = "fetchTrackList", n = {"trackList"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LibraryTracksViewModel.this.fetchTrackList(0, null, this);
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel, com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public void fetchMorePage(int pageIndex, int pageSize) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.amazon.mp3.detailpages.track.LibraryTracksViewModel$downloadStateModelProviderListener$1] */
    public LibraryTracksViewModel(Application application, RefinablePageDataRepository<MusicTrack, DatabaseSource<MusicTrack>> pageDataRepo, Set<? extends RefinementFilterType> set, Function0<Unit> refinementSelectionOccurred) {
        super(application, pageDataRepo, AmazonApplication.getCapabilities().shouldEnablePaginationForBauhausPages());
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pageDataRepo, "pageDataRepo");
        Intrinsics.checkNotNullParameter(refinementSelectionOccurred, "refinementSelectionOccurred");
        this.refinementSelectionOccurred = refinementSelectionOccurred;
        this.fetchedPages = SetsKt__SetsKt.mutableSetOf(0);
        CompletableJob completableJobSupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        this.playbackJob = completableJobSupervisorJob$default;
        this.playbackScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(completableJobSupervisorJob$default));
        enableFilters(set);
        Context applicationContext = application.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "application.applicationContext");
        this.groupDownloadStateModelProvider = new GroupDownloadStateModelProvider(applicationContext);
        this.downloadStateModelProviderListener = new GroupDownloadStateModelProvider.BauhausDefaultListener() { // from class: com.amazon.mp3.detailpages.track.LibraryTracksViewModel$downloadStateModelProviderListener$1
            {
                super(false, 1, null);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.BauhausDefaultListener
            /* JADX INFO: renamed from: getPageModel */
            public PageGridViewModel get$pageGridViewModel() {
                return (PageGridViewModel) this.this$0.getPageData().getValue();
            }

            @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.BauhausDefaultListener
            public PageStateManager getPageStateManager() {
                return this.this$0.getPageStateManager();
            }
        };
    }

    /* JADX INFO: compiled from: LibraryTracksViewModel.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/detailpages/track/LibraryTracksViewModel$Companion;", "", "()V", "PAGE_SIZE", "", "getPAGE_SIZE", "()I", "PAGINATION_THRESHOLD", "getPAGINATION_THRESHOLD", "TAG", "", "kotlin.jvm.PlatformType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getPAGE_SIZE() {
            return LibraryTracksViewModel.PAGE_SIZE;
        }

        public final int getPAGINATION_THRESHOLD() {
            return LibraryTracksViewModel.PAGINATION_THRESHOLD;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel
    public Map<RefinementFilterType, Filter<MusicTrack, ?, DatabaseSource<MusicTrack>>> getFilterMapping() {
        return RefinementsMapping.INSTANCE.getTrackFilterMapping();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel
    public Map<RefinementFilterType, Sort<MusicTrack, DatabaseSource<MusicTrack>>> getSortMapping() {
        return RefinementsMapping.INSTANCE.getTrackSortMapping();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel
    public void onFilterReset(BaseViewsDiffUtilFactory diffUtilFactory) {
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
        this.refinementSelectionOccurred.invoke();
        super.onFilterReset(diffUtilFactory);
        this.fetchedPages.clear();
        this.fetchedPages.add(0);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel
    protected void onFilterClicked(RefinementPillModel model, BaseViewsDiffUtilFactory diffUtilFactory) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
        this.refinementSelectionOccurred.invoke();
        super.onFilterClicked(model, diffUtilFactory);
        this.fetchedPages.clear();
        this.fetchedPages.add(0);
    }

    public final void scrolledToIndex(int firstVisibleIndex, int lastVisibleIndex, ScrollDirection scrollDirection) {
        Intrinsics.checkNotNullParameter(scrollDirection, "scrollDirection");
        fetchPagesIfNeeded$default(this, getPagesToFetch(firstVisibleIndex, lastVisibleIndex), firstVisibleIndex, lastVisibleIndex, scrollDirection, false, 16, null);
    }

    static /* synthetic */ void fetchPagesIfNeeded$default(LibraryTracksViewModel libraryTracksViewModel, List list, int i, int i2, ScrollDirection scrollDirection, boolean z, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            z = false;
        }
        libraryTracksViewModel.fetchPagesIfNeeded(list, i, i2, scrollDirection, z);
    }

    private final void fetchNewPages(List<Integer> pagesToFetch, boolean isPrefetching) {
        fetchPageAndReplace(((Number) CollectionsKt___CollectionsKt.first((List) pagesToFetch)).intValue(), getPageSize(), pagesToFetch.size(), !isPrefetching);
        this.fetchedPages.addAll(pagesToFetch);
    }

    private final List<Integer> getPagesToFetch(int firstVisibleIndex, int lastVisibleIndex) {
        List<BaseViewModel> models;
        int pageSize = getPageSize();
        IntProgression intProgressionStep = RangesKt___RangesKt.step(RangesKt___RangesKt.until((firstVisibleIndex / pageSize) * pageSize, ((lastVisibleIndex / pageSize) + 1) * pageSize), pageSize);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(intProgressionStep, 10));
        Iterator<Integer> it = intProgressionStep.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((IntIterator) it).nextInt() / pageSize));
        }
        PageGridViewModel value = getPageData().getValue();
        int size = ((value == null || (models = value.getModels()) == null) ? 0 : models.size()) / getPageSize();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            int iIntValue = ((Number) obj).intValue();
            if (!this.fetchedPages.contains(Integer.valueOf(iIntValue)) && iIntValue > 0 && iIntValue <= size) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    private final void prefetchNextPage(ScrollDirection scrollDirection, int firstVisibleIndex, int lastVisibleIndex) {
        List<Integer> pagesToFetch;
        int i = PAGINATION_THRESHOLD;
        if (scrollDirection == ScrollDirection.DOWN) {
            int i2 = i + lastVisibleIndex;
            pagesToFetch = getPagesToFetch(i2, i2);
        } else {
            int i3 = firstVisibleIndex - i;
            pagesToFetch = getPagesToFetch(i3, i3);
        }
        fetchPagesIfNeeded(pagesToFetch, firstVisibleIndex, lastVisibleIndex, scrollDirection, true);
    }

    public final void registerDownloadStateListener(Uri uri) {
        this.groupDownloadStateModelProvider.registerDownloadStateModelListener(this.downloadStateModelProviderListener, uri);
    }

    public final void unregisterDownloadStateListener() {
        this.groupDownloadStateModelProvider.unregisterDownloadStateModelListener();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.track.LibraryTracksViewModel$handlePlayback$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LibraryTracksViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.track.LibraryTracksViewModel$handlePlayback$1", f = "LibraryTracksViewModel.kt", i = {0, 1, 1, 2, 2, 3, 3, 3}, l = {188, 202, 203, 209}, m = "invokeSuspend", n = {"repository", "repository", "databaseQuery", "databaseQuery", "trackCount", "databaseQuery", "trackList", "trackCount"}, s = {"L$0", "L$0", "L$1", "L$0", "I$0", "L$0", "L$1", "I$0"})
    static final class C03571 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ContentMetadata $contentMetadata;
        final /* synthetic */ Context $context;
        final /* synthetic */ Integer $position;
        final /* synthetic */ Boolean $shuffleAll;
        final /* synthetic */ Uri $tracksUri;
        int I$0;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03571(Boolean bool, Context context, ContentMetadata contentMetadata, Integer num, Uri uri, Continuation<? super C03571> continuation) {
            super(2, continuation);
            this.$shuffleAll = bool;
            this.$context = context;
            this.$contentMetadata = contentMetadata;
            this.$position = num;
            this.$tracksUri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LibraryTracksViewModel.this.new C03571(this.$shuffleAll, this.$context, this.$contentMetadata, this.$position, this.$tracksUri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03571) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:53:0x0109  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x011d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:57:0x011e  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0130 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x0142  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x0145  */
        /* JADX WARN: Removed duplicated region for block: B:67:0x014d  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x0150  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x0159  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r27) {
            /*
                Method dump skipped, instruction units count: 382
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.detailpages.track.LibraryTracksViewModel.C03571.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void handlePlayback(Context context, Uri tracksUri, ContentMetadata contentMetadata, Integer position, Boolean shuffleAll) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(tracksUri, "tracksUri");
        JobKt__JobKt.cancelChildren$default(this.playbackScope.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
        BuildersKt__Builders_commonKt.launch$default(this.playbackScope, null, null, new C03571(shuffleAll, context, contentMetadata, position, tracksUri, null), 3, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel, androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.groupDownloadStateModelProvider.unregisterDownloadStateModelListener();
    }

    public final void hasUpdatedPage() {
        this.lastUpdatedIndices = null;
    }

    /* JADX INFO: renamed from: getIndicesToUpdate, reason: from getter */
    public final IntRange getLastUpdatedIndices() {
        return this.lastUpdatedIndices;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected void didReplaceItemsAtRange(PageGridViewModel pageModel, IntRange range) {
        Intrinsics.checkNotNullParameter(range, "range");
        this.lastUpdatedIndices = range;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected void didCancelPageRequest(int startPageIndex, int pageSize, int pageCount) {
        Iterator<Integer> it = RangesKt___RangesKt.until(startPageIndex, pageCount + startPageIndex).iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            if (this.fetchedPages.contains(Integer.valueOf(iNextInt))) {
                this.fetchedPages.remove(Integer.valueOf(iNextInt));
            }
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel
    public int getPageSize() {
        return PAGE_SIZE;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel, com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public synchronized void fetchUpdates(int pageSize, BaseViewsDiffUtilFactory diffUtilFactory, FetchType fetchType) {
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
        if (fetchType != FetchType.SYNC) {
            super.fetchUpdates(pageSize, diffUtilFactory, fetchType);
        } else {
            fetchUpdatesForSync();
        }
    }

    private final void fetchUpdatesForSync() {
        int i;
        List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) this.fetchedPages);
        CollectionsKt__MutableCollectionsJVMKt.sort(mutableList);
        int size = mutableList.size();
        int i2 = 0;
        while (i2 < size) {
            int iIntValue = ((Number) mutableList.get(i2)).intValue();
            int i3 = i2;
            while (true) {
                i = i3 + 1;
                if (i >= size || ((Number) mutableList.get(i)).intValue() - ((Number) mutableList.get(i3)).intValue() != 1) {
                    break;
                } else {
                    i3 = i;
                }
            }
            fetchPageAndReplace(iIntValue, getPageSize(), (i3 - i2) + 1, false);
            i2 = i;
        }
    }

    private final void fetchPagesIfNeeded(List<Integer> pagesToFetch, int firstVisibleIndex, int lastVisibleIndex, ScrollDirection scrollDirection, boolean isPrefetching) {
        Integer num;
        Object next;
        Iterator<T> it = pagesToFetch.iterator();
        while (true) {
            num = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (!this.fetchedPages.contains(Integer.valueOf(((Number) next).intValue()))) {
                break;
            }
        }
        Integer num2 = (Integer) next;
        ListIterator<Integer> listIterator = pagesToFetch.listIterator(pagesToFetch.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                break;
            }
            Integer numPrevious = listIterator.previous();
            if (!this.fetchedPages.contains(Integer.valueOf(numPrevious.intValue()))) {
                num = numPrevious;
                break;
            }
        }
        Integer num3 = num;
        if (num2 != null || num3 != null) {
            fetchNewPages(pagesToFetch, isPrefetching);
        } else {
            if (isPrefetching) {
                return;
            }
            prefetchNextPage(scrollDirection, firstVisibleIndex, lastVisibleIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r9v1, types: [T, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object fetchTrackList(int r8, com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository<com.amazon.mp3.library.item.MusicTrack, com.amazon.mp3.library.refinement.datasource.DatabaseSource<com.amazon.mp3.library.item.MusicTrack>> r9, kotlin.coroutines.Continuation<? super java.util.List<? extends com.amazon.mp3.library.item.MusicTrack>> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof com.amazon.mp3.detailpages.track.LibraryTracksViewModel.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            com.amazon.mp3.detailpages.track.LibraryTracksViewModel$fetchTrackList$1 r0 = (com.amazon.mp3.detailpages.track.LibraryTracksViewModel.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.detailpages.track.LibraryTracksViewModel$fetchTrackList$1 r0 = new com.amazon.mp3.detailpages.track.LibraryTracksViewModel$fetchTrackList$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r8 = r0.L$0
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L5b
        L2d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L35:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlin.jvm.internal.Ref$ObjectRef r10 = new kotlin.jvm.internal.Ref$ObjectRef
            r10.<init>()
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
            r10.element = r2
            com.amazon.mp3.providers.LibraryTracksPlaybackHandler r2 = com.amazon.mp3.providers.LibraryTracksPlaybackHandler.INSTANCE
            r4 = 2
            r5 = 0
            boolean r8 = com.amazon.mp3.providers.LibraryTracksPlaybackHandler.shouldAllowCloudQueuePlayback$default(r2, r8, r5, r4, r5)
            if (r8 == 0) goto L6a
            r0.L$0 = r10
            r0.label = r3
            java.lang.Object r8 = r9.getDataSource(r0)
            if (r8 != r1) goto L58
            return r1
        L58:
            r6 = r10
            r10 = r8
            r8 = r6
        L5b:
            com.amazon.mp3.library.refinement.datasource.DatabaseSource r10 = (com.amazon.mp3.library.refinement.datasource.DatabaseSource) r10
            if (r10 != 0) goto L60
            goto L69
        L60:
            java.util.List r9 = r10.query()
            if (r9 != 0) goto L67
            goto L69
        L67:
            r8.element = r9
        L69:
            r10 = r8
        L6a:
            T r8 = r10.element
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.detailpages.track.LibraryTracksViewModel.fetchTrackList(int, com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<MusicTrack> fetchTrackAtIndex(final int index) {
        Observable<MusicTrack> observableSubscribeOn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.detailpages.track.LibraryTracksViewModel$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) throws InterruptedException {
                LibraryTracksViewModel.m897fetchTrackAtIndex$lambda8(this.f$0, index, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "create<MusicTrack> {\n   …hedulers.Schedulers.io())");
        return observableSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchTrackAtIndex$lambda-8, reason: not valid java name */
    public static final void m897fetchTrackAtIndex$lambda8(LibraryTracksViewModel this$0, int i, Subscriber subscriber) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BuildersKt__BuildersKt.runBlocking$default(null, new LibraryTracksViewModel$fetchTrackAtIndex$1$1(this$0, subscriber, i, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MusicTrack getTrackAtIndex(int index, DatabaseSourcePaged<MusicTrack> dataSource) {
        List<R> listQuery = dataSource.query(index, 1);
        if (listQuery == 0) {
            return null;
        }
        return (MusicTrack) CollectionsKt___CollectionsKt.firstOrNull((List) listQuery);
    }
}
