package com.android.systemui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.wm.GestureControl;

/* JADX INFO: loaded from: classes.dex */
public class GestureRelativeLayout extends RelativeLayout {
    protected GestureControl gestureControl;
    private boolean mCanDrag;
    private float mLastX;
    private float mLastY;
    protected int mTouchSlop;
    private TempEventListener tempEventListener;
    private WindEventListener windEventListener;

    public interface TempEventListener {
        void handTempEvent(float moveY);
    }

    public interface WindEventListener {
        void handWindEvent(float moveX);
    }

    public GestureRelativeLayout(Context context) {
        super(context);
        this.mCanDrag = false;
        onCreate();
    }

    public GestureRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCanDrag = false;
        onCreate();
    }

    public GestureRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCanDrag = false;
        onCreate();
    }

    public void setTempEventListener(TempEventListener tempEventListener) {
        this.tempEventListener = tempEventListener;
    }

    public void setWindEventListener(WindEventListener windEventListener) {
        this.windEventListener = windEventListener;
    }

    protected final void onCreate() {
        this.mTouchSlop = 106;
        this.gestureControl = (GestureControl) Dependency.get(GestureControl.class);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0056  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionIndex()
            int r1 = r6.getPointerCount()
            int r2 = r6.getActionMasked()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "action:"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r3 = " , pointerCount:"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r1)
            java.lang.String r3 = " , actionIndex:"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.bydux.api.common.tools.log.BydLogHelper.logV(r0)
            int r0 = r6.getActionMasked()
            if (r0 == 0) goto L5a
            r2 = 1
            if (r0 == r2) goto L56
            r2 = 2
            if (r0 == r2) goto L52
            r3 = 3
            if (r0 == r3) goto L56
            r3 = 4
            if (r0 == r3) goto L56
            r3 = 5
            if (r0 == r3) goto L4c
            goto L5d
        L4c:
            if (r1 <= r2) goto L5d
            r0 = 0
            r5.mCanDrag = r0
            goto L5d
        L52:
            r5.doMove(r6)
            goto L5d
        L56:
            r5.doUp()
            goto L5d
        L5a:
            r5.doDown(r6)
        L5d:
            boolean r6 = super.onTouchEvent(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.widget.GestureRelativeLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void doDown(MotionEvent event) {
        this.gestureControl.cancelDismissMessage();
        this.mCanDrag = true;
        this.mLastX = event.getX();
        this.mLastY = event.getY();
    }

    private void doMove(MotionEvent event) {
        if (event.getPointerCount() == 1 && this.mCanDrag) {
            float x = event.getX();
            float y = event.getY();
            float f = x - this.mLastX;
            float f2 = y - this.mLastY;
            if (Math.abs(f) >= Math.abs(f2)) {
                if (Math.abs(f) > this.mTouchSlop) {
                    this.mLastX = x;
                    this.mLastY = y;
                    handWindEvent(f);
                    return;
                }
                return;
            }
            if (Math.abs(f2) > this.mTouchSlop) {
                this.mLastX = x;
                this.mLastY = y;
                handTempEvent(f2);
            }
        }
    }

    private void doUp() {
        this.mCanDrag = false;
        this.gestureControl.handlerUp();
    }

    public void handWindEvent(float moveX) {
        WindEventListener windEventListener = this.windEventListener;
        if (windEventListener != null) {
            windEventListener.handWindEvent(moveX);
        }
    }

    public void handTempEvent(float moveY) {
        TempEventListener tempEventListener = this.tempEventListener;
        if (tempEventListener != null) {
            tempEventListener.handTempEvent(moveY);
        }
    }
}
