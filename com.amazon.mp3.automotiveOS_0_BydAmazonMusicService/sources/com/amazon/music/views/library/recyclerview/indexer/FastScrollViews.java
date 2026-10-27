package com.amazon.music.views.library.recyclerview.indexer;

import android.view.View;
import androidx.annotation.ColorInt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FastScrollViews.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\t\u001a\u00020\u0004\u0012\b\b\u0003\u0010\u000f\u001a\u00020\u0004\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR$\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u0019\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/indexer/FastScrollViews;", "", "", "toString", "", "hashCode", "other", "", "equals", "scrollHandleColor", "I", "getScrollHandleColor", "()I", "setScrollHandleColor", "(I)V", "scrollBarColor", "getScrollBarColor", "setScrollBarColor", "Landroid/view/View;", "scrollHandle", "Landroid/view/View;", "getScrollHandle", "()Landroid/view/View;", "setScrollHandle", "(Landroid/view/View;)V", "scrollBar", "getScrollBar", "setScrollBar", "<init>", "(IILandroid/view/View;Landroid/view/View;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class FastScrollViews {
    private View scrollBar;
    private int scrollBarColor;
    private View scrollHandle;
    private int scrollHandleColor;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FastScrollViews)) {
            return false;
        }
        FastScrollViews fastScrollViews = (FastScrollViews) other;
        return this.scrollHandleColor == fastScrollViews.scrollHandleColor && this.scrollBarColor == fastScrollViews.scrollBarColor && Intrinsics.areEqual(this.scrollHandle, fastScrollViews.scrollHandle) && Intrinsics.areEqual(this.scrollBar, fastScrollViews.scrollBar);
    }

    public int hashCode() {
        int iHashCode = ((Integer.hashCode(this.scrollHandleColor) * 31) + Integer.hashCode(this.scrollBarColor)) * 31;
        View view = this.scrollHandle;
        int iHashCode2 = (iHashCode + (view == null ? 0 : view.hashCode())) * 31;
        View view2 = this.scrollBar;
        return iHashCode2 + (view2 != null ? view2.hashCode() : 0);
    }

    public String toString() {
        return "FastScrollViews(scrollHandleColor=" + this.scrollHandleColor + ", scrollBarColor=" + this.scrollBarColor + ", scrollHandle=" + this.scrollHandle + ", scrollBar=" + this.scrollBar + ')';
    }

    public FastScrollViews(@ColorInt int i, @ColorInt int i2, View view, View view2) {
        this.scrollHandleColor = i;
        this.scrollBarColor = i2;
        this.scrollHandle = view;
        this.scrollBar = view2;
    }

    public /* synthetic */ FastScrollViews(int i, int i2, View view, View view2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? -1 : i, (i3 & 2) != 0 ? -1 : i2, view, view2);
    }

    public final int getScrollHandleColor() {
        return this.scrollHandleColor;
    }

    public final void setScrollHandleColor(int i) {
        this.scrollHandleColor = i;
    }

    public final int getScrollBarColor() {
        return this.scrollBarColor;
    }

    public final void setScrollBarColor(int i) {
        this.scrollBarColor = i;
    }

    public final View getScrollHandle() {
        return this.scrollHandle;
    }

    public final View getScrollBar() {
        return this.scrollBar;
    }
}
