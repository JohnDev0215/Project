package p;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ax2 extends yw2 {
    public ax2(fx2 fx2Var, WindowInsets windowInsets) {
        super(fx2Var, windowInsets);
    }

    @Override // p.dx2
    public fx2 a() {
        return fx2.g(this.c.consumeDisplayCutout(), null);
    }

    @Override // p.dx2
    public ka0 e() {
        DisplayCutout displayCutout = this.c.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new ka0(displayCutout);
    }

    @Override // p.xw2, p.dx2
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ax2)) {
            return false;
        }
        ax2 ax2Var = (ax2) obj;
        return Objects.equals(this.c, ax2Var.c) && Objects.equals(this.g, ax2Var.g);
    }

    @Override // p.dx2
    public int hashCode() {
        return this.c.hashCode();
    }
}
