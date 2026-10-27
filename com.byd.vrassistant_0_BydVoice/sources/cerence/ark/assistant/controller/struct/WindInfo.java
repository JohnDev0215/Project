package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class WindInfo implements JsonCompliant {
    private int direction;
    private String directionCode;
    private MeasurementInfo speed;
    private int strengthCode;

    public void setDirection(int i) {
        this.direction = i;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirectionCode(String str) {
        this.directionCode = str;
    }

    public String getDirectionCode() {
        return this.directionCode;
    }

    public void setSpeed(MeasurementInfo measurementInfo) {
        this.speed = measurementInfo;
    }

    public MeasurementInfo getSpeed() {
        return this.speed;
    }

    public void setStrengthCode(int i) {
        this.strengthCode = i;
    }

    public int getStrengthCode() {
        return this.strengthCode;
    }

    public static WindInfo fromJson(String str) throws JSONException {
        WindInfo windInfo = new WindInfo();
        JSONObject jSONObject = new JSONObject(str);
        windInfo.direction = jSONObject.optInt("direction");
        windInfo.directionCode = jSONObject.optString("directionCode");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("speed");
        if (jSONObjectOptJSONObject != null) {
            windInfo.speed = MeasurementInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        windInfo.strengthCode = jSONObject.optInt("strengthCode");
        return windInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("direction", this.direction);
        jSONObject.put("directionCode", this.directionCode);
        if (this.speed != null) {
            jSONObject.put("speed", new JSONObject(this.speed.toJsonString()));
        }
        jSONObject.put("strengthCode", this.strengthCode);
        return jSONObject.toString();
    }
}
