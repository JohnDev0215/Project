package com.amazon.music.explore.views.swipeablePages.binders;

import CoreInterface.v1_0.Element;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeableSectionsElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.ThumbnailElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.amazon.music.explore.R;
import com.amazon.music.explore.picasso.Blur2Transformation;
import com.amazon.music.explore.picasso.BlurTransformation;
import com.amazon.music.explore.views.swipeablePages.StageGradientMaskView;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesStyleSheet;
import com.amazon.music.explore.views.swipeablePages.pages.StageSwipeableSectionsView;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeableSectionsView;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.VerticalContainerView;
import com.amazon.music.msautils.MsaUrl;
import com.amazon.music.picassoimageloader.ImageBlurTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeableSectionsBinder {
    private static final int MILLSECOND = 1000;
    private static final int STAGE_SWIPEABLE_PAGE_GRADIENT_POS = 1;

    public static String getSizedThumbnailUrl(String str, int i) {
        return MsaUrl.with(str).scaleToWidth(i).toUrl();
    }

    public static void bind(SwipeableSectionsView swipeableSectionsView, SwipeableSectionsElement swipeableSectionsElement, SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        bindBackgroundImage(swipeableSectionsView, swipeableSectionsElement.backgroundImage(), swipeableSectionsElement.shouldBlurBackground().booleanValue());
        bindSectionDuration(swipeableSectionsView, swipeableSectionsElement.duration());
        bindVerticalContainers(swipeableSectionsView, swipeableSectionsElement.sections(), swipeablePagesStyleSheet, false, false);
        bindSpeed(swipeableSectionsView, swipeableSectionsElement.speed());
    }

    public static void bindStage(StageSwipeableSectionsView stageSwipeableSectionsView, SwipeablePageElement swipeablePageElement, SwipeablePagesStyleSheet swipeablePagesStyleSheet, int i, boolean z) {
        bindStageSectionDuration(stageSwipeableSectionsView, i);
        if (swipeablePageElement instanceof SwipeableSectionsElement) {
            bindVerticalContainers(stageSwipeableSectionsView, ((SwipeableSectionsElement) swipeablePageElement).sections(), swipeablePagesStyleSheet, true, z);
        }
        bindSpeed(stageSwipeableSectionsView, swipeablePageElement.speed());
    }

    private static void bindVerticalContainers(SwipeablePageView swipeablePageView, List<Element> list, SwipeablePagesStyleSheet swipeablePagesStyleSheet, boolean z, boolean z2) {
        int maxWidth;
        ArrayList arrayList = new ArrayList();
        for (Element element : list) {
            if (element instanceof VerticalContainerElement) {
                VerticalContainerElement verticalContainerElement = (VerticalContainerElement) element;
                loadBackgroundImage(verticalContainerElement.backgroundImage(), z ? swipeablePagesStyleSheet.getThumbnailSizeForStage() : swipeablePagesStyleSheet.getThumbnailSize());
                if (z) {
                    maxWidth = getMaxWidthForStage(swipeablePagesStyleSheet);
                } else {
                    maxWidth = getMaxWidth(verticalContainerElement.elements(), swipeablePagesStyleSheet);
                }
                arrayList.addAll(createVerticalContainerViews(swipeablePageView, verticalContainerElement, swipeablePagesStyleSheet, maxWidth, z, z2));
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        if (!z) {
            ((SwipeableSectionsView) swipeablePageView).setSections(arrayList);
        } else {
            ((StageSwipeableSectionsView) swipeablePageView).setSections(arrayList);
        }
    }

    private static List<VerticalContainerView> createVerticalContainerViews(SwipeablePageView swipeablePageView, VerticalContainerElement verticalContainerElement, SwipeablePagesStyleSheet swipeablePagesStyleSheet, int i, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        VerticalContainerItemBinders verticalContainerItemBinders = new VerticalContainerItemBinders(swipeablePageView.getOwnerId(), swipeablePageView.getContext(), swipeablePageView.getMethodsDispatcher(), swipeablePagesStyleSheet, i);
        List<View> listBind = verticalContainerItemBinders.bind(verticalContainerElement, z, z2, swipeablePageView);
        if (!listBind.isEmpty()) {
            arrayList.add(createVerticalContainerView(swipeablePageView.getContext(), verticalContainerElement, listBind));
        }
        Iterator<List<View>> it = verticalContainerItemBinders.getListOfSupplementalViewItems(swipeablePageView, z, z2).iterator();
        while (it.hasNext()) {
            arrayList.add(createVerticalContainerView(swipeablePageView.getContext(), verticalContainerElement, it.next()));
        }
        return arrayList;
    }

    private static VerticalContainerView createVerticalContainerView(Context context, VerticalContainerElement verticalContainerElement, List<View> list) {
        VerticalContainerView verticalContainerView = new VerticalContainerView(context);
        verticalContainerView.setBackgroundImage(verticalContainerElement.backgroundImage());
        verticalContainerView.setShouldBlurBackground(verticalContainerElement.shouldBlurBackground().booleanValue());
        verticalContainerView.addOnViewedMethods(verticalContainerElement.onViewed());
        if (verticalContainerElement.onViewedPreview() != null) {
            verticalContainerView.addOnViewedPreviewMethods(verticalContainerElement.onViewedPreview());
        }
        if (verticalContainerElement.onLongPressed() != null) {
            verticalContainerView.addOnLongPressedMethods(verticalContainerElement.onLongPressed());
        }
        if (verticalContainerElement.onPreviewClicked() != null) {
            verticalContainerView.addOnPreviewClickedMethods(verticalContainerElement.onPreviewClicked());
        }
        Iterator<View> it = list.iterator();
        while (it.hasNext()) {
            verticalContainerView.addView(it.next());
        }
        return verticalContainerView;
    }

    private static void bindBackgroundImage(SwipeableSectionsView swipeableSectionsView, String str, boolean z) {
        if (StringUtils.isNotEmpty(str)) {
            ImageView imageView = (ImageView) swipeableSectionsView.findViewById(R.id.swipeable_sections_background);
            ImageView imageView2 = (ImageView) swipeableSectionsView.findViewById(R.id.swipeable_sections_background_overlay);
            RequestCreator requestCreatorLoad = Picasso.get().load(str);
            if (z) {
                requestCreatorLoad.transform(new Blur2Transformation(swipeableSectionsView.getContext()));
                imageView2.setImageResource(R.color.secondary_glass_3);
            } else {
                imageView2.setImageResource(R.color.black_50);
            }
            requestCreatorLoad.into(imageView);
        }
    }

    private static void bindBackgroundImageForStage(StageSwipeableSectionsView stageSwipeableSectionsView, String str, boolean z, SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        if (StringUtils.isNotEmpty(str)) {
            FrameLayout frameLayout = (FrameLayout) stageSwipeableSectionsView.findViewById(R.id.stage_swipeable_pages_images_container);
            ImageView imageView = (ImageView) stageSwipeableSectionsView.findViewById(R.id.stage_swipeable_sections_background);
            ImageView imageView2 = (ImageView) stageSwipeableSectionsView.findViewById(R.id.stage_swipeable_sections_background_overlay);
            frameLayout.setVisibility(0);
            RelativeLayout relativeLayout = (RelativeLayout) stageSwipeableSectionsView.findViewById(R.id.stage_swipeable_sections_view);
            RequestCreator requestCreatorLoad = Picasso.get().load(str);
            if (z) {
                if (stageSwipeableSectionsView.isShownInAutoSequencing()) {
                    frameLayout.setForeground(imageView.getContext().getDrawable(R.drawable.stage_swipeable_pages_background_art_gradient));
                    requestCreatorLoad = requestCreatorLoad.transform(new BlurTransformation(stageSwipeableSectionsView.getContext(), swipeablePagesStyleSheet.getBlurRadiusForStage()));
                    requestCreatorLoad.transform(new BlurTransformation(stageSwipeableSectionsView.getContext(), swipeablePagesStyleSheet.getBlurRadiusForStage()));
                } else if (stageSwipeableSectionsView.canVerticalContainerOverflow()) {
                    requestCreatorLoad.transform(new ImageBlurTransformation());
                    imageView2.setImageResource(R.color.secondary_glass_4);
                    relativeLayout.addView(createGradientView(relativeLayout.getContext()), 1);
                } else {
                    requestCreatorLoad.transform(new Blur2Transformation(stageSwipeableSectionsView.getContext()));
                    imageView2.setImageResource(R.color.secondary_glass_3);
                }
            } else {
                imageView2.setImageResource(R.color.black_50);
            }
            requestCreatorLoad.into(imageView);
        }
    }

    private static View createGradientView(Context context) {
        StageGradientMaskView stageGradientMaskView = new StageGradientMaskView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(6, R.id.swipeable_sections_vertical_container);
        stageGradientMaskView.setLayoutParams(layoutParams);
        return stageGradientMaskView;
    }

    private static void bindSectionDuration(SwipeableSectionsView swipeableSectionsView, Integer num) {
        if (num != null) {
            swipeableSectionsView.getVerticalContainer().setSectionDuration(num.intValue() * 1000);
        }
    }

    private static void bindStageSectionDuration(StageSwipeableSectionsView stageSwipeableSectionsView, int i) {
        stageSwipeableSectionsView.getVerticalContainer().setSectionDuration(i);
    }

    private static void bindSpeed(SwipeablePageView swipeablePageView, Integer num) {
        if (num != null) {
            if (swipeablePageView instanceof SwipeableSectionsView) {
                ((SwipeableSectionsView) swipeablePageView).getVerticalContainer().setSpeed(num.intValue());
            } else {
                ((StageSwipeableSectionsView) swipeablePageView).getVerticalContainer().setSpeed(num.intValue());
            }
        }
    }

    private static void loadBackgroundImage(String str, int i) {
        if (StringUtils.isNotEmpty(str)) {
            Picasso.get().load(getSizedThumbnailUrl(str, i));
        }
    }

    private static int getMaxWidth(List<Element> list, SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        Iterator<Element> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() instanceof ThumbnailElement) {
                return swipeablePagesStyleSheet.getThumbnailSize();
            }
        }
        return swipeablePagesStyleSheet.getSwipeableSectionsMaxWidth();
    }

    private static int getMaxWidthForStage(SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        return swipeablePagesStyleSheet.getStageSwipeableSectionsMaxWidth();
    }
}
