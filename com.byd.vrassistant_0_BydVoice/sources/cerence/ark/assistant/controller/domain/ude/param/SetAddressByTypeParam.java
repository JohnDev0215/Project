package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.controller.struct.PlaceInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetAddressByTypeParam implements JsonCompliant {
    private PlaceInfo address;
    private String addressType;

    public void setAddressType(String str) {
        this.addressType = str;
    }

    public String getAddressType() {
        return this.addressType;
    }

    public void setAddress(PlaceInfo placeInfo) {
        this.address = placeInfo;
    }

    public PlaceInfo getAddress() {
        return this.address;
    }

    public static SetAddressByTypeParam fromJson(String str) throws JSONException {
        SetAddressByTypeParam setAddressByTypeParam = new SetAddressByTypeParam();
        JSONObject jSONObject = new JSONObject(str);
        setAddressByTypeParam.addressType = jSONObject.optString("address_type");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("address");
        if (jSONObjectOptJSONObject != null) {
            setAddressByTypeParam.address = PlaceInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return setAddressByTypeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("address_type", this.addressType);
        if (this.address != null) {
            jSONObject.put("address", new JSONObject(this.address.toJsonString()));
        }
        return jSONObject.toString();
    }
}
