package com.android.launcher3.shortcuts;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;

/* JADX INFO: loaded from: classes4.dex */
public class DeepShortcutTextView extends BubbleTextView {
    private final Rect mDragHandleBounds;
    private final int mDragHandleWidth;
    private Toast mInstructionToast;
    private final Rect mLoadingStateBounds;
    private Drawable mLoadingStatePlaceholder;
    private boolean mShowInstructionToast;
    private boolean mShowLoadingState;

    @Override // com.android.launcher3.BubbleTextView
    protected void applyCompoundDrawables(Drawable icon) {
    }

    public DeepShortcutTextView(Context context) {
        this(context, null, 0);
    }

    public DeepShortcutTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DeepShortcutTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mDragHandleBounds = new Rect();
        this.mShowInstructionToast = false;
        this.mLoadingStateBounds = new Rect();
        Resources resources = getResources();
        this.mDragHandleWidth = resources.getDimensionPixelSize(R.dimen.popup_padding_end) + resources.getDimensionPixelSize(R.dimen.deep_shortcut_drag_handle_size) + (resources.getDimensionPixelSize(R.dimen.deep_shortcut_drawable_padding) / 2);
        showLoadingState(true);
    }

    @Override // com.android.launcher3.BubbleTextView, android.widget.TextView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mDragHandleBounds.set(0, 0, this.mDragHandleWidth, getMeasuredHeight());
        if (!Utilities.isRtl(getResources())) {
            this.mDragHandleBounds.offset(getMeasuredWidth() - this.mDragHandleBounds.width(), 0);
        }
        setLoadingBounds();
    }

    private void setLoadingBounds() {
        if (this.mLoadingStatePlaceholder == null) {
            return;
        }
        this.mLoadingStateBounds.set(0, 0, (getMeasuredWidth() - this.mDragHandleWidth) - getPaddingStart(), this.mLoadingStatePlaceholder.getIntrinsicHeight());
        this.mLoadingStateBounds.offset(Utilities.isRtl(getResources()) ? this.mDragHandleWidth : getPaddingStart(), (int) ((getMeasuredHeight() - this.mLoadingStatePlaceholder.getIntrinsicHeight()) / 2.0f));
        this.mLoadingStatePlaceholder.setBounds(this.mLoadingStateBounds);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence text, TextView.BufferType type) {
        super.setText(text, type);
        if (TextUtils.isEmpty(text)) {
            return;
        }
        showLoadingState(false);
    }

    @Override // com.android.launcher3.BubbleTextView, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            this.mShowInstructionToast = this.mDragHandleBounds.contains((int) ev.getX(), (int) ev.getY());
        }
        return super.onTouchEvent(ev);
    }

    @Override // android.view.View
    public boolean performClick() {
        if (this.mShowInstructionToast) {
            showToast();
            return true;
        }
        return super.performClick();
    }

    @Override // com.android.launcher3.BubbleTextView, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        if (!this.mShowLoadingState) {
            super.onDraw(canvas);
        } else {
            this.mLoadingStatePlaceholder.draw(canvas);
        }
    }

    private void showLoadingState(boolean loading) {
        if (loading == this.mShowLoadingState) {
            return;
        }
        this.mShowLoadingState = loading;
        if (loading) {
            this.mLoadingStatePlaceholder = getContext().getDrawable(R.drawable.deep_shortcuts_text_placeholder);
            setLoadingBounds();
        } else {
            this.mLoadingStatePlaceholder = null;
        }
        invalidate();
    }

    private void showToast() {
        Toast toast = this.mInstructionToast;
        if (toast != null) {
            toast.cancel();
        }
        Toast toastMakeText = Toast.makeText(getContext(), Utilities.wrapForTts(getContext().getText(R.string.long_press_shortcut_to_add), getContext().getString(R.string.long_accessible_way_to_add_shortcut)), 0);
        this.mInstructionToast = toastMakeText;
        toastMakeText.show();
    }
}
