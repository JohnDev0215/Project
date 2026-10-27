package com.byd.card.floatcard;

/* JADX INFO: loaded from: classes.dex */
public class CardItemBean {
    public static final String TYPE_CARD_APP = "type_card_app";
    public static final String TYPE_CARD_SHORTCUT = "type_card_shortcut";
    public static final String TYPE_CARD_WIDGET = "type_card_widget";
    private String activity;
    private String packageName;
    private String type;

    public CardItemBean(String packageName, String type) {
        this.packageName = packageName;
        this.type = type;
    }

    public CardItemBean(String packageName, String activity, String type) {
        this.packageName = packageName;
        this.activity = activity;
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static CardItemBean unFlattenFromString(String str) {
        int i;
        int iIndexOf = str.indexOf("$");
        if (iIndexOf < 0 || (i = iIndexOf + 1) >= str.length()) {
            return null;
        }
        return new CardItemBean(str.substring(0, iIndexOf), str.substring(i));
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String flattenToString() {
        return this.packageName + "$" + this.type;
    }

    public String toString() {
        return "CardItemBean{packageName='" + this.packageName + "', activity='" + this.activity + "', type='" + this.type + "'}";
    }
}
