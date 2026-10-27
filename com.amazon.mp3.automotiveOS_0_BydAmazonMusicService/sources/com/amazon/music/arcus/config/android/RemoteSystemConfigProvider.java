package com.amazon.music.arcus.config.android;

import android.content.Context;
import com.amazon.music.arcus.ArcusUtils;
import com.amazon.music.arcus.ConfigurationManager;
import com.amazon.music.arcus.RemoteConfigProvider;
import com.amazon.music.arcus.RemoteConfigRequest;
import com.amazon.music.destination.parser.ParserUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class RemoteSystemConfigProvider extends RemoteConfigProvider implements SystemConfigProvider {
    public RemoteSystemConfigProvider(Context context, RemoteConfigRequest remoteConfigRequest) {
        super(context, remoteConfigRequest);
    }

    @Override // com.amazon.music.arcus.RemoteConfigProvider
    protected Map<String, Object> getSegmentAttributes(RemoteConfigRequest remoteConfigRequest) {
        return SystemConfigurationFactory.getSegmentAttributes(remoteConfigRequest);
    }

    @Override // com.amazon.music.arcus.RemoteConfigProvider
    protected ConfigurationManager getConfigurationManager(Context context, RemoteConfigRequest remoteConfigRequest) {
        return SystemConfigurationFactory.createConfigurationManager(context, remoteConfigRequest, this.segmentAttributes);
    }

    @Override // com.amazon.music.arcus.config.android.SystemConfigProvider
    public String getLogLevel() throws Exception {
        return (String) getRemoteValue("log_level", String.class);
    }

    @Override // com.amazon.music.arcus.config.android.SystemConfigProvider
    public Map<String, String> getLogLevelTagMap() throws Exception {
        JSONArray jSONArray = (JSONArray) getRemoteValue("log_tags", JSONArray.class);
        if (jSONArray == null) {
            return null;
        }
        HashMap map = new HashMap(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            addTagObjectToMap(map, jSONArray.getJSONObject(i));
        }
        return map;
    }

    private void addTagObjectToMap(Map<String, String> map, JSONObject jSONObject) throws JSONException {
        String string;
        if (jSONObject == null || (string = jSONObject.getString(ParserUtil.TAG_QUERY_PARAM_NAME)) == null) {
            return;
        }
        map.put(string, jSONObject.getString("log_level"));
    }

    @Override // com.amazon.music.arcus.config.android.SystemConfigProvider
    public Date getUploadLogRequestDate(String str) throws Exception {
        JSONArray jSONArray = (JSONArray) getRemoteValue("customer_logs", JSONArray.class);
        if (jSONArray == null) {
            return null;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String strOptString = jSONObject.optString("date_requested");
            if (str.equals(jSONObject.optString("dsn"))) {
                return ArcusUtils.toDate(strOptString);
            }
        }
        return null;
    }
}
