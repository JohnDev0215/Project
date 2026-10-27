package com.android.systemui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.SeekBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.wm.GestureControl;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class CustomVerticalSeekBar extends AppCompatSeekBar {
    private static final String TAG = "CustomVerticalSeekBar";
    private SeekBar.OnSeekBarChangeListener listener;
    private final GestureControl mGestureControl;
    private boolean mIsDragging;
    private Method mMethodSetProgressFromUser;

    public CustomVerticalSeekBar(Context context) {
        super(context);
        this.mGestureControl = (GestureControl) Dependency.get(GestureControl.class);
    }

    public CustomVerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mGestureControl = (GestureControl) Dependency.get(GestureControl.class);
    }

    public CustomVerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mGestureControl = (GestureControl) Dependency.get(GestureControl.class);
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.rotate(-90.0f);
        canvas.translate(-getHeight(), 0.0f);
        super.onDraw(canvas);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener l) {
        super.setOnSeekBarChangeListener(l);
        this.listener = l;
    }

    private void onStartTrackingTouch() {
        this.mIsDragging = true;
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.listener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStartTrackingTouch(this);
        }
    }

    private void onStopTrackingTouch() {
        this.mIsDragging = false;
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.listener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStopTrackingTouch(this);
        }
    }

    private void attemptClaimDrag(boolean active) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(active);
        }
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        String str = TAG;
        BydLogHelper.logW(str, "onTouchEvent isEnabled = " + isEnabled());
        if (!isEnabled()) {
            return false;
        }
        boolean zIsTouchEventInView = isTouchEventInView(this, event);
        BydLogHelper.logW(str, "onTouchEvent isTouchEventInView = " + zIsTouchEventInView + "  ---  mIsDragging = " + this.mIsDragging);
        int action = event.getAction();
        if (action == 0) {
            BydLogHelper.logW(str, "onTouchEvent ACTION_DOWN");
            setPressed(true);
            onStartTrackingTouch();
            trackTouchEvent(event);
        } else if (action == 1) {
            BydLogHelper.logW(str, "onTouchEvent ACTION_UP ");
            if (this.mIsDragging) {
                if (zIsTouchEventInView) {
                    trackTouchEvent(event);
                } else {
                    BydLogHelper.logD("isTouchEventInView false");
                }
                onStopTrackingTouch();
                setPressed(false);
            }
        } else if (action != 2) {
            if (action == 3) {
                BydLogHelper.logW(str, "onTouchEvent ACTION_CANCEL ");
                if (this.mIsDragging) {
                    onStopTrackingTouch();
                    setPressed(false);
                }
            }
        } else if (this.mIsDragging) {
            if (zIsTouchEventInView) {
                trackTouchEvent(event);
            } else {
                BydLogHelper.logD("isTouchEventInView false");
            }
        }
        return true;
    }

    private void trackTouchEvent(MotionEvent event) {
        setProgressFromUser(getMax() - ((int) (((getMax() - getMin()) * event.getY()) / getHeight())), true);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x007f A[Catch: all -> 0x008d, TryCatch #3 {, blocks: (B:3:0x0001, B:6:0x0009, B:10:0x0042, B:12:0x0046, B:21:0x0088, B:20:0x007f, B:19:0x0063, B:9:0x0027), top: B:27:0x0001, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0046 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void setProgressFromUser(int r9, boolean r10) {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.reflect.Method r0 = r8.mMethodSetProgressFromUser     // Catch: java.lang.Throwable -> L8d
            r1 = 2
            r2 = 3
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L42
            java.lang.Class<android.widget.ProgressBar> r0 = android.widget.ProgressBar.class
            java.lang.String r5 = "setProgressInternal"
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch: java.lang.NoSuchMethodException -> L26 java.lang.Throwable -> L8d
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch: java.lang.NoSuchMethodException -> L26 java.lang.Throwable -> L8d
            r6[r4] = r7     // Catch: java.lang.NoSuchMethodException -> L26 java.lang.Throwable -> L8d
            java.lang.Class r7 = java.lang.Boolean.TYPE     // Catch: java.lang.NoSuchMethodException -> L26 java.lang.Throwable -> L8d
            r6[r3] = r7     // Catch: java.lang.NoSuchMethodException -> L26 java.lang.Throwable -> L8d
            java.lang.Class r7 = java.lang.Boolean.TYPE     // Catch: java.lang.NoSuchMethodException -> L26 java.lang.Throwable -> L8d
            r6[r1] = r7     // Catch: java.lang.NoSuchMethodException -> L26 java.lang.Throwable -> L8d
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r5, r6)     // Catch: java.lang.NoSuchMethodException -> L26 java.lang.Throwable -> L8d
            r0.setAccessible(r3)     // Catch: java.lang.NoSuchMethodException -> L26 java.lang.Throwable -> L8d
            r8.mMethodSetProgressFromUser = r0     // Catch: java.lang.NoSuchMethodException -> L26 java.lang.Throwable -> L8d
            goto L42
        L26:
            r0 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8d
            r5.<init>()     // Catch: java.lang.Throwable -> L8d
            java.lang.String r6 = "setProgressFromUser e1 "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch: java.lang.Throwable -> L8d
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L8d
            java.lang.StringBuilder r0 = r5.append(r0)     // Catch: java.lang.Throwable -> L8d
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L8d
            com.bydux.api.common.tools.log.BydLogHelper.logD(r0)     // Catch: java.lang.Throwable -> L8d
        L42:
            java.lang.reflect.Method r0 = r8.mMethodSetProgressFromUser     // Catch: java.lang.Throwable -> L8d
            if (r0 == 0) goto L7f
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.IllegalAccessException -> L5e java.lang.reflect.InvocationTargetException -> L60 java.lang.IllegalArgumentException -> L62 java.lang.Throwable -> L8d
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.IllegalAccessException -> L5e java.lang.reflect.InvocationTargetException -> L60 java.lang.IllegalArgumentException -> L62 java.lang.Throwable -> L8d
            r2[r4] = r9     // Catch: java.lang.IllegalAccessException -> L5e java.lang.reflect.InvocationTargetException -> L60 java.lang.IllegalArgumentException -> L62 java.lang.Throwable -> L8d
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r10)     // Catch: java.lang.IllegalAccessException -> L5e java.lang.reflect.InvocationTargetException -> L60 java.lang.IllegalArgumentException -> L62 java.lang.Throwable -> L8d
            r2[r3] = r9     // Catch: java.lang.IllegalAccessException -> L5e java.lang.reflect.InvocationTargetException -> L60 java.lang.IllegalArgumentException -> L62 java.lang.Throwable -> L8d
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r4)     // Catch: java.lang.IllegalAccessException -> L5e java.lang.reflect.InvocationTargetException -> L60 java.lang.IllegalArgumentException -> L62 java.lang.Throwable -> L8d
            r2[r1] = r9     // Catch: java.lang.IllegalAccessException -> L5e java.lang.reflect.InvocationTargetException -> L60 java.lang.IllegalArgumentException -> L62 java.lang.Throwable -> L8d
            r0.invoke(r8, r2)     // Catch: java.lang.IllegalAccessException -> L5e java.lang.reflect.InvocationTargetException -> L60 java.lang.IllegalArgumentException -> L62 java.lang.Throwable -> L8d
            goto L88
        L5e:
            r9 = move-exception
            goto L63
        L60:
            r9 = move-exception
            goto L63
        L62:
            r9 = move-exception
        L63:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8d
            r10.<init>()     // Catch: java.lang.Throwable -> L8d
            java.lang.String r0 = "setProgressFromUser e2 "
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch: java.lang.Throwable -> L8d
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L8d
            java.lang.StringBuilder r9 = r10.append(r9)     // Catch: java.lang.Throwable -> L8d
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L8d
            com.bydux.api.common.tools.log.BydLogHelper.logD(r9)     // Catch: java.lang.Throwable -> L8d
            goto L88
        L7f:
            java.lang.String r10 = "setProgressFromUser null "
            com.bydux.api.common.tools.log.BydLogHelper.logD(r10)     // Catch: java.lang.Throwable -> L8d
            super.setProgress(r9)     // Catch: java.lang.Throwable -> L8d
        L88:
            r8.refreshThumb()     // Catch: java.lang.Throwable -> L8d
            monitor-exit(r8)
            return
        L8d:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.widget.CustomVerticalSeekBar.setProgressFromUser(int, boolean):void");
    }

    private void refreshThumb() {
        onSizeChanged(super.getWidth(), super.getHeight(), 0, 0);
    }

    public boolean isTouchEventInView(View view, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        return x >= ((float) view.getLeft()) && x < ((float) view.getRight()) && y >= ((float) (view.getTop() + (-20))) && y < ((float) (view.getBottom() + 20));
    }
}
