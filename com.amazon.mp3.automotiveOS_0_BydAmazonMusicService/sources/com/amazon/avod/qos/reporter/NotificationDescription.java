package com.amazon.avod.qos.reporter;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum NotificationDescription {
    WAN_VIDEO_DISABLED("WAN Video Disabled Dialog"),
    NO_WIFI_DIALOG("No Wifi Dialog"),
    BUFFERING_LIMIT_REACHED_DIALOG("Buffering Limit Reached Dialog");


    @Nonnull
    private final String text;

    NotificationDescription(@Nonnull String str) {
        this.text = str;
    }

    @Nonnull
    public String getText() {
        return this.text;
    }
}
