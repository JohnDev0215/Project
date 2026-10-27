package com.android.launcher3.allapps;

import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.animation.Interpolator;
import com.android.launcher3.anim.PropertySetter;

/* JADX INFO: loaded from: classes4.dex */
public interface SearchUiManager {
    float getScrollRangeDelta(Rect insets);

    void initialize(AllAppsContainerView containerView);

    void preDispatchKeyEvent(KeyEvent keyEvent);

    void resetSearch();

    void setContentVisibility(int visibleElements, PropertySetter setter, Interpolator interpolator);
}
