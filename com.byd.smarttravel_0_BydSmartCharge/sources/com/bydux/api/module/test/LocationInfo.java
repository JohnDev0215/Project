package com.bydux.api.module.test;

import com.byd.shareddata.DataUpdateInterface;

/* JADX INFO: loaded from: classes.dex */
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

        void setLocationInfo(LocationInfo locationInfo);
    }

    public LocationInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, double d2, double d3, float f, long j) {
        this.country = str;
        this.countryCode = str2;
        this.province = str3;
        this.city = str4;
        this.adCode = str5;
        this.cityCode = str6;
        this.district = str7;
        this.detailAdress = str8;
        this.latitude = d2;
        this.longitude = d3;
        this.speed = f;
        this.num = j;
    }

    public String getAdCode() {
        return this.adCode;
    }

    public String getCity() {
        return this.city;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getDetailAdress() {
        return this.detailAdress;
    }

    public String getDistrict() {
        return this.district;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public long getNum() {
        return this.num;
    }

    public String getProvince() {
        return this.province;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setAdCode(String str) {
        this.adCode = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setCityCode(String str) {
        this.cityCode = str;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setDetailAdress(String str) {
        this.detailAdress = str;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public void setLatitude(double d2) {
        this.latitude = d2;
    }

    public void setLongitude(double d2) {
        this.longitude = d2;
    }

    public void setNum(long j) {
        this.num = j;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public void setSpeed(float f) {
        this.speed = f;
    }
}
