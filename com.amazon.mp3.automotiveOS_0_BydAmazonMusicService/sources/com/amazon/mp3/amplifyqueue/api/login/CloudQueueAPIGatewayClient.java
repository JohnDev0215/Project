package com.amazon.mp3.amplifyqueue.api.login;

import com.amazon.digitalmusicxp.types.ConnectionInfoRequestBody;
import com.amazon.digitalmusicxp.types.ConnectionInfoResponse;
import com.amazonaws.mobileconnectors.apigateway.annotation.Operation;
import com.amazonaws.mobileconnectors.apigateway.annotation.Parameter;
import com.amazonaws.mobileconnectors.apigateway.annotation.Service;
import com.google.firebase.perf.FirebasePerformance;

/* JADX INFO: loaded from: classes3.dex */
@Service(endpoint = "")
public interface CloudQueueAPIGatewayClient {
    @Operation(method = FirebasePerformance.HttpMethod.POST, path = "/getConnectionInfo")
    ConnectionInfoResponse getConnectionInfoPost(@Parameter(location = "header", name = "x-amz-customer-id") String str, @Parameter(location = "header", name = "x-amz-device-type") String str2, @Parameter(location = "header", name = "x-amz-device-id") String str3, @Parameter(location = "header", name = "x-amz-access-token") String str4, ConnectionInfoRequestBody connectionInfoRequestBody);
}
