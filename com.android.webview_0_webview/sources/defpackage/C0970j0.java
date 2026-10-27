package defpackage;

import android.graphics.Typeface;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: j0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0970j0 extends AbstractC1140m2 {
    public final /* synthetic */ WeakReference a;
    public final /* synthetic */ C1026k0 b;

    public C0970j0(C1026k0 c1026k0, WeakReference weakReference) {
        this.b = c1026k0;
        this.a = weakReference;
    }

    @Override // defpackage.AbstractC1140m2
    public void c(Typeface typeface) {
        C1026k0 c1026k0 = this.b;
        WeakReference weakReference = this.a;
        if (c1026k0.k) {
            c1026k0.j = typeface;
            TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, c1026k0.i);
            }
        }
    }
}
