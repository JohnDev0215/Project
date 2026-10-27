package com.amazon.podcast.views;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes5.dex */
public interface UiMetrics {
    void handleOnViewed();

    default void addOnViewedListener(final View view) {
        if (view == null) {
            return;
        }
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.amazon.podcast.views.UiMetrics.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                if (!UiMetrics.isOnScreen(view)) {
                    return true;
                }
                UiMetrics.this.handleOnViewed();
                return true;
            }
        });
    }

    static boolean isOnScreen(View view) {
        if (view == null) {
            return false;
        }
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        Rect rect = new Rect();
        Rect rect2 = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.getGlobalVisibleRect(rect);
        return rect.intersect(rect2);
    }
}
