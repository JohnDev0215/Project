package com.amazon.music.search;

import com.amazon.music.search.Album;
import com.amazon.music.search.Artist;
import com.amazon.music.search.CommunityPlaylist;
import com.amazon.music.search.Competition;
import com.amazon.music.search.Playlist;
import com.amazon.music.search.PodcastEpisode;
import com.amazon.music.search.PodcastShow;
import com.amazon.music.search.Program;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.Station;
import com.amazon.music.search.Track;
import com.amazon.music.search.Video;
import com.amazon.music.search.VideoPlaylist;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes4.dex */
public abstract class MultiSearchRequest extends SearchRequest<ResultItem> {

    public static abstract class Builder<TBuilder extends Builder, TSearchRequest extends MultiSearchRequest> extends SearchRequest.Builder<TBuilder, ResultItem, TSearchRequest> {
        private final List<Album.AdditionalFields> mAlbumAdditionals;
        private final List<Artist.AdditionalFields> mArtistAdditionals;
        private AssetQualitiesProvider mAssetQualitiesProvider;
        private final List<CommunityPlaylist.AdditionalFields> mCommunityPlayListAdditionals;
        private final List<Competition.AdditionalFields> mCompetitionAdditionals;
        private final List<Object> mLiveEventAdditionals;
        private final List<Playlist.AdditionalFields> mPlaylistAdditionals;
        private final List<PodcastEpisode.AdditionalFields> mPodcastEpisodeAdditionals;
        private final List<PodcastShow.AdditionalFields> mPodcastShowAdditionals;
        private final List<Program.AdditionalFields> mProgramAdditionals;
        private final List<Station.AdditionalFields> mStationAdditionals;
        private final List<Track.AdditionalFields> mTrackAdditionals;
        private final List<Video.AdditionalFields> mVideoAdditionals;
        private final List<VideoPlaylist.AdditionalFields> mVideoPlaylistAdditionals;

        public Builder(String str) {
            super(str);
            this.mAlbumAdditionals = new LinkedList();
            this.mArtistAdditionals = new LinkedList();
            this.mPlaylistAdditionals = new LinkedList();
            this.mCommunityPlayListAdditionals = new LinkedList();
            this.mStationAdditionals = new LinkedList();
            this.mTrackAdditionals = new LinkedList();
            this.mProgramAdditionals = new LinkedList();
            this.mCompetitionAdditionals = new LinkedList();
            this.mVideoAdditionals = new LinkedList();
            this.mVideoPlaylistAdditionals = new LinkedList();
            this.mPodcastShowAdditionals = new LinkedList();
            this.mPodcastEpisodeAdditionals = new LinkedList();
            this.mLiveEventAdditionals = new LinkedList();
            this.mAssetQualitiesProvider = null;
        }

        protected List<Album.AdditionalFields> getAlbumAdditionals() {
            return this.mAlbumAdditionals;
        }

        protected List<Artist.AdditionalFields> getArtistAdditionals() {
            return this.mArtistAdditionals;
        }

        protected List<Playlist.AdditionalFields> getPlaylistAdditionals() {
            return this.mPlaylistAdditionals;
        }

        protected List<CommunityPlaylist.AdditionalFields> getCommunityPlaylistAdditionals() {
            return this.mCommunityPlayListAdditionals;
        }

        protected List<Station.AdditionalFields> getStationAdditionals() {
            return this.mStationAdditionals;
        }

        protected List<Track.AdditionalFields> getTrackAdditionals() {
            return this.mTrackAdditionals;
        }

        protected List<Program.AdditionalFields> getProgramAdditionals() {
            return this.mProgramAdditionals;
        }

        protected List<Video.AdditionalFields> getVideoAdditionals() {
            return this.mVideoAdditionals;
        }

        protected List<VideoPlaylist.AdditionalFields> getVideoPlaylistAdditionals() {
            return this.mVideoPlaylistAdditionals;
        }

        protected List<Competition.AdditionalFields> getCompetitionAdditionals() {
            return this.mCompetitionAdditionals;
        }

        protected List<PodcastShow.AdditionalFields> getPodcastShowAdditionals() {
            return this.mPodcastShowAdditionals;
        }

        protected List<PodcastEpisode.AdditionalFields> getPodcastEpisodeAdditionals() {
            return this.mPodcastEpisodeAdditionals;
        }

        protected List<Object> getLiveEventAdditionals() {
            return this.mLiveEventAdditionals;
        }

        public <TAdditionalFields extends AdditionalFields> TBuilder withAdditionalFields(List<TAdditionalFields> list, TAdditionalFields... tadditionalfieldsArr) {
            list.clear();
            Collections.addAll(list, tadditionalfieldsArr);
            return this;
        }

        public TBuilder withAdditionalFields(Album.AdditionalFields... additionalFieldsArr) {
            return (TBuilder) withAdditionalFields(this.mAlbumAdditionals, additionalFieldsArr);
        }

        public TBuilder withAdditionalFields(Artist.AdditionalFields... additionalFieldsArr) {
            return (TBuilder) withAdditionalFields(this.mArtistAdditionals, additionalFieldsArr);
        }

        public TBuilder withAdditionalFields(Playlist.AdditionalFields... additionalFieldsArr) {
            return (TBuilder) withAdditionalFields(this.mPlaylistAdditionals, additionalFieldsArr);
        }

        public TBuilder withAdditionalFields(CommunityPlaylist.AdditionalFields... additionalFieldsArr) {
            return (TBuilder) withAdditionalFields(this.mCommunityPlayListAdditionals, additionalFieldsArr);
        }

        public TBuilder withAdditionalFields(Station.AdditionalFields... additionalFieldsArr) {
            return (TBuilder) withAdditionalFields(this.mStationAdditionals, additionalFieldsArr);
        }

        public TBuilder withAdditionalFields(Track.AdditionalFields... additionalFieldsArr) {
            return (TBuilder) withAdditionalFields(this.mTrackAdditionals, additionalFieldsArr);
        }

        public TBuilder withAdditionalFields(Program.AdditionalFields... additionalFieldsArr) {
            return (TBuilder) withAdditionalFields(this.mProgramAdditionals, additionalFieldsArr);
        }

        public TBuilder withAdditionalFields(Competition.AdditionalFields... additionalFieldsArr) {
            return (TBuilder) withAdditionalFields(this.mCompetitionAdditionals, additionalFieldsArr);
        }

        public TBuilder withAdditionalFields(Video.AdditionalFields... additionalFieldsArr) {
            return (TBuilder) withAdditionalFields(this.mVideoAdditionals, additionalFieldsArr);
        }

        public TBuilder withAdditionalFields(VideoPlaylist.AdditionalFields... additionalFieldsArr) {
            return (TBuilder) withAdditionalFields(this.mVideoPlaylistAdditionals, additionalFieldsArr);
        }

        public TBuilder withAdditionalFields(PodcastShow.AdditionalFields... additionalFieldsArr) {
            return (TBuilder) withAdditionalFields(this.mPodcastShowAdditionals, additionalFieldsArr);
        }

        public TBuilder withAdditionalFields(PodcastEpisode.AdditionalFields... additionalFieldsArr) {
            return (TBuilder) withAdditionalFields(this.mPodcastEpisodeAdditionals, additionalFieldsArr);
        }

        public TBuilder withAssetQualitiesProvider(AssetQualitiesProvider assetQualitiesProvider) {
            this.mAssetQualitiesProvider = assetQualitiesProvider;
            return this;
        }

        protected AssetQualitiesProvider getAssetQualitiesProvider() {
            return this.mAssetQualitiesProvider;
        }

        protected List<String> getAssetQualitiesForSearchType(ResultType resultType, String str) {
            AssetQualitiesProvider assetQualitiesProvider = this.mAssetQualitiesProvider;
            if (assetQualitiesProvider != null) {
                List<String> assetQualities = assetQualitiesProvider.getAssetQualities(resultType, str);
                return assetQualities != null ? assetQualities : getAssetQualities();
            }
            return getAssetQualities();
        }
    }

    MultiSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<ResultItem> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
