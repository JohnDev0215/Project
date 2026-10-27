package com.amazon.mp3.auto.carmode.fragment.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.auto.carmode.CarModeConfigs;
import com.amazon.mp3.auto.carmode.CarModeDataRepository;
import com.amazon.mp3.playback.state.LibraryEqualizerStateManager;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.util.Log;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.views.library.models.PageGridViewModel;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import rx.Observer;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: CarModeDetailViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\u0018\u0000 #2\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0018\u001a\u00020\u0019J(\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0018\b\u0002\u0010\u001d\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u001eJ\u0006\u0010 \u001a\u00020\u0019J\b\u0010!\u001a\u00020\u0019H\u0002J\u0006\u0010\"\u001a\u00020\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R2\u0010\u0012\u001a&\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00140\u0014 \u0015*\u0012\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00140\u0014\u0018\u00010\u00130\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/viewmodel/CarModeDetailViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "equalizerStateManager", "Lcom/amazon/mp3/playback/state/LibraryEqualizerStateManager;", "gridPageModel", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "getGridPageModel", "()Landroidx/lifecycle/MutableLiveData;", "initPageLoad", "", "getInitPageLoad", "()Z", "setInitPageLoad", "(Z)V", "onClearedSubject", "Lrx/subjects/PublishSubject;", "Ljava/lang/Void;", "kotlin.jvm.PlatformType", "pageStateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "clear", "", "fetchCarModeDetailPage", "stringUri", "", "pageErrorCallback", "Lkotlin/Function1;", "", "initEqualizer", "initPageStateManager", "refreshEqualizer", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeDetailViewModel extends AndroidViewModel {
    private static final String TAG = CarModeDetailViewModel.class.getSimpleName();
    private final Application application;
    private LibraryEqualizerStateManager equalizerStateManager;
    private final MutableLiveData<PageGridViewModel> gridPageModel;
    private boolean initPageLoad;
    private final PublishSubject<Void> onClearedSubject;
    private final PageStateManager pageStateManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModeDetailViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.application = application;
        this.gridPageModel = new MutableLiveData<>();
        this.initPageLoad = true;
        this.pageStateManager = new PageStateManager();
        this.onClearedSubject = PublishSubject.create();
    }

    public final MutableLiveData<PageGridViewModel> getGridPageModel() {
        return this.gridPageModel;
    }

    public final boolean getInitPageLoad() {
        return this.initPageLoad;
    }

    public final void setInitPageLoad(boolean z) {
        this.initPageLoad = z;
    }

    private final void initPageStateManager() {
        this.pageStateManager.observePageData(this.gridPageModel);
        PageStateManager pageStateManager = this.pageStateManager;
        PublishSubject<Void> onClearedSubject = this.onClearedSubject;
        Intrinsics.checkNotNullExpressionValue(onClearedSubject, "onClearedSubject");
        pageStateManager.observeOnCleared(onClearedSubject);
    }

    public final void initEqualizer() {
        initPageStateManager();
        Context context = getApplication().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        PageStateManager pageStateManager = this.pageStateManager;
        PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.APP_UI);
        Intrinsics.checkNotNullExpressionValue(playbackController, "getInstance().getPlaybac…ler(ControlSource.APP_UI)");
        this.equalizerStateManager = new LibraryEqualizerStateManager(context, pageStateManager, playbackController);
    }

    public final void fetchCarModeDetailPage(String stringUri, final Function1<? super Throwable, Unit> pageErrorCallback) {
        Intrinsics.checkNotNullParameter(stringUri, "stringUri");
        Context applicationContext = this.application.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "application.applicationContext");
        new CarModeDataRepository(applicationContext).fetchCarModeDetailPage(stringUri).observeOn(Schedulers.io()).timeout(CarModeConfigs.INSTANCE.getCarModeDetailPageTimeout(), TimeUnit.MILLISECONDS).subscribe(new Observer<PageGridViewModel>() { // from class: com.amazon.mp3.auto.carmode.fragment.viewmodel.CarModeDetailViewModel.fetchCarModeDetailPage.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable throwable) {
                String str = CarModeDetailViewModel.TAG;
                Object[] objArr = new Object[1];
                objArr[0] = throwable == null ? null : throwable.getMessage();
                Log.error(str, "Fail to get Car Mode Details page from DMAutomotive service", objArr);
                BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode-detail", LatencyTrackingLeg.LOAD_COMPLETE);
                Function1<Throwable, Unit> function1 = pageErrorCallback;
                if (function1 == null) {
                    return;
                }
                function1.invoke(throwable);
            }

            @Override // rx.Observer
            public void onNext(PageGridViewModel model) {
                Unit unit;
                if (model == null) {
                    unit = null;
                } else {
                    CarModeDetailViewModel carModeDetailViewModel = this;
                    carModeDetailViewModel.setInitPageLoad(true);
                    carModeDetailViewModel.getGridPageModel().postValue(model);
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    Function1<Throwable, Unit> function1 = pageErrorCallback;
                    Log.error(CarModeDetailViewModel.TAG, "Invalid null response from DMAutomotive service");
                    BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode-detail", LatencyTrackingLeg.LOAD_COMPLETE);
                    if (function1 == null) {
                        return;
                    }
                    function1.invoke(new Throwable("Invalid null response from DMAutomotive service"));
                }
            }
        });
    }

    public final void refreshEqualizer() {
        LibraryEqualizerStateManager libraryEqualizerStateManager = this.equalizerStateManager;
        if (libraryEqualizerStateManager == null) {
            return;
        }
        libraryEqualizerStateManager.onRefresh();
    }

    public final void clear() {
        this.gridPageModel.setValue(null);
        this.onClearedSubject.onNext(null);
        LibraryEqualizerStateManager libraryEqualizerStateManager = this.equalizerStateManager;
        if (libraryEqualizerStateManager != null) {
            libraryEqualizerStateManager.onDestroy();
        }
        this.initPageLoad = true;
        Log.debug(TAG, "Clear Car Mode Detail view model data");
    }
}
