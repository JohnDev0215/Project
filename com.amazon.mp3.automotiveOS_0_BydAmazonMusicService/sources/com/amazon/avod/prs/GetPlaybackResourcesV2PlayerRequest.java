package com.amazon.avod.prs;

import com.amazon.avod.http.PlaybackHttpRequestBuilder;
import com.amazon.avod.http.RequestBuildException;
import com.amazon.avod.http.RequestPriority;
import com.amazon.avod.playbackresourcev2.LivePlaybackResourceResponseParser;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2Wrapper;
import com.amazon.bolthttp.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;
import okhttp3.MediaType;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackResourcesV2PlayerRequest implements GetPlaybackResourcesV2RequestProvider {
    @Override // com.amazon.avod.prs.GetPlaybackResourcesV2RequestProvider
    @Nonnull
    public Request<PlaybackResourcesV2Wrapper> buildRequest(@Nonnull PRSV2ResourceRequest pRSV2ResourceRequest) {
        Preconditions.checkNotNull(pRSV2ResourceRequest, "prsv2ResourceRequest");
        try {
            PlaybackHttpRequestBuilder responseParser = PlaybackHttpRequestBuilder.newBuilder().setUrlPath(pRSV2ResourceRequest.isForValidation() ? "/playback/prs/GetLivePlaybackResources" : "/playback/prs/GetLivePlaybackResourcesForCaching").setUrlParamMap(ImmutableMap.builder().put("titleId", pRSV2ResourceRequest.getTitleId()).put("videoMaterialType", pRSV2ResourceRequest.getVideoMaterialType().getValue()).put("consumptionType", pRSV2ResourceRequest.getConsumptionType().toString()).build()).setRequestPriority(RequestPriority.CRITICAL).setResponseParser(new LivePlaybackResourceResponseParser(pRSV2ResourceRequest.getTitleId()));
            responseParser.setHttpMethod(Request.HttpMethod.POST);
            responseParser.setBody(Request.Body.create(MediaType.parse("application/json"), ResourceParamsUtils.formatParamsToJsonString(pRSV2ResourceRequest.getResourceParams())));
            responseParser.setAuthentication(pRSV2ResourceRequest.getSessionContext());
            return responseParser.build();
        } catch (RequestBuildException | JsonProcessingException e) {
            throw new IllegalStateException("Unable to generate a request", e);
        }
    }
}
