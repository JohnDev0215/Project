package com.amazon.mp3.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AutoCompleteTextView;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes4.dex */
public class HeaderBarSearch extends AutoCompleteTextView {
    private OnClearListener mListener;
    private int mSearchButtonWidth;
    private boolean mTouchDown;

    public interface OnClearListener {
        void onClear();
    }

    public HeaderBarSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setOnClearListener(OnClearListener listener) {
        this.mListener = listener;
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence text, int start, int before, int after) {
        super.onTextChanged(text, start, before, after);
        setBackgroundResource(text.length() == 0 ? R.drawable.search_empty : R.drawable.search_nonempty);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action != 0) {
            if (action == 1) {
                if (this.mTouchDown && isTouchEventOnButton(event)) {
                    setText("");
                    OnClearListener onClearListener = this.mListener;
                    if (onClearListener != null) {
                        onClearListener.onClear();
                    }
                    this.mTouchDown = false;
                }
                this.mTouchDown = false;
            } else if (action == 3) {
                this.mTouchDown = false;
            }
        } else if (isTouchEventOnButton(event)) {
            this.mTouchDown = true;
        }
        return super.onTouchEvent(event);
    }

    private boolean isTouchEventOnButton(MotionEvent event) {
        if (event.getX() < getWidth() - this.mSearchButtonWidth) {
            return false;
        }
        float y = event.getY();
        return y > 0.0f && y < ((float) getHeight());
    }

    private void init() {
        setBackgroundResource(R.drawable.search_empty);
        setDropDownBackgroundResource(R.color.primo_blue);
        this.mSearchButtonWidth = getTotalPaddingRight();
    }
}
