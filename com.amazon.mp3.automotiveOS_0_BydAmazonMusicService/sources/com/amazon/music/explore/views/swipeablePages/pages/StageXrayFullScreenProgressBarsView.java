package com.amazon.music.explore.views.swipeablePages.pages;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.amazon.music.explore.R;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;

/* JADX INFO: compiled from: StageXrayFullScreenProgressBarsView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 $2\u00020\u0001:\u0001$B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u000fH\u0002J[\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\u001c\u001a\u00020\u00072\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0002\u0010!J\u000e\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0007R\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006%"}, d2 = {"Lcom/amazon/music/explore/views/swipeablePages/pages/StageXrayFullScreenProgressBarsView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "currentSection", "getCurrentSection", "()I", "setCurrentSection", "(I)V", "heightOfOneProgressBar", "", "progressBars", "", "Lcom/amazon/music/explore/views/swipeablePages/pages/StageXrayFullScreenProgressBar;", "getProgressBars", "()Ljava/util/List;", "dpToPx", "dp", "setMargins", "", "view", "Landroid/view/View;", "width", "height", "left", "top", "right", "bottom", "(Landroid/view/View;IILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setProgressBars", "totalProgressBars", "Companion", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StageXrayFullScreenProgressBarsView extends LinearLayout {
    private static final float TOTAL_PROGRESS_BARS_VIEW_HEIGHT = 144.0f;
    private int currentSection;
    private float heightOfOneProgressBar;
    private final List<StageXrayFullScreenProgressBar> progressBars;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StageXrayFullScreenProgressBarsView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StageXrayFullScreenProgressBarsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ StageXrayFullScreenProgressBarsView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StageXrayFullScreenProgressBarsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.progressBars = new ArrayList();
    }

    public final int getCurrentSection() {
        return this.currentSection;
    }

    public final void setCurrentSection(int i) {
        this.currentSection = i;
    }

    public final List<StageXrayFullScreenProgressBar> getProgressBars() {
        return this.progressBars;
    }

    public final void setProgressBars(int totalProgressBars) {
        StageXrayFullScreenProgressBar stageXrayFullScreenProgressBar;
        removeAllViews();
        this.progressBars.clear();
        this.heightOfOneProgressBar = TOTAL_PROGRESS_BARS_VIEW_HEIGHT / totalProgressBars;
        int i = 1;
        if (1 > totalProgressBars) {
            return;
        }
        while (true) {
            int i2 = i + 1;
            if (i <= this.currentSection) {
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                stageXrayFullScreenProgressBar = new StageXrayFullScreenProgressBar(context, dpToPx(this.heightOfOneProgressBar), dpToPx(this.heightOfOneProgressBar));
            } else {
                Context context2 = getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "context");
                stageXrayFullScreenProgressBar = new StageXrayFullScreenProgressBar(context2, dpToPx(this.heightOfOneProgressBar), 0);
            }
            this.progressBars.add(stageXrayFullScreenProgressBar);
            addView(stageXrayFullScreenProgressBar);
            setMargins(stageXrayFullScreenProgressBar, (int) getResources().getDimension(R.dimen.stage_x_ray_full_screen_progress_bar_width), dpToPx(this.heightOfOneProgressBar), 0, 0, 0, Integer.valueOf((int) getResources().getDimension(R.dimen.stage_x_ray_full_screen_progress_bar_margin)));
            stageXrayFullScreenProgressBar.invalidate();
            if (i == totalProgressBars) {
                return;
            } else {
                i = i2;
            }
        }
    }

    private final int dpToPx(float dp) {
        return MathKt__MathJVMKt.roundToInt(dp * getResources().getDisplayMetrics().density);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setMargins(View view, int width, int height, Integer left, Integer top, Integer right, Integer bottom) {
        if (view == null) {
            return;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, width, height, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }
}
