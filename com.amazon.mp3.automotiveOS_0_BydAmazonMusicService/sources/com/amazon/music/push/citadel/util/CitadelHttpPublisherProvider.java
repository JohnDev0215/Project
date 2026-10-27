package com.amazon.music.push.citadel.util;

import com.amazon.music.push.util.MetricsUtil;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes4.dex */
public class CitadelHttpPublisherProvider {
    public static final OkHttpClient httpClient = UnifiedOkHttpClient.newClient();

    public static CitadelHttpPublisher getDefaultHttpPublisher() {
        return new CitadelHttpPublisher(httpClient, MetricsUtil.initializeTechnicalCollectionMetrics("com.amazon.music.push.CitadelHttpPublisher"));
    }
}
