package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import com.byd.common.bean.WuwInfoBean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MeasurementInfo implements JsonCompliant {
    private String type;
    private String unit;
    private String value;

    public void setType(String str) {
        this.type = str;
    }

    public String getType() {
        return this.type;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public static MeasurementInfo fromJson(String str) throws JSONException {
        MeasurementInfo measurementInfo = new MeasurementInfo();
        JSONObject jSONObject = new JSONObject(str);
        measurementInfo.type = jSONObject.optString("type");
        measurementInfo.unit = jSONObject.optString("unit");
        measurementInfo.value = jSONObject.optString(WuwInfoBean.ValueKey);
        return measurementInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", this.type);
        jSONObject.put("unit", this.unit);
        jSONObject.put(WuwInfoBean.ValueKey, this.value);
        return jSONObject.toString();
    }
}
