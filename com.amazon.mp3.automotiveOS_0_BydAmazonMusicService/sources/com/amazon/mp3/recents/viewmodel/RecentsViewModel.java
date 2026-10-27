package com.amazon.mp3.recents.viewmodel;

import android.app.Application;
import android.database.ContentObserver;
import android.os.Handler;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.recents.usecase.RecentsModelUseCase;
import com.amazon.mp3.recents.viewmodel.RecentsViewModel;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.models.PageGridViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: RecentsViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0011\u001a\u00020\u0012J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\b\u0010\u0014\u001a\u00020\u0012H\u0014R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/recents/viewmodel/RecentsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "useCase", "Lcom/amazon/mp3/recents/usecase/RecentsModelUseCase;", "(Landroid/app/Application;Lcom/amazon/mp3/recents/usecase/RecentsModelUseCase;)V", "TAG", "", "kotlin.jvm.PlatformType", "changeListener", "Landroid/database/ContentObserver;", "pageGridModel", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "subscription", "Lrx/Subscription;", "fetchData", "", "getPageData", "onCleared", "ChangeListener", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentsViewModel extends AndroidViewModel {
    private final String TAG;
    private final ContentObserver changeListener;
    private final MutableLiveData<PageGridViewModel> pageGridModel;
    private Subscription subscription;
    private final RecentsModelUseCase useCase;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecentsViewModel(Application application, RecentsModelUseCase useCase) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(useCase, "useCase");
        this.useCase = useCase;
        this.TAG = RecentsViewModel.class.getSimpleName();
        this.pageGridModel = new MutableLiveData<>();
        Handler backgroundHandler = AmazonApplication.getBackgroundHandler();
        Intrinsics.checkNotNullExpressionValue(backgroundHandler, "getBackgroundHandler()");
        ChangeListener changeListener = new ChangeListener(this, backgroundHandler);
        this.changeListener = changeListener;
        getApplication().getContentResolver().registerContentObserver(CirrusMediaSource.NOTIFICATION_URI, false, changeListener);
    }

    /* JADX INFO: compiled from: RecentsViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/recents/viewmodel/RecentsViewModel$ChangeListener;", "Landroid/database/ContentObserver;", "", "selfChange", "", "onChange", "Landroid/os/Handler;", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "<init>", "(Lcom/amazon/mp3/recents/viewmodel/RecentsViewModel;Landroid/os/Handler;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public final class ChangeListener extends ContentObserver {
        private final Handler handler;
        final /* synthetic */ RecentsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChangeListener(RecentsViewModel this$0, Handler handler) {
            super(handler);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(handler, "handler");
            this.this$0 = this$0;
            this.handler = handler;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Handler handler = this.handler;
            final RecentsViewModel recentsViewModel = this.this$0;
            handler.postDelayed(new Runnable() { // from class: com.amazon.mp3.recents.viewmodel.RecentsViewModel$ChangeListener$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RecentsViewModel.ChangeListener.m1617onChange$lambda0(recentsViewModel);
                }
            }, 1000L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onChange$lambda-0, reason: not valid java name */
        public static final void m1617onChange$lambda0(RecentsViewModel this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.fetchData();
        }
    }

    public final void fetchData() {
        Subscription subscription = this.subscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.subscription = this.useCase.getInitPageData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.recents.viewmodel.RecentsViewModel$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RecentsViewModel.m1615fetchData$lambda0(this.f$0, (PageGridViewModel) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.recents.viewmodel.RecentsViewModel$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RecentsViewModel.m1616fetchData$lambda1(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchData$lambda-0, reason: not valid java name */
    public static final void m1615fetchData$lambda0(RecentsViewModel this$0, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.pageGridModel.setValue(pageGridViewModel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchData$lambda-1, reason: not valid java name */
    public static final void m1616fetchData$lambda1(RecentsViewModel this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.pageGridModel.setValue(null);
        Log.error(this$0.TAG, Intrinsics.stringPlus("Error fetching Recents data: ", th));
    }

    public final MutableLiveData<PageGridViewModel> getPageData() {
        return this.pageGridModel;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        Subscription subscription = this.subscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.subscription = null;
        getApplication().getContentResolver().unregisterContentObserver(this.changeListener);
    }
}
