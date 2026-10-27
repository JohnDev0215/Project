package com.amazon.device.ads;

import androidx.core.app.NotificationCompat;
import com.amazon.device.ads.Configuration;
import com.amazon.device.ads.WebRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class SISRequestor {
    private final Configuration configuration;
    private final Settings settings;
    private final SISRequestorCallback sisRequestorCallback;
    private final SISRequest[] sisRequests;
    private final SystemTime systemTime;
    private final WebRequest.WebRequestFactory webRequestFactory;

    public SISRequestor(SISRequestorCallback sISRequestorCallback, SISRequest... sISRequestArr) {
        this(new WebRequest.WebRequestFactory(), sISRequestorCallback, Configuration.getInstance(), Settings.getInstance(), new SystemTime(), sISRequestArr);
    }

    SISRequestor(WebRequest.WebRequestFactory webRequestFactory, SISRequestorCallback sISRequestorCallback, Configuration configuration, Settings settings, SystemTime systemTime, SISRequest... sISRequestArr) {
        this.webRequestFactory = webRequestFactory;
        this.sisRequestorCallback = sISRequestorCallback;
        this.configuration = configuration;
        this.settings = settings;
        this.systemTime = systemTime;
        this.sisRequests = sISRequestArr;
    }

    private String getSISEndPoint() {
        String string = this.configuration.getString(Configuration.ConfigOption.SIS_URL);
        return (string == null || string.isEmpty()) ? "s.amazon-adsystem.com" : string;
    }

    public void startCallSIS() {
        for (SISRequest sISRequest : this.sisRequests) {
            callSIS(sISRequest);
        }
        SISRequestorCallback sisRequestorCallback = getSisRequestorCallback();
        if (sisRequestorCallback != null) {
            sisRequestorCallback.onSISCallComplete();
        }
    }

    private void callSIS(SISRequest sISRequest) {
        try {
            JSONObject asJSON = getWebRequest(sISRequest).makeCall().getResponseReader().readAsJSON();
            if (asJSON == null) {
                return;
            }
            this.settings.putLong("amzn-ad-sis-last-checkin", this.systemTime.currentTimeMillis());
            int integerFromJSON = JSONUtils.getIntegerFromJSON(asJSON, "rcode", 0);
            String stringFromJSON = JSONUtils.getStringFromJSON(asJSON, NotificationCompat.CATEGORY_MESSAGE, "");
            if (integerFromJSON == 1 || integerFromJSON == 103 || (integerFromJSON == 101 && ("103".equals(stringFromJSON) || "need at least one native id in parameter".equals(stringFromJSON)))) {
                sISRequest.getLogger().i("Result - code: %d, msg: %s", Integer.valueOf(integerFromJSON), stringFromJSON);
                sISRequest.onResponseReceived(asJSON);
            } else {
                sISRequest.getLogger().w("Result - code: %d, msg: %s", Integer.valueOf(integerFromJSON), stringFromJSON);
            }
        } catch (WebRequest.WebRequestException unused) {
        }
    }

    private WebRequest getWebRequest(SISRequest sISRequest) {
        WebRequest webRequestCreateWebRequest = this.webRequestFactory.createWebRequest();
        webRequestCreateWebRequest.setExternalLogTag(sISRequest.getLogTag());
        webRequestCreateWebRequest.setHttpMethod(WebRequest.HttpMethod.POST);
        webRequestCreateWebRequest.setHost(getHostname());
        webRequestCreateWebRequest.setPath(getEndpoint(sISRequest));
        webRequestCreateWebRequest.enableLog(true);
        HashMap<String, String> postParameters = sISRequest.getPostParameters();
        if (postParameters != null) {
            for (Map.Entry<String, String> entry : postParameters.entrySet()) {
                webRequestCreateWebRequest.putPostParameter(entry.getKey(), entry.getValue());
            }
        }
        webRequestCreateWebRequest.setQueryStringParameters(sISRequest.getQueryParameters());
        webRequestCreateWebRequest.setMetricsCollector(Metrics.getInstance().getMetricsCollector());
        webRequestCreateWebRequest.setServiceCallLatencyMetric(sISRequest.getCallMetricType());
        return webRequestCreateWebRequest;
    }

    private String getHostname() {
        int iIndexOf;
        String sISEndPoint = getSISEndPoint();
        return (sISEndPoint == null || (iIndexOf = sISEndPoint.indexOf("/")) <= -1) ? sISEndPoint : sISEndPoint.substring(0, iIndexOf);
    }

    private String getEndpoint(SISRequest sISRequest) {
        String sISEndPoint = getSISEndPoint();
        if (sISEndPoint != null) {
            int iIndexOf = sISEndPoint.indexOf("/");
            sISEndPoint = iIndexOf > -1 ? sISEndPoint.substring(iIndexOf) : "";
        }
        return sISEndPoint + "/api3" + sISRequest.getPath();
    }

    private SISRequestorCallback getSisRequestorCallback() {
        return this.sisRequestorCallback;
    }

    static class SISRequestorFactory {
        SISRequestorFactory() {
        }

        public SISRequestor createSISRequestor(SISRequest... sISRequestArr) {
            return createSISRequestor(null, sISRequestArr);
        }

        public SISRequestor createSISRequestor(SISRequestorCallback sISRequestorCallback, SISRequest... sISRequestArr) {
            return new SISRequestor(sISRequestorCallback, sISRequestArr);
        }
    }
}
