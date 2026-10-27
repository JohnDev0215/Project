package com.amazon.mp3.amplifyqueue.api.login;

import android.util.Log;
import androidx.annotation.WorkerThread;
import com.amazon.digitalmusicxp.types.ConnectionInfoRequestBody;
import com.amazon.digitalmusicxp.types.ConnectionInfoResponse;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: CloudQueueAuthDataClient.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B9\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b¢\u0006\u0002\u0010\tJ\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/api/login/CloudQueueAuthDataClient;", "", "customerId", "", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "marketPlace", "accessTokenProvider", "Lkotlin/Function0;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "apiClient", "Lcom/amazon/mp3/amplifyqueue/api/login/CloudQueueAPIGatewayClient;", "apiClientFactory", "Lcom/amazonaws/mobileconnectors/apigateway/ApiClientFactory;", "getConnectionInfoResponse", "Lcom/amazon/digitalmusicxp/types/ConnectionInfoResponse;", "Companion", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CloudQueueAuthDataClient {
    private static final String TAG = Reflection.getOrCreateKotlinClass(CloudQueueAuthDataClient.class).getSimpleName();
    private final Function0<String> accessTokenProvider;
    private CloudQueueAPIGatewayClient apiClient;
    private ApiClientFactory apiClientFactory;
    private String customerId;
    private String deviceId;
    private String deviceType;

    public CloudQueueAuthDataClient(String customerId, String deviceId, String deviceType, String marketPlace, Function0<String> accessTokenProvider) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(marketPlace, "marketPlace");
        Intrinsics.checkNotNullParameter(accessTokenProvider, "accessTokenProvider");
        ApiClientFactory apiClientFactory = new ApiClientFactory();
        this.apiClientFactory = apiClientFactory;
        Object objBuild = apiClientFactory.endpoint(marketPlace).build(CloudQueueAPIGatewayClient.class);
        Intrinsics.checkNotNullExpressionValue(objBuild, "apiClientFactory.endpoin…atewayClient::class.java)");
        this.apiClient = (CloudQueueAPIGatewayClient) objBuild;
        this.customerId = customerId;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.accessTokenProvider = accessTokenProvider;
    }

    @WorkerThread
    public final ConnectionInfoResponse getConnectionInfoResponse() {
        ConnectionInfoRequestBody connectionInfoRequestBody = new ConnectionInfoRequestBody(this.customerId);
        String strInvoke = this.accessTokenProvider.invoke();
        if (strInvoke != null) {
            return this.apiClient.getConnectionInfoPost(this.customerId, this.deviceType, this.deviceId, strInvoke, connectionInfoRequestBody);
        }
        Log.e(TAG, "fail to make api call to get connection info");
        return null;
    }
}
