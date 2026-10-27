package android.view;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import com.android.internal.util.FastMath;

/* JADX INFO: loaded from: classes3.dex */
public final class ScrollCaptureTarget {
    private final ScrollCaptureCallback mCallback;
    private final View mContainingView;
    private final int mHint;
    private final Rect mLocalVisibleRect;
    private final Point mPositionInWindow;
    private Rect mScrollBounds;
    private final float[] mTmpFloatArr = new float[2];
    private final Matrix mMatrixViewLocalToWindow = new Matrix();
    private final Rect mTmpRect = new Rect();

    public ScrollCaptureTarget(View view, Rect rect, Point point, ScrollCaptureCallback scrollCaptureCallback) {
        this.mContainingView = view;
        this.mHint = view.getScrollCaptureHint();
        this.mCallback = scrollCaptureCallback;
        this.mLocalVisibleRect = rect;
        this.mPositionInWindow = point;
    }

    public int getHint() {
        return this.mHint;
    }

    public ScrollCaptureCallback getCallback() {
        return this.mCallback;
    }

    public View getContainingView() {
        return this.mContainingView;
    }

    public Rect getLocalVisibleRect() {
        return this.mLocalVisibleRect;
    }

    public Point getPositionInWindow() {
        return this.mPositionInWindow;
    }

    public Rect getScrollBounds() {
        return this.mScrollBounds;
    }

    public void setScrollBounds(Rect rect) {
        Rect rectCopyOrNull = Rect.copyOrNull(rect);
        this.mScrollBounds = rectCopyOrNull;
        if (rectCopyOrNull == null || rectCopyOrNull.intersect(0, 0, this.mContainingView.getWidth(), this.mContainingView.getHeight())) {
            return;
        }
        this.mScrollBounds.setEmpty();
    }

    private static void zero(float[] fArr) {
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
    }

    private static void roundIntoPoint(Point point, float[] fArr) {
        point.x = FastMath.round(fArr[0]);
        point.y = FastMath.round(fArr[1]);
    }

    public void updatePositionInWindow() {
        this.mMatrixViewLocalToWindow.reset();
        this.mContainingView.transformMatrixToGlobal(this.mMatrixViewLocalToWindow);
        zero(this.mTmpFloatArr);
        this.mMatrixViewLocalToWindow.mapPoints(this.mTmpFloatArr);
        roundIntoPoint(this.mPositionInWindow, this.mTmpFloatArr);
    }
}
