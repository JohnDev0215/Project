package com.amazon.music.account;

import android.os.Build;
import com.amazon.hermes.Hermes;
import com.amazon.music.account.User;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.marketplace.MarketplaceResolver;
import com.amazon.stratus.AccountInfo;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class UserBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(UserBuilder.class.getSimpleName());

    @Deprecated
    private boolean acceptedTermsOfUse;

    @Deprecated
    private boolean accountVerified;
    private final Set<User.Benefit> benefits;
    private User.CloudAccountStatus cloudAccountStatus;
    private boolean cloudPlayerAccountExists;
    private Marketplace countryOfResidence;
    private long creationDate;
    private String customerId;
    private boolean inBundesligaMarketplace;
    private boolean inHawkfireMarketplace;
    private boolean inKatanaMarketplace;
    private boolean inNightwingMarketplace;
    private boolean inPrimeMarketplace;
    private boolean inSonicRushMusicTerritory;
    private Boolean isAccountValid;
    private boolean isCCBAOptOut;
    private boolean isExplicitFilterEnabledOnDevice;
    private boolean isTargetedAdsEnabled;
    private Marketplace libraryHomeMarketplace;
    private User.MusicAccountStatus musicAccountStatus;
    private String musicTerritoryOfResidence;

    @Deprecated
    private Marketplace termsOfUseMarketplace;

    public UserBuilder() {
        this.isAccountValid = Boolean.FALSE;
        this.customerId = null;
        this.countryOfResidence = null;
        this.libraryHomeMarketplace = null;
        this.musicTerritoryOfResidence = null;
        this.cloudPlayerAccountExists = false;
        this.inKatanaMarketplace = false;
        this.inNightwingMarketplace = false;
        this.inSonicRushMusicTerritory = false;
        this.inPrimeMarketplace = false;
        this.inHawkfireMarketplace = false;
        this.inBundesligaMarketplace = false;
        this.cloudAccountStatus = User.CloudAccountStatus.UNKNOWN;
        this.musicAccountStatus = User.MusicAccountStatus.NOT_CREATED;
        this.benefits = new HashSet();
        this.isExplicitFilterEnabledOnDevice = false;
        this.isTargetedAdsEnabled = true;
        this.isCCBAOptOut = false;
        this.creationDate = 0L;
        this.termsOfUseMarketplace = null;
        this.acceptedTermsOfUse = true;
        this.accountVerified = true;
    }

    public UserBuilder(Boolean bool) {
        this.isAccountValid = Boolean.FALSE;
        this.customerId = null;
        this.countryOfResidence = null;
        this.libraryHomeMarketplace = null;
        this.musicTerritoryOfResidence = null;
        this.cloudPlayerAccountExists = false;
        this.inKatanaMarketplace = false;
        this.inNightwingMarketplace = false;
        this.inSonicRushMusicTerritory = false;
        this.inPrimeMarketplace = false;
        this.inHawkfireMarketplace = false;
        this.inBundesligaMarketplace = false;
        this.cloudAccountStatus = User.CloudAccountStatus.UNKNOWN;
        this.musicAccountStatus = User.MusicAccountStatus.NOT_CREATED;
        this.benefits = new HashSet();
        this.isExplicitFilterEnabledOnDevice = false;
        this.isTargetedAdsEnabled = true;
        this.isCCBAOptOut = false;
        this.creationDate = 0L;
        this.termsOfUseMarketplace = null;
        this.acceptedTermsOfUse = true;
        this.accountVerified = true;
        this.isAccountValid = bool;
    }

    public User build() {
        return new User(this.customerId, this.musicAccountStatus, this.countryOfResidence, this.libraryHomeMarketplace, this.termsOfUseMarketplace, this.musicTerritoryOfResidence, this.acceptedTermsOfUse, this.cloudPlayerAccountExists, this.accountVerified, this.inKatanaMarketplace, this.inNightwingMarketplace, this.inSonicRushMusicTerritory, this.inPrimeMarketplace, this.inHawkfireMarketplace, this.inBundesligaMarketplace, this.cloudAccountStatus, this.benefits, this.isExplicitFilterEnabledOnDevice, this.isTargetedAdsEnabled, this.isCCBAOptOut, this.creationDate);
    }

    public UserBuilder withCustomerHomeInfo(String str, String str2, String str3) {
        this.libraryHomeMarketplace = MarketplaceResolver.lookupByObfuscatedId(str);
        this.musicTerritoryOfResidence = str2;
        this.customerId = str3;
        return this;
    }

    public UserBuilder withAccountInfo(AccountInfo accountInfo) {
        User.CloudAccountStatus cloudAccountStatusFromString;
        User.MusicAccountStatus musicAccountStatus;
        if (accountInfo != null) {
            this.cloudPlayerAccountExists = true;
            this.customerId = accountInfo.getCustomerId();
            Date creationDate = accountInfo.getCreationDate();
            if (creationDate != null) {
                this.creationDate = creationDate.getTime();
            }
            Marketplace marketplaceLookupByObfuscatedId = MarketplaceResolver.lookupByObfuscatedId(accountInfo.getMarketplaceId());
            this.libraryHomeMarketplace = marketplaceLookupByObfuscatedId;
            this.countryOfResidence = marketplaceLookupByObfuscatedId;
            this.musicTerritoryOfResidence = accountInfo.getMusicTerritory();
            String accountStatus = accountInfo.getAccountStatus();
            if (accountStatus != null && !accountStatus.isEmpty()) {
                cloudAccountStatusFromString = User.CloudAccountStatus.fromString(accountStatus);
            } else {
                cloudAccountStatusFromString = User.CloudAccountStatus.ACTIVE;
            }
            this.cloudAccountStatus = cloudAccountStatusFromString;
            if (Boolean.TRUE.equals(this.isAccountValid)) {
                musicAccountStatus = User.MusicAccountStatus.VALID;
            } else {
                musicAccountStatus = User.MusicAccountStatus.INVALID;
            }
            this.musicAccountStatus = musicAccountStatus;
        }
        return this;
    }

    private boolean isBenefitAvailable(Map<String, String> map, User.Benefit benefit) {
        return map.containsKey(benefit.toString());
    }

    private void addBenefitIfPresent(Map<String, String> map, User.Benefit[] benefitArr) {
        for (User.Benefit benefit : benefitArr) {
            if (map.get(benefit.toString()).equals("true")) {
                this.benefits.add(benefit);
            }
        }
    }

    public UserBuilder withBenefits(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            User.Benefit benefit = User.Benefit.SONIC_RUSH;
            if (isBenefitAvailable(map, benefit) && Build.VERSION.SDK_INT >= 26) {
                this.inSonicRushMusicTerritory = true;
                addBenefitIfPresent(map, new User.Benefit[]{benefit});
            }
            User.Benefit benefit2 = User.Benefit.PRIME_MUSIC_BROWSE;
            if (isBenefitAvailable(map, benefit2)) {
                this.inPrimeMarketplace = true;
                addBenefitIfPresent(map, new User.Benefit[]{benefit2, User.Benefit.PRIME_MUSIC_CONTENT_ACCESS});
            }
            User.Benefit benefit3 = User.Benefit.HAWKFIRE_BROWSE_AND_ADD;
            if (isBenefitAvailable(map, benefit3)) {
                this.inHawkfireMarketplace = true;
                addBenefitIfPresent(map, new User.Benefit[]{benefit3, User.Benefit.HAWKFIRE_PLAYBACK_ACCESS});
            }
            User.Benefit benefit4 = User.Benefit.HAWKFIRE_KATANA_ACCESS;
            if (isBenefitAvailable(map, benefit4)) {
                this.inKatanaMarketplace = true;
                addBenefitIfPresent(map, new User.Benefit[]{benefit4});
            }
            User.Benefit benefit5 = User.Benefit.NIGHTWING;
            if (isBenefitAvailable(map, benefit5)) {
                this.inNightwingMarketplace = true;
                addBenefitIfPresent(map, new User.Benefit[]{benefit5});
            }
            User.Benefit benefit6 = User.Benefit.BUNDESLIGA_BROWSE;
            if (isBenefitAvailable(map, benefit6)) {
                this.inBundesligaMarketplace = true;
                addBenefitIfPresent(map, new User.Benefit[]{benefit6, User.Benefit.BUNDESLIGA_FREE_PLAYBACK, User.Benefit.BUNDESLIGA_PRIME_PLAYBACK, User.Benefit.BUNDESLIGA_UNLIMITED_PLAYBACK});
            }
        }
        return this;
    }

    public UserBuilder withPreferences(Map<String, Map<String, String>> map, String str) {
        if (map != null && !map.isEmpty()) {
            Map<String, String> map2 = map.get(str);
            if (map2 != null && !map2.isEmpty()) {
                try {
                    this.isExplicitFilterEnabledOnDevice = !((Boolean) ((Map) Hermes.get().getObjectMapper().readValue(map2.get("allowedParentalControls"), Map.class)).get("hasExplicitLanguage")).booleanValue();
                } catch (IOException e) {
                    LOG.error("Error occurred when parsing PreferencesByNamespace", (Throwable) e);
                }
            }
            Map<String, String> map3 = map.get("adPreferences");
            if (map3 != null && map3.containsKey("isBehavioralAdsEnabled")) {
                this.isTargetedAdsEnabled = Boolean.parseBoolean(map3.get("isBehavioralAdsEnabled"));
            }
        }
        return this;
    }

    public UserBuilder withCrossContextualBehavioralAdsOptOutPreferences(Map<String, Map<String, String>> map) {
        Map<String, String> map2;
        if (map != null && !map.isEmpty() && (map2 = map.get("ccba-opt-out-preference")) != null && map2.containsKey("CCBAOptOut")) {
            this.isCCBAOptOut = Objects.equals(map2.get("CCBAOptOut"), "OPT_OUT");
        }
        return this;
    }
}
