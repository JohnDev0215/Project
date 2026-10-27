package com.android.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.android.internal.telephony.UserIcons;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EmergencyInfoGroup extends FrameLayout implements View.OnClickListener {
    private static final int[] ICON_VIEWS = {R.id.emergency_info_image, R.id.confirmed_emergency_info_image};
    private final Runnable mCancelSelectedButtonRunnable;
    private boolean mConfirmViewHiding;
    private View mEmergencyInfoButton;
    private View mEmergencyInfoConfirmButton;
    private TextView mEmergencyInfoName;
    private OnConfirmClickListener mOnConfirmClickListener;
    private MotionEvent mPendingTouchEvent;

    public interface OnConfirmClickListener {
        void onConfirmClick(EmergencyInfoGroup emergencyInfoGroup);
    }

    public EmergencyInfoGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCancelSelectedButtonRunnable = new Runnable() { // from class: com.android.phone.EmergencyInfoGroup.2
            @Override // java.lang.Runnable
            public void run() {
                if (EmergencyInfoGroup.this.isAttachedToWindow()) {
                    EmergencyInfoGroup.this.hideSelectedButton();
                }
            }
        };
    }

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.mOnConfirmClickListener = onConfirmClickListener;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mEmergencyInfoButton = findViewById(R.id.emergency_info_view);
        this.mEmergencyInfoName = (TextView) findViewById(R.id.emergency_info_name);
        this.mEmergencyInfoConfirmButton = findViewById(R.id.emergency_info_confirm_view);
        this.mEmergencyInfoButton.setOnClickListener(this);
        this.mEmergencyInfoConfirmButton.setOnClickListener(this);
        this.mConfirmViewHiding = true;
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            setupButtonInfo();
        }
    }

    private void setupButtonInfo() {
        boolean z;
        List<ResolveInfo> listResolveAssistPackageAndQueryActivities = EmergencyAssistanceHelper.resolveAssistPackageAndQueryActivities(getContext());
        if (listResolveAssistPackageAndQueryActivities == null || listResolveAssistPackageAndQueryActivities.size() <= 0) {
            z = false;
        } else {
            setTag(R.id.tag_intent, new Intent(EmergencyAssistanceHelper.getIntentAction()).setPackage(listResolveAssistPackageAndQueryActivities.get(0).activityInfo.packageName));
            setUserIcon();
            z = true;
        }
        this.mEmergencyInfoName.setText(getUserName());
        setVisibility(z ? 0 : 8);
    }

    private void setUserIcon() {
        for (int i : ICON_VIEWS) {
            ((ImageView) findViewById(i)).setImageDrawable(getCircularUserIcon());
        }
    }

    private Drawable getCircularUserIcon() {
        Bitmap userIcon = ((UserManager) getContext().getSystemService("user")).getUserIcon();
        if (userIcon == null) {
            userIcon = UserIcons.convertToBitmap(UserIcons.getDefaultUserIcon(getContext().getResources(), UserHandle.myUserId(), false));
        }
        RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(getContext().getResources(), userIcon);
        roundedBitmapDrawableCreate.setCircular(true);
        return roundedBitmapDrawableCreate;
    }

    private CharSequence getUserName() {
        String userName = ((UserManager) getContext().getSystemService("user")).getUserName();
        return TextUtils.isEmpty(userName) ? getContext().getText(R.string.emergency_information_owner_hint) : userName;
    }

    public void onPreTouchEvent(MotionEvent motionEvent) {
        this.mPendingTouchEvent = motionEvent;
    }

    public void onPostTouchEvent(MotionEvent motionEvent) {
        if (this.mPendingTouchEvent != null) {
            hideSelectedButton();
        }
        this.mPendingTouchEvent = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (this.mPendingTouchEvent == motionEvent && zDispatchTouchEvent) {
            this.mPendingTouchEvent = null;
        }
        return zDispatchTouchEvent;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnConfirmClickListener onConfirmClickListener;
        if (view.getId() == R.id.emergency_info_view) {
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
        if (view.getId() != R.id.emergency_info_confirm_view || (onConfirmClickListener = this.mOnConfirmClickListener) == null) {
            return;
        }
        onConfirmClickListener.onConfirmClick(this);
    }

    private void revealSelectedButton() {
        this.mConfirmViewHiding = false;
        this.mEmergencyInfoConfirmButton.setVisibility(0);
        int left = this.mEmergencyInfoButton.getLeft() + (this.mEmergencyInfoButton.getWidth() / 2);
        int top = this.mEmergencyInfoButton.getTop() + (this.mEmergencyInfoButton.getHeight() / 2);
        ViewAnimationUtils.createCircularReveal(this.mEmergencyInfoConfirmButton, left, top, 0.0f, Math.max(left, r2.getWidth() - left) + Math.max(top, this.mEmergencyInfoConfirmButton.getHeight() - top)).start();
        postDelayed(this.mCancelSelectedButtonRunnable, 3000L);
        this.mEmergencyInfoConfirmButton.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSelectedButton() {
        if (this.mConfirmViewHiding || this.mEmergencyInfoConfirmButton.getVisibility() != 0) {
            return;
        }
        this.mConfirmViewHiding = true;
        removeCallbacks(this.mCancelSelectedButtonRunnable);
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(this.mEmergencyInfoConfirmButton, this.mEmergencyInfoConfirmButton.getLeft() + (this.mEmergencyInfoConfirmButton.getWidth() / 2), this.mEmergencyInfoConfirmButton.getTop() + (this.mEmergencyInfoConfirmButton.getHeight() / 2), Math.max(r0, this.mEmergencyInfoButton.getWidth() - r0) + Math.max(r1, this.mEmergencyInfoButton.getHeight() - r1), 0.0f);
        animatorCreateCircularReveal.addListener(new AnimatorListenerAdapter() { // from class: com.android.phone.EmergencyInfoGroup.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                EmergencyInfoGroup.this.mEmergencyInfoConfirmButton.setVisibility(4);
            }
        });
        animatorCreateCircularReveal.start();
        this.mEmergencyInfoButton.requestFocus();
    }
}
