package com.spotify.localfiles.localfiles;

import com.spotify.localfiles.localfiles.LocalFilesEndpoint;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface CachedFilesEndpoint {
    Single<LocalContentSummary> getContentSummary(LocalFilesEndpoint.Configuration configuration);

    Single<LocalTracksResponse> getTracks(LocalFilesEndpoint.Configuration configuration);

    Observable<LocalContentSummary> subscribeContentSummary(LocalFilesEndpoint.Configuration configuration);

    Observable<LocalTracksResponse> subscribeTracks(LocalFilesEndpoint.Configuration configuration);
}
