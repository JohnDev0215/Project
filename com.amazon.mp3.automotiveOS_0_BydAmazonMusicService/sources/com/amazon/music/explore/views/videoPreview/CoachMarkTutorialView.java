package com.amazon.music.explore.views.videoPreview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.amazon.music.explore.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CoachMarkTutorialView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 02\u00020\u0001:\u00010B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020,H\u0002J\b\u0010-\u001a\u00020,H\u0002J\b\u0010.\u001a\u00020,H\u0002J\b\u0010/\u001a\u00020,H\u0002R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u001a8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u001f\u001a\u00020\u001a8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\u001e\u0010\"\u001a\u00020\u001a8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001c\"\u0004\b$\u0010\u001eR\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/amazon/music/explore/views/videoPreview/CoachMarkTutorialView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "acceptButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getAcceptButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "setAcceptButton", "(Lcom/amazon/ui/foundations/views/BaseButton;)V", "coachMarkCircleView", "Lcom/amazon/music/explore/views/videoPreview/CoachMarkCircleView;", "getCoachMarkCircleView", "()Lcom/amazon/music/explore/views/videoPreview/CoachMarkCircleView;", "setCoachMarkCircleView", "(Lcom/amazon/music/explore/views/videoPreview/CoachMarkCircleView;)V", "coachMarkTutorialArrow", "Landroid/widget/ImageView;", "getCoachMarkTutorialArrow", "()Landroid/widget/ImageView;", "setCoachMarkTutorialArrow", "(Landroid/widget/ImageView;)V", "coachMarkTutorialPrimaryText", "Landroid/widget/TextView;", "getCoachMarkTutorialPrimaryText", "()Landroid/widget/TextView;", "setCoachMarkTutorialPrimaryText", "(Landroid/widget/TextView;)V", "coachMarkTutorialSecondaryText", "getCoachMarkTutorialSecondaryText", "setCoachMarkTutorialSecondaryText", "coachMarkTutorialTertiaryText", "getCoachMarkTutorialTertiaryText", "setCoachMarkTutorialTertiaryText", "screenWidth", "", "onTouchEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "setArrowViewMargin", "", "setButtonStyle", "setCircleViewMargin", "setTextStyle", "Companion", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CoachMarkTutorialView extends RelativeLayout {
    private static final double EXPANDED_LARGE_CIRCLE_RATIO = 0.13526d;

    @ViewFinder.AView
    public BaseButton acceptButton;

    @ViewFinder.AView
    public CoachMarkCircleView coachMarkCircleView;

    @ViewFinder.AView
    public ImageView coachMarkTutorialArrow;

    @ViewFinder.AView
    public TextView coachMarkTutorialPrimaryText;

    @ViewFinder.AView
    public TextView coachMarkTutorialSecondaryText;

    @ViewFinder.AView
    public TextView coachMarkTutorialTertiaryText;
    private float screenWidth;
    private StyleSheet styleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoachMarkTutorialView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        ViewFinderKt.findViews$default(ViewGroupKt.inflateThis(this, R.layout.coach_mark_tutorial_layout), (Object) null, 1, (Object) null);
        ScreenUtils.Companion companion = ScreenUtils.INSTANCE;
        Intrinsics.checkNotNull(context);
        this.screenWidth = companion.getScreenWidth(context);
        setCircleViewMargin();
        setArrowViewMargin();
        setTextStyle();
        setButtonStyle();
    }

    public final CoachMarkCircleView getCoachMarkCircleView() {
        CoachMarkCircleView coachMarkCircleView = this.coachMarkCircleView;
        if (coachMarkCircleView != null) {
            return coachMarkCircleView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("coachMarkCircleView");
        return null;
    }

    public final void setCoachMarkCircleView(CoachMarkCircleView coachMarkCircleView) {
        Intrinsics.checkNotNullParameter(coachMarkCircleView, "<set-?>");
        this.coachMarkCircleView = coachMarkCircleView;
    }

    public final ImageView getCoachMarkTutorialArrow() {
        ImageView imageView = this.coachMarkTutorialArrow;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("coachMarkTutorialArrow");
        return null;
    }

    public final void setCoachMarkTutorialArrow(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.coachMarkTutorialArrow = imageView;
    }

    public final TextView getCoachMarkTutorialPrimaryText() {
        TextView textView = this.coachMarkTutorialPrimaryText;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("coachMarkTutorialPrimaryText");
        return null;
    }

    public final void setCoachMarkTutorialPrimaryText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.coachMarkTutorialPrimaryText = textView;
    }

    public final TextView getCoachMarkTutorialSecondaryText() {
        TextView textView = this.coachMarkTutorialSecondaryText;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("coachMarkTutorialSecondaryText");
        return null;
    }

    public final void setCoachMarkTutorialSecondaryText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.coachMarkTutorialSecondaryText = textView;
    }

    public final TextView getCoachMarkTutorialTertiaryText() {
        TextView textView = this.coachMarkTutorialTertiaryText;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("coachMarkTutorialTertiaryText");
        return null;
    }

    public final void setCoachMarkTutorialTertiaryText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.coachMarkTutorialTertiaryText = textView;
    }

    public final BaseButton getAcceptButton() {
        BaseButton baseButton = this.acceptButton;
        if (baseButton != null) {
            return baseButton;
        }
        Intrinsics.throwUninitializedPropertyAccessException("acceptButton");
        return null;
    }

    public final void setAcceptButton(BaseButton baseButton) {
        Intrinsics.checkNotNullParameter(baseButton, "<set-?>");
        this.acceptButton = baseButton;
    }

    private final void setCircleViewMargin() {
        float f = this.screenWidth;
        double d = (((double) f) - (((double) f) * EXPANDED_LARGE_CIRCLE_RATIO)) / ((double) 2);
        int dimensionPixelSize = (getResources().getDimensionPixelSize(R.dimen.spacer_epic) * 2) + getResources().getDimensionPixelSize(R.dimen.spacer_giant);
        ViewGroup.LayoutParams layoutParams = getCoachMarkCircleView().getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        ((ViewGroup.MarginLayoutParams) layoutParams).setMargins((int) d, dimensionPixelSize, 0, 0);
    }

    private final void setTextStyle() {
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_bold));
        Typeface typefaceCreateFromAsset2 = Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_regular));
        getCoachMarkTutorialPrimaryText().setTypeface(typefaceCreateFromAsset);
        getCoachMarkTutorialSecondaryText().setTypeface(typefaceCreateFromAsset2);
        getCoachMarkTutorialTertiaryText().setTypeface(typefaceCreateFromAsset2);
    }

    private final void setArrowViewMargin() {
        float f = this.screenWidth;
        double d = ((double) (f / 2)) + ((((double) f) * EXPANDED_LARGE_CIRCLE_RATIO) / ((double) 2));
        double dimensionPixelSize = (((double) f) * EXPANDED_LARGE_CIRCLE_RATIO) + ((double) getResources().getDimensionPixelSize(R.dimen.spacer_medium));
        Resources resources = getResources();
        int i = R.dimen.spacer_giant;
        double dimensionPixelSize2 = dimensionPixelSize + ((double) resources.getDimensionPixelSize(i)) + ((double) (getResources().getDimensionPixelSize(R.dimen.spacer_epic) * 2));
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(i) * 2;
        ViewGroup.LayoutParams layoutParams = getCoachMarkTutorialArrow().getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        ((ViewGroup.MarginLayoutParams) layoutParams).setMargins((int) d, (int) dimensionPixelSize2, 0, dimensionPixelSize3);
    }

    private final void setButtonStyle() {
        StyleSheet styleSheet = this.styleSheet;
        BaseButton.StyleBuilder buttonBuilder = styleSheet == null ? null : styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID);
        Intrinsics.checkNotNull(buttonBuilder);
        String string = getResources().getString(R.string.coach_mark_button_text);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…g.coach_mark_button_text)");
        buttonBuilder.withText(string).applyStyle(getAcceptButton());
        getAcceptButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.videoPreview.CoachMarkTutorialView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CoachMarkTutorialView.m2005setButtonStyle$lambda0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setButtonStyle$lambda-0, reason: not valid java name */
    public static final void m2005setButtonStyle$lambda0(CoachMarkTutorialView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setVisibility(8);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        return true;
    }
}
