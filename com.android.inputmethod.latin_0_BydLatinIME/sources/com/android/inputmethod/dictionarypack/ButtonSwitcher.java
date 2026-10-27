package com.android.inputmethod.dictionarypack;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.FrameLayout;
import com.android.inputmethod.latin.R;

/* JADX INFO: loaded from: classes2.dex */
public class ButtonSwitcher extends FrameLayout {
    public static final int ANIMATION_IN = 1;
    public static final int ANIMATION_OUT = 2;
    public static final int NOT_INITIALIZED = -1;
    public static final int STATUS_CANCEL = 2;
    public static final int STATUS_DELETE = 3;
    public static final int STATUS_INSTALL = 1;
    public static final int STATUS_NO_BUTTON = 0;
    private int mAnimateToStatus;
    private Button mCancelButton;
    private Button mDeleteButton;
    private Button mInstallButton;
    private DictionaryListInterfaceState mInterfaceState;
    private View.OnClickListener mOnClickListener;
    private int mStatus;

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    protected /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override // android.view.ViewGroup, android.view.View
    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public ButtonSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mStatus = -1;
        this.mAnimateToStatus = -1;
    }

    public ButtonSwitcher(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mStatus = -1;
        this.mAnimateToStatus = -1;
    }

    public void reset(final DictionaryListInterfaceState interfaceState) {
        this.mStatus = -1;
        this.mAnimateToStatus = -1;
        this.mInterfaceState = interfaceState;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(final boolean changed, final int left, final int top, final int right, final int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.mInstallButton = (Button) findViewById(R.id.dict_install_button);
        this.mCancelButton = (Button) findViewById(R.id.dict_cancel_button);
        this.mDeleteButton = (Button) findViewById(R.id.dict_delete_button);
        setInternalOnClickListener(this.mOnClickListener);
        setButtonPositionWithoutAnimation(this.mStatus);
        int i = this.mAnimateToStatus;
        if (i != -1) {
            animateButtonPosition(this.mStatus, i);
            this.mStatus = this.mAnimateToStatus;
            this.mAnimateToStatus = -1;
        }
    }

    private Button getButton(final int status) {
        if (status == 1) {
            return this.mInstallButton;
        }
        if (status == 2) {
            return this.mCancelButton;
        }
        if (status != 3) {
            return null;
        }
        return this.mDeleteButton;
    }

    public void setStatusAndUpdateVisuals(final int status) {
        int i = this.mStatus;
        if (i == -1) {
            setButtonPositionWithoutAnimation(status);
            this.mStatus = status;
        } else if (this.mInstallButton == null) {
            this.mAnimateToStatus = status;
        } else {
            animateButtonPosition(i, status);
            this.mStatus = status;
        }
    }

    private void setButtonPositionWithoutAnimation(final int status) {
        if (this.mInstallButton == null) {
            return;
        }
        int width = getWidth();
        this.mInstallButton.setTranslationX(1 == status ? 0.0f : width);
        this.mCancelButton.setTranslationX(2 == status ? 0.0f : width);
        this.mDeleteButton.setTranslationX(3 != status ? width : 0.0f);
    }

    void animateButtonIfStatusIsEqual(final View newButton, final int newStatus) {
        if (newStatus != this.mStatus) {
            return;
        }
        animateButton(newButton, 1);
    }

    private void animateButtonPosition(final int oldStatus, final int newStatus) {
        Button button = getButton(oldStatus);
        final Button button2 = getButton(newStatus);
        if (button != null && button2 != null) {
            animateButton(button, 2).setListener(new AnimatorListenerAdapter() { // from class: com.android.inputmethod.dictionarypack.ButtonSwitcher.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(final Animator animation) {
                    ButtonSwitcher.this.animateButtonIfStatusIsEqual(button2, newStatus);
                }
            });
        } else if (button != null) {
            animateButton(button, 2);
        } else if (button2 != null) {
            animateButton(button2, 1);
        }
    }

    public void setInternalOnClickListener(final View.OnClickListener listener) {
        this.mOnClickListener = listener;
        Button button = this.mInstallButton;
        if (button != null) {
            button.setOnClickListener(listener);
            this.mCancelButton.setOnClickListener(this.mOnClickListener);
            this.mDeleteButton.setOnClickListener(this.mOnClickListener);
        }
    }

    private ViewPropertyAnimator animateButton(final View button, final int direction) {
        float width = getWidth();
        float x = button.getX() - button.getTranslationX();
        this.mInterfaceState.removeFromCache((View) getParent());
        if (1 == direction) {
            button.setClickable(true);
            return button.animate().translationX(0.0f);
        }
        button.setClickable(false);
        return button.animate().translationX(width - x);
    }
}
