package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum LinkType implements NamedEnum {
    BROWSE("browse"),
    SETTINGS("settings"),
    EXTERNALLINK("externalLink"),
    YVL("yvl"),
    EXTERNALAPP("externalApp"),
    SIGNUP("signup"),
    ENTITY("entity"),
    INAPPLINK("inAppLink"),
    SHOWCASE("showcase"),
    LANDING("landing"),
    INAPPMESSAGE("inAppMessage"),
    REGISTRATION("registration"),
    XRAY("xray"),
    NONE("none"),
    DOWNLOADS("downloads"),
    HELP("help"),
    RECENTLYWATCHED("recentlyWatched"),
    SEARCH("search"),
    WATCHLIST("watchlist"),
    PRIMESIGNUP("primeSignup"),
    DEREGISTRATION("deregistration"),
    DETAIL("detail"),
    CLIENT_SEARCH_INPUT("clientSearchInput"),
    ZOOM_IN("zoomIn"),
    PLAYER("player"),
    HOME("home");

    private final String strValue;

    LinkType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static LinkType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (LinkType linkType : values()) {
            if (linkType.strValue.equals(str)) {
                return linkType;
            }
        }
        return null;
    }
}
