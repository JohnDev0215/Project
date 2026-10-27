package com.bydux.api.module.test;

import com.byd.shareddata.DataUpdateInterface;

/* JADX INFO: loaded from: classes2.dex */
public class LocationInfo {
    public String adCode;
    public String city;
    public String cityCode;
    public String country;
    public String countryCode;
    public String detailAdress;
    public String district;
    public double latitude;
    public double longitude;
    public long num;
    public String province;
    public float speed;

    public interface LocationInfoInterface extends DataUpdateInterface {
        LocationInfo getLocationInfo();

        void setLocationInfo(LocationInfo var1);
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdCode() {
        return this.adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetailAdress() {
        return this.detailAdress;
    }

    public void setDetailAdress(String detailAdress) {
        this.detailAdress = detailAdress;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public long getNum() {
        return this.num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public LocationInfo(String country, String countryCode, String province, String city, String adCode, String cityCode, String district, String detailAdress, double latitude, double longitude, float speed, long num) {
        this.country = country;
        this.countryCode = countryCode;
        this.province = province;
        this.city = city;
        this.adCode = adCode;
        this.cityCode = cityCode;
        this.district = district;
        this.detailAdress = detailAdress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.num = num;
    }
}
