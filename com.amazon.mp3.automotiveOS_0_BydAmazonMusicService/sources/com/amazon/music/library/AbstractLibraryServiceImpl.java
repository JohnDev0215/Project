package com.amazon.music.library;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.library.AbstractLibraryServiceImpl.Configuration;
import com.amazon.musicplaylist.model.AuthorizationRequiredRequest;
import com.amazon.musicplaylist.model.SociallySharePlaylistCall;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractLibraryServiceImpl<TConfiguration extends Configuration> implements LibraryService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private final TConfiguration mConfiguration;

    public interface Configuration {
        String getDeviceId();

        String getDeviceType();

        URL getPlaylistServiceUrl();

        RequestInterceptor getRequestInterceptor();
    }

    public AbstractLibraryServiceImpl(TConfiguration tconfiguration) {
        this.mConfiguration = tconfiguration;
    }

    protected void addInfoToRequest(AuthorizationRequiredRequest authorizationRequiredRequest) {
        authorizationRequiredRequest.setDeviceId(this.mConfiguration.getDeviceId());
        authorizationRequiredRequest.setDeviceType(this.mConfiguration.getDeviceType());
    }

    @Override // com.amazon.music.library.LibraryService
    public com.amazon.musicplaylist.model.SociallySharePlaylistResponse sociallySharePlaylist(com.amazon.musicplaylist.model.SociallySharePlaylistRequest sociallySharePlaylistRequest) throws VolleyError {
        addInfoToRequest(sociallySharePlaylistRequest);
        return new SociallySharePlaylistCall(this.mConfiguration.getPlaylistServiceUrl(), sociallySharePlaylistRequest, this.mConfiguration.getRequestInterceptor()).execute(MAPPER);
    }
}
