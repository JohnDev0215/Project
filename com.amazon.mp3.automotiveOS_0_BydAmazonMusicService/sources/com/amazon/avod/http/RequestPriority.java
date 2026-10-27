package com.amazon.avod.http;

import com.amazon.music.skyfire.ui.skyfire.Methods;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum RequestPriority {
    CRITICAL("CRITICAL"),
    BACKGROUND("BACKGROUND"),
    NOTIFICATION(Methods.notification);

    private final String mPriorityString;

    RequestPriority(@Nonnull String str) {
        this.mPriorityString = str;
    }

    @Nonnull
    public String getPriorityString() {
        return this.mPriorityString;
    }

    public static RequestPriority getRequestPriority(String str) {
        if (str == null) {
            return CRITICAL;
        }
        try {
            return valueOf(str);
        } catch (IllegalArgumentException unused) {
            return CRITICAL;
        }
    }
}
