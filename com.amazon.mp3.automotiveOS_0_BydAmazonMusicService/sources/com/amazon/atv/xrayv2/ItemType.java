package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ItemType implements NamedEnum {
    ADS_TITLE_CARD("adsTitleCard"),
    XRAY_RELATED_COLLECTION_WITH_ICONS_BLUEPRINTED_ITEM("xrayRelatedCollectionWithIconsBlueprintedItem"),
    TITLE_CARD("titleCard"),
    DETAIL_HEADER("detailHeader"),
    IMAGE_LINK("imageLink"),
    HERO_TITLE("heroTitle"),
    XRAY_GRAPHED_ITEM("xrayGraphedItem"),
    RELATED_COLLECTION_BLUEPRINTED_ITEM("relatedCollectionBlueprintedItem"),
    XRAY_TRICKPLAY_ITEM("xrayTrickplayItem"),
    VIDEO_ROLL("videoRoll"),
    PERSON("person"),
    SPLASH_IMAGE("splashImage"),
    XRAY_CHECKABLE_ANSWER_ITEM("xrayCheckableAnswerItem"),
    XRAY_SCOREBOARD_ITEM("xrayScoreboardItem"),
    ROTATOR_ITEM("rotatorItem"),
    LIVE_LINEAR_CARD("liveLinearCard"),
    TEXT_LINK("textLink"),
    LIVE_CARD("liveCard"),
    BENEFIT_CARD("benefitCard"),
    PLACE_HOLDER("placeHolder"),
    ITEM_WITH_BLUEPRINT("itemWithBlueprint"),
    LIVE_CHANNEL_CARD("liveChannelCard"),
    BLUEPRINTED_ITEM("blueprintedItem"),
    IMAGE_TEXT_LINK("imageTextLink"),
    XRAY_MULTIPLE_CHOICE_QUESTION_ITEM("xrayMultipleChoiceQuestionItem");

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
