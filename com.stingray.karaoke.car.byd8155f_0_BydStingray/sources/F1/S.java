package F1;

import android.graphics.Insets;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class S extends Q {
    public y1.b l;

    public S(V v10, WindowInsets windowInsets) {
        super(v10, windowInsets);
        this.l = null;
    }

    @Override // F1.U
    public y1.b f() {
        if (this.l == null) {
            Insets mandatorySystemGestureInsets = this.c.getMandatorySystemGestureInsets();
            this.l = y1.b.a(mandatorySystemGestureInsets.left, mandatorySystemGestureInsets.top, mandatorySystemGestureInsets.right, mandatorySystemGestureInsets.bottom);
        }
        return this.l;
    }

    @Override // F1.U
    public V i(int i7, int i10, int i11, int i12) {
        return V.c(this.c.inset(i7, i10, i11, i12), null);
    }
}
