package io.sentry;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public enum G1 implements InterfaceC1485m0 {
    SESSION,
    BUFFER;

    @Override // io.sentry.InterfaceC1485m0
    public void serialize(C0 c02, K k10) {
        ((C1489n1) c02).L(name().toLowerCase(Locale.ROOT));
    }
}
