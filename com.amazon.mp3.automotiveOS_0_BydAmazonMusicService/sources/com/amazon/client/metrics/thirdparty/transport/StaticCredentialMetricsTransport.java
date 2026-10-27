package com.amazon.client.metrics.thirdparty.transport;

import android.content.Context;
import android.util.Log;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: loaded from: classes2.dex */
public class StaticCredentialMetricsTransport extends AbstractHTTPMetricsTransport {
    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public void shutdown() {
    }

    public StaticCredentialMetricsTransport(Context context, MetricsConfiguration metricsConfiguration, DeviceUtil deviceUtil, MetricsTransport metricsTransport) {
        super(context, metricsConfiguration, deviceUtil, metricsTransport);
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport
    protected HttpURLConnection openConnection(URL url) throws IOException {
        return (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection()));
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport
    protected URL resolveEndpoint() {
        try {
            return new URL(this.mMetricsConfiguration.getHttpConfiguration().getStaticCredentialUrlEndpoint() + "/metricsBatch");
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Malformed URL supplied: " + this.mMetricsConfiguration.getHttpConfiguration().getStaticCredentialUrlEndpoint(), e);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport
    protected boolean signRequest(HttpURLConnection httpURLConnection) {
        String strFetchStaticCredential = this.mDeviceUtil.fetchStaticCredential();
        if (strFetchStaticCredential != null && !strFetchStaticCredential.isEmpty()) {
            httpURLConnection.addRequestProperty("x-credential-token", strFetchStaticCredential);
            return true;
        }
        Log.e(this.TAG, "Static token cannot be null or empty");
        return false;
    }
}
