package O3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f5137a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Throwable f5138b;

    public o(a aVar) {
        this.f5137a = aVar;
        this.f5138b = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        a aVar = this.f5137a;
        if (aVar != null && aVar.equals(oVar.f5137a)) {
            return true;
        }
        Throwable th = this.f5138b;
        if (th == null || oVar.f5138b == null) {
            return false;
        }
        return th.toString().equals(th.toString());
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f5137a, this.f5138b});
    }

    public o(Throwable th) {
        this.f5138b = th;
        this.f5137a = null;
    }
}
