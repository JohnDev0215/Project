package com.amazon.core.api;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MarketplaceId {
    static final Map<AmazonDomain, String> MarketplaceIdMap = new HashMap<AmazonDomain, String>() { // from class: com.amazon.core.api.MarketplaceId.1
        {
            put(AmazonDomain.US, "ATVPDKIKX0DER");
            put(AmazonDomain.CA, "A2EUQ1WTGCTBG2");
            put(AmazonDomain.MX, "A1AM78C64UM0Y8");
            put(AmazonDomain.BR, "A2Q3Y263D00KWC");
            put(AmazonDomain.UK, "A1F83G8C2ARO7P");
            put(AmazonDomain.DE, "A1PA6795UKMFR9");
            put(AmazonDomain.FR, "A13V1IB3VIYZZH");
            put(AmazonDomain.IT, "APJ6JRA9NG5V4");
            put(AmazonDomain.ES, "A1RKKUPIHCS9HS");
            put(AmazonDomain.IN, "A21TJRUUN4KGV");
            put(AmazonDomain.AE, "A2VIGQ35RCS4UG");
            put(AmazonDomain.SA, "A17E79C6D8DWNP");
            put(AmazonDomain.EG, "ARBP9OOSHTCHU");
            put(AmazonDomain.JP, "A1VC38T7YXB528");
            put(AmazonDomain.AU, "A39IBJ37TRP1C6");
        }
    };

    public static final String lookupMarketplaceIdByDomain(AmazonDomain amazonDomain) {
        return MarketplaceIdMap.getOrDefault(amazonDomain, "ATVPDKIKX0DER");
    }
}
