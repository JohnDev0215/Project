package androidx.appcompat.widget;

import a.b.k.l;
import a.b.k.o;
import a.b.o.i.g;
import a.b.p.c0;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TypedValue f1437a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TypedValue f1438b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TypedValue f1439c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TypedValue f1440d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TypedValue f1441e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TypedValue f1442f;
    public final Rect g;
    public a h;

    public interface a {
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.g = new Rect();
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f1441e == null) {
            this.f1441e = new TypedValue();
        }
        return this.f1441e;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f1442f == null) {
            this.f1442f = new TypedValue();
        }
        return this.f1442f;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f1439c == null) {
            this.f1439c = new TypedValue();
        }
        return this.f1439c;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f1440d == null) {
            this.f1440d = new TypedValue();
        }
        return this.f1440d;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f1437a == null) {
            this.f1437a = new TypedValue();
        }
        return this.f1437a;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f1438b == null) {
            this.f1438b = new TypedValue();
        }
        return this.f1438b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = this.h;
        if (aVar != null && ((o) aVar) == null) {
            throw null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.h;
        if (aVar != null) {
            l lVar = ((o) aVar).f47a;
            c0 c0Var = lVar.k;
            if (c0Var != null) {
                c0Var.j();
            }
            if (lVar.p != null) {
                lVar.f22e.getDecorView().removeCallbacks(lVar.q);
                if (lVar.p.isShowing()) {
                    try {
                        lVar.p.dismiss();
                    } catch (IllegalArgumentException unused) {
                    }
                }
                lVar.p = null;
            }
            lVar.z();
            g gVar = lVar.E(0).h;
            if (gVar != null) {
                gVar.c(true);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00db  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public void setAttachListener(a aVar) {
        this.h = aVar;
    }
}
