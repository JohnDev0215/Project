package com.amazon.music.search;

import UpsellInterface.v1_0.UpsellType;
import androidx.annotation.Nullable;
import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.model.Art;
import com.amazon.music.platform.model.ArtURL;
import com.amazon.tenzing.textsearch.v1_1.Hit;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class CommunityPlaylist implements ResultItem {
    private final String mBlockRef;
    private Set<ContentAccessType> mContentAccess = null;
    private final Map<String, String> mContentInfo;
    private final com.amazon.music.platform.model.CommunityPlaylist mPlaylist;

    public enum AdditionalFields implements com.amazon.music.search.AdditionalFields {
        Image("artOriginal"),
        Curator("curator"),
        Title("title"),
        PlaylistId("playlistId"),
        TrackCount("trackCount"),
        PlaymodeEligibilityOnDemand("playmodeEligibility.onDemand"),
        PlaymodeEligibilitySFA("playmodeEligibility.sfa"),
        PlaymodeEligibilityShuffleType("playmodeEligibility.shuffleType"),
        ContentTier("contentTier");

        private final String mCommunityField;

        AdditionalFields(String str) {
            this.mCommunityField = str;
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getCatalogField() {
            throw new UnsupportedOperationException("There are no catalog playlists.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getLibraryField() {
            throw new UnsupportedOperationException("There are no library playlists.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getCommunityField() {
            return this.mCommunityField;
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

    CommunityPlaylist(Hit hit, Map<String, String> map) {
        this.mPlaylist = (com.amazon.music.platform.model.CommunityPlaylist) hit.getDocument();
        this.mContentInfo = map;
        this.mBlockRef = hit.getBlockRef();
    }

    public String getTitle() {
        return this.mPlaylist.getTitle();
    }

    public int getTrackCount() {
        if (this.mPlaylist.getTrackCount() == null) {
            return 0;
        }
        return this.mPlaylist.getTrackCount().intValue();
    }

    public Set<ContentAccessType> getContentAccess() {
        if (this.mContentAccess == null) {
            HashSet hashSet = new HashSet();
            List<String> contentTier = this.mPlaylist.getContentTier();
            if (contentTier != null) {
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

    public String getPlaylistId() {
        return this.mPlaylist.getPlaylistId();
    }

    public String getCurator() {
        return this.mPlaylist.getCurator();
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        com.amazon.music.platform.model.PlaymodeEligibility playmodeEligibility = this.mPlaylist.getPlaymodeEligibility();
        if (playmodeEligibility == null) {
            return null;
        }
        return new PlaymodeEligibility(playmodeEligibility);
    }
}
