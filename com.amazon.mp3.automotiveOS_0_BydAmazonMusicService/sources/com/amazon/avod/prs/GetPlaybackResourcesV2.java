package com.amazon.avod.prs;

import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2Wrapper;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackResourcesV2 {
    private final GetPlaybackResourcesV2RequestProvider mRequestProvider;
    private final ServiceClient mServiceClient;

    public GetPlaybackResourcesV2(@Nonnull GetPlaybackResourcesV2RequestProvider getPlaybackResourcesV2RequestProvider) {
        this(ServiceClient.getInstance(), getPlaybackResourcesV2RequestProvider);
    }

    GetPlaybackResourcesV2(@Nonnull ServiceClient serviceClient, @Nonnull GetPlaybackResourcesV2RequestProvider getPlaybackResourcesV2RequestProvider) {
        this.mServiceClient = (ServiceClient) Preconditions.checkNotNull(serviceClient, "serviceClient");
        this.mRequestProvider = (GetPlaybackResourcesV2RequestProvider) Preconditions.checkNotNull(getPlaybackResourcesV2RequestProvider, "requestProvider");
    }

    @Nonnull
    public PlaybackResourcesV2Wrapper get(@Nonnull PRSV2ResourceRequest pRSV2ResourceRequest) throws BoltException {
        Preconditions.checkNotNull(pRSV2ResourceRequest, "prsv2ResourceRequest");
        Request<PlaybackResourcesV2Wrapper> requestBuildRequest = this.mRequestProvider.buildRequest(pRSV2ResourceRequest);
        Response responseExecuteSync = this.mServiceClient.executeSync(requestBuildRequest);
        PlaybackResourcesResponseListener responseListener = pRSV2ResourceRequest.getResponseListener();
        if (responseExecuteSync.hasException()) {
            if (responseListener != null) {
                responseListener.onHTTPFailure(requestBuildRequest, responseExecuteSync.getException(), responseExecuteSync.getDownloadStatistics());
            }
            throw responseExecuteSync.getException();
        }
        if (responseListener != null) {
            responseListener.onHTTPSuccess(requestBuildRequest, responseExecuteSync, responseExecuteSync.getDownloadStatistics());
        }
        return (PlaybackResourcesV2Wrapper) responseExecuteSync.getValue();
    }
}
