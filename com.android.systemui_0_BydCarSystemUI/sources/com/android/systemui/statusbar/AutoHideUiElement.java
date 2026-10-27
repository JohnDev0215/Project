package com.android.systemui.statusbar;

/* JADX INFO: loaded from: classes.dex */
public interface AutoHideUiElement {
    void hide();

    boolean isVisible();

    default boolean shouldHideOnTouch() {
        return true;
    }

    void synchronizeState();
}
