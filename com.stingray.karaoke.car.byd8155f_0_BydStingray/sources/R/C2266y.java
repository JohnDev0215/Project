package r;

import android.graphics.Typeface;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import x1.AbstractC2663b;

/* JADX INFO: renamed from: r.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2266y extends AbstractC2663b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f21570e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f21571f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ WeakReference f21572g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ C2215C f21573h;

    public C2266y(C2215C c2215c, int i7, int i10, WeakReference weakReference) {
        this.f21573h = c2215c;
        this.f21570e = i7;
        this.f21571f = i10;
        this.f21572g = weakReference;
    }

    @Override // x1.AbstractC2663b
    public final void g(int i7) {
    }

    @Override // x1.AbstractC2663b
    public final void h(Typeface typeface) {
        int i7 = this.f21570e;
        if (i7 != -1) {
            typeface = AbstractC2214B.a(typeface, i7, (this.f21571f & 2) != 0);
        }
        C2215C c2215c = this.f21573h;
        if (c2215c.f21427m) {
            c2215c.l = typeface;
            TextView textView = (TextView) this.f21572g.get();
            if (textView != null) {
                if (textView.isAttachedToWindow()) {
                    textView.post(new P4.a(textView, typeface, c2215c.f21425j));
                } else {
                    textView.setTypeface(typeface, c2215c.f21425j);
                }
            }
        }
    }
}
