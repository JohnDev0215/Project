package com.spotify.core.core;

import java.util.Locale;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public enum DeviceType {
    COMPUTER(1),
    TABLET(2),
    SMARTPHONE(3),
    SPEAKER(4),
    TV(5),
    AVR(6),
    STB(7),
    AUDIODONGLE(8),
    GAMECONSOLE(9),
    CASTVIDEO(10),
    CASTAUDIO(11),
    AUTOMOBILE(12),
    SMARTWATCH(13),
    CHROMEBOOK(14);

    private final int value;

    DeviceType(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        String strName = name();
        Locale locale = Locale.US;
        v03.i(locale, "US");
        String lowerCase = strName.toLowerCase(locale);
        v03.i(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        return lowerCase;
    }
}
