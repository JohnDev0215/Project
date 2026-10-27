package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TypedValue f452b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TypedValue f453c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TypedValue f454d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TypedValue f455e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TypedValue f456f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TypedValue f457g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Rect f458h;
    private a i;

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

    public void b(int i, int i2, int i3, int i4) {
        this.f458h.set(i, i2, i3, i4);
        if (b.g.k.t.P(this)) {
            requestLayout();
        }
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f456f == null) {
            this.f456f = new TypedValue();
        }
        return this.f456f;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f457g == null) {
            this.f457g = new TypedValue();
        }
        return this.f457g;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f454d == null) {
            this.f454d = new TypedValue();
        }
        return this.f454d;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f455e == null) {
            this.f455e = new TypedValue();
        }
        return this.f455e;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f452b == null) {
            this.f452b = new TypedValue();
        }
        return this.f452b;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f453c == null) {
            this.f453c = new TypedValue();
        }
        return this.f453c;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = this.i;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.i;
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
        this.i = aVar;
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f458h = new Rect();
    }
}
