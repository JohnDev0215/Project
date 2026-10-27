package com.amazon.music.account;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.marketplace.Marketplace;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class User {
    private final boolean acceptedTermsOfUse;
    private final boolean accountVerified;
    private final Set<Benefit> benefits;
    private final CloudAccountStatus cloudAccountStatus;
    private final boolean cloudPlayerAccountExists;
    private final Marketplace countryOfResidence;
    private final long creationDate;
    private final String customerId;
    private final boolean inBundesligaMarketplace;
    private final boolean inHawkfireMarketplace;
    private final boolean inKatanaMarketplace;
    private final boolean inNightwingMarketplace;
    private final boolean inPrimeMarketplace;
    private final boolean inSonicRushMusicTerritory;
    private final boolean isCCBAOptOut;
    private final boolean isExplicitFilterEnabledOnDevice;
    private final boolean isTargetedAdsEnabled;
    private final Marketplace libraryHomeMarketplace;
    private final MusicAccountStatus musicAccountStatus;
    private final String musicTerritoryOfResidence;
    private final Marketplace termsOfUseMarketplace;

    public enum Benefit {
        SONIC_RUSH,
        PRIME_MUSIC_BROWSE,
        PRIME_MUSIC_CONTENT_ACCESS,
        HAWKFIRE_PLAYBACK_ACCESS,
        HAWKFIRE_BROWSE_AND_ADD,
        BUNDESLIGA_BROWSE,
        BUNDESLIGA_FREE_PLAYBACK,
        BUNDESLIGA_PRIME_PLAYBACK,
        BUNDESLIGA_UNLIMITED_PLAYBACK,
        NIGHTWING,
        HAWKFIRE_KATANA_ACCESS
    }

    public enum CloudAccountStatus {
        ACTIVE("ACTIVE"),
        INACTIVE("INACTIVE"),
        LOCKED("LOCKED"),
        UNKNOWN("UNKNOWN");

        public final String mStatus;

        CloudAccountStatus(String str) {
            this.mStatus = str;
        }

        public static CloudAccountStatus fromString(String str) {
            return valueOf(str);
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mStatus;
        }
    }

    public enum MusicAccountStatus {
        VALID("VALID"),
        INVALID("INVALID"),
        NOT_CREATED("NOT_CREATED");

        public final String mStatus;

        MusicAccountStatus(String str) {
            this.mStatus = str;
        }

        public static MusicAccountStatus fromString(String str) {
            return valueOf(str);
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mStatus;
        }
    }

    public User(@Nullable String str, @NonNull MusicAccountStatus musicAccountStatus, @Nullable Marketplace marketplace, @Nullable Marketplace marketplace2, @Nullable Marketplace marketplace3, @Nullable String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, @NonNull CloudAccountStatus cloudAccountStatus, @Nullable Set<Benefit> set, boolean z10, boolean z11, boolean z12, long j) {
        this.customerId = str;
        this.musicAccountStatus = musicAccountStatus;
        this.countryOfResidence = marketplace;
        this.libraryHomeMarketplace = marketplace2;
        this.termsOfUseMarketplace = marketplace3;
        this.musicTerritoryOfResidence = str2;
        this.acceptedTermsOfUse = z;
        this.cloudPlayerAccountExists = z2;
        this.accountVerified = z3;
        this.inKatanaMarketplace = z4;
        this.inNightwingMarketplace = z5;
        this.inSonicRushMusicTerritory = z6;
        this.inPrimeMarketplace = z7;
        this.inHawkfireMarketplace = z8;
        this.inBundesligaMarketplace = z9;
        this.cloudAccountStatus = cloudAccountStatus;
        this.benefits = set;
        this.isExplicitFilterEnabledOnDevice = z10;
        this.isTargetedAdsEnabled = z11;
        this.isCCBAOptOut = z12;
        this.creationDate = j;
    }

    @NonNull
    public String getCustomerId() {
        return this.customerId;
    }

    @Nullable
    public Marketplace getCountryOfResidence() {
        return this.countryOfResidence;
    }

    @Nullable
    public Marketplace getLibraryHomeMarketplace() {
        return this.libraryHomeMarketplace;
    }

    @Nullable
    public Marketplace getTermsOfUseMarketplace() {
        return this.termsOfUseMarketplace;
    }

    @Nullable
    public String getMusicTerritoryOfResidence() {
        return this.musicTerritoryOfResidence;
    }

    public boolean hasAcceptedTermsOfUse() {
        return this.acceptedTermsOfUse;
    }

    public boolean hasCloudPlayerAccount() {
        return this.cloudPlayerAccountExists;
    }

    public boolean isAccountVerified() {
        return this.accountVerified;
    }

    public boolean isInNightwingMarketplace() {
        return this.inNightwingMarketplace;
    }

    public boolean isInKatanaMarketplace() {
        return this.inKatanaMarketplace;
    }

    public boolean isInSonicRushMusicTerritory() {
        return this.inSonicRushMusicTerritory;
    }

    public boolean isInPrimeMarketplace() {
        return this.inPrimeMarketplace;
    }

    public boolean isInHawkfireMarketplace() {
        return this.inHawkfireMarketplace;
    }

    public boolean isInBundesligaMarketplace() {
        return this.inBundesligaMarketplace;
    }

    public boolean isAccountLocked() {
        return CloudAccountStatus.LOCKED.equals(this.cloudAccountStatus);
    }

    public CloudAccountStatus getCloudAccountStatus() {
        return this.cloudAccountStatus;
    }

    protected MusicAccountStatus getMusicAccountStatus() {
        return this.musicAccountStatus;
    }

    public boolean isAccountValid() {
        return MusicAccountStatus.VALID.equals(this.musicAccountStatus);
    }

    public boolean isAccountNotCreated() {
        return MusicAccountStatus.NOT_CREATED.equals(this.musicAccountStatus);
    }

    public boolean isExplicitFilterEnabledOnDevice() {
        return this.isExplicitFilterEnabledOnDevice;
    }

    public boolean isTargetedAdsEnabled() {
        return this.isTargetedAdsEnabled;
    }

    public boolean isCCBAOptOut() {
        return this.isCCBAOptOut;
    }

    public long getCreationDate() {
        return this.creationDate;
    }

    @NonNull
    public Set<Benefit> getBenefits() {
        return this.benefits;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return equals(this.customerId, user.customerId) && equals(this.musicAccountStatus, user.musicAccountStatus) && equals(Boolean.valueOf(this.acceptedTermsOfUse), Boolean.valueOf(user.acceptedTermsOfUse)) && equals(Boolean.valueOf(this.cloudPlayerAccountExists), Boolean.valueOf(user.cloudPlayerAccountExists)) && equals(Boolean.valueOf(this.accountVerified), Boolean.valueOf(user.accountVerified)) && equals(Boolean.valueOf(this.inKatanaMarketplace), Boolean.valueOf(user.inKatanaMarketplace)) && equals(Boolean.valueOf(this.inNightwingMarketplace), Boolean.valueOf(user.inNightwingMarketplace)) && equals(Boolean.valueOf(this.inSonicRushMusicTerritory), Boolean.valueOf(user.inSonicRushMusicTerritory)) && equals(Boolean.valueOf(this.inPrimeMarketplace), Boolean.valueOf(user.inPrimeMarketplace)) && equals(Boolean.valueOf(this.inHawkfireMarketplace), Boolean.valueOf(user.inHawkfireMarketplace)) && equals(Boolean.valueOf(this.inBundesligaMarketplace), Boolean.valueOf(user.inBundesligaMarketplace)) && equals(this.cloudAccountStatus, user.cloudAccountStatus) && equals(this.countryOfResidence, user.countryOfResidence) && equals(this.libraryHomeMarketplace, user.libraryHomeMarketplace) && equals(this.termsOfUseMarketplace, user.termsOfUseMarketplace) && equals(this.musicTerritoryOfResidence, user.musicTerritoryOfResidence) && equals(this.benefits, user.benefits) && equals(Boolean.valueOf(this.isExplicitFilterEnabledOnDevice), Boolean.valueOf(user.isExplicitFilterEnabledOnDevice)) && equals(Boolean.valueOf(this.isTargetedAdsEnabled), Boolean.valueOf(user.isTargetedAdsEnabled)) && equals(Boolean.valueOf(this.isCCBAOptOut), Boolean.valueOf(user.isCCBAOptOut)) && equals(Long.valueOf(this.creationDate), Long.valueOf(user.creationDate));
    }

    public int hashCode() {
        return hash(this.customerId, this.musicAccountStatus, this.countryOfResidence, this.libraryHomeMarketplace, this.termsOfUseMarketplace, this.musicTerritoryOfResidence, Boolean.valueOf(this.acceptedTermsOfUse), Boolean.valueOf(this.cloudPlayerAccountExists), Boolean.valueOf(this.accountVerified), Boolean.valueOf(this.inKatanaMarketplace), Boolean.valueOf(this.inNightwingMarketplace), Boolean.valueOf(this.inSonicRushMusicTerritory), Boolean.valueOf(this.inPrimeMarketplace), Boolean.valueOf(this.inHawkfireMarketplace), Boolean.valueOf(this.inBundesligaMarketplace), this.cloudAccountStatus, this.benefits, Boolean.valueOf(this.isExplicitFilterEnabledOnDevice), Boolean.valueOf(this.isTargetedAdsEnabled), Boolean.valueOf(this.isCCBAOptOut));
    }

    private int hash(Object... objArr) {
        int length = objArr.length;
        int iHashCode = 1;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            iHashCode = (iHashCode * 31) + (obj == null ? 0 : obj.hashCode());
        }
        return iHashCode;
    }

    private boolean equals(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }
}
