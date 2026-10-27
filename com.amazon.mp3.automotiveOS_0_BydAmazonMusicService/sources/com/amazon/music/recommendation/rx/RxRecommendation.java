package com.amazon.music.recommendation.rx;

import com.amazon.music.pager.Pager;
import com.amazon.music.recommendation.BrowseRequest;
import com.amazon.music.recommendation.Recommendation;
import com.amazon.music.recommendation.RecommendationException;
import com.amazon.music.recommendation.RecommendationRequestPageOptions;
import com.amazon.music.recommendation.RecommendationResult;
import com.amazon.music.recommendation.SimilarityRequest;
import com.amazon.music.recommendation.widgets.BrowseWidgetRequest;
import com.amazon.music.recommendation.widgets.RecommendationWidgetResult;
import com.amazon.music.storeservice.model.RecommendedAlbumItem;
import com.amazon.music.storeservice.model.RecommendedPlaylistItem;
import com.amazon.music.storeservice.model.RecommendedStationItem;
import com.amazon.music.storeservice.model.RecommendedTrackItem;
import java.util.List;
import org.apache.commons.lang3.Validate;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes4.dex */
public final class RxRecommendation {
    private final Recommendation recommendation;

    public RxRecommendation(Recommendation recommendation) {
        Validate.notNull(recommendation, "recommendation can't be null", new Object[0]);
        this.recommendation = recommendation;
    }

    public Observable<RecommendationWidgetResult> getBrowseRecommendationWidgetsResult(final BrowseWidgetRequest browseWidgetRequest) {
        return Observable.create(new Observable.OnSubscribe<RecommendationWidgetResult>() { // from class: com.amazon.music.recommendation.rx.RxRecommendation.2
            @Override // rx.functions.Action1
            public void call(Subscriber<? super RecommendationWidgetResult> subscriber) {
                try {
                    subscriber.onNext(RxRecommendation.this.recommendation.getBrowseRecommendationWidgetsResult(browseWidgetRequest));
                } catch (RecommendationException e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        });
    }

    public Observable<RecommendationResult> getSimilarityRecommendationResult(final SimilarityRequest similarityRequest) {
        return Observable.create(new Observable.OnSubscribe<RecommendationResult>() { // from class: com.amazon.music.recommendation.rx.RxRecommendation.3
            @Override // rx.functions.Action1
            public void call(Subscriber<? super RecommendationResult> subscriber) {
                try {
                    subscriber.onNext(RxRecommendation.this.recommendation.getSimilarityRecommendationResult(similarityRequest));
                } catch (RecommendationException e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        });
    }

    public Pager<List<RecommendedAlbumItem>> getBrowseAlbums(BrowseRequest browseRequest, RecommendationRequestPageOptions recommendationRequestPageOptions) {
        return this.recommendation.getBrowseAlbums(browseRequest, recommendationRequestPageOptions);
    }

    public Pager<List<RecommendedTrackItem>> getBrowseTracks(BrowseRequest browseRequest, RecommendationRequestPageOptions recommendationRequestPageOptions) {
        return this.recommendation.getBrowseTracks(browseRequest, recommendationRequestPageOptions);
    }

    public Pager<List<RecommendedPlaylistItem>> getBrowsePlaylists(BrowseRequest browseRequest, RecommendationRequestPageOptions recommendationRequestPageOptions) {
        return this.recommendation.getBrowsePlaylists(browseRequest, recommendationRequestPageOptions);
    }

    public Pager<List<RecommendedStationItem>> getBrowseStations(BrowseRequest browseRequest, RecommendationRequestPageOptions recommendationRequestPageOptions) {
        return this.recommendation.getBrowseStations(browseRequest, recommendationRequestPageOptions);
    }

    public Pager<List<RecommendedAlbumItem>> getSimilarityAlbums(SimilarityRequest similarityRequest) {
        return this.recommendation.getSimilarityAlbums(similarityRequest);
    }

    public Pager<List<RecommendedTrackItem>> getSimilarityTracks(SimilarityRequest similarityRequest) {
        return this.recommendation.getSimilarityTracks(similarityRequest);
    }

    public Pager<List<RecommendedPlaylistItem>> getSimilarityPlaylists(SimilarityRequest similarityRequest) {
        return this.recommendation.getSimilarityPlaylists(similarityRequest);
    }

    public Pager<List<RecommendedStationItem>> getSimilarityStations(SimilarityRequest similarityRequest) {
        return this.recommendation.getSimilarityStations(similarityRequest);
    }
}
