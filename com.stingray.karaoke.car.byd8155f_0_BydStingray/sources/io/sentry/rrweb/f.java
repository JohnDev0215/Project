package io.sentry.rrweb;

import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public enum f implements InterfaceC1485m0 {
    MouseUp,
    MouseDown,
    Click,
    ContextMenu,
    DblClick,
    Focus,
    Blur,
    TouchStart,
    TouchMove_Departed,
    TouchEnd,
    TouchCancel;

    @Override // io.sentry.InterfaceC1485m0
    public void serialize(C0 c02, K k10) throws IOException {
        ((C1489n1) c02).H(ordinal());
    }
}
