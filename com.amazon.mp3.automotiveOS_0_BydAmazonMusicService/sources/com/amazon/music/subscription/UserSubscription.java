package com.amazon.music.subscription;

import com.amazon.music.account.AccountManager;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.account.User;
import com.amazon.music.featureflag.FeatureFlagProvider;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class UserSubscription {
    private static final Logger LOG = LoggerFactory.getLogger(UserSubscription.class.getSimpleName());
    private final AccountManager accountManager;
    private final FeatureFlagProviderWrapper featureFlagProviderWrapper;

    public UserSubscription(AccountManager accountManager, FeatureFlagProvider featureFlagProvider) {
        this.accountManager = (AccountManager) Validate.notNull(accountManager, "AccountManager cannot be null", new Object[0]);
        Validate.notNull(featureFlagProvider, "FeatureFlag cannot be null", new Object[0]);
        this.featureFlagProviderWrapper = new FeatureFlagProviderWrapper(featureFlagProvider);
    }

    public boolean isNightwingOnly() {
        try {
            Set<User.Benefit> benefits = this.accountManager.getUser().getBenefits();
            boolean z = benefits.contains(User.Benefit.NIGHTWING) && this.featureFlagProviderWrapper.canAccessNightwing();
            boolean z2 = this.featureFlagProviderWrapper.canAccessPrime() && benefits.contains(User.Benefit.PRIME_MUSIC_BROWSE);
            boolean z3 = this.featureFlagProviderWrapper.canAccessUnlimited() && (benefits.contains(User.Benefit.HAWKFIRE_BROWSE_AND_ADD) || benefits.contains(User.Benefit.HAWKFIRE_PLAYBACK_ACCESS));
            if (!z || z2 || z3) {
                if (!z || !isHomeOnly()) {
                    return false;
                }
                if (!this.featureFlagProviderWrapper.canSDPAccessFreeTier()) {
                    return false;
                }
            }
            return true;
        } catch (DataNotReadyException | MusicAccountNotCreatedException e) {
            LOG.error("Error fetching user from AccountManager: " + e);
            return false;
        }
    }

    public boolean isNightwing() {
        try {
            if (this.accountManager.getUser().getBenefits().contains(User.Benefit.NIGHTWING)) {
                return this.featureFlagProviderWrapper.canAccessNightwing();
            }
            return false;
        } catch (DataNotReadyException | MusicAccountNotCreatedException e) {
            LOG.error("Error fetching user from AccountManager: " + e);
            return false;
        }
    }

    public boolean isNightwingNonHome() {
        return (!isNightwing() || isPrime() || isHawkfireHome() || isHawkfireAllDevices()) ? false : true;
    }

    public boolean isSonicRush() {
        try {
            if (this.featureFlagProviderWrapper.canAccessSonicRush()) {
                return this.accountManager.getUser().getBenefits().contains(User.Benefit.SONIC_RUSH);
            }
            return false;
        } catch (DataNotReadyException | MusicAccountNotCreatedException e) {
            LOG.error("Error fetching user from AccountManager: " + e);
            return false;
        }
    }

    public boolean isPrimeOnly() {
        try {
            boolean z = this.featureFlagProviderWrapper.canAccessUnlimited() && this.accountManager.getUser().getBenefits().contains(User.Benefit.HAWKFIRE_BROWSE_AND_ADD);
            if (isSonicRush()) {
                return false;
            }
            return isPrime() && !z;
        } catch (DataNotReadyException | MusicAccountNotCreatedException e) {
            LOG.error("Error fetching user from AccountManager: " + e);
            return false;
        }
    }

    public boolean isPrime() {
        try {
            if (this.featureFlagProviderWrapper.canAccessPrime()) {
                return this.accountManager.getUser().getBenefits().contains(User.Benefit.PRIME_MUSIC_BROWSE);
            }
            return false;
        } catch (DataNotReadyException | MusicAccountNotCreatedException e) {
            LOG.error("Error fetching user from AccountManager: " + e);
            return false;
        }
    }

    public boolean isHawkfireHome() {
        try {
            Set<User.Benefit> benefits = this.accountManager.getUser().getBenefits();
            if (this.featureFlagProviderWrapper.canAccessUnlimited() && benefits.contains(User.Benefit.HAWKFIRE_BROWSE_AND_ADD)) {
                return !benefits.contains(User.Benefit.HAWKFIRE_PLAYBACK_ACCESS);
            }
            return false;
        } catch (DataNotReadyException | MusicAccountNotCreatedException e) {
            LOG.error("Error fetching user from AccountManager: " + e);
            return false;
        }
    }

    public boolean isHawkfireAllDevices() {
        try {
            if (this.featureFlagProviderWrapper.canAccessUnlimited()) {
                return this.accountManager.getUser().getBenefits().contains(User.Benefit.HAWKFIRE_PLAYBACK_ACCESS);
            }
            return false;
        } catch (DataNotReadyException | MusicAccountNotCreatedException e) {
            LOG.error("Error fetching user from AccountManager: " + e);
            return false;
        }
    }

    public boolean isKatana() {
        try {
            if (this.featureFlagProviderWrapper.canAccessKatana()) {
                return this.accountManager.getUser().getBenefits().contains(User.Benefit.HAWKFIRE_KATANA_ACCESS);
            }
            return false;
        } catch (DataNotReadyException | MusicAccountNotCreatedException e) {
            LOG.error("Error fetching user from AccountManager: " + e);
            return false;
        }
    }

    public boolean isIvy() {
        return isKatana() && this.featureFlagProviderWrapper.canAccessIvy();
    }

    public boolean hasSubscription() {
        return isHawkfireAllDevices() || isPrime();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isInSubscriptionMarketplace() {
        /*
            r6 = this;
            r0 = 0
            com.amazon.music.account.AccountManager r1 = r6.accountManager     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L43 com.amazon.music.account.DataNotReadyException -> L45
            com.amazon.music.account.User r1 = r1.getUser()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L43 com.amazon.music.account.DataNotReadyException -> L45
            com.amazon.music.subscription.FeatureFlagProviderWrapper r2 = r6.featureFlagProviderWrapper     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L43 com.amazon.music.account.DataNotReadyException -> L45
            boolean r2 = r2.canAccessNightwing()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L43 com.amazon.music.account.DataNotReadyException -> L45
            r3 = 1
            if (r2 == 0) goto L18
            boolean r2 = r1.isInNightwingMarketplace()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L43 com.amazon.music.account.DataNotReadyException -> L45
            if (r2 == 0) goto L18
            r2 = r3
            goto L19
        L18:
            r2 = r0
        L19:
            com.amazon.music.subscription.FeatureFlagProviderWrapper r4 = r6.featureFlagProviderWrapper     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L43 com.amazon.music.account.DataNotReadyException -> L45
            boolean r4 = r4.canAccessUnlimited()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L43 com.amazon.music.account.DataNotReadyException -> L45
            if (r4 == 0) goto L29
            boolean r4 = r1.isInHawkfireMarketplace()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L43 com.amazon.music.account.DataNotReadyException -> L45
            if (r4 == 0) goto L29
            r4 = r3
            goto L2a
        L29:
            r4 = r0
        L2a:
            com.amazon.music.subscription.FeatureFlagProviderWrapper r5 = r6.featureFlagProviderWrapper     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L43 com.amazon.music.account.DataNotReadyException -> L45
            boolean r5 = r5.canAccessPrime()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L43 com.amazon.music.account.DataNotReadyException -> L45
            if (r5 == 0) goto L3a
            boolean r1 = r1.isInPrimeMarketplace()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L43 com.amazon.music.account.DataNotReadyException -> L45
            if (r1 == 0) goto L3a
            r1 = r3
            goto L3b
        L3a:
            r1 = r0
        L3b:
            if (r2 != 0) goto L41
            if (r4 != 0) goto L41
            if (r1 == 0) goto L42
        L41:
            r0 = r3
        L42:
            return r0
        L43:
            r1 = move-exception
            goto L46
        L45:
            r1 = move-exception
        L46:
            org.slf4j.Logger r2 = com.amazon.music.subscription.UserSubscription.LOG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Error fetching user from AccountManager: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.error(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.subscription.UserSubscription.isInSubscriptionMarketplace():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isHomeOnly() {
        /*
            r5 = this;
            r0 = 0
            com.amazon.music.account.AccountManager r1 = r5.accountManager     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L40 com.amazon.music.account.DataNotReadyException -> L42
            com.amazon.music.account.User r1 = r1.getUser()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L40 com.amazon.music.account.DataNotReadyException -> L42
            java.util.Set r1 = r1.getBenefits()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L40 com.amazon.music.account.DataNotReadyException -> L42
            com.amazon.music.subscription.FeatureFlagProviderWrapper r2 = r5.featureFlagProviderWrapper     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L40 com.amazon.music.account.DataNotReadyException -> L42
            boolean r2 = r2.canAccessPrime()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L40 com.amazon.music.account.DataNotReadyException -> L42
            r3 = 1
            if (r2 == 0) goto L1e
            com.amazon.music.account.User$Benefit r2 = com.amazon.music.account.User.Benefit.PRIME_MUSIC_BROWSE     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L40 com.amazon.music.account.DataNotReadyException -> L42
            boolean r2 = r1.contains(r2)     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L40 com.amazon.music.account.DataNotReadyException -> L42
            if (r2 == 0) goto L1e
            r2 = r3
            goto L1f
        L1e:
            r2 = r0
        L1f:
            com.amazon.music.subscription.FeatureFlagProviderWrapper r4 = r5.featureFlagProviderWrapper     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L40 com.amazon.music.account.DataNotReadyException -> L42
            boolean r4 = r4.canAccessUnlimited()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L40 com.amazon.music.account.DataNotReadyException -> L42
            if (r4 == 0) goto L39
            com.amazon.music.account.User$Benefit r4 = com.amazon.music.account.User.Benefit.HAWKFIRE_BROWSE_AND_ADD     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L40 com.amazon.music.account.DataNotReadyException -> L42
            boolean r4 = r1.contains(r4)     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L40 com.amazon.music.account.DataNotReadyException -> L42
            if (r4 == 0) goto L39
            com.amazon.music.account.User$Benefit r4 = com.amazon.music.account.User.Benefit.HAWKFIRE_PLAYBACK_ACCESS     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L40 com.amazon.music.account.DataNotReadyException -> L42
            boolean r1 = r1.contains(r4)     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L40 com.amazon.music.account.DataNotReadyException -> L42
            if (r1 != 0) goto L39
            r1 = r3
            goto L3a
        L39:
            r1 = r0
        L3a:
            if (r2 != 0) goto L3f
            if (r1 == 0) goto L3f
            r0 = r3
        L3f:
            return r0
        L40:
            r1 = move-exception
            goto L43
        L42:
            r1 = move-exception
        L43:
            org.slf4j.Logger r2 = com.amazon.music.subscription.UserSubscription.LOG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Error fetching user from AccountManager: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.error(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.subscription.UserSubscription.isHomeOnly():boolean");
    }
}
