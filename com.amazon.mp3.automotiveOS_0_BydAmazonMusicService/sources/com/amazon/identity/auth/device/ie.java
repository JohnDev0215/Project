package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.me;
import com.amazon.minerva.client.api.AmazonMinerva;
import com.amazon.minerva.client.api.MetricEvent;
import com.amazon.minerva.client.api.Predefined;
import com.google.android.gms.common.internal.ImagesContract;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ie extends me {
    public final MetricEvent l;

    /* JADX INFO: compiled from: DCP */
    public static class a extends me.a<ie> {
        @Override // com.amazon.identity.auth.device.me.a
        public me a() {
            return new ie(this.f438a, this.b, this.c, this.d, null, null, this.e, this.f, this.g);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ie(String str, String str2, String str3, String str4, String str5, String str6, Long l, Double d, Boolean bool) {
        super(str, str2, str3, str4, null, null, l, d, bool, "20220722N", ga.a());
        boolean z = ha.f;
        MetricEvent metricEvent = new MetricEvent("sxayqhri", "6fb2/2/03330410");
        this.l = metricEvent;
        metricEvent.addPredefined(Predefined.PLATFORM);
        metricEvent.addPredefined(Predefined.BUILD_TYPE);
        metricEvent.addPredefined(Predefined.SOFTWARE_VERSION);
        metricEvent.addPredefined(Predefined.MODEL);
        metricEvent.addPredefined(Predefined.HARDWARE);
        metricEvent.addPredefined(Predefined.DEVICE_TYPE);
        metricEvent.addPredefined(Predefined.TIME_ZONE);
        metricEvent.addPredefined(Predefined.MARKETPLACE_ID);
        metricEvent.addPredefined(Predefined.COUNTRY_OF_RESIDENCE);
        metricEvent.addPredefined(Predefined.DEVICE_LANGUAGE);
        metricEvent.addString("map_version", "20220722N");
        metricEvent.addString("client_identifier", ga.a());
        metricEvent.addLong("occurrence", 1L);
        if (!e.h(str)) {
            metricEvent.addString("event_name", str);
        }
        if (!e.h(str2)) {
            metricEvent.addString("subevent_name", str2);
        }
        if (bool != null) {
            metricEvent.addBoolean("success", bool.booleanValue());
        }
        if (l != null) {
            metricEvent.addLong("count", l.longValue());
        }
        if (d != null) {
            metricEvent.addDouble("duration_ms", d.doubleValue());
        }
        if (!e.h(str3)) {
            metricEvent.addString("reason_code", str3);
        }
        if (!e.h(str4)) {
            metricEvent.addString(ImagesContract.URL, str4);
        }
        if (!e.h((String) null)) {
            metricEvent.addString("config_key", (String) null);
        }
        if (e.h((String) null)) {
            return;
        }
        metricEvent.addString("config_value", (String) null);
    }

    @Override // com.amazon.identity.auth.device.me
    public void a() {
        AmazonMinerva amazonMinerva;
        AmazonMinerva amazonMinerva2;
        synchronized (he.class) {
            if (he.f373a == null) {
                yd.b();
            }
            amazonMinerva = he.f373a;
        }
        if (amazonMinerva != null) {
            synchronized (he.class) {
                if (he.f373a == null) {
                    yd.b();
                }
                amazonMinerva2 = he.f373a;
            }
            amazonMinerva2.record(this.l);
        }
    }
}
