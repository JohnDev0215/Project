package com.amazon.weblab.mobile.service;

import android.net.Uri;
import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
class AssignmentsServiceRequest extends ServiceRequest {
    private Collection<String> mWeblabs;

    AssignmentsServiceRequest(IMobileWeblabClientAttributes iMobileWeblabClientAttributes, SessionInfo sessionInfo, CustomerInfo customerInfo, Collection<String> collection) {
        super(iMobileWeblabClientAttributes, sessionInfo, customerInfo);
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("Weblabs can't be null or empty.");
        }
        this.mWeblabs = collection;
    }

    @Override // com.amazon.weblab.mobile.service.ServiceRequest
    void appendParamToUrl(Uri.Builder builder) {
        super.appendParamToUrl(builder);
        builder.appendEncodedPath("assignments/v2");
    }

    @Override // com.amazon.weblab.mobile.service.ServiceRequest
    Map<String, String> getHttpHeaders() {
        Map<String, String> httpHeaders = super.getHttpHeaders();
        httpHeaders.put("content-type", "application/json");
        return httpHeaders;
    }

    @Override // com.amazon.weblab.mobile.service.ServiceRequest
    String constructPayload() throws MobileWeblabException {
        try {
            JSONArray jSONArray = new JSONArray((Collection<?>) this.mWeblabs);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("Weblabs", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new MobileWeblabException("Cannot generate JSON object.", e);
        }
    }
}
