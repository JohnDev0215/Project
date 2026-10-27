package com.amazon.mp3.account.details;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.ContentAccessType;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.account.User;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.marketplace.MarketplaceResolver;
import com.amazon.music.subscription.UserSubscription;
import com.google.common.collect.ImmutableSet;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class AccountDetailUtil {
    private static final String TAG = "AccountDetailUtil";
    private static AccountDetailUtil sInstance;
    private final Context mContext;
    private final AccountDetailStorage mDetails;
    private final BehaviorSubject<String> mMarketPlaceIdSubject;
    private static final long SEVEN_DAYS_MILLISECONDS = TimeUnit.DAYS.toMillis(7);
    private static final List<Marketplace> ROW_MARKETPLACE = Arrays.asList(Marketplace.ROW_NA, Marketplace.ROE_EU, Marketplace.ROW_FE);

    public static synchronized void createInstance(final Context context) {
        if (sInstance == null && context != null) {
            sInstance = new AccountDetailUtil(context);
        }
    }

    public static AccountDetailUtil get(final Context context) {
        if (sInstance == null) {
            createInstance(context);
        }
        return sInstance;
    }

    @Deprecated
    public static AccountDetailUtil get() {
        if (sInstance == null) {
            createInstance(AmazonApplication.getContext());
        }
        return sInstance;
    }

    private AccountDetailUtil(final Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mDetails = AccountDetailStorage.get(applicationContext);
        this.mMarketPlaceIdSubject = BehaviorSubject.create();
    }

    public boolean canViewAndPlayCloudContent() {
        return this.mDetails.hasAcceptedTermsOfUse() && this.mDetails.getAccountStatus() != User.CloudAccountStatus.LOCKED && DeviceAuthorizationManager.getInstance().isAuthorized() && AccountCredentialUtil.get(this.mContext).isSignedIn();
    }

    public boolean canSyncRemoteContent() {
        try {
            User user = AccountManagerSingleton.get().getUser();
            User.CloudAccountStatus cloudAccountStatus = user.getCloudAccountStatus();
            if (!user.isAccountValid() || cloudAccountStatus == User.CloudAccountStatus.INACTIVE) {
                return false;
            }
            return cloudAccountStatus != User.CloudAccountStatus.UNKNOWN;
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            return false;
        }
    }

    private String getHomeMarketPlaceIdNotDefaulted() throws MusicAccountNotCreatedException, DataNotReadyException {
        return AccountManagerSingleton.get().getUser().getLibraryHomeMarketplace().getObfuscatedId();
    }

    public String getCustomerId() {
        try {
            return AccountManagerSingleton.get().getUser().getCustomerId();
        } catch (Exception unused) {
            Log.error(TAG, "Can't get user");
            return null;
        }
    }

    public String getHomeMarketPlaceId() {
        String obfuscatedId;
        try {
            obfuscatedId = getHomeMarketPlaceIdNotDefaulted();
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            Marketplace marketplace = AccountManagerSingleton.get().getMarketplace();
            if (marketplace != null) {
                obfuscatedId = marketplace.getObfuscatedId();
            } else {
                obfuscatedId = Marketplace.USA.getObfuscatedId();
            }
        }
        this.mMarketPlaceIdSubject.onNext(obfuscatedId);
        return obfuscatedId;
    }

    public static String getMusicTerritoryOfResidence() {
        try {
            String musicTerritoryOfResidence = AccountManagerSingleton.get().getUser().getMusicTerritoryOfResidence();
            if (TextUtils.isEmpty(musicTerritoryOfResidence)) {
                throw new DataNotReadyException();
            }
            return musicTerritoryOfResidence;
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            return AccountManagerSingleton.get().getMusicTerritory();
        }
    }

    public boolean isRowMarketplace() {
        return ROW_MARKETPLACE.contains(getHomeMarketPlace());
    }

    public boolean isRowPrimeOnlyMarketplace() {
        return isUserInPrimeOnlyMarketplace() && isRowMarketplace();
    }

    public Marketplace getHomeMarketPlace() {
        return MarketplaceResolver.lookupByObfuscatedId(getHomeMarketPlaceId());
    }

    public Marketplace getHomeMarketPlaceNotDefaulted() throws MusicAccountNotCreatedException, DataNotReadyException {
        return MarketplaceResolver.lookupByObfuscatedId(getHomeMarketPlaceIdNotDefaulted());
    }

    public boolean isUserInEUMarketplace() {
        return ImmutableSet.of(Marketplace.UK, Marketplace.GERMANY, Marketplace.FRANCE, Marketplace.ITALY, Marketplace.SPAIN, Marketplace.ROE_EU, new Marketplace[0]).contains(getHomeMarketPlace());
    }

    public boolean isPrimeMusicSupported() {
        return this.mDetails.isPrimeMusicMarket() || this.mDetails.isPrimeMusicAccessible();
    }

    public boolean isUserInPrimeOnlyMarketplace() {
        return ContentAccessUtil.canBrowseContent(ContentAccessType.PRIME) && !isUserInHawkfireMarketplace();
    }

    public static boolean isROWMarketplaceWithMusicTerritorySpecificMusicDomain(final String musicTerritory) {
        return MusicTerritory.CANADA.equalsIgnoreCase(musicTerritory) || MusicTerritory.INDIA.equalsIgnoreCase(musicTerritory) || MusicTerritory.AUSTRALIA.equalsIgnoreCase(musicTerritory) || MusicTerritory.MEXICO.equalsIgnoreCase(musicTerritory) || MusicTerritory.NEW_ZEALAND.equalsIgnoreCase(musicTerritory) || MusicTerritory.BRAZIL.equalsIgnoreCase(musicTerritory);
    }

    public boolean isUserInHawkfireMarketplace() {
        return ContentAccessUtil.canBrowseContent(ContentAccessType.HAWKFIRE);
    }

    public boolean isUserInSonicRushMusicTerritory() {
        return ContentAccessUtil.canBrowseContent(ContentAccessType.SONIC_RUSH);
    }

    public boolean shouldAvoidFreeWordingForPrime() {
        String string = AmazonApplication.getConfiguration(this.mContext).getString("avoid_free_wording_markets", Marketplace.USA.getObfuscatedId());
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return StringUtil.csvContains(string, get().getHomeMarketPlaceId());
    }

    public void logDebugInfo() {
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Log.info(TAG, "Market: " + getHomeMarketPlaceId() + ", canViewAndPlayCloudContent: " + canViewAndPlayCloudContent() + ", isPrimeMusicSupported: " + isPrimeMusicSupported() + ", isPrimeMusicAccessible: " + this.mDetails.isPrimeMusicAccessible() + ", isUnlimitedAllDevices: " + userSubscription.isHawkfireAllDevices() + ", isUnlimitedHomeOnly: " + userSubscription.isHawkfireHome());
    }

    public Observable<String> getMarketPlaceIdObservable() {
        return this.mMarketPlaceIdSubject.hide();
    }
}
