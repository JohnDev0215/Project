package com.amazon.digitalmusicxp.clients;

import com.amazon.digitalmusicxp.callbacks.Outcome;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueViewDirectionEnum;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.digitalmusicxp.inputs.ParentalControlsInput;
import com.amazon.digitalmusicxp.models.DevicePlaybackState;
import com.amazon.digitalmusicxp.models.MetricsContext;
import com.amazon.digitalmusicxp.models.Queue;
import com.amazon.digitalmusicxp.models.QueueCapabilitySet;
import com.amazon.digitalmusicxp.models.QueueEntityCapabilitySet;
import com.amazon.digitalmusicxp.models.QueueEntityMetadata;
import com.amazon.digitalmusicxp.models.QueueSequenceSlice;
import com.amazon.digitalmusicxp.types.GenericQueueEntity;
import com.amazon.digitalmusicxp.types.GetQueueViewResponse;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: CloudQueueClient.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H&J\"\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\n0\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\nH&J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0011\u0010\u0011\u001a\u0004\u0018\u00010\u0010H&¢\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0010H&¢\u0006\u0004\b\u0013\u0010\u0012J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0002H&J*\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00042\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002H&J\u001e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00042\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0002H&J\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u00042\u0006\u0010\u001e\u001a\u00020\u0002H&J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00042\u0006\u0010!\u001a\u00020\u000eH&J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010#\u001a\u00020\bH&J\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010%\u001a\u00020\u0005H&J\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010'\u001a\u00020\bH&J\u0010\u0010*\u001a\u00020)2\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010+\u001a\u00020)2\u0006\u0010\u0003\u001a\u00020\u0002H&J\b\u0010,\u001a\u00020)H&JN\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\n0\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u00020\u00022\u0006\u00100\u001a\u00020\u00022\u0006\u00102\u001a\u0002012\b\u00104\u001a\u0004\u0018\u000103H&JJ\u0010;\u001a\b\u0012\u0004\u0012\u00020:0\u00042\u0006\u0010-\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u00020\u00022\b\u00107\u001a\u0004\u0018\u00010\u00022\u0006\u00109\u001a\u0002082\b\u00104\u001a\u0004\u0018\u000103H&¨\u0006<"}, d2 = {"Lcom/amazon/digitalmusicxp/clients/CloudQueueClient;", "", "", "queueId", "Lcom/amazon/digitalmusicxp/callbacks/Outcome;", "Lcom/amazon/digitalmusicxp/models/Queue;", "getQueueById", "sequenceSliceName", "Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;", "getQueueSequenceSliceById", "", "queueEntityIds", "Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "getQueueEntityById", "Lcom/amazon/digitalmusicxp/models/DevicePlaybackState;", "getDevicePlaybackState", "", "getSettingMaxEntitiesPerQueueSequenceSlice", "()Ljava/lang/Integer;", "getSettingQueueTtlInSeconds", "getSettingDefaultQueueEntityCapabilitiesId", "Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "serviceTier", "Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;", "queueSeedType", "queueInteractionPattern", "Lcom/amazon/digitalmusicxp/models/QueueCapabilitySet;", "getQueueCapabilitySet", "Lcom/amazon/digitalmusicxp/models/QueueEntityCapabilitySet;", "getQueueEntityCapabilitySet", "metricsContextId", "Lcom/amazon/digitalmusicxp/models/MetricsContext;", "getMetricsContextById", "newState", "changeDeviceState", "newSequenceSlice", "changeQueueSequenceSlice", "queue", "createNewQueue", "queueSequenceSlice", "createNewQueueSequenceSlice", "", "deleteQueueById", "deleteQueueSequenceSliceById", "clearDataStore", "customerId", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "lastEntityReferenceId", "", "isUserInit", "Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "parentalControls", "Lcom/amazon/digitalmusicxp/types/GenericQueueEntity;", "getNextTracks", "currentEntityReferenceId", "Lcom/amazon/digitalmusicxp/enums/QueueViewDirectionEnum;", "direction", "Lcom/amazon/digitalmusicxp/types/GetQueueViewResponse;", "getQueueView", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public interface CloudQueueClient {
    Outcome<DevicePlaybackState> changeDeviceState(DevicePlaybackState newState);

    Outcome<QueueSequenceSlice> changeQueueSequenceSlice(QueueSequenceSlice newSequenceSlice);

    void clearDataStore();

    Outcome<Queue> createNewQueue(Queue queue);

    Outcome<QueueSequenceSlice> createNewQueueSequenceSlice(QueueSequenceSlice queueSequenceSlice);

    void deleteQueueById(String queueId);

    void deleteQueueSequenceSliceById(String queueId);

    Outcome<DevicePlaybackState> getDevicePlaybackState(String queueId);

    Outcome<MetricsContext> getMetricsContextById(String metricsContextId);

    Outcome<List<GenericQueueEntity>> getNextTracks(String queueId, String customerId, String deviceId, String deviceType, String lastEntityReferenceId, boolean isUserInit, ParentalControlsInput parentalControls);

    Outcome<Queue> getQueueById(String queueId);

    Outcome<QueueCapabilitySet> getQueueCapabilitySet(ServiceTierEnum serviceTier, QueueSeedTypeEnum queueSeedType, String queueInteractionPattern);

    Outcome<List<QueueEntityMetadata>> getQueueEntityById(List<String> queueEntityIds);

    Outcome<QueueEntityCapabilitySet> getQueueEntityCapabilitySet(ServiceTierEnum serviceTier, String queueInteractionPattern);

    Outcome<QueueSequenceSlice> getQueueSequenceSliceById(String queueId, String sequenceSliceName);

    Outcome<GetQueueViewResponse> getQueueView(String customerId, String queueId, String deviceId, String deviceType, String currentEntityReferenceId, QueueViewDirectionEnum direction, ParentalControlsInput parentalControls);

    String getSettingDefaultQueueEntityCapabilitiesId();

    Integer getSettingMaxEntitiesPerQueueSequenceSlice();

    Integer getSettingQueueTtlInSeconds();
}
