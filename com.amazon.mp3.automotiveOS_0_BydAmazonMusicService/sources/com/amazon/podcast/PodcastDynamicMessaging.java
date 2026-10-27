package com.amazon.podcast;

import UpsellInterface.v1_0.UpsellTriggerType;
import android.app.Activity;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.inappmessaging.DynamicMessagingManager;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.util.Log;
import com.amazon.music.inappmessaging.external.model.DynamicMessageContextualEventBuilder;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.podcast.bootstrap.DynamicMessagingProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PodcastDynamicMessaging.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J \u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0010\u001a\n \u000f*\u0004\u0018\u00010\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/podcast/PodcastDynamicMessaging;", "Lcom/amazon/podcast/bootstrap/DynamicMessagingProvider;", "", "upsellTriggerType", "Lcom/amazon/music/inappmessaging/external/model/Trigger;", "getDynamicMessageTrigger", "redirectDeeplinkUrl", "pageType", "", "showDynamicMessage", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "(Landroid/app/Activity;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PodcastDynamicMessaging implements DynamicMessagingProvider {
    private final String TAG;
    private final Activity activity;

    public PodcastDynamicMessaging(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.TAG = DynamicMessagingManager.class.getSimpleName();
    }

    @Override // com.amazon.podcast.bootstrap.DynamicMessagingProvider
    public void showDynamicMessage(String upsellTriggerType, String redirectDeeplinkUrl, String pageType) {
        Intrinsics.checkNotNullParameter(upsellTriggerType, "upsellTriggerType");
        Intrinsics.checkNotNullParameter(redirectDeeplinkUrl, "redirectDeeplinkUrl");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        DynamicMessagingManager dynamicMessagingManager = new DynamicMessagingManager(this.activity);
        DynamicMessageEvent dynamicMessageEventBuild = DynamicMessageContextualEventBuilder.builder(getDynamicMessageTrigger(upsellTriggerType)).build();
        DynamicMessageMetricsAttributes dynamicMessageMetricsAttributes = new DynamicMessageMetricsAttributes(pageType, UpsellReason.PERSISTENT_UPSELL.getValue());
        Activity activity = this.activity;
        if (activity instanceof FragmentActivity) {
            dynamicMessagingManager.displayDynamicMessage((FragmentActivity) activity, dynamicMessageEventBuild, dynamicMessageMetricsAttributes).subscribe();
        } else {
            Log.error(this.TAG, "Activity is not FragmentActivity. DynamicMessagingManager only works with FragmentActivity. Not showing Upsell.");
        }
    }

    private final Trigger getDynamicMessageTrigger(String upsellTriggerType) {
        if (Intrinsics.areEqual(UpsellTriggerType.INELIGIBLE_CONTENT_REQUEST, upsellTriggerType)) {
            return Trigger.INELIGIBLE_CONTENT_REQUEST;
        }
        return Trigger.PERSISTENT_UPSELL;
    }
}
