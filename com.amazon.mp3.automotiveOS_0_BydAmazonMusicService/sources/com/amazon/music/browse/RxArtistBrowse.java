package com.amazon.music.browse;

import com.amazon.music.browse.artist.ArtistBrowse;
import com.amazon.music.browse.artist.ArtistBrowseRequest;
import com.amazon.musicensembleservice.ArtistDetailsMetadataResponse;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes4.dex */
public final class RxArtistBrowse {
    private final ArtistBrowse artistBrowse;

    public RxArtistBrowse(ArtistBrowse artistBrowse) {
        this.artistBrowse = artistBrowse;
    }

    public Observable<ArtistDetailsMetadataResponse> getArtistDetails(final ArtistBrowseRequest artistBrowseRequest) {
        return Observable.create(new Observable.OnSubscribe<ArtistDetailsMetadataResponse>() { // from class: com.amazon.music.browse.RxArtistBrowse.1
            @Override // rx.functions.Action1
            public void call(Subscriber<? super ArtistDetailsMetadataResponse> subscriber) {
                try {
                    subscriber.onNext(RxArtistBrowse.this.artistBrowse.getArtistDetailsMetadata(artistBrowseRequest));
                    subscriber.onCompleted();
                } catch (BrowseException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
