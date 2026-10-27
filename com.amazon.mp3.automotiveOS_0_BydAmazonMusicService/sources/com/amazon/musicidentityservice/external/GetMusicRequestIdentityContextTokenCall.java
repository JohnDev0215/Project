package com.amazon.musicidentityservice.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicidentityservice.GetMusicRequestIdentityContextTokenRequest;
import com.amazon.musicidentityservice.GetMusicRequestIdentityContextTokenResponse;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetMusicRequestIdentityContextTokenCall extends CoralCall<GetMusicRequestIdentityContextTokenRequest, GetMusicRequestIdentityContextTokenResponse> {
    public GetMusicRequestIdentityContextTokenCall(URL url, GetMusicRequestIdentityContextTokenRequest getMusicRequestIdentityContextTokenRequest, List<RequestInterceptor> list) {
        this(url, getMusicRequestIdentityContextTokenRequest, list, false);
    }

    public GetMusicRequestIdentityContextTokenCall(URL url, GetMusicRequestIdentityContextTokenRequest getMusicRequestIdentityContextTokenRequest, List<RequestInterceptor> list, boolean z) {
        super(url, getMusicRequestIdentityContextTokenRequest, list, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetMusicRequestIdentityContextTokenApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetMusicRequestIdentityContextTokenResponse> getResponseType() {
        return GetMusicRequestIdentityContextTokenResponse.class;
    }
}
