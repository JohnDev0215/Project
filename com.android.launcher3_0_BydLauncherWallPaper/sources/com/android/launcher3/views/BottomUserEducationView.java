package com.android.launcher3.views;

import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import com.android.launcher3.Insettable;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.compat.AccessibilityManagerCompat;

/* JADX INFO: loaded from: classes4.dex */
public class BottomUserEducationView extends AbstractSlideInView implements Insettable {
    private static final int DEFAULT_CLOSE_DURATION = 200;
    private static final String KEY_SHOWED_BOTTOM_USER_EDUCATION = "showed_bottom_user_education";
    private View mCloseButton;
    private final Rect mInsets;

    @Override // com.android.launcher3.AbstractFloatingView
    public int getLogContainerType() {
        return 14;
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected boolean isOfType(int type) {
        return (type & 32) != 0;
    }

    @Override // com.android.launcher3.AbstractFloatingView
    public void logActionCommand(int command) {
    }

    public BottomUserEducationView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public BottomUserEducationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mInsets = new Rect();
        this.mContent = this;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        View viewFindViewById = findViewById(R.id.close_bottom_user_tip);
        this.mCloseButton = viewFindViewById;
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.views.-$$Lambda$BottomUserEducationView$Tv6AN8XcpM3SJwVE6L3sOUvwL_4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onFinishInflate$0$BottomUserEducationView(view);
            }
        });
    }

    public /* synthetic */ void lambda$onFinishInflate$0$BottomUserEducationView(View view) {
        handleClose(true);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        setTranslationShift(this.mTranslationShift);
        expandTouchAreaOfCloseButton();
    }

    @Override // com.android.launcher3.Insettable
    public void setInsets(Rect insets) {
        int i = insets.left - this.mInsets.left;
        int i2 = insets.right - this.mInsets.right;
        int i3 = insets.bottom - this.mInsets.bottom;
        this.mInsets.set(insets);
        setPadding(getPaddingLeft() + i, getPaddingTop(), getPaddingRight() + i2, getPaddingBottom() + i3);
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected void handleClose(boolean animate) {
        handleClose(animate, 200L);
        if (animate) {
            this.mLauncher.getSharedPrefs().edit().putBoolean(KEY_SHOWED_BOTTOM_USER_EDUCATION, true).apply();
            AccessibilityManagerCompat.sendCustomAccessibilityEvent(this, 32, getContext().getString(R.string.bottom_work_tab_user_education_closed));
        }
    }

    private void open(boolean animate) {
        if (this.mIsOpen || this.mOpenCloseAnimator.isRunning()) {
            return;
        }
        this.mIsOpen = true;
        if (animate) {
            this.mOpenCloseAnimator.setValues(PropertyValuesHolder.ofFloat(TRANSLATION_SHIFT, 0.0f));
            this.mOpenCloseAnimator.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
            this.mOpenCloseAnimator.start();
            return;
        }
        setTranslationShift(0.0f);
    }

    public static void showIfNeeded(Launcher launcher) {
        if (launcher.getSharedPrefs().getBoolean(KEY_SHOWED_BOTTOM_USER_EDUCATION, false)) {
            return;
        }
        BottomUserEducationView bottomUserEducationView = (BottomUserEducationView) LayoutInflater.from(launcher).inflate(R.layout.work_tab_bottom_user_education_view, (ViewGroup) launcher.getDragLayer(), false);
        launcher.getDragLayer().addView(bottomUserEducationView);
        bottomUserEducationView.open(true);
    }

    private void expandTouchAreaOfCloseButton() {
        Rect rect = new Rect();
        this.mCloseButton.getHitRect(rect);
        rect.left -= this.mCloseButton.getWidth();
        rect.top -= this.mCloseButton.getHeight();
        rect.right += this.mCloseButton.getWidth();
        rect.bottom += this.mCloseButton.getHeight();
        ((View) this.mCloseButton.getParent()).setTouchDelegate(new TouchDelegate(rect, this.mCloseButton));
    }
}
