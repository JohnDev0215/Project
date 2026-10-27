package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class HintsAndLeadingQuestionsUpdateParam implements JsonCompliant {
    private List<String> hintsList;
    private String leadingQuestions;

    public void setHintsList(List<String> list) {
        this.hintsList = list;
    }

    public List<String> getHintsList() {
        return this.hintsList;
    }

    public void setLeadingQuestions(String str) {
        this.leadingQuestions = str;
    }

    public String getLeadingQuestions() {
        return this.leadingQuestions;
    }

    public static HintsAndLeadingQuestionsUpdateParam fromJson(String str) throws JSONException {
        HintsAndLeadingQuestionsUpdateParam hintsAndLeadingQuestionsUpdateParam = new HintsAndLeadingQuestionsUpdateParam();
        JSONObject jSONObject = new JSONObject(str);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("hints_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.get(i).toString());
            }
        }
        hintsAndLeadingQuestionsUpdateParam.hintsList = arrayList;
        hintsAndLeadingQuestionsUpdateParam.leadingQuestions = jSONObject.optString("leading_questions");
        return hintsAndLeadingQuestionsUpdateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.hintsList != null) {
            for (int i = 0; i < this.hintsList.size(); i++) {
                jSONArray.put(this.hintsList.get(i));
            }
        }
        jSONObject.put("hints_list", jSONArray);
        jSONObject.put("leading_questions", this.leadingQuestions);
        return jSONObject.toString();
    }
}
