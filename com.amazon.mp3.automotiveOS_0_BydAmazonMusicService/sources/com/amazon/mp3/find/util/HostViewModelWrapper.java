package com.amazon.mp3.find.util;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.amazon.mp3.configuration.PreferenceUpdateProvider;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.find.viewmodels.BaseViewModel;
import com.amazon.music.find.viewmodels.BaseViewModelLifecycle;
import com.amazon.music.find.viewmodels.SearchHostViewData;
import com.amazon.music.find.viewmodels.ViewModelClearedListener;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: HostViewModelWrapper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 $*\u000e\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0004:\u0001$B\u0017\u0012\u0006\u0010!\u001a\u00020 \u0012\u0006\u0010\u001f\u001a\u00028\u0000¢\u0006\u0004\b\"\u0010#J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000bH\u0016R\u0014\u0010\u000e\u001a\u00028\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001f\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006%"}, d2 = {"Lcom/amazon/mp3/find/util/HostViewModelWrapper;", "Lcom/amazon/music/find/viewmodels/BaseViewModel;", "Lcom/amazon/music/find/viewmodels/SearchHostViewData;", "T", "Lcom/amazon/music/find/viewmodels/BaseViewModelLifecycle;", "", "onResume", "onRefresh", "onCleared", "Landroidx/lifecycle/MutableLiveData;", "getPageData", "Lcom/amazon/music/find/service/ExecutionContext;", "executionContext", "completeOperation", "mViewModel", "Lcom/amazon/music/find/viewmodels/BaseViewModel;", "Lrx/subjects/PublishSubject;", "Ljava/lang/Void;", "mOnClearedSubject", "Lrx/subjects/PublishSubject;", "Lcom/amazon/music/account/AccountStateChangeListener;", "mAccountStateChangeListener", "Lcom/amazon/music/account/AccountStateChangeListener;", "Lcom/amazon/mp3/configuration/PreferenceUpdateProvider$PreferenceUpdateReceiver;", "mPreferenceUpdateReceiver", "Lcom/amazon/mp3/configuration/PreferenceUpdateProvider$PreferenceUpdateReceiver;", "Lcom/amazon/music/find/viewmodels/ViewModelClearedListener;", "mViewModelClearedListener", "Lcom/amazon/music/find/viewmodels/ViewModelClearedListener;", "getViewModel", "()Lcom/amazon/music/find/viewmodels/BaseViewModel;", "viewModel", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/find/viewmodels/BaseViewModel;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class HostViewModelWrapper<T extends BaseViewModel<SearchHostViewData>> implements BaseViewModelLifecycle<SearchHostViewData> {
    private static final String TAG = "SPL";
    private static final Logger logger = LoggerFactory.getLogger(HostViewModelWrapper.class.getName());
    private final AccountStateChangeListener mAccountStateChangeListener;
    private final PublishSubject<Void> mOnClearedSubject;
    private final PreferenceUpdateProvider.PreferenceUpdateReceiver mPreferenceUpdateReceiver;
    private final T mViewModel;
    private final ViewModelClearedListener mViewModelClearedListener;

    public HostViewModelWrapper(Context context, T viewModel) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        this.mViewModel = viewModel;
        PublishSubject<Void> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create<Void>()");
        this.mOnClearedSubject = publishSubjectCreate;
        AccountStateChangeListener accountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.find.util.HostViewModelWrapper$$ExternalSyntheticLambda0
            @Override // com.amazon.music.account.AccountStateChangeListener
            public final void onAccountStateChange(Set set) {
                HostViewModelWrapper.m998_init_$lambda0(this.f$0, set);
            }
        };
        this.mAccountStateChangeListener = accountStateChangeListener;
        PreferenceUpdateProvider.PreferenceUpdateReceiver preferenceUpdateReceiver = new PreferenceUpdateProvider.PreferenceUpdateReceiver() { // from class: com.amazon.mp3.find.util.HostViewModelWrapper$$ExternalSyntheticLambda1
            @Override // com.amazon.mp3.configuration.PreferenceUpdateProvider.PreferenceUpdateReceiver
            public final void onReceive() {
                HostViewModelWrapper.m999_init_$lambda1(this.f$0);
            }
        };
        this.mPreferenceUpdateReceiver = preferenceUpdateReceiver;
        ViewModelClearedListener viewModelClearedListener = new ViewModelClearedListener(this) { // from class: com.amazon.mp3.find.util.HostViewModelWrapper.3
            final /* synthetic */ HostViewModelWrapper<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // com.amazon.music.find.viewmodels.ViewModelClearedListener
            public void notifyViewModelCleared() {
                HostViewModelWrapper.logger.debug(Intrinsics.stringPlus(HostViewModelWrapper.TAG, "::notifyViewModelCleared"));
                this.this$0.onCleared();
            }
        };
        this.mViewModelClearedListener = viewModelClearedListener;
        AccountManagerSingleton.get().registerListener(accountStateChangeListener);
        PreferenceUpdateProvider.registerReceiver(preferenceUpdateReceiver);
        viewModel.setViewModelClearedListener(viewModelClearedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m998_init_$lambda0(HostViewModelWrapper this$0, Set set) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug(TAG + "::mAccountStateChangeListener. set: " + set);
        if (set.contains(AccountStateField.USER_BENEFITS) || set.contains(AccountStateField.USER_TERMS_OF_USE_MARKETPLACE) || set.contains(AccountStateField.USER_HAS_ACCEPTED_TERMS_OF_USE) || set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE)) {
            this$0.onRefresh();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m999_init_$lambda1(HostViewModelWrapper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug(Intrinsics.stringPlus(TAG, "::mPreferenceUpdateReceiver"));
        this$0.onRefresh();
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void onResume() {
        logger.debug(Intrinsics.stringPlus(TAG, "::onResume"));
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
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public LiveData<SearchHostViewData> getPageData() {
        return this.mViewModel.getPageData();
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void completeOperation(ExecutionContext executionContext) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        this.mViewModel.completeOperation(executionContext);
    }

    public final T getViewModel() {
        return this.mViewModel;
    }
}
