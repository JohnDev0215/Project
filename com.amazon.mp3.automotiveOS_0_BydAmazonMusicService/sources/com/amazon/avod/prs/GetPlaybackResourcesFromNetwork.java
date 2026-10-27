package com.amazon.avod.prs;

import com.amazon.atvplaybackdevice.types.ConsumptionType;
import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackdevice.types.ResourceUsage;
import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.playbackresource.PlaybackResourcesWrapper;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackResourcesFromNetwork implements GetPlaybackResources {
    private final GetPlaybackResourcesRequestProvider mRequestProvider;
    private final ServiceClient mServiceClient;

    GetPlaybackResourcesFromNetwork(@Nonnull GetPlaybackResourcesRequestProvider getPlaybackResourcesRequestProvider) {
        this(ServiceClient.getInstance(), getPlaybackResourcesRequestProvider);
    }

    GetPlaybackResourcesFromNetwork(@Nonnull ServiceClient serviceClient, @Nonnull GetPlaybackResourcesRequestProvider getPlaybackResourcesRequestProvider) {
        this.mServiceClient = (ServiceClient) Preconditions.checkNotNull(serviceClient, "serviceClient");
        this.mRequestProvider = (GetPlaybackResourcesRequestProvider) Preconditions.checkNotNull(getPlaybackResourcesRequestProvider, "requestProvider");
    }

    @Override // com.amazon.avod.prs.GetPlaybackResources
    @Nonnull
    public PlaybackResourcesWrapper get(@Nonnull String str, @Nonnull VideoMaterialType videoMaterialType, @Nonnull ConsumptionType consumptionType, @Nonnull ResourceUsage resourceUsage, @Nonnull Set<Resource> set, @Nonnull Map<String, String> map, @Nullable PlaybackResourcesResponseListener playbackResourcesResponseListener) throws BoltException {
        return get(str, videoMaterialType, consumptionType, resourceUsage, set, NoAdditionalParams.instance(), map, playbackResourcesResponseListener);
    }

    @Override // com.amazon.avod.prs.GetPlaybackResources
    @Nonnull
    public PlaybackResourcesWrapper get(@Nonnull String str, @Nonnull VideoMaterialType videoMaterialType, @Nonnull ConsumptionType consumptionType, @Nonnull ResourceUsage resourceUsage, @Nonnull Set<Resource> set, @Nonnull AdditionalParams additionalParams, @Nonnull Map<String, String> map, @Nullable PlaybackResourcesResponseListener playbackResourcesResponseListener) throws BoltException {
        Preconditions.checkNotNull(str, "titleId");
        Preconditions.checkNotNull(videoMaterialType, "videoMaterialType");
        Preconditions.checkNotNull(consumptionType, "consumptionType");
        Preconditions.checkNotNull(resourceUsage, "resourceUsage");
        Preconditions.checkNotNull(set, "desiredResources");
        Preconditions.checkNotNull(additionalParams, "additionalParams");
        Preconditions.checkNotNull(map, "sessionContext");
        Request<PlaybackResourcesWrapper> requestBuildRequest = this.mRequestProvider.buildRequest(str, videoMaterialType, consumptionType, resourceUsage, set, additionalParams, map);
        Response responseExecuteSync = this.mServiceClient.executeSync(requestBuildRequest);
        if (responseExecuteSync.hasException()) {
            if (playbackResourcesResponseListener != null) {
                playbackResourcesResponseListener.onHTTPFailure(requestBuildRequest, responseExecuteSync.getException(), responseExecuteSync.getDownloadStatistics());
            }
            throw responseExecuteSync.getException();
        }
        if (playbackResourcesResponseListener != null) {
            playbackResourcesResponseListener.onHTTPSuccess(requestBuildRequest, responseExecuteSync, responseExecuteSync.getDownloadStatistics());
        }
        return (PlaybackResourcesWrapper) responseExecuteSync.getValue();
    }
}
