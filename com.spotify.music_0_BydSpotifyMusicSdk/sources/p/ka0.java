package p;

import android.view.DisplayCutout;

/* JADX INFO: loaded from: classes.dex */
public final class ka0 {
    public final DisplayCutout a;

    public ka0(DisplayCutout displayCutout) {
        this.a = displayCutout;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ka0.class != obj.getClass()) {
            return false;
        }
        return ap1.a(this.a, ((ka0) obj).a);
    }

    public final int hashCode() {
        DisplayCutout displayCutout = this.a;
        if (displayCutout == null) {
            return 0;
        }
        return displayCutout.hashCode();
    }

    public final String toString() {
        return "DisplayCutoutCompat{" + this.a + "}";
    }
}
