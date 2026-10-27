package com.apollographql.apollo3.network.ws;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class WsFrameType {
    public static final WsFrameType c;
    public static final /* synthetic */ WsFrameType[] e;

    static {
        WsFrameType wsFrameType = new WsFrameType("Text", 0);
        c = wsFrameType;
        e = new WsFrameType[]{wsFrameType, new WsFrameType("Binary", 1)};
    }

    public static WsFrameType valueOf(String str) {
        return (WsFrameType) Enum.valueOf(WsFrameType.class, str);
    }

    public static WsFrameType[] values() {
        return (WsFrameType[]) e.clone();
    }
}
