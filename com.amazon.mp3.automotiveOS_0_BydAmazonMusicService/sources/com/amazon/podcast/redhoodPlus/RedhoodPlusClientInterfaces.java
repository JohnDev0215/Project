package com.amazon.podcast.redhoodPlus;

import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplate;
import Podcast.Touch.AlertTemplateInterface.v1_0.PositiveButtonElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.BorderedBadgeElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.ButtonElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.AccentOutlineBadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.BadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.LightUnborderedBadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.UnborderedBadgeElement;
import Podcast.UpsellInterface.v1_0.ShowUpsellMethod;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethod;
import SOATemplateListInterface.v1_0.TemplateLaunchMode;
import UpsellInterface.v1_0.ContentTrait;
import UpsellInterface.v1_0.UpsellTriggerType;
import UpsellInterface.v1_0.UpsellType;
import android.content.Context;
import android.content.res.Resources;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$string;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.views.Strings;
import com.amazonaws.util.DateUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class RedhoodPlusClientInterfaces {
    private static final Logger logger = LoggerFactory.getLogger("RedhoodPlusClientInterfaces");

    public static final List<Method> upsellMethods(final Context context, final String redirectDeeplinkUrl, final List<String> availableUpsells, final UiMetricAttributes$PageType pageType, final UiMetricAttributes$ContentName contentName, final String entityId, final UiMetricAttributes$EntityType entityType, final UiMetricAttributes$EntityIdType entityIdType) {
        if (!Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled()) {
            return Collections.emptyList();
        }
        if (!hasUpsells(availableUpsells)) {
            return Collections.emptyList();
        }
        return showUpsellMethods(redirectDeeplinkUrl, pageType, contentName, entityId, entityType, entityIdType);
    }

    public static final List<Method> upsellMethods(final Context context, final String redirectDeeplinkUrl, final List<String> availableUpsells, final String blockRef, final UiMetricAttributes$PageType pageType) {
        if (!Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled()) {
            return Collections.emptyList();
        }
        if (!hasUpsells(availableUpsells)) {
            return Collections.emptyList();
        }
        return showUpsellMethods(redirectDeeplinkUrl, blockRef, pageType);
    }

    public static final List<Method> upsellMethodsForDownloadsWithOverrideIfStaleUpsells(final Context context, final String redirectDeeplinkUrl, final List<String> availableUpsells, final Set<String> benefits, final UiMetricAttributes$PageType pageType, final UiMetricAttributes$ContentName contentName, final String entityId, final UiMetricAttributes$EntityType entityType, final UiMetricAttributes$EntityIdType entityIdType) {
        if (!Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled() || !hasUpsells(availableUpsells)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        if (!(CustomerTier.isUnlimitedCustomer() || CustomerTier.isPrimeCustomer())) {
            return showUpsellMethods(redirectDeeplinkUrl, pageType, contentName, entityId, entityType, entityIdType);
        }
        arrayList.add(CreateAndBindTemplateMethod.builder().withForced(Boolean.FALSE).withQueue(Queues.template()).withTemplate(AlertTemplate.builder().withTitle(context.getResources().getString(R$string.dmp_stale_episode_redownload_episode_alert_title)).withSubtitle(context.getResources().getString(R$string.dmp_stale_episode_redownload_episode_alert_subtitle)).withPositiveButton(PositiveButtonElement.builder().withOnItemSelected(Collections.emptyList()).withText(context.getResources().getString(R$string.podcast_alert_ok)).build()).withLaunchMode(TemplateLaunchMode.singleUse).build()).withScreenMode("standard").build());
        return arrayList;
    }

    public static final ButtonElement episodeRowItemUpsellButton(final Context context, final List<String> episodeContentTraits, final List<Method> upsellOnItemSelectedMethods) {
        if (!Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled()) {
            return null;
        }
        List<String> listTraits = ContentTraits.traits(episodeContentTraits);
        String str = (listTraits == null || listTraits.isEmpty()) ? null : listTraits.get(0);
        if (StringUtils.isBlank(str) || upsellOnItemSelectedMethods == null || upsellOnItemSelectedMethods.isEmpty()) {
            return null;
        }
        if (!str.equals(ContentTrait.PRE_RELEASE) && !str.equals(ContentTrait.BONUS) && !str.equals(ContentTrait.EXCLUSIVE)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(upsellOnItemSelectedMethods);
        return ButtonElement.builder().withText(context.getResources().getString(R$string.dmp_upgrade_to_listen_now_text)).withOnItemSelected(arrayList).build();
    }

    public static final BadgeElement horizontalRowItemBadge(final Context context, final Resources resources, final List<String> contentTraits, final List<String> availableUpsells) {
        if (!Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled()) {
            return null;
        }
        List<String> listTraits = ContentTraits.traits(contentTraits);
        boolean z = false;
        String str = (listTraits == null || listTraits.isEmpty()) ? null : listTraits.get(0);
        if (CustomerTier.isPrimeCustomer() && !CustomerTier.isUnlimitedCustomer()) {
            z = true;
        }
        boolean zIsAccentOutlineUpsellBadgeEnabled = Podcast.getFeatureGatePreferences().isAccentOutlineUpsellBadgeEnabled();
        if (StringUtils.isBlank(str) || availableUpsells == null || !availableUpsells.isEmpty()) {
            return null;
        }
        if (ContentTrait.AD_FREE.equals(str) || ContentTrait.PARTIALLY_AD_FREE.equals(str)) {
            return UnborderedBadgeElement.builder().withText(resources.getString(R$string.dmp_ad_free_badge_text)).build();
        }
        if (!ContentTrait.EXCLUSIVE.equals(str)) {
            return null;
        }
        if (z && zIsAccentOutlineUpsellBadgeEnabled) {
            return AccentOutlineBadgeElement.builder().withText(context.getResources().getString(R$string.dmp_bonus_badge_text)).build();
        }
        return LightUnborderedBadgeElement.builder().withText(resources.getString(R$string.dmp_exclusive_badge_text)).build();
    }

    public static final String horizontalRowItemUpsellText(final Context context, final Resources resources, final List<String> contentTraits, final List<String> availableUpsells) {
        if (!Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled()) {
            return null;
        }
        List<String> listTraits = ContentTraits.traits(contentTraits);
        if (Arrays.asList(ContentTrait.values()).contains(listTraits) && availableUpsells != null && !availableUpsells.isEmpty()) {
            if (ContentTrait.EXCLUSIVE.equals((listTraits == null || listTraits.isEmpty()) ? null : listTraits.get(0))) {
                return resources.getString(R$string.dmp_available_with_amu_text);
            }
        }
        return null;
    }

    public static final Podcast.Touch.EpisodeRowItemsInterface.v1_0.BadgeElement episodeRowItemImageBadge(final Context context, final List<String> episodeContentTraits, final List<Method> upsellOnItemSelectedMethods) {
        if (!Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled()) {
            return null;
        }
        List<String> listTraits = ContentTraits.traits(episodeContentTraits);
        boolean z = false;
        String str = (listTraits == null || listTraits.isEmpty()) ? null : listTraits.get(0);
        if (CustomerTier.isPrimeCustomer() && !CustomerTier.isUnlimitedCustomer()) {
            z = true;
        }
        boolean zIsAccentOutlineUpsellBadgeEnabled = Podcast.getFeatureGatePreferences().isAccentOutlineUpsellBadgeEnabled();
        if (StringUtils.isBlank(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(upsellOnItemSelectedMethods);
        if (str.equals(ContentTrait.PRE_RELEASE)) {
            if (z && zIsAccentOutlineUpsellBadgeEnabled) {
                return Podcast.Touch.EpisodeRowItemsInterface.v1_0.AccentOutlineBadgeElement.builder().withText(context.getResources().getString(R$string.dmp_pre_release_badge_text)).withOnItemSelected((List<Method>) arrayList).build();
            }
            return Podcast.Touch.EpisodeRowItemsInterface.v1_0.LightUnborderedBadgeElement.builder().withText(context.getResources().getString(R$string.dmp_pre_release_badge_text)).withOnItemSelected((List<Method>) arrayList).build();
        }
        if (str.equals(ContentTrait.BONUS)) {
            if (z && zIsAccentOutlineUpsellBadgeEnabled) {
                return Podcast.Touch.EpisodeRowItemsInterface.v1_0.AccentOutlineBadgeElement.builder().withText(context.getResources().getString(R$string.dmp_bonus_badge_text)).withOnItemSelected((List<Method>) arrayList).build();
            }
            return Podcast.Touch.EpisodeRowItemsInterface.v1_0.LightUnborderedBadgeElement.builder().withText(context.getResources().getString(R$string.dmp_bonus_badge_text)).withOnItemSelected((List<Method>) arrayList).build();
        }
        if (!str.equals(ContentTrait.EXCLUSIVE)) {
            return null;
        }
        if (z && zIsAccentOutlineUpsellBadgeEnabled) {
            return Podcast.Touch.EpisodeRowItemsInterface.v1_0.AccentOutlineBadgeElement.builder().withText(context.getResources().getString(R$string.dmp_exclusive_badge_text)).withOnItemSelected((List<Method>) arrayList).build();
        }
        return Podcast.Touch.EpisodeRowItemsInterface.v1_0.LightUnborderedBadgeElement.builder().withText(context.getResources().getString(R$string.dmp_exclusive_badge_text)).withOnItemSelected((List<Method>) arrayList).build();
    }

    public static final Podcast.Touch.EpisodeRowItemsInterface.v1_0.BadgeElement episodeRowItemDescriptionBadge(final Context context, final List<String> episodeContentTraits, final List<Method> upsellOnItemSelectedMethods) {
        if (!Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled()) {
            return null;
        }
        List<String> listTraits = ContentTraits.traits(episodeContentTraits);
        String str = (listTraits == null || listTraits.isEmpty()) ? null : listTraits.get(0);
        if (StringUtils.isBlank(str)) {
            return null;
        }
        if (str.equals(ContentTrait.AD_FREE)) {
            return Podcast.Touch.EpisodeRowItemsInterface.v1_0.UnborderedBadgeElement.builder().withText(context.getResources().getString(R$string.dmp_ad_free_badge_text)).build();
        }
        if (!str.equals(ContentTrait.AD_SUPPORTED)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(upsellOnItemSelectedMethods);
        return BorderedBadgeElement.builder().withText(context.getResources().getString(R$string.dmp_ad_supported_badge_text)).withOnItemSelected((List<Method>) arrayList).build();
    }

    public static final Podcast.Touch.CompactDetailTemplateInterface.v1_0.BadgeElement compactDetailBadge(final Context context, final Set<String> benefits, final List<String> episodeContentTraits, final List<Method> upsellOnItemSelectedMethods) {
        if (!Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled()) {
            return null;
        }
        List<String> listTraits = ContentTraits.traits(episodeContentTraits);
        String str = (listTraits == null || listTraits.isEmpty()) ? null : listTraits.get(0);
        if (StringUtils.isBlank(str)) {
            return null;
        }
        boolean zEquals = str.equals(ContentTrait.EXCLUSIVE);
        boolean zEquals2 = str.equals(ContentTrait.BONUS);
        boolean zEquals3 = str.equals(ContentTrait.PRE_RELEASE);
        boolean zEquals4 = str.equals(ContentTrait.AD_FREE);
        boolean zEquals5 = str.equals(ContentTrait.AD_SUPPORTED);
        boolean zIsUnlimitedCustomer = CustomerTier.isUnlimitedCustomer();
        boolean zIsPrimeCustomer = CustomerTier.isPrimeCustomer();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(upsellOnItemSelectedMethods);
        if (zEquals3) {
            if (zIsUnlimitedCustomer || zIsPrimeCustomer) {
                return null;
            }
            return Podcast.Touch.CompactDetailTemplateInterface.v1_0.LightUnborderedBadgeElement.builder().withText(context.getResources().getString(R$string.dmp_pre_release_badge_text)).withOnItemSelected((List<Method>) arrayList).build();
        }
        if (zEquals || zEquals2) {
            if (zIsUnlimitedCustomer || !zIsPrimeCustomer) {
                return Podcast.Touch.CompactDetailTemplateInterface.v1_0.LightUnborderedBadgeElement.builder().withText(context.getResources().getString(R$string.dmp_bonus_badge_text)).withOnItemSelected((List<Method>) arrayList).build();
            }
            return null;
        }
        if (zEquals4) {
            return Podcast.Touch.CompactDetailTemplateInterface.v1_0.UnborderedBadgeElement.builder().withText(context.getResources().getString(R$string.dmp_ad_free_badge_text)).build();
        }
        if (zEquals5) {
            return Podcast.Touch.CompactDetailTemplateInterface.v1_0.BorderedBadgeElement.builder().withText(context.getResources().getString(R$string.dmp_ad_supported_badge_text)).withOnItemSelected((List<Method>) arrayList).build();
        }
        return null;
    }

    public static final Podcast.Touch.CompactDetailTemplateInterface.v1_0.ButtonElement compactDetailUpsellButton(final Context context, final List<String> episodeContentTraits, final List<Method> upsellOnItemSelectedMethods) {
        if (!Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled()) {
            return null;
        }
        List<String> listTraits = ContentTraits.traits(episodeContentTraits);
        String str = (listTraits == null || listTraits.isEmpty()) ? null : listTraits.get(0);
        if (StringUtils.isBlank(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(upsellOnItemSelectedMethods);
        if (!Arrays.asList(ContentTrait.values()).contains(str) || upsellOnItemSelectedMethods == null || upsellOnItemSelectedMethods.isEmpty()) {
            return null;
        }
        if (str.equals(ContentTrait.PRE_RELEASE) || str.equals(ContentTrait.BONUS) || str.equals(ContentTrait.EXCLUSIVE)) {
            return Podcast.Touch.CompactDetailTemplateInterface.v1_0.ButtonElement.builder().withText(context.getResources().getString(R$string.dmp_upgrade_to_listen_now_text)).withOnItemSelected(arrayList).build();
        }
        return null;
    }

    public static final String compactDetailTemplateUpsellText(final Context context, final Set<String> benefits, final List<String> availableUpsells, final List<String> episodeContentTraits) {
        if (!Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled()) {
            return null;
        }
        List<String> listTraits = ContentTraits.traits(episodeContentTraits);
        String str = (listTraits == null || listTraits.isEmpty()) ? null : listTraits.get(0);
        if (StringUtils.isBlank(str)) {
            return null;
        }
        if (hasUpsells(availableUpsells)) {
            boolean z = availableUpsells.contains(UpsellType.UNLIMITED) || availableUpsells.contains(UpsellType.PRIME);
            boolean z2 = ContentTrait.AD_SUPPORTED.equals(str) || ContentTrait.PARTIALLY_AD_SUPPORTED.equals(str);
            if (!z || z2) {
                return null;
            }
            return context.getResources().getString(R$string.dmp_available_with_amu_text);
        }
        boolean zIsUnlimitedCustomer = CustomerTier.isUnlimitedCustomer();
        boolean zIsPrimeCustomer = CustomerTier.isPrimeCustomer();
        if (str.equals(ContentTrait.EXCLUSIVE) || str.equals(ContentTrait.BONUS)) {
            if (zIsUnlimitedCustomer) {
                return null;
            }
            if (zIsPrimeCustomer) {
                return context.getResources().getString(R$string.dmp_included_with_prime_text);
            }
        }
        if (str.equals(ContentTrait.PRE_RELEASE)) {
            if (zIsUnlimitedCustomer) {
                return context.getResources().getString(R$string.dmp_early_access_with_amu_text);
            }
            if (zIsPrimeCustomer) {
                return context.getResources().getString(R$string.dmp_early_access_with_prime_text);
            }
        }
        return null;
    }

    public static final String getDisplayDate(final Resources resources, final List<String> availableUpsells, final List<String> contentTraits, final String publishDate, final String availabilityDate) {
        if (!Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled()) {
            return Strings.getReadablePublishDate(publishDate, resources);
        }
        String availabilityDate2 = getAvailabilityDate(resources, availableUpsells, contentTraits, availabilityDate);
        return StringUtils.isNotEmpty(availabilityDate2) ? availabilityDate2 : Strings.getReadablePublishDate(publishDate, resources);
    }

    public static final boolean hasAvailabilityDatePassed(final String availabilityDateString) {
        if (availabilityDateString == null) {
            return true;
        }
        try {
            return new SimpleDateFormat(DateUtils.ALTERNATE_ISO8601_DATE_PATTERN).parse(availabilityDateString).before(new Date());
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    private static final String getAvailabilityDate(final Resources resources, final List<String> availableUpsells, final List<String> contentTraits, final String availabilityDate) {
        if (!StringUtils.isBlank(availabilityDate) && contentTraits != null && !contentTraits.isEmpty()) {
            List<String> listTypes = Upsells.types(availableUpsells);
            boolean z = listTypes != null && listTypes.size() > 0;
            boolean zContains = contentTraits.contains(ContentTrait.PRE_RELEASE);
            if (z && zContains) {
                try {
                    return String.format(resources.getString(R$string.dmp_redhoodplus_available_on_date), Strings.convertDateToPattern(availabilityDate));
                } catch (Exception e) {
                    logger.error("Error in formatting availability date ", (Throwable) e);
                }
            }
        }
        return null;
    }

    private static final boolean hasUpsells(final List<String> availableUpsells) {
        List<String> listTypes = Upsells.types(availableUpsells);
        return listTypes != null && listTypes.size() > 0;
    }

    private static final List<Method> showUpsellMethods(final String redirectDeeplinkUrl, final UiMetricAttributes$PageType pageType, final UiMetricAttributes$ContentName contentName, final String entityId, final UiMetricAttributes$EntityType entityType, final UiMetricAttributes$EntityIdType entityIdType) {
        ArrayList arrayList = new ArrayList();
        ShowUpsellMethod showUpsellMethodBuild = ShowUpsellMethod.builder().withTrigger(UpsellTriggerType.PERSISTENT_UPSELL).withRedirectDeeplinkUrl(redirectDeeplinkUrl).withQueue(Queues.template()).build();
        List<Method> listOnClicked = UiMetricMethods.onClicked(pageType, UiMetricAttributes$ActionType.SELECT_PODCAST_UPSELL, entityType, entityIdType, entityId, (Integer) null, contentName);
        arrayList.add(showUpsellMethodBuild);
        arrayList.addAll(listOnClicked);
        return arrayList;
    }

    private static final List<Method> showUpsellMethods(final String redirectDeeplinkUrl, final String blockRef, final UiMetricAttributes$PageType pageType) {
        ArrayList arrayList = new ArrayList();
        ShowUpsellMethod showUpsellMethodBuild = ShowUpsellMethod.builder().withTrigger(UpsellTriggerType.PERSISTENT_UPSELL).withRedirectDeeplinkUrl(redirectDeeplinkUrl).withQueue(Queues.template()).build();
        List<Method> listOnClicked = UiMetricMethods.onClicked(blockRef, UiMetricAttributes$ActionType.SELECT_PODCAST_UPSELL, pageType, (UiMetricAttributes$ContentName) null);
        arrayList.add(showUpsellMethodBuild);
        arrayList.addAll(listOnClicked);
        return arrayList;
    }
}
