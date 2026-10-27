package com.amazon.mp3.util;

import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentActivity;
import com.amazon.digitalmusicxp.enums.UpsellTypeEnum;
import com.amazon.mp3.cloudqueue.QueueEntityCapabilityTranslator;
import com.amazon.mp3.cloudqueue.sequencer.QueueEntityCapabilityQueryable;
import com.amazon.mp3.inappmessaging.DynamicMessagingManager;
import com.amazon.mp3.prime.upsell.nightwing.UpsellManager;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DynamicUpsellTranslator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0001¢\u0006\u0002\b\nJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0001¢\u0006\u0002\b\u000fJ\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007H\u0007J$\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\b\u001a\u00020\tH\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/util/DynamicUpsellTranslator;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getUpsellReason", "Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellReason;", "capability", "Lcom/amazon/mp3/cloudqueue/QueueEntityCapabilityTranslator$QueueEntityCapability;", "getUpsellReason$DigitalMusicAndroid3P_automotiveProdRelease", "getUpsellTrigger", "Lcom/amazon/music/inappmessaging/external/model/Trigger;", "upsellType", "Lcom/amazon/digitalmusicxp/enums/UpsellTypeEnum;", "getUpsellTrigger$DigitalMusicAndroid3P_automotiveProdRelease", "upsellReason", "showBlockingCapabilityUpsell", "", "fragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DynamicUpsellTranslator {
    public static final DynamicUpsellTranslator INSTANCE = new DynamicUpsellTranslator();
    private static final String TAG = DynamicUpsellTranslator.class.getSimpleName();

    /* JADX INFO: compiled from: DynamicUpsellTranslator.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[UpsellReason.values().length];
            iArr[UpsellReason.PERSISTENT_UPSELL.ordinal()] = 1;
            iArr[UpsellReason.TRACK_SKIP_LIMIT.ordinal()] = 2;
            iArr[UpsellReason.TRACK_PREVIOUS.ordinal()] = 3;
            iArr[UpsellReason.TRACK_NEXT.ordinal()] = 4;
            iArr[UpsellReason.AD_SKIP.ordinal()] = 5;
            iArr[UpsellReason.TRACK_SEEK.ordinal()] = 6;
            iArr[UpsellReason.DOWNLOAD_CONTENT.ordinal()] = 7;
            iArr[UpsellReason.ADD_TO_PLAY_QUEUE.ordinal()] = 8;
            iArr[UpsellReason.ADD_TO_LIBRARY.ordinal()] = 9;
            iArr[UpsellReason.SHUFFLE_ALL.ordinal()] = 10;
            iArr[UpsellReason.PLAY.ordinal()] = 11;
            iArr[UpsellReason.OTHER.ordinal()] = 12;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[UpsellTypeEnum.values().length];
            iArr2[UpsellTypeEnum.FEATURE_UNAVAILABLE.ordinal()] = 1;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[QueueEntityCapabilityTranslator.QueueEntityCapability.values().length];
            iArr3[QueueEntityCapabilityTranslator.QueueEntityCapability.SCRUB_FORWARD.ordinal()] = 1;
            iArr3[QueueEntityCapabilityTranslator.QueueEntityCapability.SCRUB_BACKWARD.ordinal()] = 2;
            iArr3[QueueEntityCapabilityTranslator.QueueEntityCapability.PREVIOUS.ordinal()] = 3;
            iArr3[QueueEntityCapabilityTranslator.QueueEntityCapability.NEXT.ordinal()] = 4;
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    private DynamicUpsellTranslator() {
    }

    @JvmStatic
    public static final Trigger getUpsellTrigger(UpsellReason upsellReason) {
        switch (upsellReason == null ? -1 : WhenMappings.$EnumSwitchMapping$0[upsellReason.ordinal()]) {
            case 1:
                return Trigger.PERSISTENT_UPSELL;
            case 2:
                return Trigger.SKIP_LIMIT_REACHED;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                return Trigger.FEATURE_UNAVAILABLE;
            default:
                Log.error(TAG, "Unhandled NativeUpsellReason. Add additional logic for " + upsellReason + ". Defaulting to Trigger.FEATURE_UNAVAILABLE");
                return Trigger.FEATURE_UNAVAILABLE;
        }
    }

    @JvmStatic
    @VisibleForTesting(otherwise = 4)
    public static final Trigger getUpsellTrigger$DigitalMusicAndroid3P_automotiveProdRelease(UpsellTypeEnum upsellType) {
        if ((upsellType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[upsellType.ordinal()]) == 1) {
            return Trigger.FEATURE_UNAVAILABLE;
        }
        Log.error(TAG, "Unhandled UpsellTypeEnum. Add additional logic for " + upsellType + ". Returning null");
        return null;
    }

    @JvmStatic
    @VisibleForTesting(otherwise = 4)
    public static final UpsellReason getUpsellReason$DigitalMusicAndroid3P_automotiveProdRelease(QueueEntityCapabilityTranslator.QueueEntityCapability capability) {
        int i = capability == null ? -1 : WhenMappings.$EnumSwitchMapping$2[capability.ordinal()];
        if (i == 1 || i == 2) {
            return UpsellReason.TRACK_SEEK;
        }
        if (i == 3) {
            return UpsellReason.TRACK_PREVIOUS;
        }
        if (i == 4) {
            return UpsellReason.TRACK_NEXT;
        }
        Log.error(TAG, "getUpsellReason:: Unhandled capability. Add additional logic for " + capability + ". Defaulting to OTHER");
        return UpsellReason.OTHER;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    @VisibleForTesting
    public static final void showBlockingCapabilityUpsell(FragmentActivity fragmentActivity, MediaItem mediaItem, QueueEntityCapabilityTranslator.QueueEntityCapability capability) {
        Intrinsics.checkNotNullParameter(capability, "capability");
        String asinFromMediaItem = UpsellUtil.getAsinFromMediaItem(mediaItem);
        UpsellReason upsellReason$DigitalMusicAndroid3P_automotiveProdRelease = getUpsellReason$DigitalMusicAndroid3P_automotiveProdRelease(capability);
        UpsellSourceEntity upsellSourceEntity = UpsellSourceEntity.TRACK;
        if (mediaItem instanceof QueueEntityCapabilityQueryable) {
            UpsellTypeEnum upsellTypeFromCapability = QueueEntityCapabilityTranslator.getUpsellTypeFromCapability((QueueEntityCapabilityQueryable) mediaItem, capability);
            if (upsellTypeFromCapability == null) {
                return;
            }
            Trigger upsellTrigger$DigitalMusicAndroid3P_automotiveProdRelease = getUpsellTrigger$DigitalMusicAndroid3P_automotiveProdRelease(upsellTypeFromCapability);
            if (upsellTrigger$DigitalMusicAndroid3P_automotiveProdRelease != null) {
                if (DynamicMessagingManager.isDynamicMessagingSupported()) {
                    UpsellInformation upsellInformation = UpsellUtil.getUpsellInformation(asinFromMediaItem, upsellReason$DigitalMusicAndroid3P_automotiveProdRelease, upsellSourceEntity);
                    DynamicMessageMetricsAttributes dynamicMessageMetricsAttributes = new DynamicMessageMetricsAttributes(PageType.NOW_PLAYING.getMetricsValue(), upsellReason$DigitalMusicAndroid3P_automotiveProdRelease.getValue());
                    UpsellUtil.emitEligibilityCheckRequestedMetric(upsellInformation);
                    UpsellManager.getInstance().showDynamicMessageOneClick(fragmentActivity, upsellTrigger$DigitalMusicAndroid3P_automotiveProdRelease, dynamicMessageMetricsAttributes);
                    return;
                }
                Log.warning(TAG, "showBlockingCapabilityUpsell:: Shouldn't get here. Falling back to hard-coded capability upsell");
            }
        }
        UpsellUtil.showBlockingUpsell(fragmentActivity, asinFromMediaItem, upsellReason$DigitalMusicAndroid3P_automotiveProdRelease, upsellSourceEntity);
    }
}
