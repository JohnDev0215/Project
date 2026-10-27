package com.android.launcher3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewDebug;
import android.view.WindowInsets;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherRootView extends InsettableFrameLayout {

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private static final List<Rect> SYSTEM_GESTURE_EXCLUSION_RECT = Collections.singletonList(new Rect());
    private View mAlignedView;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private final Rect mConsumedInsets;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private boolean mDisallowBackGesture;
    private final Launcher mLauncher;
    private final Paint mOpaquePaint;
    private final Rect mTempRect;
    private WindowStateListener mWindowStateListener;

    public interface WindowStateListener {
        void onWindowFocusChanged(boolean hasFocus);

        void onWindowVisibilityChanged(int visibility);
    }

    public LauncherRootView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTempRect = new Rect();
        this.mConsumedInsets = new Rect();
        Paint paint = new Paint(1);
        this.mOpaquePaint = paint;
        paint.setColor(-16777216);
        paint.setStyle(Paint.Style.FILL);
        this.mLauncher = Launcher.getLauncher(context);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            this.mAlignedView = getChildAt(0);
        }
        super.onFinishInflate();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void handleSystemWindowInsets(android.graphics.Rect r6) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.LauncherRootView.handleSystemWindowInsets(android.graphics.Rect):void");
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        this.mTempRect.set(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(), insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
        handleSystemWindowInsets(this.mTempRect);
        if (Utilities.ATLEAST_Q) {
            return insets.inset(this.mConsumedInsets.left, this.mConsumedInsets.top, this.mConsumedInsets.right, this.mConsumedInsets.bottom);
        }
        return insets.replaceSystemWindowInsets(this.mTempRect);
    }

    @Override // com.android.launcher3.InsettableFrameLayout, com.android.launcher3.Insettable
    public void setInsets(Rect insets) {
        if (insets.equals(this.mInsets)) {
            return;
        }
        super.setInsets(insets);
    }

    public void dispatchInsets() {
        this.mLauncher.updateInsets(this.mInsets);
        super.setInsets(this.mInsets);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mConsumedInsets.right > 0) {
            canvas.drawRect(r0 - this.mConsumedInsets.right, 0.0f, getWidth(), getHeight(), this.mOpaquePaint);
        }
        if (this.mConsumedInsets.left > 0) {
            canvas.drawRect(0.0f, 0.0f, this.mConsumedInsets.left, getHeight(), this.mOpaquePaint);
        }
        if (this.mConsumedInsets.bottom > 0) {
            canvas.drawRect(0.0f, r0 - this.mConsumedInsets.bottom, getWidth(), getHeight(), this.mOpaquePaint);
        }
    }

    public void setWindowStateListener(WindowStateListener listener) {
        this.mWindowStateListener = listener;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        WindowStateListener windowStateListener = this.mWindowStateListener;
        if (windowStateListener != null) {
            windowStateListener.onWindowFocusChanged(hasWindowFocus);
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        WindowStateListener windowStateListener = this.mWindowStateListener;
        if (windowStateListener != null) {
            windowStateListener.onWindowVisibilityChanged(visibility);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        SYSTEM_GESTURE_EXCLUSION_RECT.get(0).set(l, t, r, b);
        setDisallowBackGesture(this.mDisallowBackGesture);
    }

    public void setDisallowBackGesture(boolean disallowBackGesture) {
        List<Rect> listEmptyList;
        if (Utilities.ATLEAST_Q) {
            this.mDisallowBackGesture = disallowBackGesture;
            if (disallowBackGesture) {
                listEmptyList = SYSTEM_GESTURE_EXCLUSION_RECT;
            } else {
                listEmptyList = Collections.emptyList();
            }
            setSystemGestureExclusionRects(listEmptyList);
        }
    }
}
