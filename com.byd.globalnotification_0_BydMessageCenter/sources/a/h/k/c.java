package a.h.k;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f811a;

    public c(Object obj) {
        this.f811a = obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f811a, ((c) obj).f811a);
    }

    public int hashCode() {
        Object obj = this.f811a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        StringBuilder sbD = b.a.a.a.a.d("DisplayCutoutCompat{");
        sbD.append(this.f811a);
        sbD.append("}");
        return sbD.toString();
    }
}
