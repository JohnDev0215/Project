package io.sentry.protocol;

import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.Locale;

/* JADX INFO: renamed from: io.sentry.protocol.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1500e implements InterfaceC1485m0 {
    PORTRAIT,
    LANDSCAPE;

    @Override // io.sentry.InterfaceC1485m0
    public void serialize(C0 c02, K k10) {
        ((C1489n1) c02).L(toString().toLowerCase(Locale.ROOT));
    }
}
