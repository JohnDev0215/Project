package com.amazon.digitalmusicxp.clients;

import com.amazon.digitalmusicxp.callbacks.Outcome;
import com.amazon.digitalmusicxp.inputs.PlaybackOptionsInput;
import com.amazon.digitalmusicxp.models.CustomerDeviceSettings;
import com.amazon.digitalmusicxp.models.PlayQueue;
import com.amazon.digitalmusicxp.types.BasicQueueSeed;
import com.amazon.digitalmusicxp.types.DeviceContext;
import com.amazon.digitalmusicxp.types.StartAt;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: CloudQueueAppClient.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001Jp\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0002H&¨\u0006\u0015"}, d2 = {"Lcom/amazon/digitalmusicxp/clients/CloudQueueAppClient;", "", "", "title", "customerId", "Lcom/amazon/digitalmusicxp/types/DeviceContext;", "deviceContext", "", "Lcom/amazon/digitalmusicxp/types/BasicQueueSeed;", "queueSeeds", "Lcom/amazon/digitalmusicxp/models/CustomerDeviceSettings;", "customerDeviceSettings", "Lcom/amazon/digitalmusicxp/types/StartAt;", "startAt", "targetDeviceContext", "Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;", "playbackOptions", "queueId", "Lcom/amazon/digitalmusicxp/callbacks/Outcome;", "Lcom/amazon/digitalmusicxp/models/PlayQueue;", "startCloudQueue", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public interface CloudQueueAppClient {
    Outcome<PlayQueue> startCloudQueue(String title, String customerId, DeviceContext deviceContext, List<? extends BasicQueueSeed> queueSeeds, CustomerDeviceSettings customerDeviceSettings, StartAt startAt, DeviceContext targetDeviceContext, PlaybackOptionsInput playbackOptions, String queueId);

    /* JADX INFO: compiled from: CloudQueueAppClient.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Outcome startCloudQueue$default(CloudQueueAppClient cloudQueueAppClient, String str, String str2, DeviceContext deviceContext, List list, CustomerDeviceSettings customerDeviceSettings, StartAt startAt, DeviceContext deviceContext2, PlaybackOptionsInput playbackOptionsInput, String str3, int i, Object obj) {
            if (obj == null) {
                return cloudQueueAppClient.startCloudQueue(str, str2, deviceContext, list, customerDeviceSettings, (i & 32) != 0 ? null : startAt, (i & 64) != 0 ? null : deviceContext2, (i & 128) != 0 ? null : playbackOptionsInput, (i & 256) != 0 ? null : str3);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: startCloudQueue");
        }
    }
}
