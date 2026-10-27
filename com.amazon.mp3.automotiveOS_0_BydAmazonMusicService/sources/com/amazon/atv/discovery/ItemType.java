package com.amazon.atv.discovery;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ItemType implements NamedEnum {
    ADS_TITLE_CARD("adsTitleCard"),
    XRAY_RELATED_COLLECTION_WITH_ICONS_BLUEPRINTED_ITEM("xrayRelatedCollectionWithIconsBlueprintedItem"),
    PERSON("person"),
    TITLE_CARD("titleCard"),
    SPLASH_IMAGE("splashImage"),
    XRAY_SCOREBOARD_ITEM("xrayScoreboardItem"),
    ROTATOR_ITEM("rotatorItem"),
    LIVE_LINEAR_CARD("liveLinearCard"),
    DETAIL_HEADER("detailHeader"),
    TEXT_LINK("textLink"),
    IMAGE_LINK("imageLink"),
    LIVE_CARD("liveCard"),
    BENEFIT_CARD("benefitCard"),
    PLACE_HOLDER("placeHolder"),
    HERO_TITLE("heroTitle"),
    XRAY_GRAPHED_ITEM("xrayGraphedItem"),
    RELATED_COLLECTION_BLUEPRINTED_ITEM("relatedCollectionBlueprintedItem"),
    LIVE_CHANNEL_CARD("liveChannelCard"),
    VOD_EXTRA_CONTENT("vodExtraContent"),
    BLUEPRINTED_ITEM("blueprintedItem"),
    XRAY_TRICKPLAY_ITEM("xrayTrickplayItem"),
    IMAGE_TEXT_LINK("imageTextLink"),
    VIDEO_ROLL("videoRoll");

    private final String strValue;

    ItemType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ItemType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ItemType itemType : values()) {
            if (itemType.strValue.equals(str)) {
                return itemType;
            }
        }
        return null;
    }
}
