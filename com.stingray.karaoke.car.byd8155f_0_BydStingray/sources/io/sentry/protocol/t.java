package io.sentry.protocol;

import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.nio.charset.Charset;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class t implements InterfaceC1485m0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final t f16774m = new t(new UUID(0, 0));
    public final UUID l;

    public t(UUID uuid) {
        this.l = uuid == null ? UUID.randomUUID() : uuid;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && t.class == obj.getClass() && this.l.compareTo(((t) obj).l) == 0;
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        ((C1489n1) c02).L(toString());
    }

    public final String toString() {
        String string = this.l.toString();
        Charset charset = io.sentry.util.i.f16925a;
        if (string.equals("0000-0000")) {
            string = "00000000-0000-0000-0000-000000000000";
        }
        return string.replace("-", "");
    }

    public t(String str) {
        Charset charset = io.sentry.util.i.f16925a;
        str = str.equals("0000-0000") ? "00000000-0000-0000-0000-000000000000" : str;
        str = str.length() == 32 ? new StringBuilder(str).insert(8, "-").insert(13, "-").insert(18, "-").insert(23, "-").toString() : str;
        if (str.length() == 36) {
            this.l = UUID.fromString(str);
            return;
        }
        throw new IllegalArgumentException("String representation of SentryId has either 32 (UUID no dashes) or 36 characters long (completed UUID). Received: ".concat(str));
    }
}
