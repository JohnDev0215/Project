package com.amazon.mp3.prime.cta;

import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.data.AsinSubstitutionException;
import com.amazon.music.browse.BrowseException;
import java.util.List;
import org.json.JSONException;

/* JADX INFO: loaded from: classes4.dex */
public interface AlbumRequester {
    AlbumMetadata requestAlbumRemoteMetadataForAsin(final String albumAsin) throws JSONException, ServiceException, BrowseException, AbstractHttpClient.HttpClientException;

    List<PrimeTrack> requestTracksForAsin(String albumAsin) throws JSONException, ServiceException, BrowseException, AbstractHttpClient.HttpClientException, AsinSubstitutionException;
}
