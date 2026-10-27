package com.amazon.mp3.auto.carmode.fragment.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.DMAutomotiveConstants$Platform;
import com.amazon.mp3.auto.carmode.CarModeConfigs;
import com.amazon.mp3.auto.carmode.CarModeDataRepository;
import com.amazon.mp3.auto.carmode.CarModeIngressAutoPlayTypes;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.auto.storage.PresetIdentifier;
import com.amazon.mp3.auto.storage.PresetIdentifierHelper;
import com.amazon.mp3.catalog.fragment.datasource.repository.BrushV3ApiPageModelDataRepository;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.state.LibraryEqualizerStateManager;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlaybackHandlerUtil;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.podcast.Podcast;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import rx.Observer;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: CarModeBrushViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\f\u0018\u0000 12\u00020\u0001:\u00011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u0019J\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0002J*\u0010!\u001a\u00020\u00192\b\b\u0002\u0010\"\u001a\u00020#2\u0018\b\u0002\u0010$\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010&\u0012\u0004\u0012\u00020\u0019\u0018\u00010%J\u0010\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\tH\u0002J\u0006\u0010)\u001a\u00020\u0019J\b\u0010*\u001a\u00020\u0019H\u0002J\u0006\u0010+\u001a\u00020\rJ\b\u0010,\u001a\u00020\u0019H\u0014J\u0006\u0010-\u001a\u00020\u0019J\u0016\u0010.\u001a\u00020\u00192\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0002J\u0016\u0010/\u001a\u00020\u00192\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0002J\u0014\u00100\u001a\u00020\u00192\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R2\u0010\u0012\u001a&\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00140\u0014 \u0015*\u0012\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00140\u0014\u0018\u00010\u00130\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/viewmodel/CarModeBrushViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "equalizerStateManager", "Lcom/amazon/mp3/playback/state/LibraryEqualizerStateManager;", "gridPageModel", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "getGridPageModel", "()Landroidx/lifecycle/MutableLiveData;", "initPageLoad", "", "getInitPageLoad", "()Z", "setInitPageLoad", "(Z)V", "onClearedSubject", "Lrx/subjects/PublishSubject;", "Ljava/lang/Void;", "kotlin.jvm.PlatformType", "pageStateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "autoPlayIfMusicOrPodcastPaused", "", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "clear", "deleteDuplicatePresets", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "presetList", "fetchCarModePage", "uri", "", "pageErrorCallback", "Lkotlin/Function1;", "", "handleResponse", "model", "initEqualizer", "initPageStateManager", "isPlayQueueActive", "onCleared", "refreshEqualizer", "removeUserDeletedPresets", "setUniquePresetCount", "updatePresetList", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeBrushViewModel extends AndroidViewModel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = CarModeBrushViewModel.class.getSimpleName();
    private final Application application;
    private LibraryEqualizerStateManager equalizerStateManager;
    private final MutableLiveData<PageGridViewModel> gridPageModel;
    private boolean initPageLoad;
    private final PublishSubject<Void> onClearedSubject;
    private final PageStateManager pageStateManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModeBrushViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.application = application;
        this.gridPageModel = new MutableLiveData<>();
        this.initPageLoad = true;
        this.pageStateManager = new PageStateManager();
        this.onClearedSubject = PublishSubject.create();
    }

    /* JADX INFO: compiled from: CarModeBrushViewModel.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/viewmodel/CarModeBrushViewModel$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "invalidateCache", "", "application", "Landroid/app/Application;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void invalidateCache(Application application) {
            Intrinsics.checkNotNullParameter(application, "application");
            Log.debug(CarModeBrushViewModel.TAG, "Sending request to invalidate response cache on API gateway client");
            BrushV3ApiPageModelDataRepository brushV3ApiPageModelDataRepository = new BrushV3ApiPageModelDataRepository(application);
            DMAutomotiveConstants$Platform dMAutomotiveConstants$Platform = DMAutomotiveConstants$Platform.ANDROID;
            brushV3ApiPageModelDataRepository.invalidateCarModePage("carmode", dMAutomotiveConstants$Platform.getValue()).subscribeOn(Schedulers.io()).subscribe();
            new BrushV3ApiPageModelDataRepository(application).invalidateCarModePage("carmode-home", dMAutomotiveConstants$Platform.getValue()).subscribeOn(Schedulers.io()).subscribe();
            new BrushV3ApiPageModelDataRepository(application).invalidateCarModePage("carmode-detail", dMAutomotiveConstants$Platform.getValue()).subscribeOn(Schedulers.io()).subscribe();
        }
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

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void fetchCarModePage$default(CarModeBrushViewModel carModeBrushViewModel, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "carmode";
        }
        if ((i & 2) != 0) {
            function1 = null;
        }
        carModeBrushViewModel.fetchCarModePage(str, function1);
    }

    public final void fetchCarModePage(final String uri, final Function1<? super Throwable, Unit> pageErrorCallback) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Context applicationContext = this.application.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "application.applicationContext");
        new CarModeDataRepository(applicationContext).fetchCarModePage(uri).observeOn(Schedulers.io()).timeout(CarModeConfigs.INSTANCE.getCarModePageTimeout(), TimeUnit.MILLISECONDS).subscribe(new Observer<PageGridViewModel>() { // from class: com.amazon.mp3.auto.carmode.fragment.viewmodel.CarModeBrushViewModel.fetchCarModePage.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable throwable) {
                String str = CarModeBrushViewModel.TAG;
                Object[] objArr = new Object[1];
                objArr[0] = throwable == null ? null : throwable.getMessage();
                Log.error(str, "Fail to get Car Mode page from DMAutomotive service", objArr);
                BrushLatencyReportingUtil.INSTANCE.trackLatency(uri, LatencyTrackingLeg.LOAD_COMPLETE);
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
                    CarModeBrushViewModel carModeBrushViewModel = this;
                    carModeBrushViewModel.setInitPageLoad(true);
                    carModeBrushViewModel.handleResponse(model);
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    String str = uri;
                    Function1<Throwable, Unit> function1 = pageErrorCallback;
                    Log.error(CarModeBrushViewModel.TAG, "Invalid null response from DMAutomotive service");
                    BrushLatencyReportingUtil.INSTANCE.trackLatency(str, LatencyTrackingLeg.LOAD_COMPLETE);
                    if (function1 == null) {
                        return;
                    }
                    function1.invoke(new Throwable("Invalid null response from DMAutomotive service"));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleResponse(PageGridViewModel model) {
        boolean zIsCarModeV2Enabled = AmazonApplication.getCapabilities().isCarModeV2Enabled();
        model.getModels();
        if (!zIsCarModeV2Enabled) {
            List<BaseViewModel> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) model.getModels());
            setUniquePresetCount(mutableList);
            removeUserDeletedPresets(mutableList);
            model.setModels(deleteDuplicatePresets(mutableList));
        }
        getGridPageModel().postValue(model);
    }

    private final void setUniquePresetCount(List<BaseViewModel> presetList) {
        Context applicationContext = getApplication().getApplicationContext();
        if (applicationContext == null) {
            return;
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : presetList) {
            BaseViewModel baseViewModel = (BaseViewModel) obj;
            SimpleHorizontalTileModel simpleHorizontalTileModel = baseViewModel instanceof SimpleHorizontalTileModel ? (SimpleHorizontalTileModel) baseViewModel : null;
            if (hashSet.add(simpleHorizontalTileModel != null ? simpleHorizontalTileModel.getTitle() : null)) {
                arrayList.add(obj);
            }
        }
        CarModePresetsUtility.INSTANCE.setDefaultPresetCount(applicationContext, arrayList.size());
    }

    private final void removeUserDeletedPresets(List<BaseViewModel> presetList) {
        List<PresetIdentifier> deletedPresetIdentifiers;
        Context applicationContext = getApplication().getApplicationContext();
        if (applicationContext == null || (deletedPresetIdentifiers = PresetIdentifierHelper.INSTANCE.getDeletedPresetIdentifiers(applicationContext)) == null) {
            return;
        }
        for (final PresetIdentifier presetIdentifier : deletedPresetIdentifiers) {
            CollectionsKt__MutableCollectionsKt.removeAll((List) presetList, (Function1) new Function1<BaseViewModel, Boolean>() { // from class: com.amazon.mp3.auto.carmode.fragment.viewmodel.CarModeBrushViewModel$removeUserDeletedPresets$1$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(BaseViewModel preset) {
                    Intrinsics.checkNotNullParameter(preset, "preset");
                    SimpleHorizontalTileModel simpleHorizontalTileModel = preset instanceof SimpleHorizontalTileModel ? (SimpleHorizontalTileModel) preset : null;
                    return Boolean.valueOf(Intrinsics.areEqual(simpleHorizontalTileModel != null ? PresetIdentifierHelper.INSTANCE.asPresetIdentifier(simpleHorizontalTileModel) : null, presetIdentifier));
                }
            });
        }
    }

    public final void updatePresetList(List<BaseViewModel> presetList) {
        Intrinsics.checkNotNullParameter(presetList, "presetList");
        PageGridViewModel value = this.gridPageModel.getValue();
        if (value != null) {
            value.setModels(presetList);
        }
        refreshEqualizer();
    }

    public final void refreshEqualizer() {
        LibraryEqualizerStateManager libraryEqualizerStateManager = this.equalizerStateManager;
        if (libraryEqualizerStateManager == null) {
            return;
        }
        libraryEqualizerStateManager.onRefresh();
    }

    public final void autoPlayIfMusicOrPodcastPaused(ActionValidatedPlaybackController playbackController) {
        Intrinsics.checkNotNullParameter(playbackController, "playbackController");
        if (Podcast.getPlayback().isPodcastLastPlayed()) {
            int playbackState = Podcast.getPlayback().getPlaybackState();
            if (playbackState == 2) {
                Log.info(TAG, "Podcast is in paused state during CarMode ingress::");
                Podcast.getPlayback().playPause();
                PlaybackHandlerUtil.INSTANCE.emitAutoPlayFlexEvent(CarModeIngressAutoPlayTypes.ACTIVE_PLAY_QUEUE_PODCAST_PAUSED_AUTOPLAY);
                return;
            } else {
                if (playbackState != 3) {
                    return;
                }
                Log.info(TAG, "Podcast is playing during CarMode ingress::");
                PlaybackHandlerUtil.INSTANCE.emitAutoPlayFlexEvent(CarModeIngressAutoPlayTypes.ACTIVE_PLAY_QUEUE_PODCAST_PLAYING_CONTINUE_PLAYBACK);
                return;
            }
        }
        if (playbackController.getCurrentMediaItem() != null) {
            if (playbackController.getPlayStatus() == PlayStatus.USER_PAUSED) {
                Log.info(TAG, "Music is in paused state during CarMode ingress::");
                playbackController.play();
                PlaybackHandlerUtil.INSTANCE.emitAutoPlayFlexEvent(CarModeIngressAutoPlayTypes.ACTIVE_PLAY_QUEUE_MUSIC_PAUSED_AUTOPLAY);
            } else {
                Log.info(TAG, "Music is playing during CarMode ingress::");
                PlaybackHandlerUtil.INSTANCE.emitAutoPlayFlexEvent(CarModeIngressAutoPlayTypes.ACTIVE_PLAY_QUEUE_MUSIC_PLAYING_CONTINUE_PLAYBACK);
            }
        }
    }

    public final boolean isPlayQueueActive() {
        return Podcast.getPlayback().isPodcastLastPlayed() || PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI).getCurrentMediaItem() != null;
    }

    public final void clear() {
        this.gridPageModel.setValue(null);
        this.onClearedSubject.onNext(null);
        LibraryEqualizerStateManager libraryEqualizerStateManager = this.equalizerStateManager;
        if (libraryEqualizerStateManager != null) {
            libraryEqualizerStateManager.onDestroy();
        }
        this.initPageLoad = true;
        Log.debug(TAG, "Clear Car Mode view model data");
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        CarModeUtility.INSTANCE.resetCarModeData();
    }

    private final List<BaseViewModel> deleteDuplicatePresets(List<BaseViewModel> presetList) {
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : presetList) {
            BaseViewModel baseViewModel = (BaseViewModel) obj;
            SimpleHorizontalTileModel simpleHorizontalTileModel = baseViewModel instanceof SimpleHorizontalTileModel ? (SimpleHorizontalTileModel) baseViewModel : null;
            if (hashSet.add(simpleHorizontalTileModel != null ? simpleHorizontalTileModel.getTitle() : null)) {
                arrayList.add(obj);
            }
        }
        return TypeIntrinsics.asMutableList(arrayList);
    }
}
