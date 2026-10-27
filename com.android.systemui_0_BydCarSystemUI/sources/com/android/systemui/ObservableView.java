package com.android.systemui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.Field;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class ObservableView extends LinearLayout {
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_TOP = 1;
    private final LinkedList<LayoutBean> mList;
    private OnWidthChangedListener widthChangedListener;

    public interface OnWidthChangedListener {
        void onWidthChanged(int newWidth, int oldWidth);
    }

    public ObservableView(Context context) {
        super(context);
        this.mList = new LinkedList<>();
    }

    public ObservableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mList = new LinkedList<>();
    }

    public ObservableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mList = new LinkedList<>();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        BydLogHelper.logD("onSizeChanged newWidth = " + w + " ,oldWidth = " + oldw);
        OnWidthChangedListener onWidthChangedListener = this.widthChangedListener;
        if (onWidthChangedListener == null || w == oldw) {
            return;
        }
        onWidthChangedListener.onWidthChanged(w, oldw);
    }

    public void setOnWidthChangedListener(OnWidthChangedListener listener) {
        this.widthChangedListener = listener;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        BydLogHelper.logD("onLayout");
        if (AppUtils.isRightRudder()) {
            layoutHorizontalRtl(l, t, r, b);
        } else {
            layoutHorizontal(l, t, r, b);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void layoutHorizontal(int r24, int r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 661
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.ObservableView.layoutHorizontal(int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x020d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void layoutHorizontalRtl(int r23, int r24, int r25, int r26) {
        /*
            Method dump skipped, instruction units count: 666
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.ObservableView.layoutHorizontalRtl(int, int, int, int):void");
    }

    private void requestMeasure() {
        measure(View.MeasureSpec.makeMeasureSpec(getLayoutParams().width, 1073741824), View.MeasureSpec.makeMeasureSpec(getLayoutParams().height, 1073741824));
    }

    private int getTotalLength() {
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField("mTotalLength");
            declaredField.setAccessible(true);
            return ((Integer) declaredField.get(this)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int[] getBytes(String name) {
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField(name);
            declaredField.setAccessible(true);
            return (int[]) declaredField.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setChildFrame(View child, int left, int top, int width, int height) {
        child.layout(left, top, width + left, height + top);
    }

    protected boolean hasDividerBeforeChildAt(int childIndex) {
        return childIndex == getChildCount() ? (getShowDividers() & 4) != 0 : allViewsAreGoneBefore(childIndex) ? (getShowDividers() & 1) != 0 : (getShowDividers() & 2) != 0;
    }

    private boolean allViewsAreGoneBefore(int childIndex) {
        for (int i = childIndex - 1; i >= 0; i--) {
            View childAt = getChildAt(i);
            if (childAt != null && childAt.getVisibility() != 8) {
                return false;
            }
        }
        return true;
    }

    private static class LayoutBean {
        public int left;
        public int top;
        public View view;

        public LayoutBean(View view, int left, int top) {
            this.view = view;
            this.left = left;
            this.top = top;
        }
    }
}
