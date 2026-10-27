package com.amazon.avod.prs;

import com.amazon.avod.http.PlaybackHttpRequestBuilder;
import com.amazon.avod.http.RequestBuildException;
import com.amazon.avod.http.RequestPriority;
import com.amazon.avod.playbackresourcev2.DrmLicenseResponseParser;
import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2Wrapper;
import com.amazon.bolthttp.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;
import okhttp3.MediaType;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlayReadyLicensePlayerRequest extends GetDrmLicenseResourcesRequestProvider {
    @Override // com.amazon.avod.prs.GetDrmLicenseResourcesRequestProvider
    @Nonnull
    public Request<PlaybackResourcesV2Wrapper> buildRequest(@Nonnull PrsV2DrmLicenseRequest prsV2DrmLicenseRequest) {
        Preconditions.checkNotNull(prsV2DrmLicenseRequest, "prsv2DrmLicenseRequest");
        try {
            return PlaybackHttpRequestBuilder.newBuilder().setUrlPath("playback/drm/GetPlayReadyLicense").setRequestPriority(RequestPriority.CRITICAL).setResponseParser(new DrmLicenseResponseParser(prsV2DrmLicenseRequest.getTitleId())).setUrlParamMap(ImmutableMap.builder().put("titleId", prsV2DrmLicenseRequest.getTitleId()).build()).setHttpMethod(Request.HttpMethod.POST).setBody(Request.Body.create(MediaType.parse("application/json"), ResourceParamsUtils.formatParamsToJsonString(prsV2DrmLicenseRequest.getParams()))).setAuthentication(prsV2DrmLicenseRequest.getSessionContext()).build();
        } catch (RequestBuildException | JsonProcessingException e) {
            throw new IllegalStateException("Unable to generate a PlayReady license request", e);
        }
    }
}
