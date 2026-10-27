package com.amazon.mp3.basequeue;

import android.util.Log;
import com.amazon.digitalmusicxp.callbacks.Outcome;
import com.amazon.digitalmusicxp.clients.CloudQueueClient;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: NoOpCloudQueueClient.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b<\u0010=J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u000e\u001a\u00020\u0006H\u0016J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00042\u0006\u0010\u0015\u001a\u00020\u0010H\u0016JN\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0 0\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eH\u0016J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J*\u0010*\u001a\b\u0012\u0004\u0012\u00020)0\u00042\u0006\u0010%\u001a\u00020$2\b\u0010'\u001a\u0004\u0018\u00010&2\b\u0010(\u001a\u0004\u0018\u00010\u0010H\u0016J\u001e\u0010,\u001a\b\u0012\u0004\u0012\u00020+0\u00042\u0006\u0010%\u001a\u00020$2\u0006\u0010(\u001a\u00020\u0010H\u0016J\"\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0 0\u00042\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00100 H\u0016J\u001e\u00101\u001a\b\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u00100\u001a\u00020\u0010H\u0016JJ\u00106\u001a\b\u0012\u0004\u0012\u0002050\u00042\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\b\u00102\u001a\u0004\u0018\u00010\u00102\u0006\u00104\u001a\u0002032\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eH\u0016J\n\u00107\u001a\u0004\u0018\u00010\u0010H\u0016J\u0011\u00109\u001a\u0004\u0018\u000108H\u0016¢\u0006\u0004\b9\u0010:J\u0011\u0010;\u001a\u0004\u0018\u000108H\u0016¢\u0006\u0004\b;\u0010:¨\u0006>"}, d2 = {"Lcom/amazon/mp3/basequeue/NoOpCloudQueueClient;", "Lcom/amazon/digitalmusicxp/clients/CloudQueueClient;", "Lcom/amazon/digitalmusicxp/models/DevicePlaybackState;", "newState", "Lcom/amazon/digitalmusicxp/callbacks/Outcome;", "changeDeviceState", "Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;", "newSequenceSlice", "changeQueueSequenceSlice", "", "clearDataStore", "Lcom/amazon/digitalmusicxp/models/Queue;", "queue", "createNewQueue", "queueSequenceSlice", "createNewQueueSequenceSlice", "", "queueId", "deleteQueueById", "deleteQueueSequenceSliceById", "getDevicePlaybackState", "metricsContext", "Lcom/amazon/digitalmusicxp/models/MetricsContext;", "getMetricsContextById", "customerId", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "lastEntityReferenceId", "", "isUserInit", "Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "parentalControls", "", "Lcom/amazon/digitalmusicxp/types/GenericQueueEntity;", "getNextTracks", "getQueueById", "Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "serviceTier", "Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;", "queueSeedType", "queueInteractionPattern", "Lcom/amazon/digitalmusicxp/models/QueueCapabilitySet;", "getQueueCapabilitySet", "Lcom/amazon/digitalmusicxp/models/QueueEntityCapabilitySet;", "getQueueEntityCapabilitySet", "queueEntityIds", "Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "getQueueEntityById", "sequenceSliceName", "getQueueSequenceSliceById", "currentEntityReferenceId", "Lcom/amazon/digitalmusicxp/enums/QueueViewDirectionEnum;", "direction", "Lcom/amazon/digitalmusicxp/types/GetQueueViewResponse;", "getQueueView", "getSettingDefaultQueueEntityCapabilitiesId", "", "getSettingMaxEntitiesPerQueueSequenceSlice", "()Ljava/lang/Integer;", "getSettingQueueTtlInSeconds", "<init>", "()V", "basequeue_release"}, k = 1, mv = {1, 6, 0})
public final class NoOpCloudQueueClient implements CloudQueueClient {
    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public void clearDataStore() {
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public void deleteQueueById(String queueId) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public void deleteQueueSequenceSliceById(String queueId) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public String getSettingDefaultQueueEntityCapabilitiesId() {
        return null;
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Integer getSettingMaxEntitiesPerQueueSequenceSlice() {
        return null;
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Integer getSettingQueueTtlInSeconds() {
        return null;
    }

    public NoOpCloudQueueClient() {
        Log.w(Reflection.getOrCreateKotlinClass(NoOpCloudQueueClient.class).getSimpleName(), "you are creating an NoOp cloud queue client");
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<DevicePlaybackState> changeDeviceState(DevicePlaybackState newState) {
        Intrinsics.checkNotNullParameter(newState, "newState");
        return new Outcome.Failure(new RuntimeException());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<QueueSequenceSlice> changeQueueSequenceSlice(QueueSequenceSlice newSequenceSlice) {
        Intrinsics.checkNotNullParameter(newSequenceSlice, "newSequenceSlice");
        return new Outcome.Failure(new RuntimeException());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<Queue> createNewQueue(Queue queue) {
        Intrinsics.checkNotNullParameter(queue, "queue");
        return new Outcome.Failure(new RuntimeException());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<QueueSequenceSlice> createNewQueueSequenceSlice(QueueSequenceSlice queueSequenceSlice) {
        Intrinsics.checkNotNullParameter(queueSequenceSlice, "queueSequenceSlice");
        return new Outcome.Failure(new RuntimeException());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<DevicePlaybackState> getDevicePlaybackState(String queueId) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        return new Outcome.Failure(new RuntimeException());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<MetricsContext> getMetricsContextById(String metricsContext) {
        Intrinsics.checkNotNullParameter(metricsContext, "metricsContext");
        return new Outcome.Failure(new RuntimeException());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<List<GenericQueueEntity>> getNextTracks(String queueId, String customerId, String deviceId, String deviceType, String lastEntityReferenceId, boolean isUserInit, ParentalControlsInput parentalControls) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(lastEntityReferenceId, "lastEntityReferenceId");
        return new Outcome.Failure(new RuntimeException());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<Queue> getQueueById(String queueId) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        return new Outcome.Failure(new RuntimeException());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<QueueCapabilitySet> getQueueCapabilitySet(ServiceTierEnum serviceTier, QueueSeedTypeEnum queueSeedType, String queueInteractionPattern) {
        Intrinsics.checkNotNullParameter(serviceTier, "serviceTier");
        return new Outcome.Failure(new RuntimeException());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<QueueEntityCapabilitySet> getQueueEntityCapabilitySet(ServiceTierEnum serviceTier, String queueInteractionPattern) {
        Intrinsics.checkNotNullParameter(serviceTier, "serviceTier");
        Intrinsics.checkNotNullParameter(queueInteractionPattern, "queueInteractionPattern");
        return new Outcome.Failure(new RuntimeException());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<List<QueueEntityMetadata>> getQueueEntityById(List<String> queueEntityIds) {
        Intrinsics.checkNotNullParameter(queueEntityIds, "queueEntityIds");
        return new Outcome.Failure(new RuntimeException());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<QueueSequenceSlice> getQueueSequenceSliceById(String queueId, String sequenceSliceName) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(sequenceSliceName, "sequenceSliceName");
        return new Outcome.Failure(new RuntimeException());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<GetQueueViewResponse> getQueueView(String customerId, String queueId, String deviceId, String deviceType, String currentEntityReferenceId, QueueViewDirectionEnum direction, ParentalControlsInput parentalControls) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(direction, "direction");
        return new Outcome.Failure(new RuntimeException());
    }
}
