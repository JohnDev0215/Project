package com.android.internal.telephony;

import android.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class UserIcons {
    private static final int[] USER_ICON_COLORS = {R.color.global_actions_container_background, R.color.group_button_dialog_focused_holo_dark, R.color.group_button_dialog_focused_holo_light, R.color.group_button_dialog_pressed_holo_dark, R.color.group_button_dialog_pressed_holo_light, R.color.highlighted_text_dark, R.color.highlighted_text_holo_dark, R.color.highlighted_text_holo_light};

    public static Bitmap convertToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static Drawable getDefaultUserIcon(Resources resources, int i, boolean z) {
        int i2 = z ? R.color.highlighted_text_material : R.color.highlighted_text_light;
        if (i != -10000) {
            int[] iArr = USER_ICON_COLORS;
            i2 = iArr[i % iArr.length];
        }
        Drawable drawableMutate = resources.getDrawable(R.drawable.divider_vertical_holo_light, null).mutate();
        drawableMutate.setColorFilter(resources.getColor(i2, null), PorterDuff.Mode.SRC_IN);
        drawableMutate.setBounds(0, 0, drawableMutate.getIntrinsicWidth(), drawableMutate.getIntrinsicHeight());
        return drawableMutate;
    }
}
