package com.amazon.ui.foundations.styles;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FontSize.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0004\u001a\u00020\u0002J&\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\u000b\u001a\u00020\u0002HÖ\u0001J\u0013\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/amazon/ui/foundations/styles/FontSize;", "", "", "getPadding", "getLineSpacingExtra", "textSize", "lineHeight", "copy", "(ILjava/lang/Integer;)Lcom/amazon/ui/foundations/styles/FontSize;", "", "toString", "hashCode", "other", "", "equals", "I", "getTextSize", "()I", "Ljava/lang/Integer;", "getLineHeight", "()Ljava/lang/Integer;", "<init>", "(ILjava/lang/Integer;)V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class FontSize {
    private final Integer lineHeight;
    private final int textSize;

    public static /* synthetic */ FontSize copy$default(FontSize fontSize, int i, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = fontSize.textSize;
        }
        if ((i2 & 2) != 0) {
            num = fontSize.lineHeight;
        }
        return fontSize.copy(i, num);
    }

    public final FontSize copy(int textSize, Integer lineHeight) {
        return new FontSize(textSize, lineHeight);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FontSize)) {
            return false;
        }
        FontSize fontSize = (FontSize) other;
        return this.textSize == fontSize.textSize && Intrinsics.areEqual(this.lineHeight, fontSize.lineHeight);
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.textSize) * 31;
        Integer num = this.lineHeight;
        return iHashCode + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        return "FontSize(textSize=" + this.textSize + ", lineHeight=" + this.lineHeight + ')';
    }

    public FontSize(int i, Integer num) {
        this.textSize = i;
        this.lineHeight = num;
    }

    public /* synthetic */ FontSize(int i, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? Integer.valueOf(i) : num);
    }

    public final int getTextSize() {
        return this.textSize;
    }

    public final Integer getLineHeight() {
        return this.lineHeight;
    }

    public final int getPadding() {
        Integer num = this.lineHeight;
        Intrinsics.checkNotNull(num);
        return (num.intValue() - this.textSize) / 2;
    }

    public final int getLineSpacingExtra() {
        Integer num = this.lineHeight;
        Intrinsics.checkNotNull(num);
        return num.intValue() - this.textSize;
    }
}
