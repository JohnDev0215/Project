package org.chromium.components.browser_ui.bottomsheet;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import defpackage.a8;
import defpackage.ii;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: BottomSheetSwipeDetector.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i extends GestureDetector.SimpleOnGestureListener {
    public final GestureDetector a;
    public final b b;
    public final VelocityTracker c;
    public boolean d;

    /* JADX INFO: compiled from: BottomSheetSwipeDetector.java */
    public class a extends GestureDetector.SimpleOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onDown(MotionEvent motionEvent) {
            if (motionEvent == null) {
                return false;
            }
            return ((BottomSheet) i.this.b).t(motionEvent);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent != null) {
                i iVar = i.this;
                if (((BottomSheet) iVar.b).t(motionEvent2) && iVar.d) {
                    iVar.d = false;
                    b bVar = iVar.b;
                    BottomSheet bottomSheet = (BottomSheet) bVar;
                    bottomSheet.q(true, a8.o((((-f2) * 218.0f) / 2000.0f) + ((BottomSheet) bVar).p, bottomSheet.f(), ((BottomSheet) bVar).c() * r0.n));
                    return true;
                }
            }
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final void onLongPress(MotionEvent motionEvent) {
            i.this.d = false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent != null) {
                i iVar = i.this;
                if (((BottomSheet) iVar.b).t(motionEvent2)) {
                    float fAbs = Math.abs(f) > 0.0f ? Math.abs(f2) / Math.abs(f) : 2.0f;
                    boolean z = iVar.d;
                    VelocityTracker velocityTracker = iVar.c;
                    if (!z && fAbs < 2.0f) {
                        velocityTracker.clear();
                        return false;
                    }
                    velocityTracker.addMovement(motionEvent2);
                    b bVar = iVar.b;
                    BottomSheet bottomSheet = (BottomSheet) bVar;
                    boolean zH = a8.h(bottomSheet.p, bottomSheet.c() * bottomSheet.n);
                    BottomSheet bottomSheet2 = (BottomSheet) bVar;
                    TouchRestrictingFrameLayout touchRestrictingFrameLayout = bottomSheet2.w;
                    int[] iArr = bottomSheet2.h;
                    touchRestrictingFrameLayout.getLocationOnScreen(iArr);
                    if (!(((float) (bottomSheet2.w.getHeight() + iArr[1])) > motionEvent2.getRawY()) && zH) {
                        ii iiVar = ((BottomSheet) bVar).t;
                        if (!(iiVar == null || iiVar.m() <= 0)) {
                            return false;
                        }
                    }
                    if (zH && f2 > 0.0f) {
                        return false;
                    }
                    BottomSheet bottomSheet3 = (BottomSheet) bVar;
                    if (a8.h(bottomSheet3.p, bottomSheet3.f()) && f2 < 0.0f) {
                        return false;
                    }
                    BottomSheet bottomSheet4 = (BottomSheet) bVar;
                    float f3 = bottomSheet4.p + f2;
                    iVar.d = true;
                    bottomSheet4.q(false, a8.o(f3, bottomSheet4.f(), ((BottomSheet) bVar).c() * r6.n));
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: BottomSheetSwipeDetector.java */
    public interface b {
    }

    public i(Context context, b bVar) {
        GestureDetector gestureDetector = new GestureDetector(context, new a(), ThreadUtils.c());
        this.a = gestureDetector;
        gestureDetector.setIsLongpressEnabled(true);
        this.b = bVar;
        this.c = VelocityTracker.obtain();
    }
}
