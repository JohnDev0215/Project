package com.amazon.ui.foundations.styles;

import android.content.res.ColorStateList;
import com.amazon.ui.foundations.utils.ColorUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StatefulStyle.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001Bg\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b \u0010!J\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0004\u001a\u00020\u0002Jp\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0019\u0010\u0018R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\b\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010\u0016\u001a\u0004\b\u001b\u0010\u0018R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\n\u0010\u0016\u001a\u0004\b\u001c\u0010\u0018R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0016\u001a\u0004\b\u001d\u0010\u0018R\u0019\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\f\u0010\u0016\u001a\u0004\b\u001e\u0010\u0018R\u0019\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\r\u0010\u0016\u001a\u0004\b\u001f\u0010\u0018¨\u0006\""}, d2 = {"Lcom/amazon/ui/foundations/styles/StatefulStyle;", "", "Landroid/content/res/ColorStateList;", "getColorStateList", "getBackgroundColorStateList", "", "color", "pressedColor", "focusedColor", "disabledColor", "backgroundColor", "pressedBackgroundColor", "focusedBackgroundColor", "disabledBackgroundColor", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/amazon/ui/foundations/styles/StatefulStyle;", "", "toString", "hashCode", "other", "", "equals", "Ljava/lang/Integer;", "getColor", "()Ljava/lang/Integer;", "getPressedColor", "getFocusedColor", "getDisabledColor", "getBackgroundColor", "getPressedBackgroundColor", "getFocusedBackgroundColor", "getDisabledBackgroundColor", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class StatefulStyle {
    private final Integer backgroundColor;
    private final Integer color;
    private final Integer disabledBackgroundColor;
    private final Integer disabledColor;
    private final Integer focusedBackgroundColor;
    private final Integer focusedColor;
    private final Integer pressedBackgroundColor;
    private final Integer pressedColor;

    public StatefulStyle() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    public final StatefulStyle copy(Integer color, Integer pressedColor, Integer focusedColor, Integer disabledColor, Integer backgroundColor, Integer pressedBackgroundColor, Integer focusedBackgroundColor, Integer disabledBackgroundColor) {
        return new StatefulStyle(color, pressedColor, focusedColor, disabledColor, backgroundColor, pressedBackgroundColor, focusedBackgroundColor, disabledBackgroundColor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StatefulStyle)) {
            return false;
        }
        StatefulStyle statefulStyle = (StatefulStyle) other;
        return Intrinsics.areEqual(this.color, statefulStyle.color) && Intrinsics.areEqual(this.pressedColor, statefulStyle.pressedColor) && Intrinsics.areEqual(this.focusedColor, statefulStyle.focusedColor) && Intrinsics.areEqual(this.disabledColor, statefulStyle.disabledColor) && Intrinsics.areEqual(this.backgroundColor, statefulStyle.backgroundColor) && Intrinsics.areEqual(this.pressedBackgroundColor, statefulStyle.pressedBackgroundColor) && Intrinsics.areEqual(this.focusedBackgroundColor, statefulStyle.focusedBackgroundColor) && Intrinsics.areEqual(this.disabledBackgroundColor, statefulStyle.disabledBackgroundColor);
    }

    public int hashCode() {
        Integer num = this.color;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.pressedColor;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.focusedColor;
        int iHashCode3 = (iHashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.disabledColor;
        int iHashCode4 = (iHashCode3 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.backgroundColor;
        int iHashCode5 = (iHashCode4 + (num5 == null ? 0 : num5.hashCode())) * 31;
        Integer num6 = this.pressedBackgroundColor;
        int iHashCode6 = (iHashCode5 + (num6 == null ? 0 : num6.hashCode())) * 31;
        Integer num7 = this.focusedBackgroundColor;
        int iHashCode7 = (iHashCode6 + (num7 == null ? 0 : num7.hashCode())) * 31;
        Integer num8 = this.disabledBackgroundColor;
        return iHashCode7 + (num8 != null ? num8.hashCode() : 0);
    }

    public String toString() {
        return "StatefulStyle(color=" + this.color + ", pressedColor=" + this.pressedColor + ", focusedColor=" + this.focusedColor + ", disabledColor=" + this.disabledColor + ", backgroundColor=" + this.backgroundColor + ", pressedBackgroundColor=" + this.pressedBackgroundColor + ", focusedBackgroundColor=" + this.focusedBackgroundColor + ", disabledBackgroundColor=" + this.disabledBackgroundColor + ')';
    }

    public StatefulStyle(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8) {
        this.color = num;
        this.pressedColor = num2;
        this.focusedColor = num3;
        this.disabledColor = num4;
        this.backgroundColor = num5;
        this.pressedBackgroundColor = num6;
        this.focusedBackgroundColor = num7;
        this.disabledBackgroundColor = num8;
    }

    public /* synthetic */ StatefulStyle(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : num3, (i & 8) != 0 ? null : num4, (i & 16) != 0 ? null : num5, (i & 32) != 0 ? null : num6, (i & 64) != 0 ? null : num7, (i & 128) == 0 ? num8 : null);
    }

    public final Integer getColor() {
        return this.color;
    }

    public final Integer getPressedColor() {
        return this.pressedColor;
    }

    public final Integer getFocusedColor() {
        return this.focusedColor;
    }

    public final Integer getDisabledColor() {
        return this.disabledColor;
    }

    public final Integer getBackgroundColor() {
        return this.backgroundColor;
    }

    public final Integer getPressedBackgroundColor() {
        return this.pressedBackgroundColor;
    }

    public final Integer getFocusedBackgroundColor() {
        return this.focusedBackgroundColor;
    }

    public final Integer getDisabledBackgroundColor() {
        return this.disabledBackgroundColor;
    }

    public final ColorStateList getColorStateList() {
        ColorStateList colorStateList;
        Integer num = this.color;
        if (num == null) {
            colorStateList = null;
        } else {
            num.intValue();
            colorStateList = ColorUtil.INSTANCE.getColorStateList(getColor().intValue(), getPressedColor(), getFocusedColor(), getDisabledColor());
        }
        return colorStateList == null ? ColorUtil.getColorStateList$default(ColorUtil.INSTANCE, 0, null, null, null, 14, null) : colorStateList;
    }

    public final ColorStateList getBackgroundColorStateList() {
        Integer num = this.backgroundColor;
        ColorStateList colorStateList = num == null ? null : ColorUtil.INSTANCE.getColorStateList(num.intValue(), getPressedBackgroundColor(), getFocusedBackgroundColor(), getDisabledBackgroundColor());
        return colorStateList == null ? ColorUtil.getColorStateList$default(ColorUtil.INSTANCE, 0, null, null, null, 14, null) : colorStateList;
    }
}
