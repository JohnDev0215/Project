package com.amazon.mp3.util;

import com.amazon.mp3.account.SubscriptionType;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.account.User;
import com.amazon.music.platform.data.subscription.FreeSubscription;
import com.amazon.music.platform.data.subscription.MusicHDSubscription;
import com.amazon.music.platform.data.subscription.MusicHomeSubscription;
import com.amazon.music.platform.data.subscription.MusicUnlimitedSubscription;
import com.amazon.music.platform.data.subscription.NoSubscription;
import com.amazon.music.platform.data.subscription.PrimeSubscription;
import com.amazon.music.platform.data.subscription.SonicRushSubscription;
import com.amazon.music.platform.providers.Subscription;
import com.amazon.music.subscription.UserSubscription;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class UserSubscriptionUtil {
    private static final String TAG = "UserSubscriptionUtil";

    public static UserSubscription getUserSubscription() {
        return new UserSubscription(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider());
    }

    public static boolean isNightwingOnly() {
        try {
            Set<User.Benefit> benefits = AccountManagerSingleton.get().getUser().getBenefits();
            benefits.contains(User.Benefit.NIGHTWING);
            benefits.contains(User.Benefit.PRIME_MUSIC_BROWSE);
            if (!benefits.contains(User.Benefit.HAWKFIRE_BROWSE_AND_ADD)) {
                benefits.contains(User.Benefit.HAWKFIRE_PLAYBACK_ACCESS);
            }
            return getUserSubscription().isNightwingOnly();
        } catch (DataNotReadyException | MusicAccountNotCreatedException e) {
            Log.debug(TAG, "isNightwingOnly failed", e);
            return false;
        }
    }

    public static boolean isHawkfire() {
        try {
            Set<User.Benefit> benefits = AccountManagerSingleton.get().getUser().getBenefits();
            return benefits.contains(User.Benefit.HAWKFIRE_BROWSE_AND_ADD) || benefits.contains(User.Benefit.HAWKFIRE_PLAYBACK_ACCESS);
        } catch (DataNotReadyException | MusicAccountNotCreatedException e) {
            Log.debug(TAG, "isHawkfire failed", e);
            return false;
        }
    }

    public static boolean isPrime() {
        try {
            return AccountManagerSingleton.get().getUser().getBenefits().contains(User.Benefit.PRIME_MUSIC_CONTENT_ACCESS);
        } catch (DataNotReadyException | MusicAccountNotCreatedException e) {
            Log.debug(TAG, "isPrime failed", e);
            return false;
        }
    }

    public static boolean isHawkfireKatana() {
        try {
            return AccountManagerSingleton.get().getUser().getBenefits().contains(User.Benefit.HAWKFIRE_KATANA_ACCESS);
        } catch (DataNotReadyException | MusicAccountNotCreatedException e) {
            Log.debug(TAG, "isHawkfireKatana failed", e);
            return false;
        }
    }

    public static ArrayList<SubscriptionType> getSubscriptions() {
        ArrayList<SubscriptionType> arrayList = new ArrayList<>();
        UserSubscription userSubscription = getUserSubscription();
        if (userSubscription.isNightwing()) {
            arrayList.add(SubscriptionType.free);
        }
        if (userSubscription.isHawkfireAllDevices()) {
            arrayList.add(SubscriptionType.music);
        }
        if (userSubscription.isKatana()) {
            arrayList.add(SubscriptionType.musicHD);
        }
        if (userSubscription.isHawkfireHome()) {
            arrayList.add(SubscriptionType.musicHome);
        }
        if (userSubscription.isSonicRush()) {
            arrayList.add(SubscriptionType.sonicRush);
        } else if (userSubscription.isPrime()) {
            arrayList.add(SubscriptionType.prime);
        }
        if (arrayList.isEmpty()) {
            arrayList.add(SubscriptionType.none);
        }
        return arrayList;
    }

    public static Subscription getSubscription() {
        UserSubscription userSubscription = getUserSubscription();
        if (userSubscription.isKatana()) {
            return new MusicHDSubscription();
        }
        if (userSubscription.isHawkfireAllDevices()) {
            return new MusicUnlimitedSubscription();
        }
        if (userSubscription.isSonicRush()) {
            return new SonicRushSubscription();
        }
        if (userSubscription.isPrime()) {
            return new PrimeSubscription();
        }
        if (isNightwingOnly()) {
            return new FreeSubscription();
        }
        if (userSubscription.isHomeOnly()) {
            return new MusicHomeSubscription();
        }
        return new NoSubscription();
    }
}
