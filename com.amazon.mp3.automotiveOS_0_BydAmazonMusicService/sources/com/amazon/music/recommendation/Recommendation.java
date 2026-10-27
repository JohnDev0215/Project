package com.amazon.music.recommendation;

import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.pager.Pager;
import com.amazon.music.recommendation.widgets.BrowseWidgetRequest;
import com.amazon.music.recommendation.widgets.RecommendationWidgetResult;
import com.amazon.music.storeservice.model.RecommendedAlbumItem;
import com.amazon.music.storeservice.model.RecommendedPlaylistItem;
import com.amazon.music.storeservice.model.RecommendedStationItem;
import com.amazon.music.storeservice.model.RecommendedTrackItem;
import com.android.volley.VolleyError;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class Recommendation {
    private final BrowseRecommendationProvider browseProvider;
    private final RecommendationProvider similarityProvider;
    private final RecommendationProvider sourceAsinsProvider;

    public Recommendation(RecommendationService recommendationService, String str, Locale locale) {
        this(recommendationService, null, str, locale);
    }

    public Recommendation(RecommendationService recommendationService, Marketplace marketplace, String str, Locale locale) {
        Validate.notNull(recommendationService, "service can't be null", new Object[0]);
        Validate.notNull(locale, "locale  can't be null", new Object[0]);
        if (marketplace == null && StringUtils.isEmpty(str)) {
            throw new NullPointerException("Either marketplace or musicTerritory can't be null.");
        }
        this.browseProvider = new BrowseRecommendationProvider(recommendationService, locale, marketplace, str);
        this.similarityProvider = new SimilarityRecommendationProvider(recommendationService, locale, marketplace, str);
        this.sourceAsinsProvider = new BrowseRecommendationsBySourceAsinsProvider(recommendationService, locale, marketplace, str);
    }

    public RecommendationWidgetResult getBrowseRecommendationWidgetsResult(BrowseWidgetRequest browseWidgetRequest) throws RecommendationException {
        return getRecommendationWidgetResult(this.browseProvider, browseWidgetRequest);
    }

    public RecommendationResult getSimilarityRecommendationResult(SimilarityRequest similarityRequest) throws RecommendationException {
        return getRecommendationResult(this.similarityProvider, similarityRequest);
    }

    public Pager<List<RecommendedTrackItem>> getBrowseTracks(BrowseRequest browseRequest, RecommendationRequestPageOptions recommendationRequestPageOptions) {
        return getTracks(this.browseProvider, browseRequest, recommendationRequestPageOptions);
    }

    public Pager<List<RecommendedTrackItem>> getSimilarityTracks(RecommendationRequest recommendationRequest) {
        return getTracks(this.similarityProvider, recommendationRequest);
    }

    public Pager<List<RecommendedAlbumItem>> getBrowseAlbums(BrowseRequest browseRequest, RecommendationRequestPageOptions recommendationRequestPageOptions) {
        return getAlbums(this.browseProvider, browseRequest, recommendationRequestPageOptions);
    }

    public Pager<List<RecommendedAlbumItem>> getSimilarityAlbums(RecommendationRequest recommendationRequest) {
        return getAlbums(this.similarityProvider, recommendationRequest);
    }

    public Pager<List<RecommendedPlaylistItem>> getBrowsePlaylists(BrowseRequest browseRequest, RecommendationRequestPageOptions recommendationRequestPageOptions) {
        return getPlaylists(this.browseProvider, browseRequest, recommendationRequestPageOptions);
    }

    public Pager<List<RecommendedPlaylistItem>> getSimilarityPlaylists(RecommendationRequest recommendationRequest) {
        return getPlaylists(this.similarityProvider, recommendationRequest);
    }

    public Pager<List<RecommendedStationItem>> getBrowseStations(BrowseRequest browseRequest, RecommendationRequestPageOptions recommendationRequestPageOptions) {
        return getStations(this.browseProvider, browseRequest, recommendationRequestPageOptions);
    }

    public Pager<List<RecommendedStationItem>> getSimilarityStations(RecommendationRequest recommendationRequest) {
        return getStations(this.similarityProvider, recommendationRequest);
    }

    private RecommendationWidgetResult getRecommendationWidgetResult(BrowseRecommendationProvider browseRecommendationProvider, BrowseWidgetRequest browseWidgetRequest) throws RecommendationException {
        Validate.notNull(browseWidgetRequest, "request can't be null", new Object[0]);
        try {
            return new RecommendationWidgetResult(browseRecommendationProvider.getRecommendations(browseWidgetRequest));
        } catch (VolleyError e) {
            throw new RecommendationException(e);
        }
    }

    private RecommendationResult getRecommendationResult(RecommendationProvider recommendationProvider, RecommendationRequest recommendationRequest) throws RecommendationException {
        Validate.notNull(recommendationRequest, "request can't be null", new Object[0]);
        try {
            return RecommendationUtils.fromResponse(recommendationProvider.getRecommendations(recommendationRequest));
        } catch (VolleyError e) {
            throw new RecommendationException(e);
        }
    }

    private Pager<List<RecommendedTrackItem>> getTracks(RecommendationProvider recommendationProvider, RecommendationRequest recommendationRequest) {
        Validate.notNull(recommendationRequest, "request can't be null", new Object[0]);
        return Pager.create(new RecommendationPagerIterator(recommendationProvider, recommendationRequest, RecommendationType.TRACK)).replay().build();
    }

    private Pager<List<RecommendedTrackItem>> getTracks(RecommendationProvider recommendationProvider, RecommendationRequest recommendationRequest, RecommendationRequestPageOptions recommendationRequestPageOptions) {
        Validate.notNull(recommendationRequest, "request can't be null", new Object[0]);
        return Pager.create(new RecommendationPagerIterator(recommendationProvider, recommendationRequest, RecommendationType.TRACK, recommendationRequestPageOptions)).replay().build();
    }

    private Pager<List<RecommendedAlbumItem>> getAlbums(RecommendationProvider recommendationProvider, RecommendationRequest recommendationRequest) {
        Validate.notNull(recommendationRequest, "request can't be null", new Object[0]);
        return Pager.create(new RecommendationPagerIterator(recommendationProvider, recommendationRequest, RecommendationType.ALBUM)).replay().build();
    }

    private Pager<List<RecommendedAlbumItem>> getAlbums(RecommendationProvider recommendationProvider, RecommendationRequest recommendationRequest, RecommendationRequestPageOptions recommendationRequestPageOptions) {
        Validate.notNull(recommendationRequest, "request can't be null", new Object[0]);
        return Pager.create(new RecommendationPagerIterator(recommendationProvider, recommendationRequest, RecommendationType.ALBUM, recommendationRequestPageOptions)).replay().build();
    }

    private Pager<List<RecommendedPlaylistItem>> getPlaylists(RecommendationProvider recommendationProvider, RecommendationRequest recommendationRequest) {
        Validate.notNull(recommendationRequest, "request can't be null", new Object[0]);
        return Pager.create(new RecommendationPagerIterator(recommendationProvider, recommendationRequest, RecommendationType.PLAYLIST)).replay().build();
    }

    private Pager<List<RecommendedPlaylistItem>> getPlaylists(RecommendationProvider recommendationProvider, RecommendationRequest recommendationRequest, RecommendationRequestPageOptions recommendationRequestPageOptions) {
        Validate.notNull(recommendationRequest, "request can't be null", new Object[0]);
        return Pager.create(new RecommendationPagerIterator(recommendationProvider, recommendationRequest, RecommendationType.PLAYLIST, recommendationRequestPageOptions)).replay().build();
    }

    private Pager<List<RecommendedStationItem>> getStations(RecommendationProvider recommendationProvider, RecommendationRequest recommendationRequest) {
        Validate.notNull(recommendationRequest, "request can't be null", new Object[0]);
        return Pager.create(new RecommendationPagerIterator(recommendationProvider, recommendationRequest, RecommendationType.STATION)).replay().build();
    }

    private Pager<List<RecommendedStationItem>> getStations(RecommendationProvider recommendationProvider, RecommendationRequest recommendationRequest, RecommendationRequestPageOptions recommendationRequestPageOptions) {
        Validate.notNull(recommendationRequest, "request can't be null", new Object[0]);
        return Pager.create(new RecommendationPagerIterator(recommendationProvider, recommendationRequest, RecommendationType.STATION)).replay().build();
    }
}
