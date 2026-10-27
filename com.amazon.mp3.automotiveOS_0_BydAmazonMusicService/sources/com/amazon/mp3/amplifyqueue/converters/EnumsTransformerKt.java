package com.amazon.mp3.amplifyqueue.converters;

import com.amazon.digitalmusicxp.enums.ActionStrategyTypeEnum;
import com.amazon.digitalmusicxp.enums.CompositeActionStrategyActionTypeEnum;
import com.amazon.digitalmusicxp.enums.ConnectResponseStatusEnum;
import com.amazon.digitalmusicxp.enums.ContentEncodingEnum;
import com.amazon.digitalmusicxp.enums.DevicePlaybackStateEnum;
import com.amazon.digitalmusicxp.enums.ExperienceStateEnum;
import com.amazon.digitalmusicxp.enums.GetNextQueueEntityResponseStatusEnum;
import com.amazon.digitalmusicxp.enums.GetQueueViewResponseStatusEnum;
import com.amazon.digitalmusicxp.enums.InitiateQueueResponseContextEnum;
import com.amazon.digitalmusicxp.enums.LoopModeEnum;
import com.amazon.digitalmusicxp.enums.MetricsSpecEnum;
import com.amazon.digitalmusicxp.enums.OnlinePopulationStatusEnum;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.digitalmusicxp.enums.RatingEnum;
import com.amazon.digitalmusicxp.enums.ShuffleModeEnum;
import com.amazon.digitalmusicxp.enums.SingleActionStrategyActionTypeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EnumsTransformer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000\u001a\f\u0010\u0002\u001a\u00020\u0004*\u00020\u0003H\u0000\u001a\f\u0010\u0002\u001a\u00020\u0006*\u00020\u0005H\u0000\u001a\f\u0010\u0002\u001a\u00020\b*\u00020\u0007H\u0000\u001a\f\u0010\t\u001a\u00020\u0007*\u00020\bH\u0000\u001a\f\u0010\u0002\u001a\u00020\u000b*\u00020\nH\u0000\u001a\f\u0010\u0002\u001a\u00020\r*\u00020\fH\u0000\u001a\f\u0010\u0002\u001a\u00020\u000f*\u00020\u000eH\u0000\u001a\f\u0010\u0002\u001a\u00020\u0011*\u00020\u0010H\u0000\u001a\f\u0010\u0002\u001a\u00020\u0013*\u00020\u0012H\u0000\u001a\f\u0010\t\u001a\u00020\u0012*\u00020\u0013H\u0000\u001a\f\u0010\u0002\u001a\u00020\u0015*\u00020\u0014H\u0000\u001a\f\u0010\u0002\u001a\u00020\u0017*\u00020\u0016H\u0000\u001a\f\u0010\t\u001a\u00020\u0016*\u00020\u0017H\u0000\u001a\f\u0010\u0002\u001a\u00020\u0019*\u00020\u0018H\u0000\u001a\f\u0010\u0002\u001a\u00020\u001b*\u00020\u001aH\u0000\u001a\f\u0010\t\u001a\u00020\u001a*\u00020\u001bH\u0000\u001a\f\u0010\u0002\u001a\u00020\u001d*\u00020\u001cH\u0000\u001a\f\u0010\u0002\u001a\u00020\u001f*\u00020\u001eH\u0000\u001a\u000e\u0010\u0002\u001a\u0004\u0018\u00010!*\u00020 H\u0000¨\u0006\""}, d2 = {"Lcom/amazon/mp3/amplifyqueue/model/ActionStrategyTypeEnum;", "Lcom/amazon/digitalmusicxp/enums/ActionStrategyTypeEnum;", "toExternal", "Lcom/amazon/mp3/amplifyqueue/model/CompositeActionStrategyActionTypeEnum;", "Lcom/amazon/digitalmusicxp/enums/CompositeActionStrategyActionTypeEnum;", "Lcom/amazon/mp3/amplifyqueue/model/ConnectResponseStatusEnum;", "Lcom/amazon/digitalmusicxp/enums/ConnectResponseStatusEnum;", "Lcom/amazon/mp3/amplifyqueue/model/DevicePlaybackStateEnum;", "Lcom/amazon/digitalmusicxp/enums/DevicePlaybackStateEnum;", "toAmplify", "Lcom/amazon/mp3/amplifyqueue/model/ExperienceStateEnum;", "Lcom/amazon/digitalmusicxp/enums/ExperienceStateEnum;", "Lcom/amazon/mp3/amplifyqueue/model/GetNextQueueEntityResponseStatusEnum;", "Lcom/amazon/digitalmusicxp/enums/GetNextQueueEntityResponseStatusEnum;", "Lcom/amazon/mp3/amplifyqueue/model/GetQueueViewResponseStatusEnum;", "Lcom/amazon/digitalmusicxp/enums/GetQueueViewResponseStatusEnum;", "Lcom/amazon/mp3/amplifyqueue/model/InitiateQueueResponseContextEnum;", "Lcom/amazon/digitalmusicxp/enums/InitiateQueueResponseContextEnum;", "Lcom/amazon/mp3/amplifyqueue/model/LoopModeEnum;", "Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;", "Lcom/amazon/mp3/amplifyqueue/model/MetricsSpecEnum;", "Lcom/amazon/digitalmusicxp/enums/MetricsSpecEnum;", "Lcom/amazon/mp3/amplifyqueue/model/OnlinePopulationStatusEnum;", "Lcom/amazon/digitalmusicxp/enums/OnlinePopulationStatusEnum;", "Lcom/amazon/mp3/amplifyqueue/model/RatingEnum;", "Lcom/amazon/digitalmusicxp/enums/RatingEnum;", "Lcom/amazon/mp3/amplifyqueue/model/ShuffleModeEnum;", "Lcom/amazon/digitalmusicxp/enums/ShuffleModeEnum;", "Lcom/amazon/mp3/amplifyqueue/model/SingleActionStrategyActionTypeEnum;", "Lcom/amazon/digitalmusicxp/enums/SingleActionStrategyActionTypeEnum;", "Lcom/amazon/mp3/amplifyqueue/model/QueueSeedTypeEnum;", "Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;", "Lcom/amazon/mp3/amplifyqueue/model/ContentEncodingEnum;", "Lcom/amazon/digitalmusicxp/enums/ContentEncodingEnum;", "amplifyqueue_release"}, k = 2, mv = {1, 6, 0})
public final class EnumsTransformerKt {
    public static final ActionStrategyTypeEnum toExternal(com.amazon.mp3.amplifyqueue.model.ActionStrategyTypeEnum actionStrategyTypeEnum) {
        Intrinsics.checkNotNullParameter(actionStrategyTypeEnum, "<this>");
        return ActionStrategyTypeEnum.valueOf(actionStrategyTypeEnum.name());
    }

    public static final CompositeActionStrategyActionTypeEnum toExternal(com.amazon.mp3.amplifyqueue.model.CompositeActionStrategyActionTypeEnum compositeActionStrategyActionTypeEnum) {
        Intrinsics.checkNotNullParameter(compositeActionStrategyActionTypeEnum, "<this>");
        return CompositeActionStrategyActionTypeEnum.valueOf(compositeActionStrategyActionTypeEnum.name());
    }

    public static final ConnectResponseStatusEnum toExternal(com.amazon.mp3.amplifyqueue.model.ConnectResponseStatusEnum connectResponseStatusEnum) {
        Intrinsics.checkNotNullParameter(connectResponseStatusEnum, "<this>");
        return ConnectResponseStatusEnum.valueOf(connectResponseStatusEnum.name());
    }

    public static final DevicePlaybackStateEnum toExternal(com.amazon.mp3.amplifyqueue.model.DevicePlaybackStateEnum devicePlaybackStateEnum) {
        Intrinsics.checkNotNullParameter(devicePlaybackStateEnum, "<this>");
        return DevicePlaybackStateEnum.valueOf(devicePlaybackStateEnum.name());
    }

    public static final com.amazon.mp3.amplifyqueue.model.DevicePlaybackStateEnum toAmplify(DevicePlaybackStateEnum devicePlaybackStateEnum) {
        Intrinsics.checkNotNullParameter(devicePlaybackStateEnum, "<this>");
        return com.amazon.mp3.amplifyqueue.model.DevicePlaybackStateEnum.valueOf(devicePlaybackStateEnum.name());
    }

    public static final ExperienceStateEnum toExternal(com.amazon.mp3.amplifyqueue.model.ExperienceStateEnum experienceStateEnum) {
        Intrinsics.checkNotNullParameter(experienceStateEnum, "<this>");
        return ExperienceStateEnum.valueOf(experienceStateEnum.name());
    }

    public static final GetNextQueueEntityResponseStatusEnum toExternal(com.amazon.mp3.amplifyqueue.model.GetNextQueueEntityResponseStatusEnum getNextQueueEntityResponseStatusEnum) {
        Intrinsics.checkNotNullParameter(getNextQueueEntityResponseStatusEnum, "<this>");
        return GetNextQueueEntityResponseStatusEnum.valueOf(getNextQueueEntityResponseStatusEnum.name());
    }

    public static final GetQueueViewResponseStatusEnum toExternal(com.amazon.mp3.amplifyqueue.model.GetQueueViewResponseStatusEnum getQueueViewResponseStatusEnum) {
        Intrinsics.checkNotNullParameter(getQueueViewResponseStatusEnum, "<this>");
        return GetQueueViewResponseStatusEnum.valueOf(getQueueViewResponseStatusEnum.name());
    }

    public static final InitiateQueueResponseContextEnum toExternal(com.amazon.mp3.amplifyqueue.model.InitiateQueueResponseContextEnum initiateQueueResponseContextEnum) {
        Intrinsics.checkNotNullParameter(initiateQueueResponseContextEnum, "<this>");
        return InitiateQueueResponseContextEnum.valueOf(initiateQueueResponseContextEnum.name());
    }

    public static final LoopModeEnum toExternal(com.amazon.mp3.amplifyqueue.model.LoopModeEnum loopModeEnum) {
        Intrinsics.checkNotNullParameter(loopModeEnum, "<this>");
        return LoopModeEnum.valueOf(loopModeEnum.name());
    }

    public static final com.amazon.mp3.amplifyqueue.model.LoopModeEnum toAmplify(LoopModeEnum loopModeEnum) {
        Intrinsics.checkNotNullParameter(loopModeEnum, "<this>");
        return com.amazon.mp3.amplifyqueue.model.LoopModeEnum.valueOf(loopModeEnum.name());
    }

    public static final MetricsSpecEnum toExternal(com.amazon.mp3.amplifyqueue.model.MetricsSpecEnum metricsSpecEnum) {
        Intrinsics.checkNotNullParameter(metricsSpecEnum, "<this>");
        return MetricsSpecEnum.valueOf(metricsSpecEnum.name());
    }

    public static final OnlinePopulationStatusEnum toExternal(com.amazon.mp3.amplifyqueue.model.OnlinePopulationStatusEnum onlinePopulationStatusEnum) {
        Intrinsics.checkNotNullParameter(onlinePopulationStatusEnum, "<this>");
        return OnlinePopulationStatusEnum.valueOf(onlinePopulationStatusEnum.name());
    }

    public static final com.amazon.mp3.amplifyqueue.model.OnlinePopulationStatusEnum toAmplify(OnlinePopulationStatusEnum onlinePopulationStatusEnum) {
        Intrinsics.checkNotNullParameter(onlinePopulationStatusEnum, "<this>");
        return com.amazon.mp3.amplifyqueue.model.OnlinePopulationStatusEnum.valueOf(onlinePopulationStatusEnum.name());
    }

    public static final RatingEnum toExternal(com.amazon.mp3.amplifyqueue.model.RatingEnum ratingEnum) {
        Intrinsics.checkNotNullParameter(ratingEnum, "<this>");
        return RatingEnum.valueOf(ratingEnum.name());
    }

    public static final ShuffleModeEnum toExternal(com.amazon.mp3.amplifyqueue.model.ShuffleModeEnum shuffleModeEnum) {
        Intrinsics.checkNotNullParameter(shuffleModeEnum, "<this>");
        return ShuffleModeEnum.valueOf(shuffleModeEnum.name());
    }

    public static final com.amazon.mp3.amplifyqueue.model.ShuffleModeEnum toAmplify(ShuffleModeEnum shuffleModeEnum) {
        Intrinsics.checkNotNullParameter(shuffleModeEnum, "<this>");
        return com.amazon.mp3.amplifyqueue.model.ShuffleModeEnum.valueOf(shuffleModeEnum.name());
    }

    public static final SingleActionStrategyActionTypeEnum toExternal(com.amazon.mp3.amplifyqueue.model.SingleActionStrategyActionTypeEnum singleActionStrategyActionTypeEnum) {
        Intrinsics.checkNotNullParameter(singleActionStrategyActionTypeEnum, "<this>");
        return SingleActionStrategyActionTypeEnum.valueOf(singleActionStrategyActionTypeEnum.name());
    }

    public static final QueueSeedTypeEnum toExternal(com.amazon.mp3.amplifyqueue.model.QueueSeedTypeEnum queueSeedTypeEnum) {
        Intrinsics.checkNotNullParameter(queueSeedTypeEnum, "<this>");
        return QueueSeedTypeEnum.valueOf(queueSeedTypeEnum.name());
    }

    public static final ContentEncodingEnum toExternal(com.amazon.mp3.amplifyqueue.model.ContentEncodingEnum contentEncodingEnum) {
        Intrinsics.checkNotNullParameter(contentEncodingEnum, "<this>");
        String strName = contentEncodingEnum.name();
        Enum enumValueOf = null;
        if (strName != null) {
            try {
                enumValueOf = Enum.valueOf(ContentEncodingEnum.class, strName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (ContentEncodingEnum) enumValueOf;
    }
}
