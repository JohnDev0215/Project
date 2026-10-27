package com.amazon.ui.foundations.utils;

import android.R;
import android.content.res.ColorStateList;
import kotlin.Metadata;

/* JADX INFO: compiled from: ColorUtil.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J7\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000eR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/amazon/ui/foundations/utils/ColorUtil;", "", "()V", "states", "", "", "[[I", "getColorStateList", "Landroid/content/res/ColorStateList;", "color", "", "pressedColor", "focusedColor", "disabledColor", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Landroid/content/res/ColorStateList;", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ColorUtil {
    public static final ColorUtil INSTANCE = new ColorUtil();
    private static final int[][] states = {new int[]{R.attr.state_focused, -16842919}, new int[]{R.attr.state_pressed}, new int[]{R.attr.state_enabled}, new int[]{-16842910}, new int[0]};

    private ColorUtil() {
    }

    public static /* synthetic */ ColorStateList getColorStateList$default(ColorUtil colorUtil, int i, Integer num, Integer num2, Integer num3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            num = null;
        }
        if ((i2 & 4) != 0) {
            num2 = null;
        }
        if ((i2 & 8) != 0) {
            num3 = null;
        }
        return colorUtil.getColorStateList(i, num, num2, num3);
    }

    public final ColorStateList getColorStateList(int color, Integer pressedColor, Integer focusedColor, Integer disabledColor) {
        int[] iArr = new int[5];
        iArr[0] = focusedColor == null ? color : focusedColor.intValue();
        iArr[1] = pressedColor == null ? color : pressedColor.intValue();
        iArr[2] = color;
        iArr[3] = disabledColor == null ? color : disabledColor.intValue();
        iArr[4] = color;
        return new ColorStateList(states, iArr);
    }
}
