package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import o0.t;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TypedValue f602d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TypedValue f603e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TypedValue f604f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TypedValue f605g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TypedValue f606h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public TypedValue f607i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Rect f608j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public a f609k;

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

    public void b(int i8, int i9, int i10, int i11) {
        this.f608j.set(i8, i9, i10, i11);
        if (t.Q(this)) {
            requestLayout();
        }
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f606h == null) {
            this.f606h = new TypedValue();
        }
        return this.f606h;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f607i == null) {
            this.f607i = new TypedValue();
        }
        return this.f607i;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f604f == null) {
            this.f604f = new TypedValue();
        }
        return this.f604f;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f605g == null) {
            this.f605g = new TypedValue();
        }
        return this.f605g;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f602d == null) {
            this.f602d = new TypedValue();
        }
        return this.f602d;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f603e == null) {
            this.f603e = new TypedValue();
        }
        return this.f603e;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = this.f609k;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.f609k;
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
        this.f609k = aVar;
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f608j = new Rect();
    }
}
