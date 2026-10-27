package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ImpressionTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class px0 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    public final View e;
    public a f;

    /* JADX INFO: compiled from: ImpressionTracker.java */
    public interface a {
        void c();
    }

    public px0(View view) {
        this.e = view;
    }

    public final void a(ns1 ns1Var) {
        a aVar = this.f;
        View view = this.e;
        if (aVar != null) {
            view.removeOnAttachStateChangeListener(this);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.g.b(view)) {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
            }
        }
        this.f = ns1Var;
        if (ns1Var != null) {
            view.addOnAttachStateChangeListener(this);
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            if (om3.g.b(view)) {
                view.getViewTreeObserver().addOnPreDrawListener(this);
            }
        }
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        View view = this.e;
        ViewParent parent = view.getParent();
        if (parent == null) {
            return true;
        }
        Rect rect = new Rect(0, 0, view.getWidth(), view.getHeight());
        int height = (view.getHeight() * 2) / 3;
        if (!parent.getChildVisibleRect(view, rect, null) || rect.height() < height) {
            return true;
        }
        this.f.c();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.e.getViewTreeObserver().addOnPreDrawListener(this);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.e.getViewTreeObserver().removeOnPreDrawListener(this);
    }
}
