package com.amazon.avod.prs;

import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2Wrapper;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class GetDrmLicenseResources {
    private final GetDrmLicenseResourcesRequestProvider mRequestProvider;
    private final ServiceClient mServiceClient;

    GetDrmLicenseResources(@Nonnull GetDrmLicenseResourcesRequestProvider getDrmLicenseResourcesRequestProvider) {
        this(getDrmLicenseResourcesRequestProvider, ServiceClient.getInstance());
    }

    GetDrmLicenseResources(@Nonnull GetDrmLicenseResourcesRequestProvider getDrmLicenseResourcesRequestProvider, @Nonnull ServiceClient serviceClient) {
        this.mRequestProvider = (GetDrmLicenseResourcesRequestProvider) Preconditions.checkNotNull(getDrmLicenseResourcesRequestProvider, "getDrmLicenseResourcesRequestProvider");
        this.mServiceClient = (ServiceClient) Preconditions.checkNotNull(serviceClient, "serviceClient");
    }

    @Nonnull
    public PlaybackResourcesV2Wrapper get(@Nonnull PrsV2DrmLicenseRequest prsV2DrmLicenseRequest) throws BoltException {
        Preconditions.checkNotNull(prsV2DrmLicenseRequest, "prsv2ResourceRequest");
        Request<PlaybackResourcesV2Wrapper> requestBuildRequest = this.mRequestProvider.buildRequest(prsV2DrmLicenseRequest);
        Response responseExecuteSync = this.mServiceClient.executeSync(requestBuildRequest);
        PlaybackResourcesResponseListener responseListener = prsV2DrmLicenseRequest.getResponseListener();
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
