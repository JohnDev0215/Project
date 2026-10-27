package com.amazon.commsnetworking;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
class CommsMetricInterceptor implements Interceptor {
    private final String[] RESPONSE_METRICS = {".success", ".redirect", ".fail", ".fault", ".network", ".unknown"};
    private AlexaCommsCoreMetricsService metricsService;

    CommsMetricInterceptor(@NonNull AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
        this.metricsService = alexaCommsCoreMetricsService;
    }

    @VisibleForTesting
    String getMetricNameForTag(RequestTag requestTag) {
        if (requestTag.includeCommsNetworkingPrefix()) {
            return "comms.net." + requestTag.getMetricName();
        }
        return requestTag.getMetricName();
    }

    private String getMetricSuffix(int i) {
        int i2 = i / 100;
        return i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? ".unknown" : ".fault" : ".fail" : ".redirect" : ".success";
    }

    private String recordResponseMetrics(Response response, RequestTag requestTag, Map<String, Object> map) {
        String metricSuffix = getMetricSuffix(response.code());
        String str = getMetricNameForTag(requestTag) + metricSuffix;
        for (String str2 : this.RESPONSE_METRICS) {
            this.metricsService.recordOccurrence(getMetricNameForTag(requestTag) + str2, requestTag.getSource(), metricSuffix.equals(str2), map);
        }
        return str;
    }

    private void recordNetworkErrorInResponseMetrics(RequestTag requestTag, Map<String, Object> map) {
        for (String str : this.RESPONSE_METRICS) {
            this.metricsService.recordOccurrence(getMetricNameForTag(requestTag) + str, requestTag.getSource(), str.equals(".network"), map);
        }
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws Exception {
        RequestTag requestTag;
        Request request = chain.request();
        RequestTag requestTag2 = (RequestTag) request.tag(RequestTag.class);
        Map<String, Object> map = new HashMap<>();
        if (requestTag2 != null) {
            map.put(TetheredMessageKey.requestId, requestTag2.getRequestId());
            map.put(PageUriUtils.SOURCE_QUERY_PARAM, requestTag2.getSource());
            map.put("contentId", requestTag2.getRequestId());
            this.metricsService.recordOccurrence(getMetricNameForTag(requestTag2) + ".call", requestTag2.getSource(), true, map);
        }
        try {
            Response responseProceed = chain.proceed(request);
            if (requestTag2 != null) {
                int iCode = responseProceed.code();
                String metricSuffix = getMetricSuffix(iCode);
                String strRecordResponseMetrics = recordResponseMetrics(responseProceed, requestTag2, map);
                long jReceivedResponseAtMillis = responseProceed.receivedResponseAtMillis() - responseProceed.sentRequestAtMillis();
                String str = getMetricNameForTag(requestTag2) + ".latency";
                AlexaCommsCoreMetricsService alexaCommsCoreMetricsService = this.metricsService;
                requestTag = requestTag2;
                try {
                    alexaCommsCoreMetricsService.recordTimer(alexaCommsCoreMetricsService.createTimer(str, requestTag2.getSource(), jReceivedResponseAtMillis, false, map));
                    if (!".success".equals(metricSuffix) && !".redirect".equals(metricSuffix)) {
                        map.put("statusCode", Integer.valueOf(iCode));
                        map.put("errorSource", responseProceed.message());
                        map.put("contentProvider", Integer.valueOf(iCode));
                        map.put("contentDetails", responseProceed.message());
                        this.metricsService.recordOccurrence(strRecordResponseMetrics + "." + iCode, requestTag.getSource(), true, map);
                    }
                } catch (Exception e) {
                    e = e;
                    if (requestTag != null) {
                        map.put("errorSource", e);
                        map.put("contentDetails", e.getMessage());
                        recordNetworkErrorInResponseMetrics(requestTag, map);
                    }
                    throw e;
                }
            }
            return responseProceed;
        } catch (Exception e2) {
            e = e2;
            requestTag = requestTag2;
        }
    }
}
