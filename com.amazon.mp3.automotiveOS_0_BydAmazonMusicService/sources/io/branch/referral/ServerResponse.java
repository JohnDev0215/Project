package io.branch.referral;

import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
public class ServerResponse {
    private Object post_;
    private int statusCode_;
    private String tag_;

    public ServerResponse(String str, int i) {
        this.tag_ = str;
        this.statusCode_ = i;
    }

    public int getStatusCode() {
        return this.statusCode_;
    }

    public void setPost(Object obj) {
        this.post_ = obj;
    }

    public JSONObject getObject() {
        Object obj = this.post_;
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        return new JSONObject();
    }

    public JSONArray getArray() {
        Object obj = this.post_;
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        return null;
    }

    public String getFailReason() {
        try {
            JSONObject object = getObject();
            if (object == null || !object.has("error") || !object.getJSONObject("error").has("message")) {
                return "";
            }
            String string = object.getJSONObject("error").getString("message");
            if (string == null || string.trim().length() <= 0) {
                return string;
            }
            return string + ".";
        } catch (Exception unused) {
            return "";
        }
    }
}
