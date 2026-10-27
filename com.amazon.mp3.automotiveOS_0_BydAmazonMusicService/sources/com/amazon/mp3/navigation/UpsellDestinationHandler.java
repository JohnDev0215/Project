package com.amazon.mp3.navigation;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.navigation.LauncherLinkStatusHandler;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.music.destination.UpsellDestination;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.router.DestinationHandler;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: UpsellDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\bH\u0002J\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u001c\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/navigation/UpsellDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/UpsellDestination;", "Lcom/amazon/mp3/navigation/LauncherLinkStatusHandler;", "()V", "convertUpsellReason", "Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellReason;", "upsellReason", "", "convertUpsellSource", "Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellSourceEntity;", "upsellSource", "getFragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "navigateTo", "", "context", "Landroid/content/Context;", "destination", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UpsellDestinationHandler implements DestinationHandler<UpsellDestination>, LauncherLinkStatusHandler {
    private static final Logger logger = LoggerFactory.getLogger(UpsellDestinationHandler.class.getName());

    /* JADX INFO: compiled from: UpsellDestinationHandler.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[UpsellReason.values().length];
            iArr[UpsellReason.PERSISTENT_UPSELL.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.mp3.navigation.LauncherLinkStatusHandler
    public void broadcastSuccessfullyHandledLauncherLink(Context context, String str, Uri uri) {
        LauncherLinkStatusHandler.DefaultImpls.broadcastSuccessfullyHandledLauncherLink(this, context, str, uri);
    }

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, UpsellDestination destination) {
        FragmentActivity fragmentActivity;
        UpsellReason upsellReasonConvertUpsellReason = convertUpsellReason(destination == null ? null : destination.getUpsellReason());
        UpsellSourceEntity upsellSourceEntityConvertUpsellSource = convertUpsellSource(destination == null ? null : destination.getUpsellSource());
        boolean z = false;
        if (destination != null && destination.getIsBlocking()) {
            z = true;
        }
        if (!z || upsellReasonConvertUpsellReason == null || (fragmentActivity = getFragmentActivity()) == null) {
            return;
        }
        UpsellUtil.showBlockingUpsell(fragmentActivity, null, upsellReasonConvertUpsellReason, upsellSourceEntityConvertUpsellSource);
    }

    private final UpsellReason convertUpsellReason(String upsellReason) {
        UpsellReason upsellReasonValueOf;
        if (upsellReason == null) {
            upsellReasonValueOf = null;
        } else {
            try {
                String upperCase = upsellReason.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                upsellReasonValueOf = UpsellReason.valueOf(upperCase);
            } catch (Exception e) {
                logger.error("Invalid upsell reason, using default value", (Throwable) e);
                upsellReasonValueOf = null;
            }
        }
        if ((upsellReasonValueOf == null ? -1 : WhenMappings.$EnumSwitchMapping$0[upsellReasonValueOf.ordinal()]) == 1) {
            return UpsellReason.PERSISTENT_UPSELL;
        }
        return null;
    }

    private final UpsellSourceEntity convertUpsellSource(String upsellSource) {
        if (upsellSource == null) {
            return null;
        }
        try {
            String upperCase = upsellSource.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            return UpsellSourceEntity.valueOf(upperCase);
        } catch (Exception e) {
            logger.error("Invalid upsell source, using default value", (Throwable) e);
            return null;
        }
    }

    private final FragmentActivity getFragmentActivity() {
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        Activity previousActivity = AndroidUtils.getPreviousActivity();
        FragmentActivity fragmentActivity = currentActivity instanceof FragmentActivity ? (FragmentActivity) currentActivity : null;
        if (fragmentActivity != null) {
            return fragmentActivity;
        }
        if (previousActivity instanceof FragmentActivity) {
            return (FragmentActivity) previousActivity;
        }
        return null;
    }
}
