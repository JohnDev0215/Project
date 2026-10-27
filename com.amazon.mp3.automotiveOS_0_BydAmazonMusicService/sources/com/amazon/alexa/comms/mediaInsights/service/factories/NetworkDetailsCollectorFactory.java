package com.amazon.alexa.comms.mediaInsights.service.factories;

import android.content.Context;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.NetworkDetailsCollector;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkDetailsCollectorFactory {
    public static NetworkDetailsCollector create(Context context) {
        return new NetworkDetailsCollector(context, "com.amazon.alexa.comms.mediaInsights.service.NETWORK_DETAILS");
    }
}
