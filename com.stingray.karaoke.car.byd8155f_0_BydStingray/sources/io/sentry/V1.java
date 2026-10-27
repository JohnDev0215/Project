package io.sentry;

import java.nio.charset.Charset;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class V1 implements InterfaceC1485m0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final V1 f15976m = new V1(new UUID(0, 0));
    public final String l;

    public V1(String str) {
        h6.e.q(str, "value is required");
        this.l = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || V1.class != obj.getClass()) {
            return false;
        }
        return this.l.equals(((V1) obj).l);
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        ((C1489n1) c02).L(this.l);
    }

    public final String toString() {
        return this.l;
    }

    public V1() {
        this(UUID.randomUUID());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public V1(UUID uuid) {
        String string = uuid.toString();
        Charset charset = io.sentry.util.i.f16925a;
        this((string.equals("0000-0000") ? "00000000-0000-0000-0000-000000000000" : string).replace("-", "").substring(0, 16));
    }
}
