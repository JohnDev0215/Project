package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PlaceInfo implements JsonCompliant {
    private String address;
    private String city;
    private String country;
    private double distance;
    private String extraInfo;
    private String houseNumber;
    private double latitude;
    private double longitude;
    private String name;
    private String state;
    private List<String> street;
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

    public void setStreet(List<String> list) {
        this.street = list;
    }

    public List<String> getStreet() {
        return this.street;
    }

    public void setHouseNumber(String str) {
        this.houseNumber = str;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public void setExtraInfo(String str) {
        this.extraInfo = str;
    }

    public String getExtraInfo() {
        return this.extraInfo;
    }

    public static PlaceInfo fromJson(String str) throws JSONException {
        PlaceInfo placeInfo = new PlaceInfo();
        JSONObject jSONObject = new JSONObject(str);
        placeInfo.type = jSONObject.optString("type");
        placeInfo.name = jSONObject.optString("name");
        placeInfo.address = jSONObject.optString("address");
        placeInfo.longitude = jSONObject.optDouble("longitude");
        placeInfo.latitude = jSONObject.optDouble("latitude");
        placeInfo.distance = jSONObject.optDouble("distance");
        placeInfo.unitOfDistance = jSONObject.optString("unit_of_distance");
        placeInfo.country = jSONObject.optString("country");
        placeInfo.state = jSONObject.optString("state");
        placeInfo.city = jSONObject.optString("city");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("street");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.optString(i));
            }
        }
        placeInfo.street = arrayList;
        placeInfo.houseNumber = jSONObject.optString("house_number");
        placeInfo.extraInfo = jSONObject.optString("extra_info");
        return placeInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", this.type);
        jSONObject.put("name", this.name);
        jSONObject.put("address", this.address);
        jSONObject.put("longitude", this.longitude);
        jSONObject.put("latitude", this.latitude);
        jSONObject.put("distance", this.distance);
        jSONObject.put("unit_of_distance", this.unitOfDistance);
        jSONObject.put("country", this.country);
        jSONObject.put("state", this.state);
        jSONObject.put("city", this.city);
        JSONArray jSONArray = new JSONArray();
        if (this.street != null) {
            for (int i = 0; i < this.street.size(); i++) {
                jSONArray.put(this.street.get(i));
            }
        }
        jSONObject.put("street", jSONArray);
        jSONObject.put("house_number", this.houseNumber);
        jSONObject.put("extra_info", this.extraInfo);
        return jSONObject.toString();
    }
}
