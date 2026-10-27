package com.byd.view;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import b.a.d;
import com.byd.util.w;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class TabItemLinearContainer extends LinearLayout implements TabContainer {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    private static final int LONG_PRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private static final int TAP = 3;
    private static final int TAP_CANCEL = 4;
    private static final int TAP_LONG_CLICK = 5;
    private static final int TAP_TIMEOUT = 150;
    private boolean autoCheck;
    private boolean checkable;
    private boolean checked;
    private TabContainerCheckChangeListener onCheckedChangeListener;
    private OnContainerTouchListener onContainerTouchListener;
    protected TapHandler tapHandler;

    public interface OnContainerTouchListener {
        void onTouchAction(TabItemLinearContainer tabItemLinearContainer, int i);
    }

    private static class TapHandler extends d<TabItemLinearContainer> {
        public TapHandler(TabItemLinearContainer tabItemLinearContainer) {
            super(tabItemLinearContainer);
        }

        @Override // b.a.d
        public void handleMessage(TabItemLinearContainer tabItemLinearContainer, Message message) {
            int i = message.what;
            if (i == 3) {
                removeCallbacksAndMessages(null);
                tabItemLinearContainer.performClick();
            } else if (i == 4) {
                removeMessages(3);
            } else {
                if (i != 5) {
                    return;
                }
                removeCallbacksAndMessages(null);
                tabItemLinearContainer.performLongClick();
            }
        }
    }

    public TabItemLinearContainer(Context context) {
        this(context, null, 0, 0);
    }

    public TabItemLinearContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    public TabItemLinearContainer(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public TabItemLinearContainer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.autoCheck = true;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.byd.auto_photo.R.styleable.TabItem, i, i2);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(1, false);
        this.checkable = typedArrayObtainStyledAttributes.getBoolean(0, true);
        typedArrayObtainStyledAttributes.recycle();
        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setLongClickable(true);
        setChecked(z);
        this.tapHandler = new TapHandler(this);
    }

    private void animTouchEvent(int i) {
        OnContainerTouchListener onContainerTouchListener = this.onContainerTouchListener;
        if (onContainerTouchListener == null) {
            return;
        }
        onContainerTouchListener.onTouchAction(this, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
    }

    @Override // com.byd.view.TabContainer
    public int getViewId() {
        return getId();
    }

    public boolean isCheckable() {
        return this.checkable;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, CHECKED_STATE_SET);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.tapHandler.removeCallbacksAndMessages(null);
            this.tapHandler.sendEmptyMessageDelayed(3, DOUBLE_TAP_TIMEOUT);
            this.tapHandler.sendEmptyMessageDelayed(5, LONG_PRESS_TIMEOUT);
            this.tapHandler.sendEmptyMessageDelayed(4, 150L);
        } else {
            if (action != 1) {
                if (action == 3) {
                    this.tapHandler.removeCallbacksAndMessages(null);
                }
                return true;
            }
            this.tapHandler.removeMessages(4);
            this.tapHandler.removeMessages(5);
        }
        animTouchEvent(action);
        return true;
    }

    @Override // android.view.View
    public boolean performClick() {
        w.c("===performClick===");
        if (!this.autoCheck) {
            return true;
        }
        if (!this.checkable) {
            return super.performClick();
        }
        if (!this.checked) {
            setChecked(true);
        }
        boolean zPerformClick = super.performClick();
        if (!zPerformClick) {
            playSoundEffect(0);
        }
        return zPerformClick;
    }

    @Override // android.view.View
    public boolean performLongClick() {
        if (!this.autoCheck) {
            return true;
        }
        if (!this.checkable) {
            return super.performLongClick();
        }
        if (!this.checked) {
            setChecked(true);
        }
        boolean zPerformLongClick = super.performLongClick();
        if (!zPerformLongClick) {
            playSoundEffect(0);
        }
        return zPerformLongClick;
    }

    public void setAutoCheck(boolean z) {
        this.autoCheck = z;
    }

    public void setCheckable(boolean z) {
        this.checkable = z;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.checked != z) {
            this.checked = z;
            refreshDrawableState();
            setChildrenCheck(z);
            TabContainerCheckChangeListener tabContainerCheckChangeListener = this.onCheckedChangeListener;
            if (tabContainerCheckChangeListener != null) {
                tabContainerCheckChangeListener.onCheckedChanged(this, z);
            }
        }
    }

    public void setChildrenCheck(boolean z) {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(this);
        while (!linkedList.isEmpty()) {
            ViewGroup viewGroup = (ViewGroup) linkedList.removeFirst();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                KeyEvent.Callback childAt = viewGroup.getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    linkedList.addLast((ViewGroup) childAt);
                }
                if (childAt instanceof Checkable) {
                    ((Checkable) childAt).setChecked(z);
                }
            }
        }
    }

    @Override // com.byd.view.TabContainer
    public void setOnCheckedChangeListener(TabContainerCheckChangeListener tabContainerCheckChangeListener) {
        this.onCheckedChangeListener = tabContainerCheckChangeListener;
    }

    public void setOnContainerTouchListener(OnContainerTouchListener onContainerTouchListener) {
        this.onContainerTouchListener = onContainerTouchListener;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.checked);
    }
}
