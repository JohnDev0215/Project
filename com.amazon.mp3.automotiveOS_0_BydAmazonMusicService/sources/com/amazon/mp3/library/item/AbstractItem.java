package com.amazon.mp3.library.item;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.util.BlockingAsyncValue;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Value;
import com.amazon.mp3.util.extensions.EligibilityExtensionsKt;
import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.eligibility.PlaylistLimitEligibility;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.eligibility.factory.ContentEligibilityContext;
import com.amazon.music.platform.data.eligibility.factory.ContentEligibilityFactory;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.identifiers.Asin;
import com.amazon.music.platform.data.identifiers.ObjectId;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractItem implements CatalogContent, EntityItem, EntityEligibilitiesProvider {
    protected String mAsin;
    protected CatalogStatusTiers mCatalogStatusTiers;
    private Uri mContentUri;
    private int mDownloadState;
    private Map<String, Eligibility> mEligibilities;
    protected EntityItemIdentifier mEntityItemIdentifier;
    private String mId;
    protected LibraryItemFactory mLibItemFactory;
    protected int mMaxContentCatalogStatusValue;
    private int mMaxContentOwnershipStatusValue;
    private int mMaxNonPreviousContentCatalogStatusValue;
    private int mMinContentCatalogStatusValue;
    private int mMinContentOwnershipStatusValue;
    private boolean mOptionalInfoSet;
    private PlaylistLimitEligibility mPlaylistLimitEligibility;
    private PlaymodeEligibility mPlaymodeEligibility;
    private Value<String> mSource;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$setSource$1(String str) {
        return str;
    }

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    @NonNull
    public EntityItem getEntityItem() {
        return this;
    }

    protected EntityItemType getEntityItemType() {
        return null;
    }

    public abstract String getItemTypeName();

    public AbstractItem() {
        this.mOptionalInfoSet = false;
        this.mMinContentCatalogStatusValue = 0;
        this.mMaxNonPreviousContentCatalogStatusValue = 0;
        this.mMaxContentCatalogStatusValue = 0;
        this.mCatalogStatusTiers = CatalogStatusTiers.fallbackValues();
        this.mPlaymodeEligibility = null;
        this.mPlaylistLimitEligibility = null;
        this.mDownloadState = -1;
    }

    public AbstractItem(LibraryItemFactory libItemFactory, Uri contentUri) {
        this.mOptionalInfoSet = false;
        this.mMinContentCatalogStatusValue = 0;
        this.mMaxNonPreviousContentCatalogStatusValue = 0;
        this.mMaxContentCatalogStatusValue = 0;
        this.mCatalogStatusTiers = CatalogStatusTiers.fallbackValues();
        this.mPlaymodeEligibility = null;
        this.mPlaylistLimitEligibility = null;
        this.mLibItemFactory = libItemFactory;
        setContentUri(contentUri);
        this.mDownloadState = -1;
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String id) {
        this.mId = id;
        if (this.mEntityItemIdentifier != null) {
            initEntityItemIdentifier();
        }
        refreshEligibilities();
    }

    public String getAsin() {
        if (TextUtils.isEmpty(this.mAsin)) {
            demandOptionalInfo();
        }
        return this.mAsin;
    }

    public void setAsin(String asin) {
        this.mAsin = asin;
        if (this.mEntityItemIdentifier != null) {
            initEntityItemIdentifier();
        }
        refreshEligibilities();
    }

    public void setContentUri(final Uri contentUri) {
        this.mContentUri = contentUri;
        if (contentUri != null) {
            this.mSource = new BlockingAsyncValue(new Function0() { // from class: com.amazon.mp3.library.item.AbstractItem$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MediaProvider.getSource(contentUri);
                }
            });
        } else {
            this.mSource = null;
        }
    }

    public Uri getContentUri() {
        return this.mContentUri;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public synchronized String getSource() {
        Value<String> value;
        value = this.mSource;
        return value != null ? value.get() : null;
    }

    public void setSource(final String source) {
        this.mSource = new Value() { // from class: com.amazon.mp3.library.item.AbstractItem$$ExternalSyntheticLambda0
            @Override // com.amazon.mp3.util.Value
            public final Object get() {
                return AbstractItem.lambda$setSource$1(source);
            }
        };
    }

    public boolean isRemote() {
        return "cirrus".equals(getSource());
    }

    protected void demandOptionalInfo() {
        if (this.mOptionalInfoSet) {
            return;
        }
        AmazonApplication.getLibraryItemFactory().updateOptionalInfo(this);
        this.mOptionalInfoSet = true;
    }

    public int getDownloadState() {
        return this.mDownloadState;
    }

    public void setDownloadState(int mDownloadState) {
        this.mDownloadState = mDownloadState;
    }

    public boolean isDownloaded() {
        return this.mDownloadState == 0 || isLocal();
    }

    public boolean isLocal() {
        if (getContentUri() != null) {
            return "cirrus-local".equals(MediaProvider.getSource(getContentUri()));
        }
        return false;
    }

    public int getMinContentCatalogStatusValue() {
        if (this.mMinContentCatalogStatusValue == 0) {
            this.mMinContentCatalogStatusValue = getMinCatalogStatusFromTiers().getValue();
        }
        return this.mMinContentCatalogStatusValue;
    }

    public void setCatalogStatusTiers(final CatalogStatusTiers catalogStatusTiers) {
        if (catalogStatusTiers == null) {
            catalogStatusTiers = CatalogStatusTiers.fallbackValues();
        }
        this.mCatalogStatusTiers = catalogStatusTiers;
        this.mMinContentCatalogStatusValue = getMinCatalogStatusFromTiers().getValue();
        int value = getMaxCatalogStatusFromTiers().getValue();
        this.mMaxContentCatalogStatusValue = value;
        this.mMaxNonPreviousContentCatalogStatusValue = value;
        refreshEligibilities();
    }

    protected ContentCatalogStatus getMinCatalogStatusFromTiers() {
        return ContentCatalogStatus.PrimeContentCatalogStatus.getMinContentCatalogStatus(this.mCatalogStatusTiers, false);
    }

    protected ContentCatalogStatus getMaxCatalogStatusFromTiers() {
        return ContentCatalogStatus.PrimeContentCatalogStatus.getMaxContentCatalogStatus(this.mCatalogStatusTiers, false);
    }

    public CatalogStatusTiers getCatalogStatusTiers() {
        return this.mCatalogStatusTiers;
    }

    public Set<SubscriptionType> getOnDemandTiers() {
        return EligibilityExtensionsKt.onDemandTiers(isSonicRushOnDemand(), isFreeOnDemand());
    }

    protected boolean shouldUpdateContentCatalogStatus() {
        return (this.mCatalogStatusTiers.getIsFree() || this.mCatalogStatusTiers.getIsFreeOnDemandPlayable() || this.mCatalogStatusTiers.getIsSonicRushOnDemandPlayable() || this.mCatalogStatusTiers.getIsSonicRushGolden()) ? false : true;
    }

    public void setMinContentCatalogStatusValue(int minPrimeStatusValue) {
        if (shouldUpdateContentCatalogStatus()) {
            this.mMinContentCatalogStatusValue = minPrimeStatusValue;
        }
    }

    public void setMaxNonPreviousContentCatalogStatusValue(int maxNonPreviousContentCatalogStatusValue) {
        if (shouldUpdateContentCatalogStatus()) {
            setCatalogStatusTiers(CatalogStatusTiers.INSTANCE.from(ContentCatalogStatus.fromValue(maxNonPreviousContentCatalogStatusValue).getSupportedTiers()));
        }
    }

    public int getMaxNonPreviousContentCatalogStatusValue() {
        if (this.mMaxNonPreviousContentCatalogStatusValue == 0) {
            this.mMaxNonPreviousContentCatalogStatusValue = getMaxContentCatalogStatusValue();
        }
        return this.mMaxNonPreviousContentCatalogStatusValue;
    }

    public void setMaxContentCatalogStatusValue(int maxPrimeStatusValue) {
        if (shouldUpdateContentCatalogStatus()) {
            setCatalogStatusTiers(CatalogStatusTiers.INSTANCE.from(ContentCatalogStatus.fromValue(maxPrimeStatusValue).getSupportedTiers()));
        }
    }

    public int getMaxContentCatalogStatusValue() {
        if (this.mMaxContentCatalogStatusValue == 0) {
            this.mMaxContentCatalogStatusValue = getMaxCatalogStatusFromTiers().getValue();
        }
        return this.mMaxContentCatalogStatusValue;
    }

    public int getMinContentOwnershipStatusValue() {
        return this.mMinContentOwnershipStatusValue;
    }

    public void setMinContentOwnershipStatusValue(int minOwnershipStatusValue) {
        this.mMinContentOwnershipStatusValue = minOwnershipStatusValue;
        refreshEligibilities();
    }

    public int getMaxContentOwnershipStatusValue() {
        return this.mMaxContentOwnershipStatusValue;
    }

    public void setMaxContentOwnershipStatusValue(int maxOwnershipStatusValue) {
        this.mMaxContentOwnershipStatusValue = maxOwnershipStatusValue;
        refreshEligibilities();
    }

    public void setCatalogStatusForCatalogOnlyContent(final boolean isPrime, final boolean isHawkfire) {
        if (shouldUpdateContentCatalogStatus()) {
            CatalogStatusTiers catalogStatusTiers = this.mCatalogStatusTiers;
            if (catalogStatusTiers == null || !catalogStatusTiers.getIsSonicRush()) {
                setCatalogStatusTiers(new CatalogStatusTiers(isPrime, isHawkfire));
            }
        }
    }

    public boolean isAllPrime() {
        return ContentCatalogStatus.fromValue(getMinContentCatalogStatusValue()).isPrime();
    }

    public boolean isAllCatalog() {
        return ContentCatalogStatus.fromValue(getMinContentCatalogStatusValue()).isHawkfire();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAnyCatalog() {
        return ContentCatalogStatus.fromValue(getMaxContentCatalogStatusValue()).isHawkfire();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public ContentCatalogStatus getMaxNonPreviousContentCatalogStatus() {
        return ContentCatalogStatus.fromValue(getMaxNonPreviousContentCatalogStatusValue());
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllPreviouslyCatalog() {
        return ContentCatalogStatus.fromValue(getMinContentCatalogStatusValue()).isPreviouslyCatalog();
    }

    public boolean isNotOwned() {
        return !ContentOwnershipStatus.fromValue(getMinContentOwnershipStatusValue()).isOwned();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllOwned() {
        return ContentOwnershipStatus.fromValue(getMaxContentOwnershipStatusValue()).isOwned();
    }

    public boolean isPurePrime() {
        return isNotOwned() && isAllPrime();
    }

    public boolean isPureCatalog() {
        return isNotOwned() && isAllCatalog();
    }

    public boolean isCatalogAccessible() {
        ContentUnavailableReason playCatalogContentUnavailableReason = ContentAccessUtil.getPlayCatalogContentUnavailableReason(this);
        return playCatalogContentUnavailableReason == null || playCatalogContentUnavailableReason == ContentUnavailableReason.ASSET_IS_PRE_WIDEVINE || playCatalogContentUnavailableReason == ContentUnavailableReason.CONTENT_TYPE_NOT_CASTABLE || playCatalogContentUnavailableReason == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK;
    }

    public boolean isAvailable() {
        if (isPureCatalog() && !isCatalogAccessible()) {
            return false;
        }
        if (isAllPreviouslyCatalog() && isNotOwned()) {
            return false;
        }
        if (ContentAccessUtil.getPlayCatalogContentUnavailableReason(this) == ContentUnavailableReason.ASSET_IS_PRE_WIDEVINE && !isRemote()) {
            return false;
        }
        if (CastingUtil.isCasting()) {
            return isCastable();
        }
        return true;
    }

    public boolean isCastable() {
        return CastingUtil.isSupportedContent(this.mAsin, getSource(), isNotOwned() || MediaProvider.Tracks.isPrimeAdditionalTrack(getContentUri()));
    }

    public boolean isInLibrary() {
        return ContentOwnershipStatus.fromValue(this.mMinContentOwnershipStatusValue).isInLibrary();
    }

    public boolean isAvailableOffline() {
        ContentUnavailableReason playCatalogContentUnavailableReason = ContentAccessUtil.getPlayCatalogContentUnavailableReason(this);
        if (playCatalogContentUnavailableReason == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK || playCatalogContentUnavailableReason == ContentUnavailableReason.ASSET_IS_PRE_WIDEVINE || playCatalogContentUnavailableReason == ContentUnavailableReason.NOT_AVAILABLE_OFFLINE) {
            return false;
        }
        return !isRemote() || isDownloaded();
    }

    public boolean isFree() {
        return this.mCatalogStatusTiers.getIsFree();
    }

    public boolean isFreeOnDemand() {
        return this.mCatalogStatusTiers.getIsFreeOnDemandPlayable();
    }

    public boolean isSonicRush() {
        return this.mCatalogStatusTiers.getIsSonicRush();
    }

    public boolean isSonicRushOnDemand() {
        return this.mCatalogStatusTiers.getIsSonicRushOnDemandPlayable();
    }

    public boolean isSonicRushGolden() {
        return this.mCatalogStatusTiers.getIsSonicRushGolden();
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.mPlaymodeEligibility;
    }

    public void setPlaymodeEligibility(final PlaymodeEligibility playmodeEligibility) {
        this.mPlaymodeEligibility = playmodeEligibility;
        Map<String, Eligibility> map = this.mEligibilities;
        if (map != null) {
            map.put("PLAYMODES", playmodeEligibility);
        }
    }

    public PlaylistLimitEligibility getPlaylistLimitEligibility() {
        return this.mPlaylistLimitEligibility;
    }

    public void setPlaylistLimitEligibility(final PlaylistLimitEligibility playlistLimitEligibility) {
        this.mPlaylistLimitEligibility = playlistLimitEligibility;
        Map<String, Eligibility> map = this.mEligibilities;
        if (map != null) {
            map.put(playlistLimitEligibility.getTYPE(), playlistLimitEligibility);
        }
    }

    public ContentCatalogStatus getMaxContentCatalogStatus() {
        int i = this.mMaxContentCatalogStatusValue;
        if (i != 0) {
            return ContentCatalogStatus.fromValue(i);
        }
        return getMaxCatalogStatusFromTiers();
    }

    public Set<ContentAccessType> getSupportedTiers() {
        return this.mCatalogStatusTiers.toSet();
    }

    public ContentEligibility getContentEligibility() {
        return ContentEligibilityFactory.INSTANCE.createFrom(new ContentEligibilityContext(getEntityItemIdentifier(), false, isAllOwned(), isFree(), this.mCatalogStatusTiers.getIsPrime(), isSonicRush(), this.mCatalogStatusTiers.getIsMusicSubscription(), isFreeOnDemand(), isSonicRushOnDemand(), isDownloaded(), false));
    }

    @Override // com.amazon.music.platform.providers.EntityEligibilitiesProvider
    @NonNull
    public Map<String, Eligibility> getEligibilities() {
        Map<String, Eligibility> map = this.mEligibilities;
        if (map == null || map.isEmpty()) {
            setupEligibilities();
        }
        return this.mEligibilities;
    }

    protected void refreshEligibilities() {
        if (this.mEligibilities != null) {
            setupEligibilities();
        }
    }

    private void setupEligibilities() {
        if (this.mEligibilities == null) {
            this.mEligibilities = new HashMap(2);
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        if (playmodeEligibility != null) {
            this.mEligibilities.put(playmodeEligibility.getTYPE(), playmodeEligibility);
        }
        PlaylistLimitEligibility playlistLimitEligibility = getPlaylistLimitEligibility();
        if (playlistLimitEligibility != null) {
            this.mEligibilities.put(playlistLimitEligibility.getTYPE(), playlistLimitEligibility);
        }
        ContentEligibility contentEligibility = getContentEligibility();
        this.mEligibilities.put(contentEligibility.getTYPE(), contentEligibility);
    }

    @Override // com.amazon.music.platform.data.entity.EntityItem
    @Nullable
    public EntityItemIdentifier getEntityItemIdentifier() {
        if (this.mEntityItemIdentifier == null) {
            initEntityItemIdentifier();
        }
        return this.mEntityItemIdentifier;
    }

    protected void initEntityItemIdentifier() {
        EntityItemType entityItemType = getEntityItemType();
        if (entityItemType == null) {
            return;
        }
        if (getAsin() != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new Asin(getAsin()), entityItemType);
        } else if (getId() != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new ObjectId(getId()), entityItemType);
        }
    }
}
