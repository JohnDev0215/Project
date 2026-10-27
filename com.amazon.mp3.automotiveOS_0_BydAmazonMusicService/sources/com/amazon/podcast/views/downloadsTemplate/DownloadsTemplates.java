package com.amazon.podcast.views.downloadsTemplate;

import Podcast.DownloadInterface.v1_0.DownloadsTemplate;
import Podcast.DownloadInterface.v1_0.RefinementBarElement;
import Podcast.DownloadInterface.v1_0.Sort;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElement;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionItemElement;
import Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElement;
import SOACoreInterface.v1_0.Method;
import android.content.res.Resources;
import com.amazon.podcast.R$string;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContainerType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class DownloadsTemplates {
    public static DownloadsTemplate getTemplate(final Resources resources) {
        ArrayList arrayList = new ArrayList();
        UiMetricAttributes$PageType uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCASTS_LIBRARY_DOWNLOADS;
        arrayList.addAll(UiMetricMethods.onPageViewed(uiMetricAttributes$PageType));
        arrayList.addAll(UiMetricMethods.onContentViewed(uiMetricAttributes$PageType, UiMetricAttributes$ContainerType.ITEM_LIST, UiMetricAttributes$ContentName.PODCASTS_LIBRARY_DOWNLOADS));
        arrayList.addAll(DeeplinkMethods.incrementalSyncOnViewed());
        DownloadsTemplate.Builder builderWithOnViewed = DownloadsTemplate.builder().withRefinementBar(getRefinementBar(resources)).withLaunchMode("standard").withOnViewed((List<Method>) arrayList);
        List<Method> list = Collections.EMPTY_LIST;
        return builderWithOnViewed.withOnBound(list).withOnCreated(list).build();
    }

    public static RefinementBarElement getRefinementBar(final Resources resources) {
        CheckBoxRefinementOptionElement sortOptionElement = getSortOptionElement(resources);
        return RefinementBarElement.builder().withSortingOptions(sortOptionElement).withUnplayedFilter(getUnplayedOptionElement(resources)).build();
    }

    private static CheckBoxRefinementOptionElement getSortOptionElement(final Resources resources) {
        UiMetricAttributes$PageType uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCASTS_LIBRARY_DOWNLOADS;
        UiMetricAttributes$ActionType uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.SELECT_SORT_DATE_DOWNLOADED;
        UiMetricAttributes$ContentName uiMetricAttributes$ContentName = UiMetricAttributes$ContentName.PODCAST_REFINEMENT_BAR;
        CheckBoxRefinementOptionItemElement checkBoxRefinementOptionItemElement = getCheckBoxRefinementOptionItemElement(Sort.DATE_DOWNLOADED, resources.getString(R$string.podcast_sort_date_downloaded), UiMetricMethods.onClicked(uiMetricAttributes$PageType, uiMetricAttributes$ActionType, uiMetricAttributes$ContentName), Sort.DATE_DOWNLOADED);
        CheckBoxRefinementOptionItemElement checkBoxRefinementOptionItemElement2 = getCheckBoxRefinementOptionItemElement("OLDEST_TO_NEWEST", resources.getString(R$string.podcast_sort_oldest_to_newest), UiMetricMethods.onClicked(uiMetricAttributes$PageType, UiMetricAttributes$ActionType.SELECT_SORT_CHRONOLOGICAL_OLDEST_FIRST, uiMetricAttributes$ContentName), "OLDEST_TO_NEWEST");
        CheckBoxRefinementOptionItemElement checkBoxRefinementOptionItemElement3 = getCheckBoxRefinementOptionItemElement("NEWEST_TO_OLDEST", resources.getString(R$string.podcast_sort_newest_to_oldest), UiMetricMethods.onClicked(uiMetricAttributes$PageType, UiMetricAttributes$ActionType.SELECT_SORT_CHRONOLOGICAL_NEWEST_FIRST, uiMetricAttributes$ContentName), "NEWEST_TO_OLDEST");
        CheckBoxRefinementOptionItemElement checkBoxRefinementOptionItemElement4 = getCheckBoxRefinementOptionItemElement("SHOW_NAME", resources.getString(R$string.podcast_sort_show_name), UiMetricMethods.onClicked(uiMetricAttributes$PageType, UiMetricAttributes$ActionType.SELECT_SORT_SHOW_NAME, uiMetricAttributes$ContentName), "SHOW_NAME");
        ArrayList arrayList = new ArrayList();
        arrayList.add(checkBoxRefinementOptionItemElement);
        arrayList.add(checkBoxRefinementOptionItemElement2);
        arrayList.add(checkBoxRefinementOptionItemElement3);
        arrayList.add(checkBoxRefinementOptionItemElement4);
        String string = resources.getString(R$string.podcast_sort);
        List<Method> listOnClicked = UiMetricMethods.onClicked(uiMetricAttributes$PageType, UiMetricAttributes$ActionType.SELECT_SORT_APPLY, uiMetricAttributes$ContentName);
        List<Method> listOnClicked2 = UiMetricMethods.onClicked(uiMetricAttributes$PageType, UiMetricAttributes$ActionType.SELECT_SORT_RESET, uiMetricAttributes$ContentName);
        return CheckBoxRefinementOptionElement.builder().withId("SORT").withDefaultState(Sort.DATE_DOWNLOADED).withOptions(arrayList).withAccessibilityLabel(string).withOnItemSelected(UiMetricMethods.onClicked(uiMetricAttributes$PageType, UiMetricAttributes$ActionType.EXPAND_PODCAST_REFINEMENT_BAR_SORT_MENU, uiMetricAttributes$ContentName)).withOnApply(listOnClicked).withOnReset(listOnClicked2).withOnDismiss(UiMetricMethods.onClicked(uiMetricAttributes$PageType, UiMetricAttributes$ActionType.SELECT_DISMISS_PODCAST_REFINEMENT_BAR_SORT_MENU, uiMetricAttributes$ContentName)).withText(string).build();
    }

    private static ToggleRefinementOptionElement getUnplayedOptionElement(final Resources resources) {
        String string = resources.getString(R$string.podcast_filter_unplayed);
        return ToggleRefinementOptionElement.builder().withId("UNPLAYED").withText(string).withAccessibilityLabel(string).withOnItemSelected(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_LIBRARY_DOWNLOADS, UiMetricAttributes$ActionType.SELECT_FILTER_UNPLAYED_PODCAST_EPISODE, UiMetricAttributes$ContentName.PODCAST_REFINEMENT_BAR)).build();
    }

    private static CheckBoxRefinementOptionItemElement getCheckBoxRefinementOptionItemElement(final String id, final String text, final List<Method> onItemSelected, final String accessibilityLabel) {
        return CheckBoxRefinementOptionItemElement.builder().withId(id).withText(text).withOnItemSelected(onItemSelected).withAccessibilityLabel(accessibilityLabel).build();
    }
}
