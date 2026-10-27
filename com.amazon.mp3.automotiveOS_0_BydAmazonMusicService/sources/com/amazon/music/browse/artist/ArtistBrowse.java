package com.amazon.music.browse.artist;

import com.amazon.music.browse.BrowseException;
import com.amazon.music.browse.BrowseService;
import com.amazon.music.browse.util.ParentalControlsUtil;
import com.amazon.musicensembleservice.ArtistDetailsMetadataRequest;
import com.amazon.musicensembleservice.ArtistDetailsMetadataResponse;
import com.android.volley.VolleyError;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class ArtistBrowse {
    private final String musicTerritory;
    private final BrowseService service;

    public ArtistBrowse(BrowseService browseService, String str) {
        this.service = browseService;
        this.musicTerritory = str;
    }

    public ArtistDetailsMetadataResponse getArtistDetailsMetadata(ArtistBrowseRequest artistBrowseRequest) throws BrowseException {
        ArtistDetailsMetadataRequest artistDetailsMetadataRequest = new ArtistDetailsMetadataRequest();
        artistDetailsMetadataRequest.setMusicTerritory(this.musicTerritory);
        artistDetailsMetadataRequest.setAsin(artistBrowseRequest.getAsin());
        artistDetailsMetadataRequest.setFeatures(Arrays.asList("ownership", "albumArtist", "hasLyrics"));
        artistDetailsMetadataRequest.setTypes(artistBrowseRequest.getTypes());
        artistDetailsMetadataRequest.setRequestedContent(artistBrowseRequest.getRequestedContent());
        artistDetailsMetadataRequest.setDeviceId(artistBrowseRequest.getDeviceId());
        artistDetailsMetadataRequest.setDeviceType(artistBrowseRequest.getDeviceType());
        artistDetailsMetadataRequest.setLang(artistBrowseRequest.getLanguage());
        artistDetailsMetadataRequest.setAllowedParentalControls(ParentalControlsUtil.getParentalControls(artistBrowseRequest.isExplicitFilterEnabled()));
        try {
            return this.service.getArtistDetailsMetadata(artistDetailsMetadataRequest);
        } catch (VolleyError e) {
            throw new BrowseException(e);
        }
    }
}
