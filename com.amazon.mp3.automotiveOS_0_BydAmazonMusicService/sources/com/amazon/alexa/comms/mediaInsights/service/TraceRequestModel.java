package com.amazon.alexa.comms.mediaInsights.service;

import android.text.TextUtils;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.DeviceDetails;
import com.amazon.alexa.comms.mediaInsights.service.deviceDetails.StaticDeviceDetailsDecorator;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class TraceRequestModel {
    private Map<String, String> annotations;

    @NonNull
    private transient DeviceDetails deviceDetails;
    private Map<String, String> principals;
    private long timestamp = System.currentTimeMillis();
    private Object[] traces = {"#STUB"};
    private static final String TRACES_JSON_PLACEHOLDER = String.format("\"traces\":[\"%s\"]", "#STUB");
    private static final List<String> PRINCIPALS_TO_BE_PUBLISHED = Arrays.asList(StaticDeviceDetailsDecorator.StaticDeviceDetailKeys.DSN.name());

    public TraceRequestModel(@NonNull DeviceDetails deviceDetails) {
        if (deviceDetails == null) {
            throw new IllegalArgumentException("deviceDetails is null");
        }
        this.deviceDetails = deviceDetails;
        setAnnotationsAndPrincipals();
    }

    public String toJson(@NonNull Collection<String> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("traces is null");
        }
        return new Gson().toJson(this).replace(TRACES_JSON_PLACEHOLDER, String.format("\"traces\":[%s]", TextUtils.join(",", collection)));
    }

    private void setAnnotationsAndPrincipals() {
        Map<String, String> mapAsMap = this.deviceDetails.asMap();
        if (mapAsMap == null) {
            return;
        }
        for (String str : PRINCIPALS_TO_BE_PUBLISHED) {
            if (mapAsMap.containsKey(str)) {
                if (this.principals == null) {
                    this.principals = new HashMap();
                }
                this.principals.put(str, mapAsMap.get(str));
                mapAsMap.remove(str);
            }
        }
        this.annotations = mapAsMap;
    }
}
