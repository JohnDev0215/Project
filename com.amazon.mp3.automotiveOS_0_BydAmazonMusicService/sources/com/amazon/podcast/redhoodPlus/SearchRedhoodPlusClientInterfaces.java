package com.amazon.podcast.redhoodPlus;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.AccentOutlineBadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.BadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.LightUnborderedBadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.UnborderedBadgeElement;
import UpsellInterface.v1_0.ContentTrait;
import UpsellInterface.v1_0.PlaybackMode;
import UpsellInterface.v1_0.UpsellType;
import android.content.res.Resources;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$string;
import com.amazon.podcast.views.Strings;
import com.amazonaws.util.DateUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class SearchRedhoodPlusClientInterfaces {
    private static final Logger logger = LoggerFactory.getLogger("SearchRedhoodPlusClientInterfaces");
    private static final Map<String, List<String>> territoryToTierBenefitsBlockList;

    static {
        HashMap map = new HashMap();
        map.put("JP", Arrays.asList("FULLY_AD_FREE", ContentTrait.PARTIALLY_AD_FREE, ContentTrait.AD_FREE));
        territoryToTierBenefitsBlockList = Collections.unmodifiableMap(map);
    }

    public static String getPlaybackMode(final List<String> searchEntityContentTiers) {
        if (searchEntityContentTiers != null && !searchEntityContentTiers.isEmpty()) {
            if (CustomerTier.isUnlimitedCustomer() && searchEntityContentTiers.contains("MUSIC_SUBSCRIPTION")) {
                return PlaybackMode.FULL_ACCESS;
            }
            if (CustomerTier.isPrimeCustomer() && searchEntityContentTiers.contains(UpsellType.PRIME)) {
                return PlaybackMode.FULL_ACCESS;
            }
            if (CustomerTier.isFreeTierCustomer() && searchEntityContentTiers.contains(UpsellType.FREE)) {
                return PlaybackMode.FULL_ACCESS;
            }
        }
        return PlaybackMode.NONE;
    }

    public static List<String> getTierBenefits(final String musicTerritory, final Map<String, List<String>> tierBenefits) {
        ArrayList arrayList = new ArrayList();
        if (CustomerTier.isUnlimitedCustomer()) {
            if (!tierBenefits.containsKey(UpsellType.UNLIMITED)) {
                return Collections.emptyList();
            }
            arrayList.addAll(tierBenefits.get(UpsellType.UNLIMITED));
        }
        if (CustomerTier.isPrimeCustomer()) {
            if (!tierBenefits.containsKey(UpsellType.PRIME)) {
                return Collections.emptyList();
            }
            arrayList.addAll(tierBenefits.get(UpsellType.PRIME));
        }
        if (CustomerTier.isFreeTierCustomer()) {
            if (!tierBenefits.containsKey("NIGHTWING")) {
                return Collections.emptyList();
            }
            arrayList.addAll(tierBenefits.get("NIGHTWING"));
        }
        return resolvedTierBenefits(musicTerritory, arrayList);
    }

    public static String getAvailabilityDate(final Map<String, Date> availabilityDateMap) {
        Date availabilityDateFromMap = getAvailabilityDateFromMap(availabilityDateMap);
        if (availabilityDateFromMap == null) {
            return null;
        }
        return new SimpleDateFormat(DateUtils.ALTERNATE_ISO8601_DATE_PATTERN).format(availabilityDateFromMap);
    }

    private static Date getAvailabilityDateFromMap(final Map<String, Date> availabilityDate) {
        if (availabilityDate != null && !availabilityDate.isEmpty()) {
            if (CustomerTier.isUnlimitedCustomer()) {
                if (availabilityDate.containsKey(UpsellType.UNLIMITED)) {
                    return availabilityDate.get(UpsellType.UNLIMITED);
                }
                return null;
            }
            if (CustomerTier.isPrimeCustomer()) {
                if (availabilityDate.containsKey(UpsellType.PRIME)) {
                    return availabilityDate.get(UpsellType.PRIME);
                }
                return null;
            }
            if (CustomerTier.isFreeTierCustomer() && availabilityDate.containsKey("NIGHTWING")) {
                return availabilityDate.get("NIGHTWING");
            }
            return null;
        }
        return null;
    }

    public static BadgeElement horizontalRowItemPodcastShowBadge(final Resources resources, final String playbackMode, final List<String> tierBenefits) {
        if (tierBenefits == null || tierBenefits.isEmpty() || PlaybackMode.NONE.equals(playbackMode)) {
            return null;
        }
        boolean z = CustomerTier.isPrimeCustomer() && !CustomerTier.isUnlimitedCustomer();
        boolean zIsAccentOutlineUpsellBadgeEnabled = Podcast.getFeatureGatePreferences().isAccentOutlineUpsellBadgeEnabled();
        if (tierBenefits.contains(ContentTrait.EXCLUSIVE)) {
            if (z && zIsAccentOutlineUpsellBadgeEnabled) {
                return AccentOutlineBadgeElement.builder().withText(resources.getString(R$string.dmp_exclusive_badge_text)).build();
            }
            return LightUnborderedBadgeElement.builder().withText(resources.getString(R$string.dmp_exclusive_badge_text)).build();
        }
        if (tierBenefits.contains("FULLY_AD_FREE") || tierBenefits.contains(ContentTrait.PARTIALLY_AD_FREE)) {
            return UnborderedBadgeElement.builder().withText(resources.getString(R$string.dmp_ad_free_badge_text)).build();
        }
        return null;
    }

    public static String horizontalRowItemUpsellText(final Resources resources, final String playbackMode) {
        if (PlaybackMode.NONE.equals(playbackMode)) {
            return resources.getString(R$string.dmp_available_with_amu_text);
        }
        return null;
    }

    public static BadgeElement horizontalRowItemEpisodeBadge(final Resources resources, final String playbackMode, final String availabilityDate, final List<String> tierBenefits) {
        if (tierBenefits != null && !tierBenefits.isEmpty()) {
            boolean z = CustomerTier.isPrimeCustomer() && !CustomerTier.isUnlimitedCustomer();
            boolean zIsAccentOutlineUpsellBadgeEnabled = Podcast.getFeatureGatePreferences().isAccentOutlineUpsellBadgeEnabled();
            if (PlaybackMode.NONE.equals(playbackMode)) {
                return null;
            }
            if (tierBenefits.contains(ContentTrait.EXCLUSIVE)) {
                if (!StringUtils.isNotBlank(availabilityDate) || hasAvailabilityDatePassed(availabilityDate)) {
                    if (z && zIsAccentOutlineUpsellBadgeEnabled) {
                        return AccentOutlineBadgeElement.builder().withText(resources.getString(R$string.dmp_exclusive_badge_text)).build();
                    }
                    return LightUnborderedBadgeElement.builder().withText(resources.getString(R$string.dmp_exclusive_badge_text)).build();
                }
                if (z && zIsAccentOutlineUpsellBadgeEnabled) {
                    return AccentOutlineBadgeElement.builder().withText(resources.getString(R$string.dmp_pre_release_badge_text)).build();
                }
                return LightUnborderedBadgeElement.builder().withText(resources.getString(R$string.dmp_pre_release_badge_text)).build();
            }
            if (tierBenefits.contains(ContentTrait.AD_FREE)) {
                return UnborderedBadgeElement.builder().withText(resources.getString(R$string.dmp_ad_free_badge_text)).build();
            }
        }
        return null;
    }

    public static final String horizontalRowItemEpisodeSubtitle(final Resources resources, final String showName, final String availabilityDate, final String playbackMode, final String publishDate) {
        if (PlaybackMode.FULL_ACCESS.equals(playbackMode)) {
            return Strings.getReadablePublishDate(publishDate, resources) + " • " + showName;
        }
        if (StringUtils.isBlank(availabilityDate)) {
            return Strings.getReadablePublishDate(publishDate, resources) + " • " + showName;
        }
        try {
            return String.format(resources.getString(R$string.dmp_redhoodplus_available_on_date), Strings.convertDateToPattern(availabilityDate)) + " • " + showName;
        } catch (Exception e) {
            logger.error("Error in formatting availability date ", (Throwable) e);
            return showName;
        }
    }

    private static List<String> resolvedTierBenefits(final String musicTerritory, final List<String> tierBenefits) {
        List<String> list;
        if (tierBenefits != null && !tierBenefits.isEmpty()) {
            Map<String, List<String>> map = territoryToTierBenefitsBlockList;
            if (!map.containsKey(musicTerritory)) {
                list = Collections.EMPTY_LIST;
            } else {
                list = map.get(musicTerritory);
            }
            if (list != null && !list.isEmpty()) {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    tierBenefits.remove(it.next());
                }
            }
        }
        return tierBenefits;
    }

    private static boolean hasAvailabilityDatePassed(final String availabilityDateString) {
        try {
            return new SimpleDateFormat(DateUtils.ALTERNATE_ISO8601_DATE_PATTERN).parse(availabilityDateString).before(new Date());
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return true;
        }
    }
}
