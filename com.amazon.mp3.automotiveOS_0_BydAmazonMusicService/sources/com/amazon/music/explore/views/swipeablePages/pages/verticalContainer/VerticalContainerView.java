package com.amazon.music.explore.views.swipeablePages.pages.verticalContainer;

import CoreInterface.v1_0.Method;
import Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.amazon.music.explore.ExploreStyleSheet;
import com.amazon.music.explore.R;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements.ClickableTextView;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class VerticalContainerView extends LinearLayout {
    private String backgroundImage;
    private boolean hasBeenViewed;
    public List<Method> onLongPressedMethods;
    public List<Method> onPreviewClickedMethods;
    public List<Method> onViewedMethods;
    public List<Method> onViewedPreviewMethods;
    private boolean shouldBlurBackground;

    public VerticalContainerView(Context context) {
        super(context);
        this.onViewedMethods = new ArrayList();
        this.onViewedPreviewMethods = new ArrayList();
        this.onLongPressedMethods = new ArrayList();
        this.onPreviewClickedMethods = new ArrayList();
        this.hasBeenViewed = false;
        setOrientation(1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean hasBeenViewed() {
        return this.hasBeenViewed;
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public boolean shouldBlurBackground() {
        return this.shouldBlurBackground;
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setShouldBlurBackground(boolean z) {
        this.shouldBlurBackground = z;
    }

    public void setHasBeenViewed(boolean z) {
        this.hasBeenViewed = z;
    }

    public void addOnViewedMethods(List<Method> list) {
        this.onViewedMethods.addAll(list);
    }

    public void addOnViewedPreviewMethods(List<Method> list) {
        this.onViewedPreviewMethods.addAll(list);
    }

    public void addOnLongPressedMethods(List<Method> list) {
        this.onLongPressedMethods.addAll(list);
    }

    public void addOnPreviewClickedMethods(List<Method> list) {
        this.onPreviewClickedMethods.addAll(list);
    }

    public void dispatchMethods(String str, MethodsDispatcher methodsDispatcher, List<Method> list) {
        methodsDispatcher.dispatchMethods(str, list);
    }

    public void addMarginBottom() {
        int dimensionPixelSize;
        View childAt = getChildAt(getChildCount() - 1);
        if ((childAt instanceof ClickableTextView) && ((ClickableTextView) childAt).getStyle() == ClickableTextElement.Style.CONTENT) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            if (ExploreStyleSheet.isXL(getContext())) {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.spacer_giant) * 2;
            } else {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.spacer_giant);
            }
            layoutParams.bottomMargin = dimensionPixelSize;
        }
    }
}
