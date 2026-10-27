package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TypedValue f870e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TypedValue f871g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private TypedValue f872h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private TypedValue f873i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private TypedValue f874j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private TypedValue f875k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final Rect f876l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private a f877m;

    public interface a {
        void a();

        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public void a(int i7, int i8, int i9, int i10) {
        this.f876l.set(i7, i8, i9, i10);
        if (i0.u.P(this)) {
            requestLayout();
        }
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f874j == null) {
            this.f874j = new TypedValue();
        }
        return this.f874j;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f875k == null) {
            this.f875k = new TypedValue();
        }
        return this.f875k;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f872h == null) {
            this.f872h = new TypedValue();
        }
        return this.f872h;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f873i == null) {
            this.f873i = new TypedValue();
        }
        return this.f873i;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f870e == null) {
            this.f870e = new TypedValue();
        }
        return this.f870e;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f871g == null) {
            this.f871g = new TypedValue();
        }
        return this.f871g;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = this.f877m;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.f877m;
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
        this.f877m = aVar;
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f876l = new Rect();
    }
}
