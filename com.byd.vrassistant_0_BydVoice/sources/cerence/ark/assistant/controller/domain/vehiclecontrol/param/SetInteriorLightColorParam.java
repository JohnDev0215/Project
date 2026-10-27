package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetInteriorLightColorParam implements JsonCompliant {
    private String color;
    private int colorNum;
    private String lightType;
    private String vehicleArea;

    public void setColor(String str) {
        this.color = str;
    }

    public String getColor() {
        return this.color;
    }

    public void setColorNum(int i) {
        this.colorNum = i;
    }

    public int getColorNum() {
        return this.colorNum;
    }

    public void setLightType(String str) {
        this.lightType = str;
    }

    public String getLightType() {
        return this.lightType;
    }

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public static SetInteriorLightColorParam fromJson(String str) throws JSONException {
        SetInteriorLightColorParam setInteriorLightColorParam = new SetInteriorLightColorParam();
        JSONObject jSONObject = new JSONObject(str);
        setInteriorLightColorParam.color = jSONObject.optString("color");
        setInteriorLightColorParam.colorNum = jSONObject.optInt("color_num");
        setInteriorLightColorParam.lightType = jSONObject.optString("light_type");
        setInteriorLightColorParam.vehicleArea = jSONObject.optString("vehicle_area");
        return setInteriorLightColorParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("color", this.color);
        jSONObject.put("color_num", this.colorNum);
        jSONObject.put("light_type", this.lightType);
        jSONObject.put("vehicle_area", this.vehicleArea);
        return jSONObject.toString();
    }
}
