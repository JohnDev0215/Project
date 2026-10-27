package com.amazon.mp3.prime.browse.metadata;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.find.model.search.SearchAssetType;
import com.amazon.music.find.model.search.TrackSearchItem;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.eligibility.factory.ContentEligibilityContext;
import com.amazon.music.platform.data.eligibility.factory.ContentEligibilityFactory;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.data.identifiers.Asin;
import com.amazon.music.platform.data.identifiers.ObjectId;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.platform.providers.NestedEligibilitiesProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeTrack extends TrackSearchItem implements CatalogContent, EntityItem, NestedEligibilitiesProvider {
    private static final String TAG = "PrimeTrack";
    private PlaymodeEligibility mAlbumPlaymodeEligibility;
    private PrimePlaylistTracksTable.AssetType mAssetType;
    private ContentCatalogStatus mCatalogStatus;
    private CatalogStatusTiers mCatalogStatusTiers;
    private int mDownloadState;
    private Map<String, Eligibility> mEligibilities;
    private EntityItemIdentifier mEntityItemIdentifier;
    private LyricsStatus mLyricsStatus;
    private ContentOwnershipStatus mOwnershipStatus;
    private EntityEligibilitiesProvider mParentEligibilities;
    private Double mPopularity;
    private RecommendationReason mRecommendationReason;
    private List<String> mTags;

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    @NonNull
    public EntityItem getEntityItem() {
        return this;
    }

    @Deprecated
    public PrimeTrack() {
        this.mLyricsStatus = LyricsStatus.UNKNOWN;
        this.mDownloadState = -1;
    }

    public PrimeTrack(final String title, final String asin, final String artworkUri, final String artist, final String artistAsin, final String album, final String albumAsin, @Nullable final String albumArtist, final boolean isVideo, final ContentOwnershipStatus ownershipStatus, final CatalogStatusTiers tiers) {
        super(title, asin, artworkUri, artist, artistAsin, album, albumAsin, albumArtist);
        this.mLyricsStatus = LyricsStatus.UNKNOWN;
        this.mDownloadState = -1;
        this.mOwnershipStatus = ownershipStatus;
        setAssetType(isVideo ? PrimePlaylistTracksTable.AssetType.VIDEO : PrimePlaylistTracksTable.AssetType.AUDIO);
        setCatalogStatusTiers(tiers);
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
        return ContentEligibilityFactory.INSTANCE.createFrom(new ContentEligibilityContext(entityItemIdentifier, false, zIsAllOwned, z, z2, z3, z4, z5, catalogStatusTiers6 != null && catalogStatusTiers6.getIsSonicRushOnDemandPlayable(), isDownloaded(), false));
    }

    private void initEntityItemIdentifier(final String asin, final String luid, final Long id) {
        if (asin != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new Asin(asin), SingleType.Track.INSTANCE);
        } else if (luid != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new ObjectId(luid), SingleType.Track.INSTANCE);
        } else if (id != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new ObjectId(id.toString()), SingleType.Track.INSTANCE);
        }
    }

    @Override // com.amazon.music.find.model.search.TrackSearchItem, com.amazon.music.find.model.search.SearchItem
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

    public PlaymodeEligibility getAlbumPlaymodeEligibility() {
        return this.mAlbumPlaymodeEligibility;
    }

    public void setAlbumPlaymodeEligibility(PlaymodeEligibility albumPlaymodeEligibility) {
        this.mAlbumPlaymodeEligibility = albumPlaymodeEligibility;
    }

    public boolean isPrime() {
        return this.mCatalogStatus.isPrime();
    }

    public boolean hasLyrics() {
        return this.mLyricsStatus == LyricsStatus.AVAILABLE;
    }

    public void setLyricsStatus(LyricsStatus lyricsStatus) {
        this.mLyricsStatus = lyricsStatus;
    }

    public LyricsStatus getLyricsStatus() {
        return this.mLyricsStatus;
    }

    public int getDownloadState() {
        return this.mDownloadState;
    }

    public void setDownloadState(int downloadState) {
        this.mDownloadState = downloadState;
        refreshEligibilities();
    }

    public boolean isDownloaded() {
        return this.mDownloadState == 0;
    }

    public ContentOwnershipStatus getOwnershipStatus() {
        return this.mOwnershipStatus;
    }

    public void setOwnershipStatus(ContentOwnershipStatus ownershipStatus) {
        this.mOwnershipStatus = ownershipStatus;
        refreshEligibilities();
    }

    public ContentCatalogStatus getCatalogStatus() {
        return this.mCatalogStatus;
    }

    public CatalogStatusTiers getCatalogStatusTiers() {
        return this.mCatalogStatusTiers;
    }

    public void setCatalogStatusTiers(CatalogStatusTiers catalogStatusTiers) {
        if (catalogStatusTiers == null) {
            catalogStatusTiers = CatalogStatusTiers.fallbackValues();
        }
        this.mCatalogStatusTiers = catalogStatusTiers;
        ContentOwnershipStatus contentOwnershipStatus = this.mOwnershipStatus;
        this.mCatalogStatus = ContentCatalogStatus.fromValues(catalogStatusTiers, contentOwnershipStatus != null && contentOwnershipStatus.isOwned(), isVideo());
        refreshEligibilities();
    }

    private boolean shouldUpdateContentCatalogStatus() {
        CatalogStatusTiers catalogStatusTiers = this.mCatalogStatusTiers;
        return catalogStatusTiers == null || !(catalogStatusTiers.getIsFree() || this.mCatalogStatusTiers.getIsFreeOnDemandPlayable() || this.mCatalogStatusTiers.getIsSonicRushOnDemandPlayable() || this.mCatalogStatusTiers.getIsSonicRushGolden());
    }

    private void setContentAccessSet(ContentCatalogStatus contentCatalogStatus) {
        setContentAccessSet(SearchItemUtils.catalogStatusToContentAccessSet(contentCatalogStatus));
    }

    public Double getPopularity() {
        return this.mPopularity;
    }

    public void setPopularity(final Double popularity) {
        this.mPopularity = popularity;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAnyCatalog() {
        return this.mCatalogStatus.isHawkfire();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public ContentCatalogStatus getMaxNonPreviousContentCatalogStatus() {
        if (this.mCatalogStatus.isPreviouslyCatalog()) {
            return ContentCatalogStatus.UNKNOWN;
        }
        return this.mCatalogStatus;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllPreviouslyCatalog() {
        return this.mCatalogStatus.isPreviouslyCatalog();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllOwned() {
        ContentOwnershipStatus contentOwnershipStatus = this.mOwnershipStatus;
        return contentOwnershipStatus != null && contentOwnershipStatus.isOwned();
    }

    @Override // com.amazon.music.find.model.search.TrackSearchItem
    public boolean isOwned() {
        return isAllOwned();
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    /* JADX INFO: renamed from: isAvailable */
    public boolean getIsAvailable() {
        if (this.mOwnershipStatus != null && isPureCatalog() && !isPrimeAccessible()) {
            return false;
        }
        if ((AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() && getIsExplicit()) || ContentAccessUtil.getPlayCatalogContentUnavailableReason(this) == ContentUnavailableReason.ASSET_IS_PRE_WIDEVINE) {
            return false;
        }
        boolean z = !isAllOwned();
        if (CastingUtil.isCasting()) {
            return CastingUtil.isSupportedContent(getAsin(), getSource(), z);
        }
        return true;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getItemTypeName() {
        return AmazonApplication.getContext().getString(R.string.dmusic_library_item_name_track);
    }

    public boolean isVideo() {
        return PrimePlaylistTracksTable.AssetType.VIDEO.equals(this.mAssetType);
    }

    public PrimePlaylistTracksTable.AssetType getAssetType() {
        return this.mAssetType;
    }

    public void setAssetType(final PrimePlaylistTracksTable.AssetType assetType) {
        this.mAssetType = assetType;
    }

    private boolean isPureCatalog() {
        return !this.mOwnershipStatus.isOwned() && this.mCatalogStatus.isHawkfire();
    }

    private boolean isPrimeAccessible() {
        ContentUnavailableReason playCatalogContentUnavailableReason = ContentAccessUtil.getPlayCatalogContentUnavailableReason(this);
        if (playCatalogContentUnavailableReason == null || playCatalogContentUnavailableReason == ContentUnavailableReason.CONTENT_TYPE_NOT_CASTABLE || playCatalogContentUnavailableReason == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK) {
            return true;
        }
        return playCatalogContentUnavailableReason == ContentUnavailableReason.CUSTOMER_HAS_NO_HAWKFIRE_SUBSCRIPTION && UserSubscriptionUtil.isNightwingOnly();
    }

    public void setRecommendationReason(RecommendationReason reason) {
        this.mRecommendationReason = reason;
    }

    public RecommendationReason getRecommendationReason() {
        return this.mRecommendationReason;
    }

    @Override // com.amazon.music.find.model.search.TrackSearchItem
    public void setCatalogStatus(boolean isPrime, boolean isHawkfire) {
        if (shouldUpdateContentCatalogStatus()) {
            setCatalogStatusTiers(new CatalogStatusTiers(isPrime, isHawkfire, false, false, false, false));
        }
    }

    @Override // com.amazon.music.find.model.search.TrackSearchItem
    public void setCatalogStatus(final int catalogStatus) {
        if (shouldUpdateContentCatalogStatus()) {
            setCatalogStatusTiers(CatalogStatusTiers.INSTANCE.from(ContentCatalogStatus.fromValue(catalogStatus).getSupportedTiers()));
        }
    }

    @Override // com.amazon.music.find.model.search.TrackSearchItem
    public void setContentAccessSet(final int catalogStatus) {
        setContentAccessSet(ContentCatalogStatus.fromValue(catalogStatus));
    }

    public void setIsExplicit(final boolean explicit) {
        setExplicit(explicit);
    }

    @Override // com.amazon.music.find.model.search.TrackSearchItem
    public void setOwnershipStatus(final int ownershipStatus) {
        setOwnershipStatus(ContentOwnershipStatus.fromValue(ownershipStatus));
        refreshEligibilities();
    }

    @Override // com.amazon.music.find.model.search.TrackSearchItem
    public void setLyricsState(final String lyricsState) {
        LyricsStatus lyricsStatusFromString = LyricsStatus.fromString(lyricsState);
        LyricsStatus lyricsStatus = LyricsStatus.AVAILABLE;
        if (!(lyricsStatusFromString == lyricsStatus)) {
            lyricsStatus = LyricsStatus.NO_LYRICS;
        }
        setLyricsStatus(lyricsStatus);
    }

    @Override // com.amazon.music.find.model.search.TrackSearchItem
    public void setSourceAndDownloadState(final String mediaSource, @Nullable final String localUri, final int downloadedState) {
        if (!TextUtils.isEmpty(localUri)) {
            mediaSource = "cirrus-local";
        }
        if ("cirrus-local".equals(mediaSource) && downloadedState != 6 && downloadedState != 7) {
            downloadedState = 0;
        }
        setSource(mediaSource);
        setDownloadState(downloadedState);
    }

    @Override // com.amazon.music.find.model.search.TrackSearchItem
    public void setAssetType(final SearchAssetType assetType) {
        this.mAssetType = SearchItemUtils.convertAssetType(assetType);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrimeTrack primeTrack = (PrimeTrack) o;
        if (getTitle() == null ? primeTrack.getTitle() != null : !getTitle().equals(primeTrack.getTitle())) {
            return false;
        }
        if (getAsin() == null ? primeTrack.getAsin() != null : !getAsin().equals(primeTrack.getAsin())) {
            return false;
        }
        if (getArtworkUri() == null ? primeTrack.getArtworkUri() != null : !getArtworkUri().equals(primeTrack.getArtworkUri())) {
            return false;
        }
        if (getArtist() == null ? primeTrack.getArtist() != null : !getArtist().equals(primeTrack.getArtist())) {
            return false;
        }
        if (getArtistAsin() == null ? primeTrack.getArtistAsin() != null : !getArtistAsin().equals(primeTrack.getArtistAsin())) {
            return false;
        }
        if (getAlbum() == null ? primeTrack.getAlbum() != null : !getAlbum().equals(primeTrack.getAlbum())) {
            return false;
        }
        if (getAlbumAsin() == null ? primeTrack.getAlbumAsin() != null : !getAlbumAsin().equals(primeTrack.getAlbumAsin())) {
            return false;
        }
        if (getAlbumArtist() == null ? primeTrack.getAlbumArtist() == null : getAlbumArtist().equals(primeTrack.getAlbumArtist())) {
            return this.mCatalogStatus == primeTrack.mCatalogStatus;
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = (((((((((((((((getTitle() != null ? getTitle().hashCode() : 0) * 31) + (getAsin() != null ? getAsin().hashCode() : 0)) * 31) + (getArtworkUri() != null ? getArtworkUri().hashCode() : 0)) * 31) + (getArtist() != null ? getArtist().hashCode() : 0)) * 31) + (getArtistAsin() != null ? getArtistAsin().hashCode() : 0)) * 31) + (getAlbum() != null ? getAlbum().hashCode() : 0)) * 31) + (getAlbumAsin() != null ? getAlbumAsin().hashCode() : 0)) * 31) + (getAlbumArtist() != null ? getAlbumArtist().hashCode() : 0)) * 31;
        ContentCatalogStatus contentCatalogStatus = this.mCatalogStatus;
        return iHashCode + (contentCatalogStatus != null ? contentCatalogStatus.hashCode() : 0);
    }

    public static PrimeTrack fromTrack(final MusicTrack track) {
        PrimeTrack primeTrack = new PrimeTrack(track.getTitle(), track.getAsin(), track.getAlbumArtUrl(), track.getArtistName(), track.getArtistAsin(), track.getAlbumName(), track.getAlbumAsin(), track.getAlbumArtistName(), PrimePlaylistTracksTable.AssetType.VIDEO.equals(track.getAssetType()), track.getOwnershipStatus(), track.getCatalogStatusTiers());
        primeTrack.setLocalUri(track.getLocalUri());
        if (!StringUtils.isEmpty(track.getLocalUri())) {
            track.setSource("cirrus-local");
        } else {
            track.setSource(track.getSource());
        }
        primeTrack.setPlaymodeEligibility(track.getPlaymodeEligibility());
        primeTrack.setContentEncoding(track.getContentEncodings());
        return primeTrack;
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

    public void setParentEligibilities(final EntityEligibilitiesProvider parentEligibilities) {
        this.mParentEligibilities = parentEligibilities;
    }

    @Override // com.amazon.music.platform.data.entity.EntityItem
    public EntityItemIdentifier getEntityItemIdentifier() {
        if (this.mEntityItemIdentifier == null) {
            initEntityItemIdentifier(getAsin(), getLuid(), getId());
        }
        return this.mEntityItemIdentifier;
    }

    public void setEntityItemIdentifier(final EntityItemIdentifier entityItemIdentifier) {
        this.mEntityItemIdentifier = entityItemIdentifier;
    }

    public void setTags(final List<String> tags) {
        this.mTags = tags;
    }

    @Override // com.amazon.music.platform.providers.NestedEligibilitiesProvider
    @Nullable
    public EntityEligibilitiesProvider getParentEligibilities() {
        return this.mParentEligibilities;
    }

    public List<String> getTags() {
        return this.mTags;
    }
}
