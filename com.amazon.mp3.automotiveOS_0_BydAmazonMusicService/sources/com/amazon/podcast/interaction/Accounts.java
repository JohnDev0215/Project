package com.amazon.podcast.interaction;

import SOACoreInterface.v1_0.AccountIdentity;
import com.amazon.podcast.bootstrap.UserInfoProvider;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
final class Accounts {

    private enum Tier {
        PRIME,
        UNLIMITED,
        UNLIMITED_HD,
        FREE,
        BUNDESLIGA
    }

    Accounts() {
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'PRIME_MUSIC_BROWSE' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    private static final class MusicBenefit {
        private static final /* synthetic */ MusicBenefit[] $VALUES;
        public static final MusicBenefit BUNDESLIGA_BROWSE;
        public static final MusicBenefit BUNDESLIGA_FREE_PLAYBACK;
        public static final MusicBenefit BUNDESLIGA_PRIME_PLAYBACK;
        public static final MusicBenefit BUNDESLIGA_UNLIMITED_PLAYBACK;
        public static final MusicBenefit HAWKFIRE_BROWSE_AND_ADD;
        public static final MusicBenefit HAWKFIRE_KATANA_ACCESS;
        public static final MusicBenefit HAWKFIRE_PLAYBACK_ACCESS;
        public static final MusicBenefit NIGHTWING;
        public static final MusicBenefit PRIME_MUSIC_BROWSE;
        public static final MusicBenefit PRIME_MUSIC_CONTENT_ACCESS;
        public static final MusicBenefit SONIC_RUSH;
        private static final Set<MusicBenefit> all;
        private final Tier type;

        public static MusicBenefit valueOf(String name) {
            return (MusicBenefit) Enum.valueOf(MusicBenefit.class, name);
        }

        public static MusicBenefit[] values() {
            return (MusicBenefit[]) $VALUES.clone();
        }

        static {
            Tier tier = Tier.PRIME;
            MusicBenefit musicBenefit = new MusicBenefit("PRIME_MUSIC_BROWSE", 0, tier);
            PRIME_MUSIC_BROWSE = musicBenefit;
            MusicBenefit musicBenefit2 = new MusicBenefit("PRIME_MUSIC_CONTENT_ACCESS", 1, tier);
            PRIME_MUSIC_CONTENT_ACCESS = musicBenefit2;
            MusicBenefit musicBenefit3 = new MusicBenefit("SONIC_RUSH", 2, tier);
            SONIC_RUSH = musicBenefit3;
            Tier tier2 = Tier.UNLIMITED;
            MusicBenefit musicBenefit4 = new MusicBenefit("HAWKFIRE_BROWSE_AND_ADD", 3, tier2);
            HAWKFIRE_BROWSE_AND_ADD = musicBenefit4;
            MusicBenefit musicBenefit5 = new MusicBenefit("HAWKFIRE_PLAYBACK_ACCESS", 4, tier2);
            HAWKFIRE_PLAYBACK_ACCESS = musicBenefit5;
            MusicBenefit musicBenefit6 = new MusicBenefit("HAWKFIRE_KATANA_ACCESS", 5, Tier.UNLIMITED_HD);
            HAWKFIRE_KATANA_ACCESS = musicBenefit6;
            Tier tier3 = Tier.BUNDESLIGA;
            MusicBenefit musicBenefit7 = new MusicBenefit("BUNDESLIGA_BROWSE", 6, tier3);
            BUNDESLIGA_BROWSE = musicBenefit7;
            MusicBenefit musicBenefit8 = new MusicBenefit("BUNDESLIGA_FREE_PLAYBACK", 7, tier3);
            BUNDESLIGA_FREE_PLAYBACK = musicBenefit8;
            MusicBenefit musicBenefit9 = new MusicBenefit("BUNDESLIGA_PRIME_PLAYBACK", 8, tier3);
            BUNDESLIGA_PRIME_PLAYBACK = musicBenefit9;
            MusicBenefit musicBenefit10 = new MusicBenefit("BUNDESLIGA_UNLIMITED_PLAYBACK", 9, tier3);
            BUNDESLIGA_UNLIMITED_PLAYBACK = musicBenefit10;
            MusicBenefit musicBenefit11 = new MusicBenefit("NIGHTWING", 10, Tier.FREE);
            NIGHTWING = musicBenefit11;
            $VALUES = new MusicBenefit[]{musicBenefit, musicBenefit2, musicBenefit3, musicBenefit4, musicBenefit5, musicBenefit6, musicBenefit7, musicBenefit8, musicBenefit9, musicBenefit10, musicBenefit11};
            all = new HashSet(Arrays.asList(values()));
        }

        private MusicBenefit(String $enum$name, int $enum$ordinal, Tier type) {
            this.type = type;
        }
    }

    public static final AccountIdentity getAccountIdentity(final UserInfoProvider userInfoProvider) {
        HashMap map = new HashMap();
        Set<String> setBenefits = userInfoProvider.benefits();
        for (MusicBenefit musicBenefit : MusicBenefit.all) {
            if (isTierLaunched(userInfoProvider, musicBenefit.type)) {
                if (setBenefits.contains(musicBenefit.name())) {
                    map.put(musicBenefit, Boolean.TRUE.toString());
                } else {
                    map.put(musicBenefit, Boolean.FALSE.toString());
                }
            }
        }
        HashMap map2 = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            map2.put(((MusicBenefit) entry.getKey()).name(), (String) entry.getValue());
        }
        return AccountIdentity.builder().withMusicTerritory(userInfoProvider.musicTerritory()).withMarketplaceId(userInfoProvider.marketplaceId()).withBenefits(map2).withExplicitLanguageFilterEnabled(Boolean.valueOf(userInfoProvider.isExplicitLanguageFilterEnabled())).build();
    }

    private static final boolean isTierLaunched(final UserInfoProvider userInfoProvider, final Tier tier) {
        if (Tier.UNLIMITED_HD.equals(tier)) {
            return userInfoProvider.isUnlimitedHDTierLaunched();
        }
        if (Tier.UNLIMITED.equals(tier)) {
            return userInfoProvider.isUnlimitedTierLaunched();
        }
        if (Tier.PRIME.equals(tier)) {
            return userInfoProvider.isPrimeTierLaunched();
        }
        if (Tier.FREE.equals(tier)) {
            return userInfoProvider.isFreeTierLaunched();
        }
        if (Tier.BUNDESLIGA.equals(tier)) {
            return userInfoProvider.isBundlesligaTierLaunched();
        }
        return false;
    }
}
