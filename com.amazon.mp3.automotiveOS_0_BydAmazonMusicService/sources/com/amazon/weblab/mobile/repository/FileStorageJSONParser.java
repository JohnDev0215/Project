package com.amazon.weblab.mobile.repository;

import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
class FileStorageJSONParser {
    private JSONObject mRoot;

    FileStorageJSONParser() {
        this.mRoot = new JSONObject();
    }

    FileStorageJSONParser(String str) throws JSONException {
        this.mRoot = new JSONObject(str);
    }

    FileStorageJSONParser putSessionInfo(SessionInfo sessionInfo) throws JSONException, IllegalArgumentException {
        if (sessionInfo == null) {
            throw new IllegalArgumentException("sessionInfo cannot be null");
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("session_id", sessionInfo.getSessionId());
        jSONObject.put("marketplace_id", sessionInfo.getMarketplaceId());
        this.mRoot.put("session_info", jSONObject);
        return this;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    FileStorageJSONParser putTreatmentAssignments(Collection<TreatmentAssignment> collection) throws JSONException, IllegalArgumentException {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("treatmentAssignments cannot be null nor empty");
        }
        JSONArray jSONArray = new JSONArray();
        for (TreatmentAssignment treatmentAssignment : collection) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("can_trigger", treatmentAssignment.canTrigger());
            jSONObject.put("date_modified", treatmentAssignment.getDateModified());
            jSONObject.put("suggested_expiration", treatmentAssignment.getSuggestedExpiration());
            jSONObject.put("is_locked", treatmentAssignment.isLocked());
            jSONObject.put("treatment", treatmentAssignment.getTreatment());
            jSONObject.put("version", treatmentAssignment.getVersion());
            jSONObject.put("weblab", treatmentAssignment.getWeblabName());
            jSONObject.put("keep_in_cache_date_in_millis", treatmentAssignment.getKeepInCacheDateInMillis());
            jSONArray.put(jSONObject);
        }
        this.mRoot.put("treatment_assignments", jSONArray);
        return this;
    }

    FileStorageJSONParser putApplicationVersion(String str) throws JSONException, IllegalArgumentException {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("appVersion cannot be null nor empty");
        }
        this.mRoot.put("app_version", str);
        return this;
    }

    public String toString() {
        return this.mRoot.toString();
    }

    SessionInfo getSessionInfo() throws JSONException {
        if (!this.mRoot.has("session_info")) {
            return null;
        }
        JSONObject jSONObject = this.mRoot.getJSONObject("session_info");
        return new SessionInfo(jSONObject.getString("session_id"), jSONObject.getString("marketplace_id"));
    }

    Map<String, TreatmentAssignment> getTreatmentAssignments(String str) throws JSONException {
        if (!this.mRoot.has("treatment_assignments")) {
            return null;
        }
        HashMap map = new HashMap();
        JSONArray jSONArray = this.mRoot.getJSONArray("treatment_assignments");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            long jOptLong = jSONObject.optLong("keep_in_cache_date_in_millis");
            if (jOptLong == 0) {
                jOptLong = System.currentTimeMillis();
            }
            TreatmentAssignment treatmentAssignment = new TreatmentAssignment(jSONObject.getString("weblab"), jSONObject.getString("treatment"), jSONObject.getString("version"), Long.valueOf(jSONObject.getLong("date_modified")), Long.valueOf(jSONObject.getLong("suggested_expiration")), jSONObject.getBoolean("can_trigger"), jOptLong);
            treatmentAssignment.setLocked(jSONObject.getBoolean("is_locked"));
            map.put(treatmentAssignment.getWeblabName(), treatmentAssignment);
        }
        return map;
    }

    String getApplicationVersion() throws JSONException {
        if (this.mRoot.has("app_version")) {
            return this.mRoot.getString("app_version");
        }
        return null;
    }
}
