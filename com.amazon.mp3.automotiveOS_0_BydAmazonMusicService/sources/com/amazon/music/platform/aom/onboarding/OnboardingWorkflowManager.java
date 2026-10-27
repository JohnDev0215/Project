package com.amazon.music.platform.aom.onboarding;

import androidx.core.app.NotificationCompat;
import com.amazon.music.platform.aom.Logger;
import com.amazon.music.platform.aom.observables.OnboardingActionPropertyObservable;
import com.amazon.music.platform.aom.onboarding.OnboardingWorkflowModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: OnboardingWorkflowManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\u0012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0002H\u0002J\u0006\u0010\b\u001a\u00020\u0007J\u0006\u0010\t\u001a\u00020\u0002J\u000e\u0010\f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\u000e\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0007J\u0006\u0010\u000f\u001a\u00020\u0002J\u000f\u0010\u0013\u001a\u00020\u0010H\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0017\u001a\u00020\u00022\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00020\u0014R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006!"}, d2 = {"Lcom/amazon/music/platform/aom/onboarding/OnboardingWorkflowManager;", "", "", "onProceedFurtherInWorkflow", "exitWorkflow", "onSuccessfulCompletion", "enableWakeWordIfRequired", "", "isOnboardingCompleted", "displayOnboardingView", "Lcom/amazon/music/platform/aom/onboarding/OnboardingEvent;", NotificationCompat.CATEGORY_EVENT, "onUserEvent", "granted", "onPermissionResult", "detach", "Lcom/amazon/music/platform/aom/onboarding/OnboardingWorkflowModel$State;", "getOnboardingState$MusicPlatformAOM_release", "()Lcom/amazon/music/platform/aom/onboarding/OnboardingWorkflowModel$State;", "getOnboardingState", "Lkotlin/Function1;", "Lcom/amazon/music/platform/aom/onboarding/OnboardingAction;", "observer", "registerActionObserver", "Lcom/amazon/music/platform/aom/onboarding/OnboardingWorkflowModel;", "model", "Lcom/amazon/music/platform/aom/onboarding/OnboardingWorkflowModel;", "Lcom/amazon/music/platform/aom/observables/OnboardingActionPropertyObservable;", "onboardingActionObservable", "Lcom/amazon/music/platform/aom/observables/OnboardingActionPropertyObservable;", "<init>", "(Lcom/amazon/music/platform/aom/onboarding/OnboardingWorkflowModel;)V", "Companion", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public final class OnboardingWorkflowManager {
    private static final String TAG;
    private final OnboardingWorkflowModel model;
    private final OnboardingActionPropertyObservable onboardingActionObservable;

    /* JADX INFO: compiled from: OnboardingWorkflowManager.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[OnboardingWorkflowModel.State.values().length];
            iArr[OnboardingWorkflowModel.State.NONE.ordinal()] = 1;
            iArr[OnboardingWorkflowModel.State.PERMISSION.ordinal()] = 2;
            iArr[OnboardingWorkflowModel.State.TOU.ordinal()] = 3;
            iArr[OnboardingWorkflowModel.State.COMPLETE.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[OnboardingEvent.values().length];
            iArr2[OnboardingEvent.TOU_ACCEPTED.ordinal()] = 1;
            iArr2[OnboardingEvent.PERMISSION_NEED_EXPLAINED.ordinal()] = 2;
            iArr2[OnboardingEvent.TOU_REJECTED.ordinal()] = 3;
            iArr2[OnboardingEvent.PERMISSION_NEED_REJECTED.ordinal()] = 4;
            iArr2[OnboardingEvent.ONBOARDING_COMPLETION_ACKNOWLEDGED.ordinal()] = 5;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public OnboardingWorkflowManager(OnboardingWorkflowModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        this.model = model;
        this.onboardingActionObservable = new OnboardingActionPropertyObservable();
    }

    static {
        String simpleName = Reflection.getOrCreateKotlinClass(OnboardingWorkflowManager.class).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        TAG = simpleName;
    }

    public final boolean isOnboardingCompleted() {
        return getOnboardingState$MusicPlatformAOM_release() == OnboardingWorkflowModel.State.COMPLETE;
    }

    public final void displayOnboardingView() {
        int i = WhenMappings.$EnumSwitchMapping$0[getOnboardingState$MusicPlatformAOM_release().ordinal()];
        if (i == 1 || i == 2) {
            this.onboardingActionObservable.updateValue(OnboardingAction.SHOW_TOU_PAGE);
        } else if (i == 3) {
            this.onboardingActionObservable.updateValue(OnboardingAction.SHOW_EXPLAIN_PERMISSION_PAGE);
        } else {
            if (i != 4) {
                return;
            }
            onSuccessfulCompletion();
        }
    }

    public final void onUserEvent(OnboardingEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Logger.INSTANCE.info(TAG, "onUserEvent " + event);
        int i = WhenMappings.$EnumSwitchMapping$1[event.ordinal()];
        if (i == 1) {
            this.model.markTOUAccepted();
            onProceedFurtherInWorkflow();
        } else if (i == 2) {
            onProceedFurtherInWorkflow();
        } else if (i == 3 || i == 4 || i == 5) {
            exitWorkflow();
        }
    }

    private final void onProceedFurtherInWorkflow() {
        OnboardingWorkflowModel.State onboardingState$MusicPlatformAOM_release = getOnboardingState$MusicPlatformAOM_release();
        int i = WhenMappings.$EnumSwitchMapping$0[onboardingState$MusicPlatformAOM_release.ordinal()];
        if (i == 3) {
            this.onboardingActionObservable.updateValue(OnboardingAction.REQUEST_MIC_PERMISSION);
            return;
        }
        if (i == 4) {
            onSuccessfulCompletion();
            return;
        }
        Logger.error$default(Logger.INSTANCE, TAG, "onProceedFurtherInWorkflow : InvalidState " + onboardingState$MusicPlatformAOM_release, null, 4, null);
        exitWorkflow();
    }

    public final void onPermissionResult(boolean granted) {
        Logger logger = Logger.INSTANCE;
        String str = TAG;
        logger.info(str, "onPermissionResult permission granted " + granted);
        if (!granted) {
            exitWorkflow();
            return;
        }
        OnboardingWorkflowModel.State onboardingState$MusicPlatformAOM_release = getOnboardingState$MusicPlatformAOM_release();
        int i = WhenMappings.$EnumSwitchMapping$0[onboardingState$MusicPlatformAOM_release.ordinal()];
        if (i == 2) {
            this.onboardingActionObservable.updateValue(OnboardingAction.SHOW_TOU_PAGE);
            return;
        }
        if (i == 4) {
            onSuccessfulCompletion();
            return;
        }
        Logger.error$default(logger, str, "onPermissionResult : InvalidState " + onboardingState$MusicPlatformAOM_release, null, 4, null);
        exitWorkflow();
    }

    public final void detach() {
        Logger.INSTANCE.info(TAG, "detach");
        this.onboardingActionObservable.clearObservers();
    }

    public final OnboardingWorkflowModel.State getOnboardingState$MusicPlatformAOM_release() {
        boolean zIsTOUAccepted = this.model.isTOUAccepted();
        boolean zIsMicPermissionGranted = this.model.isMicPermissionGranted();
        if (!zIsTOUAccepted && !zIsMicPermissionGranted) {
            return OnboardingWorkflowModel.State.NONE;
        }
        if (zIsTOUAccepted && zIsMicPermissionGranted) {
            return OnboardingWorkflowModel.State.COMPLETE;
        }
        if (zIsTOUAccepted) {
            return OnboardingWorkflowModel.State.TOU;
        }
        return OnboardingWorkflowModel.State.PERMISSION;
    }

    private final void exitWorkflow() {
        if (!isOnboardingCompleted()) {
            this.model.setWakeWordUserSetting(false);
        }
        this.onboardingActionObservable.updateValue(OnboardingAction.CLOSE_UI);
    }

    private final void onSuccessfulCompletion() {
        this.onboardingActionObservable.updateValue(OnboardingAction.SHOW_SUCCESS_PAGE);
        enableWakeWordIfRequired();
    }

    private final void enableWakeWordIfRequired() {
        if (this.model.canAutoEnableWakeWord()) {
            this.model.markCannotAutoEnableWakeWord();
            this.model.setWakeWordUserSetting(true);
        }
        if (this.model.isWakeWordUserSettingEnabled()) {
            this.onboardingActionObservable.updateValue(OnboardingAction.ENABLE_WAKE_WORD);
        }
    }

    public final void registerActionObserver(Function1<? super OnboardingAction, Unit> observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        this.onboardingActionObservable.addObserver(observer);
    }
}
