package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import b.f.m.s;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {
    public TypedValue E3;
    public final Rect F3;
    public a G3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TypedValue f224c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TypedValue f225d;
    public TypedValue s;
    public TypedValue x;
    public TypedValue y;

    public interface a {
        void a();

        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public void a(Rect rect) {
        fitSystemWindows(rect);
    }

    public void b(int i2, int i3, int i4, int i5) {
        this.F3.set(i2, i3, i4, i5);
        if (s.G(this)) {
            requestLayout();
        }
    }

    public TypedValue getFixedHeightMajor() {
        if (this.y == null) {
            this.y = new TypedValue();
        }
        return this.y;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.E3 == null) {
            this.E3 = new TypedValue();
        }
        return this.E3;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.s == null) {
            this.s = new TypedValue();
        }
        return this.s;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.x == null) {
            this.x = new TypedValue();
        }
        return this.x;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f224c == null) {
            this.f224c = new TypedValue();
        }
        return this.f224c;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f225d == null) {
            this.f225d = new TypedValue();
        }
        return this.f225d;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = this.G3;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.G3;
        if (aVar != null) {
            aVar.onDetachedFromWindow();
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
        this.G3 = aVar;
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.F3 = new Rect();
    }
}
