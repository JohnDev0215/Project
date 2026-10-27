package com.amazon.mp3.amplifyqueue.converters;

import com.amazon.digitalmusicxp.enums.QueueEntityIdTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueEntitySubtypeEnum;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.digitalmusicxp.enums.SupportedFeatureEnum;
import com.amazon.digitalmusicxp.models.CustomerDeviceSettings;
import com.amazon.digitalmusicxp.models.DevicePlaybackState;
import com.amazon.digitalmusicxp.models.MetricsContext;
import com.amazon.digitalmusicxp.models.Queue;
import com.amazon.digitalmusicxp.models.QueueCapabilitySet;
import com.amazon.digitalmusicxp.models.QueueEntityCapabilitySet;
import com.amazon.digitalmusicxp.models.QueueEntityMetadata;
import com.amazon.digitalmusicxp.models.QueueSequenceSlice;
import com.amazon.digitalmusicxp.types.EntityReference;
import com.amazon.mp3.amplifyqueue.model.AdContext;
import com.amazon.mp3.amplifyqueue.model.ClientMetricsInfo;
import com.amazon.mp3.amplifyqueue.model.ContentEncodingEnum;
import com.amazon.mp3.amplifyqueue.model.CuePoints;
import com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings;
import com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState;
import com.amazon.mp3.amplifyqueue.model.DevicePlaybackState;
import com.amazon.mp3.amplifyqueue.model.DevicePlaybackStateEnum;
import com.amazon.mp3.amplifyqueue.model.EntityReference;
import com.amazon.mp3.amplifyqueue.model.ImageMetadata;
import com.amazon.mp3.amplifyqueue.model.ImageSizeUrlTuple;
import com.amazon.mp3.amplifyqueue.model.LoopModeEnum;
import com.amazon.mp3.amplifyqueue.model.MetricsContextTuple;
import com.amazon.mp3.amplifyqueue.model.OnlinePopulationStatusEnum;
import com.amazon.mp3.amplifyqueue.model.ParentalControls;
import com.amazon.mp3.amplifyqueue.model.PlaybackAssetSource;
import com.amazon.mp3.amplifyqueue.model.Queue;
import com.amazon.mp3.amplifyqueue.model.QueueCapability;
import com.amazon.mp3.amplifyqueue.model.QueueCapabilitySetV2;
import com.amazon.mp3.amplifyqueue.model.QueueEntityCapability;
import com.amazon.mp3.amplifyqueue.model.QueueInteractionPatternEnum;
import com.amazon.mp3.amplifyqueue.model.QueueSeed;
import com.amazon.mp3.amplifyqueue.model.QueueSeedTypeEnum;
import com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice;
import com.amazon.mp3.amplifyqueue.model.RatingEnum;
import com.amazon.mp3.amplifyqueue.model.ShuffleModeEnum;
import com.amazon.mp3.amplifyqueue.model.TrackAlbum;
import com.amazon.mp3.amplifyqueue.model.TrackArtist;
import com.amplifyframework.core.model.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ModelsTransformer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000l\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0003*\u00020\u0000H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0000*\u00020\u0001H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0000*\u00020\u0003H\u0000\u001a\n\u0010\b\u001a\u00020\u0007*\u00020\u0006\u001a\n\u0010\t\u001a\u00020\u0006*\u00020\u0007\u001a\n\u0010\b\u001a\u00020\u000b*\u00020\n\u001a\n\u0010\t\u001a\u00020\n*\u00020\u000b\u001a\f\u0010\b\u001a\u00020\r*\u00020\fH\u0002\u001a\f\u0010\t\u001a\u00020\f*\u00020\rH\u0002\u001a\n\u0010\b\u001a\u00020\u000f*\u00020\u000e\u001a\n\u0010\t\u001a\u00020\u000e*\u00020\u000f\u001a\f\u0010\b\u001a\u00020\u0011*\u00020\u0010H\u0000\u001a\n\u0010\b\u001a\u00020\u0013*\u00020\u0012\u001a\n\u0010\t\u001a\u00020\u0012*\u00020\u0013\u001a\n\u0010\b\u001a\u00020\u0015*\u00020\u0014\u001a\n\u0010\t\u001a\u00020\u0014*\u00020\u0015\u001a\f\u0010\b\u001a\u00020\u0017*\u00020\u0016H\u0000\u001a\f\u0010\b\u001a\u00020\u0019*\u00020\u0018H\u0000\u001a\f\u0010\b\u001a\u00020\u0011*\u00020\u001aH\u0000\u001a\f\u0010\b\u001a\u00020\u001c*\u00020\u001bH\u0000¨\u0006\u001d"}, d2 = {"", "Lcom/amplifyframework/core/model/temporal/Temporal$DateTime;", "toDateTime", "Lcom/amplifyframework/core/model/temporal/Temporal$Timestamp;", "toTimestamp", "toMilliSeconds", "Lcom/amazon/mp3/amplifyqueue/model/CustomerDeviceSettings;", "Lcom/amazon/digitalmusicxp/models/CustomerDeviceSettings;", "toExternal", "toAmplify", "Lcom/amazon/mp3/amplifyqueue/model/DevicePlaybackState;", "Lcom/amazon/digitalmusicxp/models/DevicePlaybackState;", "Lcom/amazon/mp3/amplifyqueue/model/DeviceCurrentPlaybackState;", "Lcom/amazon/digitalmusicxp/types/DeviceCurrentPlaybackState;", "Lcom/amazon/mp3/amplifyqueue/model/Queue;", "Lcom/amazon/digitalmusicxp/models/Queue;", "Lcom/amazon/mp3/amplifyqueue/model/QueueCapabilitySetV2;", "Lcom/amazon/digitalmusicxp/models/QueueCapabilitySet;", "Lcom/amazon/mp3/amplifyqueue/model/EntityReference;", "Lcom/amazon/digitalmusicxp/types/EntityReference;", "Lcom/amazon/mp3/amplifyqueue/model/QueueSequenceSlice;", "Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;", "Lcom/amazon/mp3/amplifyqueue/model/QueueEntityMetadata;", "Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "Lcom/amazon/mp3/amplifyqueue/model/QueueEntityCapabilitySet;", "Lcom/amazon/digitalmusicxp/models/QueueEntityCapabilitySet;", "Lcom/amazon/mp3/amplifyqueue/model/QueueCapabilitySet;", "Lcom/amazon/mp3/amplifyqueue/model/MetricsContext;", "Lcom/amazon/digitalmusicxp/models/MetricsContext;", "amplifyqueue_release"}, k = 2, mv = {1, 6, 0})
public final class ModelsTransformerKt {
    public static final Temporal.DateTime toDateTime(long j) {
        return new Temporal.DateTime(new Date(j), 0);
    }

    public static final Temporal.Timestamp toTimestamp(long j) {
        return new Temporal.Timestamp(j, TimeUnit.MILLISECONDS);
    }

    public static final long toMilliSeconds(Temporal.DateTime dateTime) {
        Intrinsics.checkNotNullParameter(dateTime, "<this>");
        return dateTime.toDate().getTime();
    }

    public static final long toMilliSeconds(Temporal.Timestamp timestamp) {
        Intrinsics.checkNotNullParameter(timestamp, "<this>");
        return timestamp.getSecondsSinceEpoch() * 1000;
    }

    public static final CustomerDeviceSettings toExternal(com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings customerDeviceSettings) {
        Intrinsics.checkNotNullParameter(customerDeviceSettings, "<this>");
        String id = customerDeviceSettings.getId();
        String customerId = customerDeviceSettings.getCustomerId();
        String deviceId = customerDeviceSettings.getDeviceId();
        String deviceType = customerDeviceSettings.getDeviceType();
        String sessionId = customerDeviceSettings.getSessionId();
        String locale = customerDeviceSettings.getLocale();
        List<String> languageOfPreference = customerDeviceSettings.getLanguageOfPreference();
        String timezone = customerDeviceSettings.getTimezone();
        AdContext adContext = customerDeviceSettings.getAdContext();
        Intrinsics.checkNotNullExpressionValue(adContext, "this.adContext");
        com.amazon.digitalmusicxp.types.AdContext external = ToExternalExtensionKt.toExternal(adContext);
        ParentalControls parentalControls = customerDeviceSettings.getParentalControls();
        Intrinsics.checkNotNullExpressionValue(parentalControls, "this.parentalControls");
        com.amazon.digitalmusicxp.types.ParentalControls external2 = ToExternalExtensionKt.toExternal(parentalControls);
        List<String> supportedFeaturesEnumValues = customerDeviceSettings.getSupportedFeaturesEnumValues();
        Intrinsics.checkNotNullExpressionValue(supportedFeaturesEnumValues, "this.supportedFeaturesEnumValues");
        ArrayList arrayList = new ArrayList();
        for (String str : supportedFeaturesEnumValues) {
            Enum enumValueOf = null;
            if (str != null) {
                try {
                    enumValueOf = Enum.valueOf(SupportedFeatureEnum.class, str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            SupportedFeatureEnum supportedFeatureEnum = (SupportedFeatureEnum) enumValueOf;
            if (supportedFeatureEnum != null) {
                arrayList.add(supportedFeatureEnum);
            }
        }
        Temporal.DateTime createdAt = customerDeviceSettings.getCreatedAt();
        Intrinsics.checkNotNullExpressionValue(createdAt, "this.createdAt");
        long milliSeconds = toMilliSeconds(createdAt);
        Temporal.DateTime updatedAt = customerDeviceSettings.getUpdatedAt();
        Intrinsics.checkNotNullExpressionValue(updatedAt, "this.updatedAt");
        long milliSeconds2 = toMilliSeconds(updatedAt);
        Intrinsics.checkNotNullExpressionValue(customerId, "customerId");
        Intrinsics.checkNotNullExpressionValue(deviceId, "deviceId");
        Intrinsics.checkNotNullExpressionValue(deviceType, "deviceType");
        Intrinsics.checkNotNullExpressionValue(locale, "locale");
        Intrinsics.checkNotNullExpressionValue(timezone, "timezone");
        return new CustomerDeviceSettings(customerId, deviceId, deviceType, id, sessionId, locale, languageOfPreference, timezone, external, external2, arrayList, milliSeconds, milliSeconds2);
    }

    public static final com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings toAmplify(CustomerDeviceSettings customerDeviceSettings) {
        List<String> listEmptyList;
        Intrinsics.checkNotNullParameter(customerDeviceSettings, "<this>");
        CustomerDeviceSettings.SupportedFeaturesEnumValuesStep supportedFeaturesEnumValuesStepSupportedFeatures = com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.builder().customerId(customerDeviceSettings.getCustomerId()).deviceId(customerDeviceSettings.getDeviceId()).deviceType(customerDeviceSettings.getDeviceType()).locale(customerDeviceSettings.getLocale()).timezone(customerDeviceSettings.getTimezone()).adContext(ToAmplifyExtensionKt.toAmplify(customerDeviceSettings.getAdContext())).parentalControls(ToAmplifyExtensionKt.toAmplify(customerDeviceSettings.getParentalControls())).supportedFeatures(CollectionsKt__CollectionsKt.emptyList());
        List<SupportedFeatureEnum> supportedFeatures = customerDeviceSettings.getSupportedFeatures();
        if (supportedFeatures == null) {
            listEmptyList = null;
        } else {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(supportedFeatures, 10));
            Iterator<T> it = supportedFeatures.iterator();
            while (it.hasNext()) {
                arrayList.add(((SupportedFeatureEnum) it.next()).name());
            }
            listEmptyList = arrayList;
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings customerDeviceSettingsBuild = supportedFeaturesEnumValuesStepSupportedFeatures.supportedFeaturesEnumValues(listEmptyList).createdAt(toDateTime(customerDeviceSettings.getCreatedAt())).updatedAt(toDateTime(customerDeviceSettings.getUpdatedAt())).id(customerDeviceSettings.getId()).sessionId(customerDeviceSettings.getSessionId()).languageOfPreference(customerDeviceSettings.getLanguageOfPreference()).build();
        Intrinsics.checkNotNullExpressionValue(customerDeviceSettingsBuild, "builder()\n            .c…nce)\n            .build()");
        return customerDeviceSettingsBuild;
    }

    public static final DevicePlaybackState toExternal(com.amazon.mp3.amplifyqueue.model.DevicePlaybackState devicePlaybackState) {
        Intrinsics.checkNotNullParameter(devicePlaybackState, "<this>");
        String id = devicePlaybackState.getId();
        String customerId = devicePlaybackState.getCustomerId();
        String deviceId = devicePlaybackState.getDeviceId();
        String deviceType = devicePlaybackState.getDeviceType();
        String queueId = devicePlaybackState.getQueueId();
        String sequenceName = devicePlaybackState.getSequenceName();
        Integer sequenceVersion = devicePlaybackState.getSequenceVersion();
        DeviceCurrentPlaybackState deviceCurrentPlaybackState = devicePlaybackState.getDeviceCurrentPlaybackState();
        com.amazon.digitalmusicxp.types.DeviceCurrentPlaybackState external = deviceCurrentPlaybackState == null ? null : toExternal(deviceCurrentPlaybackState);
        LoopModeEnum loopMode = devicePlaybackState.getLoopMode();
        Intrinsics.checkNotNullExpressionValue(loopMode, "this.loopMode");
        com.amazon.digitalmusicxp.enums.LoopModeEnum external2 = EnumsTransformerKt.toExternal(loopMode);
        ShuffleModeEnum shuffleMode = devicePlaybackState.getShuffleMode();
        Intrinsics.checkNotNullExpressionValue(shuffleMode, "this.shuffleMode");
        com.amazon.digitalmusicxp.enums.ShuffleModeEnum external3 = EnumsTransformerKt.toExternal(shuffleMode);
        String djMode = devicePlaybackState.getDjMode();
        String playbackContinuationMode = devicePlaybackState.getPlaybackContinuationMode();
        DevicePlaybackStateEnum playbackState = devicePlaybackState.getPlaybackState();
        Intrinsics.checkNotNullExpressionValue(playbackState, "this.playbackState");
        com.amazon.digitalmusicxp.enums.DevicePlaybackStateEnum external4 = EnumsTransformerKt.toExternal(playbackState);
        String ipAddress = devicePlaybackState.getIpAddress();
        Temporal.DateTime createdAt = devicePlaybackState.getCreatedAt();
        Intrinsics.checkNotNullExpressionValue(createdAt, "this.createdAt");
        long milliSeconds = toMilliSeconds(createdAt);
        Temporal.DateTime updatedAt = devicePlaybackState.getUpdatedAt();
        Intrinsics.checkNotNullExpressionValue(updatedAt, "this.updatedAt");
        long milliSeconds2 = toMilliSeconds(updatedAt);
        Intrinsics.checkNotNullExpressionValue(deviceId, "deviceId");
        Intrinsics.checkNotNullExpressionValue(deviceType, "deviceType");
        Intrinsics.checkNotNullExpressionValue(queueId, "queueId");
        Intrinsics.checkNotNullExpressionValue(customerId, "customerId");
        Intrinsics.checkNotNullExpressionValue(sequenceName, "sequenceName");
        Intrinsics.checkNotNullExpressionValue(sequenceVersion, "sequenceVersion");
        return new DevicePlaybackState(deviceId, deviceType, queueId, customerId, id, sequenceName, sequenceVersion.intValue(), external, external2, external3, djMode, playbackContinuationMode, external4, ipAddress, milliSeconds, milliSeconds2);
    }

    public static final com.amazon.mp3.amplifyqueue.model.DevicePlaybackState toAmplify(DevicePlaybackState devicePlaybackState) {
        Intrinsics.checkNotNullParameter(devicePlaybackState, "<this>");
        DevicePlaybackState.BuildStep buildStepId = com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.builder().deviceId(devicePlaybackState.getDeviceId()).deviceType(devicePlaybackState.getDeviceType()).queueId(devicePlaybackState.getQueueId()).customerId(devicePlaybackState.getCustomerId()).sequenceName(devicePlaybackState.getSequenceName()).sequenceVersion(Integer.valueOf(devicePlaybackState.getSequenceVersion())).loopMode(EnumsTransformerKt.toAmplify(devicePlaybackState.getLoopMode())).shuffleMode(EnumsTransformerKt.toAmplify(devicePlaybackState.getShuffleMode())).playbackState(EnumsTransformerKt.toAmplify(devicePlaybackState.getPlaybackState())).createdAt(toDateTime(devicePlaybackState.getCreatedAt())).updatedAt(toDateTime(devicePlaybackState.getUpdatedAt())).id(devicePlaybackState.getId());
        com.amazon.digitalmusicxp.types.DeviceCurrentPlaybackState deviceCurrentPlaybackState = devicePlaybackState.getDeviceCurrentPlaybackState();
        com.amazon.mp3.amplifyqueue.model.DevicePlaybackState devicePlaybackStateBuild = buildStepId.deviceCurrentPlaybackState(deviceCurrentPlaybackState == null ? null : toAmplify(deviceCurrentPlaybackState)).djMode(devicePlaybackState.getDjMode()).playbackContinuationMode(devicePlaybackState.getPlaybackContinuationMode()).ipAddress(devicePlaybackState.getIpAddress()).build();
        Intrinsics.checkNotNullExpressionValue(devicePlaybackStateBuild, "builder()\n            .d…ess)\n            .build()");
        return devicePlaybackStateBuild;
    }

    private static final com.amazon.digitalmusicxp.types.DeviceCurrentPlaybackState toExternal(DeviceCurrentPlaybackState deviceCurrentPlaybackState) {
        String referenceId = deviceCurrentPlaybackState.getReferenceId();
        Intrinsics.checkNotNullExpressionValue(referenceId, "this.referenceId");
        String typeEnumValue = deviceCurrentPlaybackState.getTypeEnumValue();
        Enum r3 = QueueEntityIdTypeEnum.ASIN;
        if (typeEnumValue != null) {
            try {
                Enum enumValueOf = Enum.valueOf(QueueEntityIdTypeEnum.class, typeEnumValue);
                if (enumValueOf != null) {
                    r3 = enumValueOf;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        QueueEntityIdTypeEnum queueEntityIdTypeEnum = (QueueEntityIdTypeEnum) r3;
        float fDoubleValue = (float) deviceCurrentPlaybackState.getProgressInMs().doubleValue();
        Temporal.Timestamp progressTimestamp = deviceCurrentPlaybackState.getProgressTimestamp();
        Intrinsics.checkNotNullExpressionValue(progressTimestamp, "this.progressTimestamp");
        long milliSeconds = toMilliSeconds(progressTimestamp);
        String title = deviceCurrentPlaybackState.getTitle();
        String subTitle = deviceCurrentPlaybackState.getSubTitle();
        ImageMetadata imageMetadata = deviceCurrentPlaybackState.getImageMetadata();
        return new com.amazon.digitalmusicxp.types.DeviceCurrentPlaybackState(referenceId, queueEntityIdTypeEnum, fDoubleValue, milliSeconds, title, subTitle, imageMetadata == null ? null : ToExternalExtensionKt.toExternal(imageMetadata));
    }

    private static final DeviceCurrentPlaybackState toAmplify(com.amazon.digitalmusicxp.types.DeviceCurrentPlaybackState deviceCurrentPlaybackState) {
        DeviceCurrentPlaybackState.BuildStep buildStepSubTitle = DeviceCurrentPlaybackState.builder().referenceId(deviceCurrentPlaybackState.getReferenceId()).type((com.amazon.mp3.amplifyqueue.model.QueueEntityIdTypeEnum) AmplifyModelConverter.getDummyEnumValue$default(AmplifyModelConverter.INSTANCE, com.amazon.mp3.amplifyqueue.model.QueueEntityIdTypeEnum.class, null, 2, null)).progressInMs(Double.valueOf(deviceCurrentPlaybackState.getProgressInMs())).progressTimestamp(toTimestamp(deviceCurrentPlaybackState.getProgressTimestamp())).typeEnumValue(deviceCurrentPlaybackState.getType().name()).title(deviceCurrentPlaybackState.getTitle()).subTitle(deviceCurrentPlaybackState.getSubTitle());
        com.amazon.digitalmusicxp.types.ImageMetadata imageMetadata = deviceCurrentPlaybackState.getImageMetadata();
        DeviceCurrentPlaybackState deviceCurrentPlaybackStateBuild = buildStepSubTitle.imageMetadata(imageMetadata != null ? ToAmplifyExtensionKt.toAmplify(imageMetadata) : null).build();
        Intrinsics.checkNotNullExpressionValue(deviceCurrentPlaybackStateBuild, "builder()\n            .r…y())\n            .build()");
        return deviceCurrentPlaybackStateBuild;
    }

    public static final Queue toExternal(com.amazon.mp3.amplifyqueue.model.Queue queue) {
        ArrayList arrayList;
        ArrayList arrayList2;
        Intrinsics.checkNotNullParameter(queue, "<this>");
        String id = queue.getId();
        String customerId = queue.getCustomerId();
        String serviceTierEnumValue = queue.getServiceTierEnumValue();
        Enum r5 = ServiceTierEnum.LIBRARY;
        if (serviceTierEnumValue != null) {
            try {
                Enum enumValueOf = Enum.valueOf(ServiceTierEnum.class, serviceTierEnumValue);
                if (enumValueOf != null) {
                    r5 = enumValueOf;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ServiceTierEnum serviceTierEnum = (ServiceTierEnum) r5;
        String title = queue.getTitle();
        List<ImageSizeUrlTuple> images = queue.getImages();
        if (images == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(images, 10));
            Iterator<T> it = images.iterator();
            while (it.hasNext()) {
                arrayList.add(ToExternalExtensionKt.toExternal((ImageSizeUrlTuple) it.next()));
            }
        }
        Integer queueTtlInSeconds = queue.getQueueTtlInSeconds();
        List<QueueSeed> queueSeeds = queue.getQueueSeeds();
        if (queueSeeds == null) {
            arrayList2 = null;
        } else {
            ArrayList arrayList3 = new ArrayList();
            Iterator<T> it2 = queueSeeds.iterator();
            while (it2.hasNext()) {
                com.amazon.digitalmusicxp.types.QueueSeed external = ToExternalExtensionKt.toExternal((QueueSeed) it2.next());
                if (external != null) {
                    arrayList3.add(external);
                }
            }
            arrayList2 = arrayList3;
        }
        String queueCapabilitySetId = queue.getQueueCapabilitySetId();
        String thingShadowName = queue.getThingShadowName();
        OnlinePopulationStatusEnum onlinePopulationStatus = queue.getOnlinePopulationStatus();
        com.amazon.digitalmusicxp.enums.OnlinePopulationStatusEnum external2 = onlinePopulationStatus != null ? EnumsTransformerKt.toExternal(onlinePopulationStatus) : null;
        Integer maxEntitiesPerQueueSequenceSlice = queue.getMaxEntitiesPerQueueSequenceSlice();
        Temporal.DateTime createdAt = queue.getCreatedAt();
        Intrinsics.checkNotNullExpressionValue(createdAt, "this.createdAt");
        long milliSeconds = toMilliSeconds(createdAt);
        Temporal.DateTime updatedAt = queue.getUpdatedAt();
        Intrinsics.checkNotNullExpressionValue(updatedAt, "this.updatedAt");
        long milliSeconds2 = toMilliSeconds(updatedAt);
        Intrinsics.checkNotNullExpressionValue(customerId, "customerId");
        Intrinsics.checkNotNullExpressionValue(id, "id");
        Intrinsics.checkNotNullExpressionValue(queueTtlInSeconds, "queueTtlInSeconds");
        int iIntValue = queueTtlInSeconds.intValue();
        Intrinsics.checkNotNullExpressionValue(queueCapabilitySetId, "queueCapabilitySetId");
        Intrinsics.checkNotNullExpressionValue(maxEntitiesPerQueueSequenceSlice, "maxEntitiesPerQueueSequenceSlice");
        return new Queue(customerId, id, serviceTierEnum, title, arrayList, iIntValue, arrayList2, queueCapabilitySetId, thingShadowName, external2, maxEntitiesPerQueueSequenceSlice.intValue(), milliSeconds, milliSeconds2);
    }

    public static final com.amazon.mp3.amplifyqueue.model.Queue toAmplify(Queue queue) {
        List<QueueSeed> arrayList;
        Intrinsics.checkNotNullParameter(queue, "<this>");
        Queue.QueueSeedsStep queueSeedsStepQueueTtlInSeconds = com.amazon.mp3.amplifyqueue.model.Queue.builder().customerId(queue.getCustomerId()).serviceTier((com.amazon.mp3.amplifyqueue.model.ServiceTierEnum) AmplifyModelConverter.INSTANCE.getDummyEnumValue(com.amazon.mp3.amplifyqueue.model.ServiceTierEnum.class, queue.getServiceTier().name())).queueTtlInSeconds(Integer.valueOf(queue.getQueueTtlInSeconds()));
        List<com.amazon.digitalmusicxp.types.QueueSeed> queueSeeds = queue.getQueueSeeds();
        ArrayList arrayList2 = null;
        if (queueSeeds == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList<>(CollectionsKt__IterablesKt.collectionSizeOrDefault(queueSeeds, 10));
            Iterator<T> it = queueSeeds.iterator();
            while (it.hasNext()) {
                arrayList.add(ToAmplifyExtensionKt.toAmplify((com.amazon.digitalmusicxp.types.QueueSeed) it.next()));
            }
        }
        if (arrayList == null) {
            arrayList = CollectionsKt__CollectionsKt.emptyList();
        }
        Queue.BuildStep buildStepThingShadowName = queueSeedsStepQueueTtlInSeconds.queueSeeds(arrayList).queueCapabilitySetId(queue.getQueueCapabilitySetId()).maxEntitiesPerQueueSequenceSlice(Integer.valueOf(queue.getMaxEntitiesPerQueueSequenceSlice())).createdAt(toDateTime(queue.getCreatedAt())).updatedAt(toDateTime(queue.getUpdatedAt())).id(queue.getId()).thingShadowName(queue.getThingShadowName());
        com.amazon.digitalmusicxp.enums.OnlinePopulationStatusEnum onlinePopulationStatus = queue.getOnlinePopulationStatus();
        Queue.BuildStep buildStepTitle = buildStepThingShadowName.onlinePopulationStatus(onlinePopulationStatus == null ? null : EnumsTransformerKt.toAmplify(onlinePopulationStatus)).serviceTierEnumValue(queue.getServiceTier().name()).title(queue.getTitle());
        List<com.amazon.digitalmusicxp.types.ImageSizeUrlTuple> images = queue.getImages();
        if (images != null) {
            ArrayList arrayList3 = new ArrayList();
            for (com.amazon.digitalmusicxp.types.ImageSizeUrlTuple imageSizeUrlTuple : images) {
                ImageSizeUrlTuple amplify = imageSizeUrlTuple == null ? null : ToAmplifyExtensionKt.toAmplify(imageSizeUrlTuple);
                if (amplify != null) {
                    arrayList3.add(amplify);
                }
            }
            arrayList2 = arrayList3;
        }
        com.amazon.mp3.amplifyqueue.model.Queue queueBuild = buildStepTitle.images(arrayList2).build();
        Intrinsics.checkNotNullExpressionValue(queueBuild, "builder()\n            .c…) })\n            .build()");
        return queueBuild;
    }

    public static final QueueCapabilitySet toExternal(QueueCapabilitySetV2 queueCapabilitySetV2) {
        Intrinsics.checkNotNullParameter(queueCapabilitySetV2, "<this>");
        String id = queueCapabilitySetV2.getId();
        Intrinsics.checkNotNullExpressionValue(id, "this.id");
        QueueCapability capabilities = queueCapabilitySetV2.getCapabilities();
        Intrinsics.checkNotNullExpressionValue(capabilities, "this.capabilities");
        com.amazon.digitalmusicxp.types.QueueCapability external = ToExternalExtensionKt.toExternal(capabilities);
        Temporal.DateTime createdAt = queueCapabilitySetV2.getCreatedAt();
        Intrinsics.checkNotNullExpressionValue(createdAt, "this.createdAt");
        long milliSeconds = toMilliSeconds(createdAt);
        Temporal.DateTime updatedAt = queueCapabilitySetV2.getUpdatedAt();
        Intrinsics.checkNotNullExpressionValue(updatedAt, "this.updatedAt");
        return new QueueCapabilitySet(id, external, milliSeconds, toMilliSeconds(updatedAt), queueCapabilitySetV2.getQueueInteractionPattern(), null, null, 96, null);
    }

    public static final EntityReference toExternal(com.amazon.mp3.amplifyqueue.model.EntityReference entityReference) {
        Intrinsics.checkNotNullParameter(entityReference, "<this>");
        String entityReferenceId = entityReference.getEntityReferenceId();
        Intrinsics.checkNotNullExpressionValue(entityReferenceId, "this.entityReferenceId");
        String identifier = entityReference.getIdentifier();
        Intrinsics.checkNotNullExpressionValue(identifier, "this.identifier");
        String identifierTypeEnumValue = entityReference.getIdentifierTypeEnumValue();
        Enum r4 = QueueEntityIdTypeEnum.ASIN;
        if (identifierTypeEnumValue != null) {
            try {
                Enum enumValueOf = Enum.valueOf(QueueEntityIdTypeEnum.class, identifierTypeEnumValue);
                if (enumValueOf != null) {
                    r4 = enumValueOf;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        QueueEntityIdTypeEnum queueEntityIdTypeEnum = (QueueEntityIdTypeEnum) r4;
        String capabilitySetId = entityReference.getCapabilitySetId();
        Intrinsics.checkNotNullExpressionValue(capabilitySetId, "this.capabilitySetId");
        String metricsContext = entityReference.getMetricsContext();
        Intrinsics.checkNotNullExpressionValue(metricsContext, "this.metricsContext");
        ClientMetricsInfo clientMetricsInfo = entityReference.getClientMetricsInfo();
        return new EntityReference(entityReferenceId, identifier, queueEntityIdTypeEnum, capabilitySetId, metricsContext, clientMetricsInfo == null ? null : InputsTransformerKt.toExternal(clientMetricsInfo));
    }

    public static final com.amazon.mp3.amplifyqueue.model.EntityReference toAmplify(EntityReference entityReference) {
        Intrinsics.checkNotNullParameter(entityReference, "<this>");
        EntityReference.BuildStep buildStepMetricsContext = com.amazon.mp3.amplifyqueue.model.EntityReference.builder().entityReferenceId(entityReference.getEntityReferenceId()).identifier(entityReference.getIdentifier()).identifierType((com.amazon.mp3.amplifyqueue.model.QueueEntityIdTypeEnum) AmplifyModelConverter.getDummyEnumValue$default(AmplifyModelConverter.INSTANCE, com.amazon.mp3.amplifyqueue.model.QueueEntityIdTypeEnum.class, null, 2, null)).capabilitySetId(entityReference.getCapabilitySetId()).metricsContext(entityReference.getMetricsContext());
        com.amazon.digitalmusicxp.types.ClientMetricsInfo clientMetricsInfo = entityReference.getClientMetricsInfo();
        com.amazon.mp3.amplifyqueue.model.EntityReference entityReferenceBuild = buildStepMetricsContext.clientMetricsInfo(clientMetricsInfo != null ? InputsTransformerKt.toAmplify(clientMetricsInfo) : null).identifierTypeEnumValue(entityReference.getIdentifierType().name()).build();
        Intrinsics.checkNotNullExpressionValue(entityReferenceBuild, "builder()\n            .e…ame)\n            .build()");
        return entityReferenceBuild;
    }

    public static final QueueSequenceSlice toExternal(com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice queueSequenceSlice) {
        Intrinsics.checkNotNullParameter(queueSequenceSlice, "<this>");
        String id = queueSequenceSlice.getId();
        String queueId = queueSequenceSlice.getQueueId();
        String customerId = queueSequenceSlice.getCustomerId();
        String sequenceName = queueSequenceSlice.getSequenceName();
        Integer sequenceVersion = queueSequenceSlice.getSequenceVersion();
        Integer sliceOrdinal = queueSequenceSlice.getSliceOrdinal();
        Boolean isLastSlice = queueSequenceSlice.getIsLastSlice();
        List<com.amazon.mp3.amplifyqueue.model.EntityReference> entityReferences = queueSequenceSlice.getEntityReferences();
        Intrinsics.checkNotNullExpressionValue(entityReferences, "this.entityReferences");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entityReferences, 10));
        Iterator<T> it = entityReferences.iterator();
        while (it.hasNext()) {
            arrayList.add(toExternal((com.amazon.mp3.amplifyqueue.model.EntityReference) it.next()));
        }
        Temporal.DateTime createdAt = queueSequenceSlice.getCreatedAt();
        Intrinsics.checkNotNullExpressionValue(createdAt, "this.createdAt");
        long milliSeconds = toMilliSeconds(createdAt);
        Temporal.DateTime updatedAt = queueSequenceSlice.getUpdatedAt();
        Intrinsics.checkNotNullExpressionValue(updatedAt, "this.updatedAt");
        long milliSeconds2 = toMilliSeconds(updatedAt);
        Intrinsics.checkNotNullExpressionValue(queueId, "queueId");
        Intrinsics.checkNotNullExpressionValue(customerId, "customerId");
        Intrinsics.checkNotNullExpressionValue(sequenceName, "sequenceName");
        Intrinsics.checkNotNullExpressionValue(sequenceVersion, "sequenceVersion");
        int iIntValue = sequenceVersion.intValue();
        Intrinsics.checkNotNullExpressionValue(sliceOrdinal, "sliceOrdinal");
        int iIntValue2 = sliceOrdinal.intValue();
        Intrinsics.checkNotNullExpressionValue(isLastSlice, "isLastSlice");
        return new QueueSequenceSlice(queueId, customerId, id, sequenceName, iIntValue, iIntValue2, isLastSlice.booleanValue(), arrayList, milliSeconds, milliSeconds2);
    }

    public static final com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice toAmplify(QueueSequenceSlice queueSequenceSlice) {
        Intrinsics.checkNotNullParameter(queueSequenceSlice, "<this>");
        QueueSequenceSlice.EntityReferencesStep entityReferencesStepIsLastSlice = com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.builder().queueId(queueSequenceSlice.getQueueId()).customerId(queueSequenceSlice.getCustomerId()).sequenceName(queueSequenceSlice.getSequenceName()).sequenceVersion(Integer.valueOf(queueSequenceSlice.getSequenceVersion())).sliceOrdinal(Integer.valueOf(queueSequenceSlice.getSliceOrdinal())).isLastSlice(Boolean.valueOf(queueSequenceSlice.getIsLastSlice()));
        List<com.amazon.digitalmusicxp.types.EntityReference> entityReferences = queueSequenceSlice.getEntityReferences();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entityReferences, 10));
        Iterator<T> it = entityReferences.iterator();
        while (it.hasNext()) {
            arrayList.add(toAmplify((com.amazon.digitalmusicxp.types.EntityReference) it.next()));
        }
        com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice queueSequenceSliceBuild = entityReferencesStepIsLastSlice.entityReferences(arrayList).createdAt(toDateTime(queueSequenceSlice.getCreatedAt())).updatedAt(toDateTime(queueSequenceSlice.getUpdatedAt())).id(queueSequenceSlice.getId()).build();
        Intrinsics.checkNotNullExpressionValue(queueSequenceSliceBuild, "builder()\n            .q….id)\n            .build()");
        return queueSequenceSliceBuild;
    }

    public static final QueueEntityMetadata toExternal(com.amazon.mp3.amplifyqueue.model.QueueEntityMetadata queueEntityMetadata) {
        Enum enumValueOf;
        ArrayList arrayList;
        ArrayList arrayList2;
        Intrinsics.checkNotNullParameter(queueEntityMetadata, "<this>");
        String id = queueEntityMetadata.getId();
        String customerId = queueEntityMetadata.getCustomerId();
        String queueId = queueEntityMetadata.getQueueId();
        String identifier = queueEntityMetadata.getIdentifier();
        String identifierTypeEnumValue = queueEntityMetadata.getIdentifierTypeEnumValue();
        Enum r7 = QueueEntityIdTypeEnum.ASIN;
        if (identifierTypeEnumValue != null) {
            try {
                Enum enumValueOf2 = Enum.valueOf(QueueEntityIdTypeEnum.class, identifierTypeEnumValue);
                if (enumValueOf2 != null) {
                    r7 = enumValueOf2;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        QueueEntityIdTypeEnum queueEntityIdTypeEnum = (QueueEntityIdTypeEnum) r7;
        String queueEntitySubtypeEnumValue = queueEntityMetadata.getQueueEntitySubtypeEnumValue();
        if (queueEntitySubtypeEnumValue == null) {
            enumValueOf = null;
        } else {
            try {
                enumValueOf = Enum.valueOf(QueueEntitySubtypeEnum.class, queueEntitySubtypeEnumValue);
            } catch (Exception e2) {
                e2.printStackTrace();
                enumValueOf = null;
            }
        }
        QueueEntitySubtypeEnum queueEntitySubtypeEnum = (QueueEntitySubtypeEnum) enumValueOf;
        Double durationInMs = queueEntityMetadata.getDurationInMs();
        Float fValueOf = durationInMs == null ? null : Float.valueOf((float) durationInMs.doubleValue());
        String title = queueEntityMetadata.getTitle();
        String subTitle = queueEntityMetadata.getSubTitle();
        ImageMetadata imageMetadata = queueEntityMetadata.getImageMetadata();
        com.amazon.digitalmusicxp.types.ImageMetadata external = imageMetadata == null ? null : ToExternalExtensionKt.toExternal(imageMetadata);
        ParentalControls parentalControls = queueEntityMetadata.getParentalControls();
        com.amazon.digitalmusicxp.types.ParentalControls external2 = parentalControls == null ? null : ToExternalExtensionKt.toExternal(parentalControls);
        TrackAlbum album = queueEntityMetadata.getAlbum();
        com.amazon.digitalmusicxp.types.TrackAlbum external3 = album == null ? null : ToExternalExtensionKt.toExternal(album);
        String concatenatedArtistName = queueEntityMetadata.getConcatenatedArtistName();
        List<TrackArtist> artists = queueEntityMetadata.getArtists();
        if (artists == null) {
            arrayList = null;
        } else {
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(artists, 10));
            Iterator<T> it = artists.iterator();
            while (it.hasNext()) {
                arrayList3.add(ToExternalExtensionKt.toExternal((TrackArtist) it.next()));
            }
            arrayList = arrayList3;
        }
        Boolean hasLyrics = queueEntityMetadata.getHasLyrics();
        CuePoints cuePoints = queueEntityMetadata.getCuePoints();
        com.amazon.digitalmusicxp.types.CuePoints external4 = cuePoints == null ? null : ToExternalExtensionKt.toExternal(cuePoints);
        PlaybackAssetSource playbackAssetSource = queueEntityMetadata.getPlaybackAssetSource();
        com.amazon.digitalmusicxp.types.PlaybackAssetSource external5 = playbackAssetSource == null ? null : ToExternalExtensionKt.toExternal(playbackAssetSource);
        RatingEnum rating = queueEntityMetadata.getRating();
        com.amazon.digitalmusicxp.enums.RatingEnum external6 = rating == null ? null : EnumsTransformerKt.toExternal(rating);
        List<ContentEncodingEnum> availableContentEncodings = queueEntityMetadata.getAvailableContentEncodings();
        if (availableContentEncodings == null) {
            arrayList2 = null;
        } else {
            ArrayList arrayList4 = new ArrayList();
            Iterator it2 = availableContentEncodings.iterator();
            while (it2.hasNext()) {
                Iterator it3 = it2;
                com.amazon.digitalmusicxp.enums.ContentEncodingEnum external7 = EnumsTransformerKt.toExternal((ContentEncodingEnum) it2.next());
                if (external7 != null) {
                    arrayList4.add(external7);
                }
                it2 = it3;
            }
            arrayList2 = arrayList4;
        }
        String playableAsin = queueEntityMetadata.getPlayableAsin();
        Temporal.DateTime createdAt = queueEntityMetadata.getCreatedAt();
        Intrinsics.checkNotNullExpressionValue(createdAt, "this.createdAt");
        long milliSeconds = toMilliSeconds(createdAt);
        Temporal.DateTime updatedAt = queueEntityMetadata.getUpdatedAt();
        Intrinsics.checkNotNullExpressionValue(updatedAt, "this.updatedAt");
        long milliSeconds2 = toMilliSeconds(updatedAt);
        Intrinsics.checkNotNullExpressionValue(customerId, "customerId");
        Intrinsics.checkNotNullExpressionValue(queueId, "queueId");
        Intrinsics.checkNotNullExpressionValue(id, "id");
        Intrinsics.checkNotNullExpressionValue(identifier, "identifier");
        return new QueueEntityMetadata(customerId, queueId, id, identifier, queueEntityIdTypeEnum, queueEntitySubtypeEnum, fValueOf, title, subTitle, external, external2, external3, concatenatedArtistName, arrayList, hasLyrics, external4, external5, external6, arrayList2, playableAsin, milliSeconds, milliSeconds2);
    }

    public static final QueueEntityCapabilitySet toExternal(com.amazon.mp3.amplifyqueue.model.QueueEntityCapabilitySet queueEntityCapabilitySet) {
        Intrinsics.checkNotNullParameter(queueEntityCapabilitySet, "<this>");
        String id = queueEntityCapabilitySet.getId();
        Intrinsics.checkNotNullExpressionValue(id, "this.id");
        QueueEntityCapability capabilities = queueEntityCapabilitySet.getCapabilities();
        Intrinsics.checkNotNullExpressionValue(capabilities, "this.capabilities");
        com.amazon.digitalmusicxp.types.QueueEntityCapability external = ToExternalExtensionKt.toExternal(capabilities);
        Temporal.DateTime createdAt = queueEntityCapabilitySet.getCreatedAt();
        Intrinsics.checkNotNullExpressionValue(createdAt, "this.createdAt");
        long milliSeconds = toMilliSeconds(createdAt);
        Temporal.DateTime updatedAt = queueEntityCapabilitySet.getUpdatedAt();
        Intrinsics.checkNotNullExpressionValue(updatedAt, "this.updatedAt");
        return new QueueEntityCapabilitySet(id, external, milliSeconds, toMilliSeconds(updatedAt));
    }

    public static final QueueCapabilitySet toExternal(com.amazon.mp3.amplifyqueue.model.QueueCapabilitySet queueCapabilitySet) {
        Enum enumValueOf;
        Intrinsics.checkNotNullParameter(queueCapabilitySet, "<this>");
        String id = queueCapabilitySet.getId();
        Intrinsics.checkNotNullExpressionValue(id, "this.id");
        QueueCapability capabilities = queueCapabilitySet.getCapabilities();
        Intrinsics.checkNotNullExpressionValue(capabilities, "this.capabilities");
        com.amazon.digitalmusicxp.types.QueueCapability external = ToExternalExtensionKt.toExternal(capabilities);
        Temporal.DateTime createdAt = queueCapabilitySet.getCreatedAt();
        Intrinsics.checkNotNullExpressionValue(createdAt, "this.createdAt");
        long milliSeconds = toMilliSeconds(createdAt);
        Temporal.DateTime updatedAt = queueCapabilitySet.getUpdatedAt();
        Intrinsics.checkNotNullExpressionValue(updatedAt, "this.updatedAt");
        long milliSeconds2 = toMilliSeconds(updatedAt);
        QueueInteractionPatternEnum queueInteractionPattern = queueCapabilitySet.getQueueInteractionPattern();
        String strName = queueInteractionPattern == null ? null : queueInteractionPattern.name();
        com.amazon.mp3.amplifyqueue.model.ServiceTierEnum serviceTier = queueCapabilitySet.getServiceTier();
        String strName2 = serviceTier == null ? null : serviceTier.name();
        if (strName2 == null) {
            enumValueOf = null;
        } else {
            try {
                enumValueOf = Enum.valueOf(ServiceTierEnum.class, strName2);
            } catch (Exception e) {
                e.printStackTrace();
                enumValueOf = null;
            }
        }
        ServiceTierEnum serviceTierEnum = (ServiceTierEnum) enumValueOf;
        QueueSeedTypeEnum queueSeedType = queueCapabilitySet.getQueueSeedType();
        return new QueueCapabilitySet(id, external, milliSeconds, milliSeconds2, strName, serviceTierEnum, queueSeedType == null ? null : EnumsTransformerKt.toExternal(queueSeedType));
    }

    public static final MetricsContext toExternal(com.amazon.mp3.amplifyqueue.model.MetricsContext metricsContext) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(metricsContext, "<this>");
        String id = metricsContext.getId();
        String customerId = metricsContext.getCustomerId();
        String queueId = metricsContext.getQueueId();
        List<MetricsContextTuple> metricsContext2 = metricsContext.getMetricsContext();
        if (metricsContext2 == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(metricsContext2, 10));
            Iterator<T> it = metricsContext2.iterator();
            while (it.hasNext()) {
                arrayList2.add(InputsTransformerKt.toExternal((MetricsContextTuple) it.next()));
            }
            arrayList = arrayList2;
        }
        Temporal.DateTime createdAt = metricsContext.getCreatedAt();
        Intrinsics.checkNotNullExpressionValue(createdAt, "this.createdAt");
        long milliSeconds = toMilliSeconds(createdAt);
        Temporal.DateTime updatedAt = metricsContext.getUpdatedAt();
        Intrinsics.checkNotNullExpressionValue(updatedAt, "this.updatedAt");
        long milliSeconds2 = toMilliSeconds(updatedAt);
        Intrinsics.checkNotNullExpressionValue(customerId, "customerId");
        Intrinsics.checkNotNullExpressionValue(queueId, "queueId");
        Intrinsics.checkNotNullExpressionValue(id, "id");
        return new MetricsContext(customerId, queueId, id, arrayList, milliSeconds, milliSeconds2);
    }
}
