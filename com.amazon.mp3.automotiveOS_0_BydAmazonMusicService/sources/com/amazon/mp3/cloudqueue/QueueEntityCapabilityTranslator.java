package com.amazon.mp3.cloudqueue;

import com.amazon.digitalmusicxp.enums.CompositeActionStrategyActionTypeEnum;
import com.amazon.digitalmusicxp.enums.UpsellTypeEnum;
import com.amazon.digitalmusicxp.types.ActionStrategy;
import com.amazon.digitalmusicxp.types.CompositeActionStrategy;
import com.amazon.digitalmusicxp.types.SingleActionStrategy;
import com.amazon.digitalmusicxp.types.UpsellAction;
import com.amazon.mp3.cloudqueue.sequencer.QueueEntityCapabilityQueryable;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.skiplimit.SkipLimitProvider;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueEntityCapabilityTranslator.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tJ\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/cloudqueue/QueueEntityCapabilityTranslator;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getAvailabilityFromCapability", "Lcom/amazon/music/media/playback/ActionAvailability;", "entity", "Lcom/amazon/mp3/cloudqueue/sequencer/QueueEntityCapabilityQueryable;", "capabilityType", "Lcom/amazon/mp3/cloudqueue/QueueEntityCapabilityTranslator$QueueEntityCapability;", "getSkipNextAvailabilityFromCapability", "getUpsellTypeFromCapability", "Lcom/amazon/digitalmusicxp/enums/UpsellTypeEnum;", "QueueEntityCapability", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class QueueEntityCapabilityTranslator {
    public static final QueueEntityCapabilityTranslator INSTANCE = new QueueEntityCapabilityTranslator();
    private static final String TAG = QueueEntityCapabilityTranslator.class.getSimpleName();

    /* JADX INFO: compiled from: QueueEntityCapabilityTranslator.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/cloudqueue/QueueEntityCapabilityTranslator$QueueEntityCapability;", "", "(Ljava/lang/String;I)V", "PREVIOUS", "NEXT", "SCRUB_FORWARD", "SCRUB_BACKWARD", "RATE", "DISPLAYABLE", "LOOP_ONE", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum QueueEntityCapability {
        PREVIOUS,
        NEXT,
        SCRUB_FORWARD,
        SCRUB_BACKWARD,
        RATE,
        DISPLAYABLE,
        LOOP_ONE
    }

    /* JADX INFO: compiled from: QueueEntityCapabilityTranslator.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CompositeActionStrategyActionTypeEnum.values().length];
            iArr[CompositeActionStrategyActionTypeEnum.SKIP_LIMIT_IOT.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[QueueEntityCapability.values().length];
            iArr2[QueueEntityCapability.LOOP_ONE.ordinal()] = 1;
            iArr2[QueueEntityCapability.RATE.ordinal()] = 2;
            iArr2[QueueEntityCapability.PREVIOUS.ordinal()] = 3;
            iArr2[QueueEntityCapability.SCRUB_FORWARD.ordinal()] = 4;
            iArr2[QueueEntityCapability.SCRUB_BACKWARD.ordinal()] = 5;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private QueueEntityCapabilityTranslator() {
    }

    public final ActionAvailability getSkipNextAvailabilityFromCapability(QueueEntityCapabilityQueryable entity) {
        CompositeActionStrategy compositeActionStrategy;
        Intrinsics.checkNotNullParameter(entity, "entity");
        ActionStrategy next = entity.getCapability().getNext();
        CompositeActionStrategyActionTypeEnum actionType = (next == null || (compositeActionStrategy = next.getCompositeActionStrategy()) == null) ? null : compositeActionStrategy.getActionType();
        if ((actionType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[actionType.ordinal()]) != 1) {
            return null;
        }
        SkipLimitProvider skipLimitProvider = Playback.getInstance().getPlaybackConfig().getSkipLimitProvider();
        return (skipLimitProvider == null ? Integer.MAX_VALUE : skipLimitProvider.getCount()) <= 0 ? ActionAvailability.UNAVAILABLE_SKIP_LIMIT : ActionAvailability.AVAILABLE;
    }

    @JvmStatic
    public static final ActionAvailability getAvailabilityFromCapability(QueueEntityCapabilityQueryable entity, QueueEntityCapability capabilityType) {
        SingleActionStrategy singleActionStrategy;
        SingleActionStrategy singleActionStrategy2;
        Intrinsics.checkNotNullParameter(entity, "entity");
        Intrinsics.checkNotNullParameter(capabilityType, "capabilityType");
        com.amazon.digitalmusicxp.types.QueueEntityCapability capability = entity.getCapability();
        int i = WhenMappings.$EnumSwitchMapping$1[capabilityType.ordinal()];
        Boolean booleanAction = null;
        if (i == 1) {
            ActionStrategy loopOne = capability.getLoopOne();
            if (loopOne != null && (singleActionStrategy = loopOne.getSingleActionStrategy()) != null) {
                booleanAction = singleActionStrategy.getBooleanAction();
            }
            return Intrinsics.areEqual(booleanAction, Boolean.TRUE) ? ActionAvailability.AVAILABLE : ActionAvailability.UNAVAILABLE;
        }
        if (i == 2) {
            ActionStrategy rate = capability.getRate();
            if (rate != null && (singleActionStrategy2 = rate.getSingleActionStrategy()) != null) {
                booleanAction = singleActionStrategy2.getBooleanAction();
            }
            return Intrinsics.areEqual(booleanAction, Boolean.TRUE) ? ActionAvailability.AVAILABLE : ActionAvailability.UNAVAILABLE;
        }
        Log.error(TAG, "Unhandled capability type [" + capabilityType + "] for action availability. Defaulting to unavailable");
        return ActionAvailability.UNAVAILABLE;
    }

    @JvmStatic
    public static final UpsellTypeEnum getUpsellTypeFromCapability(QueueEntityCapabilityQueryable entity, QueueEntityCapability capabilityType) {
        SingleActionStrategy singleActionStrategy;
        UpsellAction upsellAction;
        SingleActionStrategy singleActionStrategy2;
        UpsellAction upsellAction2;
        SingleActionStrategy singleActionStrategy3;
        UpsellAction upsellAction3;
        Intrinsics.checkNotNullParameter(entity, "entity");
        Intrinsics.checkNotNullParameter(capabilityType, "capabilityType");
        com.amazon.digitalmusicxp.types.QueueEntityCapability capability = entity.getCapability();
        int i = WhenMappings.$EnumSwitchMapping$1[capabilityType.ordinal()];
        if (i == 3) {
            ActionStrategy previous = capability.getPrevious();
            if (previous == null || (singleActionStrategy = previous.getSingleActionStrategy()) == null || (upsellAction = singleActionStrategy.getUpsellAction()) == null) {
                return null;
            }
            return upsellAction.getUpsellType();
        }
        if (i == 4) {
            ActionStrategy scrubForward = capability.getScrubForward();
            if (scrubForward == null || (singleActionStrategy2 = scrubForward.getSingleActionStrategy()) == null || (upsellAction2 = singleActionStrategy2.getUpsellAction()) == null) {
                return null;
            }
            return upsellAction2.getUpsellType();
        }
        if (i == 5) {
            ActionStrategy scrubBackward = capability.getScrubBackward();
            if (scrubBackward == null || (singleActionStrategy3 = scrubBackward.getSingleActionStrategy()) == null || (upsellAction3 = singleActionStrategy3.getUpsellAction()) == null) {
                return null;
            }
            return upsellAction3.getUpsellType();
        }
        Log.error(TAG, "Unhandled capability type [" + capabilityType + "] for upsell unwrapping. Defaulting to null");
        return null;
    }
}
