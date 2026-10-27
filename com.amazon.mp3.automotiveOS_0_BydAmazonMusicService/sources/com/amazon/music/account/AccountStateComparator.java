package com.amazon.music.account;

import java.util.EnumSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
class AccountStateComparator {
    AccountStateComparator() {
    }

    public Set<AccountStateField> getDifferences(User user, User user2) {
        EnumSet enumSetNoneOf = EnumSet.noneOf(AccountStateField.class);
        if (user != null || user2 != null) {
            if (user == null || user2 == null) {
                enumSetNoneOf.addAll(EnumSet.of(AccountStateField.USER_COUNTRY_OF_RESIDENCE, AccountStateField.USER_LIBRARY_HOME_MARKETPLACE, AccountStateField.USER_TERMS_OF_USE_MARKETPLACE, AccountStateField.USER_MUSIC_TERRITORY_OF_RESIDENCE, AccountStateField.USER_HAS_ACCEPTED_TERMS_OF_USE, AccountStateField.USER_HAS_CLOUD_PLAYER_ACCOUNT, AccountStateField.USER_IS_ACCOUNT_VERIFIED, AccountStateField.USER_IS_IN_KATANA_MARKETPLACE, AccountStateField.USER_IS_IN_NIGHTWING_MARKETPLACE, AccountStateField.USER_IS_IN_SONIC_RUSH_MUSIC_TERRITORY, AccountStateField.USER_IS_IN_PRIME_MARKETPLACE, AccountStateField.USER_IS_IN_HAWKFIRE_MARKETPLACE, AccountStateField.USER_IS_ACCOUNT_LOCKED, AccountStateField.USER_BENEFITS, AccountStateField.IS_ACCOUNT_VALID, AccountStateField.ACCOUNT_MIGRATED, AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE, AccountStateField.TARGETED_ADS_ENABLED, AccountStateField.CCBA_OPT_OUT));
            } else {
                if (!equals(user.getCountryOfResidence(), user2.getCountryOfResidence())) {
                    enumSetNoneOf.add(AccountStateField.USER_COUNTRY_OF_RESIDENCE);
                }
                if (!equals(user.getLibraryHomeMarketplace(), user2.getLibraryHomeMarketplace())) {
                    enumSetNoneOf.add(AccountStateField.USER_LIBRARY_HOME_MARKETPLACE);
                }
                if (!equals(user.getTermsOfUseMarketplace(), user2.getTermsOfUseMarketplace())) {
                    enumSetNoneOf.add(AccountStateField.USER_TERMS_OF_USE_MARKETPLACE);
                }
                if (!equals(user.getMusicTerritoryOfResidence(), user2.getMusicTerritoryOfResidence())) {
                    enumSetNoneOf.add(AccountStateField.USER_MUSIC_TERRITORY_OF_RESIDENCE);
                }
                if (user.hasAcceptedTermsOfUse() != user2.hasAcceptedTermsOfUse()) {
                    enumSetNoneOf.add(AccountStateField.USER_HAS_ACCEPTED_TERMS_OF_USE);
                }
                if (user.hasCloudPlayerAccount() != user2.hasCloudPlayerAccount()) {
                    enumSetNoneOf.add(AccountStateField.USER_HAS_CLOUD_PLAYER_ACCOUNT);
                }
                if (user.isAccountVerified() != user2.isAccountVerified()) {
                    enumSetNoneOf.add(AccountStateField.USER_IS_ACCOUNT_VERIFIED);
                }
                if (user.isInKatanaMarketplace() != user2.isInKatanaMarketplace()) {
                    enumSetNoneOf.add(AccountStateField.USER_IS_IN_KATANA_MARKETPLACE);
                }
                if (user.isInNightwingMarketplace() != user2.isInNightwingMarketplace()) {
                    enumSetNoneOf.add(AccountStateField.USER_IS_IN_NIGHTWING_MARKETPLACE);
                }
                if (user.isInSonicRushMusicTerritory() != user2.isInSonicRushMusicTerritory()) {
                    enumSetNoneOf.add(AccountStateField.USER_IS_IN_SONIC_RUSH_MUSIC_TERRITORY);
                }
                if (user.isInPrimeMarketplace() != user2.isInPrimeMarketplace()) {
                    enumSetNoneOf.add(AccountStateField.USER_IS_IN_PRIME_MARKETPLACE);
                }
                if (user.isInHawkfireMarketplace() != user2.isInHawkfireMarketplace()) {
                    enumSetNoneOf.add(AccountStateField.USER_IS_IN_HAWKFIRE_MARKETPLACE);
                }
                if (user.isAccountLocked() != user2.isAccountLocked()) {
                    enumSetNoneOf.add(AccountStateField.USER_IS_ACCOUNT_LOCKED);
                }
                if (!equals(user.getBenefits(), user2.getBenefits())) {
                    enumSetNoneOf.add(AccountStateField.USER_BENEFITS);
                }
                if (user.isExplicitFilterEnabledOnDevice() != user2.isExplicitFilterEnabledOnDevice()) {
                    enumSetNoneOf.add(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE);
                }
                if (user.isAccountValid() != user2.isAccountValid()) {
                    enumSetNoneOf.add(AccountStateField.IS_ACCOUNT_VALID);
                }
                if (hasMigrationOccurred(user, user2)) {
                    enumSetNoneOf.add(AccountStateField.ACCOUNT_MIGRATED);
                }
                if (user.isTargetedAdsEnabled() != user2.isTargetedAdsEnabled()) {
                    enumSetNoneOf.add(AccountStateField.TARGETED_ADS_ENABLED);
                }
                if (user.isCCBAOptOut() != user2.isCCBAOptOut()) {
                    enumSetNoneOf.add(AccountStateField.CCBA_OPT_OUT);
                }
            }
        }
        return enumSetNoneOf;
    }

    private boolean hasMigrationOccurred(User user, User user2) {
        if (user2.getMusicTerritoryOfResidence() == null) {
            return false;
        }
        return (equals(user.getMusicTerritoryOfResidence(), user2.getMusicTerritoryOfResidence()) ^ true) && !(user.hasCloudPlayerAccount() && !user2.hasCloudPlayerAccount());
    }

    public Set<AccountStateField> getDifferences(Device device, Device device2) {
        EnumSet enumSetNoneOf = EnumSet.noneOf(AccountStateField.class);
        if (device != null || device2 != null) {
            if (device == null || device2 == null) {
                enumSetNoneOf.addAll(EnumSet.of(AccountStateField.DEVICE_AUTHORIZATION_STATE, AccountStateField.DEVICE_AVAILABLE_STATE, AccountStateField.DEVICE_CAPABILITIES));
            } else {
                if (!equals(device.getAuthorizationState(), device2.getAuthorizationState())) {
                    enumSetNoneOf.add(AccountStateField.DEVICE_AUTHORIZATION_STATE);
                }
                if (!equals(device.getAvailableState(), device2.getAvailableState())) {
                    enumSetNoneOf.add(AccountStateField.DEVICE_AVAILABLE_STATE);
                }
                if (!equals(device.getCapabilities(), device2.getCapabilities())) {
                    enumSetNoneOf.add(AccountStateField.DEVICE_CAPABILITIES);
                }
            }
        }
        return enumSetNoneOf;
    }

    public Set<AccountStateField> getDifferences(DeviceAuthorizations deviceAuthorizations, DeviceAuthorizations deviceAuthorizations2) {
        EnumSet enumSetNoneOf = EnumSet.noneOf(AccountStateField.class);
        if (deviceAuthorizations != null || deviceAuthorizations2 != null) {
            if (deviceAuthorizations == null || deviceAuthorizations2 == null) {
                enumSetNoneOf.addAll(EnumSet.of(AccountStateField.DEVICE_AUTHORIZATIONS_REMAINING_FOR_LIBRARY, AccountStateField.DEVICE_AUTHORIZATIONS_REMAINING_FOR_CATALOG));
            } else {
                if (deviceAuthorizations.getRemainingLibraryDeviceAuthorizations() != deviceAuthorizations2.getRemainingLibraryDeviceAuthorizations()) {
                    enumSetNoneOf.add(AccountStateField.DEVICE_AUTHORIZATIONS_REMAINING_FOR_LIBRARY);
                }
                if (deviceAuthorizations.getRemainingCatalogDeviceAuthorizations() != deviceAuthorizations2.getRemainingCatalogDeviceAuthorizations()) {
                    enumSetNoneOf.add(AccountStateField.DEVICE_AUTHORIZATIONS_REMAINING_FOR_CATALOG);
                }
            }
        }
        return enumSetNoneOf;
    }

    public Set<AccountStateField> getDifferences(DeviceCapabilities deviceCapabilities, DeviceCapabilities deviceCapabilities2) {
        EnumSet enumSetNoneOf = EnumSet.noneOf(AccountStateField.class);
        if (deviceCapabilities != null || deviceCapabilities2 != null) {
            if (deviceCapabilities == null || deviceCapabilities2 == null) {
                enumSetNoneOf.addAll(EnumSet.of(AccountStateField.MAX_CATALOG_DEVICE_AUTHORIZATIONS, AccountStateField.NEXT_DEVICE_LOSING_AUTHORIZATION_NAME, AccountStateField.NEXT_DEVICE_LOSING_AUTHORIZATION_ID));
            } else {
                if (deviceCapabilities.getMaxCatalogDeviceAuthorizations() != deviceCapabilities.getMaxCatalogDeviceAuthorizations()) {
                    enumSetNoneOf.add(AccountStateField.MAX_CATALOG_DEVICE_AUTHORIZATIONS);
                }
                if (!equals(deviceCapabilities.getDeviceLosingCatalogDeviceAuthorizationNextName(), deviceCapabilities.getDeviceLosingCatalogDeviceAuthorizationNextName())) {
                    enumSetNoneOf.add(AccountStateField.NEXT_DEVICE_LOSING_AUTHORIZATION_NAME);
                }
                if (!equals(deviceCapabilities.getDeviceLosingCatalogDeviceAuthorizationNextId(), deviceCapabilities.getDeviceLosingCatalogDeviceAuthorizationNextId())) {
                    enumSetNoneOf.add(AccountStateField.NEXT_DEVICE_LOSING_AUTHORIZATION_ID);
                }
            }
        }
        return enumSetNoneOf;
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
