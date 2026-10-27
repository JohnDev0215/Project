package com.android.launcher3.util;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.animation.Interpolator;
import com.android.launcher3.Utilities;
import com.android.launcher3.Workspace;
import com.android.launcher3.anim.Interpolators;

/* JADX INFO: loaded from: classes4.dex */
public class WallpaperOffsetInterpolator extends BroadcastReceiver {
    private static final int ANIMATION_DURATION = 250;
    private static final int MIN_PARALLAX_PAGE_SPAN = 4;
    private static final int MSG_APPLY_OFFSET = 3;
    private static final int MSG_JUMP_TO_FINAL = 5;
    private static final int MSG_SET_NUM_PARALLAX = 4;
    private static final int MSG_START_ANIMATION = 1;
    private static final int MSG_UPDATE_OFFSET = 2;
    private static final String TAG = "WPOffsetInterpolator";
    private static final int[] sTempInt = new int[2];
    private final Handler mHandler;
    private final boolean mIsRtl;
    private boolean mLockedToDefaultPage;
    private int mNumScreens;
    private boolean mRegistered = false;
    private boolean mWallpaperIsLiveWallpaper;
    private IBinder mWindowToken;
    private final Workspace mWorkspace;

    public WallpaperOffsetInterpolator(Workspace workspace) {
        this.mWorkspace = workspace;
        this.mIsRtl = Utilities.isRtl(workspace.getResources());
        this.mHandler = new OffsetHandler(workspace.getContext());
    }

    public void setLockToDefaultPage(boolean lockToDefaultPage) {
        this.mLockedToDefaultPage = lockToDefaultPage;
    }

    public boolean isLockedToDefaultPage() {
        return this.mLockedToDefaultPage;
    }

    private void wallpaperOffsetForScroll(int i, int i2, int[] iArr) {
        int i3;
        int i4;
        iArr[1] = 1;
        if (this.mLockedToDefaultPage || i2 <= 1) {
            iArr[0] = this.mIsRtl ? 1 : 0;
            return;
        }
        int iMax = this.mWallpaperIsLiveWallpaper ? i2 : Math.max(4, i2);
        if (this.mIsRtl) {
            i4 = (i2 + 0) - 1;
            i3 = 0;
        } else {
            i3 = (i2 + 0) - 1;
            i4 = 0;
        }
        int scrollForPage = this.mWorkspace.getScrollForPage(i4);
        int scrollForPage2 = this.mWorkspace.getScrollForPage(i3) - scrollForPage;
        if (scrollForPage2 <= 0) {
            iArr[0] = 0;
            return;
        }
        int iBoundToRange = Utilities.boundToRange((i - scrollForPage) - this.mWorkspace.getLayoutTransitionOffsetForPage(0), 0, scrollForPage2);
        iArr[1] = (iMax - 1) * scrollForPage2;
        iArr[0] = (this.mIsRtl ? iArr[1] - ((i2 - 1) * scrollForPage2) : 0) + (iBoundToRange * (i2 - 1));
    }

    public float wallpaperOffsetForScroll(int scroll) {
        wallpaperOffsetForScroll(scroll, getNumScreensExcludingEmpty(), sTempInt);
        return r1[0] / r1[1];
    }

    private int getNumScreensExcludingEmpty() {
        int childCount = this.mWorkspace.getChildCount();
        return (childCount < 4 || !this.mWorkspace.hasExtraEmptyScreen()) ? childCount : childCount - 1;
    }

    public void syncWithScroll() {
        int numScreensExcludingEmpty = getNumScreensExcludingEmpty();
        int scrollX = this.mWorkspace.getScrollX();
        int[] iArr = sTempInt;
        wallpaperOffsetForScroll(scrollX, numScreensExcludingEmpty, iArr);
        Message messageObtain = Message.obtain(this.mHandler, 2, iArr[0], iArr[1], this.mWindowToken);
        int i = this.mNumScreens;
        if (numScreensExcludingEmpty != i) {
            if (i > 0) {
                messageObtain.what = 1;
            }
            this.mNumScreens = numScreensExcludingEmpty;
            updateOffset();
        }
        messageObtain.sendToTarget();
    }

    private void updateOffset() {
        int iMax;
        if (this.mWallpaperIsLiveWallpaper) {
            iMax = this.mNumScreens;
        } else {
            iMax = Math.max(4, this.mNumScreens);
        }
        Message.obtain(this.mHandler, 4, iMax, 0, this.mWindowToken).sendToTarget();
    }

    public void jumpToFinal() {
        Message.obtain(this.mHandler, 5, this.mWindowToken).sendToTarget();
    }

    public void setWindowToken(IBinder token) {
        this.mWindowToken = token;
        if (token == null && this.mRegistered) {
            this.mWorkspace.getContext().unregisterReceiver(this);
            this.mRegistered = false;
        } else {
            if (token == null || this.mRegistered) {
                return;
            }
            this.mWorkspace.getContext().registerReceiver(this, new IntentFilter(Intent.ACTION_WALLPAPER_CHANGED));
            onReceive(this.mWorkspace.getContext(), null);
            this.mRegistered = true;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.mWallpaperIsLiveWallpaper = WallpaperManager.getInstance(this.mWorkspace.getContext()).getWallpaperInfo() != null;
        updateOffset();
    }

    private static class OffsetHandler extends Handler {
        private boolean mAnimating;
        private float mAnimationStartOffset;
        private long mAnimationStartTime;
        private float mCurrentOffset;
        private float mFinalOffset;
        private final Interpolator mInterpolator;
        private float mOffsetX;
        private final WallpaperManager mWM;

        public OffsetHandler(Context context) {
            super(UiThreadHelper.getBackgroundLooper());
            this.mCurrentOffset = 0.5f;
            this.mInterpolator = Interpolators.DEACCEL_1_5;
            this.mWM = WallpaperManager.getInstance(context);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0059  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x007d  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0097  */
        /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r11) {
            /*
                r10 = this;
                java.lang.Object r0 = r11.obj
                android.os.IBinder r0 = (android.os.IBinder) r0
                if (r0 != 0) goto L7
                return
            L7:
                int r1 = r11.what
                r2 = 0
                r3 = 3
                r4 = 1065353216(0x3f800000, float:1.0)
                r5 = 1
                if (r1 == r5) goto L3e
                r6 = 2
                if (r1 == r6) goto L4a
                if (r1 == r3) goto L53
                r3 = 4
                if (r1 == r3) goto L30
                r11 = 5
                if (r1 == r11) goto L1c
                return
            L1c:
                float r11 = r10.mCurrentOffset
                float r1 = r10.mFinalOffset
                int r11 = java.lang.Float.compare(r11, r1)
                if (r11 == 0) goto L2d
                float r11 = r10.mFinalOffset
                r10.mCurrentOffset = r11
                r10.setOffsetSafely(r0)
            L2d:
                r10.mAnimating = r2
                return
            L30:
                int r11 = r11.arg1
                int r11 = r11 - r5
                float r11 = (float) r11
                float r11 = r4 / r11
                r10.mOffsetX = r11
                android.app.WallpaperManager r10 = r10.mWM
                r10.setWallpaperOffsetSteps(r11, r4)
                return
            L3e:
                r10.mAnimating = r5
                float r1 = r10.mCurrentOffset
                r10.mAnimationStartOffset = r1
                long r6 = r11.getWhen()
                r10.mAnimationStartTime = r6
            L4a:
                int r1 = r11.arg1
                float r1 = (float) r1
                int r11 = r11.arg2
                float r11 = (float) r11
                float r1 = r1 / r11
                r10.mFinalOffset = r1
            L53:
                float r11 = r10.mCurrentOffset
                boolean r1 = r10.mAnimating
                if (r1 == 0) goto L7d
                long r6 = android.os.SystemClock.uptimeMillis()
                long r8 = r10.mAnimationStartTime
                long r6 = r6 - r8
                float r1 = (float) r6
                r8 = 1132068864(0x437a0000, float:250.0)
                float r1 = r1 / r8
                android.view.animation.Interpolator r8 = r10.mInterpolator
                float r1 = r8.getInterpolation(r1)
                float r8 = r10.mAnimationStartOffset
                float r9 = r10.mFinalOffset
                float r9 = r9 - r8
                float r9 = r9 * r1
                float r8 = r8 + r9
                r10.mCurrentOffset = r8
                r8 = 250(0xfa, double:1.235E-321)
                int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r1 >= 0) goto L7a
                r2 = r5
            L7a:
                r10.mAnimating = r2
                goto L81
            L7d:
                float r1 = r10.mFinalOffset
                r10.mCurrentOffset = r1
            L81:
                float r1 = r10.mCurrentOffset
                int r11 = java.lang.Float.compare(r1, r11)
                if (r11 == 0) goto L93
                r10.setOffsetSafely(r0)
                android.app.WallpaperManager r11 = r10.mWM
                float r1 = r10.mOffsetX
                r11.setWallpaperOffsetSteps(r1, r4)
            L93:
                boolean r11 = r10.mAnimating
                if (r11 == 0) goto L9e
                android.os.Message r10 = android.os.Message.obtain(r10, r3, r0)
                r10.sendToTarget()
            L9e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.util.WallpaperOffsetInterpolator.OffsetHandler.handleMessage(android.os.Message):void");
        }

        private void setOffsetSafely(IBinder token) {
            try {
                this.mWM.setWallpaperOffsets(token, this.mCurrentOffset, 0.5f);
            } catch (IllegalArgumentException e) {
                Log.e(WallpaperOffsetInterpolator.TAG, "Error updating wallpaper offset: " + e);
            }
        }
    }
}
