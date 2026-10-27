package com.amazon.mp3.view;

import android.view.View;
import android.view.animation.AlphaAnimation;
import com.amazon.mp3.view.TooltipView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TooltipDisplayer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u0016\u0010\n\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0004J\u0006\u0010\u000b\u001a\u00020\u0002J\u0006\u0010\f\u001a\u00020\u0002J\u000e\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rR\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/view/TooltipDisplayer;", "", "", "setupClickToDimiss", "", "animationDuration", "setAnimationDuration", "Lcom/amazon/mp3/view/TooltipView$ArrowPosition;", "position", "arrowOffset", "setupPositioning", "show", "dismiss", "Landroid/view/View$OnClickListener;", "clickListener", "setClickListener", "Lcom/amazon/mp3/view/TooltipView;", "tooltipView", "Lcom/amazon/mp3/view/TooltipView;", "getTooltipView", "()Lcom/amazon/mp3/view/TooltipView;", "", "isShowing", "Z", "I", "<init>", "(Lcom/amazon/mp3/view/TooltipView;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class TooltipDisplayer {
    private int animationDuration;
    private boolean isShowing;
    private final TooltipView tooltipView;
    private static final String TAG = TooltipView.class.getSimpleName();
    private static final int ANIMATION_DURATION = 2000;

    public TooltipDisplayer(TooltipView tooltipView) {
        Intrinsics.checkNotNullParameter(tooltipView, "tooltipView");
        this.tooltipView = tooltipView;
        this.animationDuration = ANIMATION_DURATION;
        setupClickToDimiss();
    }

    public final void setAnimationDuration(int animationDuration) {
        this.animationDuration = animationDuration;
    }

    public final void setupPositioning(TooltipView.ArrowPosition position, int arrowOffset) {
        Intrinsics.checkNotNullParameter(position, "position");
        this.tooltipView.setPosition(position);
        this.tooltipView.setArrowOffset(arrowOffset);
    }

    public final void show() {
        if (this.isShowing) {
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(this.animationDuration);
        this.tooltipView.startAnimation(alphaAnimation);
        this.tooltipView.setVisibility(0);
        this.isShowing = true;
    }

    public final void dismiss() {
        if (this.isShowing) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(this.animationDuration);
            this.tooltipView.startAnimation(alphaAnimation);
            this.tooltipView.setVisibility(8);
            this.isShowing = false;
        }
    }

    public final void setClickListener(View.OnClickListener clickListener) {
        Intrinsics.checkNotNullParameter(clickListener, "clickListener");
        this.tooltipView.setOnClickListener(clickListener);
    }

    private final void setupClickToDimiss() {
        this.tooltipView.setClickable(true);
        this.tooltipView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.TooltipDisplayer$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TooltipDisplayer.m1708setupClickToDimiss$lambda0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupClickToDimiss$lambda-0, reason: not valid java name */
    public static final void m1708setupClickToDimiss$lambda0(TooltipDisplayer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }
}
