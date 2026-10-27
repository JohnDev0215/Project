package com.spotify.esperanto.esperantoimpl;

import com.spotify.esperanto.esperanto.Transport;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SchedulingTransport implements Transport {
    private final Scheduler scheduler;
    private final Transport transport;

    public SchedulingTransport(Scheduler scheduler, Transport transport) {
        v03.j(scheduler, "scheduler");
        v03.j(transport, "transport");
        this.scheduler = scheduler;
        this.transport = transport;
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public Single<byte[]> callSingle(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        Single<byte[]> singleUnsubscribeOn = this.transport.callSingle(str, str2, bArr).subscribeOn(this.scheduler).unsubscribeOn(this.scheduler);
        v03.i(singleUnsubscribeOn, "transport.callSingle(ser….unsubscribeOn(scheduler)");
        return singleUnsubscribeOn;
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public Observable<byte[]> callStream(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        Observable<byte[]> observableUnsubscribeOn = this.transport.callStream(str, str2, bArr).subscribeOn(this.scheduler).unsubscribeOn(this.scheduler);
        v03.i(observableUnsubscribeOn, "transport.callStream(ser….unsubscribeOn(scheduler)");
        return observableUnsubscribeOn;
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public byte[] callSync(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        return this.transport.callSync(str, str2, bArr);
    }
}
