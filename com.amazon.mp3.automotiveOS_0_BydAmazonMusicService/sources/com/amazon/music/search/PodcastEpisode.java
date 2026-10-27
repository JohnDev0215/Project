package com.amazon.music.search;

import UpsellInterface.v1_0.UpsellType;
import androidx.annotation.Nullable;
import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.model.Art;
import com.amazon.music.platform.model.ArtURL;
import com.amazon.music.platform.model.CatalogPodcastEpisode;
import com.amazon.music.platform.model.ParentalControls;
import com.amazon.tenzing.textsearch.v1_1.Hit;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class PodcastEpisode implements ResultItem {
    private final String mBlockRef;
    private Set<ContentAccessType> mContentAccess = null;
    private final Map<String, String> mContentInfo;
    private final CatalogPodcastEpisode mPodcastEpisode;

    public enum AdditionalFields implements com.amazon.music.search.AdditionalFields {
        Image("artOriginal"),
        Id("id"),
        TitleGlobal("title.GLOBAL"),
        ShowTitleGlobal("podcast.title.GLOBAL");

        private final String mCatalogPodcastField;

        AdditionalFields(String str) {
            this.mCatalogPodcastField = str;
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getCatalogField() {
            throw new UnsupportedOperationException("There are no Amazon Music Catalog podcast episodes.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getLibraryField() {
            throw new UnsupportedOperationException("There are no library podcast episodes.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getSoccerField() {
            throw new UnsupportedOperationException("There are no soccer podcast episodes.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getPodcastCatalogField() {
            return this.mCatalogPodcastField;
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getCommunityField() {
            throw new UnsupportedOperationException("There are no community podcast episodes.");
        }
    }

    public PodcastEpisode(Hit hit, Map<String, String> map) {
        this.mPodcastEpisode = (CatalogPodcastEpisode) hit.getDocument();
        this.mContentInfo = map;
        this.mBlockRef = hit.getBlockRef();
    }

    public String getId() {
        return this.mPodcastEpisode.getId();
    }

    public String getTitle() {
        return this.mPodcastEpisode.getTitle();
    }

    public String getPodcastId() {
        return this.mPodcastEpisode.getShow().getId();
    }

    public String getVariantId() {
        return this.mPodcastEpisode.getVariantId();
    }

    public String getPodcastVariantId() {
        return this.mPodcastEpisode.getShow().getVariantId();
    }

    public Date getPublishDate() {
        return this.mPodcastEpisode.getPublishDate();
    }

    @Nullable
    public String getSubtitle() {
        return this.mPodcastEpisode.getShow().getTitle();
    }

    @Nullable
    public Map<String, List<String>> getTierBenefits() {
        return this.mPodcastEpisode.getTierBenefit();
    }

    @Nullable
    public Map<String, List<String>> getShowTierBenefits() {
        return this.mPodcastEpisode.getShow().getTierBenefit();
    }

    @Nullable
    public Map<String, Date> getAvailabilityDisplayDate() {
        return this.mPodcastEpisode.getAvailabilityDisplayDate();
    }

    @Nullable
    public Set<ContentAccessType> getContentAccess() {
        if (this.mPodcastEpisode != null && this.mContentAccess == null) {
            HashSet hashSet = new HashSet();
            List<String> contentTier = this.mPodcastEpisode.getContentTier();
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

    public String getImage() {
        Art artOriginal = this.mPodcastEpisode.getArtOriginal();
        if (artOriginal instanceof ArtURL) {
            return ((ArtURL) artOriginal).getArtUrl();
        }
        return null;
    }

    public boolean isExplicit() {
        ParentalControls parentalControls = this.mPodcastEpisode.getParentalControls();
        return parentalControls != null && parentalControls.isHasExplicitLanguage().booleanValue();
    }

    @Override // com.amazon.music.search.ResultItem
    public Map<String, String> getContentInfo() {
        return this.mContentInfo;
    }

    @Override // com.amazon.music.search.ResultItem
    public String getBlockRef() {
        return this.mBlockRef;
    }
}
