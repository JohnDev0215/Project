package defpackage;

import android.view.DisplayCutout;
import java.util.Objects;

/* JADX INFO: compiled from: DisplayCutoutCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class ab0 {
    public final DisplayCutout a;

    public ab0(DisplayCutout displayCutout) {
        this.a = displayCutout;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ab0.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.a, ((ab0) obj).a);
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
