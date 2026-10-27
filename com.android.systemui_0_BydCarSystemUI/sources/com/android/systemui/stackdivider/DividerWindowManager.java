package com.android.systemui.stackdivider;

import android.graphics.Region;
import android.os.Binder;
import android.view.View;
import android.view.WindowManager;
import com.android.systemui.wm.SystemWindows;

/* JADX INFO: loaded from: classes.dex */
public class DividerWindowManager {
    private static final String WINDOW_TITLE = "DockedStackDivider";
    private WindowManager.LayoutParams mLp;
    final SystemWindows mSystemWindows;
    private View mView;

    public DividerWindowManager(SystemWindows systemWindows) {
        this.mSystemWindows = systemWindows;
    }

    public void add(View view, int width, int height, int displayId) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(width, height, 2034, 545521704, -3);
        this.mLp = layoutParams;
        layoutParams.token = new Binder();
        this.mLp.setTitle(WINDOW_TITLE);
        this.mLp.privateFlags |= 64;
        this.mLp.layoutInDisplayCutoutMode = 3;
        view.setSystemUiVisibility(1792);
        this.mSystemWindows.addView(view, this.mLp, displayId, 2034);
        this.mView = view;
    }

    public void remove() {
        View view = this.mView;
        if (view != null) {
            this.mSystemWindows.removeView(view);
        }
        this.mView = null;
    }

    public void setSlippery(boolean slippery) {
        boolean z = true;
        if (slippery && (this.mLp.flags & 536870912) == 0) {
            WindowManager.LayoutParams layoutParams = this.mLp;
            layoutParams.flags = 536870912 | layoutParams.flags;
        } else if (slippery || (this.mLp.flags & 536870912) == 0) {
            z = false;
        } else {
            this.mLp.flags &= -536870913;
        }
        if (z) {
            this.mSystemWindows.updateViewLayout(this.mView, this.mLp);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setTouchable(boolean r4) {
        /*
            r3 = this;
            android.view.View r0 = r3.mView
            if (r0 != 0) goto L5
            return
        L5:
            r0 = 0
            r1 = 1
            if (r4 != 0) goto L1b
            android.view.WindowManager$LayoutParams r2 = r3.mLp
            int r2 = r2.flags
            r2 = r2 & 16
            if (r2 != 0) goto L1b
            android.view.WindowManager$LayoutParams r4 = r3.mLp
            int r0 = r4.flags
            r0 = r0 | 16
            r4.flags = r0
        L19:
            r0 = r1
            goto L2e
        L1b:
            if (r4 == 0) goto L2e
            android.view.WindowManager$LayoutParams r4 = r3.mLp
            int r4 = r4.flags
            r4 = r4 & 16
            if (r4 == 0) goto L2e
            android.view.WindowManager$LayoutParams r4 = r3.mLp
            int r0 = r4.flags
            r0 = r0 & (-17)
            r4.flags = r0
            goto L19
        L2e:
            if (r0 == 0) goto L39
            com.android.systemui.wm.SystemWindows r4 = r3.mSystemWindows
            android.view.View r0 = r3.mView
            android.view.WindowManager$LayoutParams r1 = r3.mLp
            r4.updateViewLayout(r0, r1)
        L39:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.stackdivider.DividerWindowManager.setTouchable(boolean):void");
    }

    public void setTouchRegion(Region touchRegion) {
        View view = this.mView;
        if (view == null) {
            return;
        }
        this.mSystemWindows.setTouchableRegion(view, touchRegion);
    }
}
