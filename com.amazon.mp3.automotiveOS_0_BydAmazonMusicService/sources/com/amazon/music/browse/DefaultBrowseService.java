package com.amazon.music.browse;

import com.amazon.hermes.CoralCall;
import com.amazon.hermes.Hermes;
import com.amazon.music.storeservice.model.LookupRequest;
import com.amazon.music.storeservice.model.LookupResponse;
import com.amazon.musicensembleservice.ArtistDetailsMetadataCall;
import com.amazon.musicensembleservice.ArtistDetailsMetadataRequest;
import com.amazon.musicensembleservice.ArtistDetailsMetadataResponse;
import com.amazon.musicensembleservice.GetTopMusicEntitiesCall;
import com.amazon.musicensembleservice.GetTopMusicEntitiesRequest;
import com.amazon.musicensembleservice.GetTopMusicEntitiesResponse;
import com.amazon.musicensembleservice.LookupCall;
import com.amazon.musicensembleservice.PageCall;
import com.amazon.musicensembleservice.brush.PageRequest;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.android.volley.Cache;
import com.android.volley.CacheStateManager;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultBrowseService implements BrowseService {
    private final Configuration configuration;
    private RetryPolicy retryPolicy = new DefaultRetryPolicy(20000, 1, 1.0f);
    private final CacheStateManager cacheManager = Hermes.get().getCacheManager();

    public DefaultBrowseService(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override // com.amazon.music.browse.BrowseService
    public GetTopMusicEntitiesResponse getTopMusicEntities(GetTopMusicEntitiesRequest getTopMusicEntitiesRequest) throws VolleyError {
        GetTopMusicEntitiesCall getTopMusicEntitiesCall = new GetTopMusicEntitiesCall(this.configuration.getMuseURL(), getTopMusicEntitiesRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(getTopMusicEntitiesCall);
        return getTopMusicEntitiesCall.execute(Mappers.getMapper());
    }

    @Override // com.amazon.music.browse.BrowseService
    public final LookupResponse lookup(LookupRequest lookupRequest) throws VolleyError {
        LookupCall lookupCall = new LookupCall(this.configuration.getTrackLookupURL(), lookupRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(lookupCall);
        return lookupCall.execute(Mappers.getMapper());
    }

    @Override // com.amazon.music.browse.BrowseService
    public ArtistDetailsMetadataResponse getArtistDetailsMetadata(ArtistDetailsMetadataRequest artistDetailsMetadataRequest) throws VolleyError {
        ArtistDetailsMetadataCall artistDetailsMetadataCall = new ArtistDetailsMetadataCall(this.configuration.getMuseURL(), artistDetailsMetadataRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(artistDetailsMetadataCall);
        return artistDetailsMetadataCall.execute(Mappers.getMapper());
    }

    @Override // com.amazon.music.browse.BrowseService
    public PageResponse getPageResponse(PageRequest pageRequest, Boolean bool, String str) throws IOException, VolleyError {
        PageCall pageCall = setupPageCall(pageRequest, str);
        if (bool.booleanValue()) {
            return getCachedPageResponse(pageRequest, pageCall);
        }
        return pageCall.execute(Mappers.getMapper());
    }

    private PageResponse getCachedPageResponse(PageRequest pageRequest, PageCall pageCall) throws IOException, VolleyError {
        Cache.Entry cacheEntry = this.cacheManager.getCacheEntry(pageCall.getCacheKey());
        if (cacheEntry == null) {
            return pageCall.execute(Mappers.getMapper());
        }
        PageResponse pageResponse = (PageResponse) Mappers.getMapper().readValue(cacheEntry.data, pageCall.getResponseType());
        if (cacheEntry.isExpired()) {
            pageResponse.getContent().setNextToken(null);
        }
        return pageResponse;
    }

    @Override // com.amazon.music.browse.BrowseService
    public boolean isCacheExpired(PageRequest pageRequest, String str) {
        return this.cacheManager.hasCacheExpired(setupPageCall(pageRequest, str).getCacheKey());
    }

    @Override // com.amazon.music.browse.BrowseService
    public boolean invalidateCache(PageRequest pageRequest, String str) {
        return this.cacheManager.invalidateCacheEntry(setupPageCall(pageRequest, str).getCacheKey());
    }

    private PageCall setupPageCall(PageRequest pageRequest, String str) {
        PageCall pageCall = new PageCall(this.configuration.getHomeURL(), pageRequest, this.configuration.getRequestInterceptor());
        pageCall.setObjectMapper(Mappers.getMapper());
        String uri = pageRequest.getUri();
        if (str != null) {
            uri = uri + str;
        }
        pageCall.setCacheKey(uri);
        setRetryPolicy(pageCall);
        return pageCall;
    }

    private void setRetryPolicy(CoralCall coralCall) {
        RetryPolicy retryPolicy = this.retryPolicy;
        if (retryPolicy != null) {
            coralCall.setRetryPolicy(retryPolicy);
        }
    }
}
