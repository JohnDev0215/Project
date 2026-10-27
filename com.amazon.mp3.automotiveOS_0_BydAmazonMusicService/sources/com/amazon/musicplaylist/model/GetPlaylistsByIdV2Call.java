package com.amazon.musicplaylist.model;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class GetPlaylistsByIdV2Call extends CoralCall<GetPlaylistsByIdV2Request, GetPlaylistsByIdV2Response> {
    public GetPlaylistsByIdV2Call(URL url, GetPlaylistsByIdV2Request getPlaylistsByIdV2Request, RequestInterceptor requestInterceptor) {
        this(url, getPlaylistsByIdV2Request, requestInterceptor, false);
    }

    public GetPlaylistsByIdV2Call(URL url, GetPlaylistsByIdV2Request getPlaylistsByIdV2Request, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getPlaylistsByIdV2Request, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetPlaylistsByIdV2ApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetPlaylistsByIdV2Response> getResponseType() {
        return GetPlaylistsByIdV2Response.class;
    }
}
