package com.amazon.podcast.views.savesTemplate;

import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElement;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionItemElement;
import Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElement;
import Podcast.SaveInterface.v1_0.RefinementBarElement;
import Podcast.SaveInterface.v1_0.SavesTemplate;
import Podcast.SaveInterface.v1_0.Sort;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
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
public final class SavesTemplates {
    public static SavesTemplate getTemplateForLibrary(final Resources resources) {
        ArrayList arrayList = new ArrayList();
        UiMetricAttributes$PageType uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCASTS_LIBRARY_PLAYLIST;
        arrayList.addAll(UiMetricMethods.onPageViewed(uiMetricAttributes$PageType));
        arrayList.addAll(UiMetricMethods.onContentViewed(uiMetricAttributes$PageType, UiMetricAttributes$ContainerType.ITEM_LIST, UiMetricAttributes$ContentName.PODCASTS_LIBRARY_PLAYLIST));
        arrayList.addAll(DeeplinkMethods.incrementalSyncOnViewed());
        return SavesTemplate.builder().withRefinementBar(getSavesTemplateRefinementBarElement(resources)).withLaunchMode("standard").withOnBound(Collections.emptyList()).withOnCreated(Collections.emptyList()).withOnViewed((List<Method>) arrayList).build();
    }

    public static SavesTemplate getTemplateForSeeMore(final Context context) {
        return SavesTemplate.builder().withLaunchMode("standard").withOnBound(Collections.emptyList()).withOnCreated(Collections.emptyList()).withOnViewed(UiMetricMethods.onPageViewed(UiMetricAttributes$PageType.PODCASTS_SAVED_EPISODES)).withHeader(context.getResources().getString(R$string.podcast_saved_episodes_header)).build();
    }

    private static RefinementBarElement getSavesTemplateRefinementBarElement(final Resources resources) {
        CheckBoxRefinementOptionElement sortOptionElement = getSortOptionElement(resources);
        return RefinementBarElement.builder().withSortingOptions(sortOptionElement).withUnplayedFilter(getUnplayedOptionElement(resources)).build();
    }

    private static CheckBoxRefinementOptionElement getSortOptionElement(final Resources resources) {
        UiMetricAttributes$PageType uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCASTS_LIBRARY_PLAYLIST;
        UiMetricAttributes$ActionType uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.SELECT_SORT_DATE_ADDED;
        UiMetricAttributes$ContentName uiMetricAttributes$ContentName = UiMetricAttributes$ContentName.PODCAST_REFINEMENT_BAR;
        CheckBoxRefinementOptionItemElement checkBoxRefinementOptionItemElement = getCheckBoxRefinementOptionItemElement(Sort.DATE_ADDED, resources.getString(R$string.podcast_sort_date_added), UiMetricMethods.onClicked(uiMetricAttributes$PageType, uiMetricAttributes$ActionType, uiMetricAttributes$ContentName), Sort.DATE_ADDED);
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
        return CheckBoxRefinementOptionElement.builder().withId("SORT").withDefaultState(Sort.DATE_ADDED).withOptions(arrayList).withAccessibilityLabel(string).withOnItemSelected(UiMetricMethods.onClicked(uiMetricAttributes$PageType, UiMetricAttributes$ActionType.EXPAND_PODCAST_REFINEMENT_BAR_SORT_MENU, uiMetricAttributes$ContentName)).withOnApply(listOnClicked).withOnReset(listOnClicked2).withOnDismiss(UiMetricMethods.onClicked(uiMetricAttributes$PageType, UiMetricAttributes$ActionType.SELECT_DISMISS_PODCAST_REFINEMENT_BAR_SORT_MENU, uiMetricAttributes$ContentName)).withText(string).build();
    }

    private static ToggleRefinementOptionElement getUnplayedOptionElement(final Resources resources) {
        String string = resources.getString(R$string.podcast_filter_unplayed);
        return ToggleRefinementOptionElement.builder().withId("UNPLAYED").withText(string).withAccessibilityLabel(string).withOnItemSelected(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_LIBRARY_PLAYLIST, UiMetricAttributes$ActionType.SELECT_FILTER_UNPLAYED_PODCAST_EPISODE, UiMetricAttributes$ContentName.PODCAST_REFINEMENT_BAR)).build();
    }

    private static CheckBoxRefinementOptionItemElement getCheckBoxRefinementOptionItemElement(final String id, final String text, final List<Method> onItemSelected, final String accessibilityLabel) {
        return CheckBoxRefinementOptionItemElement.builder().withId(id).withText(text).withOnItemSelected(onItemSelected).withAccessibilityLabel(accessibilityLabel).build();
    }
}
