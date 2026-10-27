package com.amazon.music.views.library.views;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.binders.ToggleButtonBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiToggleView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001TB\u0017\u0012\u0006\u0010Q\u001a\u00020P\u0012\u0006\u0010)\u001a\u00020(¢\u0006\u0004\bR\u0010SJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0016\u0010\u0007\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0005H\u0002J\u001e\u0010\f\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\nH\u0002J\u0012\u0010\u0012\u001a\u00020\u00022\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0014J\u001c\u0010\u0013\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u000b\u001a\u00020\nJ\u001e\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u0019\u001a\u00020\u0002J\u001e\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001cJ\u000e\u0010!\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u001fJ\u0006\u0010\"\u001a\u00020\u0002J\u0016\u0010'\u001a\u00020\u00022\u0006\u0010$\u001a\u00020#2\u0006\u0010&\u001a\u00020%R\u0017\u0010)\u001a\u00020(8\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u0016\u0010-\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00100\u001a\u00020/8\u0002X\u0082D¢\u0006\u0006\n\u0004\b0\u00101R\u001b\u00107\u001a\u0002028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u00106R\u001b\u0010;\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b8\u00104\u001a\u0004\b9\u0010:R\u001b\u0010?\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b<\u00104\u001a\u0004\b=\u0010>R\u001b\u0010D\u001a\u00020@8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bA\u00104\u001a\u0004\bB\u0010CR\u0016\u0010E\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010G\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010FR\u001c\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bH\u0010IR\u0016\u0010J\u001a\u00020#8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bJ\u0010KR\u0016\u0010L\u001a\u00020%8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bL\u0010MR\u0018\u0010N\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bN\u0010O¨\u0006U"}, d2 = {"Lcom/amazon/music/views/library/views/MultiToggleView;", "Landroid/widget/FrameLayout;", "", "setupAnimationView", "", "Lcom/amazon/ui/foundations/views/BaseButton;", "buttons", "resizeButtons", "button", "resizeButton", "", "layoutWidth", "setWeightToButtons", "i", "", "getButtonXPosition", "Landroid/graphics/Canvas;", "canvas", "dispatchDraw", "addButtons", "newIndex", "previousIndex", "Lcom/amazon/music/views/library/binders/ToggleButtonBinder;", "buttonBinder", "playAnimation", "stopAnimations", "index", "binder", "", "isActive", "updateActiveButton", "Lcom/amazon/music/views/library/views/MultiToggleView$OnValueChangedListener;", "l", "setOnValueChangedListener", "clearReference", "Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "iconSizeKey", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "buttonSizeKey", "setButtonSize", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "buttonsLayoutWeight", "F", "", "animationDuration", "J", "Landroid/widget/LinearLayout;", "buttonsLayout$delegate", "Lkotlin/Lazy;", "getButtonsLayout", "()Landroid/widget/LinearLayout;", "buttonsLayout", "animationView$delegate", "getAnimationView", "()Lcom/amazon/ui/foundations/views/BaseButton;", "animationView", "cornerRadius$delegate", "getCornerRadius", "()F", "cornerRadius", "Landroid/graphics/Paint;", "strokePaint$delegate", "getStrokePaint", "()Landroid/graphics/Paint;", "strokePaint", "buttonMaxWidth", "I", "buttonMaxHeight", "buttonsList", "Ljava/util/List;", "defaultIconSizeKey", "Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "defaultButtonSizeKey", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "onValueChangedListener", "Lcom/amazon/music/views/library/views/MultiToggleView$OnValueChangedListener;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "OnValueChangedListener", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class MultiToggleView extends FrameLayout {
    private final long animationDuration;

    /* JADX INFO: renamed from: animationView$delegate, reason: from kotlin metadata */
    private final Lazy animationView;
    private int buttonMaxHeight;
    private int buttonMaxWidth;

    /* JADX INFO: renamed from: buttonsLayout$delegate, reason: from kotlin metadata */
    private final Lazy buttonsLayout;
    private float buttonsLayoutWeight;
    private List<? extends BaseButton> buttonsList;

    /* JADX INFO: renamed from: cornerRadius$delegate, reason: from kotlin metadata */
    private final Lazy cornerRadius;
    private ButtonSizeKey defaultButtonSizeKey;
    private IconSizeKey defaultIconSizeKey;
    private OnValueChangedListener onValueChangedListener;

    /* JADX INFO: renamed from: strokePaint$delegate, reason: from kotlin metadata */
    private final Lazy strokePaint;
    private final StyleSheet styleSheet;

    /* JADX INFO: compiled from: MultiToggleView.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/views/library/views/MultiToggleView$OnValueChangedListener;", "", "onValueChanged", "", "value", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnValueChangedListener {
        void onValueChanged(int value);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiToggleView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.buttonsLayoutWeight = 2.0f;
        this.animationDuration = 250L;
        this.buttonsLayout = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.MultiToggleView$buttonsLayout$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.buttons_layout);
            }
        });
        this.animationView = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.MultiToggleView$animationView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.animation_view);
            }
        });
        this.cornerRadius = LazyKt__LazyJVMKt.lazy(new Function0<Float>() { // from class: com.amazon.music.views.library.views.MultiToggleView$cornerRadius$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getStyleSheet().getCornerSize(CornerSizeKey.ROUND) == null ? 0.0f : r0.intValue());
            }
        });
        this.strokePaint = LazyKt__LazyJVMKt.lazy(new Function0<Paint>() { // from class: com.amazon.music.views.library.views.MultiToggleView$strokePaint$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Paint invoke() {
                Paint paint = new Paint();
                MultiToggleView multiToggleView = this.this$0;
                paint.setStrokeWidth(multiToggleView.getResources().getDimension(R$dimen.spacer_1));
                paint.setStyle(Paint.Style.STROKE);
                Integer color = multiToggleView.getStyleSheet().getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_3);
                if (color != null) {
                    paint.setColor(color.intValue());
                }
                return paint;
            }
        });
        View.inflate(context, R$layout.dmmviewlibrary_multi_toggle_layout, this);
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    private final LinearLayout getButtonsLayout() {
        Object value = this.buttonsLayout.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-buttonsLayout>(...)");
        return (LinearLayout) value;
    }

    private final BaseButton getAnimationView() {
        Object value = this.animationView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-animationView>(...)");
        return (BaseButton) value;
    }

    private final float getCornerRadius() {
        return ((Number) this.cornerRadius.getValue()).floatValue();
    }

    private final Paint getStrokePaint() {
        return (Paint) this.strokePaint.getValue();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (canvas == null) {
            return;
        }
        canvas.drawRoundRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), getCornerRadius(), getCornerRadius(), getStrokePaint());
    }

    public final void addButtons(final List<? extends BaseButton> buttons, final int layoutWidth) {
        Intrinsics.checkNotNullParameter(buttons, "buttons");
        LinearLayout buttonsLayout = getButtonsLayout();
        buttonsLayout.removeAllViews();
        this.buttonsList = buttons;
        final int i = 0;
        for (final BaseButton baseButton : buttons) {
            buttonsLayout.addView(baseButton);
            baseButton.post(new Runnable() { // from class: com.amazon.music.views.library.views.MultiToggleView$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    MultiToggleView.m2421addButtons$lambda1$lambda0(this.f$0, baseButton, i, buttons, layoutWidth);
                }
            });
            i++;
        }
        buttonsLayout.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addButtons$lambda-1$lambda-0, reason: not valid java name */
    public static final void m2421addButtons$lambda1$lambda0(MultiToggleView this$0, BaseButton button, int i, List buttons, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(button, "$button");
        Intrinsics.checkNotNullParameter(buttons, "$buttons");
        this$0.buttonMaxHeight = button.getHeight();
        if (button.getWidth() > this$0.buttonMaxWidth) {
            this$0.buttonMaxWidth = button.getWidth();
        }
        if (i == buttons.size() - 1) {
            if (i2 == -2) {
                this$0.resizeButtons(buttons);
            } else {
                List<? extends BaseButton> list = this$0.buttonsList;
                if (list == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("buttonsList");
                    list = null;
                }
                this$0.buttonsLayoutWeight = list.size();
                this$0.setWeightToButtons(buttons, i2);
            }
            this$0.setupAnimationView();
        }
    }

    private final void setupAnimationView() {
        getAnimationView().setVisibility(8);
        StyleSheet styleSheet = this.styleSheet;
        ButtonSizeKey buttonSizeKey = this.defaultButtonSizeKey;
        if (buttonSizeKey == null) {
            Intrinsics.throwUninitializedPropertyAccessException("defaultButtonSizeKey");
            buttonSizeKey = null;
        }
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(buttonSizeKey, ButtonStyleKey.SOLID);
        if (buttonBuilder != null) {
            buttonBuilder.withText("");
            buttonBuilder.applyStyle(getAnimationView());
        }
        getButtonsLayout().post(new Runnable() { // from class: com.amazon.music.views.library.views.MultiToggleView$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                MultiToggleView.m2422setupAnimationView$lambda6(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupAnimationView$lambda-6, reason: not valid java name */
    public static final void m2422setupAnimationView$lambda6(final MultiToggleView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View childAt = this$0.getButtonsLayout().getChildAt(0);
        if (childAt != null) {
            BaseButton baseButton = (BaseButton) childAt;
            Integer spacerInPixels = this$0.getStyleSheet().getSpacerInPixels(baseButton.getButton().getText() == null ? SpacerKey.MICRO : SpacerKey.NANO);
            int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
            this$0.buttonMaxWidth = baseButton.getWidth() - iIntValue;
            this$0.getAnimationView().setPadding(iIntValue, 0, 0, 0);
        }
        this$0.resizeButton(this$0.getAnimationView());
        List<? extends BaseButton> list = this$0.buttonsList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonsList");
            list = null;
        }
        for (final BaseButton baseButton2 : list) {
            if (baseButton2.getIsActive()) {
                this$0.getAnimationView().post(new Runnable() { // from class: com.amazon.music.views.library.views.MultiToggleView$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        MultiToggleView.m2423setupAnimationView$lambda6$lambda5$lambda4(this.f$0, baseButton2);
                    }
                });
            }
        }
        this$0.getAnimationView().setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupAnimationView$lambda-6$lambda-5$lambda-4, reason: not valid java name */
    public static final void m2423setupAnimationView$lambda6$lambda5$lambda4(MultiToggleView this$0, BaseButton it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        this$0.getAnimationView().setX(it.getX());
    }

    private final void resizeButton(BaseButton button) {
        ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
        layoutParams.height = this.buttonMaxHeight;
        layoutParams.width = this.buttonMaxWidth;
        button.setLayoutParams(layoutParams);
    }

    private final void setWeightToButtons(List<? extends BaseButton> buttons, int layoutWidth) {
        getButtonsLayout().getLayoutParams().width = layoutWidth;
        getButtonsLayout().setWeightSum(this.buttonsLayoutWeight);
        Iterator<T> it = buttons.iterator();
        while (it.hasNext()) {
            ((BaseButton) it.next()).setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        }
    }

    public final void playAnimation(final int newIndex, final int previousIndex, final ToggleButtonBinder buttonBinder) {
        Intrinsics.checkNotNullParameter(buttonBinder, "buttonBinder");
        stopAnimations();
        ViewPropertyAnimator viewPropertyAnimatorAnimate = getAnimationView().animate();
        viewPropertyAnimatorAnimate.x(getButtonXPosition(newIndex));
        viewPropertyAnimatorAnimate.setInterpolator(new LinearInterpolator());
        viewPropertyAnimatorAnimate.setDuration(this.animationDuration);
        viewPropertyAnimatorAnimate.setListener(new Animator.AnimatorListener() { // from class: com.amazon.music.views.library.views.MultiToggleView.playAnimation.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator p) {
                Intrinsics.checkNotNullParameter(p, "p");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator p) {
                Intrinsics.checkNotNullParameter(p, "p");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator p) {
                Intrinsics.checkNotNullParameter(p, "p");
                MultiToggleView.this.updateActiveButton(previousIndex, buttonBinder, false);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator p) {
                Intrinsics.checkNotNullParameter(p, "p");
                MultiToggleView.this.updateActiveButton(newIndex, buttonBinder, true);
                OnValueChangedListener onValueChangedListener = MultiToggleView.this.onValueChangedListener;
                if (onValueChangedListener == null) {
                    return;
                }
                onValueChangedListener.onValueChanged(newIndex);
            }
        }).start();
    }

    public final void stopAnimations() {
        getAnimationView().animate().cancel();
        getAnimationView().clearAnimation();
    }

    private final float getButtonXPosition(int i) {
        List<? extends BaseButton> list = this.buttonsList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonsList");
            list = null;
        }
        return list.get(i).getX();
    }

    public final void updateActiveButton(int index, ToggleButtonBinder binder, boolean isActive) {
        Intrinsics.checkNotNullParameter(binder, "binder");
        List<? extends BaseButton> list = this.buttonsList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonsList");
            list = null;
        }
        binder.handleStateChange(list.get(index), isActive);
    }

    public final void setOnValueChangedListener(OnValueChangedListener l) {
        Intrinsics.checkNotNullParameter(l, "l");
        this.onValueChangedListener = l;
    }

    public final void clearReference() {
        this.onValueChangedListener = null;
        List<? extends BaseButton> list = this.buttonsList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonsList");
            list = null;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            ((BaseButton) it.next()).setOnClickListener(null);
        }
    }

    public final void setButtonSize(IconSizeKey iconSizeKey, ButtonSizeKey buttonSizeKey) {
        Intrinsics.checkNotNullParameter(iconSizeKey, "iconSizeKey");
        Intrinsics.checkNotNullParameter(buttonSizeKey, "buttonSizeKey");
        this.defaultIconSizeKey = iconSizeKey;
        this.defaultButtonSizeKey = buttonSizeKey;
    }

    private final void resizeButtons(List<? extends BaseButton> buttons) {
        Iterator<T> it = buttons.iterator();
        while (it.hasNext()) {
            resizeButton((BaseButton) it.next());
        }
    }
}
