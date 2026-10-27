package com.amazon.minerva.client.thirdparty.transport;

import com.amazon.ion.IonString;
import com.amazon.ion.IonSymbol;

/* JADX INFO: loaded from: classes3.dex */
public class MetricEventResponse {
    private final IonString metricEventId;
    private final IonSymbol status;

    public MetricEventResponse(IonString ionString, IonSymbol ionSymbol) {
        this.metricEventId = ionString;
        this.status = ionSymbol;
    }

    public IonString getMetricEventId() {
        return this.metricEventId;
    }

    public IonSymbol getStatus() {
        return this.status;
    }
}
