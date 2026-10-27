package com.amazon.music.explore.views.swipeablePages.binders;

import CoreInterface.v1_0.Element;
import Touch.SwipeablePagesTemplateInterface.v1_0.ScrollableListElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.amazon.music.explore.ExploreStyleSheet;
import com.amazon.music.explore.R;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesStyleSheet;
import com.amazon.music.explore.views.swipeablePages.pages.ScrollableListView;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.VerticalContainerView;
import com.amazon.music.picassoimageloader.ImageBlurTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class ScrollableListBinder {
    private static final int MILLSECOND = 1000;

    public static void bind(ScrollableListView scrollableListView, ScrollableListElement scrollableListElement, SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        bindBackgroundImage(scrollableListView, scrollableListElement.backgroundImage(), scrollableListElement.shouldBlurBackground().booleanValue());
        bindVerticalContainers(scrollableListView, scrollableListElement.elements(), swipeablePagesStyleSheet);
        bindDuration(scrollableListView, scrollableListElement.duration());
        bindSpeed(scrollableListView, scrollableListElement.speed());
    }

    private static void bindVerticalContainers(ScrollableListView scrollableListView, List<Element> list, SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        if (list == null || list.isEmpty()) {
            return;
        }
        VerticalContainerView verticalContainerViewCreateVerticalContainerView = createVerticalContainerView(scrollableListView, list, swipeablePagesStyleSheet, swipeablePagesStyleSheet.getScrollableListMaxWidth());
        scrollableListView.getVerticalContainer().addVerticalContainerView(verticalContainerViewCreateVerticalContainerView);
        addBeginningSpace(verticalContainerViewCreateVerticalContainerView);
    }

    private static VerticalContainerView createVerticalContainerView(ScrollableListView scrollableListView, List<Element> list, SwipeablePagesStyleSheet swipeablePagesStyleSheet, int i) {
        VerticalContainerItemBinders verticalContainerItemBinders = new VerticalContainerItemBinders(scrollableListView.getOwnerId(), scrollableListView.getContext(), scrollableListView.getMethodsDispatcher(), swipeablePagesStyleSheet, i);
        VerticalContainerView verticalContainerView = new VerticalContainerView(scrollableListView.getContext());
        for (Element element : list) {
            if (element instanceof VerticalContainerElement) {
                Iterator<View> it = verticalContainerItemBinders.bind((VerticalContainerElement) element, false, false).iterator();
                while (it.hasNext()) {
                    verticalContainerView.addView(it.next());
                }
            }
            verticalContainerView.addMarginBottom();
        }
        return verticalContainerView;
    }

    private static void bindBackgroundImage(ScrollableListView scrollableListView, String str, boolean z) {
        if (StringUtils.isNotEmpty(str)) {
            ImageView imageView = (ImageView) scrollableListView.findViewById(R.id.scrollable_list_background);
            RequestCreator requestCreatorLoad = Picasso.get().load(str);
            if (z) {
                requestCreatorLoad.transform(new ImageBlurTransformation());
            }
            requestCreatorLoad.into(imageView);
        }
    }

    private static void bindDuration(ScrollableListView scrollableListView, Integer num) {
        if (num != null) {
            scrollableListView.getVerticalContainer().setMinDuration(num.intValue() * 1000);
        }
    }

    private static void bindSpeed(ScrollableListView scrollableListView, Integer num) {
        if (num != null) {
            scrollableListView.getVerticalContainer().setSpeed(num.intValue());
        }
    }

    private static void addBeginningSpace(VerticalContainerView verticalContainerView) {
        int dimensionPixelSize;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) verticalContainerView.getLayoutParams();
        if (ExploreStyleSheet.isXL(verticalContainerView.getContext())) {
            dimensionPixelSize = verticalContainerView.getResources().getDimensionPixelSize(R.dimen.spacer_huge);
        } else {
            dimensionPixelSize = verticalContainerView.getResources().getDimensionPixelSize(R.dimen.spacer_medium);
        }
        layoutParams.topMargin = dimensionPixelSize * 2;
    }
}
