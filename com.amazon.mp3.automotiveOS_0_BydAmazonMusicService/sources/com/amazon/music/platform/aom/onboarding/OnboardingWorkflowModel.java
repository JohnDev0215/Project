package com.amazon.music.platform.aom.onboarding;

import UpsellInterface.v1_0.PlaybackMode;
import com.amazon.music.platform.aom.onboarding.provider.MicPermissionProvider;
import com.amazon.music.platform.aom.onboarding.provider.TOUSettingProvider;
import com.amazon.music.platform.aom.onboarding.provider.WakeWordSettingProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OnboardingWorkflowModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u0013B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\nJ\u0006\u0010\r\u001a\u00020\nJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/aom/onboarding/OnboardingWorkflowModel;", "", "touSettingProvider", "Lcom/amazon/music/platform/aom/onboarding/provider/TOUSettingProvider;", "micPermissionProvider", "Lcom/amazon/music/platform/aom/onboarding/provider/MicPermissionProvider;", "wakeWordSettingProvider", "Lcom/amazon/music/platform/aom/onboarding/provider/WakeWordSettingProvider;", "(Lcom/amazon/music/platform/aom/onboarding/provider/TOUSettingProvider;Lcom/amazon/music/platform/aom/onboarding/provider/MicPermissionProvider;Lcom/amazon/music/platform/aom/onboarding/provider/WakeWordSettingProvider;)V", "canAutoEnableWakeWord", "", "isMicPermissionGranted", "isTOUAccepted", "isWakeWordUserSettingEnabled", "markCannotAutoEnableWakeWord", "", "markTOUAccepted", "setWakeWordUserSetting", "shouldEnable", "State", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OnboardingWorkflowModel {
    private final MicPermissionProvider micPermissionProvider;
    private final TOUSettingProvider touSettingProvider;
    private final WakeWordSettingProvider wakeWordSettingProvider;

    /* JADX INFO: compiled from: OnboardingWorkflowModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/platform/aom/onboarding/OnboardingWorkflowModel$State;", "", "(Ljava/lang/String;I)V", PlaybackMode.NONE, "TOU", "PERMISSION", "COMPLETE", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum State {
        NONE,
        TOU,
        PERMISSION,
        COMPLETE
    }

    public OnboardingWorkflowModel(TOUSettingProvider touSettingProvider, MicPermissionProvider micPermissionProvider, WakeWordSettingProvider wakeWordSettingProvider) {
        Intrinsics.checkNotNullParameter(touSettingProvider, "touSettingProvider");
        Intrinsics.checkNotNullParameter(micPermissionProvider, "micPermissionProvider");
        Intrinsics.checkNotNullParameter(wakeWordSettingProvider, "wakeWordSettingProvider");
        this.touSettingProvider = touSettingProvider;
        this.micPermissionProvider = micPermissionProvider;
        this.wakeWordSettingProvider = wakeWordSettingProvider;
    }

    public final boolean isTOUAccepted() {
        return this.touSettingProvider.isTOUAccepted();
    }

    public final void markTOUAccepted() {
        this.touSettingProvider.markTOUAccepted();
    }

    public final boolean isMicPermissionGranted() {
        return this.micPermissionProvider.isMicPermissionGranted();
    }

    public final boolean isWakeWordUserSettingEnabled() {
        return this.wakeWordSettingProvider.isWakeWordUserSettingEnabled();
    }

    public final void setWakeWordUserSetting(boolean shouldEnable) {
        this.wakeWordSettingProvider.setWakeWordUserSetting(shouldEnable);
    }

    public final boolean canAutoEnableWakeWord() {
        return this.wakeWordSettingProvider.canAutoEnableWakeWord();
    }

    public final void markCannotAutoEnableWakeWord() {
        this.wakeWordSettingProvider.markCannotAutoEnableWakeWord();
    }
}
