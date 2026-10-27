package com.amazon.mp3.amplifyqueue;

import android.content.Context;
import androidx.annotation.WorkerThread;
import com.amazon.digitalmusicxp.CustomerInfoProvider;
import com.amazon.digitalmusicxp.callbacks.Outcome;
import com.amazon.digitalmusicxp.clients.CloudQueueAppClient;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueSequenceSliceName;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.digitalmusicxp.inputs.PlaybackOptionsInput;
import com.amazon.digitalmusicxp.inputs.QueueSeedContextInput;
import com.amazon.digitalmusicxp.models.CustomerDeviceSettings;
import com.amazon.digitalmusicxp.models.MetricsContext;
import com.amazon.digitalmusicxp.models.PlayQueue;
import com.amazon.digitalmusicxp.models.Queue;
import com.amazon.digitalmusicxp.models.QueueSequenceSlice;
import com.amazon.digitalmusicxp.types.BasicQueueSeed;
import com.amazon.digitalmusicxp.types.DeviceContext;
import com.amazon.digitalmusicxp.types.EntityReference;
import com.amazon.digitalmusicxp.types.GenericQueueEntity;
import com.amazon.digitalmusicxp.types.InitiateQueueRequest;
import com.amazon.digitalmusicxp.types.InitiateQueueResponse;
import com.amazon.digitalmusicxp.types.ParentalControls;
import com.amazon.digitalmusicxp.types.QueueMetadata;
import com.amazon.digitalmusicxp.types.StartAt;
import com.amazon.mp3.amplifyqueue.utils.MetricsUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: AmplifyAppClient.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \"2\u00020\u0001:\u0001\"B1\b\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\u0006\u0010\u001d\u001a\u00020\u0002\u0012\u0006\u0010\u001e\u001a\u00020\u0002\u0012\u0006\u0010\u001f\u001a\u00020\u0002¢\u0006\u0004\b \u0010!Jh\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0017R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006#"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/AmplifyAppClient;", "Lcom/amazon/digitalmusicxp/clients/CloudQueueAppClient;", "", "title", "customerId", "Lcom/amazon/digitalmusicxp/types/DeviceContext;", "deviceContext", "", "Lcom/amazon/digitalmusicxp/types/BasicQueueSeed;", "queueSeeds", "Lcom/amazon/digitalmusicxp/models/CustomerDeviceSettings;", "customerDeviceSettings", "Lcom/amazon/digitalmusicxp/types/StartAt;", "startAt", "targetDeviceContext", "Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;", "playbackOptions", "queueId", "Lcom/amazon/digitalmusicxp/callbacks/Outcome;", "Lcom/amazon/digitalmusicxp/models/PlayQueue;", "startCloudQueue", "Lcom/amazon/mp3/amplifyqueue/AmplifyClient;", "amplifyModelClient", "Lcom/amazon/mp3/amplifyqueue/AmplifyClient;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/digitalmusicxp/CustomerInfoProvider;", "customerInfoProvider", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "marketPlace", "<init>", "(Landroid/content/Context;Lcom/amazon/digitalmusicxp/CustomerInfoProvider;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Companion", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0})
public final class AmplifyAppClient implements CloudQueueAppClient {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(AmplifyClient.class).getSimpleName();
    private final AmplifyClient amplifyModelClient;
    private final Context context;

    /* JADX INFO: compiled from: AmplifyAppClient.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/AmplifyAppClient$Companion;", "", "()V", "TAG", "", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public AmplifyAppClient(Context context, CustomerInfoProvider customerInfoProvider, String deviceId, String deviceType, String marketPlace) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(customerInfoProvider, "customerInfoProvider");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(marketPlace, "marketPlace");
        this.amplifyModelClient = AmplifyClient.INSTANCE.getInstance(context, customerInfoProvider, deviceId, deviceType, marketPlace);
        this.context = context;
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueAppClient
    @WorkerThread
    public Outcome<PlayQueue> startCloudQueue(String title, String customerId, DeviceContext deviceContext, List<? extends BasicQueueSeed> queueSeeds, CustomerDeviceSettings customerDeviceSettings, StartAt startAt, DeviceContext targetDeviceContext, PlaybackOptionsInput playbackOptions, String queueId) {
        Throwable cause;
        String message;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(queueSeeds, "queueSeeds");
        if (customerDeviceSettings != null) {
            this.amplifyModelClient.createCustomerDeviceSettings(customerDeviceSettings);
            boolean hasExplicitLanguage = customerDeviceSettings.getParentalControls().getHasExplicitLanguage();
            Intrinsics.checkNotNull(deviceContext);
            Outcome<InitiateQueueResponse> outcomeInitiateQueue = this.amplifyModelClient.initiateQueue(new InitiateQueueRequest(customerId, null, deviceContext, targetDeviceContext, customerDeviceSettings, queueSeeds, null, startAt, new ParentalControls(hasExplicitLanguage), null, null, playbackOptions, null, 5698, null));
            QueueSeedContextInput queueSeedContextInput = (QueueSeedContextInput) CollectionsKt___CollectionsKt.firstOrNull((List) queueSeeds);
            QueueSeedTypeEnum queueSeedType = queueSeedContextInput == null ? null : queueSeedContextInput.getQueueSeedType();
            String string = queueSeedType == null ? null : queueSeedType.toString();
            if (outcomeInitiateQueue instanceof Outcome.Success) {
                InitiateQueueResponse initiateQueueResponse = (InitiateQueueResponse) ((Outcome.Success) outcomeInitiateQueue).getValue();
                QueueMetadata queueMetadata = initiateQueueResponse.getQueueMetadata();
                if (queueMetadata == null) {
                    return new Outcome.Failure(new RuntimeException("init queue api call did not return queue metadata"));
                }
                GenericQueueEntity genericQueueEntity = initiateQueueResponse.getGenericQueueEntity();
                String string2 = initiateQueueResponse.getResponseContext().toString();
                if (genericQueueEntity == null) {
                    MetricsUtil.INSTANCE.trackEvent("cloudQueueInitiateQueue", (32 & 2) != 0 ? null : string, (32 & 4) != 0 ? null : string2, (32 & 8) == 0 ? null : null, (32 & 16) != 0, (32 & 32) != 0 ? false : false);
                    return new Outcome.Failure(new RuntimeException("init queue api call did not include any playable entity "));
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                ServiceTierEnum serviceTier = queueMetadata.getServiceTier();
                MetricsUtil metricsUtil = MetricsUtil.INSTANCE;
                metricsUtil.setServiceTier(serviceTier.name());
                String settingDefaultQueueEntityCapabilitiesId = this.amplifyModelClient.getSettingDefaultQueueEntityCapabilitiesId();
                if (settingDefaultQueueEntityCapabilitiesId == null) {
                    settingDefaultQueueEntityCapabilitiesId = "";
                }
                Queue queue = new Queue(customerId, queueMetadata.getQueueId(), serviceTier, queueMetadata.getTitle(), queueMetadata.getImages(), this.amplifyModelClient.getSettingQueueTtlInSeconds().intValue(), queueMetadata.getQueueSeeds(), "", queueMetadata.getThingShadowName(), null, this.amplifyModelClient.getSettingMaxEntitiesPerQueueSequenceSlice().intValue(), jCurrentTimeMillis, jCurrentTimeMillis);
                String string3 = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string3, "randomUUID().toString()");
                QueueSequenceSlice queueSequenceSlice = new QueueSequenceSlice(queueMetadata.getQueueId(), queueMetadata.getCustomerId(), UUID.randomUUID().toString(), QueueSequenceSliceName.NATURAL.name(), 1, 0, false, CollectionsKt__CollectionsJVMKt.listOf(new EntityReference(genericQueueEntity.getEntityReferenceId(), genericQueueEntity.getIdentifier(), genericQueueEntity.getIdentifierType(), settingDefaultQueueEntityCapabilitiesId, string3, genericQueueEntity.getClientMetricsInfo())), jCurrentTimeMillis, jCurrentTimeMillis);
                this.amplifyModelClient.createMetricsContext(new MetricsContext(customerId, queue.getId(), string3, genericQueueEntity.getMetricsContext(), jCurrentTimeMillis, jCurrentTimeMillis));
                metricsUtil.trackEvent("cloudQueueInitiateQueue", (32 & 2) != 0 ? null : string, (32 & 4) != 0 ? null : string2, (32 & 8) == 0 ? null : null, (32 & 16) != 0 ? true : true, (32 & 32) != 0 ? false : false);
                return new Outcome.Success(new PlayQueue(queue, CollectionsKt__CollectionsJVMKt.listOf(genericQueueEntity), CollectionsKt__CollectionsJVMKt.listOf(queueSequenceSlice), queueMetadata.getCapabilities()));
            }
            Outcome.Failure failure = outcomeInitiateQueue instanceof Outcome.Failure ? (Outcome.Failure) outcomeInitiateQueue : null;
            String str = "error when calling initiateQueue";
            if (failure != null && (cause = failure.getCause()) != null && (message = cause.getMessage()) != null) {
                str = message;
            }
            RuntimeException runtimeException = new RuntimeException(str);
            MetricsUtil metricsUtil2 = MetricsUtil.INSTANCE;
            metricsUtil2.trackEvent(MetricsUtil.EventType.EVENT_START_CLOUD_QUEUE, runtimeException);
            metricsUtil2.trackEvent("cloudQueueInitiateQueue", (32 & 2) != 0 ? null : string, (32 & 4) != 0 ? null : null, (32 & 8) == 0 ? null : null, (32 & 16) != 0, (32 & 32) != 0 ? false : false);
            return new Outcome.Failure(runtimeException);
        }
        return new Outcome.Failure(new RuntimeException("customerDeviceSettings is required"));
    }
}
