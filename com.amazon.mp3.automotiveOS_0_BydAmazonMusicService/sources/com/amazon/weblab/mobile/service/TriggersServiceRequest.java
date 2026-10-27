package com.amazon.weblab.mobile.service;

import android.net.Uri;
import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
class TriggersServiceRequest extends ServiceRequest {
    private Collection<TreatmentAssignment> mTriggers;

    TriggersServiceRequest(IMobileWeblabClientAttributes iMobileWeblabClientAttributes, SessionInfo sessionInfo, CustomerInfo customerInfo, Collection<TreatmentAssignment> collection) {
        super(iMobileWeblabClientAttributes, sessionInfo, customerInfo);
        if (collection == null) {
            throw new IllegalArgumentException("triggers can't null.");
        }
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("triggers can't be empty.");
        }
        this.mTriggers = collection;
    }

    @Override // com.amazon.weblab.mobile.service.ServiceRequest
    void appendParamToUrl(Uri.Builder builder) {
        super.appendParamToUrl(builder);
        builder.appendEncodedPath("triggers/v2");
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
            JSONArray jSONArray = new JSONArray();
            for (TreatmentAssignment treatmentAssignment : this.mTriggers) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("Weblab", treatmentAssignment.getWeblabName());
                jSONObject.put("Treatment", treatmentAssignment.getTreatment());
                jSONObject.put("AllocationVersion", treatmentAssignment.getVersion());
                jSONArray.put(jSONObject);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("Triggers", jSONArray);
            return jSONObject2.toString();
        } catch (JSONException e) {
            throw new MobileWeblabException("Cannot generate JSON object.", e);
        }
    }
}
