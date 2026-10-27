package com.amazon.ui.foundations.styles;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PopularityWidgetStyle.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u000b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0002¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\r\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0006R\u0017\u0010\u0013\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0004\u001a\u0004\b\u0014\u0010\u0006¨\u0006\u0017"}, d2 = {"Lcom/amazon/ui/foundations/styles/PopularityWidgetStyle;", "", "", "totalBarCount", "I", "getTotalBarCount", "()I", "Lcom/amazon/ui/foundations/styles/PopularityBarStyle;", "popularityBarStyle", "Lcom/amazon/ui/foundations/styles/PopularityBarStyle;", "getPopularityBarStyle", "()Lcom/amazon/ui/foundations/styles/PopularityBarStyle;", "", "barGutter", "Ljava/lang/Float;", "getBarGutter", "()Ljava/lang/Float;", "inactiveColor", "getInactiveColor", "activeColor", "getActiveColor", "<init>", "(ILcom/amazon/ui/foundations/styles/PopularityBarStyle;Ljava/lang/Float;II)V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final class PopularityWidgetStyle {
    private final int activeColor;
    private final Float barGutter;
    private final int inactiveColor;
    private final PopularityBarStyle popularityBarStyle;
    private final int totalBarCount;

    public PopularityWidgetStyle(int i, PopularityBarStyle popularityBarStyle, Float f, int i2, int i3) {
        Intrinsics.checkNotNullParameter(popularityBarStyle, "popularityBarStyle");
        this.totalBarCount = i;
        this.popularityBarStyle = popularityBarStyle;
        this.barGutter = f;
        this.inactiveColor = i2;
        this.activeColor = i3;
    }

    public final int getTotalBarCount() {
        return this.totalBarCount;
    }

    public /* synthetic */ PopularityWidgetStyle(int i, PopularityBarStyle popularityBarStyle, Float f, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, popularityBarStyle, (i4 & 4) != 0 ? Float.valueOf(popularityBarStyle.getWidth()) : f, i2, i3);
    }

    public final int getInactiveColor() {
        return this.inactiveColor;
    }

    public final int getActiveColor() {
        return this.activeColor;
    }
}
