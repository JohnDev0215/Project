package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import r.InterfaceC2223K;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {
    public TypedValue l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public TypedValue f11343m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public TypedValue f11344n;
    public TypedValue o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public TypedValue f11345p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public TypedValue f11346q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Rect f11347r;

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f11347r = new Rect();
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f11345p == null) {
            this.f11345p = new TypedValue();
        }
        return this.f11345p;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f11346q == null) {
            this.f11346q = new TypedValue();
        }
        return this.f11346q;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f11344n == null) {
            this.f11344n = new TypedValue();
        }
        return this.f11344n;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.o == null) {
            this.o = new TypedValue();
        }
        return this.o;
    }

    public TypedValue getMinWidthMajor() {
        if (this.l == null) {
            this.l = new TypedValue();
        }
        return this.l;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f11343m == null) {
            this.f11343m = new TypedValue();
        }
        return this.f11343m;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
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

    public void setAttachListener(InterfaceC2223K interfaceC2223K) {
    }
}
