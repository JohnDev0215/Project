package com.amazon.podcast.views.refinements;

import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElement;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionItemElement;

/* JADX INFO: loaded from: classes5.dex */
public interface CheckBoxRefinementEventsListener {
    void onApply(final CheckBoxRefinementOptionItemElement item, final CheckBoxRefinementOptionElement element);

    void onOptionItemSelectedForPopUp(final CheckBoxRefinementOptionItemElement item, final CheckBoxRefinementOptionElement element);

    void onResetSelected(final CheckBoxRefinementOptionElement element);
}
