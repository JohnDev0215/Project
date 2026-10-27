package com.amazon.ui.foundations.styles;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageStyle.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/ui/foundations/styles/ImageStyle;", "", "", "toString", "", "hashCode", "other", "", "equals", "backgroundColor", "Ljava/lang/Integer;", "getBackgroundColor", "()Ljava/lang/Integer;", "Lcom/amazon/ui/foundations/styles/OutlineStyle;", "outlineStyle", "Lcom/amazon/ui/foundations/styles/OutlineStyle;", "getOutlineStyle", "()Lcom/amazon/ui/foundations/styles/OutlineStyle;", "Lcom/amazon/ui/foundations/styles/ShadowStyle;", "shadowStyle", "Lcom/amazon/ui/foundations/styles/ShadowStyle;", "getShadowStyle", "()Lcom/amazon/ui/foundations/styles/ShadowStyle;", "<init>", "(Ljava/lang/Integer;Lcom/amazon/ui/foundations/styles/OutlineStyle;Lcom/amazon/ui/foundations/styles/ShadowStyle;)V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ImageStyle {
    private final Integer backgroundColor;
    private final OutlineStyle outlineStyle;
    private final ShadowStyle shadowStyle;

    public ImageStyle() {
        this(null, null, null, 7, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageStyle)) {
            return false;
        }
        ImageStyle imageStyle = (ImageStyle) other;
        return Intrinsics.areEqual(this.backgroundColor, imageStyle.backgroundColor) && Intrinsics.areEqual(this.outlineStyle, imageStyle.outlineStyle) && Intrinsics.areEqual(this.shadowStyle, imageStyle.shadowStyle);
    }

    public int hashCode() {
        Integer num = this.backgroundColor;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        OutlineStyle outlineStyle = this.outlineStyle;
        int iHashCode2 = (iHashCode + (outlineStyle == null ? 0 : outlineStyle.hashCode())) * 31;
        ShadowStyle shadowStyle = this.shadowStyle;
        return iHashCode2 + (shadowStyle != null ? shadowStyle.hashCode() : 0);
    }

    public String toString() {
        return "ImageStyle(backgroundColor=" + this.backgroundColor + ", outlineStyle=" + this.outlineStyle + ", shadowStyle=" + this.shadowStyle + ')';
    }

    public ImageStyle(Integer num, OutlineStyle outlineStyle, ShadowStyle shadowStyle) {
        this.backgroundColor = num;
        this.outlineStyle = outlineStyle;
        this.shadowStyle = shadowStyle;
    }

    public /* synthetic */ ImageStyle(Integer num, OutlineStyle outlineStyle, ShadowStyle shadowStyle, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : outlineStyle, (i & 4) != 0 ? null : shadowStyle);
    }

    public final OutlineStyle getOutlineStyle() {
        return this.outlineStyle;
    }

    public final ShadowStyle getShadowStyle() {
        return this.shadowStyle;
    }
}
