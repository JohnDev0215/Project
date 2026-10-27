package com.amazon.weblab.mobile.service;

import com.amazon.weblab.mobile.settings.Interval;
import com.amazonaws.http.HttpHeader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
class ServiceResponse {
    private final Interval mCacheControl;
    private final Map<String, ServiceAssignment> mTreatments;

    private ServiceResponse() {
        this.mTreatments = new HashMap();
        this.mCacheControl = null;
    }

    ServiceResponse(Map<String, List<String>> map, String str, String str2) throws IOException {
        if (str == null) {
            throw new IOException("Service payload should only be null if using createEmptyResponse");
        }
        if (map == null) {
            throw new IOException("Headers should not be null");
        }
        List<String> list = map.get(HttpHeader.CONTENT_TYPE);
        if (list == null || list.isEmpty() || map.get(HttpHeader.CONTENT_TYPE).get(0) == null) {
            throw new IOException("Service doesn't return a content type.");
        }
        if (!map.get(HttpHeader.CONTENT_TYPE).get(0).startsWith("application/json")) {
            throw new IOException(String.format("Service returns type %s, expecting %s", map.get(HttpHeader.CONTENT_TYPE).get(0), "application/json"));
        }
        this.mCacheControl = getCacheControlValue(map);
        this.mTreatments = ServiceAssignmentJSONParser.parse(str, str2);
    }

    private Interval getCacheControlValue(Map<String, List<String>> map) throws IOException {
        List<String> list = map.get("Cache-Control");
        if (list == null || list.isEmpty() || list.get(0) == null) {
            throw new IOException("Missing cache control key");
        }
        String str = map.get("Cache-Control").get(0);
        String[] strArrSplit = str.split("=");
        if (strArrSplit.length == 2 && "max-age".equals(strArrSplit[0].trim())) {
            try {
                return new Interval(Long.parseLong(strArrSplit[1].trim()), TimeUnit.SECONDS);
            } catch (NumberFormatException unused) {
            }
        }
        throw new IOException(String.format("Service returns cache control %s, expecting %s.", str, "max-age=<cache_control_value>"));
    }

    Interval getTtl() {
        return this.mCacheControl;
    }

    Map<String, ServiceAssignment> getAssignments() {
        return this.mTreatments;
    }

    static ServiceResponse createEmptyServiceResponse() {
        return new ServiceResponse();
    }
}
