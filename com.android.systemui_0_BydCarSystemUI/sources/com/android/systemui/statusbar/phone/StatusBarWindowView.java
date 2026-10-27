package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.wm.AirPanelController;
import com.android.systemui.statusbar.wm.GestureControl;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class StatusBarWindowView extends FrameLayout {
    public static final boolean DEBUG = false;
    public static final String TAG = "StatusBarWindowView";
    private AirPanelController mAirPanelController;
    private boolean mExpandingBelowNotch;
    private GestureControl mGestureControl;
    private StatusBar mService;
    private PhoneStatusBarView mStatusBarView;
    private boolean mTouchActive;
    private boolean mTouchCancelled;

    public static class CancelTouchEvent {
    }

    private boolean isLimit(int count) {
        return count == 1;
    }

    public StatusBarWindowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setMotionEventSplittingEnabled(false);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new FrameLayout.LayoutParams(getContext(), attrs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateDefaultLayoutParams() {
        return new FrameLayout.LayoutParams(-1, -1);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    void onBootCompleted() {
        this.mAirPanelController = (AirPanelController) Dependency.get(AirPanelController.class);
        this.mGestureControl = (GestureControl) Dependency.get(GestureControl.class);
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View child) {
        super.onViewAdded(child);
    }

    public void setStatusBarView(PhoneStatusBarView statusBarView) {
        this.mStatusBarView = statusBarView;
    }

    public void setService(StatusBar service) {
        this.mService = service;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003b  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchKeyEvent(android.view.KeyEvent r6) {
        /*
            r5 = this;
            boolean r0 = super.dispatchKeyEvent(r6)
            r1 = 1
            if (r0 == 0) goto L8
            return r1
        L8:
            int r0 = r6.getAction()
            r2 = 0
            if (r0 != 0) goto L11
            r0 = r1
            goto L12
        L11:
            r0 = r2
        L12:
            int r6 = r6.getKeyCode()
            r3 = 4
            java.lang.String r4 = "StatusBarWindowView"
            if (r6 == r3) goto L41
            r1 = 62
            if (r6 == r1) goto L39
            r1 = 82
            if (r6 == r1) goto L32
            r0 = 24
            if (r6 == r0) goto L2c
            r0 = 25
            if (r6 == r0) goto L2c
            goto L40
        L2c:
            java.lang.String r6 = "KEYCODE_VOLUME_UP or KEYCODE_VOLUME_DOWN"
            android.util.Log.d(r4, r6)
            goto L40
        L32:
            if (r0 != 0) goto L39
            java.lang.String r6 = "KEYCODE_MENU"
            android.util.Log.d(r4, r6)
        L39:
            if (r0 != 0) goto L40
            java.lang.String r6 = "KEYCODE_SPACE"
            android.util.Log.d(r4, r6)
        L40:
            return r2
        L41:
            if (r0 != 0) goto L48
            java.lang.String r6 = "KEYCODE_BACK"
            android.util.Log.d(r4, r6)
        L48:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.StatusBarWindowView.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    public void setTouchActive(boolean touchActive) {
        this.mTouchActive = touchActive;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00b6  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r9) {
        /*
            Method dump skipped, instruction units count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.StatusBarWindowView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CancelTouchEvent event) {
        cancelCurrentTouch();
    }

    public void cancelCurrentTouch() {
        Log.d(TAG, "cancelCurrentTouch");
        if (this.mTouchActive) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
            motionEventObtain.setSource(4098);
            dispatchTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
            this.mTouchCancelled = true;
        }
    }

    public void cancelCurrentTouchOnLimit() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        motionEventObtain.setSource(4098);
        dispatchTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        pw.print("  mTouchCancelled=");
        pw.println(this.mTouchCancelled);
        pw.print("  mTouchActive=");
        pw.println(this.mTouchActive);
    }
}
