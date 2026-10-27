package com.amazon.mp3.navigation;

import android.content.Context;
import com.amazon.mp3.activity.WideDialogActivity;
import com.amazon.mp3.activity.WideDialogActivityPurpose;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.destination.SettingsDestination;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.router.DestinationHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SettingsDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/navigation/SettingsDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/SettingsDestination;", "()V", "navigateTo", "", "context", "Landroid/content/Context;", "destination", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SettingsDestinationHandler implements DestinationHandler<SettingsDestination> {
    private static final Logger logger = LoggerFactory.getLogger(SettingsDestinationHandler.class.getName());

    /* JADX INFO: compiled from: SettingsDestinationHandler.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SettingsDestination.SettingTarget.values().length];
            iArr[SettingsDestination.SettingTarget.STREAMING_AUDIO_QUALITY.ordinal()] = 1;
            iArr[SettingsDestination.SettingTarget.DOWNLOAD_AUDIO_QUALITY.ordinal()] = 2;
            iArr[SettingsDestination.SettingTarget.NOTIFICATIONS.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, SettingsDestination destination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(destination, "destination");
        SettingsDestination.SettingTarget target = destination.getTarget();
        int i = target == null ? -1 : WhenMappings.$EnumSwitchMapping$0[target.ordinal()];
        if (i == 1) {
            WideDialogActivity.Companion.showActivity$default(WideDialogActivity.INSTANCE, context, WideDialogActivityPurpose.SHOW_KATANA_AUDIO_QUALITY_PREFERENCES, R.style.Theme_AmazonMP3_Dialog, null, 8, null);
            UserInteractionAppEvent.builder("goAudioQualitySettings").publish();
        } else if (i == 2) {
            WideDialogActivity.Companion.showActivity$default(WideDialogActivity.INSTANCE, context, WideDialogActivityPurpose.SHOW_KATANA_DOWNLOAD_QUALITY_PREFERENCES, R.style.Theme_AmazonMP3_Dialog, null, 8, null);
            UserInteractionAppEvent.builder("goDownloadSettings").publish();
        } else if (i == 3) {
            WideDialogActivity.Companion.showActivity$default(WideDialogActivity.INSTANCE, context, WideDialogActivityPurpose.SHOW_PUSH_TOPIC_SELECTION, R.style.Theme_AmazonMP3_Dialog, null, 8, null);
            UserInteractionAppEvent.builder("pushNotificationTopicSettings").publish();
        } else {
            logger.error(Intrinsics.stringPlus("Unexpected target in navigateTo ", destination.getTarget()));
        }
    }
}
