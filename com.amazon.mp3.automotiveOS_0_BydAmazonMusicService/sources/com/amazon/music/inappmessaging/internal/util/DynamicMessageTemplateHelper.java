package com.amazon.music.inappmessaging.internal.util;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageTemplateHelper {
    private static final String DISMISS_ACTION = "action:/dismiss";

    public static boolean isDismissButton(final String buttonLink) {
        return buttonLink != null && buttonLink.startsWith(DISMISS_ACTION);
    }
}
