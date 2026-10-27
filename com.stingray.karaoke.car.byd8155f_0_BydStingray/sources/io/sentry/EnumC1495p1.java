package io.sentry;

import java.util.Locale;

/* JADX INFO: renamed from: io.sentry.p1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1495p1 implements InterfaceC1485m0 {
    DEBUG,
    INFO,
    WARNING,
    ERROR,
    FATAL;

    @Override // io.sentry.InterfaceC1485m0
    public void serialize(C0 c02, K k10) {
        ((C1489n1) c02).L(name().toLowerCase(Locale.ROOT));
    }
}
