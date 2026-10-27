package com.amazon.ui.foundations.styles;

import android.graphics.Typeface;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FontStyle.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u001d\u0010\u001eJ:\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\r\u001a\u00020\fHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\u0010\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, d2 = {"Lcom/amazon/ui/foundations/styles/FontStyle;", "", "Lcom/amazon/ui/foundations/styles/FontSize;", "fontSize", "Landroid/graphics/Typeface;", "typeface", "", "color", "", "allCaps", "copy", "(Lcom/amazon/ui/foundations/styles/FontSize;Landroid/graphics/Typeface;ILjava/lang/Boolean;)Lcom/amazon/ui/foundations/styles/FontStyle;", "", "toString", "hashCode", "other", "equals", "Lcom/amazon/ui/foundations/styles/FontSize;", "getFontSize", "()Lcom/amazon/ui/foundations/styles/FontSize;", "Landroid/graphics/Typeface;", "getTypeface", "()Landroid/graphics/Typeface;", "I", "getColor", "()I", "Ljava/lang/Boolean;", "getAllCaps", "()Ljava/lang/Boolean;", "<init>", "(Lcom/amazon/ui/foundations/styles/FontSize;Landroid/graphics/Typeface;ILjava/lang/Boolean;)V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class FontStyle {
    private final Boolean allCaps;
    private final int color;
    private final FontSize fontSize;
    private final Typeface typeface;

    public static /* synthetic */ FontStyle copy$default(FontStyle fontStyle, FontSize fontSize, Typeface typeface, int i, Boolean bool, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            fontSize = fontStyle.fontSize;
        }
        if ((i2 & 2) != 0) {
            typeface = fontStyle.typeface;
        }
        if ((i2 & 4) != 0) {
            i = fontStyle.color;
        }
        if ((i2 & 8) != 0) {
            bool = fontStyle.allCaps;
        }
        return fontStyle.copy(fontSize, typeface, i, bool);
    }

    public final FontStyle copy(FontSize fontSize, Typeface typeface, int color, Boolean allCaps) {
        Intrinsics.checkNotNullParameter(fontSize, "fontSize");
        Intrinsics.checkNotNullParameter(typeface, "typeface");
        return new FontStyle(fontSize, typeface, color, allCaps);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FontStyle)) {
            return false;
        }
        FontStyle fontStyle = (FontStyle) other;
        return Intrinsics.areEqual(this.fontSize, fontStyle.fontSize) && Intrinsics.areEqual(this.typeface, fontStyle.typeface) && this.color == fontStyle.color && Intrinsics.areEqual(this.allCaps, fontStyle.allCaps);
    }

    public int hashCode() {
        int iHashCode = ((((this.fontSize.hashCode() * 31) + this.typeface.hashCode()) * 31) + Integer.hashCode(this.color)) * 31;
        Boolean bool = this.allCaps;
        return iHashCode + (bool == null ? 0 : bool.hashCode());
    }

    public String toString() {
        return "FontStyle(fontSize=" + this.fontSize + ", typeface=" + this.typeface + ", color=" + this.color + ", allCaps=" + this.allCaps + ')';
    }

    public FontStyle(FontSize fontSize, Typeface typeface, int i, Boolean bool) {
        Intrinsics.checkNotNullParameter(fontSize, "fontSize");
        Intrinsics.checkNotNullParameter(typeface, "typeface");
        this.fontSize = fontSize;
        this.typeface = typeface;
        this.color = i;
        this.allCaps = bool;
    }

    public final FontSize getFontSize() {
        return this.fontSize;
    }

    public final Typeface getTypeface() {
        return this.typeface;
    }

    public final int getColor() {
        return this.color;
    }

    public /* synthetic */ FontStyle(FontSize fontSize, Typeface typeface, int i, Boolean bool, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(fontSize, typeface, i, (i2 & 8) != 0 ? Boolean.FALSE : bool);
    }

    public final Boolean getAllCaps() {
        return this.allCaps;
    }
}
