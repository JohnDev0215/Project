package org.chromium.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.ui.base.ViewAndroidDelegate;

/* JADX INFO: loaded from: classes3.dex */
public class DropdownPopupWindow extends ListPopupWindow {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    ListAdapter mAdapter;
    private float mAnchorHeight;
    private final View mAnchorView;
    private float mAnchorWidth;
    private float mAnchorX;
    private float mAnchorY;
    private final Context mContext;
    private CharSequence mDescription;
    private int mInitialSelection;
    private View.OnLayoutChangeListener mLayoutChangeListener;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private boolean mRtl;
    private final ViewAndroidDelegate mViewAndroidDelegate;

    public DropdownPopupWindow(Context context, ViewAndroidDelegate viewAndroidDelegate) {
        super(context, null, 0, R.style.DropdownPopupWindow);
        this.mInitialSelection = -1;
        this.mContext = context;
        this.mViewAndroidDelegate = viewAndroidDelegate;
        View viewAcquireAnchorView = viewAndroidDelegate.acquireAnchorView();
        this.mAnchorView = viewAcquireAnchorView;
        viewAcquireAnchorView.setId(R.id.dropdown_popup_window);
        viewAcquireAnchorView.setTag(this);
        View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: org.chromium.ui.DropdownPopupWindow.1
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (view == DropdownPopupWindow.this.mAnchorView) {
                    DropdownPopupWindow.this.show();
                }
            }
        };
        this.mLayoutChangeListener = onLayoutChangeListener;
        viewAcquireAnchorView.addOnLayoutChangeListener(onLayoutChangeListener);
        super.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: org.chromium.ui.DropdownPopupWindow.2
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                if (DropdownPopupWindow.this.mOnDismissListener != null) {
                    DropdownPopupWindow.this.mOnDismissListener.onDismiss();
                }
                DropdownPopupWindow.this.mAnchorView.removeOnLayoutChangeListener(DropdownPopupWindow.this.mLayoutChangeListener);
                DropdownPopupWindow.this.mAnchorView.setTag(null);
                DropdownPopupWindow.this.mViewAndroidDelegate.releaseAnchorView(DropdownPopupWindow.this.mAnchorView);
            }
        });
        setAnchorView(viewAcquireAnchorView);
        Rect rect = new Rect();
        getBackground().getPadding(rect);
        setVerticalOffset(-rect.top);
    }

    public void setAnchorRect(float f, float f2, float f3, float f4) {
        this.mAnchorWidth = f3;
        this.mAnchorHeight = f4;
        this.mAnchorX = f;
        this.mAnchorY = f2;
        View view = this.mAnchorView;
        if (view != null) {
            this.mViewAndroidDelegate.setAnchorViewPosition(view, f, f2, f3, f4);
        }
    }

    @Override // android.widget.ListPopupWindow
    public void setAdapter(ListAdapter listAdapter) {
        this.mAdapter = listAdapter;
        super.setAdapter(listAdapter);
    }

    public void setInitialSelection(int i) {
        this.mInitialSelection = i;
    }

    @Override // android.widget.ListPopupWindow
    public void show() {
        setInputMethodMode(1);
        int iMeasureContentWidth = measureContentWidth();
        float f = iMeasureContentWidth / this.mContext.getResources().getDisplayMetrics().density;
        getBackground().getPadding(new Rect());
        if (f + r2.left + r2.right > this.mAnchorWidth) {
            setContentWidth(iMeasureContentWidth);
            Rect rect = new Rect();
            this.mAnchorView.getWindowVisibleDisplayFrame(rect);
            if (getWidth() > rect.width()) {
                setWidth(rect.width());
            }
        } else {
            setWidth(-2);
        }
        this.mViewAndroidDelegate.setAnchorViewPosition(this.mAnchorView, this.mAnchorX, this.mAnchorY, this.mAnchorWidth, this.mAnchorHeight);
        boolean zIsShowing = isShowing();
        super.show();
        getListView().setDividerHeight(0);
        ApiCompatibilityUtils.setLayoutDirection(getListView(), this.mRtl ? 1 : 0);
        if (!zIsShowing) {
            getListView().setContentDescription(this.mDescription);
            getListView().sendAccessibilityEvent(32);
        }
        if (this.mInitialSelection >= 0) {
            getListView().setSelection(this.mInitialSelection);
            this.mInitialSelection = -1;
        }
    }

    @Override // android.widget.ListPopupWindow
    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setRtl(boolean z) {
        this.mRtl = z;
    }

    public void disableHideOnOutsideTap() {
        try {
            ListPopupWindow.class.getMethod("setForceIgnoreOutsideTouch", Boolean.TYPE).invoke(this, true);
        } catch (Exception e) {
            Log.e("AutofillPopup", "ListPopupWindow.setForceIgnoreOutsideTouch not found", e);
        }
    }

    public void setContentDescriptionForAccessibility(CharSequence charSequence) {
        this.mDescription = charSequence;
    }

    private int measureContentWidth() {
        View[] viewArr = new View[this.mAdapter.getViewTypeCount()];
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMax = 0;
        for (int i = 0; i < this.mAdapter.getCount(); i++) {
            int itemViewType = this.mAdapter.getItemViewType(i);
            View view = this.mAdapter.getView(i, viewArr[itemViewType], null);
            viewArr[itemViewType] = view;
            view.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax = Math.max(iMax, view.getMeasuredWidth());
        }
        return iMax;
    }
}
