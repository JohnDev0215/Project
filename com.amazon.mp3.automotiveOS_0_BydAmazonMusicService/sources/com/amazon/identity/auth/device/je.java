package com.amazon.identity.auth.device;

import android.text.TextUtils;
import com.amazon.identity.auth.device.me;
import com.amazon.minerva.client.thirdparty.api.AmazonMinerva;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.minerva.client.thirdparty.api.Predefined;
import com.google.android.gms.common.internal.ImagesContract;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class je extends me {
    public final MetricEvent l;

    /* JADX INFO: compiled from: DCP */
    public static class a extends me.a<je> {
        @Override // com.amazon.identity.auth.device.me.a
        public me a() {
            return new je(this.f438a, this.b, this.c, this.d, null, null, this.e, this.f, this.g);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public je(String str, String str2, String str3, String str4, String str5, String str6, Long l, Double d, Boolean bool) {
        super(str, str2, str3, str4, null, null, l, d, bool, "20220722N", ga.a());
        boolean z = ha.f;
        MetricEvent metricEvent = new MetricEvent("sxayqhri", "4zkx/2/02330410");
        this.l = metricEvent;
        metricEvent.addPredefined(Predefined.PLATFORM);
        metricEvent.addPredefined(Predefined.BUILD_TYPE);
        metricEvent.addPredefined(Predefined.SOFTWARE_VERSION);
        metricEvent.addPredefined(Predefined.MODEL);
        metricEvent.addPredefined(Predefined.HARDWARE);
        metricEvent.addPredefined(Predefined.TIME_ZONE);
        metricEvent.addPredefined(Predefined.COUNTRY_OF_RESIDENCE);
        metricEvent.addPredefined(Predefined.DEVICE_LANGUAGE);
        metricEvent.addString("map_version", "20220722N");
        metricEvent.addString("client_identifier", ga.a());
        metricEvent.addLong("occurrence", 1L);
        if (!TextUtils.isEmpty(str)) {
            metricEvent.addString("event_name", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            metricEvent.addString("subevent_name", str2);
        }
        if (bool != null) {
            metricEvent.addBoolean("success", bool.booleanValue());
        }
        if (l != null) {
            metricEvent.addLong("count", l.longValue());
        } else {
            metricEvent.addLong("count", 1L);
        }
        if (d != null) {
            metricEvent.addDouble("duration_ms", d.doubleValue());
        }
        if (!TextUtils.isEmpty(str3)) {
            metricEvent.addString("reason_code", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            metricEvent.addString(ImagesContract.URL, str4);
        }
        if (!TextUtils.isEmpty(null)) {
            metricEvent.addString("config_key", null);
        }
        if (TextUtils.isEmpty(null)) {
            return;
        }
        metricEvent.addString("config_value", null);
    }

    @Override // com.amazon.identity.auth.device.me
    public void a() {
        if (pe.f498a == null) {
            yd.b();
        }
        AmazonMinerva amazonMinerva = pe.f498a;
        if (amazonMinerva != null) {
            amazonMinerva.record(this.l);
        }
    }
}
