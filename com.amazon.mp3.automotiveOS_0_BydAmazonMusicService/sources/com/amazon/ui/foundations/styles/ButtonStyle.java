package com.amazon.ui.foundations.styles;

import android.content.res.ColorStateList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ButtonStyle.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ>\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b\t\u0010\nJ\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\t\u0010\r\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0011\u001a\u0004\b\u0014\u0010\u0013R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/amazon/ui/foundations/styles/ButtonStyle;", "", "Lcom/amazon/ui/foundations/styles/StatefulStyle;", "statefulStyle", "iconStatefulStyle", "Landroid/content/res/ColorStateList;", "innerBorderColor", "", "innerBorderSize", "copy", "(Lcom/amazon/ui/foundations/styles/StatefulStyle;Lcom/amazon/ui/foundations/styles/StatefulStyle;Landroid/content/res/ColorStateList;Ljava/lang/Integer;)Lcom/amazon/ui/foundations/styles/ButtonStyle;", "", "toString", "hashCode", "other", "", "equals", "Lcom/amazon/ui/foundations/styles/StatefulStyle;", "getStatefulStyle", "()Lcom/amazon/ui/foundations/styles/StatefulStyle;", "getIconStatefulStyle", "Landroid/content/res/ColorStateList;", "getInnerBorderColor", "()Landroid/content/res/ColorStateList;", "Ljava/lang/Integer;", "getInnerBorderSize", "()Ljava/lang/Integer;", "<init>", "(Lcom/amazon/ui/foundations/styles/StatefulStyle;Lcom/amazon/ui/foundations/styles/StatefulStyle;Landroid/content/res/ColorStateList;Ljava/lang/Integer;)V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ButtonStyle {
    private final StatefulStyle iconStatefulStyle;
    private final ColorStateList innerBorderColor;
    private final Integer innerBorderSize;
    private final StatefulStyle statefulStyle;

    public static /* synthetic */ ButtonStyle copy$default(ButtonStyle buttonStyle, StatefulStyle statefulStyle, StatefulStyle statefulStyle2, ColorStateList colorStateList, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            statefulStyle = buttonStyle.statefulStyle;
        }
        if ((i & 2) != 0) {
            statefulStyle2 = buttonStyle.iconStatefulStyle;
        }
        if ((i & 4) != 0) {
            colorStateList = buttonStyle.innerBorderColor;
        }
        if ((i & 8) != 0) {
            num = buttonStyle.innerBorderSize;
        }
        return buttonStyle.copy(statefulStyle, statefulStyle2, colorStateList, num);
    }

    public final ButtonStyle copy(StatefulStyle statefulStyle, StatefulStyle iconStatefulStyle, ColorStateList innerBorderColor, Integer innerBorderSize) {
        Intrinsics.checkNotNullParameter(statefulStyle, "statefulStyle");
        return new ButtonStyle(statefulStyle, iconStatefulStyle, innerBorderColor, innerBorderSize);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ButtonStyle)) {
            return false;
        }
        ButtonStyle buttonStyle = (ButtonStyle) other;
        return Intrinsics.areEqual(this.statefulStyle, buttonStyle.statefulStyle) && Intrinsics.areEqual(this.iconStatefulStyle, buttonStyle.iconStatefulStyle) && Intrinsics.areEqual(this.innerBorderColor, buttonStyle.innerBorderColor) && Intrinsics.areEqual(this.innerBorderSize, buttonStyle.innerBorderSize);
    }

    public int hashCode() {
        int iHashCode = this.statefulStyle.hashCode() * 31;
        StatefulStyle statefulStyle = this.iconStatefulStyle;
        int iHashCode2 = (iHashCode + (statefulStyle == null ? 0 : statefulStyle.hashCode())) * 31;
        ColorStateList colorStateList = this.innerBorderColor;
        int iHashCode3 = (iHashCode2 + (colorStateList == null ? 0 : colorStateList.hashCode())) * 31;
        Integer num = this.innerBorderSize;
        return iHashCode3 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "ButtonStyle(statefulStyle=" + this.statefulStyle + ", iconStatefulStyle=" + this.iconStatefulStyle + ", innerBorderColor=" + this.innerBorderColor + ", innerBorderSize=" + this.innerBorderSize + ')';
    }

    public ButtonStyle(StatefulStyle statefulStyle, StatefulStyle statefulStyle2, ColorStateList colorStateList, Integer num) {
        Intrinsics.checkNotNullParameter(statefulStyle, "statefulStyle");
        this.statefulStyle = statefulStyle;
        this.iconStatefulStyle = statefulStyle2;
        this.innerBorderColor = colorStateList;
        this.innerBorderSize = num;
    }

    public /* synthetic */ ButtonStyle(StatefulStyle statefulStyle, StatefulStyle statefulStyle2, ColorStateList colorStateList, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(statefulStyle, (i & 2) != 0 ? null : statefulStyle2, (i & 4) != 0 ? null : colorStateList, (i & 8) != 0 ? null : num);
    }

    public final StatefulStyle getStatefulStyle() {
        return this.statefulStyle;
    }

    public final StatefulStyle getIconStatefulStyle() {
        return this.iconStatefulStyle;
    }

    public final ColorStateList getInnerBorderColor() {
        return this.innerBorderColor;
    }

    public final Integer getInnerBorderSize() {
        return this.innerBorderSize;
    }
}
