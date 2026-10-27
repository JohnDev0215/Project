package com.amazon.atv.discovery;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum CollectionType implements NamedEnum {
    CONTINUOUS_PLAYBACK_QUEUE("continuousPlaybackQueue"),
    HORIZONTAL_LIST("horizontalList"),
    SEEDED_CAROUSEL("seededCarousel"),
    ROTATOR_CAROUSEL("rotatorCarousel"),
    GRID_PREVIEW("gridPreview"),
    GRID("grid"),
    TILE("tile"),
    ADS_CAROUSEL("adsCarousel"),
    VERTICAL_LIST("verticalList"),
    CAROUSEL("carousel"),
    SEEDED_GRID_PREVIEW("seededGridPreview"),
    SEEDED_FACETED_CAROUSEL("seededFacetedCarousel"),
    SELECTABLE_TEXT_LINKS("selectableTextLinks"),
    FACETED_CAROUSEL("facetedCarousel"),
    VERTICAL_GROUP("verticalGroup"),
    HERO_CAROUSEL("heroCarousel"),
    BANNER_CAROUSEL("bannerCarousel");

    private final String strValue;

    CollectionType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static CollectionType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (CollectionType collectionType : values()) {
            if (collectionType.strValue.equals(str)) {
                return collectionType;
            }
        }
        return null;
    }
}
