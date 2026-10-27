package p;

import android.graphics.Typeface;
import android.os.Build;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class w9 extends zu {
    public final /* synthetic */ int i;
    public final /* synthetic */ int j;
    public final /* synthetic */ WeakReference k;
    public final /* synthetic */ ca l;

    public w9(ca caVar, int i, int i2, WeakReference weakReference) {
        this.l = caVar;
        this.i = i;
        this.j = i2;
        this.k = weakReference;
    }

    @Override // p.zu
    public final void u0(int i) {
    }

    @Override // p.zu
    public final void v0(Typeface typeface) {
        int i;
        if (Build.VERSION.SDK_INT >= 28 && (i = this.i) != -1) {
            typeface = ba.a(typeface, i, (this.j & 2) != 0);
        }
        ca caVar = this.l;
        if (caVar.m) {
            caVar.l = typeface;
            TextView textView = (TextView) this.k.get();
            if (textView != null) {
                WeakHashMap weakHashMap = zt2.a;
                if (lt2.b(textView)) {
                    textView.post(new x9(textView, typeface, caVar.j));
                } else {
                    textView.setTypeface(typeface, caVar.j);
                }
            }
        }
    }
}
