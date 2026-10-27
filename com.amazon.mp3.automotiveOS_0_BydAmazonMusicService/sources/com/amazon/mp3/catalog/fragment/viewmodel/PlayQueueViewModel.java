package com.amazon.mp3.catalog.fragment.viewmodel;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.datasource.repository.EditablePageDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.detailpages.BaseViewsDragDropManager;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.TextRowItemModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: PlayQueueViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 _2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001_B!\u0012\u0006\u0010Y\u001a\u00020X\u0012\u0006\u0010[\u001a\u00020Z\u0012\b\b\u0002\u0010\\\u001a\u000201¢\u0006\u0004\b]\u0010^J&\u0010\f\u001a\u00020\u000b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0002J\u001c\u0010\u0010\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\bH\u0002J\u001a\u0010\u0012\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010\u000f\u001a\u00020\bH\u0002J\u001a\u0010\u0013\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010\u000f\u001a\u00020\bH\u0002J\b\u0010\u0014\u001a\u00020\bH\u0002J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\bH\u0002J\u001e\u0010\u001a\u001a\u00020\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0012\u0010\u001c\u001a\u00020\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\rH\u0014J\u0018\u0010 \u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\bH\u0014J\u001e\u0010#\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\bJ\u0018\u0010&\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\bH\u0016J \u0010(\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b2\u0006\u0010'\u001a\u00020\rH\u0016J\u0010\u0010*\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\bH\u0016J\b\u0010+\u001a\u00020\u000bH\u0016J\u0018\u0010.\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\bH\u0016J\u0018\u00102\u001a\u0002012\u0006\u00100\u001a\u00020/2\u0006\u0010\u0019\u001a\u00020/H\u0016J\b\u00103\u001a\u00020\u000bH\u0014J\b\u00104\u001a\u00020\u000bH\u0016J\u000f\u00105\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b5\u00106R\u001c\u00109\u001a\n 8*\u0004\u0018\u000107078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R\u001b\u0010F\u001a\u00020A8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bD\u0010ER\u0014\u0010H\u001a\u00020G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010J\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u0010:R\u0014\u0010K\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010:R\"\u0010L\u001a\u0002018\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bL\u0010N\"\u0004\bO\u0010PR\u0016\u0010Q\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bQ\u0010RR(\u0010T\u001a\u0004\u0018\u00010\r2\b\u0010S\u001a\u0004\u0018\u00010\r8\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\bT\u0010U\"\u0004\bV\u0010WR\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010R¨\u0006`"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PlayQueueViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseAndroidViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/EditableViewModelProvider;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "Lcom/amazon/mp3/detailpages/BaseViewsDragDropManager$Listener;", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "models", "", "startIndex", "endIndex", "", "updateIndexes", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageGridViewModel", "currentIndex", "getDisplayedPageModel", "", "addTextRowItemModels", "removeCurrentPlayingVisualRowItem", "getCurrentPlayingTrackIndex", "displayIndex", "findPositionInDataList", "searchList", "Lcom/amazon/music/views/library/models/VisualRowItemModel;", "targetModel", "findPositionForVisualRowItemModel", "pageModel", "onPageResult", "", "error", "pageIndex", "onPageError", "originalPageData", "pageSize", "fetchMorePage", "from", "to", "reorderModel", "newDataModel", "onModelsReordered", "index", "deleteModel", "clearModels", "fromIndex", "toIndex", "onItemMoved", "", "model", "", "shouldMoveItem", "onCleared", "onPlayStateChanged", "getUpcomingHeaderPosition", "()Ljava/lang/Integer;", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lkotlinx/coroutines/CompletableJob;", "viewModelJob", "Lkotlinx/coroutines/CompletableJob;", "Lkotlinx/coroutines/CoroutineScope;", "defaultScope", "Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController$delegate", "Lkotlin/Lazy;", "getPlaybackController", "()Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/detailpages/BaseViewsDragDropManager;", "dragDropManager", "Lcom/amazon/mp3/detailpages/BaseViewsDragDropManager;", "previousHeaderString", "upcomingHeaderString", "isCarModePlayQueue", "Z", "()Z", "setCarModePlayQueue", "(Z)V", "currentTrackIndex", "I", "value", "dataPageModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "setDataPageModel", "(Lcom/amazon/music/views/library/models/PageGridViewModel;)V", "Landroid/app/Application;", "application", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/EditablePageDataRepository;", "editablePageDataRepository", "initEqualizer", "<init>", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/EditablePageDataRepository;Z)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlayQueueViewModel extends BaseAndroidViewModel implements EditableViewModelProvider, OnPlayStateChangedListener, BaseViewsDragDropManager.Listener {
    private final String TAG;
    private int currentTrackIndex;
    private PageGridViewModel dataPageModel;
    private final CoroutineScope defaultScope;
    private final BaseViewsDragDropManager dragDropManager;
    private boolean isCarModePlayQueue;

    /* JADX INFO: renamed from: playbackController$delegate, reason: from kotlin metadata */
    private final Lazy playbackController;
    private final String previousHeaderString;
    private int startIndex;
    private final String upcomingHeaderString;
    private final CompletableJob viewModelJob;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayQueueViewModel(Application application, EditablePageDataRepository editablePageDataRepository, boolean z) {
        super(application, editablePageDataRepository, false, z);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(editablePageDataRepository, "editablePageDataRepository");
        this.TAG = PlayQueueViewModel.class.getSimpleName();
        CompletableJob completableJobSupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        this.viewModelJob = completableJobSupervisorJob$default;
        this.defaultScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(completableJobSupervisorJob$default));
        this.playbackController = LazyKt__LazyJVMKt.lazy(new Function0<ActionValidatedPlaybackController>() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.PlayQueueViewModel$playbackController$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ActionValidatedPlaybackController invoke() {
                return PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
            }
        });
        this.dragDropManager = new BaseViewsDragDropManager(this);
        String string = application.getResources().getString(R.string.dmusic_now_playing_queue_previous_song);
        Intrinsics.checkNotNullExpressionValue(string, "application.resources.ge…ying_queue_previous_song)");
        this.previousHeaderString = string;
        String string2 = application.getResources().getString(R.string.dmusic_now_playing_queue_upcoming_song);
        Intrinsics.checkNotNullExpressionValue(string2, "application.resources.ge…ying_queue_upcoming_song)");
        this.upcomingHeaderString = string2;
        this.currentTrackIndex = getPlaybackController().getCurrentIndex();
        getPlaybackController().addOnPlayStateChangedListener(this);
    }

    private final ActionValidatedPlaybackController getPlaybackController() {
        return (ActionValidatedPlaybackController) this.playbackController.getValue();
    }

    public final void setCarModePlayQueue(boolean z) {
        this.isCarModePlayQueue = z;
    }

    private final void setDataPageModel(PageGridViewModel pageGridViewModel) {
        List<BaseViewModel> models;
        Integer rowNumber;
        this.dataPageModel = pageGridViewModel;
        int iIntValue = 0;
        BaseViewModel baseViewModel = (pageGridViewModel == null || (models = pageGridViewModel.getModels()) == null) ? null : (BaseViewModel) CollectionsKt___CollectionsKt.getOrNull(models, 0);
        VisualRowItemModel visualRowItemModel = baseViewModel instanceof VisualRowItemModel ? (VisualRowItemModel) baseViewModel : null;
        if (visualRowItemModel != null && (rowNumber = visualRowItemModel.getRowNumber()) != null) {
            iIntValue = rowNumber.intValue();
        }
        this.startIndex = iIntValue;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected void onPageResult(PageGridViewModel pageModel) {
        setDataPageModel(pageModel);
        getPageData().postValue(getDisplayedPageModel(pageModel, getCurrentPlayingTrackIndex()));
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected void onPageError(Throwable error, int pageIndex) {
        Intrinsics.checkNotNullParameter(error, "error");
        Log.error(this.TAG, "Error trying to load PlayQueue page at index " + pageIndex + ": " + ((Object) error.getMessage()));
    }

    public final void fetchMorePage(PageGridViewModel originalPageData, final int pageIndex, int pageSize) {
        Intrinsics.checkNotNullParameter(originalPageData, "originalPageData");
        if (pageIndex <= originalPageData.getPageIndex()) {
            return;
        }
        Disposable disposableSubscribe = PageDataRepository.fetchPageViewModels$default(getPageDataRepository(), pageIndex, pageSize, FetchType.MORE, null, 8, null).subscribeOn(Schedulers.from(getExecutorService())).observeOn(AndroidSchedulers.mainThread()).map(new Function() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.PlayQueueViewModel$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return PlayQueueViewModel.m665fetchMorePage$lambda1(this.f$0, pageIndex, (PageGridViewModel) obj);
            }
        }).subscribe(new BiConsumer() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.PlayQueueViewModel$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PlayQueueViewModel.m666fetchMorePage$lambda2(this.f$0, pageIndex, (PageGridViewModel) obj, (Throwable) obj2);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "pageDataRepository.fetch…eIndex)\n                }");
        getCompositeDisposable().add(disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchMorePage$lambda-1, reason: not valid java name */
    public static final PageGridViewModel m665fetchMorePage$lambda1(PlayQueueViewModel this$0, int i, PageGridViewModel newPageData) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(newPageData, "newPageData");
        PageGridViewModel pageGridViewModel = this$0.dataPageModel;
        PageGridViewModel pageGridViewModelMergePageData = pageGridViewModel == null ? null : this$0.mergePageData(pageGridViewModel, newPageData, i);
        if (pageGridViewModelMergePageData != null) {
            return pageGridViewModelMergePageData;
        }
        throw new NullPointerException("OriginalPageData should not be null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchMorePage$lambda-2, reason: not valid java name */
    public static final void m666fetchMorePage$lambda2(PlayQueueViewModel this$0, int i, PageGridViewModel pageGridViewModel, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (th == null) {
            this$0.onPageResult(pageGridViewModel);
        } else {
            this$0.onPageError(th, i);
        }
    }

    public void reorderModel(int from, int to) {
        this.dragDropManager.reorderModel(from, to, this.dataPageModel);
        Log.info(this.TAG, "Reorder the track from " + (this.startIndex + from) + " to " + (this.startIndex + to));
        EditablePageDataRepository editablePageDataRepository = (EditablePageDataRepository) getPageDataRepository();
        int i = this.startIndex;
        editablePageDataRepository.reorderModel(from + i, i + to);
    }

    @Override // com.amazon.mp3.detailpages.BaseViewsDragDropManager.Listener
    public void onModelsReordered(int from, int to, PageGridViewModel newDataModel) {
        Intrinsics.checkNotNullParameter(newDataModel, "newDataModel");
        IntRange intRange = new IntRange(Math.min(from, to), Math.max(from, to));
        int first = intRange.getFirst();
        int last = intRange.getLast();
        int i = this.currentTrackIndex;
        boolean z = false;
        if (first <= i && i <= last) {
            z = true;
        }
        if (z) {
            return;
        }
        getPageData().postValue(getDisplayedPageModel(this.dataPageModel, getCurrentPlayingTrackIndex()));
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.PlayQueueViewModel$deleteModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayQueueViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.PlayQueueViewModel$deleteModel$1", f = "PlayQueueViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $index;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(int i, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$index = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlayQueueViewModel.this.new AnonymousClass1(this.$index, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List<BaseViewModel> models;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            PageGridViewModel pageGridViewModel = PlayQueueViewModel.this.dataPageModel;
            List mutableList = null;
            if (pageGridViewModel != null && (models = pageGridViewModel.getModels()) != null) {
                mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) models);
            }
            if (mutableList != null) {
                int i = this.$index;
                boolean z = false;
                if (i >= 0 && i < mutableList.size()) {
                    int size = mutableList.size();
                    int i2 = PlayQueueViewModel.this.currentTrackIndex;
                    int i3 = this.$index;
                    if (i3 <= i2 && i2 < size) {
                        z = true;
                    }
                    mutableList.remove(i3);
                    PlayQueueViewModel.this.updateIndexes(mutableList, this.$index, mutableList.size());
                    PageGridViewModel pageGridViewModel2 = PlayQueueViewModel.this.dataPageModel;
                    if (pageGridViewModel2 != null) {
                        pageGridViewModel2.updateModels(mutableList);
                    }
                    int currentPlayingTrackIndex = z ? PlayQueueViewModel.this.getCurrentPlayingTrackIndex() - 1 : PlayQueueViewModel.this.getCurrentPlayingTrackIndex();
                    MutableLiveData<PageGridViewModel> pageData = PlayQueueViewModel.this.getPageData();
                    PlayQueueViewModel playQueueViewModel = PlayQueueViewModel.this;
                    pageData.postValue(playQueueViewModel.getDisplayedPageModel(playQueueViewModel.dataPageModel, currentPlayingTrackIndex));
                    Log.info(PlayQueueViewModel.this.TAG, Intrinsics.stringPlus("Delete the track in ", Boxing.boxInt(PlayQueueViewModel.this.startIndex + this.$index)));
                    ((EditablePageDataRepository) PlayQueueViewModel.this.getPageDataRepository()).deleteModel(PlayQueueViewModel.this.startIndex + this.$index);
                }
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.EditableViewModelProvider
    public void deleteModel(int index) {
        BuildersKt__Builders_commonKt.launch$default(this.defaultScope, null, null, new AnonymousClass1(index, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateIndexes(List<? extends BaseViewModel> models, int startIndex, int endIndex) {
        while (startIndex < endIndex) {
            int i = startIndex + 1;
            BaseViewModel baseViewModel = models.get(startIndex);
            if (baseViewModel instanceof VisualRowItemModel) {
                ((VisualRowItemModel) baseViewModel).updateRowNumber(Integer.valueOf(startIndex));
            }
            startIndex = i;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.EditableViewModelProvider
    public void clearModels() {
        getPlaybackController().stop(ChangeReason.SOURCE_MODIFIED);
        ((EditablePageDataRepository) getPageDataRepository()).clearModels();
    }

    @Override // com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter.ItemMovedListener
    public void onItemMoved(int fromIndex, int toIndex) {
        int iFindPositionInDataList = findPositionInDataList(fromIndex);
        int iFindPositionInDataList2 = findPositionInDataList(toIndex);
        if (iFindPositionInDataList != -1 && iFindPositionInDataList2 != -1) {
            reorderModel(iFindPositionInDataList, iFindPositionInDataList2);
        } else {
            Log.error(this.TAG, "dataFromIndex and dataToIndex should not be invalid");
        }
    }

    @Override // com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter.ItemMovedListener
    public boolean shouldMoveItem(Object model, Object targetModel) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(targetModel, "targetModel");
        return ((targetModel instanceof TextRowItemModel) && Intrinsics.areEqual(((TextRowItemModel) targetModel).getText(), this.previousHeaderString)) ? false : true;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel, androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        getPlaybackController().removeOnPlayStateChangedListener(this);
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        if (getCurrentPlayingTrackIndex() != this.currentTrackIndex) {
            if (!this.isCarModePlayQueue) {
                getPageData().postValue(getDisplayedPageModel(this.dataPageModel, getCurrentPlayingTrackIndex()));
            }
            this.currentTrackIndex = getCurrentPlayingTrackIndex();
        }
    }

    public final Integer getUpcomingHeaderPosition() {
        List<BaseViewModel> models;
        PageGridViewModel value = getPageData().getValue();
        if (value == null || (models = value.getModels()) == null) {
            return null;
        }
        Iterator<BaseViewModel> it = models.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            BaseViewModel next = it.next();
            if ((next instanceof TextRowItemModel) && Intrinsics.areEqual(((TextRowItemModel) next).getText(), this.upcomingHeaderString)) {
                break;
            }
            i++;
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PageGridViewModel getDisplayedPageModel(PageGridViewModel pageGridViewModel, int currentIndex) {
        if (this.isCarModePlayQueue) {
            return pageGridViewModel;
        }
        if (pageGridViewModel == null) {
            return null;
        }
        List<BaseViewModel> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) pageGridViewModel.getModels());
        addTextRowItemModels(mutableList, currentIndex);
        removeCurrentPlayingVisualRowItem(mutableList, currentIndex);
        Unit unit = Unit.INSTANCE;
        return pageGridViewModel.copy((1839 & 1) != 0 ? pageGridViewModel.viewId : null, (1839 & 2) != 0 ? pageGridViewModel.nestedModelViewId : null, (1839 & 4) != 0 ? pageGridViewModel.title : null, (1839 & 8) != 0 ? pageGridViewModel.subTitle : null, (1839 & 16) != 0 ? pageGridViewModel.getModels() : mutableList, (1839 & 32) != 0 ? pageGridViewModel.getTileType() : 0, (1839 & 64) != 0 ? pageGridViewModel.pageIndex : 0, (1839 & 128) != 0 ? pageGridViewModel.nextPageToken : null, (1839 & 256) != 0 ? pageGridViewModel.filters : null, (1839 & 512) != 0 ? pageGridViewModel.context : null, (1839 & 1024) != 0 ? pageGridViewModel.header : null);
    }

    private final void addTextRowItemModels(List<BaseViewModel> list, int i) {
        if (i >= 0 && i < list.size()) {
            if (i < CollectionsKt__CollectionsKt.getIndices(list).getLast()) {
                list.add(i + 1, new TextRowItemModel(null, this.upcomingHeaderString, null, null, null, false, 61, null));
            }
            if (i > 0) {
                list.add(0, new TextRowItemModel(null, this.previousHeaderString, null, null, null, false, 61, null));
            }
        }
    }

    private final void removeCurrentPlayingVisualRowItem(List<BaseViewModel> list, int i) {
        List<BaseViewModel> models;
        if (i >= 0 && i < list.size()) {
            PageGridViewModel pageGridViewModel = this.dataPageModel;
            BaseViewModel baseViewModel = null;
            if (pageGridViewModel != null && (models = pageGridViewModel.getModels()) != null) {
                baseViewModel = models.get(i);
            }
            if (baseViewModel instanceof VisualRowItemModel) {
                int iFindPositionForVisualRowItemModel = findPositionForVisualRowItemModel(list, (VisualRowItemModel) baseViewModel);
                if (iFindPositionForVisualRowItemModel >= 0 && iFindPositionForVisualRowItemModel < list.size()) {
                    list.remove(iFindPositionForVisualRowItemModel);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getCurrentPlayingTrackIndex() {
        return getPlaybackController().getCurrentIndex();
    }

    private final int findPositionInDataList(int displayIndex) {
        List<BaseViewModel> models;
        BaseViewModel baseViewModel;
        List<BaseViewModel> models2;
        PageGridViewModel value = getPageData().getValue();
        if (value == null || (models = value.getModels()) == null || (baseViewModel = models.get(displayIndex)) == null) {
            return -1;
        }
        if (baseViewModel instanceof VisualRowItemModel) {
            PageGridViewModel pageGridViewModel = this.dataPageModel;
            if (pageGridViewModel == null || (models2 = pageGridViewModel.getModels()) == null) {
                return -1;
            }
            return findPositionForVisualRowItemModel(models2, (VisualRowItemModel) baseViewModel);
        }
        if (!(baseViewModel instanceof TextRowItemModel)) {
            return -1;
        }
        String text = ((TextRowItemModel) baseViewModel).getText();
        return Intrinsics.areEqual(text, this.upcomingHeaderString) ? this.currentTrackIndex : Intrinsics.areEqual(text, this.previousHeaderString) ? 0 : -1;
    }

    private final int findPositionForVisualRowItemModel(List<? extends BaseViewModel> searchList, VisualRowItemModel targetModel) {
        int i = 0;
        for (BaseViewModel baseViewModel : searchList) {
            if ((baseViewModel instanceof VisualRowItemModel) && Intrinsics.areEqual(((VisualRowItemModel) baseViewModel).getRowNumber(), targetModel.getRowNumber())) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
