package defpackage;

import java.util.Objects;

/* JADX INFO: compiled from: GlobalRenderFrameHostId.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ct0 {
    public final int a;
    public final int b;

    public ct0(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ct0)) {
            return false;
        }
        ct0 ct0Var = (ct0) obj;
        return this.a == ct0Var.a && this.b == ct0Var.b;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.a), Integer.valueOf(this.b));
    }
}
