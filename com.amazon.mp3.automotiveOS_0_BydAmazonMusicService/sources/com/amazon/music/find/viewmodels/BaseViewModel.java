package com.amazon.music.find.viewmodels;

import android.os.SystemClock;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.amazon.music.find.livedata.SingleLiveEvent;
import com.amazon.music.find.model.search.ContentOwnershipItem;
import com.amazon.music.find.service.ExecutionContext;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: BaseViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 D*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001DB\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0004J\u0006\u0010$\u001a\u00020!J\u0010\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020'H\u0016J!\u0010(\u001a\u0004\u0018\u00018\u00002\u0006\u0010&\u001a\u00020'2\b\u0010)\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010*JB\u0010+\u001a\u00020!2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00100-2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020/0-2\u001c\u00100\u001a\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u0002020-\u0012\u0004\u0012\u00020!01j\u0002`3H&J\b\u00104\u001a\u00020\u0010H\u0004J\u000e\u00105\u001a\b\u0012\u0004\u0012\u00028\u000006H\u0016J\u0010\u00107\u001a\u00020!2\u0006\u0010&\u001a\u00020'H\u0014J\b\u00108\u001a\u00020!H\u0014J\b\u00109\u001a\u00020!H\u0014J\u0015\u0010:\u001a\u0004\u0018\u00010\u00102\u0006\u0010;\u001a\u00020\n¢\u0006\u0002\u0010<J\u0010\u0010=\u001a\u00020!2\b\u0010>\u001a\u0004\u0018\u00010\u001bJ\u001d\u0010?\u001a\u00020!2\u0006\u0010&\u001a\u00020'2\b\u0010)\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010@J7\u0010?\u001a\u00020!2\u0006\u0010&\u001a\u00020'2\b\u0010)\u001a\u0004\u0018\u00018\u00002\u0018\u0010A\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000B\u0012\u0004\u0012\u00020\n01¢\u0006\u0002\u0010CR\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0084\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006E"}, d2 = {"Lcom/amazon/music/find/viewmodels/BaseViewModel;", "T", "Landroidx/lifecycle/ViewModel;", "Lcom/amazon/music/find/viewmodels/BaseViewModelLifecycle;", "()V", "mCompositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getMCompositeDisposable", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "mIsFetching", "", "getMIsFetching", "()Z", "setMIsFetching", "(Z)V", "mLastFetchRequestedAt", "", "getMLastFetchRequestedAt", "()Ljava/lang/Long;", "setMLastFetchRequestedAt", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "mPageModel", "Lcom/amazon/music/find/livedata/SingleLiveEvent;", "getMPageModel", "()Lcom/amazon/music/find/livedata/SingleLiveEvent;", "mViewModelClearedListener", "Lcom/amazon/music/find/viewmodels/ViewModelClearedListener;", "getMViewModelClearedListener", "()Lcom/amazon/music/find/viewmodels/ViewModelClearedListener;", "setMViewModelClearedListener", "(Lcom/amazon/music/find/viewmodels/ViewModelClearedListener;)V", "addDisposable", "", "disposable", "Lio/reactivex/rxjava3/disposables/Disposable;", "clearViewModelClearedListener", "completeOperation", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "copyDataWithContext", "pageData", "(Lcom/amazon/music/find/service/ExecutionContext;Ljava/lang/Object;)Ljava/lang/Object;", "fetchRequestState", "trackIds", "", "trackAsins", "", "callback", "Lkotlin/Function1;", "Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "Lcom/amazon/music/find/model/search/ContentOwnershipCallback;", "getCurrentTimestamp", "getPageData", "Landroidx/lifecycle/MutableLiveData;", "handleOperationCompleted", "notifyViewModelCleared", "onCleared", "setIsFetching", "isFetching", "(Z)Ljava/lang/Long;", "setViewModelClearedListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "updatePageData", "(Lcom/amazon/music/find/service/ExecutionContext;Ljava/lang/Object;)V", "pageDataShouldBeUpdated", "Lcom/amazon/music/find/viewmodels/PageDataShouldBeUpdatedEvent;", "(Lcom/amazon/music/find/service/ExecutionContext;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseViewModel<T> extends ViewModel implements BaseViewModelLifecycle<T> {
    private static final String TAG = "SPL";
    private static final Logger logger = LoggerFactory.getLogger(BaseViewModel.class.getName());
    private boolean mIsFetching;
    private volatile Long mLastFetchRequestedAt;
    private ViewModelClearedListener mViewModelClearedListener;
    private final SingleLiveEvent<T> mPageModel = new SingleLiveEvent<>();
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public abstract T copyDataWithContext(ExecutionContext executionContext, T pageData);

    public abstract void fetchRequestState(List<Long> trackIds, List<String> trackAsins, Function1<? super List<ContentOwnershipItem>, Unit> callback);

    protected void handleOperationCompleted(ExecutionContext executionContext) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
    }

    protected final SingleLiveEvent<T> getMPageModel() {
        return this.mPageModel;
    }

    protected final CompositeDisposable getMCompositeDisposable() {
        return this.mCompositeDisposable;
    }

    protected final ViewModelClearedListener getMViewModelClearedListener() {
        return this.mViewModelClearedListener;
    }

    protected final void setMViewModelClearedListener(ViewModelClearedListener viewModelClearedListener) {
        this.mViewModelClearedListener = viewModelClearedListener;
    }

    protected final boolean getMIsFetching() {
        return this.mIsFetching;
    }

    protected final void setMIsFetching(boolean z) {
        this.mIsFetching = z;
    }

    protected final Long getMLastFetchRequestedAt() {
        return this.mLastFetchRequestedAt;
    }

    protected final void setMLastFetchRequestedAt(Long l) {
        this.mLastFetchRequestedAt = l;
    }

    public final synchronized Long setIsFetching(boolean isFetching) {
        this.mIsFetching = isFetching;
        if (!isFetching) {
            return null;
        }
        long currentTimestamp = getCurrentTimestamp();
        this.mLastFetchRequestedAt = Long.valueOf(currentTimestamp);
        return Long.valueOf(currentTimestamp);
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public MutableLiveData<T> getPageData() {
        return this.mPageModel;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        logger.debug(Intrinsics.stringPlus(TAG, "::onCleared"));
        notifyViewModelCleared();
        clearViewModelClearedListener();
        this.mCompositeDisposable.clear();
        super.onCleared();
    }

    public final void setViewModelClearedListener(ViewModelClearedListener listener) {
        this.mViewModelClearedListener = listener;
    }

    public final void clearViewModelClearedListener() {
        this.mViewModelClearedListener = null;
    }

    public final void updatePageData(ExecutionContext executionContext, T pageData) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        updatePageData(executionContext, pageData, new Function1<PageDataShouldBeUpdatedEvent<T>, Boolean>() { // from class: com.amazon.music.find.viewmodels.BaseViewModel.updatePageData.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(PageDataShouldBeUpdatedEvent<T> noName_0) {
                Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                return Boolean.TRUE;
            }
        });
    }

    public final void updatePageData(ExecutionContext executionContext, T pageData, Function1<? super PageDataShouldBeUpdatedEvent<T>, Boolean> pageDataShouldBeUpdated) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(pageDataShouldBeUpdated, "pageDataShouldBeUpdated");
        if (pageDataShouldBeUpdated.invoke(new PageDataShouldBeUpdatedEvent(pageData, this.mIsFetching, this.mLastFetchRequestedAt)).booleanValue()) {
            this.mPageModel.postValue(copyDataWithContext(executionContext, pageData));
        }
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void completeOperation(ExecutionContext executionContext) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        handleOperationCompleted(executionContext);
    }

    protected void notifyViewModelCleared() {
        ViewModelClearedListener viewModelClearedListener = this.mViewModelClearedListener;
        if (viewModelClearedListener == null) {
            return;
        }
        viewModelClearedListener.notifyViewModelCleared();
    }

    protected final void addDisposable(Disposable disposable) {
        if (disposable == null) {
            return;
        }
        getMCompositeDisposable().add(disposable);
    }

    protected final long getCurrentTimestamp() {
        return SystemClock.elapsedRealtime();
    }
}
