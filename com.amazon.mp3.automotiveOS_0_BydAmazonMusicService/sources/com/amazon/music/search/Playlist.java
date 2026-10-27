package com.amazon.music.search;

import UpsellInterface.v1_0.UpsellType;
import androidx.annotation.Nullable;
import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.model.Art;
import com.amazon.music.platform.model.ArtURL;
import com.amazon.music.platform.model.CatalogPlaylist;
import com.amazon.music.platform.model.LanguageOfPerformance;
import com.amazon.music.platform.model.LibraryPlaylist;
import com.amazon.tenzing.textsearch.v1_1.Hit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes4.dex */
public final class Playlist implements ResultItem {
    private final String mBlockRef;
    private Set<ContentAccessType> mContentAccess = null;
    private final Map<String, String> mContentInfo;
    private final com.amazon.music.platform.model.Playlist mPlaylist;

    public enum AdditionalFields implements com.amazon.music.search.AdditionalFields {
        Image("artOriginal", "artOriginal"),
        PlaymodeEligibilityOnDemand("playmodeEligibility.onDemand", ""),
        PlaymodeEligibilitySFA("playmodeEligibility.sfa", ""),
        PlaymodeEligibilityShuffleType("playmodeEligibility.shuffleType", ""),
        LanguageOfPerformance("languageOfPerformance", "");

        private final String mCatalogField;
        private final String mLibraryField;

        AdditionalFields(String str, String str2) {
            this.mCatalogField = str;
            this.mLibraryField = str2;
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getCatalogField() {
            return this.mCatalogField;
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getLibraryField() {
            return this.mLibraryField;
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getCommunityField() {
            throw new UnsupportedOperationException("There are no community playlists.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getSoccerField() {
            throw new UnsupportedOperationException("There are no soccer playlists.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getPodcastCatalogField() {
            throw new UnsupportedOperationException("There are no podcast catalog playlists.");
        }
    }

    Playlist(Hit hit, Map<String, String> map) {
        this.mPlaylist = (com.amazon.music.platform.model.Playlist) hit.getDocument();
        this.mContentInfo = map;
        this.mBlockRef = hit.getBlockRef();
    }

    @Nullable
    public String getLibraryId() {
        com.amazon.music.platform.model.Playlist playlist = this.mPlaylist;
        if (playlist instanceof LibraryPlaylist) {
            return ((LibraryPlaylist) playlist).getPlaylistId();
        }
        return null;
    }

    public String getTitle() {
        return this.mPlaylist.getTitle();
    }

    @Nullable
    public String getAsin() {
        com.amazon.music.platform.model.Playlist playlist = this.mPlaylist;
        if (playlist instanceof CatalogPlaylist) {
            return ((CatalogPlaylist) playlist).getAsin();
        }
        return null;
    }

    public Integer getTrackCount() {
        return this.mPlaylist.getTrackCount();
    }

    public Set<ContentAccessType> getContentAccess() {
        if (this.mContentAccess == null) {
            HashSet hashSet = new HashSet();
            List<String> contentTier = this.mPlaylist.getContentTier();
            if (contentTier != null) {
                if ((this.mPlaylist instanceof LibraryPlaylist) && !contentTier.contains(UpsellType.PRIME) && !contentTier.contains("SONIC_RUSH") && !contentTier.contains(UpsellType.UNLIMITED) && !contentTier.contains("NIGHTWING") && !contentTier.contains("NIGHTWING_ONDEMAND_PLAYABLE")) {
                    hashSet.add(ContentAccessType.OWNED);
                }
                if (contentTier.contains(UpsellType.PRIME)) {
                    hashSet.add(ContentAccessType.PRIME);
                }
                if (contentTier.contains("SONIC_RUSH")) {
                    hashSet.add(ContentAccessType.SONIC_RUSH);
                }
                if (contentTier.contains(UpsellType.UNLIMITED)) {
                    hashSet.add(ContentAccessType.HAWKFIRE);
                }
                if (contentTier.contains("NIGHTWING")) {
                    hashSet.add(ContentAccessType.NIGHTWING);
                }
                if (contentTier.contains("NIGHTWING_ONDEMAND_PLAYABLE")) {
                    hashSet.add(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE);
                }
            }
            if (hashSet.isEmpty()) {
                this.mContentAccess = EnumSet.noneOf(ContentAccessType.class);
            } else {
                this.mContentAccess = EnumSet.copyOf((Collection) hashSet);
            }
        }
        return this.mContentAccess;
    }

    @Nullable
    public String getImage() {
        Art artOriginal = this.mPlaylist.getArtOriginal();
        if (artOriginal == null || !(artOriginal instanceof ArtURL)) {
            return null;
        }
        return ((ArtURL) artOriginal).getArtUrl();
    }

    @Override // com.amazon.music.search.ResultItem
    public Map<String, String> getContentInfo() {
        return this.mContentInfo;
    }

    @Override // com.amazon.music.search.ResultItem
    public String getBlockRef() {
        return this.mBlockRef;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        com.amazon.music.platform.model.PlaymodeEligibility playmodeEligibility;
        com.amazon.music.platform.model.Playlist playlist = this.mPlaylist;
        if (!(playlist instanceof CatalogPlaylist) || (playmodeEligibility = ((CatalogPlaylist) playlist).getPlaymodeEligibility()) == null) {
            return null;
        }
        return new PlaymodeEligibility(playmodeEligibility);
    }

    public List<String> getLanguageOfPerformances() {
        List<LanguageOfPerformance> languageOfPerformances;
        com.amazon.music.platform.model.Playlist playlist = this.mPlaylist;
        if ((playlist instanceof CatalogPlaylist) && (languageOfPerformances = ((CatalogPlaylist) playlist).getLanguageOfPerformances()) != null) {
            return (List) languageOfPerformances.stream().filter(new Album$$ExternalSyntheticLambda0()).map(new Album$$ExternalSyntheticLambda1()).collect(Collectors.toList());
        }
        return new ArrayList();
    }
}
