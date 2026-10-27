package com.amazon.music.find.model;

import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.search.SearchContentTier;
import com.amazon.music.search.SpellCorrections;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExecuteQueryRequest.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b;\b\u0086\b\u0018\u00002\u00020\u0001Bû\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\u0006\u0010\u0017\u001a\u00020\u0005\u0012\u0006\u0010\u0018\u001a\u00020\u0005\u0012\u0006\u0010\u0019\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u0005\u0012\u0006\u0010\u001b\u001a\u00020\u0005\u0012\u0006\u0010\u001c\u001a\u00020\u0005\u0012\u0006\u0010\u001d\u001a\u00020\u0005\u0012\u0006\u0010\u001e\u001a\u00020\u0005\u0012\u0006\u0010\u001f\u001a\u00020\u0005\u0012\u0006\u0010 \u001a\u00020\u0005\u0012\u0006\u0010!\u001a\u00020\u0005\u0012\u0006\u0010\"\u001a\u00020\u0005\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010$\u001a\u0004\u0018\u00010%¢\u0006\u0002\u0010&J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\t\u0010=\u001a\u00020\u0010HÆ\u0003J\t\u0010>\u001a\u00020\u0005HÆ\u0003J\t\u0010?\u001a\u00020\u0005HÆ\u0003J\t\u0010@\u001a\u00020\u0005HÆ\u0003J\t\u0010A\u001a\u00020\u0005HÆ\u0003J\t\u0010B\u001a\u00020\u0005HÆ\u0003J\t\u0010C\u001a\u00020\u0005HÆ\u0003J\t\u0010D\u001a\u00020\u0005HÆ\u0003J\t\u0010E\u001a\u00020\u0005HÆ\u0003J\t\u0010F\u001a\u00020\u0005HÆ\u0003J\t\u0010G\u001a\u00020\u0005HÆ\u0003J\t\u0010H\u001a\u00020\u0005HÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\t\u0010J\u001a\u00020\u0005HÆ\u0003J\t\u0010K\u001a\u00020\u0005HÆ\u0003J\t\u0010L\u001a\u00020\u0005HÆ\u0003J\t\u0010M\u001a\u00020\u0005HÆ\u0003J\t\u0010N\u001a\u00020\u0005HÆ\u0003J\t\u0010O\u001a\u00020\u0005HÆ\u0003J\t\u0010P\u001a\u00020\u0005HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010S\u001a\u0004\u0018\u00010%HÆ\u0003¢\u0006\u0002\u0010,J\t\u0010T\u001a\u00020\tHÆ\u0003J\t\u0010U\u001a\u00020\u0005HÆ\u0003J\t\u0010V\u001a\u00020\u0005HÆ\u0003J\t\u0010W\u001a\u00020\u0005HÆ\u0003J\t\u0010X\u001a\u00020\u0005HÆ\u0003J\t\u0010Y\u001a\u00020\u0005HÆ\u0003JÀ\u0002\u0010Z\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u00052\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u00052\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%HÆ\u0001¢\u0006\u0002\u0010[J\u0013\u0010\\\u001a\u00020\u00052\b\u0010]\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010^\u001a\u00020%H\u0016J\t\u0010_\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010(R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010(R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010(R\u0011\u0010\u001b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010(R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010(R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010(R\u0011\u0010\u001d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010(R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010(R\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010(R\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010(R\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010(R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010(R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010(R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010(R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010(R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010(R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010(R\u0015\u0010$\u001a\u0004\u0018\u00010%¢\u0006\n\n\u0002\u0010-\u001a\u0004\b+\u0010,R\u0013\u0010#\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u0010/R\u0011\u0010\u001f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010(R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u0010(R\u0011\u0010!\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010(R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010(R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010(R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;¨\u0006`"}, d2 = {"Lcom/amazon/music/find/model/ExecuteQueryRequest;", "", "searchTerm", "", "enableSpellCorrections", "", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "searchFilterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "isKatana", "isExplicitLanguageFilterEnabled", "filterByExplicitLanguage", "filterByChildFriendlyContent", "isUpsellToUnlimitedOnTopHitsTurnedOn", "searchContentTier", "Lcom/amazon/music/search/SearchContentTier;", "isVideoEnabled", "isVideoPlaylistEnabled", "isPodcastEnabled", "isTrackEnabled", "isAlbumEnabled", "isArtistEnabled", "isPlaylistEnabled", "isStationEnabled", "isSportEnabled", "isTopHitsEnabled", "isCommunityPlaylistEnabled", "isLiveEventEnabled", "isLanguageOfPerformanceEnabled", "shouldRequestTrackRelatedPlaylists", "shouldRequestAlbumPlaymodeEligibility", "shouldRequestTrackPlaymodeEligibility", "shouldRequestPlayListPlaymodeEligibility", "shouldRequestCommunityPlayListPlaymodeEligibility", PageUriUtils.QUERYMETADATA_QUERY_PARAM, "pageSize", "", "(Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/find/model/filter/SearchFilterSelections;ZZZZZLcom/amazon/music/search/SearchContentTier;ZZZZZZZZZZZZZZZZZZLjava/lang/String;Ljava/lang/Integer;)V", "getEnableSpellCorrections", "()Z", "getFilterByChildFriendlyContent", "getFilterByExplicitLanguage", "getPageSize", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getQueryMetadata", "()Ljava/lang/String;", "getSearchContentTier", "()Lcom/amazon/music/search/SearchContentTier;", "getSearchFilterSelections", "()Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "getSearchTerm", "getShouldRequestAlbumPlaymodeEligibility", "getShouldRequestCommunityPlayListPlaymodeEligibility", "getShouldRequestPlayListPlaymodeEligibility", "getShouldRequestTrackPlaymodeEligibility", "getShouldRequestTrackRelatedPlaylists", "getSpellCorrections", "()Lcom/amazon/music/search/SpellCorrections;", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/find/model/filter/SearchFilterSelections;ZZZZZLcom/amazon/music/search/SearchContentTier;ZZZZZZZZZZZZZZZZZZLjava/lang/String;Ljava/lang/Integer;)Lcom/amazon/music/find/model/ExecuteQueryRequest;", "equals", "other", "hashCode", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class ExecuteQueryRequest {
    private final boolean enableSpellCorrections;
    private final boolean filterByChildFriendlyContent;
    private final boolean filterByExplicitLanguage;
    private final boolean isAlbumEnabled;
    private final boolean isArtistEnabled;
    private final boolean isCommunityPlaylistEnabled;
    private final boolean isExplicitLanguageFilterEnabled;
    private final boolean isKatana;
    private final boolean isLanguageOfPerformanceEnabled;
    private final boolean isLiveEventEnabled;
    private final boolean isPlaylistEnabled;
    private final boolean isPodcastEnabled;
    private final boolean isSportEnabled;
    private final boolean isStationEnabled;
    private final boolean isTopHitsEnabled;
    private final boolean isTrackEnabled;
    private final boolean isUpsellToUnlimitedOnTopHitsTurnedOn;
    private final boolean isVideoEnabled;
    private final boolean isVideoPlaylistEnabled;
    private final Integer pageSize;
    private final String queryMetadata;
    private final SearchContentTier searchContentTier;
    private final SearchFilterSelections searchFilterSelections;
    private final String searchTerm;
    private final boolean shouldRequestAlbumPlaymodeEligibility;
    private final boolean shouldRequestCommunityPlayListPlaymodeEligibility;
    private final boolean shouldRequestPlayListPlaymodeEligibility;
    private final boolean shouldRequestTrackPlaymodeEligibility;
    private final boolean shouldRequestTrackRelatedPlaylists;
    private final SpellCorrections spellCorrections;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSearchTerm() {
        return this.searchTerm;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final SearchContentTier getSearchContentTier() {
        return this.searchContentTier;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getIsVideoEnabled() {
        return this.isVideoEnabled;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final boolean getIsVideoPlaylistEnabled() {
        return this.isVideoPlaylistEnabled;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final boolean getIsPodcastEnabled() {
        return this.isPodcastEnabled;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final boolean getIsTrackEnabled() {
        return this.isTrackEnabled;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final boolean getIsAlbumEnabled() {
        return this.isAlbumEnabled;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final boolean getIsArtistEnabled() {
        return this.isArtistEnabled;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final boolean getIsPlaylistEnabled() {
        return this.isPlaylistEnabled;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final boolean getIsStationEnabled() {
        return this.isStationEnabled;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final boolean getIsSportEnabled() {
        return this.isSportEnabled;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getEnableSpellCorrections() {
        return this.enableSpellCorrections;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final boolean getIsTopHitsEnabled() {
        return this.isTopHitsEnabled;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final boolean getIsCommunityPlaylistEnabled() {
        return this.isCommunityPlaylistEnabled;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final boolean getIsLiveEventEnabled() {
        return this.isLiveEventEnabled;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final boolean getIsLanguageOfPerformanceEnabled() {
        return this.isLanguageOfPerformanceEnabled;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final boolean getShouldRequestTrackRelatedPlaylists() {
        return this.shouldRequestTrackRelatedPlaylists;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final boolean getShouldRequestAlbumPlaymodeEligibility() {
        return this.shouldRequestAlbumPlaymodeEligibility;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final boolean getShouldRequestTrackPlaymodeEligibility() {
        return this.shouldRequestTrackPlaymodeEligibility;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final boolean getShouldRequestPlayListPlaymodeEligibility() {
        return this.shouldRequestPlayListPlaymodeEligibility;
    }

    /* JADX INFO: renamed from: component28, reason: from getter */
    public final boolean getShouldRequestCommunityPlayListPlaymodeEligibility() {
        return this.shouldRequestCommunityPlayListPlaymodeEligibility;
    }

    /* JADX INFO: renamed from: component29, reason: from getter */
    public final String getQueryMetadata() {
        return this.queryMetadata;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final SpellCorrections getSpellCorrections() {
        return this.spellCorrections;
    }

    /* JADX INFO: renamed from: component30, reason: from getter */
    public final Integer getPageSize() {
        return this.pageSize;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final SearchFilterSelections getSearchFilterSelections() {
        return this.searchFilterSelections;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsKatana() {
        return this.isKatana;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsExplicitLanguageFilterEnabled() {
        return this.isExplicitLanguageFilterEnabled;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getFilterByExplicitLanguage() {
        return this.filterByExplicitLanguage;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getFilterByChildFriendlyContent() {
        return this.filterByChildFriendlyContent;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getIsUpsellToUnlimitedOnTopHitsTurnedOn() {
        return this.isUpsellToUnlimitedOnTopHitsTurnedOn;
    }

    public final ExecuteQueryRequest copy(String searchTerm, boolean enableSpellCorrections, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, boolean isKatana, boolean isExplicitLanguageFilterEnabled, boolean filterByExplicitLanguage, boolean filterByChildFriendlyContent, boolean isUpsellToUnlimitedOnTopHitsTurnedOn, SearchContentTier searchContentTier, boolean isVideoEnabled, boolean isVideoPlaylistEnabled, boolean isPodcastEnabled, boolean isTrackEnabled, boolean isAlbumEnabled, boolean isArtistEnabled, boolean isPlaylistEnabled, boolean isStationEnabled, boolean isSportEnabled, boolean isTopHitsEnabled, boolean isCommunityPlaylistEnabled, boolean isLiveEventEnabled, boolean isLanguageOfPerformanceEnabled, boolean shouldRequestTrackRelatedPlaylists, boolean shouldRequestAlbumPlaymodeEligibility, boolean shouldRequestTrackPlaymodeEligibility, boolean shouldRequestPlayListPlaymodeEligibility, boolean shouldRequestCommunityPlayListPlaymodeEligibility, String queryMetadata, Integer pageSize) {
        Intrinsics.checkNotNullParameter(searchTerm, "searchTerm");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        Intrinsics.checkNotNullParameter(searchContentTier, "searchContentTier");
        return new ExecuteQueryRequest(searchTerm, enableSpellCorrections, spellCorrections, searchFilterSelections, isKatana, isExplicitLanguageFilterEnabled, filterByExplicitLanguage, filterByChildFriendlyContent, isUpsellToUnlimitedOnTopHitsTurnedOn, searchContentTier, isVideoEnabled, isVideoPlaylistEnabled, isPodcastEnabled, isTrackEnabled, isAlbumEnabled, isArtistEnabled, isPlaylistEnabled, isStationEnabled, isSportEnabled, isTopHitsEnabled, isCommunityPlaylistEnabled, isLiveEventEnabled, isLanguageOfPerformanceEnabled, shouldRequestTrackRelatedPlaylists, shouldRequestAlbumPlaymodeEligibility, shouldRequestTrackPlaymodeEligibility, shouldRequestPlayListPlaymodeEligibility, shouldRequestCommunityPlayListPlaymodeEligibility, queryMetadata, pageSize);
    }

    public String toString() {
        return "ExecuteQueryRequest(searchTerm=" + this.searchTerm + ", enableSpellCorrections=" + this.enableSpellCorrections + ", spellCorrections=" + this.spellCorrections + ", searchFilterSelections=" + this.searchFilterSelections + ", isKatana=" + this.isKatana + ", isExplicitLanguageFilterEnabled=" + this.isExplicitLanguageFilterEnabled + ", filterByExplicitLanguage=" + this.filterByExplicitLanguage + ", filterByChildFriendlyContent=" + this.filterByChildFriendlyContent + ", isUpsellToUnlimitedOnTopHitsTurnedOn=" + this.isUpsellToUnlimitedOnTopHitsTurnedOn + ", searchContentTier=" + this.searchContentTier + ", isVideoEnabled=" + this.isVideoEnabled + ", isVideoPlaylistEnabled=" + this.isVideoPlaylistEnabled + ", isPodcastEnabled=" + this.isPodcastEnabled + ", isTrackEnabled=" + this.isTrackEnabled + ", isAlbumEnabled=" + this.isAlbumEnabled + ", isArtistEnabled=" + this.isArtistEnabled + ", isPlaylistEnabled=" + this.isPlaylistEnabled + ", isStationEnabled=" + this.isStationEnabled + ", isSportEnabled=" + this.isSportEnabled + ", isTopHitsEnabled=" + this.isTopHitsEnabled + ", isCommunityPlaylistEnabled=" + this.isCommunityPlaylistEnabled + ", isLiveEventEnabled=" + this.isLiveEventEnabled + ", isLanguageOfPerformanceEnabled=" + this.isLanguageOfPerformanceEnabled + ", shouldRequestTrackRelatedPlaylists=" + this.shouldRequestTrackRelatedPlaylists + ", shouldRequestAlbumPlaymodeEligibility=" + this.shouldRequestAlbumPlaymodeEligibility + ", shouldRequestTrackPlaymodeEligibility=" + this.shouldRequestTrackPlaymodeEligibility + ", shouldRequestPlayListPlaymodeEligibility=" + this.shouldRequestPlayListPlaymodeEligibility + ", shouldRequestCommunityPlayListPlaymodeEligibility=" + this.shouldRequestCommunityPlayListPlaymodeEligibility + ", queryMetadata=" + ((Object) this.queryMetadata) + ", pageSize=" + this.pageSize + ')';
    }

    public ExecuteQueryRequest(String searchTerm, boolean z, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, SearchContentTier searchContentTier, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, boolean z20, boolean z21, boolean z22, boolean z23, boolean z24, String str, Integer num) {
        Intrinsics.checkNotNullParameter(searchTerm, "searchTerm");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        Intrinsics.checkNotNullParameter(searchContentTier, "searchContentTier");
        this.searchTerm = searchTerm;
        this.enableSpellCorrections = z;
        this.spellCorrections = spellCorrections;
        this.searchFilterSelections = searchFilterSelections;
        this.isKatana = z2;
        this.isExplicitLanguageFilterEnabled = z3;
        this.filterByExplicitLanguage = z4;
        this.filterByChildFriendlyContent = z5;
        this.isUpsellToUnlimitedOnTopHitsTurnedOn = z6;
        this.searchContentTier = searchContentTier;
        this.isVideoEnabled = z7;
        this.isVideoPlaylistEnabled = z8;
        this.isPodcastEnabled = z9;
        this.isTrackEnabled = z10;
        this.isAlbumEnabled = z11;
        this.isArtistEnabled = z12;
        this.isPlaylistEnabled = z13;
        this.isStationEnabled = z14;
        this.isSportEnabled = z15;
        this.isTopHitsEnabled = z16;
        this.isCommunityPlaylistEnabled = z17;
        this.isLiveEventEnabled = z18;
        this.isLanguageOfPerformanceEnabled = z19;
        this.shouldRequestTrackRelatedPlaylists = z20;
        this.shouldRequestAlbumPlaymodeEligibility = z21;
        this.shouldRequestTrackPlaymodeEligibility = z22;
        this.shouldRequestPlayListPlaymodeEligibility = z23;
        this.shouldRequestCommunityPlayListPlaymodeEligibility = z24;
        this.queryMetadata = str;
        this.pageSize = num;
    }

    public final String getSearchTerm() {
        return this.searchTerm;
    }

    public final boolean getEnableSpellCorrections() {
        return this.enableSpellCorrections;
    }

    public final SpellCorrections getSpellCorrections() {
        return this.spellCorrections;
    }

    public final SearchFilterSelections getSearchFilterSelections() {
        return this.searchFilterSelections;
    }

    public final boolean isKatana() {
        return this.isKatana;
    }

    public final boolean isExplicitLanguageFilterEnabled() {
        return this.isExplicitLanguageFilterEnabled;
    }

    public final boolean getFilterByExplicitLanguage() {
        return this.filterByExplicitLanguage;
    }

    public final boolean getFilterByChildFriendlyContent() {
        return this.filterByChildFriendlyContent;
    }

    public final boolean isUpsellToUnlimitedOnTopHitsTurnedOn() {
        return this.isUpsellToUnlimitedOnTopHitsTurnedOn;
    }

    public final SearchContentTier getSearchContentTier() {
        return this.searchContentTier;
    }

    public final boolean isVideoEnabled() {
        return this.isVideoEnabled;
    }

    public final boolean isVideoPlaylistEnabled() {
        return this.isVideoPlaylistEnabled;
    }

    public final boolean isPodcastEnabled() {
        return this.isPodcastEnabled;
    }

    public final boolean isTrackEnabled() {
        return this.isTrackEnabled;
    }

    public final boolean isAlbumEnabled() {
        return this.isAlbumEnabled;
    }

    public final boolean isArtistEnabled() {
        return this.isArtistEnabled;
    }

    public final boolean isPlaylistEnabled() {
        return this.isPlaylistEnabled;
    }

    public final boolean isStationEnabled() {
        return this.isStationEnabled;
    }

    public final boolean isSportEnabled() {
        return this.isSportEnabled;
    }

    public final boolean isTopHitsEnabled() {
        return this.isTopHitsEnabled;
    }

    public final boolean isCommunityPlaylistEnabled() {
        return this.isCommunityPlaylistEnabled;
    }

    public final boolean isLiveEventEnabled() {
        return this.isLiveEventEnabled;
    }

    public final boolean isLanguageOfPerformanceEnabled() {
        return this.isLanguageOfPerformanceEnabled;
    }

    public final boolean getShouldRequestTrackRelatedPlaylists() {
        return this.shouldRequestTrackRelatedPlaylists;
    }

    public final boolean getShouldRequestAlbumPlaymodeEligibility() {
        return this.shouldRequestAlbumPlaymodeEligibility;
    }

    public final boolean getShouldRequestTrackPlaymodeEligibility() {
        return this.shouldRequestTrackPlaymodeEligibility;
    }

    public final boolean getShouldRequestPlayListPlaymodeEligibility() {
        return this.shouldRequestPlayListPlaymodeEligibility;
    }

    public final boolean getShouldRequestCommunityPlayListPlaymodeEligibility() {
        return this.shouldRequestCommunityPlayListPlaymodeEligibility;
    }

    public final String getQueryMetadata() {
        return this.queryMetadata;
    }

    public final Integer getPageSize() {
        return this.pageSize;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(ExecuteQueryRequest.class, other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            ExecuteQueryRequest executeQueryRequest = (ExecuteQueryRequest) other;
            return Intrinsics.areEqual(this.searchTerm, executeQueryRequest.searchTerm) && this.enableSpellCorrections == executeQueryRequest.enableSpellCorrections && Intrinsics.areEqual(this.spellCorrections, executeQueryRequest.spellCorrections) && Intrinsics.areEqual(this.searchFilterSelections, executeQueryRequest.searchFilterSelections) && this.isKatana == executeQueryRequest.isKatana && this.isExplicitLanguageFilterEnabled == executeQueryRequest.isExplicitLanguageFilterEnabled && this.filterByExplicitLanguage == executeQueryRequest.filterByExplicitLanguage && this.filterByChildFriendlyContent == executeQueryRequest.filterByChildFriendlyContent && this.isUpsellToUnlimitedOnTopHitsTurnedOn == executeQueryRequest.isUpsellToUnlimitedOnTopHitsTurnedOn && this.searchContentTier == executeQueryRequest.searchContentTier && this.isVideoEnabled == executeQueryRequest.isVideoEnabled && this.isVideoPlaylistEnabled == executeQueryRequest.isVideoPlaylistEnabled && this.isPodcastEnabled == executeQueryRequest.isPodcastEnabled && this.isTrackEnabled == executeQueryRequest.isTrackEnabled && this.isAlbumEnabled == executeQueryRequest.isAlbumEnabled && this.isArtistEnabled == executeQueryRequest.isArtistEnabled && this.isPlaylistEnabled == executeQueryRequest.isPlaylistEnabled && this.isStationEnabled == executeQueryRequest.isStationEnabled && this.isSportEnabled == executeQueryRequest.isSportEnabled && this.isTopHitsEnabled == executeQueryRequest.isTopHitsEnabled && this.isCommunityPlaylistEnabled == executeQueryRequest.isCommunityPlaylistEnabled && this.isLiveEventEnabled == executeQueryRequest.isLiveEventEnabled && this.isLanguageOfPerformanceEnabled == executeQueryRequest.isLanguageOfPerformanceEnabled && Intrinsics.areEqual(this.queryMetadata, executeQueryRequest.queryMetadata) && this.shouldRequestTrackRelatedPlaylists == executeQueryRequest.shouldRequestTrackRelatedPlaylists && this.shouldRequestAlbumPlaymodeEligibility == executeQueryRequest.shouldRequestAlbumPlaymodeEligibility && this.shouldRequestTrackPlaymodeEligibility == executeQueryRequest.shouldRequestTrackPlaymodeEligibility && this.shouldRequestPlayListPlaymodeEligibility == executeQueryRequest.shouldRequestPlayListPlaymodeEligibility && this.shouldRequestCommunityPlayListPlaymodeEligibility == executeQueryRequest.shouldRequestCommunityPlayListPlaymodeEligibility;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.find.model.ExecuteQueryRequest");
    }

    public int hashCode() {
        int iHashCode = ((this.searchTerm.hashCode() * 31) + Boolean.hashCode(this.enableSpellCorrections)) * 31;
        SpellCorrections spellCorrections = this.spellCorrections;
        int iHashCode2 = (((((((((((((((((((((((((((((((((((((((((iHashCode + (spellCorrections == null ? 0 : spellCorrections.hashCode())) * 31) + this.searchFilterSelections.hashCode()) * 31) + Boolean.hashCode(this.isKatana)) * 31) + Boolean.hashCode(this.isExplicitLanguageFilterEnabled)) * 31) + Boolean.hashCode(this.filterByExplicitLanguage)) * 31) + Boolean.hashCode(this.filterByChildFriendlyContent)) * 31) + Boolean.hashCode(this.isUpsellToUnlimitedOnTopHitsTurnedOn)) * 31) + this.searchContentTier.hashCode()) * 31) + Boolean.hashCode(this.isVideoEnabled)) * 31) + Boolean.hashCode(this.isVideoPlaylistEnabled)) * 31) + Boolean.hashCode(this.isPodcastEnabled)) * 31) + Boolean.hashCode(this.isTrackEnabled)) * 31) + Boolean.hashCode(this.isAlbumEnabled)) * 31) + Boolean.hashCode(this.isArtistEnabled)) * 31) + Boolean.hashCode(this.isPlaylistEnabled)) * 31) + Boolean.hashCode(this.isStationEnabled)) * 31) + Boolean.hashCode(this.isSportEnabled)) * 31) + Boolean.hashCode(this.isTopHitsEnabled)) * 31) + Boolean.hashCode(this.isCommunityPlaylistEnabled)) * 31) + Boolean.hashCode(this.isLiveEventEnabled)) * 31) + Boolean.hashCode(this.isLanguageOfPerformanceEnabled)) * 31;
        String str = this.queryMetadata;
        return ((((((((((iHashCode2 + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.shouldRequestTrackRelatedPlaylists)) * 31) + Boolean.hashCode(this.shouldRequestAlbumPlaymodeEligibility)) * 31) + Boolean.hashCode(this.shouldRequestTrackPlaymodeEligibility)) * 31) + Boolean.hashCode(this.shouldRequestPlayListPlaymodeEligibility)) * 31) + Boolean.hashCode(this.shouldRequestCommunityPlayListPlaymodeEligibility);
    }
}
