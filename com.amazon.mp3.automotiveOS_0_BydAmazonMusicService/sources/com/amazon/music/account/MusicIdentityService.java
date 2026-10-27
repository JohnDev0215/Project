package com.amazon.music.account;

import com.amazon.musicidentityservice.GetMusicRequestIdentityContextTokenRequest;
import com.amazon.musicidentityservice.GetMusicRequestIdentityContextTokenResponse;
import com.amazon.musicidentityservice.external.GetMusicRequestIdentityContextTokenCall;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class MusicIdentityService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private final MusicIdentityServiceConfiguration configuration;

    public MusicIdentityService(MusicIdentityServiceConfiguration musicIdentityServiceConfiguration) {
        this.configuration = (MusicIdentityServiceConfiguration) Validate.notNull(musicIdentityServiceConfiguration);
    }

    public GetMusicRequestIdentityContextTokenCall buildGetMusicRequestIdentityContextTokenCall(GetMusicRequestIdentityContextTokenRequest getMusicRequestIdentityContextTokenRequest) {
        return new GetMusicRequestIdentityContextTokenCall(getMusicIdentityServiceUrl(), getMusicRequestIdentityContextTokenRequest, this.configuration.getRequestInterceptors());
    }

    public URL getMusicIdentityServiceUrl() {
        return this.configuration.getMusicIdentityServiceURL();
    }

    public GetMusicRequestIdentityContextTokenResponse retrieveMusicRequestIdentityContextToken(GetMusicRequestIdentityContextTokenRequest getMusicRequestIdentityContextTokenRequest) throws VolleyError {
        return buildGetMusicRequestIdentityContextTokenCall(getMusicRequestIdentityContextTokenRequest).execute(MAPPER);
    }
}
