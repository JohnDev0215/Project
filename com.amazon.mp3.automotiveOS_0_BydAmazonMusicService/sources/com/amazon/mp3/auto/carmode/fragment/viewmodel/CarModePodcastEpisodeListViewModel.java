package com.amazon.mp3.auto.carmode.fragment.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.auto.DMAutomotiveConstants$Platform;
import com.amazon.mp3.auto.carmode.CarModeConfigs;
import com.amazon.mp3.catalog.fragment.datasource.repository.BrushV3ApiPageModelDataRepository;
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

/* JADX INFO: compiled from: CarModePodcastEpisodeListViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J2\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00062\u0018\b\u0002\u0010\u0019\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u001aJ\u0006\u0010\u001c\u001a\u00020\u0015J\b\u0010\u001d\u001a\u00020\u0015H\u0002J\u0006\u0010\u001e\u001a\u00020\u0015R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR2\u0010\u000f\u001a&\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00110\u0011 \u0007*\u0012\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00110\u0011\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/viewmodel/CarModePodcastEpisodeListViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "TAG", "", "kotlin.jvm.PlatformType", "equalizerStateManager", "Lcom/amazon/mp3/playback/state/LibraryEqualizerStateManager;", "gridPageModel", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "getGridPageModel", "()Landroidx/lifecycle/MutableLiveData;", "onClearedSubject", "Lrx/subjects/PublishSubject;", "Ljava/lang/Void;", "pageStateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "clear", "", "fetchCarModePodcastEpisodeListPage", "uri", "podcastShowId", "pageErrorCallback", "Lkotlin/Function1;", "", "initEqualizer", "initPageStateManager", "refreshEqualizer", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModePodcastEpisodeListViewModel extends AndroidViewModel {
    private final String TAG;
    private LibraryEqualizerStateManager equalizerStateManager;
    private final MutableLiveData<PageGridViewModel> gridPageModel;
    private final PublishSubject<Void> onClearedSubject;
    private final PageStateManager pageStateManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModePodcastEpisodeListViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.TAG = CarModePodcastEpisodeListViewModel.class.getSimpleName();
        this.pageStateManager = new PageStateManager();
        this.onClearedSubject = PublishSubject.create();
        this.gridPageModel = new MutableLiveData<>();
    }

    public final MutableLiveData<PageGridViewModel> getGridPageModel() {
        return this.gridPageModel;
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

    private final void initPageStateManager() {
        this.pageStateManager.observePageData(this.gridPageModel);
        PageStateManager pageStateManager = this.pageStateManager;
        PublishSubject<Void> onClearedSubject = this.onClearedSubject;
        Intrinsics.checkNotNullExpressionValue(onClearedSubject, "onClearedSubject");
        pageStateManager.observeOnCleared(onClearedSubject);
    }

    public final void fetchCarModePodcastEpisodeListPage(String uri, String podcastShowId, final Function1<? super Throwable, Unit> pageErrorCallback) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(podcastShowId, "podcastShowId");
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
        new BrushV3ApiPageModelDataRepository(application).fetchCarModePodcastEpisodeListPage(uri, DMAutomotiveConstants$Platform.ANDROID.getValue(), podcastShowId).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).timeout(CarModeConfigs.INSTANCE.getCarModePageTimeout(), TimeUnit.MILLISECONDS).subscribe(new Observer<PageGridViewModel>() { // from class: com.amazon.mp3.auto.carmode.fragment.viewmodel.CarModePodcastEpisodeListViewModel.fetchCarModePodcastEpisodeListPage.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable throwable) {
                String str = CarModePodcastEpisodeListViewModel.this.TAG;
                Object[] objArr = new Object[1];
                objArr[0] = throwable == null ? null : throwable.getMessage();
                Log.error(str, "Fail to get Car Mode page from DMAutomotive service", objArr);
                BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode-podcast-episode-list", LatencyTrackingLeg.LOAD_COMPLETE);
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
                    CarModePodcastEpisodeListViewModel.this.getGridPageModel().postValue(model);
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    CarModePodcastEpisodeListViewModel carModePodcastEpisodeListViewModel = CarModePodcastEpisodeListViewModel.this;
                    Function1<Throwable, Unit> function1 = pageErrorCallback;
                    Log.error(carModePodcastEpisodeListViewModel.TAG, "Invalid null response from DMAutomotive service");
                    BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode-podcast-episode-list", LatencyTrackingLeg.LOAD_COMPLETE);
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
        Log.debug(this.TAG, "Clear Car Mode Podcast Episode List view model data");
    }
}
