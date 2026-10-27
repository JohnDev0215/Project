package com.amazon.avod.prs;

import com.amazon.atvplaybackdevice.types.ConsumptionType;
import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackdevice.types.ResourceUsage;
import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.avod.http.PlaybackHttpRequestBuilder;
import com.amazon.avod.http.RequestBuildException;
import com.amazon.avod.http.RequestPriority;
import com.amazon.avod.playbackresource.PlaybackResourcesWrapper;
import com.amazon.bolthttp.Request;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackResourcesPlayerRequest implements GetPlaybackResourcesRequestProvider {
    @Override // com.amazon.avod.prs.GetPlaybackResourcesRequestProvider
    @Nonnull
    public Request<PlaybackResourcesWrapper> buildRequest(@Nonnull String str, @Nonnull VideoMaterialType videoMaterialType, @Nonnull ConsumptionType consumptionType, @Nonnull ResourceUsage resourceUsage, @Nonnull Set<Resource> set, @Nonnull AdditionalParams additionalParams, @Nonnull Map<String, String> map) {
        Preconditions.checkNotNull(str, "titleId");
        Preconditions.checkNotNull(videoMaterialType, "videoMaterialType");
        Preconditions.checkNotNull(consumptionType, "consumptionType");
        Preconditions.checkNotNull(resourceUsage, "resourceUsage");
        Preconditions.checkNotNull(set, "desiredResources");
        Preconditions.checkNotNull(additionalParams, "additionalParams");
        Preconditions.checkNotNull(map, "sessionContext");
        ImmutableMap.Builder builderPut = ImmutableMap.builder().put("titleId", str).put("videoMaterialType", videoMaterialType.getValue()).put("consumptionType", consumptionType.getValue()).put("resourceUsage", resourceUsage.getValue()).put("desiredResources", GetPlaybackResourcesRequestProvider.JOINER.join(set));
        ImmutableMap.Builder builder = ImmutableMap.builder();
        if (additionalParams.hasPostBodyParams()) {
            builder.putAll(additionalParams.getPostBodyParams());
        }
        if (additionalParams.hasQueryStringParams()) {
            builderPut.putAll(additionalParams.getQueryStringParams());
        }
        try {
            PlaybackHttpRequestBuilder responseParser = PlaybackHttpRequestBuilder.newBuilder().setUrlPath("/cdp/catalog/GetPlaybackResources").setUrlParamMap(builderPut.build()).setRequestPriority(resourceUsage == ResourceUsage.ImmediateConsumption ? RequestPriority.CRITICAL : RequestPriority.BACKGROUND).setResponseParser(new PlaybackResourceResponseParser());
            if (additionalParams.hasPostBodyParams()) {
                responseParser.setHttpMethod(Request.HttpMethod.POST);
                responseParser.setBody(Request.Body.createFormBody(builder.build()));
            } else {
                responseParser.setHttpMethod(Request.HttpMethod.GET);
            }
            responseParser.setAuthentication(map);
            return responseParser.build();
        } catch (RequestBuildException e) {
            throw new IllegalStateException("Unable to generate a request", e);
        }
    }
}
