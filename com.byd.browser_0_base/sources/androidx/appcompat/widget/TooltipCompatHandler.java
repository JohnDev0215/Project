package androidx.appcompat.widget;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class TooltipCompatHandler implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    public static TooltipCompatHandler g;
    public static TooltipCompatHandler h;
    public TooltipPopup e;
    public boolean f;

    public TooltipCompatHandler() {
        throw null;
    }

    public static void b(TooltipCompatHandler tooltipCompatHandler) {
        if (g != null) {
            throw null;
        }
        g = tooltipCompatHandler;
        if (tooltipCompatHandler == null) {
            return;
        }
        ViewConfiguration.getLongPressTimeout();
        throw null;
    }

    public final void a() {
        if (h == this) {
            h = null;
            TooltipPopup tooltipPopup = this.e;
            if (tooltipPopup != null) {
                tooltipPopup.getClass();
                throw null;
            }
            Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
        }
        if (g != this) {
            throw null;
        }
        b(null);
        throw null;
    }

    public final void c(boolean z) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.g.b(null)) {
            b(null);
            TooltipCompatHandler tooltipCompatHandler = h;
            if (tooltipCompatHandler != null) {
                tooltipCompatHandler.a();
                throw null;
            }
            h = this;
            this.f = z;
            throw null;
        }
    }

    @Override // android.view.View.OnHoverListener
    public final boolean onHover(View view, MotionEvent motionEvent) {
        if (this.e == null || !this.f) {
            throw null;
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        int width = view.getWidth() / 2;
        int height = view.getHeight() / 2;
        c(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        a();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}
