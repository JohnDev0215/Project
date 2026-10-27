package com.android.launcher3.allapps;

import android.graphics.Rect;
import android.view.View;
import android.view.animation.Interpolator;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.anim.PropertySetter;
import com.android.systemui.plugins.AllAppsRow;

/* JADX INFO: loaded from: classes4.dex */
public class PluginHeaderRow implements FloatingHeaderRow {
    private final AllAppsRow mPlugin;
    final View mView;

    @Override // com.android.launcher3.allapps.FloatingHeaderRow
    public boolean hasVisibleContent() {
        return true;
    }

    @Override // com.android.launcher3.allapps.FloatingHeaderRow
    public void setInsets(Rect insets, DeviceProfile grid) {
    }

    @Override // com.android.launcher3.allapps.FloatingHeaderRow
    public void setup(FloatingHeaderView parent, FloatingHeaderRow[] allRows, boolean tabsHidden) {
    }

    @Override // com.android.launcher3.allapps.FloatingHeaderRow
    public boolean shouldDraw() {
        return true;
    }

    PluginHeaderRow(AllAppsRow plugin, FloatingHeaderView parent) {
        this.mPlugin = plugin;
        this.mView = plugin.setup(parent);
    }

    @Override // com.android.launcher3.allapps.FloatingHeaderRow
    public int getExpectedHeight() {
        return this.mPlugin.getExpectedHeight();
    }

    @Override // com.android.launcher3.allapps.FloatingHeaderRow
    public void setContentVisibility(boolean hasHeaderExtra, boolean hasContent, PropertySetter setter, Interpolator fadeInterpolator) {
        setter.setFloat(this.mView, View.ALPHA, hasContent ? 1.0f : 0.0f, fadeInterpolator);
    }

    @Override // com.android.launcher3.allapps.FloatingHeaderRow
    public void setVerticalScroll(int scroll, boolean isScrolledOut) {
        this.mView.setVisibility(isScrolledOut ? 4 : 0);
        if (isScrolledOut) {
            return;
        }
        this.mView.setTranslationY(scroll);
    }

    @Override // com.android.launcher3.allapps.FloatingHeaderRow
    public Class<PluginHeaderRow> getTypeClass() {
        return PluginHeaderRow.class;
    }
}
