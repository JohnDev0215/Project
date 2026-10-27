package com.amazon.music.search;

import UpsellInterface.v1_0.UpsellType;
import androidx.annotation.Nullable;
import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.model.Art;
import com.amazon.music.platform.model.ArtURL;
import com.amazon.music.platform.model.CatalogVideoPlaylist;
import com.amazon.tenzing.textsearch.v1_1.Hit;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class VideoPlaylist implements ResultItem {
    private final String mBlockRef;
    private Set<ContentAccessType> mContentAccess = null;
    private final Map<String, String> mContentInfo;
    private final CatalogVideoPlaylist mVideoPlaylist;

    public enum AdditionalFields implements com.amazon.music.search.AdditionalFields {
        Asin("asin", "asin"),
        Title("title", "title"),
        Image("artOriginal", "artOriginal");

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
        public String getSoccerField() {
            throw new UnsupportedOperationException("There are no soccer tracks.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getPodcastCatalogField() {
            throw new UnsupportedOperationException("There are no podcast catalog video playlists.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getCommunityField() {
            throw new UnsupportedOperationException("There are no community video playlists.");
        }
    }

    VideoPlaylist(Hit hit, Map<String, String> map) {
        this.mVideoPlaylist = (CatalogVideoPlaylist) hit.getDocument();
        this.mContentInfo = map;
        this.mBlockRef = hit.getBlockRef();
    }

    @Override // com.amazon.music.search.ResultItem
    public Map<String, String> getContentInfo() {
        return this.mContentInfo;
    }

    @Override // com.amazon.music.search.ResultItem
    public String getBlockRef() {
        return this.mBlockRef;
    }

    public String getTitle() {
        return this.mVideoPlaylist.getTitle();
    }

    @Nullable
    public String getAsin() {
        return this.mVideoPlaylist.getAsin();
    }

    public Set<ContentAccessType> getContentAccess() {
        if (this.mContentAccess == null) {
            HashSet hashSet = new HashSet();
            List<String> contentTier = this.mVideoPlaylist.getContentTier();
            if (contentTier != null) {
                if (!contentTier.contains(UpsellType.PRIME) && !contentTier.contains(UpsellType.UNLIMITED) && !contentTier.contains("NIGHTWING") && !contentTier.contains("NIGHTWING_ONDEMAND_PLAYABLE")) {
                    hashSet.add(ContentAccessType.OWNED);
                }
                if (contentTier.contains(UpsellType.PRIME)) {
                    hashSet.add(ContentAccessType.PRIME);
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
        Art artOriginal = this.mVideoPlaylist.getArtOriginal();
        if (artOriginal == null || !(artOriginal instanceof ArtURL)) {
            return null;
        }
        return ((ArtURL) artOriginal).getArtUrl();
    }
}
