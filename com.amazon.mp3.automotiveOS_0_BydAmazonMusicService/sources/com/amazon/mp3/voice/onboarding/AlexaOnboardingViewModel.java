package com.amazon.mp3.voice.onboarding;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.amazon.mp3.util.Log;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.aom.onboarding.OnboardingAction;
import com.amazon.music.platform.aom.onboarding.OnboardingEvent;
import com.amazon.music.platform.aom.onboarding.OnboardingWorkflowManager;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaOnboardingViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001b\u001a\u00020\u0005J\u000e\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020\u0005H\u0014J\u000e\u0010 \u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010!\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eJ\u0018\u0010&\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020$H\u0002J\u0010\u0010(\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0007R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0007¨\u0006*"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "closeUI", "Landroidx/lifecycle/LiveData;", "", "getCloseUI", "()Landroidx/lifecycle/LiveData;", "displayExplainPermissionPage", "getDisplayExplainPermissionPage", "displaySuccessPage", "getDisplaySuccessPage", "displayTOUPage", "getDisplayTOUPage", "enableWakeWord", "getEnableWakeWord", "mutableCloseUI", "Landroidx/lifecycle/MutableLiveData;", "mutableDisplayExplainPermissionPage", "mutableDisplaySuccessPage", "mutableDisplayTOUPage", "mutableEnableWakeWord", "mutableRequestMicPermission", "onboardingWorkflowManager", "Lcom/amazon/music/platform/aom/onboarding/OnboardingWorkflowManager;", "requestMicPermission", "getRequestMicPermission", "displayOnboardingView", "onBackButtonPressed", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "onCleared", "onCloseButtonClicked", "onNegativeButtonClicked", "onPermissionResult", "isGranted", "", "onPositiveButtonClicked", "sendUIClickEvent", "isPositiveButtonClicked", "sendUIPageViewEvent", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaOnboardingViewModel extends ViewModel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = AlexaOnboardingViewModel.class.getSimpleName();
    private final MutableLiveData<Unit> mutableCloseUI;
    private final MutableLiveData<Unit> mutableDisplayExplainPermissionPage;
    private final MutableLiveData<Unit> mutableDisplaySuccessPage;
    private final MutableLiveData<Unit> mutableDisplayTOUPage;
    private final MutableLiveData<Unit> mutableEnableWakeWord;
    private final MutableLiveData<Unit> mutableRequestMicPermission;
    private final OnboardingWorkflowManager onboardingWorkflowManager;

    /* JADX INFO: compiled from: AlexaOnboardingViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PageType.values().length];
            iArr[PageType.ALEXA_TOU.ordinal()] = 1;
            iArr[PageType.ALEXA_EXPLAIN_PERMISSION.ordinal()] = 2;
            iArr[PageType.ALEXA_ONBOARDING_SUCCESS.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: compiled from: AlexaOnboardingViewModel.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingViewModel$Companion;", "", "()V", "METRIC_VALUE_CLIENT", "", "TAG", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return AlexaOnboardingViewModel.TAG;
        }
    }

    public AlexaOnboardingViewModel() {
        OnboardingWorkflowManager onboardingWorkflowManagerCreateOnboardingWorkflowManager = new OnboardingWorkflowManagerFactory().createOnboardingWorkflowManager();
        this.onboardingWorkflowManager = onboardingWorkflowManagerCreateOnboardingWorkflowManager;
        this.mutableDisplayTOUPage = new MutableLiveData<>();
        this.mutableDisplayExplainPermissionPage = new MutableLiveData<>();
        this.mutableDisplaySuccessPage = new MutableLiveData<>();
        this.mutableRequestMicPermission = new MutableLiveData<>();
        this.mutableEnableWakeWord = new MutableLiveData<>();
        this.mutableCloseUI = new MutableLiveData<>();
        onboardingWorkflowManagerCreateOnboardingWorkflowManager.registerActionObserver(new Function1<OnboardingAction, Unit>() { // from class: com.amazon.mp3.voice.onboarding.AlexaOnboardingViewModel.1

            /* JADX INFO: renamed from: com.amazon.mp3.voice.onboarding.AlexaOnboardingViewModel$1$WhenMappings */
            /* JADX INFO: compiled from: AlexaOnboardingViewModel.kt */
            @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[OnboardingAction.values().length];
                    iArr[OnboardingAction.SHOW_TOU_PAGE.ordinal()] = 1;
                    iArr[OnboardingAction.SHOW_EXPLAIN_PERMISSION_PAGE.ordinal()] = 2;
                    iArr[OnboardingAction.SHOW_SUCCESS_PAGE.ordinal()] = 3;
                    iArr[OnboardingAction.CLOSE_UI.ordinal()] = 4;
                    iArr[OnboardingAction.REQUEST_MIC_PERMISSION.ordinal()] = 5;
                    iArr[OnboardingAction.ENABLE_WAKE_WORD.ordinal()] = 6;
                    iArr[OnboardingAction.NONE.ordinal()] = 7;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OnboardingAction onboardingAction) {
                invoke2(onboardingAction);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OnboardingAction it) {
                Intrinsics.checkNotNullParameter(it, "it");
                switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                    case 1:
                        AlexaOnboardingViewModel.this.mutableDisplayTOUPage.postValue(Unit.INSTANCE);
                        AlexaOnboardingViewModel.this.sendUIPageViewEvent(PageType.ALEXA_TOU);
                        break;
                    case 2:
                        AlexaOnboardingViewModel.this.mutableDisplayExplainPermissionPage.postValue(Unit.INSTANCE);
                        AlexaOnboardingViewModel.this.sendUIPageViewEvent(PageType.ALEXA_EXPLAIN_PERMISSION);
                        break;
                    case 3:
                        AlexaOnboardingViewModel.this.mutableDisplaySuccessPage.postValue(Unit.INSTANCE);
                        AlexaOnboardingViewModel.this.sendUIPageViewEvent(PageType.ALEXA_ONBOARDING_SUCCESS);
                        break;
                    case 4:
                        AlexaOnboardingViewModel.this.mutableCloseUI.postValue(Unit.INSTANCE);
                        break;
                    case 5:
                        AlexaOnboardingViewModel.this.mutableRequestMicPermission.postValue(Unit.INSTANCE);
                        break;
                    case 6:
                        AlexaOnboardingViewModel.this.mutableEnableWakeWord.postValue(Unit.INSTANCE);
                        break;
                    case 7:
                        Log.info(AlexaOnboardingViewModel.INSTANCE.getTAG(), "Observed action NONE from OnboardingWorkflowManager");
                        break;
                }
            }
        });
    }

    public final LiveData<Unit> getDisplayTOUPage() {
        return this.mutableDisplayTOUPage;
    }

    public final LiveData<Unit> getDisplayExplainPermissionPage() {
        return this.mutableDisplayExplainPermissionPage;
    }

    public final LiveData<Unit> getDisplaySuccessPage() {
        return this.mutableDisplaySuccessPage;
    }

    public final LiveData<Unit> getRequestMicPermission() {
        return this.mutableRequestMicPermission;
    }

    public final LiveData<Unit> getEnableWakeWord() {
        return this.mutableEnableWakeWord;
    }

    public final LiveData<Unit> getCloseUI() {
        return this.mutableCloseUI;
    }

    public final void displayOnboardingView() {
        this.onboardingWorkflowManager.displayOnboardingView();
    }

    public final void onPositiveButtonClicked(PageType pageType) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        int i = WhenMappings.$EnumSwitchMapping$0[pageType.ordinal()];
        if (i == 1) {
            this.onboardingWorkflowManager.onUserEvent(OnboardingEvent.TOU_ACCEPTED);
            sendUIClickEvent(PageType.ALEXA_TOU, true);
        } else if (i == 2) {
            this.onboardingWorkflowManager.onUserEvent(OnboardingEvent.PERMISSION_NEED_EXPLAINED);
            sendUIClickEvent(PageType.ALEXA_EXPLAIN_PERMISSION, true);
        } else {
            Log.error(TAG, Intrinsics.stringPlus("Received onPositiveButtonClicked from invalid pageType ", pageType));
        }
    }

    public final void onNegativeButtonClicked(PageType pageType) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        int i = WhenMappings.$EnumSwitchMapping$0[pageType.ordinal()];
        if (i == 1) {
            this.onboardingWorkflowManager.onUserEvent(OnboardingEvent.TOU_REJECTED);
            sendUIClickEvent(PageType.ALEXA_TOU, false);
        } else if (i == 2) {
            this.onboardingWorkflowManager.onUserEvent(OnboardingEvent.PERMISSION_NEED_REJECTED);
            sendUIClickEvent(PageType.ALEXA_EXPLAIN_PERMISSION, false);
        } else {
            Log.error(TAG, Intrinsics.stringPlus("Received onNegativeButtonClicked from invalid pageType ", pageType));
        }
    }

    public final void onCloseButtonClicked(PageType pageType) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        if (WhenMappings.$EnumSwitchMapping$0[pageType.ordinal()] == 3) {
            this.onboardingWorkflowManager.onUserEvent(OnboardingEvent.ONBOARDING_COMPLETION_ACKNOWLEDGED);
        } else {
            Log.error(TAG, Intrinsics.stringPlus("Received onCloseButtonClicked from invalid pageType ", pageType));
        }
    }

    public final void onBackButtonPressed(PageType pageType) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        int i = WhenMappings.$EnumSwitchMapping$0[pageType.ordinal()];
        if (i == 1) {
            this.onboardingWorkflowManager.onUserEvent(OnboardingEvent.TOU_REJECTED);
            sendUIClickEvent(PageType.ALEXA_TOU, false);
        } else if (i == 2) {
            this.onboardingWorkflowManager.onUserEvent(OnboardingEvent.PERMISSION_NEED_REJECTED);
            sendUIClickEvent(PageType.ALEXA_EXPLAIN_PERMISSION, false);
        } else {
            if (i != 3) {
                return;
            }
            this.onboardingWorkflowManager.onUserEvent(OnboardingEvent.ONBOARDING_COMPLETION_ACKNOWLEDGED);
        }
    }

    public final void onPermissionResult(boolean isGranted) {
        this.onboardingWorkflowManager.onPermissionResult(isGranted);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.onboardingWorkflowManager.detach();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendUIPageViewEvent(PageType pageType) {
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(pageType.getMetricsValue()).build());
    }

    private final void sendUIClickEvent(PageType pageType, boolean isPositiveButtonClicked) {
        ActionType actionType = isPositiveButtonClicked ? ActionType.SELECT_OK : ActionType.SELECT_NO_THANKS;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
        String metricsValue = pageType.getMetricsValue();
        Intrinsics.checkNotNullExpressionValue(metricsValue, "pageType.metricValue");
        linkedHashMap.put("contentName", metricsValue);
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withPageType(pageType).withContentInfo(CollectionsKt__CollectionsJVMKt.listOf(linkedHashMap)).build());
    }
}
