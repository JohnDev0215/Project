package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import l0.t;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TypedValue f501b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TypedValue f502c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TypedValue f503d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TypedValue f504e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TypedValue f505f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TypedValue f506g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Rect f507h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a f508i;

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

    public void b(int i3, int i4, int i5, int i6) {
        this.f507h.set(i3, i4, i5, i6);
        if (t.P(this)) {
            requestLayout();
        }
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f505f == null) {
            this.f505f = new TypedValue();
        }
        return this.f505f;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f506g == null) {
            this.f506g = new TypedValue();
        }
        return this.f506g;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f503d == null) {
            this.f503d = new TypedValue();
        }
        return this.f503d;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f504e == null) {
            this.f504e = new TypedValue();
        }
        return this.f504e;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f501b == null) {
            this.f501b = new TypedValue();
        }
        return this.f501b;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f502c == null) {
            this.f502c = new TypedValue();
        }
        return this.f502c;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = this.f508i;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.f508i;
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
        this.f508i = aVar;
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f507h = new Rect();
    }
}
