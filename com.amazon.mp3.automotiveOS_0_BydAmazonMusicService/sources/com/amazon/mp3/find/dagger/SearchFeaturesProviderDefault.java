package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.featuregate.PCAEvaluator;
import com.amazon.mp3.find.util.AlexaUtil;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.FireOSSonicRushFeature;
import com.amazon.music.alps.gating.AlpsFeatureGating;
import com.amazon.music.find.featuregating.FindFeatureGating;
import com.amazon.music.find.model.EntityMetaData;
import com.amazon.music.find.model.search.SearchLibrarySource;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ViewType;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.ProfileProvider;
import com.amazon.music.search.SearchContentTier;
import com.amazon.music.skyfire.core.network.CapabilitiesInfo;
import com.amazon.music.subscription.BrowseMode;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.NavigationMetadata;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: SearchFeaturesProviderDefault.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \\2\u00020\u00012\u00020\u0002:\u0001\\B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00070BH\u0016J\b\u0010C\u001a\u00020DH\u0002J\u0012\u0010E\u001a\u0004\u0018\u00010F2\u0006\u0010G\u001a\u00020HH\u0016J\u0012\u0010I\u001a\u0004\u0018\u00010F2\u0006\u0010J\u001a\u00020KH\u0016J\u0010\u0010L\u001a\u00020\u00072\u0006\u0010M\u001a\u00020NH\u0016J\u0018\u0010O\u001a\u00020\u00072\u0006\u0010P\u001a\u00020\u00072\u0006\u0010Q\u001a\u00020\u0007H\u0016J\u0012\u0010R\u001a\u00020\n2\b\u0010S\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010T\u001a\u00020\nH\u0002J\u0010\u0010U\u001a\u00020\n2\u0006\u0010V\u001a\u00020DH\u0002J\u0010\u0010W\u001a\u00020\n2\u0006\u0010V\u001a\u00020DH\u0002J\u0010\u0010X\u001a\u00020\n2\u0006\u0010V\u001a\u00020DH\u0002J\u0012\u0010Y\u001a\u00020\n2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0016R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\fR\u0014\u0010\u0013\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\fR\u0014\u0010\u0014\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\fR\u0014\u0010\u0015\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\fR\u0014\u0010\u0016\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\fR\u0016\u0010\u0017\u001a\u0004\u0018\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\fR\u0014\u0010\u001a\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\fR\u0011\u0010\u001b\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\fR\u0014\u0010\u001c\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\fR\u0011\u0010\u001d\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\fR\u0011\u0010\u001e\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\fR\u0014\u0010\u001f\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\fR\u0014\u0010 \u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\fR\u0014\u0010!\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\fR\u0014\u0010\"\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\fR\u0011\u0010#\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b#\u0010\fR\u0011\u0010$\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b$\u0010\fR\u0014\u0010%\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\fR\u0014\u0010&\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\fR\u0011\u0010'\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b'\u0010\fR\u0014\u0010(\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\fR\u0011\u0010)\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b)\u0010\fR\u0014\u0010*\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\fR\u0014\u0010+\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\fR\u0014\u0010,\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\fR\u0014\u0010-\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010\fR\u0011\u0010.\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b.\u0010\fR\u0014\u0010/\u001a\u0002008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\u0002068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u0014\u00109\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u0010\fR\u0014\u0010;\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010\fR\u0016\u0010=\u001a\u0004\u0018\u00010>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010@¨\u0006]"}, d2 = {"Lcom/amazon/mp3/find/dagger/SearchFeaturesProviderDefault;", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "Lcom/amazon/music/skyfire/core/network/CapabilitiesInfo;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "areIvyRefinementPillsEnabled", "", "getAreIvyRefinementPillsEnabled", "()Z", "areQualityFiltersEnabled", "getAreQualityFiltersEnabled", "currentProfileMusicDirectedId", "getCurrentProfileMusicDirectedId", "()Ljava/lang/String;", "isAlexaSearchFindBrowseEnabled", "isAlexaSearchOrdinalPlaybackEnabled", "isBundesligaAccessible", "isChildDirected", "isCommunityPlaylistEnabled", "isDefaultProfile", "()Ljava/lang/Boolean;", "isExplicitLanguageFilterEnabled", "isFireOS", "isFireOSSonicRushEnabled", "isFreeTierCustomer", "isHawkfire", "isHawkfireKatana", "isInCarMode", "isKatana", "isLanguageOfPerformanceEnabled", "isLiveEventEnabled", "isMerchEnabled", "isMusicVideosEnabled", "isOffline", "isPodcastEnabled", "isPrime", "isSearchResultsCachingEnabled", "isSpatialAudioEnabled", "isUpsellToUnlimitedOnTopHitsTurnedOn", "isUserInHawkfireMarketplace", "isVideoPlaylistEnabled", "isVideosEnabled", "isWoodstockEnabled", "librarySource", "Lcom/amazon/music/find/model/search/SearchLibrarySource;", "getLibrarySource", "()Lcom/amazon/music/find/model/search/SearchLibrarySource;", "profileProvider", "Lcom/amazon/music/platform/providers/ProfileProvider;", "searchContentTier", "Lcom/amazon/music/search/SearchContentTier;", "getSearchContentTier", "()Lcom/amazon/music/search/SearchContentTier;", "shouldRequestRelatedEntities", "getShouldRequestRelatedEntities", "shouldSuppressIvyCatalogNonTrackAlbumFiltering", "getShouldSuppressIvyCatalogNonTrackAlbumFiltering", "viewType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ViewType;", "getViewType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ViewType;", "featureFlags", "", "getBrowseMode", "Lcom/amazon/music/subscription/BrowseMode$Mode;", "getEntityInfoFromContentMetaData", "Lcom/amazon/music/find/model/EntityMetaData;", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getEntityInfoFromNavigationMetaData", "navigationMetadata", "Lcom/amazon/music/views/library/metadata/NavigationMetadata;", "getLocalizedString", "id", "", "getPodcastShowContentUrl", "podcastId", "title", "isLibrarySource", PageUriUtils.SOURCE_QUERY_PARAM, "isLiveEventSearchEnabled", "isRelatedEntitySearchSupported", "mode", "isVideoPlaylistSearchSupported", "isVideoSearchSupported", "shouldRequestPlaymodeEligibility", "entity", "Lcom/amazon/music/platform/find/common/EntityType;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchFeaturesProviderDefault implements SearchFeaturesProvider, CapabilitiesInfo {
    private final String TAG;
    private final Context context;
    private final ProfileProvider profileProvider;

    /* JADX INFO: compiled from: SearchFeaturesProviderDefault.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[BrowseMode.Mode.values().length];
            iArr[BrowseMode.Mode.PRIME.ordinal()] = 1;
            iArr[BrowseMode.Mode.SONIC_RUSH.ordinal()] = 2;
            iArr[BrowseMode.Mode.HAWKFIRE.ordinal()] = 3;
            iArr[BrowseMode.Mode.NIGHTWING.ordinal()] = 4;
            iArr[BrowseMode.Mode.KATANA.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[EntityType.values().length];
            iArr2[EntityType.ALBUM.ordinal()] = 1;
            iArr2[EntityType.TRACK.ordinal()] = 2;
            iArr2[EntityType.PLAYLIST.ordinal()] = 3;
            iArr2[EntityType.COMMUNITY_PLAYLIST.ordinal()] = 4;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isBundesligaAccessible() {
        return false;
    }

    public SearchFeaturesProviderDefault(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = SearchFeaturesProviderDefault.class.getSimpleName();
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ProfileProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        this.profileProvider = (ProfileProvider) (interfaceProvider instanceof ProfileProvider ? interfaceProvider : null);
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean getFilterByChildFriendlyContent() {
        return SearchFeaturesProvider.DefaultImpls.getFilterByChildFriendlyContent(this);
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean getFilterByExplicitLanguage() {
        return SearchFeaturesProvider.DefaultImpls.getFilterByExplicitLanguage(this);
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean getAreIvyRefinementPillsEnabled() {
        return AmazonApplication.getCapabilities().areIvyRefinementPillsEnabled();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean getAreQualityFiltersEnabled() {
        return isSpatialAudioEnabled();
    }

    public final boolean isSpatialAudioEnabled() {
        return AmazonApplication.getCapabilities().isSpatialAudioEnabled(this.context);
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isSearchResultsCachingEnabled() {
        return AmazonApplication.getCapabilities().isSearchResultsCachingEnabled();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isExplicitLanguageFilterEnabled() {
        return AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isChildDirected() {
        return ChildUserUtil.INSTANCE.isChildUser(this.context);
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public Boolean isDefaultProfile() {
        if (!PCAEvaluator.isEnabled$default(new PCAEvaluator(this.context), false, 1, null)) {
            return null;
        }
        ProfileProvider profileProvider = this.profileProvider;
        if (profileProvider != null) {
            profileProvider.currentProfile();
        }
        return Boolean.FALSE;
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public String getCurrentProfileMusicDirectedId() {
        ProfileProvider profileProvider;
        if (!PCAEvaluator.isEnabled$default(new PCAEvaluator(this.context), false, 1, null) || (profileProvider = this.profileProvider) == null) {
            return SQLiteCommandFactory.UNDEFINED;
        }
        profileProvider.currentProfile();
        return SQLiteCommandFactory.UNDEFINED;
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isVideosEnabled() {
        return AmazonApplication.getCapabilities().isVideosEnabled() && isVideoSearchSupported(getBrowseMode()) && FindFeatureGating.VIDEO_SEARCH_ENABLED.isEnabled();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isVideoPlaylistEnabled() {
        return AmazonApplication.getCapabilities().isVideosEnabled() && isVideoPlaylistSearchSupported(getBrowseMode()) && FindFeatureGating.VIDEO_PLAYLIST_SEARCH_ENABLED.isEnabled();
    }

    public final boolean isMusicVideosEnabled() {
        return AmazonApplication.getCapabilities().isMusicVideosEnabled();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isPodcastEnabled() {
        return PodcastFeatureGating.PODCAST.isEnabled();
    }

    public final boolean isWoodstockEnabled() {
        return AmazonApplication.getCapabilities().isWoodstockEnabled();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isKatana() {
        return UserSubscriptionUtil.getUserSubscription().isKatana();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isFreeTierCustomer() {
        return UserSubscriptionUtil.isNightwingOnly();
    }

    public final boolean isPrime() {
        return UserSubscriptionUtil.getUserSubscription().isPrimeOnly();
    }

    public final boolean isHawkfire() {
        return UserSubscriptionUtil.getUserSubscription().isHawkfireAllDevices();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isUserInHawkfireMarketplace() {
        return AccountDetailUtil.get(this.context).isUserInHawkfireMarketplace();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public SearchContentTier getSearchContentTier() {
        int i = WhenMappings.$EnumSwitchMapping$0[getBrowseMode().ordinal()];
        if (i == 1) {
            return SearchContentTier.PRIME;
        }
        if (i == 2) {
            return SearchContentTier.SONIC_RUSH;
        }
        if (i == 3) {
            return SearchContentTier.UNLIMITED;
        }
        if (i == 4) {
            return SearchContentTier.NIGHTWING_ONDEMAND_PLAYABLE;
        }
        return SearchContentTier.UNLIMITED;
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isUpsellToUnlimitedOnTopHitsTurnedOn() {
        BrowseMode.Mode browseMode = getBrowseMode();
        return (browseMode == BrowseMode.Mode.HAWKFIRE || browseMode == BrowseMode.Mode.KATANA || browseMode == BrowseMode.Mode.SONIC_RUSH || !AccountDetailUtil.get(this.context).isUserInHawkfireMarketplace() || CarModeUtility.isInCarMode()) ? false : true;
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public ViewType getViewType() {
        return ScreenUtil.getScreenViewType(this.context);
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isOffline() {
        return !ConnectivityUtil.hasAnyInternetConnection(this.context);
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isInCarMode() {
        return CarModeUtility.isInCarMode();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isLiveEventEnabled() {
        return AmazonApplication.getCapabilities().isLiveEventEnabled() && isLiveEventSearchEnabled();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public SearchLibrarySource getLibrarySource() {
        return isOffline() ? SearchLibrarySource.DEVICE : SearchLibrarySource.CLOUD;
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean getShouldSuppressIvyCatalogNonTrackAlbumFiltering() {
        return AmazonApplication.getCapabilities().shouldSuppressIvyCatalogNonTrackAlbumFiltering();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isFireOS() {
        return PlatformUtil.isFireOS();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isAlexaSearchFindBrowseEnabled() {
        return AlexaUtil.isAlexaSearchFindBrowseEnabled();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isAlexaSearchOrdinalPlaybackEnabled() {
        return AlexaUtil.isAlexaSearchOrdinalPlaybackEnabled();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean getShouldRequestRelatedEntities() {
        return isRelatedEntitySearchSupported(getBrowseMode()) && !isInCarMode();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isCommunityPlaylistEnabled() {
        return AmazonApplication.getCapabilities().isCommunityPlaylistEnabled() && !isInCarMode();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isLanguageOfPerformanceEnabled() {
        return FindFeatureGating.LANGUAGE_OF_PERFORMANCE_BADGING.isEnabled();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean shouldRequestPlaymodeEligibility(EntityType entity) {
        if (!isRelatedEntitySearchSupported(getBrowseMode())) {
            return false;
        }
        int i = entity == null ? -1 : WhenMappings.$EnumSwitchMapping$1[entity.ordinal()];
        return i == 1 || i == 2 || i == 3 || i == 4;
    }

    public final boolean isHawkfireKatana() {
        return UserSubscriptionUtil.isHawkfireKatana();
    }

    public final boolean isMerchEnabled() {
        return AmazonApplication.getCapabilities().isMerchEnabled();
    }

    public final boolean isFireOSSonicRushEnabled() {
        return new FireOSSonicRushFeature().isEnabled();
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public boolean isLibrarySource(String source) {
        return Intrinsics.areEqual(source, "cirrus") || Intrinsics.areEqual(source, "cirrus-local");
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public EntityMetaData getEntityInfoFromContentMetaData(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        return SearchItemUtils.getEntityInfoFromContentMetaData(contentMetadata);
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public EntityMetaData getEntityInfoFromNavigationMetaData(NavigationMetadata navigationMetadata) {
        Intrinsics.checkNotNullParameter(navigationMetadata, "navigationMetadata");
        return SearchItemUtils.getEntityInfoFromNavigationMetaData(navigationMetadata);
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public String getLocalizedString(int id) {
        String string = this.context.getString(id);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(id)");
        return string;
    }

    @Override // com.amazon.music.skyfire.core.network.CapabilitiesInfo
    public List<String> featureFlags() {
        ArrayList arrayList = new ArrayList();
        if (isMusicVideosEnabled()) {
            arrayList.add("includeVideo");
        }
        if (isPodcastEnabled()) {
            arrayList.add("includePodcast");
        }
        if (isWoodstockEnabled() && !PlatformUtil.isFireOS()) {
            arrayList.add("includeLiveStream");
        }
        if (isSpatialAudioEnabled() && !PlatformUtil.isFireOS()) {
            arrayList.add("includeSpatialAudio");
        }
        if (isHawkfireKatana()) {
            arrayList.add("includeUHD");
        }
        if (isMerchEnabled()) {
            arrayList.add("includeMerch");
        }
        if (ChildUserUtil.INSTANCE.isChildUser(this.context)) {
            arrayList.add("isChildUser");
        }
        if (SettingsActivity.shouldEnableFindLandingPageDevelopmentMode(this.context)) {
            arrayList.add("enableFindLandingPageDevelopmentMode");
        }
        if (isKatana()) {
            arrayList.add("contentTierKatana");
        } else if (isHawkfire()) {
            arrayList.add("contentTierMusicSubscription");
        } else if (isPrime()) {
            arrayList.add("contentTierPrime");
        } else if (isFreeTierCustomer()) {
            arrayList.add("contentTierFree");
        }
        arrayList.add("useBrushV3CategoryPages");
        if (AmazonApplication.getCapabilities().isSonicRushEnabled()) {
            arrayList.add("isSonicRushEnabled");
        }
        if (AmazonApplication.getCapabilities().isSonicRushEnabled()) {
            arrayList.add("isSonicRushEnabled");
        }
        if (isFireOS()) {
            arrayList.add("isFireOS");
        }
        if (isFireOSSonicRushEnabled()) {
            arrayList.add("isFireOSSonicRushEnabled");
        }
        return CollectionsKt___CollectionsKt.toList(arrayList);
    }

    @Override // com.amazon.music.find.providers.SearchFeaturesProvider
    public String getPodcastShowContentUrl(String podcastId, String title) {
        Intrinsics.checkNotNullParameter(podcastId, "podcastId");
        Intrinsics.checkNotNullParameter(title, "title");
        String strPodcastForSearch = Deeplinks.podcastForSearch(podcastId, title);
        Intrinsics.checkNotNullExpressionValue(strPodcastForSearch, "podcastForSearch(podcastId, title)");
        return strPodcastForSearch;
    }

    private final BrowseMode.Mode getBrowseMode() {
        BrowseMode.Mode browseMode = new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode();
        Intrinsics.checkNotNullExpressionValue(browseMode, "BrowseMode(AccountManage…)\n            .browseMode");
        return browseMode;
    }

    private final boolean isRelatedEntitySearchSupported(BrowseMode.Mode mode) {
        int i = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
        if (i == 2) {
            return true;
        }
        if (i != 4) {
            return false;
        }
        return FindFeatureGating.RELATED_ENTITY_SEARCH_FREE.isEnabled();
    }

    private final boolean isVideoSearchSupported(BrowseMode.Mode mode) {
        int i = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
        return i == 2 || i == 3 || i == 5;
    }

    private final boolean isVideoPlaylistSearchSupported(BrowseMode.Mode mode) {
        int i = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
        return i == 3 || i == 5;
    }

    private final boolean isLiveEventSearchEnabled() {
        return AlpsFeatureGating.LIVE_EVENT_SEARCH_TOP_RESULTS.isEnabled();
    }
}
