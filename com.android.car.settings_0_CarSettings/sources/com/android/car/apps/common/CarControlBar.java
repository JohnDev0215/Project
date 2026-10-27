package com.android.car.apps.common;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes5.dex */
public interface CarControlBar {
    public static final int SLOT_EXPAND_COLLAPSE = 3;
    public static final int SLOT_LEFT = 1;
    public static final int SLOT_MAIN = 0;
    public static final int SLOT_RIGHT = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SlotPosition {
    }

    ImageButton createIconButton(Drawable drawable);

    ImageButton createIconButton(Drawable drawable, int i);

    void setView(View view, int i);

    void setViews(View[] viewArr);

    static int getSlotIndex(int slotPosition, int numColumns) {
        if (slotPosition == 0) {
            return numColumns / 2;
        }
        if (slotPosition == 1) {
            if (numColumns < 3) {
                return -1;
            }
            return (numColumns / 2) - 1;
        }
        if (slotPosition == 2) {
            if (numColumns < 2) {
                return -1;
            }
            return (numColumns / 2) + 1;
        }
        if (slotPosition == 3) {
            return numColumns - 1;
        }
        throw new IllegalArgumentException("Unknown position: " + slotPosition);
    }
}
