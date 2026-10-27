package com.android.systemui.widget;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.core.content.ContextCompat;
import com.android.systemui.R;
import com.android.systemui.util.ClickChecker;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class BydSwitch extends Switch implements Invalidable {
    private static final String TAG = "BydSwitch";
    private static final Object lock = new Object();
    private boolean intercept;
    private boolean isEverTouch;
    protected CompoundButton.OnCheckedChangeListener mListener;
    private OnInterceptTouchListener mTouchListener;
    private volatile boolean tempChecked;
    private long time;

    public interface OnInterceptTouchListener {
        void OnBydSwitchTouch(View v, MotionEvent event, boolean isChecked);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isEverTouch() {
        return this.isEverTouch;
    }

    public void setEverTouch(boolean everTouch) {
        this.isEverTouch = everTouch;
    }

    public BydSwitch(Context context) {
        super(context);
        this.time = 300L;
        this.isEverTouch = false;
        initViews(context, null);
    }

    public BydSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.time = 300L;
        this.isEverTouch = false;
        initViews(context, attrs);
    }

    public BydSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.time = 300L;
        this.isEverTouch = false;
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        setTrackDrawable(null);
        setBackground(ContextCompat.getDrawable(context, R.drawable.switch_off));
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        this.mListener = listener;
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean checked) {
        if (this.isEverTouch) {
            setChecked(checked, true);
        }
    }

    public void setChecked(boolean checked, boolean fromUser) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        this.tempChecked = checked;
        super.setChecked(checked);
        if (checked) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.switch_on));
        } else {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.switch_off));
        }
        if (!fromUser || (onCheckedChangeListener = this.mListener) == null) {
            return;
        }
        onCheckedChangeListener.onCheckedChanged(this, checked);
    }

    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == 0) {
            this.isEverTouch = true;
        }
        if (action == 0 && ClickChecker.checkMutiClick(this.time)) {
            return false;
        }
        OnInterceptTouchListener onInterceptTouchListener = this.mTouchListener;
        if (onInterceptTouchListener != null && this.intercept && action == 1) {
            onInterceptTouchListener.OnBydSwitchTouch(this, ev, !isChecked());
            return true;
        }
        return super.onTouchEvent(ev);
    }

    public void setOnInterceptTouchListener(OnInterceptTouchListener listener) {
        this.intercept = true;
        this.mTouchListener = listener;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isEverTouch = false;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable state) {
        setFieldValue(state, "checked", Boolean.valueOf(this.tempChecked));
        super.onRestoreInstanceState(state);
    }

    public static void setFieldValue(Object obj, String fieldName, Object value) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            declaredField.set(obj, value);
        } catch (IllegalAccessException | NoSuchFieldException unused) {
        }
    }
}
