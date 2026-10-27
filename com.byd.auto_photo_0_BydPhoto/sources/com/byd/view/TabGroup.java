package com.byd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;
import com.byd.util.w;
import com.byd.view.TabItem;

/* JADX INFO: loaded from: classes2.dex */
public class TabGroup extends LinearLayout {
    private int mCheckedId;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private TabItem.OnCheckedChangeListener onChildCheckListener;
    private TabContainerCheckChangeListener onChildContainerListener;

    private class CheckedStateTracker implements TabItem.OnCheckedChangeListener {
        private CheckedStateTracker() {
        }

        @Override // com.byd.view.TabItem.OnCheckedChangeListener
        public void onCheckedChanged(TabItem tabItem, boolean z) {
            if (z) {
                if (TabGroup.this.mCheckedId != -1) {
                    TabGroup tabGroup = TabGroup.this;
                    tabGroup.setCheckedStateForView(tabGroup.mCheckedId, false);
                }
                TabGroup.this.setCheckedId(tabItem.getId());
            }
        }
    }

    private class ContainerCheckedStateTracker implements TabContainerCheckChangeListener {
        private ContainerCheckedStateTracker() {
        }

        @Override // com.byd.view.TabContainerCheckChangeListener
        public void onCheckedChanged(ViewGroup viewGroup, boolean z) {
            if (z) {
                if (TabGroup.this.mCheckedId != -1) {
                    TabGroup tabGroup = TabGroup.this;
                    tabGroup.setCheckedStateForView(tabGroup.mCheckedId, false);
                }
                TabGroup.this.setCheckedId(viewGroup.getId());
            }
        }
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(TabGroup tabGroup, int i);
    }

    public TabGroup(Context context) {
        super(context);
        this.mCheckedId = -1;
        init();
        setOrientation(1);
    }

    public TabGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCheckedId = -1;
        init();
    }

    private void init() {
        this.onChildCheckListener = new CheckedStateTracker();
        this.onChildContainerListener = new ContainerCheckedStateTracker();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedId(int i) {
        this.mCheckedId = i;
        OnCheckedChangeListener onCheckedChangeListener = this.mOnCheckedChangeListener;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(this, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedStateForView(int i, boolean z) {
        KeyEvent.Callback callbackFindViewById = findViewById(i);
        if (callbackFindViewById instanceof Checkable) {
            ((Checkable) callbackFindViewById).setChecked(z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if ((view instanceof ViewGroup) && (view instanceof TabContainer)) {
            TabContainer tabContainer = (TabContainer) view;
            if (tabContainer.isChecked()) {
                int i2 = this.mCheckedId;
                if (i2 != -1) {
                    setCheckedStateForView(i2, false);
                }
                setCheckedId(tabContainer.getViewId());
            }
            tabContainer.setOnCheckedChangeListener(this.onChildContainerListener);
        } else if (view instanceof TabItem) {
            TabItem tabItem = (TabItem) view;
            if (tabItem.isChecked()) {
                int i3 = this.mCheckedId;
                if (i3 != -1) {
                    setCheckedStateForView(i3, false);
                }
                setCheckedId(tabItem.getId());
            }
            tabItem.setOnCheckedChangeListener(this.onChildCheckListener);
        }
        super.addView(view, i, layoutParams);
    }

    public void check(int i) {
        if (i == -1 || i != this.mCheckedId) {
            int i2 = this.mCheckedId;
            if (i2 != -1) {
                setCheckedStateForView(i2, false);
            }
            if (i != -1) {
                setCheckedStateForView(i, true);
            }
            setCheckedId(i);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() <= 1) {
            return super.dispatchTouchEvent(motionEvent);
        }
        w.c("break by more pointer!!!");
        return true;
    }

    public int getCheckedId() {
        return this.mCheckedId;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setEnabled(z);
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }
}
