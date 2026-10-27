package com.amazon.podcast;

import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.User;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.podcast.bootstrap.UserInfoProvider;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: UserInfo.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\b\u0010\u000f\u001a\u00020\u000bH\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0016J\b\u0010\u0012\u001a\u00020\bH\u0016J\b\u0010\u0013\u001a\u00020\bH\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/podcast/UserInfo;", "Lcom/amazon/podcast/bootstrap/UserInfoProvider;", "()V", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "benefits", "", "", "customerId", "isBundlesligaTierLaunched", "", "isExplicitLanguageFilterEnabled", "isFreeTierLaunched", "isPodcastLaunched", "isPrimeTierLaunched", "isUnlimitedHDTierLaunched", "isUnlimitedTierLaunched", "marketplaceId", Splash.PARAMS_MUSIC_TERRITORY, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserInfo extends UserInfoProvider {
    private final Logger logger = LoggerFactory.getLogger("UserInfo");

    @Override // com.amazon.podcast.bootstrap.UserInfoProvider
    public String customerId() {
        try {
            String customerId = AccountManagerSingleton.get().getUser().getCustomerId();
            Intrinsics.checkNotNullExpressionValue(customerId, "get()\n                  …              .customerId");
            return customerId;
        } catch (Exception e) {
            this.logger.error("Error retrieving customerId", (Throwable) e);
            return "";
        }
    }

    @Override // com.amazon.podcast.bootstrap.UserInfoProvider
    public String marketplaceId() {
        try {
            Marketplace libraryHomeMarketplace = AccountManagerSingleton.get().getUser().getLibraryHomeMarketplace();
            Intrinsics.checkNotNull(libraryHomeMarketplace);
            String obfuscatedId = libraryHomeMarketplace.getObfuscatedId();
            Intrinsics.checkNotNullExpressionValue(obfuscatedId, "get()\n                  …            .obfuscatedId");
            return obfuscatedId;
        } catch (Exception e) {
            this.logger.error("Error retrieving customerId", (Throwable) e);
            return "";
        }
    }

    @Override // com.amazon.podcast.bootstrap.UserInfoProvider
    public String musicTerritory() {
        try {
            String musicTerritoryOfResidence = AccountManagerSingleton.get().getUser().getMusicTerritoryOfResidence();
            Intrinsics.checkNotNull(musicTerritoryOfResidence);
            Intrinsics.checkNotNullExpressionValue(musicTerritoryOfResidence, "get()\n                  …sicTerritoryOfResidence!!");
            return musicTerritoryOfResidence;
        } catch (Exception e) {
            this.logger.error("Error retrieving musicTerritory", (Throwable) e);
            return "";
        }
    }

    @Override // com.amazon.podcast.bootstrap.UserInfoProvider
    public boolean isPrimeTierLaunched() {
        try {
            return AccountManagerSingleton.get().getUser().isInPrimeMarketplace();
        } catch (Exception e) {
            this.logger.error("Error retrieving isPrimeTierLaunched", (Throwable) e);
            return false;
        }
    }

    @Override // com.amazon.podcast.bootstrap.UserInfoProvider
    public boolean isFreeTierLaunched() {
        try {
            return AccountManagerSingleton.get().getUser().isInNightwingMarketplace();
        } catch (Exception e) {
            this.logger.error("Error retrieving isFreeTierLaunched", (Throwable) e);
            return false;
        }
    }

    @Override // com.amazon.podcast.bootstrap.UserInfoProvider
    public boolean isUnlimitedHDTierLaunched() {
        try {
            return AccountManagerSingleton.get().getUser().isInKatanaMarketplace();
        } catch (Exception e) {
            this.logger.error("Error retrieving inUnlimitedHDTierLaunched", (Throwable) e);
            return false;
        }
    }

    @Override // com.amazon.podcast.bootstrap.UserInfoProvider
    public boolean isBundlesligaTierLaunched() {
        try {
            return AccountManagerSingleton.get().getUser().isInBundesligaMarketplace();
        } catch (Exception e) {
            this.logger.error("Error retrieving isBundlesligaTierLaunched", (Throwable) e);
            return false;
        }
    }

    @Override // com.amazon.podcast.bootstrap.UserInfoProvider
    public boolean isUnlimitedTierLaunched() {
        try {
            return AccountManagerSingleton.get().getUser().isInHawkfireMarketplace();
        } catch (Exception e) {
            this.logger.error("Error retrieving isUnlimitedTierLaunched", (Throwable) e);
            return false;
        }
    }

    @Override // com.amazon.podcast.bootstrap.UserInfoProvider
    public boolean isExplicitLanguageFilterEnabled() {
        try {
            return AccountManagerSingleton.get().getUser().isExplicitFilterEnabledOnDevice();
        } catch (Exception e) {
            this.logger.error("Error retrieving isExplicitLanguageFilterEnabled", (Throwable) e);
            return false;
        }
    }

    @Override // com.amazon.podcast.bootstrap.UserInfoProvider
    public Set<String> benefits() {
        try {
            Set<User.Benefit> benefits = AccountManagerSingleton.get().getUser().getBenefits();
            Intrinsics.checkNotNullExpressionValue(benefits, "get()\n                  …                .benefits");
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(benefits, 10));
            Iterator<T> it = benefits.iterator();
            while (it.hasNext()) {
                arrayList.add(((User.Benefit) it.next()).name());
            }
            return new HashSet(arrayList);
        } catch (Exception e) {
            this.logger.error("Error retrieving benefits", (Throwable) e);
            return new HashSet();
        }
    }

    @Override // com.amazon.podcast.bootstrap.UserInfoProvider
    public boolean isPodcastLaunched() {
        return PodcastFeatureGating.PODCAST.isEnabled();
    }
}
