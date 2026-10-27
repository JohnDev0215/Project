package com.android.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class EmergencyShortcutButton extends FrameLayout implements View.OnClickListener {
    private static final int[] ICON_VIEWS = {R.id.phone_type_icon, R.id.confirmed_phone_type_icon};
    private View mCallNumberInfoView;
    private final Runnable mCancelSelectedButtonRunnable;
    private View mConfirmView;
    private boolean mConfirmViewHiding;
    private OnConfirmClickListener mOnConfirmClickListener;
    private MotionEvent mPendingTouchEvent;
    private TextView mPhoneCallHint;
    private TextView mPhoneNumber;
    private TextView mPhoneTypeDescription;

    public interface OnConfirmClickListener {
        void onConfirmClick(EmergencyShortcutButton emergencyShortcutButton);
    }

    public EmergencyShortcutButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCancelSelectedButtonRunnable = new Runnable() { // from class: com.android.phone.EmergencyShortcutButton.2
            @Override // java.lang.Runnable
            public void run() {
                if (EmergencyShortcutButton.this.isAttachedToWindow()) {
                    EmergencyShortcutButton.this.hideSelectedButton();
                }
            }
        };
    }

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.mOnConfirmClickListener = onConfirmClickListener;
    }

    public void setPhoneTypeIcon(int i) {
        for (int i2 : ICON_VIEWS) {
            ((ImageView) findViewById(i2)).setImageResource(i);
        }
    }

    public void setPhoneDescription(CharSequence charSequence) {
        this.mPhoneTypeDescription.setText(charSequence);
    }

    public void setPhoneNumber(CharSequence charSequence) {
        this.mPhoneNumber.setText(charSequence);
        this.mPhoneCallHint.setText(getContext().getString(R.string.emergency_call_shortcut_hint, charSequence));
        if (charSequence.length() > 1) {
            StringBuilder sb = new StringBuilder();
            for (char c : charSequence.toString().toCharArray()) {
                sb.append(c);
                sb.append(" ");
            }
            this.mPhoneNumber.setContentDescription(sb.toString().trim());
        }
    }

    public String getPhoneNumber() {
        TextView textView = this.mPhoneNumber;
        if (textView != null) {
            return textView.getText().toString();
        }
        return null;
    }

    public void onPreTouchEvent(MotionEvent motionEvent) {
        this.mPendingTouchEvent = motionEvent;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (this.mPendingTouchEvent == motionEvent && zDispatchTouchEvent) {
            this.mPendingTouchEvent = null;
        }
        return zDispatchTouchEvent;
    }

    public void onPostTouchEvent(MotionEvent motionEvent) {
        if (this.mPendingTouchEvent != null) {
            hideSelectedButton();
        }
        this.mPendingTouchEvent = null;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mCallNumberInfoView = findViewById(R.id.emergency_call_number_info_view);
        this.mConfirmView = findViewById(R.id.emergency_call_confirm_view);
        this.mCallNumberInfoView.setOnClickListener(this);
        this.mConfirmView.setOnClickListener(this);
        this.mPhoneNumber = (TextView) this.mCallNumberInfoView.findViewById(R.id.phone_number);
        this.mPhoneTypeDescription = (TextView) this.mCallNumberInfoView.findViewById(R.id.phone_number_description);
        this.mPhoneCallHint = (TextView) this.mConfirmView.findViewById(R.id.phone_call_hint);
        this.mConfirmViewHiding = true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnConfirmClickListener onConfirmClickListener;
        if (view.getId() == R.id.emergency_call_number_info_view) {
            if (((AccessibilityManager) getContext().getSystemService("accessibility")).isTouchExplorationEnabled()) {
                OnConfirmClickListener onConfirmClickListener2 = this.mOnConfirmClickListener;
                if (onConfirmClickListener2 != null) {
                    onConfirmClickListener2.onConfirmClick(this);
                    return;
                }
                return;
            }
            revealSelectedButton();
            return;
        }
        if (view.getId() != R.id.emergency_call_confirm_view || (onConfirmClickListener = this.mOnConfirmClickListener) == null) {
            return;
        }
        onConfirmClickListener.onConfirmClick(this);
    }

    private void revealSelectedButton() {
        this.mConfirmViewHiding = false;
        this.mConfirmView.setVisibility(0);
        int left = this.mCallNumberInfoView.getLeft() + (this.mCallNumberInfoView.getWidth() / 2);
        int top = this.mCallNumberInfoView.getTop() + (this.mCallNumberInfoView.getHeight() / 2);
        ViewAnimationUtils.createCircularReveal(this.mConfirmView, left, top, 0.0f, Math.max(left, r2.getWidth() - left) + Math.max(top, this.mConfirmView.getHeight() - top)).start();
        postDelayed(this.mCancelSelectedButtonRunnable, 3000L);
        this.mConfirmView.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSelectedButton() {
        if (this.mConfirmViewHiding || this.mConfirmView.getVisibility() != 0) {
            return;
        }
        this.mConfirmViewHiding = true;
        removeCallbacks(this.mCancelSelectedButtonRunnable);
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(this.mConfirmView, this.mConfirmView.getLeft() + (this.mConfirmView.getWidth() / 2), this.mConfirmView.getTop() + (this.mConfirmView.getHeight() / 2), Math.max(r0, this.mCallNumberInfoView.getWidth() - r0) + Math.max(r1, this.mCallNumberInfoView.getHeight() - r1), 0.0f);
        animatorCreateCircularReveal.addListener(new AnimatorListenerAdapter() { // from class: com.android.phone.EmergencyShortcutButton.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                EmergencyShortcutButton.this.mConfirmView.setVisibility(4);
            }
        });
        animatorCreateCircularReveal.start();
        this.mCallNumberInfoView.requestFocus();
    }
}
