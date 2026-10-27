package F1;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class Q extends P {
    public Q(V v10, WindowInsets windowInsets) {
        super(v10, windowInsets);
    }

    @Override // F1.U
    public V a() {
        return V.c(this.c.consumeDisplayCutout(), null);
    }

    @Override // F1.U
    public C0249h e() {
        DisplayCutout displayCutout = this.c.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new C0249h(displayCutout);
    }

    @Override // F1.U
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Q)) {
            return false;
        }
        Q q4 = (Q) obj;
        return Objects.equals(this.c, q4.c) && Objects.equals(this.f2315e, q4.f2315e);
    }

    @Override // F1.U
    public int hashCode() {
        return this.c.hashCode();
    }
}
