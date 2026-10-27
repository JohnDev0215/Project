package com.byd.music.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class FlowLayout extends ViewGroup {
    private OnHistoryItemClickListener mListener;

    public interface OnHistoryItemClickListener {
        void onHistoryClick(String str);
    }

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int childCount = getChildCount();
        int iMax = 0;
        int i3 = 0;
        int iMax2 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            measureChild(childAt, i, i2);
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
            if (childAt.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            }
            int i6 = iMax;
            int measuredWidth = childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            int measuredHeight = childAt.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            int i7 = i3 + measuredWidth;
            if (i7 > size) {
                i4 += iMax2;
                iMax2 = measuredHeight;
                iMax = i3;
                i3 = measuredWidth;
            } else {
                iMax2 = Math.max(iMax2, measuredHeight);
                i3 = i7;
                iMax = i6;
            }
            if (i5 == childCount - 1) {
                i4 += iMax2;
                iMax = Math.max(iMax, i3);
            }
        }
        int i8 = mode == 1073741824 ? size : iMax;
        if (mode2 == 1073741824) {
            i4 = size2;
        }
        setMeasuredDimension(i8, i4);
        LogUtil.d(getClass().toString(), "The Measured height is " + size2 + ". The Current line width is " + size);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        int childCount = getChildCount();
        int iMax = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int measuredWidth = childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            LogUtil.d(getClass().toString(), "The Child Measured width is " + childAt.getMeasuredWidth());
            LogUtil.d(getClass().toString(), "The Child Left Margin is " + marginLayoutParams.leftMargin);
            LogUtil.d(getClass().toString(), "The Child Right Margin is " + marginLayoutParams.rightMargin);
            int measuredHeight = childAt.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            LogUtil.d(getClass().toString(), "The Current line height is " + iMax + ". The Current line width is " + i5);
            LogUtil.d(getClass().toString(), "The Child height is " + measuredHeight + ". The Child width is " + measuredWidth);
            int i9 = measuredWidth + i5;
            if (i9 > getMeasuredWidth()) {
                if ((iMax * 2) + i6 <= getMeasuredHeight()) {
                    i6 += iMax;
                    i7 = 0;
                    i5 = measuredWidth;
                    iMax = measuredHeight;
                    z2 = false;
                } else {
                    z2 = true;
                }
            } else {
                iMax = Math.max(iMax, measuredHeight);
                z2 = false;
                i5 = i9;
            }
            int i10 = marginLayoutParams.leftMargin + i7;
            int i11 = marginLayoutParams.topMargin + i6;
            int measuredWidth2 = childAt.getMeasuredWidth() + i10;
            int measuredHeight2 = childAt.getMeasuredHeight() + i11;
            if (!z2) {
                childAt.layout(i10, i11, measuredWidth2, measuredHeight2);
                i7 += measuredWidth;
            }
            childAt.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.view.FlowLayout.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    String string = getClass().toString();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Child ");
                    TextView textView = (TextView) view;
                    sb.append((Object) textView.getText());
                    sb.append(" is Clicked.");
                    LogUtil.d(string, sb.toString());
                    if (FlowLayout.this.mListener != null) {
                        FlowLayout.this.mListener.onHistoryClick(textView.getText().toString());
                    }
                }
            });
        }
    }

    public void addHistoryItemClickListener(OnHistoryItemClickListener onHistoryItemClickListener) {
        this.mListener = onHistoryItemClickListener;
    }

    public void removeHistoryItemClickListener() {
        this.mListener = null;
    }
}
