package com.amazon.ui.foundations.styles;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PopularityBarStyle.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\r\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/ui/foundations/styles/PopularityBarStyle;", "", "", "width", "F", "getWidth", "()F", "height", "getHeight", "radius", "Ljava/lang/Float;", "getRadius", "()Ljava/lang/Float;", "<init>", "(FFLjava/lang/Float;)V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final class PopularityBarStyle {
    private final float height;
    private final Float radius;
    private final float width;

    public PopularityBarStyle(float f, float f2, Float f3) {
        this.width = f;
        this.height = f2;
        this.radius = f3;
    }

    public final float getWidth() {
        return this.width;
    }

    public final float getHeight() {
        return this.height;
    }

    public /* synthetic */ PopularityBarStyle(float f, float f2, Float f3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, (i & 4) != 0 ? Float.valueOf(f / 2) : f3);
    }
}
