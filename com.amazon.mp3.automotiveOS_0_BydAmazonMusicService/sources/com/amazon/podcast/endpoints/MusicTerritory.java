package com.amazon.podcast.endpoints;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum MusicTerritory {
    US("US"),
    UK("GB"),
    GERMANY("DE"),
    FRANCE("FR"),
    ITALY("IT"),
    SPAIN("ES"),
    IRELAND("IE"),
    JAPAN("JP"),
    AUSTRALIA("AU"),
    NEW_ZEALAND("NZ"),
    INDIA("IN"),
    CANADA("CA"),
    BRAZIL("BR"),
    MEXICO("MX"),
    COLOMBIA("CO"),
    CHILE("CL"),
    SAUDIARABIA("SA"),
    UAE("AE"),
    EGYPT("EG"),
    ARGENTINA("AR"),
    PERU("PR"),
    ECUADOR("EC"),
    COSTRICA("CR"),
    BOLIVIA("BR"),
    GUATEMALA("GT"),
    NICARAGUA("NI"),
    PARAGUAY("PY"),
    ELSALADOR("SV"),
    URUGUAY("UY"),
    PANAMA("PA"),
    DOMINICANREPUBLIC("DO"),
    HONDURAS("HN");

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
