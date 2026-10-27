package com.amazon.music.browse;

import com.amazon.music.storeservice.model.LookupRequest;
import com.amazon.music.storeservice.model.LookupResponse;
import com.amazon.musicensembleservice.ArtistDetailsMetadataRequest;
import com.amazon.musicensembleservice.ArtistDetailsMetadataResponse;
import com.amazon.musicensembleservice.GetTopMusicEntitiesRequest;
import com.amazon.musicensembleservice.GetTopMusicEntitiesResponse;
import com.amazon.musicensembleservice.brush.PageRequest;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.android.volley.VolleyError;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public interface BrowseService {
    ArtistDetailsMetadataResponse getArtistDetailsMetadata(ArtistDetailsMetadataRequest artistDetailsMetadataRequest) throws VolleyError;

    PageResponse getPageResponse(PageRequest pageRequest, Boolean bool, String str) throws IOException, VolleyError;

    GetTopMusicEntitiesResponse getTopMusicEntities(GetTopMusicEntitiesRequest getTopMusicEntitiesRequest) throws VolleyError;

    boolean invalidateCache(PageRequest pageRequest, String str);

    boolean isCacheExpired(PageRequest pageRequest, String str);

    LookupResponse lookup(LookupRequest lookupRequest) throws VolleyError;
}
