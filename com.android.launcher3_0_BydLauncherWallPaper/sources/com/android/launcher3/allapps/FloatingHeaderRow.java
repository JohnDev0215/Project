package com.android.launcher3.allapps;

import android.graphics.Rect;
import android.view.animation.Interpolator;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.anim.PropertySetter;

/* JADX INFO: loaded from: classes4.dex */
public interface FloatingHeaderRow {
    public static final FloatingHeaderRow[] NO_ROWS = new FloatingHeaderRow[0];

    int getExpectedHeight();

    Class<? extends FloatingHeaderRow> getTypeClass();

    boolean hasVisibleContent();

    void setContentVisibility(boolean hasHeaderExtra, boolean hasContent, PropertySetter setter, Interpolator fadeInterpolator);

    void setInsets(Rect insets, DeviceProfile grid);

    void setVerticalScroll(int scroll, boolean isScrolledOut);

    void setup(FloatingHeaderView parent, FloatingHeaderRow[] allRows, boolean tabsHidden);

    boolean shouldDraw();
}
