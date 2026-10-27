package com.amazon.music.explore.views.swipeablePages.binders;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.IconLinkElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.ImmutableClickableTextElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.ImmutableVerticalContainerElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.LabelElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.SeparatorElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.ThumbnailElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesStyleSheet;
import com.amazon.music.explore.views.swipeablePages.pages.StageSwipeableSectionsView;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageCallback;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ClickableTextView;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.LabelView;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ThumbnailView;
import com.amazon.music.explore.widgets.views.DividerView;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class VerticalContainerItemBinders {
    private static final String ELLIPSES = "...";
    private static final char SPACE_CHARACTER = ' ';
    private final Context context;
    private int maxWidth;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final SwipeablePagesStyleSheet styleSheet;
    private SwipeablePageCallback swipeablePageCallback;
    private List<List<Element>> supplementalElements = new ArrayList();
    private List<Element> preservedElements = new ArrayList();
    private boolean shouldUpdatePreservedElements = true;

    public VerticalContainerItemBinders(String str, Context context, MethodsDispatcher methodsDispatcher, SwipeablePagesStyleSheet swipeablePagesStyleSheet, int i) {
        this.ownerId = str;
        this.context = context;
        this.methodsDispatcher = methodsDispatcher;
        this.styleSheet = swipeablePagesStyleSheet;
        this.maxWidth = i;
    }

    public List<List<View>> getListOfSupplementalViewItems(SwipeablePageView swipeablePageView, boolean z, boolean z2) {
        if (this.supplementalElements.isEmpty()) {
            return new ArrayList();
        }
        this.shouldUpdatePreservedElements = false;
        ArrayList arrayList = new ArrayList();
        for (List<Element> list : this.supplementalElements) {
            ImmutableVerticalContainerElement.Builder builderAddAllElements = ImmutableVerticalContainerElement.builder().addAllElements(this.preservedElements);
            builderAddAllElements.addAllElements(list);
            arrayList.add(bind(builderAddAllElements.build(), z, z2, swipeablePageView));
        }
        return arrayList;
    }

    private int breakVerticalContainerIntoMultipleSectionsForStage(List<Element> list, boolean z) {
        int i;
        int maxLinesToShow = this.styleSheet.getMaxLinesToShow(z);
        int i2 = maxLinesToShow + 1;
        int i3 = i2;
        while (i3 < list.size()) {
            ArrayList arrayList = new ArrayList();
            int i4 = i3;
            while (true) {
                i = i3 + maxLinesToShow;
                if (i4 < Math.min(list.size(), i)) {
                    arrayList.add(list.get(i4));
                    i4++;
                }
            }
            this.supplementalElements.add(arrayList);
            i3 = i;
        }
        return Math.min(i2, list.size());
    }

    public List<View> bind(VerticalContainerElement verticalContainerElement, boolean z, boolean z2) {
        return bind(verticalContainerElement, z, z2, null);
    }

    public List<View> bind(VerticalContainerElement verticalContainerElement, boolean z, boolean z2, SwipeablePageView swipeablePageView) {
        int size = verticalContainerElement.elements() != null ? verticalContainerElement.elements().size() : 0;
        if (z && verticalContainerElement.canSpillOverToNextSection().booleanValue()) {
            size = breakVerticalContainerIntoMultipleSectionsForStage(verticalContainerElement.elements(), z2);
        }
        if (swipeablePageView != null && (swipeablePageView instanceof StageSwipeableSectionsView)) {
            this.swipeablePageCallback = (StageSwipeableSectionsView) swipeablePageView;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            Element element = verticalContainerElement.elements().get(i);
            if (element instanceof LabelElement) {
                arrayList.add(label((LabelElement) element, z, z2));
                if (this.shouldUpdatePreservedElements) {
                    this.preservedElements.add(element);
                }
            } else if (element instanceof ClickableTextElement) {
                arrayList.add(clickableText((ClickableTextElement) element, verticalContainerElement.canSpillOverToNextSection().booleanValue(), z, z2, this.swipeablePageCallback));
            } else if (element instanceof ThumbnailElement) {
                arrayList.add(thumbnail((ThumbnailElement) element, z, this.swipeablePageCallback));
            } else if (element instanceof SeparatorElement) {
                arrayList.add(separator());
            }
        }
        return arrayList;
    }

    private LabelView label(LabelElement labelElement, boolean z, boolean z2) {
        LabelView labelView = new LabelView(this.context, z, z2);
        labelView.setText(labelElement.text(), z);
        labelView.setIcons(labelElement.icons(), z, (int) this.styleSheet.getLabelFontSizeForStage());
        labelView.setMaxWidth(getLabelMaxWidth(labelView.getIcons()));
        if (z) {
            labelView.overrideForStage(this.styleSheet.getLabelFontSizeForStage(), this.styleSheet.getLabelLineHeightForStage());
        }
        return labelView;
    }

    private ClickableTextView clickableText(ClickableTextElement clickableTextElement, boolean z, boolean z2, boolean z3, SwipeablePageCallback swipeablePageCallback) {
        ClickableTextView clickableTextView = new ClickableTextView(this.ownerId, this.context, this.methodsDispatcher, clickableTextElement.style());
        if (!z2) {
            clickableTextView.setup();
            clickableTextView.setText(clickableTextElement.text(), clickableTextElement.onItemSelected());
            clickableTextView.setMaxWidth(this.maxWidth);
            if (z && clickableTextElement.maxLines() != null && clickableTextView.getLineCount() > clickableTextElement.maxLines().intValue()) {
                List<String> listSplitIntoParts = splitIntoParts(clickableTextElement.text(), clickableTextView.getLineCount(), clickableTextElement.maxLines().intValue());
                int i = 0;
                while (i < listSplitIntoParts.size()) {
                    if (i == 0) {
                        clickableTextView.setText(listSplitIntoParts.get(i) + ELLIPSES, clickableTextElement.onItemSelected());
                    } else {
                        String str = ELLIPSES + listSplitIntoParts.get(i);
                        if (i != listSplitIntoParts.size() - 1) {
                            str = str + ELLIPSES;
                        }
                        this.supplementalElements.add(Collections.singletonList(ImmutableClickableTextElement.builder().text(str).style(clickableTextElement.style()).maxLines(clickableTextElement.maxLines()).link(i == listSplitIntoParts.size() + (-1) ? clickableTextElement.link() : null).onItemSelected(clickableTextElement.onItemSelected()).build()));
                    }
                    i++;
                }
            } else {
                clickableTextView.setLink((IconLinkElement) clickableTextElement.link());
                clickableTextView.setMaxLines(clickableTextElement.maxLines());
            }
        } else {
            clickableTextView.setupForStage(this.styleSheet.getClickableTextFontSizeForStage(clickableTextElement), this.styleSheet.getClickableTextLineHeightForStage(clickableTextElement));
            clickableTextView.setTextForStage(clickableTextElement.text(), clickableTextElement.onItemSelected(), (IconLinkElement) clickableTextElement.link(), this.styleSheet.getClickableTextMaxChars(clickableTextElement, z3), z3, clickableTextElement.style(), swipeablePageCallback);
            clickableTextView.setMaxWidthForStage(this.maxWidth);
        }
        return clickableTextView;
    }

    private ThumbnailView thumbnail(ThumbnailElement thumbnailElement, boolean z, SwipeablePageCallback swipeablePageCallback) {
        ThumbnailView thumbnailView = new ThumbnailView(this.ownerId, this.context, this.methodsDispatcher, z, swipeablePageCallback);
        String strThumbnailImage = thumbnailElement.thumbnailImage();
        List<Method> listOnItemSelected = thumbnailElement.onItemSelected();
        SwipeablePagesStyleSheet swipeablePagesStyleSheet = this.styleSheet;
        thumbnailView.setThumbnail(strThumbnailImage, listOnItemSelected, z ? swipeablePagesStyleSheet.getThumbnailSizeForStage() : swipeablePagesStyleSheet.getThumbnailSize());
        if (!z) {
            thumbnailView.setLink((IconLinkElement) thumbnailElement.link());
            thumbnailView.setThumbnailSize(this.maxWidth);
        } else {
            thumbnailView.overrideForStage(this.styleSheet.getThumbnailSizeForStage());
        }
        return thumbnailView;
    }

    private DividerView separator() {
        return new DividerView(this.context);
    }

    private List<String> splitIntoParts(String str, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        int numberOfParts = getNumberOfParts(i, i2);
        int length = str.length() / numberOfParts;
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        while (true) {
            if (i3 > str.length()) {
                break;
            }
            if (i3 == str.length()) {
                if (StringUtils.isNotEmpty(sb)) {
                    if (arrayList.size() == numberOfParts) {
                        arrayList.set(arrayList.size() - 1, ((String) arrayList.get(arrayList.size() - 1)) + ((Object) sb));
                    } else {
                        arrayList.add(sb.toString());
                    }
                }
            } else {
                sb.append(str.charAt(i3));
                if (sb.length() >= length && !Character.isLetterOrDigit(str.charAt(i3))) {
                    if (sb.charAt(sb.length() - 1) == ' ') {
                        sb.setLength(sb.length() - 1);
                    }
                    arrayList.add(sb.toString());
                    sb = new StringBuilder();
                }
                i3++;
            }
        }
        return arrayList;
    }

    private int getNumberOfParts(int i, int i2) {
        if (i % i2 == 0) {
            return i / i2;
        }
        return (i / i2) + 1;
    }

    private int getLabelMaxWidth(LinearLayout linearLayout) {
        if (linearLayout.getChildCount() == 0) {
            return this.maxWidth;
        }
        linearLayout.measure(0, 0);
        return this.maxWidth - linearLayout.getMeasuredWidth();
    }
}
