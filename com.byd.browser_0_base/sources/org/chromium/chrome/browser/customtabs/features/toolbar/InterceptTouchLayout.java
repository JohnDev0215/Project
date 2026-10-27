package org.chromium.chrome.browser.customtabs.features.toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import defpackage.n3;
import org.chromium.base.ThreadUtils;

/* JADX INFO: loaded from: classes.dex */
class InterceptTouchLayout extends FrameLayout {
    public final GestureDetector e;

    public class a extends GestureDetector.SimpleOnGestureListener {
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (org.chromium.base.library_loader.a.l.d()) {
                n3.X0("CustomTabs.TapUrlBar");
            }
            return super.onSingleTapConfirmed(motionEvent);
        }
    }

    public InterceptTouchLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new GestureDetector(getContext(), new a(), ThreadUtils.c());
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        this.e.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
}
