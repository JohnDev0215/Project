package com.amazon.podcast.redhoodPlus;

import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.User;
import com.amazon.podcast.Podcast;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class CustomerTier {
    private static final Logger logger = LoggerFactory.getLogger("CustomerTier");

    public static boolean isFreeTierCustomer() {
        return getUserBenefits().contains("NIGHTWING");
    }

    public static boolean isPrimeCustomer() {
        Set<String> userBenefits = getUserBenefits();
        return userBenefits.contains("PRIME_MUSIC_CONTENT_ACCESS") || userBenefits.contains("SONIC_RUSH");
    }

    public static boolean isUnlimitedCustomer() {
        Set<String> userBenefits = getUserBenefits();
        return userBenefits.contains("HAWKFIRE_PLAYBACK_ACCESS") || userBenefits.contains("HAWKFIRE_KATANA_ACCESS");
    }

    private static Set<String> getUserBenefits() {
        if (CollectionUtils.isEmpty(Podcast.getUserInfoProvider().benefits())) {
            try {
                Set<User.Benefit> benefits = AccountManagerSingleton.get().getUser().getBenefits();
                HashSet hashSet = new HashSet();
                Iterator<User.Benefit> it = benefits.iterator();
                while (it.hasNext()) {
                    hashSet.add(it.next().name());
                }
                return hashSet;
            } catch (Exception e) {
                logger.error("Failed to retrieve MusicTerritory. ", (Throwable) e);
                return Collections.EMPTY_SET;
            }
        }
        return Podcast.getUserInfoProvider().benefits();
    }
}
