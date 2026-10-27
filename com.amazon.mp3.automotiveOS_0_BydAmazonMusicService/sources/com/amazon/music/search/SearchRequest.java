package com.amazon.music.search;

import androidx.annotation.NonNull;
import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.ResultItem;
import com.amazon.tenzing.textsearch.v1_1.FeaturesRequest;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import com.amazon.tenzing.textsearch.v1_1.SpellCorrectionFeedback;
import com.amazon.tenzing.textsearch.v1_1.SpellCorrectionRequest;
import com.amazon.tenzing.textsearch.v1_1.UpsellRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes4.dex */
public abstract class SearchRequest<TResultItem extends ResultItem> {
    private final Builder.PagerCreator<TResultItem> mPagerCreator;
    private final List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> mRequests;
    private final String mTerm;

    public static abstract class Builder<TBuilder extends Builder, TResultItem extends ResultItem, TSearchRequest extends SearchRequest<TResultItem>> {
        private final String mTerm;
        private SearchContentTier searchContentTier = null;
        private int mPageSize = 10;
        private boolean mIsExplicitFilterEnabled = false;
        private List<String> mAssetQualities = new ArrayList();
        private boolean mIsSpellCorrectionsSupported = false;
        private SpellCorrections mSpellCorrections = null;
        private boolean mWithTopHits = true;
        private boolean mWithAlbums = true;
        private boolean mWithPlaylists = true;
        private boolean mWithCommunityPlayLists = false;
        private boolean mWithStations = true;
        private boolean mWithTracks = true;
        private boolean mWithArtists = true;
        private boolean mWithSports = true;
        private boolean mWithVideos = false;
        private boolean mWithVideoPlaylists = false;
        private boolean mWithPodcasts = false;
        private boolean mWithLiveEvents = false;
        private boolean mWithLibrary = true;
        private boolean isUpsellToUnlimitedOnTopHitsTurnedOn = false;
        private boolean mWithExplicitLanguageFilter = false;
        private boolean mWithChildFriendlyContentFilter = false;
        private String mQueryMetadata = null;

        protected interface PagerCreator<TResultItem extends ResultItem> {
            PagerIterator<PageResult<TResultItem>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification);
        }

        protected abstract List<ResultSpecification> buildSpecs(ResultType resultType);

        protected abstract TSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, PagerCreator<TResultItem> pagerCreator, String str);

        protected abstract PagerCreator<TResultItem> getPagerCreator();

        public String getQueryMetadata() {
            return this.mQueryMetadata;
        }

        public Builder(String str) {
            this.mTerm = str;
        }

        void enableSpellCorrections() {
            this.mIsSpellCorrectionsSupported = true;
        }

        TBuilder withSpellCorrections(SpellCorrections spellCorrections) {
            this.mSpellCorrections = spellCorrections;
            return this;
        }

        public TBuilder withPageSize(int i) {
            this.mPageSize = i;
            return this;
        }

        public String getTerm() {
            return this.mTerm;
        }

        protected int getPageSize() {
            return this.mPageSize;
        }

        public TBuilder withExplicitLanguageFilter(boolean z) {
            this.mWithExplicitLanguageFilter = z;
            return this;
        }

        public TBuilder withChildFriendlyContentFilter(boolean z) {
            this.mWithChildFriendlyContentFilter = z;
            return this;
        }

        public TBuilder setIsExplicitFilterEnabled(boolean z) {
            this.mIsExplicitFilterEnabled = z;
            return this;
        }

        public TBuilder setAssetQualities(List<String> list) {
            this.mAssetQualities = list;
            return this;
        }

        public TBuilder withVideos(boolean z) {
            this.mWithVideos = z;
            return this;
        }

        protected boolean isWithVideos() {
            return this.mWithVideos;
        }

        public TBuilder withVideoPlaylists(boolean z) {
            this.mWithVideoPlaylists = z;
            return this;
        }

        protected boolean isWithVideoPlaylists() {
            return this.mWithVideoPlaylists;
        }

        public TBuilder withPodcasts(boolean z) {
            this.mWithPodcasts = z;
            return this;
        }

        protected boolean isWithPodcasts() {
            return this.mWithPodcasts;
        }

        public TBuilder withLiveEvents(boolean z) {
            this.mWithLiveEvents = z;
            return this;
        }

        protected boolean isWithLiveEvents() {
            return this.mWithLiveEvents;
        }

        public TBuilder withTopHits(boolean z) {
            this.mWithTopHits = z;
            return this;
        }

        protected boolean isWithTopHits() {
            return this.mWithTopHits;
        }

        public TBuilder withArtists(boolean z) {
            this.mWithArtists = z;
            return this;
        }

        protected boolean isWithArtists() {
            return this.mWithArtists;
        }

        public TBuilder withAlbums(boolean z) {
            this.mWithAlbums = z;
            return this;
        }

        protected boolean isWithAlbums() {
            return this.mWithAlbums;
        }

        public TBuilder withTracks(boolean z) {
            this.mWithTracks = z;
            return this;
        }

        protected boolean isWithTracks() {
            return this.mWithTracks;
        }

        public TBuilder withPlaylists(boolean z) {
            this.mWithPlaylists = z;
            return this;
        }

        protected boolean isWithPlaylists() {
            return this.mWithPlaylists;
        }

        public TBuilder withCommunityPlayLists(boolean z) {
            this.mWithCommunityPlayLists = z;
            return this;
        }

        protected boolean isWithCommunityPlaylists() {
            return this.mWithCommunityPlayLists;
        }

        public TBuilder withStations(boolean z) {
            this.mWithStations = z;
            return this;
        }

        protected boolean isWithStations() {
            return this.mWithStations;
        }

        public TBuilder withSports(boolean z) {
            this.mWithSports = z;
            return this;
        }

        protected boolean isWithSports() {
            return this.mWithSports;
        }

        public TBuilder withSearchContentTier(@NonNull SearchContentTier searchContentTier) {
            this.searchContentTier = searchContentTier;
            return this;
        }

        public TBuilder withLibrary(boolean z) {
            this.mWithLibrary = z;
            return this;
        }

        protected boolean isWithLibrary() {
            return this.mWithLibrary;
        }

        protected SearchContentTier getSearchContentTier() {
            return this.searchContentTier;
        }

        public TBuilder withUpsellToUnlimitedOnTopHitsTurnedOn(boolean z) {
            this.isUpsellToUnlimitedOnTopHitsTurnedOn = z;
            return this;
        }

        public TBuilder withQueryMetadata(String str) {
            this.mQueryMetadata = str;
            return this;
        }

        protected boolean isUpsellToUnlimitedOnTopHitsTurnedOn() {
            return this.isUpsellToUnlimitedOnTopHitsTurnedOn;
        }

        protected boolean isWithExplicitLanguageFilter() {
            return this.mWithExplicitLanguageFilter;
        }

        protected boolean isWithChildFriendlyContentFilter() {
            return this.mWithChildFriendlyContentFilter;
        }

        protected boolean isExplicitFilterEnabled() {
            return this.mIsExplicitFilterEnabled;
        }

        protected List<String> getAssetQualities() {
            return this.mAssetQualities;
        }

        public FeaturesRequest.Builder getFeatureBuilder() {
            FeaturesRequest.Builder builder = FeaturesRequest.builder();
            if (this.mIsSpellCorrectionsSupported) {
                SpellCorrectionRequest.Builder builder2 = SpellCorrectionRequest.builder();
                SpellCorrections spellCorrections = this.mSpellCorrections;
                if (spellCorrections != null && spellCorrections.hasCorrection()) {
                    if (this.mSpellCorrections.hasDidYouMeanCorrection()) {
                        builder2.withAccepted(SpellCorrectionFeedback.builder().withId(this.mSpellCorrections.getDidYouMeanCorrectionId()).build());
                    } else if (this.mSpellCorrections.hasAutoCorrection()) {
                        builder2.withRejected(SpellCorrectionFeedback.builder().withId(this.mSpellCorrections.getAutoCorrectionId()).build());
                    }
                } else {
                    builder2.withAllowCorrection(Boolean.TRUE);
                }
                FeaturesRequest.Builder builderWithSpellCorrection = builder.withSpellCorrection(builder2.build());
                new UpsellRequest();
                builderWithSpellCorrection.withUpsell(UpsellRequest.builder().withAllowUpsellForCatalogContent(Boolean.valueOf(isUpsellToUnlimitedOnTopHitsTurnedOn())).build());
            }
            return builder;
        }

        public TSearchRequest build() {
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            LinkedList linkedList3 = new LinkedList();
            for (ResultSpecification resultSpecification : buildSpecs(ResultType.Catalog)) {
                if (resultSpecification != null) {
                    linkedList.add(resultSpecification);
                }
            }
            for (ResultSpecification resultSpecification2 : buildSpecs(ResultType.Sports)) {
                if (resultSpecification2 != null) {
                    linkedList.add(resultSpecification2);
                }
            }
            if (isWithLiveEvents()) {
                for (ResultSpecification resultSpecification3 : buildSpecs(ResultType.LiveEvent)) {
                    if (resultSpecification3 != null) {
                        linkedList.add(resultSpecification3);
                    }
                }
            }
            if (isWithLibrary()) {
                for (ResultSpecification resultSpecification4 : buildSpecs(ResultType.Library)) {
                    if (resultSpecification4 != null) {
                        linkedList2.add(resultSpecification4);
                    }
                }
            }
            if (isWithPodcasts()) {
                for (ResultSpecification resultSpecification5 : buildSpecs(ResultType.Podcast_Catalog)) {
                    if (resultSpecification5 != null) {
                        linkedList3.add(resultSpecification5);
                    }
                }
            }
            LinkedList linkedList4 = new LinkedList();
            FeaturesRequest.Builder featureBuilder = getFeatureBuilder();
            if (!linkedList2.isEmpty()) {
                linkedList4.add(com.amazon.tenzing.textsearch.v1_1.SearchRequest.builder().withQuery(this.mTerm).withFeatures(featureBuilder.build()).withResultSpecs(linkedList2).withQueryMetadata(this.mQueryMetadata).build());
            }
            if (!linkedList.isEmpty()) {
                linkedList4.add(com.amazon.tenzing.textsearch.v1_1.SearchRequest.builder().withQuery(this.mTerm).withFeatures(featureBuilder.build()).withResultSpecs(linkedList).withQueryMetadata(this.mQueryMetadata).build());
            } else if (!linkedList3.isEmpty()) {
                linkedList4.add(com.amazon.tenzing.textsearch.v1_1.SearchRequest.builder().withQuery(this.mTerm).withFeatures(featureBuilder.build()).withResultSpecs(linkedList3).withQueryMetadata(this.mQueryMetadata).build());
            }
            return (TSearchRequest) create(linkedList4, getPagerCreator(), this.mTerm);
        }
    }

    SearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, Builder.PagerCreator<TResultItem> pagerCreator, String str) {
        this.mPagerCreator = pagerCreator;
        this.mRequests = list;
        this.mTerm = str;
    }

    List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> getRequests() {
        return this.mRequests;
    }

    String getTerm() {
        return this.mTerm;
    }

    PagerIterator<PageResult<TResultItem>> getPagerIterator(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
        return this.mPagerCreator.create(searchService, searchRequest, resultSpecification);
    }
}
