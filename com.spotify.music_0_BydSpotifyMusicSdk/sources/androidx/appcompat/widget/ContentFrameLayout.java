package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import p.c4;
import p.dz;
import p.g4;
import p.hk0;
import p.hm2;
import p.i60;
import p.s8;
import p.si1;
import p.tu2;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {
    public TypedValue c;
    public TypedValue e;
    public TypedValue k;
    public TypedValue l;
    public TypedValue m;
    public TypedValue n;
    public final Rect o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public dz f9p;

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.o = new Rect();
    }

    public TypedValue getFixedHeightMajor() {
        if (this.m == null) {
            this.m = new TypedValue();
        }
        return this.m;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.n == null) {
            this.n = new TypedValue();
        }
        return this.n;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.k == null) {
            this.k = new TypedValue();
        }
        return this.k;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.l == null) {
            this.l = new TypedValue();
        }
        return this.l;
    }

    public TypedValue getMinWidthMajor() {
        if (this.c == null) {
            this.c = new TypedValue();
        }
        return this.c;
    }

    public TypedValue getMinWidthMinor() {
        if (this.e == null) {
            this.e = new TypedValue();
        }
        return this.e;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        dz dzVar = this.f9p;
        if (dzVar != null) {
            dzVar.getClass();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        g4 g4Var;
        super.onDetachedFromWindow();
        dz dzVar = this.f9p;
        if (dzVar != null) {
            s8 s8Var = (s8) ((hk0) dzVar).e;
            i60 i60Var = s8Var.z;
            if (i60Var != null) {
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) i60Var;
                actionBarOverlayLayout.k();
                ActionMenuView actionMenuView = ((hm2) actionBarOverlayLayout.m).a.c;
                if (actionMenuView != null && (g4Var = actionMenuView.B) != null) {
                    g4Var.c();
                    c4 c4Var = g4Var.B;
                    if (c4Var != null && c4Var.b()) {
                        c4Var.j.dismiss();
                    }
                }
            }
            if (s8Var.E != null) {
                s8Var.t.getDecorView().removeCallbacks(s8Var.F);
                if (s8Var.E.isShowing()) {
                    try {
                        s8Var.E.dismiss();
                    } catch (IllegalArgumentException unused) {
                    }
                }
                s8Var.E = null;
            }
            tu2 tu2Var = s8Var.G;
            if (tu2Var != null) {
                tu2Var.b();
            }
            si1 si1Var = s8Var.x(0).h;
            if (si1Var != null) {
                si1Var.c(true);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00de  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r17, int r18) {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public void setAttachListener(dz dzVar) {
        this.f9p = dzVar;
    }
}
