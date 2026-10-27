package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import com.neusoft.na.ocservice.MotsBl;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PlaceInfoResult extends BaseResult {
    private String address;
    private String city;
    private String country;
    private double distance;
    private double latitude;
    private double longitude;
    private String name;
    private String state;
    private String type;
    private String unitOfDistance;

    public void setType(String str) {
        this.type = str;
    }

    public String getType() {
        return this.type;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setUnitOfDistance(String str) {
        this.unitOfDistance = str;
    }

    public String getUnitOfDistance() {
        return this.unitOfDistance;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getState() {
        return this.state;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getCity() {
        return this.city;
    }

    public static PlaceInfoResult fromJson(String str) throws JSONException {
        PlaceInfoResult placeInfoResult = new PlaceInfoResult();
        JSONObject jSONObject = new JSONObject(str);
        placeInfoResult.type = jSONObject.optString("type");
        placeInfoResult.name = jSONObject.optString("name");
        placeInfoResult.address = jSONObject.optString("address");
        placeInfoResult.longitude = jSONObject.optDouble("longitude");
        placeInfoResult.latitude = jSONObject.optDouble("latitude");
        placeInfoResult.distance = jSONObject.optDouble("distance");
        placeInfoResult.unitOfDistance = jSONObject.optString("unit_of_distance");
        placeInfoResult.country = jSONObject.optString(MotsBl.JS_PROPERTY_LOCALE_COUNTRY);
        placeInfoResult.state = jSONObject.optString("state");
        placeInfoResult.city = jSONObject.optString("city");
        return placeInfoResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", this.type);
        jSONObject.put("name", this.name);
        jSONObject.put("address", this.address);
        jSONObject.put("longitude", this.longitude);
        jSONObject.put("latitude", this.latitude);
        jSONObject.put("distance", this.distance);
        jSONObject.put("unit_of_distance", this.unitOfDistance);
        jSONObject.put(MotsBl.JS_PROPERTY_LOCALE_COUNTRY, this.country);
        jSONObject.put("state", this.state);
        jSONObject.put("city", this.city);
        return jSONObject.toString();
    }
}
