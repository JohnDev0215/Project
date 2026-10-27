package com.amazon.music.marketplace;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public enum MusicTerritory {
    US("US"),
    UK("GB"),
    GERMANY("DE"),
    FRANCE("FR"),
    ITALY("IT"),
    SPAIN("ES"),
    JAPAN("JP"),
    INDIA("IN"),
    CANADA("CA"),
    BRAZIL("BR"),
    MEXICO("MX"),
    AUSTRALIA("AU"),
    NEW_ZEALAND("NZ"),
    SAUDI_ARABIA("SA"),
    EGYPT("EG"),
    UAE("AE"),
    BELGIUM("BE");

    private static final Map<String, MusicTerritory> territoryMap = new HashMap();
    public final String value;

    static {
        for (MusicTerritory musicTerritory : values()) {
            territoryMap.put(musicTerritory.value, musicTerritory);
        }
    }

    MusicTerritory(String str) {
        this.value = str;
    }

    public static MusicTerritory fromValue(String str) {
        return territoryMap.get(str);
    }

    public boolean equalsIgnoreCase(String str) {
        return this.value.equalsIgnoreCase(str);
    }
}
