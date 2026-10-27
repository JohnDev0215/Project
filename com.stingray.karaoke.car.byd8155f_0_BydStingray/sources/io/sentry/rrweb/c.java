package io.sentry.rrweb;

import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public enum c implements InterfaceC1485m0 {
    DomContentLoaded,
    Load,
    FullSnapshot,
    IncrementalSnapshot,
    Meta,
    Custom,
    Plugin;

    @Override // io.sentry.InterfaceC1485m0
    public void serialize(C0 c02, K k10) throws IOException {
        ((C1489n1) c02).H(ordinal());
    }
}
