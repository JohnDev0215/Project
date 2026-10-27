package com.amazon.mp3.search.model;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.browse.metadata.RecommendationReason;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.music.ContentAccessType;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.find.model.search.StationSearchItem;
import com.amazon.music.station.StationItem;
import com.amazon.music.subscription.BrowseMode;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class Station extends StationSearchItem implements CatalogContent {
    private ContentCatalogStatus mCatalogStatus;
    private CatalogStatusTiers mCatalogStatusTiers;
    private String mNextToken;
    private boolean mPreferKeyOverNextToken;
    private String mQueueId;
    private RecommendationReason mRecommendationReason;

    @Override // com.amazon.music.find.model.search.SearchItem, com.amazon.mp3.library.item.CatalogContent
    public String getSource() {
        return "cirrus";
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllOwned() {
        return false;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllPreviouslyCatalog() {
        return false;
    }

    public Station() {
    }

    public Station(final CatalogStatusTiers catalogStatusTiers, final String title, final String imageUrl, final String key, final Map<ContentAccessType, Boolean> explicitMap, String mSFASeed, String token) {
        this(catalogStatusTiers, title, imageUrl, key, explicitMap, null, mSFASeed, token, null);
    }

    public Station(final CatalogStatusTiers catalogStatusTiers, final String title, final String imageUrl, final String key, final Map<ContentAccessType, Boolean> explicitMap, String mSFASeed) {
        this(catalogStatusTiers, title, imageUrl, key, explicitMap, null, mSFASeed, null, null);
    }

    public Station(final CatalogStatusTiers catalogStatusTiers, final String title, final String imageUrl, final String key, final Map<ContentAccessType, Boolean> explicitMap) {
        this(catalogStatusTiers, title, imageUrl, key, explicitMap, null, null, null, null);
    }

    public Station(final CatalogStatusTiers catalogStatusTiers, final String title, final String imageUrl, final String key, final Map<ContentAccessType, Boolean> explicitMap, final RecommendationReason recommendationReason, String mSFASeed, String token, String queueId) {
        super(title, imageUrl);
        setKey(key);
        setExplicitMap(explicitMap);
        setSFASeed(mSFASeed);
        setCatalogStatusTiers(catalogStatusTiers);
        this.mRecommendationReason = recommendationReason;
        this.mNextToken = token;
        this.mQueueId = queueId;
    }

    private void setCatalogStatusTiers(CatalogStatusTiers catalogStatusTiers) {
        this.mCatalogStatusTiers = catalogStatusTiers;
        this.mCatalogStatus = ContentAccessUtil.setContentAccess(catalogStatusTiers.getIsMusicSubscription(), this.mCatalogStatusTiers.getIsPrime(), this.mCatalogStatusTiers.getIsSonicRush());
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAnyCatalog() {
        ContentCatalogStatus contentCatalogStatus = this.mCatalogStatus;
        return contentCatalogStatus != null && contentCatalogStatus.isHawkfire();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public ContentCatalogStatus getMaxNonPreviousContentCatalogStatus() {
        return this.mCatalogStatus;
    }

    public String getNextToken() {
        return this.mNextToken;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    /* JADX INFO: renamed from: isAvailable */
    public boolean getIsAvailable() {
        return (AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() && isExplicit()) ? false : true;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getItemTypeName() {
        return AmazonApplication.getContext().getString(R.string.dmusic_library_item_name_station);
    }

    @Override // com.amazon.music.find.model.search.StationSearchItem
    public void setExplicit(final boolean isExplicit) {
        setExplicitMap(PrimeItemsTransformationUtil.buildExplicitMap(isExplicit));
    }

    @Override // com.amazon.music.find.model.search.StationSearchItem
    public void setCatalogStatus(final Set<? extends ContentAccessType> catalogStatus) {
        setCatalogStatusTiers(CatalogStatusTiers.INSTANCE.from(catalogStatus));
    }

    public RecommendationReason getRecommendationReason() {
        return this.mRecommendationReason;
    }

    public CatalogStatusTiers getCatalogStatusTiers() {
        return this.mCatalogStatusTiers;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        Map<ContentAccessType, Boolean> explicitMap = getExplicitMap();
        if (explicitMap == null) {
            return false;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$subscription$BrowseMode$Mode[new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode().ordinal()];
        if (i == 1) {
            return Boolean.TRUE.equals(explicitMap.get(ContentAccessType.PRIME));
        }
        if (i == 2) {
            return Boolean.TRUE.equals(explicitMap.get(ContentAccessType.HAWKFIRE));
        }
        if (i != 3) {
            return false;
        }
        return Boolean.TRUE.equals(explicitMap.get(ContentAccessType.NIGHTWING));
    }

    /* JADX INFO: renamed from: com.amazon.mp3.search.model.Station$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode;

        static {
            int[] iArr = new int[BrowseMode.Mode.values().length];
            $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode = iArr;
            try {
                iArr[BrowseMode.Mode.PRIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode[BrowseMode.Mode.HAWKFIRE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode[BrowseMode.Mode.NIGHTWING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static StationItem toStationItem(Station station) {
        String str;
        String str2;
        String str3;
        String sFASeed = null;
        if (station.mPreferKeyOverNextToken) {
            str3 = "PREFER_KEY_OVER_NEXT_TOKEN";
        } else if (StringUtils.isNotBlank(station.getSFASeed())) {
            sFASeed = station.getSFASeed();
            str3 = "STATION_SEED_ID";
        } else {
            str = null;
            str2 = null;
            StationItem stationItem = new StationItem(station.mCatalogStatusTiers.toSet(), station.getExplicitMap(), station.getTitle(), station.getArtworkUri(), (String) null, (String) null, station.getKey(), station.getSFASeed(), str, str2);
            stationItem.setNextToken(station.getNextToken());
            return stationItem;
        }
        str = str3;
        str2 = sFASeed;
        StationItem stationItem2 = new StationItem(station.mCatalogStatusTiers.toSet(), station.getExplicitMap(), station.getTitle(), station.getArtworkUri(), (String) null, (String) null, station.getKey(), station.getSFASeed(), str, str2);
        stationItem2.setNextToken(station.getNextToken());
        return stationItem2;
    }

    public void setPreferKeyOverNextToken(final boolean preferKeyThanNextToken) throws RuntimeException {
        if (getKey() == null) {
            throw new RuntimeException("key must be set before calling setPreferKeyOverNextToken");
        }
        this.mPreferKeyOverNextToken = preferKeyThanNextToken;
    }
}
