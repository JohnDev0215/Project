package F1;

import android.view.DisplayCutout;
import java.util.Objects;

/* JADX INFO: renamed from: F1.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0249h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DisplayCutout f2337a;

    public C0249h(DisplayCutout displayCutout) {
        this.f2337a = displayCutout;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0249h.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f2337a, ((C0249h) obj).f2337a);
    }

    public final int hashCode() {
        DisplayCutout displayCutout = this.f2337a;
        if (displayCutout == null) {
            return 0;
        }
        return displayCutout.hashCode();
    }

    public final String toString() {
        return "DisplayCutoutCompat{" + this.f2337a + "}";
    }
}
