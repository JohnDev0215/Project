package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TypedValue f470b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TypedValue f471c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TypedValue f472d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TypedValue f473e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TypedValue f474f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TypedValue f475g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Rect f476h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private a f477i;

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
        this.f476h.set(i2, i3, i4, i5);
        if (b.g.k.t.P(this)) {
            requestLayout();
        }
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f474f == null) {
            this.f474f = new TypedValue();
        }
        return this.f474f;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f475g == null) {
            this.f475g = new TypedValue();
        }
        return this.f475g;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f472d == null) {
            this.f472d = new TypedValue();
        }
        return this.f472d;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f473e == null) {
            this.f473e = new TypedValue();
        }
        return this.f473e;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f470b == null) {
            this.f470b = new TypedValue();
        }
        return this.f470b;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f471c == null) {
            this.f471c = new TypedValue();
        }
        return this.f471c;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = this.f477i;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.f477i;
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
    protected void onMeasure(int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public void setAttachListener(a aVar) {
        this.f477i = aVar;
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f476h = new Rect();
    }
}
