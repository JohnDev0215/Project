package com.byd.card.drag;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatEditText;

/* JADX INFO: loaded from: classes.dex */
public class FolderTitleEdit extends AppCompatEditText {
    private Drawable dRight;

    public FolderTitleEdit(Context context) {
        this(context, null);
    }

    public FolderTitleEdit(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FolderTitleEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        this.dRight = right;
        super.setCompoundDrawables(left, top, right, bottom);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Drawable drawable;
        if (event.getAction() == 1 && (drawable = this.dRight) != null) {
            Rect bounds = drawable.getBounds();
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (x >= getRight() - bounds.width() && x <= getRight() - getPaddingRight() && y >= getPaddingTop() && y <= getHeight() - getPaddingBottom()) {
                setText("");
                event.setAction(3);
            }
        }
        return super.onTouchEvent(event);
    }
}
