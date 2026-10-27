package com.spotify.eventsender.corebridge;

import com.spotify.eventsender.api.EventSenderCoreBridge;
import java.nio.charset.Charset;
import p.jw0;
import p.vi0;

/* JADX INFO: loaded from: classes.dex */
public class EventSenderCoreBridgeImpl implements EventSenderCoreBridge {
    private final vi0 mEventPublisher;

    public EventSenderCoreBridgeImpl(vi0 vi0Var) {
        this.mEventPublisher = vi0Var;
    }

    @Override // com.spotify.eventsender.api.EventSenderCoreBridge
    public void queueMetricsDataSnapshotForSending(byte[] bArr, byte[] bArr2) {
    }

    @Override // com.spotify.eventsender.api.EventSenderCoreBridge
    public int send(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        ((jw0) this.mEventPublisher).b(new String(bArr, Charset.forName("UTF-8")), bArr3 == null ? null : new String(bArr3, Charset.forName("UTF-8")), bArr2);
        return 0;
    }
}
