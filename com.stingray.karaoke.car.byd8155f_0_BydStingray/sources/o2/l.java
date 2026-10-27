package o2;

import android.media.metrics.LogSessionId;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f20449a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k f20450b;
    public final Object c;

    static {
        if (AbstractC1343A.f15195a < 31) {
            new l("");
        } else {
            new l(k.f20447b, "");
        }
    }

    public l(String str) {
        AbstractC1346c.j(AbstractC1343A.f15195a < 31);
        this.f20449a = str;
        this.f20450b = null;
        this.c = new Object();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return Objects.equals(this.f20449a, lVar.f20449a) && Objects.equals(this.f20450b, lVar.f20450b) && Objects.equals(this.c, lVar.c);
    }

    public final int hashCode() {
        return Objects.hash(this.f20449a, this.f20450b, this.c);
    }

    public l(LogSessionId logSessionId, String str) {
        this(new k(logSessionId), str);
    }

    public l(k kVar, String str) {
        this.f20450b = kVar;
        this.f20449a = str;
        this.c = new Object();
    }
}
