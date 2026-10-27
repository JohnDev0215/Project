package com.amazon.music.casting;

import com.amazon.hermes.CoralCall;
import com.amazon.musicplayqueueservice.client.common.CreateQueueRequest;
import com.amazon.musicplayqueueservice.client.common.CreateQueueResponse;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoRequest;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoResponse;
import com.amazon.musicplayqueueservice.client.common.GetDeviceListRequest;
import com.amazon.musicplayqueueservice.client.common.GetDeviceListResponse;
import com.amazon.musicplayqueueservice.client.common.LoginRequest;
import com.amazon.musicplayqueueservice.client.common.LoginResponse;
import com.amazon.musicplayqueueservice.client.common.TransferQueueRequest;
import com.amazon.musicplayqueueservice.client.common.TransferQueueResponse;
import com.amazon.musicplayqueueservice.model.client.external.voiceenabled.CreateQueueCall;
import com.amazon.musicplayqueueservice.model.client.external.voiceenabled.GetConnectionInfoCall;
import com.amazon.musicplayqueueservice.model.client.external.voiceenabled.GetDeviceListCall;
import com.amazon.musicplayqueueservice.model.client.external.voiceenabled.LoginCall;
import com.amazon.musicplayqueueservice.model.client.external.voiceenabled.TransferQueueCall;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
public class HttpCastingService implements CastingService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private final Configuration configuration;
    private final ServiceEnvironment environment;
    private RetryPolicy retryPolicy;

    public HttpCastingService(Configuration configuration, ServiceEnvironment serviceEnvironment, RetryPolicy retryPolicy) {
        this.configuration = configuration;
        this.environment = serviceEnvironment;
        this.retryPolicy = retryPolicy;
    }

    public HttpCastingService(Configuration configuration, ServiceEnvironment serviceEnvironment) {
        this(configuration, serviceEnvironment, new DefaultRetryPolicy(20000, 1, 1.0f));
    }

    public HttpCastingService(Configuration configuration) {
        this(configuration, new ServiceEnvironment_Prod(), new DefaultRetryPolicy(20000, 1, 1.0f));
    }

    @Override // com.amazon.music.casting.CastingService
    public GetDeviceListResponse getDeviceList() throws VolleyError {
        GetDeviceListCall getDeviceListCall = new GetDeviceListCall(MpqsUrls.create(this.configuration, this.environment), new GetDeviceListRequest(), this.configuration.getRequestInterceptor());
        setRetryPolicy(getDeviceListCall);
        return getDeviceListCall.execute(MAPPER);
    }

    @Override // com.amazon.music.casting.CastingService
    public GetConnectionInfoResponse getConnectionInfo() throws VolleyError {
        GetConnectionInfoCall getConnectionInfoCall = new GetConnectionInfoCall(MpqsUrls.create(this.configuration, this.environment), new GetConnectionInfoRequest(), this.configuration.getRequestInterceptor());
        setRetryPolicy(getConnectionInfoCall);
        return getConnectionInfoCall.execute(MAPPER);
    }

    @Override // com.amazon.music.casting.CastingService
    public LoginResponse login() throws VolleyError {
        LoginCall loginCall = new LoginCall(MpqsUrls.create(this.configuration, this.environment), new LoginRequest(), this.configuration.getRequestInterceptor());
        setRetryPolicy(loginCall);
        return loginCall.execute(MAPPER);
    }

    @Override // com.amazon.music.casting.CastingService
    public CreateQueueResponse createQueue(CreateQueueRequest createQueueRequest) throws VolleyError {
        CreateQueueCall createQueueCall = new CreateQueueCall(MpqsUrls.create(this.configuration, this.environment), createQueueRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(createQueueCall);
        return createQueueCall.execute(MAPPER);
    }

    @Override // com.amazon.music.casting.CastingService
    public TransferQueueResponse transferQueue(TransferQueueRequest transferQueueRequest) throws VolleyError {
        TransferQueueCall transferQueueCall = new TransferQueueCall(MpqsUrls.create(this.configuration, this.environment), transferQueueRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(transferQueueCall);
        return transferQueueCall.execute(MAPPER);
    }

    private void setRetryPolicy(CoralCall coralCall) {
        RetryPolicy retryPolicy = this.retryPolicy;
        if (retryPolicy != null) {
            coralCall.setRetryPolicy(retryPolicy);
        }
    }
}
