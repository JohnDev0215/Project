package com.amazon.music.search;

import UpsellInterface.v1_0.UpsellType;
import androidx.annotation.Nullable;
import com.amazon.music.ContentAccessType;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.model.Art;
import com.amazon.music.platform.model.ArtURL;
import com.amazon.music.platform.model.CatalogTrack;
import com.amazon.music.platform.model.LanguageOfPerformance;
import com.amazon.music.platform.model.LibraryTrack;
import com.amazon.music.platform.model.ParentalControls;
import com.amazon.tenzing.textsearch.v1_1.Hit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes4.dex */
public final class Track implements ResultItem {
    private final String mBlockRef;
    private Set<ContentAccessType> mContentAccess = null;
    private final Map<String, String> mContentInfo;
    private final com.amazon.music.platform.model.Track mTrack;

    public enum AdditionalFields implements com.amazon.music.search.AdditionalFields {
        Image("artOriginal", "artFull"),
        ContentEncoding(ContextMappingConstants.CONTENT_ENCODING, ""),
        RelatedPlaylistAsin("relatedPlaylist.asin", ""),
        RelatedPlaylistTitle("relatedPlaylist.title", ""),
        RelatedPlaylistArtOriginal("relatedPlaylist.artOriginal", ""),
        RelatedPlaylistEntityRelation("relatedPlaylist.entityRelation", ""),
        RelatedPlaylistPlaymodeEligibilityOnDemand("relatedPlaylist.playmodeEligibility.onDemand", ""),
        RelatedPlaylistPlaymodeEligibilitySFA("relatedPlaylist.playmodeEligibility.sfa", ""),
        RelatedPlaylistPlaymodeEligibilityShuffleType("relatedPlaylist.playmodeEligibility.shuffleType", ""),
        RelatedPlaylistPlayListId("relatedPlaylist.playlistId", ""),
        RelatedPlaylistBlockRef("relatedPlaylist.blockRef", ""),
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
            throw new UnsupportedOperationException("There are no soccer tracks.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getPodcastCatalogField() {
            throw new UnsupportedOperationException("There are no podcast catalog tracks.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getCommunityField() {
            throw new UnsupportedOperationException("There are no community tracks.");
        }
    }

    Track(Hit hit, Map<String, String> map) {
        this.mTrack = (com.amazon.music.platform.model.Track) hit.getDocument();
        this.mContentInfo = map;
        this.mBlockRef = hit.getBlockRef();
    }

    @Nullable
    public String getLibraryId() {
        com.amazon.music.platform.model.Track track = this.mTrack;
        if (track instanceof LibraryTrack) {
            return ((LibraryTrack) track).getObjectId();
        }
        return null;
    }

    public String getTitle() {
        return this.mTrack.getTitle();
    }

    @Nullable
    public String getAsin() {
        return this.mTrack.getAsin();
    }

    public String getAlbumName() {
        return this.mTrack.getAlbumName();
    }

    @Nullable
    public String getAlbumAsin() {
        return this.mTrack.getAlbumAsin();
    }

    public String getArtistName() {
        return this.mTrack.getArtistName();
    }

    @Nullable
    public String getArtistAsin() {
        return this.mTrack.getArtistAsin();
    }

    public String getAlbumArtistName() {
        return this.mTrack.getAlbumArtistName();
    }

    public Integer getTrackNumber() {
        return this.mTrack.getTrackNum();
    }

    public Integer getDiscNumber() {
        return this.mTrack.getDiscNum();
    }

    public Integer getDurationInSeconds() {
        return this.mTrack.getDuration();
    }

    public Set<ContentAccessType> getContentAccess() {
        if (this.mContentAccess == null) {
            HashSet hashSet = new HashSet();
            List<String> contentTier = this.mTrack.getContentTier();
            if (contentTier != null) {
                if ((this.mTrack instanceof LibraryTrack) && !contentTier.contains(UpsellType.PRIME) && !contentTier.contains("SONIC_RUSH") && !contentTier.contains(UpsellType.UNLIMITED) && !contentTier.contains("NIGHTWING") && !contentTier.contains("NIGHTWING_ONDEMAND_PLAYABLE")) {
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
        Art artOriginal;
        com.amazon.music.platform.model.Track track = this.mTrack;
        if (track instanceof LibraryTrack) {
            artOriginal = ((LibraryTrack) track).getArtFull();
        } else {
            artOriginal = track instanceof CatalogTrack ? ((CatalogTrack) track).getArtOriginal() : null;
        }
        if (artOriginal == null || !(artOriginal instanceof ArtURL)) {
            return null;
        }
        return ((ArtURL) artOriginal).getArtUrl();
    }

    public List<RelatedPlaylist> getRelatedPlaylists() {
        List<com.amazon.music.platform.model.RelatedPlaylist> relatedPlaylist;
        List<com.amazon.music.platform.model.RelatedPlaylist> arrayList = new ArrayList<>();
        com.amazon.music.platform.model.Track track = this.mTrack;
        if ((track instanceof CatalogTrack) && (relatedPlaylist = ((CatalogTrack) track).getRelatedPlaylist()) != null) {
            arrayList = relatedPlaylist;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<com.amazon.music.platform.model.RelatedPlaylist> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new RelatedPlaylist(it.next()));
        }
        return arrayList2;
    }

    @Override // com.amazon.music.search.ResultItem
    public Map<String, String> getContentInfo() {
        return this.mContentInfo;
    }

    public boolean isExplicit() {
        ParentalControls parentalControls = this.mTrack.getParentalControls();
        return parentalControls != null && parentalControls.isHasExplicitLanguage().booleanValue();
    }

    @Override // com.amazon.music.search.ResultItem
    public String getBlockRef() {
        return this.mBlockRef;
    }

    public List<ContentEncoding> getContentEncoding() {
        com.amazon.music.platform.model.Track track = this.mTrack;
        return ContentEncodingDecoder.loadFrom(track instanceof CatalogTrack ? ((CatalogTrack) track).getContentEncoding() : null);
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        com.amazon.music.platform.model.PlaymodeEligibility playmodeEligibility;
        com.amazon.music.platform.model.Track track = this.mTrack;
        if (!(track instanceof CatalogTrack) || (playmodeEligibility = ((CatalogTrack) track).getPlaymodeEligibility()) == null) {
            return null;
        }
        return new PlaymodeEligibility(playmodeEligibility);
    }

    public List<String> getLanguageOfPerformances() {
        List<LanguageOfPerformance> languageOfPerformances;
        com.amazon.music.platform.model.Track track = this.mTrack;
        if ((track instanceof CatalogTrack) && (languageOfPerformances = ((CatalogTrack) track).getLanguageOfPerformances()) != null) {
            return (List) languageOfPerformances.stream().filter(new Album$$ExternalSyntheticLambda0()).map(new Album$$ExternalSyntheticLambda1()).collect(Collectors.toList());
        }
        return new ArrayList();
    }
}
