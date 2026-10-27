package com.android.inputmethod.latin.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class ViewLayoutUtils {
    private ViewLayoutUtils() {
    }

    public static ViewGroup.MarginLayoutParams newLayoutParam(final ViewGroup placer, final int width, final int height) {
        if (placer instanceof FrameLayout) {
            return new FrameLayout.LayoutParams(width, height);
        }
        if (placer instanceof RelativeLayout) {
            return new RelativeLayout.LayoutParams(width, height);
        }
        Objects.requireNonNull(placer, "placer is null");
        throw new IllegalArgumentException("placer is neither FrameLayout nor RelativeLayout: " + placer.getClass().getName());
    }

    public static void placeViewAt(final View view, final int x, final int y, final int w, final int h) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.width = w;
            marginLayoutParams.height = h;
            marginLayoutParams.setMargins(x, y, 0, 0);
        }
    }

    public static void updateLayoutHeightOf(final Window window, final int layoutHeight) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (attributes == null || attributes.height == layoutHeight) {
            return;
        }
        attributes.height = layoutHeight;
        window.setAttributes(attributes);
    }

    public static void updateLayoutHeightOf(final View view, final int layoutHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null || layoutParams.height == layoutHeight) {
            return;
        }
        layoutParams.height = layoutHeight;
        view.setLayoutParams(layoutParams);
    }

    public static void updateLayoutGravityOf(final View view, final int layoutGravity) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            if (layoutParams2.gravity != layoutGravity) {
                layoutParams2.gravity = layoutGravity;
                view.setLayoutParams(layoutParams2);
                return;
            }
            return;
        }
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) layoutParams;
            if (layoutParams3.gravity != layoutGravity) {
                layoutParams3.gravity = layoutGravity;
                view.setLayoutParams(layoutParams3);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Layout parameter doesn't have gravity: " + layoutParams.getClass().getName());
    }
}
