package com.byd.vrassistant.bean;

import cerence.ark.assistant.controller.enumeration.PlaceTypeEnum;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import com.byd.naviserviceapi.Place;

/* JADX INFO: loaded from: classes.dex */
public class BeanTransform {
    public static Place getWithPlaceInfo(PlaceInfo placeInfo) {
        Place place = new Place();
        place.setId(placeInfo.getExtraInfo());
        place.setAddress(placeInfo.getAddress());
        place.setCity(placeInfo.getCity());
        place.setCountry(placeInfo.getCountry());
        place.setDistance(placeInfo.getDistance());
        place.setHouse_number(placeInfo.getHouseNumber());
        place.setLatitude(placeInfo.getLatitude());
        place.setLongitude(placeInfo.getLongitude());
        place.setName(placeInfo.getName());
        place.setState(placeInfo.getState());
        place.setCategoryId(new String[]{"0"});
        if (placeInfo.getStreet() != null && placeInfo.getStreet().size() > 0) {
            place.setStreet((String[]) placeInfo.getStreet().toArray(new String[0]));
        } else {
            place.setStreet(new String[]{"0"});
        }
        if (PlaceTypeEnum.POI.equals(placeInfo.getType())) {
            place.setType(1);
        } else if (PlaceTypeEnum.ADDRESS.equals(placeInfo.getType())) {
            place.setType(2);
        }
        return place;
    }
}
