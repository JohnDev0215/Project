package com.amazon.music.subscription;

import com.amazon.music.ContentAccessType;
import com.amazon.music.account.AccountManager;
import com.amazon.music.account.User;
import com.amazon.music.featureflag.FeatureFlagProvider;
import java.util.Arrays;
import java.util.HashSet;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class ContentAccess {
    private static final Logger LOG = LoggerFactory.getLogger(ContentAccess.class.getSimpleName());
    private final AccountManager accountManager;
    private final FeatureFlagProviderWrapper featureFlagProviderWrapper;
    private final UserSubscription userSubscription;

    public ContentAccess(AccountManager accountManager, FeatureFlagProvider featureFlagProvider) {
        this.accountManager = (AccountManager) Validate.notNull(accountManager);
        Validate.notNull(featureFlagProvider);
        this.featureFlagProviderWrapper = new FeatureFlagProviderWrapper(featureFlagProvider);
        this.userSubscription = new UserSubscription(accountManager, featureFlagProvider);
    }

    public ContentAccessResult canDownloadContent(ContentAccessType... contentAccessTypeArr) {
        ContentAccessTypeBase contentAccessTypeBaseResolveTierForAccessType = resolveTierForAccessType(resolveAccessTypeForSet(contentAccessTypeArr));
        ContentAccessResult contentAccessResultIsInMarketplace = contentAccessTypeBaseResolveTierForAccessType.isInMarketplace();
        return !contentAccessResultIsInMarketplace.isAccessible() ? contentAccessResultIsInMarketplace : contentAccessTypeBaseResolveTierForAccessType.canDownloadContent();
    }

    public ContentAccessResult canPlayDownloadedContent(ContentAccessType... contentAccessTypeArr) {
        ContentAccessTypeBase contentAccessTypeBaseResolveTierForAccessType = resolveTierForAccessType(resolveAccessTypeForSet(contentAccessTypeArr));
        ContentAccessResult contentAccessResultIsInMarketplace = contentAccessTypeBaseResolveTierForAccessType.isInMarketplace();
        return !contentAccessResultIsInMarketplace.isAccessible() ? contentAccessResultIsInMarketplace : contentAccessTypeBaseResolveTierForAccessType.canPlayDownloadedContent();
    }

    public ContentAccessResult canStreamContent(ContentAccessType... contentAccessTypeArr) {
        ContentAccessTypeBase contentAccessTypeBaseResolveTierForAccessType = resolveTierForAccessType(resolveAccessTypeForSet(contentAccessTypeArr));
        ContentAccessResult contentAccessResultIsInMarketplace = contentAccessTypeBaseResolveTierForAccessType.isInMarketplace();
        return !contentAccessResultIsInMarketplace.isAccessible() ? contentAccessResultIsInMarketplace : contentAccessTypeBaseResolveTierForAccessType.canStreamContent();
    }

    public ContentAccessResult canBrowseContent(ContentAccessType... contentAccessTypeArr) {
        ContentAccessTypeBase contentAccessTypeBaseResolveTierForAccessType = resolveTierForAccessType(resolveAccessTypeForSet(contentAccessTypeArr));
        ContentAccessResult contentAccessResultIsInMarketplace = contentAccessTypeBaseResolveTierForAccessType.isInMarketplace();
        return !contentAccessResultIsInMarketplace.isAccessible() ? contentAccessResultIsInMarketplace : contentAccessTypeBaseResolveTierForAccessType.canBrowseContent();
    }

    public ContentAccessResult canInteractWithContent(ContentAccessType... contentAccessTypeArr) throws IllegalArgumentException {
        ContentAccessTypeBase contentAccessTypeBaseResolveTierForAccessType = resolveTierForAccessType(resolveAccessTypeForSet(contentAccessTypeArr));
        ContentAccessResult contentAccessResultIsInMarketplace = contentAccessTypeBaseResolveTierForAccessType.isInMarketplace();
        return !contentAccessResultIsInMarketplace.isAccessible() ? contentAccessResultIsInMarketplace : contentAccessTypeBaseResolveTierForAccessType.canInteractWithContent();
    }

    ContentAccessType resolveAccessTypeForSet(ContentAccessType... contentAccessTypeArr) throws IllegalArgumentException {
        Validate.notEmpty(contentAccessTypeArr, "ContentAccessTypes cannot be null or empty", new Object[0]);
        if (contentAccessTypeArr.length == 1) {
            ContentAccessType contentAccessType = contentAccessTypeArr[0];
            return isSubscriptionAccess(contentAccessType) ? getMarketplaceEligibleBenefit(contentAccessType) : contentAccessType;
        }
        HashSet hashSet = new HashSet(Arrays.asList(contentAccessTypeArr));
        ContentAccessType contentAccessType2 = ContentAccessType.OWNED;
        if (hashSet.contains(contentAccessType2)) {
            return contentAccessType2;
        }
        ContentAccessType contentAccessType3 = ContentAccessType.NIGHTWING;
        if (hashSet.contains(contentAccessType3)) {
            return getMarketplaceEligibleBenefit(contentAccessType3);
        }
        ContentAccessType contentAccessType4 = ContentAccessType.PRIME;
        if (hashSet.contains(contentAccessType4)) {
            return getMarketplaceEligibleBenefit(contentAccessType4);
        }
        ContentAccessType contentAccessType5 = ContentAccessType.SONIC_RUSH;
        if (hashSet.contains(contentAccessType5)) {
            return getMarketplaceEligibleBenefit(contentAccessType5);
        }
        ContentAccessType contentAccessType6 = ContentAccessType.HAWKFIRE;
        if (hashSet.contains(contentAccessType6)) {
            return getMarketplaceEligibleBenefit(contentAccessType6);
        }
        ContentAccessType contentAccessType7 = ContentAccessType.BOP;
        if (hashSet.contains(contentAccessType7)) {
            return contentAccessType7;
        }
        ContentAccessType contentAccessType8 = ContentAccessType.BUNDESLIGA_FREE;
        if (hashSet.contains(contentAccessType8)) {
            return contentAccessType8;
        }
        ContentAccessType contentAccessType9 = ContentAccessType.BUNDESLIGA_PRIME;
        if (hashSet.contains(contentAccessType9)) {
            return contentAccessType9;
        }
        ContentAccessType contentAccessType10 = ContentAccessType.BUNDESLIGA_HAWKFIRE;
        if (hashSet.contains(contentAccessType10)) {
            return contentAccessType10;
        }
        throw new IllegalArgumentException("Called resolveAccessTypeForSet for unsupported ContentAccessType " + hashSet);
    }

    private ContentAccessType getMarketplaceEligibleBenefit(ContentAccessType contentAccessType) {
        try {
            User user = this.accountManager.getUser();
            int i = AnonymousClass1.$SwitchMap$com$amazon$music$ContentAccessType[contentAccessType.ordinal()];
            if (i == 1) {
                if (!user.isInNightwingMarketplace() || !this.featureFlagProviderWrapper.canAccessNightwing()) {
                    if (user.isInSonicRushMusicTerritory() && this.featureFlagProviderWrapper.canAccessSonicRush()) {
                        return ContentAccessType.SONIC_RUSH;
                    }
                    if (user.isInPrimeMarketplace() && this.featureFlagProviderWrapper.canAccessPrime()) {
                        return ContentAccessType.PRIME;
                    }
                    if (isUnlimitedSupported(user, this.featureFlagProviderWrapper)) {
                        return ContentAccessType.HAWKFIRE;
                    }
                }
                return ContentAccessType.NIGHTWING;
            }
            if (i == 2) {
                if ((!user.isInSonicRushMusicTerritory() || !this.featureFlagProviderWrapper.canAccessSonicRush()) && isUnlimitedSupported(user, this.featureFlagProviderWrapper)) {
                    return ContentAccessType.HAWKFIRE;
                }
                return ContentAccessType.SONIC_RUSH;
            }
            if (i != 3) {
                return i != 4 ? contentAccessType : ContentAccessType.HAWKFIRE;
            }
            if ((!user.isInPrimeMarketplace() || !this.featureFlagProviderWrapper.canAccessPrime()) && isUnlimitedSupported(user, this.featureFlagProviderWrapper)) {
                return ContentAccessType.HAWKFIRE;
            }
            return ContentAccessType.PRIME;
        } catch (Exception e) {
            LOG.error("Exception occurred " + e.getMessage(), (Throwable) e);
            return contentAccessType;
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.subscription.ContentAccess$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$ContentAccessType;

        static {
            int[] iArr = new int[ContentAccessType.values().length];
            $SwitchMap$com$amazon$music$ContentAccessType = iArr;
            try {
                iArr[ContentAccessType.NIGHTWING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$ContentAccessType[ContentAccessType.SONIC_RUSH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$ContentAccessType[ContentAccessType.PRIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$ContentAccessType[ContentAccessType.HAWKFIRE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$ContentAccessType[ContentAccessType.OWNED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$ContentAccessType[ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$ContentAccessType[ContentAccessType.SONIC_RUSH_ONDEMAND_PLAYABLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$ContentAccessType[ContentAccessType.SONIC_RUSH_GOLDEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$music$ContentAccessType[ContentAccessType.BOP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$music$ContentAccessType[ContentAccessType.BUNDESLIGA_FREE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$music$ContentAccessType[ContentAccessType.BUNDESLIGA_PRIME.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$music$ContentAccessType[ContentAccessType.BUNDESLIGA_HAWKFIRE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    private boolean isUnlimitedSupported(User user, FeatureFlagProviderWrapper featureFlagProviderWrapper) {
        return user.isInHawkfireMarketplace() && this.featureFlagProviderWrapper.canAccessUnlimited();
    }

    ContentAccessTypeBase resolveTierForAccessType(ContentAccessType contentAccessType) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$music$ContentAccessType[contentAccessType.ordinal()]) {
            case 1:
            case 6:
                return new NightwingContentAccess(this.accountManager, this.featureFlagProviderWrapper, this.userSubscription);
            case 2:
            case 7:
            case 8:
                return new SonicRushContentAccess(this.accountManager, this.featureFlagProviderWrapper, this.userSubscription);
            case 3:
                return new PrimeContentAccess(this.accountManager, this.featureFlagProviderWrapper, this.userSubscription);
            case 4:
                return new HawkfireContentAccess(this.accountManager, this.featureFlagProviderWrapper, this.userSubscription);
            case 5:
                return new OwnedContentAccess(this.accountManager);
            case 9:
                return new BOPContentAccess(this.accountManager, this.featureFlagProviderWrapper, this.userSubscription);
            case 10:
                return new BundesligaFreeContentAccess(this.accountManager);
            case 11:
                return new BundesligaPrimeContentAccess(this.accountManager);
            case 12:
                return new BundesligaHawkfireContentAccess(this.accountManager);
            default:
                throw new IllegalArgumentException("Called isInMarketplace for unsupported ContentAccessType: " + contentAccessType);
        }
    }

    private boolean isSubscriptionAccess(ContentAccessType contentAccessType) {
        return contentAccessType.equals(ContentAccessType.HAWKFIRE) || contentAccessType.equals(ContentAccessType.SONIC_RUSH) || contentAccessType.equals(ContentAccessType.PRIME) || contentAccessType.equals(ContentAccessType.NIGHTWING);
    }
}
