package com.amazon.ui.foundations.styles;

import com.BV.LinearGradient.LinearGradientManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GradientStyle.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0005\u001a\u0004\b\n\u0010\u0007R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\f\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/ui/foundations/styles/GradientStyle;", "", "", "", LinearGradientManager.PROP_COLORS, "Ljava/util/List;", "getColors", "()Ljava/util/List;", "", "positions", "getPositions", "", "isHorizontal", "Z", "()Z", "<init>", "(Ljava/util/List;Ljava/util/List;Z)V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final class GradientStyle {
    private final List<Integer> colors;
    private final boolean isHorizontal;
    private final List<Float> positions;

    public GradientStyle(List<Integer> colors, List<Float> positions, boolean z) {
        Intrinsics.checkNotNullParameter(colors, "colors");
        Intrinsics.checkNotNullParameter(positions, "positions");
        this.colors = colors;
        this.positions = positions;
        this.isHorizontal = z;
    }

    public final List<Integer> getColors() {
        return this.colors;
    }

    public final List<Float> getPositions() {
        return this.positions;
    }
}
