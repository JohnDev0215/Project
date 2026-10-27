package com.amazon.mp3.prime.browse.metadata;

import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.ContentAccessTypeKt;
import com.amazon.music.find.model.search.PlaylistSearchItem;
import com.amazon.music.find.model.search.PlaylistType;
import com.amazon.music.find.model.search.SearchAssetType;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.eligibility.factory.ContentEligibilityContext;
import com.amazon.music.platform.data.eligibility.factory.ContentEligibilityFactory;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.identifiers.Asin;
import com.amazon.music.platform.data.identifiers.ObjectId;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class PrimePlaylist extends PlaylistSearchItem implements CatalogContent, EntityItem, EntityEligibilitiesProvider {
    private static final String TAG = "PrimePlaylist";
    private List<PrimePlaylistTracksTable.AssetType> mAssetTypes;
    private CatalogStatusTiers mCatalogStatusTiers;
    private Map<String, Eligibility> mEligibilities;
    private EntityItemIdentifier mEntityItemIdentifier;
    private ContentCatalogStatus mMaxContentCatalogStatus;
    private ContentCatalogStatus mMaxNonPreviousContentCatalogStatus;
    private ContentCatalogStatus mMinContentCatalogStatus;
    private RecommendationReason mRecommendationReason;

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    @NonNull
    public EntityItem getEntityItem() {
        return this;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllPreviouslyCatalog() {
        return false;
    }

    @Deprecated
    public PrimePlaylist() {
    }

    public PrimePlaylist(final String title, final String asin, final String artworkUri, final String description, final float rating, final int numReviews, final int trackCount, final List<String> authors, final List<PrimePlaylistTracksTable.AssetType> assetTypes, final CatalogStatusTiers tiers) {
        super(title, asin, artworkUri, description, rating, numReviews, trackCount);
        setAuthors(authors);
        this.mAssetTypes = assetTypes;
        setCatalogStatusTiers(tiers);
    }

    public PrimePlaylist(final String title, final String artworkUri, final String description, final float rating, final int numReviews, final int trackCount, final String playlistId, final boolean ownership, final CatalogStatusTiers tiers) {
        super(title, null, artworkUri, description, rating, numReviews, trackCount);
        setPlaylistId(playlistId);
        setOwnership(ownership);
        setCatalogStatusTiers(tiers);
    }

    public PrimePlaylist(final String title, final String artworkUri, final String description, final float rating, final int numReviews, final int trackCount, final String playlistId, final boolean ownership, final String curator, final PlaylistType type, final CatalogStatusTiers tiers) {
        super(title, null, artworkUri, description, rating, numReviews, trackCount);
        setPlaylistId(playlistId);
        setOwnership(ownership);
        setCurator(curator);
        setPlaylistType(type);
        setCatalogStatusTiers(tiers);
    }

    private void initEntityItemIdentifier(final String asin, final String playlistId, final Long id) {
        if (asin != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new Asin(asin), CollectionType.Playlist.INSTANCE);
        } else if (playlistId != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new ObjectId(playlistId), CollectionType.Playlist.INSTANCE);
        } else if (id != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new ObjectId(id.toString()), CollectionType.Playlist.INSTANCE);
        }
    }

    private void refreshEligibilities() {
        if (this.mEligibilities != null) {
            setupEligibilities();
        }
    }

    private void setupEligibilities() {
        if (this.mEligibilities == null) {
            this.mEligibilities = new HashMap(2);
        }
        ContentEligibility contentEligibility = getContentEligibility();
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        if (playmodeEligibility != null) {
            this.mEligibilities.put(playmodeEligibility.getTYPE(), playmodeEligibility);
        }
        this.mEligibilities.put(contentEligibility.getTYPE(), contentEligibility);
    }

    private ContentEligibility getContentEligibility() {
        EntityItemIdentifier entityItemIdentifier = getEntityItemIdentifier();
        boolean zIsAllOwned = isAllOwned();
        CatalogStatusTiers catalogStatusTiers = this.mCatalogStatusTiers;
        boolean z = catalogStatusTiers != null && catalogStatusTiers.getIsFree();
        CatalogStatusTiers catalogStatusTiers2 = this.mCatalogStatusTiers;
        boolean z2 = catalogStatusTiers2 != null && catalogStatusTiers2.getIsPrime();
        CatalogStatusTiers catalogStatusTiers3 = this.mCatalogStatusTiers;
        boolean z3 = catalogStatusTiers3 != null && catalogStatusTiers3.getIsSonicRush();
        CatalogStatusTiers catalogStatusTiers4 = this.mCatalogStatusTiers;
        boolean z4 = catalogStatusTiers4 != null && catalogStatusTiers4.getIsMusicSubscription();
        CatalogStatusTiers catalogStatusTiers5 = this.mCatalogStatusTiers;
        boolean z5 = catalogStatusTiers5 != null && catalogStatusTiers5.getIsFreeOnDemandPlayable();
        CatalogStatusTiers catalogStatusTiers6 = this.mCatalogStatusTiers;
        return ContentEligibilityFactory.INSTANCE.createFrom(new ContentEligibilityContext(entityItemIdentifier, false, zIsAllOwned, z, z2, z3, z4, z5, catalogStatusTiers6 != null && catalogStatusTiers6.getIsSonicRushOnDemandPlayable(), false, false));
    }

    private void setCatalogStatusTiers(final CatalogStatusTiers catalogStatusTiers) {
        if (catalogStatusTiers == null) {
            catalogStatusTiers = CatalogStatusTiers.fallbackValues();
        }
        this.mCatalogStatusTiers = catalogStatusTiers;
        setCatalogStatus();
        refreshEligibilities();
    }

    private boolean shouldUpdateContentCatalogStatus() {
        CatalogStatusTiers catalogStatusTiers = this.mCatalogStatusTiers;
        return catalogStatusTiers == null || !(catalogStatusTiers.getIsFree() || this.mCatalogStatusTiers.getIsFreeOnDemandPlayable() || this.mCatalogStatusTiers.getIsSonicRushOnDemandPlayable() || this.mCatalogStatusTiers.getIsSonicRushGolden());
    }

    private void setCatalogStatus() {
        ContentCatalogStatus minContentCatalogStatus;
        ContentCatalogStatus maxContentCatalogStatus;
        boolean zIsPrimeMusicSupported = AccountDetailUtil.get().isPrimeMusicSupported();
        boolean zIsUserInSonicRushMusicTerritory = AccountDetailUtil.get().isUserInSonicRushMusicTerritory();
        if (zIsUserInSonicRushMusicTerritory) {
            minContentCatalogStatus = ContentCatalogStatus.PrimeContentCatalogStatus.getMinContentCatalogStatus(this.mCatalogStatusTiers, isVideoPlaylist());
        } else if (zIsPrimeMusicSupported) {
            minContentCatalogStatus = ContentCatalogStatus.PrimeContentCatalogStatus.getMinContentCatalogStatus(this.mCatalogStatusTiers.getIsPrime(), this.mCatalogStatusTiers.getIsMusicSubscription());
        } else {
            minContentCatalogStatus = ContentCatalogStatus.HAWKFIRE;
        }
        this.mMinContentCatalogStatus = minContentCatalogStatus;
        if (zIsUserInSonicRushMusicTerritory) {
            maxContentCatalogStatus = ContentCatalogStatus.PrimeContentCatalogStatus.getMaxContentCatalogStatus(this.mCatalogStatusTiers, isVideoPlaylist());
        } else if (zIsPrimeMusicSupported) {
            maxContentCatalogStatus = ContentCatalogStatus.PrimeContentCatalogStatus.getMaxContentCatalogStatus(this.mCatalogStatusTiers.getIsPrime(), this.mCatalogStatusTiers.getIsMusicSubscription());
        } else {
            maxContentCatalogStatus = ContentCatalogStatus.HAWKFIRE;
        }
        this.mMaxContentCatalogStatus = maxContentCatalogStatus;
        this.mMaxNonPreviousContentCatalogStatus = maxContentCatalogStatus;
    }

    @Override // com.amazon.music.find.model.search.PlaylistSearchItem
    public void setCatalogStatus(final int catalogStatus) {
        if (shouldUpdateContentCatalogStatus()) {
            setCatalogStatusTiers(ContentAccessTypeKt.toCatalogStatusTiers(ContentCatalogStatus.fromValue(catalogStatus).getSupportedTiers()));
        }
    }

    @Override // com.amazon.music.find.model.search.PlaylistSearchItem
    public void setCatalogStatus(final boolean isPrime, final boolean isSonicRush, final boolean isHawkfire, final boolean isFree, final boolean isFreeOnDemand) {
        if (shouldUpdateContentCatalogStatus()) {
            setCatalogStatusTiers(new CatalogStatusTiers(isPrime, isHawkfire, isFree, isFreeOnDemand, isSonicRush, false));
            setContentAccessSet(this.mCatalogStatusTiers.toSet());
        }
    }

    public int getAuthorStringLocalizedId() {
        List<String> authors = getAuthors();
        if (authors == null) {
            return 0;
        }
        int size = authors.size();
        return size >= 3 ? R.string.dmusic_prime_playlist_3_artists_description : size == 2 ? R.string.dmusic_prime_playlist_2_artists_description : R.string.dmusic_prime_playlist_1_artist_description;
    }

    public String getAuthorsText(final String templateString) {
        List<String> authors = getAuthors();
        if (authors == null) {
            return "";
        }
        int size = authors.size();
        return size >= 3 ? String.format(templateString, authors.get(0), authors.get(1), authors.get(2)) : size == 2 ? String.format(templateString, authors.get(0), authors.get(1)) : size == 1 ? String.format(templateString, authors.get(0)) : "";
    }

    public RecommendationReason getRecommendationReason() {
        return this.mRecommendationReason;
    }

    public void setRecommendationReason(RecommendationReason reason) {
        this.mRecommendationReason = reason;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getItemTypeName() {
        return AmazonApplication.getContext().getString(R.string.dmusic_library_item_name_playlist);
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAnyCatalog() {
        ContentCatalogStatus contentCatalogStatus = this.mMaxContentCatalogStatus;
        return contentCatalogStatus != null && contentCatalogStatus.isHawkfire();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public ContentCatalogStatus getMaxNonPreviousContentCatalogStatus() {
        return this.mMaxNonPreviousContentCatalogStatus;
    }

    @Override // com.amazon.music.find.model.search.PlaylistSearchItem, com.amazon.music.find.model.search.SearchPlaylist
    public int getTrackCount() {
        Uri contentUri = MediaProvider.PrimePlaylists.Tracks.getContentUri("cirrus", getAsin());
        Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(contentUri);
        if (playlist != null) {
            return (int) playlist.getTrackCount();
        }
        CatalogPlaylist catalogPlaylist = AmazonApplication.getLibraryItemFactory().getCatalogPlaylist(contentUri);
        return catalogPlaylist != null ? catalogPlaylist.getTrackCount() : super.getTrackCount();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllOwned() {
        return getOwnership();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        return isVideoPlaylist();
    }

    public void setAssetTypes(final List<PrimePlaylistTracksTable.AssetType> assetTypes) {
        this.mAssetTypes = assetTypes;
    }

    @Override // com.amazon.music.find.model.search.PlaylistSearchItem
    public void setAssetType(final SearchAssetType assetType) {
        setAssetTypes(Collections.singletonList(SearchItemUtils.convertAssetType(assetType)));
    }

    public boolean isVideoPlaylist() {
        List<PrimePlaylistTracksTable.AssetType> list = this.mAssetTypes;
        if (list == null) {
            return false;
        }
        return list.contains(PrimePlaylistTracksTable.AssetType.VIDEO);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrimePlaylist primePlaylist = (PrimePlaylist) o;
        if (Float.compare(primePlaylist.getRating(), getRating()) != 0 || getNumReviews() != primePlaylist.getNumReviews()) {
            return false;
        }
        if (getTitle() == null ? primePlaylist.getTitle() != null : !getTitle().equals(primePlaylist.getTitle())) {
            return false;
        }
        if (getAsin() == null ? primePlaylist.getAsin() != null : !getAsin().equals(primePlaylist.getAsin())) {
            return false;
        }
        if (getArtworkUri() == null ? primePlaylist.getArtworkUri() != null : !getArtworkUri().equals(primePlaylist.getArtworkUri())) {
            return false;
        }
        if (getDescription() == null ? primePlaylist.getDescription() != null : !getDescription().equals(primePlaylist.getDescription())) {
            return false;
        }
        ContentCatalogStatus contentCatalogStatus = this.mMinContentCatalogStatus;
        if (contentCatalogStatus == null ? primePlaylist.mMinContentCatalogStatus != null : !contentCatalogStatus.equals(primePlaylist.mMinContentCatalogStatus)) {
            return false;
        }
        ContentCatalogStatus contentCatalogStatus2 = this.mMaxContentCatalogStatus;
        if (contentCatalogStatus2 == null ? primePlaylist.mMaxContentCatalogStatus != null : !contentCatalogStatus2.equals(primePlaylist.mMaxContentCatalogStatus)) {
            return false;
        }
        ContentCatalogStatus contentCatalogStatus3 = this.mMaxNonPreviousContentCatalogStatus;
        ContentCatalogStatus contentCatalogStatus4 = primePlaylist.mMaxNonPreviousContentCatalogStatus;
        return contentCatalogStatus3 == null ? contentCatalogStatus4 == null : contentCatalogStatus3.equals(contentCatalogStatus4);
    }

    public int hashCode() {
        int iHashCode = (((((((((((getTitle() != null ? getTitle().hashCode() : 0) * 31) + (getAsin() != null ? getAsin().hashCode() : 0)) * 31) + (getArtworkUri() != null ? getArtworkUri().hashCode() : 0)) * 31) + (getDescription() != null ? getDescription().hashCode() : 0)) * 31) + (getRating() != 0.0f ? Float.floatToIntBits(getRating()) : 0)) * 31) + getNumReviews()) * 31;
        ContentCatalogStatus contentCatalogStatus = this.mMinContentCatalogStatus;
        int iHashCode2 = (iHashCode + (contentCatalogStatus != null ? contentCatalogStatus.hashCode() : 0)) * 31;
        ContentCatalogStatus contentCatalogStatus2 = this.mMaxContentCatalogStatus;
        int iHashCode3 = (iHashCode2 + (contentCatalogStatus2 != null ? contentCatalogStatus2.hashCode() : 0)) * 31;
        ContentCatalogStatus contentCatalogStatus3 = this.mMaxNonPreviousContentCatalogStatus;
        return iHashCode3 + (contentCatalogStatus3 != null ? contentCatalogStatus3.hashCode() : 0);
    }

    @Override // com.amazon.music.find.model.search.PlaylistSearchItem, com.amazon.music.find.model.search.SearchItem
    public PlaymodeEligibility getPlaymodeEligibility() {
        Eligibility eligibility;
        Map<String, Eligibility> map = this.mEligibilities;
        if (map != null && !map.isEmpty() && (eligibility = this.mEligibilities.get("PLAYMODES")) != null && (eligibility instanceof PlaymodeEligibility)) {
            if (super.getPlaymodeEligibility() != null && super.getPlaymodeEligibility() != eligibility) {
                Log.warning(TAG, "conflict eligibility: " + super.getPlaymodeEligibility() + " " + eligibility);
            }
            return (PlaymodeEligibility) eligibility;
        }
        return super.getPlaymodeEligibility();
    }

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    @NonNull
    public Map<String, Eligibility> getEligibilities() {
        if (this.mEligibilities == null) {
            setupEligibilities();
        }
        return this.mEligibilities;
    }

    public void setEligibilities(final Map<String, Eligibility> eligibilities) {
        this.mEligibilities = eligibilities;
    }

    @Override // com.amazon.music.platform.data.entity.EntityItem
    public EntityItemIdentifier getEntityItemIdentifier() {
        if (this.mEntityItemIdentifier == null) {
            initEntityItemIdentifier(getAsin(), getPlaylistId(), getId());
        }
        return this.mEntityItemIdentifier;
    }

    public void setEntityItemIdentifier(final EntityItemIdentifier entityItemIdentifier) {
        this.mEntityItemIdentifier = entityItemIdentifier;
    }
}
