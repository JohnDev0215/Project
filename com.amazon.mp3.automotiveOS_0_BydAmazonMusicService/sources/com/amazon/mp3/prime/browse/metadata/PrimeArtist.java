package com.amazon.mp3.prime.browse.metadata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.find.model.search.ArtistSearchItem;
import com.amazon.music.find.model.search.SearchContentOwnershipStatus;
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
public class PrimeArtist extends ArtistSearchItem implements CatalogContent, EntityItem, EntityEligibilitiesProvider {
    private static final String TAG = "PrimeArtist";
    private ContentCatalogStatus mCatalogStatus;
    private CatalogStatusTiers mCatalogStatusTiers;
    private Map<String, Eligibility> mEligibilities;
    private EntityItemIdentifier mEntityItemIdentifier;
    private ContentOwnershipStatus mOwnershipStatus;

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    @NonNull
    public EntityItem getEntityItem() {
        return this;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllPreviouslyCatalog() {
        return false;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAnyCatalog() {
        return true;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        return false;
    }

    public PrimeArtist() {
    }

    public PrimeArtist(String name, String asin, @Nullable String contributorAsin, final int trackCount, final int albumCount, @Nullable String artworkUrl, final ContentOwnershipStatus ownershipStatus, final CatalogStatusTiers catalogStatusTiers) {
        setAsin(asin);
        setTitle(name);
        setContributorAsin(contributorAsin);
        setArtworkUri(artworkUrl);
        setTrackCount(trackCount);
        setAlbumCount(albumCount);
        this.mOwnershipStatus = ownershipStatus;
        setCatalogStatusTiers(catalogStatusTiers, AccountDetailUtil.get().isPrimeMusicSupported());
    }

    public String getName() {
        return getTitle();
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getItemTypeName() {
        return AmazonApplication.getContext().getString(R.string.dmusic_library_item_name_artist);
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public ContentCatalogStatus getMaxNonPreviousContentCatalogStatus() {
        ContentCatalogStatus contentCatalogStatus = this.mCatalogStatus;
        return contentCatalogStatus != null ? contentCatalogStatus : ContentCatalogStatus.PRIME;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllOwned() {
        ContentOwnershipStatus contentOwnershipStatus = this.mOwnershipStatus;
        if (contentOwnershipStatus != null) {
            return contentOwnershipStatus.isOwned();
        }
        return false;
    }

    private CatalogStatusTiers catalogTiersBasedOnMarketplace(final boolean isPrime) {
        return new CatalogStatusTiers(isPrime, AccountDetailUtil.get().isUserInHawkfireMarketplace(), UserSubscriptionUtil.isNightwingOnly(), false, AccountDetailUtil.get().isUserInSonicRushMusicTerritory(), false);
    }

    @Override // com.amazon.music.find.model.search.ArtistSearchItem
    public void setCatalogStatus(boolean isPrime) {
        if (shouldUpdateContentCatalogStatus()) {
            setCatalogStatusTiers(null, isPrime);
        }
    }

    private void setCatalogStatusTiers(final CatalogStatusTiers tiers, final boolean isPrime) {
        if (tiers == null || !tiers.isInCatalog()) {
            this.mCatalogStatusTiers = catalogTiersBasedOnMarketplace(isPrime);
        } else {
            this.mCatalogStatusTiers = tiers;
        }
        setCatalogStatus();
        refreshEligibilities();
    }

    private boolean shouldUpdateContentCatalogStatus() {
        CatalogStatusTiers catalogStatusTiers = this.mCatalogStatusTiers;
        return catalogStatusTiers == null || !(catalogStatusTiers.getIsFree() || this.mCatalogStatusTiers.getIsFreeOnDemandPlayable() || this.mCatalogStatusTiers.getIsSonicRushOnDemandPlayable() || this.mCatalogStatusTiers.getIsSonicRushGolden());
    }

    private void setCatalogStatus() {
        CatalogStatusTiers catalogStatusTiers = this.mCatalogStatusTiers;
        ContentOwnershipStatus contentOwnershipStatus = this.mOwnershipStatus;
        this.mCatalogStatus = ContentCatalogStatus.fromValues(catalogStatusTiers, contentOwnershipStatus != null && contentOwnershipStatus.isOwned(), false);
    }

    @Override // com.amazon.music.find.model.search.ArtistSearchItem
    public void setOwnershipStatus(final int ownershipStatus) {
        this.mOwnershipStatus = ContentOwnershipStatus.fromValue(ownershipStatus);
        refreshEligibilities();
    }

    @Override // com.amazon.music.find.model.search.ArtistSearchItem
    public void setOwnershipStatus(final SearchContentOwnershipStatus ownershipStatus) {
        this.mOwnershipStatus = SearchItemUtils.convertOwnershipStatus(ownershipStatus);
        refreshEligibilities();
    }

    @Override // com.amazon.music.find.model.search.ArtistSearchItem, com.amazon.music.find.model.search.SearchItem
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

    @Override // com.amazon.music.platform.data.entity.EntityItem
    @Nullable
    public EntityItemIdentifier getEntityItemIdentifier() {
        if (this.mEntityItemIdentifier == null) {
            initEntityItemIdentifier(getAsin(), getId());
        }
        return this.mEntityItemIdentifier;
    }

    private void initEntityItemIdentifier(final String asin, final Long id) {
        if (asin != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new Asin(asin), CollectionType.Artist.INSTANCE);
        } else if (id != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new ObjectId(id.toString()), CollectionType.Artist.INSTANCE);
        }
    }

    public void setEntityItemIdentifier(final EntityItemIdentifier entityItemIdentifier) {
        this.mEntityItemIdentifier = entityItemIdentifier;
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
        return ContentEligibilityFactory.INSTANCE.createFrom(new ContentEligibilityContext(entityItemIdentifier, false, zIsAllOwned, z, z2, z3, z4, z5, catalogStatusTiers6 != null && catalogStatusTiers6.getIsSonicRushOnDemandPlayable()));
    }
}
