package com.amazon.music.social;

import com.amazon.music.marketplace.Marketplace;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class ServiceEnvironment_Prod extends ServiceEnvironment {
    public ServiceEnvironment_Prod() {
        super("Social_Prod");
    }

    @Override // com.amazon.music.social.ServiceEnvironment
    protected Map<Marketplace, String> getContent() {
        HashMap map = new HashMap();
        map.put(Marketplace.USA, "https://music.amazon.com/api/graph/");
        map.put(Marketplace.UK, "https://music.amazon.co.uk/api/graph/");
        map.put(Marketplace.GERMANY, "https://music.amazon.de/api/graph/");
        map.put(Marketplace.SPAIN, "https://music.amazon.es/api/graph/");
        map.put(Marketplace.FRANCE, "https://music.amazon.fr/api/graph/");
        map.put(Marketplace.ITALY, "https://music.amazon.it/api/graph/");
        map.put(Marketplace.JAPAN, "https://music.amazon.co.jp/api/graph/");
        map.put(Marketplace.ROW_NA, "https://music.amazon.com/NA/api/graph/");
        map.put(Marketplace.ROE_EU, "https://music.amazon.com/EU/api/graph/");
        map.put(Marketplace.ROW_FE, "https://music.amazon.com/FE/api/graph/");
        return map;
    }
}
