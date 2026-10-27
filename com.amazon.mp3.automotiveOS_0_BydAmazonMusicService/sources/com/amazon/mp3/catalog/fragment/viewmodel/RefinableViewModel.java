package com.amazon.mp3.catalog.fragment.viewmodel;

import android.app.Application;
import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinementLoadingListener;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.datasource.RefinableDataSource;
import com.amazon.mp3.library.refinement.filter.Filter;
import com.amazon.mp3.library.refinement.sort.Sort;
import com.amazon.mp3.view.refinements.BaseViewsRefinementOverflowManager;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.mp3.view.refinements.SingleSelectRefinementOverflowSession;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.models.TableRowItemModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.google.firebase.perf.util.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: RefinableViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\u0012\b\u0001\u0010\u0004*\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u00032\u00020\u0005B-\u0012\u0006\u0010Y\u001a\u00020X\u0012\u0012\u0010[\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010Z\u0012\b\b\u0002\u0010\\\u001a\u00020\b¢\u0006\u0004\b]\u0010^J \u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002J \u0010\u0012\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\bH\u0002J0\u0010\u0017\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00160\u00132\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\u001a\u001a\u00020\f2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0018H\u0004J\u0018\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\bH\u0014J2\u0010$\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010!\u001a\u00020 2\b\u0010#\u001a\u0004\u0018\u00010\"J\u0018\u0010&\u001a\u00020\f2\u0006\u0010%\u001a\u00020 2\u0006\u0010\u000b\u001a\u00020\nH\u0014J\u0010\u0010'\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010'\u001a\u00020\f2\u0006\u0010%\u001a\u00020\u000eH\u0016J\b\u0010)\u001a\u0004\u0018\u00010(J\u0012\u0010,\u001a\u00020\f2\b\u0010+\u001a\u0004\u0018\u00010*H\u0014J$\u0010/\u001a\u00020\f2\b\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u00010-H\u0014J/\u00103\u001a\u00020\f2\f\u00101\u001a\b\u0012\u0004\u0012\u0002000\u00132\f\u00102\u001a\b\u0012\u0004\u0012\u0002000\u0013H\u0094@ø\u0001\u0000¢\u0006\u0004\b3\u00104J\u0012\u00105\u001a\u00020\f2\b\u0010+\u001a\u0004\u0018\u00010*H\u0014J\u001a\u00107\u001a\u0004\u0018\u0001062\u0006\u0010.\u001a\u00020-2\b\u0010+\u001a\u0004\u0018\u00010*J\u0012\u00108\u001a\u0004\u0018\u0001062\u0006\u0010+\u001a\u00020*H\u0014J\u0006\u00109\u001a\u00020\fJ\b\u0010;\u001a\u00020:H\u0016J\u000e\u0010>\u001a\u00020\f2\u0006\u0010=\u001a\u00020<J\u0006\u0010?\u001a\u00020\fR$\u0010A\u001a\u0004\u0018\u00010@8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bA\u0010B\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR$\u0010H\u001a\u0004\u0018\u00010G8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u0018\u0010N\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bN\u0010OR\u0018\u0010=\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010PR0\u0010T\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00010\u00160Q8&X¦\u0004¢\u0006\u0006\u001a\u0004\bR\u0010SR,\u0010W\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010U0Q8&X¦\u0004¢\u0006\u0006\u001a\u0004\bV\u0010S\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006_"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinableViewModel;", "Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "D", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseAndroidViewModel;", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "isSelected", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "diffUtilFactory", "", "fetchUpdatesForRefinement", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "filterCollectionModel", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "handleAudioQualityClick", "", "Lcom/amazon/music/views/library/models/FilterItemModel;", "filters", "Lcom/amazon/mp3/library/refinement/filter/Filter;", "dbFilters", "", "refinements", "enableFilters", "refinementChanged", Constants.ENABLE_DISABLE, "onFilterToggled", "Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;", "refinementOverflowManager", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "refinementPillModel", "Landroid/view/View;", "view", "onRefinementClicked", "model", "onFilterClicked", "onFilterReset", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageFetchInfo;", "checkLastPageFetchInfo", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageModel", "handleInitPageResponse", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "fetchType", "handleUpdatedPageResponse", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "oldModels", "newModels", "checkModelsForUpdates", "(Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handlePaginatedResponse", "Lkotlin/ranges/IntRange;", "getRangeChangedForFetchType", "getRangeChangedForSort", "clearListeners", "", "getPageSize", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "contentEnabilityProvider", "setContentEnabilityProvider", "updateAvailabilityState", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "filterListener", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "getFilterListener", "()Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "setFilterListener", "(Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;)V", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementLoadingListener;", "loadingListener", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementLoadingListener;", "getLoadingListener", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementLoadingListener;", "setLoadingListener", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementLoadingListener;)V", "lastFetchedPageInfo", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageFetchInfo;", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "", "getFilterMapping", "()Ljava/util/Map;", "filterMapping", "Lcom/amazon/mp3/library/refinement/sort/Sort;", "getSortMapping", "sortMapping", "Landroid/app/Application;", "application", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;", "pageDataRepo", "usePagination", "<init>", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;Z)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class RefinableViewModel<R extends Refinable, D extends RefinableDataSource<R, ?>> extends BaseAndroidViewModel {
    private ContentEnabilityProvider contentEnabilityProvider;
    private OnFilterChangedListener filterListener;
    private PageFetchInfo lastFetchedPageInfo;
    private RefinementLoadingListener loadingListener;

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$checkModelsForUpdates$1, reason: invalid class name */
    /* JADX INFO: compiled from: RefinableViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel", f = "RefinableViewModel.kt", i = {0}, l = {249, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION}, m = "checkModelsForUpdates$suspendImpl", n = {"this"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RefinableViewModel<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(RefinableViewModel<R, D> refinableViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = refinableViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RefinableViewModel.checkModelsForUpdates$suspendImpl((RefinableViewModel) this.this$0, (List) null, (List) null, (Continuation) this);
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected Object checkModelsForUpdates(List<? extends BaseViewModel> list, List<? extends BaseViewModel> list2, Continuation<? super Unit> continuation) {
        return checkModelsForUpdates$suspendImpl((RefinableViewModel) this, (List) list, (List) list2, (Continuation) continuation);
    }

    public abstract Map<RefinementFilterType, Filter<R, ?, D>> getFilterMapping();

    public int getPageSize() {
        return 20;
    }

    public abstract Map<RefinementFilterType, Sort<R, D>> getSortMapping();

    public void onFilterReset(FilterCollectionModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
    }

    public /* synthetic */ RefinableViewModel(Application application, RefinablePageDataRepository refinablePageDataRepository, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(application, refinablePageDataRepository, (i & 4) != 0 ? false : z);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RefinableViewModel(Application application, RefinablePageDataRepository<R, D> pageDataRepo, boolean z) {
        super(application, pageDataRepo, z, false, 8, null);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pageDataRepo, "pageDataRepo");
    }

    public final void setFilterListener(OnFilterChangedListener onFilterChangedListener) {
        this.filterListener = onFilterChangedListener;
    }

    public final RefinementLoadingListener getLoadingListener() {
        return this.loadingListener;
    }

    public final void setLoadingListener(RefinementLoadingListener refinementLoadingListener) {
        this.loadingListener = refinementLoadingListener;
    }

    protected void onFilterToggled(RefinementFilterType refinementChanged, boolean isEnabled) {
        OnFilterChangedListener onFilterChangedListener;
        Intrinsics.checkNotNullParameter(refinementChanged, "refinementChanged");
        if (refinementChanged != RefinementFilterType.DOWNLOADED || (onFilterChangedListener = this.filterListener) == null) {
            return;
        }
        onFilterChangedListener.downloadSelectionChanged(isEnabled);
    }

    public final void onRefinementClicked(BaseViewsRefinementOverflowManager refinementOverflowManager, final BaseViewsDiffUtilFactory diffUtilFactory, final PageType pageType, final RefinementPillModel refinementPillModel, View view) {
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(refinementPillModel, "refinementPillModel");
        FilterModel content = refinementPillModel.getContent();
        if (content == null) {
            return;
        }
        if (content.isCollection()) {
            final FilterCollectionModel collectionModel = content.getCollectionModel();
            if (collectionModel == null) {
                return;
            }
            String title = collectionModel.getTitle();
            RefinementFilterType.Companion companion = RefinementFilterType.INSTANCE;
            if ((Intrinsics.areEqual(title, companion.getDisplayName(RefinementFilterType.SORT)) || Intrinsics.areEqual(collectionModel.getTitle(), companion.getDisplayName(RefinementFilterType.AUDIO_QUALITY))) && refinementOverflowManager != null) {
                refinementOverflowManager.showRefinementOverflow(collectionModel, view, new SingleSelectRefinementOverflowSession(collectionModel), new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        RefinableViewModel.m674onRefinementClicked$lambda9$lambda8(this.f$0, collectionModel, diffUtilFactory, pageType, refinementPillModel, view2);
                    }
                });
                return;
            }
            return;
        }
        if (content.isCollection()) {
            return;
        }
        RefinementLoadingListener loadingListener = getLoadingListener();
        if (loadingListener != null) {
            RefinementLoadingListener.DefaultImpls.displayRefinementLoading$default(loadingListener, false, 1, null);
        }
        onFilterClicked(refinementPillModel, diffUtilFactory);
        FilterItemModel itemModel = content.getItemModel();
        if (itemModel == null) {
            return;
        }
        BaseViewsRefinementManager.INSTANCE.sendRefinementUiClickMetric(itemModel, pageType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onRefinementClicked$lambda-9$lambda-8, reason: not valid java name */
    public static final void m674onRefinementClicked$lambda9$lambda8(RefinableViewModel this$0, FilterCollectionModel collectionFilter, BaseViewsDiffUtilFactory diffUtilFactory, PageType pageType, RefinementPillModel refinementPillModel, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(collectionFilter, "$collectionFilter");
        Intrinsics.checkNotNullParameter(diffUtilFactory, "$diffUtilFactory");
        Intrinsics.checkNotNullParameter(pageType, "$pageType");
        Intrinsics.checkNotNullParameter(refinementPillModel, "$refinementPillModel");
        RefinementLoadingListener refinementLoadingListener = this$0.loadingListener;
        if (refinementLoadingListener != null) {
            RefinementLoadingListener.DefaultImpls.displayRefinementLoading$default(refinementLoadingListener, false, 1, null);
        }
        if (Intrinsics.areEqual(collectionFilter.getTitle(), RefinementFilterType.INSTANCE.getDisplayName(RefinementFilterType.AUDIO_QUALITY))) {
            this$0.handleAudioQualityClick(collectionFilter, diffUtilFactory, pageType);
        } else {
            this$0.onFilterClicked(refinementPillModel, diffUtilFactory);
            Iterator it = CollectionsKt___CollectionsKt.filterNotNull(collectionFilter.getFilters()).iterator();
            while (it.hasNext()) {
                BaseViewsRefinementManager.INSTANCE.sendRefinementUiClickMetric((FilterItemModel) it.next(), pageType);
            }
        }
        refinementPillModel.updateFilterModel(collectionFilter);
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onFilterClicked(com.amazon.music.views.library.models.RefinementPillModel r12, com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory r13) {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel.onFilterClicked(com.amazon.music.views.library.models.RefinementPillModel, com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory):void");
    }

    private final void fetchUpdatesForRefinement(RefinementFilterType type, boolean isSelected, BaseViewsDiffUtilFactory diffUtilFactory) {
        if (type == RefinementFilterType.DOWNLOADED) {
            ((RefinablePageDataRepository) getPageDataRepository()).shouldUseOfflineSource(isSelected);
            fetchUpdates(getPageSize(), diffUtilFactory, FetchType.FILTER);
            return;
        }
        Filter<R, ?, D> filter = getFilterMapping().get(type);
        if (filter != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C03301(isSelected, this, filter, diffUtilFactory, null), 3, null);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.library.refinement.filter.Filter<R of com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel, *, D of com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel>");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$fetchUpdatesForRefinement$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RefinableViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0012\b\u0001\u0010\u0003*\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0002*\u00020\u0004H\u008a@"}, d2 = {"Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "D", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$fetchUpdatesForRefinement$1", f = "RefinableViewModel.kt", i = {}, l = {178, 180}, m = "invokeSuspend", n = {}, s = {})
    static final class C03301 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BaseViewsDiffUtilFactory $diffUtilFactory;
        final /* synthetic */ Filter<R, ?, D> $filter;
        final /* synthetic */ boolean $isSelected;
        int label;
        final /* synthetic */ RefinableViewModel<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03301(boolean z, RefinableViewModel<R, D> refinableViewModel, Filter<R, ?, D> filter, BaseViewsDiffUtilFactory baseViewsDiffUtilFactory, Continuation<? super C03301> continuation) {
            super(2, continuation);
            this.$isSelected = z;
            this.this$0 = refinableViewModel;
            this.$filter = filter;
            this.$diffUtilFactory = baseViewsDiffUtilFactory;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03301(this.$isSelected, this.this$0, this.$filter, this.$diffUtilFactory, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03301) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$isSelected) {
                    RefinablePageDataRepository refinablePageDataRepository = (RefinablePageDataRepository) this.this$0.getPageDataRepository();
                    Filter<R, ?, D> filter = this.$filter;
                    this.label = 1;
                    if (refinablePageDataRepository.addFilter(filter, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    RefinablePageDataRepository refinablePageDataRepository2 = (RefinablePageDataRepository) this.this$0.getPageDataRepository();
                    Filter<R, ?, D> filter2 = this.$filter;
                    this.label = 2;
                    if (refinablePageDataRepository2.removeFilter(filter2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i == 1 || i == 2) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            RefinableViewModel<R, D> refinableViewModel = this.this$0;
            refinableViewModel.fetchUpdates(refinableViewModel.getPageSize(), this.$diffUtilFactory, FetchType.FILTER);
            return Unit.INSTANCE;
        }
    }

    private final void handleAudioQualityClick(FilterCollectionModel filterCollectionModel, BaseViewsDiffUtilFactory diffUtilFactory, PageType pageType) {
        FilterItemModel filterItemModel = (FilterItemModel) CollectionsKt___CollectionsKt.firstOrNull((List) filters(filterCollectionModel, true));
        if (filterItemModel != null) {
            BaseViewsRefinementManager.INSTANCE.sendRefinementUiClickMetric(filterItemModel, pageType);
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C03312(this, filterCollectionModel, diffUtilFactory, null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$handleAudioQualityClick$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RefinableViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0012\b\u0001\u0010\u0003*\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0002*\u00020\u0004H\u008a@"}, d2 = {"Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "D", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$handleAudioQualityClick$2", f = "RefinableViewModel.kt", i = {0}, l = {197, 201}, m = "invokeSuspend", n = {"selectedFilters"}, s = {"L$0"})
    static final class C03312 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BaseViewsDiffUtilFactory $diffUtilFactory;
        final /* synthetic */ FilterCollectionModel $filterCollectionModel;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ RefinableViewModel<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03312(RefinableViewModel<R, D> refinableViewModel, FilterCollectionModel filterCollectionModel, BaseViewsDiffUtilFactory baseViewsDiffUtilFactory, Continuation<? super C03312> continuation) {
            super(2, continuation);
            this.this$0 = refinableViewModel;
            this.$filterCollectionModel = filterCollectionModel;
            this.$diffUtilFactory = baseViewsDiffUtilFactory;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03312(this.this$0, this.$filterCollectionModel, this.$diffUtilFactory, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03312) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0085  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L33
                if (r1 == r3) goto L23
                if (r1 != r2) goto L1b
                java.lang.Object r1 = r8.L$1
                java.util.Iterator r1 = (java.util.Iterator) r1
                java.lang.Object r3 = r8.L$0
                com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel r3 = (com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel) r3
                kotlin.ResultKt.throwOnFailure(r9)
                r9 = r8
                goto L7f
            L1b:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L23:
                java.lang.Object r1 = r8.L$2
                java.util.Iterator r1 = (java.util.Iterator) r1
                java.lang.Object r4 = r8.L$1
                com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel r4 = (com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel) r4
                java.lang.Object r5 = r8.L$0
                java.util.List r5 = (java.util.List) r5
                kotlin.ResultKt.throwOnFailure(r9)
                goto L53
            L33:
                kotlin.ResultKt.throwOnFailure(r9)
                com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel<R extends com.amazon.mp3.library.refinement.Refinable, D extends com.amazon.mp3.library.refinement.datasource.RefinableDataSource<R, ?>> r9 = r8.this$0
                com.amazon.music.views.library.models.FilterCollectionModel r1 = r8.$filterCollectionModel
                r4 = 0
                java.util.List r9 = com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel.access$dbFilters(r9, r1, r4)
                com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel<R extends com.amazon.mp3.library.refinement.Refinable, D extends com.amazon.mp3.library.refinement.datasource.RefinableDataSource<R, ?>> r1 = r8.this$0
                com.amazon.music.views.library.models.FilterCollectionModel r4 = r8.$filterCollectionModel
                java.util.List r1 = com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel.access$dbFilters(r1, r4, r3)
                java.util.List r9 = kotlin.collections.CollectionsKt.filterNotNull(r9)
                com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel<R extends com.amazon.mp3.library.refinement.Refinable, D extends com.amazon.mp3.library.refinement.datasource.RefinableDataSource<R, ?>> r4 = r8.this$0
                java.util.Iterator r9 = r9.iterator()
                r5 = r1
                r1 = r9
            L53:
                r9 = r8
            L54:
                boolean r6 = r1.hasNext()
                if (r6 == 0) goto L75
                java.lang.Object r6 = r1.next()
                com.amazon.mp3.library.refinement.filter.Filter r6 = (com.amazon.mp3.library.refinement.filter.Filter) r6
                com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository r7 = r4.getPageDataRepository()
                com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository r7 = (com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository) r7
                r9.L$0 = r5
                r9.L$1 = r4
                r9.L$2 = r1
                r9.label = r3
                java.lang.Object r6 = r7.removeFilter(r6, r9)
                if (r6 != r0) goto L54
                return r0
            L75:
                java.util.List r1 = kotlin.collections.CollectionsKt.filterNotNull(r5)
                com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel<R extends com.amazon.mp3.library.refinement.Refinable, D extends com.amazon.mp3.library.refinement.datasource.RefinableDataSource<R, ?>> r3 = r9.this$0
                java.util.Iterator r1 = r1.iterator()
            L7f:
                boolean r4 = r1.hasNext()
                if (r4 == 0) goto La1
                java.lang.Object r4 = r1.next()
                com.amazon.mp3.library.refinement.filter.Filter r4 = (com.amazon.mp3.library.refinement.filter.Filter) r4
                com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository r5 = r3.getPageDataRepository()
                com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository r5 = (com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository) r5
                r9.L$0 = r3
                r9.L$1 = r1
                r6 = 0
                r9.L$2 = r6
                r9.label = r2
                java.lang.Object r4 = r5.addFilter(r4, r9)
                if (r4 != r0) goto L7f
                return r0
            La1:
                com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel<R extends com.amazon.mp3.library.refinement.Refinable, D extends com.amazon.mp3.library.refinement.datasource.RefinableDataSource<R, ?>> r0 = r9.this$0
                int r1 = r0.getPageSize()
                com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory r9 = r9.$diffUtilFactory
                com.amazon.mp3.catalog.fragment.viewmodel.FetchType r2 = com.amazon.mp3.catalog.fragment.viewmodel.FetchType.FILTER
                r0.fetchUpdates(r1, r9, r2)
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel.C03312.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final List<FilterItemModel> filters(FilterCollectionModel filterCollectionModel, boolean isSelected) {
        List<FilterItemModel> listFilterNotNull = CollectionsKt___CollectionsKt.filterNotNull(filterCollectionModel.getFilters());
        ArrayList arrayList = new ArrayList();
        for (FilterItemModel filterItemModel : listFilterNotNull) {
            if (!(filterItemModel.getSelected() == isSelected)) {
                filterItemModel = null;
            }
            if (filterItemModel != null) {
                arrayList.add(filterItemModel);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Filter<R, ?, D>> dbFilters(FilterCollectionModel filterCollectionModel, boolean isSelected) {
        List<FilterItemModel> listFilters = filters(filterCollectionModel, isSelected);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listFilters.iterator();
        while (it.hasNext()) {
            Filter<R, ?, D> filter = getFilterMapping().get(RefinableViewModelKt.getType((FilterItemModel) it.next()));
            if (filter != null) {
                arrayList.add(filter);
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$onFilterReset$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RefinableViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0012\b\u0001\u0010\u0003*\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0002*\u00020\u0004H\u008a@"}, d2 = {"Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "D", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$onFilterReset$1", f = "RefinableViewModel.kt", i = {}, l = {219}, m = "invokeSuspend", n = {}, s = {})
    static final class C03321 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BaseViewsDiffUtilFactory $diffUtilFactory;
        int label;
        final /* synthetic */ RefinableViewModel<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03321(RefinableViewModel<R, D> refinableViewModel, BaseViewsDiffUtilFactory baseViewsDiffUtilFactory, Continuation<? super C03321> continuation) {
            super(2, continuation);
            this.this$0 = refinableViewModel;
            this.$diffUtilFactory = baseViewsDiffUtilFactory;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03321(this.this$0, this.$diffUtilFactory, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03321) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                RefinablePageDataRepository refinablePageDataRepository = (RefinablePageDataRepository) this.this$0.getPageDataRepository();
                this.label = 1;
                if (refinablePageDataRepository.reset(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            RefinableViewModel<R, D> refinableViewModel = this.this$0;
            refinableViewModel.fetchUpdates(refinableViewModel.getPageSize(), this.$diffUtilFactory, FetchType.FILTER);
            this.this$0.onFilterToggled(RefinementFilterType.DOWNLOADED, false);
            return Unit.INSTANCE;
        }
    }

    public void onFilterReset(BaseViewsDiffUtilFactory diffUtilFactory) {
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
        RefinementLoadingListener refinementLoadingListener = this.loadingListener;
        if (refinementLoadingListener != null) {
            RefinementLoadingListener.DefaultImpls.displayRefinementLoading$default(refinementLoadingListener, false, 1, null);
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C03321(this, diffUtilFactory, null), 3, null);
    }

    /* JADX INFO: renamed from: checkLastPageFetchInfo, reason: from getter */
    public final PageFetchInfo getLastFetchedPageInfo() {
        return this.lastFetchedPageInfo;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected void handleInitPageResponse(PageGridViewModel pageModel) {
        this.lastFetchedPageInfo = new PageFetchInfo(FetchType.INIT, null, 2, null);
        super.handleInitPageResponse(pageModel);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected void handleUpdatedPageResponse(PageGridViewModel pageModel, BaseViewsDiffUtilFactory diffUtilFactory, FetchType fetchType) {
        PageFetchInfo pageFetchInfo;
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
        if (fetchType != null) {
            pageFetchInfo = new PageFetchInfo(fetchType, getRangeChangedForFetchType(fetchType, pageModel));
        } else {
            pageFetchInfo = new PageFetchInfo(FetchType.UPDATE, null, 2, null);
        }
        this.lastFetchedPageInfo = pageFetchInfo;
        super.handleUpdatedPageResponse(pageModel, diffUtilFactory, fetchType);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object checkModelsForUpdates$suspendImpl(com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel r5, java.util.List r6, java.util.List r7, kotlin.coroutines.Continuation r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$checkModelsForUpdates$1 r0 = (com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$checkModelsForUpdates$1 r0 = new com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$checkModelsForUpdates$1
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3c
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5f
        L2c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L34:
            java.lang.Object r5 = r0.L$0
            com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel r5 = (com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel) r5
            kotlin.ResultKt.throwOnFailure(r8)
            goto L4a
        L3c:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = super.checkModelsForUpdates(r6, r7, r0)
            if (r6 != r1) goto L4a
            return r1
        L4a:
            kotlinx.coroutines.MainCoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getMain()
            com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$checkModelsForUpdates$2 r7 = new com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$checkModelsForUpdates$2
            r8 = 0
            r7.<init>(r5, r8)
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r6, r7, r0)
            if (r8 != r1) goto L5f
            return r1
        L5f:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel.checkModelsForUpdates$suspendImpl(com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel, java.util.List, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$checkModelsForUpdates$2, reason: invalid class name */
    /* JADX INFO: compiled from: RefinableViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0006\u001a\u0004\u0018\u00010\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0012\b\u0001\u0010\u0003*\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0002*\u00020\u0004H\u008a@"}, d2 = {"Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "D", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$checkModelsForUpdates$2", f = "RefinableViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ RefinableViewModel<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(RefinableViewModel<R, D> refinableViewModel, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = refinableViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            RefinementLoadingListener loadingListener = this.this$0.getLoadingListener();
            if (loadingListener == null) {
                return null;
            }
            loadingListener.displayRefinementLoading(false);
            return Unit.INSTANCE;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected void handlePaginatedResponse(PageGridViewModel pageModel) {
        this.lastFetchedPageInfo = new PageFetchInfo(FetchType.MORE, null, 2, null);
        super.handlePaginatedResponse(pageModel);
    }

    public final IntRange getRangeChangedForFetchType(FetchType fetchType, PageGridViewModel pageModel) {
        Intrinsics.checkNotNullParameter(fetchType, "fetchType");
        if (pageModel != null && FetchType.SORT == fetchType) {
            return getRangeChangedForSort(pageModel);
        }
        return null;
    }

    protected IntRange getRangeChangedForSort(PageGridViewModel pageModel) {
        Intrinsics.checkNotNullParameter(pageModel, "pageModel");
        Iterator<BaseViewModel> it = pageModel.getModels().iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next() instanceof VisualRowItemModel) {
                break;
            }
            i++;
        }
        if (i >= 0) {
            return new IntRange(i, pageModel.getModels().size() - 1);
        }
        return null;
    }

    public final void clearListeners() {
        this.loadingListener = null;
        this.filterListener = null;
        PageDataRepository pageDataRepository = getPageDataRepository();
        RefinablePageDataRepository refinablePageDataRepository = pageDataRepository instanceof RefinablePageDataRepository ? (RefinablePageDataRepository) pageDataRepository : null;
        if (refinablePageDataRepository == null) {
            return;
        }
        refinablePageDataRepository.clearListeners();
    }

    public final void setContentEnabilityProvider(ContentEnabilityProvider contentEnabilityProvider) {
        Intrinsics.checkNotNullParameter(contentEnabilityProvider, "contentEnabilityProvider");
        this.contentEnabilityProvider = contentEnabilityProvider;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$updateAvailabilityState$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RefinableViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0012\b\u0001\u0010\u0003*\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0002*\u00020\u0004H\u008a@"}, d2 = {"Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "D", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel$updateAvailabilityState$1", f = "RefinableViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ RefinableViewModel<R, D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03331(RefinableViewModel<R, D> refinableViewModel, Continuation<? super C03331> continuation) {
            super(2, continuation);
            this.this$0 = refinableViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03331(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03331) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List<BaseViewModel> models;
            List<TableRowItemModel> listFilterIsInstance;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            PageGridViewModel value = this.this$0.getPageData().getValue();
            if (value != null && (models = value.getModels()) != null && (listFilterIsInstance = CollectionsKt___CollectionsJvmKt.filterIsInstance(models, TableRowItemModel.class)) != null) {
                RefinableViewModel<R, D> refinableViewModel = this.this$0;
                for (TableRowItemModel tableRowItemModel : listFilterIsInstance) {
                    ContentEnabilityProvider contentEnabilityProvider = ((RefinableViewModel) refinableViewModel).contentEnabilityProvider;
                    if (contentEnabilityProvider != null) {
                        tableRowItemModel.updateAvailabilityState(contentEnabilityProvider.getAvailabilityState(tableRowItemModel.getMetadata()));
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final void updateAvailabilityState() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C03331(this, null), 3, null);
    }

    protected final void enableFilters(Set<? extends RefinementFilterType> refinements) {
        if (refinements != null) {
            ArrayList<RefinementFilterType> arrayList = new ArrayList();
            for (Object obj : refinements) {
                if (getSortMapping().containsKey((RefinementFilterType) obj)) {
                    arrayList.add(obj);
                }
            }
            for (RefinementFilterType refinementFilterType : arrayList) {
                BuildersKt__BuildersKt.runBlocking$default(null, new RefinableViewModel$enableFilters$2$1$1(getSortMapping().get(refinementFilterType), this, null), 1, null);
                onFilterToggled(refinementFilterType, true);
            }
        }
        if (refinements == null) {
            return;
        }
        ArrayList<RefinementFilterType> arrayList2 = new ArrayList();
        for (Object obj2 : refinements) {
            if (getFilterMapping().containsKey((RefinementFilterType) obj2)) {
                arrayList2.add(obj2);
            }
        }
        for (RefinementFilterType refinementFilterType2 : arrayList2) {
            Filter<R, ?, D> filter = getFilterMapping().get(refinementFilterType2);
            if (filter != null) {
                BuildersKt__BuildersKt.runBlocking$default(null, new RefinableViewModel$enableFilters$4$1$1$1(this, filter, null), 1, null);
            }
            onFilterToggled(refinementFilterType2, true);
        }
    }
}
