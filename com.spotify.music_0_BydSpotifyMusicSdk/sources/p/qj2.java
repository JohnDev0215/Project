package p;

import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class qj2 {
    public float c;
    public final WeakReference e;
    public kj2 f;
    public final TextPaint a = new TextPaint(1);
    public final ks b = new ks(1, this);
    public boolean d = true;

    public qj2(pj2 pj2Var) {
        this.e = new WeakReference(null);
        this.e = new WeakReference(pj2Var);
    }
}
