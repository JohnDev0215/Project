package com.amazon.mp3.basequeue;

import com.amazon.digitalmusicxp.callbacks.Outcome;
import com.amazon.digitalmusicxp.clients.CloudQueueAppClient;
import com.amazon.digitalmusicxp.inputs.PlaybackOptionsInput;
import com.amazon.digitalmusicxp.models.CustomerDeviceSettings;
import com.amazon.digitalmusicxp.models.PlayQueue;
import com.amazon.digitalmusicxp.types.BasicQueueSeed;
import com.amazon.digitalmusicxp.types.DeviceContext;
import com.amazon.digitalmusicxp.types.StartAt;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoOpCloudQueueAppClient.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0015\u0010\u0016Jh\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/basequeue/NoOpCloudQueueAppClient;", "Lcom/amazon/digitalmusicxp/clients/CloudQueueAppClient;", "", "title", "customerId", "Lcom/amazon/digitalmusicxp/types/DeviceContext;", "deviceContext", "", "Lcom/amazon/digitalmusicxp/types/BasicQueueSeed;", "queueSeeds", "Lcom/amazon/digitalmusicxp/models/CustomerDeviceSettings;", "customerDeviceSettings", "Lcom/amazon/digitalmusicxp/types/StartAt;", "startAt", "targetDeviceContext", "Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;", "playbackOptions", "queueId", "Lcom/amazon/digitalmusicxp/callbacks/Outcome;", "Lcom/amazon/digitalmusicxp/models/PlayQueue;", "startCloudQueue", "<init>", "()V", "basequeue_release"}, k = 1, mv = {1, 6, 0})
public final class NoOpCloudQueueAppClient implements CloudQueueAppClient {
    @Override // com.amazon.digitalmusicxp.clients.CloudQueueAppClient
    public Outcome<PlayQueue> startCloudQueue(String title, String customerId, DeviceContext deviceContext, List<? extends BasicQueueSeed> queueSeeds, CustomerDeviceSettings customerDeviceSettings, StartAt startAt, DeviceContext targetDeviceContext, PlaybackOptionsInput playbackOptions, String queueId) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(queueSeeds, "queueSeeds");
        throw new NotImplementedError(Intrinsics.stringPlus("An operation is not implemented: ", "Not yet implemented"));
    }
}
