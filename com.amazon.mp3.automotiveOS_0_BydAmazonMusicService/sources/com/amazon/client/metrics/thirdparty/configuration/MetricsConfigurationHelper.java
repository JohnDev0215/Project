package com.amazon.client.metrics.thirdparty.configuration;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class MetricsConfigurationHelper {
    private final Context mContext;

    public MetricsConfigurationHelper(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context may not be null");
        }
        this.mContext = context;
    }

    public MetricsConfiguration initializeMetricsConfiguration(MetricsConfigurationParser metricsConfigurationParser) throws MetricsConfigurationException {
        try {
            return metricsConfigurationParser.parseConfiguration(getMetricsConfigurationJSONObject(this.mContext.getAssets().open("metrics_configuration")), isDebugFlagEnabled());
        } catch (IOException e) {
            throw new MetricsConfigurationException("An IOException was thrown loading the metrics configuration", e);
        }
    }

    private JSONObject getMetricsConfigurationJSONObject(InputStream inputStream) throws MetricsConfigurationException {
        try {
            return new JSONObject(convertInputStreamToString(inputStream));
        } catch (IOException e) {
            throw new MetricsConfigurationException("IO Exception while parsing the Metrics Configuration", e);
        } catch (JSONException e2) {
            throw new MetricsConfigurationException("JSON Exception while parsing Metrics Configuration", e2);
        }
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                } else {
                    return sb.toString();
                }
            }
        } finally {
            bufferedReader.close();
        }
    }

    protected boolean isDebugFlagEnabled() {
        return (this.mContext.getApplicationInfo().flags & 2) != 0;
    }
}
