package com.byd.viewsup;

import android.content.Context;
import android.util.Log;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes5.dex */
public class BydInterceptHelper {
    public static final String TAG = "BydInterceptHelper";
    private boolean isHorizontal;
    private int mDefaultTouchSlop;
    private ViewGroup mView;
    private float mOriginalX = -1.0f;
    private float mOriginalY = -1.0f;
    private float mDelX = -1.0f;
    private float mDelY = -1.0f;
    private float mTotalX = 0.0f;
    private float mTotalY = 0.0f;
    private boolean mIsBeingDragged = false;

    public BydInterceptHelper(boolean z) {
        this.isHorizontal = false;
        this.isHorizontal = z;
    }

    public BydInterceptHelper(boolean z, ViewGroup viewGroup) {
        this.isHorizontal = false;
        this.isHorizontal = z;
        this.mView = viewGroup;
        this.mDefaultTouchSlop = getDefaultTouchSlop(viewGroup.getContext());
        Log.d(TAG, "default touch slop: " + this.mDefaultTouchSlop);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getAction()
            r1 = 2
            r2 = 1
            if (r0 != r1) goto Ld
            boolean r3 = r5.mIsBeingDragged
            if (r3 == 0) goto Ld
            return r2
        Ld:
            r3 = 0
            r4 = 0
            if (r0 == 0) goto L55
            if (r0 == r2) goto L4e
            if (r0 == r1) goto L19
            r6 = 3
            if (r0 == r6) goto L4e
            goto L67
        L19:
            float r0 = r6.getX()
            float r1 = r5.mOriginalX
            float r0 = r0 - r1
            r5.mDelX = r0
            float r0 = r6.getY()
            float r1 = r5.mOriginalY
            float r0 = r0 - r1
            r5.mDelY = r0
            float r1 = r5.mTotalX
            float r3 = r5.mDelX
            float r1 = r1 + r3
            r5.mTotalX = r1
            float r1 = r5.mTotalY
            float r1 = r1 + r0
            r5.mTotalY = r1
            boolean r1 = r5.isHorizontal
            boolean r0 = r5.isNeedIntercept(r1, r3, r0)
            if (r0 == 0) goto L41
            r5.mIsBeingDragged = r2
        L41:
            float r0 = r6.getX()
            r5.mOriginalX = r0
            float r6 = r6.getY()
            r5.mOriginalY = r6
            goto L67
        L4e:
            r5.mTotalX = r4
            r5.mTotalY = r4
            r5.mIsBeingDragged = r3
            goto L67
        L55:
            r5.mIsBeingDragged = r3
            float r0 = r6.getX()
            r5.mOriginalX = r0
            float r6 = r6.getY()
            r5.mOriginalY = r6
            r5.mTotalX = r4
            r5.mTotalY = r4
        L67:
            boolean r6 = r5.mIsBeingDragged
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.viewsup.BydInterceptHelper.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean isNeedIntercept(boolean r5, float r6, float r7) {
        /*
            r4 = this;
            android.view.ViewGroup r0 = r4.mView
            if (r0 == 0) goto Lf
            android.view.ViewParent r0 = r0.getParent()
            boolean r1 = r0 instanceof com.byd.viewsup.BydScrollerSupportedFrameLayout
            if (r1 == 0) goto Lf
            com.byd.viewsup.BydScrollerSupportedFrameLayout r0 = (com.byd.viewsup.BydScrollerSupportedFrameLayout) r0
            goto L10
        Lf:
            r0 = 0
        L10:
            int r1 = r4.mDefaultTouchSlop
            float r1 = (float) r1
            if (r0 == 0) goto L1d
            float r0 = r0.getScaledTouchSlop()
            float r1 = java.lang.Math.max(r1, r0)
        L1d:
            r0 = 1
            r2 = 0
            r3 = 1073741824(0x40000000, float:2.0)
            if (r5 == 0) goto L45
            float r5 = java.lang.Math.abs(r6)
            int r5 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r5 > 0) goto L36
            float r5 = r4.mTotalX
            float r5 = java.lang.Math.abs(r5)
            float r1 = r1 * r3
            int r5 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r5 <= 0) goto L43
        L36:
            float r5 = java.lang.Math.abs(r6)
            float r6 = java.lang.Math.abs(r7)
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 <= 0) goto L43
            goto L44
        L43:
            r0 = r2
        L44:
            return r0
        L45:
            float r5 = java.lang.Math.abs(r7)
            int r5 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r5 > 0) goto L58
            float r5 = r4.mTotalY
            float r5 = java.lang.Math.abs(r5)
            float r1 = r1 * r3
            int r5 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r5 <= 0) goto L65
        L58:
            float r5 = java.lang.Math.abs(r7)
            float r6 = java.lang.Math.abs(r6)
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 <= 0) goto L65
            goto L66
        L65:
            r0 = r2
        L66:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.viewsup.BydInterceptHelper.isNeedIntercept(boolean, float, float):boolean");
    }

    private int getDefaultTouchSlop(Context context) {
        return ViewConfiguration.get(context).getScaledTouchSlop();
    }
}
