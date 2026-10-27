package android.view;

import android.graphics.Rect;
import android.graphics.Region;
import android.util.ArrayMap;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes3.dex */
public class TouchDelegate {
    public static final int ABOVE = 1;
    public static final int BELOW = 2;
    public static final int TO_LEFT = 4;
    public static final int TO_RIGHT = 8;
    private Rect mBounds;
    private boolean mDelegateTargeted;
    private View mDelegateView;
    private int mSlop;
    private Rect mSlopBounds;
    private AccessibilityNodeInfo.TouchDelegateInfo mTouchDelegateInfo;

    public TouchDelegate(Rect rect, View view) {
        this.mBounds = rect;
        this.mSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        Rect rect2 = new Rect(rect);
        this.mSlopBounds = rect2;
        int i = this.mSlop;
        rect2.inset(-i, -i);
        this.mDelegateView = view;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            float r0 = r8.getX()
            int r0 = (int) r0
            float r1 = r8.getY()
            int r1 = (int) r1
            int r2 = r8.getActionMasked()
            r3 = 1
            r4 = 0
            r5 = 2
            if (r2 == 0) goto L33
            if (r2 == r3) goto L27
            if (r2 == r5) goto L27
            r6 = 3
            if (r2 == r6) goto L22
            r6 = 5
            if (r2 == r6) goto L27
            r6 = 6
            if (r2 == r6) goto L27
            r0 = r4
            goto L3b
        L22:
            boolean r0 = r7.mDelegateTargeted
            r7.mDelegateTargeted = r4
            goto L3b
        L27:
            boolean r2 = r7.mDelegateTargeted
            if (r2 == 0) goto L31
            android.graphics.Rect r3 = r7.mSlopBounds
            boolean r3 = r3.contains(r0, r1)
        L31:
            r0 = r2
            goto L3b
        L33:
            android.graphics.Rect r2 = r7.mBounds
            boolean r0 = r2.contains(r0, r1)
            r7.mDelegateTargeted = r0
        L3b:
            if (r0 == 0) goto L61
            if (r3 == 0) goto L53
            android.view.View r0 = r7.mDelegateView
            int r0 = r0.getWidth()
            int r0 = r0 / r5
            float r0 = (float) r0
            android.view.View r1 = r7.mDelegateView
            int r1 = r1.getHeight()
            int r1 = r1 / r5
            float r1 = (float) r1
            r8.setLocation(r0, r1)
            goto L5b
        L53:
            int r0 = r7.mSlop
            int r0 = r0 * r5
            int r0 = -r0
            float r0 = (float) r0
            r8.setLocation(r0, r0)
        L5b:
            android.view.View r7 = r7.mDelegateView
            boolean r4 = r7.dispatchTouchEvent(r8)
        L61:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.TouchDelegate.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onTouchExplorationHoverEvent(MotionEvent motionEvent) {
        if (this.mBounds == null) {
            return false;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        boolean zContains = this.mBounds.contains(x, y);
        int actionMasked = motionEvent.getActionMasked();
        boolean z = true;
        if (actionMasked != 7) {
            if (actionMasked == 9) {
                this.mDelegateTargeted = zContains;
            } else if (actionMasked == 10) {
                this.mDelegateTargeted = true;
            }
        } else if (zContains) {
            this.mDelegateTargeted = true;
        } else if (this.mDelegateTargeted && !this.mSlopBounds.contains(x, y)) {
            z = false;
        }
        if (!this.mDelegateTargeted) {
            return false;
        }
        if (z) {
            motionEvent.setLocation(this.mDelegateView.getWidth() / 2, this.mDelegateView.getHeight() / 2);
        } else {
            this.mDelegateTargeted = false;
        }
        return this.mDelegateView.dispatchHoverEvent(motionEvent);
    }

    public AccessibilityNodeInfo.TouchDelegateInfo getTouchDelegateInfo() {
        if (this.mTouchDelegateInfo == null) {
            ArrayMap arrayMap = new ArrayMap(1);
            Rect rect = this.mBounds;
            if (rect == null) {
                rect = new Rect();
            }
            arrayMap.put(new Region(rect), this.mDelegateView);
            this.mTouchDelegateInfo = new AccessibilityNodeInfo.TouchDelegateInfo(arrayMap);
        }
        return this.mTouchDelegateInfo;
    }
}
