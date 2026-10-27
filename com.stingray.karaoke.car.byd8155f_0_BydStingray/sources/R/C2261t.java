package r;

import N1.C0420a;
import a.AbstractC0904a;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import n.AbstractC1850a;

/* JADX INFO: renamed from: r.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2261t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f21558a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0420a f21559b;

    public C2261t(TextView textView) {
        this.f21558a = textView;
        C0420a c0420a = new C0420a();
        c0420a.l = new V1.f(textView);
        this.f21559b = c0420a;
    }

    public final void a(AttributeSet attributeSet, int i7) {
        TypedArray typedArrayObtainStyledAttributes = this.f21558a.getContext().obtainStyledAttributes(attributeSet, AbstractC1850a.f19356g, i7, 0);
        try {
            boolean z6 = typedArrayObtainStyledAttributes.hasValue(14) ? typedArrayObtainStyledAttributes.getBoolean(14, true) : true;
            typedArrayObtainStyledAttributes.recycle();
            ((AbstractC0904a) this.f21559b.l).R(z6);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }
}
