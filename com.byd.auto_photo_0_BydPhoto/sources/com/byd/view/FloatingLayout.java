package com.byd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.byd.auto_photo.R;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class FloatingLayout extends ViewGroup {
    private List<Integer> childLayoutInLineNum;
    private SparseArray<LinkedList<Rect>> childrenPos;
    private Context context;
    private int itemBgRes;
    private int itemHorizontalMargin;
    private View.OnClickListener itemOnClick;
    private int itemVerticalMargin;
    private int layoutWidth;
    private SparseArray<Integer> linesHeight;
    private OnItemClickListener onItemClickListener;
    private int textColor;
    private float textSize;

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public FloatingLayout(Context context) {
        this(context, null);
    }

    public FloatingLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.itemHorizontalMargin = 50;
        this.itemVerticalMargin = 50;
        this.itemOnClick = new View.OnClickListener() { // from class: com.byd.view.FloatingLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FloatingLayout.this.onItemClickListener != null) {
                    FloatingLayout.this.onItemClickListener.onItemClick(((Integer) view.getTag()).intValue());
                }
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingLayout);
        this.itemBgRes = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        this.textColor = typedArrayObtainStyledAttributes.getColor(1, -1);
        this.textSize = typedArrayObtainStyledAttributes.getDimension(2, 16.0f);
        typedArrayObtainStyledAttributes.recycle();
        init(context);
    }

    private int computeChildPos(View view) {
        int minTop;
        int i;
        boolean z;
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int size = this.childrenPos.size();
        int i2 = size > 0 ? size - 1 : 0;
        if (size > 0) {
            while (i2 < size) {
                Rect last = this.childrenPos.get(i2).getLast();
                i = last.right + this.itemHorizontalMargin;
                if (i + measuredWidth <= this.layoutWidth && i > 0) {
                    minTop = last.top;
                    z = true;
                    break;
                }
                i2++;
            }
            minTop = 0;
            i = 0;
            z = false;
        } else {
            minTop = 0;
            i = 0;
            z = false;
        }
        if (!z && size > 0) {
            minTop = getMinTop(i, i + measuredWidth, measuredHeight, this.childrenPos.get(i2 - 1).get(0).bottom) + this.itemVerticalMargin;
        }
        Rect rect = new Rect(i, minTop, measuredWidth + i, measuredHeight + minTop);
        if (this.childrenPos.get(i2) == null) {
            this.childrenPos.put(i2, new LinkedList<>());
        }
        this.childrenPos.get(i2).addLast(rect);
        return i2;
    }

    private void createTagView(String str, int i) {
        createTagView(str, i, false);
    }

    private void createTagView(String str, int i, boolean z) {
        TextView textView = new TextView(this.context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        textView.setText(str);
        textView.setTextSize(0, this.textSize);
        textView.setTextColor(this.textColor);
        int i2 = this.itemBgRes;
        if (i2 != 0) {
            textView.setBackgroundResource(i2);
        }
        textView.setTag(Integer.valueOf(i));
        textView.setSelected(z);
        addView(textView);
        textView.setOnClickListener(this.itemOnClick);
    }

    private int getAllLineHeight() {
        int size = this.linesHeight.size();
        int iIntValue = 0;
        for (int i = 0; i < size; i++) {
            iIntValue += this.linesHeight.get(i).intValue();
        }
        return iIntValue;
    }

    private int getMinTop(int i, int i2, int i3, int i4) {
        Rect rect = new Rect(i, i4, i2, i4 + i3);
        while (isChildrenContain(rect)) {
            i4++;
            rect.top = i4;
            rect.bottom = i4 + i3;
        }
        return i4;
    }

    private void init(Context context) {
        this.childrenPos = new SparseArray<>();
        this.linesHeight = new SparseArray<>();
        this.childLayoutInLineNum = new ArrayList();
        this.context = context;
    }

    private boolean isChildrenContain(Rect rect) {
        int size = this.childrenPos.size();
        for (int i = 0; i < size; i++) {
            int size2 = this.childrenPos.get(i).size();
            for (int i2 = 0; i2 < size2; i2++) {
                if (Rect.intersects(rect, this.childrenPos.get(i).get(i2))) {
                    return true;
                }
            }
        }
        return false;
    }

    public <T> void addItem(T t, String str, boolean z) {
        try {
            createTagView((String) t.getClass().getDeclaredMethod(str, new Class[0]).invoke(t, new Object[0]), getChildCount(), z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int childCount = getChildCount();
        boolean z2 = getResources().getConfiguration().getLayoutDirection() == 1;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                Rect rectRemoveFirst = this.childrenPos.get(this.childLayoutInLineNum.get(i8).intValue()).removeFirst();
                if (z2) {
                    i5 = i3 - rectRemoveFirst.right;
                    i6 = rectRemoveFirst.top;
                    i7 = i3 - rectRemoveFirst.left;
                } else {
                    i5 = rectRemoveFirst.left;
                    i6 = rectRemoveFirst.top;
                    i7 = rectRemoveFirst.right;
                }
                childAt.layout(i5, i6, i7, rectRemoveFirst.bottom);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        this.childrenPos.clear();
        this.linesHeight.clear();
        this.childLayoutInLineNum.clear();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        this.layoutWidth = ViewGroup.resolveSize(0, i);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i, i2);
                int iComputeChildPos = computeChildPos(childAt);
                this.childLayoutInLineNum.add(Integer.valueOf(iComputeChildPos));
                int iHeight = this.childrenPos.get(iComputeChildPos).getLast().height() + this.itemVerticalMargin;
                if (this.linesHeight.get(iComputeChildPos) == null || (iHeight = Math.max(this.linesHeight.get(iComputeChildPos).intValue(), iHeight)) != this.linesHeight.get(iComputeChildPos).intValue()) {
                    this.linesHeight.put(iComputeChildPos, Integer.valueOf(iHeight));
                }
            }
        }
        setMeasuredDimension(this.layoutWidth, ViewGroup.resolveSize(getAllLineHeight() + paddingBottom + paddingTop, i2));
    }

    public void setChildViews(List<View> list) {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            addView(list.get(i));
        }
        if (isShown()) {
            requestLayout();
        }
    }

    public void setItemBgRes(int i) {
        this.itemBgRes = i;
        invalidate();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setTags(List<String> list) {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                createTagView(list.get(i), i);
            }
        }
    }

    public <T> void setTags(List<T> list, String str) {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                T t = list.get(i);
                try {
                    createTagView((String) t.getClass().getDeclaredMethod(str, new Class[0]).invoke(t, new Object[0]), i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setTextColor(int i) {
        this.textColor = i;
        invalidate();
    }
}
