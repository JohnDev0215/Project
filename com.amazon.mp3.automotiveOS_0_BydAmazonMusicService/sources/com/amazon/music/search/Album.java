package com.amazon.music.search;

import UpsellInterface.v1_0.UpsellType;
import androidx.annotation.Nullable;
import com.amazon.music.ContentAccessType;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.model.Art;
import com.amazon.music.platform.model.ArtURL;
import com.amazon.music.platform.model.CatalogAlbum;
import com.amazon.music.platform.model.LanguageOfPerformance;
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
public final class Album implements ResultItem {
    private final com.amazon.music.platform.model.Album mAlbum;
    private final String mBlockRef;
    private Set<ContentAccessType> mContentAccess = null;
    private final Map<String, String> mContentInfo;

    public enum AdditionalFields implements com.amazon.music.search.AdditionalFields {
        Image("artOriginal", "artOriginal"),
        ContentEncoding(ContextMappingConstants.CONTENT_ENCODING, ""),
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
        public String getSoccerField() {
            throw new UnsupportedOperationException("There are no soccer albums.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getPodcastCatalogField() {
            throw new UnsupportedOperationException("There are no podcast catalog albums.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getCommunityField() {
            throw new UnsupportedOperationException("There are no community album.");
        }
    }

    Album(Hit hit, Map<String, String> map) {
        this.mAlbum = (com.amazon.music.platform.model.Album) hit.getDocument();
        this.mContentInfo = map;
        this.mBlockRef = hit.getBlockRef();
    }

    public String getTitle() {
        return this.mAlbum.getTitle();
    }

    @Nullable
    public String getAsin() {
        return this.mAlbum.getAsin();
    }

    public String getArtistName() {
        return this.mAlbum.getArtistName();
    }

    @Nullable
    public String getArtistAsin() {
        return this.mAlbum.getArtistAsin();
    }

    public Integer getTrackCount() {
        return this.mAlbum.getTrackCount();
    }

    @Nullable
    public Set<ContentAccessType> getContentAccess() {
        if ((this.mAlbum instanceof CatalogAlbum) && this.mContentAccess == null) {
            HashSet hashSet = new HashSet();
            List<String> contentTier = this.mAlbum.getContentTier();
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
        Art artOriginal = this.mAlbum.getArtOriginal();
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

    public List<ContentEncoding> getContentEncoding() {
        return ContentEncodingDecoder.loadFrom(this.mAlbum.getContentEncoding());
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        com.amazon.music.platform.model.PlaymodeEligibility playmodeEligibility;
        com.amazon.music.platform.model.Album album = this.mAlbum;
        if (!(album instanceof CatalogAlbum) || (playmodeEligibility = ((CatalogAlbum) album).getPlaymodeEligibility()) == null) {
            return null;
        }
        return new PlaymodeEligibility(playmodeEligibility);
    }

    public List<String> getLanguageOfPerformances() {
        List<LanguageOfPerformance> languageOfPerformances;
        com.amazon.music.platform.model.Album album = this.mAlbum;
        if ((album instanceof CatalogAlbum) && (languageOfPerformances = ((CatalogAlbum) album).getLanguageOfPerformances()) != null) {
            return (List) languageOfPerformances.stream().filter(new Album$$ExternalSyntheticLambda0()).map(new Album$$ExternalSyntheticLambda1()).collect(Collectors.toList());
        }
        return new ArrayList();
    }
}
