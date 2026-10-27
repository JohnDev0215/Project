package cerence.ark.assistant.controller.domain.phone.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.PhoneNumberInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetPhoneNumberResult extends BaseResult {
    private List<PhoneNumberInfo> numberList;

    public void setNumberList(List<PhoneNumberInfo> list) {
        this.numberList = list;
    }

    public List<PhoneNumberInfo> getNumberList() {
        return this.numberList;
    }

    public static GetPhoneNumberResult fromJson(String str) throws JSONException {
        GetPhoneNumberResult getPhoneNumberResult = new GetPhoneNumberResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("number_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PhoneNumberInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        getPhoneNumberResult.numberList = arrayList;
        return getPhoneNumberResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.numberList != null) {
            for (int i = 0; i < this.numberList.size(); i++) {
                jSONArray.put(new JSONObject(this.numberList.get(i).toJsonString()));
            }
        }
        jSONObject.put("number_list", jSONArray);
        return jSONObject.toString();
    }
}
