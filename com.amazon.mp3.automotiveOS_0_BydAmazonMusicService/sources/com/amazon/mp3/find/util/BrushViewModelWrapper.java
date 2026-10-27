package com.amazon.mp3.find.util;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.amazon.mp3.brush.LibraryStateUseCase;
import com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel;
import com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider;
import com.amazon.mp3.configuration.PreferenceUpdateProvider;
import com.amazon.mp3.library.provider.ContentModelLibraryStateManager;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.playback.state.LibraryEqualizerStateManager;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.find.model.search.ContentOwnershipItem;
import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.find.viewmodels.BasePageGridViewModel;
import com.amazon.music.find.viewmodels.BaseViewModelLifecycle;
import com.amazon.music.find.viewmodels.ViewModelClearedListener;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.providers.ItemOrdinalProvider;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: BrushViewModelWrapper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 C*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003:\u0001CB\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0002\u0010\bJ\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0016JB\u00103\u001a\u0002002\f\u00104\u001a\b\u0012\u0004\u0012\u000206052\f\u00107\u001a\b\u0012\u0004\u0012\u000208052\u001c\u00109\u001a\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020;05\u0012\u0004\u0012\u0002000:j\u0002`<H\u0002J\u000e\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00040>H\u0016J\u0006\u0010?\u001a\u000200J\b\u0010@\u001a\u000200H\u0016J\b\u0010A\u001a\u000200H\u0016J\u0006\u0010B\u001a\u000200R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010'R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010*\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0011\u0010\u0007\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\b-\u0010.¨\u0006D"}, d2 = {"Lcom/amazon/mp3/find/util/BrushViewModelWrapper;", "T", "Lcom/amazon/music/find/viewmodels/BasePageGridViewModel;", "Lcom/amazon/music/find/viewmodels/BaseViewModelLifecycle;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "context", "Landroid/content/Context;", "viewModel", "(Landroid/content/Context;Lcom/amazon/music/find/viewmodels/BasePageGridViewModel;)V", "downloadStateModelProvider", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider;", "getDownloadStateModelProvider", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider;", "itemOrdinalProvider", "Lcom/amazon/music/views/library/providers/ItemOrdinalProvider;", "getItemOrdinalProvider", "()Lcom/amazon/music/views/library/providers/ItemOrdinalProvider;", "libraryStateProvider", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "getLibraryStateProvider", "()Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "mAccountStateChangeListener", "Lcom/amazon/music/account/AccountStateChangeListener;", "mDownloadStateModelProvider", "mEqualizerStateManager", "Lcom/amazon/mp3/playback/state/LibraryEqualizerStateManager;", "mLibraryState", "Lcom/amazon/mp3/library/provider/ContentModelLibraryStateManager;", "mLibraryStateProvider", "mLibraryStateUseCase", "Lcom/amazon/mp3/brush/LibraryStateUseCase;", "mOnClearedSubject", "Lrx/subjects/PublishSubject;", "Ljava/lang/Void;", "mPageStateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "mPreferenceUpdateReceiver", "Lcom/amazon/mp3/configuration/PreferenceUpdateProvider$PreferenceUpdateReceiver;", "mViewModel", "Lcom/amazon/music/find/viewmodels/BasePageGridViewModel;", "mViewModelClearedListener", "Lcom/amazon/music/find/viewmodels/ViewModelClearedListener;", "pageStateManager", "getPageStateManager", "()Lcom/amazon/mp3/playback/state/PageStateManager;", "getViewModel", "()Lcom/amazon/music/find/viewmodels/BasePageGridViewModel;", "completeOperation", "", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "fetchRequestStateHandler", "trackIds", "", "", "trackAsins", "", "callback", "Lkotlin/Function1;", "Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "Lcom/amazon/music/find/model/search/ContentOwnershipCallback;", "getPageData", "Landroidx/lifecycle/MutableLiveData;", "onCleared", "onRefresh", "onResume", "refreshModelStateProviders", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushViewModelWrapper<T extends BasePageGridViewModel> implements BaseViewModelLifecycle<PageGridViewModel> {
    private static final String TAG = "SPL";
    private static final Logger logger = LoggerFactory.getLogger(BrushViewModelWrapper.class.getName());
    private final AccountStateChangeListener mAccountStateChangeListener;
    private final GroupDownloadStateModelProvider mDownloadStateModelProvider;
    private final LibraryEqualizerStateManager mEqualizerStateManager;
    private final ContentModelLibraryStateManager mLibraryState;
    private final LibraryStateProvider mLibraryStateProvider;
    private final LibraryStateUseCase mLibraryStateUseCase;
    private final PublishSubject<Void> mOnClearedSubject;
    private final PageStateManager mPageStateManager;
    private final PreferenceUpdateProvider.PreferenceUpdateReceiver mPreferenceUpdateReceiver;
    private final T mViewModel;
    private final ViewModelClearedListener mViewModelClearedListener;

    public BrushViewModelWrapper(Context context, T viewModel) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        this.mViewModel = viewModel;
        PageStateManager pageStateManager = new PageStateManager();
        this.mPageStateManager = pageStateManager;
        LibraryStateProvider libraryStateProvider = new LibraryStateProvider(context);
        this.mLibraryStateProvider = libraryStateProvider;
        this.mLibraryState = new ContentModelLibraryStateManager(pageStateManager, libraryStateProvider);
        LibraryStateUseCase libraryStateUseCase = new LibraryStateUseCase(pageStateManager);
        this.mLibraryStateUseCase = libraryStateUseCase;
        PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.APP_UI);
        Intrinsics.checkNotNullExpressionValue(playbackController, "getInstance().getPlaybac…ler(ControlSource.APP_UI)");
        this.mEqualizerStateManager = new LibraryEqualizerStateManager(context, pageStateManager, playbackController);
        PublishSubject<Void> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create<Void>()");
        this.mOnClearedSubject = publishSubjectCreate;
        AccountStateChangeListener accountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.find.util.BrushViewModelWrapper$$ExternalSyntheticLambda0
            @Override // com.amazon.music.account.AccountStateChangeListener
            public final void onAccountStateChange(Set set) {
                BrushViewModelWrapper.m995_init_$lambda0(this.f$0, set);
            }
        };
        this.mAccountStateChangeListener = accountStateChangeListener;
        this.mDownloadStateModelProvider = new GroupDownloadStateModelProvider(context);
        PreferenceUpdateProvider.PreferenceUpdateReceiver preferenceUpdateReceiver = new PreferenceUpdateProvider.PreferenceUpdateReceiver() { // from class: com.amazon.mp3.find.util.BrushViewModelWrapper$$ExternalSyntheticLambda1
            @Override // com.amazon.mp3.configuration.PreferenceUpdateProvider.PreferenceUpdateReceiver
            public final void onReceive() {
                BrushViewModelWrapper.m996_init_$lambda1(this.f$0);
            }
        };
        this.mPreferenceUpdateReceiver = preferenceUpdateReceiver;
        ViewModelClearedListener viewModelClearedListener = new ViewModelClearedListener(this) { // from class: com.amazon.mp3.find.util.BrushViewModelWrapper.3
            final /* synthetic */ BrushViewModelWrapper<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // com.amazon.music.find.viewmodels.ViewModelClearedListener
            public void notifyViewModelCleared() {
                BrushViewModelWrapper.logger.debug(Intrinsics.stringPlus(BrushViewModelWrapper.TAG, "::notifyViewModelCleared"));
                this.this$0.onCleared();
            }
        };
        this.mViewModelClearedListener = viewModelClearedListener;
        pageStateManager.observePageData(viewModel.getPageData());
        pageStateManager.observeOnCleared(publishSubjectCreate);
        libraryStateUseCase.observeOnCleared(publishSubjectCreate);
        AccountManagerSingleton.get().registerListener(accountStateChangeListener);
        PreferenceUpdateProvider.registerReceiver(preferenceUpdateReceiver);
        viewModel.setViewModelClearedListener(viewModelClearedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m995_init_$lambda0(BrushViewModelWrapper this$0, Set set) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug(TAG + "::mAccountStateChangeListener. set: " + set);
        if (set.contains(AccountStateField.USER_BENEFITS) || set.contains(AccountStateField.USER_TERMS_OF_USE_MARKETPLACE) || set.contains(AccountStateField.USER_HAS_ACCEPTED_TERMS_OF_USE) || set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE)) {
            this$0.onRefresh();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m996_init_$lambda1(BrushViewModelWrapper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug(Intrinsics.stringPlus(TAG, "::mPreferenceUpdateReceiver"));
        this$0.onRefresh();
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void onResume() {
        logger.debug(Intrinsics.stringPlus(TAG, "::onResume"));
        T t = this.mViewModel;
        BrushViewModel brushViewModel = t instanceof BrushViewModel ? (BrushViewModel) t : null;
        if (brushViewModel != null) {
            brushViewModel.registerDownloadStateListenerForTracks();
        }
        this.mViewModel.onResume();
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void onRefresh() {
        logger.debug(Intrinsics.stringPlus(TAG, "::onRefresh"));
        this.mViewModel.onRefresh();
    }

    public final void onCleared() {
        logger.debug(Intrinsics.stringPlus(TAG, "::onCleared"));
        this.mOnClearedSubject.onNext(null);
        AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
        PreferenceUpdateProvider.deregisterReceiver(this.mPreferenceUpdateReceiver);
        getMLibraryStateProvider().setListener(null);
        this.mEqualizerStateManager.onDestroy();
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public LiveData<PageGridViewModel> getPageData() {
        return this.mViewModel.getPageData();
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void completeOperation(ExecutionContext executionContext) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        this.mViewModel.completeOperation(executionContext);
    }

    /* JADX INFO: renamed from: getPageStateManager, reason: from getter */
    public final PageStateManager getMPageStateManager() {
        return this.mPageStateManager;
    }

    /* JADX INFO: renamed from: getLibraryStateProvider, reason: from getter */
    public final LibraryStateProvider getMLibraryStateProvider() {
        return this.mLibraryStateProvider;
    }

    public final T getViewModel() {
        return this.mViewModel;
    }

    /* JADX INFO: renamed from: getDownloadStateModelProvider, reason: from getter */
    public final GroupDownloadStateModelProvider getMDownloadStateModelProvider() {
        return this.mDownloadStateModelProvider;
    }

    public final ItemOrdinalProvider getItemOrdinalProvider() {
        Object viewModel = getViewModel();
        if (viewModel instanceof ItemOrdinalProvider) {
            return (ItemOrdinalProvider) viewModel;
        }
        return null;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.find.util.BrushViewModelWrapper$refreshModelStateProviders$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BrushViewModelWrapper.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class C03581 extends FunctionReferenceImpl implements Function3<List<? extends Long>, List<? extends String>, Function1<? super List<? extends ContentOwnershipItem>, ? extends Unit>, Unit> {
        C03581(Object obj) {
            super(3, obj, BrushViewModelWrapper.class, "fetchRequestStateHandler", "fetchRequestStateHandler(Ljava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends Long> list, List<? extends String> list2, Function1<? super List<? extends ContentOwnershipItem>, ? extends Unit> function1) {
            invoke2((List<Long>) list, (List<String>) list2, (Function1<? super List<ContentOwnershipItem>, Unit>) function1);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(List<Long> p0, List<String> p1, Function1<? super List<ContentOwnershipItem>, Unit> p2) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            Intrinsics.checkNotNullParameter(p1, "p1");
            Intrinsics.checkNotNullParameter(p2, "p2");
            ((BrushViewModelWrapper) this.receiver).fetchRequestStateHandler(p0, p1, p2);
        }
    }

    public final void refreshModelStateProviders() {
        this.mLibraryState.requestLibraryStateInBatch(new C03581(this));
        this.mEqualizerStateManager.onRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fetchRequestStateHandler(List<Long> trackIds, List<String> trackAsins, final Function1<? super List<ContentOwnershipItem>, Unit> callback) {
        this.mViewModel.fetchRequestState(trackIds, trackAsins, new Function1<List<? extends ContentOwnershipItem>, Unit>() { // from class: com.amazon.mp3.find.util.BrushViewModelWrapper.fetchRequestStateHandler.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends ContentOwnershipItem> list) {
                invoke2((List<ContentOwnershipItem>) list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<ContentOwnershipItem> items) {
                Intrinsics.checkNotNullParameter(items, "items");
                BrushViewModelWrapper.logger.debug(BrushViewModelWrapper.TAG + "::fetchRequestStateHandler numCallbackItems=" + items.size());
                callback.invoke(items);
            }
        });
    }
}
