package com.amazon.music.recents.model;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RecentlyPlayedTracksResponse {
    private String pageToken;
    private List<RecentlyPlayedTrack> recentTrackList;

    private RecentlyPlayedTracksResponse(List<RecentlyPlayedTrack> list, String str) {
        this.recentTrackList = list;
        this.pageToken = str;
    }

    public List<RecentlyPlayedTrack> getRecentTrackList() {
        return this.recentTrackList;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public static final class Builder {
        private String pageToken;
        private List<RecentlyPlayedTrack> recentTrackList;

        public final Builder recentTrackList(List<RecentlyPlayedTrack> list) {
            this.recentTrackList = list;
            return this;
        }

        public final Builder pageToken(String str) {
            this.pageToken = str;
            return this;
        }

        public final RecentlyPlayedTracksResponse build() {
            return new RecentlyPlayedTracksResponse(this.recentTrackList, this.pageToken);
        }
    }
}
