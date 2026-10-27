package x1;

import android.content.res.Resources;
import java.util.Objects;

/* JADX INFO: renamed from: x1.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2670i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Resources f23706a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Resources.Theme f23707b;

    public C2670i(Resources resources, Resources.Theme theme) {
        this.f23706a = resources;
        this.f23707b = theme;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2670i.class != obj.getClass()) {
            return false;
        }
        C2670i c2670i = (C2670i) obj;
        return this.f23706a.equals(c2670i.f23706a) && Objects.equals(this.f23707b, c2670i.f23707b);
    }

    public final int hashCode() {
        return Objects.hash(this.f23706a, this.f23707b);
    }
}
