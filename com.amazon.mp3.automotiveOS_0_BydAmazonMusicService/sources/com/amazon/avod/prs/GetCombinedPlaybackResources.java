package com.amazon.avod.prs;

import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackdevice.types.ResourceUsage;
import com.amazon.avod.content.config.PlaybackResourcesV2Config;
import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.playbackresource.PlaybackResourcesWrapper;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2Wrapper;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.Response;
import com.google.common.base.Preconditions;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class GetCombinedPlaybackResources {
    private final PlaybackResourcesV2Config mPlaybackResourcesV2Config;
    private final GetPlaybackResourcesRequestProvider mRequestProviderV1;
    private final GetPlaybackResourcesV2RequestProvider mRequestProviderV2;
    private final ServiceClient mServiceClient;

    public GetCombinedPlaybackResources(@Nonnull GetPlaybackResourcesRequestProvider getPlaybackResourcesRequestProvider, @Nonnull GetPlaybackResourcesV2RequestProvider getPlaybackResourcesV2RequestProvider) {
        this(ServiceClient.getInstance(), PlaybackResourcesV2Config.getInstance(), getPlaybackResourcesRequestProvider, getPlaybackResourcesV2RequestProvider);
    }

    GetCombinedPlaybackResources(@Nonnull ServiceClient serviceClient, @Nonnull PlaybackResourcesV2Config playbackResourcesV2Config, @Nonnull GetPlaybackResourcesRequestProvider getPlaybackResourcesRequestProvider, @Nonnull GetPlaybackResourcesV2RequestProvider getPlaybackResourcesV2RequestProvider) {
        this.mServiceClient = (ServiceClient) Preconditions.checkNotNull(serviceClient, "serviceClient");
        this.mPlaybackResourcesV2Config = (PlaybackResourcesV2Config) Preconditions.checkNotNull(playbackResourcesV2Config, "playbackResourcesV2Config");
        this.mRequestProviderV1 = (GetPlaybackResourcesRequestProvider) Preconditions.checkNotNull(getPlaybackResourcesRequestProvider, "requestProviderV1");
        this.mRequestProviderV2 = (GetPlaybackResourcesV2RequestProvider) Preconditions.checkNotNull(getPlaybackResourcesV2RequestProvider, "requestProviderV2");
    }

    @Nonnull
    public PlaybackResourcesV2Wrapper get(@Nonnull PRSV2ResourceRequest pRSV2ResourceRequest) throws ExecutionException, InterruptedException, BoltException, TimeoutException {
        return get(pRSV2ResourceRequest, this.mPlaybackResourcesV2Config.shouldCallPRSV1ForUnsupportedResources(), this.mPlaybackResourcesV2Config.getPRSV2UnsupportedResourceSet());
    }

    @Nonnull
    private PlaybackResourcesV2Wrapper get(@Nonnull PRSV2ResourceRequest pRSV2ResourceRequest, boolean z, @Nonnull Set<Resource> set) throws ExecutionException, InterruptedException, BoltException, TimeoutException {
        Preconditions.checkNotNull(pRSV2ResourceRequest, "prsV2ResourceRequest");
        Preconditions.checkNotNull(set, "prsV2UnsupportedResourceSet");
        if (!z || set.isEmpty()) {
            Response<PlaybackResourcesV2Wrapper> responseCall = new GetV2PlaybackResources(pRSV2ResourceRequest).call();
            if (!responseCall.hasException()) {
                return responseCall.getValue();
            }
            DLog.exceptionf(responseCall.getException(), "Fail to get playback resources from PRSv2", new Object[0]);
            throw responseCall.getException();
        }
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorBuild = ScheduledExecutorBuilder.newBuilderFor(this, new String[0]).withFixedThreadPoolSize(2).build();
        Future futureSubmit = scheduledThreadPoolExecutorBuild.submit(new GetV2PlaybackResources(pRSV2ResourceRequest));
        Future futureSubmit2 = scheduledThreadPoolExecutorBuild.submit(new GetV1PlaybackResources(pRSV2ResourceRequest, set));
        try {
            try {
                long pRSV2ExecutorTimeoutMilliseconds = this.mPlaybackResourcesV2Config.getPRSV2ExecutorTimeoutMilliseconds();
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                Response response = (Response) futureSubmit.get(pRSV2ExecutorTimeoutMilliseconds, timeUnit);
                if (response.hasException()) {
                    DLog.exceptionf(response.getException(), "Fail to get playback resources from PRSv2", new Object[0]);
                    throw response.getException();
                }
                try {
                    Response response2 = (Response) futureSubmit2.get(this.mPlaybackResourcesV2Config.getPRSV1ExecutorTimeoutMilliseconds(), timeUnit);
                    if (response2.hasException()) {
                        DLog.exceptionf(response2.getException(), "Fail to get playback resources from PRSv1, returning raw PRSv2 response", new Object[0]);
                        PlaybackResourcesV2Wrapper playbackResourcesV2Wrapper = (PlaybackResourcesV2Wrapper) response.getValue();
                        scheduledThreadPoolExecutorBuild.shutdownNow();
                        return playbackResourcesV2Wrapper;
                    }
                    DLog.logf("Combining PRSv2 response with PRSv1 response");
                    PlaybackResourcesV2Wrapper playbackResourcesV2WrapperUpdate = ((PlaybackResourcesV2Wrapper) response.getValue()).update((PlaybackResourcesWrapper) response2.getValue());
                    scheduledThreadPoolExecutorBuild.shutdownNow();
                    return playbackResourcesV2WrapperUpdate;
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    DLog.exceptionf(e, "Fail to get playback resources from PRSv1, returning raw PRSv2 response", new Object[0]);
                    PlaybackResourcesV2Wrapper playbackResourcesV2Wrapper2 = (PlaybackResourcesV2Wrapper) response.getValue();
                    scheduledThreadPoolExecutorBuild.shutdownNow();
                    return playbackResourcesV2Wrapper2;
                }
            } catch (InterruptedException e2) {
                e = e2;
                DLog.exceptionf(e, "Fail to get playback resources from PRSv2", new Object[0]);
                throw e;
            } catch (ExecutionException e3) {
                e = e3;
                DLog.exceptionf(e, "Fail to get playback resources from PRSv2", new Object[0]);
                throw e;
            } catch (TimeoutException e4) {
                e = e4;
                DLog.exceptionf(e, "Fail to get playback resources from PRSv2", new Object[0]);
                throw e;
            }
        } catch (Throwable th) {
            scheduledThreadPoolExecutorBuild.shutdownNow();
            throw th;
        }
    }

    private final class GetV1PlaybackResources implements Callable<Response<PlaybackResourcesWrapper>> {
        private final Set<Resource> mDesiredResourceSet;
        private final PRSV2ResourceRequest mResourceRequest;

        private GetV1PlaybackResources(@Nonnull PRSV2ResourceRequest pRSV2ResourceRequest, @Nonnull Set<Resource> set) {
            this.mResourceRequest = (PRSV2ResourceRequest) Preconditions.checkNotNull(pRSV2ResourceRequest, "prsV2ResourceRequest");
            this.mDesiredResourceSet = (Set) Preconditions.checkNotNull(set, "prsV2UnsupportedResourceSet");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Response<PlaybackResourcesWrapper> call() {
            return GetCombinedPlaybackResources.this.mServiceClient.executeSync(GetCombinedPlaybackResources.this.mRequestProviderV1.buildRequest(this.mResourceRequest.getTitleId(), this.mResourceRequest.getVideoMaterialType(), this.mResourceRequest.getConsumptionType() == ConsumptionType.Streaming ? com.amazon.atvplaybackdevice.types.ConsumptionType.Streaming : com.amazon.atvplaybackdevice.types.ConsumptionType.Download, this.mResourceRequest.isForValidation() ? ResourceUsage.ImmediateConsumption : ResourceUsage.CacheResources, this.mDesiredResourceSet, NoAdditionalParams.instance(), this.mResourceRequest.getSessionContext()));
        }
    }

    private final class GetV2PlaybackResources implements Callable<Response<PlaybackResourcesV2Wrapper>> {
        private final PRSV2ResourceRequest mResourceRequest;

        private GetV2PlaybackResources(@Nonnull PRSV2ResourceRequest pRSV2ResourceRequest) {
            this.mResourceRequest = (PRSV2ResourceRequest) Preconditions.checkNotNull(pRSV2ResourceRequest, "prsV2ResourceRequest");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Response<PlaybackResourcesV2Wrapper> call() {
            return GetCombinedPlaybackResources.this.mServiceClient.executeSync(GetCombinedPlaybackResources.this.mRequestProviderV2.buildRequest(this.mResourceRequest));
        }
    }
}
