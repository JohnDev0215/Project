package com.amazon.ui.foundations.styles;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: DividerStyle.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0006\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/ui/foundations/styles/DividerStyle;", "", "lineWidth", "", "color", "", "isHorizontal", "", "(FILjava/lang/Boolean;)V", "getColor", "()I", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLineWidth", "()F", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DividerStyle {
    private final int color;
    private final Boolean isHorizontal;
    private final float lineWidth;

    public DividerStyle(float f, int i, Boolean bool) {
        this.lineWidth = f;
        this.color = i;
        this.isHorizontal = bool;
    }

    public final float getLineWidth() {
        return this.lineWidth;
    }

    public final int getColor() {
        return this.color;
    }

    public /* synthetic */ DividerStyle(float f, int i, Boolean bool, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, i, (i2 & 4) != 0 ? Boolean.TRUE : bool);
    }

    /* JADX INFO: renamed from: isHorizontal, reason: from getter */
    public final Boolean getIsHorizontal() {
        return this.isHorizontal;
    }
}
