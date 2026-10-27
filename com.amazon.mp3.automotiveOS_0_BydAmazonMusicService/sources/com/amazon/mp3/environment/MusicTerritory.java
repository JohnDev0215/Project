package com.amazon.mp3.environment;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
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
    IRELAND("IE"),
    MEXICO("MX"),
    AUSTRALIA("AU"),
    NEW_ZEALAND("NZ"),
    BELGIUM("BE"),
    NETHERLANDS("NL"),
    COLUMBIA("CO"),
    CHILE("CL"),
    COSTA_RICA("CR"),
    ARGENTINA("AR"),
    PERU("PE");

    private static final Map<String, MusicTerritory> territoryMap = new HashMap();
    public final String value;

    static {
        for (MusicTerritory musicTerritory : values()) {
            territoryMap.put(musicTerritory.value, musicTerritory);
        }
    }

    MusicTerritory(final String value) {
        this.value = value;
    }

    public static MusicTerritory fromValue(final String value) {
        return territoryMap.get(value);
    }

    public boolean equalsIgnoreCase(final String territory) {
        return this.value.equalsIgnoreCase(territory);
    }
}
