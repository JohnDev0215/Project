package cerence.ark.assistant.controller.struct;

import androidx.core.app.NotificationCompat;
import cerence.ark.assistant.core.JsonCompliant;
import com.byd.mediacenter.api.media.MediaControl;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class TireInfo implements JsonCompliant {
    private String position;
    private double pressure;
    private String status;

    public void setPosition(String str) {
        this.position = str;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPressure(double d) {
        this.pressure = d;
    }

    public double getPressure() {
        return this.pressure;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getStatus() {
        return this.status;
    }

    public static TireInfo fromJson(String str) throws JSONException {
        TireInfo tireInfo = new TireInfo();
        JSONObject jSONObject = new JSONObject(str);
        tireInfo.position = jSONObject.optString(MediaControl.PARAM_POSITION);
        tireInfo.pressure = jSONObject.optDouble("pressure");
        tireInfo.status = jSONObject.optString(NotificationCompat.CATEGORY_STATUS);
        return tireInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(MediaControl.PARAM_POSITION, this.position);
        jSONObject.put("pressure", this.pressure);
        jSONObject.put(NotificationCompat.CATEGORY_STATUS, this.status);
        return jSONObject.toString();
    }
}
