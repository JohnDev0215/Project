package com.android.systemui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class StatusBydLinearLayout extends LinearLayout {
    public StatusBydLinearLayout(Context context) {
        this(context, null);
    }

    public StatusBydLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusBydLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public StatusBydLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 1 || action == 4) {
            float y = event.getY();
            float x = event.getX();
            if (y > getHeight() || x > getWidth()) {
                BydLogHelper.logD("click out view");
                setPressed(false);
            }
        }
        return super.onTouchEvent(event);
    }
}
