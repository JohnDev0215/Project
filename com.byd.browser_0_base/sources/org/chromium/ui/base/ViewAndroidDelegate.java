package org.chromium.ui.base;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import defpackage.od0;
import defpackage.qd0;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;

/* JADX INFO: loaded from: classes3.dex */
public class ViewAndroidDelegate {
    public final ViewGroup b;
    public a f;
    public PointerIcon g;
    public boolean h;
    public final int[] c = new int[2];
    public final org.chromium.base.b<Object> d = new org.chromium.base.b<>();
    public final org.chromium.base.b<b> e = new org.chromium.base.b<>();
    public final od0 a = new od0();

    public interface a {
        boolean n(ViewGroup viewGroup);
    }

    public interface b {
        void b(boolean z, float f);
    }

    public ViewAndroidDelegate(ViewGroup viewGroup) {
        this.b = viewGroup;
    }

    public qd0 a() {
        return null;
    }

    @CalledByNative
    public View acquireView() {
        ViewGroup viewGroup = this.b;
        if (viewGroup == null || viewGroup.getParent() == null) {
            return null;
        }
        View view = new View(viewGroup.getContext());
        viewGroup.addView(view);
        return view;
    }

    @CalledByNative
    public final View getContainerView() {
        return this.b;
    }

    @CalledByNative
    public int[] getDisplayFeature() {
        return null;
    }

    @CalledByNative
    public int getViewportInsetBottom() {
        return 0;
    }

    @CalledByNative
    public final int getXLocationOfContainerViewInWindow() {
        View containerView = getContainerView();
        if (containerView == null) {
            return 0;
        }
        int[] iArr = this.c;
        containerView.getLocationInWindow(iArr);
        return iArr[0];
    }

    @CalledByNative
    public final int getXLocationOnScreen() {
        View containerView = getContainerView();
        if (containerView == null) {
            return 0;
        }
        int[] iArr = this.c;
        containerView.getLocationOnScreen(iArr);
        return iArr[0];
    }

    @CalledByNative
    public final int getYLocationOfContainerViewInWindow() {
        View containerView = getContainerView();
        if (containerView == null) {
            return 0;
        }
        int[] iArr = this.c;
        containerView.getLocationInWindow(iArr);
        return iArr[1];
    }

    @CalledByNative
    public final int getYLocationOnScreen() {
        View containerView = getContainerView();
        if (containerView == null) {
            return 0;
        }
        int[] iArr = this.c;
        containerView.getLocationOnScreen(iArr);
        return iArr[1];
    }

    @CalledByNative
    public final boolean hasFocus() {
        View containerView = getContainerView();
        if (containerView == null) {
            return false;
        }
        if (containerView.isInTouchMode() ? containerView.isFocusableInTouchMode() : containerView.isFocusable()) {
            return containerView.hasFocus();
        }
        return true;
    }

    @CalledByNative
    public final void notifyHoverActionStylusWritable(boolean z) {
        ViewGroup viewGroup = this.b;
        if (z) {
            a aVar = this.f;
            if (aVar != null && aVar.n(viewGroup)) {
                this.h = true;
                return;
            }
        }
        if (this.h && !z) {
            viewGroup.setPointerIcon(this.g);
        }
        this.h = false;
    }

    @CalledByNative
    public void onCursorChanged(int i) {
        int i2 = 1000;
        if (i != 45) {
            switch (i) {
                case 1:
                    i2 = 1007;
                    break;
                case 2:
                    i2 = 1002;
                    break;
                case 3:
                    i2 = 1008;
                    break;
                case 4:
                case 34:
                    i2 = 1004;
                    break;
                case 5:
                    i2 = 1003;
                    break;
                case 6:
                case 13:
                case 15:
                case 18:
                    i2 = 1014;
                    break;
                case 7:
                case 10:
                case 14:
                case 19:
                    i2 = 1015;
                    break;
                case 8:
                case 12:
                case 16:
                    i2 = 1016;
                    break;
                case 9:
                case 11:
                case 17:
                    i2 = 1017;
                    break;
                case 20:
                case 29:
                    i2 = 1013;
                    break;
                case 30:
                    i2 = 1009;
                    break;
                case 31:
                    i2 = 1006;
                    break;
                case 32:
                    i2 = 1001;
                    break;
                case 33:
                    i2 = 1010;
                    break;
                case 35:
                case 38:
                    i2 = 1012;
                    break;
                case 36:
                    i2 = 1011;
                    break;
                case 37:
                    i2 = 0;
                    break;
                case 39:
                    i2 = 1018;
                    break;
                case 40:
                    i2 = 1019;
                    break;
                case 41:
                    i2 = 1020;
                    break;
                case 42:
                    i2 = 1021;
                    break;
            }
        }
        ViewGroup viewGroup = this.b;
        PointerIcon systemIcon = PointerIcon.getSystemIcon(viewGroup.getContext(), i2);
        this.g = systemIcon;
        if (this.h) {
            return;
        }
        viewGroup.setPointerIcon(systemIcon);
    }

    @CalledByNative
    public void onCursorChangedToCustom(Bitmap bitmap, int i, int i2) {
        PointerIcon pointerIconCreate = PointerIcon.create(bitmap, i, i2);
        this.g = pointerIconCreate;
        if (this.h) {
            return;
        }
        this.b.setPointerIcon(pointerIconCreate);
    }

    @CalledByNative
    public void onVerticalScrollDirectionChanged(boolean z, float f) {
        Iterator<b> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((b) aVar.next()).b(z, f);
            }
        }
    }

    @CalledByNative
    public void removeView(View view) {
        ViewGroup viewGroup = this.b;
        if (viewGroup == null) {
            return;
        }
        viewGroup.removeView(view);
    }

    @CalledByNative
    public final void requestDisallowInterceptTouchEvent() {
        ViewGroup viewGroup = this.b;
        if (viewGroup != null) {
            viewGroup.requestDisallowInterceptTouchEvent(true);
        }
    }

    @CalledByNative
    public final void requestFocus() {
        ViewGroup viewGroup = this.b;
        if (viewGroup != null) {
            if (!(viewGroup.isInTouchMode() ? viewGroup.isFocusableInTouchMode() : viewGroup.isFocusable()) || viewGroup.isFocused()) {
                return;
            }
            viewGroup.requestFocus();
        }
    }

    @CalledByNative
    public final void requestUnbufferedDispatch(MotionEvent motionEvent) {
        ViewGroup viewGroup = this.b;
        if (viewGroup != null) {
            for (int i = 0; i < motionEvent.getPointerCount(); i++) {
                if (motionEvent.getToolType(i) == 2) {
                    return;
                }
            }
            viewGroup.requestUnbufferedDispatch(motionEvent);
        }
    }

    @CalledByNative
    public void setViewPosition(View view, float f, float f2, float f3, float f4, int i, int i2) {
        ViewGroup viewGroup = this.b;
        if (viewGroup == null) {
            return;
        }
        int iRound = Math.round(f3);
        int iRound2 = Math.round(f4);
        if (viewGroup.getLayoutDirection() == 1) {
            i = viewGroup.getMeasuredWidth() - Math.round(f3 + f);
        }
        if (iRound + i > viewGroup.getWidth()) {
            iRound = viewGroup.getWidth() - i;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.width = iRound;
        marginLayoutParams.height = iRound2;
        marginLayoutParams.setMarginStart(i);
        marginLayoutParams.topMargin = i2;
        view.setLayoutParams(marginLayoutParams);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00c8  */
    /* JADX WARN: Type inference failed for: r5v7, types: [byte[], java.io.Serializable] */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean startDragAndDrop(android.graphics.Bitmap r26, org.chromium.ui.dragdrop.DropDataAndroid r27, int r28, int r29, int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 791
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.base.ViewAndroidDelegate.startDragAndDrop(android.graphics.Bitmap, org.chromium.ui.dragdrop.DropDataAndroid, int, int, int, int):boolean");
    }

    @CalledByNative
    public void onBackgroundColorChanged(int i) {
    }

    @CalledByNative
    public void onBottomControlsChanged(int i, int i2) {
    }

    @CalledByNative
    public void onTopControlsChanged(int i, int i2, int i3) {
    }
}
