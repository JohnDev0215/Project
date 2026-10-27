package com.amazon.core.api;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MusicTerritory {
    static final Map<AmazonDomain, String> MusicTerritoryMap = new HashMap<AmazonDomain, String>() { // from class: com.amazon.core.api.MusicTerritory.1
        {
            put(AmazonDomain.US, "US");
            put(AmazonDomain.CA, "CA");
            put(AmazonDomain.MX, "MX");
            put(AmazonDomain.BR, "BR");
            put(AmazonDomain.UK, "GB");
            put(AmazonDomain.DE, "DE");
            put(AmazonDomain.FR, "FR");
            put(AmazonDomain.IT, "IT");
            put(AmazonDomain.ES, "ES");
            put(AmazonDomain.IN, "IN");
            put(AmazonDomain.AE, "AE");
            put(AmazonDomain.SA, "SA");
            put(AmazonDomain.EG, "EG");
            put(AmazonDomain.JP, "JP");
            put(AmazonDomain.AU, "AU");
        }
    };

    public static final String lookupTerritoryByDomain(AmazonDomain amazonDomain) {
        return MusicTerritoryMap.getOrDefault(amazonDomain, "US");
    }
}
