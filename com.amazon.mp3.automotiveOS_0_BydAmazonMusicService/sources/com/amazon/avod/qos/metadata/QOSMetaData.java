package com.amazon.avod.qos.metadata;

import com.google.common.collect.ImmutableMap;

/* JADX INFO: loaded from: classes2.dex */
public class QOSMetaData {
    public static final ImmutableMap<String, String> PLUGIN_NAME_TO_AVAILABILITY_ATTRIBUTES_MAP;
    public static final String TIMED_TEXT;
    public static final String TRICK_PLAY;

    static {
        String strName = QOSEventName.TimedText.name();
        TIMED_TEXT = strName;
        String strName2 = QOSEventName.TrickPlay.name();
        TRICK_PLAY = strName2;
        PLUGIN_NAME_TO_AVAILABILITY_ATTRIBUTES_MAP = new ImmutableMap.Builder().put(strName, "isTimedTextAvailable").put(strName2, "isTrickPlayAvailable").put("XrayForTitle", "drakeAvailable").put("XrayForUser", "drakeEnabled").build();
    }
}
