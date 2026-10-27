package org.chromium.ui.base;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import defpackage.AbstractC0940iP;
import defpackage.C1240nt;
import defpackage.WN;
import defpackage.Ws;
import defpackage.XN;
import defpackage.Ys;
import java.util.Iterator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewAndroidDelegate {
    public ViewGroup a;
    public int[] b = new int[2];
    public Ys c = new Ys();

    public ViewAndroidDelegate(ViewGroup viewGroup) {
        this.a = viewGroup;
    }

    public static void fallbackCursorModeLockCursor(boolean z, boolean z2, boolean z3, boolean z4) {
        AbstractC0940iP.b(z, z2, z3, z4);
        throw null;
    }

    public static void fallbackCursorModeSetCursorVisibility(boolean z) {
        AbstractC0940iP.c(z);
        throw null;
    }

    private int getXLocationOfContainerViewInWindow() {
        ViewGroup containerView = getContainerView();
        if (containerView == null) {
            return 0;
        }
        containerView.getLocationInWindow(this.b);
        return this.b[0];
    }

    private int getXLocationOnScreen() {
        ViewGroup containerView = getContainerView();
        if (containerView == null) {
            return 0;
        }
        containerView.getLocationOnScreen(this.b);
        return this.b[0];
    }

    private int getYLocationOfContainerViewInWindow() {
        ViewGroup containerView = getContainerView();
        if (containerView == null) {
            return 0;
        }
        containerView.getLocationInWindow(this.b);
        return this.b[1];
    }

    private int getYLocationOnScreen() {
        ViewGroup containerView = getContainerView();
        if (containerView == null) {
            return 0;
        }
        containerView.getLocationOnScreen(this.b);
        return this.b[1];
    }

    private boolean hasFocus() {
        ViewGroup containerView = getContainerView();
        if (containerView == null) {
            return false;
        }
        return XN.a(containerView);
    }

    public static boolean hasTouchlessEventHandler() {
        AbstractC0940iP.d();
        return false;
    }

    public static boolean onUnconsumedKeyboardEventAck(int i) {
        AbstractC0940iP.e(i);
        throw null;
    }

    private void requestDisallowInterceptTouchEvent() {
        ViewGroup containerView = getContainerView();
        if (containerView != null) {
            containerView.requestDisallowInterceptTouchEvent(true);
        }
    }

    private void requestFocus() {
        ViewGroup containerView = getContainerView();
        if (containerView != null) {
            if (!(containerView.isInTouchMode() ? containerView.isFocusableInTouchMode() : containerView.isFocusable()) || containerView.isFocused()) {
                return;
            }
            containerView.requestFocus();
        }
    }

    private void requestUnbufferedDispatch(MotionEvent motionEvent) {
        ViewGroup containerView = getContainerView();
        if (containerView != null) {
            containerView.requestUnbufferedDispatch(motionEvent);
        }
    }

    private boolean startDragAndDrop(String str, Bitmap bitmap) {
        ViewGroup containerView;
        if (Build.VERSION.SDK_INT <= 23 || (containerView = getContainerView()) == null) {
            return false;
        }
        ImageView imageView = new ImageView(containerView.getContext());
        imageView.setImageBitmap(bitmap);
        imageView.layout(0, 0, bitmap.getWidth(), bitmap.getHeight());
        return C1240nt.i(containerView, ClipData.newPlainText(null, str), new View.DragShadowBuilder(imageView), null, 256);
    }

    public void a() {
    }

    public View acquireView() {
        ViewGroup containerView = getContainerView();
        if (containerView == null || containerView.getParent() == null) {
            return null;
        }
        View view = new View(containerView.getContext());
        containerView.addView(view);
        return view;
    }

    public final void b(ViewGroup viewGroup) {
        ViewGroup viewGroup2 = this.a;
        this.a = viewGroup;
        c(viewGroup2);
        Iterator it = this.c.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((WN) ws.next()).m(viewGroup);
            }
        }
    }

    public void c(ViewGroup viewGroup) {
    }

    public final ViewGroup getContainerView() {
        return this.a;
    }

    public int getViewportInsetBottom() {
        return 0;
    }

    public void onBackgroundColorChanged(int i) {
    }

    public void onBottomControlsChanged(int i, int i2, int i3) {
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onCursorChanged(int r9) {
        /*
            r8 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 24
            if (r0 >= r1) goto L7
            return
        L7:
            r0 = 45
            r1 = 1012(0x3f4, float:1.418E-42)
            r2 = 1004(0x3ec, float:1.407E-42)
            r3 = 1000(0x3e8, float:1.401E-42)
            r4 = 1017(0x3f9, float:1.425E-42)
            r5 = 1016(0x3f8, float:1.424E-42)
            r6 = 1015(0x3f7, float:1.422E-42)
            r7 = 1014(0x3f6, float:1.421E-42)
            if (r9 == r0) goto L58
            switch(r9) {
                case 0: goto L58;
                case 1: goto L55;
                case 2: goto L52;
                case 3: goto L4f;
                case 4: goto L4c;
                case 5: goto L49;
                case 6: goto L46;
                case 7: goto L43;
                case 8: goto L40;
                case 9: goto L3d;
                case 10: goto L43;
                case 11: goto L3d;
                case 12: goto L40;
                case 13: goto L46;
                case 14: goto L43;
                case 15: goto L46;
                case 16: goto L40;
                case 17: goto L3d;
                case 18: goto L46;
                case 19: goto L43;
                case 20: goto L3a;
                case 21: goto L58;
                case 22: goto L58;
                case 23: goto L58;
                case 24: goto L58;
                case 25: goto L58;
                case 26: goto L58;
                case 27: goto L58;
                case 28: goto L58;
                case 29: goto L3a;
                case 30: goto L37;
                case 31: goto L34;
                case 32: goto L31;
                case 33: goto L2e;
                case 34: goto L4c;
                case 35: goto L5a;
                case 36: goto L2b;
                case 37: goto L29;
                case 38: goto L5a;
                case 39: goto L26;
                case 40: goto L23;
                case 41: goto L20;
                case 42: goto L1d;
                default: goto L1c;
            }
        L1c:
            goto L58
        L1d:
            r1 = 1021(0x3fd, float:1.431E-42)
            goto L5a
        L20:
            r1 = 1020(0x3fc, float:1.43E-42)
            goto L5a
        L23:
            r1 = 1019(0x3fb, float:1.428E-42)
            goto L5a
        L26:
            r1 = 1018(0x3fa, float:1.427E-42)
            goto L5a
        L29:
            r1 = 0
            goto L5a
        L2b:
            r1 = 1011(0x3f3, float:1.417E-42)
            goto L5a
        L2e:
            r1 = 1010(0x3f2, float:1.415E-42)
            goto L5a
        L31:
            r1 = 1001(0x3e9, float:1.403E-42)
            goto L5a
        L34:
            r1 = 1006(0x3ee, float:1.41E-42)
            goto L5a
        L37:
            r1 = 1009(0x3f1, float:1.414E-42)
            goto L5a
        L3a:
            r1 = 1013(0x3f5, float:1.42E-42)
            goto L5a
        L3d:
            r1 = 1017(0x3f9, float:1.425E-42)
            goto L5a
        L40:
            r1 = 1016(0x3f8, float:1.424E-42)
            goto L5a
        L43:
            r1 = 1015(0x3f7, float:1.422E-42)
            goto L5a
        L46:
            r1 = 1014(0x3f6, float:1.421E-42)
            goto L5a
        L49:
            r1 = 1003(0x3eb, float:1.406E-42)
            goto L5a
        L4c:
            r1 = 1004(0x3ec, float:1.407E-42)
            goto L5a
        L4f:
            r1 = 1008(0x3f0, float:1.413E-42)
            goto L5a
        L52:
            r1 = 1002(0x3ea, float:1.404E-42)
            goto L5a
        L55:
            r1 = 1007(0x3ef, float:1.411E-42)
            goto L5a
        L58:
            r1 = 1000(0x3e8, float:1.401E-42)
        L5a:
            android.view.ViewGroup r9 = r8.getContainerView()
            android.content.Context r0 = r9.getContext()
            android.view.PointerIcon r0 = android.view.PointerIcon.getSystemIcon(r0, r1)
            defpackage.C1240nt.f(r9, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.base.ViewAndroidDelegate.onCursorChanged(int):void");
    }

    public void onCursorChangedToCustom(Bitmap bitmap, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 24) {
            C1240nt.f(getContainerView(), C1240nt.a(bitmap, i, i2));
        }
    }

    public void onTopControlsChanged(int i, int i2, int i3) {
    }

    public void removeView(View view) {
        ViewGroup containerView = getContainerView();
        if (containerView == null) {
            return;
        }
        containerView.removeView(view);
    }

    public void setViewPosition(View view, float f, float f2, float f3, float f4, int i, int i2) {
        ViewGroup containerView = getContainerView();
        if (containerView == null) {
            return;
        }
        int iRound = Math.round(f3);
        int iRound2 = Math.round(f4);
        if (containerView.getLayoutDirection() == 1) {
            i = containerView.getMeasuredWidth() - Math.round(f3 + f);
        }
        if (iRound + i > containerView.getWidth()) {
            iRound = containerView.getWidth() - i;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.width = iRound;
        marginLayoutParams.height = iRound2;
        marginLayoutParams.setMarginStart(i);
        marginLayoutParams.topMargin = i2;
        view.setLayoutParams(marginLayoutParams);
    }
}
