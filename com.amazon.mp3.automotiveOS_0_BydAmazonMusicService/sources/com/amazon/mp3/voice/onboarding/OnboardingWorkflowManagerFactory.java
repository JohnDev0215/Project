package com.amazon.mp3.voice.onboarding;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.voice.onboarding.provider.AlexaMicPermissionProvider;
import com.amazon.mp3.voice.onboarding.provider.AlexaTOUSettingProvider;
import com.amazon.mp3.voice.onboarding.provider.AlexaWakeWordSettingProvider;
import com.amazon.music.platform.aom.onboarding.OnboardingWorkflowManager;
import com.amazon.music.platform.aom.onboarding.OnboardingWorkflowModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OnboardingWorkflowManagerFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/OnboardingWorkflowManagerFactory;", "", "()V", "createOnboardingWorkflowManager", "Lcom/amazon/music/platform/aom/onboarding/OnboardingWorkflowManager;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OnboardingWorkflowManagerFactory {
    public final OnboardingWorkflowManager createOnboardingWorkflowManager() {
        Context context = AmazonApplication.getApplication().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return new OnboardingWorkflowManager(new OnboardingWorkflowModel(new AlexaTOUSettingProvider(context), new AlexaMicPermissionProvider(context), new AlexaWakeWordSettingProvider(context)));
    }
}
