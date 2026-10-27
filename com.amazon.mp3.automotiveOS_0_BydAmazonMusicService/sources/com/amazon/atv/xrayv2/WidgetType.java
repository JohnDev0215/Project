package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum WidgetType implements NamedEnum {
    COLLECTION("collection"),
    ITEMS_WIDGETS("itemsWidget"),
    TIME_INDEXED_COLLECTION("timeIndexedCollection"),
    CLIP_EDITOR("clipEditor"),
    GRID("grid"),
    DATA_WIDGET("dataWidget"),
    XRAY_TAB("xrayTab"),
    TIME_INDEXED_WIDGET_GROUP("timeIndexedWidgetGroup"),
    ADS_CAROUSEL("adsCarousel"),
    NAVIGATIONS("navigations"),
    VERTICAL_LIST("verticalList"),
    CAROUSEL("carousel"),
    FACETED_CAROUSEL("facetedCarousel"),
    XRAY_FLOATABLE_WIDGET("xrayFloatableWidget"),
    HERO_CAROUSEL("heroCarousel"),
    BANNER_CAROUSEL("bannerCarousel"),
    WIDGET_GROUP("widgetGroup");

    private final String strValue;

    WidgetType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static WidgetType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (WidgetType widgetType : values()) {
            if (widgetType.strValue.equals(str)) {
                return widgetType;
            }
        }
        return null;
    }
}
