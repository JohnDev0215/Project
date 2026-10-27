package com.amazon.mp3.prime.browse.metadata;

import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.music.find.model.search.AlbumSearchItem;
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
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeAlbum extends AlbumSearchItem implements CatalogContent, EntityItem, EntityEligibilitiesProvider {
    private static final String TAG = "PrimeAlbum";
    private CatalogStatusTiers mCatalogStatusTiers;
    private int mDownloadState;
    private Map<String, Eligibility> mEligibilities;
    private EntityItemIdentifier mEntityItemIdentifier;
    private ContentCatalogStatus mMaxContentCatalogStatus;
    private ContentCatalogStatus mMaxNonPreviousContentCatalogStatus;
    private ContentOwnershipStatus mMaxOwnershipStatus;
    private ContentCatalogStatus mMinContentCatalogStatus;
    private ContentOwnershipStatus mMinOwnershipStatus;
    private int mNumRatings;
    private float mRating;
    private RecommendationReason mRecommendationReason;

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    @NonNull
    public EntityItem getEntityItem() {
        return this;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        return false;
    }

    @Deprecated
    public PrimeAlbum() {
        this.mDownloadState = -1;
    }

    public PrimeAlbum(final String title, final String asin, final String artworkUri, final String artist, final String artistASIN, final int trackCount, final ContentOwnershipStatus ownershipStatus, final CatalogStatusTiers tiers) {
        super(title, asin, artworkUri, artist, artistASIN);
        this.mDownloadState = -1;
        setTrackCount(trackCount);
        setOwnershipStatus(ownershipStatus == null ? ContentOwnershipStatus.NOT_IN_LIBRARY : ownershipStatus);
        setCatalogStatusTiers(tiers);
    }

    private void initEntityItemIdentifier(final String asin, final Long id) {
        if (asin != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new Asin(asin), CollectionType.Album.INSTANCE);
        } else if (id != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new ObjectId(id.toString()), CollectionType.Album.INSTANCE);
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
        return ContentEligibilityFactory.INSTANCE.createFrom(new ContentEligibilityContext(entityItemIdentifier, false, zIsAllOwned, z, z2, z3, z4, z5, catalogStatusTiers6 != null && catalogStatusTiers6.getIsSonicRushOnDemandPlayable(), isDownloaded(), false));
    }

    public Uri getContentUri() {
        return MediaProvider.Albums.getContentUri("cirrus", IdGenerator.generateAlbumId(getAsin(), getTitle()));
    }

    public Uri getTrackListContentUri() {
        return MediaProvider.Albums.Tracks.getContentUri("cirrus", IdGenerator.generateAlbumId(getAsin(), getTitle()));
    }

    public boolean isPrime() {
        ContentCatalogStatus contentCatalogStatus = this.mMinContentCatalogStatus;
        return (contentCatalogStatus == null || this.mMinOwnershipStatus == null || !contentCatalogStatus.isPrime() || this.mMinOwnershipStatus.isOwned()) ? false : true;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getItemTypeName() {
        return AmazonApplication.getContext().getString(R.string.dmusic_library_item_name_album);
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    /* JADX INFO: renamed from: isAvailable */
    public boolean getIsAvailable() {
        return this.mMinContentCatalogStatus == null || this.mMinOwnershipStatus == null || !isPureCatalog() || isPrimeAccessible();
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

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllPreviouslyCatalog() {
        ContentCatalogStatus contentCatalogStatus = this.mMinContentCatalogStatus;
        return contentCatalogStatus != null && contentCatalogStatus.isPreviouslyCatalog();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllOwned() {
        ContentOwnershipStatus contentOwnershipStatus = this.mMaxOwnershipStatus;
        return contentOwnershipStatus != null && contentOwnershipStatus.isOwned();
    }

    @Override // com.amazon.music.find.model.search.AlbumSearchItem
    public boolean isOwned() {
        return isAllOwned();
    }

    private boolean isPureCatalog() {
        ContentOwnershipStatus contentOwnershipStatus = this.mMinOwnershipStatus;
        return (contentOwnershipStatus == null || contentOwnershipStatus.isOwned() || !this.mMinContentCatalogStatus.isHawkfire()) ? false : true;
    }

    private boolean isPrimeAccessible() {
        return ContentAccessUtil.getPlayCatalogContentUnavailableReason(this) == null;
    }

    private void setContentAccessSet(ContentCatalogStatus contentCatalogStatus) {
        setContentAccessSet(SearchItemUtils.catalogStatusToContentAccessSet(contentCatalogStatus));
    }

    private void setOwnershipStatus(ContentOwnershipStatus ownershipStatus) {
        this.mMinOwnershipStatus = ownershipStatus;
        this.mMaxOwnershipStatus = ownershipStatus;
        refreshEligibilities();
    }

    private void setMinOwnershipStatus(ContentOwnershipStatus minOwnershipStatus) {
        this.mMinOwnershipStatus = minOwnershipStatus;
        refreshEligibilities();
    }

    private void setMaxOwnershipStatus(ContentOwnershipStatus maxOwnershipStatus) {
        this.mMaxOwnershipStatus = maxOwnershipStatus;
        refreshEligibilities();
    }

    public int getNumRatings() {
        return this.mNumRatings;
    }

    public void setNumRatings(int numRatings) {
        this.mNumRatings = numRatings;
    }

    public float getRating() {
        return this.mRating;
    }

    public void setRating(float rating) {
        this.mRating = rating;
    }

    public void setRecommendationReason(RecommendationReason reason) {
        this.mRecommendationReason = reason;
    }

    public RecommendationReason getRecommendationReason() {
        return this.mRecommendationReason;
    }

    @Override // com.amazon.music.find.model.search.AlbumSearchItem
    public void setCatalogStatus(boolean isPrime, boolean isHawkfire) {
        if (shouldUpdateContentCatalogStatus()) {
            setCatalogStatusTiers(new CatalogStatusTiers(isPrime, isHawkfire, false, false, false, false));
        }
    }

    @Override // com.amazon.music.find.model.search.AlbumSearchItem
    public void setCatalogStatuses(final int minCatalogStatus, final int maxCatalogStatus, final int maxNonPreviousCatalogStatus) {
        if (shouldUpdateContentCatalogStatus()) {
            setCatalogStatusTiers(CatalogStatusTiers.INSTANCE.from(ContentCatalogStatus.fromValue(maxCatalogStatus).getSupportedTiers()));
        }
    }

    @Override // com.amazon.music.find.model.search.AlbumSearchItem
    public void setContentAccessSet(final int catalogStatus) {
        setContentAccessSet(ContentCatalogStatus.fromValue(catalogStatus));
        refreshEligibilities();
    }

    @Override // com.amazon.music.find.model.search.AlbumSearchItem
    public void setOwnershipStatuses(final int minOwnershipStatus, final int maxOwnershipStatus) {
        setMinOwnershipStatus(ContentOwnershipStatus.fromValue(minOwnershipStatus));
        setMaxOwnershipStatus(ContentOwnershipStatus.fromValue(maxOwnershipStatus));
        refreshEligibilities();
    }

    public int getDownloadState() {
        return this.mDownloadState;
    }

    @Override // com.amazon.music.find.model.search.AlbumSearchItem
    public void setDownloadState(int downloadState) {
        this.mDownloadState = downloadState;
    }

    public boolean isDownloaded() {
        return this.mDownloadState == 0;
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrimeAlbum primeAlbum = (PrimeAlbum) o;
        if (getTitle() == null ? primeAlbum.getTitle() != null : !getTitle().equals(primeAlbum.getTitle())) {
            return false;
        }
        if (getAsin() == null ? primeAlbum.getAsin() != null : !getAsin().equals(primeAlbum.getAsin())) {
            return false;
        }
        if (getArtworkUri() == null ? primeAlbum.getArtworkUri() != null : !getArtworkUri().equals(primeAlbum.getArtworkUri())) {
            return false;
        }
        if (getArtist() == null ? primeAlbum.getArtist() == null : getArtist().equals(primeAlbum.getArtist())) {
            return getArtistAsin() != null ? getArtistAsin().equals(primeAlbum.getArtistAsin()) : primeAlbum.getArtistAsin() == null;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((getTitle() != null ? getTitle().hashCode() : 0) * 31) + (getAsin() != null ? getAsin().hashCode() : 0)) * 31) + (getArtworkUri() != null ? getArtworkUri().hashCode() : 0)) * 31) + (getArtist() != null ? getArtist().hashCode() : 0)) * 31) + (getArtistAsin() != null ? getArtistAsin().hashCode() : 0);
    }

    public void setCatalogStatusTiers(final CatalogStatusTiers tiers) {
        if (tiers == null) {
            tiers = CatalogStatusTiers.fallbackValues();
        }
        this.mCatalogStatusTiers = tiers;
        updateContentCatalogStatuses();
        refreshEligibilities();
    }

    private boolean shouldUpdateContentCatalogStatus() {
        CatalogStatusTiers catalogStatusTiers = this.mCatalogStatusTiers;
        return catalogStatusTiers == null || !(catalogStatusTiers.getIsFree() || this.mCatalogStatusTiers.getIsFreeOnDemandPlayable() || this.mCatalogStatusTiers.getIsSonicRushOnDemandPlayable() || this.mCatalogStatusTiers.getIsSonicRushGolden());
    }

    private void updateContentCatalogStatuses() {
        this.mMinContentCatalogStatus = ContentCatalogStatus.PrimeContentCatalogStatus.getMinContentCatalogStatus(this.mCatalogStatusTiers, false);
        ContentCatalogStatus maxContentCatalogStatus = ContentCatalogStatus.PrimeContentCatalogStatus.getMaxContentCatalogStatus(this.mCatalogStatusTiers, false);
        this.mMaxContentCatalogStatus = maxContentCatalogStatus;
        this.mMaxNonPreviousContentCatalogStatus = maxContentCatalogStatus;
    }

    @Override // com.amazon.music.find.model.search.AlbumSearchItem, com.amazon.music.find.model.search.SearchItem
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
            initEntityItemIdentifier(getAsin(), getId());
        }
        return this.mEntityItemIdentifier;
    }

    public void setEntityItemIdentifier(final EntityItemIdentifier entityItemIdentifier) {
        this.mEntityItemIdentifier = entityItemIdentifier;
    }
}
