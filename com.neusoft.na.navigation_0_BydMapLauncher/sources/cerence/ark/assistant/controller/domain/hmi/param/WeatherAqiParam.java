package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.controller.struct.CurrentWeatherInfo;
import cerence.ark.assistant.controller.struct.DailyWeatherInfo;
import cerence.ark.assistant.controller.struct.HourlyWeatherInfo;
import cerence.ark.assistant.controller.struct.WeatherSearchCriteriaInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class WeatherAqiParam implements JsonCompliant {
    private CurrentWeatherInfo currentInfo;
    private List<DailyWeatherInfo> dayList;
    private int endIndex;
    private String focusType;
    private List<HourlyWeatherInfo> hourList;
    private WeatherSearchCriteriaInfo searchCriteria;
    private int startIndex;
    private String unitOfTemperature;

    public void setStartIndex(int i) {
        this.startIndex = i;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public void setEndIndex(int i) {
        this.endIndex = i;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public void setUnitOfTemperature(String str) {
        this.unitOfTemperature = str;
    }

    public String getUnitOfTemperature() {
        return this.unitOfTemperature;
    }

    public void setSearchCriteria(WeatherSearchCriteriaInfo weatherSearchCriteriaInfo) {
        this.searchCriteria = weatherSearchCriteriaInfo;
    }

    public WeatherSearchCriteriaInfo getSearchCriteria() {
        return this.searchCriteria;
    }

    public void setFocusType(String str) {
        this.focusType = str;
    }

    public String getFocusType() {
        return this.focusType;
    }

    public void setCurrentInfo(CurrentWeatherInfo currentWeatherInfo) {
        this.currentInfo = currentWeatherInfo;
    }

    public CurrentWeatherInfo getCurrentInfo() {
        return this.currentInfo;
    }

    public void setDayList(List<DailyWeatherInfo> list) {
        this.dayList = list;
    }

    public List<DailyWeatherInfo> getDayList() {
        return this.dayList;
    }

    public void setHourList(List<HourlyWeatherInfo> list) {
        this.hourList = list;
    }

    public List<HourlyWeatherInfo> getHourList() {
        return this.hourList;
    }

    public static WeatherAqiParam fromJson(String str) throws JSONException {
        WeatherAqiParam weatherAqiParam = new WeatherAqiParam();
        JSONObject jSONObject = new JSONObject(str);
        weatherAqiParam.startIndex = jSONObject.optInt("start_index");
        weatherAqiParam.endIndex = jSONObject.optInt("end_index");
        weatherAqiParam.unitOfTemperature = jSONObject.optString("unit_of_temperature");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("search_criteria");
        if (jSONObjectOptJSONObject != null) {
            weatherAqiParam.searchCriteria = WeatherSearchCriteriaInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        weatherAqiParam.focusType = jSONObject.optString("focus_type");
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("current_info");
        if (jSONObjectOptJSONObject2 != null) {
            weatherAqiParam.currentInfo = CurrentWeatherInfo.fromJson(jSONObjectOptJSONObject2.toString());
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("day_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(DailyWeatherInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        weatherAqiParam.dayList = arrayList;
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("hour_list");
        ArrayList arrayList2 = new ArrayList();
        if (jSONArrayOptJSONArray2 != null) {
            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                arrayList2.add(HourlyWeatherInfo.fromJson(jSONArrayOptJSONArray2.get(i2).toString()));
            }
        }
        weatherAqiParam.hourList = arrayList2;
        return weatherAqiParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("start_index", this.startIndex);
        jSONObject.put("end_index", this.endIndex);
        jSONObject.put("unit_of_temperature", this.unitOfTemperature);
        if (this.searchCriteria != null) {
            jSONObject.put("search_criteria", new JSONObject(this.searchCriteria.toJsonString()));
        }
        jSONObject.put("focus_type", this.focusType);
        if (this.currentInfo != null) {
            jSONObject.put("current_info", new JSONObject(this.currentInfo.toJsonString()));
        }
        JSONArray jSONArray = new JSONArray();
        if (this.dayList != null) {
            for (int i = 0; i < this.dayList.size(); i++) {
                jSONArray.put(new JSONObject(this.dayList.get(i).toJsonString()));
            }
        }
        jSONObject.put("day_list", jSONArray);
        JSONArray jSONArray2 = new JSONArray();
        if (this.hourList != null) {
            for (int i2 = 0; i2 < this.hourList.size(); i2++) {
                jSONArray2.put(new JSONObject(this.hourList.get(i2).toJsonString()));
            }
        }
        jSONObject.put("hour_list", jSONArray2);
        return jSONObject.toString();
    }
}
