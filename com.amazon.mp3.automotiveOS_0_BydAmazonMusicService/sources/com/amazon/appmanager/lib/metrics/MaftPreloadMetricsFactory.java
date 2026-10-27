package com.amazon.appmanager.lib.metrics;

import android.content.Context;
import android.util.Log;
import com.amazon.maft.metrics.PreloadMetrics;
import com.amazon.maft.metrics.PreloadMetricsFactory;
import com.amazon.minerva.client.common.api.AmazonMinerva;
import com.amazon.minerva.client.common.api.AmazonMinervaClientBuilder;
import com.amazon.minerva.client.common.api.MetricEvent;
import com.amazon.minerva.client.common.api.MinervaVersion;
import com.amazon.minerva.client.common.api.MinervaVersionChecker;
import com.amazon.minerva.client.common.transport.OAuthProvider;

/* JADX INFO: loaded from: classes2.dex */
public class MaftPreloadMetricsFactory implements PreloadMetricsFactory {
    static Context appContext;
    private AmazonMinerva minervaClient;

    public MaftPreloadMetricsFactory() {
    }

    public MaftPreloadMetricsFactory(Context context) {
        synchronized (MaftPreloadMetricsFactory.class) {
            if (this.minervaClient == null) {
                MinervaVersionChecker minervaVersionChecker = MinervaVersionChecker.getInstance(context);
                appContext = context.getApplicationContext();
                if (minervaVersionChecker.isVersionSupported(MinervaVersion.BASE)) {
                    this.minervaClient = AmazonMinervaClientBuilder.standard(context).withRegion("us-east-1").withDeviceType("A1MPSLFC7L5AFK").withOAuthProvider(new OAuthProvider() { // from class: com.amazon.appmanager.lib.metrics.MaftPreloadMetricsFactory$$ExternalSyntheticLambda0
                        @Override // com.amazon.minerva.client.common.transport.OAuthProvider
                        public final String getAccessToken() {
                            return MaftPreloadMetricsFactory.lambda$new$0();
                        }
                    }).withChildProfileVerifier(new MaftDefaultChildProfileVerifier()).withUserControlVerifier(new MaftDefaultUserControlVerifier()).withUseDES(true).withRunInitialUpload(true).build();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$new$0() throws Exception {
        throw new Exception("No Access token");
    }

    @Override // com.amazon.maft.metrics.PreloadMetricsFactory
    public PreloadMetrics newPreloadMetrics(String... strArr) {
        final MetricEvent metricEvent = new MetricEvent(strArr[0], strArr[1]);
        return new PreloadMetrics() { // from class: com.amazon.appmanager.lib.metrics.MaftPreloadMetricsFactory.1
            @Override // com.amazon.maft.metrics.PreloadMetrics
            public PreloadMetrics addCount(String str, double d) {
                metricEvent.addDouble(str, d);
                return this;
            }

            @Override // com.amazon.maft.metrics.PreloadMetrics
            public PreloadMetrics addProperty(String str, String str2) {
                metricEvent.addString(str, str2);
                return this;
            }

            @Override // com.amazon.maft.metrics.PreloadMetrics
            public void record() {
                if ((MaftPreloadMetricsFactory.appContext.getApplicationInfo().flags & 2) > 0) {
                    Log.i(MaftPreloadMetricsFactory.class.getSimpleName(), metricEvent.toString());
                }
                MaftPreloadMetricsFactory.this.minervaClient.record(metricEvent);
            }
        };
    }
}
